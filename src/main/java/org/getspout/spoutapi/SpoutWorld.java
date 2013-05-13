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
package org.getspout.spoutapi;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.BlockChangeDelegate;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Difficulty;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import org.getspout.spout.block.SpoutCraftChunk;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.SpoutChunk;

/**
 * A proxy class providing easy to use Spout objects from the {@link World}
 * interface. <br/>
 * <br/>
 * Not meant for long term storage. If you need to store this, store the UUID to
 * the world. <br/>
 * <br/>
 * Note: {@link #equals(Object)} will NOT work. Compare World UUIDs instead!
 */
@SuppressWarnings("deprecation")
public class SpoutWorld implements World {
	private World world;

	public SpoutWorld(World world) {
		this.world = world;
	}

	@Override
	public boolean createExplosion(Location loc, float power) {
		return world.createExplosion(loc, power);
	}

	@Override
	public boolean createExplosion(Location loc, float power, boolean setFire) {
		return world.createExplosion(loc, power, setFire);
	}

	@Override
	public boolean createExplosion(double x, double y, double z, float power) {
		return world.createExplosion(x, y, z, power);
	}

	@Override
	public boolean createExplosion(double x, double y, double z, float power, boolean setFire) {
		return world.createExplosion(x, y, z, power, setFire);
	}

	@Override
	public boolean createExplosion(double v, double v2, double v3, float v4, boolean b, boolean b2) {
		return world.createExplosion(v, v2, v3, v4, b, b2);
	}

	@Override
	public Item dropItem(Location location, ItemStack item) {
		return world.dropItem(location, item);
	}

	@Override
	public Item dropItemNaturally(Location location, ItemStack item) {
		return world.dropItemNaturally(location, item);
	}

	@Override
	public boolean generateTree(Location location, TreeType type) {
		return world.generateTree(location, type);
	}

	@Override
	public boolean generateTree(Location loc, TreeType type, BlockChangeDelegate delegate) {
		return world.generateTree(loc, type, delegate);
	}

	@Override
	public boolean getAllowAnimals() {
		return world.getAllowAnimals();
	}

	@Override
	public boolean getAllowMonsters() {
		return world.getAllowMonsters();
	}

	@Override
	public Biome getBiome(int x, int z) {
		return world.getBiome(x, z);
	}

	@Override
	public void setBiome(int x, int z, Biome biome) {
		world.setBiome(x, z, biome);
	}

