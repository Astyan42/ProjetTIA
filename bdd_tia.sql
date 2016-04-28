DROP TABLE IF EXISTS utilisateur;
CREATE TABLE utilisateur (
id_util INT(10) AUTO_INCREMENT , 
 nom VARCHAR(50)  NOT NULL UNIQUE,
 mail VARCHAR(50)  NOT NULL,
 password VARCHAR(50) NOT NULL,
 CONSTRAINT PRIMARY KEY(id_util)
)ENGINE = InnoDB;

INSERT INTO utilisateur VALUES (1,'Pierre','pierre@free.fr','test');
INSERT INTO utilisateur VALUES (2,'Jean','jean@free.fr','test');
INSERT INTO utilisateur VALUES (3,'David','david@free.fr','test');
INSERT INTO utilisateur VALUES (4,'Marc','marc@free.fr','test');
INSERT INTO utilisateur VALUES (5,'Dominique','dominique@free.fr','test');
INSERT INTO utilisateur VALUES (6,'Clement','clement@free.fr','test');
INSERT INTO utilisateur VALUES (7,'Kevin','kevin@free.fr','test');
INSERT INTO utilisateur VALUES (8,'Yann','yann@free.fr','test');
INSERT INTO utilisateur VALUES (9,'Mathias','mathias@free.fr','test');
INSERT INTO utilisateur VALUES (10,'Michel','michel@free.fr','test');
INSERT INTO utilisateur VALUES (11,'pseudo','pseudo@free.fr','password');


DROP TABLE IF EXISTS projet;
CREATE TABLE projet (
id_projet INT(10) AUTO_INCREMENT ,
nom VARCHAR(50) NOT NULL,
id_pere INT(10),
 CONSTRAINT PRIMARY KEY(id_projet)
)ENGINE=InnoDB;

INSERT INTO projet VALUES (0,'/',NULL);
 
DROP TABLE IF EXISTS fichier;
CREATE TABLE fichier (
id_fichier INT(10) AUTO_INCREMENT ,
id_projet INT(10) ,
nom VARCHAR(50)  NOT NULL UNIQUE,
chemin VARCHAR(50)  NOT NULL UNIQUE,
 CONSTRAINT PRIMARY KEY(id_fichier)
)ENGINE=InnoDB;


DROP TABLE IF EXISTS acces_projet;
CREATE TABLE acces_projet (
id_projet INT(10),
id_util INT(50) NOT NULL,
admin BOOLEAN,
lecture BOOLEAN,
CONSTRAINT FOREIGN KEY (id_projet) REFERENCES projet(id_projet),
CONSTRAINT FOREIGN KEY (id_util) REFERENCES utilisateur(id_util),
CONSTRAINT PRIMARY KEY(id_projet,id_util)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS droit_fichier;
CREATE TABLE droit_fichier(
id_util INT(10) NOT NULL,
id_fichier INT(10) NOT NULL,
droit_lecture BOOLEAN,
droit_ecriture BOOLEAN,
administrateur BOOLEAN,
CONSTRAINT PRIMARY KEY(id_util,id_fichier)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS commentaire_persistant;
CREATE TABLE commentaire_persistant(
id_commentaire INT(10) AUTO_INCREMENT,
id_fichier INT(10) NOT NULL,
id_util INT(10) NOT NULL,
contenu VARCHAR(150),
date_commentaire DATE,
CONSTRAINT FOREIGN KEY (id_fichier) REFERENCES fichier(id_fichier),
CONSTRAINT FOREIGN KEY (id_util) REFERENCES utilisateur(id_util),
CONSTRAINT PRIMARY KEY(id_commentaire)
)ENGINE=InnoDB;
