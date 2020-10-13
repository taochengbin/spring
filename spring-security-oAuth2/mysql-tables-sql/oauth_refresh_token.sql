/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.43.221_mysql_local
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 192.168.43.221:3306
 Source Schema         : spring_security

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 13/10/2020 10:50:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`  (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` blob NULL,
  `authentication` blob NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
