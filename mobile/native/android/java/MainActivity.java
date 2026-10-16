package io.github.bzeitel25.levelup;

import android.os.Bundle;

import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        registerPlugin(LevelUpWidgetPlugin.class);
        registerPlugin(LevelUpUsagePlugin.class);   // Linked apps (v0.27)
        super.onCreate(savedInstanceState);
    }
}
