package com.core.designsystem.tokens.components.button

import com.core.designsystem.tokens.reference.Palette
import com.core.designsystem.tokens.system.ThemeColors
import com.core.designsystem.tokens.system.ThemeHolder

/**
 * Layer 3 - Component Tokens (Strictly for Button)
 * MUI-style: variants (text, contained, outlined), colors, sizes.
 */
object ButtonTokens {

    // --- Defaults (backward compatible) ---
    val containerColor: Long get() = getContainerColor(ButtonVariant.CONTAINED, ButtonColorRole.PRIMARY)
    val contentColor: Long get() = getContentColor(ButtonVariant.CONTAINED, ButtonColorRole.PRIMARY)
    val cornerRadius: Double get() = getCornerRadius(ButtonSize.MEDIUM)
    val paddingHorizontal: Double get() = getPaddingHorizontal(ButtonSize.MEDIUM)
    val paddingVertical: Double get() = getPaddingVertical(ButtonSize.MEDIUM)

    // --- Variant + Color resolution ---
    fun getContainerColor(variant: ButtonVariant, colorRole: ButtonColorRole): Long {
        val theme = ThemeHolder.current
        return when (variant) {
            ButtonVariant.CONTAINED -> getColorByRole(theme, colorRole)
            ButtonVariant.OUTLINED, ButtonVariant.TEXT -> Palette.Transparent
        }
    }

    fun getContentColor(variant: ButtonVariant, colorRole: ButtonColorRole): Long {
        val theme = ThemeHolder.current
        return when (variant) {
            ButtonVariant.CONTAINED -> getOnColorByRole(theme, colorRole)
            ButtonVariant.OUTLINED, ButtonVariant.TEXT -> getColorByRole(theme, colorRole)
        }
    }

    fun getBorderColor(variant: ButtonVariant, colorRole: ButtonColorRole): Long {
        return when (variant) {
            ButtonVariant.OUTLINED -> getColorByRole(ThemeHolder.current, colorRole)
            ButtonVariant.CONTAINED, ButtonVariant.TEXT -> Palette.Transparent
        }
    }

    fun getColorByRole(theme: ThemeColors, role: ButtonColorRole): Long =
        when (role) {
            ButtonColorRole.PRIMARY -> theme.primary
            ButtonColorRole.SECONDARY -> theme.secondary
            ButtonColorRole.SUCCESS -> theme.success
            ButtonColorRole.ERROR -> theme.error
            ButtonColorRole.WARNING -> theme.warning
            ButtonColorRole.INFO -> theme.info
        }

    private fun getOnColorByRole(theme: ThemeColors, role: ButtonColorRole): Long =
        when (role) {
            ButtonColorRole.PRIMARY -> theme.onPrimary
            ButtonColorRole.SECONDARY -> theme.onSecondary
            ButtonColorRole.SUCCESS -> theme.onSuccess
            ButtonColorRole.ERROR -> theme.onError
            ButtonColorRole.WARNING -> theme.onWarning
            ButtonColorRole.INFO -> theme.onInfo
        }

    // --- Size tokens ---
    fun getPaddingHorizontal(size: ButtonSize): Double = when (size) {
        ButtonSize.SMALL -> 12.0
        ButtonSize.MEDIUM -> 24.0
        ButtonSize.LARGE -> 32.0
    }

    fun getPaddingVertical(size: ButtonSize): Double = when (size) {
        ButtonSize.SMALL -> 8.0
        ButtonSize.MEDIUM -> 12.0
        ButtonSize.LARGE -> 16.0
    }

    fun getCornerRadius(size: ButtonSize): Double = when (size) {
        ButtonSize.SMALL -> 6.0
        ButtonSize.MEDIUM -> 8.0
        ButtonSize.LARGE -> 10.0
    }
}
