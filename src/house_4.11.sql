/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : house

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-04-11 15:14:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `addressid` int(9) NOT NULL AUTO_INCREMENT,
  `country` varchar(128) NOT NULL,
  `province` varchar(128) NOT NULL,
  `city` varchar(128) NOT NULL,
  `detailaddress` varchar(512) NOT NULL,
  `postal` varchar(128) DEFAULT NULL,
  `fax` varchar(128) DEFAULT NULL,
  `phonenumber` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('102', '中国', '湖北省', '荆州', '详细地址1', '公司邮编1', null, null);
INSERT INTO `address` VALUES ('103', 'Country', 'Province', 'City', '详细地址', null, null, null);
INSERT INTO `address` VALUES ('104', '中国', '湖北省', '荆州', '账务地址', '账务邮编', null, null);
INSERT INTO `address` VALUES ('105', '中国', '湖北省', '荆州', '详细地址', '邮编', null, null);
INSERT INTO `address` VALUES ('106', '中国', '湖北省', '荆州', '', null, null, null);
INSERT INTO `address` VALUES ('107', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('108', '中国', '湖北省', '武汉', '', '', null, null);
INSERT INTO `address` VALUES ('109', '中国', '湖北省', '武汉', '', '', null, null);
INSERT INTO `address` VALUES ('110', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('112', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('113', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('115', '中国', '湖北省', '荆州', 'sdf', null, null, null);
INSERT INTO `address` VALUES ('116', '中国', '湖北省', '荆州', 'asdfasdfa', null, null, null);
INSERT INTO `address` VALUES ('117', '中国', '湖北省', '荆州', 'asfadsf', null, null, null);
INSERT INTO `address` VALUES ('118', '中国', '湖北省', '荆州', 'adsfasd', 'fsadfasd', null, null);
INSERT INTO `address` VALUES ('119', '中国', '湖北省', '荆州', 'fasdfas', 'dfasdfsadf', null, null);
INSERT INTO `address` VALUES ('120', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('121', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('122', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('123', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('124', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('125', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('126', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('127', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('128', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('129', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('130', '中国', '湖北省', '荆州', '', '', null, null);
INSERT INTO `address` VALUES ('131', '中国', '湖北省', '荆州', 'asfs', null, null, null);
INSERT INTO `address` VALUES ('132', '中国', '湖北省', '荆州', 'fadsfaf', 'sfdsafasdf', null, null);
INSERT INTO `address` VALUES ('133', '中国', '湖北省', '荆州', '', 'sadfasdf', null, null);
INSERT INTO `address` VALUES ('134', '中国', '湖北省', '荆州', 'dsafdasf', 'fsdafasdf', null, null);

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `cityid` int(11) NOT NULL AUTO_INCREMENT,
  `provinceid` int(11) NOT NULL,
  `cityname` varchar(255) NOT NULL,
  `citycode` varchar(255) NOT NULL,
  `cityabbreviation` varchar(255) NOT NULL,
  PRIMARY KEY (`cityid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '1', '荆州', '1', '420000');
INSERT INTO `city` VALUES ('2', '1', '恩施', '2', '430000');
INSERT INTO `city` VALUES ('3', '1', '武汉', '3', '020000');

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `countryid` int(11) NOT NULL AUTO_INCREMENT,
  `countryname` varchar(255) NOT NULL,
  `countryabbreviation` varchar(255) NOT NULL,
  PRIMARY KEY (`countryid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES ('1', '中国', '86');
INSERT INTO `country` VALUES ('2', '美国', '001');
INSERT INTO `country` VALUES ('3', '韩国', '002');
INSERT INTO `country` VALUES ('4', '日本', '003');
INSERT INTO `country` VALUES ('5', '英国', '004');
INSERT INTO `country` VALUES ('6', '德国', '007');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employeeid` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `organizationid` int(9) NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `status` int(9) NOT NULL,
  `personid` int(9) DEFAULT NULL,
  `addressid` int(11) DEFAULT NULL,
  `expiredate` datetime DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('28', 'admin', '123123', 'admin', 'wOgkupwkgZJZlCtxV5Zo/QMuwKYOec3s', '1', 'QAQ', 'wOgkupwkgZJZlCtxV5Zo/QMuwKYOec3s', '1', null, null, '2018-03-27 11:45:32', '2018-03-29 11:48:23');
INSERT INTO `employee` VALUES ('59', 'logins', null, 'logins', 'qTiUT5ow8bHSPSC2mb1uB50MrVKzlqNa', '56', 'qwe', '1Z3mNvf3OQGWm+km5DmKpvKYEG4ZvPYQ', '1', null, null, null, '2018-03-26 11:57:59');
INSERT INTO `employee` VALUES ('60', 'logini', null, 'zhu', 'fWxLjVYSZTXWehnmnbKXUbf7B2ZkXbCl', '57', '', 'QGOsQ5QuT7XDaC0NUwdYzThBGqAemyCa', '1', null, null, null, '2018-03-23 13:58:33');
INSERT INTO `employee` VALUES ('61', 's2login', null, null, 'xyUA/9kr9NTAk7m2ArZWK3+gDRvAJxYy', '58', '', 'qtBZyz/9pe7kfPvyz54kbpYmH44qLaLI', '1', null, null, null, '2018-03-29 17:44:26');
INSERT INTO `employee` VALUES ('62', 'xixi', null, null, 'dfax0yF8CHISTSDDr7uYJf9GGHCIl8Qz', '63', '', 'aqHOdbF1sz0lYqsBxHhYWddmkZozIN2I', '1', null, null, null, '2018-03-30 14:12:46');
INSERT INTO `employee` VALUES ('63', 'xixi', null, null, '1SYbiJgZ27jBiB3n/gbXTj8YYAJ5oH6K', '64', '', 'lO5j/Mc+P87gSeDeNbVNEJOLj91JwQaA', '1', null, null, null, '2018-03-30 14:13:25');
INSERT INTO `employee` VALUES ('64', 'test001', '', 'test', 'b6WLvEXigldas6v2H6TinxZBFwMD65iq', '1', 'dfa', 'BYKiZ3Z4wBS0pKifm6eoGRztuS3ztBn8', '1', null, null, '2018-04-03 16:17:44', '2018-04-03 16:17:44');
INSERT INTO `employee` VALUES ('65', 'test002', '', '', 'y3BBb1c0hB/Fsg/A+ZTbcY6r2t5XswGq', '1', '', 'MOZfGphiX4JInABtr2v78RVjlNqBQfT1', '1', null, null, '2018-04-03 16:21:02', '2018-04-03 16:21:02');
INSERT INTO `employee` VALUES ('66', 'test003', '', ' ', 'bU8EAzI0MI70QG6APl+rXaxH4ePFJSzd', '1', '', 'B1yIwmSOLO6BCM7Ohv/Lbiy37Yg1h2UZ', '1', null, null, '2018-04-03 16:27:49', '2018-04-03 16:27:39');
INSERT INTO `employee` VALUES ('67', 'test005', '', ' ', 'gi/3s/37/s6ZI0ux2QmzmiXOyUZbzJl4', '1', '', 'Pdbp66+cK1UbqhGQe8VmuJT6b35qVUIo', '1', null, null, '2018-04-03 17:01:29', '2018-04-03 17:01:29');
INSERT INTO `employee` VALUES ('68', 'xixix', '999', 'zhulei', 'vyoCgb8r+C+879Jg88gcglrD50JOebpq', '64', 'qwer', 'zcHA9lEtmt/JNm8vp6gP4yPV081FDKcW', '1', '92', null, '2018-04-04 16:35:26', '2018-04-04 16:35:26');
INSERT INTO `employee` VALUES ('69', 'test999', '', '', 'lvsg0LYsfZy9GpNSF1gSAIJgMTOyBhrB', '1', 'asdf', '5xh/lXceJ5/usrsLNKPhGpjPIN8eJASS', '1', null, null, '2018-04-04 19:45:05', '2018-04-04 19:45:05');
INSERT INTO `employee` VALUES ('70', 'suptest1admin', null, 'zhul', 'wpE3DjHQShfx4jt2AxrUSq9BnRpu+FWj', '65', '', 'lwMLl3oNYmUAdWTSC+Bninp8TCJmfHok', '1', null, null, null, '2018-04-09 18:15:55');
INSERT INTO `employee` VALUES ('71', 'ins1', null, 'zhul2', 's1Q9be1o6KVBSmboelUvYerULJP7XI8O', '66', '', '6qYmlL9eTEi4g2Oxkw3W6RlDXX1pFvo+', '1', null, null, null, '2018-04-09 18:57:43');
INSERT INTO `employee` VALUES ('72', 'ins1', null, 'zhu333', '+YTJ31C6muuJKBaEQ2q3G/ccZsbFNO6U', '68', '', '8+XwE4Rpz1sI2mE+jd43rAwuYFPh9vGC', '1', null, null, null, '2018-04-09 18:59:05');
INSERT INTO `employee` VALUES ('73', 'ins1', null, 'xixixi', 'nQxf7pNj6nAeMj1pXl7mkiOCdLfbl1m5', '76', '', 'pNJS4GbuZjME/c3mhYXu5/qiPzk0KTxY', '1', null, '130', null, '2018-04-09 19:08:20');
INSERT INTO `employee` VALUES ('74', 'ameta11', '', 'ffff', 'gpdMn1zf6OeUyXJoI8hWvi2WtmIlAqNl', '1', '123', '41QFWPXRxwLqSDTN4nQGQrJ/eYoZ9eW/', '1', '96', '131', '2018-04-10 15:33:01', '2018-04-10 15:33:01');
INSERT INTO `employee` VALUES ('75', 'xixitest', 'fadsfasd', '', 'DSHO/nRoO2q1+1ceGDIl/DFtoOJ0a7UC', '64', '', '2xbWhNuw2tFvyreAHwy2hyUOAiJNmS0y', '1', null, null, '2018-04-11 14:59:22', '2018-04-11 14:59:22');
INSERT INTO `employee` VALUES ('76', 'suptest', '', '', 'r2z2xLv9HXXFXNat+kodg2thYd787i83', '56', '', '9ayRNLGx34Rx1pHLUMDWvuYuLJS5r8mD', '1', null, null, '2018-04-11 15:00:41', '2018-04-11 15:00:41');
INSERT INTO `employee` VALUES ('77', 'fwsadmin', null, null, 'J94TzCqOzbRzODAGVCI5Qj4Kja//TdRk', '77', 'q', 'i7SSEKMUbrMwUfmRD9hhKkO9is0//V+M', '1', null, null, null, '2018-04-11 15:13:53');

-- ----------------------------
-- Table structure for employeerole
-- ----------------------------
DROP TABLE IF EXISTS `employeerole`;
CREATE TABLE `employeerole` (
  `employeeroleid` int(11) NOT NULL AUTO_INCREMENT,
  `employeeid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`employeeroleid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeerole
-- ----------------------------
INSERT INTO `employeerole` VALUES ('1', '28', '1', '2018-03-23 13:36:04');
INSERT INTO `employeerole` VALUES ('2', '60', '4', '2018-04-09 15:52:19');
INSERT INTO `employeerole` VALUES ('3', '66', '2', '2018-04-09 18:10:42');
INSERT INTO `employeerole` VALUES ('4', '70', '2', '2018-04-09 18:15:55');
INSERT INTO `employeerole` VALUES ('5', '59', '2', '2018-04-09 18:49:36');
INSERT INTO `employeerole` VALUES ('6', '61', '2', '2018-04-09 18:49:36');
INSERT INTO `employeerole` VALUES ('7', '62', '2', '2018-04-09 18:49:36');
INSERT INTO `employeerole` VALUES ('8', '63', '2', '2018-04-09 18:49:37');
INSERT INTO `employeerole` VALUES ('9', '64', '1', '2018-04-09 18:50:36');
INSERT INTO `employeerole` VALUES ('10', '65', '5', '2018-04-09 18:50:36');
INSERT INTO `employeerole` VALUES ('11', '67', '5', '2018-04-09 18:50:36');
INSERT INTO `employeerole` VALUES ('12', '68', '4', '2018-04-09 18:50:36');
INSERT INTO `employeerole` VALUES ('13', '69', '2', '2018-04-09 18:50:36');
INSERT INTO `employeerole` VALUES ('14', '70', '2', '2018-04-09 19:12:58');
INSERT INTO `employeerole` VALUES ('15', '71', '3', '2018-04-09 19:13:07');
INSERT INTO `employeerole` VALUES ('16', '72', '3', '2018-04-09 19:13:19');
INSERT INTO `employeerole` VALUES ('17', '73', '3', '2018-04-09 19:13:26');
INSERT INTO `employeerole` VALUES ('18', '74', '5', '2018-04-10 15:33:02');
INSERT INTO `employeerole` VALUES ('19', '75', '4', '2018-04-11 14:59:22');
INSERT INTO `employeerole` VALUES ('20', '76', '6', '2018-04-11 15:00:41');
INSERT INTO `employeerole` VALUES ('21', '77', '2', '2018-04-11 15:13:53');

-- ----------------------------
-- Table structure for gateway
-- ----------------------------
DROP TABLE IF EXISTS `gateway`;
CREATE TABLE `gateway` (
  `deviceid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `status` int(9) NOT NULL COMMENT '0:离线 1:在线',
  `model` varchar(128) DEFAULT NULL,
  `battery` int(9) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `firmwareversion` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`deviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gateway
-- ----------------------------
INSERT INTO `gateway` VALUES ('fdsfasd', null, '1', 'fsdfsadf', null, '2018-04-04 19:03:48', 'fsdsdafasdf');
INSERT INTO `gateway` VALUES ('fsdfsadfasd', null, '1', 'fsdfsda', null, '2018-04-04 19:02:47', 'asdfasdfasd');
INSERT INTO `gateway` VALUES ('iRemote8005000000001', '8005001', '0', '001-1', '98', '2018-03-23 11:50:05', '0.0.1');
INSERT INTO `gateway` VALUES ('iRemote8005000000002', '8005002', '0', '233', '88', '2018-03-28 14:51:55', '1.0');
INSERT INTO `gateway` VALUES ('iRemote8005000000003', '8005003', '1', 'zhulei', null, '2018-04-08 19:07:42', 'zhulei');
INSERT INTO `gateway` VALUES ('iRemote8005000000004', '8005004', '1', 'fsdfa', null, '2018-04-25 11:28:28', '');
INSERT INTO `gateway` VALUES ('iRemote8005000000005', null, '1', 'fasdf', null, '2018-04-09 11:29:35', 'asdfasdfasd');

-- ----------------------------
-- Table structure for gatewaybinding
-- ----------------------------
DROP TABLE IF EXISTS `gatewaybinding`;
CREATE TABLE `gatewaybinding` (
  `gatewaybindingid` int(11) NOT NULL AUTO_INCREMENT,
  `deviceid` varchar(64) NOT NULL,
  `organizationid` int(9) NOT NULL,
  `bindingtype` int(9) NOT NULL COMMENT '1:绑定服务商 2.绑定安装商',
  `status` int(9) NOT NULL COMMENT '1:正常 2:冻结',
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`gatewaybindingid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gatewaybinding
-- ----------------------------
INSERT INTO `gatewaybinding` VALUES ('1', 'iRemote8005000000001', '56', '1', '1', '2018-03-28 16:59:28');
INSERT INTO `gatewaybinding` VALUES ('2', 'iRemote8005000000002', '1', '1', '1', '2018-03-28 14:50:55');
INSERT INTO `gatewaybinding` VALUES ('3', 'iRemote8005000000003', '1', '1', '1', '2018-03-30 16:57:26');
INSERT INTO `gatewaybinding` VALUES ('4', 'fsdfsadfasd', '1', '0', '1', '2018-04-04 19:02:47');
INSERT INTO `gatewaybinding` VALUES ('5', 'fdsfasd', '1', '0', '1', '2018-04-04 19:03:48');
INSERT INTO `gatewaybinding` VALUES ('6', 'zhulei', '1', '0', '1', '2018-04-08 19:07:42');
INSERT INTO `gatewaybinding` VALUES ('7', 'iRemote8005000000004', '1', '0', '1', '2018-04-09 11:29:35');
INSERT INTO `gatewaybinding` VALUES ('8', 'iRemote8005000000005', '1', '1', '1', '2018-04-09 11:35:33');

-- ----------------------------
-- Table structure for gatewayuser
-- ----------------------------
DROP TABLE IF EXISTS `gatewayuser`;
CREATE TABLE `gatewayuser` (
  `gatewayuserbindingid` int(11) NOT NULL AUTO_INCREMENT,
  `deviceid` varchar(64) NOT NULL,
  `userid` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`gatewayuserbindingid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gatewayuser
-- ----------------------------
INSERT INTO `gatewayuser` VALUES ('1', 'iRemote8005000000001', '1', '2018-03-28 16:19:11');
INSERT INTO `gatewayuser` VALUES ('3', 'iRemote8005000000002', '13', '2018-03-28 17:00:37');
INSERT INTO `gatewayuser` VALUES ('4', 'iRemote8005000000003', '15', '2018-03-30 17:15:21');
INSERT INTO `gatewayuser` VALUES ('5', 'fsdfsadfasd', '16', '2018-04-04 20:21:30');
INSERT INTO `gatewayuser` VALUES ('6', 'iRemote8005000000005', '17', '2018-04-09 11:46:18');

-- ----------------------------
-- Table structure for operationlog
-- ----------------------------
DROP TABLE IF EXISTS `operationlog`;
CREATE TABLE `operationlog` (
  `operationlogid` int(11) NOT NULL AUTO_INCREMENT,
  `employeeroleid` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `requestdata` varchar(2048) NOT NULL,
  `resutl` varchar(255) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `createtime` datetime NOT NULL,
  `result` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`operationlogid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operationlog
-- ----------------------------

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `organizationid` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `citycode` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `parentorgid` int(9) DEFAULT NULL,
  `orgtype` int(9) NOT NULL,
  `status` int(9) NOT NULL COMMENT '1.正常 2.冻结 9.删除',
  `officeaddressid` int(11) DEFAULT NULL,
  `billingaddressid` int(11) DEFAULT NULL,
  `contactid` int(11) DEFAULT NULL,
  `centralstationname` varchar(255) DEFAULT NULL,
  `csaddressid` int(11) DEFAULT NULL,
  `cscontactid` int(11) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`organizationid`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('1', '002', '1', 'ameta', null, '0', '1', '53', '55', '60', 'AMETA CO.', '54', '40', '2018-03-28 19:43:47');
INSERT INTO `organization` VALUES ('56', 's1', '1', 'supplier', '1', '1', '1', '102', '104', '87', '总公司名称', '103', '88', '2018-03-29 18:21:26');
INSERT INTO `organization` VALUES ('57', '123123', '1', 'installerorg', '56', '2', '1', '105', null, null, '', null, null, '2018-03-29 18:21:39');
INSERT INTO `organization` VALUES ('58', 'sss', '1', 'sup', '1', '1', '1', '107', null, null, '', null, null, '2018-03-29 18:21:26');
INSERT INTO `organization` VALUES ('59', 'fadsf', '3', 'supllier', '1', '1', '1', '108', null, null, '', null, null, '2018-03-30 14:01:53');
INSERT INTO `organization` VALUES ('61', 'adfafadfafadf', '1', 'fadfasfdasfa', '1', '1', '1', '110', null, null, '', null, null, '2018-03-30 14:06:51');
INSERT INTO `organization` VALUES ('63', 'dfasfasdfas', '1', 'fadsfsfa', '1', '1', '1', '112', null, null, '', null, null, '2018-03-30 14:12:46');
INSERT INTO `organization` VALUES ('64', 'hahah', '1', 'xixix', '63', '2', '1', '113', null, null, '', null, null, '2018-03-30 14:13:25');
INSERT INTO `organization` VALUES ('65', 'suptest1', '1', 'suptest1', '1', '1', '1', '118', '119', '95', '', null, null, '2018-04-09 18:15:54');
INSERT INTO `organization` VALUES ('66', 'ins1', '1', 'suptest1ins', '65', '2', '1', '120', null, null, '', null, null, '2018-04-09 18:57:43');
INSERT INTO `organization` VALUES ('68', 'ins3', '1', 'suptest1ins3', '65', '2', '1', '122', null, null, '', null, null, '2018-04-09 18:59:05');
INSERT INTO `organization` VALUES ('76', 'ins2', '1', 'suptest1ins2', '65', '2', '1', '130', null, null, '', null, null, '2018-04-09 19:07:56');
INSERT INTO `organization` VALUES ('77', 'fws', '1', 'fws', '1', '1', '1', '132', '134', '97', 'fasdfas', '133', '98', '2018-04-11 15:13:53');

-- ----------------------------
-- Table structure for orgrole
-- ----------------------------
DROP TABLE IF EXISTS `orgrole`;
CREATE TABLE `orgrole` (
  `orgprivilegeid` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `privilegeid` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`orgprivilegeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orgrole
-- ----------------------------

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `personid` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `ssn` varchar(255) DEFAULT NULL,
  `gender` int(9) DEFAULT NULL COMMENT '0:女 1:男 2:LGBT',
  `title` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(128) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `fax` varchar(128) DEFAULT NULL,
  `addressid` int(9) DEFAULT NULL,
  PRIMARY KEY (`personid`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('87', '服务商姓名', null, null, null, null, null, '电话', '邮箱', '传真', null);
INSERT INTO `person` VALUES ('88', '姓名', null, null, null, null, null, '电话', '邮箱', '传真', null);
INSERT INTO `person` VALUES ('89', 'lei zhu', '', null, null, 'lei', 'zhu', 'afsdasf', '', '', '106');
INSERT INTO `person` VALUES ('91', 'adfasd fdsafasdf', 'asdfasddf', '0', null, 'adfasd', 'fdsafasdf', 'asddfas', 'adsfads@q.com', 'fasfd', '115');
INSERT INTO `person` VALUES ('92', 'zhu lei', '13123', '1', 'tile', 'lei', 'zhu', 'fasfsad', '', '', null);
INSERT INTO `person` VALUES ('93', 'sadfsad sadfsadf', 'sadfsadfas', '1', null, 'sadfsad', 'sadfsadf', 'sadfsadf', 'fsadfasd', 'asdfsadf', '116');
INSERT INTO `person` VALUES ('94', 'fsadfsadfsadf', 'safdfsadf', '0', null, 'fsadf', 'sadfsadf', 'fdsafasd', 'fsdafasdf', 'dsafasdfsad', '117');
INSERT INTO `person` VALUES ('95', 'asdfdsa', null, null, null, null, null, 'asdfasda', 'asdfasd', 'asdfasd', null);
INSERT INTO `person` VALUES ('96', 'ffff', 'ff', '0', 'ff', 'ff', 'ff', 'ff', 'ff', 'ff', '131');
INSERT INTO `person` VALUES ('97', 'sadfasd', null, null, null, null, null, 'fasdfasdf', 'asdfasdfda', 'sadfasdf', null);
INSERT INTO `person` VALUES ('98', 'asfasdfda', null, null, null, null, null, 'sfsadfads', 'fasdfasfa', 'fasdfasddfasd', null);

-- ----------------------------
-- Table structure for phonecard
-- ----------------------------
DROP TABLE IF EXISTS `phonecard`;
CREATE TABLE `phonecard` (
  `phonecardid` int(9) NOT NULL AUTO_INCREMENT,
  `serialnumber` varchar(128) NOT NULL,
  `model` varchar(255) NOT NULL,
  `status` int(9) NOT NULL,
  `rateplan` varchar(128) NOT NULL,
  `firmwareversion` varchar(128) DEFAULT NULL,
  `orderingdate` datetime DEFAULT NULL,
  `activationdate` datetime DEFAULT NULL,
  `expiredate` datetime DEFAULT NULL,
  `reactivationdate` datetime DEFAULT NULL,
  `firstprogrammedondate` datetime DEFAULT NULL,
  `lastprogrammedondate` datetime DEFAULT NULL,
  `lastsavedondate` datetime DEFAULT NULL,
  PRIMARY KEY (`phonecardid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phonecard
-- ----------------------------
INSERT INTO `phonecard` VALUES ('1', 'i6kyt', 'ythg', '1', 'trgf', 'thgf', '2018-03-07 00:00:00', '2018-03-21 00:00:00', '2018-03-09 00:00:00', null, '2018-03-14 00:00:00', '2018-03-07 00:00:00', null);
INSERT INTO `phonecard` VALUES ('2', '3424543', 'ythg', '1', 'trgf', 'thgf', '2018-03-07 00:00:00', '2018-03-21 00:00:00', '2018-03-09 00:00:00', null, '2018-03-14 00:00:00', '2018-03-07 00:00:00', null);
INSERT INTO `phonecard` VALUES ('3', '3424gf', 'ythg544', '1', 'trgf', 'thgf', '2018-03-07 00:00:00', '2018-03-21 00:00:00', '2018-03-09 00:00:00', null, '2018-03-14 00:00:00', '2018-03-07 00:00:00', null);
INSERT INTO `phonecard` VALUES ('4', 'f24rtfgfgf', 'ythg544', '2', 'trgf', 'thgf', '2018-03-07 00:00:00', '2018-03-21 00:00:00', '2018-03-09 00:00:00', null, '2018-03-14 00:00:00', '2018-03-07 00:00:00', null);
INSERT INTO `phonecard` VALUES ('5', 'fdsaf', 'dsfasdf', '1', 'sdfasd', 'fasdf', '2018-04-09 11:06:46', '2018-04-09 11:06:50', '2018-04-19 11:06:54', null, '2018-04-18 11:06:58', '2018-05-28 11:07:01', null);
INSERT INTO `phonecard` VALUES ('6', '89302690201003397984\r\n', 'dsfasdf', '1', 'sdfasd', 'fasdf', '2018-04-09 11:06:46', '2018-04-09 11:06:50', '2018-04-19 11:06:54', '2018-04-09 11:18:27', '2018-04-18 11:06:58', '2018-05-28 11:07:01', '2018-04-09 11:18:35');
INSERT INTO `phonecard` VALUES ('7', '89302690201003397976', 'fsdasdf', '1', 'fdsaf', 'fasdfas', '2018-04-09 11:19:31', '2018-04-09 11:19:35', '2018-04-09 11:19:38', '2018-04-18 11:19:51', '2018-04-11 11:19:54', '2018-04-24 11:19:58', '2018-04-23 11:20:02');
INSERT INTO `phonecard` VALUES ('8', '89302690201003397968', 'fdsafa', '2', 'sadfsadfasdf', 'sdafsadf', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for phonecarduser
-- ----------------------------
DROP TABLE IF EXISTS `phonecarduser`;
CREATE TABLE `phonecarduser` (
  `phonecarduserbindingid` int(11) NOT NULL AUTO_INCREMENT,
  `phonecardid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`phonecarduserbindingid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phonecarduser
-- ----------------------------
INSERT INTO `phonecarduser` VALUES ('1', '1', '16', '2018-04-04 20:21:30');
INSERT INTO `phonecarduser` VALUES ('2', '2', '13', '2018-04-09 11:03:01');
INSERT INTO `phonecarduser` VALUES ('3', '3', '14', '2018-04-09 11:03:11');
INSERT INTO `phonecarduser` VALUES ('4', '4', '15', '2018-04-09 11:03:36');
INSERT INTO `phonecarduser` VALUES ('5', '5', '1', '2018-04-19 11:07:12');
INSERT INTO `phonecarduser` VALUES ('6', '8', '17', '2018-04-09 11:46:18');

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `privilegeid` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `parentprivilegeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`privilegeid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('2', 'org/supplierList', 'label.DealerList', '0');
INSERT INTO `privilege` VALUES ('3', 'org/addSupplierPage', 'label.AddDealer', '2');
INSERT INTO `privilege` VALUES ('4', 'org/installerList', 'label.InstallerList', '0');
INSERT INTO `privilege` VALUES ('5', 'org/addInstallerPage', 'label.AddInstaller', '4');
INSERT INTO `privilege` VALUES ('6', 'employee/employeeList', 'label.EmployeeList', '0');
INSERT INTO `privilege` VALUES ('7', 'employee/addEmployeePage', 'label.AddEmployee', '6');
INSERT INTO `privilege` VALUES ('8', 'gateway/gatewayList', 'label.GatewayList', '0');
INSERT INTO `privilege` VALUES ('9', 'gateway/typeGatewayInfo', 'label.InputGatewayInformation', '8');
INSERT INTO `privilege` VALUES ('10', 'gateway/gatewayDetail', 'label.GatewayDetail', null);
INSERT INTO `privilege` VALUES ('11', 'device/deviceList', 'label.DeviceList', '0');
INSERT INTO `privilege` VALUES ('12', 'device/deviceDetail', 'label.DeviceDetail', null);
INSERT INTO `privilege` VALUES ('13', 'user/userList', 'label.UserList', '0');
INSERT INTO `privilege` VALUES ('14', 'user/typeUserInfo', 'label.InputUserInformation', '13');
INSERT INTO `privilege` VALUES ('15', 'phonecard/phonecardList', 'label.SIMCardList', '0');
INSERT INTO `privilege` VALUES ('16', 'phonecard/typePhonecardInfo', 'label.InputSIMCardInformation', '15');
INSERT INTO `privilege` VALUES ('17', 'index', 'label.Index', null);
INSERT INTO `privilege` VALUES ('18', 'changeStatus', 'button:changeStatus', null);

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `provinceid` int(11) NOT NULL AUTO_INCREMENT,
  `countryid` int(11) NOT NULL,
  `provincename` varchar(255) NOT NULL,
  `provinceabbreviation` varchar(255) NOT NULL,
  PRIMARY KEY (`provinceid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('1', '1', '湖北省', '鄂');
INSERT INTO `province` VALUES ('2', '2', '纽约', 'NY');
INSERT INTO `province` VALUES ('3', '1', '湖南省', '湘');
INSERT INTO `province` VALUES ('4', '1', '广东省', '粤');
INSERT INTO `province` VALUES ('5', '1', '四川省', '川');
INSERT INTO `province` VALUES ('6', '1', '北京市', 'X');
INSERT INTO `province` VALUES ('7', '1', '江西省', 'G');
INSERT INTO `province` VALUES ('8', '1', '广西省', '桂');
INSERT INTO `province` VALUES ('9', '1', '云南省', '云');
INSERT INTO `province` VALUES ('10', '2', '哈哈', 'H');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `organizationid` int(9) NOT NULL,
  `status` int(9) NOT NULL COMMENT '1:正常 2.冻结 9.删除',
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ametaadmin', '1', '1', '2018-03-23 13:31:48');
INSERT INTO `role` VALUES ('2', 'supplieradmin', '2', '1', '2018-03-23 13:32:44');
INSERT INTO `role` VALUES ('3', 'installeradmin', '3', '1', '2018-03-23 13:33:00');
INSERT INTO `role` VALUES ('4', 'installer', '4', '1', '2018-03-23 13:33:17');
INSERT INTO `role` VALUES ('5', 'ametaemployee', '1', '1', '2018-04-09 10:26:17');
INSERT INTO `role` VALUES ('6', 'supplieremployee', '2', '1', '2018-04-11 14:43:14');
INSERT INTO `role` VALUES ('7', 'employee', '1', '1', '2018-04-11 14:53:49');

-- ----------------------------
-- Table structure for roleprivilege
-- ----------------------------
DROP TABLE IF EXISTS `roleprivilege`;
CREATE TABLE `roleprivilege` (
  `roleprivilegeid` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `privilegeid` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`roleprivilegeid`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleprivilege
-- ----------------------------
INSERT INTO `roleprivilege` VALUES ('1', '1', '1', '2018-04-08 11:51:38');
INSERT INTO `roleprivilege` VALUES ('2', '1', '2', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('3', '1', '3', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('4', '1', '4', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('5', '1', '5', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('6', '1', '6', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('7', '1', '7', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('8', '1', '8', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('9', '1', '9', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('10', '1', '10', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('11', '1', '11', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('12', '1', '12', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('13', '1', '13', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('14', '1', '14', '2018-04-08 11:59:14');
INSERT INTO `roleprivilege` VALUES ('15', '1', '15', '2018-04-08 11:59:15');
INSERT INTO `roleprivilege` VALUES ('16', '1', '16', '2018-04-08 11:59:15');
INSERT INTO `roleprivilege` VALUES ('17', '1', '17', '2018-04-09 15:22:00');
INSERT INTO `roleprivilege` VALUES ('18', '4', '16', '2018-04-09 15:52:42');
INSERT INTO `roleprivilege` VALUES ('21', '2', '4', '2018-04-09 18:07:32');
INSERT INTO `roleprivilege` VALUES ('22', '2', '5', '2018-04-09 18:07:32');
INSERT INTO `roleprivilege` VALUES ('23', '2', '6', '2018-04-09 18:07:32');
INSERT INTO `roleprivilege` VALUES ('24', '2', '7', '2018-04-09 18:07:32');
INSERT INTO `roleprivilege` VALUES ('25', '2', '8', '2018-04-09 18:07:32');
INSERT INTO `roleprivilege` VALUES ('26', '2', '9', '2018-04-09 18:07:33');
INSERT INTO `roleprivilege` VALUES ('27', '2', '10', '2018-04-09 18:07:33');
INSERT INTO `roleprivilege` VALUES ('28', '2', '11', '2018-04-09 18:07:33');
INSERT INTO `roleprivilege` VALUES ('29', '2', '12', '2018-04-09 18:07:33');
INSERT INTO `roleprivilege` VALUES ('30', '2', '13', '2018-04-09 18:07:33');
INSERT INTO `roleprivilege` VALUES ('31', '2', '14', '2018-04-09 18:07:33');
INSERT INTO `roleprivilege` VALUES ('32', '2', '15', '2018-04-09 18:07:33');
INSERT INTO `roleprivilege` VALUES ('33', '2', '16', '2018-04-09 18:07:33');
INSERT INTO `roleprivilege` VALUES ('34', '3', '6', '2018-04-09 18:08:37');
INSERT INTO `roleprivilege` VALUES ('35', '3', '7', '2018-04-09 18:08:38');
INSERT INTO `roleprivilege` VALUES ('36', '3', '8', '2018-04-09 18:08:38');
INSERT INTO `roleprivilege` VALUES ('37', '3', '9', '2018-04-09 18:08:38');
INSERT INTO `roleprivilege` VALUES ('38', '3', '10', '2018-04-09 18:08:38');
INSERT INTO `roleprivilege` VALUES ('39', '3', '11', '2018-04-09 18:08:38');
INSERT INTO `roleprivilege` VALUES ('40', '3', '12', '2018-04-09 18:08:38');
INSERT INTO `roleprivilege` VALUES ('41', '3', '13', '2018-04-09 18:08:38');
INSERT INTO `roleprivilege` VALUES ('42', '3', '14', '2018-04-09 18:08:38');
INSERT INTO `roleprivilege` VALUES ('43', '3', '15', '2018-04-09 18:08:38');
INSERT INTO `roleprivilege` VALUES ('44', '3', '16', '2018-04-09 18:08:38');
INSERT INTO `roleprivilege` VALUES ('46', '3', '18', '2018-04-10 09:34:47');
INSERT INTO `roleprivilege` VALUES ('47', '2', '18', '2018-04-10 09:34:59');
INSERT INTO `roleprivilege` VALUES ('48', '1', '18', '2018-04-10 09:35:07');
INSERT INTO `roleprivilege` VALUES ('50', '5', '2', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('51', '5', '3', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('52', '5', '4', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('53', '5', '5', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('54', '5', '6', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('55', '5', '7', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('56', '5', '8', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('57', '5', '9', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('58', '5', '10', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('59', '5', '11', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('60', '5', '12', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('61', '5', '13', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('62', '5', '14', '2018-04-11 14:55:46');
INSERT INTO `roleprivilege` VALUES ('63', '5', '15', '2018-04-11 14:55:47');
INSERT INTO `roleprivilege` VALUES ('64', '5', '16', '2018-04-11 14:55:47');
INSERT INTO `roleprivilege` VALUES ('65', '6', '4', '2018-04-11 14:56:55');
INSERT INTO `roleprivilege` VALUES ('66', '6', '5', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('67', '6', '6', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('68', '6', '7', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('69', '6', '8', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('70', '6', '9', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('71', '6', '10', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('72', '6', '11', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('73', '6', '12', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('74', '6', '13', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('75', '6', '14', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('76', '6', '15', '2018-04-11 14:56:56');
INSERT INTO `roleprivilege` VALUES ('77', '6', '16', '2018-04-11 14:56:56');

-- ----------------------------
-- Table structure for systemparameter
-- ----------------------------
DROP TABLE IF EXISTS `systemparameter`;
CREATE TABLE `systemparameter` (
  `systemparameterid` int(11) NOT NULL AUTO_INCREMENT,
  `intvalue` int(11) DEFAULT NULL,
  `paramkey` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `stringvalue` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`systemparameterid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of systemparameter
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(128) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `citycode` varchar(128) NOT NULL,
  `organizationid` int(9) NOT NULL,
  `installerorgid` int(9) NOT NULL,
  `codepostfix` varchar(128) NOT NULL,
  `usercode` varchar(128) NOT NULL,
  `personid` int(9) NOT NULL,
  `createtime` datetime NOT NULL,
  `installerid` int(11) DEFAULT NULL,
  `status` int(9) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '', null, '1', '1', '57', '', '1', '1', '2018-03-29 19:04:21', '59', '2');
INSERT INTO `user` VALUES ('13', 'afsdasf', 'lei zhu', '1', '1', '56', '', 'ametaafsdasf', '89', '2018-03-29 19:04:22', '59', '2');
INSERT INTO `user` VALUES ('15', 'asddfas', 'adfasd fdsafasdf', '1', '1', '1', 'as', 'ametaasddfas', '92', '2018-03-30 17:15:21', '28', '1');
INSERT INTO `user` VALUES ('16', 'sadfsadf', 'sadfsad sadfsadf', '1', '1', '1', 'sadfsadf', 'ametasadfsadf', '93', '2018-04-04 20:21:30', '28', '1');
INSERT INTO `user` VALUES ('17', 'fdsafasd', 'fsadfsadfsadf', '1', '1', '1', 'asf', 'ametafdsafasd', '94', '2018-04-09 11:46:18', '28', '1');

-- ----------------------------
-- Table structure for zwavedevice
-- ----------------------------
DROP TABLE IF EXISTS `zwavedevice`;
CREATE TABLE `zwavedevice` (
  `zwavedeviceid` int(11) NOT NULL,
  `deviceid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `devicetype` varchar(64) NOT NULL,
  `battery` int(9) DEFAULT NULL,
  `status` int(9) DEFAULT NULL,
  `statuses` varchar(128) DEFAULT NULL,
  `warningstatuses` varchar(128) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`zwavedeviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zwavedevice
-- ----------------------------
INSERT INTO `zwavedevice` VALUES ('11541', 'iRemote8005000000001', '门磁', '4', '80', null, null, null, '2018-03-23 17:21:35');
INSERT INTO `zwavedevice` VALUES ('11542', 'iRemote8005000000001', 'hw', '6', '100', '255', null, '[255]', '2018-03-23 17:21:35');
INSERT INTO `zwavedevice` VALUES ('11544', 'iRemote8005000000001', '漏水', '2', '100', null, null, '[255]', '2018-03-23 17:21:36');
