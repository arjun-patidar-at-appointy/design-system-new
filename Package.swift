// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "DesignSystemNew",
    platforms: [
        .iOS(.v14),
    ],
    products: [
        .library(name: "Shared", targets: ["Shared"]),
        .library(name: "DesignSystemNew", targets: ["Shared"]),
        .library(name: "DesignSystemSwiftUI", targets: ["DesignSystemSwiftUI"]),
    ],
    targets: [
        .binaryTarget(
            name: "Shared",
            url: "https://github.com/arjun-patidar-at-appointy/design-system-new/releases/download/1.0.4/Shared.xcframework.zip",
            checksum: "187e2fe4b76c2e63143b389ba9b87f752435ab2ba653e1acde9ba8e8c2b08b32"
        ),
        .target(
            name: "DesignSystemSwiftUI",
            dependencies: ["Shared"],
            path: "Sources/DesignSystemSwiftUI"
        ),
    ]
)
