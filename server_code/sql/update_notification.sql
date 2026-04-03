-- 数据库更新脚本
-- 用于添加通知状态字段和创建通知发送记录表
-- 执行时间: 2025-03-27

-- =============================================
-- 1. 为就诊通知表添加通知状态字段
-- =============================================
ALTER TABLE `jiuzhentongzhi` 
ADD COLUMN `tongzhizhuangtai` INT(11) DEFAULT 0 COMMENT '通知状态：0-待发送，1-发送成功，2-发送失败' 
AFTER `tongzhibeizhu`;

-- 更新现有数据，设置默认状态为发送成功（假设历史数据都已发送）
UPDATE `jiuzhentongzhi` SET `tongzhizhuangtai` = 1 WHERE `tongzhizhuangtai` IS NULL;

-- =============================================
-- 2. 创建通知发送记录表
-- =============================================
DROP TABLE IF EXISTS `tongzhisongjilu`;

CREATE TABLE `tongzhisongjilu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tongzhiid` bigint(20) DEFAULT NULL COMMENT '关联通知id',
  `tongzhibianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '通知编号',
  `zhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '接收人账号',
  `shouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `fasongshijian` datetime DEFAULT NULL COMMENT '发送时间',
  `fasongzhuangtai` int(11) DEFAULT 0 COMMENT '发送状态：0-待发送，1-发送成功，2-发送失败',
  `shibaiyuanyin` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '失败原因',
  `chongshicishu` int(11) DEFAULT 0 COMMENT '重试次数',
  `zuihouchongshishijian` datetime DEFAULT NULL COMMENT '最后重试时间',
  `tongzhineirong` text COLLATE utf8mb4_unicode_ci COMMENT '通知内容',
  PRIMARY KEY (`id`),
  KEY `idx_tongzhiid` (`tongzhiid`),
  KEY `idx_zhanghao` (`zhanghao`),
  KEY `idx_fasongzhuangtai` (`fasongzhuangtai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='通知发送记录';

-- =============================================
-- 3. 创建索引以提高查询性能
-- =============================================
-- 为就诊通知表的状态字段创建索引
ALTER TABLE `jiuzhentongzhi` ADD INDEX `idx_tongzhizhuangtai` (`tongzhizhuangtai`);

-- =============================================
-- 4. 可选：将现有就诊通知数据迁移到发送记录表
-- =============================================
-- INSERT INTO `tongzhisongjilu` (`tongzhiid`, `tongzhibianhao`, `zhanghao`, `shouji`, 
--     `fasongshijian`, `fasongzhuangtai`, `chongshicishu`, `tongzhineirong`)
-- SELECT `id`, `tongzhibianhao`, `zhanghao`, `shouji`, `tongzhishijian`, 
--     `tongzhizhuangtai`, 0, `tongzhibeizhu` 
-- FROM `jiuzhentongzhi`;

-- =============================================
-- 完成
-- =============================================
