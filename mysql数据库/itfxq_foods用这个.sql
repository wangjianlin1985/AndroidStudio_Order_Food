/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.6
 Source Server Type    : MySQL
 Source Server Version : 50620
 Source Host           : localhost:3306
 Source Schema         : itfxq_foods

 Target Server Type    : MySQL
 Target Server Version : 50620
 File Encoding         : 65001

 Date: 29/08/2022 21:40:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_foods
-- ----------------------------
DROP TABLE IF EXISTS `t_foods`;
CREATE TABLE `t_foods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `foodName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `taste` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(20, 2) NULL DEFAULT NULL,
  `count` bigint(20) NULL DEFAULT NULL,
  `foodPic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `foodType` bigint(20) NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_foods
-- ----------------------------
INSERT INTO `t_foods` VALUES (2, '宫爆鸡丁', '中辣', 29.00, NULL, '647aa13a-6f16-414f-b0b4-c29cb0d5b375.png', 1, '2022-01-19 18:37:38');
INSERT INTO `t_foods` VALUES (3, '回锅肉', '微辣', 19.00, NULL, '6c0edf4b-1931-4872-ab49-78ae6469b77d.png', 1, '2022-01-19 18:39:04');
INSERT INTO `t_foods` VALUES (4, '藿香鲫鱼', '香麻', 30.00, NULL, '3c74b47f-fe65-452d-9136-89a5f572915b.png', 1, '2022-01-19 18:39:48');
INSERT INTO `t_foods` VALUES (5, '麻婆豆腐', '微麻', 19.00, NULL, '538ebc17-d7ef-46cd-9fc8-e382daca2b2d.png', 1, '2022-01-19 18:40:29');
INSERT INTO `t_foods` VALUES (6, '水煮肉片', '辣', 45.00, NULL, '9db9a523-ef9b-4df2-9ef9-151c6c1ab18e.png', 1, '2022-01-19 18:40:56');
INSERT INTO `t_foods` VALUES (7, '拌耳片', '微辣', 30.00, NULL, 'd2a151b0-f69d-4474-93ef-d5511506b1aa.png', 2, '2022-01-19 18:45:04');
INSERT INTO `t_foods` VALUES (8, '凉拌豇豆', '有点酸', 10.00, NULL, 'b4ac8f50-1514-4ed3-ad20-e79d0010a5cd.png', 2, '2022-01-19 18:45:57');
INSERT INTO `t_foods` VALUES (9, '凉拌皮蛋', '清爽', 9.00, NULL, 'ff8be8c6-94f5-4396-84b7-3c894eff1778.png', 2, '2022-01-19 18:46:38');
INSERT INTO `t_foods` VALUES (10, '冰淇淋', '甜', 5.00, NULL, '7e54798e-cebd-4040-9989-7901734345e5.png', 3, '2022-01-19 18:47:23');
INSERT INTO `t_foods` VALUES (11, '水果蛋糕', '甜', 20.00, NULL, '73d97c8d-5078-4712-ac93-784abc4906b2.png', 3, '2022-01-19 18:47:49');
INSERT INTO `t_foods` VALUES (12, '干锅肥肠', '微辣', 59.00, NULL, '49584efd-9b67-4a07-9ddb-191703fbf303.png', 4, '2022-01-19 18:48:28');
INSERT INTO `t_foods` VALUES (13, '干锅花菜', '爽口', 20.00, NULL, '64baac5a-f304-4641-b693-c578807f8907.png', 4, '2022-01-19 18:49:33');
INSERT INTO `t_foods` VALUES (14, '干锅排骨', '麻辣', 59.00, NULL, '915366f4-e401-4362-8410-5d0c31c14459.png', 4, '2022-01-19 18:50:08');
INSERT INTO `t_foods` VALUES (15, '干锅虾', '辣', 69.00, NULL, 'd6716b5d-8246-4b11-af4f-365393a8f277.png', 4, '2022-01-19 18:50:39');
INSERT INTO `t_foods` VALUES (16, '紫菜蛋花汤', '清汤', 10.00, NULL, '25c5bb86-35b0-4699-82df-e5d6b198f807.png', 5, '2022-01-19 18:52:01');
INSERT INTO `t_foods` VALUES (17, '蔬菜汤', '微甜', 20.00, NULL, 'd878b972-cb44-478f-9854-a842515a67e4.png', 5, '2022-01-19 18:52:34');
INSERT INTO `t_foods` VALUES (18, '丸子汤', '清香', 25.00, NULL, 'ae0976f3-2bfd-4397-bbf6-6c9f130d166a.png', 5, '2022-01-19 18:53:39');
INSERT INTO `t_foods` VALUES (19, '米饭', '白味', 2.00, NULL, 'eee87b25-862d-4230-a1d5-64c1f209202a.png', 7, '2022-01-19 18:54:15');
INSERT INTO `t_foods` VALUES (20, '红牛', '有点甜', 5.00, NULL, '9148d46f-e7fe-4ce3-bd3f-e27f2b11cd34.png', 6, '2022-01-19 19:06:57');
INSERT INTO `t_foods` VALUES (21, '绿茶', '有点甜', 3.00, NULL, '7ce04dc1-24df-4ef2-bcef-33bb482372b3.png', 6, '2022-01-19 19:07:21');
INSERT INTO `t_foods` VALUES (28, '青椒肉丝', '麻辣', 23.00, NULL, '0c87602e-9b8e-407e-9aae-661e49596949.jpg', 1, '2022-08-29 14:14:13');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` bigint(20) NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '系统管理', NULL, NULL, 'mdi mdi-file-outline');
INSERT INTO `t_menu` VALUES (3, '角色维护', '/role/index', 1, 'mdi mdi-alarm-bell');
INSERT INTO `t_menu` VALUES (4, '权限维护', '/permission/index', 1, NULL);
INSERT INTO `t_menu` VALUES (5, '菜单维护', '/menu/index', 1, NULL);
INSERT INTO `t_menu` VALUES (22, '菜品管理', NULL, NULL, 'mdi mdi-file-outline');
INSERT INTO `t_menu` VALUES (23, '菜品列表', '/food/index', 22, 'mdi mdi-alarm-bell');
INSERT INTO `t_menu` VALUES (24, '订单管理', NULL, NULL, 'mdi mdi-file-outline');
INSERT INTO `t_menu` VALUES (25, '订单列表', '/order/index', 24, NULL);

