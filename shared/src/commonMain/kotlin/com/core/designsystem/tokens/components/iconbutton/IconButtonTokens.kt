package com.core.designsystem.tokens.components.iconbutton

import com.core.designsystem.tokens.components.button.ButtonColorRole
import com.core.designsystem.tokens.components.button.ButtonSize
import com.core.designsystem.tokens.components.button.ButtonTokens
import com.core.designsystem.tokens.system.ThemeHolder

/**
 * Layer 3 - Component Tokens (Strictly for Icon Button)
 * MUI-style icon buttons colors, sizes.
 */
object IconButtonTokens {

    fun getColor(colorRole: ButtonColorRole): Long {
        return ButtonTokens.getColorByRole(ThemeHolder.current, colorRole)
    }

    // Usually IconButton has a circular ripple but no default background unless pressed.
    // The sizes here will determine the overall touch target size.
    fun getSize(size: ButtonSize): Double = when (size) {
        ButtonSize.SMALL -> 32.0
        ButtonSize.MEDIUM -> 40.0
        ButtonSize.LARGE -> 48.0
    }

    // The inner icon size
    fun getIconSize(size: ButtonSize): Double = when (size) {
        ButtonSize.SMALL -> 18.0
        ButtonSize.MEDIUM -> 24.0
        ButtonSize.LARGE -> 28.0
    }
}
