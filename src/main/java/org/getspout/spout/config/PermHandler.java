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
package org.getspout.spout.config;

import org.getspout.spoutapi.player.SpoutPlayer;

public class PermHandler {
	private PermHandler() {
	}

	public static boolean allowCheat(SpoutPlayer forWho, String which) {
		return forWho.hasPermission("spout.plugin.cheat." + which);
	}

	public static boolean forceCheat(SpoutPlayer forWho, String which) {
		return forWho.hasPermission("spout.plugin.force." + which);
	}

	public static boolean showCheat(SpoutPlayer forWho, String which) {
		return forWho.hasPermission("spout.plugin.show." + which);
	}

	public static boolean allowSkyCheat(SpoutPlayer forWho) {
		return allowCheat(forWho, "sky");
	}

	public static boolean forceSkyCheat(SpoutPlayer forWho) {
		return forceCheat(forWho, "sky");
	}

	public static boolean showSkyCheat(SpoutPlayer forWho) {
		return showCheat(forWho, "sky");
	}

	public static boolean allowClearWaterCheat(SpoutPlayer forWho) {
		return allowCheat(forWho, "clearwater");
	}

	public static boolean forceClearWaterCheat(SpoutPlayer forWho) {
		return forceCheat(forWho, "clearwater");
	}

	public static boolean showClearWaterCheat(SpoutPlayer forWho) {
		return showCheat(forWho, "clearwater");
	}

	public static boolean allowStarsCheat(SpoutPlayer forWho) {
		return allowCheat(forWho, "stars");
	}

	public static boolean forceStarsCheat(SpoutPlayer forWho) {
		return forceCheat(forWho, "stars");
	}

	public static boolean showStarsCheat(SpoutPlayer forWho) {
		return showCheat(forWho, "stars");
	}

	public static boolean allowWeatherCheat(SpoutPlayer forWho) {
		return allowCheat(forWho, "weather");
	}

	public static boolean forceWeatherCheat(SpoutPlayer forWho) {
		return forceCheat(forWho, "weather");
	}

	public static boolean showWeatherCheat(SpoutPlayer forWho) {
		return showCheat(forWho, "weather");
	}

	public static boolean allowTimeCheat(SpoutPlayer forWho) {
		return allowCheat(forWho, "time");
	}

	public static boolean allowCoordsCheat(SpoutPlayer forWho) {
		return allowCheat(forWho, "coords");
	}

	public static boolean allowEntityLabelCheat(SpoutPlayer forWho) {
		return allowCheat(forWho, "entitylabel");
	}

	public static boolean allowVoidFogCheat(SpoutPlayer forWho) {
		return allowCheat(forWho, "voidfog");
	}

	public static boolean forceVoidFogCheat(SpoutPlayer forWho) {
		return forceCheat(forWho, "voidfog");
	}

	public static boolean showVoidFogCheat(SpoutPlayer forWho) {
		return showCheat(forWho, "voidfog");
	}

	public static boolean allowFlightSpeedCheat(SpoutPlayer forWho) {
		return allowCheat(forWho, "flightspeed");
	}
}
