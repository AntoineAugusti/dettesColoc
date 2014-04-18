DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `nom` TEXT default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

INSERT INTO `permissions` (`id`,`nom`) VALUES (1,"creerMembre");
INSERT INTO `permissions` (`id`,`nom`) VALUES (2,"supprimerMembre");
INSERT INTO `permissions` (`id`,`nom`) VALUES (3,"creerEvenement");
INSERT INTO `permissions` (`id`,`nom`) VALUES (4,"ajouterMateriel");
INSERT INTO `permissions` (`id`,`nom`) VALUES (5,"reserverMateriel");