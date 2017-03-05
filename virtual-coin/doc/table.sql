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

/*Table structure for table `t_entrust_info` */

DROP TABLE IF EXISTS `t_entrust_info`;

CREATE TABLE `t_entrust_info` (
  `entrust_id` int(11) NOT NULL AUTO_INCREMENT,
  `entrust_money_id` int(11) DEFAULT NULL,
  `entrust_type` int(2) DEFAULT NULL,
  `entrust_unit_money` double DEFAULT NULL,
  `entrust_num` int(11) DEFAULT NULL,
  `entrust_money` double DEFAULT NULL,
  `entrust_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `entrust_comp_money` double DEFAULT NULL,
  `entrust_sts` int(2) DEFAULT NULL,
  `entrust_last_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `entrust_desc` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`entrust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_entrust_info` */

/*Table structure for table `t_trans_info` */

DROP TABLE IF EXISTS `t_trans_info`;

CREATE TABLE `t_trans_info` (
  `trans_id` int(11) NOT NULL AUTO_INCREMENT,
  `trans_type` int(2) DEFAULT NULL,
  `trans_money_id` int(11) DEFAULT NULL,
  `trans_unit_money` double DEFAULT NULL,
  `trans_entrust_id` int(11) DEFAULT NULL,
  `trans_num` int(11) DEFAULT NULL,
  `trans_money` double DEFAULT NULL,
  `trans_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `trans_sts` int(2) DEFAULT NULL,
  `trans_comp_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `trans_desc` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `trans_buy_user` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `trans_sell_user` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `trans_buy_hand_money` double DEFAULT NULL,
  `trans_sell_hand_money` double DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_trans_info` */

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_user` */

insert  into `t_user`(`user_id`,`user_name`,`user_type`,`user_lvl`,`user_phone`,`user_login_id`,`user_login_pwd`,`user_trans_pwd`,`user_card`,`user_sts`,`user_fid`,`user_create_time`,`user_modify_time`) values (1,'林',1,1,'18588886666','admin','123456',NULL,NULL,1,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
