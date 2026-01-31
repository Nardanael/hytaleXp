package com.onepiece.xp.stats;

/**
 * Enum représentant les différentes statistiques One Piece
 */
public enum StatType {
    VITALITY("Vitalité", "❤️", 200, true, "PV"),
    RESISTANCE("Résistance", "🛡️", 200, true, "% Réduction"),
    ATTACK("Attaque", "⚔️", 200, true, "% Dégâts"),
    STAMINA("Stamina", "⚡", 200, true, "Stamina"),
    HAKI("Stamina Haki", "🌀", 200, true, "Haki"),
    RESPIRATION("Respiration", "🫧", 60, false, "secondes");

    private final String displayName;
    private final String icon;
    private final int maxPoints;
    private final boolean manuallyUpgradeable;
    private final String unit;

    StatType(String displayName, String icon, int maxPoints, boolean manuallyUpgradeable, String unit) {
        this.displayName = displayName;
        this.icon = icon;
        this.maxPoints = maxPoints;
        this.manuallyUpgradeable = manuallyUpgradeable;
        this.unit = unit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIcon() {
        return icon;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public boolean isManuallyUpgradeable() {
        return manuallyUpgradeable;
    }

    public String getUnit() {
        return unit;
    }

    /**
     * Calcule l'effet de cette stat en fonction des points investis
     */
    public double calculateEffect(int points) {
        return switch (this) {
            case VITALITY -> 100 + (points * 2); // Base 100 HP + 2 HP par point
            case RESISTANCE -> points * 0.25; // 0.25% de réduction par point (max 50%)
            case ATTACK -> points * 0.5; // 0.5% de dégâts par point (max 100%)
            case STAMINA -> points * 2; // 2 stamina par point
            case HAKI -> points * 2; // 2 haki stamina par point
            case RESPIRATION -> points * 2; // 2 secondes par point
        };
    }

    /**
     * Obtient la description formatée de l'effet pour un nombre de points donné
     */
    public String getEffectDescription(int points) {
        double effect = calculateEffect(points);
        return switch (this) {
            case VITALITY -> String.format("%.0f %s", effect, unit);
            case RESISTANCE -> String.format("%.2f%% %s", effect, unit);
            case ATTACK -> String.format("%.1f%% %s", effect, unit);
            case STAMINA -> String.format("%.0f %s", effect, unit);
            case HAKI -> String.format("%.0f %s", effect, unit);
            case RESPIRATION -> String.format("%.0f %s", effect, unit);
        };
    }

    /**
     * Applique l'effet de cette stat à un joueur
     * Cette méthode doit être implémentée selon l'API Hytale
     */
    public void applyEffect(Object player, int points) {
        // TODO: Implémenter selon l'API Hytale
        // Par exemple:
        // - VITALITY: player.setMaxHealth(calculateEffect(points))
        // - RESISTANCE: player.setDamageReduction(calculateEffect(points) / 100.0)
        // - ATTACK: player.setDamageMultiplier(1.0 + calculateEffect(points) / 100.0)
        // - etc.
    }
}
