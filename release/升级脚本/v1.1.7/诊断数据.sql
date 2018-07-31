--
-- ��SQLiteStudio v3.1.1 �������ļ� �ܶ� 7�� 31 10:34:24 2018
--
-- �ı����룺System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- ��diagnosis
CREATE TABLE "diagnosis" (`DiagnosisCode` VARCHAR , `DiagnosisName` VARCHAR , `InputCode` VARCHAR , `ID` INTEGER , PRIMARY KEY (`ID`) );
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('1', '���ʰ�', 'bya', 1);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('2', '���ʰ�', 'xya', 2);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('3', '���ʰ�', 'kya', 3);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('4', '���ٰ�', 'sxa', 4);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('5', '�఩', 'sa', 5);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('6', 'ʳ�ܰ�', 'sga', 6);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('7', '�ΰ�', 'fa', 7);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('8', '���ٰ�', 'xxa', 8);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('9', 'θ��', 'wa', 9);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('10', '��ֱ����', 'jzca', 10);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('11', '���ٰ�', 'rxa', 11);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('12', '�ܰ���', 'lbl', 12);

-- ������diagnosis_InputCode_idx
CREATE INDEX `diagnosis_InputCode_idx` ON "diagnosis" ( `InputCode` );

-- ������diagnosis_DiagnosisName_idx
CREATE INDEX `diagnosis_DiagnosisName_idx` ON "diagnosis" ( `DiagnosisName` );

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
