package me.freeze_dolphin.safe_backpack.listeners;

import me.freeze_dolphin.safe_backpack.SafeBackpack;
import me.freeze_dolphin.safe_backpack.command.ItemConvertor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;

public class AutoConvertorListener implements Listener {

	public AutoConvertorListener(SafeBackpack plug) {
		plug.getServer().getPluginManager().registerEvents(this, plug);
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("safebackpack.auto-convert")) {
			ItemConvertor.convert(e.getPlayer());
		}
	}

	@EventHandler (ignoreCancelled = true)
	public void onOpenInv(InventoryOpenEvent e) {
		if (e.isCancelled()) return;
		if (e.getInventory().getType().equals(InventoryType.PLAYER)
				|| e.getInventory().getType().equals(InventoryType.CHEST)
				|| e.getInventory().getType().equals(InventoryType.ENDER_CHEST)
				|| e.getInventory().getType().equals(InventoryType.SHULKER_BOX)
				|| e.getInventory().getType().equals(InventoryType.HOPPER)) {
			Player p = Bukkit.getPlayer(e.getPlayer().getName());
			if (p.hasPermission("safebackpack.auto-convert")) {
				ItemConvertor.convert(p);
			}
		}
	}

}
