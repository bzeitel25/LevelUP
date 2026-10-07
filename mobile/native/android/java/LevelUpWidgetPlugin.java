package io.github.bzeitel25.levelup;

import android.content.Context;
import android.content.SharedPreferences;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

/** Bridge the web app uses to hand the widget its latest snapshot (and, when it changes, the hero portrait). */
@CapacitorPlugin(name = "LevelUpWidget")
public class LevelUpWidgetPlugin extends Plugin {
    @PluginMethod
    public void update(PluginCall call) {
        String json = call.getString("json");
        String portrait = call.getString("portrait");
        SharedPreferences.Editor editor = getContext().getSharedPreferences(LevelUpWidget.PREFS, Context.MODE_PRIVATE).edit();
        if (json != null) editor.putString("json", json);
        if (portrait != null) editor.putString("portrait", portrait);
        editor.apply();
        LevelUpWidget.updateAll(getContext());
        call.resolve();
    }
}
