DROP TABLE IF EXISTS `mooc_user`;
CREATE TABLE `mooc_user` (
  `id` varchar(32) NOT NULL  COMMENT '用户ID',
  `name` varchar(255) NOT NULL  COMMENT '用户名',
  `phone` varchar(32) NOT NULL  COMMENT '手机号',
  `email` varchar(255) COMMENT '邮箱',
  `password` varchar(32) NOT NULL  COMMENT '密码',
  `type` varchar(255) NOT NULL  COMMENT '用户类型',
  `head` varchar(255) COMMENT '头像',
  `gmt_update` timestamp(0) NOT NULL  DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  `gmt_create` timestamp(0) NOT NULL  DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`id`), 
  UNIQUE KEY `name_index` (`name`),UNIQUE KEY `email_index` (`email`),UNIQUE KEY `phone_index` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
