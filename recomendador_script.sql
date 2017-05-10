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
-- Table `recomendador`.`albumes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recomendador`.`albumes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  `anio` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recomendador`.`generos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recomendador`.`generos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recomendador`.`canciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recomendador`.`canciones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NULL,
  `artista` VARCHAR(45) NULL,
  `albumes_id` INT NOT NULL,
  `generos_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_canciones_albumes_idx` (`albumes_id` ASC),
  INDEX `fk_canciones_generos1_idx` (`generos_id` ASC),
  CONSTRAINT `fk_canciones_albumes`
    FOREIGN KEY (`albumes_id`)
    REFERENCES `recomendador`.`albumes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_canciones_generos1`
    FOREIGN KEY (`generos_id`)
    REFERENCES `recomendador`.`generos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
