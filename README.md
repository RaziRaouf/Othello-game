---


# 📝 Table des Matières

- [Introduction](#Introduction)
- [Description du jeu](#Description_du_jeu)
- [Méthodologie](#Méthodologie)
- [Implémentation](#Implémentation)
- [Bilan](#Bilan)
- [Lancement du jeu](#built_using)
- [Auteurs](#authors)
- [Remerciements](#acknowledgement)
- [Contact](#contact)

# 🧐 Introduction 

Le projet "Jeu d’Othello avec Intelligence Artificielle" a pour objectif de développer un jeu
d’Othello fonctionnel intégrant plusieurs niveaux d’IA. L’algorithme Minimax et l’élagage
alpha-beta seront utilisés pour permettre aux IA de jouer de manière stratégique et
d’améliorer leurs performances en fonction de la profondeur de recherche et fonction
d’évaluation. Ce projet vise à explorer les concepts clés de l’intelligence artificielle appliquée aux jeux de stratégie, en particulier la recherche d’arbres de jeu, l’évaluation
d’états et l’optimisation des algorithmes.

# 🚀 Description du jeu

## Présentation du jeu
Othello, également connu sous le nom de Reversi, est un jeu de stratégie à deux joueurs
se jouant sur un plateau de 8 cases par 8. Chaque joueur possède 16 pions de sa couleur
(blancs ou noirs) et le but du jeu est de retourner le plus de pions de l’adversaire possible.
Les pions sont retournés en les encadrant entre deux pions de sa propre couleur déjà placés
sur le plateau. Le joueur qui a le plus de pions retournés à la fin de la partie gagne.
## Règles du jeu
Le jeu se déroule sur un plateau de 8 cases par 8, représenté par un quadrillage. Chaque
joueur commence avec 16 pions de sa couleur (blancs ou noirs), placés au début de la
partie sur les quatre cases centrales du plateau, deux pions de chaque couleur par case.
Les joueurs alternent leurs tours, plaçant un pion de leur couleur sur une case vide du
plateau. Un pion ne peut être placé que sur une case vide adjacente à au moins un pion
adverse.
L’objectif principal du jeu est de retourner les pions adverses. Pour retourner un pion
adverse, un pion doit être placé de manière à encadrer une ligne de pions adverses entre
deux pions de sa propre couleur déjà présents sur le plateau.
Un joueur peut placer plusieurs pions et retourner plusieurs lignes de pions adverses
en un seul tour, à condition que les placements respectent les règles énoncées ci-dessus.
Un tour se termine lorsque le joueur ne peut plus placer de pion ou qu’il choisit de passer
son tour.
## Déroulement du jeu
### Placement des pions initiaux
Au début de la partie, les quatre cases centrales
du plateau sont occupées par deux pions de chaque couleur, les pions blancs en
diagonale et les pions noirs en diagonale opposée.
### Tours des joueurs
Les joueurs alternent leurs tours, en plaçant un pion de leur
couleur sur une case vide du plateau. Un pion peut être placé sur n’importe quelle
case vide à condition qu’il soit possible de retourner au moins un pion adverse.

### Retournement des pions
Pour retourner des pions adverses, un pion doit être
placé de manière à encadrer une ligne de pions adverses entre deux pions de sa
propre couleur déjà placés sur le plateau. Tous les pions adverses de cette ligne
sont alors retournés et deviennent des pions de la couleur du joueur qui a effectué
le placement.
### Fin du tour
Un tour se termine lorsque le joueur a placé un pion ou lorsqu’il n’a
plus de coups possibles. Dans ce dernier cas, le tour passe au joueur suivant.
| ![Image 1](https://github.com/RaziRaouf/Othello-game/blob/main/Capture%20d'%C3%A9cran%202024-04-29%20134232.png) | ![Image 2](https://github.com/RaziRaouf/Othello-game/blob/main/Capture%20d'%C3%A9cran%202024-04-29%20134314.png) | ![Image 3](https://github.com/RaziRaouf/Othello-game/blob/main/Capture%20d'%C3%A9cran%202024-04-29%20134352.png) |
|:---------------------------------------------------------------------------------------:|
|Déroulement du jeu|

## Fin de partie
La partie se termine dans deux cas :
### Plus de case vide sur le plateau:
Si toutes les cases du plateau sont occupées
par des pions, la partie se termine. Dans ce cas, le joueur qui a le plus de pions
retournés gagne la partie.
### Un joueur ne peut plus placer de pion
Si un joueur n’a plus de coups possibles
(il n’y a aucune case vide sur le plateau où il peut placer un pion et retourner des
pions adverses), son tour est terminé et le jeu continue avec le joueur suivant. Si, à
son tour, le joueur suivant ne peut également placer aucun pion, la partie se termine.
Dans ce cas, c’est le joueur qui a le plus de pions retournés qui gagne la partie.
Exemples de fins de partie avant que la grille ne soit complètement remplie:
### Vecchi 13 – 51 Nicolas (World Othello Championship 2017, Ghent)
Dans cette partie, les blancs ont abandonné après 32 coups, alors qu’il restait encore
quelques cases vides sur le plateau. La raison en est que les noirs avaient une
position écrasante et qu’il était clair que les blancs ne pourraient pas revenir.
### Hassan 3 – 17 Verstuyft J. (European Grand Prix Ghent 2017)
Dans cette partie, les noirs ont abandonné après 20 coups, bien qu’il restait encore de nombreuses
cases vides sur le plateau. La raison en est que les blancs avaient une position
dominante et qu’il était clair que les noirs ne pourraient pas inverser le résultat.

### Vlasáková 1 – 63 Schotte (European Grand Prix Prague 2011)
 Cette partie est entrée dans le livre des records du Guinness World Records pour le plus grand écart de score dans une partie d’Othello officielle. Les noirs (Schotte) ont remporté la partie par un score de 63 à 1, malgré le fait que les blancs (Vlasáková) aient commencé la partie en jouant en premier. Cela montre à quel point un joueur peut
être désavantagé s’il ne place pas ses pions stratégiquement.

### Motivation du choix du jeu
Le jeu d’Othello a été choisi pour ce projet pour plusieurs raisons:
• Simplicité des règles: Les règles du jeu d’Othello sont relativement simples à
comprendre et à implémenter, ce qui en fait un bon choix pour un premier projet
d’intelligence artificielle.
• Profondeur stratégique: Malgré sa simplicité, le jeu d’Othello offre une grande
profondeur stratégique, ce qui le rend intéressant pour l’exploration de différentes
techniques d’IA.
• Existence de solutions optimales: Le jeu d’Othello est un jeu résolu, ce qui
signifie qu’il existe des stratégies optimales pour chaque joueur. Cela permet de
comparer les performances des différentes IA implémentées.

## 🎈 Méthodologie
### Analyse du jeu d’Othello
Avant de se lancer dans le développement, il était crucial de comprendre en profondeur
les règles du jeu d’Othello. Cela impliquait de maîtriser les concepts clés tels que :

#### La structure du plateau de jeu et la disposition initiale des pions
#### Les mouvements possibles des pions et les conditions de retournement
#### Les règles de victoire et de fin de partie
Cette compréhension approfondie du jeu a permis de définir clairement les objectifs à
atteindre lors de la modélisation et du développement des IA.
### Modélisation du problème
La modélisation du problème d’Othello consistait à représenter les différents éléments du
jeu sous forme de structures de données adaptées. Les choix effectués incluent :
#### Représentation du plateau de jeu par un tableau à deux dimensions, permettant un
accès facile à chaque case et une manipulation efficace des positions des pions.
#### Création d’objets "pion" dotés d’attributs tels que la couleur et la position, facilitant
le suivi de l’état des pions sur le plateau.
#### Implémentation d’une liste de coups possibles pour chaque joueur, permettant d’explorer
les options stratégiques disponibles à chaque tour.
Cette modélisation rigoureuse a jeté les bases d’une implémentation claire et efficiente
du jeu et des algorithmes d’IA.
### Choix des algorithmes d’IA
La sélection d’algorithmes d’IA appropriés était essentielle pour doter les IA de capacités de prise de décision stratégiques. Deux algorithmes ont été retenus pour leur efficacité et leur complémentarité :
#### Algorithme Minimax: Cet algorithme permet d’explorer de manière récursive
l’arbre des coups possibles, en évaluant chaque coup en fonction du nombre de pions
retournés. Il permet de sélectionner le coup qui maximise l’avantage du joueur.
#### Élagage alpha-beta: Cette optimisation du Minimax permet de réduire consid-
érablement le nombre de nœuds explorés dans l’arbre de recherche, améliorant ainsi
l’efficacité du calcul.
La combinaison de ces deux algorithmes a permis aux IA de prendre des décisions
judicieuses en tenant compte de plusieurs niveaux de profondeur et en réduisant le temps
de calcul nécessaire.

#### Implémentation des algorithmes et structure du code
Le développement des IA a été réalisé en utilisant le langage de programmation Java.
Le code est organisé de manière structurée et modulaire, réparti en quatre packages
principaux :
#### othelloGame
 Ce package contient les classes implémentant la logique du jeu d’Othello, telles que les classes OthelloPlayer, OthelloGame et OthelloBoard. Ces classes gèrent les règles du jeu, l’état du plateau et les interactions entre les joueurs.
#### GUI
Ce package regroupe les classes implémentant l’interface graphique du jeu,
notamment la classe OthelloFrame. Cette classe permet d’afficher le plateau de jeu,
les pions et les informations de jeu aux utilisateurs.
#### IA
Ce package contient les classes implémentant les différentes IA du jeu, telles
que FacileIA, MoyenIA, HardIA, FlipAI, StabiltyAI et ProAI. Chaque classe IA
définit une stratégie et une implémentation de l’algorithme Minimax avec élagage
alpha-beta, ce qui permet aux IA de jouer avec des niveaux de difficulté croissants.
#### TestIA:
Ce package contient la classe TestIA dédiée au test des différentes IA du
jeu. Cette classe permet d’exécuter des parties de test et d’évaluer les performances
des IA en termes de taux de victoire et de score moyen.
Cette organisation claire du code facilite la compréhension, la maintenance et l’évolution future du projet.
### Gestion des erreurs et commentaires
Afin de garantir la robustesse du code, une gestion des erreurs a été mise en place pour
détecter et gérer les situations inattendues, telles que les coups invalides ou les erreurs de
logique. Cela permet d’éviter les plantages du programme et d’offrir une expérience de
jeu fluide aux utilisateurs.
De plus, le code est abondamment commenté pour améliorer sa lisibilité et sa compréhension. Les commentaires décrivent la fonction de chaque bloc de code, les variables
utilisées et les choix algorithmiques effectués. Cela facilite la maintenance du code et
permet à d’autres développeurs de comprendre et de modifier le projet plus facilement.
En conclusion, la méthodologie rigoureuse adoptée dans ce projet a permis de développer un jeu d’Othello fonctionnel avec des IA performantes. La modélisation du problème,
le choix des algorithmes, l’implémentation structurée du code, la gestion des erreurs et
les commentaires abondants ont contribué à la qualité et à la durabilité du projet.

## 🛠️ Implémentation
### Choix du langage
Le langage Java a été choisi pour le développement du projet en raison de sa robustesse,
de sa portabilité et de sa richesse de bibliothèques logicielles. Il offre également un bon équilibre entre performance et facilité d’utilisation.
### Algorithme Minimax et Élagage Alpha-Beta
L’algorithme Minimax et l’élagage alpha-beta sont des techniques fondamentales de l’intelligence artificielle utilisées pour sélectionner le meilleur coup possible dans un jeu à deux joueurs
comme Othello.
#### L’algorithme Minimax
L’algorithme Minimax fonctionne par une exploration récursive de l’arbre des jeux pos￾sibles. À chaque niveau de l’arbre, l’algorithme évalue chaque état du jeu (configuration du plateau) en utilisant une fonction d’évaluation heuristique. Cette fonction attribue un score à chaque état en fonction de sa position avantageuse ou désavantageuse pour le joueur.
L’algorithme sélectionne ensuite le coup qui maximise le score du joueur maximiseur
(et minimise le score du joueur minimiseur) en explorant récursivement les sous-arbres
de chaque coup possible. Ce processus se poursuit jusqu’à ce qu’il n’y ait plus de coups
possibles à explorer dans la branche de l’arbre.
#### L’élagage alpha-beta
L’exploration exhaustive de l’arbre des jeux peut devenir très gourmande en calcul, surtout pour des jeux complexes comme Othello. L’élagage alpha-beta est une optimisation de l’algorithme Minimax qui permet de réduire considérablement le nombre de nœuds ex￾plorés dans l’arbre de recherche.
L’élagage alpha-beta utilise deux valeurs, alpha et beta, pour élaguer les branches de
l’arbre qui ne peuvent pas conduire à un meilleur résultat. Alpha représente le meilleur
score minimum que le joueur maximiseur peut garantir, et beta représente le meilleur
score maximum que le joueur minimiseur peut forcer.
En comparant alpha et beta pendant la recherche, l’algorithme peut éviter d’explorer
des branches entières si on sait qu’elles ne peuvent pas influencer le résultat final. Cela permet de réduire considérablement le temps de calcul nécessaire pour trouver le meilleur coup possible.
L’implémentation de ces algorithmes en Java utilise des techniques de programmation
récursive pour explorer l’arbre de jeu et des fonctions d’évaluation pour attribuer un score à chaque état du jeu. La fonction d’évaluation prend en compte des facteurs stratégiques tels que le nombre de pions retournés, la position des pions sur le plateau et le nombre de coups possibles.
L’utilisation de l’algorithme Minimax avec élagage alpha-beta permet aux IA de jouer
de manière stratégique et de prendre des décisions judicieuses en fonction de la situation du jeu.
### Structure du code
Le code du projet est organisé en quatre packages:
#### othelloGame
Ce package contient les classes implémentant la logique du jeu d’Othello, notamment les classes OthelloPlayer, OthelloGame et OthelloBoard.
#### GUI
Le projet dispose d’une interface graphique développée en Java Swing. Cette
interface permet à l’utilisateur de jouer contre l’IA en sélectionnant la difficulté
souhaitée. Elle affiche également l’état du jeu, le score de chaque joueur et le
gagnant à la fin de la partie.
#### AI
le package ou on a implementé plusieurs niveaux de difficulté d’IA a travers
ces classes:
##### -FacileIA
Utilise une fonction d’évaluation simple qui calcule la différence
entre le nombre de pions du joueur et le nombre de pions de l’adversaire.
##### -MoyenIA
Utilise une fonction d’évaluation qui prend en compte le nombre
de coups possibles pour chaque joueur.
##### -HardIA
Utilise une fonction d’évaluation qui prend en compte la position des
pions sur le plateau, en favorisant les pions situés sur les bords du plateau.
##### -FlipAI
Utilise une fonction d’évaluation qui prend en compte le nombre de
pions retournés lors du coup actuel.
##### -StabiltyAI
Utilise une fonction d’évaluation qui prend en compte la stabilité
des pions sur le plateau, en favorisant les pions qui sont moins susceptibles
d’être retournés par l’adversaire.
##### -ProAI
Utilise une fonction d’évaluation complexe qui prend en compte plusieurs
facteurs, tels que la position des pions, le nombre de coups possibles et la sta￾bilité des pions.
#### Tests
ce package contient La classe TestIA qui permet de tester les différentes
IA du jeu en jouant des parties entre elles et en analysant les résultats.
Les tests portent sur le nombre de parties gagnées par chaque IA pour différentes profondeurs
de recherche de l’algorithme Minimax. Les résultats des tests permettent d’observer
l’amélioration des performances des IA en fonction de la profondeur de recherche.
Les IA plus complexes, comme ProAI, obtiennent de meilleurs résultats que les IA
plus simples, mais nécessitent également une plus grande puissance de calcul.
### interface graphique
Cette interface graphique représente une version du jeu Othello, également connu
sous le nom de Reversi, avec un adversaire informatique avec des difficultés variantes.
Voici une brève description de ses fonctionnalités principales :
##### Plateau de Jeu Interactif
Le plateau de jeu est représenté par une grille 8x8 de
boutons interactifs, chaque bouton correspondant à une case du plateau. Les joueurs
peuvent cliquer sur les boutons pour effectuer leurs mouvements
##### Affichage des Pions
Lorsqu’un joueur effectue un mouvement, les pions correspon￾dants sont affichés sur le plateau de jeu. Les pions blancs et noirs sont représentés par des pièces de couleur blanche et noire, respectivement.
##### Validation des Mouvements : Les mouvements valides sont mis en surbrillance en
gris sur le plateau de jeu, ce qui aide le joueur à identifier les options de mouvement
disponibles à chaque tour.
##### IA pour l’Adversaire
L’adversaire informatique est implémenté avec une IA de
niveau de difficulté avec une profondeur qui sont choisi par l’utilisateur. L’IA choisit
ses mouvements en fonction d’une évaluation simplifiée de la position actuelle sur
le plateau.
##### Comptage des Pions
Le nombre de pions blancs et noirs capturés est affiché en temps réel à côté du plateau de jeu, permettant aux joueurs de suivre l’évolution
du jeu.
##### Gestion du Tour de Jeu
Le jeu alterne automatiquement entre les tours du joueur
humain et de l’adversaire informatique. Lorsque le joueur humain ne peut pas
effectuer de mouvement valide, c’est au tour de l’IA de jouer.

## 🛠️ Prérequis <a name = "prerequisites"></a>

Assurez-vous d'avoir les éléments suivants installés :

- Python 3.7 ou supérieur
- OpenCV
- NumPy
- Matplotlib

## 🏁 Bilan

Ce projet a permis de développer un jeu d’Othello fonctionnel avec une interface
graphique et plusieurs IA de difficulté croissante. L’utilisation de l’algorithme Min￾imax et de l’élagage Alpha-Beta permet aux IA de jouer de manière stratégique.
Les tests effectués montrent l’amélioration des performances des IA en fonction de
la profondeur de recherche et de la complexité de la fonction d’évaluation.
### Résultats des tests
Les tests réalisés ont pour objectif d’évaluer les performances des différentes intel￾ligences artificielles (IA) développées pour le jeu Othello. Chaque IA a été testée
à différentes profondeurs de recherche pour analyser son comportement dans des
conditions variées.
#### Test n°1
Ce test confronte l’IA ProAi à différentes profondeurs de recherche contre d’autres
IA. Chaque IA joue 125 parties contre d’autres IAs, ce qui correspond à 25 parties
par profondeur.
#### – ProAi (profondeur variable) : 91 victoires
#### – StabiltyAi (profondeur variable) : 79 victoires

#### – HardAI (profondeur variable) : 63 victoires


#### - FlipAI (profondeur variable) : 68 victoires

#### – MoyenAI (profondeur variable) : 56 victoires

#### – FacileIA (profondeur variable) : 14 victoires

### Analyse des résultats
On observe que les performances des IA augmentent généralement avec la profondeur
de recherche. Cela est dû au fait que des profondeurs de recherche plus élevées
permettent aux IA d’explorer un plus grand nombre de coups possibles et de choisir
des stratégies plus optimales.
ProAi est l’IA la plus performante, avec un taux de victoire de 91
FacileIA est l’IA la moins performante, avec un taux de victoire de 14
On observe également que la différence de performance entre les IA diminue avec la
profondeur de recherche. Cela est dû au fait que des profondeurs de recherche plus
élevées permettent aux IA de converger vers des stratégies optimales similaires.
## ⛏️ Lancement du Jeu

Le jeu a été développé en Java avec Swing et l’IDE Eclipse.
Pour exécuter le jeu Othello que vous avez développé, suivez ces étapes simples :
Prérequis:
#### Avoir Java installé sur votre système.
#### Avoir Eclipse IDE installé sur votre système.
Étapes d’exécution:
Importer le projet dans Eclipse:
1. Lancez Eclipse IDE.
2. Allez dans Fichier > Ouvrir > Projets.
3. Sélectionnez le dossier racine de votre projet Othello et cliquez sur Ouvrir.
4. Attendez que le projet soit importé dans Eclipse.
Exécuter le jeu:
1. Dans la vue Explorateur de paquets, repérez la classe principale de votre jeu
(par exemple, OthelloMain.java).
2. Faites un clic droit sur la classe principale et sélectionnez Exécuter ou Exécuter
en tant que.
3. Dans la fenêtre de dialogue qui apparaît, assurez-vous que l’option Lancer
l’application est sélectionnée.
4. Cliquez sur OK.

## ⛏️ Construit avec <a name = "built_using"></a>

- Java - Langage de programmation
- Swing - bibliothèque java

## ✍️ Auteurs

Mohamed Raouf Razi

## 📞 Contact <a name = "contact"></a>

Si vous avez des questions, des suggestions ou souhaitez simplement entrer en contact, voici comment me joindre :

- Email : mohamedraoufrazi@gmail.com
- LinkedIn : [Votre LinkedIn](https://www.linkedin.com/in/mohamed-raouf-razi-81368922b/)
- GitHub : [Votre GitHub](https://github.com/RaziRaouf)

N'hésitez pas à me contacter !
