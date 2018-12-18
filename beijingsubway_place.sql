-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: beijingsubway
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place` (
  `placeName` varchar(80) NOT NULL,
  `staName` varchar(80) DEFAULT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`placeName`),
  KEY `staName` (`staName`),
  CONSTRAINT `place_ibfk_1` FOREIGN KEY (`staName`) REFERENCES `station` (`staName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES ('798 Art District','Wangjing','famous,modern and innovative art museum'),('Beijing Airport ','Beijing Airport',NULL),('Beijing Deshengmen','Jishuitan','Deshengmen Embrasured watchtown is famous cultural heritage from Ming dynasty'),('BUPT Hongfu campus','Huoying','a campus of Beijing University of Posts and Telecommunications'),('BUPT main campus','Xitucheng','main campus of Beijing University of Posts and Telecommunications'),('Changpingxishankou','Changpingxishankou',NULL),('Ciqu','Ciqu',NULL),('Forbidden City','Tiananmendong','famous royal palace of Ming&Qing dynasty, one of the World\'s Five Palace'),('Huoying','Huoying','a historical military district that is ruled by Huo\'s family in Ming Dynasty'),('Jianguomen','Jianguomen',''),('Nanluoguxiang','Nanluoguxiang',''),('National Library','National Library','the largest library in Asia, one of Beijing Ten Buildings in 80\'s '),('Panjiayuan','Panjiayuan',NULL),('Pingguoyuan','Pingguoyuan',NULL),('Songjiazhuang','Songjiazhuang',NULL),('Summer Palace','Beigongmen','famous royal garden in Qing dynasty which is known as the Garden Museum'),('Temple of Heaven','Temple of Heaven','famous sacrificial place of Ming&Qing emperors, 5A tourist attration in national level'),('Tiananmen Square','Tiananmendong','the biggest city square in the word, the origin of many historical events'),('Tuqiao','Tuqiao','a famous royal palace which belongs to Emperor Qianlong and Yongzheng'),('Xierqi','Xierqi',NULL),('Yonghegong','Yonghegong',NULL);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-06 23:10:42
