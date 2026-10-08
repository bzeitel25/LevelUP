# Level UP roadmap

Design rules that every feature follows:

- No streaks and no lost progress. Rested XP is the catch-up mechanic.
- Levels come only from time logged in the app.
- Gold is never sold for real money, and power is never sold.
- Gear and talents stay small next to skill levels, so practice is always the main source of power.
- Tips must be accurate, with safety first.

## Shipped

| Version | Highlights |
|---|---|
| v0.1 to v0.3 | Tracker, timer, solo and lesson sessions, rested XP, veteran badges, tips, keys, dungeons, gear |
| v0.4 | Pixel hero, celebrations, sound, animated battles, themes, backdrops |
| v0.5 | Android app with real home-screen widgets |
| v0.6 | Talent trees: one per category, with titles and signature moves |
| v0.7 | Portrait frames and the Progress view (weekly chart and per-skill time) |
| v0.8 | Rare relics: Radiant drops tied to your real skills, plus the Relic Hunter frame |

## Next up

### 1. Active combat in dungeons and adventures

Bruno wants combat to be something you play, not something that plays itself.

- The hero still auto-attacks.
- Skill buttons unlock based on your class and level. Tap a skill's icon to use it.
- Each skill has a cooldown of about 5 to 10 seconds. Stronger skills have longer cooldowns, which keeps things balanced.
- Starting skill ideas, by path:
  - Bard: Rally Song, which buffs your attack.
  - Warrior: Power Strike, which does heavy damage.
  - Scholar: Rune Shield, which blocks the next hit.
  - Artisan: Quick Repair, which heals.
  - Artificer: Gadget Trap, which stuns.
  - Monk: Focus, which lands a critical hit.
  - Steward: Lucky Find, which gives bonus gold.
  - Hybrid classes get one skill from each of their two paths.
- New skills unlock at hero levels, and talent signature moves become skill buttons.
- Things to settle when we build it:
  - Battle length, about 30 to 60 seconds.
  - Whether a battle can be lost.
  - How tapping well changes win chance and gold, while practice stays the main source of power.

### 2. Adventure mode

- A world map with quest chains, from Adventure level 1 to 100, built on top of dungeons.
- Rare drops tied to real skills.

### 3. Verified practice

- Private, on-device checks that sync with apps like Health Connect.
- Verified time counts for a little more. The honor system stays the default.

### 4. Group adventures

- Party up with friends. This needs accounts and a small backend.

### 5. iPhone widgets

- The WidgetKit code is written, in mobile/native/ios. Building it needs a Mac.
