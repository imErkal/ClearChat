package ro.imerkal.ClearChat.Utile;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import net.md_5.bungee.api.ChatColor;

public class SettingsManager extends YamlConfiguration {
	
	static SettingsManager instance = new SettingsManager();
	Plugin p;
	FileConfiguration config;
	File cfile;
	
	public static SettingsManager getInstance() {
		return instance;
	}
	
	public void setup(Plugin p) {
		this.config = p.getConfig();
		this.cfile = new File(p.getDataFolder(), "config.yml");
	}
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	public void save() {
		try {
			this.config.save(cfile);
		}
		catch (Exception e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
		}
	}
	
	public void reloadConfig() {
		this.config = YamlConfiguration.loadConfiguration(this.cfile);
	}
	
	public PluginDescriptionFile getDesc() {
		return this.p.getDescription();
	}

}
