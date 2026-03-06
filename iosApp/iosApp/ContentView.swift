import Shared
import SwiftUI

struct ContentView: View {
    @State private var showContent = false
    @State private var isDarkTheme = false

    var body: some View {
        VStack(spacing: 0) {
            // Theme toggle fixed at top (does not scroll)
            HStack {
                Text(isDarkTheme ? "Dark" : "Light")
                    .font(.headline)
                Spacer()
                Toggle("", isOn: $isDarkTheme)
                    .labelsHidden()
                    .onChange(of: isDarkTheme) { _, newValue in
                        ThemeHolder.shared.current = newValue ? DarkThemeColors.shared : LightThemeColors.shared
                    }
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 12)
            .onAppear {
                ThemeHolder.shared.current = isDarkTheme ? DarkThemeColors.shared : LightThemeColors.shared
            }

            ScrollView {
                VStack(alignment: .center, spacing: 0) {
                    DsButton(action: {
                        withAnimation {
                            showContent = !showContent
                        }
                    }) {
                        Text("Click me!")
                    }

                    if showContent {
                        VStack(spacing: 16) {
                            Image(systemName: "swift")
                                .font(.system(size: 200))
                                .foregroundColor(.accentColor)
                            Text("SwiftUI: \(Greeting().greet())")
                        }
                        .frame(maxWidth: .infinity)
                        .transition(.move(edge: .top).combined(with: .opacity))
                    }

                    Spacer().frame(height: 32)
                    Text("— Button examples —")
                        .font(.title3.weight(.medium))
                    Spacer().frame(height: 16)

                // Basic button
                Group {
                    Text("Basic button")
                        .font(.title2.weight(.medium))
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 16)

                    // Text button
                    Text("Text button")
                        .font(.headline)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("Text buttons are typically used for less-pronounced actions.")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 8)
                    HStack(spacing: 8) {
                        DsButton(action: { }, variant: .text) { Text("Primary") }
                        DsButton(action: { }, variant: .text, disabled: true) { Text("Disabled") }
                        Spacer()
                    }
                    Spacer().frame(height: 24)

                    // Contained button
                    Text("Contained button")
                        .font(.headline)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("Contained buttons are high-emphasis, distinguished by their use of elevation and fill.")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 8)
                    HStack(spacing: 8) {
                        DsButton(action: { }, variant: .contained) { Text("Contained") }
                        DsButton(action: { }, variant: .contained, disabled: true) { Text("Disabled") }
                        Spacer()
                    }
                    Spacer().frame(height: 24)

                    // Outlined button
                    Text("Outlined button")
                        .font(.headline)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("Outlined buttons are medium-emphasis buttons.")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 8)
                    HStack(spacing: 8) {
                        DsButton(action: { }, variant: .outlined) { Text("Primary") }
                        DsButton(action: { }, variant: .outlined, disabled: true) { Text("Disabled") }
                        Spacer()
                    }
                }
                Spacer().frame(height: 32)

                // Color
                Group {
                    Text("Color")
                        .font(.title2.weight(.medium))
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 8)
                    HStack(spacing: 8) {
                        DsButton(action: { }, variant: .text, color: .secondary) { Text("Secondary") }
                        DsButton(action: { }, variant: .contained, color: .success) { Text("Success") }
                        DsButton(action: { }, variant: .outlined, color: .error) { Text("Error") }
                        Spacer()
                    }
                }
                Spacer().frame(height: 32)

                // Sizes
                Group {
                    Text("Sizes")
                        .font(.title2.weight(.medium))
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("For larger or smaller buttons, use the size prop.")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 8)
                    HStack(spacing: 8) {
                        DsButton(action: { }, size: .small) { Text("Small") }
                        DsButton(action: { }, size: .medium) { Text("Medium") }
                        DsButton(action: { }, size: .large) { Text("Large") }
                        Spacer()
                    }
                }
                Spacer().frame(height: 32)

                // Buttons with icons and label
                Group {
                    Text("Buttons with icons and label")
                        .font(.title2.weight(.medium))
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 8)
                    HStack(spacing: 8) {
                        DsButton(action: { }, variant: .outlined) {
                            HStack(spacing: 8) {
                                Image(systemName: "trash")
                                Text("Delete")
                            }
                        }
                        DsButton(action: { }, variant: .contained) {
                            HStack(spacing: 8) {
                                Text("Send")
                                Image(systemName: "paperplane.fill")
                            }
                        }
                        Spacer()
                    }
                }
                Spacer().frame(height: 32)

                // Icon button
                Group {
                    Text("Icon button")
                        .font(.title2.weight(.medium))
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("Icon buttons are commonly found in app bars and toolbars.")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 8)
                    HStack(spacing: 8) {
                        DsIconButton(action: { }) { Image(systemName: "trash") }
                        DsIconButton(action: { }, disabled: true) { Image(systemName: "trash") }
                        DsIconButton(action: { }, color: .secondary) { Image(systemName: "alarm") }
                        DsIconButton(action: { }, color: .primary) { Image(systemName: "cart.badge.plus") }
                        Spacer()
                    }
                    Spacer().frame(height: 24)

                    // Icon button Sizes
                    Text("Sizes")
                        .font(.headline)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 8)
                    HStack(spacing: 8) {
                        DsIconButton(action: { }, size: .small) { Image(systemName: "trash") }
                        DsIconButton(action: { }, size: .medium) { Image(systemName: "trash") }
                        DsIconButton(action: { }, size: .large) { Image(systemName: "trash") }
                        Spacer()
                    }
                    Spacer().frame(height: 24)

                    // Icon button Colors
                    Text("Colors")
                        .font(.headline)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 8)
                    HStack(spacing: 8) {
                        DsIconButton(action: { }, color: .secondary) { Image(systemName: "touchid") }
                        DsIconButton(action: { }, color: .success) { Image(systemName: "touchid") }
                        Spacer()
                    }
                }
                Spacer().frame(height: 32)

                // Loading
                Group {
                    Text("Loading")
                        .font(.title2.weight(.medium))
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Spacer().frame(height: 8)
                    HStack(spacing: 8) {
                        DsButton(action: { }, loading: true) { Text("Submit") }
                        DsButton(action: { }, variant: .outlined, loading: true) { Text("Submit") }
                        DsIconButton(action: { }, loading: true) { Image(systemName: "trash") }
                        Spacer()
                    }
                }
                Spacer().frame(height: 40)
                }
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 16)
                .padding(.vertical, 16)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .preferredColorScheme(isDarkTheme ? .dark : .light)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
