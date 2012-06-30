package org.getspout.spoutapi.inventory;

import java.lang.reflect.Field;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class SpoutEnchantment extends Enchantment {
	public static final Enchantment UNSTACKABLE = new SpoutEnchantment(1000);
	public static final Enchantment DURABILITY = new SpoutEnchantment(1001);
	public static final Enchantment MAX_DURABILITY = new SpoutEnchantment(1002);

	public SpoutEnchantment(int id) {
		super(id);
		try {
			boolean val = Enchantment.isAcceptingRegistrations();
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(this);
			f.set(null, val);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "Spout Enchantment " + getId();
	}

	@Override
	public int getMaxLevel() {
		return 1001;
	}

	@Override
	public int getStartLevel() {
		return 1000;//Should be impossible to get :P
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ALL;
	}

	@Override
	public boolean conflictsWith(Enchantment paramEnchantment) {
		return false;
	}

	@Override
	public boolean canEnchantItem(ItemStack paramItemStack) {
		return true;
	}

}
