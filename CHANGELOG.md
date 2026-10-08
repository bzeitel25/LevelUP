# Changelog

## v0.9.0 — 2026-10-08 (build 2026-10-08.6)

Hero art upgrade.

- New lighting on the hero: soft light from the top-left gives the face, hair, clothes and shoes real volume. Outlines take a deep shade of the color they wrap in place of flat black, and the fringe casts a soft shadow on the forehead.
- Bigger, more expressive eyes, with 7 styles: Round, Sparkle, Sleepy, Fierce, Happy, Wink and Dot.
- 7 mouths: Smile, Grin, Neutral, Open, Cat, Smirk and Blep. 5 cheek options: Blush, Freckles, Both, Bandage and None.
- The hero blinks now and then, and always grins with happy eyes when celebrating a level-up.
- 4 new hairstyles: Swoop (side-swept), Curly, Mohawk and Twin tails, for 11 in all.
- Outfit details: a neckline, sleeve cuffs and a shirt hem.
- Face colors adapt to skin tone, so eyes and mouths stay clear on every skin.
- "Surprise me" now mixes expressions too. Every existing hero keeps their look, with the new Round eyes and Smile as defaults.

## v0.8.1 — 2026-10-08 (build 2026-10-08.5)

Automatic updates.

- Level UP now updates itself. It checks when you open it and when you come back to it (at most every 15 minutes), downloads any new version, and switches over. If you're mid-dungeon, in a dialog or watching a celebration, it waits until you leave the app. A short "Level UP updated" note confirms it. Your hero is never touched.
- Settings → App version has an "Update automatically" switch (on by default) and a "Check now" button.
- Android app: new versions of the app itself download in the background, are kept on the phone (they work offline and share your saved hero), and start on the next launch. If a downloaded version ever fails to start, the app drops it within a few seconds and falls back to the built-in version. A new APK is only needed when the native widget code changes. Install this build once to get it.

## v0.8.0 — 2026-10-08 (build 2026-10-08.4)

Inventory and Radiant relics.

- New Inventory tab: all the gear you've bought, grouped by skill. Equip items for their stat bonus from here. It also holds your trophies and an empty Consumables section, ready for potions and elixirs later.
- Radiant relics: once you've bought an item, every dungeon win has a small chance to drop its Radiant version. Deeper dungeons give better odds: 4% in the Goblin Cellar up to 15% in the Endless Spire. Drops favor your most practiced skills.
- Each item with a Radiant version gets a Regular / Radiant switch in the Inventory. Radiant items shine on your hero with a prismatic sparkle, and they're cosmetic only. A new find shows its Radiant version right away.
- New Relic Hunter portrait frame, for finding 5 relics.
- Navigation: Gear is now the Shop, and Settings moved to the gear button in the header, so the phone bar fits Training, Hero, Talents, Inventory, Shop and Dungeons.
- Fix: unlocks earned in a dungeon (themes, backdrops, frames) are now announced reliably.
- The Android app now builds and publishes: https://github.com/bzeitel25/LevelUP/releases/download/android/LevelUP.apk
- New ROADMAP.md. Next up: active combat (tap skills on cooldowns), then consumables.

## v0.7.0 — 2026-10-08 (build 2026-10-08.2)

Frames and progress.

- Portrait frames: 16 pixel-art frames for your hero, with their own colors and corner ornaments. Pick one in the Hero tab. It shows on the character sheet, the Hero tab, your saved profile picture and the Android widget.
  - Free: Plain and Oak. By total level: Iron (10), Gilded (50), Royal (150), Mythic (300).
  - One per path at 20 combined levels: Bard's Lyre, Warrior's Steel, Scholar's Runes, Artisan's Bench, Artificer's Copper, Monk's Jade, Steward's Coin.
  - Feats: Dungeon Delver (clear 10 dungeons), Laureate (learn a talent capstone), Legend (any skill Lv 100).
  - New frames appear in the level-up celebration list.
