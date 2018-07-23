package cn.kancare.mobile.common.db;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import cn.kancare.mobile.bean.BodyAnalysisReport;
import cn.kancare.mobile.bean.CourseRecord;
import cn.kancare.mobile.bean.advice.NutrientAdvice;
import cn.kancare.mobile.bean.advice.NutrientAdviceDetail;
import cn.kancare.mobile.bean.advice.NutrientAdviceSummary;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bean.basic.Diagnosis;
import cn.kancare.mobile.bean.laboratoryindex.LaboratoryIndex;
import cn.kancare.mobile.bean.laboratoryindex.SearchPageConfig;
import cn.kancare.mobile.bean.laboratoryindex.TestItemDetail;
import cn.kancare.mobile.bean.laboratoryindex.TestResult;
import cn.kancare.mobile.bean.mealrecord.MealRecord;
import cn.kancare.mobile.bean.mealrecord.RelationOfDietaryFood;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;

public class DbUpgrade {
	public static void doUpgrade(SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion)
			throws SQLException {

		if (oldVersion == 47 && oldVersion < newVersion) {
			TableUtils.createTable(connectionSource, BodyAnalysisReport.class);
			TableUtils.createTable(connectionSource, LaboratoryIndex.class);
			TableUtils.dropTable(connectionSource,
					PatientHospitalizeBasicInfo.class, true);
			TableUtils.createTable(connectionSource,
					PatientHospitalizeBasicInfo.class);
			TableUtils.dropTable(connectionSource, CourseRecord.class, true);
			TableUtils.createTable(connectionSource, CourseRecord.class);

			oldVersion++;
		}

		if (oldVersion == 48 && oldVersion < newVersion) {
			TableUtils.dropTable(connectionSource, CourseRecord.class, true);
			TableUtils.createTable(connectionSource, CourseRecord.class);
			oldVersion++;
		}

		if (oldVersion == 49 && oldVersion < newVersion) {
			TableUtils.dropTable(connectionSource, LaboratoryIndex.class, true);
			TableUtils.createTable(connectionSource, LaboratoryIndex.class);
			TableUtils.createTable(connectionSource, TestResult.class);
			TableUtils.createTable(connectionSource, TestItemDetail.class);
			TableUtils.dropTable(connectionSource,
					PatientHospitalizeBasicInfo.class, true);
			TableUtils.createTable(connectionSource,
					PatientHospitalizeBasicInfo.class);
			oldVersion++;
		}

		if (oldVersion == 50 && oldVersion < newVersion) {
			TableUtils
					.createTable(connectionSource, ChinaFoodComposition.class);
			TableUtils.createTable(connectionSource, MealRecord.class);
			TableUtils.createTable(connectionSource,
					RelationOfDietaryFood.class);
			oldVersion++;
		}

		if (oldVersion == 51 && oldVersion < newVersion) {
			TableUtils.createTable(connectionSource, SearchPageConfig.class);
			oldVersion++;
		}

		if (oldVersion == 52 && oldVersion < newVersion) {
			// 添加未见患者字段
			database.execSQL("ALTER TABLE 'courserecord' ADD  'PatientNoShow' INTEGER");
			database.execSQL("update courserecord set PatientNoShow = 0");
			// 添加是否接受营养治疗字段
			database.execSQL("ALTER TABLE 'patientquestionnaire' ADD  'AgreeHelp' INTEGER");
			database.execSQL("update patientquestionnaire set AgreeHelp = 0");
			oldVersion++;
		}

		if (oldVersion == 53 && oldVersion < newVersion) {
			// 添加床号前缀、后缀字段
			database.execSQL("ALTER TABLE 'PatientHospitalizeBasicInfo' ADD  'BedCodePrefix' VARCHAR");
			database.execSQL("ALTER TABLE 'PatientHospitalizeBasicInfo' ADD  'BedCodeSuffix' INTEGER");

			oldVersion++;
		}

		if (oldVersion == 54 && oldVersion < newVersion) {

			// 查房记录
			database.execSQL("ALTER TABLE 'courserecord' ADD  'Height' DOUBLE");
			database.execSQL("ALTER TABLE 'courserecord' ADD  'Weight' DOUBLE");
			database.execSQL("ALTER TABLE 'courserecord' ADD  'NauseaRemark' VARCHAR");
			database.execSQL("ALTER TABLE 'courserecord' ADD  'DiarrheaRemark' VARCHAR");
			database.execSQL("ALTER TABLE 'courserecord' ADD  'ConstipationRemark' VARCHAR");
			database.execSQL("ALTER TABLE 'courserecord' ADD  'VomitRemark' VARCHAR");
			database.execSQL("ALTER TABLE 'courserecord' ADD  'AbdominalDistensionRemark' VARCHAR");
			database.execSQL("ALTER TABLE 'courserecord' ADD  'AbdominalPainRemark' VARCHAR");

			oldVersion++;
		}

		if (oldVersion == 55 && oldVersion < newVersion) {

			// 检验数据加报告分类字段
			database.execSQL("ALTER TABLE 'laboratoryindex' ADD  'TestType' VARCHAR");
			database.execSQL("update department set isactive = 1");

			oldVersion++;
		}

		if (oldVersion == 56 && oldVersion < newVersion) {

			// 创建医嘱表
			TableUtils.createTable(connectionSource,
					NutrientAdviceSummary.class);
			TableUtils.createTable(connectionSource, NutrientAdvice.class);
			TableUtils
					.createTable(connectionSource, NutrientAdviceDetail.class);

			oldVersion++;
		}
		
		if (oldVersion == 57 && oldVersion < newVersion) {
			database.execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'RecipeAndProduct_DBKey' INTEGER");
			oldVersion++;
		}

		if (oldVersion == 58 && oldVersion < newVersion) {
			database.execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'MeasureUnitName' VARCHAR");
			oldVersion++;
		}
		
		if (oldVersion == 59 && oldVersion < newVersion) {
			database.execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'RecipeAndProductPrice' DOUBLE");
			oldVersion++;
		}

		if (oldVersion == 60 && oldVersion < newVersion) {
			database.execSQL("ALTER TABLE 'nutrientadvicedetail' ADD  'UnitKey' VARCHAR");
			database.execSQL("ALTER TABLE 'nutrientadvicedetail' ADD  'TotalMoney' DOUBLE");
			database.execSQL("ALTER TABLE 'nutrientadvicedetail' ADD  'NetContent' VARCHAR");
			database.execSQL("ALTER TABLE 'nutrientadvicedetail' ADD  'NetContentUnit' VARCHAR");
			oldVersion++;
		}

		if (oldVersion == 61 && oldVersion < newVersion) {
			database.execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'BaseUnitName' VARCHAR");
			database.execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'MinUnitName' VARCHAR");
			oldVersion++;
		}

		if (oldVersion == 62 && oldVersion < newVersion) {
			database.execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'MeasureUnit_DBKey' VARCHAR");
			database.execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'BaseUnit_DBKey' VARCHAR");
			database.execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'MinUnit_DBKey' VARCHAR");
			oldVersion++;
		}

        if (oldVersion == 63 && oldVersion < newVersion) {
            database.execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'MinNum' DOUBLE");
            oldVersion++;
        }

		if (oldVersion == 64 && oldVersion < newVersion) {
			database.execSQL("update patienthospitalizebasicinfo set BedCodeSuffix = BedCode where BedCodeSuffix is null");
			oldVersion++;
		}

		if (oldVersion == 65 && oldVersion < newVersion) {
			TableUtils.createTable(connectionSource, Diagnosis.class);
			oldVersion++;
		}
	}
}
