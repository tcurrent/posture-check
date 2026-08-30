# Posture Check

A RuneLite plugin that reminds you to correct your posture during long play sessions.

## Local Development

1. Open this folder in your IDE (IntelliJ or VS Code).
2. Ensure Java 17+ is installed.
3. Build the plugin:

	```bash
	gradle build
	```

4. Follow the [RuneLite plugin development guide](https://github.com/runelite/runelite/wiki/Building-with-IntelliJ-IDEA) to set up the dev environment and launch the client with the plugin.

## Config

- **Interval (min)**: How often to remind you, with a minimum of one minute (default 60)
- **Reminder text**: The text shown for each posture reminder
- **RuneLite notifications**: Show or hide desktop notifications
- **In-game chat message**: Show or hide reminders in the game chat window
- **Prefix color**: Color for the `[Posture Check]` chat label
- **Message color**: Color for the remainder of the in-game chat reminder

## Attribution

Plugin icon based on Hans (sitting) asset from [Old School RuneScape](https://www.jagex.com/), owned by Jagex Limited. Image sourced from [Old School RuneScape Wiki](https://oldschool.runescape.wiki).
