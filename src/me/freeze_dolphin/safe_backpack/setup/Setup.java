package me.freeze_dolphin.safe_backpack.setup;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.freeze_dolphin.safe_backpack.lists.SafeBackpackItems;
import me.freeze_dolphin.safe_backpack.objects.SafeBackpack;
import me.freeze_dolphin.safe_backpack.objects.SafeSoulboundBackpack;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Setup {

	public static void init() {

		ItemStack CLOTH16 = SlimefunItems.CLOTH.clone();
		CLOTH16.setAmount(16);
		(new SlimefunItem(Categories.MISC, SafeBackpackItems.CANVAS, "CANVAS", RecipeType.COMPRESSOR, new ItemStack[] {
				CLOTH16, null, null, null, null, null, null, null, null
		})).register(false);

		(new SafeBackpack(9, Categories.PORTABLE, SafeBackpackItems.BACKPACK_SMALL, "SAFE_SMALL_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { 
				new ItemStack(Material.LEATHER), null, new ItemStack(Material.LEATHER), 
				SlimefunItems.GOLD_6K, new ItemStack(Material.BROWN_SHULKER_BOX), SlimefunItems.GOLD_6K, 
				new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER)
		})).register(false);

		(new SafeBackpack(18, Categories.PORTABLE, SafeBackpackItems.BACKPACK_MEDIUM, "SAFE_MEDIUM_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { 
				new ItemStack(Material.LEATHER), null, new ItemStack(Material.LEATHER), 
				SlimefunItems.GOLD_10K, SafeBackpackItems.BACKPACK_SMALL, SlimefunItems.GOLD_10K, 
				new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER)
		})).register(false);

		(new SafeBackpack(27, Categories.PORTABLE, SafeBackpackItems.BACKPACK_LARGE, "SAFE_LARGE_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { 
				new ItemStack(Material.LEATHER), null, new ItemStack(Material.LEATHER), 
				SlimefunItems.GOLD_14K, SafeBackpackItems.BACKPACK_MEDIUM, SlimefunItems.GOLD_14K, 
				new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER)
		})).register(false);

		(new SafeBackpack(36, Categories.PORTABLE, SafeBackpackItems.WOVEN_BACKPACK, "SAFE_WOVEN_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { 
				SlimefunItems.CLOTH, null, SlimefunItems.CLOTH, 
				SlimefunItems.GOLD_16K, SafeBackpackItems.BACKPACK_LARGE, SlimefunItems.GOLD_16K, 
				SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH
		})).register(false);

		(new SafeBackpack(45, Categories.PORTABLE, SafeBackpackItems.GILDED_BACKPACK, "SAFE_GILDED_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { 
				SlimefunItems.GOLD_22K, null, SlimefunItems.GOLD_22K, 
				new ItemStack(Material.LEATHER), SafeBackpackItems.WOVEN_BACKPACK, new ItemStack(Material.LEATHER), 
				SlimefunItems.GOLD_22K, null, SlimefunItems.GOLD_22K
		})).register(false);

		(new SafeSoulboundBackpack(36, Categories.PORTABLE, SafeBackpackItems.BOUND_BACKPACK, "SAFE_BOUND_BACKPACK", new ItemStack[] { 
				SlimefunItems.ENDER_LUMP_2, null, SlimefunItems.ENDER_LUMP_2, 
				SlimefunItems.ESSENCE_OF_AFTERLIFE, SafeBackpackItems.GILDED_BACKPACK, SlimefunItems.ESSENCE_OF_AFTERLIFE, 
				SlimefunItems.ENDER_LUMP_2, null, SlimefunItems.ENDER_LUMP_2
		})).register(false);

		(new SafeBackpack(54, Categories.PORTABLE, SafeBackpackItems.JOURNEY_BACKPACK, "SAFE_JOURNEY_BACKPACK", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { 
				SafeBackpackItems.CANVAS, null, SafeBackpackItems.CANVAS, 
				SlimefunItems.GILDED_IRON, SafeBackpackItems.WOVEN_BACKPACK, SlimefunItems.GILDED_IRON, 
				SafeBackpackItems.CANVAS, null, SafeBackpackItems.CANVAS
		})).register(false);

		(new SafeBackpack(27, Categories.PORTABLE, SafeBackpackItems.COOLER, "SAFE_COOLER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH, 
				SlimefunItems.ALUMINUM_INGOT, SlimefunItems.COOLING_UNIT, SlimefunItems.ALUMINUM_INGOT, 
				SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ALUMINUM_INGOT
		})).register(true);

		(new SafeBackpack(54, Categories.PORTABLE, SafeBackpackItems.REINFORCED_COOLER, "SAFE_REINFORCED_COOLER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				SafeBackpackItems.CANVAS, SafeBackpackItems.CANVAS, SafeBackpackItems.CANVAS, 
				SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.COOLING_UNIT, SlimefunItems.REINFORCED_ALLOY_INGOT, 
				SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT
		})).register(true);

	}

}
