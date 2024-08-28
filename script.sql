-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : tp_docker_db
-- Généré le : ven. 26 juil. 2024 à 09:26
-- Version du serveur : 8.0.39
-- Version de PHP : 8.2.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `spring_sec_ex2`
--

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

USE `spring_sec_ex2`;

CREATE TABLE `account` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `account`
--

INSERT INTO `account` (`id`, `email`, `firstname`, `lastname`, `password`) VALUES
(1, 'admin@mail.fr', 'Admin', 'Admin', '$2a$10$xiLGdHxSP3lY.mAEyD/wzO.vjrBH3s1M6GF6NVb.FdiHuRP3iyTLm'),
(2, 'user@mail.fr', 'User', 'User', '$2a$10$fUT8wYcOkZRkU/mB8ySUjOWY2HdiJDOj7uTynDbvSTAk3zxP1nAXi'),
(3, 'admin2@mail.fr', 'Admin2', 'Admin2', '$2a$10$yf8KIBS9RUxa6TZkTUC5KuhVz4fSHYjq9AHao.P20gOtej/SoZKGS');

-- --------------------------------------------------------

--
-- Structure de la table `account_role`
--

CREATE TABLE `account_role` (
  `user_id` int NOT NULL,
  `roles_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `account_role`
--

INSERT INTO `account_role` (`user_id`, `roles_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `role`) VALUES
(2, 'ROLE_ADMIN'),
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `task`
--

CREATE TABLE `task` (
  `id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `done` bit(1) NOT NULL,
  `title` varchar(255) NOT NULL,
  `user_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `task`
--

INSERT INTO `task` (`id`, `description`, `done`, `title`, `user_id`) VALUES
(1, 'Générer un script sql', b'0', 'Script SQL', 1),
(2, 'Ajouter des données en DB', b'1', 'Datas', 1),
(3, 'En effet ce serait bien', b'0', 'Faire des trucs bien', 1),
(4, 'Créer un compte avec le role user', b'0', 'Compte user', 1),
(5, 'Ne plus supprimer de volumes', b'0', 'Volume', 1),
(6, 'C\'est du bootstrap mais ça passe', b'1', 'Admirer le front', 1),
(7, 'Créer un second compte administrateur', b'1', 'Compte Administrateur', 3),
(8, 'C\'est plutôt facile', b'0', 'Ajouter des tâches', 3),
(9, ' ', b'0', 'Exporter la DB', 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKq0uja26qgu1atulenwup9rxyr` (`email`);

--
-- Index pour la table `account_role`
--
ALTER TABLE `account_role`
  ADD KEY `FK4rkceep4a4foul2i8mhv9s5db` (`roles_id`),
  ADD KEY `FKqxj80o41im5q5pa6ybds87gac` (`user_id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKbjxn5ii7v7ygwx39et0wawu0q` (`role`);

--
-- Index pour la table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg6eb4sp5cdyw6vf92whgfsp50` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `account`
--
ALTER TABLE `account`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `task`
--
ALTER TABLE `task`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `account_role`
--
ALTER TABLE `account_role`
  ADD CONSTRAINT `FK4rkceep4a4foul2i8mhv9s5db` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKqxj80o41im5q5pa6ybds87gac` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`);

--
-- Contraintes pour la table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `FKg6eb4sp5cdyw6vf92whgfsp50` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;