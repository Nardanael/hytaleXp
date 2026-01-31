package com.onepiece.xp.systems;

import com.onepiece.xp.stats.PlayerStats;
import com.onepiece.xp.stats.StatType;

/**
 * Système de gestion de l'XP et des niveaux
 */
public class LevelingSystem {
    
    // Table d'expérience par niveau
    private static final int[] XP_TABLE = generateXPTable();
    
    /**
     * Génère la table d'XP requise pour chaque niveau
     * Progression exponentielle pour rendre les niveaux plus difficiles
     */
    private static int[] generateXPTable() {
        int[] table = new int[PlayerStats.MAX_LEVEL + 1];
        table[0] = 0;
        
        for (int level = 1; level <= PlayerStats.MAX_LEVEL; level++) {
            // Formule exponentielle: XP = base * (1.1 ^ level)
            // Zone East Blue (1-20): plus facile
            // Zone Paradise (21-40): moyen
            // Zone New World (41-60): plus difficile
            double baseXP = 100;
            double multiplier = 1.1;
            
            if (level <= 20) {
                baseXP = 100;
                multiplier = 1.08;
            } else if (level <= 40) {
                baseXP = 150;
                multiplier = 1.10;
            } else {
                baseXP = 200;
                multiplier = 1.12;
            }
            
            table[level] = (int) (baseXP * Math.pow(multiplier, level));
        }
        
        return table;
    }
    
    /**
     * Obtient l'XP requise pour atteindre un niveau spécifique
     */
    public static int getXPForLevel(int level) {
        if (level < 0 || level > PlayerStats.MAX_LEVEL) {
            return 0;
        }
        return XP_TABLE[level];
    }
    
    /**
     * Ajoute de l'expérience à un joueur et gère les montées de niveau
     * @return nombre de niveaux gagnés
     */
    public static int addExperience(PlayerStats stats, int amount) {
        int levelsGained = 0;
        stats.addExperience(amount);
        
        // Vérifier les montées de niveau
        while (stats.getLevel() < PlayerStats.MAX_LEVEL) {
            int nextLevel = stats.getLevel() + 1;
            int requiredXP = getXPForLevel(nextLevel);
            
            if (stats.getExperience() >= requiredXP) {
                stats.addLevel();
                levelsGained++;
                System.out.println("[OnePieceXP] Niveau " + stats.getLevel() + " atteint!");
                
                // Message de zone si changement
                if (stats.getLevel() == 21) {
                    System.out.println("[OnePieceXP] Bienvenue à Paradise!");
                } else if (stats.getLevel() == 41) {
                    System.out.println("[OnePieceXP] Bienvenue dans le New World!");
                }
            } else {
                break;
            }
        }
        
        return levelsGained;
    }
    
    /**
     * Calcule le pourcentage de progression vers le prochain niveau
     */
    public static double getProgressToNextLevel(PlayerStats stats) {
        if (stats.getLevel() >= PlayerStats.MAX_LEVEL) {
            return 100.0;
        }
        
        int currentLevel = stats.getLevel();
        int currentXP = stats.getExperience();
        int currentLevelXP = getXPForLevel(currentLevel);
        int nextLevelXP = getXPForLevel(currentLevel + 1);
        
        if (nextLevelXP == currentLevelXP) {
            return 100.0;
        }
        
        int xpIntoCurrentLevel = currentXP - currentLevelXP;
        int xpNeededForLevel = nextLevelXP - currentLevelXP;
        
        return (xpIntoCurrentLevel / (double) xpNeededForLevel) * 100.0;
    }
    
    /**
     * Obtient l'XP restante pour le prochain niveau
     */
    public static int getXPToNextLevel(PlayerStats stats) {
        if (stats.getLevel() >= PlayerStats.MAX_LEVEL) {
            return 0;
        }
        
        int nextLevelXP = getXPForLevel(stats.getLevel() + 1);
        return Math.max(0, nextLevelXP - stats.getExperience());
    }
    
    /**
     * Sources d'XP possibles dans le jeu
     */
    public enum XPSource {
        MINING(5, 10),          // 5-10 XP par bloc miné
        COMBAT(20, 50),         // 20-50 XP par ennemi tué
        QUESTS(100, 500),       // 100-500 XP par quête
        EXPLORATION(10, 30),    // 10-30 XP pour découverte
        BOSS(500, 2000),        // 500-2000 XP pour boss
        EVENT(200, 1000);       // 200-1000 XP pour events
        
        private final int minXP;
        private final int maxXP;
        
        XPSource(int minXP, int maxXP) {
            this.minXP = minXP;
            this.maxXP = maxXP;
        }
        
        public int getMinXP() { return minXP; }
        public int getMaxXP() { return maxXP; }
        
        /**
         * Génère une quantité d'XP aléatoire dans la fourchette
         */
        public int generateXP() {
            return minXP + (int)(Math.random() * (maxXP - minXP + 1));
        }
    }
}
