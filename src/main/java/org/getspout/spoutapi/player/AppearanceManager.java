/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.player;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;

import org.getspout.spoutapi.ClientOnly;

/**
 * @deprecated see {@link SpoutPlayer} methods instead.
 */
public interface AppearanceManager {
	/**
	 * Sets the skin for the target human that is visible to all players
	 * @param target to change the skin for
	 * @param Url for the new skin
	 *
	 * @deprecated see {@link SpoutPlayer#setSkin(String)} instead.
	 */
	@ClientOnly
	public void setGlobalSkin(HumanEntity target, String Url);

	/**
	 * Sets the skin for the target human that is only visible to the viewingPlayer
	 * @param viewingPlayer that will see the new skin
	 * @param target to change the skin for
	 * @param Url for the new skin
	 *
	 *
	 * @deprecated see {@link SpoutPlayer#setSkinfor (SpoutPlayer, String)} instead.
	 */
	@ClientOnly
	public void setPlayerSkin(SpoutPlayer viewingPlayer, HumanEntity target, String Url);

	/**
	 * Sets the cloak for the target human that is visible to all players
	 * @param target to change the cloak for
	 * @param Url for the new cloak
	 *
	 * @deprecated see {@link SpoutPlayer#setCape(String)} instead.
	 */
	@ClientOnly
	public void setGlobalCloak(HumanEntity target, String Url);

	/**
	 * Sets the cloak for the target human that is only visible to the viewingPlayer
	 * @param viewingPlayer that will see the new cloak
	 * @param target to change the cloak for
	 * @param Url for the new cloak
	 *
	 * @deprecated see {@link SpoutPlayer#setCapefor(SpoutPlayer, String)} instead.
	 */
	@ClientOnly
	public void setPlayerCloak(SpoutPlayer viewingPlayer, HumanEntity target, String Url);

	/**
	 * Sets the title for the target living entity that is only visible to the viewingPlayer
	 * Note: if the title has newline characters ('\n') in it, it will be rendered over multiple lines
	 * @param viewingPlayer that will see the new title
	 * @param target to change the title for
	 * @param title to set to the target
	 *
	 * @deprecated see {@link SpoutPlayer#setTitlefor (SpoutPlayer, String)} instead.
	 */
	@ClientOnly
	public void setPlayerTitle(SpoutPlayer viewingPlayer, LivingEntity target, String title);

	/**
	 * Sets the title for the target living entity that is visible to all players
	 * Note: if the title has newline characters ('\n') in it, it will be rendered over multiple lines
	 * @param target to change the title for
	 * @param title to set to the target
	 *
	 * @deprecated see {@link SpoutPlayer#setTitle(String)} instead.
	 */
	@ClientOnly
	public void setGlobalTitle(LivingEntity target, String title);

	/**
	 * Hides title for the target living entity that is only visible to the viewingPlayer
	 * @param viewingPlayer that will not see the title
	 * @param target to hide the title for
	 *
	 * @deprecated see {@link SpoutPlayer#hideTitleFrom(SpoutPlayer)} instead.
	 */
	@ClientOnly
	public void hidePlayerTitle(SpoutPlayer viewingPlayer, LivingEntity target);

	/**
	 * Hides the title for the target living entity that is visible to all players
	 * @param target to hide the title for
	 *
	 * @deprecated see {@link SpoutPlayer#hideTitle()} instead.
	 */
	@ClientOnly
	public void hideGlobalTitle(LivingEntity target);

	/**
	 * Get's the current skin Url for the viewing player of the target
	 * @param viewingPlayer viewing the target
	 * @param target to get the skin Url for
	 * @return skin Url
	 *
	 * @deprecated see {@link SpoutPlayer#getSkin(SpoutPlayer)} instead.
	 */
	@ClientOnly
	public String getSkinUrl(SpoutPlayer viewingPlayer, HumanEntity target);

	/**
	 * Reset's the skin for the target for all players
	 * @param target to reset the skin for
	 *
	 * @deprecated see {@link SpoutPlayer#resetSkin()} instead.
	 */
	@ClientOnly
	public void resetGlobalSkin(HumanEntity target);

