# Host: 218.241.106.159  (Version: 5.5.11-log)
# Date: 2014-03-04 13:09:27
# Generator: MySQL-Front 5.3  (Build 2.42)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

#
# Source for table "autnum"
#

DROP TABLE IF EXISTS `autnum`;
CREATE TABLE `autnum` (
  `Handle` varchar(100) NOT NULL DEFAULT ' ',
  `Start_Autnum` int(11) DEFAULT NULL,
  `End_Autnum` int(11) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Type` varchar(100) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `Country` varchar(3) DEFAULT NULL,
  `Lang` varchar(10) DEFAULT ' ',
  `$x$xnic_auttest` varchar(100) DEFAULT '',
  PRIMARY KEY (`Handle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "autnum"
#

/*!40000 ALTER TABLE `autnum` DISABLE KEYS */;
INSERT INTO `autnum` VALUES ('AS1',1,1,'LVLT-1','DIRECT ALLOCATION','allocated','US','en',''),('AS1221',1221,1221,'APNIC-1221','DIRECT ALLOCATION','allocated','AU','en',''),('AS1233',1223,1223,'APNIC-1223','DIRECT ALLOCATION','allocated\'~\'ok','AU','en','auttest'),('AS1237',1237,1237,'APNIC-1237','DIRECT ALLOCATION','allocated','AU','en',''),('AS1250',1250,1250,'APNIC-1250','DIRECT ALLOCATION','allocated','AU','en',''),('AS1659',1659,1659,'APNIC-1659','DIRECT ALLOCATION','allocated','AU','en',''),('AS1704',1704,1704,'APNIC-1704','DIRECT ALLOCATION','allocated','AU','en',''),('AS173',173,173,'APNIC-173','DIRECT ALLOCATION','allocated','AU','en',''),('AS1768',1768,1769,'APNIC-1768','DIRECT ALLOCATION','allocated','AU','en',''),('AS1781',1781,1781,'APNIC-1781','DIRECT ALLOCATION','allocated','AU','en',''),('AS1851',1851,1851,'APNIC-1851','DIRECT ALLOCATION','allocated','AU','en',''),('AS2042',2042,2042,'APNIC-2042','DIRECT ALLOCATION','allocated','AU','en',''),('AS2144',2144,2144,'APNIC-2144','DIRECT ALLOCATION','allocated','AU','en',''),('AS2385',2385,2385,'APNIC-2385','DIRECT ALLOCATION','allocated','AU','en',''),('AS2497',2497,2528,'APNIC-AS-X-BLOCK','DIRECT ALLOCATION','allocated','AU','en',''),('AS2537',2537,2537,'APNIC-2537','DIRECT ALLOCATION','allocated','AU','en',''),('AS2554',2554,2554,'APNIC-2554','DIRECT ALLOCATION','allocated','AU','en',''),('AS2563',2563,2563,'APNIC-2563','DIRECT ALLOCATION','allocated','AU','en',''),('AS2569',2569,2570,'APNIC-2569','DIRECT ALLOCATION','allocated','AU','en',''),('AS2697',2697,2697,'APNIC-2697','DIRECT ALLOCATION','allocated','AU','en',''),('AS2706',2706,2706,'APNIC-2706','DIRECT ALLOCATION','allocated','AU','en',''),('AS2713',2713,2713,'APNIC-2713','DIRECT ALLOCATION','allocated','AU','en',''),('AS2756',2756,2756,'APNIC-2756','DIRECT ALLOCATION','allocated','AU','en',''),('AS2764',2764,2764,'APNIC-2764','DIRECT ALLOCATION','allocated','AU','en',''),('AS2772',2772,2772,'APNIC-2772','DIRECT ALLOCATION','allocated','AU','en',''),('AS2823',2823,2823,'APNIC-2823','DIRECT ALLOCATION','allocated','AU','en',''),('AS2907',2907,2907,'APNIC-2907','DIRECT ALLOCATION','allocated','AU','en',''),('AS2915',2915,2915,'APNIC-2915','DIRECT ALLOCATION','allocated','AU','en',''),('AS2925',2925,2926,'APNIC-2925','DIRECT ALLOCATION','allocated','AU','en',''),('AS3357',3357,3357,'APNIC-3357','DIRECT ALLOCATION','allocated','AU','en',''),('AS3363',3363,3363,'APNIC-3363','DIRECT ALLOCATION','allocated','AU','en',''),('AS3382',3382,3382,'APNIC-3382','DIRECT ALLOCATION','allocated','AU','en',''),('AS3391',3391,3391,'APNIC-3391','DIRECT ALLOCATION','allocated','AU','en',''),('AS3395',3395,3395,'APNIC-3395','DIRECT ALLOCATION','allocated','AU','en',''),('AS3460',3460,3460,'APNIC-3460','DIRECT ALLOCATION','allocated','AU','en',''),('AS3462',3462,3462,'APNIC-3462','DIRECT ALLOCATION','allocated','AU','en',''),('AS3488',3488,3488,'APNIC-3488','DIRECT ALLOCATION','allocated','AU','en',''),('AS3510',3510,3510,'APNIC-3510','DIRECT ALLOCATION','allocated','AU','en',''),('AS3550',3550,3550,'APNIC-3550','DIRECT ALLOCATION','allocated','AU','en',''),('AS3558',3558,3559,'APNIC-3558','DIRECT ALLOCATION','allocated','AU','en',''),('AS3583',3583,3583,'APNIC-3583','DIRECT ALLOCATION','allocated','AU','en',''),('AS3605',3605,3605,'APNIC-3605','DIRECT ALLOCATION','allocated','AU','en',''),('AS3608',3608,3608,'APNIC-3608','DIRECT ALLOCATION','allocated','AU','en',''),('AS3661',3661,3662,'APNIC-3661','DIRECT ALLOCATION','allocated','AU','en',''),('AS3689',3689,3693,'APNIC-3689','DIRECT ALLOCATION','allocated','AU','en',''),('AS3711',3711,3711,'APNIC-3711','DIRECT ALLOCATION','allocated','AU','en',''),('AS3717',3717,3717,'APNIC-3717','DIRECT ALLOCATION','allocated','AU','en',''),('AS3747',3747,3748,'APNIC-3747','DIRECT ALLOCATION','allocated','AU','en',''),('AS3757',3757,3758,'APNIC-3757','DIRECT ALLOCATION','allocated','AU','en',''),('AS3773',3773,3773,'APNIC-3773','DIRECT ALLOCATION','allocated','AU','en',''),('AS3775',3775,3775,'APNIC-3775','DIRECT ALLOCATION','allocated','AU','en',''),('AS3784',3784,3784,'APNIC-3784','DIRECT ALLOCATION','allocated','AU','en',''),('AS3786',3786,3787,'APNIC-3786','DIRECT ALLOCATION','allocated','AU','en',''),('AS3813',3813,3813,'APNIC-3813','DIRECT ALLOCATION','allocated','AU','en',''),('AS3825',3825,3825,'APNIC-3825','DIRECT ALLOCATION','allocated','AU','en',''),('AS3836',3836,3836,'APNIC-3836','DIRECT ALLOCATION','allocated','AU','en',''),('AS3839',3839,3840,'APNIC-3839','DIRECT ALLOCATION','allocated','AU','en',''),('AS3929',3929,3929,'APNIC-3929','DIRECT ALLOCATION','allocated','AU','en',''),('AS3969',3969,3969,'APNIC-3969','DIRECT ALLOCATION','allocated','AU','en',''),('AS3976',3976,3976,'APNIC-3976','DIRECT ALLOCATION','allocated','AU','en',''),('AS4007',4007,4007,'APNIC-4007','DIRECT ALLOCATION','allocated','AU','en',''),('AS4040',4040,4040,'APNIC-4040','DIRECT ALLOCATION','allocated','AU','en',''),('AS681',681,681,'APNIC-681','DIRECT ALLOCATION','allocated','AU','en','');
/*!40000 ALTER TABLE `autnum` ENABLE KEYS */;

#
# Source for table "autnum_redirect"
#

DROP TABLE IF EXISTS `autnum_redirect`;
CREATE TABLE `autnum_redirect` (
  `startNumber` int(11) NOT NULL,
  `endNumber` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `redirectURL` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Data for table "autnum_redirect"
#

/*!40000 ALTER TABLE `autnum_redirect` DISABLE KEYS */;
INSERT INTO `autnum_redirect` VALUES (1916,1916,2,'http://restfulwhoisv2.labs.lacnic.net/rdap/autnum'),(1917,1916,6,'http://restfulwhoisv2.labs.lacnic.net/rdap/autnum'),(12234,12237,7,'http://restfulwhoisv2.labs.lacnic.net/rdap/autnum'),(1111,111,9,'http://restfulwhoisv2.labs.lacnic.net/rdap/autnum');
/*!40000 ALTER TABLE `autnum_redirect` ENABLE KEYS */;

#
# Source for table "dnrdomain"
#

DROP TABLE IF EXISTS `dnrdomain`;
CREATE TABLE `dnrdomain` (
  `Handle` varchar(100) NOT NULL,
  `Ldh_Name` varchar(100) DEFAULT ' ',
  `Status` varchar(100) DEFAULT ' ',
  `Port43` varchar(40) DEFAULT ' ',
  `Lang` varchar(10) DEFAULT NULL,
  `Unicode_Name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Handle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "dnrdomain"
#

/*!40000 ALTER TABLE `dnrdomain` DISABLE KEYS */;
INSERT INTO `dnrdomain` VALUES ('20030310s10001s00013960-cn','z.cn','ok ',' ',' ','z.cn'),('20030311s10001s00033420-cn','abc.cn','ok','whois.cnnic.cn',' ','abc.cn'),('20030311s10001s00038551-cn','sina.cn','ok','whois.cnnic.cn',' ','sina.cn'),('20030311s10001s00038768-cn','163.cn','ok','whois.cnnic.cn',' ','163.cn'),('20030312s10001s00062053-cn','baidu.cn','serverDeleteProhibited\'~\'serverUpdateProhibited\'~\'serverTransferProhibited','whois.cnnic.cn',' ','baidu.cn'),('20030321s10001s00193214-cn','qq.cn','clientTransferProhibited','whois.cnnic.cn',' en','qq.cn'),('20030321s10001s00193214-zh','xn--qqa-cma.cn','ok','whois.cnnic.cn','zh','qqéa.cn'),('20060829s10001s77370958-cn','xn--faade-csa.com','ok','whois.cnnic.cn',' ','xn--faade-csa.com'),('20060829s10001s77371042-cn','bipa.cn','ok','whois.cnnic.cn',' ','bipa.cn'),('20060829s10001s77378526-cn','dila.cn','ok','whois.cnnic.cn',' ','dila.cn'),('20060829s10001s77378528-cn','faha.cn','ok','whois.cnnic.cn',' ','faha.cn'),('20120503s10001s30098636-cn','jcra.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcra.cn'),('20120614s10001s59901629-cn','AC80.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','AC80.cn'),('20120620s10001s88662672-cn','stcu.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','stcu.cn'),('20120620s10001s89007800-cn','hdkw.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','hdkw.cn'),('20120620s10001s89164404-cn','stau.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','stau.cn'),('20120629s10001s40700682-cn','wzyi.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','wzyi.cn'),('20120629s10001s41126142-cn','jxoi.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jxoi.cn'),('20120629s10001s41160130-cn','jxuy.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jxuy.cn'),('20120828s10001s51987299-cn','jxob.cn','serverDeleteProhibited,serverUpdateProhibited,serverTransferProhibited,clientUpdateProhibited,client','whois.cnnic.cn',' ','jxob.cn'),('20120828s10001s51987313-cn','jxpo.cn','ok','whois.cnnic.cn',' ','jxpo.cn'),('20120828s10001s51995593-cn','zsbo.cn','','whois.cnnic.cn',' ','zsbo.cn'),('20120828s10001s52018584-cn','jcpm.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcpm.cn'),('20120828s10001s52018602-cn','jcvx.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcvx.cn'),('20120828s10001s52044771-cn','xn--cole-9oa.example.com','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','école.example.com'),('20120828s10001s52049613-cn','xn--wxtr44c.com',NULL,'whois.cnnic.cn',' ','xn--wxtr44c.com'),('20120828s10001s52056788-cn','swxz.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swxz.cn'),('20120906s10001s53653714-cn','stqa.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','stqa.cn'),('20120906s10001s53653975-cn','stva.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','stva.cn'),('20120906s10001s53654172-cn','stya.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','stya.cn'),('20120906s10001s53657404-cn','swrd.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swrd.cn'),('20120906s10001s53657916-cn','jmfb.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jmfb.cn'),('20120906s10001s53658251-cn','stje.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','stje.cn'),('20120906s10001s53659439-cn','swef.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swef.cn'),('20120906s10001s53660962-cn','jmnd.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jmnd.cn'),('20120906s10001s53664248-cn','jcof.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcof.cn'),('20120906s10001s53664577-cn','scui.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','scui.cn'),('20120906s10001s53664778-cn','stwi.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','stwi.cn'),('20120906s10001s53665720-cn','swnj.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swnj.cn'),('20120906s10001s53665964-cn','jxog.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jxog.cn'),('20120906s10001s53667450-cn','jcoh.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcoh.cn'),('20120906s10001s53669911-cn','swnm.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swnm.cn'),('20120906s10001s53670551-cn','swzm.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swzm.cn'),('20120906s10001s53670843-cn','swgn.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swgn.cn'),('20120906s10001s53670938-cn','jcuj.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcuj.cn'),('20120906s10001s53671366-cn','SWRN.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','SWRN.cn'),('20120906s10001s53671956-cn','jmlk.cn','serverDeleteProhibited,serverUpdateProhibited,serverTransferProhibited,clientUpdateProhibited,client','whois.cnnic.cn',' ','jmlk.cn'),('20120906s10001s53671957-cn','swdo.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swdo.cn'),('20120906s10001s53672068-cn','jcnk.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcnk.cn'),('20120906s10001s53672369-cn','stjo.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','stjo.cn'),('20120906s10001s53672399-cn','scko.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','scko.cn'),('20120906s10001s53675199-cn','jmqm.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jmqm.cn'),('20120906s10001s53675236-cn','swfq.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swfq.cn'),('20120906s10001s53676974-cn','jcun.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcun.cn'),('20120906s10001s53677056-cn','swfr.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swfr.cn'),('20120906s10001s53677151-cn','scir.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','scir.cn'),('20120906s10001s53677773-cn','swrr.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','swrr.cn'),('20120906s10001s53681129-cn','stmu.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','stmu.cn'),('20120906s10001s53681396-cn','jmlq.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jmlq.cn'),('20120906s10001s53681665-cn','jcpq.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcpq.cn'),('20120906s10001s53681701-cn','jmpq.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jmpq.cn'),('20120906s10001s53681868-cn','stxu.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','stxu.cn'),('20120906s10001s53682987-cn','jcir.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcir.cn'),('20120906s10001s53683522-cn','jcpr.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcpr.cn'),('20120906s10001s53685095-cn','jcps.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcps.cn'),('20120906s10001s53686629-cn','jcwt.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcwt.cn'),('20120906s10001s53687099-cn','sciy.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','sciy.cn'),('20120906s10001s53688075-cn','jcvu.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcvu.cn'),('20120906s10001s53689348-cn','czqa.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','czqa.cn'),('20120906s10001s53690221-cn','jcwv.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcwv.cn'),('20120906s10001s53690416-cn','jcyv.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcyv.cn'),('20120906s10001s53691037-cn','jciw.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jciw.cn'),('20120906s10001s53691696-cn','jmrw.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jmrw.cn'),('20120906s10001s53691821-cn','jcuw.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','jcuw.cn'),('20120906s10001s53693397-cn','czqe.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','czqe.cn'),('20120906s10001s53698697-cn','czri.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','czri.cn'),('20120906s10001s53700663-cn','tpcg.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','tpcg.cn'),('20120906s10001s53706250-cn','czqo.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','czqo.cn'),('20120906s10001s53757619-cn','lwof.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','lwof.cn'),('20120906s10001s53823760-cn','muap.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','muap.cn'),('20120906s10001s53866745-cn','fjex.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','fjex.cn'),('20120906s10001s53887470-cn','gcaj.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','gcaj.cn'),('20120906s10001s53932422-cn','hdmk.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','hdmk.cn'),('20120906s10001s53934119-cn','xmvd.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','xmvd.cn'),('20120906s10001s53937220-cn','hdxn.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','hdxn.cn'),('20120906s10001s53937648-cn','hyeo.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','hyeo.cn'),('20120906s10001s53939946-cn','hdnp.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','hdnp.cn'),('20120906s10001s53941793-cn','hdpq.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','hdpq.cn'),('20120906s10001s53946322-cn','hyut.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','hyut.cn'),('20120906s10001s53946962-cn','hyeu.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','hyeu.cn'),('20120906s10001s53954563-cn','hdpy.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','hdpy.cn'),('20120906s10001s53956275-cn','xmvo.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','xmvo.cn'),('20120906s10001s53965852-cn','pkof.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','pkof.cn'),('20120906s10001s53967986-cn','xmlv.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','xmlv.cn'),('20120906s10001s53972937-cn','xmvy.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','xmvy.cn'),('20120906s10001s53981411-cn','yzue.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','yzue.cn'),('20120906s10001s54004341-cn','yzut.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','yzut.cn'),('20120906s10001s54025589-cn','zsbi.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','zsbi.cn'),('20120906s10001s54034109-cn','qnwp.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','qnwp.cn'),('20120906s10001s54037567-cn','zseq.cn','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn',' ','zseq.cn'),('unicode1','xn--xkry9kk1bz66a.xn--fiqs8s','clientDeleteProhibited\'~\'clientTransferProhibited','whois.cnnic.cn ',' ','清华大学.中国'),('unicode2','xn--satqqa-fva.cn','ok','whois.cnnic.cn','','satqqéa.cn'),('unicode5',' xn--aa-bja.cn','clientDeleteProhibited\'~\'clientTransferProhibited',' whois.cnnic.cn',NULL,'aéa.cn');
/*!40000 ALTER TABLE `dnrdomain` ENABLE KEYS */;

#
# Source for table "dnrentity"
#

DROP TABLE IF EXISTS `dnrentity`;
CREATE TABLE `dnrentity` (
  `Handle` varchar(100) NOT NULL,
  `Entity_Names` varchar(100) DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `Emails` varchar(100) DEFAULT NULL,
  `Port43` varchar(40) DEFAULT NULL,
  `Roles` varchar(50) DEFAULT NULL,
  `Lang` varchar(10) DEFAULT NULL,
  `Bday` varchar(100) DEFAULT NULL,
  `Anniversary` varchar(100) DEFAULT NULL,
  `Gender` varchar(100) DEFAULT NULL,
  `Kind` varchar(100) DEFAULT NULL,
  `Language_Tag_1` varchar(100) DEFAULT NULL,
  `Pref1` varchar(10) DEFAULT NULL,
  `Language_Tag_2` varchar(100) DEFAULT NULL,
  `Pref2` varchar(10) DEFAULT NULL,
  `Org` varchar(100) DEFAULT NULL,
  `Title` varchar(100) DEFAULT NULL,
  `Role` varchar(100) DEFAULT NULL,
  `Geo` varchar(100) DEFAULT NULL,
  `Key` varchar(256) DEFAULT NULL,
  `Tz` varchar(100) DEFAULT NULL,
  `Url` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`Handle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "dnrentity"
#

/*!40000 ALTER TABLE `dnrentity` DISABLE KEYS */;
INSERT INTO `dnrentity` VALUES ('15122_1','杭州狗狗网络有限公司\'~\'gougou@365.com','','gougou@365.com','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('belks_jenny','BELKS MEDIA\'~\'jennybrownbelks@gmail.com','','jennybrownbelks@gmail.com','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('bu1048055833141','百度在线网络技术（北京）有限公司\'~\'eric@baidu.com','','eric@baidu.com','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnnic-zdmd-022','Google Ireland Holdings\'~\'dns-admin@google.com','','dns-admin@google.com','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnniccn022','Dnbiz Limited','','','','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnniccn023','厦门东南融通在线科技有限公司(原厦门华商盛世网络有限公司)','','','','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnniccn033','北京中科三方ネットワーク技術有限会社','','','','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnniccn095','北京新网数码信息技术有限公司','','','','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnniccn101','IP Mirror Pte Ltd.','','','','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnniccn108','베이징 万网 지성 이 텍','','','','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnniccn115','厦门东南融通在线科技有限公司(原厦门华商盛世网络有限公司)','','','','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnniccn120','MarkMonitor Inc.','','','','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnniccn122','Пекин WanWang ZhiCheng науки и техники компании с ограниченной ответственностью','','','','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cnorg-w6qgyr','Ya Ma Xun Zhuo Yue You Xian Gong Si\'~\'globaladmin@lovellsnames.org','','globaladmin@lovellsnames.org','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('dnbz_orbissearch','ORBIS SEARCH\'~\'orbissearch@gmail.com','','orbissearch@gmail.com','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('hc85906540-cn','宁波市江北孔浦依迪埃主页设计工作室\'~\'abc.cn.jean@163.com','','abc.cn.jean@163.com','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('mmr-32107','Google Ireland Holdings\'~\'dns-admin@google.com','','dns-admin@google.com','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('qq1048056475479','中国联合网络通信有限公司黑龙江省分公司\'~\'liaomh@vip.hl.cn','','liaomh@vip.hl.cn','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('s1100582478155','北京搜狐新时代信息技术有限公司\'~\'jjzhang@sohu-inc.com',' ','jjzhang@sohu-inc.com','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('s1195618557743','广东广信通信服务有限公司\'~\'sjgl@163.cn','','sjgl@163.cn','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('s1255673574881','中国互联网络信息中心\'~\'中国互联网络信息中心\'~\'servicei@cnnic.cn\'~\'servicei@cnnic.cn',' ','servicei@cnnic.cn\'~\'servicei@cnnic.cn','whois.cnnic.cn','registrar',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('s1265874462073','新浪网技术（中国）有限公司\'~\'domainname@staff.sina.com.cn','','domainname@staff.sina.com.cn','whois.cnnic.cn','registrant',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('whois_private','satqqéa',' ','whois.private.service@gmail.com','whois.cnnic.cn','registrar',' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dnrentity` ENABLE KEYS */;

#
# Source for table "domain_redirect"
#

DROP TABLE IF EXISTS `domain_redirect`;
CREATE TABLE `domain_redirect` (
  `redirectType` varchar(100) DEFAULT NULL,
  `redirectURL` varchar(100) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "domain_redirect"
#

/*!40000 ALTER TABLE `domain_redirect` DISABLE KEYS */;
INSERT INTO `domain_redirect` VALUES ('la','https://rdap.centralnic.com/',1),('pw','https://rdap.centralnic.com/',2),('cc','http://dnrd.verisignlabs.com/',5);
/*!40000 ALTER TABLE `domain_redirect` ENABLE KEYS */;

#
# Source for table "dsdata"
#

DROP TABLE IF EXISTS `dsdata`;
CREATE TABLE `dsdata` (
  `Key_Tag` int(11) DEFAULT NULL,
  `Algorithm` int(11) DEFAULT NULL,
  `Digest` varchar(512) DEFAULT NULL,
  `Digest_Type` int(11) DEFAULT NULL,
  `DsDataID` varchar(100) NOT NULL,
  PRIMARY KEY (`DsDataID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "dsdata"
#

INSERT INTO `dsdata` VALUES (12345,3,'49FD46E6C4B45C55D4AC',1,'1');

#
# Source for table "errormessage"
#

DROP TABLE IF EXISTS `errormessage`;
CREATE TABLE `errormessage` (
  `Error_Code` varchar(100) DEFAULT NULL,
  `Title` varchar(256) DEFAULT NULL,
  `Description` varchar(256) DEFAULT NULL,
  `Lang` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "errormessage"
#

INSERT INTO `errormessage` VALUES ('404','Error Message','NOT FOUND',NULL),('400','Error Message','BAD REQUEST',NULL),('4145','Error Message','UNKNOWN_COMMAND',NULL),('429','Error Message','RATE LIMIT',NULL),('422','Error Message','UnProcessed',NULL);

#
# Source for table "events"
#

DROP TABLE IF EXISTS `events`;
CREATE TABLE `events` (
  `Event_Action` varchar(100) DEFAULT NULL,
  `Event_Actor` varchar(100) DEFAULT NULL,
  `Event_Date` varchar(100) DEFAULT NULL,
  `eventsId` varchar(50) NOT NULL,
  PRIMARY KEY (`eventsId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "events"
#

/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES ('registration','AWC12-ARI','1990-12-31T23:59:30Z','1'),('last changed','AWC12-ARIN','1990-12-31T23:59:30Z','2');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;

#
# Source for table "ip"
#

DROP TABLE IF EXISTS `ip`;
CREATE TABLE `ip` (
  `Handle` varchar(100) NOT NULL,
  `StartLowAddress` bigint(40) DEFAULT NULL,
  `IP_Version` varchar(2) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Type` varchar(100) DEFAULT NULL,
  `Country` varchar(10) DEFAULT NULL,
  `Parent_Handle` varchar(100) DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `Lang` varchar(10) DEFAULT NULL,
  `StartHighAddress` bigint(20) DEFAULT NULL,
  `EndLowAddress` bigint(20) DEFAULT NULL,
  `EndHighAddress` bigint(20) DEFAULT NULL,
  `$x$testip` varchar(50) DEFAULT '',
  PRIMARY KEY (`Handle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "ip"
#

/*!40000 ALTER TABLE `ip` DISABLE KEYS */;
INSERT INTO `ip` VALUES ('NET-0-0-0-0-1',2306131802814676993,'v4','SPECIAL-IPV4-LOCAL-ID-IANA-RESERVED','IANA Special Use','US',' ',' ','',0,16777215,0,''),('NET-1-0-0-0-1',16777216,'v4','APNIC-1','Allocated to APNIC','AU',' ',' ','',0,33554431,0,'testtest'),('NET-10-0-0-0-1',167772160,'v4','ATT-LIN850-0','Reassigned','US','','','',0,184549375,0,''),('NET-101-0-0-0-1',1694498816,'v4','APNIC-101','Allocated to APNIC','AU',' ',' ','',0,1711276031,0,''),('NET-103-0-0-0-1',1728053248,'v4','APNIC-103','Allocated to APNIC','AU',' ',' ','',0,1744830463,0,''),('NET-106-0-0-0-1',1778384896,'v4','APNIC-106','Allocated to APNIC','AU',' ',' ','',0,1795162106,0,''),('NET-11-0-0-0-1',184549376,'v4','DODIIS','Direct Allocation','US',' ',' ','',0,201326591,0,''),('NET-110-0-0-0-1',1845493760,'v4','APNIC-AP','Allocated to APNIC','AU',' ',' ','',0,1862270970,0,''),('NET-111-0-0-0-1',1862270976,'v4','APNIC-AP','Allocated to APNIC','AU',' ',' ','',0,1879048186,0,''),('NET-112-0-0-0-1',1879048192,'v4','APNIC-112','Allocated to APNIC','AU',' ',' ','',0,1895825402,0,''),('NET-113-0-0-0-1',1895825408,'v4','APNIC-113','Allocated to APNIC','AU',' ',' ','',0,1912602618,0,''),('NET-12-0-0-0-2',2306131802814676993,'v4','ATT-LIN850-0','Reassigned','US',' ',' ','',0,218103807,0,''),('NET-14-0-0-0-1',234881024,'v4','APNIC-14','Allocated to APNIC','AU','','','',0,251658239,0,''),('NET-2-0-0-0-1',33554432,'v4','2-RIPE','Allocated to RIPE NCC','NL','','','',0,50331647,0,''),('NET-218-0-0-0-1',3657433088,'v4','APNIC4','Allocated to APNIC','AU',' ',' ','',0,2147483647,0,''),('NET-23-0-0-0-1',385875968,'v4','AKAMAI','Direct Allocation','US',' ',' ','',0,402653183,0,''),('NET-27-0-0-0-1',452984832,'v4','APNIC-27','Allocated to APNIC','AU','','','',0,469762047,0,''),('NET-3-0-0-0-1',50331648,'v4','GE-INTERNET','Direct Assignment','US','','','',0,67108863,0,''),('NET-36-0-0-0-1',603979776,'v4','APNIC-36','Allocated to APNIC','AU',' ',' ','',0,620756991,0,''),('NET-39-0-0-0-1',654311424,'v4','APNIC-39','Allocated to APNIC','AU',' ',' ','',0,671088639,0,''),('NET-4-0-0-0-1',67108864,'v4','LVLT-ORG-4-8','Direct Allocation','US','','','',0,83886079,0,''),('NET-42-0-0-0-1',704643072,'v4','APNIC-42','Allocated to APNIC','AU',' ',' ','',0,838860799,0,''),('NET-43-0-0-0-1',721420288,'v4','APNIC-ERX-43','Early Registrations, Maintained by APNIC','AU',' ',' ','',0,738197503,0,''),('NET-49-0-0-0-0',2306131802814676993,'v4','APNIC-49','Allocated to APNIC','AU',' ',' ','',0,721420287,0,''),('NET-5-0-0-0-1',83886080,'v4','RIPE-5','Allocated to RIPE NCC','NL','','','',0,100663295,0,''),('NET-50-0-0-0-0',2306131802814676993,'v4','NET50','Allocated to ARIN','zxh','','','',0,855638015,0,''),('NET-50-0-0-0-1',838860800,'v4','SONIC-BLK','Direct Allocation','US','','','',0,838991871,0,''),('NET-58-0-0-0-1',973078528,'v4','APNIC-58','Allocated to APNIC','AU',' ',' ','',0,989855743,0,''),('NET-59-0-0-0-1',989855744,'v4','APNIC-59','Allocated to APNIC','AU',' ',' ','',0,1006632959,0,''),('NET-6-0-0-0-1',100663296,'v4','CONUS-YPG-NET','Direct Allocation','US','','','',0,117440511,0,''),('NET-60-0-0-0-1',1006632960,'v4','APNIC-60','Allocated to APNIC','AU',' ',' ','',0,1023410175,0,''),('NET-61-0-0-0-1',1023410176,'v4','APNIC3','Allocated to APNIC','AU',' ',' ','',0,1040187391,0,''),('NET-7-0-0-0-1',117440512,'v4','DISANET7','Direct Allocation','US','','','',0,134217727,0,''),('NET-8-0-0-0-1',134217728,'v4','LVLT-ORG-8-8','Direct Allocation','US','','','',0,150994943,0,''),('NET-9-0-0-0-1',150994944,'v4','IBM','Direct Assignment','US','','','',0,167772159,0,''),('net-Ipv6',2306131802814676993,'v6','IBM','Direct Assignment','AU','','','',0,2306131802814676994,2306139570357600256,'');
/*!40000 ALTER TABLE `ip` ENABLE KEYS */;

#
# Source for table "ip_redirect"
#

DROP TABLE IF EXISTS `ip_redirect`;
CREATE TABLE `ip_redirect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `StartHighAddress` bigint(100) DEFAULT NULL,
  `redirectURL` varchar(100) DEFAULT NULL,
  `StartLowAddress` bigint(100) DEFAULT NULL,
  `EndHighAddress` bigint(100) DEFAULT NULL,
  `EndLowAddress` bigint(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "ip_redirect"
#

/*!40000 ALTER TABLE `ip_redirect` DISABLE KEYS */;
INSERT INTO `ip_redirect` VALUES (1,0,'http://www.otherRdapServer.com1',3232235521,0,3232235527),(2,0,'http://www.otherRdapServer.com3',0,0,3232235521),(5,0,'http://www.otherRdapServer.com2',3232235521,0,3232235523);
/*!40000 ALTER TABLE `ip_redirect` ENABLE KEYS */;

#
# Source for table "keydata"
#

DROP TABLE IF EXISTS `keydata`;
CREATE TABLE `keydata` (
  `Flags` varchar(100) DEFAULT NULL,
  `Protocol` varchar(100) DEFAULT NULL,
  `Public_Key` varchar(512) DEFAULT NULL,
  `Algorithm` int(11) DEFAULT NULL,
  `KeyDataID` varchar(100) NOT NULL,
  PRIMARY KEY (`KeyDataID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "keydata"
#


#
# Source for table "link"
#

DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `Value` varchar(150) DEFAULT NULL,
  `Rel` varchar(10) DEFAULT NULL,
  `Href` varchar(100) DEFAULT NULL,
  `linkId` varchar(100) NOT NULL DEFAULT '',
  `hreflang` varchar(50) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `media` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`linkId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "link"
#

/*!40000 ALTER TABLE `link` DISABLE KEYS */;
INSERT INTO `link` VALUES ('http://rdap.restfulwhois.org/ip/2.0.0.0/8','self','http://rdap.restfulwhois.org/ip/2.0.0.0/8','2-RIPE-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/ABUSE546-ARIN','self','http://rdap.restfulwhois.org/entity/ABUSE546-ARIN','ABUSE546-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/23.0.0.0/12','self','http://rdap.restfulwhois.org/ip/23.0.0.0/12','AKAMAI-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/AKAMAI','self','http://rdap.restfulwhois.org/entity/AKAMAI','AKAMAI-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/ALO-3','self','http://rdap.restfulwhois.org/entity/ALO-3','ALO-3-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/APL7-ARIN','self','http://rdap.restfulwhois.org/entity/APL7-ARIN','APL7-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/APL8-ARIN','self','http://rdap.restfulwhois.org/entity/APL8-ARIN','APL8-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/1.0.0.0/8','self','http://rdap.restfulwhois.org/ip/1.0.0.0/8','APNIC-1-IP-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/101.0.0.0/8','self','http://rdap.restfulwhois.org/ip/101.0.0.0/8','APNIC-101-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/103.0.0.0/8','self','http://rdap.restfulwhois.org/ip/103.0.0.0/8','APNIC-103-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/106.0.0.0/8','self','http://rdap.restfulwhois.org/ip/106.0.0.0/8','APNIC-106-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/112.0.0.0/8','self','http://rdap.restfulwhois.org/ip/112.0.0.0/8','APNIC-112-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/113.0.0.0/9','self','http://rdap.restfulwhois.org/ip/113.0.0.0/9','APNIC-113-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/14.0.0.0/8','self','http://rdap.restfulwhois.org/ip/14.0.0.0/8','APNIC-14-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/1.0.0.0/16','self','http://rdap.restfulwhois.org/ip/1.0.0.0/16','APNIC-2-IP-LINK','a','a','a','application/rdap+json'),('http://rdap.restfulwhois.org/ip/27.0.0.0/8','self','http://rdap.restfulwhois.org/ip/27.0.0.0/8','APNIC-27-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/36.0.0.0/8','self','http://rdap.restfulwhois.org/ip/36.0.0.0/8','APNIC-36-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/39.0.0.0/8','self','http://rdap.restfulwhois.org/ip/39.0.0.0/8','APNIC-39-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/42.0.0.0/8','self','http://rdap.restfulwhois.org/ip/42.0.0.0/8','APNIC-42-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/49.0.0.0/8','self','http://rdap.restfulwhois.org/ip/49.0.0.0/8','APNIC-49-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/58.0.0.0/8','self','http://rdap.restfulwhois.org/ip/58.0.0.0/8','APNIC-58-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/59.0.0.0/8','self','http://rdap.restfulwhois.org/ip/59.0.0.0/8','APNIC-59-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/60.0.0.0/8','self','http://rdap.restfulwhois.org/ip/60.0.0.0/8','APNIC-60-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/110.0.0.0/8','self','http://rdap.restfulwhois.org/ip/110.0.0.0/8','APNIC-AP-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/111.0.0.0/8','self','http://rdap.restfulwhois.org/ip/111.0.0.0/8','APNIC-AP1-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/AWC12-ARIN','self','http://rdap.restfulwhois.org/entity/AWC12-ARIN','APNIC-AWC12-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/43.0.0.0/8','self','http://rdap.restfulwhois.org/ip/43.0.0.0/8','APNIC-ERX-43-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/APNIC','self','http://rdap.restfulwhois.org/entity/APNIC','APNIC-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/61.0.0.0/8','self','http://rdap.restfulwhois.org/ip/61.0.0.0/8','APNIC3-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/218.0.0.0/8','self','http://rdap.restfulwhois.org/ip/218.0.0.0/8','APNIC4-IP-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/ARIN-HOSTMASTER','self','http://rdap.restfulwhois.org/entity/ARIN-HOSTMASTER','ARIN-HOSTMASTER-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/ARIN','self','http://rdap.restfulwhois.org/entity/ARIN','ARIN-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/192.in-addr.arp','self','http://rdap.restfulwhois.org/domain/192.in-addr.arp','ARP-1-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS1','self','http://rdap.restfulwhois.org/autnum/AS1','AS1-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS1221','self','http://rdap.restfulwhois.org/autnum/AS1221','AS1221-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS1223','self','http://rdap.restfulwhois.org/autnum/AS1223','AS1223-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS1237','self','http://rdap.restfulwhois.org/autnum/AS1237','AS1237-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS1250','self','http://rdap.restfulwhois.org/autnum/AS1250','AS1250-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS1659','self','http://rdap.restfulwhois.org/autnum/AS1659','AS1659-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS1704','self','http://rdap.restfulwhois.org/autnum/AS1704','AS1704-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS173','self','http://rdap.restfulwhois.org/autnum/AS173','AS173-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS1768','self','http://rdap.restfulwhois.org/autnum/AS1768','AS1768-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS1781','self','http://rdap.restfulwhois.org/autnum/AS1781','AS1781-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS1851','self','http://rdap.restfulwhois.org/autnum/AS1851','AS1851-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2042','self','http://rdap.restfulwhois.org/autnum/AS2042','AS2042-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2144','self','http://rdap.restfulwhois.org/autnum/AS2144','AS2144-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2385','self','http://rdap.restfulwhois.org/autnum/AS2385','AS2385-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2497','self','http://rdap.restfulwhois.org/autnum/AS2497','AS2497-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2537','self','http://rdap.restfulwhois.org/autnum/AS2537','AS2537-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2554','self','http://rdap.restfulwhois.org/autnum/AS2554','AS2554-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2563','self','http://rdap.restfulwhois.org/autnum/AS2563','AS2563-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2569','self','http://rdap.restfulwhois.org/autnum/AS2569','AS2569-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2697','self','http://rdap.restfulwhois.org/autnum/AS2697','AS2697-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2706','self','http://rdap.restfulwhois.org/autnum/AS2706','AS2706-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2713','self','http://rdap.restfulwhois.org/autnum/AS2713','AS2713-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2756','self','http://rdap.restfulwhois.org/autnum/AS2756','AS2756-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2764','self','http://rdap.restfulwhois.org/autnum/AS2764','AS2764-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2772','self','http://rdap.restfulwhois.org/autnum/AS2772','AS2772-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2823','self','http://rdap.restfulwhois.org/autnum/AS2823','AS2823-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2907','self','http://rdap.restfulwhois.org/autnum/AS2907','AS2907-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2915','self','http://rdap.restfulwhois.org/autnum/AS2915','AS2915-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS2925','self','http://rdap.restfulwhois.org/autnum/AS2925','AS2925-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3357','self','http://rdap.restfulwhois.org/autnum/AS3357','AS3357-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3363','self','http://rdap.restfulwhois.org/autnum/AS3363','AS3363-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3382','self','http://rdap.restfulwhois.org/autnum/AS3382','AS3382-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3391','self','http://rdap.restfulwhois.org/autnum/AS3391','AS3391-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3395','self','http://rdap.restfulwhois.org/autnum/AS3395','AS3395-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3460','self','http://rdap.restfulwhois.org/autnum/AS3460','AS3460-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3462','self','http://rdap.restfulwhois.org/autnum/AS3462','AS3462-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3488','self','http://rdap.restfulwhois.org/autnum/AS3488','AS3488-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3510','self','http://rdap.restfulwhois.org/autnum/AS3510','AS3510-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3550','self','http://rdap.restfulwhois.org/autnum/AS3550','AS3550-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3558','self','http://rdap.restfulwhois.org/autnum/AS3558','AS3558-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3583','self','http://rdap.restfulwhois.org/autnum/AS3583','AS3583-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3605','self','http://rdap.restfulwhois.org/autnum/AS3605','AS3605-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3608','self','http://rdap.restfulwhois.org/autnum/AS3608','AS3608-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3661','self','http://rdap.restfulwhois.org/autnum/AS3661','AS3661-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3689','self','http://rdap.restfulwhois.org/autnum/AS3689','AS3689-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3711','self','http://rdap.restfulwhois.org/autnum/AS3711','AS3711-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3717','self','http://rdap.restfulwhois.org/autnum/AS3717','AS3717-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3747','self','http://rdap.restfulwhois.org/autnum/AS3747','AS3747-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3757','self','http://rdap.restfulwhois.org/autnum/AS3757','AS3757-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3773','self','http://rdap.restfulwhois.org/autnum/AS3773','AS3773-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3775','self','http://rdap.restfulwhois.org/autnum/AS3775','AS3775-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3784','self','http://rdap.restfulwhois.org/autnum/AS3784','AS3784-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3786','self','http://rdap.restfulwhois.org/autnum/AS3786','AS3786-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3813','self','http://rdap.restfulwhois.org/autnum/AS3813','AS3813-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3825','self','http://rdap.restfulwhois.org/autnum/AS3825','AS3825-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3836','self','http://rdap.restfulwhois.org/autnum/AS3836','AS3836-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3839','self','http://rdap.restfulwhois.org/autnum/AS3839','AS3839-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3929','self','http://rdap.restfulwhois.org/autnum/AS3929','AS3929-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3969','self','http://rdap.restfulwhois.org/autnum/AS3969','AS3969-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS3976','self','http://rdap.restfulwhois.org/autnum/AS3976','AS3976-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS4007','self','http://rdap.restfulwhois.org/autnum/AS4007','AS4007-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS4040','self','http://rdap.restfulwhois.org/autnum/AS4040','AS4040-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/autnum/AS681','self','http://rdap.restfulwhois.org/autnum/AS681','AS681-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/12.0.0.0/24','self','http://rdap.restfulwhois.org/ip/12.0.0.0/24','ATT-LIN850-0-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/CMU8-ARIN','self','http://rdap.restfulwhois.org/entity/CMU8-ARIN','CMU8-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnniccn022','self','http://rdap.restfulwhois.org/entity/cnniccn022','cnniccn022',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnniccn023','self','http://rdap.restfulwhois.org/entity/cnniccn023','cnniccn023',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnniccn033','self','http://rdap.restfulwhois.org/entity/cnniccn033','cnniccn033',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnniccn095','self','http://rdap.restfulwhois.org/entity/cnniccn095','cnniccn095',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnniccn101','self','http://rdap.restfulwhois.org/entity/cnniccn101','cnniccn101',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnniccn108','self','http://rdap.restfulwhois.org/entity/cnniccn108','cnniccn108',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnniccn115','self','http://rdap.restfulwhois.org/entity/cnniccn115','cnniccn115',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnniccn120','self','http://rdap.restfulwhois.org/entity/cnniccn120','cnniccn120',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnniccn122','self','http://rdap.restfulwhois.org/entity/cnniccn122','cnniccn122',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/6.0.0.0/8','self','http://rdap.restfulwhois.org/ip/6.0.0.0/8','CONUS-YPG-NET-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/DNIC','self','http://rdap.restfulwhois.org/entity/DNIC','DNIC-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/11.0.0.0/8','self','http://rdap.restfulwhois.org/ip/11.0.0.0/8','DODIIS-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcnk.cn','self','http://rdap.restfulwhois.org/domain/jcnk.cn','domain1',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/zseq.cn','self','http://rdap.restfulwhois.org/domain/zseq.cn','domain10',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jciw.cn','self','http://rdap.restfulwhois.org/domain/jciw.cn','domain11',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/163.cn','self','http://rdap.restfulwhois.org/domain/163.cn','domain12',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/hdmk.cn','self','http://rdap.restfulwhois.org/domain/hdmk.cn','domain13',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcwt.cn','self','http://rdap.restfulwhois.org/domain/jcwt.cn','domain14',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jmqm.cn','self','http://rdap.restfulwhois.org/domain/jmqm.cn','domain15',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/stje.cn','self','http://rdap.restfulwhois.org/domain/stje.cn','domain16',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcuj.cn','self','http://rdap.restfulwhois.org/domain/jcuj.cn','domain17',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jmnd.cn','self','http://rdap.restfulwhois.org/domain/jmnd.cn','domain18',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/czri.cn','self','http://rdap.restfulwhois.org/domain/czri.cn','domain19',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/baidu.cn','self','http://rdap.restfulwhois.org/domain/baidu.cn','domain2',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcvx.cn','self','http://rdap.restfulwhois.org/domain/jcvx.cn','domain20',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swfq.cn','self','http://rdap.restfulwhois.org/domain/swfq.cn','domain21',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcun.cn','self','http://rdap.restfulwhois.org/domain/jcun.cn','domain22',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/stva.cn','self','http://rdap.restfulwhois.org/domain/stva.cn','domain23',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jmlk.cn','self','http://rdap.restfulwhois.org/domain/jmlk.cn','domain24',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jxob.cn','self','http://rdap.restfulwhois.org/domain/jxob.cn','domain25',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcvu.cn','self','http://rdap.restfulwhois.org/domain/jcvu.cn','domain26',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jxpo.cn','self','http://rdap.restfulwhois.org/domain/jxpo.cn','domain27',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swrd.cn','self','http://rdap.restfulwhois.org/domain/swrd.cn','domain28',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swrr.cn','self','http://rdap.restfulwhois.org/domain/swrr.cn','domain29',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/fjex.cn','self','http://rdap.restfulwhois.org/domain/fjex.cn','domain3',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcra.cn','self','http://rdap.restfulwhois.org/domain/jcra.cn','domain30',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/tpcg.cn','self','http://rdap.restfulwhois.org/domain/tpcg.cn','domain31',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swnj.cn','self','http://rdap.restfulwhois.org/domain/swnj.cn','domain32',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/stwi.cn','self','http://rdap.restfulwhois.org/domain/stwi.cn','domain33',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/yzue.cn','self','http://rdap.restfulwhois.org/domain/yzue.cn','domain34',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/hyeo.cn','self','http://rdap.restfulwhois.org/domain/hyeo.cn','domain35',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/scui.cn','self','http://rdap.restfulwhois.org/domain/scui.cn','domain36',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swxz.cn','self','http://rdap.restfulwhois.org/domain/swxz.cn','domain37',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/stjo.cn','self','http://rdap.restfulwhois.org/domain/stjo.cn','domain38',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/czqe.cn','self','http://rdap.restfulwhois.org/domain/czqe.cn','domain39',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcwv.cn','self','http://rdap.restfulwhois.org/domain/jcwv.cn','domain4',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/ac80.cn','self','http://rdap.restfulwhois.org/domain/ac80.cn','domain40',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/stau.cn','self','http://rdap.restfulwhois.org/domain/stau.cn','domain41',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/sohu.cn','self','http://rdap.restfulwhois.org/domain/sohu.cn','domain42',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jmfb.cn','self','http://rdap.restfulwhois.org/domain/jmfb.cn','domain43',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/gcaj.cn','self','http://rdap.restfulwhois.org/domain/gcaj.cn','domain44',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swrn.cn','self','http://rdap.restfulwhois.org/domain/swrn.cn','domain45',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcyv.cn','self','http://rdap.restfulwhois.org/domain/jcyv.cn','domain46',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/czqo.cn','self','http://rdap.restfulwhois.org/domain/czqo.cn','domain47',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/sina.cn','self','http://rdap.restfulwhois.org/domain/sina.cn','domain48',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcuw.cn','self','http://rdap.restfulwhois.org/domain/jcuw.cn','domain49',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/stxu.cn','self','http://rdap.restfulwhois.org/domain/stxu.cn','domain5',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jxog.cn','self','http://rdap.restfulwhois.org/domain/jxog.cn','domain50',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/xmlv.cn','self','http://rdap.restfulwhois.org/domain/xmlv.cn','domain51',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/hdnp.cn','self','http://rdap.restfulwhois.org/domain/hdnp.cn','domain52',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/muap.cn','self','http://rdap.restfulwhois.org/domain/muap.cn','domain53',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcpm.cn','self','http://rdap.restfulwhois.org/domain/jcpm.cn','domain54',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jmrw.cn','self','http://rdap.restfulwhois.org/domain/jmrw.cn','domain55',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/xmvo.cn','self','http://rdap.restfulwhois.org/domain/xmvo.cn','domain56',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/xmvy.cn','self','http://rdap.restfulwhois.org/domain/xmvy.cn','domain57',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/cnnic.cn','self','http://rdap.restfulwhois.org/domain/cnnic.cn','domain58',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/scil.cn','self','http://rdap.restfulwhois.org/domain/scil.cn','domain59',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/stqa.cn','self','http://rdap.restfulwhois.org/domain/stqa.cn','domain6',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/pkof.cn','self','http://rdap.restfulwhois.org/domain/pkof.cn','domain60',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/hdkw.cn','self','http://rdap.restfulwhois.org/domain/hdkw.cn','domain61',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/scko.cn','self','http://rdap.restfulwhois.org/domain/scko.cn','domain62',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/stya.cn','self','http://rdap.restfulwhois.org/domain/stya.cn','domain63',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcpr.cn','self','http://rdap.restfulwhois.org/domain/jcpr.cn','domain64',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/zsbi.cn','self','http://rdap.restfulwhois.org/domain/zsbi.cn','domain65',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/scir.cn','self','http://rdap.restfulwhois.org/domain/scir.cn','domain66',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/stmu.cn','self','http://rdap.restfulwhois.org/domain/stmu.cn','domain67',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swdo.cn','self','http://rdap.restfulwhois.org/domain/swdo.cn','domain68',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/stcu.cn','self','http://rdap.restfulwhois.org/domain/stcu.cn','domain69',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/hdpq.cn','self','http://rdap.restfulwhois.org/domain/hdpq.cn','domain7',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jmpq.cn','self','http://rdap.restfulwhois.org/domain/jmpq.cn','domain70',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/xmvd.cn','self','http://rdap.restfulwhois.org/domain/xmvd.cn','domain71',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/hdpy.cn','self','http://rdap.restfulwhois.org/domain/hdpy.cn','domain72',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/hyut.cn','self','http://rdap.restfulwhois.org/domain/hyut.cn','domain73',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/satu.cn','self','http://rdap.restfulwhois.org/domain/satu.cn','domain74',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/lwof.cn','self','http://rdap.restfulwhois.org/domain/lwof.cn','domain75',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/faha.cn','self','http://rdap.restfulwhois.org/domain/faha.cn','domain76',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/wzyi.cn','self','http://rdap.restfulwhois.org/domain/wzyi.cn','domain77',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/sciy.cn','self','http://rdap.restfulwhois.org/domain/sciy.cn','domain78',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/hdxn.cn','self','http://rdap.restfulwhois.org/domain/hdxn.cn','domain79',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swfr.cn','self','http://rdap.restfulwhois.org/domain/swfr.cn','domain8',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/z.cn','self','http://rdap.restfulwhois.org/domain/z.cn','domain80',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jxoi.cn','self','http://rdap.restfulwhois.org/domain/jxoi.cn','domain81',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jxuy.cn','self','http://rdap.restfulwhois.org/domain/jxuy.cn','domain82',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/qnwp.cn','self','http://rdap.restfulwhois.org/domain/qnwp.cn','domain83',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swef.cn','self','http://rdap.restfulwhois.org/domain/swef.cn','domain84',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcof.cn','self','http://rdap.restfulwhois.org/domain/jcof.cn','domain85',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcir.cn','self','http://rdap.restfulwhois.org/domain/jcir.cn','domain86',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/abc.cn','self','http://rdap.restfulwhois.org/domain/abc.cn','domain87',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jmfh.cn','self','http://rdap.restfulwhois.org/domain/jmfh.cn','domain88',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swnm.cn','self','http://rdap.restfulwhois.org/domain/swnm.cn','domain89',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swgn.cn','self','http://rdap.restfulwhois.org/domain/swgn.cn','domain9',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/yzut.cn','self','http://rdap.restfulwhois.org/domain/yzut.cn','domain90',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/swzm.cn','self','http://rdap.restfulwhois.org/domain/swzm.cn','domain91',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcoh.cn','self','http://rdap.restfulwhois.org/domain/jcoh.cn','domain92',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jmlq.cn','self','http://rdap.restfulwhois.org/domain/jmlq.cn','domain93',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/qq.cn','self','http://rdap.restfulwhois.org/domain/qq.cn','domain94',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/hyeu.cn','self','http://rdap.restfulwhois.org/domain/hyeu.cn','domain95',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/xmih.cn','self','http://rdap.restfulwhois.org/domain/xmih.cn','domain96',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/zsbo.cn','self','http://rdap.restfulwhois.org/domain/zsbo.cn','domain97',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/domain/jcpq.cn','self','http://rdap.restfulwhois.org/domain/jcpq.cn','domain98',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1000',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/bu1048055833141','self','http://rdap.restfulwhois.org/entity/bu1048055833141','entity1001',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1002',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1003',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1004',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1005',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1006',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1007',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1008',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1009',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1010',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/s1195618557743','self','http://rdap.restfulwhois.org/entity/s1195618557743','entity1011',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1012',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1013',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1014',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1015',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1016',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1017',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1018',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1019',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1020',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1021',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1022',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1023',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1024',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1025',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1026',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1027',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1028',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/dnbz_orbissearch','self','http://rdap.restfulwhois.org/entity/dnbz_orbissearch','entity1029',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1030',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1031',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1032',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1033',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1034',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1035',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1036',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1037',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1038',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/belks_jenny','self','http://rdap.restfulwhois.org/entity/belks_jenny','entity1039',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/belks_jenny','self','http://rdap.restfulwhois.org/entity/belks_jenny','entity1040',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/s1100582478155','self','http://rdap.restfulwhois.org/entity/s1100582478155','entity1041',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1042',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1043',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1044',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1045',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1046',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/s1265874462073','self','http://rdap.restfulwhois.org/entity/s1265874462073','entity1047',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1048',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1049',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1050',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1051',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1052',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1053',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1054',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1055',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1056',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1058',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1059',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/belks_jenny','self','http://rdap.restfulwhois.org/entity/belks_jenny','entity1060',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1061',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1062',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1063',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1064',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1065',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1066',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1067',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/belks_jenny','self','http://rdap.restfulwhois.org/entity/belks_jenny','entity1068',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1069',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1070',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1071',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1072',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/15122_1','self','http://rdap.restfulwhois.org/entity/15122_1','entity1073',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1074',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/15122_1','self','http://rdap.restfulwhois.org/entity/15122_1','entity1075',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/belks_jenny','self','http://rdap.restfulwhois.org/entity/belks_jenny','entity1076',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1077',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1078',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnorg-w6qgyr','self','http://rdap.restfulwhois.org/entity/cnorg-w6qgyr','entity1079',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/belks_jenny','self','http://rdap.restfulwhois.org/entity/belks_jenny','entity1080',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/belks_jenny','self','http://rdap.restfulwhois.org/entity/belks_jenny','entity1081',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1082',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1083',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1084',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1085',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/hc85906540-cn','self','http://rdap.restfulwhois.org/entity/hc85906540-cn','entity1086',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1087',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1088',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1089',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1090',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1091',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1092',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/qq1048056475479','self','http://rdap.restfulwhois.org/entity/qq1048056475479','entity1093',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1094',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1095',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1096',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1097',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/cnnic-zdmd-022','self','http://rdap.restfulwhois.org/entity/cnnic-zdmd-022','entity1098',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/mmr-32107','self','http://rdap.restfulwhois.org/entity/mmr-32107','entity1099',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/15122_1','self','http://rdap.restfulwhois.org/entity/15122_1','entity1100',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1101',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/whois_private','self','http://rdap.restfulwhois.org/entity/whois_private','entity1102',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/15122_1','self','http://rdap.restfulwhois.org/entity/15122_1','entity1103',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/3.0.0.0/8','self','http://rdap.restfulwhois.org/ip/3.0.0.0/8','GE-INTERNET-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/GENERA-9','self','http://rdap.restfulwhois.org/entity/GENERA-9','GENERA-9-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/GET2-ORG-ARIN','self','http://rdap.restfulwhois.org/entity/GET2-ORG-ARIN','GET2-ORG-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/HEADQU-3','self','http://rdap.restfulwhois.org/entity/HEADQU-3','HEADQU-3-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/IANA-ARIN','self','http://rdap.restfulwhois.org/entity/IANA-ARIN','IANA-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/IANA-IP-ARIN','self','http://rdap.restfulwhois.org/entity/IANA-IP-ARIN','IANA-IP-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/IANA','self','http://rdap.restfulwhois.org/entity/IANA','IANA-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/IBM-1','self','http://rdap.restfulwhois.org/entity/IBM-1','IBM-1-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/9.0.0.0/8','self','http://rdap.restfulwhois.org/ip/9.0.0.0/8','IBM-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/IPADD5-ARIN','self','http://rdap.restfulwhois.org/entity/IPADD5-ARIN','IPADD5-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/4.0.0.0/8','self','http://rdap.restfulwhois.org/ip/4.0.0.0/8','LVLT-ORG-4-8-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/8.0.0.0/8','self','http://rdap.restfulwhois.org/ip/8.0.0.0/8','LVLT-ORG-8-8-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/LVLT','self','http://rdap.restfulwhois.org/entity/LVLT','LVLT-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/MHA379-ARIN','self','http://rdap.restfulwhois.org/entity/MHA379-ARIN','MHA379-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/50.0.0.0/8','self','http://rdap.restfulwhois.org/ip/50.0.0.0/8','NET50-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/NETWO144-ARIN','self','http://rdap.restfulwhois.org/entity/NETWO144-ARIN','NETWO144-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/NF81-ARIN','self','http://rdap.restfulwhois.org/entity/NF81-ARIN','NF81-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.example.net/entity/AWC12-ARIN','alternate','http://rdap.www.example.com/terms_of_use.html','noticelink1','zh','aa','test','application/rdap+json'),('http://rdap.restfulwhois.org/entity/RAIN-ARIN','self','http://rdap.restfulwhois.org/entity/RAIN-ARIN','RAIN-ARIN--LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/REGIS10-ARIN','self','http://rdap.restfulwhois.org/entity/REGIS10-ARIN','REGIS10-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/5.0.0.0/8','self','http://rdap.restfulwhois.org/ip/5.0.0.0/8','RIPE-5-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/RIPE','self','http://rdap.restfulwhois.org/entity/RIPE','RIPE-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/RNO29-ARIN','self','http://rdap.restfulwhois.org/entity/RNO29-ARIN','RNO29-ARIN-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/entity/SNIC','self','http://rdap.restfulwhois.org/entity/SNIC','SNIC-REG-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/50.0.0.0/15','self','http://rdap.restfulwhois.org/ip/50.0.0.0/15','SONIC-BLK-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/50.0.0.0/8','up','http://rdap.restfulwhois.org/ip/50.0.0.0/8','SONIC-BLK-PARENT-LINK',' ',' ',' ','application/rdap+json'),('http://rdap.restfulwhois.org/ip/0.0.0.0/8','self','http://rdap.restfulwhois.org/ip/0.0.0.0/8','SPECIAL-IPV4-LOCAL-ID-IANA-RESERVED-LINK',' ',' ',' ','application/rdap+json');
/*!40000 ALTER TABLE `link` ENABLE KEYS */;

#
# Source for table "m2m_dnrentity"
#

DROP TABLE IF EXISTS `m2m_dnrentity`;
CREATE TABLE `m2m_dnrentity` (
  `Handle` varchar(100) NOT NULL,
  `dnrentityHandle` varchar(100) NOT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "m2m_dnrentity"
#

/*!40000 ALTER TABLE `m2m_dnrentity` DISABLE KEYS */;
INSERT INTO `m2m_dnrentity` VALUES ('20120906s10001s53672068-cn','whois_private','dnrdomain'),('20030312s10001s00062053-cn','bu1048055833141','dnrdomain'),('20030310s10001s00013960-cn','cnorg-w6qgyr','dnrdomain'),('20030312s10001s00062053-cn','whois_private','dnrdomain'),('dnr-27','mmr-32107','nameserver');
/*!40000 ALTER TABLE `m2m_dnrentity` ENABLE KEYS */;

#
# Source for table "m2m_dsdata"
#

DROP TABLE IF EXISTS `m2m_dsdata`;
CREATE TABLE `m2m_dsdata` (
  `Handle` varchar(100) DEFAULT NULL,
  `DsDataID` varchar(100) DEFAULT NULL,
  `type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "m2m_dsdata"
#

INSERT INTO `m2m_dsdata` VALUES ('1','1','secureDNS');

#
# Source for table "m2m_events"
#

DROP TABLE IF EXISTS `m2m_events`;
CREATE TABLE `m2m_events` (
  `Handle` varchar(100) NOT NULL,
  `eventsId` varchar(100) NOT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "m2m_events"
#

INSERT INTO `m2m_events` VALUES ('AWC12-ARIN','1','rirentity'),('AS173','1','autunm'),('NET-1-0-0-0-1','2','ip'),('NET-1-0-0-0-1','1','ip'),('1','1','dsData');

#
# Source for table "m2m_keydata"
#

DROP TABLE IF EXISTS `m2m_keydata`;
CREATE TABLE `m2m_keydata` (
  `Handle` varchar(100) NOT NULL,
  `KeyDataID` varchar(100) NOT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "m2m_keydata"
#


#
# Source for table "m2m_link"
#

DROP TABLE IF EXISTS `m2m_link`;
CREATE TABLE `m2m_link` (
  `linkId` varchar(100) NOT NULL,
  `Handle` varchar(100) NOT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "m2m_link"
#

/*!40000 ALTER TABLE `m2m_link` DISABLE KEYS */;
INSERT INTO `m2m_link` VALUES ('APNIC-1-IP-LINK','NET-1-0-0-0-1','ip'),('APNIC-REG-LINK','APNIC','rirentity'),('APNIC-AWC12-ARIN-LINK','AWC12-ARIN','rirentity'),('SONIC-BLK-PARENT-LINK','NET-50-0-0-0-1','ip'),('SONIC-BLK-LINK','NET-50-0-0-0-1','ip'),('NET50-LINK','NET-50-0-0-0-0','ip'),('domain1','20120906s10001s53672068-cn','dnrdomain'),('domain2','20030312s10001s00062053-cn','dnrdomain'),('domain80','20030310s10001s00013960-cn','dnrdomain'),('entity1000','whois_private','dnrentity'),('AS173-LINK','AS173','autnum'),('noticelink1','1','notice'),('APNIC-2-IP-LINK','NET-1-0-0-0-1','ip'),('noticelink1','helpID','help');
/*!40000 ALTER TABLE `m2m_link` ENABLE KEYS */;

#
# Source for table "m2m_nameserver"
#

DROP TABLE IF EXISTS `m2m_nameserver`;
CREATE TABLE `m2m_nameserver` (
  `Handle` varchar(100) NOT NULL,
  `nameserverHandle` varchar(100) NOT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "m2m_nameserver"
#

/*!40000 ALTER TABLE `m2m_nameserver` DISABLE KEYS */;
INSERT INTO `m2m_nameserver` VALUES ('20120906s10001s53672068-cn','dnr-1','dnrdomain'),('20120906s10001s53672068-cn','dnr-2','dnrdomain'),('20030312s10001s00062053-cn','dnr-4','dnrdomain'),('20030312s10001s00062053-cn','dnr-3','dnrdomain'),('20030310s10001s00013960-cn','dnr-16','dnrdomain'),('20030310s10001s00013960-cn','dnr-17','dnrdomain'),('20030310s10001s00013960-cn','dnr-18','dnrdomain'),('20030310s10001s00013960-cn','dnr-21','dnrdomain'),('20030310s10001s00013960-cn','dnr-19','dnrdomain'),('20030310s10001s00013960-cn','dnr-20','dnrdomain');
/*!40000 ALTER TABLE `m2m_nameserver` ENABLE KEYS */;

#
# Source for table "m2m_notices"
#

DROP TABLE IF EXISTS `m2m_notices`;
CREATE TABLE `m2m_notices` (
  `Handle` varchar(100) DEFAULT NULL,
  `noticesId` varchar(100) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "m2m_notices"
#

INSERT INTO `m2m_notices` VALUES ('NET-1-0-0-0-1','1','ip'),('AS173','1','autnum'),('AWC12-ARIN','1','rirentity'),('AWC12-ARIN','2','rirentity'),('20030312s10001s00062053-cn','1','dnrdomain'),('helpID','helpID','help');

#
# Source for table "m2m_phones"
#

DROP TABLE IF EXISTS `m2m_phones`;
CREATE TABLE `m2m_phones` (
  `Handle` varchar(100) NOT NULL,
  `phoneId` varchar(100) NOT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "m2m_phones"
#

/*!40000 ALTER TABLE `m2m_phones` DISABLE KEYS */;
INSERT INTO `m2m_phones` VALUES ('AWC12-ARIN','APNIC-AWC12-ARIN-PHONE','rirentity'),('asd','APNIC-AWC12-ARIN-PHONE','registrar');
/*!40000 ALTER TABLE `m2m_phones` ENABLE KEYS */;

#
# Source for table "m2m_postaladress"
#

DROP TABLE IF EXISTS `m2m_postaladress`;
CREATE TABLE `m2m_postaladress` (
  `Handle` varchar(100) NOT NULL,
  `postalAddressId` varchar(100) NOT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "m2m_postaladress"
#

/*!40000 ALTER TABLE `m2m_postaladress` DISABLE KEYS */;
INSERT INTO `m2m_postaladress` VALUES ('AWC12-ARIN','APNIC-AWC12-ARIN-ADDR','rirentity'),('APNIC','APNIC-ADDR','rirentity');
/*!40000 ALTER TABLE `m2m_postaladress` ENABLE KEYS */;

#
# Source for table "m2m_publicids"
#

DROP TABLE IF EXISTS `m2m_publicids`;
CREATE TABLE `m2m_publicids` (
  `Handle` varchar(100) DEFAULT NULL,
  `identifier` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "m2m_publicids"
#

INSERT INTO `m2m_publicids` VALUES ('20120906s10001s53672068-cn','1234567890','dnrdomain'),('APNIC','1234567890','rirentity');

#
# Source for table "m2m_registrar"
#

DROP TABLE IF EXISTS `m2m_registrar`;
CREATE TABLE `m2m_registrar` (
  `Handle` varchar(100) NOT NULL,
  `registrarHandle` varchar(100) NOT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "m2m_registrar"
#

/*!40000 ALTER TABLE `m2m_registrar` DISABLE KEYS */;
/*!40000 ALTER TABLE `m2m_registrar` ENABLE KEYS */;

#
# Source for table "m2m_remarks"
#

DROP TABLE IF EXISTS `m2m_remarks`;
CREATE TABLE `m2m_remarks` (
  `Handle` varchar(100) NOT NULL,
  `remarksId` varchar(100) NOT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "m2m_remarks"
#

INSERT INTO `m2m_remarks` VALUES ('AWC12-ARIN','1','rirentity'),('AS173','1','autnum');

#
# Source for table "m2m_rirentity"
#

DROP TABLE IF EXISTS `m2m_rirentity`;
CREATE TABLE `m2m_rirentity` (
  `Handle` varchar(100) NOT NULL,
  `rirentityHandle` varchar(100) NOT NULL,
  `type` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "m2m_rirentity"
#

/*!40000 ALTER TABLE `m2m_rirentity` DISABLE KEYS */;
INSERT INTO `m2m_rirentity` VALUES ('NET-1-0-0-0-1','APNIC','ip'),('NET-1-0-0-0-1','AWC12-ARIN','ip'),('NET-50-0-0-0-1','SNIC','ip'),('NET-50-0-0-0-1','NETWO144-ARIN','ip'),('NET-50-0-0-0-1','ABUSE546-ARIN','ip'),('NET-50-0-0-0-0','ARIN','ip'),('NET-50-0-0-0-0','ARIN-HOSTMASTER','ip'),('NET-50-0-0-0-0','ARIN-HOSTMASTER','ip'),('NET-50-0-0-0-0','ARIN-HOSTMASTER','ip'),('ARP-1','APNIC','rirdomain'),('AS173','AWC12-ARIN','autnum');
/*!40000 ALTER TABLE `m2m_rirentity` ENABLE KEYS */;

#
# Source for table "m2m_securedns"
#

DROP TABLE IF EXISTS `m2m_securedns`;
CREATE TABLE `m2m_securedns` (
  `Handle` varchar(100) NOT NULL,
  `SecureDNSID` varchar(100) NOT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "m2m_securedns"
#

INSERT INTO `m2m_securedns` VALUES ('20120906s10001s53672068-cn','1','dnrdomain');

#
# Source for table "m2m_variants"
#

DROP TABLE IF EXISTS `m2m_variants`;
CREATE TABLE `m2m_variants` (
  `Handle` varchar(100) NOT NULL,
  `variantsId` varchar(100) NOT NULL,
  `type` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "m2m_variants"
#

/*!40000 ALTER TABLE `m2m_variants` DISABLE KEYS */;
INSERT INTO `m2m_variants` VALUES ('unicode1','1',NULL),('20120614s10001s59901629-cn','2',''),('20120614s10001s59901679-cn','3',NULL);
/*!40000 ALTER TABLE `m2m_variants` ENABLE KEYS */;

#
# Source for table "nameserver"
#

DROP TABLE IF EXISTS `nameserver`;
CREATE TABLE `nameserver` (
  `Handle` varchar(100) NOT NULL,
  `Ldh_Name` varchar(100) DEFAULT NULL,
  `IPV4_Addresses` varchar(100) DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `Port43` varchar(40) DEFAULT NULL,
  `Lang` varchar(10) DEFAULT NULL,
  `Unicode_Name` varchar(100) DEFAULT NULL,
  `IPV6_Addresses` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Handle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "nameserver"
#

/*!40000 ALTER TABLE `nameserver` DISABLE KEYS */;
INSERT INTO `nameserver` VALUES ('dnr-1','ns1.ee28.cn',NULL,'','',' ',' ','2adc::0001'),('dnr-10','ns2.sina.com.cn','1.0.0.0,1.0.0.1,1.0.0.2,1.0.0.3,10.0.0.7,1.0.0.255,1.0.0.244,1.0.0.166','','',' ',' ',' '),('dnr-11','a.cnnic.cn',NULL,'','',' ',' ',' '),('dnr-111','a.c.cn','1.0.0.0,1.0.0.1,1.0.0.2,1.0.0.3,10.0.0.7,1.0.0.255,1.0.0.244,1.0.0.166','ok','whois.cnnic.cn','11','名字','2001:6a8:0:1:210:4bff:fe4b:4c61,2002:6a8:0:1:210:4bff:fe4b:4c61,\r\n2001:5a8:0:1:210:4bff:fe4b:4c61,20'),('dnr-12','b.cnnic.cn',NULL,'','',' ','ns1.fóo.example',' '),('dnr-13','c.cnnic.cn',NULL,'','',' ',' ',' '),('dnr-14','d.cnnic.cn',NULL,'','',' ',' ',' '),('dnr-15','e.cnnic.cn',NULL,'','',' ',' ',' '),('dnr-16','pdns1.ultradns.net',NULL,'','',' ',' ',' '),('dnr-17','pdns2.ultradns.net',NULL,'','',' ',' ',' '),('dnr-18','pdns3.ultradns.org',NULL,'','',' ',' ',' '),('dnr-19','pdns4.ultradns.org',NULL,'','',' ',' ',' '),('dnr-2','ns2.eé28.cn',NULL,'','',' ',' ns2.eé28.cn',' '),('dnr-20','pdns5.ultradns.info',NULL,'','',' ',' ',' '),('dnr-21','pdns6.ultradns.co.uk',NULL,'','',' ',' ',' '),('dnr-22','dns1.hichina.com',NULL,'','',' ',' ',' '),('dnr-23','dns2.hichina.com',NULL,'','',' ',' ',' '),('dnr-24','ns.hlhrptt.net.cn',NULL,'','',' ',' ',' '),('dnr-25','ns1.hlhrptt.net.cn',NULL,'','',' ',' ',' '),('dnr-26','ns2.google.com',NULL,'','',' ',' ',' '),('dnr-27','        ',NULL,'','',' ','ns2.eé28ééééé.cn',' '),('dnr-28','ns3.google.com',NULL,'','',' ','중국 인터넷 네트워크 정보 센터',' '),('dnr-29','ns4.google.com',NULL,' ',' ',' ','中国インターネットネットワーク情報センター',' '),('dnr-3','proxy.baidu.com',NULL,'','',' ',' ',' '),('dnr-30','d.cnnic.cn',NULL,' ',' ',' ','مركز معلومات شبكة الإنترنت الصين',' '),('dnr-4','dns.baidu.com',NULL,'','',' ',' ',' '),('dnr-5','ns1.newfavor.net',NULL,'','',' ',' ',' '),('dnr-6','ns2.newfavor.net',NULL,'','',' ',' ',' '),('dnr-7','dns.sohu-inc.com',NULL,'','',' ',' ',' '),('dnr-8','dns1.sohu-inc.com',NULL,'','',' ',' ',' '),('dnr-9','ns3.sina.com.cn',NULL,'','',' ',' ',' '),('rir-1','ns1.rir.net',NULL,'','',' ',' ',' '),('rir-2','ns2.rir.net',NULL,'','',' ',' ',' ');
/*!40000 ALTER TABLE `nameserver` ENABLE KEYS */;

#
# Source for table "notices"
#

DROP TABLE IF EXISTS `notices`;
CREATE TABLE `notices` (
  `Title` varchar(100) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `noticesId` varchar(100) NOT NULL,
  PRIMARY KEY (`noticesId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "notices"
#

/*!40000 ALTER TABLE `notices` DISABLE KEYS */;
INSERT INTO `notices` VALUES ('Terms of Use','Service subject to The Registry of the Moon\'s TOS.\'~\'Copyright (c) 2020 LunarNIC','1'),('test','test','2'),('help notice','test help','helpID');
/*!40000 ALTER TABLE `notices` ENABLE KEYS */;

#
# Source for table "oauth_accessor"
#

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
) ENGINE=InnoDB AUTO_INCREMENT=254 DEFAULT CHARSET=utf8;

#
# Data for table "oauth_accessor"
#

INSERT INTO `oauth_accessor` VALUES (253,'46765108209fbe9e51bb6140047bd101','b5ab812f1386994add7596b18fca3111','4043d07af83eeb5b844460f89f152a8f','key1385973838215','secret1385973838215','2014-02-26 08:56:27','root');

#
# Source for table "oauth_users"
#

DROP TABLE IF EXISTS `oauth_users`;
CREATE TABLE `oauth_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `pwd` varchar(80) NOT NULL,
  `user_role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "oauth_users"
#

INSERT INTO `oauth_users` VALUES (1,'auth','auth','authenticated'),(2,'root','root','root'),(3,'admin','admin','anonymous');

#
# Source for table "oauth_users_app"
#

DROP TABLE IF EXISTS `oauth_users_app`;
CREATE TABLE `oauth_users_app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `app_key` varchar(40) NOT NULL,
  `app_secret` varchar(80) NOT NULL,
  `app_description` varchar(200) NOT NULL,
  `user_id` int(10) DEFAULT NULL,
  `invalid_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Data for table "oauth_users_app"
#

INSERT INTO `oauth_users_app` VALUES (3,'key-498702880','secret-858606152','范德萨',1,1),(4,'key-498702880f','secret-858606152f','fff',2,7),(7,'key1385973838215','secret1385973838215','fff',1,7),(8,'key1386742777776','secret1386742777776','其它',2,7),(9,'key1386899431580','secret1386899431580','其它',6,7);

#
# Source for table "permissions"
#

DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `tableName` varchar(20) NOT NULL DEFAULT '',
  `columnName` varchar(50) NOT NULL DEFAULT '',
  `anonymous` varchar(10) DEFAULT '1',
  `authenticated` varchar(10) DEFAULT '1',
  `root` varchar(10) DEFAULT '1',
  `columnLength` varchar(10) DEFAULT '255',
  PRIMARY KEY (`tableName`,`columnName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "permissions"
#

/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES ('autnum','$array$Description','1','1','1','255'),('autnum','$array$Status','1','1','1','255'),('autnum','$join$entities','1','1','1','255'),('autnum','$join$events','1','1','1','255'),('autnum','$join$links','1','1','1','255'),('autnum','$join$notices','1','1','1','255'),('autnum','$join$remarks','1','1','1','255'),('autnum','$x$xnic_auttest','1','1','1','100'),('autnum','Country','1','1','1','255'),('autnum','End_Autnum','1','1','1','255'),('autnum','Handle','1','1','1','255'),('autnum','Lang','1','1','1','255'),('autnum','Name','1','1','1','255'),('autnum','Start_Autnum','1','1','1','255'),('autnum','Type','1','1','1','255'),('dnrdomain','$array$Status','1','1','1','255'),('dnrdomain','$join$entities','1','1','1','255'),('dnrdomain','$join$events','1','1','1','255'),('dnrdomain','$join$links','1','1','1','255'),('dnrdomain','$join$nameServer','1','1','1','255'),('dnrdomain','$join$notices','1','1','1','255'),('dnrdomain','$join$publicIds','1','1','1','255'),('dnrdomain','$join$registrar','1','1','1','255'),('dnrdomain','$join$secureDNS','1','1','1','255'),('dnrdomain','$join$variants','1','1','1','255'),('dnrdomain','Handle','1','1','1','255'),('dnrdomain','Lang','1','1','1','255'),('dnrdomain','Ldh_Name','1','1','1','255'),('dnrdomain','Port43','1','1','1','255'),('dnrdomain','Unicode_Name','1','1','1','255'),('dnrentity','$array$Emails','1','1','1','255'),('dnrentity','$array$Entity_Names','1','1','1','255'),('dnrentity','$array$Roles','1','1','1','255'),('dnrentity','$array$Status','1','1','1','255'),('dnrentity','$join$events','1','1','1','255'),('dnrentity','$join$links','1','1','1','255'),('dnrentity','$join$notices','1','1','1','255'),('dnrentity','$join$phones','1','1','1','255'),('dnrentity','$join$postalAddress','1','1','1','255'),('dnrentity','$join$publicIds','1','1','1','255'),('dnrentity','$join$registrar','1','1','1','255'),('dnrentity','$join$remarks','1','1','1','255'),('dnrentity','Anniversary','1','1','1','255'),('dnrentity','Bday','1','1','1','255'),('dnrentity','Gender','1','1','1','255'),('dnrentity','Geo','1','1','1','255'),('dnrentity','Handle','1','1','1','255'),('dnrentity','Key','1','1','1','255'),('dnrentity','Kind','1','1','1','255'),('dnrentity','Lang','1','1','1','255'),('dnrentity','Language_Tag_1','1','1','1','255'),('dnrentity','Language_Tag_2','1','1','1','255'),('dnrentity','Org','1','1','1','255'),('dnrentity','Port43','1','1','1','255'),('dnrentity','Pref1','1','1','1','255'),('dnrentity','Pref2','1','1','1','255'),('dnrentity','Role','1','1','1','255'),('dnrentity','Title','1','1','1','255'),('dnrentity','Tz','1','1','1','255'),('dnrentity','Url','1','1','1','255'),('dsData','$join$events','1','1','1','255'),('dsData','Algorithm','1','1','1','255'),('dsData','Digest','1','1','1','255'),('dsData','Digest_Type','1','1','1','255'),('dsData','DsDataID','1','1','1','255'),('dsData','Key_Tag','1','1','1','255'),('errormessage','$array$Description','1','1','1','255'),('errormessage','$join$notices','1','1','1','255'),('errormessage','Error_Code','1','1','1','255'),('errormessage','Lang','1','1','1','255'),('errormessage','Title','1','1','1','255'),('events','eventsId','1','1','1','255'),('events','Event_Action','1','1','1','255'),('events','Event_Actor','1','1','1','255'),('events','Event_Date','1','1','1','255'),('help','$join$notices','1','1','1','255'),('ip','$array$Description','1','1','1','500'),('ip','$array$Status','1','1','1','100'),('ip','$join$entities','1','1','1','123'),('ip','$join$events','1','1','1','255'),('ip','$join$links','1','1','1','123'),('ip','$join$notices','1','1','1','255'),('ip','$join$remarks','1','1','1','500'),('ip','$x$testip','1','1','1','50'),('ip','Country','1','1','1','10'),('ip','EndHighAddress','1','1','1','255'),('ip','EndLowAddress','1','1','1','40'),('ip','Handle','1','1','1','100'),('ip','IP_Version','1','1','1','2'),('ip','Lang','0','1','1','123'),('ip','Name','1','1','1','100'),('ip','Parent_Handle','1','1','1','100'),('ip','StartHighAddress','1','1','1','255'),('ip','StartLowAddress','1','1','1','40'),('ip','Type','1','1','1','100'),('keyData','Algorithm','1','1','1','255'),('keyData','Flags','1','1','1','255'),('keyData','KeyDataID','1','1','1','255'),('keyData','Protocol','1','1','1','255'),('keyData','Public_Key','1','1','1','255'),('links','$array$hreflang','1','1','1','255'),('links','$array$title','1','1','1','255'),('links','Href','1','1','1','255'),('links','linkId','1','1','1','255'),('links','media','1','1','1','255'),('links','Rel','1','1','1','255'),('links','type','1','1','1','255'),('links','Value','1','1','1','255'),('nameserver','$array$IPV4_Addresses','1','1','1','255'),('nameserver','$array$IPV6_Addresses','1','1','1','255'),('nameserver','$array$Status','1','1','1','255'),('nameserver','$join$entities','1','1','1','255'),('nameserver','$join$events','1','1','1','255'),('nameserver','$join$links','1','1','1','255'),('nameserver','$join$notices','1','1','1','255'),('nameserver','$join$registrar','1','1','1','255'),('nameserver','$join$remarks','1','1','1','255'),('nameserver','Handle','1','1','1','255'),('nameserver','Lang','1','1','1','255'),('nameserver','Ldh_Name','1','1','1','255'),('nameserver','Unicode_Name','1','1','1','255'),('notices','$array$Description','1','1','1','255'),('notices','$join$links','1','1','1','255'),('notices','noticesId','1','1','1','255'),('notices','Title','1','1','1','255'),('phones','$array$Fax','1','1','1','255'),('phones','$array$Mobile','1','1','1','255'),('phones','$array$Office','1','1','1','255'),('phones','phonesId','1','1','1','255'),('postalAddress','City','1','1','1','255'),('postalAddress','Country','1','1','1','255'),('postalAddress','postalAddressId','1','1','1','255'),('postalAddress','Postal_Code','1','1','1','255'),('postalAddress','SP','1','1','1','255'),('postalAddress','Street','1','1','1','255'),('postalAddress','Street1','1','1','1','255'),('postalAddress','Street2','1','1','1','255'),('publicids','identifier','1','1','1','255'),('publicids','type','1','1','1','255'),('registrar','$array$Emails','1','1','1','255'),('registrar','$array$Entity_Names','1','1','1','255'),('registrar','$array$Status','1','1','1','255'),('registrar','$join$events','1','1','1','255'),('registrar','$join$links','1','1','1','255'),('registrar','$join$phones','1','1','1','255'),('registrar','$join$postalAddress','1','1','1','255'),('registrar','$join$remarks','1','1','1','255'),('registrar','Handle','1','1','1','255'),('registrar','Port43','1','1','1','255'),('registrar','Roles','1','1','1','255'),('remarks','$array$Description','1','1','1','255'),('remarks','remarksId','1','1','1','255'),('remarks','Title','1','1','1','255'),('rirdomain','$join$entities','1','1','1','255'),('rirdomain','$join$events','1','1','1','255'),('rirdomain','$join$links','1','1','1','255'),('rirdomain','$join$nameServer','1','1','1','255'),('rirdomain','$join$notices','1','1','1','255'),('rirdomain','$join$publicIds','1','1','1','255'),('rirdomain','$join$remarks','1','1','1','255'),('rirdomain','$join$secureDNS','1','1','1','255'),('rirdomain','Handle','1','1','1','255'),('rirdomain','Lang','1','1','1','255'),('rirdomain','Ldh_Name','1','1','1','255'),('rirentity','$array$Emails','1','1','1','255'),('rirentity','$array$Entity_Names','1','1','1','255'),('rirentity','$array$Roles','1','1','1','255'),('rirentity','$join$events','1','1','1','255'),('rirentity','$join$links','1','1','1','255'),('rirentity','$join$notices','1','1','1','255'),('rirentity','$join$phones','1','1','1','255'),('rirentity','$join$postalAddress','1','1','1','255'),('rirentity','$join$publicIds','1','1','1','255'),('rirentity','$join$remarks','1','1','1','255'),('rirentity','Anniversary','1','1','1','255'),('rirentity','Bday','1','1','1','255'),('rirentity','Gender','1','1','1','255'),('rirentity','Geo','1','1','1','255'),('rirentity','Handle','1','1','1','255'),('rirentity','Key','1','1','1','255'),('rirentity','Kind','1','1','1','255'),('rirentity','Lang','1','1','1','255'),('rirentity','Language_Tag_1','1','1','1','255'),('rirentity','Language_Tag_2','1','1','1','255'),('rirentity','Org','1','1','1','255'),('rirentity','Pref1','1','1','1','255'),('rirentity','Pref2','1','1','1','255'),('rirentity','Role','1','1','1','255'),('rirentity','Title','1','1','1','255'),('rirentity','Tz','1','1','1','255'),('rirentity','Url','1','1','1','255'),('secureDNS','$join$dsData','1','1','1','255'),('secureDNS','$join$keyData','1','1','1','255'),('secureDNS','Delegation_Signed','1','1','1','255'),('secureDNS','Max_SigLife','1','1','1','255'),('secureDNS','SecureDNSID','1','1','1','255'),('secureDNS','Zone_Signed','1','1','1','255'),('variants','$array$Relation','1','1','1','255'),('variants','$array$Variant_Names','1','1','1','255'),('variants','IDNTable','1','1','1','255'),('variants','variantsId','1','1','1','11');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;

#
# Source for table "phones"
#

DROP TABLE IF EXISTS `phones`;
CREATE TABLE `phones` (
  `Office` varchar(100) DEFAULT NULL,
  `Fax` varchar(100) DEFAULT NULL,
  `Mobile` varchar(100) DEFAULT NULL,
  `phonesId` varchar(100) NOT NULL,
  PRIMARY KEY (`phonesId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "phones"
#

/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` VALUES ('+61 7 3858 3188','','','APNIC-AWC12-ARIN-PHONE'),('+1-703-227-0660','','','ARIN-HOSTMASTER-PHONE'),('+1-732-576-3052','','','CMU8-ARIN-PHONE'),('+1-203-373-2962','','','GET2-ORG-ARIN-PHONE'),('+1-310-823-9358','','','IANA-ARIN-PHONE'),('+1-310-301-5820','','','IANA-IP-ARIN-PHONE'),('+1-877-453-8353','','','IPADD5-ARIN-PHONE'),('+1-617-444-2535','','','MHA379-ARIN-PHONE'),('1-707-522-1000','','','NETWO144-ARIN-PHONE'),('+1-617-938-3130','','','NF81-ARIN-PHONE'),('+1-800-426-7378','','','RAIN-ARIN-PHONE'),('+1-800-365-3642','','','REGIS10-ARIN-PHONE'),('+31 20 535 4444','','','RNO29-ARIN-PHONE');
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;

#
# Source for table "postaladdress"
#

DROP TABLE IF EXISTS `postaladdress`;
CREATE TABLE `postaladdress` (
  `Street` varchar(100) DEFAULT NULL,
  `Street1` varchar(100) DEFAULT NULL,
  `Street2` varchar(100) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `SP` varchar(20) DEFAULT NULL,
  `Postal_Code` varchar(10) DEFAULT NULL,
  `postalAddressId` varchar(100) NOT NULL,
  `Country` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`postalAddressId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "postaladdress"
#

/*!40000 ALTER TABLE `postaladdress` DISABLE KEYS */;
INSERT INTO `postaladdress` VALUES ('8 Cambridge Center','','','Cambridge','MA','02142','AKAMAI-ADDR','US'),('12976 HOLLENBERG','','','BRIDGETON','MO','63044','ALO-3-ADDR','US'),('PO Box 3646','','','South Brisbane','QLD','4101','APNIC-ADDR','AU'),('Level 1 - 33 Park Road Milton 4064','','','Brisbane','','','APNIC-AWC12-ARIN-ADDR','AU'),('3635 Concorde Parkway ','Suite 200','','Chantilly','VA','20151','ARIN-ADDR','US'),('307 Middletown Road','','','Lincroft','NJ','07738','CMU8-ARIN-ADDR','US'),('Internet Registrations','3135 Easton Turnpike','','Fairfield','CT','06828-0001','GENERA-9-ADDR','US'),('NETC-ANC CONUS TNOSC','','','Fort Huachuca','AZ','85613','HEADQU-3-ADDR','US'),('12025 Waterfront Drive Suite 300','','','Los Angeles','CA','90292','IANA-ADDR','US'),('3039 Cornwallis Road','','','Research Triangle Park','NC','27709-2195','IBM-1-ADDR','US'),('1025 Eldorado Blvd.','','','Broomfield','CO','80021','LVLT-ADDR','US'),('8 Cambridge Center','Mailstop 926-G','','Cambridge','MA','02142','MHA379-ARIN-ADDR','US'),('3990 E. Broad Street','','','Columbus','OH','43218','REGIS10-ARIN-ADDR','US'),('P.O. Box 10096','','','Amsterdam','zxh','1001EB','RIPE-ADDR','NL'),('2260 Apollo Way','','','Santa Rosa','CA','95407','SNIC-ADDR','US');
/*!40000 ALTER TABLE `postaladdress` ENABLE KEYS */;

#
# Source for table "publicids"
#

DROP TABLE IF EXISTS `publicids`;
CREATE TABLE `publicids` (
  `identifier` varchar(256) NOT NULL,
  `type` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`identifier`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "publicids"
#

INSERT INTO `publicids` VALUES ('1234567890','ENS_Auth ID');

#
# Source for table "registrar"
#

DROP TABLE IF EXISTS `registrar`;
CREATE TABLE `registrar` (
  `Handle` varchar(100) NOT NULL,
  `Entity_Names` varchar(100) DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `Emails` varchar(100) DEFAULT NULL,
  `Port43` varchar(40) DEFAULT NULL,
  `Roles` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Handle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "registrar"
#

/*!40000 ALTER TABLE `registrar` DISABLE KEYS */;
INSERT INTO `registrar` VALUES ('whois_private','WHOIS PRIVACY',' ','whois.private.service@gmail.com','whois.cnnic.cn','registrant');
/*!40000 ALTER TABLE `registrar` ENABLE KEYS */;

#
# Source for table "remarks"
#

DROP TABLE IF EXISTS `remarks`;
CREATE TABLE `remarks` (
  `Title` varchar(100) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `remarksId` varchar(100) NOT NULL,
  PRIMARY KEY (`remarksId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "remarks"
#

/*!40000 ALTER TABLE `remarks` DISABLE KEYS */;
INSERT INTO `remarks` VALUES ('','She sells sea shells down by the sea shore.\'~\'Originally written by Terry Sullivan.','1');
/*!40000 ALTER TABLE `remarks` ENABLE KEYS */;

#
# Source for table "rirdomain"
#

DROP TABLE IF EXISTS `rirdomain`;
CREATE TABLE `rirdomain` (
  `Handle` varchar(100) NOT NULL,
  `Ldh_Name` varchar(25) DEFAULT NULL,
  `Lang` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Handle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "rirdomain"
#

/*!40000 ALTER TABLE `rirdomain` DISABLE KEYS */;
INSERT INTO `rirdomain` VALUES ('ARP-1','192.in-addr.arpa',' ');
/*!40000 ALTER TABLE `rirdomain` ENABLE KEYS */;

#
# Source for table "rirentity"
#

DROP TABLE IF EXISTS `rirentity`;
CREATE TABLE `rirentity` (
  `Handle` varchar(100) NOT NULL,
  `Entity_Names` varchar(100) DEFAULT NULL,
  `Roles` varchar(100) DEFAULT NULL,
  `Emails` varchar(100) DEFAULT NULL,
  `Lang` varchar(10) DEFAULT NULL,
  `Bday` varchar(100) DEFAULT NULL,
  `Anniversary` varchar(100) DEFAULT NULL,
  `Gender` varchar(100) DEFAULT NULL,
  `Kind` varchar(100) DEFAULT NULL,
  `Language_Tag_1` varchar(100) DEFAULT NULL,
  `Pref1` varchar(10) DEFAULT NULL,
  `Language_Tag_2` varchar(100) DEFAULT NULL,
  `Pref2` varchar(10) DEFAULT NULL,
  `Org` varchar(100) DEFAULT NULL,
  `Title` varchar(100) DEFAULT NULL,
  `Role` varchar(100) DEFAULT NULL,
  `Geo` varchar(100) DEFAULT NULL,
  `Key` varchar(256) DEFAULT NULL,
  `Tz` varchar(100) DEFAULT NULL,
  `Url` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`Handle`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "rirentity"
#

/*!40000 ALTER TABLE `rirentity` DISABLE KEYS */;
INSERT INTO `rirentity` VALUES ('ABUSE546-ARIN','Abuse Department\'~\' Sonic.net, Inc.','abuse','abuse@sonic.net',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('AKAMAI','Akamai Technologies','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('ALO-3','ATT LINCROFT ORT','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('APL7-ARIN','ADMIN POC LVLT\'~\'Level 3 Communications - IP Addressing','administrative','rshibata@level3.net ipaddressing@level3.com',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('APL8-ARIN','Abuse POC LVLT\'~\'Level 3 Communications - Abuse','abuse','abuse@level3.com security@level3.com','  ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('APNIC','Asia Pacific Network Information Centre (APNIC)','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('ARIN','American Registry for Internet Numbers','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('ARIN-HOSTMASTER','Registration Services Department\'~\'American Registry for Internet Numbers','technical\'~\'administrative\'~\'abuse','hostmaster@arin.net',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('AWC12-ARIN','Asia Pacific Network Information Centre','technical\'~\'administrative\'~\'abuse','search-apnic-not-arin@apnic.net',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('CMU8-ARIN','Muhlhausen ','technical\'~\'administrative\'~\'abuse','ledzep@att.com',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('DNIC','DoD Network Information Center','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('GENERA-9','General Electric Company','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('GET2-ORG-ARIN','General Electric Company','technical\'~\'administrative\'~\'abuse','nic.admin@ge.com',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('HEADQU-3','Headquarters','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('IANA','Internet Assigned Numbers Authority','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('IANA-ARIN','Internet Corporation for Assigned Names and Number','administrative','res-ip@iana.org',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('IANA-IP-ARIN','Internet Corporation for Assigned Names and Number','technical\'~\'abuse','abuse@iana.org',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('IBM-1','IBM','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('IPADD5-ARIN','ipaddressing\'~\'Level 3 Communications','technical','ipaddressing@level3.com',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('LVLT','Level 3 Communications','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('MHA379-ARIN','Hannigan ','technical\'~\'abuse','ip-admin@akamai.com',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('NETWO144-ARIN','Network Operations\'~\' Sonic.net','technical','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('NF81-ARIN','Freedman ','administrative','abuse@akamai.com',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('RAIN-ARIN','Registrar Authority','technical\'~\'administrative\'~\'abuse','ipreg@us.ibm.com',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('REGIS10-ARIN','Registration\'~\'DoD Network Information Center','technical\'~\'administrative\'~\'abuse','registra@nic.mil',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('RIPE','RIPE Network Coordination Centre','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('RNO29-ARIN','RIPE NCC Operations\'~\'RIPE Network Coordination Centre','technical\'~\'administrative\'~\'abuse','hostmaster@ripe.net',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org'),('SNIC','SONIC.NET','registrant','',' ','1983-10-23T00:00:00Z','1983-10-23T00:00:00Z','M','individual','en','1','fr','2','example inc.','employee','testrole','geo:46.772673,-71.282945','http://www.example.com/joe.user/joe.asc','-05:00','http://example.org');
/*!40000 ALTER TABLE `rirentity` ENABLE KEYS */;

#
# Source for table "securedns"
#

DROP TABLE IF EXISTS `securedns`;
CREATE TABLE `securedns` (
  `Zone_Signed` tinyint(4) DEFAULT NULL,
  `Delegation_Signed` tinyint(4) DEFAULT NULL,
  `Max_SigLife` int(11) DEFAULT NULL,
  `secureDNSID` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`secureDNSID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "securedns"
#

INSERT INTO `securedns` VALUES (0,1,1,'1');

#
# Source for table "users_app"
#

DROP TABLE IF EXISTS `users_app`;
CREATE TABLE `users_app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `app_key` varchar(40) NOT NULL,
  `app_secret` varchar(80) NOT NULL,
  `app_description` varchar(200) NOT NULL,
  `user_id` int(10) DEFAULT NULL,
  `invalid_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "users_app"
#

INSERT INTO `users_app` VALUES (3,'key-498702880','secret-858606152','范德萨',1,7),(4,'key-498702880f','secret-858606152f','fff',2,7),(7,'key1385973838215','secret1385973838215','fff',1,7),(8,'key1386742777776','secret1386742777776','其它',2,7);

#
# Source for table "variants"
#

DROP TABLE IF EXISTS `variants`;
CREATE TABLE `variants` (
  `Relation` varchar(100) DEFAULT NULL,
  `Variant_Names` varchar(100) DEFAULT NULL,
  `variantsId` varchar(100) NOT NULL,
  `IDNTable` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`variantsId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "variants"
#

/*!40000 ALTER TABLE `variants` DISABLE KEYS */;
INSERT INTO `variants` VALUES ('registered','variantname','1','idntable_zh'),('registereddddd','野有餓莩','2',NULL),('registereddddd','variant_name','3',NULL);
/*!40000 ALTER TABLE `variants` ENABLE KEYS */;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