- Progress view on the Training tab: this week, weekly average and best week, a 12-week bar chart with hover details, and time per skill over the last 4 weeks. Filter by skill.
- Practice history is now kept as daily totals, so the chart never loses old weeks (the session list still shows the latest 400). Existing saves fill it in from their sessions automatically.
- Dungeon clears are now counted for life (they used to come from only the last 30 runs), which also makes the Dungeon theme and backdrop unlocks reliable.

## v0.6.0 — 2026-10-08 (build 2026-10-08.1)

Talent trees.

- New Talents tab: one talent tree per category (Instrument, Language, Health, Cooking, Trade, Art, Tech, Study, Science, Life skills), each with its own names.
  - Category level = all your skill levels in that category added together. Talent points come at category levels 3, 6, 10, 15, 20, 25, 30, 40, 50, 60, 75 and 90 (12 per tree).
  - Three branches per tree: Power (stat bonuses), Fortune (dungeon gold, cheaper gear for that category, trophy drops) and Tactics (win chance, more gold when you retreat).
  - Each branch ends in a capstone that needs 6 points in the tree first. Capstones grant a title to show under your class, and the Tactics capstone adds a signature move that appears in dungeon battles.
  - Talents never add XP, so levels always mean real practice. Totals are capped: +25% gold, +10% win chance, +30% retreat gold, +15% trophy drops.
  - Resetting a tree is free. If removing a session lowers a category level, the newest extra talents rest until you earn the level back. Nothing is lost.
- Level-up celebrations announce new talent points, with a "Spend talent point" button that opens the right tree.
- The character sheet shows talent bonuses in gold next to gear bonuses (green).
- The Settings download button opens the Android releases page instead of a dead link.
- Android build fix: the CI now uses the Android SDK built into GitHub's runners (the setup step was failing, so no APK was published).
- Phone bottom bar fits six tabs: the gold badge hides on phones and the key badge shows just the number.

## v0.5.0 — 2026-10-07 (build 2026-10-07.7)

Real home-screen widgets.

- New Android app with real home-screen widgets, built automatically by GitHub on every push. Download: https://github.com/bzeitel25/LevelUP/releases/download/android/LevelUP.apk
  - Medium widget (4×2): your pixel hero portrait, name, class, total level, focus skill with XP bar, Solo and Lesson buttons, today's time and keys.
  - Small widget (2×2): portrait, total level, focus skill bar and a Start button.
  - While training, both show a live timer and a Stop button. Taps open the app and start, stop or log right away.
  - Level UP launcher icon and splash screen. Every build is signed with the same key, so new versions install over old ones and your hero stays.
- The widget preview moved off the Training screen into Settings → Home screen widget, with a download button for Android.
- iPhone widget code (WidgetKit) and step-by-step Xcode setup in mobile/native/ios. Building it needs a Mac.

## v0.4.1 — 2026-10-07 (build 2026-10-07.6)

Phone-first fixes.

- Phones get an app-style bottom tab bar (Training, Hero, Gear, Dungeons, Settings) with icons and gold and key badges.
- New "Install app" button in the header opens an install screen at the top of Settings, with a QR code, the app link, a copy button, and iPhone and Android steps. On Android, it shows the one-tap install button.
- Fix: harmless browser warnings (like layout observer notices in the mobile Claude preview) no longer pop up as "Something went wrong" errors.
- Fix: creating a hero now lands on the Hero tab as intended.

## v0.4.0 — 2026-10-07 (build 2026-10-07.5)

Make it feel amazing.

