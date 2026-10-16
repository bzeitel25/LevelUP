package io.github.bzeitel25.levelup;

import android.app.AppOpsManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Linked apps (v0.27): reads how long other apps were open, so Level UP can offer
 * "Found 15 min in Duolingo, log it?". Needs Usage access, which the player turns on
 * in system settings. Nothing runs in the background: the web app asks when it opens.
 */
@CapacitorPlugin(name = "LevelUpUsage")
public class LevelUpUsagePlugin extends Plugin {
    private static final int FOREGROUND = 1;   // UsageEvents.Event.ACTIVITY_RESUMED (MOVE_TO_FOREGROUND before API 29)
    private static final int BACKGROUND = 2;   // UsageEvents.Event.ACTIVITY_PAUSED (MOVE_TO_BACKGROUND before API 29)

    private boolean granted() {
        AppOpsManager ops = (AppOpsManager) getContext().getSystemService(Context.APP_OPS_SERVICE);
        if (ops == null) return false;
        int mode;
        if (Build.VERSION.SDK_INT >= 29) {
            mode = ops.unsafeCheckOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), getContext().getPackageName());
        } else {
            mode = ops.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), getContext().getPackageName());
        }
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    private String label(String pkg) {
        try {
            PackageManager pm = getContext().getPackageManager();
            return String.valueOf(pm.getApplicationLabel(pm.getApplicationInfo(pkg, 0)));
        } catch (Exception e) {
            return pkg;
        }
    }

    private static long dayStart(int daysBack) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DAY_OF_YEAR, -daysBack);
        return c.getTimeInMillis();
    }

    @PluginMethod
    public void status(PluginCall call) {
        JSObject r = new JSObject();
        r.put("granted", granted());
        call.resolve(r);
    }

    @PluginMethod
    public void openSettings(PluginCall call) {
        Intent i = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(i);
        call.resolve();
    }

    /** Apps used in the last 7 days, most used first, with their names. */
    @PluginMethod
    public void apps(PluginCall call) {
        if (!granted()) { call.reject("no-access"); return; }
        UsageStatsManager usm = (UsageStatsManager) getContext().getSystemService(Context.USAGE_STATS_SERVICE);
        long end = System.currentTimeMillis(), start = dayStart(6);
        List<UsageStats> stats = usm.queryUsageStats(UsageStatsManager.INTERVAL_BEST, start, end);
        Map<String, Long> total = new HashMap<>();
        if (stats != null) {
            for (UsageStats s : stats) {
                String p = s.getPackageName();
                Long prev = total.get(p);
                total.put(p, (prev == null ? 0L : prev) + s.getTotalTimeInForeground());
            }
        }
        String me = getContext().getPackageName();
        List<Map.Entry<String, Long>> list = new ArrayList<>(total.entrySet());
        java.util.Collections.sort(list, (a, b) -> Long.compare(b.getValue(), a.getValue()));
        JSArray out = new JSArray();
        for (Map.Entry<String, Long> e : list) {
            if (e.getValue() < 60000L || e.getKey().equals(me)) continue;
            JSObject o = new JSObject();
            o.put("pkg", e.getKey());
            o.put("name", label(e.getKey()));
            o.put("min", Math.round(e.getValue() / 60000.0));
            out.put(o);
            if (out.length() >= 60) break;
        }
        JSObject r = new JSObject();
        r.put("apps", out);
        call.resolve(r);
    }

    /** Foreground minutes per day (local yyyy-MM-dd) for the given packages over the last `days` days. */
    @PluginMethod
    public void usage(PluginCall call) {
        if (!granted()) { call.reject("no-access"); return; }
        Integer daysArg = call.getInt("days", 3);
        int days = Math.max(1, Math.min(7, daysArg == null ? 3 : daysArg));
        Set<String> want = new HashSet<>();
        JSArray pk = call.getArray("packages");
        if (pk != null) {
            try { for (Object o : pk.toList()) want.add(String.valueOf(o)); } catch (Exception ignored) { }
        }
        UsageStatsManager usm = (UsageStatsManager) getContext().getSystemService(Context.USAGE_STATS_SERVICE);
        long end = System.currentTimeMillis(), start = dayStart(days - 1);
        UsageEvents ev = usm.queryEvents(start, end);
        Map<String, Long> open = new HashMap<>();
        Map<String, Map<String, Long>> out = new HashMap<>();
        UsageEvents.Event e = new UsageEvents.Event();
        while (ev != null && ev.hasNextEvent()) {
            ev.getNextEvent(e);
            String p = e.getPackageName();
            if (!want.isEmpty() && !want.contains(p)) continue;
            int t = e.getEventType();
            if (t == FOREGROUND) {
                if (!open.containsKey(p)) open.put(p, e.getTimeStamp());
            } else if (t == BACKGROUND) {
                Long s = open.remove(p);
                if (s != null) add(out, p, s, e.getTimeStamp());
            }
        }
        for (Map.Entry<String, Long> o : open.entrySet()) add(out, o.getKey(), o.getValue(), end);
        JSObject daysObj = new JSObject();
        for (Map.Entry<String, Map<String, Long>> d : out.entrySet()) {
            JSObject per = new JSObject();
            for (Map.Entry<String, Long> x : d.getValue().entrySet()) per.put(x.getKey(), Math.floor(x.getValue() / 60000.0));
            daysObj.put(d.getKey(), per);
        }
        JSObject r = new JSObject();
        r.put("days", daysObj);
        call.resolve(r);
    }

    // Adds a stretch of foreground time, split at midnight so each day gets its own share.
    private static void add(Map<String, Map<String, Long>> out, String pkg, long from, long to) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        while (from < to) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(from);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            c.add(Calendar.DAY_OF_YEAR, 1);
            long cut = Math.min(to, c.getTimeInMillis());
            String day = f.format(new Date(from));
            Map<String, Long> per = out.get(day);
            if (per == null) { per = new HashMap<>(); out.put(day, per); }
            Long prev = per.get(pkg);
            per.put(pkg, (prev == null ? 0L : prev) + (cut - from));
            from = cut;
        }
    }
}
