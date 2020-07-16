package me.freeze_dolphin.safe_backpack.listeners;

import me.freeze_dolphin.safe_backpack.api.Backpacks;
import me.freeze_dolphin.safe_backpack.lists.SafeBackpackItems;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

public class SafeCoolerListener implements Listener {

	public SafeCoolerListener(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onStarve(FoodLevelChangeEvent e) {
		if (e.getFoodLevel() < ((Player) e.getEntity()).getFoodLevel()) {
			Player p = (Player) e.getEntity();
			for (ItemStack item : p.getInventory().getContents()) {
				if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.COOLER, false)
						|| SlimefunManager.isItemSimiliar(item, SafeBackpackItems.REINFORCED_COOLER, false)) {
					Inventory inv = Backpacks.getInventory(p, item);
					if (inv != null) {
						ItemStack drink = null;
						for (ItemStack i: inv.getContents()) {
							if (i != null && i.getType() == Material.POTION && i.hasItemMeta()) {
								drink = i;
								break;
							}
						}
						if (drink != null) {
							PotionMeta im = (PotionMeta) drink.getItemMeta();
							for (PotionEffect effect: im.getCustomEffects()) {
								p.addPotionEffect(effect);
							}
							p.setSaturation(6F);
							p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1F, 1F);
							inv.removeItem(drink);
							Backpacks.saveBackpack(inv, item);
							break;
						}
					}
				}
			}
		}
	}

}
