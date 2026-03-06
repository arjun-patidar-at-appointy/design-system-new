import SwiftUI
import Shared

/// DS Button — MUI-style API. Variants: text, contained, outlined.
/// Colors: primary, secondary, success, error, warning, info. Sizes: small, medium, large.
struct DsButton<Content: View>: View {
    let action: () -> Void
    let content: Content
    var variant: ButtonVariant
    var colorRole: ButtonColorRole
    var size: ButtonSize
    var fullWidth: Bool
    var disabled: Bool
    var loading: Bool

    init(
        action: @escaping () -> Void,
        variant: ButtonVariant = .contained,
        color: ButtonColorRole = .primary,
        size: ButtonSize = .medium,
        fullWidth: Bool = false,
        disabled: Bool = false,
        loading: Bool = false,
        @ViewBuilder content: () -> Content
    ) {
        self.action = action
        self.content = content()
        self.variant = variant
        self.colorRole = color
        self.size = size
        self.fullWidth = fullWidth
        self.disabled = disabled
        self.loading = loading
    }

    var body: some View {
        let tokens = ButtonTokens.shared
        let containerColor = Color(kmpArgb: tokens.getContainerColor(variant: variant, colorRole: colorRole))
        let contentColor = Color(kmpArgb: tokens.getContentColor(variant: variant, colorRole: colorRole))
        let borderColor = Color(kmpArgb: tokens.getBorderColor(variant: variant, colorRole: colorRole))
        let paddingH = tokens.getPaddingHorizontal(size: size)
        let paddingV = tokens.getPaddingVertical(size: size)
        let cornerRadius = tokens.getCornerRadius(size: size)

        let isOutlined = variant == .outlined
        let isText = variant == .text

        Button(action: action) {
            Group {
                if loading {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: contentColor))
                } else {
                    content
                }
            }
            .padding(.horizontal, paddingH)
            .padding(.vertical, paddingV)
            .frame(maxWidth: fullWidth ? .infinity : nil)
            .foregroundColor(contentColor)
            .background(isText || isOutlined ? Color.clear : containerColor)
            .overlay(
                isOutlined ? RoundedRectangle(cornerRadius: cornerRadius)
                    .stroke(borderColor, lineWidth: 1) : nil
            )
            .clipShape(RoundedRectangle(cornerRadius: cornerRadius))
            .opacity(disabled || loading ? 0.5 : 1)
        }
        .buttonStyle(.plain)
        .disabled(disabled || loading)
    }
}
