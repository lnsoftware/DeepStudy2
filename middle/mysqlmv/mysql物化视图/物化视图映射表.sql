CREATE TABLE `physical_view_mapping` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `target_db` VARCHAR(20) NOT NULL COMMENT 'Ŀ����������ݿ�',
  `target_table` VARCHAR(50) NOT NULL COMMENT 'Ŀ���',
  `source_table` VARCHAR(50) NOT NULL COMMENT 'Դ��,ʾ��:database.table',
  `target_table_column` VARCHAR(50) NOT NULL COMMENT 'Ŀ����ӳ���ֶ�,ʾ��:column1,column2,column3',
  `source_table_column` VARCHAR(50) NOT NULL COMMENT 'Դ���ӳ���ֶ�,��Ŀ����ӳ���ֶ�˳��һ��',
  `target_table_relation_column` VARCHAR(50) NOT NULL COMMENT 'Ŀ�����Դ��Ĺ����ֶ�',
  `source_table_relation_column` VARCHAR(50) NOT NULL COMMENT 'Դ����Ŀ���Ĺ����ֶ�',
  `is_subject` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '��Ӧ��Դ���Ƿ�������',
  `is_new_view` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '�Ƿ�������ͼ',
  `creation_date` BIGINT(20) DEFAULT NULL,
  `modification_date` BIGINT(20) DEFAULT NULL,
  `is_deleted` TINYINT(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='�ﻯ��ͼӳ���'