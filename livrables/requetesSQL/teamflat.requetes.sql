-- Vérification des permissions de l'organisatrice "Cheyenne"
SELECT r.nom
FROM roles r
JOIN roles_organisateurs ro ON ro.id_role = r.id
JOIN organisateurs o ON o.id = ro.id_organisateur
WHERE o.login =  'Cheyenne';

-- Vérification du mot de passe de l'organisatrice "Cheyenne"
SELECT * FROM organisateurs WHERE login = 'Cheyenne' AND motdepasse = 'JYN95PHY4GV';

-- Création d'un nouvel organisateur
INSERT INTO organisateurs (idAdherent, login, motdepasse) VALUES (341, "Grosbatise", "QFF00TLS3XE");
INSERT INTO roles_organisateurs (id_organisateur, id_role) VALUES (101, 1);
INSERT INTO roles_organisateurs (id_organisateur, id_role) VALUES (101, 2);
INSERT INTO roles_organisateurs (id_organisateur, id_role) VALUES (101, 3);

-- Vérification des rôles dirigés par le "Président"
SELECT r2.nom
FROM roles r2
JOIN dirige_role d ON r2.id = d.id_role_dirige
JOIN roles r ON r.id = d.id_role_dirigeant
WHERE r.nom = 'president';

-- Vérification des organisateurs dirigés par le "Président"
SELECT ro.id_organisateur, o.login, r.nom
FROM organisateurs o
JOIN roles_organisateurs ro ON ro.id_organisateur = o.id
JOIN roles r ON ro.id_role = r.id
WHERE ro.id_role IN (
	SELECT r2.id
	FROM roles r2
	JOIN dirige_role d ON r2.id = d.id_role_dirige
	JOIN roles r ON r.id = d.id_role_dirigeant
	WHERE r.nom = 'president'
)
ORDER BY ro.id_organisateur ASC;