#����ƶ��鷿ͬ��ʱ�����г�ͻ����Ϊbigint����
ALTER TABLE `chargingadvicedetail`
MODIFY COLUMN `NutrientAdviceDetail_DBKEY`  bigint(11) NOT NULL COMMENT 'DBKey' FIRST;
