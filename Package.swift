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
            url: "https://github.com/arjun-patidar-at-appointy/design-system-new/releases/download/1.0.3/Shared.xcframework.zip",
            checksum: "d750060a64c8afea7baade5a21744abaf281d6a053b2e7b6419796ea45f00c3f"
        ),
        .target(
            name: "DesignSystemSwiftUI",
            dependencies: ["Shared"],
            path: "Sources/DesignSystemSwiftUI"
        ),
    ]
)
