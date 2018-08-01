package cn.kancare.mobile.bean.patient;

import java.util.Date;

import cn.kancare.mobile.core.base.BaseBean;
import os.zxs.force.core.util.DateHelper;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "patienthospitalizebasicinfo")
public class PatientHospitalizeBasicInfo extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// PatientHospitalize_DBKey
	@DatabaseField(columnName = "PatientHospitalize_DBKey", id = true)
	private String PatientHospitalize_DBKey;

	// PATIENT_DBKEY
	@DatabaseField(columnName = "PATIENT_DBKEY")
	private String PATIENT_DBKEY;

	// Disease_DBKEY
	@DatabaseField(columnName = "Disease_DBKEY")
	private int Disease_DBKEY;

	// Department_DBKey
	@DatabaseField(columnName = "Department_DBKey")
	private int Department_DBKey;

	// BedNumber_DBKey
	@DatabaseField(columnName = "BedNumber_DBKey")
	private int BedNumber_DBKey;

	// HospitalizationNumber
	@DatabaseField(columnName = "HospitalizationNumber")
	private String HospitalizationNumber;

	// InHospitalData
	@DatabaseField(columnName = "InHospitalData", index = true)
	private Date InHospitalData;

	// OutHospitalData
	@DatabaseField(columnName = "OutHospitalData")
	private String OutHospitalData;

	// TherapyStartTime
	@DatabaseField(columnName = "TherapyStartTime")
	private String TherapyStartTime;

	// LastScreeningDate
	@DatabaseField(columnName = "LastScreeningDate")
	private String LastScreeningDate;

	// NextScreeningDate
	@DatabaseField(columnName = "NextScreeningDate")
	private String NextScreeningDate;

	// Height
	@DatabaseField(columnName = "Height")
	private Double Height;

	// Weight
	@DatabaseField(columnName = "Weight")
	private Double Weight;

	// MedicalHistory
	@DatabaseField(columnName = "MedicalHistory")
	private String MedicalHistory;

	// PastMedicalHistory
	@DatabaseField(columnName = "PastMedicalHistory")
	private String PastMedicalHistory;

	// ChiefComplaint
	@DatabaseField(columnName = "ChiefComplaint")
	private String ChiefComplaint;

	// NutritionChiefComplaint
	@DatabaseField(columnName = "NutritionChiefComplaint")
	private String NutritionChiefComplaint;

	// NutritionCondition
	@DatabaseField(columnName = "NutritionCondition")
	private String NutritionCondition;

	// PhysicalActivityLevel
	@DatabaseField(columnName = "PhysicalActivityLevel")
	private int PhysicalActivityLevel;

	// PregnantCondition
	@DatabaseField(columnName = "PregnantCondition")
	private int PregnantCondition;

	// Staging
	@DatabaseField(columnName = "Staging")
	private String Staging;

	// RiskStratification
	@DatabaseField(columnName = "RiskStratification")
	private String RiskStratification;

	// TherapyStatus
	@DatabaseField(columnName = "TherapyStatus")
	private String TherapyStatus;

	// ClinicalDietOrders
	@DatabaseField(columnName = "ClinicalDietOrders")
	private String ClinicalDietOrders;

	// OutHospitalSummary
	@DatabaseField(columnName = "OutHospitalSummary")
	private String OutHospitalSummary;

	// Clinicist_DBKey
	@DatabaseField(columnName = "Clinicist_DBKey")
	private int Clinicist_DBKey;

	// NutrientDoctor_DBKey
	@DatabaseField(columnName = "NutrientDoctor_DBKey")
	private int NutrientDoctor_DBKey;

	// ClinicalDiagnosis
	@DatabaseField(columnName = "ClinicalDiagnosis")
	private String ClinicalDiagnosis;

	// ClinicalTreatment
	@DatabaseField(columnName = "ClinicalTreatment")
	private String ClinicalTreatment;

	// CreateBy
	@DatabaseField(columnName = "CreateBy")
	private String CreateBy;

	// CreateTime
	@DatabaseField(columnName = "CreateTime")
	private String CreateTime;

	// CreateProgram
	@DatabaseField(columnName = "CreateProgram")
	private String CreateProgram;

	// CreateIP
	@DatabaseField(columnName = "CreateIP")
	private String CreateIP;

	// UpdateBy
	@DatabaseField(columnName = "UpdateBy")
	private String UpdateBy;

	// UpdateTime
	@DatabaseField(columnName = "UpdateTime")
	private String UpdateTime;

	// UpdateProgram
	@DatabaseField(columnName = "UpdateProgram")
	private String UpdateProgram;

	// UpdateIP
	@DatabaseField(columnName = "UpdateIP")
	private String UpdateIP;

	// DepartmentName
	@DatabaseField(columnName = "DepartmentName")
	private String DepartmentName;

	// BedCode
	@DatabaseField(columnName = "BedCode")
	private String BedCode;

	// BedCodePrefix
	@DatabaseField(columnName = "BedCodePrefix")
	private String BedCodePrefix;

	// BedCodeSuffix
	@DatabaseField(columnName = "BedCodeSuffix")
	private int BedCodeSuffix;

	// ClinicistName
	@DatabaseField(columnName = "ClinicistName")
	private String ClinicistName;

	// DiseaseName
	@DatabaseField(columnName = "DiseaseName")
	private String DiseaseName;

	// TherapyStatusName
	@DatabaseField(columnName = "TherapyStatusName")
	private String TherapyStatusName;

	// PatientName
	@DatabaseField(columnName = "PatientName")
	private String PatientName;

	// Gender
	@DatabaseField(columnName = "Gender")
	private String Gender;

	// Age
	@DatabaseField(columnName = "Age")
	private String Age;

	// PatientNo
	@DatabaseField(columnName = "PatientNo")
	private String PatientNo;

	// PatientNameFirstLetter
	@DatabaseField(columnName = "PatientNameFirstLetter", index = true, indexName = "PatientHospitalizeBasicInfo_PatientNameFirstLetter_Index")
	private String PatientNameFirstLetter;

	// OrderBy
	@DatabaseField(columnName = "OrderBy")
	private int OrderBy;

	public String getPatientHospitalize_DBKey() {
		return PatientHospitalize_DBKey;
	}

	public void setPatientHospitalize_DBKey(String PatientHospitalize_DBKey) {
		this.PatientHospitalize_DBKey = PatientHospitalize_DBKey;
	}

	public String getPATIENT_DBKEY() {
		return PATIENT_DBKEY;
	}

	public void setPATIENT_DBKEY(String PATIENT_DBKEY) {
		this.PATIENT_DBKEY = PATIENT_DBKEY;
	}

	public int getDisease_DBKEY() {
		return Disease_DBKEY;
	}

	public void setDisease_DBKEY(int Disease_DBKEY) {
		this.Disease_DBKEY = Disease_DBKEY;
	}

	public int getDepartment_DBKey() {
		return Department_DBKey;
	}

	public void setDepartment_DBKey(int Department_DBKey) {
		this.Department_DBKey = Department_DBKey;
	}

	public int getBedNumber_DBKey() {
		return BedNumber_DBKey;
	}

	public void setBedNumber_DBKey(int BedNumber_DBKey) {
		this.BedNumber_DBKey = BedNumber_DBKey;
	}

	public String getHospitalizationNumber() {
		return HospitalizationNumber;
	}

	public void setHospitalizationNumber(String HospitalizationNumber) {
		this.HospitalizationNumber = HospitalizationNumber;
	}

	public Date getInHospitalData() {
		return InHospitalData;
	}

	public String getInHospitalData2() {
		return DateHelper.getInstance().getDataString_2(InHospitalData);
	}

	public void setInHospitalData(Date InHospitalData) {
		this.InHospitalData = InHospitalData;
	}

	public String getOutHospitalData() {
		return OutHospitalData;
	}

	public void setOutHospitalData(String OutHospitalData) {
		if(OutHospitalData == null){
			this.OutHospitalData = "";
		}else{
			this.OutHospitalData = OutHospitalData;
		}
	}

	public String getTherapyStartTime() {
		return TherapyStartTime;
	}

	public void setTherapyStartTime(String TherapyStartTime) {
		this.TherapyStartTime = TherapyStartTime;
	}

	public String getLastScreeningDate() {
		return LastScreeningDate;
	}

	public void setLastScreeningDate(String LastScreeningDate) {
		this.LastScreeningDate = LastScreeningDate;
	}

	public String getNextScreeningDate() {
		return NextScreeningDate;
	}

	public void setNextScreeningDate(String NextScreeningDate) {
		this.NextScreeningDate = NextScreeningDate;
	}

	public Double getHeight() {
		return Height;
	}

	public void setHeight(Double Height) {
		this.Height = Height;
	}

	public Double getWeight() {
		return Weight;
	}

	public void setWeight(Double Weight) {
		this.Weight = Weight;
	}

	public String getMedicalHistory() {
		if(MedicalHistory == null)
			return "";
		return MedicalHistory;
	}

	public void setMedicalHistory(String MedicalHistory) {
		this.MedicalHistory = MedicalHistory;
	}

	public String getPastMedicalHistory() {
		return PastMedicalHistory;
	}

	public void setPastMedicalHistory(String PastMedicalHistory) {
		this.PastMedicalHistory = PastMedicalHistory;
	}

	public String getChiefComplaint() {
		if(ChiefComplaint == null)
			return "";
		return ChiefComplaint;
	}

	public void setChiefComplaint(String ChiefComplaint) {
		this.ChiefComplaint = ChiefComplaint;
	}

	public String getNutritionChiefComplaint() {
		return NutritionChiefComplaint;
	}

	public void setNutritionChiefComplaint(String NutritionChiefComplaint) {
		this.NutritionChiefComplaint = NutritionChiefComplaint;
	}

	public String getNutritionCondition() {
		return NutritionCondition;
	}

	public void setNutritionCondition(String NutritionCondition) {
		this.NutritionCondition = NutritionCondition;
	}

	public int getPhysicalActivityLevel() {
		return PhysicalActivityLevel;
	}

	public void setPhysicalActivityLevel(int PhysicalActivityLevel) {
		this.PhysicalActivityLevel = PhysicalActivityLevel;
	}

	public int getPregnantCondition() {
		return PregnantCondition;
	}

	public void setPregnantCondition(int PregnantCondition) {
		this.PregnantCondition = PregnantCondition;
	}

	public String getStaging() {
		if (Staging == null)
			return "";

		return Staging;
	}

	public void setStaging(String Staging) {
		this.Staging = Staging;
	}

	public String getRiskStratification() {
		return RiskStratification;
	}

	public void setRiskStratification(String RiskStratification) {
		this.RiskStratification = RiskStratification;
	}

	public String getTherapyStatus() {
		return TherapyStatus;
	}

	public void setTherapyStatus(String TherapyStatus) {
		this.TherapyStatus = TherapyStatus;
	}

	public String getClinicalDietOrders() {
		return ClinicalDietOrders;
	}

	public void setClinicalDietOrders(String ClinicalDietOrders) {
		this.ClinicalDietOrders = ClinicalDietOrders;
	}

	public String getOutHospitalSummary() {
		return OutHospitalSummary;
	}

	public void setOutHospitalSummary(String OutHospitalSummary) {
		this.OutHospitalSummary = OutHospitalSummary;
	}

	public int getClinicist_DBKey() {
		return Clinicist_DBKey;
	}

	public void setClinicist_DBKey(int Clinicist_DBKey) {
		this.Clinicist_DBKey = Clinicist_DBKey;
	}

	public int getNutrientDoctor_DBKey() {
		return NutrientDoctor_DBKey;
	}

	public void setNutrientDoctor_DBKey(int NutrientDoctor_DBKey) {
		this.NutrientDoctor_DBKey = NutrientDoctor_DBKey;
	}

	public String getClinicalDiagnosis() {
		if (ClinicalDiagnosis == null)
			return "";
		else
			return ClinicalDiagnosis;
	}

	public void setClinicalDiagnosis(String ClinicalDiagnosis) {
		this.ClinicalDiagnosis = ClinicalDiagnosis;
	}

	public String getClinicalTreatment() {
		return ClinicalTreatment;
	}

	public void setClinicalTreatment(String ClinicalTreatment) {
		this.ClinicalTreatment = ClinicalTreatment;
	}

	public String getCreateBy() {
		return CreateBy;
	}

	public void setCreateBy(String CreateBy) {
		this.CreateBy = CreateBy;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String CreateTime) {
		this.CreateTime = CreateTime;
	}

	public String getCreateProgram() {
		return CreateProgram;
	}

	public void setCreateProgram(String CreateProgram) {
		this.CreateProgram = CreateProgram;
	}

	public String getCreateIP() {
		return CreateIP;
	}

	public void setCreateIP(String CreateIP) {
		this.CreateIP = CreateIP;
	}

	public String getUpdateBy() {
		return UpdateBy;
	}

	public void setUpdateBy(String UpdateBy) {
		this.UpdateBy = UpdateBy;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(String UpdateTime) {
		this.UpdateTime = UpdateTime;
	}

	public String getUpdateProgram() {
		return UpdateProgram;
	}

	public void setUpdateProgram(String UpdateProgram) {
		this.UpdateProgram = UpdateProgram;
	}

	public String getUpdateIP() {
		return UpdateIP;
	}

	public void setUpdateIP(String UpdateIP) {
		this.UpdateIP = UpdateIP;
	}

	@Override
	public String toString() {
		return "patienthospitalizebasicinfo [PatientHospitalize_DBKey="
				+ PatientHospitalize_DBKey + ",PATIENT_DBKEY=" + PATIENT_DBKEY
				+ ",Disease_DBKEY=" + Disease_DBKEY + ",Department_DBKey="
				+ Department_DBKey + ",BedNumber_DBKey=" + BedNumber_DBKey
				+ ",HospitalizationNumber=" + HospitalizationNumber
				+ ",InHospitalData=" + InHospitalData + ",OutHospitalData="
				+ OutHospitalData + ",TherapyStartTime=" + TherapyStartTime
				+ ",LastScreeningDate=" + LastScreeningDate
				+ ",NextScreeningDate=" + NextScreeningDate + ",Height="
				+ Height + ",Weight=" + Weight + ",MedicalHistory="
				+ MedicalHistory + ",PastMedicalHistory=" + PastMedicalHistory
				+ ",ChiefComplaint=" + ChiefComplaint
				+ ",NutritionChiefComplaint=" + NutritionChiefComplaint
				+ ",NutritionCondition=" + NutritionCondition
				+ ",PhysicalActivityLevel=" + PhysicalActivityLevel
				+ ",PregnantCondition=" + PregnantCondition + ",Staging="
				+ Staging + ",RiskStratification=" + RiskStratification
				+ ",TherapyStatus=" + TherapyStatus + ",ClinicalDietOrders="
				+ ClinicalDietOrders + ",OutHospitalSummary="
				+ OutHospitalSummary + ",Clinicist_DBKey=" + Clinicist_DBKey
				+ ",NutrientDoctor_DBKey=" + NutrientDoctor_DBKey
				+ ",ClinicalDiagnosis=" + ClinicalDiagnosis
				+ ",ClinicalTreatment=" + ClinicalTreatment + ",CreateBy="
				+ CreateBy + ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP + ",]";
	}

	public String getDepartmentName() {
		if (DepartmentName == null) {
			return "";
		}
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getBedCode() {
		if (BedCode == null) {
			return "";
		}
		return BedCode;
	}

	public void setBedCode(String bedCode) {
		BedCode = bedCode;
	}

	public String getClinicistName() {
		if (ClinicistName == null) {
			return "";
		}
		return ClinicistName;
	}

	public void setClinicistName(String clinicistName) {
		ClinicistName = clinicistName;
	}

	public String getDiseaseName() {
		if (DiseaseName == null) {
			return "";
		}
		return DiseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		DiseaseName = diseaseName;
	}

	public String getTherapyStatusName() {
		if (TherapyStatusName == null) {
			return "";
		}
		return TherapyStatusName;
	}

	public void setTherapyStatusName(String therapyStatusName) {
		TherapyStatusName = therapyStatusName;
	}

	public String getPatientName() {
		return PatientName;
	}

	public void setPatientName(String patientName) {
		PatientName = patientName;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getAge() {
		if (Age.equals("") || Age == null) {
			return "0";
		}
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getPatientNo() {
		return PatientNo;
	}

	public void setPatientNo(String patientNo) {
		PatientNo = patientNo;
	}

	public String getPatientNameFirstLetter() {
		return PatientNameFirstLetter;
	}

	public void setPatientNameFirstLetter(String patientNameFirstLetter) {
		PatientNameFirstLetter = patientNameFirstLetter;
	}

	public int getOrderBy() {
		return OrderBy;
	}

	public void setOrderBy(int orderBy) {
		OrderBy = orderBy;
	}

	public String getBedCodePrefix() {
		return BedCodePrefix;
	}

	public void setBedCodePrefix(String bedCodePrefix) {
		if(bedCodePrefix.equals(null)){
			BedCodePrefix = "";
		}else {
			BedCodePrefix = bedCodePrefix;
		}
	}

	public int getBedCodeSuffix() {
		return BedCodeSuffix;
	}

	public void setBedCodeSuffix(int bedCodeSuffix) {
		BedCodeSuffix = bedCodeSuffix;
	}
}
