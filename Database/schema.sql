-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: concerter_db
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `Artists`
--

DROP TABLE IF EXISTS `Artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Artists` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Artists`
--

LOCK TABLES `Artists` WRITE;
/*!40000 ALTER TABLE `Artists` DISABLE KEYS */;
INSERT INTO `Artists` VALUES (45,'Kursat Basar'),(46,'Test Artist');
/*!40000 ALTER TABLE `Artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Attendees`
--

DROP TABLE IF EXISTS `Attendees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Attendees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `concert_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Attendees_Concerts` (`concert_id`),
  KEY `FK_Attendees_Users` (`user_id`),
  CONSTRAINT `FK_Attendees_Concerts` FOREIGN KEY (`concert_id`) REFERENCES `Concerts` (`id`),
  CONSTRAINT `FK_Attendees_Users` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attendees`
--

LOCK TABLES `Attendees` WRITE;
/*!40000 ALTER TABLE `Attendees` DISABLE KEYS */;
INSERT INTO `Attendees` VALUES (10,20,1,48,NULL),(11,21,1,48,NULL);
/*!40000 ALTER TABLE `Attendees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comments`
--

DROP TABLE IF EXISTS `Comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commented_by` int(11) NOT NULL,
  `concert_id` int(11) NOT NULL,
  `up_votes` int(11) NOT NULL DEFAULT '0',
  `down_votes` int(11) NOT NULL DEFAULT '0',
  `comment` varchar(100) NOT NULL,
  `date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Comments_Users` (`commented_by`),
  CONSTRAINT `FK_Comments_Users` FOREIGN KEY (`commented_by`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comments`
--

LOCK TABLES `Comments` WRITE;
/*!40000 ALTER TABLE `Comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comments_categories`
--

DROP TABLE IF EXISTS `Comments_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comments_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comments_categories`
--

LOCK TABLES `Comments_categories` WRITE;
/*!40000 ALTER TABLE `Comments_categories` DISABLE KEYS */;
INSERT INTO `Comments_categories` VALUES (1,'Costume'),(2,'Music'),(3,'Place'),(4,'Foods');
/*!40000 ALTER TABLE `Comments_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Concerts`
--

DROP TABLE IF EXISTS `Concerts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Concerts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `created_by` int(11) NOT NULL,
  `artist` int(11) DEFAULT NULL,
  `location` int(11) NOT NULL,
  `date_time` datetime DEFAULT NULL,
  `min_price` int(11) NOT NULL,
  `max_price` int(11) NOT NULL,
  `rate` int(11) NOT NULL DEFAULT '0',
  `voter_amount` int(11) NOT NULL DEFAULT '0',
  `image_path` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Concerts_Users` (`created_by`),
  KEY `FK_Concerts_Location` (`location`),
  KEY `FK_Concerts_Artists` (`artist`),
  CONSTRAINT `FK_Concerts_Artists` FOREIGN KEY (`artist`) REFERENCES `Artists` (`id`),
  CONSTRAINT `FK_Concerts_Location` FOREIGN KEY (`location`) REFERENCES `Locations` (`id`),
  CONSTRAINT `FK_Concerts_Users` FOREIGN KEY (`created_by`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Concerts`
--

LOCK TABLES `Concerts` WRITE;
/*!40000 ALTER TABLE `Concerts` DISABLE KEYS */;
INSERT INTO `Concerts` VALUES (20,'Kursat Basar Orkestrasi ve Jale',48,45,38,'2017-12-10 15:00:00',10,100,0,0,'http://www.wts.com.tr/images/konserler-wts.jpg'),(21,'Test Concert',48,46,39,'2018-12-17 16:00:00',20,120,0,0,'https://sc-events.s3.amazonaws.com/4315318/main.jpg');
/*!40000 ALTER TABLE `Concerts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Locations`
--

DROP TABLE IF EXISTS `Locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Locations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Locations`
--

LOCK TABLES `Locations` WRITE;
/*!40000 ALTER TABLE `Locations` DISABLE KEYS */;
INSERT INTO `Locations` VALUES (38,45.12312,63.43432,'Trump Kultur Merkezi','Trump Kultur Merkezi'),(39,45.12312,63.43432,'Beykoz','Beykoz');
/*!40000 ALTER TABLE `Locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notification_type`
--

DROP TABLE IF EXISTS `Notification_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Notification_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notification_type`
--

LOCK TABLES `Notification_type` WRITE;
/*!40000 ALTER TABLE `Notification_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `Notification_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notifications`
--

DROP TABLE IF EXISTS `Notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL,
  `content` varchar(200) NOT NULL,
  `target_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Notifications_Notification_type` (`type_id`),
  KEY `FK_Notifications_Users` (`target_id`),
  CONSTRAINT `FK_Notifications_Notification_type` FOREIGN KEY (`type_id`) REFERENCES `Notification_type` (`id`),
  CONSTRAINT `FK_Notifications_Users` FOREIGN KEY (`target_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notifications`
--

LOCK TABLES `Notifications` WRITE;
/*!40000 ALTER TABLE `Notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `Notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Relations`
--

DROP TABLE IF EXISTS `Relations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Relations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `follower_id` int(11) NOT NULL,
  `following_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relations_Users_Following` (`follower_id`),
  CONSTRAINT `FK_Relations_Users_Follower` FOREIGN KEY (`follower_id`) REFERENCES `Users` (`id`),
  CONSTRAINT `FK_Relations_Users_Following` FOREIGN KEY (`follower_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Relations`
--

LOCK TABLES `Relations` WRITE;
/*!40000 ALTER TABLE `Relations` DISABLE KEYS */;
/*!40000 ALTER TABLE `Relations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Report_types`
--

DROP TABLE IF EXISTS `Report_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Report_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Report_types`
--

LOCK TABLES `Report_types` WRITE;
/*!40000 ALTER TABLE `Report_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `Report_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reports`
--

DROP TABLE IF EXISTS `Reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `concert_id` int(11) NOT NULL,
  `reported_by` int(11) NOT NULL,
  `report_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reports_Concerts` (`concert_id`),
  KEY `FK_Reports_Users` (`reported_by`),
  KEY `FK_Reports_Report_type` (`report_type`),
  CONSTRAINT `FK_Reports_Concerts` FOREIGN KEY (`concert_id`) REFERENCES `Concerts` (`id`),
  CONSTRAINT `FK_Reports_Report_type` FOREIGN KEY (`report_type`) REFERENCES `Report_types` (`id`),
  CONSTRAINT `FK_Reports_Users` FOREIGN KEY (`reported_by`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reports`
--

LOCK TABLES `Reports` WRITE;
/*!40000 ALTER TABLE `Reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) DEFAULT NULL,
  `followers` int(11) DEFAULT '0',
  `followings` int(11) DEFAULT '0',
  `photo_path` varchar(255) NOT NULL DEFAULT '',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `spotify_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (48,'Fatih Güven','fatih.guven@hotmail.com',NULL,0,0,'https://scontent.xx.fbcdn.net/v/t1.0-1/p200x200/12042817_10206845907799008_1170828127403682503_n.jpg?oh=0690ae3394ceb9d99b87b10dc5063031&oe=5A9B890B','2017-12-04 20:22:53','2017-12-04 20:22:53','2017-12-04 20:22:53',NULL,'11128938007');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-04 23:48:21
