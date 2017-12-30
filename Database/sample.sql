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
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Artists`
--

LOCK TABLES `Artists` WRITE;
/*!40000 ALTER TABLE `Artists` DISABLE KEYS */;
INSERT INTO `Artists` VALUES (45,'Kursat Basar'),(46,'Test Artist'),(47,'Test Artist'),(48,'Pentagram'),(49,''),(50,'Hamza Bekir'),(51,'Ezhel'),(52,'Athena'),(53,'Manga'),(54,'Cem Adrian'),(55,'Ak?n Eldes, Alper Durmu?, Batu Mutlugil, Berk Arihan, Cem Çat?k'),(56,'LP'),(57,'LP'),(58,'DEMET GÜRHAN, soprano GÜLER DEM?ROVA GYÖFFRY, piyano HAYR? ÇELEB?, anlat?m ve ?iirler'),(59,'Birsen Tezer'),(60,'Birsen Tezer'),(61,'Sertab Erener'),(62,'Burcin Buke, Tahir Aydogdu, Bilgin Canaz'),(63,'Edd Sheeran'),(64,'Maroon 5'),(65,'Lana Del Rey'),(66,'Lady Gaga'),(67,'Lady Gaga'),(68,'Merve Yentur, Puren Ustun, Pinar Bayer, Ipek Gurel'),(69,'Katy Perry'),(70,'Sam Smith'),(71,'The Killers');
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attendees`
--