	/**
	 * Reset's the skin for the target for only the viewingPlayer
	 * @param viewingPlayer that will see the reset skin
	 * @param target to reset the skin for
	 *
	 * @deprecated see {@link SpoutPlayer#resetSkinfor (SpoutPlayer)} instead.
	 */
	@ClientOnly
	public void resetPlayerSkin(SpoutPlayer viewingPlayer, HumanEntity target);

	/**
	 * Resets the cloak for the target for all players
	 * @param target to reset the cloak for
	 *
	 * @deprecated see {@link SpoutPlayer#resetCape()} instead.
	 */
	@ClientOnly
	public void resetGlobalCloak(HumanEntity target);

	/**
	 * Resets the cloak for the target for only the viewingPlayer
	 * @param viewingPlayer that will see the reset cloak
	 * @param target to reset the cloak for
	 *
	 * @deprecated see {@link SpoutPlayer#resetCapefor(SpoutPlayer)} instead.
	 */
	@ClientOnly
	public void resetPlayerCloak(SpoutPlayer viewingPlayer, HumanEntity target);

	/**
	 * Resets the title for the target for only the viewingPlayer
	 * @param viewingPlayer that will see the reset title
	 * @param target to reset the title for
	 *
	 * @deprecated see {@link SpoutPlayer#resetTitlefor (SpoutPlayer)} instead.
	 */
	@ClientOnly
	public void resetPlayerTitle(SpoutPlayer viewingPlayer, LivingEntity target);

	/**
	 * Resets the tite for the target for all players
	 * @param target to reset the title for
	 *
	 * @deprecated see {@link SpoutPlayer#resetTitle()} instead.
	 */
	@ClientOnly
	public void resetGlobalTitle(LivingEntity target);

	/**
	 * Gets the cloak Url currently set for the viewingPlayer seeing the target
	 * @param viewingPlayer that sees the target
	 * @param target that has the cloak
	 * @return cloak Url
	 *
	 * @deprecated see {@link SpoutPlayer#getCape()} instead.
	 */
	@ClientOnly
	public String getCloakUrl(SpoutPlayer viewingPlayer, HumanEntity target);

	/**
	 * Gets the title currently set for the viewingPlayer seeing the target
	 * @param viewingPlayer that sees the target
	 * @param target that has the title
	 * @return title
	 *
	 * @deprecated see {@link SpoutPlayer#getTitle()} instead.
	 */
	@ClientOnly
	public String getTitle(SpoutPlayer viewingPlayer, LivingEntity target);

	/**
	 * Resets the skins of all humans and players back to their defaults
	 */
	@ClientOnly
	@Deprecated
	public void resetAllSkins();

	/**
	 * Resets the cloaks of all humans and players back to their defaults
	 */
	@ClientOnly
	@Deprecated
	public void resetAllCloaks();

	/**
	 * Resets the titles of all living entities back to their defaults
	 */
	@ClientOnly
	@Deprecated
	public void resetAllTitles();

	/**
	 * Resets all skins, cloaks, and titles back to their defaults
	 */
	@ClientOnly
	@Deprecated
	public void resetAll();

	/**
	 * Sets a new skin for the given target.
	 * @param viewingPlayer that sees the target
	 * @param target to change the skin for
	 * @param url of the texture
	 *
	 * @deprecated see {@link SpoutPlayer#setEntitySkin(LivingEntity, String, EntitySkinType)} instead.
	 */
	@ClientOnly
	public void setEntitySkin(SpoutPlayer viewingPlayer, LivingEntity target, String url, EntitySkinType type);

	/**
	 * Sets a new secondary skin for the given target. This skin will be used for mobs that have 2 "states" (spiders, ghasts, sheep).
	 * @param viewingPlayer that sees the target
	 * @param target to change the skin for
	 * @param url of the texture
	 */
	@ClientOnly
	@Deprecated
	public void setGlobalEntitySkin(LivingEntity entity, String url, EntitySkinType type);

	/**
	 * Resets the skin for the given entity back to the users default texture pack skin
	 * @param entity to reset the skin for
	 *
	 * @deprecated see {@link SpoutPlayer#resetEntitySkin(LivingEntity)} instead.
	 */
	@ClientOnly
	void resetEntitySkin(LivingEntity entity);
}
