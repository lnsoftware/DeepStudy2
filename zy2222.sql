/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhiyin

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-01-23 16:47:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zy_app_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_app_info`;
CREATE TABLE `zy_app_info` (
  `id` bigint(20) NOT NULL,
  `name` bigint(20) NOT NULL COMMENT '名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `description` varchar(1000) DEFAULT NULL COMMENT 'app描述',
  `found_time` datetime DEFAULT NULL COMMENT '立项时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un` (`name`,`remark`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_app_suggest_feedback
-- ----------------------------
DROP TABLE IF EXISTS `zy_app_suggest_feedback`;
CREATE TABLE `zy_app_suggest_feedback` (
  `id` bigint(20) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `pics` varchar(1000) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_phone` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_app_version
-- ----------------------------
DROP TABLE IF EXISTS `zy_app_version`;
CREATE TABLE `zy_app_version` (
  `id` bigint(20) NOT NULL,
  `app_id` bigint(20) DEFAULT NULL COMMENT 'app标识',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `client_os` varchar(100) DEFAULT NULL,
  `client_os_version` varchar(100) DEFAULT NULL,
  `force_upgrade` int(11) DEFAULT '0',
  `is_latest` int(11) DEFAULT '0' COMMENT '是否为最新版本（0不是，1是）',
  `issue_uid` bigint(20) DEFAULT NULL COMMENT '版本发行人',
  `develop_uid` bigint(20) DEFAULT NULL COMMENT '开发人',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app版本号信息';

-- ----------------------------
-- Table structure for zy_user_2
-- ----------------------------
DROP TABLE IF EXISTS `zy_user_2`;
CREATE TABLE `zy_user_2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(80) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` char(60) NOT NULL DEFAULT '' COMMENT '密码',
  `platform_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '平台ID(1=PC,2=Wap,3=Android,4=iOS)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账号表';

-- ----------------------------
-- Table structure for zy_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `zy_user_login_log`;
CREATE TABLE `zy_user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `platform_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '平台ID(1=PC,2=Wap,3=Android,4=iOS)',
  `channel_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '渠道ID(1=QQ,2=SinaWeibo,3=Weixin)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录日志表';

-- ----------------------------
-- Table structure for zy_user_social
-- ----------------------------
DROP TABLE IF EXISTS `zy_user_social`;
CREATE TABLE `zy_user_social` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `open_id` varchar(120) NOT NULL DEFAULT '' COMMENT '社交平台开放ID',
  `platform_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '平台ID(1=PC,2=Wap,3=Android,4=iOS)',
  `channel_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '渠道ID(1=QQ,2=SinaWeibo,3=Weixin)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户社交账号表';
