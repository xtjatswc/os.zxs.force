#解决移动查房同步时自增列冲突，改为bigint类型
ALTER TABLE `chargingadvicedetail`
MODIFY COLUMN `NutrientAdviceDetail_DBKEY`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST;
