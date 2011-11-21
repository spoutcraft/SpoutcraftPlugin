package org.getspout.spoutapi.material.block;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.packet.PacketType;
import org.getspout.spoutapi.packet.PacketUtil;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.player.SpoutPlayer;

public class GenericCustomBlock extends GenericBlock implements CustomBlock, SpoutPacket {
	public BlockDesign design = new GenericBlockDesign();
	private String fullName;
	private int customId;
	private Plugin plugin;
	private CustomItem item;
	private int blockId;
	private boolean opaque;
	private float hardness = 1.5F;
	private float friction = 0.6F;
	private int lightLevel = 0;

	/**
	 * Creates a GenericCustomBlock with no model yet.
	 * 
	 * @param plugin creating the block
	 * @param name of the block
	 * @param isOpaque true if you want the block solid
	 */
	protected GenericCustomBlock(Plugin plugin, String name, boolean isOpaque) {
		super(name, isOpaque ? 1 : 20);
		this.opaque = isOpaque;
		item = new GenericCustomItem(plugin, name);
		this.blockId = isOpaque ? 1 : 20;
		this.plugin = plugin;
		this.fullName = item.getFullName();
		this.customId = item.getCustomId();
		MaterialData.addCustomBlock(this);
		this.setItemDrop(new SpoutItemStack(this, 1));
	}

	/**
	 * Creates a GenericCustomBlock with a specified Design and metadata
	 * 
	 * @param plugin creating the block
	 * @param name of the block
	 * @param isOpaque true if you want the block solid
	 * @param design to use for the block
	 * @param customMetaData for the block
	 */
	public GenericCustomBlock(Plugin plugin, String name, boolean isOpaque, BlockDesign design) {
		this(plugin, name, isOpaque);
		setBlockDesign(design);
	}

	/**
	 * Creates a basic GenericCustomblock with no design that is opaque/solid.
	 * 
	 * @param plugin creating the block
	 * @param name of the block
	 */
	public GenericCustomBlock(Plugin plugin, String name) {
		this(plugin, name, true);
	}
	
	@Override
	public boolean isOpaque() {
		return opaque;
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		item.setName(name);
	}

	@Override
	public BlockDesign getBlockDesign() {
		return design;
	}

	@Override
	public CustomBlock setBlockDesign(BlockDesign design) {
		this.design = design;
		SpoutManager.getMaterialManager().setCustomBlockDesign(this, design);
		SpoutManager.getMaterialManager().setCustomBlockDesign(this.getBlockItem(), design);
		SpoutManager.getMaterialManager().setCustomItemBlock(item, this);

		return this;
	}

	@Override
	public int getCustomId() {
		return customId;
	}

	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public Plugin getPlugin() {
		return plugin;
	}

	@Override
	public CustomItem getBlockItem() {
		return item;
	}
	
	@Override
	public int getRawId() {
		return this.item.getRawId();
	}
	
	@Override
	public int getRawData() {
		return this.item.getCustomId();
	}
	
	@Override
	public int getBlockId() {
		return this.blockId;
	}
	
	@Override
	public CustomBlock setItemDrop(ItemStack item) {
		SpoutManager.getMaterialManager().registerItemDrop(this, item);
		return this;
	}
	
	@Override
	public float getHardness() {
		return hardness;
	}
	
	@Override
	public CustomBlock setHardness(float hardness) {
		this.hardness = hardness;
		return this;
	}
	
	@Override
	public float getFriction() {
		return friction;
	}
	
	@Override
	public CustomBlock setFriction(float friction) {
		this.friction = friction;
		return this;
	}
	
	@Override
	public int getLightLevel() {
		return lightLevel;
	}
	
	@Override
	public CustomBlock setLightLevel(int level) {
		lightLevel = level;
		return this;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int changedId) {
	}

	@Override
	public void onBlockPlace(World world, int x, int y, int z) {
	}

	@Override
	public void onBlockPlace(World world, int x, int y, int z, LivingEntity living) {
	}

	@Override
	public void onBlockDestroyed(World world, int x, int y, int z) {
	}

	@Override
	public void onBlockDestroyed(World world, int x, int y, int z, LivingEntity living) {
	}

	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		return false;
	}

	@Override
	public void onEntityMoveAt(World world, int x, int y, int z, Entity entity) {
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player) {		
	}

	@Override
	public boolean isProvidingPowerTo(World world, int x, int y, int z,	BlockFace face) {
		return false;
	}

	@Override
	public boolean isIndirectlyProvidingPowerTo(World world, int x, int y, int z, BlockFace face) {
		return false;
	}

	@Override
	public int getNumBytes() {
		return 4 + PacketUtil.getNumBytes(getName()) + PacketUtil.getNumBytes(getPlugin().getDescription().getName()) + 1 + 4 + 4 + 4;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		customId = input.readInt();
		setName(PacketUtil.readString(input));
		plugin = Bukkit.getServer().getPluginManager().getPlugin(PacketUtil.readString(input));
		opaque = input.readBoolean();
		setFriction(input.readFloat());
		setHardness(input.readFloat());
		setLightLevel(input.readInt());
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(customId);
		PacketUtil.writeString(output, getName());
		PacketUtil.writeString(output, getPlugin().getDescription().getName());
		output.writeBoolean(isOpaque());
		output.writeFloat(getFriction());
		output.writeFloat(getHardness());
		output.writeInt(getLightLevel());
	}

	@Override
	public void run(int playerId) {}

	@Override
	public void failure(int playerId) {}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomBlock;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
