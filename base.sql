CREATE DATABASE IF NOT EXISTS test2;
USE test2;

drop table usuario;
CREATE TABLE usuario (
	id_usuario INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(45) NULL,
    password VARCHAR(128) NULL,
    PRIMARY KEY (id_usuario)
);


drop table if exists rol;
CREATE TABLE rol (
	id_rol INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NULL,
    id_usuario INT NULL,
    PRIMARY KEY(id_rol),
    INDEX id_rol_usuario_idx (id_usuario ASC) VISIBLE,
    CONSTRAINT id_rol_usuario
    FOREIGN KEY(id_usuario)
    REFERENCES usuario(id_usuario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);



CREATE TABLE if not exists `test2`.`persona` (
  `id_persona` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`id_persona`));

-- crear mis tablas ---------------------------------------------

/*

CREATE TABLE IF NOT EXISTS camara (
	id_camara INT NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(45) NULL,
    escenario_id_escenario int,
    PRIMARY KEY (id_camara)
);

CREATE TABLE IF NOT EXISTS sensor (
	id_sensor INT NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(45) NULL,
    lectura_sensor int,
    escenario_id_escenario int,
    PRIMARY KEY (id_sensor)
);

CREATE TABLE IF NOT EXISTS video (
	id_video INT NOT NULL AUTO_INCREMENT,
    fecha datetime,
    id_escenario INT,
    escenario_id_escenario int,
    PRIMARY KEY (id_video)
);

CREATE TABLE IF NOT EXISTS imagen (
	id_imagen INT NOT NULL AUTO_INCREMENT,
    fecha datetime,
    id_escenario INT,
    escenario_id_escenario int,
    PRIMARY KEY (id_imagen)
);

CREATE TABLE IF NOT EXISTS escenario (
	id_escenario INT NOT NULL AUTO_INCREMENT,
    nombre varchar(45),
	PRIMARY KEY (id_escenario)
);

-- crea tabla detalleventa
CREATE TABLE detallePersona (
usuario_idusuario INT,
persona_idpersona INT,
escenario_idescenario INT -- ,
-- cantidad INT
);
*/

-- --------------------------------------------------------------------
/*
-- FK2 persona > usuario
ALTER TABLE detallePersona
ADD CONSTRAINT persona_usuario_FK 
FOREIGN KEY ( usuario_idusuario )
REFERENCES usuario ( id_usuario )
-- ON DELETE SET NULL -- acepta nulos
; 

-- PK detalleventa
ALTER TABLE detallePersona 
ADD CONSTRAINT detallePersona_PK 
PRIMARY KEY ( persona_idpersona, escenario_idescenario);

-- FK1 detalleventa > ventas
ALTER TABLE detallePersona
ADD CONSTRAINT detallePersona_persona_FK 
FOREIGN KEY ( persona_idpersona )
REFERENCES persona ( id_persona )
-- ON DELETE SET NULL -- acepta nulos
; 

-- FK2 detalleventa > producto
ALTER TABLE detallePersona
ADD CONSTRAINT detallePersona_escenario_FK 
FOREIGN KEY ( escenario_idescenario )
REFERENCES escenario ( id_escenario )
-- ON DELETE SET NULL -- acepta nulos
;

-- FK2 camara > escenario
ALTER TABLE camara
ADD CONSTRAINT camara_escenario_FK 
FOREIGN KEY ( escenario_id_escenario )
REFERENCES escenario ( id_escenario )
-- ON DELETE SET NULL -- acepta nulos
;
-- FK2 sensor > escenario
ALTER TABLE sensor
ADD CONSTRAINT sensor_escenario_FK  
FOREIGN KEY ( escenario_id_escenario )
REFERENCES escenario ( id_escenario )
-- ON DELETE SET NULL -- acepta nulos
;
-- FK2 video > escenario
ALTER TABLE video
ADD CONSTRAINT video_escenario_FK 
FOREIGN KEY ( escenario_id_escenario )
REFERENCES escenario ( id_escenario )
-- ON DELETE SET NULL -- acepta nulos
;

-- FK2 imagen > escenario
ALTER TABLE imagen
ADD CONSTRAINT imagen_escenario_FK 
FOREIGN KEY ( escenario_id_escenario )
REFERENCES escenario ( id_escenario )
-- ON DELETE SET NULL -- acepta nulos
;
*/

--      --------------------------------------------------------

INSERT INTO `test2`.`persona` (`nombre`, `apellido`, `email`, `telefono`) VALUES ('Juan', 'Perez', 'jperez@mail.com', '5513445567');
INSERT INTO `test2`.`persona` (`nombre`, `apellido`, `email`, `telefono`) VALUES ('Karla', 'Gomez', 'kgomez@mail.com', '771344');
INSERT INTO `test2`.`persona` (`nombre`, `apellido`, `email`, `telefono`) VALUES ('Carlos', 'Lara ', 'clara@mail.com', '33221144');


INSERT INTO `test2`.`usuario` (`username`, `password`) VALUES ('admin', '123');
INSERT INTO `test2`.`usuario` (`username`, `password`) VALUES ('user', '123');


INSERT INTO `test2`.`rol` (`nombre`, `id_usuario`) VALUES ('ROLE_ADMIN', '1');
INSERT INTO `test2`.`rol` (`nombre`, `id_usuario`) VALUES ('ROLE_USER', '1');
INSERT INTO `test2`.`rol` (`nombre`, `id_usuario`) VALUES ('ROLE_USER', '2');


UPDATE `test2`.`usuario` SET `password` = '$2a$10$MMyCa9vKGG.LvseRZLG7CupjzV4i1l8EoHOKcT29IRZX6jnwRrnZC' WHERE (`id_usuario` = '1');
UPDATE `test2`.`usuario` SET `password` = '$2a$10$6VA2ZoSpiwq6CyJrQko4BuZKUOOUVMytfHAPrE9VteOXrie6pVwu6' WHERE (`id_usuario` = '2');

/*
ALTER TABLE `test2`.`persona` 
ADD COLUMN `saldo` DOUBLE NULL AFTER `telefono`;
-- ---------------------------------------------------------------------
UPDATE `test2`.`persona` SET `saldo` = '100' WHERE (`id_persona` = '1');
UPDATE `test2`.`persona` SET `saldo` = '150' WHERE (`id_persona` = '2');
UPDATE `test2`.`persona` SET `saldo` = '200' WHERE (`id_persona` = '3');

UPDATE `test`.`persona` SET `saldo` = '300' WHERE (`id_persona` = '7');
UPDATE `test`.`persona` SET `saldo` = '300' WHERE (`id_persona` = '8');
UPDATE `test`.`persona` SET `saldo` = '300' WHERE (`id_persona` = '9');

-- -----------------------

UPDATE `test`.`persona` SET `saldo` = '0' WHERE (`id_persona` = '11');
UPDATE `test`.`persona` SET `saldo` = '0' WHERE (`id_persona` = '12');
UPDATE `test`.`persona` SET `saldo` = '0' WHERE (`id_persona` = '13');
UPDATE `test`.`persona` SET `saldo` = '0' WHERE (`id_persona` = '14');
*/
