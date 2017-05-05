/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-04-01 15:05:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for binloguser
-- ----------------------------
DROP TABLE IF EXISTS `binloguser`;
CREATE TABLE `binloguser` (
  `id` int(11) NOT NULL,
  `firstname` char(40) DEFAULT NULL,
  `middlename` varchar(40) DEFAULT NULL,
  `lastname` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of binloguser
-- ----------------------------
INSERT INTO `binloguser` VALUES ('1', 'hg_222d', 'm3', 'tangss');
INSERT INTO `binloguser` VALUES ('3', 'f3', 'm3', 'wang');

-- ----------------------------
-- Table structure for catptcha
-- ----------------------------
DROP TABLE IF EXISTS `catptcha`;
CREATE TABLE `catptcha` (
  `id` bigint(20) NOT NULL COMMENT '序号',
  `pic` varchar(255) DEFAULT '' COMMENT '图片',
  `value` varchar(255) DEFAULT '' COMMENT '验证码值',
  `type` varchar(255) DEFAULT 'default' COMMENT '验证码类型',
  `module` varchar(255) DEFAULT 'default' COMMENT '验证码所属业务模块',
  `token` varchar(255) DEFAULT 'default' COMMENT '验证码Token',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `expire_time` datetime DEFAULT NULL COMMENT '失效时间，null代表永久有效',
  `del_status` int(11) DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of catptcha
-- ----------------------------

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
-- Records of msg_notify
-- ----------------------------
INSERT INTO `msg_notify` VALUES ('1459420766649', '用户B评论Product A', '2', '435', 'product', 'comment', '112', '2016-03-31 18:39:27', '2016-03-31 18:39:27', '0');

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
-- Records of msg_save_remind
-- ----------------------------

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
-- Records of msg_subscription
-- ----------------------------
INSERT INTO `msg_subscription` VALUES ('1459420766079', '435', 'product', 'comment', '111', '2016-03-31 18:39:26', '2016-03-31 18:39:26', '0');

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
-- Records of msg_subscription_config
-- ----------------------------

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
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_user_notify
-- ----------------------------
INSERT INTO `msg_user_notify` VALUES ('1459420766790', '0', '111', '1459420766649', null, '2016-03-31 18:39:27', '2016-03-31 18:39:27', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_USER');
INSERT INTO `role` VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('3', 'ROLE_GUEST');

-- ----------------------------
-- Table structure for spring_trans_user
-- ----------------------------
DROP TABLE IF EXISTS `spring_trans_user`;
CREATE TABLE `spring_trans_user` (
  `id` int(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of spring_trans_user
-- ----------------------------

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `Num` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1');
INSERT INTO `test` VALUES ('2');
INSERT INTO `test` VALUES ('3');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'roy', null, 'spring');
INSERT INTO `user` VALUES ('2', 'Craig', null, 'spring');
INSERT INTO `user` VALUES ('3', 'Greg', null, 'spring');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '1', '2');
INSERT INTO `user_role` VALUES ('3', '2', '1');
INSERT INTO `user_role` VALUES ('4', '3', '1');
