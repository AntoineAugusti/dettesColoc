DROP TABLE IF EXISTS `organisateurs`;

CREATE TABLE `organisateurs` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `idAdherent` mediumint,
  `login` varchar(255) default NULL,
  `motdepasse` varchar(255),
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `nom` TEXT default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `permissions_organisateurs`;

CREATE TABLE `permissions_organisateurs` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `id_organisateur` mediumint default NULL,
  `id_permission` mediumint default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;