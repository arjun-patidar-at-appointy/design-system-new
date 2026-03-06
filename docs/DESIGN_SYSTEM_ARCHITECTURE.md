# Core Design System: Architecture & Publishing

## Objective

Build and distribute a scalable, platform-agnostic Design System for **Android (Compose)** and **iOS (SwiftUI)** using **Kotlin Multiplatform (KMP)** with a **"Logic Shared, UI Native"** approach.

## Architecture: Logic Shared, UI Native

| Layer | Responsibility |
|-------|----------------|
| **Shared (KMP)** | Design tokens, themes, component state — single source of truth in pure Kotlin |
| **Android** | Jetpack Compose reads KMP tokens and renders UI |
| **iOS** | SwiftUI reads the same KMP tokens and renders UI |

## 3-Tier Token Architecture

Material-style token aliasing for theming and dark mode:

| Layer | Location | Purpose |
|-------|----------|---------|
| **Layer 1 – Reference** | `tokens/reference/Palette.kt` | Raw hex values. UI must not use these directly. |
| **Layer 2 – System** | `tokens/system/` | Semantic theme roles (e.g. `primary`, `onPrimary`). Light/Dark switching lives here. |
| **Layer 3 – Component** | `tokens/components/` | Maps system roles to specific components (e.g. Button) so one component’s change doesn’t affect others. |

## Folder Structure (shared)

```
shared/src/commonMain/kotlin/com/core/designsystem/
├── tokens/
│   ├── reference/           # LAYER 1
│   │   └── Palette.kt
│   ├── system/              # LAYER 2
│   │   ├── ColorSystem.kt   # ThemeColors interface
│   │   ├── LightThemeColors.kt
│   │   ├── DarkThemeColors.kt
│   │   └── ThemeHolder.kt   # Current theme (Light/Dark)
│   └── components/          # LAYER 3
│       ├── button/
│       │   ├── ButtonTokens.kt
│       │   ├── ButtonVariant.kt   # text, contained, outlined
│       │   ├── ButtonColorRole.kt # primary, secondary, success, error, warning, info
│       │   └── ButtonSize.kt     # small, medium, large
│       └── iconbutton/
│           └── IconButtonTokens.kt
```

## DsButton API (MUI-style)

| Prop | Android (Compose) | iOS (SwiftUI) | Description |
|------|------------------|---------------|-------------|
| **variant** | `ButtonVariant.TEXT` / `CONTAINED` / `OUTLINED` | `.text` / `.contained` / `.outlined` | Text = no background; Contained = solid (primary action); Outlined = border only |
| **color** | `ButtonColorRole.PRIMARY` … `INFO` | `.primary` … `.info` | primary, secondary, success, error, warning, info |
| **size** | `ButtonSize.SMALL` / `MEDIUM` / `LARGE` | `.small` / `.medium` / `.large` | Padding and corner radius |
| **fullWidth** | `fullWidth = true` | `fullWidth: true` | Button takes 100% width |
| **startIcon** / **endIcon** | `@Composable () -> Unit` slots | Use inside content | MUI-style icon slots (Android); iOS put icon in content |
| **disabled** | `enabled = false` | — | Disable the button |

### Android example

```kotlin
DsButton(
    onClick = { },
    variant = ButtonVariant.CONTAINED,
    color = ButtonColorRole.SUCCESS,
    size = ButtonSize.MEDIUM,
    fullWidth = false,
    startIcon = { Icon(Icons.Default.Delete, null) },
    endIcon = { Icon(Icons.Default.Send, null) }
) { Text("Submit") }
```

### iOS example

```swift
DsButton(action: { }, variant: .outlined, color: .secondary, size: .large, fullWidth: true) {
    Text("Cancel")
}
```

## M3-Inspired Principles

1. **Slot API** – Components take a content slot (lambda/ViewBuilder), not raw data (e.g. no `text = "Submit"`). Enables text, icons, or loaders without new variants.
2. **Surface foundation** – Components can be built on a base that keeps contrast (e.g. onPrimary on primary).
3. **State layers** – Use semi-transparent overlays for pressed/hover instead of new colors.
4. **Defaults object** – Styling lives in a separate object (e.g. `AppButtonDefaults`) to keep the main component clean.

## Usage

### Android (Compose)

```kotlin
DsButton(onClick = { ... }) { Text("Submit") }
// With variant/color/size:
DsButton(onClick = { }, variant = ButtonVariant.OUTLINED, color = ButtonColorRole.SECONDARY) { Text("Cancel") }
```

### iOS (SwiftUI)

```swift
DsButton(action: { ... }) { Text("Submit") }
DsButton(action: { }, variant: .outlined, color: .error) { Text("Delete") }
```

### Theme switching (shared)

```kotlin
ThemeHolder.current = DarkThemeColors  // or LightThemeColors
```

## Publishing

- **Shared**: Publish as a KMP library (AAR for Android, XCFramework or framework for iOS).
- **Android**: Consume shared and provide Compose components in a separate artifact or the same module.
- **iOS**: Consume Shared framework; SwiftUI wrappers live in the app or a separate Swift package.
