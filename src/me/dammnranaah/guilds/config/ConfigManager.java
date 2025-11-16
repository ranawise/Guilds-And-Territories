package me.dammnranaah.guilds.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {
    private FileConfiguration config;

    public ConfigManager(JavaPlugin plugin) {
        this.config = plugin.getConfig();
    }

    public int getGuildCreationCost() {
        return config.getInt("guild_creation_cost");
    }
}
