
USE test;

DROP TABLE IF EXISTS optimistic_user;

CREATE TABLE optimistic_user (
    id INT PRIMARY KEY,
    `value` VARCHAR (20),
	version INT DEFAULT 0
) ENGINE = INNODB DEFAULT charset = utf8;

INSERT INTO optimistic_user VALUES(1,'hg',1);