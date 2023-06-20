-- vuelo

use test;

/*
drop table if exists vuelo;
CREATE TABLE vuelo (
  id_vuelo INT AUTO_INCREMENT PRIMARY KEY,
  ruta_vuelo VARCHAR(255) NOT NULL,
  origen VARCHAR(100) NOT NULL,
  destino VARCHAR(100) NOT NULL,
  carga_pasajeros DECIMAL(10,2) NOT NULL,
  carga DECIMAL(10,2) NOT NULL,
  carga_ultimos_100 DECIMAL(10,2) NOT NULL,
  datos_meteorologicos TEXT
);
*/


drop table if exists aeropuerto;

CREATE TABLE if not exists aeropuerto (
  codigo VARCHAR(10) PRIMARY KEY not null,
  AEROPUERTO VARCHAR(100),
  PAX_TOTAL DECIMAL(10, 2),
  CARGA_TOTA DECIMAL(10, 2),
  coordinates text (50)
  -- longitud DECIMAL(10, 8), 
  -- latitud DECIMAL(11, 8)
);
/*
CREATE TABLE if not exists aeropuerto (
  codigo VARCHAR(10) PRIMARY KEY not null,
  AEROPUERTO VARCHAR(100),
  PAX_TOTAL DECIMAL(10, 2),
  CARGA_TOTA DECIMAL(10, 2),
  longitud DECIMAL(10, 8), 
  latitud DECIMAL(11, 8)
);

insert into aeropuerto(codigo) values
(23423);
*/
EXPLAIN aeropuerto;

/*
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\flightsArizona_2015.csv' 
INTO TABLE aeropuerto 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS 
(codigo, aeropuerto, pax_total, carga_tota, @longitud, @latitud)
SET longitud = CAST(SUBSTRING_INDEX(@longitud, ',', 0) AS DECIMAL(10, 8)),
    latitud = CAST(SUBSTRING_INDEX(@latitud, ',', 1) AS DECIMAL(10, 8));
  
*/
  
SHOW VARIABLES LIKE "secure_file_priv";
SET GLOBAL LOCAL_INFILE=TRUE;
SHOW GLOBAL VARIABLES LIKE 'local_infile';
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\flightsArizona_2015.csv'
INTO TABLE aeropuerto
FIELDS TERMINATED BY ','
optionally enclosed by '"'
lines terminated by '\r\n'
IGNORE 1 ROWS;

SHOW VARIABLES LIKE 'secure_file_priv';

SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(coordinates, ',', 1), '[', -1) AS longitud,
       SUBSTRING_INDEX(SUBSTRING_INDEX(coordinates, ',', -1), ']', 1) AS latitud
FROM aeropuerto;

ALTER TABLE aeropuerto
ADD COLUMN longitud DECIMAL(10, 8),
ADD COLUMN latitud DECIMAL(11, 8);

INSERT INTO aeropuerto (longitud, latitud)
SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(coordinates, ',', 1), '[', -1) AS longitud,
       SUBSTRING_INDEX(SUBSTRING_INDEX(coordinates, ',', -1), ']', 1) AS latitud
FROM aeropuerto;

UPDATE aeropuerto
SET longitud = SUBSTRING_INDEX(SUBSTRING_INDEX(coordinates, ',', 1), '[', -1),
    latitud = SUBSTRING_INDEX(SUBSTRING_INDEX(coordinates, ',', -1), ']', 1)
WHERE longitud IS NULL AND latitud IS NULL;

-- actualizar el modo seguro
SET SQL_SAFE_UPDATES = 0;

ALTER TABLE aeropuerto
DROP COLUMN coordinates;

drop table if exists vuelo;
CREATE TABLE vuelo (
  id_vuelo INT AUTO_INCREMENT PRIMARY KEY,
  ruta_vuelo VARCHAR(255) NOT NULL,
  origen_codigo VARCHAR(10),
  destino_codigo VARCHAR(10),
  carga_pasajeros DECIMAL(10,2) NOT NULL,
  carga DECIMAL(10,2) NOT NULL,
  consumo_ultimos_100 DECIMAL(10,2) NOT NULL,
  datos_meteorologicos TEXT,
  FOREIGN KEY (origen_codigo) REFERENCES aeropuerto(codigo),
  FOREIGN KEY (destino_codigo) REFERENCES aeropuerto(codigo)
);

