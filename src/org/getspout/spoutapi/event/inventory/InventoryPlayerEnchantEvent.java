package org.getspout.spoutapi.event.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.event.EventType;
import org.getspout.spoutapi.event.SpoutEvent;

public class InventoryPlayerEnchantEvent extends InventoryEvent implements SpoutEvent {

    private static final long serialVersionUID = 2644169499292008813L;
    private final ItemStack before;
    private ItemStack result;
    private final int levelBefore;
    private int levelAfter;
    private static final EventType eventtype = EventType.Player_Enchant;
    
    public InventoryPlayerEnchantEvent(Player player, Inventory inventory, ItemStack before, ItemStack result, int levelBefore, int levelAfter) {
        super("InventoryPlayerEnchantEvent", player, inventory, player.getLocation());
        this.before = before;
        this.result = result;
        this.levelBefore = levelBefore;
        this.levelAfter = levelAfter;
    }

    public ItemStack getBefore() {
        return before;
    }

    public ItemStack getResult() {
        return result;
    }

    public void setResult(ItemStack result) {
        this.result = result;
    }

    public int getLevelBefore() {
        return levelBefore;
    }   

    public int getLevelAfter() {
        return levelAfter;
    }

    public void setLevelAfter(int levelAfter) {
        this.levelAfter = levelAfter;
    }

    @Override
    public EventType getEventType() {
        return eventtype;
    }
}
