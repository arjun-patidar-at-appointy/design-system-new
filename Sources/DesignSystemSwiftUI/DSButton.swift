import SwiftUI
import Shared

private func dsColorFrom(argb: Int64) -> Color {
    let bits = UInt64(bitPattern: argb)
    let a = Double((bits >> 24) & 0xFF) / 255.0
    let r = Double((bits >> 16) & 0xFF) / 255.0
    let g = Double((bits >> 8) & 0xFF) / 255.0
    let b = Double(bits & 0xFF) / 255.0
    return Color(.sRGB, red: r, green: g, blue: b, opacity: a)
}

public struct DSButton: View {
    public let title: String
    public let variant: ButtonVariant
    public let colorRole: ButtonColorRole
    public let size: ButtonSize
    public let enabled: Bool
    public let action: () -> Void

    public init(
        _ title: String,
        variant: ButtonVariant = .contained,
        colorRole: ButtonColorRole = .primary,
        size: ButtonSize = .medium,
        enabled: Bool = true,
        action: @escaping () -> Void
    ) {
        self.title = title
        self.variant = variant
        self.colorRole = colorRole
        self.size = size
        self.enabled = enabled
        self.action = action
    }

    private var containerColor: Color {
        dsColorFrom(argb: ButtonTokens.shared.getContainerColor(variant: variant, colorRole: colorRole))
    }

    private var contentColor: Color {
        dsColorFrom(argb: ButtonTokens.shared.getContentColor(variant: variant, colorRole: colorRole))
    }

    private var borderColor: Color {
        dsColorFrom(argb: ButtonTokens.shared.getBorderColor(variant: variant, colorRole: colorRole))
    }

    private var paddingHorizontal: CGFloat {
        CGFloat(ButtonTokens.shared.getPaddingHorizontal(size: size))
    }

    private var paddingVertical: CGFloat {
        CGFloat(ButtonTokens.shared.getPaddingVertical(size: size))
    }

    private var cornerRadius: CGFloat {
        CGFloat(ButtonTokens.shared.getCornerRadius(size: size))
    }

    private var font: Font {
        switch size {
        case .small:
            return .caption
        case .medium:
            return .body
        case .large:
            return .headline
        default:
            return .body
        }
    }

    public var body: some View {
        Button(action: action) {
            Text(title)
                .font(font)
                .fontWeight(.semibold)
                .foregroundColor(contentColor.opacity(enabled ? 1 : 0.5))
                .padding(.horizontal, paddingHorizontal)
                .padding(.vertical, paddingVertical)
                .frame(maxWidth: .infinity)
        }
        .buttonStyle(.plain)
        .background(containerColor.opacity(enabled ? 1 : 0.5))
        .overlay(
            RoundedRectangle(cornerRadius: cornerRadius)
                .stroke(borderColor.opacity(enabled ? 1 : 0.5), lineWidth: variant == .outlined ? 1.5 : 0)
        )
        .cornerRadius(cornerRadius)
        .disabled(!enabled)
    }
}

