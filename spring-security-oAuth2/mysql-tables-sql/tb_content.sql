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

 Date: 13/10/2020 10:50:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(0) NOT NULL COMMENT '内容类目ID',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容标题',
  `sub_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子标题',
  `title_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题描述',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `pic` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片绝对路径',
  `pic2` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片2',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `created` datetime(0) NULL DEFAULT NULL,
  `updated` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  INDEX `updated`(`updated`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
INSERT INTO `tb_content` VALUES (28, 89, '标题', '子标题', '标题说明', 'http://www.jd.com', NULL, NULL, NULL, '2019-04-07 00:56:09', '2019-04-07 00:56:11');
INSERT INTO `tb_content` VALUES (29, 89, 'ad2', 'ad2', 'ad2', 'http://www.baidu.com', NULL, NULL, NULL, '2019-04-07 00:56:13', '2019-04-07 00:56:15');
INSERT INTO `tb_content` VALUES (30, 89, 'ad3', 'ad3', 'ad3', 'http://www.sina.com.cn', NULL, NULL, NULL, '2019-04-07 00:56:17', '2019-04-07 00:56:19');
INSERT INTO `tb_content` VALUES (31, 89, 'ad4', 'ad4', 'ad4', 'http://www.funtl.com', NULL, NULL, NULL, '2019-04-07 00:56:22', '2019-04-07 00:56:25');

SET FOREIGN_KEY_CHECKS = 1;
