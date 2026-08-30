package net.runelite.client.plugins.posturecheck;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("posturecheck")
public interface PostureCheckConfig extends Config
{
    String GROUP = "posturecheck";

    @ConfigItem(
        keyName = "reminderIntervalMinutes",
        name = "Reminder interval",
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
        name = "Notifications",
        description = "Show a RuneLite notification when the reminder triggers",
        position = 3
    )
    default boolean notificationEnabled()
    {
        return true;
    }
}
