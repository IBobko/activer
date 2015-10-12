-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: activer
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_username` varchar(255) NOT NULL,
  `account_password` varchar(255) NOT NULL,
  `account_email` varchar(255) NOT NULL,
  `account_firstname` varchar(255) NOT NULL,
  `account_lastname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account_username` (`account_username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (NULL,'limit-speed@yandex.ru','123456','limit-speed@yandex.ru','Igor','Bobko');
INSERT INTO `account` VALUES (NULL,'jamilla.jousma@example.com','123456','jamilla.jousma@example.com','Jousma','Jamilla');
INSERT INTO `account` VALUES (NULL,'joan.fernandez@example.com','123456','joan.fernandez@example.com','Fernandez','Joan');
INSERT INTO `account` VALUES (NULL,'emmie.fabre@example.com','123456','emmie.fabre@example.com','Fabre','Emmie');
INSERT INTO `account` VALUES (NULL,'ilze.doelman@example.com','123456','ilze.doelman@example.com','Doelman','Ilze');
INSERT INTO `account` VALUES (NULL,'nicoline.holtkamp@example.com','123456','nicoline.holtkamp@example.com','Holtkamp','Nicoline');
INSERT INTO `account` VALUES (NULL,'donna.bell@example.com','123456','donna.bell@example.com','Bell','Donna');
INSERT INTO `account` VALUES (NULL,'genesis.west@example.com','123456','genesis.west@example.com','West','Genesis');
INSERT INTO `account` VALUES (NULL,'pihla.pesola@example.com','123456','pihla.pesola@example.com','Pesola','Pihla');
INSERT INTO `account` VALUES (NULL,'landon.ellis@example.com','123456','landon.ellis@example.com','Ellis','Landon');
INSERT INTO `account` VALUES (NULL,'milton.turner@example.com','123456','milton.turner@example.com','Turner','Milton');
INSERT INTO `account` VALUES (NULL,'dianne.roest@example.com','123456','dianne.roest@example.com','Roest','Dianne');
INSERT INTO `account` VALUES (NULL,'audrey.scott@example.com','123456','audrey.scott@example.com','Scott','Audrey');
INSERT INTO `account` VALUES (NULL,'jay.crawford@example.com','123456','jay.crawford@example.com','Crawford','Jay');
INSERT INTO `account` VALUES (NULL,'mia.henry@example.com','123456','mia.henry@example.com','Henry','Mia');
INSERT INTO `account` VALUES (NULL,'valtteri.tanner@example.com','123456','valtteri.tanner@example.com','Tanner','Valtteri');
INSERT INTO `account` VALUES (NULL,'isla.jarvela@example.com','123456','isla.jarvela@example.com','Jarvela','Isla');
INSERT INTO `account` VALUES (NULL,'judith.watson@example.com','123456','judith.watson@example.com','Watson','Judith');
INSERT INTO `account` VALUES (NULL,'iiris.salminen@example.com','123456','iiris.salminen@example.com','Salminen','Iiris');
INSERT INTO `account` VALUES (NULL,'tiffany.wagner@example.com','123456','tiffany.wagner@example.com','Wagner','Tiffany');
INSERT INTO `account` VALUES (NULL,'lesa.garrett@example.com','123456','lesa.garrett@example.com','Garrett','Lesa');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_friend_relation`
--

DROP TABLE IF EXISTS `account_friend_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_friend_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `friend_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_friend_relation`
--

