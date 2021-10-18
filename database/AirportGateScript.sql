CREATE DATABASE  IF NOT EXISTS `airportgate` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `airportgate`;
-- MySQL dump 10.13  Distrib 5.7.25, for Win64 (x86_64)
--
-- Host: localhost    Database: airportgate
-- ------------------------------------------------------
-- Server version	5.7.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flight_number` varchar(255) NOT NULL,
  `gate_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_flight_gate1_idx` (`gate_id`),
  FULLTEXT KEY `full_text_search_idx` (`flight_number`),
  CONSTRAINT `FKovob4xje53mpck871fhyicu0` FOREIGN KEY (`gate_id`) REFERENCES `gate` (`id`),
  CONSTRAINT `fk_flight_gate1` FOREIGN KEY (`gate_id`) REFERENCES `gate` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (2,'AC11',2),(5,'AC13',13),(12,'AC17',1),(14,'AB11',14);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gate`
--

DROP TABLE IF EXISTS `gate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gate_name` varchar(255) DEFAULT NULL,
  `gate_opened` datetime DEFAULT NULL,
  `gate_closed` datetime DEFAULT NULL,
  `gate_status_id` int(11) NOT NULL,
  `flight_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gate_gate_status1_idx` (`gate_status_id`),
  KEY `FK5eruecx3xblymmxn2gwg34759` (`flight_id`),
  CONSTRAINT `FK5eruecx3xblymmxn2gwg34759` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`),
  CONSTRAINT `FKa2sb3irjhdln9h01kd2b6nuyi` FOREIGN KEY (`gate_status_id`) REFERENCES `gate_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gate`
--

LOCK TABLES `gate` WRITE;
/*!40000 ALTER TABLE `gate` DISABLE KEYS */;
INSERT INTO `gate` VALUES (1,'Gate1','2021-08-27 10:00:00','2021-08-27 10:15:00',2,NULL),(2,'Gate2','2021-08-28 10:00:00','2021-08-28 10:15:00',2,NULL),(13,'Gate3','2021-08-31 21:10:10','2021-08-31 21:25:10',2,NULL),(14,'Gate4','2021-08-31 22:36:58','2021-08-31 22:36:58',2,NULL);
/*!40000 ALTER TABLE `gate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gate_status`
--

DROP TABLE IF EXISTS `gate_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gate_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gate_status_name` varchar(255) DEFAULT NULL,
  `gate_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgtirkqoa89u6fyisiyplm9arj` (`gate_id`),
  CONSTRAINT `FKgtirkqoa89u6fyisiyplm9arj` FOREIGN KEY (`gate_id`) REFERENCES `gate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gate_status`
--

LOCK TABLES `gate_status` WRITE;
/*!40000 ALTER TABLE `gate_status` DISABLE KEYS */;
INSERT INTO `gate_status` VALUES (1,'Available',NULL),(2,'Unavailable',NULL);
/*!40000 ALTER TABLE `gate_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `enable` bit(1) DEFAULT NULL,
  `encrypted_password` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'admin@gmail.com',_binary '','$2a$10$AeE0drDFSXv6B.Qnear8rumEkUOLjj6e3WgXc4EtqLnMTiFHqA6ve','admin','admin','06090980811','admin'),(4,'goran@gmail.com',_binary '','$2a$10$Y1ss.1nlxRV/heOHJSCezeoF91KljRTA8D7TRXhu55c88clWoBOSC','Goran','Colak','065-939-2255','goran'),(8,'zarko@yahoo.com',_binary '','$2a$10$GNxGvhqJx71AUyvDwkwWY.a6tNNxm3wCob0gNd9WOsjqPgS2rNhqG','Zarko','Zarkovic','065-939-2255','zarko');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_roles`
--

DROP TABLE IF EXISTS `person_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_roles` (
  `person_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`person_id`,`role_id`),
  KEY `FKeylgk8k8teqaxmadsiaixinfe` (`role_id`),
  CONSTRAINT `FKeylgk8k8teqaxmadsiaixinfe` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKs955luj19xyjwi3s1omo1pgh4` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_roles`
--

LOCK TABLES `person_roles` WRITE;
/*!40000 ALTER TABLE `person_roles` DISABLE KEYS */;
INSERT INTO `person_roles` VALUES (1,10),(4,20);
/*!40000 ALTER TABLE `person_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (10,'ROLE_ADMIN'),(20,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-31 22:42:26
