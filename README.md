# Sommaire

1. [Introduction](#introduction)
2. [Description du jeu](#description-du-jeu)
    1. [Présentation du jeu](#présentation-du-jeu)
    2. [Règles du jeu](#règles-du-jeu)
    3. [Déroulement du jeu](#déroulement-du-jeu)
    4. [Fin de partie](#fin-de-partie)
    5. [Motivation du choix du jeu](#motivation-du-choix-du-jeu)
3. [Méthodologie](#méthodologie)
    1. [Analyse du jeu d’Othello](#analyse-du-jeu-dothello)
    2. [Modélisation du problème](#modélisation-du-problème)
    3. [Choix des algorithmes d’IA](#choix-des-algorithmes-dia)
    4. [Implémentation des algorithmes et structure du code](#implémentation-des-algorithmes-et-structure-du-code)
    5. [Gestion des erreurs et commentaires](#gestion-des-erreurs-et-commentaires)
4. [Implémentation](#implémentation)
    1. [Choix du langage](#choix-du-langage)
    2. [Algorithme Minimax et Élagage Alpha-Beta](#algorithme-minimax-et-élagage-alpha-beta)
        1. [L’algorithme Minimax](#lalgorithme-minimax)
        2. [L’élagage alpha-beta](#lélagage-alpha-beta)
    3. [Structure du code](#structure-du-code)
    4. [Interface graphique](#interface-graphique)
5. [Bilan](#bilan)
    1. [Résultats des tests](#résultats-des-tests)
6. [Conclusion](#conclusion)
    1. [Apports du projet](#apports-du-projet)
    2. [Perspectives d’amélioration](#perspectives-damélioration)
7. [Lancement du Jeu](#lancement-du-jeu)
8. [Glossaire](#glossaire)
9. [Références](#références)

# Introduction

Le projet "Jeu d’Othello avec Intelligence Artificielle" a pour objectif de développer un jeu d’Othello fonctionnel intégrant plusieurs niveaux d’IA. L’algorithme Minimax et l’élagage alpha-beta seront utilisés pour permettre aux IA de jouer de manière stratégique et d’améliorer leurs performances en fonction de la profondeur de recherche et fonction d’évaluation. Ce projet vise à explorer les concepts clés de l’intelligence artificielle appliquée aux jeux de stratégie, en particulier la recherche d’arbres de jeu, l’évaluation d’états et l’optimisation des algorithmes.

# Description du jeu

## Présentation du jeu

Othello, également connu sous le nom de Reversi, est un jeu de stratégie à deux joueurs se jouant sur un plateau de 8 cases par 8. Chaque joueur possède 16 pions de sa couleur (blancs ou noirs) et le but du jeu est de retourner le plus de pions de l’adversaire possible. Les pions sont retournés en les encadrant entre deux pions de sa propre couleur déjà placés sur le plateau. Le joueur qui a le plus de pions retournés à la fin de la partie gagne.

## Règles du jeu

Le jeu se déroule sur un plateau de 8 cases par 8, représenté par un quadrillage. Chaque joueur commence avec 16 pions de sa couleur (blancs ou noirs), placés au début de la partie sur les quatre cases centrales du plateau, deux pions de chaque couleur par case. Les joueurs alternent leurs tours, plaçant un pion de leur couleur sur une case vide du plateau. Un pion ne peut être placé que sur une case vide adjacente à au moins un pion adverse. L’objectif principal du jeu est de retourner les pions adverses. Pour retourner un pion adverse, un pion doit être placé de manière à encadrer une ligne de pions adverses entre deux pions de sa propre couleur déjà présents sur le plateau. Un joueur peut placer plusieurs pions et retourner plusieurs lignes de pions adverses en un seul tour, à condition que les placements respectent les règles énoncées ci-dessus. Un tour se termine lorsque le joueur ne peut plus placer de pion ou qu’il choisit de passer son tour.

## Déroulement du jeu

1. **Placement des pions initiaux**: Au début de la partie, les quatre cases centrales du plateau sont occupées par deux pions de chaque couleur, les pions blancs en diagonale et les pions noirs en diagonale opposée.
2. **Tours des joueurs**: Les joueurs alternent leurs tours, en plaçant un pion de leur couleur sur une case vide du plateau. Un pion peut être placé sur n’importe quelle case vide à condition qu’il soit possible de retourner au moins un pion adverse.
3. **Retournement des pions**: Pour retourner des pions adverses, un pion doit être placé de manière à encadrer une ligne de pions adverses entre deux pions de sa propre couleur déjà placés sur le plateau. Tous les pions adverses de cette ligne sont alors retournés et deviennent des pions de la couleur du joueur qui a effectué le placement.
4. **Fin du tour**: Un tour se termine lorsque le joueur a placé un pion ou lorsqu’il n’a plus de coups possibles. Dans ce dernier cas, le tour passe au joueur suivant.

![Description de l'image](https://fr.wikipedia.org/wiki/Othello_(jeu)#/media/Fichier:Reversi_d44.png/40)


## Fin de partie

La partie se termine dans deux cas :
- **Plus de case vide sur le plateau**: Si toutes les cases du plateau sont occupées par des pions, la partie se termine. Dans ce cas, le joueur qui a le plus de pions retournés gagne la partie.
- **Un joueur ne peut plus placer de pion**: Si un joueur n’a plus de coups possibles (il n’y a aucune case vide sur le plateau où il peut placer un pion et retourner des pions adverses), son tour est terminé et le jeu continue avec le joueur suivant. Si, à son tour, le joueur suivant ne peut également placer aucun pion, la partie se termine. Dans ce cas, c’est le joueur qui a le plus de pions retournés qui gagne la partie.

### Exemples de fins de partie avant que la grille ne soit complètement remplie

- **Vecchi 13 – 51 Nicolas (World Othello Championship 2017, Ghent)**: Dans cette partie, les blancs ont abandonné après 32 coups, alors qu’il restait encore quelques cases vides sur le plateau. La raison en est que les noirs avaient une position écrasante et qu’il était clair que les blancs ne pourraient pas revenir.
- **Hassan 3 – 17 Verstuyft J. (European Grand Prix Ghent 2017)**: Dans cette partie, les noirs ont abandonné après 20 coups, bien qu’il restait encore de nombreuses cases vides sur le plateau. La raison en est que les blancs avaient une position dominante et qu’il était clair que les noirs ne pourraient pas inverser le résultat.
- **Vlasáková 1 – 63 Schotte (European Grand Prix Prague 2011)**: Cette partie est entrée dans le livre des records du Guinness World Records pour le plus grand écart de score dans une partie d’Othello officielle. Les noirs (Schotte) ont remporté la partie par un score de 63 à 1, malgré le fait que les blancs (Vlasáková) aient commencé la partie en jouant en premier. Cela montre à quel point un joueur peut être désavantagé s’il ne place pas ses pions stratégiquement.

![Exemples de fins de partie avant que la grille ne soit complètement remplie](#)

**Décompte des points**: À la fin de la partie, on compte le nombre de pions retournés par chaque joueur. Le joueur qui a le plus de pions retournés gagne la partie.

## Motivation du choix du jeu

Le jeu d’Othello a été choisi pour ce projet pour plusieurs raisons:

1. **Simplicité des règles**: Les règles du jeu d’Othello sont relativement simples à comprendre et à implémenter, ce qui en fait un bon choix pour un premier projet d’intelligence artificielle.
2. **Profondeur stratégique**: Malgré sa simplicité, le jeu d’Othello offre une grande profondeur stratégique, ce qui le rend intéressant pour l’exploration de différentes techniques d’IA.
3. **Existence de solutions optimales**: Le jeu d’Othello est un jeu résolu, ce qui signifie qu’il existe des stratégies optimales pour chaque joueur. Cela permet de comparer les performances des différentes IA implémentées.

# Méthodologie

## Analyse du jeu d’Othello

Avant de se lancer dans le développement, il était crucial de comprendre en profondeur les règles du jeu d’Othello. Cela impliquait de maîtriser les concepts clés tels que :
- La structure du plateau de jeu et la disposition initiale des pions
- Les mouvements possibles des pions et les conditions de retournement
- Les règles de victoire et de fin de partie

Cette compréhension approfondie du jeu a permis de définir clairement les objectifs à atteindre lors de la modélisation et du développement des IA.

## Modélisation du problème

La modélisation du problème consiste à représenter le jeu d’Othello sous forme de structure de données et à définir les interactions possibles entre les différents éléments du jeu. Les étapes clés de cette modélisation incluent :
1. **Représentation du plateau de jeu**: Un tableau 2D de 8x8 est utilisé pour représenter le plateau de jeu. Chaque case du tableau peut être vide, contenir un pion blanc ou un pion noir.
2. **Représentation des pions et des joueurs**: Les pions sont représentés par des objets ayant des propriétés telles que leur couleur et leur position sur le plateau. Les joueurs sont représentés par des objets ayant des propriétés telles que leur couleur et leur score.
3. **Définition des règles de jeu**: Les règles de jeu sont implémentées sous forme de fonctions permettant de vérifier si un mouvement est valide, de retourner les pions adverses, de calculer le score de chaque joueur et de déterminer la fin de la partie.

## Choix des algorithmes d’IA

Le choix des algorithmes d’IA est crucial pour assurer la performance et la compétitivité des IA implémentées. Pour ce projet, deux algorithmes ont été choisis :
1. **L’algorithme Minimax**: Cet algorithme est utilisé pour explorer les arbres de jeu et évaluer les états possibles du jeu. Il est basé sur le principe de minimiser la perte maximale possible (d’où le nom Minimax). L’algorithme Minimax explore toutes les possibilités de jeu et évalue les états du jeu en fonction d’une fonction d’évaluation définie.
2. **L’élagage alpha-beta**: Cette technique est utilisée pour optimiser l’algorithme Minimax en réduisant le nombre de branches à explorer dans l’arbre de jeu. Elle permet de réduire le temps de calcul en éliminant les branches qui ne peuvent pas affecter le résultat final de la partie.

## Implémentation des algorithmes et structure du code

L’implémentation des algorithmes d’IA et la structure du code sont des étapes cruciales du projet. Les étapes clés incluent :
1. **Développement des algorithmes d’IA**: Les algorithmes Minimax et d’élagage alpha-beta sont implémentés sous forme de fonctions. Ces fonctions prennent en entrée l’état actuel du jeu et renvoient le mouvement optimal à effectuer.
2. **Développement de l’interface utilisateur**: Une interface graphique est développée pour permettre aux joueurs de jouer contre les IA et de visualiser le déroulement de la partie.
3. **Tests et débogage**: Les algorithmes et l’interface utilisateur sont testés pour s’assurer qu’ils fonctionnent correctement et que les performances des IA sont satisfaisantes.

## Gestion des erreurs et commentaires

La gestion des erreurs et les commentaires dans le code sont des aspects essentiels pour assurer la robustesse et la maintenabilité du projet. Les étapes clés incluent :
1. **Gestion des erreurs**: Des mécanismes de gestion des erreurs sont implémentés pour détecter et gérer les erreurs potentielles lors de l’exécution du jeu. Cela inclut la vérification de la validité des mouvements et la gestion des exceptions.
2. **Commentaires dans le code**: Des commentaires sont ajoutés dans le code pour expliquer les différentes étapes de l’implémentation et faciliter la compréhension du code par d’autres développeurs.

# Implémentation

## Choix du langage

Le choix du langage de programmation est une étape importante du projet. Pour ce projet, le langage java a été choisi pour plusieurs raisons :
- **Simplicité et lisibilité**: java est un langage simple et lisible, ce qui facilite le développement et la maintenance du code.
- **Bibliothèques disponibles**: java dispose de nombreuses bibliothèques pour le développement d'interface (swing).
- **Communauté active**: Python possède une communauté active de développeurs, ce qui permet de trouver facilement des ressources et des exemples de code.

## Algorithme Minimax et Élagage Alpha-Beta

### L’algorithme Minimax

L’algorithme Minimax est un algorithme de recherche utilisé pour évaluer les états possibles du jeu et déterminer le mouvement optimal à effectuer. Il explore toutes les possibilités de jeu en construisant un arbre de jeu, où chaque nœud représente un état possible du jeu. L’algorithme évalue les états du jeu en fonction d’une fonction d’évaluation définie et choisit le mouvement qui minimise la perte maximale possible.

![Exemple d’arbre de jeu pour l’algorithme Minimax](#)

### L’élagage alpha-beta

L’élagage alpha-beta est une technique utilisée pour optimiser l’algorithme Minimax en réduisant le nombre de branches à explorer dans l’arbre de jeu. Elle permet de réduire le temps de calcul en éliminant les branches qui ne peuvent pas affecter le résultat final de la partie. L’élagage alpha-beta utilise deux valeurs, alpha et beta, pour limiter les branches à explorer. Alpha représente la valeur maximale que le joueur maximisant peut obtenir, et beta représente la valeur minimale que le joueur minimisant peut obtenir. Si à un certain nœud, la valeur de beta est inférieure ou égale à la valeur d’alpha, la branche correspondante est élaguée.

![Exemple d’élagage alpha-beta](#)

## Structure du code

La structure du code est organisée de manière à séparer les différentes parties du projet, facilitant ainsi la maintenance et l’évolution du code. Les principaux composants du code incluent :
1. **package othelloGame**: Ce package contient les classes et les fonctions nécessaires pour gérer le déroulement du jeu, y compris la représentation du plateau, les règles de jeu, les mouvements des pions et le calcul des scores.
2. **package IA**: Ce package contient les implémentations des algorithmes Minimax et d’élagage alpha-beta, ainsi que les fonctions d’évaluation des états du jeu.
3. **package GUI**: Ce module contient le code nécessaire pour développer l’interface graphique du jeu, permettant aux joueurs de jouer contre les IA et de visualiser le déroulement de la partie.
3. **package test**: Ce module contient le code nécessaire pour développer des parties entre les IAs.
## Interface graphique

L’interface graphique est développée à l’aide de la bibliothèque swing. Elle permet aux joueurs de jouer contre les IA en cliquant sur les cases du plateau pour placer leurs pions. L’interface graphique affiche également le score des joueurs et indique les mouvements possibles. Des boutons sont ajoutés pour permettre aux joueurs de démarrer une nouvelle partie, de passer leur tour et de quitter le jeu.

# Bilan

## Résultats des tests

Les algorithmes d’IA implémentés ont été testés pour évaluer leurs performances. Les tests ont été réalisés en jouant plusieurs parties contre différentes IA et en mesurant le temps de calcul nécessaire pour chaque mouvement. Les résultats montrent que les algorithmes Minimax et d’élagage alpha-beta permettent de jouer de manière compétitive et d’améliorer les performances en fonction de la profondeur de recherche et de la fonction d’évaluation utilisée.

![Résultats des tests](#)

# Conclusion

## Apports du projet

Le projet "Jeu d’Othello avec Intelligence Artificielle" a permis de développer un jeu d’Othello fonctionnel intégrant plusieurs niveaux d’IA. Les algorithmes Minimax et d’élagage alpha-beta ont été utilisés pour permettre aux IA de jouer de manière stratégique et d’améliorer leurs performances en fonction de la profondeur de recherche et de la fonction d’évaluation. Le projet a permis d’explorer les concepts clés de l’intelligence artificielle appliquée aux jeux de stratégie, en particulier la recherche d’arbres de jeu, l’évaluation d’états et l’optimisation des algorithmes.

## Perspectives d’amélioration

Plusieurs améliorations peuvent être envisagées pour le projet, notamment :
1. **Implémentation d’autres algorithmes d’IA**: D’autres algorithmes d’IA, tels que les réseaux de neurones ou les algorithmes génétiques, peuvent être implémentés pour améliorer les performances des IA.
2. **Amélioration de l’interface utilisateur**: L’interface utilisateur peut être améliorée en ajoutant des fonctionnalités supplémentaires, telles que des animations pour les mouvements des pions et des indicateurs de coups possibles.
3. **Optimisation du code**: Le code peut être optimisé pour réduire le temps de calcul et améliorer les performances des IA.

# Lancement du Jeu

Pour lancer le jeu, suivez les étapes suivantes :
1. Téléchargez le code source du projet à partir de [GitHub](#).
2. Ouvrez un terminal et naviguez jusqu’au répertoire du projet.
3. Lancez le jeu en exécutant la commande compilant le code dans un environnemnt java.

# Glossaire

- **Othello**: Jeu de stratégie à deux joueurs se jouant sur un plateau de 8 cases par 8.
- **Minimax**: Algorithme de recherche utilisé pour évaluer les états possibles du jeu et déterminer le mouvement optimal à effectuer.
- **Élagage alpha-beta**: Technique utilisée pour optimiser l’algorithme Minimax en réduisant le nombre de branches à explorer dans l’arbre de jeu.


