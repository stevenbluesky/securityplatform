/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : house

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-03-23 18:54:17
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
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('83', '中国', '湖北省', '恩施', '湖北民族', '00001', null, null);
INSERT INTO `address` VALUES ('84', '中国', '湖北省', '荆州', '', '00003', null, null);
INSERT INTO `address` VALUES ('85', '中国', '湖北省', '武汉', '账务地址', '00002', null, null);
INSERT INTO `address` VALUES ('87', '中国', '湖北省', '荆州', '23123', null, null, null);
INSERT INTO `address` VALUES ('88', '中国', '湖北省', '荆州', '详细地址', null, null, null);

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
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('28', 'admin', 'adsfs', 'zhu lei', 'CE7375F74003DA76FEED139DAEF052D6', '44', 'q', 'B3181C56CA06AE2C25DD5C456607BBB8', '1', '56', '82', '2018-03-21 20:25:34', '2018-03-23 18:52:55');
INSERT INTO `employee` VALUES ('29', 'login', null, null, 'CE7375F74003DA76FEED139DAEF052D6', '44', 'q', '6E445E61E07FB97A6EAB2E577F1F62CE', '1', null, null, null, '2018-03-22 17:16:16');

-- ----------------------------
-- Table structure for employeerole
-- ----------------------------
DROP TABLE IF EXISTS `employeerole`;
CREATE TABLE `employeerole` (
  `employeeroleid` int(11) NOT NULL AUTO_INCREMENT,
  `employeeid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`employeeroleid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeerole
-- ----------------------------
INSERT INTO `employeerole` VALUES ('1', '28', '1', '2018-03-23 13:36:04');

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
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `firmwareversion` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`deviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gateway
-- ----------------------------
INSERT INTO `gateway` VALUES ('iRemote8005000000001', '8005001', '1', '001-1', '98', '2018-03-23 11:50:05', '0.0.1');

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
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`gatewaybindingid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gatewaybinding
-- ----------------------------
INSERT INTO `gatewaybinding` VALUES ('1', 'iRemote8005000000001', '1', '1', '1', '2018-03-23 11:51:17');
INSERT INTO `gatewaybinding` VALUES ('2', 'iRemote8005000000001', '2', '1', '1', '2018-03-23 17:31:12');
INSERT INTO `gatewaybinding` VALUES ('3', 'iRemote8005000000001', '44', '1', '1', '2018-03-23 17:31:35');

-- ----------------------------
-- Table structure for gatewayuser
-- ----------------------------
DROP TABLE IF EXISTS `gatewayuser`;
CREATE TABLE `gatewayuser` (
  `gatewayuserbindingid` int(11) NOT NULL AUTO_INCREMENT,
  `deviceid` varchar(64) NOT NULL,
  `userid` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`gatewayuserbindingid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gatewayuser
-- ----------------------------
INSERT INTO `gatewayuser` VALUES ('1', 'iRemote8005000000001', '10', '2018-03-23 11:51:41');

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
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`organizationid`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('1', '001', null, 'ameta', null, '0', '1', '53', '55', '39', 'AMETA CO.', '54', '40', '2018-03-20 09:37:08');
INSERT INTO `organization` VALUES ('2', '002', null, 'supplier', '1', '1', '1', '83', '85', null, 'supplier', '84', '58', '2018-03-23 18:51:15');
INSERT INTO `organization` VALUES ('44', '003', null, 'ameta2', '2', '2', '1', '83', '85', null, 'AMETA2', '84', '58', '2018-03-23 18:51:13');

-- ----------------------------
-- Table structure for orgrole
-- ----------------------------
DROP TABLE IF EXISTS `orgrole`;
CREATE TABLE `orgrole` (
  `orgprivilegeid` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `privilegeid` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('57', '朱磊', null, null, null, null, null, '180', '759@q.com', '1801', null);
INSERT INTO `person` VALUES ('58', '总公司', null, null, null, null, null, '1801', '8@q.com', '1802', null);
INSERT INTO `person` VALUES ('60', 'zhul lei', '2313', '1', null, 'zhul', 'lei', '1232', '123123', '2321', '87');
INSERT INTO `person` VALUES ('61', '姓 名', '421024199', '1', null, '姓', '名', '18071272', '871329@qq.com', '12312313', '88');

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
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`phonecarduserbindingid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phonecarduser
-- ----------------------------
INSERT INTO `phonecarduser` VALUES ('4', '1', '10', '2018-03-22 20:06:38');
INSERT INTO `phonecarduser` VALUES ('5', '1', '11', '2018-03-23 17:25:40');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

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

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `organizationid` int(9) NOT NULL,
  `status` int(9) NOT NULL COMMENT '1:正常 2.冻结 9.删除',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ameta', '1', '1', '2018-03-23 13:31:48');
INSERT INTO `role` VALUES ('2', '服务商', '2', '1', '2018-03-23 13:32:44');
INSERT INTO `role` VALUES ('3', '安装商', '3', '1', '2018-03-23 13:33:00');
INSERT INTO `role` VALUES ('4', '安装员', '4', '1', '2018-03-23 13:33:17');

-- ----------------------------
-- Table structure for roleprivilege
-- ----------------------------
DROP TABLE IF EXISTS `roleprivilege`;
CREATE TABLE `roleprivilege` (
  `roleprivilegeid` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `privilegeid` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`roleprivilegeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleprivilege
-- ----------------------------

-- ----------------------------
-- Table structure for systemparameter
-- ----------------------------
DROP TABLE IF EXISTS `systemparameter`;
CREATE TABLE `systemparameter` (
  `systemparameterid` int(11) NOT NULL,
  `intvalue` int(11) DEFAULT NULL,
  `paramkey` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `stringvalue` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`systemparameterid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of systemparameter
-- ----------------------------
INSERT INTO `systemparameter` VALUES ('0', '679504', 'lastSynId', null);

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
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `installerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10', '1232', 'zhul lei', '1', '1', '1', '12312312', 'ameta1232', '60', '2018-03-22 20:06:38', '28');
INSERT INTO `user` VALUES ('11', '18071272', '姓 名', '1', '44', '2', 'JWZH', 'ameta18071272', '61', '2018-03-23 17:25:39', '28');

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
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`zwavedeviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zwavedevice
-- ----------------------------
INSERT INTO `zwavedevice` VALUES ('11541', 'iRemote8005000000001', '门磁', '4', '80', null, null, null, '2018-03-23 17:21:35');
INSERT INTO `zwavedevice` VALUES ('11542', 'iRemote8005000000001', 'hw', '6', '100', '255', null, '[255]', '2018-03-23 17:21:35');
INSERT INTO `zwavedevice` VALUES ('11544', 'iRemote8005000000001', '漏水', '2', '100', null, null, '[255]', '2018-03-23 17:21:36');
