package com.appointy.designsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appointy.designsystem.components.DsButton
import com.core.designsystem.tokens.components.button.ButtonColorRole
import com.core.designsystem.tokens.components.button.ButtonSize
import com.core.designsystem.tokens.components.button.ButtonVariant
import com.core.designsystem.tokens.system.DarkThemeColors
import com.core.designsystem.tokens.system.LightThemeColors
import com.core.designsystem.tokens.system.ThemeHolder
import org.jetbrains.compose.resources.painterResource
import designsystem.composeapp.generated.resources.Res
import designsystem.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    var isDarkTheme by remember { mutableStateOf(false) }
    SideEffect {
        ThemeHolder.current = if (isDarkTheme) DarkThemeColors else LightThemeColors
    }
    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    ) {
        var showContent by remember { mutableStateOf(false) }
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .background(
                    if (isDarkTheme) Color.Black
                    else MaterialTheme.colorScheme.primaryContainer
                )
                .fillMaxSize()
        ) {
            // Theme toggle fixed at top (does not scroll)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .safeContentPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    if (isDarkTheme) "Dark" else "Light",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { isDarkTheme = it }
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            DsButton(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text(
                        "Compose: $greeting",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                "— Button examples —",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Variants (Text, Contained, Outlined)
            Text(
                "Basic button",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Text button
            Text(
                "Text button",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "Text buttons are typically used for less-pronounced actions.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                DsButton(onClick = { }, variant = ButtonVariant.TEXT) { Text("Primary") }
                DsButton(onClick = { }, variant = ButtonVariant.TEXT, enabled = false) { Text("Disabled") }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Contained button
            Text(
                "Contained button",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "Contained buttons are high-emphasis, distinguished by their use of elevation and fill.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                DsButton(onClick = { }, variant = ButtonVariant.CONTAINED) { Text("Contained") }
                DsButton(onClick = { }, variant = ButtonVariant.CONTAINED, enabled = false) { Text("Disabled") }
                DsButton(onClick = { }, variant = ButtonVariant.CONTAINED, disableElevation = true) { Text("Disable elevation") }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Outlined button
            Text(
                "Outlined button",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "Outlined buttons are medium-emphasis buttons.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                DsButton(onClick = { }, variant = ButtonVariant.OUTLINED) { Text("Primary") }
                DsButton(onClick = { }, variant = ButtonVariant.OUTLINED, enabled = false) { Text("Disabled") }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Color
            Text(
                "Color",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                DsButton(onClick = { }, color = ButtonColorRole.SECONDARY) { Text("Secondary") }
                DsButton(onClick = { }, variant = ButtonVariant.CONTAINED, color = ButtonColorRole.SUCCESS) { Text("Success") }
                DsButton(onClick = { }, variant = ButtonVariant.OUTLINED, color = ButtonColorRole.ERROR) { Text("Error") }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Sizes
            Text(
                "Sizes",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "For larger or smaller buttons, use the size prop.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                DsButton(onClick = { }, size = ButtonSize.SMALL) { Text("Small") }
                DsButton(onClick = { }, size = ButtonSize.MEDIUM) { Text("Medium") }
                DsButton(onClick = { }, size = ButtonSize.LARGE) { Text("Large") }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Buttons with icons and label
            Text(
                "Buttons with icons and label",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                DsButton(
                    onClick = { },
                    variant = ButtonVariant.OUTLINED,
                    startIcon = { Text("🗑️", modifier = Modifier.padding(end = 8.dp)) }
                ) { Text("Delete") }
                DsButton(
                    onClick = { },
                    variant = ButtonVariant.CONTAINED,
                    endIcon = { Text("📤", modifier = Modifier.padding(start = 8.dp)) }
                ) { Text("Send") }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Icon button
            Text(
                "Icon button",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "Icon buttons are commonly found in app bars and toolbars.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                com.appointy.designsystem.components.DsIconButton(onClick = { }) { Text("🗑️") }
                com.appointy.designsystem.components.DsIconButton(onClick = { }, enabled = false, color = ButtonColorRole.PRIMARY) { Text("🗑️") }
                com.appointy.designsystem.components.DsIconButton(onClick = { }, color = ButtonColorRole.SECONDARY) { Text("⏰") }
                com.appointy.designsystem.components.DsIconButton(onClick = { }, color = ButtonColorRole.PRIMARY) { Text("🛒") }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Icon button sizes
            Text(
                "Sizes",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                com.appointy.designsystem.components.DsIconButton(onClick = { }, size = ButtonSize.SMALL) { Text("🗑️") }
                com.appointy.designsystem.components.DsIconButton(onClick = { }, size = ButtonSize.MEDIUM) { Text("🗑️") }
                com.appointy.designsystem.components.DsIconButton(onClick = { }, size = ButtonSize.LARGE) { Text("🗑️") }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Icon button colors
            Text(
                "Colors",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                com.appointy.designsystem.components.DsIconButton(onClick = { }, color = ButtonColorRole.SECONDARY) { Text("👆") }
                com.appointy.designsystem.components.DsIconButton(onClick = { }, color = ButtonColorRole.SUCCESS) { Text("👆") }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Loading state
            Text(
                "Loading",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                DsButton(onClick = { }, loading = true) { Text("Submit") }
                DsButton(onClick = { }, variant = ButtonVariant.OUTLINED, loading = true) { Text("Submit") }
                com.appointy.designsystem.components.DsIconButton(onClick = { }, loading = true) { Text("🛒") }
            }
            Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}