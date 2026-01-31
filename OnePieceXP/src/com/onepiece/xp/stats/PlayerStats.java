package com.onepiece.xp.stats;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe stockant les statistiques d'un joueur
 */
public class PlayerStats {
    // Constantes de progression
    public static final int MAX_LEVEL = 60;
    public static final int FREE_POINTS_PER_LEVEL = 5;
    public static final int AUTO_POINTS_PER_LEVEL = 1; // Par stat (6 au total)
    
    // Capacités totales
    public static final int MAX_TOTAL_STATS = 1000; // 5 stats x 200
    public static final int BONUS_STATS_CAP = 400; // Points endgame
    
    // Données du joueur
    private int level;
    private int experience;
    private int freePoints;
    private int bonusPoints; // Points obtenus via endgame
    
    // Points de stats (manuel + auto combinés)
    private final Map<StatType, Integer> statPoints;
    
    public PlayerStats() {
        this.level = 0;
        this.experience = 0;
        this.freePoints = 0;
        this.bonusPoints = 0;
        this.statPoints = new HashMap<>();
        
        // Initialiser toutes les stats à 0
        for (StatType stat : StatType.values()) {
            statPoints.put(stat, 0);
        }
    }
    
    /**
     * Ajoute un niveau et octroie les points
     */
    public void addLevel() {
        if (level >= MAX_LEVEL) {
            return; // Niveau max atteint
        }
        
        level++;
        freePoints += FREE_POINTS_PER_LEVEL;
        
        // Ajouter les points automatiques à toutes les stats
        for (StatType stat : StatType.values()) {
            statPoints.put(stat, statPoints.get(stat) + AUTO_POINTS_PER_LEVEL);
        }
    }
    
    /**
     * Alloue un point libre à une statistique
     * @return true si l'allocation a réussi, false sinon
     */
    public boolean allocatePoint(StatType stat, int amount) {
        // Vérifier si la stat est améliorable manuellement
        if (!stat.isManuallyUpgradeable()) {
            return false; // Respiration ne peut pas être améliorée manuellement
        }
        
        // Vérifier si on a assez de points libres
        if (freePoints < amount) {
            return false;
        }
        
        // Vérifier si on ne dépasse pas le cap
        int currentPoints = statPoints.get(stat);
        if (currentPoints + amount > stat.getMaxPoints()) {
            return false;
        }
        
        // Allouer le point
        freePoints -= amount;
        statPoints.put(stat, currentPoints + amount);
        return true;
    }
    
    /**
     * Ajoute des points bonus (endgame) à une statistique
     * @return true si l'ajout a réussi, false sinon
     */
    public boolean addBonusPoint(StatType stat, int amount) {
        if (!stat.isManuallyUpgradeable()) {
            return false;
        }
        
        // Vérifier le cap de points bonus
        if (bonusPoints + amount > BONUS_STATS_CAP) {
            return false;
        }
        
        // Vérifier le cap de la stat
        int currentPoints = statPoints.get(stat);
        if (currentPoints + amount > stat.getMaxPoints()) {
            return false;
        }
        
        // Ajouter le point bonus
        bonusPoints += amount;
        statPoints.put(stat, currentPoints + amount);
        return true;
    }
    
    /**
     * Obtient le nombre de points dans une stat
     */
    public int getStatPoints(StatType stat) {
        return statPoints.getOrDefault(stat, 0);
    }
    
    /**
     * Calcule le total de points investis (sans Respiration)
     */
    public int getTotalStats() {
        int total = 0;
        for (StatType stat : StatType.values()) {
            if (stat != StatType.RESPIRATION) {
                total += statPoints.get(stat);
            }
        }
        return total;
    }
    
    /**
     * Obtient la zone actuelle en fonction du niveau
     */
    public String getZone() {
        if (level <= 20) return "East Blue";
        if (level <= 40) return "Paradise";
        return "New World";
    }
    
    /**
     * Applique tous les effets des stats au joueur
     */
    public void applyAllEffects(Object player) {
        for (StatType stat : StatType.values()) {
            int points = statPoints.get(stat);
            stat.applyEffect(player, points);
        }
    }
    
    // Getters
    public int getLevel() { return level; }
    public int getExperience() { return experience; }
    public int getFreePoints() { return freePoints; }
    public int getBonusPoints() { return bonusPoints; }
    
    // Setters
    public void setLevel(int level) { this.level = Math.min(level, MAX_LEVEL); }
    public void setExperience(int experience) { this.experience = experience; }
    public void addExperience(int amount) { this.experience += amount; }
    
    /**
     * Réinitialise toutes les stats (admin)
     */
    public void reset() {
        level = 0;
        experience = 0;
        freePoints = 0;
        bonusPoints = 0;
        for (StatType stat : StatType.values()) {
            statPoints.put(stat, 0);
        }
    }
    
    /**
     * Obtient une représentation textuelle des stats
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== STATISTIQUES ONE PIECE =====\n");
        sb.append(String.format("Niveau: %d/%d (%s)\n", level, MAX_LEVEL, getZone()));
        sb.append(String.format("Points disponibles: %d\n\n", freePoints));
        
        for (StatType stat : StatType.values()) {
            int points = statPoints.get(stat);
            String upgradeText = stat.isManuallyUpgradeable() ? "" : " -Non améliorable-";
            sb.append(String.format("%s %s: %d/%d%s (%s)\n",
                    stat.getIcon(),
                    stat.getDisplayName(),
                    points,
                    stat.getMaxPoints(),
                    upgradeText,
                    stat.getEffectDescription(points)));
        }
        
        sb.append(String.format("\nStats totales: %d/%d (Ne prend pas en compte l'apnée)\n",
                getTotalStats(), MAX_TOTAL_STATS));
        sb.append(String.format("Stats bonus: %d/%d (Points endgame)\n",
                bonusPoints, BONUS_STATS_CAP));
        
        return sb.toString();
    }
}
