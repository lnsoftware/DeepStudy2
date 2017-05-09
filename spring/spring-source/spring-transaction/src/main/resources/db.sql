CREATE TABLE `spring_trans_user` (
	`id` INT(10) NOT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`score` INT NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;
