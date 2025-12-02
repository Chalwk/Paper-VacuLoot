package com.chalwk.config;

import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginConfig {

    private final Map<String, Object> settings = new HashMap<>();
    private final Map<String, Map<String, Object>> magnetTiers = new HashMap<>();

    public void loadFromConfig(org.bukkit.configuration.ConfigurationSection config) {
        // General settings
        settings.put("default_tier", config.getString("default_tier", "basic"));
        settings.put("magnet_interval", config.getInt("magnet.interval", 5));
        settings.put("toggle_cooldown", config.getInt("toggle.cooldown", 5));

        // Attraction settings
        settings.put("attract_items", config.getBoolean("attraction.items", true));
        settings.put("attract_experience", config.getBoolean("attraction.experience", true));
        settings.put("attraction_speed", config.getDouble("attraction.speed", 0.3));

        // Economy settings
        settings.put("economy.enabled", config.getBoolean("economy.enabled", false));
        settings.put("economy.toggle_cost", config.getDouble("economy.toggle_cost", 10.0));

        // World restrictions
        List<String> allowedWorlds = config.getStringList("worlds.allowed");
        settings.put("worlds.allowed", allowedWorlds);

        // Item blacklist
        List<String> blacklist = config.getStringList("blacklist.materials");
        settings.put("blacklist.materials", blacklist);

        // Load magnet tiers
        ConfigurationSection tiers = config.getConfigurationSection("tiers");
        magnetTiers.clear();
        if (tiers != null) {
            for (String tier : tiers.getKeys(false)) {
                Map<String, Object> tierData = new HashMap<>();
                tierData.put("range", tiers.getDouble(tier + ".range", 5.0));
                tierData.put("speed_multiplier", tiers.getDouble(tier + ".speed_multiplier", 1.0));
                tierData.put("permission", tiers.getString(tier + ".permission", "magnet.tier." + tier));
                magnetTiers.put(tier.toLowerCase(), tierData);
            }
        }

        // Messages
        ConfigurationSection messages = config.getConfigurationSection("messages");
        if (messages != null) {
            for (String key : messages.getKeys(false)) {
                settings.put("message." + key, messages.getString(key));
            }
        }
    }

    public String getMessage(String key) {
        return (String) settings.getOrDefault("message." + key, "&cMessage not configured: " + key);
    }

    public String getDefaultTier() {
        return (String) settings.get("default_tier");
    }

    public int getMagnetInterval() {
        return (int) settings.get("magnet_interval");
    }

    public int getToggleCooldown() {
        return (int) settings.get("toggle_cooldown");
    }

    public boolean isAttractItems() {
        return (boolean) settings.get("attract_items");
    }

    public boolean isAttractExperience() {
        return (boolean) settings.get("attract_experience");
    }

    public double getBaseAttractionSpeed() {
        return (double) settings.get("attraction_speed");
    }

    public double getAttractionSpeed(String tier) {
        Map<String, Object> tierData = magnetTiers.get(tier);
        if (tierData != null) {
            double multiplier = (double) tierData.getOrDefault("speed_multiplier", 1.0);
            return getBaseAttractionSpeed() * multiplier;
        }
        return getBaseAttractionSpeed();
    }

    public boolean isEconomyEnabled() {
        return (boolean) settings.get("economy.enabled");
    }

    public double getToggleCost() {
        return (double) settings.get("economy.toggle_cost");
    }

    @SuppressWarnings("unchecked")
    public List<String> getAllowedWorlds() {
        return (List<String>) settings.get("worlds.allowed");
    }

    @SuppressWarnings("unchecked")
    public List<String> getBlacklistedMaterials() {
        return (List<String>) settings.get("blacklist.materials");
    }

    public Map<String, Map<String, Object>> getMagnetTiers() {
        return magnetTiers;
    }

    public double getMagnetRange(String tier) {
        Map<String, Object> tierData = magnetTiers.get(tier);
        if (tierData != null) {
            return (double) tierData.getOrDefault("range", 5.0);
        }
        return 5.0;
    }
}