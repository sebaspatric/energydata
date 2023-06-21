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



