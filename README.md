# Projet Java Boutique

----
## Description du projet
Le but du projet est de réaliser une application en java qui mette en oeuvre les concepts objets vus en cours.

Le thème retenu est un gestionnaire de boutique. Le sujet n'est pas totalement formalisé car il représente les besoins parfois imprécis d’un client.

voir [le sujet](http://www.ensiie.fr/~bouyer/IPRO/Projet.html)

----
## Utilisation
1. S'assurer d'avoir une connexion internet sans ports bloqués
2. Executer le fichier Boutique.jar

----
## Choix des technologies :

### Bases de données
 Nous avons utilisé une base PostgreSQL hébergée sur un server Amazon aws. Cela nous permet d'avoir un stock centralisé pour la boutique et de profiter des avantages d'une base de données pour le stockage ordonné de données.

De plus, les données sont mises à jour au fur et a mesure de l'utilisation, grâce à aux classe de mise a jour présentes dans le package *DAO* .Ainsi si l'application se termine brusquement les données modifiées seront sauvegardées. Enfin, nous avons fait en sorte que notre base soit protégée contre les injections SQL. Toutefois cela nécessite d'avoir une connexion constante à internet et que les ports ne soient pas bloqués.


### JavaFX
Sans doute l'une des meilleures librairies graphiques en Java, elle comporte toutefois de nombreux défauts et serait déjà considéré comme obsolète. Elle convenait tout de même parfaitement pour implémenter notre IHM.

Nous avons utilisé un système de contrôleurs et de fichiers FXML afin de générer et naviguer entre les différents panneaux de l'application.
Pour cela nous avons implémenté un framework léger permettant de charger facilement les différents panneaux


## thanks
* [markdown-js](https://github.com/evilstreak/markdown-js)
