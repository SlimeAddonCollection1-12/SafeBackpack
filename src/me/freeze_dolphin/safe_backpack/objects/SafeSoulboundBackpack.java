package me.freeze_dolphin.safe_backpack.objects;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunBackpack;

import org.bukkit.inventory.ItemStack;

public class SafeSoulboundBackpack extends SlimefunBackpack {

	public SafeSoulboundBackpack(int size, Category category, ItemStack item, String id, ItemStack[] recipe) { 
		super(size, category, item, id, RecipeType.MAGIC_WORKBENCH, recipe); 
	}

	public SafeSoulboundBackpack(int size, Category category, ItemStack item, String id, RecipeType type, ItemStack[] recipe) { 
		super(size, category, item, id, type, recipe); 
	}

}