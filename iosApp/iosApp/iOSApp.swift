import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        KoinModulesKt.initializeKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
