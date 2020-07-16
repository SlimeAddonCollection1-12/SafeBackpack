package me.freeze_dolphin.safe_backpack.setup;

import me.freeze_dolphin.safe_backpack.lists.SafeBackpackItems;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.api.Slimefun;

public class ResearchSetup {
	
	public static void init() {
		Slimefun.registerResearch(new Research(500, "Safe Backpacks", 15), 
				SafeBackpackItems.BACKPACK_LARGE, SafeBackpackItems.BACKPACK_MEDIUM, SafeBackpackItems.BACKPACK_SMALL);
		Slimefun.registerResearch(new Research(501, "Safe Woven Backpack", 19), 
				SafeBackpackItems.WOVEN_BACKPACK);
		Slimefun.registerResearch(new Research(502, "Safe Gilded Backpacks", 22), 
				SafeBackpackItems.GILDED_BACKPACK);
		Slimefun.registerResearch(new Research(503, "Safe Backpacks", 15), 
				SafeBackpackItems.BACKPACK_LARGE, SafeBackpackItems.BACKPACK_MEDIUM, SafeBackpackItems.BACKPACK_SMALL);
		Slimefun.registerResearch(new Research(504, "Reinforced Cooler", 45), 
				SafeBackpackItems.REINFORCED_COOLER);
		Slimefun.registerResearch(new Research(505, "Journey Backpack", 42), 
				SafeBackpackItems.JOURNEY_BACKPACK);
		
	}
	
}
