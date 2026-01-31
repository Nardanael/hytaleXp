# One Piece XP Mod pour Hytale

Un mod Hytale implémentant un système de progression et de statistiques inspiré de l'univers One Piece.

## 🌊 Système de Progression

### Niveaux et Zones
- **60 niveaux maximum** répartis en 3 zones maritimes :
  - **Niveaux 1-20** : East Blue
  - **Niveaux 21-40** : Paradise (première moitié de Grand Line)
  - **Niveaux 41-60** : New World (seconde moitié de Grand Line)

### Distribution des Points
À chaque niveau, le joueur reçoit :
- **5 points libres** à distribuer manuellement
- **1 point automatique** dans chaque statistique (6 points auto au total)

**Total au niveau 60** :
- 300 points libres (60 x 5)
- 300 points automatiques (60 x 5 stats)
- **600 points au total**

## 📊 Les Statistiques

### ❤️ Vitalité (PV)
- **Cap maximum** : 200 points
- **Effet** : 1 point = +2 PV
- **Valeurs** : Base 100 PV → Maximum 500 PV (à 200 points)
- **Description** : Augmente vos points de vie

### 🛡️ Résistance (Défense)
- **Cap maximum** : 200 points
- **Effet** : +0.25% de réduction de dégâts par point
- **Valeurs** : 0% → Maximum 50% de réduction (à 200 points)
- **Description** : Réduit les dégâts reçus

### ⚔️ Attaque (Puissance)
- **Cap maximum** : 200 points
- **Effet** : +0.5% de dégâts par point
- **Valeurs** : 0% → Maximum +100% de dégâts (à 200 points)
- **Description** : Augmente vos dégâts infligés

### ⚡ Stamina (Endurance Physique)
- **Cap maximum** : 200 points
- **Effet** : 1 point = +2 Stamina
- **Valeurs** : 0 → Maximum 400 Stamina (à 200 points)
- **Description** : Augmente votre endurance pour les actions physiques

### 🌀 Stamina Haki (Énergie Spirituelle)
- **Cap maximum** : 200 points
- **Effet** : 1 point = +2 Stamina Haki
- **Valeurs** : 0 → Maximum 400 Haki (à 200 points)
- **Description** : Augmente votre énergie spirituelle pour utiliser le Haki

### 🫧 Respiration (Apnée)
- **Cap maximum** : 60 points
- **Non améliorable manuellement** - S'améliore uniquement avec les points automatiques
- **Effet** : 1 point = +2 secondes d'apnée
- **Valeurs** : 0 sec → Maximum +120 secondes (à 60 points, niveau 60)
- **Description** : Augmente le temps que vous pouvez passer sous l'eau

## 🎯 Spécialisation et Endgame

### Le Gap de 400 Points
Au niveau 60, il est **mathématiquement impossible** de maximiser toutes les statistiques :

- **Capacité totale nécessaire** : 1000 points (5 stats x 200)
- **Points acquis au niveau 60** : 600 points (300 libres + 300 auto)
- **Gap** : **400 points manquants**

### Conséquence : Spécialisation Obligatoire
Les joueurs doivent choisir une **spécialisation stricte** :
- Build Tank (Vitalité + Résistance)
- Build DPS (Attaque + Stamina)
- Build Haki (Haki + Attaque)
- Build Équilibré (répartition variée)

### Obtenir les 400 Points Manquants
Les 400 points bonus s'obtiennent exclusivement via le **contenu Endgame** :
- Quêtes spéciales épiques
- Donjons difficiles
- World Boss
- Events saisonniers
- Achievements spéciaux

Cela permet aux joueurs les plus investis de progresser vers la perfection absolue.

## 📝 Exemple d'Affichage In-Game

### Niveau 0 (Début)
```
PV : 0/200 
Armure : 0/200
Attaque : 0/200
Stamina : 0/200
Haki : 0/200
Apnée : 0/60 -Non améliorable-

Stats totales : 0/1000 (Ne prend pas en compte l'apnée)
Stats bonus : 0/400 (Points obtenables via events)
Points disponibles : 0
```

### Niveau 1
```
PV : 1/200 
Armure : 1/200
Attaque : 1/200
Stamina : 1/200
Haki : 1/200
Apnée : 1/60 -Non améliorable-

Stats totales : 5/1000 (Ne prend pas en compte l'apnée)
Stats bonus : 0/400 (Points obtenables via events)
Points disponibles : 5
```

### Niveau 60 (Maximum)
```
PV : 60/200 (minimum automatique)
Armure : 60/200
Attaque : 60/200
Stamina : 60/200
Haki : 60/200
Apnée : 60/60 -Non améliorable- ✓ MAX

Stats totales : 600/1000 (300 auto + 300 libres distribués)
Stats bonus : 0/400 (À obtenir via endgame)
Points disponibles : 0
```

## 🎮 Utilisation

### Interface
- Ouvrir le menu des statistiques avec la touche définie
- Cliquer sur "+ Allouer" à côté d'une stat pour y ajouter 1 point
- Les points automatiques sont ajoutés instantanément à chaque niveau

### Stratégie Recommandée
1. **Début de jeu (Niveaux 1-20)** : Équilibrez vos stats ou commencez à vous spécialiser
2. **Mi-jeu (Niveaux 21-40)** : Affirmez votre spécialisation
3. **Fin de jeu (Niveaux 41-60)** : Maximisez vos stats principales
4. **Endgame** : Complétez vos stats secondaires avec les points bonus

## 🔧 Configuration

Les formules de calcul et valeurs peuvent être modifiées dans les fichiers de configuration du mod.

## 📜 Version

**Version actuelle** : 1.0.0

## 👤 Auteur

Nardanael

## 📄 Licence

MIT
