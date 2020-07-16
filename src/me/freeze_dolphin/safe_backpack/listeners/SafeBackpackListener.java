package me.freeze_dolphin.safe_backpack.listeners;

import java.util.List;

import me.freeze_dolphin.safe_backpack.Variables;
import me.freeze_dolphin.safe_backpack.api.Backpacks;
import me.freeze_dolphin.safe_backpack.lists.SafeBackpackItems;
import me.freeze_dolphin.safe_backpack.objects.SafeBackpack;
import me.freeze_dolphin.safe_backpack.objects.SafeSoulboundBackpack;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Setup.Messages;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.Slimefun;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class SafeBackpackListener implements Listener {

	public SafeBackpackListener(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (Variables.safebackpack.containsKey(e.getPlayer().getUniqueId())) {
			((Player)e.getPlayer()).playSound(e.getPlayer().getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
			Backpacks.saveBackpack(e.getInventory(), (ItemStack)Variables.safebackpack.get(e.getPlayer().getUniqueId()));
			Variables.safebackpack.remove(e.getPlayer().getUniqueId());
		}
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		if (Variables.safebackpack.containsKey(e.getPlayer().getUniqueId())) {
			ItemStack item = e.getItemDrop().getItemStack();
			SlimefunItem sfItem = me.freeze_dolphin.safe_backpack.SafeBackpack.getSfiByItem(item);
			if (sfItem instanceof SafeBackpack) e.setCancelled(true); 
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (Variables.safebackpack.containsKey(e.getWhoClicked().getUniqueId())) {
			ItemStack item = (ItemStack)Variables.safebackpack.get(e.getWhoClicked().getUniqueId());
			if (e.getClick() == ClickType.NUMBER_KEY) {
				ItemStack hotbarItem = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());
				SlimefunItem sfItem = me.freeze_dolphin.safe_backpack.SafeBackpack.getSfiByItem(hotbarItem);
				if (hotbarItem != null && hotbarItem.getType().toString().contains("SHULKER_BOX")) { e.setCancelled(true); }
				else if (sfItem instanceof SafeBackpack) { e.setCancelled(true); }
				else if (sfItem instanceof SafeSoulboundBackpack) { e.setCancelled(true); }

			} else {
				SlimefunItem sfItem = me.freeze_dolphin.safe_backpack.SafeBackpack.getSfiByItem(e.getCurrentItem());
				if (SlimefunManager.isItemSimiliar(item, SlimefunItem.getItem("SAFE_COOLER"), false)
						|| SlimefunManager.isItemSimiliar(item, SlimefunItem.getItem("SAFE_REINFORCED_COOLER"), false)) { 
					if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR) && !(sfItem instanceof me.mrCookieSlime.Slimefun.Objects.SlimefunItem.Juice)) { 
						e.setCancelled(true);
					}
				}
				else if (e.getCurrentItem() != null && e.getCurrentItem().getType().toString().contains("SHULKER_BOX")) { e.setCancelled(true); }
				else if (sfItem instanceof SafeBackpack) { e.setCancelled(true); }
				else if (sfItem instanceof SafeSoulboundBackpack) { e.setCancelled(true); }
				else if (SlimefunManager.isItemSimiliar(e.getCurrentItem(), SlimefunItems.VOIDBAG_SMALL, false)) { e.setCancelled(true); }
				else if (SlimefunManager.isItemSimiliar(e.getCurrentItem(), SlimefunItems.VOIDBAG_MEDIUM, false)) { e.setCancelled(true); }
				else if (SlimefunManager.isItemSimiliar(e.getCurrentItem(), SlimefunItems.VOIDBAG_BIG, false)) { e.setCancelled(true); }
				else if (SlimefunManager.isItemSimiliar(e.getCurrentItem(), SlimefunItems.VOIDBAG_LARGE, false)) { e.setCancelled(true); }
				else if (SlimefunManager.isItemSimiliar(e.getCurrentItem(), SlimefunItems.BOUND_VOIDBAG, false)) { e.setCancelled(true); }

			} 
		} 
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if (e.getAction() == Action.RIGHT_CLICK_AIR && p.isSneaking()) return;

			ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
			if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.BACKPACK_SMALL, false)) {
				e.setCancelled(true);
				if (Slimefun.hasUnlocked(p, SafeBackpackItems.BACKPACK_SMALL, true)) {
					if (item.getAmount() == 1) { 
						for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
							if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
								ItemMeta im = item.getItemMeta();
								List<String> lore = im.getLore();
								lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 9)));
								im.setLore(lore);
								item.setItemMeta(im);
								break;
							} 
						} 
						if (!Variables.safebackpack.containsValue(item)) {

							Backpacks.openBackpack(p, item);
							p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
							Variables.safebackpack.put(p.getUniqueId(), item);
						} else {
							Messages.local.sendTranslation(p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
						}
					}
					else { Messages.local.sendTranslation(p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }

				}
			} else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.BACKPACK_MEDIUM, false)) {
				e.setCancelled(true);
				if (Slimefun.hasUnlocked(p, SafeBackpackItems.BACKPACK_MEDIUM, true)) {
					if (item.getAmount() == 1) { 
						for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
							if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
								ItemMeta im = item.getItemMeta();
								List<String> lore = im.getLore();
								lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 18)));
								im.setLore(lore);
								item.setItemMeta(im);
								break;
							} 
						} 
						if (!Variables.safebackpack.containsValue(item)) {

							Backpacks.openBackpack(p, item);
							p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
							Variables.safebackpack.put(p.getUniqueId(), item);
						} else {
							Messages.local.sendTranslation(p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
						}
					}
					else { Messages.local.sendTranslation(p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }

				}
			} else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.BACKPACK_LARGE, false)) {
				e.setCancelled(true);
				if (Slimefun.hasUnlocked(p, SafeBackpackItems.BACKPACK_LARGE, true)) {
					if (item.getAmount() == 1) { 
						for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
							if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
								ItemMeta im = item.getItemMeta();
								List<String> lore = im.getLore();
								lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 27)));
								im.setLore(lore);
								item.setItemMeta(im);
								break;
							} 
						} 
						if (!Variables.safebackpack.containsValue(item)) {

							Backpacks.openBackpack(p, item);
							p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
							Variables.safebackpack.put(p.getUniqueId(), item);
						} else {
							Messages.local.sendTranslation(p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
						}
					}
					else { Messages.local.sendTranslation(p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }

				}
			} else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.WOVEN_BACKPACK, false)) {
				e.setCancelled(true);
				if (Slimefun.hasUnlocked(p, SafeBackpackItems.WOVEN_BACKPACK, true)) {
					if (item.getAmount() == 1) { 
						for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
							if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
								ItemMeta im = item.getItemMeta();
								List<String> lore = im.getLore();
								lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 36)));
								im.setLore(lore);
								item.setItemMeta(im);
								break;
							} 
						} 
						if (!Variables.safebackpack.containsValue(item)) {

							Backpacks.openBackpack(p, item);
							p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
							Variables.safebackpack.put(p.getUniqueId(), item);
						} else {
							Messages.local.sendTranslation(p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
						}
					}
					else { Messages.local.sendTranslation(p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }

				}
			} else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.GILDED_BACKPACK, false)) {
				e.setCancelled(true);
				if (Slimefun.hasUnlocked(p, SafeBackpackItems.GILDED_BACKPACK, true)) {
					if (item.getAmount() == 1) { 
						for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
							if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
								ItemMeta im = item.getItemMeta();
								List<String> lore = im.getLore();
								lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 45)));
								im.setLore(lore);
								item.setItemMeta(im);
								break;
							} 
						} 
						if (!Variables.safebackpack.containsValue(item)) {

							Backpacks.openBackpack(p, item);
							p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
							Variables.safebackpack.put(p.getUniqueId(), item);
						} else {
							Messages.local.sendTranslation(p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
						}
					}
					else { Messages.local.sendTranslation(p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }

				}
			} else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.BOUND_BACKPACK, false)) {
				e.setCancelled(true);
				if (Slimefun.hasUnlocked(p, SafeBackpackItems.BOUND_BACKPACK, true)) {
					if (item.getAmount() == 1) { 
						for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
							if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
								ItemMeta im = item.getItemMeta();
								List<String> lore = im.getLore();
								lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 36)));
								im.setLore(lore);
								item.setItemMeta(im);
								break;
							} 
						} 
						if (!Variables.safebackpack.containsValue(item)) {

							Backpacks.openBackpack(p, item);
							p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
							Variables.safebackpack.put(p.getUniqueId(), item);
						} else {
							Messages.local.sendTranslation(p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
						}
					}
					else { Messages.local.sendTranslation(p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }

				}
			} else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.COOLER, false)) {
				e.setCancelled(true);
				if (Slimefun.hasUnlocked(p, SafeBackpackItems.COOLER, true))
					if (item.getAmount() == 1) { 
						for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
							if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
								ItemMeta im = item.getItemMeta();
								List<String> lore = im.getLore();
								lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 27)));
								im.setLore(lore);
								item.setItemMeta(im);
								break;
							} 
						} 
						if (!Variables.safebackpack.containsValue(item)) {
							Backpacks.openBackpack(p, item);
							p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
							Variables.safebackpack.put(p.getUniqueId(), item);
						} else {
							Messages.local.sendTranslation(p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
						}
					}
					else { Messages.local.sendTranslation(p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
			} else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.REINFORCED_COOLER, false)) {
				e.setCancelled(true);
				if (Slimefun.hasUnlocked(p, SafeBackpackItems.REINFORCED_COOLER, true))
					if (item.getAmount() == 1) { 
						for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
							if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
								ItemMeta im = item.getItemMeta();
								List<String> lore = im.getLore();
								lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 54)));
								im.setLore(lore);
								item.setItemMeta(im);
								break;
							} 
						} 
						if (!Variables.safebackpack.containsValue(item)) {
							Backpacks.openBackpack(p, item);
							p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
							Variables.safebackpack.put(p.getUniqueId(), item);
						} else {
							Messages.local.sendTranslation(p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
						}
					}
					else { Messages.local.sendTranslation(p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
			} else if (SlimefunManager.isItemSimiliar(item, SafeBackpackItems.JOURNEY_BACKPACK, false)) {
				e.setCancelled(true);
				if (Slimefun.hasUnlocked(p, SafeBackpackItems.JOURNEY_BACKPACK, true))
					if (item.getAmount() == 1) { 
						for (int line = 0; line < item.getItemMeta().getLore().size(); line++) {
							if (((String)item.getItemMeta().getLore().get(line)).equals(ChatColor.translateAlternateColorCodes('&', "&7ID: <ID>"))) {
								ItemMeta im = item.getItemMeta();
								List<String> lore = im.getLore();
								lore.set(line, ((String)lore.get(line)).replace("<ID>", Backpacks.createBackpack(p, 54)));
								im.setLore(lore);
								item.setItemMeta(im);
								break;
							}
						}
						if (!Variables.safebackpack.containsValue(item)) {
							Backpacks.openBackpack(p, item);
							p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
							Variables.safebackpack.put(p.getUniqueId(), item);
						} else {
							Messages.local.sendTranslation(p, "backpack.already-open", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]);
						}
					}
					else { Messages.local.sendTranslation(p, "backpack.no-stack", true, new me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable[0]); }
			}
		}
	}
}