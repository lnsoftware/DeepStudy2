CREATE TABLE `physical_view_mapping` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `target_db` VARCHAR(20) NOT NULL COMMENT '目标表所在数据库',
  `target_table` VARCHAR(50) NOT NULL COMMENT '目标表',
  `source_table` VARCHAR(50) NOT NULL COMMENT '源表,示例:database.table',
  `target_table_column` VARCHAR(50) NOT NULL COMMENT '目标表的映射字段,示例:column1,column2,column3',
  `source_table_column` VARCHAR(50) NOT NULL COMMENT '源表的映射字段,与目标表的映射字段顺序一致',
  `target_table_relation_column` VARCHAR(50) NOT NULL COMMENT '目标表与源表的关联字段',
  `source_table_relation_column` VARCHAR(50) NOT NULL COMMENT '源表与目标表的关联字段',
  `is_subject` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '对应的源表是否是主表',
  `is_new_view` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否是新视图',
  `creation_date` BIGINT(20) DEFAULT NULL,
  `modification_date` BIGINT(20) DEFAULT NULL,
  `is_deleted` TINYINT(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='物化视图映射表'