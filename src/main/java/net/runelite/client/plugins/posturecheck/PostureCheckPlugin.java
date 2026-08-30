package net.runelite.client.plugins.posturecheck;

import com.google.inject.Provides;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
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
    @Inject
    private Client client;

    @Inject
    private PostureCheckConfig config;

    @Inject
    private Notifier notifier;

    private Instant lastReminder = Instant.now();

    @Provides
    PostureCheckConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(PostureCheckConfig.class);
    }

    @Override
    protected void startUp()
    {
        lastReminder = Instant.now();
    }

    @Subscribe
    public void onGameTick(GameTick event)
    {
        if (client.getGameState() != GameState.LOGGED_IN)
        {
            return;
        }

        long intervalMs = Duration.ofMinutes(config.reminderIntervalMinutes()).toMillis();
        if (Duration.between(lastReminder, Instant.now()).toMillis() < intervalMs)
        {
            return;
        }

        triggerReminder();
        lastReminder = Instant.now();
    }

    private void triggerReminder()
    {
        if (!config.notificationEnabled())
        {
            return;
        }

        notifier.notify(config.reminderText());
    }
}
