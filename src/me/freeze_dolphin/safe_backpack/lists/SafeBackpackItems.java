package me.freeze_dolphin.safe_backpack.lists;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.freeze_dolphin.safe_backpack.SafeBackpack;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

public class SafeBackpackItems {
	
	private static String bpDisplayName(String str, int integer) {
		return SafeBackpack.cfg.getStringList("backpack-desc").get(0).replaceAll("<type>", str).replaceAll("<capacity>", String.valueOf(integer));
	}
	
	private static String crDisplayName(String str, int integer) {
		return SafeBackpack.cfg.getStringList("cooler-desc").get(0).replaceAll("<type>", str).replaceAll("<capacity>", String.valueOf(integer));
	}
	
	private static String[] bpLore(String str, int replacement) {
		List<String> l = new LinkedList<>();
		List<String> desc = SafeBackpack.cfg.getStringList("backpack-desc");
		for (int i = 1; i < desc.size(); i++) {
			l.add(ChatColor.translateAlternateColorCodes('&', desc.get(i).replaceAll("<type>", str).replaceAll("<capacity>", String.valueOf(replacement))));
		}
		return l.toArray(new String[l.size()]);
	}
	
	private static String[] crLore(String str, int replacement) {
		List<String> l = new LinkedList<>();
		List<String> desc = SafeBackpack.cfg.getStringList("cooler-desc");
		for (int i = 1; i < desc.size(); i++) {
			l.add(ChatColor.translateAlternateColorCodes('&', desc.get(i).replaceAll("<type>", str).replaceAll("<capacity>", String.valueOf(replacement))));
		}
		return l.toArray(new String[l.size()]);
	}
	
	public static ItemStack BACKPACK_SMALL, BACKPACK_MEDIUM, BACKPACK_LARGE, WOVEN_BACKPACK, GILDED_BACKPACK, BOUND_BACKPACK, JOURNEY_BACKPACK, COOLER, REINFORCED_COOLER = null;
	public static ItemStack CANVAS = new CustomItem(Material.PAPER, "&b帆布", 0);

	static {
		try {
			BACKPACK_SMALL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					bpDisplayName("&f&e小型背包", 9), bpLore("&f&e小型背包", 9));
			BACKPACK_MEDIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					bpDisplayName("&f&e背包", 18), bpLore("&f&e背包", 18));
			BACKPACK_LARGE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					bpDisplayName("&f&e大型背包", 27), bpLore("&f&e大型背包", 27));
			WOVEN_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					bpDisplayName("&f&e编织背包", 36), bpLore("&f&e编织背包", 36));
			GILDED_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					bpDisplayName("&f&e镀金背包", 45), bpLore("&f&e镀金背包", 45));
			BOUND_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), 
					bpDisplayName("&f&c灵魂绑定背包", 36), bpLore("&f&c灵魂绑定背包", 36));
			JOURNEY_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					bpDisplayName("&f&e旅行背包", 54), bpLore("&f&e旅行背包", 54));
			COOLER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDRjMTU3MjU4NGViNWRlMjI5ZGU5ZjVhNGY3NzlkMGFhY2JhZmZkMzNiY2IzM2ViNDUzNmE2YTJiYzZhMSJ9fX0="), 
					crDisplayName("&f&b冰箱", 27), crLore("&f&b冰箱", 27));
			REINFORCED_COOLER = new CustomItem(CustomSkull.getItem(SkullCodes.REINFORCED_COOLER), 
					crDisplayName("&f&d强化冰箱", 54), crLore("&f&d强化冰箱", 54));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
