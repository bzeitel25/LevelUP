# Turning on the online World

The World works offline on every device. Online play (other heroes walking around, phrases, friends and
leaderboards) needs a free Supabase project. It takes about five minutes.

## 1. Create the project

1. Sign up at https://supabase.com (free).
2. **New project**, name it `Level UP`, pick the region closest to you, set a database password (save it somewhere; the app never needs it).

## 2. Create the tables

1. In the project, open **SQL Editor** → **New query**.
2. Paste everything from `supabase/setup.sql` and press **Run**. It should say "Success. No rows returned".

## 3. Allow sign-in without email

Players get an anonymous account per device, so nobody types an email or password.

1. Open **Authentication** → **Sign In / Providers** (or **Providers**).
2. Turn on **Allow anonymous sign-ins** and save.

## 4. Copy two keys into Level UP

1. Open **Project Settings** → **API** (or **Data API** and **API Keys**).
2. Copy the **Project URL** (looks like `https://abcdefgh.supabase.co`) and the **anon public** key (a long string starting with `eyJ`, or `sb_publishable_` on newer projects).
3. Paste them into `WORLD_ONLINE` in `src/app.html`, or send them to Claude to do it:

```js
const WORLD_ONLINE = { url: "https://abcdefgh.supabase.co", key: "eyJ..." };
```

Both are meant to be public: the security rules in `setup.sql` decide what anyone can read or change.
Never paste the **service_role** / secret key into the app.

## 5. Build and push

```
cd /d "C:\Dev\Level UP" && node build.mjs && git add -A && git commit -m "Turn on the online World" && git push origin main
```

## Free tier limits (Supabase Free)

- 200 players online at the same moment, 2 million live messages a month, 50,000 accounts a month.
- The project pauses after 1 week with no activity; open the Supabase dashboard and press **Restore** to wake it.
- Level UP keeps messages low: a move is sent only when a hero steps onto a new tile, only to players in the same
  region, and monsters run on each player's own phone.

## Testing without editing the code

In the browser console on the live app:

```js
localStorage.setItem("levelup.online", JSON.stringify({ url: "https://abcdefgh.supabase.co", key: "eyJ..." })); location.reload();
```