	@Override
	public SpoutBlock getBlockAt(Location location) {
		return getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	@Override
	public SpoutBlock getBlockAt(int x, int y, int z) {
		return (SpoutBlock) world.getBlockAt(x, y, z);
	}

	@Override
	public int getBlockTypeIdAt(Location location) {
		return world.getBlockTypeIdAt(location);
	}

	@Override
	public int getBlockTypeIdAt(int x, int y, int z) {
		return world.getBlockTypeIdAt(x, y, z);
	}

	@Override
	public SpoutChunk getChunkAt(Location location) {
		return SpoutCraftChunk.getChunkSafe(world.getChunkAt(location));
	}

	@Override
	public SpoutChunk getChunkAt(Block block) {
		return SpoutCraftChunk.getChunkSafe(world.getChunkAt(block));
	}

	public SpoutChunk getChunkAt(int x, int y, int z) {
		return getChunkAt(x >> 4, z >> 4);
	}

	@Override
	public SpoutChunk getChunkAt(int x, int z) {
		return SpoutCraftChunk.getChunkSafe(world.getChunkAt(x, z));
	}

	@Override
	public Difficulty getDifficulty() {
		return world.getDifficulty();
	}

	@Override
	public ChunkSnapshot getEmptyChunkSnapshot(int x, int z, boolean includeBiome, boolean includeBiomeTempRain) {
		return world.getEmptyChunkSnapshot(x, z, includeBiome, includeBiomeTempRain);
	}

	@Override
	public List<Entity> getEntities() {
		return world.getEntities();
	}

	@Override
	public World.Environment getEnvironment() {
		return world.getEnvironment();
	}

	@Override
	public long getFullTime() {
		return world.getFullTime();
	}

	@Override
	public ChunkGenerator getGenerator() {
		return world.getGenerator();
	}

	@Override
	public SpoutBlock getHighestBlockAt(Location location) {
		return (SpoutBlock) world.getHighestBlockAt(location);
	}

	@Override
	public SpoutBlock getHighestBlockAt(int x, int z) {
		return (SpoutBlock) world.getHighestBlockAt(x, z);
	}

	@Override
	public int getHighestBlockYAt(Location location) {
		return world.getHighestBlockYAt(location);
	}

	@Override
	public int getHighestBlockYAt(int x, int z) {
		return world.getHighestBlockYAt(x, z);
	}

	@Override
	public double getHumidity(int x, int z) {
		return world.getHumidity(x, z);
	}

	@Override
	public boolean getKeepSpawnInMemory() {
		return world.getKeepSpawnInMemory();
	}

	@Override
	public List<LivingEntity> getLivingEntities() {
		return world.getLivingEntities();
	}

	@Override
	public SpoutChunk[] getLoadedChunks() {
		Chunk[] loaded = world.getLoadedChunks();
		SpoutChunk[] chunks = new SpoutChunk[loaded.length];
		for (int i = 0; i < loaded.length; i++) {
			chunks[i] = (SpoutChunk) loaded[i];
		}
		return chunks;
	}

	@Override
	public int getMaxHeight() {
		return world.getMaxHeight();
	}

	public int getXBitShifts() {
		return SpoutManager.getWorldManager().getWorldXShiftBits(world);
	}

	public int getZBitShifts() {
		return SpoutManager.getWorldManager().getWorldZShiftBits(world);
	}

	@Override
	public String getName() {
		return world.getName();
	}

	@Override
	public boolean getPVP() {
		return world.getPVP();
	}

	@Override
	public List<Player> getPlayers() {
		return world.getPlayers();
	}

	@Override
	public List<BlockPopulator> getPopulators() {
		return world.getPopulators();
	}

	@Override
	public int getSeaLevel() {
		return world.getSeaLevel();
	}

	@Override
	public long getSeed() {
		return world.getSeed();
	}

	@Override
	public Location getSpawnLocation() {
		return world.getSpawnLocation();
	}

	@Override
	public double getTemperature(int x, int z) {
		return world.getTemperature(x, z);
	}

	@Override
	public int getThunderDuration() {
		return world.getThunderDuration();
	}

	@Override
	public long getTime() {
		return world.getTime();
	}

	@Override
	public UUID getUID() {
		return world.getUID();
	}

	@Override
	public int getWeatherDuration() {
		return world.getWeatherDuration();
	}

	@Override
	public boolean hasStorm() {
		return world.hasStorm();
	}

	@Override
	public boolean isAutoSave() {
		return world.isAutoSave();
	}

	@Override
	public boolean isChunkLoaded(Chunk chunk) {
		return world.isChunkLoaded(chunk);
	}

	@Override
	public boolean isChunkLoaded(int x, int z) {
		return world.isChunkLoaded(x, z);
	}

	@Override
	public boolean isChunkInUse(int i, int i1) {
		return world.isChunkInUse(i, i1);
	}

	@Override
	public boolean isThundering() {
		return world.isThundering();
	}

	@Override
	public void loadChunk(Chunk chunk) {
		world.loadChunk(chunk);
	}

	@Override
	public void loadChunk(int x, int z) {
		world.loadChunk(x, z);
	}

	@Override
	public boolean loadChunk(int x, int z, boolean generate) {
		return world.loadChunk(x, z, generate);
	}

	@Override
	public void playEffect(Location location, Effect effect, int data) {
		world.playEffect(location, effect, data);
	}

	@Override
	public void playEffect(Location location, Effect effect, int data, int radius) {
		world.playEffect(location, effect, data, radius);
	}

	@Override
	public <T> void playEffect(Location location, Effect effect, T data) {
		world.playEffect(location, effect, data);
	}

	@Override
	public <T> void playEffect(Location location, Effect effect, T data, int radius) {
		world.playEffect(location, effect, data, radius);
	}

	@Override
	public boolean refreshChunk(int x, int z) {
		return world.refreshChunk(x, z);
	}

	@Override
	public boolean regenerateChunk(int x, int z) {
		return world.regenerateChunk(x, z);
	}

	@Override
	public void save() {
		world.save();
	}

	@Override
	public void setAutoSave(boolean value) {
		world.setAutoSave(value);
	}

	@Override
	public void setDifficulty(Difficulty difficulty) {
		world.setDifficulty(difficulty);
	}

	@Override
	public void setFullTime(long time) {
		world.setFullTime(time);
	}

	@Override
	public void setKeepSpawnInMemory(boolean keepLoaded) {
		world.setKeepSpawnInMemory(keepLoaded);
	}

	@Override
	public void setPVP(boolean pvp) {
		world.setPVP(pvp);
	}

	@Override
	public void setSpawnFlags(boolean allowMonsters, boolean allowAnimals) {
		world.setSpawnFlags(allowMonsters, allowAnimals);
	}

	@Override
	public boolean setSpawnLocation(int x, int y, int z) {
		return world.setSpawnLocation(x, y, z);
	}

	@Override
	public void setStorm(boolean hasStorm) {
		world.setStorm(hasStorm);
	}

	@Override
	public void setThunderDuration(int duration) {
		world.setThunderDuration(duration);
	}

	@Override
	public void setThundering(boolean thundering) {
		world.setThundering(thundering);
	}

	@Override
	public void setTime(long time) {
		world.setTime(time);
	}

	@Override
	public void setWeatherDuration(int duration) {
		world.setWeatherDuration(duration);
	}

	@Override
	public <T extends Entity> T spawn(Location location, Class<T> clazz) throws IllegalArgumentException {
		return world.spawn(location, clazz);
	}

	@Override
	public FallingBlock spawnFallingBlock(Location location, Material material, byte b) throws IllegalArgumentException {
		return world.spawnFallingBlock(location, material, b);
	}

	@Override
	public FallingBlock spawnFallingBlock(Location location, int i, byte b) throws IllegalArgumentException {
		return world.spawnFallingBlock(location, i, b);
	}

	@Override
	public Entity spawnEntity(Location location, EntityType entityType) {
		return world.spawnEntity(location, entityType);
	}

	@Override
	public Arrow spawnArrow(Location location, Vector velocity, float speed, float spread) {
		return world.spawnArrow(location, velocity, speed, spread);
	}

	public LivingEntity spawnCreature(Location loc, CreatureType type) {
		return world.spawnCreature(loc, type);
	}

	@Override
	public LightningStrike strikeLightning(Location loc) {
		return world.strikeLightning(loc);
	}

	@Override
	public LightningStrike strikeLightningEffect(Location loc) {
		return world.strikeLightningEffect(loc);
	}

	@Override
	public boolean unloadChunk(Chunk chunk) {
		return world.unloadChunk(chunk);
	}

	@Override
	public boolean unloadChunk(int x, int z) {
		return world.unloadChunk(x, z);
	}

	@Override
	public boolean unloadChunk(int x, int z, boolean save) {
		return world.unloadChunk(x, z, save);
	}

	@Override
	public boolean unloadChunk(int x, int z, boolean save, boolean safe) {
		return world.unloadChunk(x, z, save, safe);
	}

	@Override
	public boolean unloadChunkRequest(int x, int z) {
		return world.unloadChunkRequest(x, z);
	}

	@Override
	public boolean unloadChunkRequest(int x, int z, boolean safe) {
		return world.unloadChunkRequest(x, z, safe);
	}

	@Override
	public int hashCode() {
		return getUID().hashCode();
	}

	@Override
	public File getWorldFolder() {
		return world.getWorldFolder();
	}

	@Override
	public Set<String> getListeningPluginChannels() {
		return world.getListeningPluginChannels();
	}

	@Override
	public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
		world.sendPluginMessage(arg0, arg1, arg2);
	}

