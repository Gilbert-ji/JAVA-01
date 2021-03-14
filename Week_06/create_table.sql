drop table if exists t_user;
drop table if exists t_product;
drop table if exists t_order_item;
drop table if exists t_order;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '密码，MD5加密',
  `email` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电话',
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `Index_name` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';


CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) NOT NULL COMMENT '分类编码',
  `name` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
  `subtitle` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品副标题',
  `main_image` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品主图',
  `detail` text COLLATE utf8mb4_bin COMMENT '商品详情',
  `price` decimal(20,2) NOT NULL COMMENT '价格，2位小数',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  `status` int(6) NOT NULL COMMENT '状态 1-在售 2-下架 3-删除',
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `Index_name` (`name`) USING BTREE,
  KEY `Index_category` (`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品表';

CREATE TABLE `t_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户编码',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单编码',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品Id',
  `product_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品名称',
  `product_image` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品主图',
  `current_unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的价格',
  `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) DEFAULT NULL COMMENT '订单总价',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `Index_1` (`order_no`) USING BTREE,
  KEY `Index_2` (`user_id`,`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单详情表';

CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户Id',
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实付金额',
  `payment_type` int(6) DEFAULT NULL COMMENT '支付类型',
  `postage` int(10) DEFAULT NULL COMMENT '运费',
  `status` int(10) DEFAULT NULL COMMENT '状态',
  `payment_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `send_time` timestamp NULL DEFAULT NULL COMMENT '发货时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` timestamp NULL DEFAULT NULL COMMENT '交易关闭时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `Index_1` (`order_no`) USING BTREE,
  KEY `Index_2` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';