// Level UP build: wraps src/app.html into docs/index.html for GitHub Pages
// and stamps the service worker with the same build, so phones pick up updates.
//
//   node build.mjs
//
// src/app.html is the single source. It is also what gets published as the
// Claude preview, which supplies its own page skeleton, so the file has no
// <html>/<head> of its own. This script adds them for the web.
import { readFileSync, writeFileSync } from "node:fs";

const app = readFileSync("src/app.html", "utf8");
const build = (app.match(/const BUILD = "([^"]+)"/) || [])[1];
const version = (app.match(/const APP_VERSION = "([^"]+)"/) || [])[1];
if (!build || !version) {
  console.error("Could not find BUILD and APP_VERSION in src/app.html. Stopping.");
  process.exit(1);
}

const head = `<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,viewport-fit=cover">
<meta name="description" content="Level up real-life skills. Practice fills your XP bars, earns dungeon keys and powers your hero.">
<meta name="theme-color" content="#151a2c">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<meta name="apple-mobile-web-app-title" content="Level UP">
<link rel="manifest" href="manifest.webmanifest">
<link rel="icon" href="icon-192.png" type="image/png">
<link rel="apple-touch-icon" href="apple-touch-icon.png">
<script>/* lvup-loader: in the Android app, start the newest downloaded version of Level UP if it's newer than the one built into the app. A version that fails to start is dropped and the built-in one is used. */
(function(){try{
if(window.__lvupShell)return;var C=window.Capacitor;if(!(C&&C.isNativePlatform&&C.isNativePlatform()))return;
var B="${build}",ls=localStorage,sb=ls.getItem("levelup.shell.build");if(!sb)return;
var cmp=function(a,b){a=a.split(".");b=b.split(".");if(a[0]!==b[0])return a[0]<b[0]?-1:1;return(parseInt(a[1],10)||0)-(parseInt(b[1],10)||0);};
if(cmp(sb,B)<=0){ls.removeItem("levelup.shell");ls.removeItem("levelup.shell.build");return;}
if(ls.getItem("levelup.shell.trying")===sb){ls.removeItem("levelup.shell");ls.removeItem("levelup.shell.build");ls.removeItem("levelup.shell.trying");return;}
var h=ls.getItem("levelup.shell");if(!h)return;
ls.setItem("levelup.shell.trying",sb);window.__lvupShell=sb;setTimeout(function(){try{if(ls.getItem("levelup.shell.trying")===sb)location.reload();}catch(e){}},8000);
document.write(h+'<plaintext hidden style="display:none">');
}catch(e){}})();
</script>
<style>:root{color-scheme:light;padding-top:env(safe-area-inset-top,0px);padding-bottom:env(safe-area-inset-bottom,0px)}body{margin:0;font:14px/1.45 system-ui,-apple-system,"Segoe UI",sans-serif}img{max-width:100%}[hidden]{display:none!important}</style>
</head>
<body>
`;
writeFileSync("docs/index.html", head + app + "\n</body>\n</html>\n");

const sw = readFileSync("docs/sw.js", "utf8");
const stamped = sw.replace(/const VERSION = "[^"]*";/, `const VERSION = "lvup-${build}";`);
if (stamped === sw && !sw.includes(`"lvup-${build}"`)) {
  console.error("Could not stamp docs/sw.js. Stopping.");
  process.exit(1);
}
writeFileSync("docs/sw.js", stamped);

console.log(`Built Level UP v${version} (build ${build}) -> docs/index.html`);
