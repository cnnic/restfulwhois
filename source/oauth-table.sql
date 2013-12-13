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
  `user_role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Dumping data for table users
#

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'auth','auth',NULL);
INSERT INTO `users` VALUES (2,'root','root',NULL);
INSERT INTO `users` VALUES (3,'admin','admin',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `oauth_accessor`;
CREATE TABLE `oauth_accessor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `request_token` varchar(100) DEFAULT NULL,
  `token_secret` varchar(100) DEFAULT NULL,
  `access_token` varchar(100) DEFAULT NULL,
  `app_key` varchar(40) DEFAULT NULL,
  `app_secret` varchar(40) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

LOCK TABLES `oauth_accessor` WRITE;
/*!40000 ALTER TABLE `oauth_accessor` DISABLE KEYS */;
INSERT INTO `oauth_accessor` VALUES (87,'aeb34f02ad87d39534b87ccba02403da','1acc4bd55518fdd95eeb527ce06ef374','a3fc662c504c9e83cb125523d9d88198','key1385973838215','secret1385973838215','2013-10-10 10:10:10');
INSERT INTO `oauth_accessor` VALUES (89,'94da680e60c7ad9de42aa8bfbf682475','5ecf7167d22792fef8b89c20f068cb3e',NULL,'key1385973838215','secret1385973838215','2012-10-10 10:10:10');
INSERT INTO `oauth_accessor` VALUES (90,'970bbecf2fba58d904c49730a8f668dd','9e30d2b3087bb97abc2225162ab8f51f','3c81887b49406861aca6c624b3e0cda4','key1385973838215','secret1385973838215','2012-10-10 10:10:10');
INSERT INTO `oauth_accessor` VALUES (91,'aaec04ec95014fb562b1e208b87dc344','16223307d1139face616a62f134298c0','363a04eecc25874a1cdc8a9d3737945f','key1385973838215','secret1385973838215','2012-10-10 10:10:10');
INSERT INTO `oauth_accessor` VALUES (92,'5fdb813dca371a6732dd22770f36d5e7','5dfdaf48ddcf1d925d80ab0812ca3873',NULL,'key1385973838215','secret1385973838215','2012-10-10 10:10:10');
/*!40000 ALTER TABLE `oauth_accessor` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `users_app`;
CREATE TABLE `users_app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `app_key` varchar(40) NOT NULL,
  `app_secret` varchar(80) NOT NULL,
  `app_description` varchar(200) NOT NULL,
  `user_id` int(10) DEFAULT NULL,
  `invalid_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

LOCK TABLES `users_app` WRITE;
/*!40000 ALTER TABLE `users_app` DISABLE KEYS */;
INSERT INTO `users_app` VALUES (3,'key-498702880','secret-858606152','范德萨',1,7);
INSERT INTO `users_app` VALUES (4,'key-498702880f','secret-858606152f','fff',2,7);
INSERT INTO `users_app` VALUES (7,'key1385973838215','secret1385973838215','fff',1,7);
INSERT INTO `users_app` VALUES (8,'key1386742777776','secret1386742777776','其它',2,7);
/*!40000 ALTER TABLE `users_app` ENABLE KEYS */;
UNLOCK TABLES;