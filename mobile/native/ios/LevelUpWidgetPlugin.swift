// Capacitor bridge for the iPhone app target: saves the snapshot to the shared App Group
// and asks WidgetKit to redraw. The web app already calls LevelUpWidget.update({ json, portrait }).
import Foundation
import Capacitor
import WidgetKit

@objc(LevelUpWidgetPlugin)
public class LevelUpWidgetPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "LevelUpWidgetPlugin"
    public let jsName = "LevelUpWidget"
    public let pluginMethods: [CAPPluginMethod] = [CAPPluginMethod(name: "update", returnType: CAPPluginReturnPromise)]

    @objc func update(_ call: CAPPluginCall) {
        let d = UserDefaults(suiteName: "group.io.github.bzeitel25.levelup")
        if let json = call.getString("json") { d?.set(json, forKey: "json") }
        if let portrait = call.getString("portrait") { d?.set(portrait, forKey: "portrait") }
        WidgetCenter.shared.reloadAllTimelines()
        call.resolve()
    }
}
