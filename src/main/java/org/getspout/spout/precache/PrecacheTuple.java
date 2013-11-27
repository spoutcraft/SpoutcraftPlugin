/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spout.precache;

public class PrecacheTuple {
	private String plugin;
	private String version;
	private long crc;

	/**
	 * A tuple for zip -> crc
	 * @param plugin - Name of the plugin
	 * @param version - plugin version
	 * @param crc - crc of the precache file
	 */
	public PrecacheTuple(String plugin, String version, long crc) {
		this.plugin = plugin;
		this.version = version;
		this.crc = crc;
	}

	public String getPlugin() {
		return plugin;
	}

	public String getVersion() {
		return version;
	}

	public long getCrc() {
		return crc;
	}
}
