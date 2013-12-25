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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Dumping data for table users
#

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'auth','auth','authenticated');
INSERT INTO `users` VALUES (2,'root','root','root');
INSERT INTO `users` VALUES (3,'admin','admin','anonymous');
INSERT INTO `users` VALUES (6,'xx','xx','anonymous');
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
  `oauth_user_role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8;

#
# Dumping data for table oauth_accessor
#

LOCK TABLES `oauth_accessor` WRITE;
/*!40000 ALTER TABLE `oauth_accessor` DISABLE KEYS */;
INSERT INTO `oauth_accessor` VALUES (210,'878e83972ef3e3a773d40212802c56ad','60a86853877b9982e423959feb9118d9','4626044f0deb6e38e21b11d3167f941e','key1385973838215','secret1385973838215','2013-12-24 09:36:56','root');
INSERT INTO `oauth_accessor` VALUES (211,'8391a6fdd6843bccf36a65419d20d1b6','177b1b1fa61733e21ef82e42078b0a25','66340e00b476f3b566cb08b37f27e4a8','key1385973838215','secret1385973838215','2013-12-24 09:37:16','anonymous');
INSERT INTO `oauth_accessor` VALUES (212,'51f9a54a4805ffec96e6154bed6e4a94','797b6ebaba92155ff8b675acea3cd356','dd0ea7644b8dfb713f2baf0c8743f04b','key1385973838215','secret1385973838215','2013-12-24 09:43:14','anonymous');
INSERT INTO `oauth_accessor` VALUES (213,'70bbc1793ab2fe40693e0539091be6bd','1564ff45300affa788a873abff9c4f38','7dbd4bfe478042685e80a60083e7304a','key1385973838215','secret1385973838215','2013-12-24 09:46:21','authenticated');
INSERT INTO `oauth_accessor` VALUES (214,'3d7c3b341406de31e245487e93f37442','aea62eae1153c70eb21b8b20439e5143','99926cc7aaf710e82453da3e2de01862','key1385973838215','secret1385973838215','2013-12-24 09:57:01','root');
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