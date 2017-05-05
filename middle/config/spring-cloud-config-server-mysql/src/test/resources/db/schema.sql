create table if not exists config_info (
  id int  PRIMARY KEY AUTO_INCREMENT,
  application VARCHAR (20),
  `profile` VARCHAR (20) DEFAULT 'default',
  `lable` VARCHAR(40) ,
  `key` VARCHAR(40),
  `value` VARCHAR(255) ,
  description VARCHAR(255) ,
  del_status int
);
