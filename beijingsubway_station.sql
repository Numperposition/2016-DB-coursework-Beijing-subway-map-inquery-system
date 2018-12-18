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
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station` (
  `staName` varchar(80) NOT NULL,
  `subNo` int(11) NOT NULL,
  `staNo` int(11) DEFAULT NULL,
  `nextDis` int(11) DEFAULT NULL,
  PRIMARY KEY (`staName`,`subNo`),
  KEY `subNo` (`subNo`),
  CONSTRAINT `station_ibfk_1` FOREIGN KEY (`subNo`) REFERENCES `subline` (`subNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES ('Anheqiaobei',4,1,4286),('Babaoshan',1,2,5067),('Bagou',10,1,2060),('Baishiqiaonan',6,3,2551),('Baishiqiaonan',9,2,2855),('Beigongmen',4,2,3082),('Beijing Airport',3,3,6772),('Beijing Railway Station',2,7,945),('Beijing South Station',4,11,2307),('Beijing West Railway Station',7,1,4917),('Beijing West Railway Station',9,4,2479),('Beijing Zoo',4,5,1441),('Beitucheng',8,5,3375),('Beitucheng',10,5,2002),('Caishikou',4,10,2843),('Caishikou',7,2,3975),('Changping',16,2,13395),('Changpingxishankou',16,1,4721),('Changyang',12,2,6815),('Chaoyangmen',2,9,1851),('Chaoyangmen',6,8,2513),('Chegongzhuang',2,2,2792),('Chegongzhuang',6,4,1443),('Chongwenmen',2,6,1023),('Chongwenmen',5,8,876),('Chuanmei university',14,2,7269),('Ciqikou',5,9,1183),('Ciqikou',7,3,5022),('Ciqu',11,4,0),('Cishousi',6,2,2597),('Cishousi',10,18,5251),('Datunludong',5,3,2960),('Datunludong',15,3,3158),('Dazhongsi',13,2,1206),('Dongdan',1,9,1230),('Dongdan',5,7,821),('Dongsi',5,6,1793),('Dongsi',6,7,1399),('Dongzhimen',2,10,2228),('Dongzhimen',3,1,2999),('Dongzhimen',13,10,0),('Fengbo',15,7,0),('Fengtaizhan',10,14,1703),('Fuxingmen',1,6,1590),('Fuxingmen',2,3,2163),('Gongzhufen',1,4,1172),('Gongzhufen',10,17,3600),('Guloudajie',2,12,1923),('Guloudajie',8,6,1188),('Guogongzhuang',9,8,0),('Guogongzhuang',12,1,11912),('Guomao',1,11,4772),('Guomao',10,10,4883),('Guozhan',15,6,16029),('Haidianhuangzhuang',4,3,3772),('Haidianhuangzhuang',10,2,2033),('Haidianwuluju',6,1,1508),('Huanlegu',7,5,4004),('Huilongguan',13,5,2110),('Huixinxijienankou',5,4,3235),('Huixinxijienankou',10,6,1712),('Hujialou',6,9,1450),('Hujialou',10,9,1569),('Huoying',8,3,10602),('Huoying',13,6,4785),('Jianguomen',1,10,2167),('Jianguomen',2,8,1763),('Jiaohuachang',7,6,0),('Jiaomenxi',4,12,1845),('Jiaomenxi',10,13,6095),('Jintailu',6,10,23672),('Jishuitan',2,13,2014),('Jiulongshan',7,4,4919),('Keyilu',9,7,2135),('Liangxiangdaxuecheng',12,3,4400),('Lishuiqiao',5,2,5591),('Lishuiqiao',13,7,8992),('Liuliqiao',9,5,1778),('Liuliqiao',10,16,3408),('Lucheng',6,11,0),('Military Museum',1,5,2881),('Military Museum',9,3,1398),('Nanluoguxiang',6,6,1937),('Nanluoguxiang',8,8,0),('National Library',4,4,1517),('National Library',9,1,1096),('Olympic Park',8,4,2567),('Olympic Park',15,2,4305),('Panjiayuan',10,11,4539),('Pinganli',4,7,2980),('Pinganli',6,5,2670),('Pingguoyuan',1,1,6480),('Pingxifu',8,2,3170),('Puhuangyu',5,11,2575),('Qianmen',2,5,1634),('Qilizhuang',9,6,3890),('Qinghuadongluxikou',15,1,2481),('Sanyuanqiao',3,2,18329),('Sanyuanqiao',10,8,4422),('Shahe',16,3,5824),('Shaoyaoju',10,7,2762),('Shaoyaoju',13,9,4014),('Shishahai',8,7,902),('Sihuidong',1,12,0),('Sihuidong',14,1,3377),('Songjiazhuang',5,12,0),('Songjiazhuang',10,12,4897),('Songjiazhuang',11,1,8254),('Suzhuang',12,4,0),('Temple of Heaven',5,10,1900),('Tiananmendong',1,8,1626),('Tiangongyuan',4,13,0),('Tiantongyuanbei',5,1,3448),('Tongjinanlu',11,3,5637),('Tongzhoubeiyuan',14,3,5713),('Tuqiao',14,4,0),('Wangjing',15,5,12650),('Wangjingxi',13,8,2152),('Wangjingxi',15,4,1758),('Wanshoulu',1,3,1313),('Xidan',1,7,2142),('Xidan',4,8,815),('Xierqi',13,4,5046),('Xierqi',16,5,0),('Xiju',10,15,1584),('Xitucheng',10,4,3403),('Xizhimen',2,1,909),('Xizhimen',4,6,2125),('Xizhimen',13,1,2839),('Xuanwumen',2,4,2022),('Xuanwumen',4,9,1152),('Yizhuangqiao',11,2,7503),('Yonghegong',2,11,794),('Yonghegong',5,5,2673),('Zhichunlu',10,3,1101),('Zhichunlu',13,3,9233),('Zhuxinzhuang',8,1,4303),('Zhuxinzhuang',16,4,7807);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
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
