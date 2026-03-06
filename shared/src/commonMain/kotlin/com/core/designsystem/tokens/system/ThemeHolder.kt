package com.core.designsystem.tokens.system

/**
 * Holds the current theme so component tokens can resolve colors for Light/Dark mode.
 * Set by the app at runtime (e.g. from system setting or user preference).
 */
object ThemeHolder {
    var current: ThemeColors = LightThemeColors
}
