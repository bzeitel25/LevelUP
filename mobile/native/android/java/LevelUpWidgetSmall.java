package io.github.bzeitel25.levelup;

/** Compact 2x2 Level UP widget: portrait, total level, focus skill bar, and one Start or Stop button. */
public class LevelUpWidgetSmall extends LevelUpWidget {
    @Override protected int layoutId() { return R.layout.widget_small; }
    @Override protected boolean isSmall() { return true; }
}
