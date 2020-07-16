package me.freeze_dolphin.safe_backpack.objects;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

import org.bukkit.inventory.ItemStack;

public class SafeBackpack extends SlimefunItem {

	public int size;

	public SafeBackpack(int size, Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
		this.size = size;
	}

}