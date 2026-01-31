# Guide de Développement - OnePieceXP Mod

## Architecture du Mod

### Structure des Dossiers

```
OnePieceXP/
├── Common/
│   └── UI/
│       └── Custom/
│           └── Pages/
│               └── onepiece_stats.ui    # Interface utilisateur principale
├── META-INF/
│   └── MANIFEST.MF                      # Métadonnées du mod
├── src/
│   └── com/
│       └── onepiece/
│           └── xp/
│               ├── OnePieceXPMod.java          # Classe principale
│               ├── TestProgram.java            # Programme de test
│               ├── stats/
│               │   ├── StatType.java           # Énumération des statistiques
│               │   └── PlayerStats.java        # Données joueur
│               ├── pages/
│               │   └── OnePieceStatsPage.java  # Gestionnaire UI
│               └── systems/
│                   └── LevelingSystem.java     # Système de niveaux/XP
├── manifest.json                        # Configuration du mod
└── README.md                            # Documentation utilisateur
```

## Classes Principales

### 1. StatType (Énumération)

Définit les 6 statistiques du jeu :

```java
public enum StatType {
    VITALITY,      // ❤️ Points de vie
    RESISTANCE,    // 🛡️ Réduction de dégâts
    ATTACK,        // ⚔️ Bonus de dégâts
    STAMINA,       // ⚡ Endurance physique
    HAKI,          // 🌀 Énergie spirituelle
    RESPIRATION    // 🫧 Apnée (auto-only)
}
```

**Méthodes importantes :**
- `calculateEffect(int points)` : Calcule l'effet en fonction des points
- `getEffectDescription(int points)` : Format l'affichage de l'effet
- `applyEffect(Object player, int points)` : Applique l'effet au joueur

### 2. PlayerStats (Données Joueur)

Stocke toutes les statistiques d'un joueur :

```java
public class PlayerStats {
    private int level;              // Niveau actuel (0-60)
    private int experience;         // XP accumulée
    private int freePoints;         // Points libres à distribuer
    private int bonusPoints;        // Points bonus (endgame)
    private Map<StatType, Integer> statPoints;  // Points par stat
}
```

**Constantes importantes :**
- `MAX_LEVEL = 60` : Niveau maximum
- `FREE_POINTS_PER_LEVEL = 5` : Points libres par niveau
- `AUTO_POINTS_PER_LEVEL = 1` : Points auto par stat par niveau
- `MAX_TOTAL_STATS = 1000` : Total théorique (5 stats × 200)
- `BONUS_STATS_CAP = 400` : Points bonus maximum

**Méthodes importantes :**
- `addLevel()` : Monte d'un niveau et octroie les points
- `allocatePoint(StatType, int)` : Alloue des points libres
- `addBonusPoint(StatType, int)` : Ajoute des points bonus
- `getZone()` : Retourne la zone actuelle (East Blue / Paradise / New World)

### 3. LevelingSystem (Système de Progression)

Gère l'XP et la progression des niveaux :

```java
public class LevelingSystem {
    private static final int[] XP_TABLE;  // XP requise par niveau
}
```

**Méthodes importantes :**
- `getXPForLevel(int level)` : XP requise pour un niveau
- `addExperience(PlayerStats, int)` : Ajoute de l'XP et gère les montées
- `getProgressToNextLevel(PlayerStats)` : % de progression
- `getXPToNextLevel(PlayerStats)` : XP restante

**Sources d'XP :**
- MINING : 5-10 XP
- COMBAT : 20-50 XP
- QUESTS : 100-500 XP
- EXPLORATION : 10-30 XP
- BOSS : 500-2000 XP
- EVENT : 200-1000 XP

### 4. OnePieceStatsPage (UI)

Gère l'interface utilisateur :

```java
public class OnePieceStatsPage {
    public void initialize(Object player);        // Init pour un joueur
    public void updateDisplay();                  // MAJ de l'affichage
    public void onAllocateVitality();            // Callback bouton
    // ... autres callbacks pour chaque stat
}
```

## Formules de Calcul

### ❤️ Vitalité (PV)
```
HP = 100 + (points × 2)
Range: 100 PV (0 points) → 500 PV (200 points)
```

### 🛡️ Résistance (Défense)
```
Réduction% = points × 0.25%
Range: 0% (0 points) → 50% (200 points)
```

### ⚔️ Attaque (Puissance)
```
Bonus% = points × 0.5%
Range: 0% (0 points) → 100% (200 points)
```

### ⚡ Stamina (Endurance)
```
Stamina = points × 2
Range: 0 (0 points) → 400 (200 points)
```

### 🌀 Haki (Énergie Spirituelle)
```
Haki = points × 2
Range: 0 (0 points) → 400 (200 points)
```

