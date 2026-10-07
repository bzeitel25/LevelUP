# START HERE

Every session, human or AI, reads this first.

## Where the project lives

```
C:\Dev\Level UP
```

GitHub: https://github.com/bzeitel25/LevelUP
Live app: https://bzeitel25.github.io/LevelUP/ (GitHub Pages, served from `docs/` on `main`)

## The one source file

`src/app.html` is the whole app. Edit only that file. Never edit `docs/index.html` by hand, because the build overwrites it.

`src/app.html` has no `<html>` or `<head>` on purpose. The same file is published as the Claude preview, which adds its own page skeleton. `build.mjs` adds the web version's head.

## Shipping a change

1. Edit `src/app.html`.
2. Bump `BUILD` near the top of the install/update section (format `YYYY-MM-DD.N`). Bump `APP_VERSION` for user-visible releases.
3. Run the build, add a line to `CHANGELOG.md`, and commit.
4. Push from Bruno's machine:

```
cd /d "C:\Dev\Level UP" && node build.mjs && git push origin main
```

AI sessions have no GitHub credentials. They commit and hand back a ready-to-push repo. The push is always Bruno's.

The **Check for updates** button compares the `BUILD` stamp in the live `index.html` against the installed one. If you forget to bump `BUILD`, phones won't see the update.

## Rules that must not break

- **Levels come only from time logged in the app.** Veteran hours give a badge and unlock tips. Nothing else.
- **Gold is never sold for real money**, since gold buys gear and gear is power.
- **Gear stays smaller than skill levels.** Gear adds roughly 10–25% on top.
- **No streaks and no lost progress.** Rested XP is the catch-up mechanic.
- **The session type (solo or lesson) is chosen before a timer starts** and can't change mid-session.
- **Tips must be accurate.** Safety first for trades and sports. When unsure, soften the claim.

## The Android app

`mobile/` wraps the same web app in a Capacitor shell so Android can have real home-screen widgets. Never commit `mobile/android/`; it's generated fresh on every build.

- On every push to `main`, `.github/workflows/android.yml` builds the APK and uploads it to the `android` release. The download link never changes: https://github.com/bzeitel25/LevelUP/releases/download/android/LevelUP.apk
- The widget code lives in `mobile/native/android/` and is copied in by `mobile/scripts/patch-android.mjs`.
- The web app talks to the widget through `window.Capacitor.Plugins.LevelUpWidget.update({ json, portrait })`. Widget buttons open `levelup://quickstart-solo`, `levelup://quickstart-lesson`, `levelup://quickstart`, `levelup://stop` and `levelup://open`.
- `mobile/resources/android/levelup.keystore` signs every build so updates install over each other. It's a sideload key only. Make a separate, secret key before ever publishing to Google Play.

## Saves

The hero lives in browser storage on each device (`levelup.hero.v1`). The Claude preview saves to the Claude account instead. They are separate saves, and the backup code in Settings moves a hero between them.
