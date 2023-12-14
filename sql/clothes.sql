/*
 Navicat Premium Data Transfer

 Source Server         : T_debian mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 49.235.120.2:6033
 Source Schema         : clothes

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 06/12/2023 18:26:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_category
-- ----------------------------
INSERT INTO `sys_category` VALUES (10001, '衣服');
INSERT INTO `sys_category` VALUES (1730175632475791362, '帽子');
INSERT INTO `sys_category` VALUES (1731508842518302721, '帽子');
INSERT INTO `sys_category` VALUES (1732237662994751490, '123');
INSERT INTO `sys_category` VALUES (1732238137500557313, '456');

-- ----------------------------
-- Table structure for sys_category_shop
-- ----------------------------
DROP TABLE IF EXISTS `sys_category_shop`;
CREATE TABLE `sys_category_shop`  (
  `id` bigint(20) NOT NULL,
  `category_id` bigint(20) NULL DEFAULT NULL,
  `shop_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_category_shop
-- ----------------------------
INSERT INTO `sys_category_shop` VALUES (1731634017718685697, 1730175632475791362, 2);
INSERT INTO `sys_category_shop` VALUES (1731645082284208130, 10001, 5);
INSERT INTO `sys_category_shop` VALUES (1731645424862375937, 10001, 6);
INSERT INTO `sys_category_shop` VALUES (1731645513731289090, 1731508842518302721, 8);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `auth_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父id',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色0是管理员1是用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '商品管理', '0', 'shop', '0');
INSERT INTO `sys_menu` VALUES (2, '发布商品', '1', 'shop', '0');
INSERT INTO `sys_menu` VALUES (3, '下单管理', '0', 'message', '0');
INSERT INTO `sys_menu` VALUES (4, '下单信息', '3', 'message', '0');
INSERT INTO `sys_menu` VALUES (5, '历史记录', '0', 'history', '1');
INSERT INTO `sys_menu` VALUES (6, '历史订单', '5', 'history', '1');
INSERT INTO `sys_menu` VALUES (7, '用户管理', '0', 'user', '0');
INSERT INTO `sys_menu` VALUES (8, '用户信息', '7', 'user', '0');

-- ----------------------------
-- Table structure for sys_shop
-- ----------------------------
DROP TABLE IF EXISTS `sys_shop`;
CREATE TABLE `sys_shop`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shopname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `shopmessage` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述',
  `shoppice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品价格',
  `shoppoto` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品图片',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0就上架1是冻结2是下架）',
  `count` bigint(20) NULL DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_shop
-- ----------------------------
INSERT INTO `sys_shop` VALUES (2, '波司登羽绒服', '\n::: hljs-center\n\n### 波司登羽绒服就是牛\n\n:::\n\n::: hljs-center\n\n![test](http://localhost:8542/file/001.jpg)\n\n:::\n\n#### 波司登甄选品质材料，将先进科技与匠心工艺结合，打造全球领先的专业羽绒服。\n#### 更保暖：羽绒源自奢侈品供应商，瑞士蓝标认证绒朵蓬松度600+，高于国家标准30%。根据抗寒级别采用智能填充,保暖更均匀。\n#### 防跑绒：采用德国防绒针、丝光防绒线，以3厘米13针精密缝制。', '1499', 'http://localhost:8542/file/001.jpg,http://localhost:8542/file/cd2c2501317849b5a43726392cdb5601.jpg', '0', 990);
INSERT INTO `sys_shop` VALUES (5, 'NASA FOG NY渐变印花发泡夹克男大码春秋季新款连帽宽松加绒外套', '品牌：NASA FOG NY\n尺码：M L XL 2XL 3XL 4XL 5XL 6XL 7XL 8XL\n面料分类：涤纶\n图案：渐变\n领型：连帽衣\n门襟：拉链\n颜色：白灰色 白绿色 灰色加绒 绿色加绒 RR600白灰+806黑色（套装） RR600白绿+806黑色（套装） RR600白灰+806灰色（套装） RR600白绿+806灰色（套装） RR600白灰加绒+806黑色加绒（套装） RR600白绿加绒+806黑色加绒（套装） RR600白灰加绒+806灰色加绒（套装） RR600白绿加绒+806灰色加绒（套装）\n袖型：收口袖\n货号：NASA021-J600\n细分风格：潮基础\n风格：青春流行适用季节：秋季\n', '158', 'http://localhost:8542/file/49edfae3ae0d4c2da7cdd0c57dd0112b.png,http://localhost:8542/file/07749bf4f3634e3f9c5233eaf2b9efe2.png', '0', 554);
INSERT INTO `sys_shop` VALUES (6, 'FILA 斐乐男装日常舒适保暖休闲基础流行百搭羽绒服F11M242908F', '品牌：Fila/斐乐\n颜色分类：F11M242908FGY\n款号：F11M242908FGY\n运动系列：男子户外\n尺寸：MLXL2XL\n吊牌价：3680\n衣长：常规\n含绒量：80%\n领型：连帽\n填充物：鹅绒\n性别：男', '1433', 'http://localhost:8542/file/226ca6dae5864d378a8bcb68e12716d8.png,http://localhost:8542/file/581d368e6f874b17aff147a9fc5ae837.png,http://localhost:8542/file/48f8b00da8ac4631bccadd10bb941d60.png,http://localhost:8542/file/bc9fb02c448d4642a49f6ee25d010581.png', '0', 544);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '角色0是管理员1是用户',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '123123', '0', '123456', '北京');
INSERT INTO `sys_user` VALUES (2, 'Jack', '123456', '1', '123456', '北京');
INSERT INTO `sys_user` VALUES (3, 'zhangsan', '123456', '1', '123456', '北京');
INSERT INTO `sys_user` VALUES (8, '6666', '6666', '1', '6666', '6666');
INSERT INTO `sys_user` VALUES (9, 'Jack1', '123456', '1', '111', '111111111');

-- ----------------------------
-- Table structure for sys_xiadan
-- ----------------------------
DROP TABLE IF EXISTS `sys_xiadan`;
CREATE TABLE `sys_xiadan`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shopid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `xiadanname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单人',
  `xiadanphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单电话',
  `xiadanmark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单备注',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_xiadan
-- ----------------------------
INSERT INTO `sys_xiadan` VALUES (1, '1', '姚谦', '12345', '杭州市钱塘区', 'zhangsan');
INSERT INTO `sys_xiadan` VALUES (2, '1', '小阮', '123456', '杭州市西湖区', 'zhangsan');
INSERT INTO `sys_xiadan` VALUES (3, '1', '唐杨', '1234567', '杭州市临平区', 'zhangsan');
INSERT INTO `sys_xiadan` VALUES (4, '1', '111111111', '1111111111', '111111111', 'Jack');
INSERT INTO `sys_xiadan` VALUES (25, '8', NULL, NULL, NULL, 'Jack');
INSERT INTO `sys_xiadan` VALUES (26, '8', '11', '111', '111', 'Jack');
INSERT INTO `sys_xiadan` VALUES (27, '2', '1213', '12313', '111', 'Jack');
INSERT INTO `sys_xiadan` VALUES (28, '2', '1231', '12313', '1232311', 'Jack');
INSERT INTO `sys_xiadan` VALUES (29, '2', '111111111', '1111111', '111111111', 'Jack');
INSERT INTO `sys_xiadan` VALUES (30, '5', '1111111', '11111111', '11111111', 'Jack');

SET FOREIGN_KEY_CHECKS = 1;
