-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           5.7.33 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour gestion-etudiant-db
CREATE DATABASE IF NOT EXISTS `gestion-etudiant-db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gestion-etudiant-db`;

-- Listage de la structure de la table gestion-etudiant-db. authorities
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table gestion-etudiant-db.authorities : ~4 rows (environ)
DELETE FROM `authorities`;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`username`, `authority`) VALUES
	('djime', 'ROLE_SUPERVISOR'),
	('supervisor', 'ROLE_SUPERVISOR'),
	('user', 'ROLE_USER'),
	('user1', 'ROLE_ADMIN');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

-- Listage de la structure de la table gestion-etudiant-db. authority
CREATE TABLE IF NOT EXISTS `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table gestion-etudiant-db.authority : ~0 rows (environ)
DELETE FROM `authority`;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;

-- Listage de la structure de la table gestion-etudiant-db. classe
CREATE TABLE IF NOT EXISTS `classe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `nbre_etudiant` int(11) NOT NULL,
  `niveau` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `nom_filiere` varchar(255) DEFAULT NULL,
  `nom_avec_niveau` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Listage des données de la table gestion-etudiant-db.classe : ~6 rows (environ)
DELETE FROM `classe`;
/*!40000 ALTER TABLE `classe` DISABLE KEYS */;
INSERT INTO `classe` (`id`, `description`, `nbre_etudiant`, `niveau`, `nom`, `nom_filiere`, `nom_avec_niveau`) VALUES
	(1, 'Informatique de Gestion', 4, '1e Annee', 'IG', 'azerty', 'IG(1e Annee)'),
	(2, 'Marketing Communication', 2, '2e Annee', 'MK', 'azerty', 'MK(2e Annee)'),
	(3, 'Informatique de Gestion', 0, '2e Annee', 'IG2', 'azerty', 'IG2(2e Annee)'),
	(5, '', 0, '1e Annee', 'IG1-B', 'test', 'IG1-B(1e Annee)'),
	(6, '', 0, 'Master 1', 'RH', 'azerty', 'RH(Master 1)'),
	(7, '', 0, 'Master 2', 'IG', 'azerty', 'IG(Master 2)');
/*!40000 ALTER TABLE `classe` ENABLE KEYS */;

-- Listage de la structure de la table gestion-etudiant-db. etudiant
CREATE TABLE IF NOT EXISTS `etudiant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `classe` varchar(255) DEFAULT NULL,
  `date_naissance` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `filiere` varchar(255) DEFAULT NULL,
  `matricule` varchar(255) DEFAULT NULL,
  `nationalite` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `total_mensualite` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Listage des données de la table gestion-etudiant-db.etudiant : ~6 rows (environ)
DELETE FROM `etudiant`;
/*!40000 ALTER TABLE `etudiant` DISABLE KEYS */;
INSERT INTO `etudiant` (`id`, `adresse`, `age`, `classe`, `date_naissance`, `email`, `filiere`, `matricule`, `nationalite`, `nom`, `prenom`, `sexe`, `tel`, `total_mensualite`) VALUES
	(4, 'azerty', 0, 'IG(1e Annee)', '', '', 'azerty', '1535cfbvh', '', 'Diarra', 'Fatoumata', 'Homme', '123456', 0),
	(5, 'azerty', 0, 'MK(2e Annee)', '', '', 'azerty', '1535cfbvh', '', 'Coulibaly', 'Christian', 'Homme', '123456', 0),
	(6, '', 0, 'IG(1e Annee)', '', '', 'azerty', 'azerty', '', 'azerty', 'azerty', 'Homme', '', 200),
	(7, '', 25, 'IG(1e Annee)', '', '', 'azerty', 'qwerty', '', 'qwerty', 'qwerty', 'Homme', '', 350),
	(8, 'azerty', 0, 'MK(2e Annee)', '', '', 'azerty', 'gvdfvdvdfv', '', 'DIALLO', 'azerty', 'Homme', '', 900),
	(9, '', 0, 'IG(1e Annee)', '', '', 'azerty', 'u,u,', '', 'ukj,,u', 'u, u,', 'Homme', '', 0);
/*!40000 ALTER TABLE `etudiant` ENABLE KEYS */;

-- Listage de la structure de la table gestion-etudiant-db. filiere
CREATE TABLE IF NOT EXISTS `filiere` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Listage des données de la table gestion-etudiant-db.filiere : ~3 rows (environ)
DELETE FROM `filiere`;
/*!40000 ALTER TABLE `filiere` DISABLE KEYS */;
INSERT INTO `filiere` (`id`, `description`, `nom`) VALUES
	(2, 'qwerty', 'azerty'),
	(3, 'test', 'test'),
	(4, 'Informatique', 'Informatique');
/*!40000 ALTER TABLE `filiere` ENABLE KEYS */;

-- Listage de la structure de la table gestion-etudiant-db. mensualite
CREATE TABLE IF NOT EXISTS `mensualite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `montant` double NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `matricule_etudiant` varchar(255) DEFAULT NULL,
  `school_year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Listage des données de la table gestion-etudiant-db.mensualite : ~8 rows (environ)
DELETE FROM `mensualite`;
/*!40000 ALTER TABLE `mensualite` DISABLE KEYS */;
INSERT INTO `mensualite` (`id`, `description`, `nom`, `montant`, `date`, `matricule_etudiant`, `school_year`) VALUES
	(1, '1ere Tranche', NULL, 200, 'Fri Apr 01 17:14:37 GMT 2022', 'azerty', NULL),
	(2, '1ere Tranche', NULL, 350, '2022-04-01', 'qwerty', NULL),
	(3, '1ere Tranche', NULL, 750, '2022-04-02', 'gvdfvdvdfv', NULL),
	(5, '2e Tranche', NULL, 250, NULL, NULL, NULL),
	(6, '2e Tranche', NULL, 250, NULL, NULL, NULL),
	(7, '2e Tranche', NULL, 456, NULL, NULL, NULL),
	(8, '2e Tranche', NULL, 200, NULL, NULL, NULL),
	(10, '3e Tranche', NULL, 150, '2022-04-02', 'gvdfvdvdfv', NULL);
/*!40000 ALTER TABLE `mensualite` ENABLE KEYS */;

-- Listage de la structure de la table gestion-etudiant-db. users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table gestion-etudiant-db.users : ~5 rows (environ)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`, `password`, `enabled`, `name`) VALUES
	('djime', '$2a$10$liFnGmDAD8zUlim3FnS3HOmTSXgueSxczSbZ4JtGobDG2YgBbVE9m', 1, NULL),
	('supervisor', '$2a$10$ssmnj4x5HdxH5iV9wI5dne4BfM81NJN6PONq1jdBOEu.9cKnZtPXy', 1, NULL),
	('user', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 1, NULL),
	('user1', '$2a$10$x23AfeAJ4HzNqoOGQRY8z.SjnErOWI7wDUsbLtJqVMCy4v9228EHy', 1, NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
