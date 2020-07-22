package me.freeze_dolphin.safe_backpack.command;

import java.util.List;
import java.util.UUID;

import me.freeze_dolphin.safe_backpack.SafeBackpack;
import me.freeze_dolphin.safe_backpack.lists.SafeBackpackItems;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemConvertor implements CommandExecutor {

	public ItemConvertor(SafeBackpack plug) {
		plug.getServer().getPluginCommand("itemconvertor").setExecutor(this);
	}

	private static String colorize(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.hasPermission("safebackpack.admin") && args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				SafeBackpack.plug.reloadConfig();
			}
		}

		if (!(sender instanceof Player)) {
			switch (args.length) {
			case 1: 
				Player target = Bukkit.getPlayer(args[0]);
				if (target != null) {
					sender.sendMessage(colorize("&a完成 " + "&7(有 " + convert(target) + " 个物品转换错误且未能转换)"));
					break;
				} else {
					sender.sendMessage(colorize("&c没有找到这个玩家"));
					break;
				}
			default: 
				sender.sendMessage(colorize("&c无效参数"));
				break;
			}
			return true;
		} else if (sender instanceof Player) {
			Player p = (Player) sender;
			if (sender.hasPermission("safebackpack.convertor.convert")) {
				switch (args.length) {
				case 0: 
					p.sendMessage(colorize("&a完成 " + "&7(有 " + convert(p) + " 个物品转换错误且未能转换)"));
					break;
				case 1: 
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						p.sendMessage(colorize("&a完成 " + "&7(有 " + convert(p) + " 个物品转换错误且未能转换)"));
						break;
					} else {
						p.sendMessage(colorize("&c没有找到这个玩家"));
						break;
					}
				default: 
					p.sendMessage(colorize("&c无效参数"));
					break;
				}
				return true;
			} else {
				sender.sendMessage(colorize("&c你没有权限"));
				return true;
			}
		} else {
			sender.sendMessage(colorize("&c只有玩家和控制台能使用这个指令"));
			return true;
		}
	}

	public static int convert(Player p) {
		Inventory inv = p.getInventory();
		int error = 0;
		for (int s = 0; s < inv.getSize(); s++) {
			ItemStack i = inv.getItem(s);
			if (i == null || !i.hasItemMeta() || !i.getItemMeta().hasLore()) continue;
			if (SlimefunManager.isItemSimiliar(i, SlimefunItems.BACKPACK_SMALL, false)) {
				ItemStack sit = modifyID(i, SafeBackpackItems.BACKPACK_SMALL);
				if (sit == null) {
					error++;
					continue;
				}
				inv.setItem(s, sit);
			}
			else if (SlimefunManager.isItemSimiliar(i, SlimefunItems.BACKPACK_MEDIUM, false)) {
				ItemStack sit = modifyID(i, SafeBackpackItems.BACKPACK_MEDIUM);
				if (sit == null) {
					error++;
					continue;
				}
				inv.setItem(s, sit);
			}
			else if (SlimefunManager.isItemSimiliar(i, SlimefunItems.BACKPACK_LARGE, false)) {
				ItemStack sit = modifyID(i, SafeBackpackItems.BACKPACK_LARGE);
				if (sit == null) {
					error++;
					continue;
				}
				inv.setItem(s, sit);
			}
			else if (SlimefunManager.isItemSimiliar(i, SlimefunItems.WOVEN_BACKPACK, false)) {
				ItemStack sit = modifyID(i, SafeBackpackItems.WOVEN_BACKPACK);
				if (sit == null) {
					error++;
					continue;
				}
				inv.setItem(s, modifyID(i, modifyID(i, SafeBackpackItems.WOVEN_BACKPACK)));
			}
			else if (SlimefunManager.isItemSimiliar(i, SlimefunItems.GILDED_BACKPACK, false)) {
				ItemStack sit = modifyID(i, SafeBackpackItems.GILDED_BACKPACK);
				if (sit == null) {
					error++;
					continue;
				}
				inv.setItem(s, sit);
			}
			else if (SlimefunManager.isItemSimiliar(i, SlimefunItems.BOUND_BACKPACK, false)) {
				ItemStack sit = modifyID(i, SafeBackpackItems.BOUND_BACKPACK);
				if (sit == null) {
					error++;
					continue;
				}
				inv.setItem(s, sit);
			}
			else if (SlimefunManager.isItemSimiliar(i, SlimefunItems.COOLER, false)) {
				ItemStack sit = modifyID(i, SafeBackpackItems.COOLER);
				if (sit == null) {
					error++;
					continue;
				}
				inv.setItem(s, sit);
			}
		}
		return error;
	}

	private static ItemStack modifyID(ItemStack item, ItemStack target) {
		if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasLore()) return null; 
		int id = -1;
		String uuid = "";
		boolean matchA = false;
		for (String line : item.getItemMeta().getLore()) {
			if (line.startsWith(ChatColor.translateAlternateColorCodes('&', "&7ID: ")) && line.contains("#")) {
				matchA = true;
				try {
					id = Integer.parseInt(line.split("#")[1]);
					uuid = line.split("#")[0].replace(ChatColor.translateAlternateColorCodes('&', "&7ID: "), "");
				} catch (NumberFormatException numberFormatException) {
					System.out.println("NUM");
					return null;
				}
			}
		}
		
		if (!matchA) {
			return null;
		}

		try { UUID.fromString(uuid); }
		catch (Exception ex) { System.out.println("UUID"); return null; }

		String tag = uuid + "#" + id;

		List<String> lore = target.getItemMeta().getLore();
		boolean match = false;
		for (int i = 0; i < lore.size(); i++) {
			if (lore.get(i).matches("(.*)<ID>")) {
				match = true;
				lore.set(i, lore.get(i).replaceAll("<ID>", tag));
				break;
			}
		}

		if (!match) { System.out.println("match"); return null; }

		ItemStack rt = target.clone();
		ItemMeta im = rt.getItemMeta();
		im.setLore(lore);
		rt.setItemMeta(im);
		return rt;

	}

}
