#科室设置为显示
update department set IsActive = 1;

#查房记录
ALTER TABLE `courserecord`
ADD COLUMN `Height`  float NULL COMMENT '身高' AFTER `WalkSpeed`,
ADD COLUMN `Weight`  float NULL COMMENT '体重' AFTER `Height`,
ADD COLUMN `NauseaRemark`  varchar(255) NULL COMMENT '恶心备注' AFTER `Weight`,
ADD COLUMN `DiarrheaRemark`  varchar(255) NULL COMMENT '腹泻备注' AFTER `NauseaRemark`,
ADD COLUMN `ConstipationRemark`  varchar(255) NULL COMMENT '便秘备注' AFTER `DiarrheaRemark`,
ADD COLUMN `VomitRemark`  varchar(255) NULL COMMENT '呕吐备注' AFTER `ConstipationRemark`,
ADD COLUMN `AbdominalDistensionRemark`  varchar(255) NULL COMMENT '腹胀备注' AFTER `VomitRemark`,
ADD COLUMN `AbdominalPainRemark`  varchar(255) NULL COMMENT '腹痛备注' AFTER `AbdominalDistensionRemark`;

#解决移动查房同步时自增列冲突，改为bigint类型
ALTER TABLE `patienthospitalizebasicinfo` MODIFY COLUMN `PatientHospitalize_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `patienthospitalizebasicinfo` MODIFY COLUMN `PATIENT_DBKEY`  bigint(11) NOT NULL COMMENT 'DBKey';
ALTER TABLE `patientbasicinfo` MODIFY COLUMN `PATIENT_DBKEY`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `courserecord` MODIFY COLUMN `CourseRecord_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `courserecord` MODIFY COLUMN `PatientHospitalize_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `patientquestionnaire` MODIFY COLUMN `PatientQuestionnaire_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `patientquestionnaire` MODIFY COLUMN `PatientHospitalize_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `patientquestion` MODIFY COLUMN `PatientQuestion_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `patientquestion` MODIFY COLUMN `PatientQuestionnaire_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `patientquestionnaireresult` MODIFY COLUMN `PatientQuestionnaireResult_DBKey`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT '患者调查问卷结果_DBKey' FIRST ;
ALTER TABLE `patientquestionnaireresult` MODIFY COLUMN `PatientQuestion_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `mealrecord` MODIFY COLUMN `MealRecord_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `mealrecord` MODIFY COLUMN `PatientHospitalize_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `relationofdietaryfood` MODIFY COLUMN `RelationOfDietaryFood_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `relationofdietaryfood` MODIFY COLUMN `MealRecord_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `laboratoryindex` MODIFY COLUMN `LaboratoryIndex_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `laboratoryindex` MODIFY COLUMN `PatientHospitalize_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `testresult` MODIFY COLUMN `TestResult_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `testresult` MODIFY COLUMN `LaboratoryIndex_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `testresult` MODIFY COLUMN `TestResultNo`  bigint(11) NOT NULL COMMENT 'DBKey' ;
