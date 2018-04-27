/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : threedis

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-04-27 18:46:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `p_gift`
-- ----------------------------
DROP TABLE IF EXISTS `p_gift`;
CREATE TABLE `p_gift` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `human_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `char_id` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_gift
-- ----------------------------
INSERT INTO `p_gift` VALUES ('1', '1', 'PGift:1', 'name:0');
INSERT INTO `p_gift` VALUES ('2', '1', 'PGift:1', 'name:1');
INSERT INTO `p_gift` VALUES ('3', '1', 'PGift:1', 'name:2');
INSERT INTO `p_gift` VALUES ('4', '1', 'PGift:1', 'name:3');
INSERT INTO `p_gift` VALUES ('5', '1', 'PGift:1', 'name:4');
INSERT INTO `p_gift` VALUES ('6', '1', 'PGift:1', 'name:5');
INSERT INTO `p_gift` VALUES ('7', '1', 'PGift:1', 'name:6');
INSERT INTO `p_gift` VALUES ('8', '1', 'PGift:1', 'name:7');
INSERT INTO `p_gift` VALUES ('9', '1', 'PGift:1', 'name:8');
INSERT INTO `p_gift` VALUES ('10', '1', 'PGift:1', 'name:9');

-- ----------------------------
-- Table structure for `p_human`
-- ----------------------------
DROP TABLE IF EXISTS `p_human`;
CREATE TABLE `p_human` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(64) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态 ，  0：刚加载进内存，1：还没有从内存中退出(还存在redis中)',
  `char_id` varchar(64) DEFAULT NULL,
  `device_mac` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_human
-- ----------------------------
INSERT INTO `p_human` VALUES ('1', '呼啦啦啦', '1', 'PHuman:1', '123456');

-- ----------------------------
-- Table structure for `p_test`
-- ----------------------------
DROP TABLE IF EXISTS `p_test`;
CREATE TABLE `p_test` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `char_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_test
-- ----------------------------
