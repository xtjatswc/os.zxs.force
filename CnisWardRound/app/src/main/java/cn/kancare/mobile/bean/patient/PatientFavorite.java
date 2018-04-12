package cn.kancare.mobile.bean.patient;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "patientfavorite")
public class PatientFavorite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4183999892380434418L;

	// PKey 联合主键 User_DBKey+PatientHospitalize_DBKey
	@DatabaseField(columnName = "PKey", id = true)
	private String PKey;

	// User_DBKey
	@DatabaseField(columnName = "User_DBKey", index = true)
	private int User_DBKey;

	// PatientHospitalize_DBKey
	@DatabaseField(columnName = "PatientHospitalize_DBKey")
	private String PatientHospitalize_DBKey;

	public int getUser_DBKey() {
		return User_DBKey;
	}

	public void setUser_DBKey(int user_DBKey) {
		User_DBKey = user_DBKey;
	}

	public String getPatientHospitalize_DBKey() {
		return PatientHospitalize_DBKey;
	}

	public void setPatientHospitalize_DBKey(String patientHospitalize_DBKey) {
		PatientHospitalize_DBKey = patientHospitalize_DBKey;
	}

	public String getPKey() {
		return PKey;
	}

	public void setPKey(String pKey) {
		PKey = pKey;
	}
}
