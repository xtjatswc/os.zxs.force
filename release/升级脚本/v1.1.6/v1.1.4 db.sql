#��������Ϊ��ʾ
update department set IsActive = 1;

#�鷿��¼
ALTER TABLE `courserecord`
ADD COLUMN `Height`  float NULL COMMENT '���' AFTER `WalkSpeed`,
ADD COLUMN `Weight`  float NULL COMMENT '����' AFTER `Height`,
ADD COLUMN `NauseaRemark`  varchar(255) NULL COMMENT '���ı�ע' AFTER `Weight`,
ADD COLUMN `DiarrheaRemark`  varchar(255) NULL COMMENT '��к��ע' AFTER `NauseaRemark`,
ADD COLUMN `ConstipationRemark`  varchar(255) NULL COMMENT '���ر�ע' AFTER `DiarrheaRemark`,
ADD COLUMN `VomitRemark`  varchar(255) NULL COMMENT 'Ż�±�ע' AFTER `ConstipationRemark`,
ADD COLUMN `AbdominalDistensionRemark`  varchar(255) NULL COMMENT '���ͱ�ע' AFTER `VomitRemark`,
ADD COLUMN `AbdominalPainRemark`  varchar(255) NULL COMMENT '��ʹ��ע' AFTER `AbdominalDistensionRemark`;

#����ƶ��鷿ͬ��ʱ�����г�ͻ����Ϊbigint����
ALTER TABLE `patienthospitalizebasicinfo` MODIFY COLUMN `PatientHospitalize_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `patienthospitalizebasicinfo` MODIFY COLUMN `PATIENT_DBKEY`  bigint(11) NOT NULL COMMENT 'DBKey';
ALTER TABLE `patientbasicinfo` MODIFY COLUMN `PATIENT_DBKEY`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `courserecord` MODIFY COLUMN `CourseRecord_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `courserecord` MODIFY COLUMN `PatientHospitalize_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `patientquestionnaire` MODIFY COLUMN `PatientQuestionnaire_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `patientquestionnaire` MODIFY COLUMN `PatientHospitalize_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `patientquestion` MODIFY COLUMN `PatientQuestion_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST ;
ALTER TABLE `patientquestion` MODIFY COLUMN `PatientQuestionnaire_DBKey`  bigint(11) NOT NULL COMMENT 'DBKey' ;
ALTER TABLE `patientquestionnaireresult` MODIFY COLUMN `PatientQuestionnaireResult_DBKey`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT '���ߵ����ʾ���_DBKey' FIRST ;
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