	@Override
	public WorldType getWorldType() {
		return world.getWorldType();
	}

	public <T extends Entity> Collection<T> getEntitiesByClass(Class<T>... classes) {
		return world.getEntitiesByClass(classes);
	}

	@Override
	public boolean canGenerateStructures() {
		return world.canGenerateStructures();
	}

	@Override
	public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> type) {
		return getEntitiesByClass(type);
	}

	@Override
	public Collection<Entity> getEntitiesByClasses(Class<?>... types) {
		return getEntitiesByClasses(types);
	}

	@Override
	public long getTicksPerAnimalSpawns() {
		return getTicksPerAnimalSpawns();
	}

	@Override
	public void setTicksPerAnimalSpawns(int i) {
		setTicksPerAnimalSpawns(i);
	}

	@Override
	public long getTicksPerMonsterSpawns() {
		return world.getTicksPerMonsterSpawns();
	}

	@Override
	public void setTicksPerMonsterSpawns(int i) {
		world.setTicksPerMonsterSpawns(i);
	}

	@Override
	public int getMonsterSpawnLimit() {
		return world.getMonsterSpawnLimit();
	}

	@Override
	public void setMonsterSpawnLimit(int i) {
		world.setMonsterSpawnLimit(i);
	}

	@Override
	public int getAnimalSpawnLimit() {
		return world.getAnimalSpawnLimit();
	}

	@Override
	public void setAnimalSpawnLimit(int i) {
		world.setAnimalSpawnLimit(i);
	}

	@Override
	public int getWaterAnimalSpawnLimit() {
		return world.getWaterAnimalSpawnLimit();
	}

	@Override
	public void setWaterAnimalSpawnLimit(int i) {
		world.setWaterAnimalSpawnLimit(i);
	}

	@Override
	public int getAmbientSpawnLimit() {
		return world.getAmbientSpawnLimit();
	}

	@Override
	public void setAmbientSpawnLimit(int i) {
		world.setAmbientSpawnLimit(i);
	}

	@Override
	public void playSound(Location location, Sound sound, float v, float v1) {
		world.playSound(location, sound, v, v1);
	}

	@Override
	public String[] getGameRules() {
		return world.getGameRules();
	}

	@Override
	public String getGameRuleValue(String s) {
		return world.getGameRuleValue(s);
	}

	@Override
	public boolean setGameRuleValue(String s, String s1) {
		return setGameRuleValue(s, s1);
	}

	@Override
	public boolean isGameRule(String s) {
		return world.isGameRule(s);
	}

	@Override
	public LivingEntity spawnCreature(Location arg0, EntityType arg1) {
		return world.spawnCreature(arg0, arg1);
	}

	@Override
	public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
		world.setMetadata(metadataKey, newMetadataValue);
	}

	@Override
	public List<MetadataValue> getMetadata(String metadataKey) {
		return world.getMetadata(metadataKey);
	}

	@Override
	public boolean hasMetadata(String metadataKey) {
		return world.hasMetadata(metadataKey);
	}

	@Override
	public void removeMetadata(String metadataKey, Plugin owningPlugin) {
		world.removeMetadata(metadataKey, owningPlugin);
	}
}
