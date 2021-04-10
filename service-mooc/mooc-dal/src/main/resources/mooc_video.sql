DROP TABLE IF EXISTS `mooc_video`;
CREATE TABLE `mooc_video` (
  `id` varchar(32) NOT NULL  COMMENT '视频ID',
  `owner_id` varchar(32) NOT NULL  COMMENT '所有者ID',
  `name` varchar(255) NOT NULL  COMMENT '视频名',
  `url` varchar(255) NOT NULL  COMMENT '视频地址',
  `cover_url` varchar(255) NOT NULL  COMMENT '封面地址',
  `status` varchar(255) NOT NULL  COMMENT '视频状态',
  `gmt_update` timestamp(0) NOT NULL  DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  `gmt_create` timestamp(0) NOT NULL  DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
