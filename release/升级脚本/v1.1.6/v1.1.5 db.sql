#解决移动查房同步时自增列冲突，改为bigint类型
ALTER TABLE `nutrientadvicesummary`
MODIFY COLUMN `NutrientAdviceSummary_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ,
MODIFY COLUMN `PatientHospitalize_DBKey`  bigint(11) NULL DEFAULT NULL COMMENT 'DBKey' AFTER `NutrientAdviceSummary_DBKey`,
MODIFY COLUMN `NutrientAdviceSummaryNo`  bigint(32) NULL DEFAULT NULL COMMENT 'DBKey' AFTER `PatientHospitalize_DBKey`;
ALTER TABLE `nutrientadvice`
MODIFY COLUMN `NutrientAdvice_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ,
MODIFY COLUMN `NutrientAdviceSummary_DBKey`  bigint(11) NULL DEFAULT NULL COMMENT 'DBKey' AFTER `NutrientAdvice_DBKey`;
ALTER TABLE `nutrientadvicedetail`
MODIFY COLUMN `NutrientAdviceDetail_DBKEY`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ,
MODIFY COLUMN `NutrientAdvice_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' AFTER `NutrientAdviceDetail_DBKEY`;
