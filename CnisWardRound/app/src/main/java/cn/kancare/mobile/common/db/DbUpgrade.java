package cn.kancare.mobile.common.db;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import cn.kancare.mobile.bean.BodyAnalysisReport;
import cn.kancare.mobile.bean.CourseRecord;
import cn.kancare.mobile.bean.advice.ChargingAdviceDetail;
import cn.kancare.mobile.bean.advice.ChargingItems;
import cn.kancare.mobile.bean.advice.ChargingItemsRelation;
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
import cn.kancare.mobile.bo.patient.PatientHospitalizeBasicInfoBo;
import cn.kancare.mobile.common.Global;

public class DbUpgrade {
	public static SQLiteDatabase db;

	public static void execSQL(String sql){
		try{
			db.execSQL(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void doUpgrade(SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion)
			throws Exception {
		db = database;

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
			execSQL("ALTER TABLE 'courserecord' ADD  'PatientNoShow' INTEGER");
			execSQL("update courserecord set PatientNoShow = 0");
			// 添加是否接受营养治疗字段
			execSQL("ALTER TABLE 'patientquestionnaire' ADD  'AgreeHelp' INTEGER");
			execSQL("update patientquestionnaire set AgreeHelp = 0");
			oldVersion++;
		}

		if (oldVersion == 53 && oldVersion < newVersion) {
			// 添加床号前缀、后缀字段
			execSQL("ALTER TABLE 'PatientHospitalizeBasicInfo' ADD  'BedCodePrefix' VARCHAR");
			execSQL("ALTER TABLE 'PatientHospitalizeBasicInfo' ADD  'BedCodeSuffix' INTEGER");

			oldVersion++;
		}

		if (oldVersion == 54 && oldVersion < newVersion) {

			// 查房记录
			execSQL("ALTER TABLE 'courserecord' ADD  'Height' DOUBLE");
			execSQL("ALTER TABLE 'courserecord' ADD  'Weight' DOUBLE");
			execSQL("ALTER TABLE 'courserecord' ADD  'NauseaRemark' VARCHAR");
			execSQL("ALTER TABLE 'courserecord' ADD  'DiarrheaRemark' VARCHAR");
			execSQL("ALTER TABLE 'courserecord' ADD  'ConstipationRemark' VARCHAR");
			execSQL("ALTER TABLE 'courserecord' ADD  'VomitRemark' VARCHAR");
			execSQL("ALTER TABLE 'courserecord' ADD  'AbdominalDistensionRemark' VARCHAR");
			execSQL("ALTER TABLE 'courserecord' ADD  'AbdominalPainRemark' VARCHAR");

			oldVersion++;
		}

		if (oldVersion == 55 && oldVersion < newVersion) {

			// 检验数据加报告分类字段
			execSQL("ALTER TABLE 'laboratoryindex' ADD  'TestType' VARCHAR");
			execSQL("update department set isactive = 1");

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
			execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'RecipeAndProduct_DBKey' INTEGER");
			oldVersion++;
		}

		if (oldVersion == 58 && oldVersion < newVersion) {
			execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'MeasureUnitName' VARCHAR");
			oldVersion++;
		}
		
		if (oldVersion == 59 && oldVersion < newVersion) {
			execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'RecipeAndProductPrice' DOUBLE");
			oldVersion++;
		}

		if (oldVersion == 60 && oldVersion < newVersion) {
			execSQL("ALTER TABLE 'nutrientadvicedetail' ADD  'UnitKey' VARCHAR");
			execSQL("ALTER TABLE 'nutrientadvicedetail' ADD  'TotalMoney' DOUBLE");
			execSQL("ALTER TABLE 'nutrientadvicedetail' ADD  'NetContent' VARCHAR");
			execSQL("ALTER TABLE 'nutrientadvicedetail' ADD  'NetContentUnit' VARCHAR");
			oldVersion++;
		}

		if (oldVersion == 61 && oldVersion < newVersion) {
			execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'BaseUnitName' VARCHAR");
			execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'MinUnitName' VARCHAR");
			oldVersion++;
		}

		if (oldVersion == 62 && oldVersion < newVersion) {
			execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'MeasureUnit_DBKey' VARCHAR");
			execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'BaseUnit_DBKey' VARCHAR");
			execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'MinUnit_DBKey' VARCHAR");
			oldVersion++;
		}

        if (oldVersion == 63 && oldVersion < newVersion) {
            execSQL("ALTER TABLE 'ChinaFoodComposition' ADD  'MinNum' DOUBLE");
            oldVersion++;
        }

		if (oldVersion == 64 && oldVersion < newVersion) {
			execSQL("update patienthospitalizebasicinfo set BedCodeSuffix = BedCode where BedCodeSuffix is null");
			oldVersion++;
		}

		if (oldVersion == 65 && oldVersion < newVersion) {
			execSQL("update patienthospitalizebasicinfo set TherapyStatus = 0 where TherapyStatus is null");
			PatientHospitalizeBasicInfoBo patientBo = new PatientHospitalizeBasicInfoBo(Global.currentActivity);
			patientBo.getDao().updateOrderBy();
			TableUtils.createTable(connectionSource, Diagnosis.class);
			oldVersion++;
		}

		if (oldVersion == 66 && oldVersion < newVersion) {
			execSQL("update patienthospitalizebasicinfo set BedCodePrefix = '' where BedCodePrefix is null");
			oldVersion++;
		}

		if (oldVersion == 67 && oldVersion < newVersion) {
			TableUtils.createTable(connectionSource, ChargingAdviceDetail.class);
			TableUtils.createTable(connectionSource, ChargingItems.class);
			TableUtils.createTable(connectionSource, ChargingItemsRelation.class);
            execSQL("CREATE UNIQUE INDEX \"main\".\"ChargingItemsRelation_unique\" ON \"ChargingItemsRelation\" (  \"RecipeAndProduct_DBKey\",  \"ChargingItemID\");");
            oldVersion++;
		}

//		if (oldVersion == 68 && oldVersion < newVersion) {
//			execSQL("INSERT INTO \"chinafoodcomposition\"(\"FoodName\", \"ChinaFoodComposition_DBKey\", \"RecipeAndProduct_DBKey\", \"FoodCode\", \"Protein\", \"Fat\", \"Carbohydrate\", \"K\", \"Na\", \"Energy\", \"FoodTableInsideType\", \"WrapperType\", \"BaseUnitName\", \"MeasureUnitName\", \"MinNum\", \"MinUnitName\", \"NutrientProductSpecification\", \"RecipeAndProductPrice\") VALUES ('枣环磷酸腺苷', 1429, 1429, '1429', 2.7, 0, 57, NULL, '51', 238.77, 2, 1, '袋', 'ml', 1.0, '袋', 200.0, 0.0);");
//			oldVersion++;
//		}

	}
}
