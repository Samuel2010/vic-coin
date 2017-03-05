/*
SQLyog v10.2 
MySQL - 5.5.27 : Database - virtcoin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`virtcoin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `virtcoin`;

/*Table structure for table `t_account` */

DROP TABLE IF EXISTS `t_account`;

CREATE TABLE `t_account` (
  `acct_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `acct_money` double DEFAULT NULL,
  `block_money` double DEFAULT NULL,
  `acct_sts` int(2) DEFAULT NULL,
  `acct_create_time` timestamp NULL DEFAULT NULL,
  `acct_modify_time` timestamp NULL DEFAULT NULL,
  KEY `acct_id` (`acct_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_account` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  `user_type` int(2) DEFAULT NULL,
  `user_lvl` int(2) DEFAULT NULL,
  `user_phone` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `user_login_id` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `user_login_pwd` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `user_trans_pwd` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `user_card` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `user_sts` int(2) DEFAULT NULL,
  `user_fid` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `user_create_time` timestamp NULL DEFAULT NULL,
  `user_modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
