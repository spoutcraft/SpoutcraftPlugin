/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
package org.getspout.spoutapi.player;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.ClientOnly;

public interface FileManager {
	/**
	 * Gets a list of all the names of the files pre-cached on the client for this plugin.
	 * <p/>
	 * The cache may be up to 60 seconds out of date.
	 * @param plugin that the files are cached for
	 * @return list of names of cached files
	 */
	@ClientOnly
	public List<String> getCache(Plugin plugin);

	/**
	 * Adds a file to the pre-login cache for clients. This file will only be downloaded when clients login.
	 * <p/>
	 * This is advantageous because it will not cause any latency issues for larger files or lots of media.
	 * <p/>
	 * Ideally, precached files should be set onEnable.
	 * @param plugin caching the files.
	 * @param file   to pre-cache
	 * @return true if the file was pre-cached
	 */
	@ClientOnly
	public boolean addToPreLoginCache(Plugin plugin, File file);

	/**
	 * Adds a file to the pre-login cache for clients. This file will only be downloaded when clients login.
	 * <p/>
	 * This is advantageous because it will not cause any latency issues for larger files or lots of media.
	 * <p/>
	 * Ideally, precached files should be set onEnable.
	 * @param plugin  caching the files.
	 * @param fileUrl to pre-cache
	 * @return true if the file was pre-cached
	 */
	@ClientOnly
	public boolean addToPreLoginCache(Plugin plugin, String fileUrl);

	/**
	 * Adds a list of files to the pre-login cache for clients. These files will only be downloaded when clients login.
	 * <p/>
	 * This is advantageous because it will not cause any latency issues for larger files or lots of media.
	 * <p/>
	 * Ideally, precached files should be set onEnable.
	 * @param plugin caching the files.
	 * @param files  to pre-cache
	 * @return true if the files were pre-cached
	 */
	@ClientOnly
	public boolean addToPreLoginCache(Plugin plugin, Collection<File> files);

	/**
	 * Adds a list of files to the pre-login cache for clients. These files will only be downloaded when clients login.
	 * <p/>
	 * This is advantageous because it will not cause any latency issues for larger files or lots of media.
	 * <p/>
	 * Ideally, precached files should be set onEnable.
	 * @param plugin   caching the files.
	 * @param fileUrls to pre-cache
	 * @return true if the files were pre-cached
	 */
	@ClientOnly
	public boolean addToPreLoginCache(Plugin plugin, List<String> fileUrls);

	/**
	 * Sends the contents of the input stream to clients during prelogin. The contents of the steam will only be downloaded
	 * when clients login.
	 * <p/>
	 * This is advantageous because it will not cause any latency issues for larger files or lots of media.
	 * <p/>
	 * Ideally, precached files should be set onEnable.
	 * @param plugin caching the files.
	 * @param input  stream containing the bytes to be read
	 * @param file   name of the resulting file.
	 * @return true if the files were pre-cached
	 */
	@ClientOnly
	public boolean addToPreLoginCache(Plugin plugin, InputStream input, String fileName);

	/**
	 * Adds a file to the cache for clients. This file will be downloaded immediately for any online players, and upon login of new clients.
	 * <p/>
	 * This is not recommended for larger files, since the extra latency for large downloads may disrupt the player's experience.
	 * @param file to pre-cache
	 * @return true if the file was pre-cached
	 */
	public boolean addToCache(Plugin plugin, File file);

	/**
	 * Adds a file to the cache for clients. This file will be downloaded immediately for any online players, and upon login of new clients.
	 * <p/>
	 * This is not recommended for larger files, since the extra latency for large downloads may disrupt the player's experience.
	 * @param fileUrl to pre-cache
	 * @return true if the file was pre-cached
	 */
	public boolean addToCache(Plugin plugin, String fileUrl);

	/**
	 * Adds a list of files to the cache for clients. These files will be downloaded immediately for any online players, and upon login of new clients.
	 * <p/>
	 * This is not recommended for larger files, since the extra latency for large downloads may disrupt the player's experience.
	 * @param files to pre-cache
	 * @return true if the files were pre-cached
	 */
	@ClientOnly
	public boolean addToCache(Plugin plugin, Collection<File> file);

	/**
	 * Adds a list of files to the cache for clients. These files will be downloaded immediately for any online players, and upon login of new clients.
	 * <p/>
	 * This is not recommended for larger files, since the extra latency for large downloads may disrupt the player's experience.
	 * @param fileUrls to pre-cache
	 * @return true if the files were pre-cached
	 */
	@ClientOnly
	public boolean addToCache(Plugin plugin, List<String> fileUrls);

	/**
	 * Sends the contents of the input stream to clients. The contents of the steam will only be downloaded
	 * immediately.
	 * <p/>
	 * This is not recommended for larger files, since the extra latency for large downloads may disrupt the player's experience.
	 * @param plugin caching the files.
	 * @param input  stream containing the bytes to be read
	 * @param file   name of the resulting file.
	 * @return true if the files were pre-cached
	 */
	@ClientOnly
	public boolean addToCache(Plugin plugin, InputStream input, String fileName);

	/**
	 * Removes the given filename from the cache, if it exists.
	 * @param plugin that the file is cached for
	 * @param file   name to remove
	 */
	@ClientOnly
	public void removeFromCache(Plugin plugin, String file);

	/**
	 * Removes the given filenames from the cache, if they exists.
	 * @param plugin that the files are cached for
	 * @param file   names to remove
	 */
	@ClientOnly
	public void removeFromCache(Plugin plugin, List<String> file);

	/**
	 * Checks if the file is approved for pre-caching. The file types approved for pre-caching are as follows:
	 * .txt, .yml, .xml, .png, .jpg, .ogg, .midi, .wav, .zip
	 * @param file to check
	 * @return true if the file can be cached on the client
	 */
	@ClientOnly
	public boolean canCache(File file);

	/**
	 * Checks if the fileUrl is approved for pre-caching. The file types approved for pre-caching are as follows:
	 * .txt, .yml, .xml, .png, .jpg, .ogg, .midi, .wav, .zip
	 * @param fileUrl to check
	 * @return true if the file can be cached on the client
	 */
	@ClientOnly
	public boolean canCache(String fileUrl);
}
