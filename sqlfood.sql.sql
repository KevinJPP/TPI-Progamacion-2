-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: food_store_db
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `eliminado` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,0,'2026-06-22 22:25:23','Hamburguesas','Hamburguesas completas y caseras'),(2,0,'2026-06-22 22:25:23','Bebidas','Gaseosas y aguas longitudinales'),(3,0,'2026-06-22 22:25:23','Pizzas','Pizzas al horno de barro'),(4,0,'2026-06-23 03:34:53','Hamburguesas','Hamburguesas caseras con papas'),(5,0,'2026-06-23 03:49:18','Hamburguesas','Hamburguesas caseras con papas'),(6,0,'2026-06-23 03:53:28','Hamburguesas','Hamburguesas caseras con papas'),(7,0,'2026-06-23 03:54:55','Hamburguesas','Hamburguesas caseras con papas'),(8,0,'2026-06-23 03:55:02','Hamburguesas','Hamburguesas caseras con papas'),(9,0,'2026-06-23 03:55:17','Hamburguesas','Hamburguesas caseras con papas'),(10,0,'2026-06-23 04:43:00','Hamburguesas','Hamburguesas caseras con papas'),(11,0,'2026-06-23 04:45:59','Hamburguesas','Hamburguesas caseras con papas'),(12,0,'2026-06-23 04:48:02','Hamburguesas','Hamburguesas caseras con papas'),(13,0,'2026-06-23 04:48:29','Hamburguesas','Hamburguesas caseras con papas'),(14,0,'2026-06-23 05:28:29','Hamburguesas','Hamburguesas caseras con papas');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `eliminado` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `cantidad` int NOT NULL,
  `subtotal` double NOT NULL,
  `producto_id` bigint NOT NULL,
  `pedido_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  KEY `pedido_id` (`pedido_id`),
  CONSTRAINT `detalle_pedido_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  CONSTRAINT `detalle_pedido_ibfk_2` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
INSERT INTO `detalle_pedido` VALUES (1,0,'2026-06-23 03:54:55',2,25000,8,1),(2,0,'2026-06-23 03:55:17',2,25000,10,2),(3,0,'2026-06-23 04:43:00',2,25000,11,3),(4,0,'2026-06-23 04:48:29',2,25000,14,4),(5,0,'2026-06-23 05:28:29',2,25000,15,5);
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `eliminado` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_pedido` datetime DEFAULT CURRENT_TIMESTAMP,
  `total` double NOT NULL DEFAULT '0',
  `estado` varchar(30) NOT NULL,
  `forma_pago` varchar(30) NOT NULL,
  `usuario_id` bigint NOT NULL,
  `codigo` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,0,'2026-06-23 03:54:55','2026-06-22 21:54:55',0,'PENDIENTE','EFECTIVO',5,'PED-37ED3108'),(2,0,'2026-06-23 03:55:17','2026-06-22 21:55:17',0,'PENDIENTE','EFECTIVO',7,'PED-B3C073C9'),(3,0,'2026-06-23 04:43:00','2026-06-22 22:43:00',0,'PENDIENTE','EFECTIVO',8,'PED-BDB4598C'),(4,0,'2026-06-23 04:48:29','2026-06-22 22:48:28',0,'PENDIENTE','EFECTIVO',11,'PED-0E3B014B'),(5,0,'2026-06-23 05:28:29','2026-06-22 23:28:29',0,'PENDIENTE','EFECTIVO',12,'PED-18D4396A');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `eliminado` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` varchar(150) NOT NULL,
  `precio` double NOT NULL,
  `categoria_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categoria_id` (`categoria_id`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,0,'2026-06-22 22:25:23','Hamburguesa Simple',4500,1),(2,0,'2026-06-22 22:25:23','Hamburguesa Completa',6000,1),(3,0,'2026-06-22 22:25:23','Coca Cola 500ml',1500,2),(4,0,'2026-06-22 22:25:23','Pizza Muzzarella',7000,3),(5,0,'2026-06-23 03:34:53','Hamburguesa Completa',12500,4),(6,0,'2026-06-23 03:49:18','Hamburguesa Completa',12500,5),(7,0,'2026-06-23 03:53:28','Hamburguesa Completa',12500,6),(8,0,'2026-06-23 03:54:55','Hamburguesa Completa',12500,7),(9,0,'2026-06-23 03:55:02','Hamburguesa Completa',12500,8),(10,0,'2026-06-23 03:55:17','Hamburguesa Completa',12500,9),(11,0,'2026-06-23 04:43:00','Hamburguesa Completa',12500,10),(12,0,'2026-06-23 04:45:59','Hamburguesa Completa',12500,11),(13,0,'2026-06-23 04:48:02','Hamburguesa Completa',12500,12),(14,0,'2026-06-23 04:48:29','Hamburguesa Completa',12500,13),(15,0,'2026-06-23 05:28:29','Hamburguesa Completa',12500,14);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `eliminado` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `rol` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,0,'2026-06-22 22:25:23','Juan','Perez','juan@mail.com','123456','CLIENTE'),(2,0,'2026-06-22 22:25:23','Ana','Gomez','ana@mail.com','654321','ADMINISTRADOR'),(3,0,'2026-06-23 03:49:18','Ana','Sosa','Ana@email.com',NULL,'CLIENTE'),(5,0,'2026-06-23 03:54:55','Ana','Sosa','Ana2@email.com',NULL,'CLIENTE'),(7,0,'2026-06-23 03:55:17','Ana','Sosa','Ana3@email.com',NULL,'CLIENTE'),(8,0,'2026-06-23 04:43:00','Ana','Sosa','Ana4@email.com',NULL,'CLIENTE'),(11,0,'2026-06-23 04:48:29','Ana','Sosa','Ana5@email.com',NULL,'CLIENTE'),(12,0,'2026-06-23 05:28:29','Ana','Sosa','Ana6@email.com',NULL,'CLIENTE');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-22 17:54:54
