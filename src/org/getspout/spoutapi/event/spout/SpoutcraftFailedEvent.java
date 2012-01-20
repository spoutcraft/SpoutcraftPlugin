package org.getspout.spoutapi.event.spout;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.event.EventType;
import org.getspout.spoutapi.event.SpoutEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutcraftFailedEvent extends Event implements SpoutEvent {
	
	private static final long serialVersionUID = -5172371431162815630L;
	private static final HandlerList handlers = new HandlerList();
	private SpoutPlayer player;
	private static final EventType type = EventType.Spoutcraft_Failed;
	
	public SpoutcraftFailedEvent(SpoutPlayer player) {
		super("SpoutcraftFailedEvent");
		this.player = player;
	}

	/**
	 * Returns the player who just had their Spout SinglePlayer Mod enabled
	 * @return player
	 */
	public SpoutPlayer getPlayer() {
		return player;
	}

	@Override
	public EventType getEventType() {
		return type;
	}
	
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
