package me.dammnranaah.guilds.commands;

import me.dammnranaah.guilds.GuildsAndTerritories;
import me.dammnranaah.guilds.guilds.Guild;
import me.dammnranaah.guilds.guilds.GuildManager;
import me.dammnranaah.guilds.guilds.GuildRank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GuildCommand implements CommandExecutor {

    private GuildManager guildManager;

    public GuildCommand() {
        this.guildManager = GuildsAndTerritories.getInstance().getGuildManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            sendHelpMessage(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                handleGuildCreate(player, args);
                break;
            case "invite":
                handleGuildInvite(player, args);
                break;
            case "kick":
                handleGuildKick(player, args);
                break;
            case "promote":
                handleGuildPromote(player, args);
                break;
            case "demote":
                handleGuildDemote(player, args);
                break;
            case "list":
                handleGuildList(player);
                break;
            case "info":
                handleGuildInfo(player);
                break;
            default:
                sendHelpMessage(player);
        }

        return true;
    }

    private void handleGuildCreate(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("Usage: /guild create <name>");
            return;
        }

        String guildName = args[1];

        if (guildManager.getGuild(guildName) != null) {
            player.sendMessage("This guild name is already taken!");
            return;
        }

        guildManager.createGuild(guildName, player.getUniqueId());
        player.sendMessage("Guild " + guildName + " has been created!");
        Bukkit.broadcastMessage(player.getName() + " has founded a new guild: " + guildName);
    }

    private void handleGuildInvite(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("Usage: /guild invite <player>");
            return;
        }

        Player targetPlayer = Bukkit.getPlayer(args[1]);
        if (targetPlayer == null) {
            player.sendMessage("Player not found!");
            return;
        }

        Guild playerGuild = guildManager.getPlayerGuild(player.getUniqueId());
        if (playerGuild == null) {
            player.sendMessage("You are not in a guild!");
            return;
        }

        if (playerGuild.getMembers().get(player.getUniqueId()) != GuildRank.LEADER) {
            player.sendMessage("Only guild leaders can invite players!");
            return;
        }

        playerGuild.invitePlayer(targetPlayer.getUniqueId());
        player.sendMessage("Invited " + targetPlayer.getName() + " to the guild.");
        targetPlayer.sendMessage("You have been invited to " + playerGuild.getName() + " guild!");
    }

    private void handleGuildKick(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("Usage: /guild kick <player>");
            return;
        }

        Player targetPlayer = Bukkit.getPlayer(args[1]);
        if (targetPlayer == null) {
            player.sendMessage("Player not found!");
            return;
        }

        Guild playerGuild = guildManager.getPlayerGuild(player.getUniqueId());
        if (playerGuild == null) {
            player.sendMessage("You are not in a guild!");
            return;
        }

        if (playerGuild.getMembers().get(player.getUniqueId()) != GuildRank.LEADER) {
            player.sendMessage("Only guild leaders can kick players!");
            return;
        }

        playerGuild.removeMember(targetPlayer.getUniqueId());
        player.sendMessage(targetPlayer.getName() + " has been kicked from the guild.");
        targetPlayer.sendMessage("You have been kicked from " + playerGuild.getName() + " guild!");
    }

    private void handleGuildPromote(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("Usage: /guild promote <player>");
            return;
        }

        Player targetPlayer = Bukkit.getPlayer(args[1]);
        if (targetPlayer == null) {
            player.sendMessage("Player not found!");
            return;
        }

        Guild playerGuild = guildManager.getPlayerGuild(player.getUniqueId());
        if (playerGuild == null) {
            player.sendMessage("You are not in a guild!");
            return;
        }

        if (playerGuild.getMembers().get(player.getUniqueId()) != GuildRank.LEADER) {
            player.sendMessage("Only guild leaders can promote players!");
            return;
        }

        playerGuild.promoteMember(targetPlayer.getUniqueId());
        player.sendMessage(targetPlayer.getName() + " has been promoted.");
        targetPlayer.sendMessage("You have been promoted in " + playerGuild.getName() + " guild!");
    }

    private void handleGuildDemote(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("Usage: /guild demote <player>");
            return;
        }

        Player targetPlayer = Bukkit.getPlayer(args[1]);
        if (targetPlayer == null) {
            player.sendMessage("Player not found!");
            return;
        }

        Guild playerGuild = guildManager.getPlayerGuild(player.getUniqueId());
        if (playerGuild == null) {
            player.sendMessage("You are not in a guild!");
            return;
        }

        if (playerGuild.getMembers().get(player.getUniqueId()) != GuildRank.LEADER) {
            player.sendMessage("Only guild leaders can demote players!");
            return;
        }

        playerGuild.demoteMember(targetPlayer.getUniqueId());
        player.sendMessage(targetPlayer.getName() + " has been demoted.");
        targetPlayer.sendMessage("You have been demoted in " + playerGuild.getName() + " guild!");
    }

    private void handleGuildList(Player player) {
        player.sendMessage("Guild List:");
        for (Guild guild : guildManager.getAllGuilds()) {
            player.sendMessage("- " + guild.getName() + " (Level " + guild.getGuildLevel() + ")");
        }
    }

    private void handleGuildInfo(Player player) {
        Guild playerGuild = guildManager.getPlayerGuild(player.getUniqueId());
        if (playerGuild == null) {
            player.sendMessage("You are not in a guild!");
            return;
        }

        player.sendMessage("Guild Information:");
        player.sendMessage("Name: " + playerGuild.getName());
        player.sendMessage("Leader: " + Bukkit.getOfflinePlayer(playerGuild.getLeader()).getName());
        player.sendMessage("Level: " + playerGuild.getGuildLevel());
        player.sendMessage("Treasury: " + playerGuild.getTreasury());
        player.sendMessage("Territories: " + playerGuild.getTerritories().size());
        player.sendMessage("Members:");
        for (UUID memberId : playerGuild.getMembers().keySet()) {
            player.sendMessage("- " + Bukkit.getOfflinePlayer(memberId).getName() + 
                               " (" + playerGuild.getMembers().get(memberId) + ")");
        }
    }

    private void sendHelpMessage(Player player) {
        player.sendMessage("Guild Commands:");
        player.sendMessage("/guild create <name> - Create a new guild");
        player.sendMessage("/guild invite <player> - Invite a player to your guild");
        player.sendMessage("/guild kick <player> - Kick a player from your guild");
        player.sendMessage("/guild promote <player> - Promote a guild member");
        player.sendMessage("/guild demote <player> - Demote a guild member");
        player.sendMessage("/guild list - List all guilds");
        player.sendMessage("/guild info - Show your guild information");
    }
}
