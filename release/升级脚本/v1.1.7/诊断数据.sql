--
-- 由SQLiteStudio v3.1.1 产生的文件 周二 7月 31 10:34:24 2018
--
-- 文本编码：System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- 表：diagnosis
CREATE TABLE "diagnosis" (`DiagnosisCode` VARCHAR , `DiagnosisName` VARCHAR , `InputCode` VARCHAR , `ID` INTEGER , PRIMARY KEY (`ID`) );
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('1', '鼻咽癌', 'bya', 1);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('2', '下咽癌', 'xya', 2);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('3', '口咽癌', 'kya', 3);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('4', '腮腺癌', 'sxa', 4);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('5', '舌癌', 'sa', 5);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('6', '食管癌', 'sga', 6);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('7', '肺癌', 'fa', 7);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('8', '胸腺癌', 'xxa', 8);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('9', '胃癌', 'wa', 9);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('10', '结直肠癌', 'jzca', 10);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('11', '乳腺癌', 'rxa', 11);
INSERT INTO diagnosis (DiagnosisCode, DiagnosisName, InputCode, ID) VALUES ('12', '淋巴瘤', 'lbl', 12);

-- 索引：diagnosis_InputCode_idx
CREATE INDEX `diagnosis_InputCode_idx` ON "diagnosis" ( `InputCode` );

-- 索引：diagnosis_DiagnosisName_idx
CREATE INDEX `diagnosis_DiagnosisName_idx` ON "diagnosis" ( `DiagnosisName` );

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
