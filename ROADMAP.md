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
| v0.23 | Veteran Reserve follow-ups: 406 new veteran-level tips (Expert to Grandmaster), per-skill "Still practicing?" length (1 to 8 hours, longer defaults for outings), a 5× boost ceiling, class unlocks stay on real hours |
| v0.24 | 9 Ascended classes (one per path at Lv 50, plus the Polymath for 3 paths at Lv 25) with stronger signatures, glowing gear sets and their own frames; Suggest a class form. 98 classes in all |
| v0.25 | 16 new path skills (2 more per path, at path Lv 25 and 50); pick which one each path brings |
| v0.26 | Pet roster: 79 new pets with pixel art (class pets from unlocked classes, skill pets at skill Lv 15); hats and charms fit every pet, 8 new pieces of pet gear |
| v0.27 | Linked apps on Android (Usage access, "Found 15 min in Duolingo, log it?", auto-approve per app, on-device only); CSV history import as veteran hours |

## Next up

### 1. Online world (MMO-style)

Needs decisions first: where accounts and data live (a hosted backend such as Supabase or Firebase), sign-in method, and cost.

- Start with accounts and a small backend, so heroes live online and sync across devices.
- See other players' heroes, titles and achievements.
- Shared "meet" maps and exploration areas where players see each other walking around.
- Leaderboards rank by real logged hours, not XP.
- Player votes on Suggest a class ideas.
- Later: parties for group adventures and co-op bosses.

### 2. Verified practice and Health Connect

- Health Connect for workouts from Strava, Garmin, Fitbit and others, through the Linked apps screen.
- Private, on-device checks. Verified time counts for a little more. The honor system stays the default.
- Before publishing to Google Play: Usage access needs a clear in-app disclosure (in place since v0.27) and a policy review; make a separate, secret signing key.

### 3. Linked apps follow-ups

- iPhone: little beyond Apple Health workouts.
- Duolingo has no official public API, so don't build on unofficial endpoints.

### 4. Pet follow-ups

- Pet levels or pet talents only much later, if ever: the focus stays on training yourself, not your pets.
- Answered in v0.26: class pets need only the class unlocked; skill pets come at skill Lv 15.

### 5. iPhone widgets

- The WidgetKit code is written, in mobile/native/ios. Building it needs a Mac.
