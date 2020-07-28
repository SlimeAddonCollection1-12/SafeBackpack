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
	public static boolean check;
	
	@Override
	public void onEnable() {

		plug = this;

		File cfgf = new File(this.getDataFolder().getPath() + File.separator + "config.yml");
		if (!cfgf.exists()) {
			this.saveDefaultConfig();
		}
		cfg = this.getConfig();

		if (!(new File("data-storage/SafeBackpacks/backpacks")).exists()) (new File("data-storage/SafeBackpacks/backpacks")).mkdirs(); 
		
		check = VanillaBackpacksChecker();

		Setup.init();

		new SafeBackpackListener(this);
		new SafeCoolerListener(this);
		new ToolListener(this);
		new DamageListener(this);
		new BackpackDyeingListener(this);

		if (cfg.getBoolean("convertor.auto-run")) {
			new AutoConvertorListener(this);
		}

		new BPDataRelocator(this);
		new ItemConvertor(this);

	}

	public static boolean VanillaBackpacksChecker() {
		if (!cfg.getBoolean("vanilla-backpacks-checker")) return true;
		
		if (!isDisabled("SMALL_BACKPACK")
				|| !isDisabled("MEDIUM_BACKPACK")
				|| !isDisabled("LARGE_BACKPACK")
				|| !isDisabled("WOVEN_BACKPACK")
				|| !isDisabled("GILDED_BACKPACK")
				|| !isDisabled("BOUND_BACKPACK")
				|| !isDisabled("COOLER")) {
			plug.getLogger().warning("检测到服务器内还未禁用Slimefun原版的小、中、大、编织、镀金、灵魂绑定背包以及冰箱");
			plug.getLogger().warning("若不及时禁用这些物品并进行数据转移等操作可能导致玩家数据被覆写而造成损失");
			plug.getLogger().warning("如果您确认禁用了这些物品并且此报警信息依然存在");
			plug.getLogger().warning("请在配置文件中将 'vanilla-backpacks-checker' 一项改为 'false'");
			return false;
		} else return true;
	}

	private static boolean isDisabled(String id) {
		SlimefunItem sfi = SlimefunItem.getByID(id);
		if (sfi == null) return false;
		return sfi.isDisabled();
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