-- ----------------------------
-- Table structure for t_orders
-- ----------------------------
DROP TABLE IF EXISTS `t_orders`;
CREATE TABLE `t_orders`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ordernum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isPay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `price` decimal(16, 2) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_orders
-- ----------------------------
INSERT INTO `t_orders` VALUES (1, '202201209594', '1', '2022-01-20 17:29:36', 70.00, '石家庄西王小区', 'admin', '18899991111');
INSERT INTO `t_orders` VALUES (2, '202201202620', '1', '2022-01-20 17:31:55', 52.00, 'xxx', 'admin', '123123123123');
INSERT INTO `t_orders` VALUES (3, '202201209399', '1', '2022-01-20 23:28:05', 24.00, 'yyy', 'admin', '19900019002');
INSERT INTO `t_orders` VALUES (4, '202201204471', '1', '2022-01-20 23:31:45', 54.00, '444', 'admin', '55123123');
INSERT INTO `t_orders` VALUES (5, '202205223793', '1', '2022-05-22 13:00:09', 51.00, '626', 'zsc', '1566548962');
INSERT INTO `t_orders` VALUES (6, '202205221404', '1', '2022-05-22 13:02:17', 101.00, '624', 'zsc', '15963521563');
INSERT INTO `t_orders` VALUES (7, '202205271876', '1', '2022-05-27 22:14:59', 38.00, '石家庄职业技术学院', 'zac', '15933896541');
INSERT INTO `t_orders` VALUES (9, '202205294662', '1', '2022-05-29 14:30:19', 33.00, '西王小区', 'asd', '15896365212');
INSERT INTO `t_orders` VALUES (10, '202208290555', '1', '2022-08-29 14:21:42', 45.00, '成都红星路10号', 'user1', '13511112222');
INSERT INTO `t_orders` VALUES (11, '202208292122', '1', '2022-08-29 14:23:43', 28.00, '成都红星路10号', 'user1', '13511112222');

