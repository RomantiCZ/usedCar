/*
 Navicat Premium Data Transfer

 Source Server         : node
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : carshop

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 04/07/2023 20:38:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `admin_id` bigint(20) NOT NULL COMMENT '管理员ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系方式',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `role` enum('超级管理员','普通管理员') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '普通管理员' COMMENT '管理员角色类型',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '状态 0为正常1为离职',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_admins` bigint(20) NULL DEFAULT NULL COMMENT '更改信息的管理员id',
  `is_delete` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES (1650386231455424513, 'admin', '70f99bd2352fae60b2fbd8d6f4c1919e', '超级管理员', '男', '15917012116', '411082199311100619', '2495387566@qq.com', '超级管理员', 0, '2023-04-24 14:28:35', '2023-05-20 22:03:53', NULL, 0);
INSERT INTO `admins` VALUES (1650831416566484993, 'caixukun', '612384d587373dae22e7e30ef5222a09', '蔡徐坤', '男', '13020088876', '411082199311100619', '2495387566@qq.com', '普通管理员', 0, '2023-04-25 19:57:35', '2023-05-29 10:36:21', NULL, 0);
INSERT INTO `admins` VALUES (1651163241928814594, 'fanchengcheng', '612384d587373dae22e7e30ef5222a09', '范丞丞', '男', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 17:56:09', '2023-04-26 17:56:09', NULL, 0);
INSERT INTO `admins` VALUES (1651162896204918786, 'chenlinong', '612384d587373dae22e7e30ef5222a09', '陈立农', '男', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 17:54:46', '2023-05-29 10:36:55', NULL, 0);
INSERT INTO `admins` VALUES (1651163331795972097, 'huangminghao', '612384d587373dae22e7e30ef5222a09', '黄明昊', '男', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 17:56:30', '2023-05-29 10:40:02', NULL, 0);
INSERT INTO `admins` VALUES (1651163606627741697, 'linyanjun', '612384d587373dae22e7e30ef5222a09', '林彦俊', '女', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 17:57:36', '2023-05-24 22:15:42', NULL, 0);
INSERT INTO `admins` VALUES (1651163683110875138, 'wangziyi', '612384d587373dae22e7e30ef5222a09', '王子异', '男', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 17:57:54', '2023-04-26 17:59:00', NULL, 0);
INSERT INTO `admins` VALUES (1651163779516952578, 'wanglinkai', '612384d587373dae22e7e30ef5222a09', '王琳凯', '女', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 17:58:17', '2023-04-26 17:58:17', NULL, 0);
INSERT INTO `admins` VALUES (1651164065841115138, 'youzhangjing', '612384d587373dae22e7e30ef5222a09', '尤长靖', '女', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 17:59:25', '2023-04-26 17:59:25', NULL, 0);
INSERT INTO `admins` VALUES (1651164120887160834, 'ikun', '085f0eebe11193f8ead8a535d638ed0f', 'ikun', '女', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 17:59:38', '2023-05-20 17:51:28', NULL, 0);
INSERT INTO `admins` VALUES (1651170154162692098, 'ceshi1', '612384d587373dae22e7e30ef5222a09', '测试1号', '女', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 18:23:37', '2023-04-26 18:23:37', NULL, 0);
INSERT INTO `admins` VALUES (1651170248404508674, 'ceshi2', '612384d587373dae22e7e30ef5222a09', '测试2号', '男', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 18:23:59', '2023-04-26 18:23:59', NULL, 0);
INSERT INTO `admins` VALUES (1651170279916314625, 'ceshi3', '612384d587373dae22e7e30ef5222a09', '测试3号', '男', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 18:24:07', '2023-04-26 18:24:07', NULL, 0);
INSERT INTO `admins` VALUES (1651170323323166722, 'ceshi4', '612384d587373dae22e7e30ef5222a09', '测试4号', '女', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 18:24:17', '2023-04-26 18:24:17', NULL, 0);
INSERT INTO `admins` VALUES (1651171700485189633, 'ceshi5', '612384d587373dae22e7e30ef5222a09', '测试5号', '女', '13020088876', '411082199311100619', '1051902103@qq.com', '普通管理员', 0, '2023-04-26 18:29:45', '2023-04-26 18:40:10', NULL, 0);
INSERT INTO `admins` VALUES (1661316036818927617, 'gucci2', '612384d587373dae22e7e30ef5222a09', 'gucci2', '男', '13535411113', '230103199001192437', 'gaojinling126206@126.com', '普通管理员', 0, '2023-05-24 18:19:44', '2023-05-24 18:19:44', NULL, 0);
INSERT INTO `admins` VALUES (1660950218110791681, 'yaxin2', '612384d587373dae22e7e30ef5222a09', 'yaxin2', '女', '15510620000', '130322198012111614', 'junqi24368hpl@163.com', '普通管理员', 0, '2023-05-23 18:06:06', '2023-05-23 18:06:06', NULL, 0);
INSERT INTO `admins` VALUES (1661315243118198785, 'gucci', '612384d587373dae22e7e30ef5222a09', 'gucci', '男', '17724259702', '500225199707256876', 'junqi24368hpl@163.com', '普通管理员', 0, '2023-05-24 18:16:34', '2023-05-24 21:47:09', NULL, 0);
INSERT INTO `admins` VALUES (1660949436334469121, 'yaxin', '612384d587373dae22e7e30ef5222a09', 'yaxin', '男', '18515000002', '370111195511205237', 'tuoba19934@163.com', '普通管理员', 0, '2023-05-23 18:02:59', '2023-05-25 22:15:43', NULL, 0);
INSERT INTO `admins` VALUES (1661315719305920513, 'gucci1', '612384d587373dae22e7e30ef5222a09', 'gucci1', '女', '18148976135', '150103198208081158', 'songpu199193ypd@163.com', '普通管理员', 0, '2023-05-24 18:18:28', '2023-05-24 22:17:33', NULL, 0);
INSERT INTO `admins` VALUES (1661318755491282945, 'gucci3', '34e4488087acb984bdf61946718400dc', 'gucci3', '女', '17724502899', '342122197701036272', 'tuoba19934@163.com', '普通管理员', 0, '2023-05-24 18:30:32', '2023-05-29 17:17:43', NULL, 0);

-- ----------------------------
-- Table structure for cars_rental
-- ----------------------------
DROP TABLE IF EXISTS `cars_rental`;
CREATE TABLE `cars_rental`  (
  `vehicle_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '车辆ID',
  `brand` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌',
  `model` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '型号',
  `year` bigint(4) NULL DEFAULT NULL COMMENT '制造年份',
  `mileage` decimal(10, 2) NULL DEFAULT NULL COMMENT '行驶里程',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `rental_price` decimal(10, 2) NOT NULL COMMENT '租借价格',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '状态 0为正常 1为为出租中 2为下架',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_admins` bigint(20) NULL DEFAULT NULL COMMENT '创建的管理员ID',
  `update_admins` bigint(20) NULL DEFAULT NULL COMMENT '更新的管理员ID',
  `is_delete` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除 0为正常  1为租借中 2为下架',
  PRIMARY KEY (`vehicle_id`) USING BTREE,
  INDEX `create_admins`(`create_admins`) USING BTREE,
  INDEX `update_admins`(`update_admins`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1676057984156385282 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cars_rental
-- ----------------------------
INSERT INTO `cars_rental` VALUES (1667152734355742721, '雷克萨斯', '雷克萨斯RX 2020款 改款 300 四驱典雅版', 2020, 4.10, 'a4102c79-96f4-49a8-b939-a4804ffe030e.png', '<p>金属面原漆，贴了车衣，加装360，极品车况</p>', 300.00, 0, '2023-06-09 20:52:41', '2023-07-03 20:29:25', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1667153384267341825, '奔驰', '奔驰E级(进口) 2020款 E 260 运动版', 2020, 6.00, '7912685b-f0b3-4670-81fb-3b16e71c60c2.png', '<p>20年9月奔驰E260运动版 进口 指导价42.58万 原车白色贴膜 一手车6万公里</p>', 350.00, 0, '2023-06-09 20:55:16', '2023-07-02 20:58:26', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1667152314958897154, '凯迪拉克', '凯迪拉克CT6 2021款 28T 精英型', 2021, 3.90, '5bfacb4e-9cfe-4c40-ba0f-03bb5edae9b1.png', '<p>个人一手用车，车况完美，公里数少，绝对排除火烧水泡大事故，接受任何三方验车机构验车！可分期方案灵活，欢迎到店看车试驾！</p>', 160.00, 0, '2023-06-09 20:51:01', '2023-06-13 16:52:01', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1667153828783874050, '宝马', '宝马X5(进口) 2017款 xDrive28i', 2017, 9.30, 'aa780888-aa8c-489c-9564-ed7d703ace2d.png', '<p>【广州宝悦宝马MINI官方认证二手车车源承诺】 1、车龄不超过6年，行驶里程不超过120,000公里 2、12个月不限里程BMW/MINI原厂保修 3、24小时道路救保障 4、360度官方认证 5、互联服务 6、贴心增值服务 广州宝悦汽车贸易有限公司隶属著名跨国企业--森那美汽车集团，是德国宝马汽车集团和华晨宝马汽车有限公司在广东省广州市授权经销商，坐落在广州市天河区大观南路57号。 本公司销售的二手车车源均为BMW/MINI官方活动用车、车展展示用车、4S店试驾车和客户置换的高品质用车，无事故，无泡水，拒绝销售差品，质量保证，信赖首选！</p>', 300.00, 0, '2023-06-09 20:57:02', '2023-06-13 16:52:01', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1667153981620117506, '奥迪', '奥迪A6L 2009款 3.0 TFSI quattro 豪华型', 2009, 22.00, '34414fb5-b820-4ac5-9c17-5bf5c45fac11.png', '<p>新到10年2月奥迪A6L 3.0T机械增压 四驱豪华版 指导价69.6万 西装暴徒 重装已整备完毕</p>', 250.00, 0, '2023-06-09 20:57:38', '2023-06-13 16:52:01', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1667154161593507842, '沃尔沃', '沃尔沃XC90 2022款 B6 智雅豪华版 7座', 2022, 1.20, '4d310436-cf0a-4be1-af40-37b12d6deeac.png', '<p>车辆全程4S店保养，车辆细小划痕，车辆无重大事故</p>', 180.00, 0, '2023-06-09 20:58:21', '2023-06-13 16:52:01', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1667154357345869825, '大众', '迈腾 2009款 1.8TSI DSG舒适型', 2009, 10.00, '83db1f2a-f489-4638-8ebb-efbc57098ba0.png', '<p>车况精品，无重大事故，无火烧泡水，无加装改装配置。让您买的放心，省心，开心。底盘结构：无明显磨损发动机舱：运转良好外观：车身无结构性损伤内饰：干净整洁漆面：保养良好</p>', 150.00, 0, '2023-06-09 20:59:08', '2023-06-13 16:52:01', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1667154478506729474, '本田', '雅阁 2018款 锐·混动 2.0L 锐领版 国VI', 2018, 4.66, '8c89f634-d20c-4128-8cd9-da18bfbe52b7.png', '<p>低首付，低利息，承诺无大事故，无水泡，无火烧，车况透明。国企品质，信心之选。</p>', 120.00, 0, '2023-06-09 20:59:37', '2023-06-13 16:52:01', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1667154693431255041, '日产', '奇骏 2014款 2.0L CVT舒适版 2WD', 2014, 8.45, '6d2d3636-5497-4a31-8ae3-ca8f394fd4ff.png', '<p>【车辆名称】 日产 日产奇骏 2014款 2.0L CVT 舒适版 两驱【上牌时间】2017|01 - 【行驶里程】8.45万公里【车辆概况】漆面成新度好，整备质量高，保证美观度的同时，也提高车漆的耐久性。内饰经过全方位清洁，清除使用痕迹和磨损修复。并确保电动调节设备、音响、导航、空调等电器设备正常工作。保证车辆行驶的稳定性和驾驶的平顺性，发动机运行平稳，变速器的动力传递稳定，制动、悬挂正常有效。车辆结构部件完整。</p>', 130.00, 0, '2023-06-09 21:00:28', '2023-06-13 18:31:30', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1667154803447848962, '日产', '思域 2016款 220TURBO CVT尊耀版', 2016, 4.58, 'ba14b527-1392-475a-a9a5-655cddd0680d.png', '<p>【车辆名称】 本田 本田思域 2016款 220TURBO CVT 尊耀版【上牌时间】2017|12 - 【行驶里程】4.58万公里【车辆概况】漆面成新度好，整备质量高，保证美观度的同时，也提高车漆的耐久性。内饰经过全方位清洁，清除使用痕迹和磨损修复。并确保电动调节设备、音响、导航、空调等电器设备正常工作。保证车辆行驶的稳定性和驾驶的平顺性，发动机运行平稳，变速器的动力传递稳定，制动、悬挂正常有效。车辆结构部件完整。</p>', 120.00, 0, '2023-06-09 21:00:54', '2023-06-13 18:38:15', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1675844649666289666, 'GGBOND', '死肥猪', 2023, 100.00, 'fea9508c-7148-4f81-8a74-434d01722504.png', '<p>顶级大肥猪</p>', 250.00, 0, '2023-07-03 20:31:15', '2023-07-04 10:28:54', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (1676057984156385281, '保时捷', '窥见', 2023, 20.00, '6dfc671f-bd7b-45ff-b2f6-7559d9c2322d.jpg', '<p>好一台保时捷</p>', 202.00, 0, '2023-07-04 10:38:58', '2023-07-04 10:40:20', NULL, NULL, 0);
INSERT INTO `cars_rental` VALUES (156156156, '宝马', '宝马5系 2020款 525Li 豪华套装', 2022, 6.91, 'c22723ad-9164-47fa-a868-028ec08c89d2.png', '<p>女车主一手车，【全车原版原漆】，全程4S店保养，选配哈曼卡顿音响、手势控制系统，升级宝马530高配原厂勺子大灯，加装行车记录仪。原车标配：一键启动、电动天窗、电子驻车、疲劳驾驶提示、前后倒车雷达、倒车影像、定速巡航、驾驶模式切换、自动驻车、上坡辅助、电动后备箱、主动闭合是格栅、电动调节方向盘、多功能方向盘、方向盘记忆、彩色行车电脑、12.3英寸全液晶仪表盘、手机无线充电、皮座椅、双电动座椅、座椅记忆、10.25英寸触控液晶屏、原厂导航、蓝牙音乐、道路救援呼叫、CarPlay、语音识别控制系统、车联网、远近光LED大灯、自动大灯、LED日行灯、11色氛围灯、电动折叠加热记忆后视镜、锁车自动折叠后视镜、自动防眩目内后视镜、自动雨刮、自动恒温分区空调、后排出风口。</p>', 250.00, 0, '2023-06-14 20:34:29', '2023-06-14 21:22:55', NULL, NULL, 0);

-- ----------------------------
-- Table structure for cars_sale
-- ----------------------------
DROP TABLE IF EXISTS `cars_sale`;
CREATE TABLE `cars_sale`  (
  `vehicle_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '车辆ID',
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '型号',
  `year` bigint(4) NULL DEFAULT NULL COMMENT '制造年份',
  `mileage` decimal(10, 2) NULL DEFAULT NULL COMMENT '行驶里程',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态 0为正常 1为别预定 2为为出售  3为下架',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_admins` bigint(20) NULL DEFAULT NULL COMMENT '创建管理员id',
  `update_admins` bigint(20) NULL DEFAULT NULL COMMENT '更新的管理员id',
  `is_delete` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除 0为正常 1为删除',
  PRIMARY KEY (`vehicle_id`) USING BTREE,
  INDEX `创建的管理员ID`(`create_admins`) USING BTREE,
  INDEX `更新的管理员ID`(`update_admins`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1670820978732122114 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cars_sale
-- ----------------------------
INSERT INTO `cars_sale` VALUES (1670819132391112706, '宝马', '宝马1系 2021款 120i M运动曜夜版', 2021, 5.00, 'bd1cad95-96a5-4171-a1f9-0d24fb57ab19.png', '<p>新到20年4月宝马120i M运动曜夜版 指导价22.68万 准新车状态 保养极好 全车原版原漆 一手车 花小钱办大事</p>', 23.80, 0, '2023-06-19 23:41:38', '2023-06-29 23:21:50', NULL, NULL, 0);
INSERT INTO `cars_sale` VALUES (1670819750375669761, '别克', '英朗 2021款 典范 1.5L 自动精英型', 2021, 4.89, '3a95b1af-58ac-48bd-ac78-34f78a9e520b.png', '<p>【车辆名称】 别克 别克英朗 2021款 典范 1.5L 手自一体 精英型【上牌时间】2020|08 - 【行驶里程】4.89万公里【车辆概况】漆面成新度好，整备质量高，保证美观度的同时，也提高车漆的耐久性。内饰经过全方位清洁，清除使用痕迹和磨损修复。并确保电动调节设备、音响、导航、空调等电器设备正常工作。保证车辆行驶的稳定性和驾驶的平顺性，发动机运行平稳，变速器的动力传递稳定，制动、悬挂正常有效。车辆结构部件完整。</p>', 6.58, 0, '2023-06-19 23:44:05', '2023-06-20 17:56:32', NULL, NULL, 0);
INSERT INTO `cars_sale` VALUES (1670819970597601281, '日产', '奇骏 2021款 VC-Turbo 300 CVT 4WD至尊版', 2021, 2.50, '489ef920-378f-4b20-a309-dbb5c356a290.png', '<p>东风日产4S店一手车源，享受及新车政策。 3年10万公里质保，一年0息，2-3年低息。 交付标准：与新车服务一致。 欢迎到店体验！</p>', 15.38, 0, '2023-06-19 23:44:58', '2023-06-20 17:18:34', NULL, NULL, 0);
INSERT INTO `cars_sale` VALUES (1670820096569327618, '宝马', '宝马X3 2020款 xDrive30i 领先型 M运动套装', 2020, 5.00, 'dd8841fb-1c6f-4737-afef-63269b24de39.png', '<p>宝马X3 30i领先型 M运动套装，让您领先别人一大步，M运动套件攻击性极强，运动感拉满，2.0T直列四缸发动机，能爆发出252匹马力，350牛米，搭配采埃孚8AT变速箱让您换挡快人一步，超车无需多言，HUD抬头显示让您不用低头看仪表盘，妈妈再也不用担心我超速啦，20寸大轮毂让您在车群中脱颖而出，360度全景影像让您不再害怕倒车入库，LED大灯让您不惧黑暗，可开启全景天窗让您亲近大自然，哈曼卡顿让您旅途中不再无聊，伴随着音乐到达目的地</p>', 30.70, 0, '2023-06-19 23:45:28', '2023-06-20 17:18:34', NULL, NULL, 0);
INSERT INTO `cars_sale` VALUES (1670820268565151746, '日产', '轩逸 2020款 1.6L XL CVT悦享版', 2020, 5.80, 'c5850786-8e81-4825-9041-d5f60654e87e.png', '<p>车辆无事故无水泡，定期在4S店做维修保养</p>', 8.38, 0, '2023-06-19 23:46:09', '2023-06-20 17:18:57', NULL, NULL, 0);
INSERT INTO `cars_sale` VALUES (1670820480197148674, '丰田', '威兰达 2020款 2.0L 四驱豪华版', 2020, 3.50, '30af9e78-8bdf-4dd0-80c5-5cfcb8513b7a.png', '<p>新到21年3月丰田威兰达2.0四驱豪华版 指导价20.68万现价16.50万 &nbsp;准新车 全车原版原漆 一手车</p>', 16.50, 0, '2023-06-19 23:46:59', '2023-06-20 17:18:34', NULL, NULL, 0);
INSERT INTO `cars_sale` VALUES (1670820978732122113, '雷克萨斯', '雷克萨斯ES 2020款 200 卓越版 国VI', 2020, 2.50, '35d7fa64-af01-4a0d-8d68-881289e9dcf8.png', '<p>雷克萨斯精选二手车 该车车况精品，整车进口，全程4s店保修保养，记录齐全，雷克萨斯置换车源，无水泡，无调表，无重大事故。</p>', 24.80, 0, '2023-06-19 23:48:58', '2023-06-20 17:18:34', NULL, NULL, 0);

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites`  (
  `favorite_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `vehicle_id` bigint(20) NOT NULL COMMENT '车辆ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` int(10) NOT NULL DEFAULT 0 COMMENT '逻辑删除 0为正常 1为删除',
  PRIMARY KEY (`favorite_id`) USING BTREE,
  INDEX `用户ID`(`user_id`) USING BTREE,
  INDEX `租借的车量ID`(`vehicle_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of favorites
-- ----------------------------

-- ----------------------------
-- Table structure for history_records
-- ----------------------------
DROP TABLE IF EXISTS `history_records`;
CREATE TABLE `history_records`  (
  `record_id` bigint(20) NOT NULL COMMENT '历史记录ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '记录id 到order表里面查询',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` int(10) NOT NULL COMMENT '逻辑删除 0正常 1删除',
  PRIMARY KEY (`record_id`) USING BTREE,
  INDEX `用户ID`(`user_id`) USING BTREE,
  INDEX `订单ID`(`order_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of history_records
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` bigint(20) NOT NULL COMMENT '订单更改或更新的管理员',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `vehicle_id` bigint(20) NOT NULL COMMENT '车辆ID',
  `order_type` enum('购买','租借') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单类型',
  `status` int(11) NOT NULL COMMENT '订单状态\r\n1.\'待付款\',\r\n2.\'待审核\',\r\n3.\'待取车\',\r\n4.\'待还车\',\r\n5.\'已完成\'\r\n6.‘取消订单’\r\n7.\'订单结束\'',
  `amount` decimal(10, 2) NOT NULL COMMENT '订单金额',
  `deposit` decimal(10, 2) NULL DEFAULT NULL COMMENT '押金',
  `start_time` datetime(0) NOT NULL COMMENT '服务开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '服务结束时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_admins` bigint(20) NULL DEFAULT NULL COMMENT '订单更改或更新的管理员',
  `is_delete` int(10) NOT NULL DEFAULT 0 COMMENT '逻辑删除 0 为正常 1为删除',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `车量ID`(`vehicle_id`) USING BTREE,
  INDEX `用户ID`(`user_id`) USING BTREE,
  INDEX `管理员ID`(`update_admins`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1675156890144333789, 1672172695461588993, 1667153384267341825, '租借', 1, 150.00, NULL, '2023-07-03 22:58:11', '2023-07-03 22:58:11', '2023-07-02 10:51:47', '2023-07-02 22:13:37', NULL, 0);
INSERT INTO `orders` VALUES (1675156890144333826, 1672172695461588993, 1667152734355742721, '租借', 1, 300.00, NULL, '2023-07-03 22:58:11', '2023-07-04 22:58:11', '2023-07-01 22:58:20', '2023-07-02 22:14:02', NULL, 0);
INSERT INTO `orders` VALUES (1675843969576673281, 1672172695461588993, 1667152734355742721, '租借', 1, 300.00, NULL, '2023-07-03 20:28:21', '2023-07-05 20:28:21', '2023-07-03 20:28:33', '2023-07-03 20:29:25', NULL, 0);
INSERT INTO `orders` VALUES (1676054923983798273, 1672172695461588993, 1675844649666289666, '租借', 1, 250.00, NULL, '2023-07-04 00:00:00', '2023-07-05 00:00:00', '2023-07-04 10:26:48', '2023-07-04 10:28:54', NULL, 0);
INSERT INTO `orders` VALUES (1676058264679825409, 1672172695461588993, 1676057984156385281, '租借', 1, 202.00, NULL, '2023-07-04 10:39:51', '2023-07-05 10:39:51', '2023-07-04 10:40:05', '2023-07-04 10:40:20', NULL, 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证信息',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件地址',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `status` int(2) NULL DEFAULT NULL COMMENT ' 状态 0为正常 1为禁用',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除 0为正常 1为删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1672172695461588994 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1671859446455304194, 'majiaqi', '7a08de86c3e765da0dcb6f1af0f7975d', '马嘉祺', '男', '450305197109061039', '16620184455', 'katlyndyrepp@gmail.com', '7c859c1d-32cf-4bca-967f-fe4398661dc0.png', 1, '2023-06-22 20:35:28', '2023-07-03 20:21:19', 0);
INSERT INTO `users` VALUES (1671860839001980929, 'liuyaowen', 'ccad94c0fc5df1abfa045508b6e46a62', '刘耀文', '男', '36220419800815401X', '13076798111', 'itotisgithens@gmail.com', '3a1892c6-7e2d-4a1e-bb74-81a9992e0f24.jpg', 0, '2023-06-22 20:41:00', '2023-06-29 14:53:17', 0);
INSERT INTO `users` VALUES (1671861461499609090, 'songyaxuan', 'cfe5644048162fcc7affac5b5651f3c7', '宋亚轩', '男', '131126199510040011', '19002001230', 'changhuo2007817@163.com', '3a1892c6-7e2d-4a1e-bb74-81a9992e0f24.jpg', 0, '2023-06-22 20:43:29', '2023-07-03 20:22:13', 0);
INSERT INTO `users` VALUES (1671861947904655362, 'mengmeiqi', '9a6ff8590047b0a55805cbbe05145257', '孟美岐', '女', '432901198006296792', '17701999001', 'huangweixu928396@126.com', '3a1892c6-7e2d-4a1e-bb74-81a9992e0f24.jpg', 0, '2023-06-22 20:45:25', '2023-06-29 14:53:17', 0);
INSERT INTO `users` VALUES (1671862312905572354, 'yangchaoyue', 'f11af5478e44d5731c6e7a62d04d0238', '杨超越', '女', '130124199304092726', '17322001752', 'fanhuolei695739@126.com', '3a1892c6-7e2d-4a1e-bb74-81a9992e0f24.jpg', 0, '2023-06-22 20:46:52', '2023-06-29 14:53:17', 0);
INSERT INTO `users` VALUES (1671862925580140546, 'laimeiyun', '8abcb8592bdc7f6d21f6f1ac3747327e', '赖美云', '女', '362222195202204086', '19195525700', 'laimeiyun0864@163.com', '3a1892c6-7e2d-4a1e-bb74-81a9992e0f24.jpg', 0, '2023-06-22 20:49:18', '2023-06-29 14:53:17', 0);
INSERT INTO `users` VALUES (1672172007230828545, 'cxk', '76d3c3a8e17eb0c147da5c7577975009', '蔡虚鲲', '男', '410802197407191510', '17665795164', 'caixukun25@163.com', '3a1892c6-7e2d-4a1e-bb74-81a9992e0f24.jpg', 0, '2023-06-23 17:17:29', '2023-07-03 20:22:10', 0);
INSERT INTO `users` VALUES (1672172695461588993, 'zzj', '40e23dbd7b61dda23db6a68760944d67', '周志鲲', '男', '342501199107230792', '14750000009', '2495387566@qq.com', '3a1892c6-7e2d-4a1e-bb74-81a9992e0f24.jpg', 1, '2023-06-23 17:20:13', '2023-07-04 10:40:33', 0);

SET FOREIGN_KEY_CHECKS = 1;
