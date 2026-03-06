package com.core.designsystem.tokens.system

import com.core.designsystem.tokens.reference.Palette

/**
 * Layer 2 - Dark theme mapping (Reference → System roles).
 */
object DarkThemeColors : ThemeColors {
    override val primary: Long = Palette.Purple700
    override val onPrimary: Long = Palette.White
    override val secondary: Long = Palette.Teal700
    override val onSecondary: Long = Palette.White
    override val success: Long = Palette.Green700
    override val onSuccess: Long = Palette.White
    override val error: Long = Palette.Red700
    override val onError: Long = Palette.White
    override val warning: Long = Palette.Orange700
    override val onWarning: Long = Palette.White
    override val info: Long = Palette.Blue700
    override val onInfo: Long = Palette.White
}
