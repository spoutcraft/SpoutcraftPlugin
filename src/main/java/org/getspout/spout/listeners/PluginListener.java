/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
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
package org.getspout.spout.listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.getspout.spout.Spout;
import org.getspout.spout.player.SimpleFileManager;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.io.FileUtil;
import org.getspout.spoutapi.packet.PacketValidatePrecache;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PluginListener implements Listener {
	public PluginListener(Spout plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPluginDisable(PluginDisableEvent event) {
		for (Player i : Bukkit.getServer().getOnlinePlayers()) {
			SpoutPlayer p = SpoutManager.getPlayer(i);
			p.getMainScreen().removeWidgets(event.getPlugin());
		}
	}
	
	public static void onPlayerJoin(final SpoutPlayer player) {
		
		//build the zip if it doesnt exist.
		File zip = new File(Spout.getInstance().getDataFolder(), "precache.zip");
		if (!zip.exists()) {
			try {
				buildPrecacheZip(zip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
		if (player.isSpoutCraftEnabled()) {
			long crc = FileUtil.getCRC(zip, new byte[(int) zip.length()]);
			Bukkit.getLogger().info("[SpoutPlugin] Precache CRC: " + String.valueOf(crc));
			
			if (crc !=-1) {
				player.sendPacket(new PacketValidatePrecache(crc, Bukkit.getServer().getName()));
			}
		}
	}
	
	/**
	 * Handle the precache setup after all plugins have loaded
	 * @param event
	 */
	@EventHandler
	public void onPluginEnabled(PluginEnableEvent event) {
		
		Bukkit.getServer().getLogger().info("[SpoutPlugin] Initializing precache for " + event.getPlugin().getName());
		
		File cacheFolder = new File(Spout.getInstance().getDataFolder(), "precache");
		if (!cacheFolder.exists()) {
			cacheFolder.mkdirs();
		}
		
		//delete the final zip, and it will be recreated on player join anew
		File zip = new File(Spout.getInstance().getDataFolder(), "precache.zip");
		if (zip.exists()) {
			zip.delete();
		}
		
		List<File> fileCaches = ((SimpleFileManager)SpoutManager.getFileManager()).getPluginPreLoginCache(event.getPlugin());
		List<String> urlCaches = ((SimpleFileManager)SpoutManager.getFileManager()).getPluginPreLoginUrlCache(event.getPlugin());
		
		if (fileCaches != null) {
			for(File file : fileCaches) {
				Bukkit.getServer().getLogger().info("[SpoutPlugin] Preparing " + file.getName());
				
				File target = new File(cacheFolder, event.getPlugin().getName() +"/"+ file.getName());
				try {
					copyFile(file, target);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (urlCaches != null) {
			for(String url : urlCaches) {
				Bukkit.getServer().getLogger().info("[SpoutPlugin] Preparing " + url);
				
				
				try {
					URL fileURL = new URL(url);
					String fileName = url.substring( url.lastIndexOf('/')+1, url.length() );
					
					File target = new File(cacheFolder, event.getPlugin().getName() +"/"+ fileName);
					
					if (!target.getParentFile().exists()) {
						target.getParentFile().mkdirs();
					}
					
					//Todo: Check last-modified header against file last-modified, and dont redownload if it isnt newer
					URLConnection connection = fileURL.openConnection();
					connection.addRequestProperty("User-Agent", Spout.getInstance().getDescription().getFullName());
					
					ReadableByteChannel channel = Channels.newChannel(connection.getInputStream());
					
					FileOutputStream outputStream = new FileOutputStream(target);
					outputStream.getChannel().transferFrom(channel, 0, 1 << 24);
					outputStream.close();
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private void copyFile(File sourceFile, File destFile) throws IOException {
		
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}
		if(!destFile.exists()) {
			destFile.createNewFile();
		}
		
		FileChannel source = null;
		FileChannel destination = null;
		
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		finally {
			if(source != null) {
				source.close();
			}
			if(destination != null) {
				destination.close();
			}
		}
	}
	
	private static void buildPrecacheZip(File zip) throws IOException {
		
		Bukkit.getLogger().info("[SpoutPlugin] Building Precache");
		//get cache dirs
		File cacheFolder = new File(Spout.getInstance().getDataFolder(), "precache");
		
		if (!cacheFolder.exists()) {
			return;
		}
		
		File[] foldersToCache = cacheFolder.listFiles();
		
		if (foldersToCache.length < 1) {
			return;
		}
		
		FileOutputStream fos = new FileOutputStream(zip);
		ZipOutputStream zos = new ZipOutputStream(fos);
		
		for(File folderToCache : cacheFolder.listFiles()) {
			if (folderToCache.isDirectory()) {
				addFolderToZip(folderToCache, zos);
			}
		}
		
		zos.flush();
		zos.close();
	}
	
	private static void addFolderToZip(File folder, ZipOutputStream zip) throws IOException  {
		
		for (File file : folder.listFiles()) {
			addFileToZip(folder, file, zip);
		}
	}
	
	private static void addFileToZip(File folder, File file, ZipOutputStream zip) throws IOException  {
		
		byte[] buf = new byte[1024];
		int len;
		FileInputStream in = new FileInputStream(file);
		zip.putNextEntry(new ZipEntry(folder.getName() + "/" + file.getName()));
		while ((len = in.read(buf)) > 0) {
			zip.write(buf, 0, len);
		}
		in.close();
	}
}