LOCK TABLES `account_friend_relation` WRITE;
/*!40000 ALTER TABLE `account_friend_relation` DISABLE KEYS */;
INSERT INTO `account_friend_relation` VALUES (1,1,8),(2,1,11),(3,1,10);
/*!40000 ALTER TABLE `account_friend_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_username` varchar(255) NOT NULL,
  `authority_role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'admin','ROLE_BUYER'),(2,'admin','ROLE_SELLER'),(3,'admin','ROLE_ADMIN'),(4,'admin','ROLE_USER'),(5,'buyer','ROLE_BUYER'),(6,'buyer','ROLE_USER'),(7,'seller','ROLE_SELLER'),(8,'seller','ROLE_USER'),(9,'habib@list.ru','ROLE_USER'),(10,'habib@list.ru','ROLE_ADMIN'),(11,'limit-speed@yandex.by','ROLE_USER'),(12,'Liutar@yandex.ru','ROLE_USER'),(13,'iibobko@gmail.com','ROLE_USER'),(14,'iibobko@gmail.com','ROLE_USER'),(15,'limit-speed@yandex.ru','ROLE_USER');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cube3d_country`
--

DROP TABLE IF EXISTS `cube3d_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cube3d_country` (
  `country_code` varchar(2) DEFAULT NULL,
  `country_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cube3d_country`
--

LOCK TABLES `cube3d_country` WRITE;
/*!40000 ALTER TABLE `cube3d_country` DISABLE KEYS */;
INSERT INTO `cube3d_country` VALUES ('AF','Afghanistan'),('AX','Aland Islands'),('AL','Albania'),('DZ','Algeria'),('AS','American Samoa'),('AD','Andorra'),('AO','Angola'),('AI','Anguilla'),('AG','Antigua and Barbuda'),('AR','Argentina'),('AM','Armenia'),('AW','Aruba'),('AU','Australia'),('AT','Austria'),('AZ','Azerbaijan'),('BH','Bahrain'),('BD','Bangladesh'),('BB','Barbados'),('BY','Belarus'),('BE','Belgium'),('BZ','Belize'),('BJ','Benin'),('BM','Bermuda'),('BT','Bhutan'),('BO','Bolivia'),('BA','Bosnia and Herzegovina'),('BW','Botswana'),('BR','Brazil'),('IO','British Indian Ocean Territory'),('VG','British Virgin Islands'),('BN','Brunei'),('BG','Bulgaria'),('BF','Burkina Faso'),('MM','Burma'),('BI','Burundi'),('KH','Cambodia'),('CM','Cameroon'),('CA','Canada'),('CV','Cape Verde'),('BQ','Caribbean Netherlands'),('KY','Cayman Islands'),('CF','Central African Republic'),('TD','Chad'),('CL','Chile'),('CN','China'),('CX','Christmas Island'),('CO','Colombia'),('KM','Comoros'),('CK','Cook Islands'),('CR','Costa Rica'),('HR','Croatia'),('CU','Cuba'),('CW','Curacao'),('CY','Cyprus'),('CZ','Czech Republic'),('CD','Democratic Republic of the Congo'),('DK','Denmark'),('DJ','Djibouti'),('DM','Dominica'),('DO','Dominican Republic'),('EC','Ecuador'),('EG','Egypt'),('SV','El Salvador'),('GQ','Equatorial Guinea'),('ER','Eritrea'),('EE','Estonia'),('ET','Ethiopia'),('FK','Falkland Islands'),('FO','Faroe Islands'),('FJ','Fiji'),('FI','Finland'),('FR','France'),('GF','French Guiana'),('PF','French Polynesia'),('GA','Gabon'),('GE','Georgia'),('DE','Germany'),('GH','Ghana'),('GI','Gibraltar'),('GR','Greece'),('GL','Greenland'),('GD','Grenada'),('GP','Guadeloupe'),('GU','Guam'),('GT','Guatemala'),('GG','Guernsey'),('GN','Guinea'),('GW','Guinea-Bissau'),('GY','Guyana'),('HT','Haiti'),('HN','Honduras'),('HK','Hong Kong'),('HU','Hungary'),('IS','Iceland'),('IN','India'),('ID','Indonesia'),('IR','Iran'),('IQ','Iraq'),('IE','Ireland'),('IM','Isle of Man'),('IL','Israel'),('IT','Italy'),('CI','Ivory Coast'),('JM','Jamaica'),('JP','Japan'),('JE','Jersey'),('JO','Jordan'),('KZ','Kazakhstan'),('KE','Kenya'),('KI','Kiribati'),('KW','Kuwait'),('KG','Kyrgyzstan'),('LA','Laos'),('LV','Latvia'),('LB','Lebanon'),('LS','Lesotho'),('LR','Liberia'),('LY','Libya'),('LI','Liechtenstein'),('LT','Lithuania'),('LU','Luxembourg'),('MO','Macau'),('MK','Macedonia'),('MG','Madagascar'),('MW','Malawi'),('MY','Malaysia'),('MV','Maldives'),('ML','Mali'),('MT','Malta'),('MH','Marshall Islands'),('MQ','Martinique'),('MR','Mauritania'),('MU','Mauritius'),('YT','Mayotte'),('MX','Mexico'),('FM','Micronesia'),('MD','Moldova'),('MC','Monaco'),('MN','Mongolia'),('ME','Montenegro'),('MS','Montserrat'),('MA','Morocco'),('MZ','Mozambique'),('NA','Namibia'),('NR','Nauru'),('NP','Nepal'),('NL','Netherlands'),('NC','New Caledonia'),('NZ','New Zealand'),('NI','Nicaragua'),('NE','Niger'),('NG','Nigeria'),('NU','Niue'),('NF','Norfolk Island'),('KP','North Korea'),('MP','Northern Mariana Islands'),('NO','Norway'),('OM','Oman'),('PK','Pakistan'),('PW','Palau'),('PS','Palestinian Territory'),('PA','Panama'),('PG','Papua New Guinea'),('PY','Paraguay'),('PE','Peru'),('PH','Philippines'),('PL','Poland'),('PT','Portugal'),('PR','Puerto Rico'),('QA','Qatar'),('CG','Republic of the Congo'),('RE','Reunion'),('RO','Romania'),('RU','Russia'),('RW','Rwanda'),('BL','Saint Barthelemy'),('SH','Saint Helena'),('KN','Saint Kitts and Nevis'),('LC','Saint Lucia'),('MF','Saint Martin'),('PM','Saint Pierre and Miquelon'),('VC','Saint Vincent and the Grenadines'),('WS','Samoa'),('SM','San Marino'),('ST','Sao Tome and Principe'),('SA','Saudi Arabia'),('SN','Senegal'),('RS','Serbia'),('SC','Seychelles'),('SL','Sierra Leone'),('SG','Singapore'),('SX','Sint Maarten'),('SK','Slovakia'),('SI','Slovenia'),('SB','Solomon Islands'),('SO','Somalia'),('ZA','South Africa'),('KR','South Korea'),('SS','South Sudan'),('ES','Spain'),('LK','Sri Lanka'),('SD','Sudan'),('SR','Suriname'),('SJ','Svalbard'),('SZ','Swaziland'),('SE','Sweden'),('CH','Switzerland'),('SY','Syria'),('TW','Taiwan'),('TJ','Tajikistan'),('TZ','Tanzania'),('TH','Thailand'),('BS','The Bahamas'),('GM','The Gambia'),('TL','Timor-Leste'),('TG','Togo'),('TK','Tokelau'),('TO','Tonga'),('TT','Trinidad and Tobago'),('TN','Tunisia'),('TR','Turkey'),('TM','Turkmenistan'),('TC','Turks and Caicos Islands'),('TV','Tuvalu'),('UG','Uganda'),('UA','Ukraine'),('AE','United Arab Emirates'),('GB','United Kingdom'),('US','United States'),('UY','Uruguay'),('UZ','Uzbekistan'),('VU','Vanuatu'),('VA','Vatican City'),('VE','Venezuela'),('VN','Vietnam'),('VI','Virgin Islands'),('WF','Wallis and Futuna'),('EH','Western Sahara'),('YE','Yemen'),('ZM','Zambia'),('ZW','Zimbabwe');
/*!40000 ALTER TABLE `cube3d_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ican`
--

DROP TABLE IF EXISTS `ican`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ican` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `ican_title` varchar(255) NOT NULL,
  `ican_description` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ican`
--

LOCK TABLES `ican` WRITE;
/*!40000 ALTER TABLE `ican` DISABLE KEYS */;
INSERT INTO `ican` VALUES (1,9,'fgsdfg2','dfgsdfg'),(2,9,'sdfasdfx','asdfasdfasdf'),(3,9,'Привет мир!','dfgsdfg'),(4,9,'Приехать в Москву','dfgsdfg'),(5,9,'найти работу','dfgsdfg');
/*!40000 ALTER TABLE `ican` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iwant`
--

DROP TABLE IF EXISTS `iwant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iwant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `iwant_title` varchar(255) DEFAULT NULL,
  `iwant_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iwant`
--

LOCK TABLES `iwant` WRITE;
/*!40000 ALTER TABLE `iwant` DISABLE KEYS */;
INSERT INTO `iwant` VALUES (1,9,'fxcgsdfg2','sfgsdfg'),(2,9,'фывафыв','фывафыва');
/*!40000 ALTER TABLE `iwant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mark`
--

DROP TABLE IF EXISTS `mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mark_name` varchar(255) DEFAULT NULL,
  `mark_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mark_name_UNIQUE` (`mark_name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mark`
--

LOCK TABLES `mark` WRITE;
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
INSERT INTO `mark` VALUES (1,'Hello world',0),(3,'dsafsdfasdf',1),(4,'sdfasdf,asdfasdf',1),(5,'dfasdf',1),(6,'dterter',1),(7,'rertwert',1),(8,'Hello',3),(9,'Rest',1),(10,'asdfasdf',1),(11,'ru.todo100.activer.data.MarkData@49c65a7d',1),(12,'ru.todo100.activer.data.MarkData@c55ca0e',1),(13,'ru.todo100.activer.data.MarkData@2e5f7d79',1),(14,'ru.todo100.activer.data.MarkData@3bae3b65',1),(15,'ru.todo100.activer.data.MarkData@40408bdf',1);
/*!40000 ALTER TABLE `mark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mark_relation`
--

DROP TABLE IF EXISTS `mark_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mark_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mark_id` int(11) DEFAULT NULL,
  `relation_id` int(11) DEFAULT NULL,
  `CW` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mark_relation`
--

LOCK TABLES `mark_relation` WRITE;
/*!40000 ALTER TABLE `mark_relation` DISABLE KEYS */;
INSERT INTO `mark_relation` VALUES (10,8,1,1),(15,15,2,1);
/*!40000 ALTER TABLE `mark_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `added_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` VALUES (1,1,'/resources/photos/7a6e1086-7dc6-424b-ba1b-b3d544aa2a26.jpg','face','2015-09-29 15:39:05');
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `want_mark`
--

DROP TABLE IF EXISTS `want_mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `want_mark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `want_id` int(11) DEFAULT NULL,
  `want_mark_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `want_mark`
--

LOCK TABLES `want_mark` WRITE;
/*!40000 ALTER TABLE `want_mark` DISABLE KEYS */;
/*!40000 ALTER TABLE `want_mark` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-06  4:09:22
