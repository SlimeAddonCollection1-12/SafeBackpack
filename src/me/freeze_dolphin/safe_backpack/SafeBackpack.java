package me.freeze_dolphin.safe_backpack;

import java.io.File;

import me.freeze_dolphin.safe_backpack.command.BPDataRelocator;
import me.freeze_dolphin.safe_backpack.command.ItemConvertor;
import me.freeze_dolphin.safe_backpack.listeners.*;
import me.freeze_dolphin.safe_backpack.setup.Setup;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.ChargableItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.ChargedItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.DamagableChargableItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunBackpack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SafeBackpack extends JavaPlugin {
	
	public static Plugin plug;
	public static FileConfiguration cfg;
	
	@Override
	public void onEnable() {

		plug = this;
		
		if (!(new File(this.getDataFolder().getPath() + File.separator + "config.yml")).exists()) {
			this.saveDefaultConfig();
		}
		cfg = this.getConfig();
		
		if (!(new File("data-storage/SafeBackpacks/backpacks")).exists()) (new File("data-storage/SafeBackpacks/backpacks")).mkdirs(); 

		Setup.init();

		new SafeBackpackListener(this);
		new SafeCoolerListener(this);
		new ToolListener(this);
		new DamageListener(this);
		new BackpackDyingListener(this);
		
		if (cfg.getBoolean("convertor.auto-run")) {
			new AutoConvertorListener(this);
		}
		
		new BPDataRelocator(this);
		new ItemConvertor(this);
		
	}

	public static SlimefunItem getSfiByItem(ItemStack item) {
		if (item == null) return null;
		if (SlimefunManager.isItemSimiliar(item, SlimefunItems.BROKEN_SPAWNER, false)) return SlimefunItem.getByID("BROKEN_SPAWNER");
		if (SlimefunManager.isItemSimiliar(item, SlimefunItems.REPAIRED_SPAWNER, false)) return SlimefunItem.getByID("REINFORCED_SPAWNER");
		for (SlimefunItem sfi: SlimefunItem.items) {
			if (sfi instanceof ChargableItem && SlimefunManager.isItemSimiliar(item, sfi.getItem(), false)) return sfi;
			else if (sfi instanceof DamagableChargableItem && SlimefunManager.isItemSimiliar(item, sfi.getItem(), false)) return sfi;
			else if (sfi instanceof ChargedItem && SlimefunManager.isItemSimiliar(item, sfi.getItem(), false)) return sfi;
			else if (sfi instanceof SlimefunBackpack && SlimefunManager.isItemSimiliar(item, sfi.getItem(), false)) return sfi;
			else if (sfi instanceof me.freeze_dolphin.safe_backpack.objects.SafeBackpack && SlimefunManager.isItemSimiliar(item, sfi.getItem(), false)) return sfi;
			else if (SlimefunManager.isItemSimiliar(item, sfi.getItem(), true)) return sfi;
		}
		return null;
	}

}
