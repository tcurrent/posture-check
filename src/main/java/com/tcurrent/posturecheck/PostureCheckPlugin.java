package com.tcurrent.posturecheck;

import java.time.Duration;
import java.time.Instant;

import javax.inject.Inject;

import com.google.inject.Provides;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
    name = "Posture Check",
    description = "Reminds you to correct your posture during long play sessions",
    enabledByDefault = true,
    tags = {"posture", "health", "reminder"}
)
public class PostureCheckPlugin extends Plugin
{
    private static final Duration PAUSE_GRACE_PERIOD = Duration.ofMinutes(5);

    @Inject
    private Client client;

    @Inject
    private PostureCheckConfig config;

    @Inject
    private Notifier notifier;

    private Duration activeTimeSinceReminder = Duration.ZERO;
    private Instant activeSince;
    private Instant pausedSince;

    @Provides
    PostureCheckConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(PostureCheckConfig.class);
    }

    @Override
    protected void startUp()
    {
        activeTimeSinceReminder = Duration.ZERO;
        activeSince = client.getGameState() == GameState.LOGGED_IN ? Instant.now() : null;
        pausedSince = null;
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event)
    {
        Instant now = Instant.now();
        if (event.getGameState() == GameState.LOGGED_IN)
        {
            resumeReminderTimer(now);
            return;
        }

        pauseReminderTimer(now);
    }

    @Subscribe
    public void onGameTick(GameTick event)
    {
        if (client.getGameState() != GameState.LOGGED_IN)
        {
            return;
        }

        Instant now = Instant.now();
        resumeReminderTimer(now);
        Duration interval = Duration.ofMinutes(config.reminderIntervalMinutes());
        Duration activeTime = activeTimeSinceReminder.plus(Duration.between(activeSince, now));
        if (activeTime.compareTo(interval) < 0)
        {
            return;
        }

        triggerReminder();
        activeTimeSinceReminder = Duration.ZERO;
        activeSince = now;
    }

    private void pauseReminderTimer(Instant now)
    {
        if (activeSince == null)
        {
            return;
        }

        activeTimeSinceReminder = activeTimeSinceReminder.plus(Duration.between(activeSince, now));
        activeSince = null;
        pausedSince = now;
    }

    private void resumeReminderTimer(Instant now)
    {
        if (activeSince != null)
        {
            return;
        }

        if (pausedSince != null && Duration.between(pausedSince, now).compareTo(PAUSE_GRACE_PERIOD) >= 0)
        {
            activeTimeSinceReminder = Duration.ZERO;
        }

        activeSince = now;
        pausedSince = null;
    }

    private void triggerReminder()
    {
        if (config.chatMessageEnabled())
        {
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("<col=%06x>[Posture Check]</col> <col=%06x>%s</col>", config.prefixColor().getRGB() & 0xFFFFFF, config.messageColor().getRGB() & 0xFFFFFF, config.reminderText()), null);
        }

        if (!config.notificationEnabled())
        {
            return;
        }

        notifier.notify("[Posture Check] " + config.reminderText());
    }
}
