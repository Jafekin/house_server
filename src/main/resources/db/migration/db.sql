/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : db

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 07/03/2024 14:18:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history`  (
  `installed_rank` int NOT NULL,
  `version` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `script` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `checksum` int NULL DEFAULT NULL,
  `installed_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`) USING BTREE,
  INDEX `flyway_schema_history_s_idx`(`success` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history` VALUES (1, '1', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', NULL, 'root', '2024-03-06 16:31:27', 0, 1);

-- ----------------------------
-- Table structure for houselist
-- ----------------------------
DROP TABLE IF EXISTS `houselist`;
CREATE TABLE `houselist`  (
  `houseid` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double(10, 2) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userlist_id` int NULL DEFAULT NULL,
  `userlist_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`houseid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of houselist
-- ----------------------------
INSERT INTO `houselist` VALUES (17, '深圳市盐田区沙头角856号4楼', 1300.00, '已出租', '1、不能使用灶火。\n2、基本家电齐全，拎包入住。\n3. 有WIFI', 31, '何小琳');
INSERT INTO `houselist` VALUES (20, '深圳市盐田区大梅沙66号1楼', 900.00, '已出租', '无', 32, '王大锤');
INSERT INTO `houselist` VALUES (21, '深圳市盐田区大梅沙66号3楼', 800.00, '已出租', '无', 29, '周晓二');
INSERT INTO `houselist` VALUES (23, '深圳市龙华新区民塘路绿景公馆1699（南区）', 2000.00, '未出租', '无', NULL, NULL);
INSERT INTO `houselist` VALUES (26, '深圳市盐田区大梅沙96号1楼', 900.00, '未出租', '家电齐全，拎包入住。', NULL, NULL);
INSERT INTO `houselist` VALUES (27, '深圳市盐田区大梅沙909号3楼', 1800.00, '未出租', '无', NULL, NULL);
INSERT INTO `houselist` VALUES (28, '北京市海淀区摩尔庄园666号6楼', 2000.00, '未出租', '可租1年', NULL, NULL);

-- ----------------------------
-- Table structure for paid
-- ----------------------------
DROP TABLE IF EXISTS `paid`;
CREATE TABLE `paid`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double(10, 2) NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `paydate` date NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userlist_id` int NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `houseid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of paid
-- ----------------------------
INSERT INTO `paid` VALUES (30, '深圳市盐田区沙头角856号4楼', 1300.00, '2019-03-31', '2024-03-06', '何小琳', 31, '已缴', 17);
INSERT INTO `paid` VALUES (31, '深圳市盐田区大梅沙66号1楼', 900.00, '2019-04-01', NULL, '王大锤', 32, '未缴', 20);
INSERT INTO `paid` VALUES (33, '深圳市盐田区大梅沙66号3楼', 800.00, '2024-01-21', NULL, '周晓二', 29, '已缴', 21);
INSERT INTO `paid` VALUES (34, '深圳市盐田区沙头角856号4楼', 1300.00, '2024-03-06', NULL, '何小琳', 31, '已缴', 17);

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` int NULL DEFAULT NULL COMMENT '公告持续天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES (6, '2019-04-01', '注意及时缴纳本月房租', 7);
INSERT INTO `schedule` VALUES (8, '2019-04-15', '电梯停止使用一周，敬请见谅。', 7);
INSERT INTO `schedule` VALUES (9, '2019-04-15', '本月还未缴纳月租的租客请注意及时缴纳月租。', 7);
INSERT INTO `schedule` VALUES (10, '2024-03-06', '天干物燥，注意防火', 20);

-- ----------------------------
-- Table structure for solve
-- ----------------------------
DROP TABLE IF EXISTS `solve`;
CREATE TABLE `solve`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userlist_id` int NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `houseid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of solve
-- ----------------------------
INSERT INTO `solve` VALUES (11, '深圳市盐田区沙头角856号4楼', '2019-04-16', '房门门栓损坏。', '何小琳', 31, '已处理', 17);
INSERT INTO `solve` VALUES (12, '深圳市盐田区大梅沙66号3楼', '2024-03-06', '空调坏了，打不开', '周晓二', 29, '未处理', 21);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL COMMENT '1为管理员，2为普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '12345678', 1);
INSERT INTO `user` VALUES (19, '13192664967', '13192664967', 2);
INSERT INTO `user` VALUES (21, '13192665859', '13192665859', 2);
INSERT INTO `user` VALUES (22, '13356998564', '13356998564', 2);
INSERT INTO `user` VALUES (23, '16538276382', '16538276382', 2);
INSERT INTO `user` VALUES (24, '17745698745', '17745698745', 2);

-- ----------------------------
-- Table structure for userlist
-- ----------------------------
DROP TABLE IF EXISTS `userlist`;
CREATE TABLE `userlist`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of userlist
-- ----------------------------
INSERT INTO `userlist` VALUES (27, '李老铁', '440582199712267459', '13199772826', 1);
INSERT INTO `userlist` VALUES (29, '周晓二', '445215966585236983', '13192664967', 19);
INSERT INTO `userlist` VALUES (31, '何小琳', '440582199512137569', '13192665859', 21);
INSERT INTO `userlist` VALUES (32, '王大锤', '440569199514285698', '13356998564', 22);
INSERT INTO `userlist` VALUES (33, '不良人天巧星', '411466789654627835', '16538276382', 23);
INSERT INTO `userlist` VALUES (34, '张三', '411198503021174', '17745698745', 24);

SET FOREIGN_KEY_CHECKS = 1;
