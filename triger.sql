
/*

-- opcion 1 de insertar registros -----------------------------------------------
-- procedimiento almacenado
DROP PROCEDURE IF EXISTS CopiarCampos;
DELIMITER //
CREATE PROCEDURE InsertarUltimoRegistro()
BEGIN
    -- Declarar variables locales
    DECLARE v_email VARCHAR(255);
    DECLARE v_contrasena VARCHAR(255);
    DECLARE v_username VARCHAR(255);
    DECLARE v_password VARCHAR(255);

    -- Obtener el último registro de tabla1
    SELECT email, contrasena INTO v_email, v_contrasena
    FROM persona
    ORDER BY id_persona DESC
    LIMIT 1;

    -- Generar el username a partir del email
    SET v_username = SUBSTRING_INDEX(v_email, '@', 1);

    -- Asignar la contraseña tal como está
    SET v_password = v_contrasena;

    -- Insertar el registro en tabla2
    INSERT INTO usuario (username, password)
    VALUES (v_username, v_password);
END //

DELIMITER ;

CALL InsertarUltimoRegistro();



DELIMITER //
CREATE PROCEDURE InsertarUltimoRegistro2()
BEGIN
    -- Declarar variables locales
    DECLARE v_email VARCHAR(255);
    DECLARE v_contrasena VARCHAR(255);
    DECLARE v_username VARCHAR(255);
    DECLARE v_password VARCHAR(255);

    -- Obtener el último registro de tabla1
    SELECT NEW.email, NEW.contrasena INTO v_email, v_contrasena
    FROM persona
    -- ORDER BY id_persona DESC
    -- LIMIT 1
    ;

    -- Generar el username a partir del email
    SET v_username = SUBSTRING_INDEX(v_email, '@', 1);

    -- Asignar la contraseña tal como está
    SET v_password = v_contrasena;

    -- Insertar el registro en tabla2
    INSERT INTO usuario (username, password)
    VALUES (v_username, v_password);
END //

DELIMITER ;
/*
-- cada vez que se inserte un nuevo registro en la tabla1, 
el procedimiento almacenado 
InsertarUltimoRegistro se llamará 
automáticamente para realizar la inserción 
correspondiente en la tabla2.


-- triger
DELIMITER //
CREATE TRIGGER InsertarRegistroTrigger
AFTER INSERT
ON persona FOR EACH ROW
BEGIN
    CALL InsertarUltimoRegistro2();
END //

DELIMITER ;

DROP TRIGGER IF EXISTS InsertarRegistroTrigger;


SHOW TRIGGERS;


DELIMITER //

CREATE TRIGGER InsertarRolUsuarioTrigger
AFTER INSERT
ON usuario FOR EACH ROW
BEGIN
    INSERT INTO rol (nombre, id_usuario)
    VALUES ('ROLE_USER', NEW.id_usuario);
END //

DELIMITER ;	


-- -----------------------------------------------------------------------------------------------
-- opcion2 sólo con triger

DELIMITER //

CREATE TRIGGER InsertarRolUsuarioTrigger
AFTER INSERT
ON usuario FOR EACH ROW
BEGIN
    INSERT INTO rol (nombre, id_usuario)
    VALUES ('ROLE_USER', NEW.id_usuario);
END //

DELIMITER ;	

show TRIGGERs;
DROP TRIGGER IF EXISTS InsertarRegistroTrigger;
DROP TRIGGER IF EXISTS InsertarRolUsuarioTrigger;

*/


-- procedimiento insertar usuario -----------------------------------------------------------------------
DELIMITER //
CREATE PROCEDURE InsertarNuevoRegistro(IN p_email VARCHAR(255), IN p_contrasena VARCHAR(255))
BEGIN
    -- Declarar variables locales
    DECLARE v_username VARCHAR(255);
    DECLARE v_password VARCHAR(255);

    -- Generar el username a partir del email
    SET v_username = SUBSTRING_INDEX(p_email, '@', 1);

    -- Asignar la contraseña tal como está
    SET v_password = p_contrasena;

    -- Insertar el registro en tabla2
    INSERT INTO usuario (username, password)
    VALUES (v_username, v_password);
END //

DELIMITER ;

-- CALL InsertarNuevoRegistro('nuevo_email@example.com', 'nueva_contrasena');
-- triger insertar usuario
DELIMITER //
CREATE TRIGGER InsertarRegistroPersona AFTER INSERT ON persona
FOR EACH ROW
BEGIN
    CALL InsertarNuevoRegistro(NEW.email, NEW.contrasena);
END //
	
DELIMITER ;




-- insertar rol ------------------------------------------------------------------------------------

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



