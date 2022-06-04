DROP
    DATABASE IF EXISTS `epam_db`;

CREATE
    DATABASE `epam_db` DEFAULT CHARACTER SET utf8;

USE
    `epam_db`;

CREATE TABLE `user`
(
    `id`       INTEGER      NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `role`     INTEGER      NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `client`
(
    `id`      INTEGER      NOT NULL,
    `name`    VARCHAR(255) NOT NULL,
    `surname` VARCHAR(255) NOT NULL,
    `phone`   VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON UPDATE RESTRICT ON DELETE CASCADE
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `room`
(
    `id`              INTEGER NOT NULL AUTO_INCREMENT,
    `number`          INTEGER NOT NULL,
    `number_of_seats` INTEGER NOT NULL,
    `apartment_type`  INTEGER,
    `price`           BIGINT  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `order`
(
    `id`              INTEGER   NOT NULL AUTO_INCREMENT,
    `creation`        TIMESTAMP NOT NULL,
    `client_id`       INTEGER   NOT NULL,
    `number_of_seats` INTEGER   NOT NULL,
    `apartment_type`  INTEGER,
    `date_arrival`    DATE      NOT NULL,
    `date_departure`  DATE      NOT NULL,
    `active`          BOOLEAN,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON UPDATE RESTRICT ON DELETE CASCADE
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `reservation`
(
    `id`       INTEGER NOT NULL AUTO_INCREMENT,
    `order_id` INTEGER NOT NULL,
    `room_id`  INTEGER NOT NULL,
    `price`    BIGINT  NOT NULL,
    FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
    FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;