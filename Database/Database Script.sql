CREATE TABLE `CATEGORIA` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DESCRIPCION` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `FAVORITO` (
  `COMPRADOR` int(11) NOT NULL,
  `PRODUCTO` int(11) NOT NULL,
  PRIMARY KEY (`COMPRADOR`,`PRODUCTO`),
  KEY `FAVORITO_PRODUCTO_idx` (`PRODUCTO`),
  KEY `FAVORITO_COMPRADOR_idx` (`COMPRADOR`),
  CONSTRAINT `FAVORITO_COMPRADOR` FOREIGN KEY (`COMPRADOR`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FAVORITO_PRODUCTO` FOREIGN KEY (`PRODUCTO`) REFERENCES `PRODUCTO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `GRUPO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MARKETING` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `GRUPO_MARKETING_idx` (`MARKETING`),
  CONSTRAINT `GRUPO_MARKETING` FOREIGN KEY (`MARKETING`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `GRUPOCOMPRADOR` (
  `COMPRADOR` int(11) NOT NULL,
  `GRUPO` int(11) NOT NULL,
  PRIMARY KEY (`COMPRADOR`,`GRUPO`),
  KEY `GRUPO_idx` (`GRUPO`),
  KEY `GC_COMPRADOR_idx` (`COMPRADOR`),
  CONSTRAINT `GC_COMPRADOR` FOREIGN KEY (`COMPRADOR`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `GC_GRUPO` FOREIGN KEY (`GRUPO`) REFERENCES `GRUPO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `MENSAJE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MARKETING` int(11) NOT NULL,
  `GRUPO` int(11) NOT NULL,
  `ASUNTO` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CONTENIDO` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FECHA` date NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`ID`),
  KEY `MENSAJE_GRUPO_idx` (`GRUPO`),
  KEY `MENSAJE_MARKETING_idx` (`MARKETING`),
  CONSTRAINT `MENSAJE_GRUPO` FOREIGN KEY (`GRUPO`) REFERENCES `GRUPO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `MENSAJE_MARKETING` FOREIGN KEY (`MARKETING`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `MENSAJECOMPRADOR` (
  `COMPRADOR` int(11) NOT NULL,
  `MENSAJE` int(11) NOT NULL,
  PRIMARY KEY (`COMPRADOR`,`MENSAJE`),
  KEY `MENSAJE_FK` (`MENSAJE`),
  CONSTRAINT `COMPRADOR_FK` FOREIGN KEY (`COMPRADOR`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `MENSAJE_FK` FOREIGN KEY (`MENSAJE`) REFERENCES `MENSAJE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `PREFERENCIA` (
  `COMPRADOR` int(11) NOT NULL,
  `CATEGORIA` int(11) NOT NULL,
  PRIMARY KEY (`COMPRADOR`,`CATEGORIA`),
  KEY `PREFERENCIA_CATEGORIA_idx` (`CATEGORIA`),
  KEY `PREFERENCIA_COMPRADOR_idx` (`COMPRADOR`),
  CONSTRAINT `PREFERENCIA_CATEGORIA` FOREIGN KEY (`CATEGORIA`) REFERENCES `CATEGORIA` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PREFERENCIA_COMPRADOR` FOREIGN KEY (`COMPRADOR`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `PRODUCTO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITULO` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DESCRIPCION` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRECIO_SALIDA` double NOT NULL,
  `FIN_PUJA` date DEFAULT NULL,
  `FOTO` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `VENDEDOR` int(11) NOT NULL,
  `COMPRADOR` int(11) DEFAULT NULL,
  `CATEGORIA` int(11) NOT NULL,
  `EN_PUJA` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`),
  KEY `CATEGORIA_idx` (`CATEGORIA`),
  KEY `PRODUCTO_COMPRADOR_idx` (`COMPRADOR`),
  KEY `PRODUCTO_VENDEDOR_idx` (`VENDEDOR`),
  CONSTRAINT `PRODUCTO_CATEGORIA` FOREIGN KEY (`CATEGORIA`) REFERENCES `CATEGORIA` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PRODUCTO_COMPRADOR` FOREIGN KEY (`COMPRADOR`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PRODUCTO_VENDEDOR` FOREIGN KEY (`VENDEDOR`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `PUJA` (
  `COMPRADOR` int(11) NOT NULL,
  `PRODUCTO` int(11) NOT NULL,
  `PRECIO` double NOT NULL,
  `FECHA` date NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`COMPRADOR`,`PRODUCTO`,`PRECIO`),
  KEY `PUJA_PRODUCTO_idx` (`PRODUCTO`),
  KEY `PUJA_COMPRADOR_idx` (`COMPRADOR`),
  CONSTRAINT `PUJA_COMPRADOR` FOREIGN KEY (`COMPRADOR`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PUJA_PRODUCTO` FOREIGN KEY (`PRODUCTO`) REFERENCES `PRODUCTO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `ROL_USUARIO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE_UQ` (`NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `USUARIO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CORREO` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PASSWORD` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NOMBRE` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `APELLIDOS` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DOMICILIO` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FECHA_NACIMIENTO` date NOT NULL DEFAULT current_timestamp(),
  `SEXO` enum('masc','fem') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CIUDAD` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SALDO` double NOT NULL DEFAULT 0,
  `ROL` int(11) NOT NULL DEFAULT 2,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CORREO_UNIQUE` (`CORREO`),
  KEY `ROL_USUARIO_FK` (`ROL`),
  CONSTRAINT `ROL_USUARIO_FK` FOREIGN KEY (`ROL`) REFERENCES `ROL_USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
