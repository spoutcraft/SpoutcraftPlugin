/*
 * This file is part of SpoutPlugin (http://www.spout.org/).
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
package org.getspout.spout.player;

import java.util.HashMap;

import org.getspout.spoutapi.player.EntitySkinType;

public class SpoutEntitySkin {
	private HashMap<EntitySkinType, String> textures = new HashMap<EntitySkinType, String>();
	public SpoutEntitySkin() {

	}

	public void setSkin(EntitySkinType type, String url) {
		textures.put(type, url);
	}

	public String getSkin(EntitySkinType type) {
		return textures.get(type);
	}

	public void reset() {
		textures.clear();
	}
}
