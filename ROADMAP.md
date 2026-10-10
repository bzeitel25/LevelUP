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

## Next up

### 1. Classes

- Choose your class from everything your practice unlocks: 5 neutral, 16 starter (one per discipline), 32 specializations, 8 dual-discipline, 56 two-path and 9 Ascended classes. Full design in the Class Compendium doc.
- Shipped: the class picker with neutral and starter classes and adventuring pets (v0.15), then specializations and dual-discipline classes (v0.16).
- Next: two-path classes, class gear sets and class talent trees, then Ascended classes and a Suggest a class form.

### 2. Online world (MMO-style)

- Start with accounts and a small backend, so heroes live online and sync across devices.
- See other players' heroes, titles and achievements.
- Shared "meet" maps and exploration areas where players see each other walking around.
- Later: parties for group adventures and co-op bosses.

### 3. Verified practice

- Private, on-device checks that sync with apps like Health Connect.
- Verified time counts for a little more. The honor system stays the default.

### 4. Combat follow-ups

- More skills per path that unlock at higher path levels (pick which ones to equip, like talent skills already do).

### 5. iPhone widgets

- The WidgetKit code is written, in mobile/native/ios. Building it needs a Mac.
