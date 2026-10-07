# Level UP for iPhone (widget)

The widget code is ready, but building an iPhone app needs a **Mac with Xcode**. Installing it needs either:

- a **free Apple ID**: the app installs from Xcode onto your own iPhone over a cable, and expires after 7 days (reinstall from Xcode to renew), or
- an **Apple Developer account** ($99/year): install through TestFlight with no expiry, and optionally publish to the App Store.

## One-time setup on a Mac

1. Install Xcode from the App Store, plus Node 22.
2. In this folder's parent (`mobile/`):
   ```
   npm ci
   node scripts/sync-www.mjs
   npx cap add ios
   npx cap open ios
   ```
3. In Xcode, select the **App** target:
   - **Signing & Capabilities**: choose your team, then add **App Groups** with `group.io.github.bzeitel25.levelup`.
   - **Info → URL Types**: add a URL scheme `levelup`.
   - Add `native/ios/LevelUpWidgetPlugin.swift` to the App target, then register it in `AppDelegate` or a `CAPBridgeViewController` subclass with `bridge?.registerPluginInstance(LevelUpWidgetPlugin())`.
4. **File → New → Target → Widget Extension**, named `LevelUpWidget` (uncheck Live Activity and Configuration Intent):
   - Replace its generated Swift file with `native/ios/LevelUpWidget.swift`.
   - Add the same **App Group** to the widget target.
5. Choose your iPhone as the run destination and press **Run**.

Then long-press your home screen, tap **+**, search for **Level UP**, and add the small or medium widget.
