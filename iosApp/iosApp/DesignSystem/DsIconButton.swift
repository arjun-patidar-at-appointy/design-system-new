import SwiftUI
import Shared

/// DS Icon Button — MUI-style API.
/// Circular button for single icons, appropriate for app bars or toolbars.
/// Colors: primary, secondary, success, error, warning, info.
/// Sizes: small, medium, large.
struct DsIconButton<Content: View>: View {
    let action: () -> Void
    let content: Content
    var colorRole: ButtonColorRole
    var size: ButtonSize
    var disabled: Bool
    var loading: Bool

    init(
        action: @escaping () -> Void,
        color: ButtonColorRole = .primary,
        size: ButtonSize = .medium,
        disabled: Bool = false,
        loading: Bool = false,
        @ViewBuilder content: () -> Content
    ) {
        self.action = action
        self.content = content()
        self.colorRole = color
        self.size = size
        self.disabled = disabled
        self.loading = loading
    }

    var body: some View {
        let tokens = IconButtonTokens.shared
        let iconColor = Color(kmpArgb: tokens.getColor(colorRole: colorRole))
        let buttonSize = tokens.getSize(size: size)
        let iconSize = tokens.getIconSize(size: size)

        Button(action: action) {
            Group {
                if loading {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: iconColor))
                        .scaleEffect(buttonSize / 40.0) // Adjust spinner size based on button size
                } else {
                    content
                }
            }
            .font(.system(size: iconSize))
            .frame(width: buttonSize, height: buttonSize)
            .foregroundColor(iconColor)
            .contentShape(Circle())
            .opacity(disabled || loading ? 0.5 : 1)
        }
        .buttonStyle(.plain)
        .disabled(disabled || loading)
    }
}
