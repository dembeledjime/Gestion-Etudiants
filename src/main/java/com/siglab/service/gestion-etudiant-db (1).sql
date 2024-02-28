-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 28 Février 2024 à 19:54
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `gestion-etudiant-db`
--

-- --------------------------------------------------------

--
-- Structure de la table `authorities`
--

CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('Djime', 'ROLE_ADMIN'),
('pers1', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `authority`
--

CREATE TABLE IF NOT EXISTS `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE IF NOT EXISTS `classe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `nbre_etudiant` int(11) NOT NULL,
  `niveau` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `nom_filiere` varchar(255) DEFAULT NULL,
  `nom_avec_niveau` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `classe`
--

INSERT INTO `classe` (`id`, `description`, `nbre_etudiant`, `niveau`, `nom`, `nom_filiere`, `nom_avec_niveau`) VALUES
(1, 'Informatique de Gestion', 1, '1e Annee', 'IG', 'azerty', 'IG(1e Annee)'),
(2, 'Marketing Communication', 0, '2e Annee', 'MK', 'azerty', 'MK(2e Annee)'),
(3, 'Informatique de Gestion', 0, '2e Annee', 'IG2', 'azerty', 'IG2(2e Annee)'),
(5, '', 0, '1e Annee', 'IG1-B', 'test', 'IG1-B(1e Annee)'),
(6, '', 0, 'Master 1', 'RH', 'azerty', 'RH(Master 1)'),
(7, '', 0, 'Master 2', 'IG', 'azerty', 'IG(Master 2)');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE IF NOT EXISTS `etudiant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) DEFAULT NULL,
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
  `code_postale` int(11) NOT NULL,
  `ville` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `adresse`, `classe`, `date_naissance`, `email`, `filiere`, `matricule`, `nationalite`, `nom`, `prenom`, `sexe`, `tel`, `total_mensualite`, `code_postale`, `ville`) VALUES
(1, '5 Rue Jean Mancé', 'IG(1e Annee)', '2001-02-12', 'mahamdoukoita2000@gmail.com', 'azerty', 'MAKOI0758', 'Malienne', 'Koita', 'Mahamadou', 'Homme', '0758645815', 2000, 94120, 'Fontenay sous bois');

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

CREATE TABLE IF NOT EXISTS `filiere` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `filiere`
--

INSERT INTO `filiere` (`id`, `description`, `nom`) VALUES
(3, 'test', 'test'),
(4, 'Informatique', 'Informatique');

-- --------------------------------------------------------

--
-- Structure de la table `mensualite`
--

CREATE TABLE IF NOT EXISTS `mensualite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `montant` double NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `matricule_etudiant` varchar(255) DEFAULT NULL,
  `school_year` varchar(255) DEFAULT NULL,
  `annee_scolaire` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mensualite`
--

INSERT INTO `mensualite` (`id`, `description`, `nom`, `montant`, `date`, `matricule_etudiant`, `school_year`, `annee_scolaire`) VALUES
(1, '1ere Tranche', NULL, 2000, '2024-02-22', 'MAKOI0758', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('Djime', '$2a$10$nSODIAPBOrYAwPIDmgP/5O9WpBQkS.5wt1idzWjC7FXkIJuMQ2aO2', 1),
('pers1', '$2a$10$hwzVHa8jqJFRnWh7ppuQTu5Uucou86Z9snApCTNaNczGwUVkFGuGa', 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
