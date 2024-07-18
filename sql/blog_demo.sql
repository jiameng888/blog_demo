/*
 Navicat Premium Data Transfer

 Source Server         : jm
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : blog_demo

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 18/07/2024 15:01:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for posts
-- ----------------------------
DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `last_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of posts
-- ----------------------------
BEGIN;
INSERT INTO `posts` VALUES (1, '哈哈哈', '嘿嘿嘿2', 1, '2024-07-17 23:32:54', '2024-07-18 13:51:31');
INSERT INTO `posts` VALUES (2, '中国蓝', '中国蓝', 1, '2024-07-17 23:40:46', '2024-07-17 23:40:46');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `last_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, '张三', 'e10adc3949ba59abbe56e057f20f883e', '100@163.com', '2024-07-17 20:10:12', '2024-07-17 20:10:12');
INSERT INTO `users` VALUES (2, '李四', 'e10adc3949ba59abbe56e057f20f883e', '28765@163.com', '2024-07-17 20:24:59', '2024-07-17 20:24:59');
INSERT INTO `users` VALUES (3, '王五', 'e10adc3949ba59abbe56e057f20f883e', '28765@163.com', '2024-07-17 20:27:01', '2024-07-17 20:27:01');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
