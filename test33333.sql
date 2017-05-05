/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-01-23 16:47:30
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
-- Table structure for cache_test_user_info
-- ----------------------------
DROP TABLE IF EXISTS `cache_test_user_info`;
CREATE TABLE `cache_test_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

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
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application` varchar(20) DEFAULT NULL,
  `profile` varchar(20) DEFAULT NULL,
  `lable` varchar(40) DEFAULT NULL,
  `key` varchar(40) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `del_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for msg_basic_data
-- ----------------------------
DROP TABLE IF EXISTS `msg_basic_data`;
CREATE TABLE `msg_basic_data` (
  `id` bigint(20) NOT NULL,
  `title` bigint(20) DEFAULT NULL,
  `test_time` bigint(20) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for msg_notify_read_mark
-- ----------------------------
DROP TABLE IF EXISTS `msg_notify_read_mark`;
CREATE TABLE `msg_notify_read_mark` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '消息的类型，1: 公告 Announce，2: 提醒 Remind，3：信息 Message',
  `read_time` datetime DEFAULT NULL,
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

-- ----------------------------
-- Table structure for msg_user_notify_copy
-- ----------------------------
DROP TABLE IF EXISTS `msg_user_notify_copy`;
CREATE TABLE `msg_user_notify_copy` (
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

-- ----------------------------
-- Table structure for mytable
-- ----------------------------
DROP TABLE IF EXISTS `mytable`;
CREATE TABLE `mytable` (
  `ID` smallint(5) unsigned NOT NULL,
  `Year` year(4) NOT NULL,
  `City` varchar(40) NOT NULL DEFAULT 'Unknown'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `Num` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for zhiyin_community_comment_info
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_comment_info`;
CREATE TABLE `zhiyin_community_comment_info` (
  `id` bigint(20) NOT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `comment` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `thumb_num` int(11) DEFAULT '0',
  `remark` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  `target_user_id` bigint(20) DEFAULT NULL COMMENT '评论回复的用户',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '评论的目标id',
  `type` int(11) DEFAULT '1' COMMENT '1 回复 2 评论回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhiyin_community_comment_thumb
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_comment_thumb`;
CREATE TABLE `zhiyin_community_comment_thumb` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `comment_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhiyin_community_topic_info
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_topic_info`;
CREATE TABLE `zhiyin_community_topic_info` (
  `id` bigint(20) NOT NULL,
  `title` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `media_type` int(11) DEFAULT NULL COMMENT '图片、音频、视频',
  `pics` varchar(1000) DEFAULT NULL COMMENT '图片信息，分号隔开',
  `addr_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `thumb_num` bigint(20) DEFAULT '0',
  `browse_num` int(11) DEFAULT '0',
  `remark` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  `comment_num` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhiyin_community_topic_media
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_topic_media`;
CREATE TABLE `zhiyin_community_topic_media` (
  `id` bigint(20) NOT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `type` bigint(20) DEFAULT NULL,
  `path` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhiyin_community_topic_thumb
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_topic_thumb`;
CREATE TABLE `zhiyin_community_topic_thumb` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_ad_allowe_site
-- ----------------------------
DROP TABLE IF EXISTS `zy_ad_allowe_site`;
CREATE TABLE `zy_ad_allowe_site` (
  `id` bigint(20) NOT NULL COMMENT '音频广告',
  `ad_id` bigint(20) DEFAULT NULL,
  `addr_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `second_pri` (`ad_id`,`addr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for zy_ad_audio_detail
-- ----------------------------
DROP TABLE IF EXISTS `zy_ad_audio_detail`;
CREATE TABLE `zy_ad_audio_detail` (
  `id` bigint(20) NOT NULL COMMENT '音频广告',
  `title` varchar(1000) DEFAULT NULL COMMENT '音频标题',
  `ad_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `save_path` varchar(1000) DEFAULT NULL COMMENT '音频路径',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL COMMENT '音频时长',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_ad_baisc_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_ad_baisc_info`;
CREATE TABLE `zy_ad_baisc_info` (
  `id` bigint(20) NOT NULL COMMENT '音频广告',
  `company_id` bigint(20) DEFAULT NULL,
  `title` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `shelf_on_time` datetime DEFAULT NULL,
  `shelf_off_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `shelf_status` int(11) DEFAULT '1' COMMENT '1未上架；2已上架；3已下架',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Table structure for zy_common_obj_view
-- ----------------------------
DROP TABLE IF EXISTS `zy_common_obj_view`;
CREATE TABLE `zy_common_obj_view` (
  `id` bigint(20) DEFAULT NULL,
  `obj_id` bigint(11) NOT NULL,
  `pond` tinyint(4) NOT NULL COMMENT '池子，就是用来随机用的',
  `view` int(11) NOT NULL,
  PRIMARY KEY (`obj_id`,`pond`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_device_var_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_device_var_info`;
CREATE TABLE `zy_device_var_info` (
  `id` bigint(20) NOT NULL COMMENT '设备可变信息',
  `system_name` varchar(1000) DEFAULT NULL COMMENT '系统名称',
  `system_version` varchar(1000) DEFAULT NULL COMMENT '系统版本号',
  `app_name` varchar(255) DEFAULT NULL COMMENT 'App的名称',
  `app_version` varchar(1000) DEFAULT NULL COMMENT 'App的版本号',
  `app_build_version` varchar(1000) DEFAULT NULL COMMENT 'App build版本号',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_ourchat_broder_dialog
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_broder_dialog`;
CREATE TABLE `zy_ourchat_broder_dialog` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '消息所属用户编号',
  `partner_id` bigint(20) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `receiver` bigint(20) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `is_read` int(11) DEFAULT '0' COMMENT '0未读 1已读',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `s_p` (`user_id`,`partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_ourchat_broder_dialog_pull
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_broder_dialog_pull`;
CREATE TABLE `zy_ourchat_broder_dialog_pull` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '消息所属用户编号',
  `pull_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `s_p` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_ourchat_dialog_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_dialog_info`;
CREATE TABLE `zy_ourchat_dialog_info` (
  `id` bigint(20) NOT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `receiver` bigint(20) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_ourchat_dialog_latest
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_dialog_latest`;
CREATE TABLE `zy_ourchat_dialog_latest` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '消息所属用户编号',
  `partner_id` bigint(20) DEFAULT NULL COMMENT '聊天对象',
  `partner_name` varchar(100) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `receiver` bigint(20) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `is_read` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_ourchat_dialog_record
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_dialog_record`;
CREATE TABLE `zy_ourchat_dialog_record` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '消息所属用户编号',
  `partner_id` bigint(20) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `receiver` bigint(20) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `is_read` int(11) DEFAULT '0' COMMENT '0 已读 1 未读',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `s_p` (`user_id`,`partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_ourchat_dialog_status
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_dialog_status`;
CREATE TABLE `zy_ourchat_dialog_status` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '消息所属用户编号',
  `partner_id` bigint(20) DEFAULT NULL,
  `unread_num` int(11) DEFAULT '0' COMMENT '未读数量',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un` (`user_id`,`partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
