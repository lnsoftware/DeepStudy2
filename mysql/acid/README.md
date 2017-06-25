
USE study_mysql;

DROP TABLE
IF EXISTS users;

CREATE TABLE users (
	id INT PRIMARY KEY auto_increment,
	`name` VARCHAR (20),
	age INT DEFAULT 0
) ENGINE = INNODB DEFAULT charset = utf8;

INSERT into users VALUES(1,'hg',18);