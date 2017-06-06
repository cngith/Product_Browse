-- MySQL dump 10.15  Distrib 10.0.17-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: tj
-- ------------------------------------------------------
-- Server version	10.0.17-MariaDB-log

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

-- CentOS下导入脚本命令 source /root/mywork/project/Product_Browse/SQL-Script/product_browse_create.sql;

-- Windows下导入脚本命令 source E:/Program/JavaSpace/Product_Browse/product_browse_create.sql;

DROP DATABASE `product_browse_2017` IF EXISTS;
CREATE DATABASE IF NOT EXISTS `product_browse_2017`;
USE `product_browse_2017`;

-- GRANT ALL PRIVILEGES ON `product_browse_2017`.* TO 'admin'@'localhost';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON `product_browse_2017`.* TO 'tuser'@'localhost' WITH GRANT OPTION;
--
-- Table structure for table `tb_commodity`
--

DROP TABLE IF EXISTS `tb_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_commodity` (
  `Id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `ShowNo` varchar(4) NOT NULL DEFAULT '' COMMENT '展号',
  `CmdtSN` varchar(10) NOT NULL DEFAULT '' COMMENT '编号',
  `StyleNo` varchar(30) NOT NULL DEFAULT '0',
  `Name` varchar(50) NOT NULL DEFAULT '',
  `ImagePath` varchar(800) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表：Image存储商品图片，不同图片用分号分隔。';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_department`
--

DROP TABLE IF EXISTS `tb_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_department` (
  `DepId` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `DepName` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`DepId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作员和员工公用此表数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `UserId` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `UserName` varchar(20) NOT NULL DEFAULT '0',
  `Password` varchar(20) NOT NULL DEFAULT '',
  `DepId` int(5) unsigned zerofill NOT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `UserName` (`UserName`),
  KEY `FK__department` (`DepId`),
  CONSTRAINT `FK__department` FOREIGN KEY (`DepId`) REFERENCES `tb_department` (`DepId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户(操作员)表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-18 15:43:53
