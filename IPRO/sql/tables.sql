CREATE TABLE LOT(
    REFLOT VARCHAR(50) PRIMARY KEY NOT NULL,
    REFART VARCHAR(50) NOT NULL,
    QTE INT NOT NULL,
    REDUC FLOAT NOT NULL,
    FOREIGN KEY (REFART) REFERENCES ARTICLE(REFARTICLE) 
);

CREATE TABLE LIGNE(
    IDCOMMANDE INT NOT NULL,
    REFLOT VARCHAR(50),
    REFART VARCHAR(50),
    FOREIGN KEY (IDCOMMANDE) REFERENCES COMMANDE(IDCOMMANDE),
    FOREIGN KEY (REFLOT) REFERENCES LOT(REFLOT),
    FOREIGN KEY (REFART) REFERENCES ARTICLE(REFARTICLE),
    PRIMARY KEY (IDCOMMANDE, REFLOT, REFART)
);

CREATE TABLE BOUTIQUECLIENT(
    IDCLIENT INT NOT NULL,
    REFBOUTIQUE VARCHAR(50) NOT NULL,
    FOREIGN KEY (IDCLIENT) REFERENCES CLIENT(IDCLIENT) ,
    FOREIGN KEY (REFBOUTIQUE) REFERENCES BOUTIQUE(REFBOUTIQUE) ,
    PRIMARY KEY (IDCLIENT, REFBOUTIQUE)
);

CREATE TABLE BOUTIQUEARTICLE(
    REFBOUTIQUE VARCHAR(50) NOT NULL,
    REFARTICLE VARCHAR(50) NOT NULL,
    FOREIGN KEY (REFBOUTIQUE) REFERENCES BOUTIQUE(REFBOUTIQUE),
    FOREIGN KEY (REFARTICLE) REFERENCES ARTICLE(REFARTICLE) ,
    PRIMARY KEY (REFBOUTIQUE, REFARTICLE)
);

CREATE TABLE BOUTIQUECOMMANDE(
    REFBOUTIQUE VARCHAR(50) NOT NULL,
    IDCOMMANDE INT NOT NULL,
    FOREIGN KEY (REFBOUTIQUE) REFERENCES BOUTIQUE(REFBOUTIQUE),
    FOREIGN KEY (IDCOMMANDE) REFERENCES COMMANDE(IDCOMMANDE),
    PRIMARY KEY (REFBOUTIQUE, IDCOMMANDE)    
);

CREATE TABLE COMMANDECLIENT(
    IDCLIENT INT NOT NULL,
    IDCOMMANDE INT NOT NULL,
    FOREIGN KEY (IDCLIENT) REFERENCES CLIENT(IDCLIENT),
    FOREIGN KEY (IDCOMMANDE) REFERENCES COMMANDE(IDCOMMANDE) ,
    PRIMARY KEY (IDCLIENT, IDCOMMANDE)
);
