/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-04-07 21:44:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for msg_notify
-- ----------------------------
DROP TABLE IF EXISTS `msg_notify`;
CREATE TABLE `msg_notify` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `content` varchar(1000) DEFAULT NULL COMMENT '消息内容',
  `type` int(11) DEFAULT NULL COMMENT '消息的类型，1: 公告 Announce，2: 提醒 Remind，3：信息 Message',
  `target` bigint(11) DEFAULT NULL,
  `target_type` varchar(100) DEFAULT NULL,
  `action` varchar(100) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for msg_save_remind
-- ----------------------------
DROP TABLE IF EXISTS `msg_save_remind`;
CREATE TABLE `msg_save_remind` (
  `id` bigint(20) NOT NULL,
  `target` bigint(20) DEFAULT NULL,
  `target_type` varchar(100) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for msg_subscription
-- ----------------------------
DROP TABLE IF EXISTS `msg_subscription`;
CREATE TABLE `msg_subscription` (
  `id` bigint(20) NOT NULL,
  `target` bigint(20) DEFAULT NULL,
  `target_type` varchar(100) DEFAULT NULL,
  `action` varchar(100) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for msg_subscription_config
-- ----------------------------
DROP TABLE IF EXISTS `msg_subscription_config`;
CREATE TABLE `msg_subscription_config` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `action` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for msg_user_notify
-- ----------------------------
DROP TABLE IF EXISTS `msg_user_notify`;
CREATE TABLE `msg_user_notify` (
  `id` bigint(20) NOT NULL,
  `is_read` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `notify_id` bigint(20) DEFAULT NULL,
  `notify_type` int(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `notify_time` datetime DEFAULT NULL COMMENT '公告创建时间',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
