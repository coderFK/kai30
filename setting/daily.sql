-- MySQL dump 10.13  Distrib 5.5.53, for Win32 (AMD64)
--
-- Host: localhost    Database: daily
-- ------------------------------------------------------
-- Server version	5.5.53

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
-- Table structure for table `t_account`
--

DROP TABLE IF EXISTS `t_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_account` (
  `name` varchar(15) NOT NULL,
  `password` varchar(31) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_account`
--

LOCK TABLES `t_account` WRITE;
/*!40000 ALTER TABLE `t_account` DISABLE KEYS */;
INSERT INTO `t_account` VALUES ('kai','123456','596448451@qq.com');
/*!40000 ALTER TABLE `t_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_account_role`
--

DROP TABLE IF EXISTS `t_account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_account_role` (
  `name` varchar(15) NOT NULL,
  `role` varchar(15) NOT NULL,
  PRIMARY KEY (`name`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_account_role`
--

LOCK TABLES `t_account_role` WRITE;
/*!40000 ALTER TABLE `t_account_role` DISABLE KEYS */;
INSERT INTO `t_account_role` VALUES ('kai','member');
/*!40000 ALTER TABLE `t_account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_daily`
--

DROP TABLE IF EXISTS `t_daily`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_daily` (
  `name` varchar(15) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  `title` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  KEY `name` (`name`),
  CONSTRAINT `t_daily_ibfk_1` FOREIGN KEY (`name`) REFERENCES `t_account` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_daily`
--

LOCK TABLES `t_daily` WRITE;
/*!40000 ALTER TABLE `t_daily` DISABLE KEYS */;
INSERT INTO `t_daily` VALUES ('kai','2016-11-13 04:17:50','Java环境变量配置：\r\n新建系统变量:JAVA_HOME 和CLASSPATH \r\n变量名：JAVA_HOME \r\n变量值：C:\\Program Files\\Java\\jdk1.7.0\r\n变量名：CLASSPATH \r\n变量值：.;%JAVA_HOME%\\lib\\dt.jar;%JAVA_HOME%\\lib\\tools.jar;\r\n变量名：Path \r\n变量值：%JAVA_HOME%\\bin;%JAVA_HOME%\\jre\\bin;','配置Java环境变量','Java'),('kai','2016-11-13 04:19:19','Java环境变量配置：\r\n新建系统变量:JAVA_HOME 和CLASSPATH \r\n变量名：JAVA_HOME \r\n变量值：C:\\Program Files\\Java\\jdk1.7.0\r\n变量名：CLASSPATH \r\n变量值：.;%JAVA_HOME%\\lib\\dt.jar;%JAVA_HOME%\\lib\\tools.jar;\r\n变量名：Path \r\n变量值：%JAVA_HOME%\\bin;%JAVA_HOME%\\jre\\bin;','配置Java环境变量','Java'),('kai','2016-11-13 04:20:24','Java环境变量配置：\r\n新建系统变量:JAVA_HOME 和CLASSPATH \r\n变量名：JAVA_HOME \r\n变量值：C:\\Program Files\\Java\\jdk1.7.0\r\n变量名：CLASSPATH \r\n变量值：.;%JAVA_HOME%\\lib\\dt.jar;%JAVA_HOME%\\lib\\tools.jar;\r\n变量名：Path \r\n变量值：%JAVA_HOME%\\bin;%JAVA_HOME%\\jre\\bin;','配置Java环境变量','Java'),('kai','2016-11-13 04:23:38','c','ss','a'),('kai','2016-11-13 04:25:08','c','sm','Java'),('kai','2016-11-13 04:25:55','c','sm','Java'),('kai','2016-11-13 04:27:03','c','sm','Java'),('kai','2016-11-13 04:28:23','c','sm','Java'),('kai','2016-11-13 09:08:28','默认','媒体','其他');
/*!40000 ALTER TABLE `t_daily` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-13 19:28:20
