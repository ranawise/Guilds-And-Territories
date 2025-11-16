package me.dammnranaah.guilds.economy;

import me.dammnranaah.guilds.guilds.Guild;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EconomyHandler {
    private Map<Guild, Double> guildBalances;
    private Map<Guild, Map<UUID, Double>> memberContributions;
    private static final double TAX_RATE = 0.1; // 10% tax rate

    public EconomyHandler() {
        this.guildBalances = new HashMap<>();
        this.memberContributions = new HashMap<>();
    }

    public boolean depositToGuildTreasury(Guild guild, Player contributor, double amount) {
        if (amount <= 0) return false;

        // Deduct tax
        double taxAmount = amount * TAX_RATE;
        double netContribution = amount - taxAmount;

        // Update guild balance
        guildBalances.merge(guild, netContribution, Double::sum);

        // Track individual member contributions
        memberContributions.computeIfAbsent(guild, k -> new HashMap<>())
                .merge(contributor.getUniqueId(), netContribution, Double::sum);

        // Update guild treasury
        guild.depositToTreasury(netContribution);

        return true;
    }

    public boolean withdrawFromGuildTreasury(Guild guild, Player withdrawer, double amount) {
        if (amount <= 0) return false;

        // Check guild rank or permissions for withdrawal
        if (!canWithdraw(guild, withdrawer)) {
            return false;
        }

        if (guild.getTreasury() >= amount) {
            guild.withdrawFromTreasury(amount);
            guildBalances.merge(guild, -amount, Double::sum);
            return true;
        }
        return false;
    }

    public double getGuildBalance(Guild guild) {
        return guildBalances.getOrDefault(guild, 0.0);
    }

    public double getMemberContribution(Guild guild, Player member) {
        return memberContributions.getOrDefault(guild, new HashMap<>())
                .getOrDefault(member.getUniqueId(), 0.0);
    }

    public void transferBetweenGuilds(Guild sender, Guild recipient, double amount) {
        if (sender.getTreasury() >= amount) {
            sender.withdrawFromTreasury(amount);
            recipient.depositToTreasury(amount);
            guildBalances.merge(sender, -amount, Double::sum);
            guildBalances.merge(recipient, amount, Double::sum);
        }
    }

    private boolean canWithdraw(Guild guild, Player player) {
        // Implement more complex permission checks based on guild rank
        return guild.getMembers().get(player.getUniqueId()).ordinal() <= 2; // Leader or Treasurer
    }

    public void applyDailyTax(Guild guild) {
        double currentBalance = getGuildBalance(guild);
        double taxAmount = currentBalance * TAX_RATE;
        
        if (taxAmount > 0) {
            guildBalances.merge(guild, -taxAmount, Double::sum);
        }
    }
} 