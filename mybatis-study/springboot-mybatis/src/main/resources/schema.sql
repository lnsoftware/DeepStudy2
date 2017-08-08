CREATE TABLE `products` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `price` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

drop table if exists user;

create table user (id int primary key auto_increment, name varchar, state varchar, country varchar,status int);

--insert into user (name, state, country) values ('Web', 'Delhi', 'India');

CREATE TABLE `order_task` (
  `id` bigint(20) NOT NULL ,
  `status` int(5) DEFAULT '0' COMMENT '执行的状态',
  created datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8