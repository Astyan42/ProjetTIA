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

DROP TABLE IF EXISTS projet;
CREATE TABLE projet (
id_projet INT(10) AUTO_INCREMENT ,
nom VARCHAR(50) NOT NULL,
id_pere INT(10),
 CONSTRAINT PRIMARY KEY(id_projet)
)ENGINE=InnoDB;

INSERT INTO projet VALUES (0,'/',NULL);
INSERT INTO projet VALUES (1,'Projet_prog',NULL);
INSERT INTO projet VALUES (2,'Projet_tia',NULL);
INSERT INTO projet VALUES (3,'Projet_taln',NULL);
INSERT INTO projet VALUES (4,'Projet_sai',NULL);
 
DROP TABLE IF EXISTS fichier;
CREATE TABLE fichier (
id_fichier INT(10) AUTO_INCREMENT ,
id_projet INT(10) ,
nom VARCHAR(50)  NOT NULL UNIQUE,
chemin VARCHAR(50)  NOT NULL UNIQUE,
 CONSTRAINT PRIMARY KEY(id_fichier)
)ENGINE=InnoDB;


INSERT INTO fichier VALUES (1,1,'prog_1.txt','fichiers/prog_1.txt');
INSERT INTO fichier VALUES (2,1,'prog_2.txt','fichiers/prog_2.txt');
INSERT INTO fichier VALUES (3,2,'tia_1.css','fichiers/tia_1.css');
INSERT INTO fichier VALUES (4,2,'tia_1.html','fichiers/tia_1.html');
INSERT INTO fichier VALUES (5,3,'taln_1.txt','fichiers/taln_1.txt');
INSERT INTO fichier VALUES (6,3,'taln_2.txt','fichiers/taln_2.txt');
INSERT INTO fichier VALUES (7,4,'sai_1.c','fichiers/sai_1.c');
INSERT INTO fichier VALUES (8,4,'sai_2.c','fichiers/sai_2.c');
INSERT INTO fichier VALUES (9,null,'Partage_seul_1.txt','fichiers/Partage_seul_1.txt');
INSERT INTO fichier VALUES (10,null,'Partage_seul_2.txt','fichiers/Partage_seul_2.txt');

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


INSERT INTO commentaire_persistant VALUES (1,1,1,'commentaire de pierre sur fichier 1','2016-04-02 16:40:00');
INSERT INTO commentaire_persistant VALUES (2,1,2,'commentaire de jena sur fichier 1','2016-04-02 16:32:22');
INSERT INTO commentaire_persistant VALUES (3,1,3,'commentaire de david sur fichier 1','2016-04-02 16:25:22');
