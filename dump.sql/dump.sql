CREATE DATABASE  IF NOT EXISTS `elecciones` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `elecciones`;
-- MySQL dump 10.13  Distrib 5.5.53, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: elecciones
-- ------------------------------------------------------
-- Server version	5.5.53-0ubuntu0.14.04.1

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
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('1505176666365-1','pablo (generated)','DELTA_001_BOLETA_MESA_PARTIDO_URNA.xml','2017-10-13 22:42:01',1,'EXECUTED','7:36370b37cc9a9e55a947e22aed6f4f2a','createTable tableName=boleta','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505176666365-2','pablo (generated)','DELTA_001_BOLETA_MESA_PARTIDO_URNA.xml','2017-10-13 22:42:02',2,'EXECUTED','7:21e2786df16ec804f5d10d89be3f1e3d','createTable tableName=mesa','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505176666365-3','pablo (generated)','DELTA_001_BOLETA_MESA_PARTIDO_URNA.xml','2017-10-13 22:42:02',3,'EXECUTED','7:1c45069a5695d9f327311f75003ff8fd','createTable tableName=partido','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505176666365-4','pablo (generated)','DELTA_001_BOLETA_MESA_PARTIDO_URNA.xml','2017-10-13 22:42:02',4,'EXECUTED','7:b90ecd210f5021f844c3da7672277999','createTable tableName=urna','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505176666365-5','pablo (generated)','DELTA_001_BOLETA_MESA_PARTIDO_URNA.xml','2017-10-13 22:42:03',5,'EXECUTED','7:58b914b4fa5bf05acde3d3bdc422d917','addForeignKeyConstraint baseTableName=urna, constraintName=FKbyjbymcvr07y0hg94qwil1lov, referencedTableName=mesa','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505176666365-6','pablo (generated)','DELTA_001_BOLETA_MESA_PARTIDO_URNA.xml','2017-10-13 22:42:03',6,'EXECUTED','7:d91db2a68581493060176a92fecc2334','addForeignKeyConstraint baseTableName=boleta, constraintName=FKdmk5u6579uppe9exvlq7bpic3, referencedTableName=urna','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505176666365-7','pablo (generated)','DELTA_001_BOLETA_MESA_PARTIDO_URNA.xml','2017-10-13 22:42:04',7,'EXECUTED','7:9a666acadc9b0250d505ab7204e58a56','addForeignKeyConstraint baseTableName=boleta, constraintName=FKe06uemevfanat18bjrw6m13el, referencedTableName=partido','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505251897867-1','pablo (generated)','DELTA_002_FISCAL_ROL_TOKEN.xml','2017-10-13 22:42:04',8,'EXECUTED','7:a1f61a0ea640b58a30ee0ccc8089f2f9','createTable tableName=fiscal','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505251897867-2','pablo (generated)','DELTA_002_FISCAL_ROL_TOKEN.xml','2017-10-13 22:42:04',9,'EXECUTED','7:6e62cbec8426a27b05e4929920f57c09','createTable tableName=fiscal_rol','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505251897867-3','pablo (generated)','DELTA_002_FISCAL_ROL_TOKEN.xml','2017-10-13 22:42:04',10,'EXECUTED','7:cfeb7f49268fdb74877830f88859f645','createTable tableName=rol','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505251897867-4','pablo (generated)','DELTA_002_FISCAL_ROL_TOKEN.xml','2017-10-13 22:42:06',11,'EXECUTED','7:0df89c77dc3cf6aa9d7067139a0366a3','createTable tableName=token','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505251897867-5','pablo (generated)','DELTA_002_FISCAL_ROL_TOKEN.xml','2017-10-13 22:42:07',12,'EXECUTED','7:1ac8ee89150b4512c4dc2969683f9a89','addPrimaryKey constraintName=fiscal_rolPK, tableName=fiscal_rol','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505251897867-6','pablo (generated)','DELTA_002_FISCAL_ROL_TOKEN.xml','2017-10-13 22:42:08',13,'EXECUTED','7:962f233c4f1bf28bef8132aa522357ca','addPrimaryKey constraintName=tokenPK, tableName=token','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505251897867-7','pablo (generated)','DELTA_002_FISCAL_ROL_TOKEN.xml','2017-10-13 22:42:08',14,'EXECUTED','7:24d695986340ff7f679d9a6eee91dae3','addUniqueConstraint constraintName=UC_FISCALUSERNAME_COL, tableName=fiscal','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505251897867-8','pablo (generated)','DELTA_002_FISCAL_ROL_TOKEN.xml','2017-10-13 22:42:10',15,'EXECUTED','7:f4d068b06eab17c132108db8385a69d5','addUniqueConstraint constraintName=UC_ROLAUTHORITY_COL, tableName=rol','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505251897867-9','pablo (generated)','DELTA_002_FISCAL_ROL_TOKEN.xml','2017-10-13 22:42:12',16,'EXECUTED','7:c77610acc6b38d9a72eaf25c1a9736c7','addForeignKeyConstraint baseTableName=fiscal_rol, constraintName=FK4j2lkl0yd3dxxl8a1ym6bfrtk, referencedTableName=rol','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505251897867-10','pablo (generated)','DELTA_002_FISCAL_ROL_TOKEN.xml','2017-10-13 22:42:17',17,'EXECUTED','7:050c69fc196a72b729ee4f717f6aaa5b','addForeignKeyConstraint baseTableName=fiscal_rol, constraintName=FKl2v1rsm8hdu1mw37h7p03ndjc, referencedTableName=fiscal','',NULL,'3.5.3',NULL,NULL,'7945320603'),('DELTA_003_AGREGAR_ADMIN','pablo','DELTA_003_AGREGAR_ADMIN.xml','2017-10-13 22:42:18',18,'EXECUTED','7:cc6918921462ba944c2217bbffe3d786','sql','',NULL,'3.5.3',NULL,NULL,'7945320603'),('DELTA_003_AGREGAR_ADMIN','pablo','DELTA_004_ROLES_FISCALES.xml','2017-10-13 22:42:20',19,'EXECUTED','7:f1a43de9b46f24d389f0699ec592a34e','sql','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505273917464-1','pablo (generated)','DELTA_005_FISCAL_DE_MESA.xml','2017-10-13 22:42:22',20,'EXECUTED','7:6e5214860a5c776749a963bb14d7307f','addColumn tableName=mesa','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505273917464-2','pablo (generated)','DELTA_005_FISCAL_DE_MESA.xml','2017-10-13 22:42:23',21,'EXECUTED','7:3c44abcc70956730b5314e12cbc55a1b','addForeignKeyConstraint baseTableName=mesa, constraintName=FK2m5ggjolmmdt5l8rr8xwdd374, referencedTableName=fiscal','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505573795660-1','pablo (generated)','DELTA_007_FISCAL_MAIL.xml','2017-10-13 22:42:24',22,'EXECUTED','7:4713976edea3e5b74f7d140fe0882f93','addColumn tableName=fiscal','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505587385581-1','pablo (generated)','DELTA_008_TOKEN_REST.xml','2017-10-13 22:42:24',23,'EXECUTED','7:bebb448f0ad1f3916d13d13c8b7ee163','createTable tableName=token_rest','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505694250731-1','pablo (generated)','DELTA_009_ESCUELA.xml','2017-10-13 22:42:25',24,'EXECUTED','7:a664ced28c04233b3e5a2222896682b3','createTable tableName=escuela','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505694250731-2','pablo (generated)','DELTA_009_ESCUELA.xml','2017-10-13 22:42:27',25,'EXECUTED','7:ef7e061299fa640aa073911d1500e19c','addColumn tableName=mesa','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505694250731-3','pablo (generated)','DELTA_009_ESCUELA.xml','2017-10-13 22:42:27',26,'EXECUTED','7:5d8dee791b2ab92df5d42a23d85d1daa','addForeignKeyConstraint baseTableName=escuela, constraintName=FKct21yeeavilyiufbiynoje9ul, referencedTableName=fiscal','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505694250731-4','pablo (generated)','DELTA_009_ESCUELA.xml','2017-10-13 22:42:28',27,'EXECUTED','7:f984d77c9bc6fbad1e8b4b54b3853fc3','addForeignKeyConstraint baseTableName=mesa, constraintName=FKp9erj826xg92yky0djnpmd5v6, referencedTableName=escuela','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505788497074-1','pablo (generated)','DELTA_010_COMUNA.xml','2017-10-13 22:42:28',28,'EXECUTED','7:eb28dbd55b45254184951a7d78a12be1','createTable tableName=comuna','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505788497074-2','pablo (generated)','DELTA_010_COMUNA.xml','2017-10-13 22:42:28',29,'EXECUTED','7:0b5722ad3a5c207c2beba2e6e19cdb27','addColumn tableName=escuela','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505788497074-3','pablo (generated)','DELTA_010_COMUNA.xml','2017-10-13 22:42:29',30,'EXECUTED','7:90b58cd10edee6c2c10949e8791fc8bf','addForeignKeyConstraint baseTableName=comuna, constraintName=FKjmchn10pj957bfedji9r3365k, referencedTableName=fiscal','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1505788497074-4','pablo (generated)','DELTA_010_COMUNA.xml','2017-10-13 22:42:29',31,'EXECUTED','7:caab34e6481b71a9633070df9d1f5354','addForeignKeyConstraint baseTableName=escuela, constraintName=FKqjnffonxr05m4pjv0aynujas7, referencedTableName=comuna','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1506209467314-1','pablo (generated)','DELTA_011_FUERZA_POLITICA.xml','2017-10-13 22:42:29',32,'EXECUTED','7:2e3c6d7c9008cabc3d59c45597c42040','createTable tableName=comuna_fuerza_politica','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1506209467314-2','pablo (generated)','DELTA_011_FUERZA_POLITICA.xml','2017-10-13 22:42:29',33,'EXECUTED','7:b8cbf4a4eb8ed1d8d6425d1078c36fb5','createTable tableName=fuerza_politica','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1506209467314-3','pablo (generated)','DELTA_011_FUERZA_POLITICA.xml','2017-10-13 22:42:30',34,'EXECUTED','7:765464c30541a66709936023aebda8ed','addUniqueConstraint constraintName=UC_COMUNANUMERO_COL, tableName=comuna','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1506209467314-4','pablo (generated)','DELTA_011_FUERZA_POLITICA.xml','2017-10-13 22:42:30',35,'EXECUTED','7:b9e2843f692d39efd71c60d9f6c34fea','addUniqueConstraint constraintName=UC_FUERZA_POLITICANOMBRE_COL, tableName=fuerza_politica','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1506209467314-5','pablo (generated)','DELTA_011_FUERZA_POLITICA.xml','2017-10-13 22:42:30',36,'EXECUTED','7:54e1b5725073a8aaab7fa815c699927d','addForeignKeyConstraint baseTableName=comuna_fuerza_politica, constraintName=FKsq6xykb4xw9qmc2u6d87btb1n, referencedTableName=comuna','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1506209467314-6','pablo (generated)','DELTA_011_FUERZA_POLITICA.xml','2017-10-13 22:42:33',37,'EXECUTED','7:3584c71631d91bc7c89967639e93b9b0','addForeignKeyConstraint baseTableName=comuna_fuerza_politica, constraintName=FKt0c94309kfu32mouofgpy9yuu, referencedTableName=fuerza_politica','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1506268370888-1','pablo (generated)','DELTA_012_ESCUELA_FUERZA_POLITICA.xml','2017-10-13 22:42:34',38,'EXECUTED','7:cb919cdf2e99037fe82256a03a6661bb','addColumn tableName=escuela','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1506268370888-2','pablo (generated)','DELTA_012_ESCUELA_FUERZA_POLITICA.xml','2017-10-13 22:42:34',39,'EXECUTED','7:1fcce42e8014622bf0173e58ee2c2aad','addForeignKeyConstraint baseTableName=escuela, constraintName=FKeqrlmujc4gf6fklwbr4o5x91e, referencedTableName=fuerza_politica','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1507416495004-1','pablo (generated)','DELTA_0013_AGREGAR_COLOR_PARTIDO_E_INFORMANTE_A_UR_E_INFORMANTE_A_URNA.xml','2017-10-13 22:42:35',40,'EXECUTED','7:5b7ce2205989373fa624199e5b680267','addColumn tableName=partido','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1507416495004-2','pablo (generated)','DELTA_0013_AGREGAR_COLOR_PARTIDO_E_INFORMANTE_A_UR_E_INFORMANTE_A_URNA.xml','2017-10-13 22:42:36',41,'EXECUTED','7:07f3695678e80f0fe7ef279ddc8c4e59','addColumn tableName=urna','',NULL,'3.5.3',NULL,NULL,'7945320603'),('1507416495004-3','pablo (generated)','DELTA_0013_AGREGAR_COLOR_PARTIDO_E_INFORMANTE_A_UR_E_INFORMANTE_A_URNA.xml','2017-10-13 22:42:36',42,'EXECUTED','7:47fff317caee922c0069fd2619b1ad31','addForeignKeyConstraint baseTableName=urna, constraintName=FKen6o702epnohl3yuxpnjau2dt, referencedTableName=fiscal','',NULL,'3.5.3',NULL,NULL,'7945320603');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleta`
--

DROP TABLE IF EXISTS `boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `urna_id` bigint(20) NOT NULL,
  `legisladores` bigint(20) DEFAULT NULL,
  `partido_id` bigint(20) NOT NULL,
  `diputados` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdmk5u6579uppe9exvlq7bpic3` (`urna_id`),
  KEY `FKe06uemevfanat18bjrw6m13el` (`partido_id`),
  CONSTRAINT `FKe06uemevfanat18bjrw6m13el` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`),
  CONSTRAINT `FKdmk5u6579uppe9exvlq7bpic3` FOREIGN KEY (`urna_id`) REFERENCES `urna` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleta`
--

LOCK TABLES `boleta` WRITE;
/*!40000 ALTER TABLE `boleta` DISABLE KEYS */;
/*!40000 ALTER TABLE `boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comuna`
--

DROP TABLE IF EXISTS `comuna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comuna` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `numero` bigint(20) NOT NULL,
  `admin_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_COMUNANUMERO_COL` (`numero`),
  KEY `FKjmchn10pj957bfedji9r3365k` (`admin_id`),
  CONSTRAINT `FKjmchn10pj957bfedji9r3365k` FOREIGN KEY (`admin_id`) REFERENCES `fiscal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comuna`
--

LOCK TABLES `comuna` WRITE;
/*!40000 ALTER TABLE `comuna` DISABLE KEYS */;
/*!40000 ALTER TABLE `comuna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comuna_fuerza_politica`
--

DROP TABLE IF EXISTS `comuna_fuerza_politica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comuna_fuerza_politica` (
  `comuna_fuerzas_politicas_id` bigint(20) NOT NULL,
  `fuerza_politica_id` bigint(20) DEFAULT NULL,
  KEY `FKsq6xykb4xw9qmc2u6d87btb1n` (`comuna_fuerzas_politicas_id`),
  KEY `FKt0c94309kfu32mouofgpy9yuu` (`fuerza_politica_id`),
  CONSTRAINT `FKt0c94309kfu32mouofgpy9yuu` FOREIGN KEY (`fuerza_politica_id`) REFERENCES `fuerza_politica` (`id`),
  CONSTRAINT `FKsq6xykb4xw9qmc2u6d87btb1n` FOREIGN KEY (`comuna_fuerzas_politicas_id`) REFERENCES `comuna` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comuna_fuerza_politica`
