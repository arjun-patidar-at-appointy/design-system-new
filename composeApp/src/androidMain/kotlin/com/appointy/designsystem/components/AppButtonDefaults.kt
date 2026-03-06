package com.appointy.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.core.designsystem.tokens.components.button.ButtonColorRole
import com.core.designsystem.tokens.components.button.ButtonSize
import com.core.designsystem.tokens.components.button.ButtonTokens
import com.core.designsystem.tokens.components.button.ButtonVariant

/**
 * Defaults object: MUI-style button styling from tokens.
 * Variant, color role, and size resolve to shape, colors, padding.
 */
@Immutable
object AppButtonDefaults {

    private const val borderWidthDp = 1.0

    fun shape(size: ButtonSize) =
        RoundedCornerShape(ButtonTokens.getCornerRadius(size).dp)

    fun containerColor(variant: ButtonVariant, colorRole: ButtonColorRole): Color =
        Color(ButtonTokens.getContainerColor(variant, colorRole).toInt())

    fun contentColor(variant: ButtonVariant, colorRole: ButtonColorRole): Color =
        Color(ButtonTokens.getContentColor(variant, colorRole).toInt())

    fun borderColor(variant: ButtonVariant, colorRole: ButtonColorRole): Color =
        Color(ButtonTokens.getBorderColor(variant, colorRole).toInt())

    fun contentPadding(size: ButtonSize) = PaddingValues(
        horizontal = ButtonTokens.getPaddingHorizontal(size).dp,
        vertical = ButtonTokens.getPaddingVertical(size).dp
    )

    fun borderStroke(variant: ButtonVariant, colorRole: ButtonColorRole): BorderStroke? =
        if (variant == ButtonVariant.OUTLINED)
            BorderStroke(borderWidthDp.dp, borderColor(variant, colorRole))
        else null

    // Backward compatibility
    val shape get() = shape(ButtonSize.MEDIUM)
    val containerColor get() = containerColor(ButtonVariant.CONTAINED, ButtonColorRole.PRIMARY)
    val contentColor get() = contentColor(ButtonVariant.CONTAINED, ButtonColorRole.PRIMARY)
    val contentPadding get() = contentPadding(ButtonSize.MEDIUM)
}
