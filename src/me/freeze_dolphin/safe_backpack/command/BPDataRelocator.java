package me.freeze_dolphin.safe_backpack.command;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class BPDataRelocator implements CommandExecutor {

	private String slimefunPath = "./data-storage/Slimefun/Players";
	private String targetPath = "./data-storage/SafeBackpacks/backpacks";
	private boolean debug = true;
	private boolean delete = false;

	private Plugin plug;

	public BPDataRelocator(Plugin plug) {
		this.plug = plug;
		plug.getServer().getPluginCommand("bpdatarelocator").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		debug = true;
		delete = false;
		if (command.getName().equals("bpdatarelocator")) {
			if (sender instanceof ConsoleCommandSender) {
				if (args.length == 5) {

					if (!args[0].equals("default")) slimefunPath = args[0];
					if (!args[1].equals("default")) targetPath = args[1];
					if (args[3].equals("true") || args[3].equals("on")) delete = true;
					if (args[4].equals("false") || args[4].equals("off")) debug = false;
					File slimefun = new File(slimefunPath);
					File target = new File(targetPath);
					if (slimefun.exists()) {
						if (!slimefun.isDirectory()) {
							warn("The slimefun data must be a directory");
							return true;
						}
					} else {
						warn("The slimefun data must be existed");
						return true;
					}
					if (target.exists()) {
						if (!target.isDirectory()) {
							warn("The target must be a directory");
							return true;
						}
					} else {
						warn("The target must be existed");
						return true;
					}

					if (!args[2].equals("ignore-check")) {
						if (new File(".relocator").exists()) {
							warn("File '.relocator' found, aborted");
							return true;
						} else {
							try {
								new File(".relocator").createNewFile();
								warn("Start to relocating backpack data");
							} catch (IOException e) {
								fatal("File '.relocator' cannot be created, aborted");
								return true;
							}
						}
					} else {
						warn("Ignoring checker and starting to relocating backpack data");
					}

					for1: 
						for (File f : (new File(slimefunPath)).listFiles()) {
							info("Checking " + f.getName());
							if (!f.getName().matches(".*\\.yml")) { 
								info(f.getName() + " is not a yaml file, ignored");
								continue for1;
							}
							UUID uid;
							try { 
								uid = UUID.fromString(f.getName().split("\\.yml")[0]); 
								info("UUID " + uid + " got");
							} catch (IllegalArgumentException ex) { 
								info(f.getName() + " is not a player data file, ignored");
								continue for1;
							}
							YamlConfiguration sc = YamlConfiguration.loadConfiguration(f);
							if (!sc.contains("backpacks")) {
								info(f.getName() + " contains not backpacks, ignored");
								continue for1;
							}
							ConfigurationSection sccs = sc.getConfigurationSection("backpacks");
							Set<String> keys = sccs.getKeys(false);
							if (keys.size() == 0) {
								info(f.getName() + " contains not backpacks, ignored");
								continue for1;
							}
							for2: 
								for (String id : keys) {
									YamlConfiguration tc = new YamlConfiguration();
									ConfigurationSection scidcs = sccs.getConfigurationSection(id);
									if (!scidcs.contains("size")) {
										info("Missing required backpack meta 'size' in backpack " + uid + "#" + id + ", ignored");
										continue for2;
									}
									if (!scidcs.contains("contents")) {
										info("Missing required backpack meta 'contents' in backpack " + uid + "#" + id + ", ignored");
										continue for2;
									}
									tc.set("size", scidcs.get("size"));
									ConfigurationSection ccs = scidcs.getConfigurationSection("contents");
									for (String contents : ccs.getKeys(false)) {
										tc.set("contents." + contents, ccs.getItemStack(contents));
									}
									File saveTo = new File(targetPath + File.separator + uid + "#" + id + ".yml");
									try {
										tc.save(saveTo);
										info("Backpack " + uid + "#" + id + " has been saved to " + saveTo.getPath());
									} catch (IOException e) {
										fatal("(" + e.getMessage() + ") IOException occourred when saving to " + saveTo.getPath());
									}
								}

							if (delete) {
								sc.set("backpacks", null);
								try {
									sc.save(f);
								} catch (IOException e) {
									fatal("(" + e.getMessage() + "IOException occourred when saving to " + f.getPath());
								}
							}
						}
					info("\033[32m\033[1mDone\033[0m");
				} else {
					warn("Missing arguments");
				}
			} else {
				sender.sendMessage("This command must be executed by Console!");
			}
		}
		return true;
	}

	private void info(String msg) {
		if (debug) plug.getLogger().info(msg);
	}

	private void warn(String msg) {
		if (debug) plug.getLogger().warning(msg);
	}

	private void fatal(String msg) {
		if (debug) plug.getLogger().severe(msg);;
	}

}
