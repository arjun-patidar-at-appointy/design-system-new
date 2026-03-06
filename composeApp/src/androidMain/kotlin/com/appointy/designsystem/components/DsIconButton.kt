package com.appointy.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.core.designsystem.tokens.components.button.ButtonColorRole
import com.core.designsystem.tokens.components.button.ButtonSize
import com.core.designsystem.tokens.components.iconbutton.IconButtonTokens

/**
 * DS Icon Button — MUI-style API.
 * Circular button for single icons, appropriate for app bars or toolbars.
 * Colors: primary, secondary, success, error, warning, info.
 * Sizes: small, medium, large.
 */
@Composable
fun DsIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: ButtonColorRole = ButtonColorRole.PRIMARY,
    size: ButtonSize = ButtonSize.MEDIUM,
    loading: Boolean = false,
    content: @Composable () -> Unit
) {
    val buttonSize = IconButtonTokens.getSize(size).dp
    val iconColor = Color(IconButtonTokens.getColor(color).toInt())

    IconButton(
        onClick = onClick,
        modifier = modifier.size(buttonSize),
        enabled = enabled && !loading,
        colors = IconButtonDefaults.iconButtonColors(contentColor = iconColor)
    ) {
        if (loading) {
            val loaderSize = when (size) {
                ButtonSize.SMALL -> 14.dp
                ButtonSize.MEDIUM -> 18.dp
                ButtonSize.LARGE -> 22.dp
            }
            CircularProgressIndicator(
                modifier = Modifier.size(loaderSize),
                color = iconColor,
                strokeWidth = 2.dp
            )
        } else {
            content()
        }
    }
}
