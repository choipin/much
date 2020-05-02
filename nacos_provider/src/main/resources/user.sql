CREATE TABLE `t_user_01` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `upadte_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;