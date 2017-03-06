
drop table if EXISTS `t_stock_info`;
CREATE TABLE `t_stock_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stockcode` varchar(20) NOT NULL DEFAULT '' COMMENT '股票代码',
  `stockName` varchar(50) DEFAULT NULL COMMENT '股票名称',
  PRIMARY KEY (`id`),
  KEY `index_name` (`stockcode`)
) ENGINE=InnoDB AUTO_INCREMENT=5926 DEFAULT CHARSET=utf8;

drop table if EXISTS `t_stock_detailed`;
CREATE TABLE `t_stock_detailed` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stockId` varchar(20) NOT NULL DEFAULT '' COMMENT '股票info基础表ID',
  `latestPrice` varchar(50) DEFAULT NULL,
  `upDown` varchar(50) DEFAULT NULL,
  `mainForceBillIncome` varchar(50) DEFAULT NULL,
  `mainForceBillIncomePercentage` varchar(50) DEFAULT NULL,
  `hugeBillIncome` varchar(50) DEFAULT NULL,
  `hugeBillIncomePercentage` varchar(50) DEFAULT NULL,
  `bigBillIncome` varchar(50) DEFAULT NULL,
  `bigBillIncomePercentage` varchar(50) DEFAULT NULL,
  `mediumBillIncome` varchar(50) DEFAULT NULL,
  `mediumBillIncomePercentage` varchar(50) DEFAULT NULL,
  `smallBillIncome` varchar(50) DEFAULT NULL,
  `smallBillIncomePercentage` varchar(50) DEFAULT NULL,
  `importTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_name` (`stockId`,`importTime`)
) ENGINE=InnoDB AUTO_INCREMENT=452866 DEFAULT CHARSET=utf8;
