# Level UP

Give your real life an RPG twist. Every minute you practice a real skill fills an XP bar, earns dungeon keys, and powers up your hero.

**Live app → [bzeitel25.github.io/LevelUP](https://bzeitel25.github.io/LevelUP/)**

*Working on this project? Read **[START-HERE.md](START-HERE.md)** first.*

---

## Install on your phone

Open the link above on your phone.

- **Android (Chrome):** go to the **Settings** tab and tap **Install on this phone**, or use the browser menu's **Install app**.
- **iPhone (Safari):** tap the **Share** button, then **Add to Home Screen**.

It installs like a native app, with its own icon, full screen and no browser bars, and it works offline. To get a new version, open **Settings → Check for updates**.

Your hero is saved on the device. To move a hero between devices, or from the Claude preview to your phone, use **Settings → Copy backup code** on one and **Restore from a code** on the other.

---

## How it works

**Training.** Start a practice timer on any skill and choose **Solo practice** (1 XP per minute) or **Class or lesson** (1.5 XP per minute). The choice is locked for the session. Your chibi hero trains alongside you while the XP bar fills live, and level-ups happen mid-session. Forgot the timer? Log time afterwards, up to two days back.

**Levels.** Levels follow the power law of practice: early levels come fast, later ones take real time.

| Level | Solo practice |
|---|---|
| 1 | 6 min |
| 5 | 2.5 h |
| 10 | 10 h |
| 30 | 90 h |
| 50 | 250 h |
| 100 | 1,000 h |

Past Lv 100, every 1,000 hours earns a mastery star. At 10,000 hours you're a Legend.

**No streaks.** Days off build **Rested XP**: 30 minutes of double XP per day off, up to 3 hours, so missing days never sets you back. Nothing you earn is ever taken away.

**Skills and categories.** Pick from 60 skills across nine categories (instruments, languages, health, cooking, trades, art, tech, study and life skills) or add your own. Your **class** comes from the paths you train most: Bard, Warrior, Scholar, Artisan, Artificer, Monk or Steward, plus hybrids like Skald, Battlemage and Wizard.

**Veteran hours.** Past experience earns a Bronze to Diamond Veteran badge and unlocks tips for your real experience level. It never adds XP, levels or rewards. Everyone's level starts at 0 in the app.

**Tips.** 962 tips across every skill and seven tiers, from Novice to Grandmaster. Safety comes first for trades and sports. Rotate tips, browse your unlocked library, rate tips and suggest your own.

**Keys, dungeons and gear.**
- Every minute of practice earns a key shard. 10 shards make a dungeon key, up to 15 keys a day.
- Spend keys on dungeon runs, which play out from your hero's stats and pay **gold**.
- Skill levels unlock gear. Gold buys it. Gear adds a smaller bonus on top of your skill-based stats, so practice stays the main source of power.

**Fair play.** Gold can never be bought, so power can't be bought. Prior experience never earns rewards, and daily caps limit grinding.

---

## Project layout

```
src/app.html     The app. Single source of truth.
build.mjs        Wraps src/app.html into docs/index.html and stamps the build.
docs/            What GitHub Pages serves: index.html, manifest, service worker, icons.
CHANGELOG.md     What changed in each version.
```
