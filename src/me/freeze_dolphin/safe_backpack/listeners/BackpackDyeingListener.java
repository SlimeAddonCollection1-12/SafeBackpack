package me.freeze_dolphin.safe_backpack.listeners;

import java.util.List;

import me.freeze_dolphin.safe_backpack.SafeBackpack;
import me.freeze_dolphin.safe_backpack.lists.SkullCodes;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuCloseHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.Slimefun;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BackpackDyeingListener implements Listener {

	public BackpackDyeingListener(SafeBackpack plug) {
		plug.getServer().getPluginManager().registerEvents(this, plug);
	}

	@EventHandler
	public void dye(PlayerInteractEvent e) {
		if (!e.getPlayer().hasPermission("safebackpack.dye.menu")) return;
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (e.hasItem()) {
				Player p = e.getPlayer();
				if (p.isSneaking()) {
					ItemStack hand = p.getInventory().getItemInMainHand();
					SlimefunItem sfi = SafeBackpack.getSfiByItem(hand);
					if (sfi instanceof me.freeze_dolphin.safe_backpack.objects.SafeBackpack) {
						if (sfi.getID().matches("SAFE_(.*)BACKPACK")) {
							if (Slimefun.hasUnlocked(p, sfi, true)) {
								openDyingMenu(p, hand);
							}
						}
					}
				}
			}
		}
	}

	private static final int[] border = {0, 1, 2, 3, 5, 6, 7, 8};
	private static void openDyingMenu(Player p, ItemStack hand) {
		ChestMenu m = new ChestMenu("染色: " + hand.getItemMeta().getDisplayName());
		for (int i : border) { 
			m.addItem(i, new CustomItem(Material.STAINED_GLASS_PANE, " ", 7), new MenuClickHandler() {

				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}
			});
		}
		m.setEmptySlotsClickable(true);
		m.setPlayerInventoryClickable(true);
		/*
		m.addMenuClickHandler(4, new MenuClickHandler() {

			@Override
			public boolean onClick(Player p, int i, ItemStack it, ClickAction ca) {
				if (it.hasItemMeta()) return false;
				return (p.getItemOnCursor().getType().equals(Material.INK_SACK) || p.getItemOnCursor() == null);
			}
		});
		 */
		m.addPlayerInventoryClickHandler(new MenuClickHandler() {

			@Override
			public boolean onClick(Player p, int slot, ItemStack currentItem, ClickAction ca) {
				if (currentItem.hasItemMeta()) return false;
				if (currentItem.getType().equals(Material.INK_SACK) || currentItem.getType().equals(Material.AIR) || currentItem == null) return true;
				else return false;
			}
		});

		m.addMenuCloseHandler(new MenuCloseHandler() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClose(Player p) {
				ItemStack four = m.getItemInSlot(4);
				if (four == null) return;
				if (four.getType().equals(Material.INK_SACK)) {
					if (four.hasItemMeta()) {
						p.getWorld().dropItemNaturally(p.getLocation(), four);
						return;
					}
					String target = null;
					boolean passPermCheck = true;
					switch (four.getData().getData()) {
					case 0: 
						if (!checkPerm(p, "black")) passPermCheck = false;
						target = SkullCodes.NORMAL_BLACK;
						break;
					case 1: 
						if (!checkPerm(p, "red")) passPermCheck = false;
						target = SkullCodes.NORMAL_RED;
						break;
					case 2: 
						if (!checkPerm(p, "green")) passPermCheck = false;
						target = SkullCodes.NORMAL_GREEN;
						break;
					case 3: 
						if (!checkPerm(p, "brown")) passPermCheck = false;
						target = SkullCodes.NORMAL_BROWN;
						break;
					case 4: 
						if (!checkPerm(p, "blue")) passPermCheck = false;
						target = SkullCodes.NORMAL_BLUE;
						break;
					case 5:
						if (!checkPerm(p, "purple")) passPermCheck = false;
						target = SkullCodes.NORMAL_PURPLE;
						break;
					case 6: 
						if (!checkPerm(p, "cyan")) passPermCheck = false;
						target = SkullCodes.NORMAL_CYAN;
						break;
					case 7: 
						if (!checkPerm(p, "light-gray")) passPermCheck = false;
						target = SkullCodes.NORMAL_LIGHT_GRAY;
						break;
					case 8: 
						if (!checkPerm(p, "gray")) passPermCheck = false;
						target = SkullCodes.NORMAL_GRAY;
						break;
					case 9: 
						if (!checkPerm(p, "pink")) passPermCheck = false;
						target = SkullCodes.NORMAL_PINK;
						break;
					case 10: 
						if (!checkPerm(p, "lime")) passPermCheck = false;
						target = SkullCodes.NORMAL_LIME;
						break;
					case 11: 
						if (!checkPerm(p, "yellow")) passPermCheck = false;
						target = SkullCodes.NORMAL_YELLOW;
						break;
					case 12: 
						if (!checkPerm(p, "light-blue")) passPermCheck = false;
						target = SkullCodes.NORMAL_LIGHT_BLUE;
						break;
					case 13: 
						if (!checkPerm(p, "magenta")) passPermCheck = false;
						target = SkullCodes.NORMAL_MAGENTA;
						break;
					case 14: 
						if (!checkPerm(p, "orange")) passPermCheck = false;
						target = SkullCodes.NORMAL_ORANGE;
						break;
					case 15: 
						if (!checkPerm(p, "white")) passPermCheck = false;
						target = SkullCodes.NORMAL_WHITE;
						break;
					default: 
						target = SkullCodes.NORMAL_BROWN;
						break;
					}
					if (!passPermCheck) {
						p.getWorld().dropItemNaturally(p.getLocation(), four);
						return;
					}
					if (SlimefunManager.isItemSimiliar(p.getInventory().getItemInMainHand(), hand, true)) {
						try {
							String displayName = hand.getItemMeta().getDisplayName();
							List<String> lore = hand.getItemMeta().getLore();
							ItemStack replace = CustomSkull.getItem(target); 
							ItemMeta im = replace.getItemMeta();
							im.setDisplayName(displayName);
							im.setLore(lore);
							replace.setItemMeta(im);
							p.getInventory().setItemInMainHand(replace);
							if (four.getAmount() > 1) {
								ItemStack i = four.clone();
								i.setAmount(i.getAmount() - 1);
								p.getWorld().dropItemNaturally(p.getLocation(), i);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					} else {
						p.getWorld().dropItemNaturally(p.getLocation(), four);
						return;
					}
				} else {
					p.getWorld().dropItemNaturally(p.getLocation(), four);
					return;
				}
			}
		});

		m.open(p);
	}

	private static boolean checkPerm(Player p, String color) {
		boolean hasPermission = p.hasPermission("safebackpack.dye.color." + color);
		if (!hasPermission) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你没有将背包染成这个颜色的权限"));
		}
		return hasPermission;
	}

}
