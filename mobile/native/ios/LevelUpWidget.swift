// Level UP iPhone widget (WidgetKit). Add this file to a Widget Extension target in Xcode.
// The app writes its snapshot to the shared App Group through LevelUpWidgetPlugin.swift;
// this widget reads it. Buttons are links (levelup://...) that open the app and act.
import WidgetKit
import SwiftUI

let appGroup = "group.io.github.bzeitel25.levelup"

struct HeroSnapshot: Decodable {
    struct Focus: Decodable { let name: String; let level: Int; let progress: Double }
    struct Timer: Decodable { let startedAt: Double; let type: String? }
    let heroName: String
    let className: String
    let totalLevel: Int
    let keys: Int?
    let todayMinutes: Int?
    let focusSkill: Focus?
    let timer: Timer?
}

struct HeroEntry: TimelineEntry {
    let date: Date
    let hero: HeroSnapshot?
    let portrait: UIImage?
}

struct Provider: TimelineProvider {
    func load() -> HeroEntry {
        let d = UserDefaults(suiteName: appGroup)
        var hero: HeroSnapshot? = nil
        if let json = d?.string(forKey: "json"), let data = json.data(using: .utf8) {
            hero = try? JSONDecoder().decode(HeroSnapshot.self, from: data)
        }
        var img: UIImage? = nil
        if let b64 = d?.string(forKey: "portrait"), let data = Data(base64Encoded: b64) { img = UIImage(data: data) }
        return HeroEntry(date: Date(), hero: hero, portrait: img)
    }
    func placeholder(in context: Context) -> HeroEntry { HeroEntry(date: Date(), hero: nil, portrait: nil) }
    func getSnapshot(in context: Context, completion: @escaping (HeroEntry) -> Void) { completion(load()) }
    func getTimeline(in context: Context, completion: @escaping (Timeline<HeroEntry>) -> Void) {
        completion(Timeline(entries: [load()], policy: .never)) // the app reloads timelines when anything changes
    }
}

let gold = Color(red: 0.95, green: 0.76, blue: 0.31)
let ink = Color(red: 0.91, green: 0.92, blue: 0.96)

struct Portrait: View {
    let image: UIImage?
    let size: CGFloat
    var body: some View {
        Group {
            if let image { Image(uiImage: image).interpolation(.none).resizable() } else { Color(red: 0.13, green: 0.15, blue: 0.22) }
        }
        .frame(width: size, height: size)
        .clipShape(RoundedRectangle(cornerRadius: 10))
        .overlay(RoundedRectangle(cornerRadius: 10).stroke(gold, lineWidth: 2))
    }
}

struct XPBar: View {
    let progress: Double
    var body: some View {
        GeometryReader { g in
            ZStack(alignment: .leading) {
                RoundedRectangle(cornerRadius: 3).fill(Color(red: 0.17, green: 0.19, blue: 0.31))
                RoundedRectangle(cornerRadius: 3).fill(gold).frame(width: g.size.width * max(0, min(1, progress)))
            }
        }.frame(height: 8)
    }
}

struct Pill: View {
    let title: String; let url: String; let stop: Bool
    var body: some View {
        Link(destination: URL(string: url)!) {
            Text(title).font(.system(size: 13, weight: .bold)).frame(maxWidth: .infinity, minHeight: 32)
                .foregroundColor(stop ? .white : Color(red: 0.11, green: 0.08, blue: 0.02))
                .background(Capsule().fill(stop ? Color(red: 0.75, green: 0.23, blue: 0.16) : gold))
        }
    }
}

struct LevelUpWidgetView: View {
    @Environment(\.widgetFamily) var family
    let entry: HeroEntry
    var body: some View {
        let h = entry.hero
        VStack(alignment: .leading, spacing: 6) {
            HStack(spacing: 10) {
                Portrait(image: entry.portrait, size: family == .systemSmall ? 44 : 52)
                if family != .systemSmall {
                    VStack(alignment: .leading, spacing: 1) {
                        Text(h?.heroName ?? "Level UP").font(.system(size: 15, weight: .bold)).foregroundColor(ink).lineLimit(1)
                        Text(h?.className ?? "Open the app to start").font(.system(size: 13, weight: .bold, design: .monospaced)).foregroundColor(gold).lineLimit(1)
                    }
                }
                Spacer()
                Text(family == .systemSmall ? "\(h?.totalLevel ?? 0)" : "Lv \(h?.totalLevel ?? 0)")
                    .font(.system(size: 20, weight: .bold, design: .monospaced)).foregroundColor(gold)
            }
            Spacer(minLength: 0)
            HStack {
                Text(h?.focusSkill?.name ?? "No skills yet").font(.system(size: 12)).foregroundColor(ink.opacity(0.8)).lineLimit(1)
                Spacer()
                if let f = h?.focusSkill { Text("Lv \(f.level)").font(.system(size: 12, weight: .bold)).foregroundColor(ink) }
            }
            XPBar(progress: h?.focusSkill?.progress ?? 0)
            if let t = h?.timer {
                let start = Date(timeIntervalSince1970: t.startedAt / 1000)
                HStack {
                    Text(timerInterval: start...Date.distantFuture, countsDown: false)
                        .font(.system(size: 15, weight: .bold, design: .monospaced)).foregroundColor(.white)
                    Pill(title: "■ Stop", url: "levelup://stop", stop: true)
                }
            } else if family == .systemSmall {
                Pill(title: "▶ Start", url: "levelup://quickstart", stop: false)
            } else {
                HStack(spacing: 6) {
                    Pill(title: "▶ Solo", url: "levelup://quickstart-solo", stop: false)
                    Pill(title: "▶ Lesson", url: "levelup://quickstart-lesson", stop: false)
                }
            }
        }
        .padding(family == .systemSmall ? 10 : 12)
        .containerBackground(Color(red: 0.09, green: 0.10, blue: 0.16), for: .widget)
        .widgetURL(URL(string: "levelup://open"))
    }
}

@main
struct LevelUpWidget: Widget {
    var body: some WidgetConfiguration {
        StaticConfiguration(kind: "LevelUpWidget", provider: Provider()) { entry in
            LevelUpWidgetView(entry: entry)
        }
        .configurationDisplayName("Level UP")
        .description("Your hero, level and focus skill, with one-tap training.")
        .supportedFamilies([.systemSmall, .systemMedium])
    }
}
