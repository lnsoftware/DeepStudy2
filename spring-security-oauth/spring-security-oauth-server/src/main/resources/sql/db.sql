# CREATE DATABASE IF NOT EXISTS `test_oauth_user`;
# USE `test_oauth_user`;
DROP TABLE IF EXISTS user_authorities;
CREATE TABLE IF NOT EXISTS `user_authorities` (
  `id`        BIGINT PRIMARY KEY AUTO_INCREMENT,
  `username`  VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;


INSERT INTO `user_authorities` (`username`, `authority`) VALUES
  ('admin', 'ROLE_ADMIN'),
  ('test', 'ROLE_USER'),

  ('app_u1', 'ROLE_APP'),
  ('krishna', 'ROLE_ADMIN'),
  ('sudama', 'ROLE_USER');

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS `users` (
  `id`       BIGINT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `enabled`  TINYINT(1)  NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
  ('admin', '21232f297a57a5a743894a0e4a801fc3', 1),
  ('test', '098f6bcd4621d373cade4e832627b4f6', 1),
  ('app_u1', 'b41798e72a4406a1c8fd2372fb71f5d34a87d2c8', 1),
  ('krishna', '21a4ed0a0cf607e77e93bf7604e2bb1ad07757c5', 1),
  ('sudama', '904752ad9c4ae4186c4b4897321c517de0618702', 1);