package org.getspout.spout.precache;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.getspout.spout.Spout;
import org.getspout.spout.player.SimpleFileManager;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.io.FileUtil;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.packet.PacketValidatePrecache;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PrecacheManager {
	
	private static HashMap<Plugin, Long> plugins = new HashMap<Plugin, Long>();
	
	public static void onPlayerJoin(final SpoutPlayer player) {
		if (player.isSpoutCraftEnabled()) {
			if (plugins.size() > 0) {
				player.sendPacket(new PacketValidatePrecache(plugins));
			}
		}
	}
	
	public static void onPluginEnabled(Plugin plugin) {
		Bukkit.getServer().getLogger().info("[SpoutPlugin] Initializing precache for " + plugin.getName());
		
		boolean changed = false;
		
		File cacheFolder = new File(Spout.getInstance().getDataFolder(), "precache");
		if (!cacheFolder.exists()) {
			cacheFolder.mkdirs();
		}
		
		List<File> fileCaches = ((SimpleFileManager)SpoutManager.getFileManager()).getPluginPreLoginCache(plugin);
		List<String> urlCaches = ((SimpleFileManager)SpoutManager.getFileManager()).getPluginPreLoginUrlCache(plugin);
		List<CustomBlock> blocks = MaterialData.getCustomBlocks(plugin);
		
		if (blocks.size() > 0) {
			int i0 = 0;
			for(CustomBlock block : blocks) {
				byte i = -128;
				do {
					if (block.getBlockDesign(i) != null) {
						BlockDesign design = block.getBlockDesign(i);
						long beforeCRC = -1;
						File target = new File(getPluginCacheFolder(plugin), String.valueOf(i0)+".sbd");
						
						if (!target.getParentFile().exists()) {
							target.getParentFile().mkdirs();
						}
						
						if (target.exists()) {
							beforeCRC = FileUtil.getCRC(target,  new byte[(int) target.length()]);
							target.delete();
						}
						
						try {
							DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(target)));
							
							out.writeShort((short)block.getCustomId());
							out.writeByte(i);
							design.write(out);
							out.flush();
							out.close();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (target.exists()) {
							long newCRC = FileUtil.getCRC(target,  new byte[(int) target.length()]);
							if (newCRC != beforeCRC) {
								changed = true;
								Bukkit.getLogger().info("[SpoutPlugin] Block Design Cache Updated: " + block.getName() + " " + String.valueOf(i));
							}
						}
						
						i0++;
					}
					i++;
				} while (i != 127);
				
			}
		}
		
		if (fileCaches != null) {
			for(File file : fileCaches) {
				File target = new File(getPluginCacheFolder(plugin), file.getName());
				
				if (FileUtil.getCRC(target, new byte[(int) target.length()]) == FileUtil.getCRC(file, new byte[(int) file.length()])) {
					continue;
				}
				
				Bukkit.getLogger().info("[SpoutPlugin] File " + target.getName() + " is out of date: Updating now.");
				
				try {
					FileUtil.copyFileMkdirs(file, target);
					changed = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (urlCaches != null) {
			for(String url : urlCaches) {
				
				try {
					URL fileURL = new URL(url);
					String fileName = url.substring( url.lastIndexOf('/')+1, url.length() );
					
					File target = new File(getPluginCacheFolder(plugin), fileName);
					
					URLConnection connection = fileURL.openConnection();
					connection.addRequestProperty("User-Agent", Spout.getInstance().getDescription().getFullName());
					
					long urlLastModified = connection.getLastModified();
					
					if (target.exists() && urlLastModified == target.lastModified()) {
						continue;
					}
					
					if (target.exists()) {
						target.delete();
					}
					
					if (!target.getParentFile().exists()) {
						target.getParentFile().mkdirs();
					}
					
					Bukkit.getLogger().info("[SpoutPlugin] File " + target.getName() + " is out of date: Updating now.");
					ReadableByteChannel channel = Channels.newChannel(connection.getInputStream());
					
					FileOutputStream outputStream = new FileOutputStream(target);
					outputStream.getChannel().transferFrom(channel, 0, 1 << 24);
					outputStream.close();
					
					//update modified time on file
					target.setLastModified(urlLastModified);
					changed= true;
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (changed == true) {
			//rebuild the zip
			try {
				buildPrecacheZip(plugin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
		File zip = getPluginCacheZip(plugin);
		if (zip.exists()) {
			long crc = FileUtil.getCRC(zip, new byte[(int) zip.length()]);
			plugins.put(plugin, crc);
		}
	}
	
	public static File getPluginCacheZip(Plugin plugin) {
		File cacheFolder = new File(Spout.getInstance().getDataFolder(), "precache");
		return new File(cacheFolder, plugin.getDescription().getFullName() + ".zip");
	}
	
	public static File getPluginCacheFolder(Plugin plugin) {
		File cacheFolder = new File(Spout.getInstance().getDataFolder(), "precache");
		return new File(cacheFolder, plugin.getDescription().getName() +"/"+ plugin.getDescription().getVersion());
	}
	
	public static void buildPrecacheZip(Plugin plugin) throws IOException {
		Bukkit.getLogger().info("[SpoutPlugin] Building Precache for " + plugin.getName());
		
		File zip = getPluginCacheZip(plugin);
		if (zip.exists()) {
			zip.delete();
		}
		
		File cacheFolder = getPluginCacheFolder(plugin);
		
		if (!cacheFolder.exists()) {
			return;
		}
		
		File[] cacheFiles = cacheFolder.listFiles();
		
		if (cacheFiles.length < 1) {
			return;
		}
		
		FileOutputStream fos = new FileOutputStream(zip);
		ZipOutputStream zos = new ZipOutputStream(fos);
		
		for(File fileToCache : cacheFiles) {
			if (!fileToCache.isDirectory()) {
				addFileToZip(fileToCache, zos);
			}
		}
		
		zos.flush();
		zos.close();
	}
	
	public static void addFileToZip(File file, ZipOutputStream zip) throws IOException  {
		
		byte[] buf = new byte[1024];
		int len;
		FileInputStream in = new FileInputStream(file);
		zip.putNextEntry(new ZipEntry(file.getName()));
		while ((len = in.read(buf)) > 0) {
			zip.write(buf, 0, len);
		}
		in.close();
	}
}
