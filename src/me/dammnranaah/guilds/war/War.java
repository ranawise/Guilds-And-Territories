package me.dammnranaah.guilds.war;

import me.dammnranaah.guilds.guilds.Guild;
import me.dammnranaah.guilds.territory.Territory;
import java.time.LocalDateTime;

public class War {
    private Guild attackingGuild;
    private Guild defendingGuild;
    private Territory targetTerritory;
    private LocalDateTime startTime;
    private WarStatus status;

    public enum WarStatus {
        ONGOING, CONCLUDED
    }

    public War(Guild attackingGuild, Guild defendingGuild, Territory targetTerritory) {
        this.attackingGuild = attackingGuild;
        this.defendingGuild = defendingGuild;
        this.targetTerritory = targetTerritory;
        this.startTime = LocalDateTime.now();
        this.status = WarStatus.ONGOING;
    }

    public Guild getAttackingGuild() {
        return attackingGuild;
    }

    public Guild getDefendingGuild() {
        return defendingGuild;
    }

    public Territory getTargetTerritory() {
        return targetTerritory;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public WarStatus getStatus() {
        return status;
    }

    public void setStatus(WarStatus status) {
        this.status = status;
    }

    public long getDuration() {
        return java.time.Duration.between(startTime, LocalDateTime.now()).toHours();
    }
} 