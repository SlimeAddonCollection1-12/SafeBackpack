package me.freeze_dolphin.safe_backpack.lists;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

public class SafeBackpackItems {

	public static ItemStack BACKPACK_SMALL, BACKPACK_MEDIUM, BACKPACK_LARGE, WOVEN_BACKPACK, GILDED_BACKPACK, BOUND_BACKPACK, JOURNEY_BACKPACK, COOLER, REINFORCED_COOLER = null;
	public static ItemStack CANVAS = new CustomItem(Material.PAPER, "&b帆布", 0);

	static {
		try {
			BACKPACK_SMALL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					"&f&e小型背包", new String[] { 
				"", 
				"&7容量: &e9", "&7ID: <ID>", 
				"", 
			"&7&e右键 &7打开" });
			BACKPACK_MEDIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					"&f&e背包", new String[] { 
				"", 
				"&7容量: &e18", "&7ID: <ID>", 
				"", 
			"&7&e右键 &7打开" });
			BACKPACK_LARGE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					"&f&e大型背包", new String[] { 
				"", 
				"&7容量: &e27", "&7ID: <ID>", 
				"", 
			"&7&e右键 &7打开" });
			WOVEN_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					"&f&e编织背包", new String[] { 
				"", 
				"&7容量: &e36", "&7ID: <ID>", 
				"", 
			"&7&e右键 &7打开" });
			GILDED_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					"&f&e镀金背包", new String[] { 
				"", 
				"&7容量: &e45", "&7ID: <ID>", 
				"", 
			"&7&e右键 &7打开" });
			BOUND_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), 
					"&f&c灵魂绑定背包", new String[] { 
				"", 
				"&7容量: &e36", "&7ID: <ID>", 
				"", 
			"&7&e右键 &7打开" });
			JOURNEY_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), 
					"&f&e旅行背包", new String[] { 
				"", 
				"&7容量: &e54", "&7ID: <ID>", 
				"", 
			"&7&e右键 &7打开" });
			COOLER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDRjMTU3MjU4NGViNWRlMjI5ZGU5ZjVhNGY3NzlkMGFhY2JhZmZkMzNiY2IzM2ViNDUzNmE2YTJiYzZhMSJ9fX0="), 
					"&f&b冰箱", new String[] { 
				"&r所有存入冰箱的 果汁/冰沙", "&r都会在你饥饿时", "&r自动消耗补充你的饥饿值", 
				"", 
				"&7容量: &e27", "&7ID: <ID>", 
				"", 
			"&7&e右键 &7打开" });
			REINFORCED_COOLER = new CustomItem(CustomSkull.getItem(SkullCodes.REINFORCED_COOLER), 
					"&f&d强化冰箱", new String[] { 
				"&r所有存入冰箱的 果汁/冰沙", "&r都会在你饥饿时", "&r自动消耗补充你的饥饿值", 
				"", 
				"&7容量: &e54", "&7ID: <ID>", 
				"", 
			"&7&e右键 &7打开" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
