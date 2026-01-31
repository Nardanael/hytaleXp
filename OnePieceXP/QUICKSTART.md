# Guide de Démarrage Rapide - OnePieceXP

## Installation

### Prérequis
- Hytale installé (lorsque disponible)
- Java 17 ou supérieur

### Étapes d'Installation

1. **Télécharger le mod**
   ```bash
   git clone https://github.com/Nardanael/hytaleXp.git
   cd hytaleXp/OnePieceXP
   ```

2. **Tester le système** (optionnel)
   ```bash
   mkdir -p bin
   javac -d bin src/com/onepiece/xp/TestProgram.java \
                 src/com/onepiece/xp/stats/*.java \
                 src/com/onepiece/xp/systems/*.java
   java -cp bin com.onepiece.xp.TestProgram
   ```

3. **Installer dans Hytale**
   - Copier le dossier `OnePieceXP` dans le répertoire des mods Hytale
   - Redémarrer le jeu

## Utilisation In-Game

### Ouvrir le Menu des Stats
- Appuyer sur la touche configurée (par défaut: **K**)
- Ou utiliser la commande `/opstats`

### Allouer des Points
1. Gagner un niveau
2. Ouvrir le menu des stats
3. Cliquer sur **"+ Allouer"** à côté de la stat souhaitée
4. Les points sont alloués immédiatement

### Commandes Disponibles

#### Joueur
- `/opstats` - Ouvrir le menu des statistiques

#### Admin (Niveau 2+)
- `/opaddlevel <joueur> [montant]` - Ajouter des niveaux
- `/opsetlevel <joueur> <niveau>` - Définir le niveau
- `/opresetstats <joueur>` - Réinitialiser les stats
- `/opbonus <joueur> <stat> <montant>` - Ajouter des points bonus

## Gagner de l'XP

### Sources d'XP

| Activité | XP Gagnée |
|----------|-----------|
| Minage | 5-10 XP par bloc |
| Combat | 20-50 XP par ennemi |
| Quêtes | 100-500 XP |
| Exploration | 10-30 XP |
| Boss | 500-2000 XP |
| Events | 200-1000 XP |

### Progression par Zone

| Niveau | Zone | XP Requise (approximatif) |
|--------|------|---------------------------|
| 1 | East Blue | 108 XP |
| 10 | East Blue | 215 XP |
| 20 | East Blue | 466 XP |
| 21 | Paradise | 513 XP |
| 40 | Paradise | 6,788 XP |
| 41 | New World | 7,622 XP |
| 60 | New World | 179,519 XP |

## Stratégies de Build

### Build Tank (PV/Défense)
```
Priorités:
1. Vitalité → 200 points
2. Résistance → 200 points
3. Reste dans Stamina

Résultat au niveau 60:
- 500 PV
- 50% de réduction de dégâts
- Build très résistant pour les combats prolongés
```

### Build DPS (Attaque)
```
Priorités:
1. Attaque → 200 points
2. Vitalité → 100-150 points
3. Reste dans Haki

Résultat au niveau 60:
- +100% de dégâts
- 300-400 PV
- Dégâts maximaux mais plus fragile
```

### Build Haki
```
Priorités:
1. Haki → 200 points
2. Attaque → 150-200 points
3. Reste dans Vitalité

Résultat au niveau 60:
- 400 Haki
- +75-100% de dégâts
- Optimal pour les capacités Haki
```

### Build Équilibré
```
Priorités:
Répartition égale ou selon les besoins

Résultat au niveau 60:
- Toutes les stats à ~120 points
- Polyvalent mais non optimisé
```

## Conseils

### Début de Jeu (Niveaux 1-20)
- **Ne vous précipitez pas** : Testez différentes stats
- **Gardez un équilibre** : Un peu de tout pour comprendre
- **Focus Vitalité** : Au moins 50 points pour la survie

### Mi-Jeu (Niveaux 21-40)
- **Choisissez votre build** : Commencez à vous spécialiser
- **Maximisez une stat** : Amenez une stat principale vers 100+
- **Gardez de la défense** : Au moins 30-40 points en Vitalité ou Résistance

### Fin de Jeu (Niveaux 41-60)
- **Finalisez votre build** : Maximisez vos stats principales
- **Préparez l'endgame** : Identifiez les stats à compléter avec les bonus
- **Optimisez** : Chaque point compte maintenant

### Endgame (Après niveau 60)
- **Farmez les points bonus** : Faites les quêtes épiques et boss
- **Complétez vos stats** : Utilisez les 400 points bonus disponibles
- **Perfectionnez** : Atteignez le build ultime

## FAQ

### Q: Puis-je réinitialiser mes stats ?
**R:** Actuellement non, sauf via la commande admin `/opresetstats`. Un système de réinitialisation payant pourrait être ajouté.

### Q: Pourquoi ne puis-je pas améliorer Respiration ?
**R:** C'est intentionnel. Respiration s'améliore automatiquement de 2 secondes par niveau (60 points = +120 secondes au niveau 60).

### Q: Comment obtenir les 400 points bonus ?
**R:** Via le contenu endgame : quêtes épiques, donjons difficiles, world boss, events saisonniers.

### Q: Quelle est la meilleure stat ?
**R:** Ça dépend de votre style de jeu :
- Tank → Vitalité/Résistance
- DPS → Attaque
- Haki User → Haki/Attaque
- Explorateur → Stamina

### Q: Puis-je maxer toutes les stats ?
**R:** Non au niveau 60. Vous aurez 600 points sur 1000 nécessaires. Les 400 restants viennent de l'endgame.

### Q: Comment sauvegarder mes stats ?
**R:** Les stats sont automatiquement sauvegardées à chaque changement et quand vous quittez le jeu.

## Support

Pour des questions ou des bugs :
- GitHub Issues : https://github.com/Nardanael/hytaleXp/issues
- Auteur : Nardanael

## Liens Utiles

- [README Principal](README.md)
- [Guide Développeur](DEVELOPER_GUIDE.md)
- [Licence](LICENSE) - MIT
