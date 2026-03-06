package com.core.designsystem.tokens.system

import com.core.designsystem.tokens.reference.Palette

/**
 * Layer 2 - Light theme mapping (Reference → System roles).
 */
object LightThemeColors : ThemeColors {
    override val primary: Long = Palette.Purple500
    override val onPrimary: Long = Palette.White
    override val secondary: Long = Palette.Teal500
    override val onSecondary: Long = Palette.Black
    override val success: Long = Palette.Green500
    override val onSuccess: Long = Palette.White
    override val error: Long = Palette.Red500
    override val onError: Long = Palette.White
    override val warning: Long = Palette.Orange500
    override val onWarning: Long = Palette.White
    override val info: Long = Palette.Blue500
    override val onInfo: Long = Palette.White
}
