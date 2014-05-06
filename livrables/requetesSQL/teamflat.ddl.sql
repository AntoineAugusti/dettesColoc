-- ORGANISATEURS
DROP TABLE IF EXISTS `organisateurs`;
CREATE TABLE `organisateurs` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `idAdherent` mediumint,
  `login` varchar(255) default NULL,
  `motdepasse` varchar(255),
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

-- ROLES
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `nom` TEXT default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

-- ROLES_ORGANISATEURS
DROP TABLE IF EXISTS `roles_organisateurs`;
CREATE TABLE `roles_organisateurs` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `id_organisateur` mediumint default NULL,
  `id_role` mediumint default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

-- DIRIGE_ROLE
CREATE TABLE `dirige_role` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `id_role_dirigeant` mediumint default NULL,
  `id_role_dirige` mediumint default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;