package os.zxs.force.common.db;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DbUpgrade {
	public static void doUpgrade(SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion)
			throws SQLException {

//		if (oldVersion == 47 && oldVersion < newVersion) {
//			TableUtils.createTable(connectionSource, BodyAnalysisReport.class);
//			TableUtils.createTable(connectionSource, LaboratoryIndex.class);
//			TableUtils.dropTable(connectionSource,
//					PatientHospitalizeBasicInfo.class, true);
//			TableUtils.createTable(connectionSource,
//					PatientHospitalizeBasicInfo.class);
//			TableUtils.dropTable(connectionSource, CourseRecord.class, true);
//			TableUtils.createTable(connectionSource, CourseRecord.class);
//
//			oldVersion++;
//		}
//
//		if (oldVersion == 48 && oldVersion < newVersion) {
//			TableUtils.dropTable(connectionSource, CourseRecord.class, true);
//			TableUtils.createTable(connectionSource, CourseRecord.class);
//			oldVersion++;
//		}


	}
}
