package me.dammnranaah.guilds.territory;

import me.dammnranaah.guilds.guilds.Guild;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TerritoryManager {
    private Map<String, Territory> territories;

    public TerritoryManager() {
        this.territories = new HashMap<>();
    }

    public Territory createTerritory(Guild guild, Location center, int radius) {
        String territoryId = UUID.randomUUID().toString();
        Territory territory = new Territory(territoryId, guild.getName(), center, radius);
        territories.put(territoryId, territory);
        guild.addTerritory(territory);
        return territory;
    }

    public Territory getTerritoryAt(Location location) {
        for (Territory territory : territories.values()) {
            if (territory.contains(location)) {
                return territory;
            }
        }
        return null;
    }

    public boolean canClaimTerritory(Guild guild, Location center, int radius) {
        // Check if the territory overlaps with existing territories
        for (Territory existingTerritory : territories.values()) {
            if (existingTerritory.overlaps(center, radius)) {
                return false;
            }
        }
        return true;
    }

    public void expandTerritory(Territory territory, int additionalRadius) {
        territory.expand(additionalRadius);
    }

    public void removeTerritory(Territory territory) {
        territories.remove(territory.getId());
    }

    public int getTerritoryCount(Guild guild) {
        return (int) territories.values().stream()
                .filter(t -> t.getOwnerGuildName().equals(guild.getName()))
                .count();
    }
} 