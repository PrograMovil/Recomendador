-- MySQL Script generated by MySQL Workbench
-- 05/10/17 22:09:13
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema recomendador
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `recomendador` ;

-- -----------------------------------------------------
-- Schema recomendador
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `recomendador` DEFAULT CHARACTER SET utf8 ;
USE `recomendador` ;

-- -----------------------------------------------------
-- Table `recomendador`.`canciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recomendador`.`canciones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NULL,
  `artista` VARCHAR(45) NULL,
  `album` VARCHAR(100) NULL,
  `genero` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `recomendador`.`canciones`
-- -----------------------------------------------------
START TRANSACTION;
USE `recomendador`;
INSERT INTO `recomendador`.`canciones` (`id`, `nombre`, `artista`, `album`, `genero`) VALUES (1, 'I\'m The One', 'Khaled', 'Chance', 'Rap');
INSERT INTO `recomendador`.`canciones` (`id`, `nombre`, `artista`, `album`, `genero`) VALUES (2, 'That\'s What I Like', 'Bruno', 'Moon Flight', 'Pop');
INSERT INTO `recomendador`.`canciones` (`id`, `nombre`, `artista`, `album`, `genero`) VALUES (3, 'Humble', 'Kendrick', 'Ed', 'Rap');
INSERT INTO `recomendador`.`canciones` (`id`, `nombre`, `artista`, `album`, `genero`) VALUES (4, 'Shape Of You', 'Ed Sheeran', 'Last Week', 'Pop');
INSERT INTO `recomendador`.`canciones` (`id`, `nombre`, `artista`, `album`, `genero`) VALUES (5, 'Mask Off', 'Future', 'Stay', 'Rock');
INSERT INTO `recomendador`.`canciones` (`id`, `nombre`, `artista`, `album`, `genero`) VALUES (6, 'DNA.', 'Lamar', 'Say You', 'Reggaeton');
INSERT INTO `recomendador`.`canciones` (`id`, `nombre`, `artista`, `album`, `genero`) VALUES (7, 'Body Like A Back Road', 'Khalid', 'Location', 'Hip Hop');
INSERT INTO `recomendador`.`canciones` (`id`, `nombre`, `artista`, `album`, `genero`) VALUES (8, 'Passionfruit', 'Drake', 'Eighteen', 'Electro');
INSERT INTO `recomendador`.`canciones` (`id`, `nombre`, `artista`, `album`, `genero`) VALUES (9, 'Believer', 'Imagine Dragon', 'Believer', 'Pop');
INSERT INTO `recomendador`.`canciones` (`id`, `nombre`, `artista`, `album`, `genero`) VALUES (10, 'Closer', 'Chainsmokers', 'Chain Smoking', 'Salsa');

COMMIT;

