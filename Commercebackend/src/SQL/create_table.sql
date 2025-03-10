DROP SCHEMA IF EXISTS `full-stack-ecommence`;

CREATE SCHEMA `full-stack-ecommence`;
USE `full-stack-ecommence`;

CREATE TABLE IF NOT EXISTS `full-stack-ecommence`.`product_category`(
                                                                        `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `category_name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    )
    ENGINE = InnoDB
    auto_increment = 1;

CREATE TABLE IF NOT EXISTS `full-stack-ecommence`.`product`(
                                                               `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `sku` VARCHAR(255) DEFAULT NULL,
    `name` VARCHAR(255) DEFAULT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `unit_price` DECIMAL(13,2) DEFAULT NULL,
    `image_url` VARCHAR(255) DEFAULT NULL,
    `active` BIT DEFAULT 1,
    `units_in_stocks` INT(11) DEFAULT NULL,
    `date_created` DATETIME(6) DEFAULT NULL,
    `last_updated` DATETIME(6) DEFAULT NULL,
    `category_id` BIGINT(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_category`(`category_id`),
    CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `product_category`(`id`)
    )
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;