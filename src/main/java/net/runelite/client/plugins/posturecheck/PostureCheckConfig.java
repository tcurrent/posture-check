package net.runelite.client.plugins.posturecheck;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("posturecheck")
public interface PostureCheckConfig extends Config
{
    String GROUP = "posturecheck";

    @Range(min = 1)
    @ConfigItem(
        keyName = "reminderIntervalMinutes",
        name = "Reminder interval (minutes)",
        description = "How often to remind you to fix your posture",
        position = 1
    )
    default int reminderIntervalMinutes()
    {
        return 30;
    }

    @ConfigItem(
        keyName = "reminderText",
        name = "Reminder text",
        description = "Text shown in the reminder notification",
        position = 2
    )
    default String reminderText()
    {
        return "Posture check: sit upright, relax your shoulders, and keep your feet flat.";
    }

    @ConfigItem(
        keyName = "notificationEnabled",
        name = "RuneLite notifications",
        description = "Show a RuneLite desktop notification when the reminder triggers",
        position = 3
    )
    default boolean notificationEnabled()
    {
        return true;
    }

    @ConfigItem(
        keyName = "chatMessageEnabled",
        name = "In-game chat message",
        description = "Show the reminder in the in-game chat window",
        position = 4
    )
    default boolean chatMessageEnabled()
    {
        return true;
    }
}