-- ----------------------------
-- Table structure for t_orders_foods
-- ----------------------------
DROP TABLE IF EXISTS `t_orders_foods`;
CREATE TABLE `t_orders_foods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `foodName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(16, 2) NULL DEFAULT NULL,
  `ordernum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_orders_foods
-- ----------------------------
INSERT INTO `t_orders_foods` VALUES (1, '宫爆鸡丁', 29.00, '202201209594', 1);
INSERT INTO `t_orders_foods` VALUES (2, '回锅肉', 19.00, '202201209594', 1);
INSERT INTO `t_orders_foods` VALUES (3, '麻婆豆腐', 19.00, '202201209594', 1);
INSERT INTO `t_orders_foods` VALUES (4, '藿香鲫鱼', 30.00, '202201202620', 1);
INSERT INTO `t_orders_foods` VALUES (5, '麻婆豆腐', 19.00, '202201202620', 1);
INSERT INTO `t_orders_foods` VALUES (6, '米饭', 2.00, '202201209399', 1);
INSERT INTO `t_orders_foods` VALUES (7, '回锅肉', 19.00, '202201209399', 1);
INSERT INTO `t_orders_foods` VALUES (8, '宫爆鸡丁', 29.00, '202201204471', 1);
INSERT INTO `t_orders_foods` VALUES (9, '干锅花菜', 20.00, '202201204471', 1);
INSERT INTO `t_orders_foods` VALUES (10, '米饭', 2.00, '202201204471', 1);
INSERT INTO `t_orders_foods` VALUES (11, '宫爆鸡丁', 29.00, '202205223793', 1);
INSERT INTO `t_orders_foods` VALUES (12, '回锅肉', 19.00, '202205223793', 1);
INSERT INTO `t_orders_foods` VALUES (13, '拌耳片', 30.00, '202205221404', 1);
INSERT INTO `t_orders_foods` VALUES (14, '凉拌豇豆', 10.00, '202205221404', 1);
INSERT INTO `t_orders_foods` VALUES (15, '凉拌皮蛋', 9.00, '202205221404', 1);
INSERT INTO `t_orders_foods` VALUES (16, '回锅肉', 19.00, '202205221404', 1);
INSERT INTO `t_orders_foods` VALUES (17, '藿香鲫鱼', 30.00, '202205221404', 1);
INSERT INTO `t_orders_foods` VALUES (18, '拌耳片', 30.00, '202205271876', 1);
INSERT INTO `t_orders_foods` VALUES (19, '冰淇淋', 5.00, '202205271876', 1);
INSERT INTO `t_orders_foods` VALUES (20, '红牛', 5.00, '202205287882', 2);
INSERT INTO `t_orders_foods` VALUES (21, '拌耳片', 30.00, '202205287882', 1);
INSERT INTO `t_orders_foods` VALUES (22, '冰淇淋', 5.00, '202205287882', 1);
INSERT INTO `t_orders_foods` VALUES (23, '紫菜蛋花汤', 10.00, '202205294662', 1);
INSERT INTO `t_orders_foods` VALUES (24, '蔬菜汤', 20.00, '202205294662', 1);
INSERT INTO `t_orders_foods` VALUES (25, '青椒肉丝', 23.00, '202208290555', 1);
INSERT INTO `t_orders_foods` VALUES (26, '红牛', 5.00, '202208290555', 1);
INSERT INTO `t_orders_foods` VALUES (27, '米饭', 2.00, '202208290555', 2);
INSERT INTO `t_orders_foods` VALUES (28, '凉拌豇豆', 10.00, '202208290555', 1);
INSERT INTO `t_orders_foods` VALUES (29, '冰淇淋', 5.00, '202208292122', 1);
INSERT INTO `t_orders_foods` VALUES (30, '蔬菜汤', 20.00, '202208292122', 1);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` bigint(20) NULL DEFAULT NULL,
  `menuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, 'user', '用户模块', 0, '2');
INSERT INTO `t_permission` VALUES (7, 'role', '角色模块', 0, '3');
INSERT INTO `t_permission` VALUES (8, 'role:add', '新增角色', 7, NULL);
INSERT INTO `t_permission` VALUES (9, 'role:delete', '删除角色', 7, NULL);
INSERT INTO `t_permission` VALUES (10, 'role:get', '查询角色', 7, NULL);
INSERT INTO `t_permission` VALUES (11, 'role:update', '修改角色', 7, NULL);
INSERT INTO `t_permission` VALUES (13, 'menu', '菜单管理', 0, '5');
INSERT INTO `t_permission` VALUES (14, 'menu:add', '新增菜单', 13, NULL);
INSERT INTO `t_permission` VALUES (15, 'user:add', '用户新增', 1, NULL);
INSERT INTO `t_permission` VALUES (16, 'user:delete', '用户删除', 1, NULL);
INSERT INTO `t_permission` VALUES (17, 'user:get', '用户查询', 1, NULL);
INSERT INTO `t_permission` VALUES (18, 'user:update', '用户更新', 1, NULL);
INSERT INTO `t_permission` VALUES (19, 'menu:delete', '菜单删除', 13, NULL);
INSERT INTO `t_permission` VALUES (20, 'menu:query', '菜单查询', 13, NULL);
INSERT INTO `t_permission` VALUES (21, 'menu:get', '菜单获取', 13, NULL);
INSERT INTO `t_permission` VALUES (22, 'permission', '权限列表', 0, '4');
INSERT INTO `t_permission` VALUES (23, 'food', '菜品列表', 0, '23');
INSERT INTO `t_permission` VALUES (24, 'order', '订单列表', 0, '25');
INSERT INTO `t_permission` VALUES (30, '5132132', '菜品分类', 0, '26');
INSERT INTO `t_permission` VALUES (31, 'asda', 'maka ', 0, '27');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', NULL, NULL);
INSERT INTO `t_role` VALUES (5, '菜品维护员', '', '<![CDATA[<p>菜品维护员</p>\n]]>');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleid` bigint(20) NULL DEFAULT NULL,
  `permissionid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 387 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (367, 1, 1);
INSERT INTO `t_role_permission` VALUES (368, 1, 7);
INSERT INTO `t_role_permission` VALUES (369, 1, 8);
INSERT INTO `t_role_permission` VALUES (370, 1, 9);
INSERT INTO `t_role_permission` VALUES (371, 1, 11);
INSERT INTO `t_role_permission` VALUES (372, 1, 13);
INSERT INTO `t_role_permission` VALUES (373, 1, 14);
INSERT INTO `t_role_permission` VALUES (374, 1, 15);
INSERT INTO `t_role_permission` VALUES (375, 1, 16);
INSERT INTO `t_role_permission` VALUES (376, 1, 18);
INSERT INTO `t_role_permission` VALUES (377, 1, 19);
INSERT INTO `t_role_permission` VALUES (378, 1, 20);
INSERT INTO `t_role_permission` VALUES (379, 1, 21);
INSERT INTO `t_role_permission` VALUES (380, 1, 22);
INSERT INTO `t_role_permission` VALUES (381, 1, 23);
INSERT INTO `t_role_permission` VALUES (382, 1, 24);
INSERT INTO `t_role_permission` VALUES (385, 5, 23);
INSERT INTO `t_role_permission` VALUES (386, 5, 30);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` bit(1) NULL DEFAULT NULL,
  `headImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '$2a$10$voz6igBUPVUYGT6pL1LQBedBnp1/SF4Xp0sXoC7uFHNLNppL32fEW', 'admin@qq.com', '18000010002', b'0', '29bd9275-cb0e-4639-be0a-3941ef7a1730.jpg', '2020-05-15 13:49:07');
INSERT INTO `t_user` VALUES (17, 't1', '$2a$10$t7K//qDwIbaPGt6fiRVL.eXeFHBHtF4aON2VGwd3u.pOwvH8AzTBe', 't1@qq.com', '18800030005', b'1', '229caa82-17c6-4515-9bd6-edbf10212099.png', '2020-06-02 15:04:05');
INSERT INTO `t_user` VALUES (20, 't6', '$2a$10$.DBmvE23vYeY16qR/kRPZeZU3wulG2EQPn/r8xEKNKl0/YOmF68Xi', 't6@qq.com', '18100010002', b'1', '6425bc92-952a-428f-81c1-0a6bb697d661.jpg', '2020-06-12 22:53:31');
INSERT INTO `t_user` VALUES (21, 't7', '$2a$10$3pQ85MLW37W3EvhiyzqkVuSVlATF6DXgK7ns/FDzUhsP6far8WBhm', 't7@qq.com', '18800010002', b'1', 'd93bae7e-70b3-4bf2-8b67-ce6925e95852.jpg', '2020-06-12 23:09:58');
INSERT INTO `t_user` VALUES (22, 't8', '$2a$10$btpAN5Xf7VVxI34qFDftl.M8E.3LKPLa2ojl2wc21Xl7irGq.tPgm', 't8@qq.com', '18800010002', b'1', '571e132b-0b8b-4cf8-ba66-3725c980c59a.jpg', '2020-06-12 23:39:27');
INSERT INTO `t_user` VALUES (24, 'stu1', '$2a$10$HXjVMCWmEck18JvqOT6IeuZwDo/XfVfgTif27DH/1CLpdUTHBrbwS', 'stu1@qq.com', '19900010003', b'0', '3996788d-f637-4f27-87f4-7afd27cd7151.png', '2020-06-30 18:06:49');
INSERT INTO `t_user` VALUES (26, 'stu2', '$2a$10$RjOTW0w0mQIXtXQFxzDPbO3HguIF4L.7qNbdyyYzKFtI4Ksdc34sW', 'stu3@qq.com', '18800010002', b'0', '4ab95f48-14d4-4d04-add0-ec85d387f4d3.jpg', '2020-07-01 09:31:51');
INSERT INTO `t_user` VALUES (27, 'stu456', '$2a$10$97PIreI/cIfNrhegwaJvme2TgqnBynSKN7c66irKNEVRi2o.v4Gxi', 'stu456@qq.com', '18800010004', b'1', '782c2047-1777-4721-ba42-b9e92b02c89d.png', '2020-07-01 09:33:33');
INSERT INTO `t_user` VALUES (28, 'stu5', '$2a$10$UxMQz2FoiajddBRuvkasjeKF8on/aPpKQ9Umcf9ARXEJ0IsTjOEOe', 'stu5@qq.com', '19900010002', b'0', '518114fd-e68c-464f-bf5a-568b9f0917d3.png', '2020-09-04 08:54:11');
INSERT INTO `t_user` VALUES (29, 'adminol', '$2a$10$vyperRvQ7CnYoGo.DwkP.e/TxcCWD63reRONMzHyVQzSHAwvrvrK2', 'stu6@qq.com', '19900010003', b'0', '958dfbf9-c58b-4c81-8806-78a4ebf61bd7.png', '2020-09-04 14:34:03');
INSERT INTO `t_user` VALUES (59, 'user1', '$2a$10$Yh675WDjdxxHg6ZB9tQVIuk19pE07CIMYdM3FEvzD5ITD6daHmln6', 'test@126.com', '13511112222', b'0', NULL, '2022-08-29 14:03:38');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NULL DEFAULT NULL,
  `roleid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (2, 1, 2);
INSERT INTO `t_user_role` VALUES (3, 17, 5);
INSERT INTO `t_user_role` VALUES (14, 18, 2);
INSERT INTO `t_user_role` VALUES (15, 19, 2);
INSERT INTO `t_user_role` VALUES (16, 20, 2);
INSERT INTO `t_user_role` VALUES (17, 21, 2);
INSERT INTO `t_user_role` VALUES (18, 22, 2);
INSERT INTO `t_user_role` VALUES (19, 24, 3);
INSERT INTO `t_user_role` VALUES (21, 26, 3);
INSERT INTO `t_user_role` VALUES (24, 30, 3);
INSERT INTO `t_user_role` VALUES (25, 31, 3);
INSERT INTO `t_user_role` VALUES (26, 32, 3);
INSERT INTO `t_user_role` VALUES (27, 33, 3);
INSERT INTO `t_user_role` VALUES (28, 29, 1);
INSERT INTO `t_user_role` VALUES (29, 28, 1);
INSERT INTO `t_user_role` VALUES (30, 27, 1);

SET FOREIGN_KEY_CHECKS = 1;
