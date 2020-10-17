-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: network
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_attribute`
--

DROP TABLE IF EXISTS `t_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_attribute` (
  `id` int NOT NULL AUTO_INCREMENT,
  `element_id` int NOT NULL,
  `attribute_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性名',
  `attribute_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '默认值',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `attribute_element` (`element_id`),
  CONSTRAINT `attribute_element` FOREIGN KEY (`element_id`) REFERENCES `t_element` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_attribute`
--

LOCK TABLES `t_attribute` WRITE;
/*!40000 ALTER TABLE `t_attribute` DISABLE KEYS */;
INSERT INTO `t_attribute` VALUES (1,1,'压力','1'),(2,1,'载荷','0'),(3,1,'压力已知','false'),(4,1,'载荷已知','false'),(5,2,'流ID','1'),(6,3,'膨胀比','1'),(7,3,'压缩比','1'),(8,3,'引射率','0'),(9,3,'等熵效率','0'),(10,4,'干线压力','0'),(11,4,'天然气热值','0'),(12,4,'原动机效率','0'),(13,4,'压缩机效率','0'),(14,5,'进场压力','0'),(15,5,'出场压力','0'),(16,5,'产率','0');
/*!40000 ALTER TABLE `t_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_base`
--

DROP TABLE IF EXISTS `t_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_base` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '节点编号',
  `project_id` int DEFAULT NULL COMMENT '节点所属项目id',
  `element_id` int DEFAULT NULL COMMENT '节点所属元件id',
  `model_id` int DEFAULT NULL COMMENT '节点模型id',
  `elevation` double(10,5) DEFAULT NULL COMMENT '节点海拔',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '节点名称',
  `element_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '元件名称',
  `x` double(20,10) DEFAULT NULL COMMENT '节点x坐标',
  `y` double(20,10) DEFAULT NULL COMMENT '节点y坐标',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `node_element` (`element_id`),
  CONSTRAINT `node_element` FOREIGN KEY (`element_id`) REFERENCES `t_element` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=665 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_base`
--

LOCK TABLES `t_base` WRITE;
/*!40000 ALTER TABLE `t_base` DISABLE KEYS */;
INSERT INTO `t_base` VALUES (526,58,1,2,0.00000,'普通节点01','普通节点',62.0000000000,58.0000000000),(527,58,2,3,0.00000,'生产井01','生产井',322.0000000000,60.0000000000),(528,58,3,4,0.00000,'引射器01','引射器',200.0000000000,130.0000000000),(529,58,4,5,0.00000,'压缩机01','压缩机',373.0000000000,226.0000000000),(530,58,5,6,0.00000,'站场01','站场',62.0000000000,210.0000000000),(531,59,1,2,1.00000,'aaa','普通节点',111.0000000000,111.0000000000),(551,60,1,2,0.00000,'普通节点01','普通节点',164.0000000000,62.0000000000),(552,60,2,3,0.00000,'生产井01','生产井',50.0000000000,87.0000000000),(553,60,3,4,0.00000,'引射器01','引射器',243.0000000000,142.0000000000),(554,60,4,5,0.00000,'压缩机01','压缩机',224.0000000000,199.0000000000),(555,60,5,6,0.00000,'站场01','站场',499.0000000000,87.0000000000),(556,61,1,2,1.00000,'aaa','普通节点',123.0000000000,123.0000000000),(640,57,1,2,0.00000,'普通节点01','普通节点',191.0000000000,112.0000000000),(641,57,2,3,0.00000,'生产井01','生产井',104.0000000000,75.0000000000),(642,57,3,4,0.00000,'引射器01','引射器',418.0000000000,131.0000000000),(643,57,4,5,0.00000,'压缩机01','压缩机',294.0000000000,193.0000000000),(644,57,5,6,0.00000,'站场01','站场',338.0000000000,85.0000000000),(645,57,1,7,1.00000,'a','普通节点',222.0000000000,222.0000000000),(646,57,2,8,1.00000,'qqq','生产井',123.0000000000,123.0000000000),(647,57,3,10,1.00000,'qqqqq','引射器',360.0000000000,270.0000000000),(648,57,1,11,1.00000,'qwazz','普通节点',234.0000000000,234.0000000000),(649,57,4,12,1.00000,'aaq','压缩机',211.0000000000,211.0000000000),(660,62,5,2,1.00000,'aa','站场',123.0000000000,123.0000000000),(661,62,4,3,1.00000,'ss','压缩机',266.0000000000,222.0000000000),(662,62,3,4,1.00000,'dd','引射器',234.0000000000,111.0000000000),(663,62,2,5,1.00000,'ff','生产井',234.0000000000,222.0000000000),(664,62,1,6,1.00000,'gg','普通节点',222.0000000000,333.0000000000);
/*!40000 ALTER TABLE `t_base` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_component`
--

DROP TABLE IF EXISTS `t_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_component` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '组分id',
  `c1` double(10,8) DEFAULT NULL COMMENT '摩尔百分比',
  `c2` double(10,8) DEFAULT NULL,
  `c3` double(10,8) DEFAULT NULL,
  `ic4` double(10,8) DEFAULT NULL,
  `nc4` double(10,8) DEFAULT NULL,
  `ic5` double(10,8) DEFAULT NULL,
  `nc5` double(10,8) DEFAULT NULL,
  `c6` double(10,8) DEFAULT NULL,
  `n2` double(10,8) DEFAULT NULL,
  `co2` double(10,8) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_component`
--

LOCK TABLES `t_component` WRITE;
/*!40000 ALTER TABLE `t_component` DISABLE KEYS */;
INSERT INTO `t_component` VALUES (1,0.94960000,0.00880000,0.00180000,0.00030000,0.00040000,0.00000000,0.00000000,0.00000000,0.00950000,0.02960000,'com01'),(49,1.00000000,0.00000000,0.00000000,0.00000000,0.00000000,0.00000000,0.00000000,0.00000000,0.00000000,0.00000000,'com02');
/*!40000 ALTER TABLE `t_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_compressor`
--

DROP TABLE IF EXISTS `t_compressor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_compressor` (
  `id` int NOT NULL COMMENT '节点编号',
  `main_pressure` double(20,10) DEFAULT NULL COMMENT '干线压力',
  `calorific_value` double(20,10) DEFAULT NULL COMMENT '天然气热值',
  `engine_efficiency` double(20,10) DEFAULT NULL COMMENT '原动机效率',
  `compressor_efficiency` double(20,10) DEFAULT NULL COMMENT '压缩机效率',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `compressor_base` FOREIGN KEY (`id`) REFERENCES `t_base` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_compressor`
--

LOCK TABLES `t_compressor` WRITE;
/*!40000 ALTER TABLE `t_compressor` DISABLE KEYS */;
INSERT INTO `t_compressor` VALUES (529,0.0000000000,0.0000000000,0.0000000000,0.0000000000),(554,0.0000000000,0.0000000000,0.0000000000,0.0000000000),(643,0.0000000000,0.0000000000,0.0000000000,0.0000000000),(649,12.0000000000,1.0000000000,1.0000000000,1.0000000000),(661,1.0000000000,1.0000000000,1.0000000000,1.0000000000);
/*!40000 ALTER TABLE `t_compressor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_connection`
--

DROP TABLE IF EXISTS `t_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_connection` (
  `id` int NOT NULL AUTO_INCREMENT,
  `element_id` int NOT NULL,
  `x` double(3,2) DEFAULT NULL COMMENT '连接点x0-1',
  `y` double(3,2) DEFAULT NULL COMMENT '连接点y0-1',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `connection_element` (`element_id`),
  CONSTRAINT `connection_element` FOREIGN KEY (`element_id`) REFERENCES `t_element` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_connection`
--

LOCK TABLES `t_connection` WRITE;
/*!40000 ALTER TABLE `t_connection` DISABLE KEYS */;
INSERT INTO `t_connection` VALUES (1,1,0.50,0.50,'连接点'),(5,2,0.50,1.00,'连接点'),(6,3,0.00,0.50,'高压入口'),(7,3,1.00,0.50,'中压出口'),(8,3,0.50,1.00,'低压入口'),(9,4,0.00,0.50,'入口'),(10,4,1.00,0.50,'出口'),(11,5,1.00,0.50,'连接点');
/*!40000 ALTER TABLE `t_connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_ejector`
--

DROP TABLE IF EXISTS `t_ejector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_ejector` (
  `id` int NOT NULL COMMENT '节点编号',
  `expand_rate` double(20,10) DEFAULT NULL COMMENT '膨胀比',
  `compress_rate` double(20,10) DEFAULT NULL COMMENT '压缩比',
  `ejector_rate` double(20,10) DEFAULT NULL COMMENT '引射率',
  `efficiency` double(20,10) DEFAULT NULL COMMENT '等熵效率',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `ejector_base` FOREIGN KEY (`id`) REFERENCES `t_base` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_ejector`
--

LOCK TABLES `t_ejector` WRITE;
/*!40000 ALTER TABLE `t_ejector` DISABLE KEYS */;
INSERT INTO `t_ejector` VALUES (528,1.0000000000,1.0000000000,0.0000000000,0.0000000000),(553,1.0000000000,1.0000000000,0.0000000000,0.0000000000),(642,1.0000000000,1.0000000000,0.0000000000,0.0000000000),(647,1.0000000000,1.0000000000,1.0000000000,1.0000000000),(662,1.0000000000,1.0000000000,1.0000000000,1.0000000000);
/*!40000 ALTER TABLE `t_ejector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_element`
--

DROP TABLE IF EXISTS `t_element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_element` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '元件名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '存储路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_element`
--

LOCK TABLES `t_element` WRITE;
/*!40000 ALTER TABLE `t_element` DISABLE KEYS */;
INSERT INTO `t_element` VALUES (1,'普通节点','/Elements/普通节点.svg'),(2,'生产井','/Elements/生产井.svg'),(3,'引射器','/Elements/引射器.svg'),(4,'压缩机','/Elements/压缩机.svg'),(5,'站场','/Elements/站场.svg');
/*!40000 ALTER TABLE `t_element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_menu` (
  `id` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
INSERT INTO `t_menu` VALUES (100,'新建工程','/create','el-icon-circle-plus-outline'),(200,'工程管理','/list','el-icon-s-order'),(300,'模拟结果','/result','el-icon-s-data'),(400,'用户管理','/admin','el-icon-s-custom'),(500,'元件管理','/element','el-icon-s-cooperation');
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_node`
--

DROP TABLE IF EXISTS `t_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_node` (
  `id` int NOT NULL COMMENT '节点编号',
  `pressure` double(20,10) DEFAULT NULL COMMENT '压力',
  `pressure_state` tinyint(1) DEFAULT NULL COMMENT '压力是否已知',
  `loads` double(20,10) DEFAULT NULL COMMENT '载荷',
  `load_state` tinyint(1) DEFAULT NULL COMMENT '载荷是否已知',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `node_base` FOREIGN KEY (`id`) REFERENCES `t_base` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_node`
--

LOCK TABLES `t_node` WRITE;
/*!40000 ALTER TABLE `t_node` DISABLE KEYS */;
INSERT INTO `t_node` VALUES (526,1.0000000000,0,0.0000000000,0),(531,1.0000000000,0,1.0000000000,0),(551,1.0000000000,0,0.0000000000,0),(556,1.0000000000,0,1.0000000000,0),(640,1.0000000000,0,0.0000000000,0),(645,1.0000000000,0,1.0000000000,0),(648,1.0000000000,0,1.0000000000,0),(664,1.0000000000,1,1.0000000000,1);
/*!40000 ALTER TABLE `t_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_pipe`
--

DROP TABLE IF EXISTS `t_pipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_pipe` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '管道id',
  `project_id` int DEFAULT NULL COMMENT '管道所属项目id',
  `model_id` int DEFAULT NULL COMMENT '节点模型id',
  `start_id` int DEFAULT NULL COMMENT '起始节点id',
  `end_id` int DEFAULT NULL COMMENT '终止节点id',
  `start_connection` int DEFAULT NULL COMMENT '起始节点连接点',
  `end_connection` int DEFAULT NULL COMMENT '终止节点连接点',
  `diameter` double(20,10) DEFAULT NULL COMMENT '内径',
  `length` double(20,10) DEFAULT NULL COMMENT '长度',
  `roughness` double(20,10) DEFAULT NULL COMMENT '粗糙度',
  `lambda` double(20,10) DEFAULT NULL COMMENT '摩阻',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管道名称',
  `start_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '起始节点名称',
  `end_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '终止节点名称',
  `start_connection_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '起始连接点名称',
  `end_connection_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '终止连接点名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `start` (`start_id`) USING BTREE,
  KEY `end` (`end_id`) USING BTREE,
  KEY `startname` (`start_name`) USING BTREE,
  KEY `endname` (`end_name`) USING BTREE,
  KEY `end_connection` (`end_connection`),
  KEY `start_connection` (`start_connection`),
  CONSTRAINT `end` FOREIGN KEY (`end_id`) REFERENCES `t_base` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `end_connection` FOREIGN KEY (`end_connection`) REFERENCES `t_connection` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `start` FOREIGN KEY (`start_id`) REFERENCES `t_base` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `start_connection` FOREIGN KEY (`start_connection`) REFERENCES `t_connection` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_pipe`
--

LOCK TABLES `t_pipe` WRITE;
/*!40000 ALTER TABLE `t_pipe` DISABLE KEYS */;
INSERT INTO `t_pipe` VALUES (63,51,17,NULL,NULL,5,1,12.0000000000,1.0000000000,1.0000000000,NULL,'qwsd','生产井01','普通节点01','连接点','连接点'),(64,51,18,NULL,NULL,5,6,0.0000000000,0.0000000000,0.0000000000,NULL,'sss','生产井qqq','引射器01','连接点','高压入口'),(73,55,7,NULL,NULL,1,5,0.0000000000,0.0000000000,0.0000000000,NULL,'ss','普通节点01','生产井01','连接点','连接点'),(87,58,7,526,528,1,6,0.0000000000,0.0000000000,0.0000000000,NULL,'qqq','普通节点01','引射器01','连接点','高压入口'),(88,58,9,527,528,5,7,0.0000000000,0.0000000000,0.0000000000,NULL,'www','生产井01','引射器01','连接点','中压出口'),(89,58,10,529,528,9,8,0.0000000000,0.0000000000,0.0000000000,NULL,'eee','压缩机01','引射器01','入口','低压入口'),(90,58,11,530,528,11,6,0.0000000000,0.0000000000,0.0000000000,NULL,'rrr','站场01','引射器01','连接点','高压入口'),(91,58,12,529,528,10,7,0.0000000000,0.0000000000,0.0000000000,NULL,'ttt','压缩机01','引射器01','出口','中压出口'),(101,60,7,551,555,1,11,0.0000000000,0.0000000000,0.0000000000,NULL,'zzz','普通节点01','站场01','连接点','连接点'),(102,60,8,552,551,5,1,0.0000000000,0.0000000000,0.0000000000,NULL,'fff','生产井01','普通节点01','连接点','连接点'),(103,60,9,553,554,6,9,0.0000000000,0.0000000000,0.0000000000,NULL,'ggg','引射器01','压缩机01','高压入口','入口'),(104,60,10,554,555,10,11,0.0000000000,0.0000000000,0.0000000000,NULL,'ggg','压缩机01','站场01','出口','连接点'),(110,57,9,640,644,1,11,1.0000000000,1.0000000000,1.0000000000,NULL,'qq','普通节点01','站场01','连接点','连接点'),(115,62,7,660,662,11,7,1.0000000000,1.0000000000,1.0000000000,NULL,'we','aa','dd','连接点','中压出口');
/*!40000 ALTER TABLE `t_pipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_projects`
--

DROP TABLE IF EXISTS `t_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_projects` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模型路径',
  `com_id` int DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_projects`
--

LOCK TABLES `t_projects` WRITE;
/*!40000 ALTER TABLE `t_projects` DISABLE KEYS */;
INSERT INTO `t_projects` VALUES (57,'wer','operator','2020-10-17 10:45:12','2020-10-17 15:00:39','稳态模拟','/Models/operator-wer.xml',1),(58,'qqq123','operator','2020-10-17 13:22:16','2020-10-17 13:36:13','稳态模拟','/Models/operator-qqq123.xml',1),(59,'zzz','operator','2020-10-17 13:38:48','2020-10-17 13:38:48','稳态模拟','/Models/operator-zzz.xml',1),(60,'fff','operator','2020-10-17 13:44:51','2020-10-17 13:46:34','稳态模拟','/Models/operator-fff.xml',1),(61,'dfghj','operator','2020-10-17 13:49:25','2020-10-17 13:49:25','稳态模拟','/Models/operator-dfghj.xml',1),(62,'aaa','operator','2020-10-17 13:59:54','2020-10-17 15:12:39','稳态模拟','/Models/operator-aaa.xml',1);
/*!40000 ALTER TABLE `t_projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_station`
--

DROP TABLE IF EXISTS `t_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_station` (
  `id` int NOT NULL COMMENT '节点编号',
  `inlet_pressure` double(20,10) DEFAULT NULL COMMENT '进场压力',
  `outlet_pressure` double(20,10) DEFAULT NULL COMMENT '出场压力',
  `production` double(20,10) DEFAULT NULL COMMENT '产率',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `station_base` FOREIGN KEY (`id`) REFERENCES `t_base` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_station`
--

LOCK TABLES `t_station` WRITE;
/*!40000 ALTER TABLE `t_station` DISABLE KEYS */;
INSERT INTO `t_station` VALUES (530,0.0000000000,0.0000000000,0.0000000000),(555,0.0000000000,0.0000000000,0.0000000000),(644,0.0000000000,0.0000000000,0.0000000000),(660,1.0000000000,1.0000000000,1.0000000000);
/*!40000 ALTER TABLE `t_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_submenu`
--

DROP TABLE IF EXISTS `t_submenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_submenu` (
  `id` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` int DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_submenu`
--

LOCK TABLES `t_submenu` WRITE;
/*!40000 ALTER TABLE `t_submenu` DISABLE KEYS */;
INSERT INTO `t_submenu` VALUES (101,'新建模拟','/newProject',100,NULL),(102,'新建组分','/newGas',100,NULL),(201,'模拟列表','/projectList',200,NULL),(202,'组分列表','/gasList',200,NULL),(203,'引射器列表','/ejectorList',200,NULL),(301,'运行模拟','/run',300,NULL),(302,'结果列表','/results',300,NULL),(401,'用户管理','/userList',400,NULL),(501,'连接点管理','/connectionList',500,NULL);
/*!40000 ALTER TABLE `t_submenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_type`
--

DROP TABLE IF EXISTS `t_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_type` (
  `tid` int NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型描述',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_type`
--

LOCK TABLES `t_type` WRITE;
/*!40000 ALTER TABLE `t_type` DISABLE KEYS */;
INSERT INTO `t_type` VALUES (1,'管网稳态模拟，根据集输压力和配产，计算井口压力和管网产能','稳态模拟'),(2,'管网优化计算','管网优化'),(3,'含引射器的管网优化计算','引射器优化');
/*!40000 ALTER TABLE `t_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'md5密码',
  `role` int NOT NULL COMMENT '用户角色',
  PRIMARY KEY (`uid`) USING BTREE,
  KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','0192023A7BBD73250516F069DF18B500',0),(3,'Tourist','cc03e747a6afbbcbf8be7668acfebee5',2),(8,'operator','2407bd807d6ca01d1bcd766c730cec9a',1),(9,'qwer1234','96e79218965eb72c92a549dd5a330112',1),(10,'test11','96e79218965eb72c92a549dd5a330112',1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_well`
--

DROP TABLE IF EXISTS `t_well`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_well` (
  `id` int NOT NULL COMMENT '节点编号',
  `flow_id` int DEFAULT NULL COMMENT '流id',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `well_base` FOREIGN KEY (`id`) REFERENCES `t_base` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_well`
--

LOCK TABLES `t_well` WRITE;
/*!40000 ALTER TABLE `t_well` DISABLE KEYS */;
INSERT INTO `t_well` VALUES (527,1),(552,1),(641,1),(646,1),(663,1);
/*!40000 ALTER TABLE `t_well` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-17 15:24:54
