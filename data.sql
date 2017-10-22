
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mall` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `mall`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `admin_id` varchar(50) NOT NULL,
  `name` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `isfreeze` tinyint(1) default NULL,
  PRIMARY KEY  (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;



/*Table structure for table `t_comments` */

DROP TABLE IF EXISTS `t_comments`;

CREATE TABLE `t_comments` (
  `comments_id` varchar(50) NOT NULL,
  `comment` varchar(200) default NULL,
  `goods_id` varchar(50) default NULL,
  `createtime` varchar(50) default NULL,
  `user_id` varchar(50) default NULL,
  PRIMARY KEY  (`comments_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;


/*Table structure for table `t_goods` */

DROP TABLE IF EXISTS `t_goods`;

CREATE TABLE `t_goods` (
  `goods_id` varchar(50) NOT NULL,
  `name` varchar(50) default NULL,
  `price` double default NULL,
  `img` varchar(50) default NULL,
  `desc` varchar(100) default NULL,
  `type_id` varchar(50) default NULL,
  `state` tinyint(1) default NULL,
  `createtime` varchar(50) default NULL,
  PRIMARY KEY  (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `order_id` varchar(50) NOT NULL,
  `consignee` varchar(50) default NULL,
  `total` double default NULL,
  `payment` varchar(50) default NULL,
  `state` tinyint(1) default NULL,
  `time` varchar(50) default NULL,
  `user_id` varchar(50) default NULL,
  `address` varchar(200) default NULL,
  `phone` varchar(50) default NULL,
  `postcard` varchar(20) default NULL,
  `delivery` varchar(50) default NULL,
  `freight` varchar(50) default NULL,
  `ispay` tinyint(1) default NULL,
  PRIMARY KEY  (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;


/*Table structure for table `t_order_goods` */

DROP TABLE IF EXISTS `t_order_goods`;

CREATE TABLE `t_order_goods` (
  `order_id` varchar(50) NOT NULL,
  `goods_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Table structure for table `t_type` */

DROP TABLE IF EXISTS `t_type`;

CREATE TABLE `t_type` (
  `type_id` varchar(50) NOT NULL,
  `name` varchar(50) default NULL,
  `fid` varchar(50) default NULL,
  `state` tinyint(1) default NULL,
  PRIMARY KEY  (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;


/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` varchar(50) NOT NULL,
  `name` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `sex` tinyint(1) default NULL,
  `address` varchar(50) default NULL,
  `phone` varchar(50) default NULL,
  `email` varchar(50) default NULL,
  `isfreeze` tinyint(1) default NULL,
  `time` varchar(50) default NULL,
  `balance` double default NULL,
  `state` tinyint(1) default NULL,
  `zip` varchar(50) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

