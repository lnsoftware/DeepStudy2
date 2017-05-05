

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zhiyin_user_user_info
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_user_user_info`;
CREATE TABLE `zhiyin_user_user_info` (
  `id` bigint(20) NOT NULL,
  `password` varchar(1024) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhiyin_user_user_privilege
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_user_user_privilege`;
CREATE TABLE `zhiyin_user_user_privilege` (
  `id` bigint(20) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `authority` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
