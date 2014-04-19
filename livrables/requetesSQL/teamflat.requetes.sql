-- Vérification des permissions de l'organisatrice "Cheyenne"
SELECT p.nom
FROM permissions p
JOIN permissions_organisateurs po ON po.id_permission = p.id
JOIN organisateurs o ON o.id = po.id_organisateur
WHERE o.login =  'Cheyenne'

-- Vérification du mot de passe de l'organisatrice "Cheyenne"
SELECT * FROM organisateurs WHERE login = 'Cheyenne' AND motdepasse = 'JYN95PHY4GV';

-- Création d'un nouvel organisateur
INSERT INTO organisateurs (idAdherent, login, motdepasse) VALUES (341, "Grosbatise", "QFF00TLS3XE");
INSERT INTO permissions_organisateurs (id_organisateur, id_permission) VALUES (101, 1);
INSERT INTO permissions_organisateurs (id_organisateur, id_permission) VALUES (101, 2);
INSERT INTO permissions_organisateurs (id_organisateur, id_permission) VALUES (101, 3);