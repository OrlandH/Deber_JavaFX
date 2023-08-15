# Deber_JavaFX

# Integrantes:
Juan Gualotuña, Juan Falconi, Heyer Tinoco

# Link Video:
https://www.youtube.com/watch?v=JpwZW85RtcM

Informe en el Aula Virtual

# Base de datos usada en MySql SCRIPT:

CREATE DATABASE POOPRUEBA2; 
USE POOPRUEBA2; 
CREATE TABLE est_prueba2( codigo_est INT(11), cedula INT(11), nombre varchar(50) NOT NULL, fecha_nac varchar(10));
INSERT INTO est_prueba2 (codigo_est, cedula, nombre, fecha_nac) VALUES (2121228020, 1725523060,'Heyer Tinoco', '03/12/2002'), (2121227012, 1777777777,'Selena Tinoco', '21/11/1997');
SELECT * FROM est_prueba2;
