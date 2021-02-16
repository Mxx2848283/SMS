/*
Navicat MySQL Data Transfer

Source Server         : jdbc
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : sms

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-12-18 09:57:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) DEFAULT NULL,
  `to_id` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `from_id` (`from_id`),
  KEY `to_id` (`to_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1068', '1069', 'Test1', '会u', '2020-10-31 10:03:40', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `last_login_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1075 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1068', '李四', 'lisi', '1111', 'wj@qq.com', '1', '2019-12-15 09:40:50', null);
INSERT INTO `user` VALUES ('1069', '王五', 'wangwu', '1111', 'wangwu@163.com', '1', '2019-12-07 10:48:39', null);
INSERT INTO `user` VALUES ('1071', '张三', 'zhangsan', '1111', 'sdf@163.com', '1', '2019-12-15 15:51:24', null);
INSERT INTO `user` VALUES ('1072', '鹿晗', 'lh', '1111', 'ssdf@163.com', '1', '2019-12-23 07:38:22', null);
INSERT INTO `user` VALUES ('1074', '吴京', 'wj', '1111', 'qeyu@163.com', '1', '2019-12-23 07:41:00', null);
