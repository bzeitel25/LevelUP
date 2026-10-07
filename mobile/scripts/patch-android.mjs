// Adds Level UP's native pieces to a freshly generated Capacitor Android project:
// the widget providers and bridge plugin, layouts and art, levelup:// links,
// a fixed signing key (so each new APK installs over the last), and the version number.
import { cpSync, readFileSync, writeFileSync, existsSync, readdirSync } from "node:fs";
const root = new URL("../", import.meta.url);
const app = new URL("android/app/", root);
const pkgDir = new URL("src/main/java/io/github/bzeitel25/levelup/", app);
const must = (u, what) => { if (!existsSync(u)) { console.error(`Missing ${what}: ${u.pathname}`); process.exit(1); } };
must(app, "Android project (run npx cap add android first)");

// 1. Java sources and resources
for (const f of readdirSync(new URL("native/android/java/", root))) cpSync(new URL(`native/android/java/${f}`, root), new URL(f, pkgDir));
cpSync(new URL("native/android/res/", root), new URL("src/main/res/", app), { recursive: true });

// 2. Manifest: levelup:// links into the app, and the two widget providers
const manPath = new URL("src/main/AndroidManifest.xml", app);
let man = readFileSync(manPath, "utf8");
if (!man.includes('android:scheme="levelup"')) {
  man = man.replace(/(<category android:name="android.intent.category.LAUNCHER" \/>\s*<\/intent-filter>)/, `$1

            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="levelup" />
            </intent-filter>`);
  man = man.replace(/(\s*<provider\s)/, `

        <receiver android:name=".LevelUpWidget" android:exported="false" android:label="@string/widget_medium_label">
            <intent-filter>
                <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
            </intent-filter>
            <meta-data android:name="android.appwidget.provider" android:resource="@xml/widget_medium_info" />
        </receiver>

        <receiver android:name=".LevelUpWidgetSmall" android:exported="false" android:label="@string/widget_small_label">
            <intent-filter>
                <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
            </intent-filter>
            <meta-data android:name="android.appwidget.provider" android:resource="@xml/widget_small_info" />
        </receiver>
$1`);
  if (!man.includes('android:scheme="levelup"') || !man.includes(".LevelUpWidgetSmall")) { console.error("Could not patch AndroidManifest.xml"); process.exit(1); }
  writeFileSync(manPath, man);
}

// 3. Version number from the web app, build number from CI, and the fixed signing key
const gradlePath = new URL("build.gradle", app);
let gradle = readFileSync(gradlePath, "utf8");
const html = readFileSync(new URL("../docs/index.html", root), "utf8");
const version = (html.match(/const APP_VERSION = "([^"]+)"/) || [])[1] || "1.0";
const code = parseInt(process.env.GITHUB_RUN_NUMBER || "1", 10) + 100;
gradle = gradle.replace(/versionCode \d+/, `versionCode ${code}`).replace(/versionName "[^"]*"/, `versionName "${version}"`);
if (!gradle.includes("levelup.keystore")) {
  gradle += `
// Level UP: sign every build with the same key so new versions install over old ones.
android.signingConfigs.debug.storeFile = file("../../resources/android/levelup.keystore")
android.signingConfigs.debug.storePassword = "android"
android.signingConfigs.debug.keyAlias = "androiddebugkey"
android.signingConfigs.debug.keyPassword = "android"
`;
}
writeFileSync(gradlePath, gradle);
console.log(`Patched Android project: widgets, links, signing, version ${version} (${code})`);
