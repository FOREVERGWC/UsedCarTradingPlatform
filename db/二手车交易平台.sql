/*
 Navicat Premium Dump SQL

 Source Server         : MYSQL8
 Source Server Type    : MySQL
 Source Server Version : 80038 (8.0.38)
 Source Host           : localhost:3306
 Source Schema         : 二手车交易平台

 Target Server Type    : MySQL
 Target Server Version : 80038 (8.0.38)
 File Encoding         : 65001

 Date: 19/11/2024 17:07:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_address
-- ----------------------------
DROP TABLE IF EXISTS `biz_address`;
CREATE TABLE `biz_address`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址详情',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除(0正常、1删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_address
-- ----------------------------

-- ----------------------------
-- Table structure for biz_car
-- ----------------------------
DROP TABLE IF EXISTS `biz_car`;
CREATE TABLE `biz_car`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '型号',
  `year` int NOT NULL COMMENT '生产年份',
  `mileage` double NOT NULL COMMENT '行驶里程',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '颜色',
  `fuel_type` tinyint NOT NULL COMMENT '燃料类型(1汽油、2柴油、3电动、4混动、5其他)',
  `transmission_type` tinyint NOT NULL COMMENT '变速器类型(1自动档、2手动档)',
  `condition` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车况(1九成新女生自用、2良好、3完好、4轻微刮擦、5叙利亚成色)',
  `license_date` date NULL DEFAULT NULL COMMENT '上牌日期',
  `has_sold` tinyint(1) NOT NULL COMMENT '是否售出',
  `has_check` tinyint(1) NOT NULL COMMENT '是否验车',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '二手车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_car
-- ----------------------------
INSERT INTO `biz_car` VALUES (1, 3, '奥迪', 'A', 2021, 2000, 190000.00, '红色', 1, 1, '2', NULL, 0, 0, '1', '2024-11-19 15:28:29', '1', '2024-11-19 15:28:29', '');

-- ----------------------------
-- Table structure for biz_car_audite
-- ----------------------------
DROP TABLE IF EXISTS `biz_car_audite`;
CREATE TABLE `biz_car_audite`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `car_id` bigint NOT NULL COMMENT '车辆ID',
  `user_id` bigint NOT NULL COMMENT '卖方ID',
  `auditor_id` bigint NOT NULL COMMENT '审核员ID',
  `info` json NOT NULL COMMENT '配置',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `car_id`(`car_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '二手车审核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_car_audite
-- ----------------------------

-- ----------------------------
-- Table structure for biz_order
-- ----------------------------
DROP TABLE IF EXISTS `biz_order`;
CREATE TABLE `biz_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `car_id` bigint NOT NULL COMMENT '车辆ID',
  `car_audite_id` bigint NOT NULL COMMENT '审核ID',
  `sell_id` bigint NOT NULL COMMENT '卖方ID',
  `buy_id` bigint NOT NULL COMMENT '买方ID',
  `address_id` bigint NOT NULL COMMENT '送货地址ID',
  `pay_time` datetime NOT NULL COMMENT '付款时间',
  `pay_price` decimal(10, 2) NOT NULL COMMENT '付款金额',
  `pay_status` tinyint(1) NOT NULL COMMENT '付款状态(0未付款、1已付款)',
  `refund_time` datetime NOT NULL COMMENT '退款时间',
  `refund_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '退款原因',
  `refund_status` tinyint(1) NOT NULL COMMENT '退款状态(0未退款、1已退款)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_order
-- ----------------------------

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `hash_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '散列值',
  `biz_id` bigint NOT NULL COMMENT '业务ID',
  `biz_type` tinyint NOT NULL COMMENT '业务类型',
  `bucket_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '桶名',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件路径',
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名称',
  `file_size` bigint NOT NULL COMMENT '文件大小',
  `chunk_total` int NOT NULL COMMENT '分片数量',
  `chunk_size` bigint NOT NULL COMMENT '分片大小',
  `status` tinyint(1) NOT NULL COMMENT '上传状态(0未完成、1已完成)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `hash_code`(`hash_code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '附件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------
INSERT INTO `sys_attachment` VALUES (10, '5d08ced39910341325c102af785beb54', 0, 0, '', '/file/5d08ced39910341325c102af785beb54.jpg', 'profile.jpg', 69348, 1, 10485760, 1, '', '2024-10-27 14:47:48', '', '2024-10-27 14:47:48', '');

-- ----------------------------
-- Table structure for sys_attachment_chunk
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment_chunk`;
CREATE TABLE `sys_attachment_chunk`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `file_id` bigint NOT NULL COMMENT '文件ID',
  `hash_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '散列值',
  `chunk_index` int NOT NULL COMMENT '分片序号',
  `chunk_size` bigint NOT NULL COMMENT '分片大小',
  `status` tinyint(1) NOT NULL COMMENT '上传状态(0未完成、1已完成)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 691 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '附件分片表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_attachment_chunk
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签',
  `value` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '键值',
  `type_id` bigint NOT NULL COMMENT '类型ID',
  `sort` int NOT NULL COMMENT '排序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态(0禁用、1正常)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, '女', '0', 1, 1, '1', '1', '2024-09-25 22:56:53', '1', '2024-10-10 22:04:54', '');
INSERT INTO `sys_dict_data` VALUES (2, '男', '1', 1, 2, '1', '1', '2024-09-25 22:57:03', '1', '2024-10-10 22:04:57', '');
INSERT INTO `sys_dict_data` VALUES (3, '未知', '2', 1, 3, '1', '1', '2024-09-25 22:57:14', '1', '2024-10-10 22:05:00', '');
INSERT INTO `sys_dict_data` VALUES (4, '禁用', '0', 2, 1, '1', '1', '2024-10-10 22:37:46', '1', '2024-10-10 22:37:46', '');
INSERT INTO `sys_dict_data` VALUES (5, '正常', '1', 2, 2, '1', '1', '2024-10-10 22:38:05', '1', '2024-10-10 22:38:05', '');
INSERT INTO `sys_dict_data` VALUES (6, '汽油', '1', 3, 1, '1', '1', '2024-11-19 14:24:16', '1', '2024-11-19 14:24:16', '');
INSERT INTO `sys_dict_data` VALUES (7, '柴油', '2', 3, 2, '1', '1', '2024-11-19 14:24:29', '1', '2024-11-19 14:24:29', '');
INSERT INTO `sys_dict_data` VALUES (8, '电动', '3', 3, 3, '1', '1', '2024-11-19 14:24:43', '1', '2024-11-19 14:24:43', '');
INSERT INTO `sys_dict_data` VALUES (9, '混动', '4', 3, 4, '1', '1', '2024-11-19 14:25:00', '1', '2024-11-19 14:25:00', '');
INSERT INTO `sys_dict_data` VALUES (10, '其他', '5', 3, 5, '1', '1', '2024-11-19 14:25:10', '1', '2024-11-19 14:25:10', '');
INSERT INTO `sys_dict_data` VALUES (11, '自动档', '1', 4, 1, '1', '1', '2024-11-19 14:32:52', '1', '2024-11-19 14:32:52', '');
INSERT INTO `sys_dict_data` VALUES (12, '手动档', '2', 4, 2, '1', '1', '2024-11-19 14:33:02', '1', '2024-11-19 14:33:02', '');
INSERT INTO `sys_dict_data` VALUES (13, '九成新女生自用', '1', 5, 1, '1', '1', '2024-11-19 14:52:42', '1', '2024-11-19 14:52:42', '');
INSERT INTO `sys_dict_data` VALUES (14, '良好', '2', 5, 2, '1', '1', '2024-11-19 14:53:00', '1', '2024-11-19 14:53:00', '');
INSERT INTO `sys_dict_data` VALUES (15, '完好', '3', 5, 3, '1', '1', '2024-11-19 14:53:14', '1', '2024-11-19 14:53:14', '');
INSERT INTO `sys_dict_data` VALUES (16, '轻微刮擦', '4', 5, 4, '1', '1', '2024-11-19 14:53:26', '1', '2024-11-19 14:53:26', '');
INSERT INTO `sys_dict_data` VALUES (17, '叙利亚成色', '5', 5, 5, '1', '1', '2024-11-19 14:53:37', '1', '2024-11-19 14:53:37', '');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典标识',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典状态(0禁用、1正常)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '性别', 'gender', '1', '1', '2024-09-25 22:56:38', '1', '2024-10-10 22:04:48', '');
INSERT INTO `sys_dict_type` VALUES (2, '启用状态', 'enable_status', '1', '1', '2024-10-10 22:36:51', '1', '2024-10-10 22:40:29', '');
INSERT INTO `sys_dict_type` VALUES (3, '燃料类型', 'fuel_type', '1', '1', '2024-11-19 14:23:41', '1', '2024-11-19 14:23:41', '');
INSERT INTO `sys_dict_type` VALUES (4, '变速器类型', 'transmission_type', '1', '1', '2024-11-19 14:32:22', '1', '2024-11-19 14:32:22', '');
INSERT INTO `sys_dict_type` VALUES (5, '车况', 'car_condition', '1', '1', '2024-11-19 14:43:38', '1', '2024-11-19 14:43:38', '');

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `login_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录类型(1账密、2邮箱验证码、3手机验证码)',
  `os` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作系统',
  `browser` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '浏览器',
  `ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'IP',
  `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'IP属地',
  `status` tinyint(1) NOT NULL COMMENT '状态(0失败、1成功)',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time` DESC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5000183 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '登录日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
INSERT INTO `sys_log_login` VALUES (5000161, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-27 15:19:00', '1', '2024-10-27 15:19:00', '');
INSERT INTO `sys_log_login` VALUES (5000162, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-27 17:30:45', '1', '2024-10-27 17:30:45', '');
INSERT INTO `sys_log_login` VALUES (5000163, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-29 15:56:09', '1', '2024-10-29 15:56:09', '');
INSERT INTO `sys_log_login` VALUES (5000164, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 0, 'Bad credentials', '1', '2024-10-29 15:56:16', '1', '2024-10-29 15:56:16', '');
INSERT INTO `sys_log_login` VALUES (5000165, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-29 16:06:25', '1', '2024-10-29 16:06:25', '');
INSERT INTO `sys_log_login` VALUES (5000166, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 0, '用户帐号已被锁定', '1', '2024-10-29 16:08:57', '1', '2024-10-29 16:08:57', '');
INSERT INTO `sys_log_login` VALUES (5000167, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-29 16:09:36', '1', '2024-10-29 16:09:36', '');
INSERT INTO `sys_log_login` VALUES (5000168, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '%s %s %s', 0, 'Bad credentials', '1', '2024-10-29 16:14:28', '1', '2024-10-29 16:14:28', '');
INSERT INTO `sys_log_login` VALUES (5000169, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-29 16:14:30', '1', '2024-10-29 16:14:30', '');
INSERT INTO `sys_log_login` VALUES (5000170, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-29 17:15:34', '1', '2024-10-29 17:15:34', '');
INSERT INTO `sys_log_login` VALUES (5000171, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-29 17:37:16', '1', '2024-10-29 17:37:16', '');
INSERT INTO `sys_log_login` VALUES (5000172, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-30 14:44:19', '1', '2024-10-30 14:44:19', '');
INSERT INTO `sys_log_login` VALUES (5000173, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-30 15:05:23', '1', '2024-10-30 15:05:23', '');
INSERT INTO `sys_log_login` VALUES (5000174, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '%s %s %s', 1, '请求成功！', '1', '2024-10-30 15:08:56', '1', '2024-10-30 15:08:56', '');
INSERT INTO `sys_log_login` VALUES (5000175, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-30 15:12:02', '1', '2024-10-30 15:12:02', '');
INSERT INTO `sys_log_login` VALUES (5000176, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-30 15:27:46', '1', '2024-10-30 15:27:46', '');
INSERT INTO `sys_log_login` VALUES (5000177, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '%s %s %s', 1, '请求成功！', '1', '2024-10-30 15:28:49', '1', '2024-10-30 15:28:49', '');
INSERT INTO `sys_log_login` VALUES (5000178, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '1', '2024-10-30 15:30:08', '1', '2024-10-30 15:30:08', '');
INSERT INTO `sys_log_login` VALUES (5000179, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '%s %s %s', 1, '请求成功！', '1', '2024-10-30 15:31:28', '1', '2024-10-30 15:31:28', '');
INSERT INTO `sys_log_login` VALUES (5000180, '2', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '未知 未知 未知', 1, '请求成功！', '916586595@qq.com', '2024-10-30 20:18:24', '916586595@qq.com', '2024-10-30 20:18:24', '');
INSERT INTO `sys_log_login` VALUES (5000181, '1', 'OSX', 'MSEdge', '127.0.0.1', '内网IP', 1, '请求成功！', '1', '2024-10-31 17:00:05', '1', '2024-10-31 17:00:05', '');
INSERT INTO `sys_log_login` VALUES (5000182, '1', 'OSX', 'MSEdge', '0:0:0:0:0:0:0:1', '%s %s %s', 1, '请求成功！', '1', '2024-10-31 17:57:51', '1', '2024-10-31 17:57:51', '');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图标',
  `parent_id` bigint NOT NULL COMMENT '父级菜单ID',
  `ancestor_id` bigint NOT NULL COMMENT '祖级菜单ID',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路由地址',
  `redirect` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '重定向地址',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组件路径',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型(1目录、2菜单、3按钮)',
  `sort` int NOT NULL COMMENT '排序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态(0禁用、1正常)',
  `visible` tinyint(1) NOT NULL COMMENT '可见(0否、1是)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (12, '仪表盘', '仪表盘', 'Odometer', 22, 22, '/dashboard', '', '/backend/dashboard/index.vue', '2', 1, '1', 1, '1', '2024-09-02 16:58:27', '1', '2024-09-14 21:31:21', '');
INSERT INTO `sys_menu` VALUES (13, '系统管理', '系统管理', 'House', 0, 0, '/system', '/user', '/', '1', 2, '1', 1, '1', '2024-09-02 17:00:03', '1', '2024-09-02 17:00:03', '');
INSERT INTO `sys_menu` VALUES (14, '用户管理', '用户管理', 'User', 13, 13, '/user', '', '/backend/system/user/index.vue', '2', 1, '1', 1, '1', '2024-09-02 17:04:45', '1', '2024-09-02 17:04:45', '');
INSERT INTO `sys_menu` VALUES (15, '角色管理', '角色管理', 'Avatar', 13, 13, '/role', '', '/backend/system/role/index.vue', '2', 2, '1', 1, '1', '2024-09-02 17:05:38', '1', '2024-09-05 22:14:14', '');
INSERT INTO `sys_menu` VALUES (16, '菜单管理', '菜单管理', 'Menu', 13, 13, '/menu', '', '/backend/system/menu/index.vue', '2', 3, '1', 1, '1', '2024-09-02 17:07:21', '1', '2024-09-02 17:07:32', '');
INSERT INTO `sys_menu` VALUES (17, '二手车管理', '二手车管理', 'Document', 0, 0, '/car', '/car-index', '/', '1', 3, '1', 1, '1', '2024-09-02 17:08:16', '1', '2024-11-15 11:20:46', '');
INSERT INTO `sys_menu` VALUES (18, '二手车', '二手车', 'Document', 17, 17, '/car-index', '', '/backend/car/index.vue', '2', 1, '1', 1, '1', '2024-09-02 17:14:03', '1', '2024-11-15 11:21:29', '');
INSERT INTO `sys_menu` VALUES (19, '二手车审核', '二手车审核', 'AddLocation', 17, 17, '/carAudite', '', '/backend/car/audite/index.vue', '2', 2, '1', 1, '1', '2024-09-02 17:16:49', '1', '2024-11-15 11:23:43', '');
INSERT INTO `sys_menu` VALUES (20, '用户地址', '用户地址', 'BrushFilled', 17, 17, '/address', '', '/backend/address/index.vue', '2', 3, '1', 1, '1', '2024-09-02 17:17:49', '1', '2024-11-15 11:25:03', '');
INSERT INTO `sys_menu` VALUES (21, '权限管理', '权限管理', 'Stamp', 13, 13, '/permission', '', '/backend/system/permission/index.vue', '2', 4, '1', 1, '1', '2024-09-05 22:53:27', '1', '2024-09-05 22:53:41', '');
INSERT INTO `sys_menu` VALUES (22, '首页', '首页', 'HomeFilled', 0, 0, '/', '/dashboard', '/', '1', 1, '1', 1, '1', '2024-09-14 21:30:24', '1', '2024-09-15 18:19:03', '');
INSERT INTO `sys_menu` VALUES (23, '日志管理', '日志管理', 'Cellphone', 13, 13, '/log', '/log/login', '', '1', 6, '1', 1, '1', '2024-09-14 21:40:26', '1', '2024-09-25 22:46:40', '');
INSERT INTO `sys_menu` VALUES (24, '登录日志', '登录日志', 'Key', 23, 13, '/log/login', '', '/backend/system/log/login/index.vue', '2', 1, '1', 1, '1', '2024-09-14 21:42:41', '1', '2024-09-14 21:44:42', '');
INSERT INTO `sys_menu` VALUES (25, 'dict-manage', '字典管理', 'Apple', 13, 13, '/dict', '', '', '1', 5, '1', 1, '1', '2024-09-25 22:46:27', '1', '2024-09-25 22:47:36', '');
INSERT INTO `sys_menu` VALUES (26, 'dict-type', '字典类型', 'Apple', 25, 13, '/dict/type', '', '/backend/system/dict/type/index.vue', '2', 1, '1', 1, '1', '2024-09-25 22:49:46', '1', '2024-09-25 22:49:46', '');
INSERT INTO `sys_menu` VALUES (27, 'dict-data', '字典数据', 'Burger', 25, 13, '/dict/data', '', '/backend/system/dict/data/index.vue', '2', 2, '1', 1, '1', '2024-09-25 22:50:45', '1', '2024-09-25 22:51:16', '');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限标识',
  `parent_id` bigint NOT NULL COMMENT '父级权限ID',
  `ancestor_id` bigint NOT NULL COMMENT '祖级权限ID',
  `sort` int NOT NULL COMMENT '排序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态(0禁用、1正常)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '用户添加', 'system:user:add', 11, 11, 1, '1', '1', '2024-09-05 22:58:46', '1', '2024-09-05 23:32:22', '');
INSERT INTO `sys_permission` VALUES (2, '用户删除', 'system:user:delete', 11, 11, 2, '1', '1', '2024-09-05 22:59:07', '1', '2024-09-05 23:32:37', '');
INSERT INTO `sys_permission` VALUES (3, '用户修改', 'system:user:edit', 11, 11, 3, '1', '1', '2024-09-05 22:59:38', '1', '2024-09-05 23:44:37', '');
INSERT INTO `sys_permission` VALUES (4, '用户列表', 'system:user:list', 11, 11, 4, '1', '1', '2024-09-05 23:00:37', '1', '2024-09-05 23:44:42', '');
INSERT INTO `sys_permission` VALUES (5, '用户查询', 'system:user:query', 11, 11, 5, '1', '1', '2024-09-05 23:05:18', '1', '2024-09-05 23:44:49', '');
INSERT INTO `sys_permission` VALUES (6, '角色添加', 'system:role:add', 12, 12, 1, '1', '1', '2024-09-05 23:11:59', '1', '2024-09-06 00:24:12', '');
INSERT INTO `sys_permission` VALUES (7, '角色删除', 'system:role:delete', 12, 12, 2, '1', '1', '2024-09-05 23:12:13', '1', '2024-09-06 00:24:16', '');
INSERT INTO `sys_permission` VALUES (8, '角色修改', 'system:role:edit', 12, 12, 3, '1', '1', '2024-09-05 23:12:29', '1', '2024-09-06 00:24:21', '');
INSERT INTO `sys_permission` VALUES (9, '角色列表', 'system:role:list', 12, 12, 4, '1', '1', '2024-09-05 23:12:50', '1', '2024-09-06 00:24:26', '');
INSERT INTO `sys_permission` VALUES (10, '角色查询', 'system:role:query', 12, 12, 5, '1', '1', '2024-09-05 23:13:13', '1', '2024-09-06 00:24:30', '');
INSERT INTO `sys_permission` VALUES (11, '用户', 'system:user:*', 0, 0, 1, '1', '1', '2024-09-05 23:14:40', '1', '2024-09-14 01:12:17', '');
INSERT INTO `sys_permission` VALUES (12, '角色', 'system:role:*', 0, 0, 2, '1', '1', '2024-09-05 23:14:57', '1', '2024-09-05 23:14:57', '');
INSERT INTO `sys_permission` VALUES (13, '菜单', 'system:menu:*', 0, 0, 3, '1', '1', '2024-09-06 01:05:09', '1', '2024-09-06 01:05:09', '');
INSERT INTO `sys_permission` VALUES (14, '菜单添加', 'system:menu:add', 13, 13, 1, '1', '1', '2024-09-06 01:51:27', '1', '2024-09-06 01:53:33', '');
INSERT INTO `sys_permission` VALUES (15, '菜单删除', 'system:menu:delete', 13, 13, 2, '1', '1', '2024-09-06 01:51:36', '1', '2024-09-06 01:53:42', '');
INSERT INTO `sys_permission` VALUES (16, '菜单修改', 'system:menu:edit', 13, 13, 3, '1', '1', '2024-09-06 01:51:50', '1', '2024-09-06 01:53:48', '');
INSERT INTO `sys_permission` VALUES (17, '菜单列表', 'system:menu:list', 13, 13, 4, '1', '1', '2024-09-06 01:52:21', '1', '2024-09-06 01:53:58', '');
INSERT INTO `sys_permission` VALUES (18, '菜单查询', 'system:menu:query', 13, 13, 5, '1', '1', '2024-09-06 01:52:34', '1', '2024-09-06 01:54:06', '');
INSERT INTO `sys_permission` VALUES (19, '权限', 'system:permission:*', 0, 0, 4, '1', '1', '2024-09-13 17:52:19', '1', '2024-09-13 17:52:56', '');
INSERT INTO `sys_permission` VALUES (20, '权限添加', 'system:permission:add', 19, 19, 1, '1', '1', '2024-09-13 17:53:20', '1', '2024-09-13 17:56:47', '');
INSERT INTO `sys_permission` VALUES (21, '权限删除', 'system:permission:delete', 19, 19, 2, '1', '1', '2024-09-13 17:53:27', '1', '2024-09-13 17:56:40', '');
INSERT INTO `sys_permission` VALUES (22, '权限修改', 'system:permission:edit', 19, 19, 3, '1', '1', '2024-09-13 17:53:36', '1', '2024-09-13 17:56:29', '');
INSERT INTO `sys_permission` VALUES (23, '权限列表', 'system:permission:list', 19, 19, 4, '1', '1', '2024-09-13 17:53:44', '1', '2024-09-13 17:56:20', '');
INSERT INTO `sys_permission` VALUES (24, '权限查询', 'system:permission:query', 19, 19, 5, '1', '1', '2024-09-13 17:54:03', '1', '2024-09-13 17:56:12', '');
INSERT INTO `sys_permission` VALUES (25, '角色分配菜单', 'system:role:assign:menu', 12, 12, 8, '1', '1', '2024-09-14 01:14:35', '1', '2024-09-24 19:56:09', '');
INSERT INTO `sys_permission` VALUES (26, '角色分配权限', 'system:role:assign:permission', 12, 12, 9, '1', '1', '2024-09-14 01:15:27', '1', '2024-09-24 19:56:14', '');
INSERT INTO `sys_permission` VALUES (27, '用户导入', 'system:user:import', 11, 11, 6, '1', '1', '2024-09-24 19:52:15', '1', '2024-09-24 19:52:15', '');
INSERT INTO `sys_permission` VALUES (28, '角色导入', 'system:role:import', 12, 12, 6, '1', '1', '2024-09-24 19:52:50', '1', '2024-09-24 19:52:50', '');
INSERT INTO `sys_permission` VALUES (29, '菜单导入', 'system:menu:import', 13, 13, 6, '1', '1', '2024-09-24 19:54:38', '1', '2024-09-24 19:54:38', '');
INSERT INTO `sys_permission` VALUES (30, '权限导入', 'system:permission:import', 19, 19, 6, '1', '1', '2024-09-24 19:55:07', '1', '2024-09-24 19:55:07', '');
INSERT INTO `sys_permission` VALUES (31, '用户导出', 'system:user:export', 11, 11, 7, '1', '1', '2024-09-24 19:55:40', '1', '2024-09-24 19:55:40', '');
INSERT INTO `sys_permission` VALUES (32, '角色导出', 'system:role:export', 12, 12, 7, '1', '1', '2024-09-24 19:56:04', '1', '2024-09-24 19:56:04', '');
INSERT INTO `sys_permission` VALUES (33, '菜单导出', 'system:menu:export', 13, 13, 7, '1', '1', '2024-09-24 19:57:30', '1', '2024-09-24 19:57:30', '');
INSERT INTO `sys_permission` VALUES (34, '权限导出', 'system:permission:export', 19, 19, 7, '1', '1', '2024-09-24 19:58:16', '1', '2024-09-24 19:58:16', '');
INSERT INTO `sys_permission` VALUES (35, '日志', 'system:log:*', 0, 0, 6, '1', '1', '2024-09-24 21:06:30', '1', '2024-09-25 22:27:06', '');
INSERT INTO `sys_permission` VALUES (36, '登录日志', 'system:log:login:*', 35, 35, 1, '1', '1', '2024-09-24 21:06:57', '1', '2024-09-24 21:06:57', '');
INSERT INTO `sys_permission` VALUES (37, '操作日志', 'system:log:operation:*', 35, 35, 2, '1', '1', '2024-09-24 21:07:37', '1', '2024-09-24 21:07:37', '');
INSERT INTO `sys_permission` VALUES (38, '字典', 'system:dict:*:*', 0, 0, 5, '1', '1', '2024-09-25 22:26:43', '1', '2024-09-25 22:26:59', '');
INSERT INTO `sys_permission` VALUES (39, '字典类型', 'system:dict:type:*', 38, 38, 1, '1', '1', '2024-09-25 22:27:41', '1', '2024-09-25 22:27:41', '');
INSERT INTO `sys_permission` VALUES (40, '字典数据', 'system:dict:data:*', 38, 38, 2, '1', '1', '2024-09-25 22:27:54', '1', '2024-09-25 22:27:54', '');
INSERT INTO `sys_permission` VALUES (41, '字典类型添加', 'system:dict:type:add', 39, 38, 1, '1', '1', '2024-09-25 22:28:53', '1', '2024-09-25 22:32:08', '');
INSERT INTO `sys_permission` VALUES (42, '字典类型删除', 'system:dict:type:delete', 39, 38, 2, '1', '1', '2024-09-25 22:29:01', '1', '2024-09-25 22:32:21', '');
INSERT INTO `sys_permission` VALUES (43, '字典类型修改', 'system:dict:type:edit', 39, 38, 3, '1', '1', '2024-09-25 22:29:11', '1', '2024-09-25 22:32:56', '');
INSERT INTO `sys_permission` VALUES (44, '字典类型列表', 'system:dict:type:list', 39, 38, 4, '1', '1', '2024-09-25 22:29:31', '1', '2024-09-25 22:33:04', '');
INSERT INTO `sys_permission` VALUES (45, '字典类型查询', 'system:dict:type:query', 39, 38, 5, '1', '1', '2024-09-25 22:29:46', '1', '2024-09-25 22:33:16', '');
INSERT INTO `sys_permission` VALUES (46, '字典类型导入', 'system:dict:type:import', 39, 38, 6, '1', '1', '2024-09-25 22:29:54', '1', '2024-09-25 22:33:27', '');
INSERT INTO `sys_permission` VALUES (47, '字典类型导出', 'system:dict:type:export', 39, 38, 7, '1', '1', '2024-09-25 22:30:14', '1', '2024-09-25 22:33:38', '');
INSERT INTO `sys_permission` VALUES (48, '字典数据添加', 'system:dict:data:add', 40, 38, 1, '1', '1', '2024-09-25 22:34:02', '1', '2024-09-25 22:36:39', '');
INSERT INTO `sys_permission` VALUES (49, '字典数据删除', 'system:dict:data:delete', 40, 38, 2, '1', '1', '2024-09-25 22:34:13', '1', '2024-09-25 22:37:33', '');
INSERT INTO `sys_permission` VALUES (50, '字典数据修改', 'system:dict:data:edit', 40, 38, 3, '1', '1', '2024-09-25 22:34:22', '1', '2024-09-25 22:36:47', '');
INSERT INTO `sys_permission` VALUES (51, '字典数据列表', 'system:dict:data:list', 40, 38, 4, '1', '1', '2024-09-25 22:34:30', '1', '2024-09-25 22:37:20', '');
INSERT INTO `sys_permission` VALUES (52, '字典数据查询', 'system:dict:data:query', 40, 38, 5, '1', '1', '2024-09-25 22:34:39', '1', '2024-09-25 22:37:11', '');
INSERT INTO `sys_permission` VALUES (53, '字典数据导入', 'system:dict:data:import', 40, 38, 6, '1', '1', '2024-09-25 22:34:47', '1', '2024-09-25 22:36:55', '');
INSERT INTO `sys_permission` VALUES (54, '字典数据导出', 'system:dict:data:export', 40, 38, 7, '1', '1', '2024-09-25 22:34:55', '1', '2024-09-25 22:37:03', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `sort` int NOT NULL COMMENT '排序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态(0禁用、1正常)',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除(0正常、1删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 1, '1', '1', '2024-08-19 20:29:06', '1', '2024-08-19 20:39:39', '', 0);
INSERT INTO `sys_role` VALUES (2, '用户', 3, '1', '1', '2024-08-19 20:29:28', '1', '2024-11-19 14:08:26', '', 0);
INSERT INTO `sys_role` VALUES (3, '游客', 3, '1', '1', '2024-08-19 20:29:38', '1', '2024-09-25 00:11:45', '', 0);
INSERT INTO `sys_role` VALUES (4, '测试1', 1, '1', '1', '2024-08-19 20:35:04', '1', '2024-08-21 11:32:50', '', 1);
INSERT INTO `sys_role` VALUES (5, '员工', 2, '1', '1', '2024-11-19 14:08:18', '1', '2024-11-19 14:08:18', '', 0);

-- ----------------------------
-- Table structure for sys_role_menu_link
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_link`;
CREATE TABLE `sys_role_menu_link`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 170 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色、菜单关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu_link
-- ----------------------------
INSERT INTO `sys_role_menu_link` VALUES (119, 2, 12, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` VALUES (120, 2, 17, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` VALUES (121, 2, 18, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` VALUES (122, 2, 19, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` VALUES (123, 2, 20, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` VALUES (124, 2, 22, '1', '2024-09-14 21:31:44', '1', '2024-09-14 21:31:44', '');
INSERT INTO `sys_role_menu_link` VALUES (125, 3, 12, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (126, 3, 18, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (127, 3, 19, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (128, 3, 17, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (129, 3, 20, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (130, 3, 13, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (131, 3, 14, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (132, 3, 15, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (133, 3, 16, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (134, 3, 21, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (135, 3, 22, '1', '2024-09-14 21:31:47', '1', '2024-09-14 21:31:47', '');
INSERT INTO `sys_role_menu_link` VALUES (149, 1, 12, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (150, 1, 17, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (151, 1, 18, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (152, 1, 19, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (153, 1, 20, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (154, 1, 13, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (155, 1, 14, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (156, 1, 15, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (157, 1, 16, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (158, 1, 21, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (159, 1, 22, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (160, 1, 23, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (161, 1, 24, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (162, 1, 25, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (163, 1, 26, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (164, 1, 27, '1', '2024-09-25 22:53:26', '1', '2024-09-25 22:53:26', '');
INSERT INTO `sys_role_menu_link` VALUES (165, 5, 17, '1', '2024-11-19 16:09:13', '1', '2024-11-19 16:09:13', '');
INSERT INTO `sys_role_menu_link` VALUES (166, 5, 12, '1', '2024-11-19 16:09:13', '1', '2024-11-19 16:09:13', '');
INSERT INTO `sys_role_menu_link` VALUES (167, 5, 22, '1', '2024-11-19 16:09:13', '1', '2024-11-19 16:09:13', '');
INSERT INTO `sys_role_menu_link` VALUES (168, 5, 18, '1', '2024-11-19 16:09:13', '1', '2024-11-19 16:09:13', '');
INSERT INTO `sys_role_menu_link` VALUES (169, 5, 19, '1', '2024-11-19 16:09:13', '1', '2024-11-19 16:09:13', '');

-- ----------------------------
-- Table structure for sys_role_permission_link
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_link`;
CREATE TABLE `sys_role_permission_link`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `permission_id` bigint NOT NULL COMMENT '权限ID',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 209 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色、权限关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission_link
-- ----------------------------
INSERT INTO `sys_role_permission_link` VALUES (89, 3, 4, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` VALUES (90, 3, 5, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` VALUES (91, 3, 10, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` VALUES (92, 3, 9, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` VALUES (93, 3, 17, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` VALUES (94, 3, 18, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` VALUES (95, 3, 24, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` VALUES (96, 3, 23, '1', '2024-09-14 01:20:27', '1', '2024-09-14 01:20:27', '');
INSERT INTO `sys_role_permission_link` VALUES (114, 2, 13, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` VALUES (115, 2, 3, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` VALUES (116, 2, 5, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` VALUES (117, 2, 10, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` VALUES (118, 2, 17, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` VALUES (119, 2, 18, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` VALUES (120, 2, 23, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` VALUES (121, 2, 24, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` VALUES (122, 2, 4, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` VALUES (123, 2, 9, '1', '2024-09-14 07:22:04', '1', '2024-09-14 07:22:04', '');
INSERT INTO `sys_role_permission_link` VALUES (158, 1, 1, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (159, 1, 2, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (160, 1, 3, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (161, 1, 4, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (162, 1, 5, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (163, 1, 12, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (164, 1, 6, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (165, 1, 7, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (166, 1, 8, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (167, 1, 9, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (168, 1, 10, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (169, 1, 13, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (170, 1, 14, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (171, 1, 15, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (172, 1, 16, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (173, 1, 17, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (174, 1, 18, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (175, 1, 19, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (176, 1, 20, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (177, 1, 21, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (178, 1, 22, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (179, 1, 23, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (180, 1, 24, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (181, 1, 25, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (182, 1, 26, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (183, 1, 11, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (184, 1, 31, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (185, 1, 27, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (186, 1, 28, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (187, 1, 32, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (188, 1, 29, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (189, 1, 33, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (190, 1, 30, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (191, 1, 34, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (192, 1, 38, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (193, 1, 39, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (194, 1, 41, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (195, 1, 42, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (196, 1, 43, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (197, 1, 44, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (198, 1, 45, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (199, 1, 46, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (200, 1, 47, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (201, 1, 40, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (202, 1, 48, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (203, 1, 49, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (204, 1, 50, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (205, 1, 51, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (206, 1, 52, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (207, 1, 54, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');
INSERT INTO `sys_role_permission_link` VALUES (208, 1, 53, '1', '2024-09-25 22:54:29', '1', '2024-09-25 22:54:29', '');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别(0女、1男、2未知)',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态(0禁用、1正常)',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信小程序开放ID',
  `balance` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '余额',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '最后登录IP',
  `login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (3, '1', '$2a$10$3alyCOMoZZt39BASQUwwTOrGodFCjiwMgHurikWrqAhINrIvDbfqG', '', '管理员', '/file/5d08ced39910341325c102af785beb54.jpg', '2', '2024-08-01', '1', '13037503398', '916586595@qq.com', '1', 0.00, '0:0:0:0:0:0:0:1', '2024-11-19 16:07:59', '', '2024-08-16 01:26:41', '', '2024-11-19 16:07:59', '');
INSERT INTO `sys_user` VALUES (4, '2', '$2a$10$elhEi/ohemfnXateL1BLZ.lLi.fJ31tDVKdSpr3xnr40pdMjAlqlG', '', '张三', '1', '2', '2024-08-22', '1', '13037503390', '1@qq.com', '1', 0.00, '0:0:0:0:0:0:0:1', '2024-11-19 16:21:42', '', '2024-08-16 09:00:11', '', '2024-11-19 16:21:42', '');
INSERT INTO `sys_user` VALUES (5, '3', '$2a$10$3alyCOMoZZt39BASQUwwTOrGodFCjiwMgHurikWrqAhINrIvDbfqG', '1', '1', '/file/c3f7a394-7b91-43b3-b924-5d1592426f06.jpg', '2', '2024-08-27', '1', '13037503391', '2@qq.com', '1', 0.00, '', NULL, '1', '2024-08-21 14:25:56', '1', '2024-08-21 14:25:56', '');
INSERT INTO `sys_user` VALUES (6, '4', '$2a$10$3alyCOMoZZt39BASQUwwTOrGodFCjiwMgHurikWrqAhINrIvDbfqG', '1', '1', '/file/a3336d6e-4ef8-46f0-99e6-a104122b9f88.jpg', '2', '2024-08-17', '0', '13037503392', '3@qq.com', '1', 0.00, '', NULL, '1', '2024-08-21 14:34:13', '1', '2024-08-21 15:13:15', '1');
INSERT INTO `sys_user` VALUES (10, '11', '$2a$10$3alyCOMoZZt39BASQUwwTOrGodFCjiwMgHurikWrqAhINrIvDbfqG', '11', '', '', '2', '2024-10-04', '1', '13037503314', '4@qq.com', '', 0.00, '', NULL, '', '2024-09-13 23:42:22', '', '2024-10-11 13:46:24', '');

-- ----------------------------
-- Table structure for sys_user_role_link
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_link`;
CREATE TABLE `sys_user_role_link`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改者',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id` ASC, `role_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户、角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role_link
-- ----------------------------
INSERT INTO `sys_user_role_link` VALUES (13, 5, 2, '1', '2024-08-21 14:25:56', '1', '2024-08-21 14:25:56', '');
INSERT INTO `sys_user_role_link` VALUES (14, 5, 3, '1', '2024-08-21 14:25:56', '1', '2024-08-21 14:25:56', '');
INSERT INTO `sys_user_role_link` VALUES (21, 6, 2, '1', '2024-08-21 14:58:22', '1', '2024-08-21 14:58:22', '');
INSERT INTO `sys_user_role_link` VALUES (22, 3, 1, '1', '2024-08-21 15:13:59', '1', '2024-08-21 15:13:59', '');
INSERT INTO `sys_user_role_link` VALUES (23, 7, 2, '', '2024-09-02 15:48:49', '', '2024-09-02 15:48:49', '');
INSERT INTO `sys_user_role_link` VALUES (24, 8, 2, '', '2024-09-02 15:48:55', '', '2024-09-02 15:48:55', '');
INSERT INTO `sys_user_role_link` VALUES (26, 10, 2, '', '2024-09-13 23:42:22', '', '2024-09-13 23:42:22', '');
INSERT INTO `sys_user_role_link` VALUES (27, 4, 5, '1', '2024-11-19 16:08:39', '1', '2024-11-19 16:08:39', '');

SET FOREIGN_KEY_CHECKS = 1;
