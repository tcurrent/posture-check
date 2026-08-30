package com.tcurrent.posturecheck;

import java.awt.Color;

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
        name = "Interval (min)",
        description = "How often to remind you to fix your posture (in minutes)",
        position = 1
    )
    default int reminderIntervalMinutes()
    {
        return 60;
    }

    @ConfigItem(
        keyName = "reminderText",
        name = "Reminder text",
        description = "Text shown in the reminder notification",
        position = 2
    )
    default String reminderText()
    {
        return "Sit upright, relax your shoulders, and keep your feet flat.";
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

    @ConfigItem(
        keyName = "prefixColor",
        name = "Prefix color",
        description = "Color for the Posture Check chat prefix",
        position = 5
    )
    default Color prefixColor()
    {
        return new Color(255, 152, 31);
    }

    @ConfigItem(
        keyName = "messageColor",
        name = "Message color",
        description = "Color for the reminder text in the in-game chat window",
        position = 6
    )
    default Color messageColor()
    {
        return Color.WHITE;
    }
}
