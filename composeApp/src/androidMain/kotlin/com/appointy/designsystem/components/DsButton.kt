package com.appointy.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.designsystem.tokens.components.button.ButtonColorRole
import com.core.designsystem.tokens.components.button.ButtonSize
import com.core.designsystem.tokens.components.button.ButtonVariant

/**
 * DS Button — MUI-style API with Slot pattern.
 * Variants: text, contained, outlined. Colors: primary, secondary, success, error, warning, info.
 * Sizes: small, medium, large. Optional startIcon/endIcon slots.
 */
@Composable
fun DsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.CONTAINED,
    color: ButtonColorRole = ButtonColorRole.PRIMARY,
    size: ButtonSize = ButtonSize.MEDIUM,
    fullWidth: Boolean = false,
    disableElevation: Boolean = false,
    loading: Boolean = false,
    startIcon: (@Composable () -> Unit)? = null,
    endIcon: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val shape = AppButtonDefaults.shape(size)
    val contentColor = AppButtonDefaults.contentColor(variant, color)
    val containerColor = AppButtonDefaults.containerColor(variant, color)
    val padding = AppButtonDefaults.contentPadding(size)
    val border = AppButtonDefaults.borderStroke(variant, color)
    val mod = if (fullWidth) modifier.fillMaxWidth() else modifier
    val elevation = if (disableElevation) ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp) else ButtonDefaults.elevatedButtonElevation()

    @Composable
    fun ButtonSlot() {
        if (loading) {
            val loaderSize = when (size) {
                ButtonSize.SMALL -> 16.dp
                ButtonSize.MEDIUM -> 20.dp
                ButtonSize.LARGE -> 24.dp
            }
            CircularProgressIndicator(
                modifier = Modifier.size(loaderSize),
                color = contentColor,
                strokeWidth = 2.dp
            )
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                startIcon?.invoke()
                content()
                endIcon?.invoke()
            }
        }
    }

    val isEnabled = enabled && !loading

    when (variant) {
        ButtonVariant.TEXT -> TextButton(
            onClick = onClick,
            modifier = mod,
            enabled = isEnabled,
            shape = shape,
            colors = ButtonDefaults.textButtonColors(contentColor = contentColor),
            contentPadding = padding
        ) { ButtonSlot() }
        ButtonVariant.OUTLINED -> OutlinedButton(
            onClick = onClick,
            modifier = mod,
            enabled = isEnabled,
            shape = shape,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = contentColor),
            border = border!!,
            contentPadding = padding
        ) { ButtonSlot() }
        ButtonVariant.CONTAINED -> Button(
            onClick = onClick,
            modifier = mod,
            enabled = isEnabled,
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
            elevation = elevation,
            contentPadding = padding
        ) { ButtonSlot() }
    }
}
