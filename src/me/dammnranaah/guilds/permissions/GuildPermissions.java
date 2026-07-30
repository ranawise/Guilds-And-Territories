package me.dammnranaah.guilds.permissions;

import me.dammnranaah.guilds.guilds.Guild;
import me.dammnranaah.guilds.guilds.GuildRank;
import org.bukkit.entity.Player;

import java.util.*;

public class GuildPermissions {
    public enum Permission {
        // Territory Permissions
        CLAIM_TERRITORY,
        EXPAND_TERRITORY,
        REMOVE_TERRITORY,

        // Member Management
        INVITE_MEMBERS,
        KICK_MEMBERS,
        PROMOTE_MEMBERS,
        DEMOTE_MEMBERS,

        // Economic Permissions
        DEPOSIT_TREASURY,
        WITHDRAW_TREASURY,
        VIEW_TREASURY,

        // War and Diplomacy
        DECLARE_WAR,
        NEGOTIATE_PEACE,
        FORM_ALLIANCES,

        // Guild Management
        CHANGE_GUILD_NAME,
        CHANGE_GUILD_DESCRIPTION,
        MANAGE_GUILD_SETTINGS
    }

    private Map<GuildRank, Set<Permission>> rankPermissions;

    public GuildPermissions() {
        this.rankPermissions = new HashMap<>();
        initializeDefaultPermissions();
    }

    private void initializeDefaultPermissions() {
        // Leader has all permissions
        Set<Permission> leaderPermissions = new HashSet<>(Arrays.asList(Permission.values()));
        rankPermissions.put(GuildRank.LEADER, leaderPermissions);

        // Warlord permissions
        Set<Permission> warlordPermissions = new HashSet<>(Arrays.asList(
            Permission.CLAIM_TERRITORY,
            Permission.EXPAND_TERRITORY,
            Permission.DECLARE_WAR,
            Permission.NEGOTIATE_PEACE
        ));
        rankPermissions.put(GuildRank.WARLORD, warlordPermissions);

        // Treasurer permissions
        Set<Permission> treasurerPermissions = new HashSet<>(Arrays.asList(
            Permission.DEPOSIT_TREASURY,
            Permission.WITHDRAW_TREASURY,
            Permission.VIEW_TREASURY
        ));
        rankPermissions.put(GuildRank.TREASURER, treasurerPermissions);

        // Guard permissions
        Set<Permission> guardPermissions = new HashSet<>(Arrays.asList(
            Permission.INVITE_MEMBERS,
            Permission.KICK_MEMBERS
        ));
        rankPermissions.put(GuildRank.GUARD, guardPermissions);

        // Citizen permissions (minimal)
        Set<Permission> citizenPermissions = new HashSet<>(Arrays.asList(
            Permission.DEPOSIT_TREASURY
        ));
        rankPermissions.put(GuildRank.CITIZEN, citizenPermissions);
    }

    public boolean hasPermission(Guild guild, Player player, Permission permission) {
        GuildRank playerRank = guild.getMembers().get(player.getUniqueId());
        return playerRank != null && rankPermissions.get(playerRank).contains(permission);
    }

    public void addRankPermission(GuildRank rank, Permission permission) {
        rankPermissions.computeIfAbsent(rank, k -> new HashSet<>()).add(permission);
    }

    public void removeRankPermission(GuildRank rank, Permission permission) {
        if (rankPermissions.containsKey(rank)) {
            rankPermissions.get(rank).remove(permission);
        }
    }

    public Set<Permission> getRankPermissions(GuildRank rank) {
        return rankPermissions.getOrDefault(rank, new HashSet<>());
    }

    public void customizeRankPermissions(GuildRank rank, Set<Permission> customPermissions) {
        rankPermissions.put(rank, customPermissions);
    }
} 