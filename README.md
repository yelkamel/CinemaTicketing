# CinemaTicketingJAVA
JAVA projet for my projet with a friend


Le projet doit se faire par groupe de 2 étudiants (indiquez précisément vos noms et prénoms). Vous rendrez une archive projetJ2EE_NOMPrenom1_NOMPrenom2.tar.gz (ou .zip) contenant:
• Le fichier de l'application (un EAR ou un WAR): erreurCaisse_typeServeur_technoPresentation_technoMetier.ear (ou .war), vous devez préciser:
- le type du serveur: JBoss, Tomcat, WebSphere, ...
- la technologie utilisée pour la couche Présentation: Servlet, JSP, Struts, JSF,...
- la technologie de la couche Métier: vous devez utiliser JDBC ou EJB3.
Le fichier rendu doit aussi contenir les sources java des classes. Afin d'obtenir un fichier EAR (ou WAR) contenant les sources des classes, il faut suivre les étapes suivantes: Démarrer Eclipse, cliquer droit sur votre projet J2EE et sélectionner «Export», puis choisir «Java EE → EAR file» (ou «Web → WAR file»); enfin, sélectionner le répertoire de destination et cocher «Export sources files». N'oublier pas de déployer le fichier de l'application avec le Manager du Serveur (en dehors d'Eclipse) et de bien tester avant de le rendre.
• Le fichier SQL erreurCaisseDB_typeBaseDonnées.sql permettant de créer la base de données, de créer les tables utilisées par l'application et d'initialiser les données dans les tables. N'oublier pas de préciser le type de la base de données: vous devez utiliser Oracle ou Derby.
• Le rapport du projet (format pdf)
• Les transparents de la soutenance à présenter le 06 Janvier 2014.
Question Bonus (6 points supplémentaire ): vous pouvez rendre en plus, un deuxième fichier erreurCaisse_technoPresentation2_technoMetier2.ear, contenant une deuxième implémentation du projet, utilisant deux technologies différentes du premier projet (une technologie différente pour chaque couche).
1. Rapport:
Le rapport doit comporter au moins les éléments suivants :
1. une introduction qui énonce clairement le contexte du projet, les membres du binôme et le plan du rapport, et qui distingue clairement ce qui a été fait de ce qui n’a pas été fait ;
2. une partie présentant le travail réalisé, les choix faits, les réponses aux questions de l’énoncé. La conception (diagramme Entité/Association de la base de données, diagrammes (UML), ...), le développement et les éventuels algorithmes;
3. une partie présentant un mode d’emploi de votre application: compilation, déploiement et exécution (manuel utilisateur), ainsi qu’une présentation des exemples et des fonctionnalités testés (cahier de tests). N'omettez pas d'y mentionner les commandes à exécuter pour faire fonctionner vos programmes.
4. une conclusion où vous pourrez présenter un bilan de votre travail, votre éventuel avis sur le projet, et de possibles améliorations (en indiquant bien sûr comment vous pourriez les réaliser à partir de l’état actuel de votre projet).
2. Notation:
– Le projet sera noté sur 20 + 6 points de bonus.
– Le projet est à faire en binôme, mais votre note sera individuelle. Le travail doit donc être réparti équitablement entre les deux membres du binôme. De plus, une (rapide) interrogation orale sur votre code et votre rapport aura lieu lors de la soutenance.
– En ce qui concerne le plagiat : il est «évident que vous devez avoir «écrit vous-même votre code et vous devez donc bien sûr être capable de l’expliquer entièrement. Les similarités sont faciles à détecter automatiquement et seront sanctionnées.
– L’aspect opérationnel du code est un point très important : il est nettement préférable de présenter un code aux possibilités restreintes mais qui compile et s’exécute correctement, que de présenter un code aux possibilités soi-disant étendues dans le rapport alors que le code présente des erreurs à l’exécution, voire ne compile pas du tout.
– La qualité et la lisibilité du code et des commentaires compteront dans la note du projet.
3. Spécification des fonctionnalités du site à développer:
L'objectif de ce projet est de concevoir un site de billetterie de cinéma permettant de réserver et payer une place pour une séance d'un film (voir Partie achat).
Cette application permettra aussi de saisir les films et les séances disponibles (voir Partie vente), les fonctionnalités de cette deuxième partie sont accessibles uniquement aux administrateurs du site.
Partie achat:
Lors de sa première connexion au site, un utilisateur (de type client) doit remplir un formulaire d'inscription, en indiquant son nom, son prénom, son adresse électronique et son adresse postale. Le site envoie alors par mail une confirmation contenant un login et un mot de passe d'accès au site.
Le scenario typique d'une session d'un client déjà inscrit est le suivant: L'utilisateur se connecte au site, il navigue parmi les films disponibles, en sélectionne un. Il obtient alors la liste des dates de projection du film sélectionné. Ensuite, pour chacun de ces dates, il peut demander la liste des séances disponibles. Il a alors la possibilité de réserver des places pour la séance choisie (i.e. ajouter une réservation dans le caddie), et de fixer la quantité de billets qu'il souhaite acheter (le site doit vérifier qu'il y a des places disponibles). A tout moment, il doit être possible à un utilisateur d'accéder à son caddie virtuel et ainsi d'obtenir l'état de celui-ci. Dans cette page, il peut modifier la quantité de billets d'une réservation, supprimer une réservation ou bien valider toute la commande (i.e. l'ensemble des réservations ajoutées au caddie). La validation d'une commande entraîne une authentification de l'utilisateur (demande d'un login et d'un mot de passe). Si celle-ci s'est correctement effectuée, l'enregistrement de la commande est réalisée dans la base de données et on demande une confirmation de l'envoi des billets à l'adresse postale indiquée dans la base de données pour ce client, sinon ce dernier peut saisir une adresse d'envoi différente. Enfin, un paiement s'effectue en mode sécurisé (crypté) avec un numéro de carte bancaire, le numéro de la commande et l'identifiant du client. Le client peut choisir de payer plus tard une commande enregistrée (par le site). Un message de confirmation doit être envoyé par mail pour chaque étape (enregistrement d'une commande, paiement d'une commande, envoi des billets d'une commande, ...).
Partie vente:
Les fonctionnalités de cette partie du site sont accessibles uniquement au administrateurs du site, il faut donc mettre en place une page d'authentification pour d'accéder aux pages de cette partie.
La partie vente propose aux utilisateurs (de type administrateur) les fonctionnalités suivantes:
- Saisir (créer) des films et des séances (une séance est définie par une date, un horaire, un titre de film, un nombre de places, un lieu, ...),
- Consulter les tableaux (listes) des films, des séances, des commandes, ... enregistrés en base de données (avec recherche par dates, horaires, mots clés du titre, lieu ...). Ensuite, pour chaque ligne de ces tableaux, l'utilisateur peut accéder à une page permettant d'avoir plus de détails sur l'objet (film, séance, commande, ...) correspondant à la ligne choisie, et/ou le modifier, le supprimer, ...
