DROP TABLE IF EXISTS `Task`;
CREATE TABLE `Task` (
  `taskId` varchar(50) NOT NULL DEFAULT '' COMMENT 'ID',
  `taskName` varchar(500) NOT NULL DEFAULT '' COMMENT '名称',
  `groupId` varchar(500) NOT NULL DEFAULT '' COMMENT '组ID',  
  `startExpress` varchar(500) NULL DEFAULT '' COMMENT '开始时间表达式',
  `stopExpress` varchar(500) NULL DEFAULT '' COMMENT '停止时间表达式',
  `startCommand` varchar(500) NULL DEFAULT '' COMMENT '开始命令',
  `stopCommand` varchar(500) NULL DEFAULT '' COMMENT '结束命令',  
  `taskState` varchar(500) NULL DEFAULT '' COMMENT '任务状态',
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务调度器';



INSERT INTO `Task` VALUES ('1001','info','101','0 0 12 * * ? "','0 0 13 * * ? "','start','stop','0');
