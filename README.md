# Energydata
# Proyecto de Gestión de Vuelos

Este es un proyecto de gestión de vuelos que permite administrar información sobre vuelos, aeropuertos y otros datos relacionados. El proyecto está desarrollado en Spring Boot y utiliza una base de datos relacional para el almacenamiento de datos.

## Características

- Administración de vuelos: Crear, actualizar y eliminar vuelos.
- Información de aeropuertos: Visualizar detalles de aeropuertos, como ubicación geográfica.
- Agrupación de vuelos: Obtener información agrupada de vuelos por destino, consumo de combustible, etc.
- Interfaz web: Interfaz de usuario basada en Thymeleaf para facilitar la interacción con el sistema.

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Thymeleaf
- HTML/CSS
- MySQL (u otro motor de base de datos relacional)

## Configuración del proyecto

1. Clona el repositorio del proyecto: `git clone https://github.com/tu-usuario/proyecto-gestion-vuelos.git`
2. Importa el proyecto en tu IDE favorito.
3. Configura la conexión a la base de datos en el archivo `application.properties`.
4. Ejecuta la aplicación.


## Configuración de la base de datos

La aplicación utiliza una base de datos relacional para almacenar la información de los vuelos y aeropuertos. A continuación se muestran los scripts SQL necesarios para crear las tablas requeridas:

```sql
-- Crear tabla de vuelos
CREATE TABLE vuelo (
  id_vuelo INT PRIMARY KEY AUTO_INCREMENT,
  ruta_vuelo VARCHAR(255) NOT NULL,
  origen_codigo VARCHAR(255) NOT NULL,
  destino_codigo VARCHAR(255) NOT NULL,
  carga_pasajeros DOUBLE NOT NULL,
  carga DOUBLE NOT NULL,
  consumo_ultimos_100 DOUBLE NOT NULL,
  datos_meteorologicos VARCHAR(255),
  FOREIGN KEY (origen_codigo) REFERENCES aeropuerto (codigo),
  FOREIGN KEY (destino_codigo) REFERENCES aeropuerto (codigo)
);

-- Crear tabla de aeropuertos
CREATE TABLE aeropuerto (
  codigo VARCHAR(255) PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  latitud DOUBLE NOT NULL,
  longitud DOUBLE NOT NULL,
  pax_total INT NOT NULL
);

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
-- datos de prueba

INSERT INTO `test`.`usuario` (`username`, `password`) VALUES ('admin', '123');
INSERT INTO `test`.`usuario` (`username`, `password`) VALUES ('user', '123');


INSERT INTO `test`.`rol` (`nombre`, `id_usuario`) VALUES ('ROLE_ADMIN', '1');
INSERT INTO `test`.`rol` (`nombre`, `id_usuario`) VALUES ('ROLE_USER', '1');
INSERT INTO `test`.`rol` (`nombre`, `id_usuario`) VALUES ('ROLE_USER', '2');


UPDATE `test`.`usuario` SET `password` = '$2a$10$MMyCa9vKGG.LvseRZLG7CupjzV4i1l8EoHOKcT29IRZX6jnwRrnZC' WHERE (`id_usuario` = '1');
UPDATE `test`.`usuario` SET `password` = '$2a$10$6VA2ZoSpiwq6CyJrQko4BuZKUOOUVMytfHAPrE9VteOXrie6pVwu6' WHERE (`id_usuario` = '2');


-- procedimiento insertar usuario -----------------------------------------------------------------------
drop procedure InsertarNuevoRegistro;
DELIMITER //
CREATE PROCEDURE InsertarNuevoRegistro(IN p_email VARCHAR(255), IN p_contrasena VARCHAR(255), IN p_id_persona INT)
BEGIN
    -- Declarar variables locales
    DECLARE v_username VARCHAR(255);
    DECLARE v_password VARCHAR(255);

    -- Generar el username a partir del email
    SET v_username = SUBSTRING_INDEX(p_email, '@', 1);

    -- Asignar la contraseña tal como está
    SET v_password = p_contrasena;

    -- Insertar el registro en tabla2
    INSERT INTO usuario (username, password, id_persona)
    VALUES (v_username, v_password, p_id_persona);
END //

DELIMITER ;
insert into persona (email, contrasena)
values ()
;


-- CALL InsertarNuevoRegistro('nuevo_email@example.com', 'nueva_contrasena', 20);
-- triger insertar usuario
drop trigger InsertarRegistroPersona;
DELIMITER //
CREATE TRIGGER InsertarRegistroPersona AFTER INSERT ON persona
FOR EACH ROW
BEGIN
    CALL InsertarNuevoRegistro(NEW.email, NEW.contrasena, NEW.id_persona);
END //
	
DELIMITER ;




-- insertar rol ------------------------------------------------------------------------------------
drop procedure InsertarRolUsuarioProcedure;
DELIMITER //

CREATE PROCEDURE InsertarRolUsuarioProcedure(IN p_id_usuario INT)
BEGIN
    INSERT INTO rol (nombre, id_usuario)
    VALUES ('ROLE_USER', p_id_usuario);
END //

DELIMITER ;

-- CALL InsertarRolUsuarioProcedure(123); -- Reemplaza 123 con el valor deseado

DELIMITER //

CREATE TRIGGER InsertarRolUsuarioTrigger
AFTER INSERT
ON usuario FOR EACH ROW
BEGIN
    CALL InsertarRolUsuarioProcedure(NEW.id_usuario);
END //
DELIMITER ;

show triggers;

drop procedure UpdateTabla;
-- actualizar tabla -------------------------------------------------
DELIMITER //

CREATE PROCEDURE UpdateTabla(IN p_id INT, IN p_email VARCHAR(255), IN p_contrasena VARCHAR(255))
BEGIN
    UPDATE usuario
    
    SET username =  SUBSTRING_INDEX(p_email, '@', 1), password = p_contrasena
    WHERE id_persona = p_id;
END //

DELIMITER ;



drop trigger update_trigger;
show triggers;
DELIMITER //
CREATE TRIGGER update_trigger
AFTER UPDATE ON persona
FOR EACH ROW
BEGIN
     CALL UpdateTabla(NEW.id_persona, NEW.email, NEW.contrasena);
END //

DELIMITER ;


```
## Contribuciones

