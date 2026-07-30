package me.dammnranaah.guilds.territory;

import org.bukkit.Location;
import org.bukkit.World;

public class Territory {
    private String id;
    private String ownerGuildName;
    private Location center;
    private int radius;

    public Territory(String id, String ownerGuildName, Location center, int radius) {
        this.id = id;
        this.ownerGuildName = ownerGuildName;
        this.center = center;
        this.radius = radius;
    }

    public boolean contains(Location location) {
        if (!location.getWorld().equals(center.getWorld())) {
            return false;
        }
        return center.distance(location) <= radius;
    }

    public boolean overlaps(Location otherCenter, int otherRadius) {
        if (!otherCenter.getWorld().equals(center.getWorld())) {
            return false;
        }
        double distance = center.distance(otherCenter);
        return distance < (radius + otherRadius);
    }

    public void expand(int additionalRadius) {
        this.radius += additionalRadius;
    }

    // Getters
    public String getId() { return id; }
    public String getOwnerGuildName() { return ownerGuildName; }
    public Location getCenter() { return center; }
    public int getRadius() { return radius; }
} 