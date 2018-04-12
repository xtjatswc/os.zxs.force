package cn.kancare.mobile.bean.advice;

import cn.kancare.mobile.core.base.BaseBean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "nutrientadvicesummary")
public class NutrientAdviceSummary extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// NutrientAdviceSummary_DBKey
	@DatabaseField(columnName = "NutrientAdviceSummary_DBKey", id = true)
	private String NutrientAdviceSummary_DBKey;

	// PatientHospitalize_DBKey
	@DatabaseField(columnName = "PatientHospitalize_DBKey")
	private String PatientHospitalize_DBKey;

	// NutrientAdviceSummaryNo
	@DatabaseField(columnName = "NutrientAdviceSummaryNo")
	private String NutrientAdviceSummaryNo;

	// AdviceType
	@DatabaseField(columnName = "AdviceType")
	private String AdviceType;

	// AdviceBeginDate
	@DatabaseField(columnName = "AdviceBeginDate")
	private String AdviceBeginDate;

	// AdviceEndDate
	@DatabaseField(columnName = "AdviceEndDate")
	private String AdviceEndDate;

	// AdviceDoctor
	@DatabaseField(columnName = "AdviceDoctor")
	private String AdviceDoctor;

	// AdviceCreateDate
	@DatabaseField(columnName = "AdviceCreateDate")
	private String AdviceCreateDate;

	// AdviceApprovalStatusEN
	@DatabaseField(columnName = "AdviceApprovalStatusEN")
	private String AdviceApprovalStatusEN;

	// AdviceApprovalDoctorEN
	@DatabaseField(columnName = "AdviceApprovalDoctorEN")
	private String AdviceApprovalDoctorEN;

	// AdviceApprovalDateEN
	@DatabaseField(columnName = "AdviceApprovalDateEN")
	private String AdviceApprovalDateEN;

	// AdviceApprovalStatusPN
	@DatabaseField(columnName = "AdviceApprovalStatusPN")
	private String AdviceApprovalStatusPN;

	// AdviceApprovalDoctorPN
	@DatabaseField(columnName = "AdviceApprovalDoctorPN")
	private String AdviceApprovalDoctorPN;

	// AdviceApprovalDatePN
	@DatabaseField(columnName = "AdviceApprovalDatePN")
	private String AdviceApprovalDatePN;

	// AdviceApprovalStatusFood
	@DatabaseField(columnName = "AdviceApprovalStatusFood")
	private String AdviceApprovalStatusFood;

	// AdviceApprovalDoctorFood
	@DatabaseField(columnName = "AdviceApprovalDoctorFood")
	private String AdviceApprovalDoctorFood;

	// AdviceApprovalDateFood
	@DatabaseField(columnName = "AdviceApprovalDateFood")
	private String AdviceApprovalDateFood;

	// ChargeStatus
	@DatabaseField(columnName = "ChargeStatus")
	private String ChargeStatus;

	// Charge
	@DatabaseField(columnName = "Charge")
	private String Charge;

	// ChargeDate
	@DatabaseField(columnName = "ChargeDate")
	private String ChargeDate;

	// IsOutHospitalAdvice
	@DatabaseField(columnName = "IsOutHospitalAdvice")
	private String IsOutHospitalAdvice;

	// ENHotNitrogenRatioOfCalories
	@DatabaseField(columnName = "ENHotNitrogenRatioOfCalories")
	private Double ENHotNitrogenRatioOfCalories;

	// ENProteinHSR
	@DatabaseField(columnName = "ENProteinHSR")
	private Double ENProteinHSR;

	// ENFatHSR
	@DatabaseField(columnName = "ENFatHSR")
	private Double ENFatHSR;

	// ENCarbohydratesHSR
	@DatabaseField(columnName = "ENCarbohydratesHSR")
	private Double ENCarbohydratesHSR;

	// ENInputMethod
	@DatabaseField(columnName = "ENInputMethod")
	private String ENInputMethod;

	// ENInfusionSpeed
	@DatabaseField(columnName = "ENInfusionSpeed")
	private String ENInfusionSpeed;

	// ENEachTimeDosage
	@DatabaseField(columnName = "ENEachTimeDosage")
	private Double ENEachTimeDosage;

	// ENEachTimeInterval
	@DatabaseField(columnName = "ENEachTimeInterval")
	private Double ENEachTimeInterval;

	// PNHotNitrogenRatioOfCalories
	@DatabaseField(columnName = "PNHotNitrogenRatioOfCalories")
	private Double PNHotNitrogenRatioOfCalories;

	// PNProteinHSR
	@DatabaseField(columnName = "PNProteinHSR")
	private Double PNProteinHSR;

	// PNTotalGlucoseHSR
	@DatabaseField(columnName = "PNTotalGlucoseHSR")
	private Double PNTotalGlucoseHSR;

	// PNFatHSR
	@DatabaseField(columnName = "PNFatHSR")
	private Double PNFatHSR;

	// PNInfusionSpeed
	@DatabaseField(columnName = "PNInfusionSpeed")
	private String PNInfusionSpeed;

	// CurrentClinicalDietOrders
	@DatabaseField(columnName = "CurrentClinicalDietOrders")
	private String CurrentClinicalDietOrders;

	// Staging
	@DatabaseField(columnName = "Staging")
	private String Staging;

	// RiskStratification
	@DatabaseField(columnName = "RiskStratification")
	private String RiskStratification;

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

	// DiHotNitrogenRatioOfCalories
	@DatabaseField(columnName = "DiHotNitrogenRatioOfCalories")
	private Double DiHotNitrogenRatioOfCalories;

	// DiProteinHSR
	@DatabaseField(columnName = "DiProteinHSR")
	private Double DiProteinHSR;

	// DiCarbohydratesHSR
	@DatabaseField(columnName = "DiCarbohydratesHSR")
	private Double DiCarbohydratesHSR;

	// DiFatHSR
	@DatabaseField(columnName = "DiFatHSR")
	private Double DiFatHSR;

	// PaymentDifference
	@DatabaseField(columnName = "PaymentDifference")
	private String PaymentDifference;

	public String getNutrientAdviceSummary_DBKey() {
		return NutrientAdviceSummary_DBKey;
	}

	public void setNutrientAdviceSummary_DBKey(String NutrientAdviceSummary_DBKey) {
		this.NutrientAdviceSummary_DBKey = NutrientAdviceSummary_DBKey;
	}

	public String getPatientHospitalize_DBKey() {
		return PatientHospitalize_DBKey;
	}

	public void setPatientHospitalize_DBKey(String PatientHospitalize_DBKey) {
		this.PatientHospitalize_DBKey = PatientHospitalize_DBKey;
	}

	public String getNutrientAdviceSummaryNo() {
		return NutrientAdviceSummaryNo;
	}

	public void setNutrientAdviceSummaryNo(String NutrientAdviceSummaryNo) {
		this.NutrientAdviceSummaryNo = NutrientAdviceSummaryNo;
	}

	public String getAdviceType() {
		return AdviceType;
	}

	public void setAdviceType(String AdviceType) {
		this.AdviceType = AdviceType;
	}

	public String getAdviceBeginDate() {
		return AdviceBeginDate;
	}

	public void setAdviceBeginDate(String AdviceBeginDate) {
		this.AdviceBeginDate = AdviceBeginDate;
	}

	public String getAdviceEndDate() {
		return AdviceEndDate;
	}

	public void setAdviceEndDate(String AdviceEndDate) {
		this.AdviceEndDate = AdviceEndDate;
	}

	public String getAdviceDoctor() {
		return AdviceDoctor;
	}

	public void setAdviceDoctor(String AdviceDoctor) {
		this.AdviceDoctor = AdviceDoctor;
	}

	public String getAdviceCreateDate() {
		return AdviceCreateDate;
	}

	public void setAdviceCreateDate(String AdviceCreateDate) {
		this.AdviceCreateDate = AdviceCreateDate;
	}

	public String getAdviceApprovalStatusEN() {
		return AdviceApprovalStatusEN;
	}

	public void setAdviceApprovalStatusEN(String AdviceApprovalStatusEN) {
		this.AdviceApprovalStatusEN = AdviceApprovalStatusEN;
	}

	public String getAdviceApprovalDoctorEN() {
		return AdviceApprovalDoctorEN;
	}

	public void setAdviceApprovalDoctorEN(String AdviceApprovalDoctorEN) {
		this.AdviceApprovalDoctorEN = AdviceApprovalDoctorEN;
	}

	public String getAdviceApprovalDateEN() {
		return AdviceApprovalDateEN;
	}

	public void setAdviceApprovalDateEN(String AdviceApprovalDateEN) {
		this.AdviceApprovalDateEN = AdviceApprovalDateEN;
	}

	public String getAdviceApprovalStatusPN() {
		return AdviceApprovalStatusPN;
	}

	public void setAdviceApprovalStatusPN(String AdviceApprovalStatusPN) {
		this.AdviceApprovalStatusPN = AdviceApprovalStatusPN;
	}

	public String getAdviceApprovalDoctorPN() {
		return AdviceApprovalDoctorPN;
	}

	public void setAdviceApprovalDoctorPN(String AdviceApprovalDoctorPN) {
		this.AdviceApprovalDoctorPN = AdviceApprovalDoctorPN;
	}

	public String getAdviceApprovalDatePN() {
		return AdviceApprovalDatePN;
	}

	public void setAdviceApprovalDatePN(String AdviceApprovalDatePN) {
		this.AdviceApprovalDatePN = AdviceApprovalDatePN;
	}

	public String getAdviceApprovalStatusFood() {
		return AdviceApprovalStatusFood;
	}

	public void setAdviceApprovalStatusFood(String AdviceApprovalStatusFood) {
		this.AdviceApprovalStatusFood = AdviceApprovalStatusFood;
	}

	public String getAdviceApprovalDoctorFood() {
		return AdviceApprovalDoctorFood;
	}

	public void setAdviceApprovalDoctorFood(String AdviceApprovalDoctorFood) {
		this.AdviceApprovalDoctorFood = AdviceApprovalDoctorFood;
	}

	public String getAdviceApprovalDateFood() {
		return AdviceApprovalDateFood;
	}

	public void setAdviceApprovalDateFood(String AdviceApprovalDateFood) {
		this.AdviceApprovalDateFood = AdviceApprovalDateFood;
	}

	public String getChargeStatus() {
		return ChargeStatus;
	}

	public void setChargeStatus(String ChargeStatus) {
		this.ChargeStatus = ChargeStatus;
	}

	public String getCharge() {
		return Charge;
	}

	public void setCharge(String Charge) {
		this.Charge = Charge;
	}

	public String getChargeDate() {
		return ChargeDate;
	}

	public void setChargeDate(String ChargeDate) {
		this.ChargeDate = ChargeDate;
	}

	public String getIsOutHospitalAdvice() {
		return IsOutHospitalAdvice;
	}

	public void setIsOutHospitalAdvice(String IsOutHospitalAdvice) {
		this.IsOutHospitalAdvice = IsOutHospitalAdvice;
	}

	public Double getENHotNitrogenRatioOfCalories() {
		return ENHotNitrogenRatioOfCalories;
	}

	public void setENHotNitrogenRatioOfCalories(
			Double ENHotNitrogenRatioOfCalories) {
		this.ENHotNitrogenRatioOfCalories = ENHotNitrogenRatioOfCalories;
	}

	public Double getENProteinHSR() {
		return ENProteinHSR;
	}

	public void setENProteinHSR(Double ENProteinHSR) {
		this.ENProteinHSR = ENProteinHSR;
	}

	public Double getENFatHSR() {
		return ENFatHSR;
	}

	public void setENFatHSR(Double ENFatHSR) {
		this.ENFatHSR = ENFatHSR;
	}

	public Double getENCarbohydratesHSR() {
		return ENCarbohydratesHSR;
	}

	public void setENCarbohydratesHSR(Double ENCarbohydratesHSR) {
		this.ENCarbohydratesHSR = ENCarbohydratesHSR;
	}

	public String getENInputMethod() {
		return ENInputMethod;
	}

	public void setENInputMethod(String ENInputMethod) {
		this.ENInputMethod = ENInputMethod;
	}

	public String getENInfusionSpeed() {
		return ENInfusionSpeed;
	}

	public void setENInfusionSpeed(String ENInfusionSpeed) {
		this.ENInfusionSpeed = ENInfusionSpeed;
	}

	public Double getENEachTimeDosage() {
		return ENEachTimeDosage;
	}

	public void setENEachTimeDosage(Double ENEachTimeDosage) {
		this.ENEachTimeDosage = ENEachTimeDosage;
	}

	public Double getENEachTimeInterval() {
		return ENEachTimeInterval;
	}

	public void setENEachTimeInterval(Double ENEachTimeInterval) {
		this.ENEachTimeInterval = ENEachTimeInterval;
	}

	public Double getPNHotNitrogenRatioOfCalories() {
		return PNHotNitrogenRatioOfCalories;
	}

	public void setPNHotNitrogenRatioOfCalories(
			Double PNHotNitrogenRatioOfCalories) {
		this.PNHotNitrogenRatioOfCalories = PNHotNitrogenRatioOfCalories;
	}

	public Double getPNProteinHSR() {
		return PNProteinHSR;
	}

	public void setPNProteinHSR(Double PNProteinHSR) {
		this.PNProteinHSR = PNProteinHSR;
	}

	public Double getPNTotalGlucoseHSR() {
		return PNTotalGlucoseHSR;
	}

	public void setPNTotalGlucoseHSR(Double PNTotalGlucoseHSR) {
		this.PNTotalGlucoseHSR = PNTotalGlucoseHSR;
	}

	public Double getPNFatHSR() {
		return PNFatHSR;
	}

	public void setPNFatHSR(Double PNFatHSR) {
		this.PNFatHSR = PNFatHSR;
	}

	public String getPNInfusionSpeed() {
		return PNInfusionSpeed;
	}

	public void setPNInfusionSpeed(String PNInfusionSpeed) {
		this.PNInfusionSpeed = PNInfusionSpeed;
	}

	public String getCurrentClinicalDietOrders() {
		return CurrentClinicalDietOrders;
	}

	public void setCurrentClinicalDietOrders(String CurrentClinicalDietOrders) {
		this.CurrentClinicalDietOrders = CurrentClinicalDietOrders;
	}

	public String getStaging() {
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

	public Double getDiHotNitrogenRatioOfCalories() {
		return DiHotNitrogenRatioOfCalories;
	}

	public void setDiHotNitrogenRatioOfCalories(
			Double DiHotNitrogenRatioOfCalories) {
		this.DiHotNitrogenRatioOfCalories = DiHotNitrogenRatioOfCalories;
	}

	public Double getDiProteinHSR() {
		return DiProteinHSR;
	}

	public void setDiProteinHSR(Double DiProteinHSR) {
		this.DiProteinHSR = DiProteinHSR;
	}

	public Double getDiCarbohydratesHSR() {
		return DiCarbohydratesHSR;
	}

	public void setDiCarbohydratesHSR(Double DiCarbohydratesHSR) {
		this.DiCarbohydratesHSR = DiCarbohydratesHSR;
	}

	public Double getDiFatHSR() {
		return DiFatHSR;
	}

	public void setDiFatHSR(Double DiFatHSR) {
		this.DiFatHSR = DiFatHSR;
	}

	public String getPaymentDifference() {
		return PaymentDifference;
	}

	public void setPaymentDifference(String PaymentDifference) {
		this.PaymentDifference = PaymentDifference;
	}

	@Override
	public String toString() {
		return "nutrientadvicesummary [NutrientAdviceSummary_DBKey="
				+ NutrientAdviceSummary_DBKey + ",PatientHospitalize_DBKey="
				+ PatientHospitalize_DBKey + ",NutrientAdviceSummaryNo="
				+ NutrientAdviceSummaryNo + ",AdviceType=" + AdviceType
				+ ",AdviceBeginDate=" + AdviceBeginDate + ",AdviceEndDate="
				+ AdviceEndDate + ",AdviceDoctor=" + AdviceDoctor
				+ ",AdviceCreateDate=" + AdviceCreateDate
				+ ",AdviceApprovalStatusEN=" + AdviceApprovalStatusEN
				+ ",AdviceApprovalDoctorEN=" + AdviceApprovalDoctorEN
				+ ",AdviceApprovalDateEN=" + AdviceApprovalDateEN
				+ ",AdviceApprovalStatusPN=" + AdviceApprovalStatusPN
				+ ",AdviceApprovalDoctorPN=" + AdviceApprovalDoctorPN
				+ ",AdviceApprovalDatePN=" + AdviceApprovalDatePN
				+ ",AdviceApprovalStatusFood=" + AdviceApprovalStatusFood
				+ ",AdviceApprovalDoctorFood=" + AdviceApprovalDoctorFood
				+ ",AdviceApprovalDateFood=" + AdviceApprovalDateFood
				+ ",ChargeStatus=" + ChargeStatus + ",Charge=" + Charge
				+ ",ChargeDate=" + ChargeDate + ",IsOutHospitalAdvice="
				+ IsOutHospitalAdvice + ",ENHotNitrogenRatioOfCalories="
				+ ENHotNitrogenRatioOfCalories + ",ENProteinHSR="
				+ ENProteinHSR + ",ENFatHSR=" + ENFatHSR
				+ ",ENCarbohydratesHSR=" + ENCarbohydratesHSR
				+ ",ENInputMethod=" + ENInputMethod + ",ENInfusionSpeed="
				+ ENInfusionSpeed + ",ENEachTimeDosage=" + ENEachTimeDosage
				+ ",ENEachTimeInterval=" + ENEachTimeInterval
				+ ",PNHotNitrogenRatioOfCalories="
				+ PNHotNitrogenRatioOfCalories + ",PNProteinHSR="
				+ PNProteinHSR + ",PNTotalGlucoseHSR=" + PNTotalGlucoseHSR
				+ ",PNFatHSR=" + PNFatHSR + ",PNInfusionSpeed="
				+ PNInfusionSpeed + ",CurrentClinicalDietOrders="
				+ CurrentClinicalDietOrders + ",Staging=" + Staging
				+ ",RiskStratification=" + RiskStratification + ",CreateBy="
				+ CreateBy + ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP
				+ ",DiHotNitrogenRatioOfCalories="
				+ DiHotNitrogenRatioOfCalories + ",DiProteinHSR="
				+ DiProteinHSR + ",DiCarbohydratesHSR=" + DiCarbohydratesHSR
				+ ",DiFatHSR=" + DiFatHSR + ",PaymentDifference="
				+ PaymentDifference + ",]";
	}
}
