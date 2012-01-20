package org.getspout.spoutapi.event.screen;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.event.EventType;
import org.getspout.spoutapi.event.SpoutEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

import java.awt.image.BufferedImage;

public class ScreenshotReceivedEvent extends Event implements SpoutEvent {

    private static final long serialVersionUID = 117L;
    private static final HandlerList handlers = new HandlerList();
    private static final EventType type = EventType.Screenshot_Received;
    private BufferedImage screenshot;
    private SpoutPlayer player;

    public ScreenshotReceivedEvent(SpoutPlayer sp, BufferedImage image)
    {
        super("ScreenshotReceivedEvent");
        screenshot = image;
        player = sp;
    }

    @Override
    public EventType getEventType() {
        return type;
    }

    public BufferedImage getScreenshot() {
        return screenshot;
    }

    public SpoutPlayer getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
