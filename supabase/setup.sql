-- Level UP online World: run this once in Supabase (SQL Editor -> New query -> paste -> Run).
-- Safe to run again: it only creates what's missing and replaces the policies.

-- Public hero cards: leaderboards, friends and the card you see when you tap a hero.
create table if not exists public.profiles (
  id uuid primary key references auth.users (id) on delete cascade,
  hero_name text not null default 'Hero' check (char_length(hero_name) between 1 and 24),
  card jsonb not null default '{}'::jsonb check (pg_column_size(card) < 12000),
  practiced_min integer not null default 0 check (practiced_min between 0 and 60000000),
  kills integer not null default 0 check (kills >= 0),
  best_depth integer not null default 0 check (best_depth >= 0),
  region text check (char_length(region) <= 16),
  updated_at timestamptz not null default now()
);
create index if not exists profiles_hours on public.profiles (practiced_min desc);
create index if not exists profiles_depth on public.profiles (best_depth desc);
create index if not exists profiles_kills on public.profiles (kills desc);

-- Friends: one row per "I added this hero". Only you can see and change your own list.
create table if not exists public.friends (
  user_id uuid not null references public.profiles (id) on delete cascade,
  friend_id uuid not null references public.profiles (id) on delete cascade,
  created_at timestamptz not null default now(),
  primary key (user_id, friend_id),
  check (user_id <> friend_id)
);

alter table public.profiles enable row level security;
alter table public.friends enable row level security;

drop policy if exists "profiles are public" on public.profiles;
drop policy if exists "write your own profile" on public.profiles;
drop policy if exists "update your own profile" on public.profiles;
drop policy if exists "delete your own profile" on public.profiles;
create policy "profiles are public" on public.profiles for select using (true);
create policy "write your own profile" on public.profiles for insert with check (auth.uid() = id);
create policy "update your own profile" on public.profiles for update using (auth.uid() = id) with check (auth.uid() = id);
create policy "delete your own profile" on public.profiles for delete using (auth.uid() = id);

drop policy if exists "see your friends" on public.friends;
drop policy if exists "add friends" on public.friends;
drop policy if exists "remove friends" on public.friends;
create policy "see your friends" on public.friends for select using (auth.uid() = user_id);
create policy "add friends" on public.friends for insert with check (auth.uid() = user_id);
create policy "remove friends" on public.friends for delete using (auth.uid() = user_id);

-- Friend codes are the first 10 letters of a hero's id.
create or replace function public.find_by_code(code text)
returns table (id uuid, hero_name text)
language sql stable as $$
  select p.id, p.hero_name from public.profiles p
  where replace(p.id::text, '-', '') like lower(regexp_replace(code, '[^0-9a-fA-F]', '', 'g')) || '%'
    and char_length(regexp_replace(code, '[^0-9a-fA-F]', '', 'g')) >= 10
  limit 1;
$$;
grant execute on function public.find_by_code(text) to anon, authenticated;
