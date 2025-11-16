package me.dammnranaah.guilds.achievements;

import me.dammnranaah.guilds.guilds.Guild;
import java.util.*;

public class AchievementSystem {
    private Map<Guild, Set<GuildAchievement>> guildAchievements;

    public enum AchievementType {
        TERRITORY_EXPANSION,
        MEMBER_COUNT,
        WAR_VICTORIES,
        ECONOMIC_POWER,
        LONGEVITY
    }

    public class GuildAchievement {
        private AchievementType type;
        private String name;
        private String description;
        private int requiredProgress;
        private int currentProgress;
        private boolean isUnlocked;

        public GuildAchievement(AchievementType type, String name, String description, int requiredProgress) {
            this.type = type;
            this.name = name;
            this.description = description;
            this.requiredProgress = requiredProgress;
            this.currentProgress = 0;
            this.isUnlocked = false;
        }

        public void updateProgress(int progress) {
            currentProgress += progress;
            if (currentProgress >= requiredProgress) {
                isUnlocked = true;
            }
        }

        // Getters
        public AchievementType getType() { return type; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public int getCurrentProgress() { return currentProgress; }
        public boolean isUnlocked() { return isUnlocked; }
    }

    public AchievementSystem() {
        this.guildAchievements = new HashMap<>();
    }

    public void registerAchievement(Guild guild, AchievementType type, String name, String description, int requiredProgress) {
        GuildAchievement achievement = new GuildAchievement(type, name, description, requiredProgress);
        guildAchievements.computeIfAbsent(guild, k -> new HashSet<>()).add(achievement);
    }

    public void updateAchievementProgress(Guild guild, AchievementType type, int progress) {
        if (guildAchievements.containsKey(guild)) {
            guildAchievements.get(guild).stream()
                .filter(achievement -> achievement.getType() == type)
                .forEach(achievement -> achievement.updateProgress(progress));
        }
    }

    public Set<GuildAchievement> getGuildAchievements(Guild guild) {
        return guildAchievements.getOrDefault(guild, new HashSet<>());
    }

    public List<GuildAchievement> getUnlockedAchievements(Guild guild) {
        return guildAchievements.getOrDefault(guild, new HashSet<>()).stream()
            .filter(GuildAchievement::isUnlocked)
            .toList();
    }

    // Predefined Achievement Templates
    public void initializeDefaultAchievements(Guild guild) {
        registerAchievement(guild, AchievementType.TERRITORY_EXPANSION, 
            "Land Grabber", "Claim 5 territories", 5);
        registerAchievement(guild, AchievementType.MEMBER_COUNT, 
            "Growing Strong", "Reach 10 guild members", 10);
        registerAchievement(guild, AchievementType.WAR_VICTORIES, 
            "Conquerors", "Win 3 wars", 3);
        registerAchievement(guild, AchievementType.ECONOMIC_POWER, 
            "Rich Guild", "Accumulate 10,000 in treasury", 10000);
        registerAchievement(guild, AchievementType.LONGEVITY, 
            "Veteran Guild", "Exist for 30 days", 30);
    }
} 