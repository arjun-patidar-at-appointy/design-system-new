package com.core.designsystem.tokens.system

/**
 * Layer 2 - System Tokens (Theme Roles)
 * Interfaces and semantic roles. Switching between Light and Dark mode is handled here.
 * MUI-style: primary, secondary, success, error, warning, info + on* variants.
 */
interface ThemeColors {
    val primary: Long
    val onPrimary: Long
    val secondary: Long
    val onSecondary: Long
    val success: Long
    val onSuccess: Long
    val error: Long
    val onError: Long
    val warning: Long
    val onWarning: Long
    val info: Long
    val onInfo: Long
}
