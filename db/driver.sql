CREATE DATABASE driver;
use driver;

DROP TABLE IF EXISTS `driver_location`;
CREATE TABLE `driver_location` (
  `id` int(8) NOT NULL,
  `latitude` double(13,10) NOT NULL,
  `longitude` double(13,10) NOT NULL,
  `accuracy` double(7,6) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
)