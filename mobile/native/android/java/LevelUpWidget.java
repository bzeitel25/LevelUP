package io.github.bzeitel25.levelup;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Base64;
import android.view.View;
import android.widget.RemoteViews;

import org.json.JSONObject;

/**
 * Level UP home-screen widget (4x2). The app pushes a snapshot of the hero through
 * LevelUpWidgetPlugin; this provider only draws what was last saved, so it works while the app is closed.
 * Buttons open the app through levelup:// links, which the app turns into actions (start, stop, log).
 */
public class LevelUpWidget extends AppWidgetProvider {
    public static final String PREFS = "levelup_widget";

    protected int layoutId() { return R.layout.widget_medium; }
    protected boolean isSmall() { return false; }

    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] ids) {
        for (int id : ids) manager.updateAppWidget(id, build(context));
    }

    /** Redraw every placed Level UP widget, both sizes. */
    public static void updateAll(Context context) {
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        LevelUpWidget[] kinds = { new LevelUpWidget(), new LevelUpWidgetSmall() };
        for (LevelUpWidget kind : kinds) {
            int[] ids = manager.getAppWidgetIds(new ComponentName(context, kind.getClass()));
            for (int id : ids) manager.updateAppWidget(id, kind.build(context));
        }
    }

    static PendingIntent link(Context context, String action, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("levelup://" + action), context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    }

    RemoteViews build(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        RemoteViews v = new RemoteViews(context.getPackageName(), layoutId());
        v.setOnClickPendingIntent(R.id.w_root, link(context, "open", 1));

        String portrait = prefs.getString("portrait", null);
        if (portrait != null) {
            try {
                byte[] bytes = Base64.decode(portrait, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                if (bitmap != null) v.setImageViewBitmap(R.id.w_portrait, bitmap);
            } catch (Exception ignored) { }
        }

        JSONObject s = null;
        try {
            String json = prefs.getString("json", null);
            if (json != null) s = new JSONObject(json);
        } catch (Exception e) {
            s = null;
        }

        if (s == null) {
            v.setTextViewText(R.id.w_level, isSmall() ? "0" : "Lv 0");
            if (!isSmall()) {
                v.setTextViewText(R.id.w_name, "Level UP");
                v.setTextViewText(R.id.w_class, "Open the app to start");
                v.setTextViewText(R.id.w_meta, "");
            }
            v.setTextViewText(R.id.w_skill, "No skills yet");
            v.setTextViewText(R.id.w_skill_lv, "");
            v.setProgressBar(R.id.w_bar, 1000, 0, false);
            showTimer(v, null);
            setButtons(context, v);
            return v;
        }

        JSONObject focus = s.optJSONObject("focusSkill");
        JSONObject timer = s.optJSONObject("timer");
        int total = s.optInt("totalLevel", 0);
        v.setTextViewText(R.id.w_level, isSmall() ? String.valueOf(total) : "Lv " + total);
        if (!isSmall()) {
            v.setTextViewText(R.id.w_name, s.optString("heroName", "Hero"));
            v.setTextViewText(R.id.w_class, s.optString("className", "Wanderer"));
            int today = s.optInt("todayMinutes", 0);
            int keys = s.optInt("keys", 0);
            v.setTextViewText(R.id.w_meta, "Today " + formatMinutes(today) + "  ·  " + keys + (keys == 1 ? " key" : " keys"));
        }
        if (focus != null) {
            v.setTextViewText(R.id.w_skill, focus.optString("name", ""));
            v.setTextViewText(R.id.w_skill_lv, "Lv " + focus.optInt("level", 0));
            v.setProgressBar(R.id.w_bar, 1000, (int) Math.round(focus.optDouble("progress", 0) * 1000), false);
        } else {
            v.setTextViewText(R.id.w_skill, "No skills yet");
            v.setTextViewText(R.id.w_skill_lv, "");
            v.setProgressBar(R.id.w_bar, 1000, 0, false);
        }
        showTimer(v, timer);
        setButtons(context, v);
        return v;
    }

    /** Running timer: a live chronometer and a Stop button. Otherwise the start buttons. */
    void showTimer(RemoteViews v, JSONObject timer) {
        if (timer != null) {
            long startedAt = timer.optLong("startedAt", System.currentTimeMillis());
            long base = SystemClock.elapsedRealtime() - (System.currentTimeMillis() - startedAt);
            v.setChronometer(R.id.w_chrono, base, null, true);
            if (!isSmall()) v.setTextViewText(R.id.w_run_label, "lesson".equals(timer.optString("type")) ? "Lesson" : "Training");
            v.setViewVisibility(R.id.w_idle, View.GONE);
            v.setViewVisibility(R.id.w_running, View.VISIBLE);
        } else {
            v.setChronometer(R.id.w_chrono, SystemClock.elapsedRealtime(), null, false);
            v.setViewVisibility(R.id.w_idle, View.VISIBLE);
            v.setViewVisibility(R.id.w_running, View.GONE);
        }
    }

    protected void setButtons(Context context, RemoteViews v) {
        v.setOnClickPendingIntent(R.id.w_stop, link(context, "stop", 4));
        v.setOnClickPendingIntent(R.id.w_running, link(context, "stop", 4));
        if (isSmall()) {
            v.setOnClickPendingIntent(R.id.w_start, link(context, "quickstart", 2));
        } else {
            v.setOnClickPendingIntent(R.id.w_solo, link(context, "quickstart-solo", 2));
            v.setOnClickPendingIntent(R.id.w_lesson, link(context, "quickstart-lesson", 3));
        }
    }

    static String formatMinutes(int min) {
        if (min < 60) return min + "m";
        int h = min / 60, m = min % 60;
        return m == 0 ? h + "h" : h + "h " + m + "m";
    }
}
