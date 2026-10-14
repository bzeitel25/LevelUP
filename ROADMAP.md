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
| v0.6 | Talent trees: one per category, with titles |
| v0.7 | Portrait frames and the Progress view (weekly chart and per-skill time) |
| v0.8 | Inventory tab, Radiant relic versions of gear you own (switch Regular or Radiant), Relic Hunter frame |
| v0.8.1 | Automatic updates on the web and in the Android app |
| v0.9 | Hero art upgrade: lighting, colored outlines, 7 eye styles, 7 mouths, 5 cheek options, 4 new hairstyles |
| v0.10 | Active combat: auto-attacks plus tappable path skills on cooldowns, signature moves, HP bars, full-art hero in battle |
| v0.11 | XP elixirs (+5% to +25%), daily and weekly quests (no FOMO, rewards arrive on their own), 37 achievements |
| v0.11.x | One elixir at a time (1 hour each, confirm before replacing), lower quest gold (up to 50 a day), buy Small and Medium elixirs |
| v0.12 | Adventure v1: a single-player overworld with 6 regions, region gates, fog of war, chests and signs, and dungeons on the map |
| v0.13 | Talent trees rebuilt: themed branches per category (warrior, wordmage, engineer, pathfinder and more), 16 capstone battle skills, treasure and utility perks, plus a full sanity pass |
| v0.14 | Paths v2: Music becomes Art (performing and visual), the new Nature path and Explore the outdoors category with its own talent tree, 16 disciplines, new skills and tips |
| v0.15 | Choose your class (5 neutral and 16 starter classes with signature skills and perks), adventuring pets, Adventure v2 (caves, villager missions, regional bosses, battle potions), Nature scenes, poses, theme and frame |
| v0.16 | 32 specializations and 8 dual-discipline classes, Herbalist herb patches on the map |
| v0.17 | Class system consolidated: 28 two-path classes (one per pair of paths), Siren and Envoy reworked, 89 playable classes |
| v0.18 | Class gear sets (4 pieces for every class, full set bonus while you play it) and 5 role talent trees |
| v0.19 | Veteran Reserve: veteran tiers (Dabbler to Lifelong) with an estimator, a per-skill reserve of bonus XP (+50/100/200%) that practice spends, reserve stretch on the XP bar, "coming back" refresher mode, Caught Up milestone and achievement. Honest sessions: "Still practicing?" check with auto-pause, trim or bank before saving, 24-hour trim window |
| v0.20 | Hand-drawn pixel icons for every item (187 designs for 255 skill items and 336 class pieces), with rarity glow and twinkling sparkles |
| v0.21 | Heroes wear their gear: wearable art for every item matching its icon, an off-hand slot for shields and books, clean layering, hats that sit on the head, gear shown in every hero view including battles and the map |
| v0.22 | Pets: a Pet slot for one pet, each with a gear-sized stat bonus, an always-on passive and a tap-to-use battle skill that scale with your hero; 6 categories, design-your-own beasts, 14 more pets from path levels, classes, dungeon drops and the shop, and 5 rare dragon hatchlings whose eggs hatch as you practice |

## Next up

### 1. Veteran Reserve follow-ups

- More veteran-level tips per skill (tips already follow lifetime hours: veteran plus logged).
- Per-skill length for the "Still practicing?" check (3 hours for everything today).
- Consider a global boost ceiling (about 4x to 5x) before adding more boost sources.
- Decide whether class unlocks should count XP (today they count real minutes, so boosts don't speed them up).

### 2. Linked apps (auto-logging)

- A Linked apps screen maps another app to a skill (Duolingo to Spanish, Yousician to Guitar).
- Android: read other apps' foreground time with Usage access (granted in system settings). No background service: on app open or widget refresh, read back the last few days. Check Google Play's policy for this permission first.
- Health Connect for workouts from Strava, Garmin, Fitbit and others. Ties into Verified practice.
- Detected time shows as "Found 15 min in Duolingo, log it?" with one-tap approval and optional auto-approve. Approved time counts as real logged hours.
- Duolingo has no official public API, so don't build on unofficial endpoints. iPhone: little beyond Apple Health workouts.
- Later: import history from other trackers (CSV or app exports) as a more credible source for veteran hours.

### 3. Classes

- Choose your class from everything your practice unlocks: 5 neutral, 16 starter (one per discipline), 32 specializations, 8 dual-discipline, 28 two-path and 9 Ascended classes (98 in all). Full design in the Class Compendium doc.
- Shipped: the class picker with neutral and starter classes and adventuring pets (v0.15), then specializations and dual-discipline classes (v0.16), then two-path classes (v0.17), then class gear sets and role talent trees (v0.18).
- Next: Ascended classes and a Suggest a class form.

### 4. Online world (MMO-style)

- Start with accounts and a small backend, so heroes live online and sync across devices.
- See other players' heroes, titles and achievements.
- Shared "meet" maps and exploration areas where players see each other walking around.
- Leaderboards rank by real logged hours, not XP.
- Later: parties for group adventures and co-op bosses.

### 5. Verified practice

- Private, on-device checks that sync with apps like Health Connect.
- Verified time counts for a little more. The honor system stays the default.

### 6. Combat follow-ups

- More skills per path that unlock at higher path levels (pick which ones to equip, like talent skills already do).

### 7. Pet follow-ups

- A pet roster across classes and skills where one fits: beasts for animal and nature paths, plus summons, robots, flying books, aliens and more for the rest. Not every class or skill needs one.
- Pet gear that any pet can wear.
- Pet levels or pet talents only much later, if ever: the focus stays on training yourself, not your pets.

### 8. iPhone widgets

- The WidgetKit code is written, in mobile/native/ios. Building it needs a Mac.
