package cn.kancare.mobile.bean;

import cn.kancare.mobile.core.base.BaseBean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "courserecord")
public class CourseRecord extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6913063067151615695L;

	// CourseRecord_DBKey
	@DatabaseField(columnName = "CourseRecord_DBKey", id = true)
	private String CourseRecord_DBKey;

	// PatientHospitalize_DBKey
	@DatabaseField(columnName = "PatientHospitalize_DBKey")
	private String PatientHospitalize_DBKey;

	// CourseRecordNo
	@DatabaseField(columnName = "CourseRecordNo")
	private String CourseRecordNo;

	// CourseRecordDate
	@DatabaseField(columnName = "CourseRecordDate")
	private String CourseRecordDate;

	// Nausea
	@DatabaseField(columnName = "Nausea")
	private String Nausea;

	// Vomit
	@DatabaseField(columnName = "Vomit")
	private String Vomit;

	// Diarrhea
	@DatabaseField(columnName = "Diarrhea")
	private String Diarrhea;

	// AbdominalDistension
	@DatabaseField(columnName = "AbdominalDistension")
	private String AbdominalDistension;

	// Constipation
	@DatabaseField(columnName = "Constipation")
	private String Constipation;

	// AbdominalPain
	@DatabaseField(columnName = "AbdominalPain")
	private String AbdominalPain;

	// Temperature
	@DatabaseField(columnName = "Temperature")
	private double Temperature;

	// HeartRate
	@DatabaseField(columnName = "HeartRate")
	private double HeartRate;

	// Breathe
	@DatabaseField(columnName = "Breathe")
	private double Breathe;

	// DBP
	@DatabaseField(columnName = "DBP")
	private double DBP;

	// SBP
	@DatabaseField(columnName = "SBP")
	private double SBP;

	// Skin
	@DatabaseField(columnName = "Skin")
	private String Skin;

	// Edema
	@DatabaseField(columnName = "Edema")
	private String Edema;

	// TricepsSkinfold
	@DatabaseField(columnName = "TricepsSkinfold")
	private double TricepsSkinfold;

	// GripLeft
	@DatabaseField(columnName = "GripLeft")
	private double GripLeft;

	// GripRight
	@DatabaseField(columnName = "GripRight")
	private double GripRight;

	// BMI
	@DatabaseField(columnName = "BMI")
	private double BMI;

	// Drainage
	@DatabaseField(columnName = "Drainage")
	private double Drainage;

	// UrineVolume
	@DatabaseField(columnName = "UrineVolume")
	private double UrineVolume;

	// GastrointestinalDecompression
	@DatabaseField(columnName = "GastrointestinalDecompression")
	private double GastrointestinalDecompression;

	// Remark
	@DatabaseField(columnName = "Remark")
	private String Remark;

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

	// BMR
	@DatabaseField(columnName = "BMR")
	private double BMR;
	
	// WalkSpeed 6分钟步行速度(m/s)
	@DatabaseField(columnName = "WalkSpeed")
	private double WalkSpeed;
	
	// 未见患者 0 见到，1 未见
	@DatabaseField(columnName = "PatientNoShow")
	private int PatientNoShow;
	
	// 身高 
	@DatabaseField(columnName = "Height")
	private double Height;
	
	// 体重
	@DatabaseField(columnName = "Weight")
	private double Weight;
	
	// 恶心 备注
	@DatabaseField(columnName = "NauseaRemark")
	private String NauseaRemark;
	
	// 腹泻 备注
	@DatabaseField(columnName = "DiarrheaRemark")
	private String DiarrheaRemark;
	
	// 便秘 备注
	@DatabaseField(columnName = "ConstipationRemark")
	private String ConstipationRemark;
	
	// 呕吐 备注
	@DatabaseField(columnName = "VomitRemark")
	private String VomitRemark;
	
	// 腹胀 备注
	@DatabaseField(columnName = "AbdominalDistensionRemark")
	private String AbdominalDistensionRemark;
	
	// 腹痛 备注
	@DatabaseField(columnName = "AbdominalPainRemark")
	private String AbdominalPainRemark;

	public String getCourseRecord_DBKey() {
		return CourseRecord_DBKey;
	}

	public void setCourseRecord_DBKey(String CourseRecord_DBKey) {
		this.CourseRecord_DBKey = CourseRecord_DBKey;
	}

	public String getPatientHospitalize_DBKey() {
		return PatientHospitalize_DBKey;
	}

	public void setPatientHospitalize_DBKey(String PatientHospitalize_DBKey) {
		this.PatientHospitalize_DBKey = PatientHospitalize_DBKey;
	}

	public String getCourseRecordNo() {
		return CourseRecordNo;
	}

	public void setCourseRecordNo(String CourseRecordNo) {
		this.CourseRecordNo = CourseRecordNo;
	}

	public String getCourseRecordDate() {
		return CourseRecordDate;
	}

	public void setCourseRecordDate(String CourseRecordDate) {
		this.CourseRecordDate = CourseRecordDate;
	}

	public String getNausea() {
		return Nausea;
	}

	public void setNausea(String Nausea) {
		this.Nausea = Nausea;
	}

	public String getVomit() {
		return Vomit;
	}

	public void setVomit(String Vomit) {
		this.Vomit = Vomit;
	}

	public String getDiarrhea() {
		return Diarrhea;
	}

	public void setDiarrhea(String Diarrhea) {
		this.Diarrhea = Diarrhea;
	}

	public String getAbdominalDistension() {
		return AbdominalDistension;
	}

	public void setAbdominalDistension(String AbdominalDistension) {
		this.AbdominalDistension = AbdominalDistension;
	}

	public String getConstipation() {
		return Constipation;
	}

	public void setConstipation(String Constipation) {
		this.Constipation = Constipation;
	}

	public String getAbdominalPain() {
		return AbdominalPain;
	}

	public void setAbdominalPain(String AbdominalPain) {
		this.AbdominalPain = AbdominalPain;
	}

	public double getTemperature() {
		return Temperature;
	}

	public void setTemperature(double Temperature) {
		this.Temperature = Temperature;
	}

	public double getHeartRate() {
		return HeartRate;
	}

	public void setHeartRate(double HeartRate) {
		this.HeartRate = HeartRate;
	}

	public double getBreathe() {
		return Breathe;
	}

	public void setBreathe(double Breathe) {
		this.Breathe = Breathe;
	}

	public double getDBP() {
		return DBP;
	}

	public void setDBP(double DBP) {
		this.DBP = DBP;
	}

	public double getSBP() {
		return SBP;
	}

	public void setSBP(double SBP) {
		this.SBP = SBP;
	}

	public String getSkin() {
		return Skin;
	}

	public void setSkin(String Skin) {
		this.Skin = Skin;
	}

	public String getEdema() {
		return Edema;
	}

	public void setEdema(String Edema) {
		this.Edema = Edema;
	}

	public double getTricepsSkinfold() {
		return TricepsSkinfold;
	}

	public void setTricepsSkinfold(double TricepsSkinfold) {
		this.TricepsSkinfold = TricepsSkinfold;
	}

	public double getGripLeft() {
		return GripLeft;
	}

	public void setGripLeft(double GripLeft) {
		this.GripLeft = GripLeft;
	}

	public double getGripRight() {
		return GripRight;
	}

	public void setGripRight(double GripRight) {
		this.GripRight = GripRight;
	}

	public double getBMI() {
		return BMI;
	}

	public void setBMI(double BMI) {
		this.BMI = BMI;
	}

	public double getDrainage() {
		return Drainage;
	}

	public void setDrainage(double Drainage) {
		this.Drainage = Drainage;
	}

	public double getUrineVolume() {
		return UrineVolume;
	}

	public void setUrineVolume(double UrineVolume) {
		this.UrineVolume = UrineVolume;
	}

	public double getGastrointestinalDecompression() {
		return GastrointestinalDecompression;
	}

	public void setGastrointestinalDecompression(
			double GastrointestinalDecompression) {
		this.GastrointestinalDecompression = GastrointestinalDecompression;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String Remark) {
		this.Remark = Remark;
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
		return "courserecord [CourseRecord_DBKey=" + CourseRecord_DBKey
				+ ",PatientHospitalize_DBKey=" + PatientHospitalize_DBKey
				+ ",CourseRecordNo=" + CourseRecordNo + ",CourseRecordDate="
				+ CourseRecordDate + ",Nausea=" + Nausea + ",Vomit=" + Vomit
				+ ",Diarrhea=" + Diarrhea + ",AbdominalDistension="
				+ AbdominalDistension + ",Constipation=" + Constipation
				+ ",AbdominalPain=" + AbdominalPain + ",Temperature="
				+ Temperature + ",HeartRate=" + HeartRate + ",Breathe="
				+ Breathe + ",DBP=" + DBP + ",SBP=" + SBP + ",Skin=" + Skin
				+ ",Edema=" + Edema + ",TricepsSkinfold=" + TricepsSkinfold
				+ ",GripLeft=" + GripLeft + ",GripRight=" + GripRight + ",BMI="
				+ BMI + ",Drainage=" + Drainage + ",UrineVolume=" + UrineVolume
				+ ",GastrointestinalDecompression="
				+ GastrointestinalDecompression + ",Remark=" + Remark
				+ ",CreateBy=" + CreateBy + ",CreateTime=" + CreateTime
				+ ",CreateProgram=" + CreateProgram + ",CreateIP=" + CreateIP
				+ ",UpdateBy=" + UpdateBy + ",UpdateTime=" + UpdateTime
				+ ",UpdateProgram=" + UpdateProgram + ",UpdateIP=" + UpdateIP
				+ ",]";
	}

	public double getBMR() {
		return BMR;
	}

	public void setBMR(double bMR) {
		BMR = bMR;
	}

	public double getWalkSpeed() {
		return WalkSpeed;
	}

	public void setWalkSpeed(double walkSpeed) {
		WalkSpeed = walkSpeed;
	}

	public int getPatientNoShow() {
		return PatientNoShow;
	}

	public void setPatientNoShow(int patientNoShow) {
		PatientNoShow = patientNoShow;
	}

	public double getHeight() {
		return Height;
	}

	public void setHeight(double height) {
		Height = height;
	}

	public double getWeight() {
		return Weight;
	}

	public void setWeight(double weight) {
		Weight = weight;
	}

	public String getNauseaRemark() {
		return NauseaRemark;
	}

	public void setNauseaRemark(String nauseaRemark) {
		NauseaRemark = nauseaRemark;
	}

	public String getDiarrheaRemark() {
		return DiarrheaRemark;
	}

	public void setDiarrheaRemark(String diarrheaRemark) {
		DiarrheaRemark = diarrheaRemark;
	}

	public String getConstipationRemark() {
		return ConstipationRemark;
	}

	public void setConstipationRemark(String constipationRemark) {
		ConstipationRemark = constipationRemark;
	}

	public String getVomitRemark() {
		return VomitRemark;
	}

	public void setVomitRemark(String vomitRemark) {
		VomitRemark = vomitRemark;
	}

	public String getAbdominalDistensionRemark() {
		return AbdominalDistensionRemark;
	}

	public void setAbdominalDistensionRemark(String abdominalDistensionRemark) {
		AbdominalDistensionRemark = abdominalDistensionRemark;
	}

	public String getAbdominalPainRemark() {
		return AbdominalPainRemark;
	}

	public void setAbdominalPainRemark(String abdominalPainRemark) {
		AbdominalPainRemark = abdominalPainRemark;
	}
}
