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

 Date: 13/10/2020 10:50:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父权限',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限英文名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授权路径',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created` datetime(0) NOT NULL,
  `updated` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (37, 0, '系统管理', 'System', '/', NULL, '2019-04-04 23:22:54', '2019-04-04 23:22:56');
INSERT INTO `tb_permission` VALUES (38, 37, '用户管理', 'SystemUser', '/users/', NULL, '2019-04-04 23:25:31', '2019-04-04 23:25:33');
INSERT INTO `tb_permission` VALUES (39, 38, '查看用户', 'SystemUserView', '', NULL, '2019-04-04 15:30:30', '2019-04-04 15:30:43');
INSERT INTO `tb_permission` VALUES (40, 38, '新增用户', 'SystemUserInsert', '', NULL, '2019-04-04 15:30:31', '2019-04-04 15:30:44');
INSERT INTO `tb_permission` VALUES (41, 38, '编辑用户', 'SystemUserUpdate', '', NULL, '2019-04-04 15:30:32', '2019-04-04 15:30:45');
INSERT INTO `tb_permission` VALUES (42, 38, '删除用户', 'SystemUserDelete', '', NULL, '2019-04-04 15:30:48', '2019-04-04 15:30:45');

SET FOREIGN_KEY_CHECKS = 1;
