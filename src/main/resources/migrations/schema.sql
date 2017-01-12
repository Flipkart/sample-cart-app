DROP TABLE IF EXISTS `user_info_table`;

CREATE TABLE `user_info_table` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL,
  `contact_no` varchar(64),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_catalog_table`;

CREATE TABLE `product_catalog_table` (
  `product_id` int(10) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(64) NOT NULL,
  `product_price` int(10) NOT NULL,
  `available_quantity` int(10) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `cart_table`;

CREATE TABLE `cart_table` (
  `user_id` int(10) NOT NULL,
  `cart_hash` longtext,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