LOCK TABLES `Attendees` WRITE;
/*!40000 ALTER TABLE `Attendees` DISABLE KEYS */;
INSERT INTO `Attendees` VALUES (4,24,1,54,NULL),(5,27,1,56,NULL),(6,31,1,56,NULL),(7,26,1,56,NULL),(8,28,1,56,NULL),(9,27,1,55,NULL),(10,24,1,55,NULL),(11,25,1,55,NULL),(12,28,1,57,NULL),(13,25,1,58,NULL),(14,28,1,58,NULL),(15,36,1,58,NULL),(16,31,1,59,NULL),(17,36,1,59,NULL),(18,31,1,59,NULL),(19,31,1,61,NULL),(20,36,1,61,NULL),(21,40,1,61,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comments`
--

LOCK TABLES `Comments` WRITE;
/*!40000 ALTER TABLE `Comments` DISABLE KEYS */;
INSERT INTO `Comments` VALUES (1,54,22,0,0,'ehehe','2017-12-26 23:34:59',1);
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
  `ticket` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Concerts_Users` (`created_by`),
  KEY `FK_Concerts_Location` (`location`),
  KEY `FK_Concerts_Artists` (`artist`),
  CONSTRAINT `FK_Concerts_Artists` FOREIGN KEY (`artist`) REFERENCES `Artists` (`id`),
  CONSTRAINT `FK_Concerts_Location` FOREIGN KEY (`location`) REFERENCES `Locations` (`id`),
  CONSTRAINT `FK_Concerts_Users` FOREIGN KEY (`created_by`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Concerts`
--

LOCK TABLES `Concerts` WRITE;
/*!40000 ALTER TABLE `Concerts` DISABLE KEYS */;
INSERT INTO `Concerts` VALUES (24,'Loc Test',54,50,43,'2017-12-29 13:00:00',10,120,0,0,'https://cdn.londonandpartners.com/visit/london-organisations/alexandra-palace/92923-640x360-alexandra-palace-gig-640.jpg','http://www.biletix.com/etkinlik-grup/134359460/TURKIYE/tr'),(25,'Ezhel',55,51,44,'2018-02-08 21:00:00',35,35,0,0,'https://etkinlik.bursadanerede.com/wp-content/uploads/2017/11/ezhel-bursa-konseri-3.jpg',''),(26,'Athena',55,52,45,'2018-03-10 20:00:00',55,55,0,0,'http://i.milliyet.com.tr/YeniAnaResim/2014/11/15/fft99_mf4977804.Jpeg',''),(27,'Manga',55,53,46,'2018-12-29 21:00:00',60,100,0,0,'http://cdn.esmedya.com.tr/Documents/alem_fm/images/2017/09/23/719x400_d18286aa-6aed-4692-9e8d-a162ad7e1c0e.jpg',''),(28,'Cem Adrian',55,54,47,'2018-01-12 21:00:00',56,56,0,0,'http://sgm.sabanciuniv.edu/sites/sgm.sabanciuniv.edu/files/cem_kucuk-_copy.jpg',''),(29,'Lokalize: Blues Gitaristleri Gecesi Vol.7',55,55,48,'2018-01-25 20:00:00',45,60,0,0,'https://www.fofomo.com/img/r1lm9XXJGz',''),(30,'25. Istanbul Caz Festivali Ozel Konseri: LP',55,56,49,'2018-03-31 20:30:00',169,225,0,0,'http://playtusu.com/wp-content/uploads/2017/12/LP-press-photo-bw-2016-billboard-1548.jpg',''),(31,'25. Istanbul Caz Festivali Ozel Konseri: LP',55,57,50,'2018-04-01 20:00:00',125,225,0,0,'http://playtusu.com/wp-content/uploads/2017/12/LP-press-photo-bw-2016-billboard-1548.jpg',''),(32,'Nazim Hikmeti Anma Konseri',55,58,51,'2018-01-13 20:00:00',56,56,0,0,'http://im.haberturk.com/2016/06/03/ver1495201105/1248542_5baebaa51fdb14f086260ce81bef493b.jpg',''),(34,'Birsen Tezer',55,60,53,'2017-09-16 22:00:00',39,137,0,0,'http://www.birsentezer.com/wp-content/uploads/2015/10/birsen-tezer.jpg',''),(35,'Tanini Trio',57,62,55,'2018-04-07 20:00:00',60,100,0,0,'https://www.havadiskibris.com/wp-content/uploads/2017/10/Tanini-Trio-364x245.jpg',''),(36,'Edd Sheeran',58,63,56,'2018-03-24 17:30:00',637,637,0,0,'http://themixradio.co.uk/wp-content/uploads/2017/01/ed-sheeran-011.jpg','https://checkout.ticketcity.com/checkout/checkout4.action?productionId=2348862&ticketId=VB1425821105&wsUser=455&wsVar=aid_0%2Bdis%3D&affiliateRedirectURL=https%3A%2F%2Fwww.ticketcity.com%2Fmount-smart-stadium-tickets%2Fed-sheeran-mar-24-2018-2348862.html&quantity=4'),(37,'Maroon 5 Concert',58,64,57,'2018-05-30 19:30:00',62,433,0,0,'https://www.billboard.com/files/styles/article_main_image/public/media/maroon-5-upclose-terryrichardson650.jpg','https://www.ticketcity.com/seattle-tickets/tacoma-dome-tickets/maroon-5-may-30-2018-2507357.html'),(38,'Lana Del Rey ',58,65,58,'2018-01-05 20:00:00',80,595,0,0,'https://espngrantland.files.wordpress.com/2014/06/hp_interscope_lanadelrey_800.jpg','https://www.ticketcity.com/minneapolis-tickets/target-center-tickets/lana-del-rey-jan-5-2018-2477165.html'),(39,'Lady Gaga Concert',59,66,59,'2018-01-14 21:00:00',273,304,0,0,'http://showstudio.com/img/contributors/201-400/299_960n.jpg?1314024774','https://www.ticketcity.com/palau-sant-jordi-tickets/lady-gaga-jan-14-2018-2496001.html'),(40,'Lady Gaga Concert',59,67,60,'2018-02-13 19:30:00',273,445,0,0,'http://showstudio.com/img/contributors/201-400/299_960n.jpg?1314024774','https://www.ticketcity.com/lanxess-arena-tickets/lady-gaga-feb-13-2018-2495996.html'),(41,'Tangolar ve Valsler ile Yilbasi Konseri',60,68,61,'2019-12-30 20:00:00',60,60,0,0,'https://www.etkinankara.com/websitem/Sayfalar/Etkinlikler/EtkinlikResim-Fiziksel.asp?f=etkinlik%5C4e2a72ea0dad8cfedd902163b58ac7c4_8491119_1.jpg&genislik=200&yukseklik=280','http://www.biletix.com/etkinlik/UMG66/DIGER/tr'),(42,'Katy Perry',60,69,62,'2018-01-05 19:30:00',22,6366,0,0,'https://i.ytimg.com/vi/CevxZvSJLk8/maxresdefault.jpg','https://www.ticketcity.com/new-orleans-tickets/smoothie-king-center-tickets/katy-perry-jan-5-2018-2348140.html'),(43,'Sam Smith',60,70,63,'2019-06-06 20:00:00',128,5026,0,0,'https://media.pitchfork.com/photos/59298fd8c0084474cd0be3be/1:1/w_300/013f677e.jpg','https://www.ticketcity.com/toronto-tickets/air-canada-centre-tickets/sam-smith-jun-18-2018-2486113.html'),(44,'The Killers',60,71,64,'2018-01-09 20:00:00',71,920,0,0,'https://ichef.bbci.co.uk/images/ic/960x540/p01bqj0c.jpg','https://www.ticketcity.com/new-york-city-tickets/barclays-center-tickets/the-killers-jan-9-2018-2413469.html');
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
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Locations`
--

LOCK TABLES `Locations` WRITE;
/*!40000 ALTER TABLE `Locations` DISABLE KEYS */;
INSERT INTO `Locations` VALUES (38,45.12312,63.43432,'Trump Kultur Merkezi','Trump Kultur Merkezi'),(39,45.12312,63.43432,'Beykoz','Beykoz'),(40,45.12312,63.43432,'Istanbul','Istanbul'),(41,45.12312,63.43432,'Zorlu PSM','Zorlu PSM'),(42,45.12312,63.43432,'null','null'),(43,28.978551400000015,41.0349374,'Hüseyina?a Mahallesi, Balo Sokak No:22','Hüseyina?a Mahallesi, Balo Sokak No:22'),(44,28.977475000000027,41.0349,'Hüseyina?a Mahallesi, Kameriye Sokak No:13','Hüseyina?a Mahallesi, Kameriye Sokak No:13'),(45,29.121495299999992,37.8261388,'Eskihisar Mahallesi','Eskihisar Mahallesi'),(46,32.51797829999998,38.0066078,'undefined','undefined'),(47,28.977701000000025,40.221691,'Cumhuriyet Mahallesi, 16210, Fatih Sultan Mehmet Bulvar? No:59','Cumhuriyet Mahallesi, 16210, Fatih Sultan Mehmet Bulvar? No:59'),(48,29.017424600000027,41.0660865,'Levaz?m Mah., Koru Sok. No:2, Be?ikta?','Levaz?m Mah., Koru Sok. No:2, Be?ikta?'),(49,32.8035989,39.9116342,'Sö?ütözü Mahallesi, Sö?ütözü Caddesi No:1','Sö?ütözü Mahallesi, Sö?ütözü Caddesi No:1'),(50,29.017424600000027,41.0660865,'Levaz?m Mah., Koru Sok. No:2, Be?ikta?','Levaz?m Mah., Koru Sok. No:2, Be?ikta?'),(51,32.85930840000003,39.8942025,'Çankaya Mahallesi, Nilgün Sokak Ba? Apartman? D:14/2','Çankaya Mahallesi, Nilgün Sokak Ba? Apartman? D:14/2'),(52,28.977475000000027,41.0349,'Hüseyina?a Mahallesi, Kameriye Sokak No:13','Hüseyina?a Mahallesi, Kameriye Sokak No:13'),(53,28.977475000000027,41.0349,'Hüseyina?a Mahallesi, Kameriye Sokak No:13','Hüseyina?a Mahallesi, Kameriye Sokak No:13'),(54,29.007756900000004,41.1081011,'Huzur Mahallesi, Maslak Ayaza?a Caddesi 2 b','Huzur Mahallesi, Maslak Ayaza?a Caddesi 2 b'),(55,29.00540019999994,41.041548,'Sinanpa?a Mahallesi, Be?ikta? Caddesi 6/1','Sinanpa?a Mahallesi, Be?ikta? Caddesi 6/1'),(56,174.81236869999998,-36.91828780000001,'2 Beasley Avenue, Penrose, Auckland','2 Beasley Avenue, Penrose, Auckland'),(57,-122.42702989999998,47.2366694,'2727 East D Street, Tacoma','2727 East D Street, Tacoma'),(58,-93.27618989999996,44.9794545,'600 North 1st Avenue, Minneapolis','600 North 1st Avenue, Minneapolis'),(59,2.1529540000000225,41.361585,'Spain','Spain'),(60,6.982951100000037,50.9383376,'Willy-Brandt-Platz 3, Köln','Willy-Brandt-Platz 3, Köln'),(61,32.85930840000003,39.8942025,'Çankaya Mahallesi, Nilgün Sokak Ba? Apartman? D:14/2','Çankaya Mahallesi, Nilgün Sokak Ba? Apartman? D:14/2'),(62,-90.08205679999998,29.9490351,'1501 Dave Dixon Drive, New Orleans','1501 Dave Dixon Drive, New Orleans'),(63,-79.37818900000002,43.643887,'Canada','Canada'),(64,-73.97503490000003,40.68249520000001,'620 Atlantic Avenue, Brooklyn','620 Atlantic Avenue, Brooklyn');
/*!40000 ALTER TABLE `Locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MusicalInterests`
--

DROP TABLE IF EXISTS `MusicalInterests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MusicalInterests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(100) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `musicalInterestId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MusicalInterests`
--

LOCK TABLES `MusicalInterests` WRITE;
/*!40000 ALTER TABLE `MusicalInterests` DISABLE KEYS */;
/*!40000 ALTER TABLE `MusicalInterests` ENABLE KEYS */;
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
  `content` varchar(200) NOT NULL,
  `target_id` int(11) NOT NULL,
  `concert_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Notifications_Users` (`target_id`),
  CONSTRAINT `FK_Notifications_Users` FOREIGN KEY (`target_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notifications`
--

LOCK TABLES `Notifications` WRITE;
/*!40000 ALTER TABLE `Notifications` DISABLE KEYS */;
INSERT INTO `Notifications` VALUES (4,'test is attending to Loc Test.',48,24),(5,'test is attending to Loc Test.',51,24);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Relations`
--

LOCK TABLES `Relations` WRITE;
/*!40000 ALTER TABLE `Relations` DISABLE KEYS */;
INSERT INTO `Relations` VALUES (2,48,54,NULL),(3,51,54,NULL),(4,55,56,NULL);
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
-- Table structure for table `SemanticTags`
--

DROP TABLE IF EXISTS `SemanticTags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SemanticTags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `concert_id` int(11) NOT NULL,
  `semanticTagId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SemanticTags`
--

LOCK TABLES `SemanticTags` WRITE;
/*!40000 ALTER TABLE `SemanticTags` DISABLE KEYS */;
/*!40000 ALTER TABLE `SemanticTags` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (48,'Fatih Güven','fatih.guven@hotmail.com',NULL,0,0,'https://scontent.xx.fbcdn.net/v/t1.0-1/p200x200/12042817_10206845907799008_1170828127403682503_n.jpg?oh=0690ae3394ceb9d99b87b10dc5063031&oe=5A9B890B','2017-12-04 20:22:53','2017-12-04 20:22:53','2017-12-04 20:22:53',NULL,'11128938007'),(51,'Test User','tuser@gmail.com','654321',0,0,'https://www.randomlists.com/img/people/arnold_schwarzenegger.jpg','2017-12-06 00:05:57','2017-12-06 00:05:57','2017-12-06 00:05:57','tuser',NULL),(54,'test','test@test.com','121212',0,0,'https://i1.wp.com/visboo.com/img/29042010/66120.jpg','2017-12-26 23:34:23','2017-12-26 23:34:23','2017-12-26 23:34:23','test',NULL),(55,'begun','begun@gmail.com','begun',0,0,'','2017-12-28 05:45:58','2017-12-28 05:45:58','2017-12-28 05:45:58','unal',NULL),(56,'hazan','hazan@gmail.com','hazan',0,0,'','2017-12-28 07:51:00','2017-12-28 07:51:00','2017-12-28 07:51:00','akgul',NULL),(57,'harun','harun@gmail.com','harun',0,0,'https://pbs.twimg.com/profile_images/527780533250818048/4rbs2Ooz_400x400.jpeg','2017-12-30 13:46:22','2017-12-30 13:46:22','2017-12-30 13:46:22','zengin',NULL),(58,'Dogacan','dogacan@gmail.com','dogacan',0,0,'https://pbs.twimg.com/media/BdF51lkIAAA8ukT.jpg','2017-12-30 14:00:34','2017-12-30 14:00:34','2017-12-30 14:00:34','Dorum',NULL),(59,'Efehan','efehan@gmail.com','efehan',0,0,'https://camo.githubusercontent.com/c528237252c0c9781e1515b80a0d483aa3859a16/68747470733a2f2f6865792e732d756c2e65752f314f4b3543344b45','2017-12-30 14:24:31','2017-12-30 14:24:31','2017-12-30 14:24:31','Atici',NULL),(60,'Ihsan','ihsan@gmail.com','ihsan',0,0,'https://www.facebook.com/photo.php?fbid=10205938737723480&set=a.1542599680849.2073419.1110664848&type=3&theater','2017-12-30 14:54:58','2017-12-30 14:54:58','2017-12-30 14:54:58','Ozturk',NULL),(61,'Necip','necip@gmail.com','necip',0,0,'http://cdn.webtekno.com/media/cache/content_detail_v2/article/28436/projelerine-yatirimci-bulamayanlara-derman-olan-olusum-cay-kahve-insan-1493383846.jpg','2017-12-30 15:24:16','2017-12-30 15:24:16','2017-12-30 15:24:16','Ergun',NULL),(62,'Emre ','emre@gmail.com','emre',0,0,'https://pbs.twimg.com/profile_images/767728003417858049/1d2oMG0f.jpg','2017-12-30 15:25:58','2017-12-30 15:25:58','2017-12-30 15:25:58','Kahreman',NULL),(63,'Mehmet','mehmet@gmail.com','mehmet',0,0,'http://www.trendsokak.com/wp-content/uploads/2015/11/doktor-nedir.jpg','2017-12-30 15:28:19','2017-12-30 15:28:19','2017-12-30 15:28:19','Akif',NULL),(64,'Kasim','kasim@gmail.com','kasim',0,0,'http://www.newdriverni.com/wp-content/uploads/2016/01/Very-handsome-businessman-with-beautiful-blue-eyes.jpg','2017-12-30 15:34:30','2017-12-30 15:34:30','2017-12-30 15:34:30','Bozdag',NULL),(65,'bugrahan','','',0,0,'','2017-12-30 15:35:20','2017-12-30 15:35:20','2017-12-30 15:35:20','tasdan',NULL),(66,'Bugrahan','bugrahan@gmail.com','bugrahan',0,0,'https://media-exp1.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAtpAAAAJDc0MmEzNzBmLTE3NTctNGYwNi1hMmM4LWJkMDM1ZTQzMDM3MA.jpg','2017-12-30 15:36:11','2017-12-30 15:36:11','2017-12-30 15:36:11','Tasdan',NULL);
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WordVec`
--

DROP TABLE IF EXISTS `WordVec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WordVec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `base` varchar(100) DEFAULT NULL,
  `compare` varchar(100) DEFAULT NULL,
  `score` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WordVec`
--

LOCK TABLES `WordVec` WRITE;
/*!40000 ALTER TABLE `WordVec` DISABLE KEYS */;
/*!40000 ALTER TABLE `WordVec` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-30 15:41:15
