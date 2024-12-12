DROP DATABASE springsecurity;
CREATE DATABASE springsecurity;
USE springsecurity;

CREATE TABLE sec_user (
    use_id BIGINT NOT NULL AUTO_INCREMENT,
    use_first_name VARCHAR(20) NOT NULL,
    use_last_name VARCHAR(20) NOT NULL,
    use_email VARCHAR(45) NOT NULL UNIQUE,
    use_passwd VARCHAR(64) NOT NULL,
    use_id_status INTEGER NOT NULL,
    use_created_by BIGINT NOT NULL,
    use_created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    use_modified_by BIGINT NOT NULL,
    use_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (use_id)
);
CREATE TABLE sec_role (
    usr_id BIGINT NOT NULL AUTO_INCREMENT,
    usr_role_name VARCHAR(30) NOT NULL,
    usr_id_status INTEGER NOT NULL,
    usr_created_by BIGINT NOT NULL,
    usr_created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    usr_modified_by BIGINT NOT NULL,
    usr_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (usr_id)
);
CREATE TABLE sec_user_role_relation (
    urr_id_user BIGINT NOT NULL,
    urr_id_user_role BIGINT NOT NULL,
    PRIMARY KEY (urr_id_user, urr_id_user_role),
    FOREIGN KEY (urr_id_user) REFERENCES sec_user(use_id),
    FOREIGN KEY (urr_id_user_role) REFERENCES sec_role(usr_id)
);

DROP TABLE IF EXISTS `artista`;
CREATE TABLE artista (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(100) NOT NULL,
    UNIQUE KEY (id)
);

DROP TABLE IF EXISTS `pinturas`;
CREATE TABLE pinturas (
id INT NOT NULL AUTO_INCREMENT,
tituloObra VARCHAR(100) NOT NULL,
idArtista INT NOT NULL,
anioCreacion date NOT NULL,
tecnica VARCHAR(100) NOT NULL,
detalles VARCHAR(700) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (idArtista) REFERENCES artista(id) );

DROP TABLE IF EXISTS `genero`;
CREATE TABLE genero (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE
);
DROP TABLE IF EXISTS `carreraProfesional`;
CREATE TABLE carreraProfesional (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS `productos`;
CREATE TABLE productos (
clave_producto VARCHAR(50) NOT NULL,
nombre VARCHAR(100) NOT NULL,
precio float NOT NULL,
descripcion VARCHAR(500) NOT NULL,
stock INT NOT NULL,
PRIMARY KEY (clave_producto) );

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE usuarios (
id INT NOT NULL AUTO_INCREMENT,
correo VARCHAR(100) NOT NULL,
nombre VARCHAR(100) NOT NULL,
contrasena VARCHAR(100) NOT NULL,
idGenero INT NOT NULL,
id_carrera_profesional INT NOT NULL,
direccion VARCHAR(200) NOT NULL,
telefono VARCHAR(20) NOT NULL,
id_sec_user BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (idGenero) REFERENCES genero(id),
FOREIGN KEY (id_sec_user) REFERENCES sec_user(use_id),
FOREIGN KEY (id_carrera_profesional) REFERENCES CarreraProfesional(id)  );

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE ventas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idUsuario INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    fecha DATETIME NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);

DROP TABLE IF EXISTS `tarjetas`;
CREATE TABLE tarjetas (
id INT NOT NULL AUTO_INCREMENT,
idUsuario INT NOT NULL,
nombreTitular VARCHAR(100) NOT NULL,
apellidoTitular VARCHAR(100) NOT NULL,
numeroTarjeta VARCHAR(100) NOT NULL,
fechaExpiracion VARCHAR(100) NOT NULL,
tipoTarjeta VARCHAR(100) NOT NULL,
saldo float NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (idUsuario) REFERENCES usuarios(id));

DROP TABLE IF EXISTS `carritoProducto`;
CREATE TABLE carritoProducto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idVentas INT NOT NULL,
    claveProducto VARCHAR(50) NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (idVentas) REFERENCES ventas(id),
    FOREIGN KEY (claveProducto) REFERENCES productos(clave_producto)
);