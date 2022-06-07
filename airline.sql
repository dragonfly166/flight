/*
 Navicat Premium Data Transfer

 Source Server         : root2
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : airline2

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 29/04/2022 10:32:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for airplane
-- ----------------------------
DROP TABLE IF EXISTS `airplane`;
CREATE TABLE `airplane`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plane_type_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of airplane
-- ----------------------------
INSERT INTO `airplane` VALUES (1, 1);
INSERT INTO `airplane` VALUES (2, 1);
INSERT INTO `airplane` VALUES (3, 1);
INSERT INTO `airplane` VALUES (4, 1);
INSERT INTO `airplane` VALUES (5, 1);
INSERT INTO `airplane` VALUES (6, 1);
INSERT INTO `airplane` VALUES (7, 1);
INSERT INTO `airplane` VALUES (8, 2);
INSERT INTO `airplane` VALUES (9, 2);
INSERT INTO `airplane` VALUES (10, 2);
INSERT INTO `airplane` VALUES (11, 2);
INSERT INTO `airplane` VALUES (12, 2);
INSERT INTO `airplane` VALUES (13, 2);
INSERT INTO `airplane` VALUES (14, 2);

-- ----------------------------
-- Table structure for airport
-- ----------------------------
DROP TABLE IF EXISTS `airport`;
CREATE TABLE `airport`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `country` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `transit_time` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of airport
-- ----------------------------
INSERT INTO `airport` VALUES (1, '天河机场', '武汉', '中国', 40);
INSERT INTO `airport` VALUES (2, '大兴机场', '北京', '中国', 30);
INSERT INTO `airport` VALUES (3, '浦东机场', '上海', '中国', 30);
INSERT INTO `airport` VALUES (4, '宝安机场', '深圳', '中国', 40);
INSERT INTO `airport` VALUES (5, '澳门机场', '澳门', '中国', 35);
INSERT INTO `airport` VALUES (6, '希思罗机场', '伦敦', '英国', 60);
INSERT INTO `airport` VALUES (7, '白云机场', '广州', '中国', 25);
INSERT INTO `airport` VALUES (8, '新郑机场', '郑州', '中国', 40);
INSERT INTO `airport` VALUES (9, '萧山机场', '杭州', '中国', 30);
INSERT INTO `airport` VALUES (10, '香港机场', '香港', '中国', 50);
INSERT INTO `airport` VALUES (11, '双流机场', '成都', '中国', 25);
INSERT INTO `airport` VALUES (12, '高崎机场', '厦门', '中国', 30);
INSERT INTO `airport` VALUES (13, '小牧机场', '名古屋', '日本', 70);

-- ----------------------------
-- Table structure for flight
-- ----------------------------
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `cost` decimal(10, 0) NULL DEFAULT NULL,
  `from_airport_id` int(11) NULL DEFAULT NULL,
  `to_airport_id` int(11) NULL DEFAULT NULL,
  `airplane_id` int(11) NULL DEFAULT NULL,
  `plane_type_id` int(11) NULL DEFAULT NULL,
  `transit_time` int(11) NULL DEFAULT NULL COMMENT '冗余字段',
  `from_date` date NULL DEFAULT NULL,
  `to_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flight
-- ----------------------------
INSERT INTO `flight` VALUES (1, '2021-11-27 07:00:00', '2021-11-27 07:50:16', 95, 1, 8, 1, 1, 40, '2020-11-01', '2022-11-01');
INSERT INTO `flight` VALUES (2, '2021-11-27 10:00:15', '2021-11-27 11:10:00', 80, 8, 1, 1, 1, 40, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (3, '2021-11-27 08:00:42', '2021-11-27 09:30:05', 150, 1, 2, 2, 1, 40, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (4, '2021-11-27 12:15:32', '2021-11-27 13:15:57', 150, 2, 1, 2, 1, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (5, '2021-11-27 07:30:02', '2021-11-27 09:08:18', 600, 2, 11, 3, 1, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (6, '2021-11-27 10:20:47', '2021-11-27 23:33:52', 15000, 2, 6, 4, 1, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (7, '2021-11-27 17:35:42', '2021-11-28 22:39:52', 8000, 2, 13, 5, 1, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (8, '2021-11-27 10:10:15', '2021-11-27 11:45:51', 800, 2, 9, 6, 1, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (9, '2021-11-27 15:50:43', '2021-11-27 17:30:12', 1000, 2, 3, 6, 1, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (10, '2021-11-27 08:00:00', '2021-11-27 08:40:26', 100, 9, 3, 7, 1, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (11, '2021-11-27 11:00:51', '2021-11-27 13:04:19', 1200, 3, 1, 7, 1, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (12, '2021-11-27 09:00:44', '2021-11-27 11:06:23', 1200, 1, 12, 8, 2, 40, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (13, '2021-11-27 14:07:32', '2021-11-27 16:10:47', 1000, 12, 1, 8, 2, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (14, '2021-11-27 15:13:04', '2021-11-27 16:33:56', 900, 1, 7, 9, 2, 40, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (15, '2021-11-27 18:10:35', '2021-11-27 19:30:45', 950, 7, 1, 9, 2, 25, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (16, '2021-11-27 08:18:39', '2021-11-27 09:32:48', 800, 4, 1, 10, 2, 40, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (17, '2021-11-27 19:21:11', '2021-11-27 20:36:33', 600, 7, 5, 11, 2, 25, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (18, '2021-11-27 17:24:18', '2021-11-27 18:35:54', 700, 7, 10, 12, 2, 25, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (19, '2021-11-27 15:25:25', '2021-11-27 16:56:44', 1200, 7, 12, 13, 2, 25, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (20, '2021-11-28 12:30:00', '2021-11-27 23:30:20', 10000, 3, 13, 14, 2, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (21, '2021-11-27 15:34:43', '2021-11-27 16:52:09', 1000, 2, 3, 6, 1, 30, '2020-11-27', '2022-11-27');
INSERT INTO `flight` VALUES (22, '2021-11-27 16:39:31', '2021-11-27 18:31:39', 1200, 1, 3, 7, 1, 40, '2020-11-27', '2022-11-27');

-- ----------------------------
-- Table structure for flight_record
-- ----------------------------
DROP TABLE IF EXISTS `flight_record`;
CREATE TABLE `flight_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flight_id` int(11) NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  `transit_id` int(11) NULL DEFAULT NULL,
  `passenger_id` int(11) NULL DEFAULT NULL,
  `plane_seat_struct_id` int(11) NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flight_record
-- ----------------------------
INSERT INTO `flight_record` VALUES (1, 2, '2021-11-27', 1, 1, 60, 0);
INSERT INTO `flight_record` VALUES (2, 1, '2021-11-25', 2, 2, 25, 0);
INSERT INTO `flight_record` VALUES (7, 6, '2021-11-28', NULL, 5, NULL, 0);
INSERT INTO `flight_record` VALUES (8, 6, '2021-11-29', NULL, 6, NULL, 0);
INSERT INTO `flight_record` VALUES (9, 6, '2021-11-29', NULL, 7, NULL, 0);
INSERT INTO `flight_record` VALUES (10, 6, '2021-11-30', NULL, 8, NULL, 0);
INSERT INTO `flight_record` VALUES (11, 1, '2021-11-28', NULL, 9, 56, 0);
INSERT INTO `flight_record` VALUES (12, 6, '2021-11-30', NULL, 10, 62, 1);
INSERT INTO `flight_record` VALUES (13, 6, '2021-11-30', NULL, 11, 61, 1);
INSERT INTO `flight_record` VALUES (14, 6, '2021-11-30', NULL, 12, 60, 0);
INSERT INTO `flight_record` VALUES (15, 6, '2021-11-30', NULL, 13, 63, 1);

-- ----------------------------
-- Table structure for flight_seat_price
-- ----------------------------
DROP TABLE IF EXISTS `flight_seat_price`;
CREATE TABLE `flight_seat_price`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flight_id` int(11) NULL DEFAULT NULL,
  `seat_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flight_seat_price
-- ----------------------------
INSERT INTO `flight_seat_price` VALUES (1, 1, '经济舱', 95);
INSERT INTO `flight_seat_price` VALUES (2, 1, '公务舱', 200);
INSERT INTO `flight_seat_price` VALUES (3, 1, '头等舱', 400);
INSERT INTO `flight_seat_price` VALUES (26, 2, '经济舱', 80);
INSERT INTO `flight_seat_price` VALUES (27, 2, '公务舱', 150);
INSERT INTO `flight_seat_price` VALUES (28, 2, '头等舱', 400);
INSERT INTO `flight_seat_price` VALUES (29, 3, '经济舱', 150);
INSERT INTO `flight_seat_price` VALUES (30, 3, '公务舱', 250);
INSERT INTO `flight_seat_price` VALUES (31, 3, '头等舱', 500);
INSERT INTO `flight_seat_price` VALUES (32, 4, '经济舱', 150);
INSERT INTO `flight_seat_price` VALUES (33, 4, '公务舱', 250);
INSERT INTO `flight_seat_price` VALUES (34, 4, '头等舱', 450);
INSERT INTO `flight_seat_price` VALUES (35, 5, '经济舱', 600);
INSERT INTO `flight_seat_price` VALUES (36, 5, '公务舱', 1000);
INSERT INTO `flight_seat_price` VALUES (37, 5, '头等舱', 2500);
INSERT INTO `flight_seat_price` VALUES (38, 6, '经济舱', 15000);
INSERT INTO `flight_seat_price` VALUES (39, 6, '公务舱', 30000);
INSERT INTO `flight_seat_price` VALUES (40, 6, '头等舱', 50000);
INSERT INTO `flight_seat_price` VALUES (41, 7, '经济舱', 8000);
INSERT INTO `flight_seat_price` VALUES (42, 7, '公务舱', 15000);
INSERT INTO `flight_seat_price` VALUES (43, 7, '头等舱', 30000);
INSERT INTO `flight_seat_price` VALUES (44, 8, '经济舱', 800);
INSERT INTO `flight_seat_price` VALUES (45, 8, '公务舱', 1000);
INSERT INTO `flight_seat_price` VALUES (46, 8, '头等舱', 1500);
INSERT INTO `flight_seat_price` VALUES (47, 9, '经济舱', 1000);
INSERT INTO `flight_seat_price` VALUES (48, 9, '公务舱', 1500);
INSERT INTO `flight_seat_price` VALUES (49, 9, '头等舱', 2000);
INSERT INTO `flight_seat_price` VALUES (50, 10, '经济舱', 100);
INSERT INTO `flight_seat_price` VALUES (51, 10, '公务舱', 160);
INSERT INTO `flight_seat_price` VALUES (52, 10, '头等舱', 300);
INSERT INTO `flight_seat_price` VALUES (53, 11, '经济舱', 1200);
INSERT INTO `flight_seat_price` VALUES (54, 11, '公务舱', 2000);
INSERT INTO `flight_seat_price` VALUES (55, 11, '头等舱', 3000);
INSERT INTO `flight_seat_price` VALUES (56, 12, '经济舱', 1200);
INSERT INTO `flight_seat_price` VALUES (57, 12, '公务舱', 1800);
INSERT INTO `flight_seat_price` VALUES (58, 12, '头等舱', 3200);
INSERT INTO `flight_seat_price` VALUES (59, 13, '经济舱', 1000);
INSERT INTO `flight_seat_price` VALUES (60, 13, '公务舱', 1500);
INSERT INTO `flight_seat_price` VALUES (61, 13, '头等舱', 2200);
INSERT INTO `flight_seat_price` VALUES (62, 14, '经济舱', 900);
INSERT INTO `flight_seat_price` VALUES (63, 14, '公务舱', 1500);
INSERT INTO `flight_seat_price` VALUES (64, 14, '头等舱', 2300);
INSERT INTO `flight_seat_price` VALUES (65, 15, '经济舱', 950);
INSERT INTO `flight_seat_price` VALUES (66, 15, '公务舱', 1500);
INSERT INTO `flight_seat_price` VALUES (67, 15, '头等舱', 2400);
INSERT INTO `flight_seat_price` VALUES (68, 16, '经济舱', 800);
INSERT INTO `flight_seat_price` VALUES (69, 16, '公务舱', 1200);
INSERT INTO `flight_seat_price` VALUES (70, 16, '头等舱', 2000);
INSERT INTO `flight_seat_price` VALUES (71, 17, '经济舱', 600);
INSERT INTO `flight_seat_price` VALUES (72, 17, '公务舱', 1000);
INSERT INTO `flight_seat_price` VALUES (73, 17, '头等舱', 1500);
INSERT INTO `flight_seat_price` VALUES (74, 18, '经济舱', 700);
INSERT INTO `flight_seat_price` VALUES (75, 18, '公务舱', 1500);
INSERT INTO `flight_seat_price` VALUES (76, 18, '头等舱', 2600);
INSERT INTO `flight_seat_price` VALUES (77, 19, '经济舱', 1200);
INSERT INTO `flight_seat_price` VALUES (78, 19, '公务舱', 2000);
INSERT INTO `flight_seat_price` VALUES (79, 19, '头等舱', 3200);
INSERT INTO `flight_seat_price` VALUES (80, 20, '经济舱', 10000);
INSERT INTO `flight_seat_price` VALUES (81, 20, '公务舱', 15000);
INSERT INTO `flight_seat_price` VALUES (82, 20, '头等舱', 20000);
INSERT INTO `flight_seat_price` VALUES (83, 21, '经济舱', 1000);
INSERT INTO `flight_seat_price` VALUES (84, 21, '公务舱', 1500);
INSERT INTO `flight_seat_price` VALUES (85, 21, '头等舱', 2000);
INSERT INTO `flight_seat_price` VALUES (86, 22, '经济舱', 1200);
INSERT INTO `flight_seat_price` VALUES (87, 22, '公务舱', 2000);
INSERT INTO `flight_seat_price` VALUES (88, 22, '头等舱', 3000);

-- ----------------------------
-- Table structure for passenger
-- ----------------------------
DROP TABLE IF EXISTS `passenger`;
CREATE TABLE `passenger`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_card` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of passenger
-- ----------------------------
INSERT INTO `passenger` VALUES (1, 'aa', '12345', NULL, NULL);
INSERT INTO `passenger` VALUES (2, '飞', '123456', NULL, NULL);
INSERT INTO `passenger` VALUES (5, '3123', '123', NULL, NULL);
INSERT INTO `passenger` VALUES (6, '312', '3123', NULL, NULL);
INSERT INTO `passenger` VALUES (7, '312', '3123', NULL, NULL);
INSERT INTO `passenger` VALUES (8, '67868', '576', NULL, NULL);
INSERT INTO `passenger` VALUES (9, 'fei', '12312313', NULL, NULL);
INSERT INTO `passenger` VALUES (10, '涂锦', '429006200001131258', NULL, NULL);
INSERT INTO `passenger` VALUES (11, '张谦煜', '429006200001131223', NULL, NULL);
INSERT INTO `passenger` VALUES (12, '涂锦', '429006200001131258', NULL, NULL);
INSERT INTO `passenger` VALUES (13, '张谦煜', '429006200001131223', NULL, NULL);

-- ----------------------------
-- Table structure for plane_seat_struct
-- ----------------------------
DROP TABLE IF EXISTS `plane_seat_struct`;
CREATE TABLE `plane_seat_struct`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `row` int(11) NULL DEFAULT NULL,
  `column` int(11) NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `plane_type_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 87 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plane_seat_struct
-- ----------------------------
INSERT INTO `plane_seat_struct` VALUES (56, 1, 1, '头等舱', 1);
INSERT INTO `plane_seat_struct` VALUES (57, 1, 2, '头等舱', 1);
INSERT INTO `plane_seat_struct` VALUES (58, 1, 3, '头等舱', 1);
INSERT INTO `plane_seat_struct` VALUES (59, 1, 4, '头等舱', 1);
INSERT INTO `plane_seat_struct` VALUES (60, 2, 1, '公务舱', 1);
INSERT INTO `plane_seat_struct` VALUES (61, 2, 2, '公务舱', 1);
INSERT INTO `plane_seat_struct` VALUES (62, 2, 3, '公务舱', 1);
INSERT INTO `plane_seat_struct` VALUES (63, 2, 4, '公务舱', 1);
INSERT INTO `plane_seat_struct` VALUES (64, 3, 1, '经济舱', 1);
INSERT INTO `plane_seat_struct` VALUES (65, 3, 2, '经济舱', 1);
INSERT INTO `plane_seat_struct` VALUES (66, 3, 3, '经济舱', 1);
INSERT INTO `plane_seat_struct` VALUES (67, 3, 4, '经济舱', 1);
INSERT INTO `plane_seat_struct` VALUES (68, 4, 1, '经济舱', 1);
INSERT INTO `plane_seat_struct` VALUES (69, 4, 2, '经济舱', 1);
INSERT INTO `plane_seat_struct` VALUES (70, 4, 3, '经济舱', 1);
INSERT INTO `plane_seat_struct` VALUES (71, 4, 4, '经济舱', 1);
INSERT INTO `plane_seat_struct` VALUES (72, 1, 1, '头等舱', 2);
INSERT INTO `plane_seat_struct` VALUES (73, 1, 2, '头等舱', 2);
INSERT INTO `plane_seat_struct` VALUES (74, 1, 3, '头等舱', 2);
INSERT INTO `plane_seat_struct` VALUES (75, 1, 4, '头等舱', 2);
INSERT INTO `plane_seat_struct` VALUES (76, 2, 1, '公务舱', 2);
INSERT INTO `plane_seat_struct` VALUES (77, 2, 2, '公务舱', 2);
INSERT INTO `plane_seat_struct` VALUES (78, 2, 3, '公务舱', 2);
INSERT INTO `plane_seat_struct` VALUES (79, 2, 4, '公务舱', 2);
INSERT INTO `plane_seat_struct` VALUES (80, 3, 1, '经济舱', 2);
INSERT INTO `plane_seat_struct` VALUES (81, 3, 2, '经济舱', 2);
INSERT INTO `plane_seat_struct` VALUES (82, 3, 3, '经济舱', 2);
INSERT INTO `plane_seat_struct` VALUES (83, 3, 4, '经济舱', 2);
INSERT INTO `plane_seat_struct` VALUES (84, 4, 1, '经济舱', 2);
INSERT INTO `plane_seat_struct` VALUES (85, 4, 2, '经济舱', 2);
INSERT INTO `plane_seat_struct` VALUES (86, 4, 3, '经济舱', 2);
INSERT INTO `plane_seat_struct` VALUES (87, 4, 4, '经济舱', 2);

-- ----------------------------
-- Table structure for plane_type
-- ----------------------------
DROP TABLE IF EXISTS `plane_type`;
CREATE TABLE `plane_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plane_type
-- ----------------------------
INSERT INTO `plane_type` VALUES (1, '空中客车A321');
INSERT INTO `plane_type` VALUES (2, '波音747-800');

-- ----------------------------
-- Table structure for transit
-- ----------------------------
DROP TABLE IF EXISTS `transit`;
CREATE TABLE `transit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT NULL,
  `from_airport_id` int(11) NULL DEFAULT NULL,
  `to_airport_id` int(11) NULL DEFAULT NULL,
  `passenger_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of transit
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
