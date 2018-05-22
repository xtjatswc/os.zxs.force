package cn.kancare.mobile.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import cn.kancare.mobile.bean.BodyAnalysisReport;
import cn.kancare.mobile.bean.CourseRecord;
import cn.kancare.mobile.bean.advice.NutrientAdvice;
import cn.kancare.mobile.bean.advice.NutrientAdviceDetail;
import cn.kancare.mobile.bean.advice.NutrientAdviceSummary;
import cn.kancare.mobile.bean.basic.BedNumber;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bean.basic.Department;
import cn.kancare.mobile.bean.basic.Setting;
import cn.kancare.mobile.bean.basic.SysCode;
import cn.kancare.mobile.bean.basic.User;
import cn.kancare.mobile.bean.laboratoryindex.LaboratoryIndex;
import cn.kancare.mobile.bean.laboratoryindex.SearchPageConfig;
import cn.kancare.mobile.bean.laboratoryindex.TestItemDetail;
import cn.kancare.mobile.bean.laboratoryindex.TestResult;
import cn.kancare.mobile.bean.mealrecord.MealRecord;
import cn.kancare.mobile.bean.mealrecord.RelationOfDietaryFood;
import cn.kancare.mobile.bean.patient.PatientFavorite;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;
import cn.kancare.mobile.bean.questionnaire.OptionDetail;
import cn.kancare.mobile.bean.questionnaire.PatientQuestion;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaire;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaireResult;
import cn.kancare.mobile.bean.questionnaire.QuestionDetail;
import cn.kancare.mobile.bean.questionnaire.QuestionDetailType;

public class DatabaseHelper extends os.zxs.force.common.db.DatabaseHelper {
    public static final String DATABASE_NAME = "cnis-ward-round.db";

    static {
        DATABASE_VERSION = 63;
    }

    private static DatabaseHelper instance;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource,
                    PatientHospitalizeBasicInfo.class);
            TableUtils.createTable(connectionSource, Department.class);
            TableUtils.createTable(connectionSource, BedNumber.class);
            TableUtils.createTable(connectionSource, SysCode.class);
            TableUtils.createTable(connectionSource, CourseRecord.class);
            TableUtils.createTable(connectionSource, QuestionDetailType.class);
            TableUtils.createTable(connectionSource, QuestionDetail.class);
            TableUtils.createTable(connectionSource, OptionDetail.class);
            TableUtils.createTable(connectionSource, PatientQuestion.class);
            TableUtils
                    .createTable(connectionSource, PatientQuestionnaire.class);
            TableUtils.createTable(connectionSource,
                    PatientQuestionnaireResult.class);
            TableUtils.createTable(connectionSource, Setting.class);
            TableUtils.createTable(connectionSource, PatientFavorite.class);
            TableUtils.createTable(connectionSource, BodyAnalysisReport.class);
            TableUtils.createTable(connectionSource, LaboratoryIndex.class);
            TableUtils.createTable(connectionSource, TestItemDetail.class);
            TableUtils.createTable(connectionSource, TestResult.class);
            TableUtils
                    .createTable(connectionSource, ChinaFoodComposition.class);
            TableUtils.createTable(connectionSource, MealRecord.class);
            TableUtils.createTable(connectionSource,
                    RelationOfDietaryFood.class);
            TableUtils.createTable(connectionSource,
                    SearchPageConfig.class);
            TableUtils.createTable(connectionSource,
                    NutrientAdvice.class);
            TableUtils.createTable(connectionSource,
                    NutrientAdviceDetail.class);
            TableUtils.createTable(connectionSource,
                    NutrientAdviceSummary.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // if (newVersion == 51) {
            // TableUtils.dropTable(connectionSource, TestResult.class, true);
            // TableUtils.createTable(connectionSource, TestResult.class);
            // return;
            // }

            DbUpgrade.doUpgrade(database, connectionSource, oldVersion, newVersion);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}