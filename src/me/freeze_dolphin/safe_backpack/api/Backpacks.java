package me.freeze_dolphin.safe_backpack.api;

import java.io.File;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Backpacks {

	public static String createBackpack(Player p, int size) {
		int id = 0;
		while (id < 1000) {
			if (!new File("data-storage/SafeBackpacks/backpacks/" + p.getUniqueId() + "#" + id + ".yml").exists()) {
				break;
			}
			id++;
		}

		Config cfg = new Config(new File("data-storage/SafeBackpacks/backpacks/" + p.getUniqueId() + "#" + id + ".yml"));
		cfg.setValue("size", Integer.valueOf(size));
		cfg.save();
		return p.getUniqueId() + "#" + id;
	}

	public static void openBackpack(Player p, ItemStack item) {
		Inventory inv = getInventory(p, item);
		if (inv != null) p.openInventory(inv); 
	}

	public static Inventory getInventory(Player p, ItemStack item) {
		if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasLore()) return null; 
		int id = -1;
		String uuid = "";
		for (String line : item.getItemMeta().getLore()) {
			if (line.startsWith(ChatColor.translateAlternateColorCodes('&', "&7ID: ")) && line.contains("#")) {
				try {
					id = Integer.parseInt(line.split("#")[1]);
					uuid = line.split("#")[0].replace(ChatColor.translateAlternateColorCodes('&', "&7ID: "), "");
				} catch (NumberFormatException numberFormatException) {}
			}
		} 


		if (id >= 0) {
			Config cfg = new Config(new File("data-storage/SafeBackpacks/backpacks/" + uuid + "#" + id + ".yml"));
			int size = cfg.getInt("size");
			Inventory inv = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', item.getItemMeta().getDisplayName() + " &8[" + size + " 格]"));
			for (int i = 0; i < size; i++) {
				inv.setItem(i, cfg.getItem("contents." + i));
			}
			return inv;
		} 
		return null;
	}

	public static void saveBackpack(Inventory inv, ItemStack item) {
		int id = -1;
		String uuid = "";
		for (String line : item.getItemMeta().getLore()) {
			if (line.startsWith(ChatColor.translateAlternateColorCodes('&', "&7ID: ")) && line.contains("#")) {
				try {
					id = Integer.parseInt(line.split("#")[1]);
					uuid = line.split("#")[0].replace(ChatColor.translateAlternateColorCodes('&', "&7ID: "), "");
				} catch (NumberFormatException numberFormatException) {}
			}
		}

		if (id >= 0) {
			Config cfg = new Config(new File("data-storage/SafeBackpacks/backpacks/" + uuid + "#" + id + ".yml"));
			for (int i = 0; i < inv.getContents().length; i++) {
				cfg.setValue("contents." + i, inv.getContents()[i]);
			}
			cfg.save();
		} 
	}

}