package me.freeze_dolphin.safe_backpack.listeners;

import java.util.Iterator;

import me.freeze_dolphin.safe_backpack.SafeBackpack;
import me.freeze_dolphin.safe_backpack.lists.SafeBackpackItems;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.Soul;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DamageListener implements Listener {

	public DamageListener(SafeBackpack plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onDamage(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Iterator<ItemStack> drops = e.getDrops().iterator();
			while (drops.hasNext()) {
				ItemStack item = drops.next();
				if (item != null) {
					if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.BOUND_BACKPACK, false)) {
						Soul.storeItem(e.getEntity().getUniqueId(), item);
						drops.remove();
					}
				}
			}

		}
	}

}