--

LOCK TABLES `comuna_fuerza_politica` WRITE;
/*!40000 ALTER TABLE `comuna_fuerza_politica` DISABLE KEYS */;
/*!40000 ALTER TABLE `comuna_fuerza_politica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `escuela`
--

DROP TABLE IF EXISTS `escuela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `escuela` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `fiscal_id` bigint(20) DEFAULT NULL,
  `numero` bigint(20) NOT NULL,
  `comuna_id` bigint(20) NOT NULL,
  `fuerza_politica_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKct21yeeavilyiufbiynoje9ul` (`fiscal_id`),
  KEY `FKqjnffonxr05m4pjv0aynujas7` (`comuna_id`),
  KEY `FKeqrlmujc4gf6fklwbr4o5x91e` (`fuerza_politica_id`),
  CONSTRAINT `FKeqrlmujc4gf6fklwbr4o5x91e` FOREIGN KEY (`fuerza_politica_id`) REFERENCES `fuerza_politica` (`id`),
  CONSTRAINT `FKct21yeeavilyiufbiynoje9ul` FOREIGN KEY (`fiscal_id`) REFERENCES `fiscal` (`id`),
  CONSTRAINT `FKqjnffonxr05m4pjv0aynujas7` FOREIGN KEY (`comuna_id`) REFERENCES `comuna` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escuela`
--

LOCK TABLES `escuela` WRITE;
/*!40000 ALTER TABLE `escuela` DISABLE KEYS */;
/*!40000 ALTER TABLE `escuela` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fiscal`
--

DROP TABLE IF EXISTS `fiscal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fiscal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `password_expired` bit(1) NOT NULL,
  `username` varchar(255) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `mail` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_FISCALUSERNAME_COL` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiscal`
--

LOCK TABLES `fiscal` WRITE;
/*!40000 ALTER TABLE `fiscal` DISABLE KEYS */;
INSERT INTO `fiscal` VALUES (1,0,'\0','admin','\0','$2a$10$U.0cGC4ebpdes4d4m/LoXOuw032Dl46fmCbQv2Sog0enLjLtjB3CK','\0','','');
/*!40000 ALTER TABLE `fiscal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fiscal_rol`
--

DROP TABLE IF EXISTS `fiscal_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fiscal_rol` (
  `fiscal_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL,
  PRIMARY KEY (`fiscal_id`,`rol_id`),
  KEY `FK4j2lkl0yd3dxxl8a1ym6bfrtk` (`rol_id`),
  CONSTRAINT `FKl2v1rsm8hdu1mw37h7p03ndjc` FOREIGN KEY (`fiscal_id`) REFERENCES `fiscal` (`id`),
  CONSTRAINT `FK4j2lkl0yd3dxxl8a1ym6bfrtk` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiscal_rol`
--

LOCK TABLES `fiscal_rol` WRITE;
/*!40000 ALTER TABLE `fiscal_rol` DISABLE KEYS */;
INSERT INTO `fiscal_rol` VALUES (1,1);
/*!40000 ALTER TABLE `fiscal_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuerza_politica`
--

DROP TABLE IF EXISTS `fuerza_politica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fuerza_politica` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `color` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_FUERZA_POLITICANOMBRE_COL` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuerza_politica`
--

LOCK TABLES `fuerza_politica` WRITE;
/*!40000 ALTER TABLE `fuerza_politica` DISABLE KEYS */;
/*!40000 ALTER TABLE `fuerza_politica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesa`
--

DROP TABLE IF EXISTS `mesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `numero` int(11) NOT NULL,
  `fiscal_id` bigint(20) DEFAULT NULL,
  `escuela_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2m5ggjolmmdt5l8rr8xwdd374` (`fiscal_id`),
  KEY `FKp9erj826xg92yky0djnpmd5v6` (`escuela_id`),
  CONSTRAINT `FKp9erj826xg92yky0djnpmd5v6` FOREIGN KEY (`escuela_id`) REFERENCES `escuela` (`id`),
  CONSTRAINT `FK2m5ggjolmmdt5l8rr8xwdd374` FOREIGN KEY (`fiscal_id`) REFERENCES `fiscal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesa`
--

LOCK TABLES `mesa` WRITE;
/*!40000 ALTER TABLE `mesa` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `computa` bit(1) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `numero` bigint(20) NOT NULL,
  `color` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_ROLAUTHORITY_COL` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,0,'ROLE_ADMIN'),(2,0,'ROLE_FISCAL_GENERAL'),(3,0,'ROLE_FISCAL_MESA'),(4,0,'ROLE_ADMIN_COMUNA');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `series` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` datetime NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_rest`
--

DROP TABLE IF EXISTS `token_rest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token_rest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_rest`
--

LOCK TABLES `token_rest` WRITE;
/*!40000 ALTER TABLE `token_rest` DISABLE KEYS */;
/*!40000 ALTER TABLE `token_rest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `urna`
--

DROP TABLE IF EXISTS `urna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `urna` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `mesa_id` bigint(20) NOT NULL,
  `informante_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbyjbymcvr07y0hg94qwil1lov` (`mesa_id`),
  KEY `FKen6o702epnohl3yuxpnjau2dt` (`informante_id`),
  CONSTRAINT `FKen6o702epnohl3yuxpnjau2dt` FOREIGN KEY (`informante_id`) REFERENCES `fiscal` (`id`),
  CONSTRAINT `FKbyjbymcvr07y0hg94qwil1lov` FOREIGN KEY (`mesa_id`) REFERENCES `mesa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `urna`
--

LOCK TABLES `urna` WRITE;
/*!40000 ALTER TABLE `urna` DISABLE KEYS */;
/*!40000 ALTER TABLE `urna` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-13 22:45:57