Las contribuciones son bienvenidas. Si encuentras algún error, tienes alguna idea de mejora o deseas agregar nuevas características, puedes hacerlo a través de pull requests.

## Licencia

Este proyecto está bajo la Licencia MIT. Si utilizas este proyecto, asegúrate de cumplir con los términos de la licencia.

## Contacto

Si tienes alguna pregunta o consulta sobre el proyecto, puedes contactarme a través de mi dirección de correo electrónico: [correo](mailto:sebastian.menares@gmail.com)

## Referencias

Aquí tienes una lista de recursos útiles relacionados con este proyecto:

- Documentación oficial de Spring Boot: [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
- Documentación oficial de Thymeleaf: [https://www.thymeleaf.org/](https://www.thymeleaf.org/)
- Documentación oficial de Hibernate: [https://hibernate.org/](https://hibernate.org/)
- Documentación oficial de MySQL: [https://dev.mysql.com/doc/](https://dev.mysql.com/doc/)
- Documentación oficial de HTML y CSS: [https://developer.mozilla.org/](https://developer.mozilla.org/)

Además, estos son algunos tutoriales y ejemplos que pueden ser útiles:

- Tutorial de Spring Boot en Baeldung: [https://www.baeldung.com/spring-boot](https://www.baeldung.com/spring-boot)
- Ejemplos de Thymeleaf en GitHub: [https://github.com/thymeleaf/thymeleafexamples](https://github.com/thymeleaf/thymeleafexamples)
- Ejemplos de Hibernate en GitHub: [https://github.com/hibernate/hibernate-orm/tree/main/documentation/src/test/java/org/hibernate/userguide](https://github.com/hibernate/hibernate-orm/tree/main/documentation/src/test/java/org/hibernate/userguide)
- Documentación oficial de MySQL en W3Schools: [https://www.w3schools.com/sql/](https://www.w3schools.com/sql/)

## Recursos adicionales

Aquí hay un curso recomendado de Udemy que cubre los conceptos fundamentales relacionados con este proyecto:

- Universidad de Java. [https://www.udemy.com/course/universidad-java-especialista-en-java-desde-cero-a-master/](https://www.udemy.com/course/universidad-java-especialista-en-java-desde-cero-a-master/) 
- Crear APIREST con Spring Boot. [https://www.youtube.com/watch?v=g_zoy9m0KMs/](https://www.youtube.com/watch?v=g_zoy9m0KMs)


Recuerda consultar estos recursos para obtener más información y ayuda mientras trabajas en tu proyecto.