- Level-up celebration: your hero jumps and cheers on their backdrop, with pixel confetti, the new level and tier, and a list of everything that just unlocked (gear to buy, locations, themes).
- 8-bit sound effects made in the browser, with no audio files: timer start, logging, level-ups, buying, coins, battle hits, victory and defeat. Plus vibration on phones that support it. Both can be turned off in Settings.
- Dungeon battles are now animated pixel fights: your hero battles a boss-sized monster (Cellar Goblin, Moss Golem, Haunted Tome, Ember Spirit, Storm Wyvern, Void Watcher) in a themed arena, with hit flashes, damage numbers and a health bar, ending in a treasure chest of coins or a retreat.
- App themes, one per class (Bard, Warrior, Scholar, Artisan, Artificer, Monk, Steward) plus Dungeon and Legend, each with light and dark palettes and a subtle pixel pattern. Class themes unlock when that path's skills add up to Lv 10.
- Panel styles: Clean, Pixel (unlocks at total level 10) and Gilded (unlocks at total level 50).
- Floating +XP numbers and a shine across the XP bar when you log, press feedback on buttons, and a pixel logo in the header.
- Fix: a closed celebration layer could block taps in some browsers.
- Battles use a tiny overworld-style hero (16×16) built from your appearance and gear: hair style, colors, crown or toque, cape, and back gear. Everything in the arena shares one pixel size, and the hero walks in, hops on victory and retreats on a loss.
- Battle encounters no longer repeat the same line.

## v0.3.0 — 2026-10-07 (build 2026-10-07.3)

Places to train. Science joins the game.

- 17 new themed pixel locations, 23 in all: Theater, Amphitheater, Concert Hall, Workshop, Engineering Bay, Kitchen, Cozy Café, Grand Dining Hall, Laboratory, Observatory, Lantern Market, Stadium Track, Dojo, Zen Garden, Neon City, Art Studio and Greenhouse. Many have animated details like spinning gears, bubbling beakers, twinkling string lights and falling petals.
- Locations unlock by leveling skills in their category (Lv 5, 15 and 30). The Dungeon unlocks when you clear your first dungeon.
- Training happens in the best location you've unlocked for that skill. A guitarist trains on stage and a chef in the kitchen.
- Location picker in the Hero tab, with previews and unlock requirements, and announcements when you unlock one.
- New category, Explore science: Chemistry, Biology, Physics and Astronomy, each with a 7-tier tip set (safety first, including solar filters for astronomy).
- New gear and sprites: Safety Goggles, Bubbling Flask, Lab Coat, Brass Microscope, Philosopher's Stone, Pocket Binoculars and Brass Telescope.

## v0.2.0 — 2026-10-07 (build 2026-10-07.2)

The hero gets a body. Pixel art throughout.

- Pixel-art chibi hero with 7 hair styles, 10 hair colors, 6 skin tones, eye colors, and outfit colors.
- A pixel sprite for every gear item (360 across all skills), in 6 tier finishes from worn to legendary gold. Bought gear shows on your hero, and legendary gear sparkles.
- New Hero tab: customize your look, choose which owned gear shows in each slot, pick a backdrop, and save a 480×480 profile picture.
- Live training scenes: your hero plays the instrument, runs, lifts, reads, cooks, hammers, codes, sweeps or meditates, with pixel effects.
- Six pixel backdrops (Meadow, Dusk, Dungeon, Forge, Library, Plain), built to grow into themed and unlockable ones.
- Your hero's portrait on the character sheet and home-screen widget, and item icons in the Gear tab.
- A few gear items renamed so each matches its sprite (for example, Copper Saucepan and Tech Backpack).

## v0.1.0 — 2026-10-07 (build 2026-10-07.1)

First version on GitHub. Prototype of the core loop.

- Skill tracking across 9 categories and 60 preset skills, plus custom skills.
- Levels on a power-law curve (Lv 100 = 1,000 solo hours), mastery stars, and Legend at 10,000 hours.
- Live training sessions with an animated chibi hero, live XP, and mid-session level-ups.
- Solo vs. class/lesson sessions (1.5× XP), chosen before the timer starts.
- Rested XP catch-up instead of streaks.
- Key shards (1 per minute, 10 = 1 key, 15 keys a day), dungeon runs, gold, and a gear shop with 6 tiers per skill.
- Hero stats and power from skill levels, and classes with hybrids.
- Veteran badges (Bronze to Diamond) that unlock tips but never add XP.
- 962 reviewed tips with rotation, a tip library, ratings, and tip suggestions.
- In-app feedback.
- Home-screen widget preview and a native bridge for a future mobile wrapper.
- Install as a home-screen app, Check for updates, and backup codes to move a hero between devices.
