package me.dammnranaah.guilds.guilds;

import java.util.*;

public class GuildManager {
    private Map<String, Guild> guilds;
    private Map<UUID, Guild> playerGuilds;

    public GuildManager() {
        this.guilds = new HashMap<>();
        this.playerGuilds = new HashMap<>();
    }

    public Guild createGuild(String name, UUID leader) {
        if (guilds.containsKey(name)) {
            return null;
        }

        Guild newGuild = new Guild(name, leader);
        guilds.put(name, newGuild);
        playerGuilds.put(leader, newGuild);
        return newGuild;
    }

    public Guild getGuild(String name) {
        return guilds.get(name);
    }

    public Guild getPlayerGuild(UUID playerUUID) {
        return playerGuilds.get(playerUUID);
    }

    public Collection<Guild> getAllGuilds() {
        return guilds.values();
    }

    public void disbandGuild(String guildName) {
        Guild guild = guilds.get(guildName);
        if (guild != null) {
            for (UUID memberId : guild.getMembers().keySet()) {
                playerGuilds.remove(memberId);
            }
            guilds.remove(guildName);
        }
    }
}
