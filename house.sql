/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : house

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-03-19 20:31:57
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('26', '1', '1', '1', '公司详细地址', '434109', null, null);
INSERT INTO `address` VALUES ('27', '1', '1', '3', '', '0000020', null, null);
INSERT INTO `address` VALUES ('28', '1', '1', '2', '账务详细地址fff', '公司账务邮编', null, null);
INSERT INTO `address` VALUES ('29', '1', '1', '1', 'address', '901233', null, null);
INSERT INTO `address` VALUES ('30', '1', '1', '3', '', '', null, null);
INSERT INTO `address` VALUES ('31', '1', '1', '2', 'detailaddress', '909123', null, null);
INSERT INTO `address` VALUES ('32', '1', '1', '1', '详细', '邮编', null, null);
INSERT INTO `address` VALUES ('33', '1', '1', '3', '', 'fdsafd', null, null);
INSERT INTO `address` VALUES ('34', '1', '1', '3', '详细', '邮编', null, null);
INSERT INTO `address` VALUES ('41', '1', '1', '1', '详细', '邮编', null, null);
INSERT INTO `address` VALUES ('42', '1', '1', '3', '', 'fdsafd', null, null);
INSERT INTO `address` VALUES ('43', '1', '1', '3', '详细', '邮编', null, null);
INSERT INTO `address` VALUES ('47', '1', '1', '3', '', '', null, null);
INSERT INTO `address` VALUES ('48', '1', '1', '1', '', '', null, null);
INSERT INTO `address` VALUES ('49', '1', '1', '2', '', '', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('2', 'zhulei', null, 'name', 'zhuleif1', '9', '密码问题f', '密码答案f', '1', null, null, null, '2018-03-19 11:39:38');
INSERT INTO `employee` VALUES ('3', 'zhulei', null, 'name1', 'zhuleif1x', '11', '密码问题f', '密码答案f', '1', null, null, null, '2018-03-19 11:47:27');
INSERT INTO `employee` VALUES ('4', 'zhuleif', null, 'name2', 'zhuleif1x', '13', '密码问题f', '密码答案f', '1', null, null, null, '2018-03-19 11:49:16');
INSERT INTO `employee` VALUES ('5', 'ameta', null, 'name3', 'psw', '14', 'q1', 'a1', '1', null, null, null, '2018-03-19 11:58:28');
INSERT INTO `employee` VALUES ('6', 'zhulei', null, 'name4', 'zhulei', '17', 'q2', 'a2', '1', null, null, null, '2018-03-19 15:48:29');
INSERT INTO `employee` VALUES ('7', 'zhulei', null, 'name5', 'zhuleif', '20', 'q2', 'a2', '1', null, null, null, '2018-03-19 15:49:12');
INSERT INTO `employee` VALUES ('8', 'zhulll', null, 'name7', 'zfff', '21', '', '', '1', null, null, null, '2018-03-19 16:57:45');
INSERT INTO `employee` VALUES ('9', 'asdff', '', ' name8', 'adsf', '15', '', '', '1', '33', null, '2018-03-19 19:40:29', '2018-03-19 19:40:29');
INSERT INTO `employee` VALUES ('10', 'asdff', '', ' name9', 'asdf', '15', '', '', '1', '34', null, '2018-03-19 19:54:14', '2018-03-19 19:54:14');
INSERT INTO `employee` VALUES ('11', 'asdffff', 'xx', ' name10', 'asdf', '15', '', '', '1', '35', null, '2018-03-19 19:56:51', '2018-03-19 19:56:51');
INSERT INTO `employee` VALUES ('12', 'zhulei3', '888', 'zhulei', 'zhule', '17', '', '', '1', '36', null, '2018-03-19 20:27:37', '2018-03-19 20:27:37');
INSERT INTO `employee` VALUES ('13', 'zzzzzz', '', 'zhu lei', '123123', '11', '', '', '1', '37', null, '2018-03-19 20:28:57', '2018-03-19 20:28:57');
INSERT INTO `employee` VALUES ('14', 'zzzzzz', '', 'zhu lei', '123123f', '11', '', '', '1', '38', null, '2018-03-19 20:29:51', '2018-03-19 20:29:51');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeerole
-- ----------------------------

-- ----------------------------
-- Table structure for gateway
-- ----------------------------
DROP TABLE IF EXISTS `gateway`;
CREATE TABLE `gateway` (
  `deviceid` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `status` int(9) NOT NULL COMMENT '0:离线 1:在线',
  `model` varchar(128) DEFAULT NULL,
  `firmvareversion` varchar(128) DEFAULT NULL,
  `battery` int(9) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `firmwareversion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gateway
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gatewaybinding
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gatewayuser
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('11', '公司代码2', null, '公司名称13', '11', '1', '1', '20', '22', '17', '总公司名称', '21', '18', '2018-03-19 11:47:27');
INSERT INTO `organization` VALUES ('13', '公司代码3', null, '公司名称00', '11', '1', '1', '26', '28', '21', '总公司名称', '27', '22', '2018-03-19 11:49:16');
INSERT INTO `organization` VALUES ('14', '000001', null, 'ameta', '11', '0', '1', '29', '31', '23', 'AmetaOne', '30', '24', '2018-03-19 11:58:28');
INSERT INTO `organization` VALUES ('15', '123123', null, '1231', '13', '2', '1', null, null, null, null, null, null, '2018-03-19 14:54:13');
INSERT INTO `organization` VALUES ('16', '3432', null, '132', '14', '2', '1', null, null, null, null, null, null, '2018-03-19 14:54:35');
INSERT INTO `organization` VALUES ('17', '代码132', null, '132安装商', null, '2', '1', '32', '34', '25', 'fuwus', '33', '26', '2018-03-19 15:48:29');
INSERT INTO `organization` VALUES ('20', '代码1322', null, '132安装商f', null, '2', '1', '41', '43', '31', 'fuwus', '42', '32', '2018-03-19 15:49:12');
INSERT INTO `organization` VALUES ('21', 'lesss', null, 'less', null, '1', '1', '47', '49', null, '', '48', null, '2018-03-19 16:57:45');

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('13', 'zhul', null, null, null, null, null, '18071723', '759366@qq.com', '1803975', null);
INSERT INTO `person` VALUES ('14', '服务商姓名', null, null, null, null, null, '服务商电话', '服务商邮箱', '服务商传真', null);
INSERT INTO `person` VALUES ('17', 'zhul', null, null, null, null, null, '18071723', '759366@qq.com', '1803975', null);
INSERT INTO `person` VALUES ('18', '服务商姓名', null, null, null, null, null, '服务商电话', '服务商邮箱', '服务商传真', null);
INSERT INTO `person` VALUES ('21', 'zhul', null, null, null, null, null, '18071723', '759366@qq.com', '1803975', null);
INSERT INTO `person` VALUES ('22', 'z服务商姓名', null, null, null, null, null, 'z服务商电话', 'z服务商邮箱', 'z服务商传真', null);
INSERT INTO `person` VALUES ('23', 'z', null, null, null, null, null, '1238034234', '7@q.com', '1398734798', null);
INSERT INTO `person` VALUES ('24', 'az', null, null, null, null, null, '12347', 'z@q.com', '14987324', null);
INSERT INTO `person` VALUES ('25', 'zzz', null, null, null, null, null, 'zzz', '2@q.c', 'zzz', null);
INSERT INTO `person` VALUES ('26', 'zhul', null, null, null, null, null, 'zhuk', '8@q.com', 'zhul', null);
INSERT INTO `person` VALUES ('31', 'zzz', null, null, null, null, null, 'zzz', '2@q.c', 'zzz', null);
INSERT INTO `person` VALUES ('32', 'zhul', null, null, null, null, null, 'zhuk', '8@q.com', 'zhul', null);
INSERT INTO `person` VALUES ('33', ' ', '', '0', '', '', '', '', '', '', null);
INSERT INTO `person` VALUES ('34', ' ', '', '0', '', '', '', '', '', '', null);
INSERT INTO `person` VALUES ('35', ' ', '', '0', '', '', '', '', '', '', null);
INSERT INTO `person` VALUES ('36', ' ', '', '0', '', '', '', '', '', '', null);
INSERT INTO `person` VALUES ('37', 'zhu lei', '', '1', '', 'lei', 'zhu', '', '', '', null);
INSERT INTO `person` VALUES ('38', 'zhu lei', '', '1', '', 'lei', 'zhu', '', '', '', null);

-- ----------------------------
-- Table structure for phonecard
-- ----------------------------
DROP TABLE IF EXISTS `phonecard`;
CREATE TABLE `phonecard` (
  `phonecardid` int(9) NOT NULL AUTO_INCREMENT,
  `serilnumber` varchar(128) NOT NULL,
  `simsn` varchar(128) NOT NULL,
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
  `serialnumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`phonecardid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phonecard
-- ----------------------------

-- ----------------------------
-- Table structure for phonecarduser
-- ----------------------------
DROP TABLE IF EXISTS `phonecarduser`;
CREATE TABLE `phonecarduser` (
  `phonecarduserbindingid` int(11) NOT NULL AUTO_INCREMENT,
  `phonecardid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  `phonecarduserbingingid` int(11) NOT NULL,
  PRIMARY KEY (`phonecarduserbindingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phonecarduser
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilege
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('1', '1', '湖北省', '鄂');
INSERT INTO `province` VALUES ('2', '2', '纽约', 'NY');
INSERT INTO `province` VALUES ('3', '1', '湖南省', '湘');
INSERT INTO `province` VALUES ('4', '1', '广东省', '粤');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleprivilege
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
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

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
