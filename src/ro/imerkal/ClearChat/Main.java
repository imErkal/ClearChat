package ro.imerkal.ClearChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import ro.imerkal.ClearChat.Utile.SettingsManager;

public class Main extends JavaPlugin {
	
	public static SettingsManager settings = new SettingsManager();
	
	public void onEnable() {
		ConsoleCommandSender console = getServer().getConsoleSender();
	    
	    console.sendMessage("§4-=-=-=-=-=-=-=-=-=-=-");
	    console.sendMessage("§a   ClearChat v1.3");
	    console.sendMessage("§a    by ImErkal_");
	    console.sendMessage("§4-=-=-=-=-=-=-=-=-=-=-");
	    
	    loadConfig();
	    autoclear();
	}
	
	public void loadConfig() {
		saveDefaultConfig();
	    Main.settings.setup(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("clearchat")) {
			if ((!(p.hasPermission("clearchat.clearchat")) && (!(p.hasPermission("clearchat.*"))))) {
				String prefix = Main.settings.getConfig().getString("Prefix").replace("&", "§");
				String noperms = Main.settings.getConfig().getString("noperms").replace("&", "§");
				p.sendMessage(prefix + " " + noperms);
				return true;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Only players can use this command!");
			}
			int a = 0;
			int line = Main.settings.getConfig().getInt("Line");
			while(a < line) {
				Bukkit.broadcastMessage(" ");
				a++;
			}
			String prefix = Main.settings.getConfig().getString("Prefix").replace("&", "§");
			String clearchat = Main.settings.getConfig().getString("clearchat-message").replace("%name%", p.getName()).replace("&", "§");
			Bukkit.broadcastMessage(prefix + " " + clearchat);
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("clearmychat")) {
			if ((!(p.hasPermission("clearchat.clearmychat")) && (!(p.hasPermission("clearchat.*"))))) {
				String prefix = Main.settings.getConfig().getString("Prefix").replace("&", "§");
				String noperms = Main.settings.getConfig().getString("noperms").replace("&", "§");
				p.sendMessage(prefix + " " + noperms);
				return true;
			}
			int a = 0;
			int line = Main.settings.getConfig().getInt("Line");
			while(a < line) {
				sender.sendMessage(" ");
				a++;
			}
			String prefix = Main.settings.getConfig().getString("Prefix").replace("&", "§");
			String clearmychat = Main.settings.getConfig().getString("clearmychat-message").replace("&", "§");
			sender.sendMessage(prefix +  " " + clearmychat);
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public void autoclear() {
		int interval = getConfig().getInt("autoclear.interval") * 20;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
			@Override
			public void run() {
				if (Main.settings.getConfig().getBoolean("autoclear.Enabled")) {
					int a = 0;
					int line = Main.settings.getConfig().getInt("Line");
					while(a < line) {
						Bukkit.broadcastMessage(" ");
						a++;
					}
					if (Main.settings.getConfig().getBoolean("autoclear.message")) {
						String prefix = Main.settings.getConfig().getString("Prefix").replace("&", "§");
						String cc = Main.settings.getConfig().getString("autoclearchat-message").replace("&", "§");
						Bukkit.broadcastMessage(prefix + " " + cc);
					}
				}
			}
		}, 20L, interval);
	}
}