### 🫧 Respiration (Apnée)
```
Secondes = points × 2
Range: 0 sec (0 points) → 120 sec (60 points)
Note: AUTO-ONLY, ne peut pas être amélioré manuellement
```

## Progression des Zones

| Niveau | Zone | Description |
|--------|------|-------------|
| 1-20 | East Blue | Mer la plus faible, progression rapide |
| 21-40 | Paradise | Première moitié de Grand Line, difficulté moyenne |
| 41-60 | New World | Deuxième moitié de Grand Line, plus difficile |

## Distribution des Points

### À chaque niveau
- **+5 points libres** que le joueur peut allouer
- **+1 point automatique** dans chaque stat (6 au total)

### Au niveau 60
- **300 points libres** distribués par le joueur
- **300 points automatiques** (60 niveaux × 5 stats × 1 point)
- **Total : 600 points** sur 1000 possibles
- **Gap de 400 points** pour l'endgame

## Intégration avec l'API Hytale

### Points d'Intégration Requis

1. **Événements Joueur**
   ```java
   // À implémenter selon l'API Hytale
   onPlayerJoin(player) -> loadPlayerStats(player.getUUID())
   onPlayerQuit(player) -> savePlayerStats(player.getUUID())
   ```

2. **Application des Effets**
   ```java
   // Vitalité
   player.setMaxHealth(stats.calculateEffect(VITALITY))
   
   // Résistance
   player.setDamageReduction(stats.calculateEffect(RESISTANCE) / 100.0)
   
   // Attaque
   player.setDamageMultiplier(1.0 + stats.calculateEffect(ATTACK) / 100.0)
   
   // Stamina
   player.setMaxStamina(stats.calculateEffect(STAMINA))
   
   // Haki
   player.setMaxHaki(stats.calculateEffect(HAKI))
   
   // Respiration
   player.setMaxBreath(stats.calculateEffect(RESPIRATION))
   ```

3. **Gain d'XP**
   ```java
   onMineBlock() -> LevelingSystem.addExperience(stats, XPSource.MINING.generateXP())
   onKillEntity() -> LevelingSystem.addExperience(stats, XPSource.COMBAT.generateXP())
   onCompleteQuest() -> LevelingSystem.addExperience(stats, XPSource.QUESTS.generateXP())
   ```

4. **Commandes**
   ```
   /opstats                           - Ouvrir le menu stats
   /opaddlevel <joueur> [montant]     - Ajouter des niveaux (admin)
   /opsetlevel <joueur> <niveau>      - Définir le niveau (admin)
   /opresetstats <joueur>             - Reset stats (admin)
   /opbonus <joueur> <stat> <montant> - Ajouter points bonus (admin)
   ```

## Tests

### Exécuter les Tests

```bash
cd OnePieceXP
mkdir -p bin
javac -d bin src/com/onepiece/xp/TestProgram.java \
              src/com/onepiece/xp/stats/*.java \
              src/com/onepiece/xp/systems/*.java
java -cp bin com.onepiece.xp.TestProgram
```

### Tests Couverts

1. ✅ État initial (niveau 0)
2. ✅ Montée au niveau 1
3. ✅ Allocation de points libres
4. ✅ Progression jusqu'au niveau 20 (East Blue)
5. ✅ Passage à Paradise (niveau 21)
6. ✅ Progression jusqu'au niveau 60 (New World)
7. ✅ Vérification du total de 600 points
8. ✅ Système d'XP
9. ✅ Tentative d'amélioration manuelle de Respiration (doit échouer)
10. ✅ Formules de calcul des effets

## Extension Future

### Points Bonus (Endgame)

Pour obtenir les 400 points manquants :
- Quêtes épiques spéciales
- Donjons de haut niveau
- World Boss
- Events saisonniers
- Achievements spéciaux

### Système de Réinitialisation (Respec)

Possibilité d'ajouter :
```java
public boolean respecStats(PlayerStats stats, int cost) {
    // Réinitialiser tous les points libres alloués
    // Garder les points automatiques
    // Coût en XP ou monnaie in-game
}
```

### Sauvegarde des Données

Recommandation : Utiliser JSON ou SQLite
```json
{
  "playerId": "uuid",
  "level": 60,
  "experience": 179519,
  "freePoints": 0,
  "bonusPoints": 150,
  "stats": {
    "VITALITY": 200,
    "RESISTANCE": 150,
    "ATTACK": 200,
    "STAMINA": 50,
    "HAKI": 150,
    "RESPIRATION": 60
  }
}
```

## Notes Importantes

1. **Respiration est spéciale** : Elle ne peut être améliorée que via les points automatiques
2. **Le gap de 400 points est intentionnel** : Force la spécialisation et donne un objectif endgame
3. **Les formules sont simples** : Faciles à comprendre et équilibrer
4. **Zones marines One Piece** : East Blue → Paradise → New World

## Licence

MIT - Libre d'utilisation et de modification
