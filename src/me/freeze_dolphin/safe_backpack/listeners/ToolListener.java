package me.freeze_dolphin.safe_backpack.listeners;

import me.freeze_dolphin.safe_backpack.SafeBackpack;
import me.freeze_dolphin.safe_backpack.lists.SafeBackpackItems;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class ToolListener implements Listener {

	public ToolListener(SafeBackpack plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		ItemStack item = e.getItemInHand();
		if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.BACKPACK_SMALL, false)) e.setCancelled(true);
		else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.BACKPACK_MEDIUM, false)) e.setCancelled(true);
		else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.BACKPACK_LARGE, false)) e.setCancelled(true);
		else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.WOVEN_BACKPACK, false)) e.setCancelled(true);
		else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.GILDED_BACKPACK, false)) e.setCancelled(true);
		else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.BOUND_BACKPACK, false)) e.setCancelled(true);
		else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.COOLER, false)) e.setCancelled(true);
		else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.JOURNEY_BACKPACK, false)) e.setCancelled(true);
		else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.REINFORCED_COOLER, false)) e.setCancelled(true);
	}

}
