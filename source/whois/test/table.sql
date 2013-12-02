# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: whois
# ------------------------------------------------------
# Server version 5.5.8

#
# Source for table users
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `pwd` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Dumping data for table users
#

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'auth','auth');
INSERT INTO `users` VALUES (2,'root','root');
INSERT INTO `users` VALUES (3,'admin','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `users_app`;
CREATE TABLE `users_app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `app_key` varchar(40) NOT NULL,
  `app_secret` varchar(80) NOT NULL,
  `app_description` varchar(200) NOT NULL,
  `user_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Dumping data for table users_app
#

LOCK TABLES `users_app` WRITE;
/*!40000 ALTER TABLE `users_app` DISABLE KEYS */;
INSERT INTO `users_app` VALUES (3,'key-498702880','secret-858606152','范德萨',1);
INSERT INTO `users_app` VALUES (4,'key-498702880f','secret-858606152f','fff',2);
INSERT INTO `users_app` VALUES (7,'key1385973838215','secret1385973838215','fff',1);
/*!40000 ALTER TABLE `users_app` ENABLE KEYS */;
UNLOCK TABLES;