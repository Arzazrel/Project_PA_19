CREATE DATABASE  IF NOT EXISTS `db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: db
-- ------------------------------------------------------
-- Server version	5.6.44-log

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
-- Table structure for table `sessioni`
--

DROP TABLE IF EXISTS `sessioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessioni` (
  `nickName` varchar(20) NOT NULL,
  `durata` varchar(20) NOT NULL,
  `km` float NOT NULL,
  `altitudine` int(11) NOT NULL,
  `pendenza` float NOT NULL,
  `provincia` varchar(20) NOT NULL,
  `velocita` float NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessioni`
--

LOCK TABLES `sessioni` WRITE;
/*!40000 ALTER TABLE `sessioni` DISABLE KEYS */;
INSERT INTO `sessioni` VALUES ('Alex','30:00',8,0,0,'PI',16,'2019-10-05'),('Lori','10:00',1.5,0,0,'MS',9,'2019-09-15'),('Lori','15:00',3,0,0,'MS',12,'2019-09-20'),('Alex','18:00',3.7,0,0,'PI',12.3333,'2019-09-27'),('Lori','20:00',4,0,0,'MS',12,'2019-09-22'),('Alex','15:00',3,0,0,'PI',12,'2019-10-01'),('Alex','15:55',4.2,0,0,'PI',15.8325,'2019-11-02'),('Alex','30:00',7.5,0,0,'PI',15,'2019-10-29'),('Alex','25:00',7.1,0,0,'PI',17.04,'2019-10-21'),('Alex','20:39',6.7,0,0,'PI',19.4673,'2019-10-14');
/*!40000 ALTER TABLE `sessioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utenti` (
  `nickName` varchar(20) NOT NULL,
  `peso` float NOT NULL,
  PRIMARY KEY (`nickName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES ('Alex',70),('Lori',85);
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db'
--

--
-- Dumping routines for database 'db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-01 17:38:02
