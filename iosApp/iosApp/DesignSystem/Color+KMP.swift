import SwiftUI

/// Converts KMP design token color (ARGB as Int64) to SwiftUI Color.
extension Color {
    /// Creates a SwiftUI Color from a KMP token value (0xAARRGGBB).
    init(kmpArgb: Int64) {
        let a = Double((kmpArgb >> 24) & 0xFF) / 255
        let r = Double((kmpArgb >> 16) & 0xFF) / 255
        let g = Double((kmpArgb >> 8) & 0xFF) / 255
        let b = Double(kmpArgb & 0xFF) / 255
        self.init(.sRGB, red: r, green: g, blue: b, opacity: a)
    }
}
