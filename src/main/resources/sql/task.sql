-- 任务调度器
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `taskId` varchar(50) NOT NULL DEFAULT '' COMMENT 'ID',
  `taskName` varchar(500) NULL DEFAULT '' COMMENT '名称',
  `groupId` varchar(500) NULL DEFAULT '' COMMENT '组ID', 
  `groupName` varchar(500) NULL DEFAULT '' COMMENT '组名称', 
  `startCommand` varchar(500) NULL DEFAULT '' COMMENT '开始命令',
  `stopCommand` varchar(500) NULL DEFAULT '' COMMENT '结束命令',  
  `taskState` varchar(500) NULL DEFAULT '' COMMENT '任务状态',  
  `createTime` datetime NULL COMMENT '创建时间',
  `updateTime` datetime NULL COMMENT '更新时间',
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务调度器';


-- 任务组
DROP TABLE IF EXISTS `taskgroup`;
CREATE TABLE `taskgroup` (
  `groupId` varchar(50) NOT NULL DEFAULT '' COMMENT 'ID',
  `groupName` varchar(500) NULL DEFAULT '' COMMENT '名称',
  `parentId` varchar(500) NULL DEFAULT '' COMMENT '父节点ID', 
  `ifLeaf` varchar(500) NULL DEFAULT '' COMMENT '是否是叶子节点：0.否;1.是', 
  `createTime` datetime NULL COMMENT '创建时间',
  `updateTime` datetime NULL COMMENT '更新时间',
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务组';


-- 规则表
DROP TABLE IF EXISTS `taskrule`;
CREATE TABLE `taskrule` (
  `ruleId` varchar(50) NOT NULL DEFAULT '' COMMENT 'ID',
  `taskId` varchar(50) NULL DEFAULT '' COMMENT '爬虫 ID', 
  `express` blob,
  PRIMARY KEY (`ruleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则表';