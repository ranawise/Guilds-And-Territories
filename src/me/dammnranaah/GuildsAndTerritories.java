package me.dammnranaah;

import me.dammnranaah.guilds.guilds.GuildManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GuildsAndTerritories extends JavaPlugin {
    private static GuildsAndTerritories instance;
    private GuildManager guildManager;

    @Override
    public void onEnable() {
        instance = this;
        guildManager = new GuildManager();

        getLogger().info("GuildsAndTerritories plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("GuildsAndTerritories plugin has been disabled!");
    }

    public static GuildsAndTerritories getInstance() {
        return instance;
    }

    public GuildManager getGuildManager() {
        return guildManager;
    }
}
