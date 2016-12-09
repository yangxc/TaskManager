-- 任务调度器
DROP TABLE IF EXISTS `Task`;
CREATE TABLE `Task` (
  `taskId` varchar(50) NOT NULL DEFAULT '' COMMENT 'ID',
  `taskName` varchar(500) NOT NULL DEFAULT '' COMMENT '名称',
  `groupId` varchar(500) NOT NULL DEFAULT '' COMMENT '组ID', 
  `groupName` varchar(500) NOT NULL DEFAULT '' COMMENT '组名称', 
  `startExpress` varchar(500) NULL DEFAULT '' COMMENT '开始时间表达式',
  `stopExpress` varchar(500) NULL DEFAULT '' COMMENT '停止时间表达式',
  `startCommand` varchar(500) NULL DEFAULT '' COMMENT '开始命令',
  `stopCommand` varchar(500) NULL DEFAULT '' COMMENT '结束命令',  
  `taskState` varchar(500) NULL DEFAULT '' COMMENT '任务状态',  
  `createTime` Date NULL COMMENT '创建时间',
  `updateTime` Date NULL COMMENT '更新时间',
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务调度器';