// Copy the built web app (docs/) into www/ for the native shell.
import { cpSync, rmSync, mkdirSync, existsSync } from "node:fs";
const src = new URL("../../docs/", import.meta.url), out = new URL("../www/", import.meta.url);
if (!existsSync(new URL("index.html", src))) { console.error("docs/index.html is missing. Run node build.mjs at the repo root first."); process.exit(1); }
rmSync(out, { recursive: true, force: true }); mkdirSync(out, { recursive: true });
cpSync(src, out, { recursive: true });
console.log("Copied docs/ into mobile/www/");
