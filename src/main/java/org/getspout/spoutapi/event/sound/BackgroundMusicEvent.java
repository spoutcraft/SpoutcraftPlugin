/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.event.sound;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.sound.Music;

public class BackgroundMusicEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private Music music = null;
	private int volume;
	private final SpoutPlayer target;
	private String url = null;
	private boolean cancel = false;

	public BackgroundMusicEvent(Music music, int volume, SpoutPlayer target) {
		this.music = music;
		this.volume = volume;
		this.target = target;
	}

	public BackgroundMusicEvent(String url, int volume, SpoutPlayer target) {
		this.url = url;
		this.volume = volume;
		this.target = target;
	}

	/**
	 * Gets the music this song is playing, or null if it is custom
	 * @return music
	 */
	public Music getMusic() {
		return music;
	}

	/**
	 * Gets the music Url this song is playing, or null if it is official MC music
	 * @return music url
	 */
	public String getMusicUrl() {
		return url;
	}

	/**
	 * Gets the volume percent for this music
	 * @return volume percent
	 */
	public int getVolumePercent() {
		return volume;
	}

	/**
	 * Sets the volume percent for this music
	 * @param volume to set
	 */
	public void setVolumePercent(int volume) {
		this.volume = volume;
	}

	/**
	 * Gets the player that the music is intended for
	 * @return target
	 */
	public SpoutPlayer getTargetPlayer() {
		return target;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
