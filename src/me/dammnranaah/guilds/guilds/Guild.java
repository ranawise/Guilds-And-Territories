package me.dammnranaah.guilds.guilds;

import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class Guild {
    private String name;
    private UUID leader;
    private Map<UUID, GuildRank> members;
    private List<Territory> territories;
    private double treasury;
    private int guildLevel;
    private List<UUID> invitedPlayers;
    private List<String> guildAllies;

    public Guild(String name, UUID leader) {
        this.name = name;
        this.leader = leader;
        this.members = new HashMap<>();
        this.territories = new ArrayList<>();
        this.treasury = 0.0;
        this.guildLevel = 1;
        this.invitedPlayers = new ArrayList<>();
        this.guildAllies = new ArrayList<>();

        // Add leader to members with LEADER rank
        members.put(leader, GuildRank.LEADER);
    }

    public void addMember(Player player, GuildRank rank) {
        members.put(player.getUniqueId(), rank);
        invitedPlayers.remove(player.getUniqueId());
    }

    public void removeMember(UUID playerUUID) {
        members.remove(playerUUID);
    }

    public void promoteMember(UUID playerUUID) {
        GuildRank currentRank = members.get(playerUUID);
        if (currentRank != null && currentRank.ordinal() < GuildRank.values().length - 1) {
            members.put(playerUUID, GuildRank.values()[currentRank.ordinal() + 1]);
        }
    }

    public void demoteMember(UUID playerUUID) {
        GuildRank currentRank = members.get(playerUUID);
        if (currentRank != null && currentRank.ordinal() > 0) {
            members.put(playerUUID, GuildRank.values()[currentRank.ordinal() - 1]);
        }
    }

    public void addTerritory(Territory territory) {
        territories.add(territory);
    }

    public void removeTerritory(Territory territory) {
        territories.remove(territory);
    }

    public void addAlly(String guildName) {
        guildAllies.add(guildName);
    }

    public void removeAlly(String guildName) {
        guildAllies.remove(guildName);
    }

    public void depositToTreasury(double amount) {
        treasury += amount;
    }

    public boolean withdrawFromTreasury(double amount) {
        if (treasury >= amount) {
            treasury -= amount;
            return true;
        }
        return false;
    }

    public void levelUp() {
        guildLevel++;
    }

    // Getters
    public String getName() { return name; }
    public UUID getLeader() { return leader; }
    public Map<UUID, GuildRank> getMembers() { return members; }
    public List<Territory> getTerritories() { return territories; }
    public double getTreasury() { return treasury; }
    public int getGuildLevel() { return guildLevel; }
    public List<UUID> getInvitedPlayers() { return invitedPlayers; }
    public List<String> getGuildAllies() { return guildAllies; }

    public void invitePlayer(UUID playerUUID) {
        if (!members.containsKey(playerUUID) && !invitedPlayers.contains(playerUUID)) {
            invitedPlayers.add(playerUUID);
        }
    }
}


GuildsAndTerritories/
│── src/
│   ├── me.dammnranaah.guilds/          # Main package
│   │   ├── GuildsAndTerritories.java  # Main Plugin Class
│   │   ├── commands/
│   │   │   ├── GuildCommand.java
│   │   │   ├── ClaimCommand.java
│   │   │   ├── WarCommand.java
│   │   │   ├── TaxCommand.java
│   │   ├── guilds/
│   │   │   ├── Guild.java
│   │   │   ├── GuildManager.java
│   │   │   ├── GuildRank.java
│   │   │   ├── Territory.java
│   │   ├── economy/
│   │   │   ├── TaxSystem.java
│   │   │   ├── Treasury.java
│   │   ├── war/
│   │   │   ├── WarManager.java
│   │   │   ├── SiegeMechanics.java
│   │   ├── events/
│   │   │   ├── GuildEvents.java
│   │   │   ├── WarEvents.java
│   │   ├── config/
│   │   │   ├── ConfigManager.java
│   │   │   ├── Messages.java
│   │   ├── plugin.yml
│   │   ├── config.yml
│   │   ├── README.md