INSERT INTO vuelo (ruta_vuelo, origen_codigo, destino_codigo, carga_pasajeros, carga, consumo_ultimos_100, datos_meteorologicos)
VALUES
    ('Vuelo 1', 'BHI', 'BRC', 200.50, 500.75, 150.25, 'Datos meteorológicos 1'),
    ('Vuelo 2', 'CTC', 'CRD', 150.25, 400.00, 100.50, 'Datos meteorológicos 2'),
    ('Vuelo 3', 'BRC', 'BHI', 300.75, 600.25, 200.50, 'Datos meteorológicos 3'),
    ('Vuelo 4', 'CTC', 'BHI', 250.00, 450.50, 125.75, 'Datos meteorológicos 4'),
    ('Vuelo 5', 'CRD', 'BRC', 180.25, 380.75, 90.50, 'Datos meteorológicos 5'),
    ('Vuelo 6', 'BRC', 'CTC', 220.75, 520.25, 175.50, 'Datos meteorológicos 6'),
    ('Vuelo 7', 'BHI', 'CTC', 270.50, 570.75, 200.25, 'Datos meteorológicos 7'),
    ('Vuelo 8', 'CRD', 'CTC', 190.25, 390.50, 140.75, 'Datos meteorológicos 8'),
    ('Vuelo 9', 'BHI', 'CRD', 230.75, 530.25, 165.50, 'Datos meteorológicos 9'),
    ('Vuelo 10', 'BRC', 'CRD', 280.00, 580.50, 225.75, 'Datos meteorológicos 10');

SELECT *
FROM vuelo
ORDER BY consumo_ultimos_100 DESC;

SELECT *
FROM vuelo
ORDER BY id_vuelo asc;

SELECT vuelo.id_vuelo, vuelo.consumo_ultimos_100, aeropuerto.PAX_TOTAL
FROM vuelo
JOIN aeropuerto ON vuelo.destino_codigo = aeropuerto.codigo;

SELECT vuelo.id_vuelo, vuelo.consumo_ultimos_100, aeropuerto.PAX_TOTAL
FROM vuelo
JOIN aeropuerto ON vuelo.destino_codigo = aeropuerto.codigo
order by 
aeropuerto.PAX_TOTAL desc
;

SELECT vuelo.id_vuelo, vuelo.consumo_ultimos_100, aeropuerto.PAX_TOTAL
FROM vuelo
JOIN aeropuerto ON vuelo.destino_codigo = aeropuerto.codigo
order by 
vuelo.consumo_ultimos_100 desc
;

SELECT aeropuerto.AEROPUERTO AS aeropuerto_destino, AVG(vuelo.consumo_ultimos_100) AS PROM_combustible
FROM vuelo
JOIN aeropuerto ON vuelo.destino_codigo = aeropuerto.codigo
GROUP BY aeropuerto_destino
HAVING PROM_combustible > 163
;

SELECT aeropuerto.AEROPUERTO AS aeropuerto_destino, AVG(vuelo.consumo_ultimos_100) AS PROM_combustible
FROM vuelo
JOIN aeropuerto WHERE vuelo.destino_codigo = aeropuerto.codigo
GROUP BY aeropuerto_destino;
-- consulta
/*

select
        aeropuerto1_.aeropuerto as col_0_0_,
        count(vuelo0_.id_vuelo) as col_1_0_,
        aeropuerto1_.latitud as col_2_0_,
        aeropuerto1_.longitud as col_3_0_,
        aeropuerto1_.pax_total as col_4_0_,
        avg(vuelo0_.consumo_ultimos_100) as col_5_0_,
        avg(vuelo0_.carga_pasajeros) as col_6_0_ 
    from
        vuelo vuelo0_ cross 
    join
        aeropuerto aeropuerto1_ 
    where
        vuelo0_.destino_codigo=aeropuerto1_.codigo 
    group by
        vuelo0_.destino_codigo 
    order by
        aeropuerto1_.aeropuerto ASC
*/
select
        aeropuerto as col_0_0_,
        count(vuelo0_.id_vuelo) as col_1_0_,
        aeropuerto1_.latitud as col_2_0_,
        aeropuerto1_.longitud as col_3_0_,
        aeropuerto1_.pax_total as col_4_0_,
        avg(vuelo0_.consumo_ultimos_100) as col_5_0_,
        avg(vuelo0_.carga_pasajeros) as col_6_0_ 
    from
        vuelo vuelo0_ cross 
    join
        aeropuerto aeropuerto1_ 
    where
        vuelo0_.destino_codigo=aeropuerto1_.codigo 
    group by
        vuelo0_.destino_codigo 
    order by
        aeropuerto1_.aeropuerto ASC