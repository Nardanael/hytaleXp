package com.onepiece.xp;

import com.onepiece.xp.stats.PlayerStats;
import com.onepiece.xp.stats.StatType;
import com.onepiece.xp.systems.LevelingSystem;

/**
 * Programme de test pour démontrer le système de stats One Piece
 * 
 * Compile et exécute avec:
 * javac -d bin src/com/onepiece/xp/TestProgram.java src/com/onepiece/xp/stats/StatType.java src/com/onepiece/xp/stats/PlayerStats.java src/com/onepiece/xp/systems/LevelingSystem.java
 * java -cp bin com.onepiece.xp.TestProgram
 */
public class TestProgram {
    
    public static void main(String[] args) {
        System.out.println("=== TEST DU SYSTÈME ONE PIECE XP ===\n");
        
        // Créer un nouveau joueur
        PlayerStats player = new PlayerStats();
        
        // Afficher l'état initial (Niveau 0)
        System.out.println("ÉTAT INITIAL (Niveau 0):");
        System.out.println(player);
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Tester la montée au niveau 1
        System.out.println("TEST: Montée au niveau 1");
        player.addLevel();
        System.out.println(player);
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Tester l'allocation de points
        System.out.println("TEST: Allocation de 5 points");
        System.out.println("Allocation 2 points à Vitalité...");
        player.allocatePoint(StatType.VITALITY, 2);
        System.out.println("Allocation 1 point à Attaque...");
        player.allocatePoint(StatType.ATTACK, 1);
        System.out.println("Allocation 2 points à Haki...");
        player.allocatePoint(StatType.HAKI, 2);
        System.out.println(player);
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Tester plusieurs niveaux
        System.out.println("TEST: Montée jusqu'au niveau 20 (fin East Blue)");
        for (int i = 0; i < 19; i++) {
            player.addLevel();
        }
        System.out.println(player);
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Passer à Paradise
        System.out.println("TEST: Montée au niveau 21 (Paradise)");
        player.addLevel();
        System.out.println(player);
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Aller au niveau 60
        System.out.println("TEST: Montée jusqu'au niveau 60 (New World - Max Level)");
        for (int i = 21; i < 60; i++) {
            player.addLevel();
        }
        
        // Allouer tous les points disponibles à Vitalité
        while (player.getFreePoints() > 0) {
            if (!player.allocatePoint(StatType.VITALITY, 1)) {
                // Si Vitalité est pleine, mettre dans Attaque
                if (!player.allocatePoint(StatType.ATTACK, 1)) {
                    // Si Attaque est pleine, mettre dans Résistance
                    if (!player.allocatePoint(StatType.RESISTANCE, 1)) {
                        break;
                    }
                }
            }
        }
        
        System.out.println(player);
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Tester le système d'XP
        System.out.println("TEST: Système d'XP");
        PlayerStats player2 = new PlayerStats();
        System.out.println("XP requise pour niveau 1: " + LevelingSystem.getXPForLevel(1));
        System.out.println("XP requise pour niveau 10: " + LevelingSystem.getXPForLevel(10));
        System.out.println("XP requise pour niveau 20: " + LevelingSystem.getXPForLevel(20));
        System.out.println("XP requise pour niveau 40: " + LevelingSystem.getXPForLevel(40));
        System.out.println("XP requise pour niveau 60: " + LevelingSystem.getXPForLevel(60));
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Tester les sources d'XP
        System.out.println("TEST: Sources d'XP");
        for (LevelingSystem.XPSource source : LevelingSystem.XPSource.values()) {
            System.out.println(String.format("%s: %d - %d XP", 
                source.name(), source.getMinXP(), source.getMaxXP()));
        }
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Tester la tentative d'amélioration de Respiration
        System.out.println("TEST: Tentative d'amélioration manuelle de Respiration (devrait échouer)");
        PlayerStats player3 = new PlayerStats();
        player3.addLevel();
        boolean success = player3.allocatePoint(StatType.RESPIRATION, 1);
        System.out.println("Résultat: " + (success ? "SUCCÈS (ERREUR!)" : "ÉCHEC (Correct - non améliorable)"));
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Afficher les formules de calcul
        System.out.println("TEST: Formules de calcul des effets");
        System.out.println("\nÀ 100 points:");
        for (StatType stat : StatType.values()) {
            System.out.println(String.format("%s %s: %s", 
                stat.getIcon(), stat.getDisplayName(), stat.getEffectDescription(100)));
        }
        
        System.out.println("\nÀ 200 points (maximum):");
        for (StatType stat : StatType.values()) {
            if (stat == StatType.RESPIRATION) continue; // Max 60
            System.out.println(String.format("%s %s: %s", 
                stat.getIcon(), stat.getDisplayName(), stat.getEffectDescription(200)));
        }
        System.out.println(String.format("%s %s: %s (Max 60 points)", 
            StatType.RESPIRATION.getIcon(), 
            StatType.RESPIRATION.getDisplayName(), 
            StatType.RESPIRATION.getEffectDescription(60)));
        
        System.out.println("\n=== FIN DES TESTS ===");
    }
}
