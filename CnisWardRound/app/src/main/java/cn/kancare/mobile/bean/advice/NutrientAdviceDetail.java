package cn.kancare.mobile.bean.advice;

import cn.kancare.mobile.core.base.BaseBean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "nutrientadvicedetail")
public class NutrientAdviceDetail extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// NutrientAdviceDetail_DBKEY
	@DatabaseField(columnName = "NutrientAdviceDetail_DBKEY", id = true)
	private String NutrientAdviceDetail_DBKEY;

	// NutrientAdvice_DBKey
	@DatabaseField(columnName = "NutrientAdvice_DBKey")
	private String NutrientAdvice_DBKey;

	// RecipeAndProduct_DBKey
	@DatabaseField(columnName = "RecipeAndProduct_DBKey")
	private int RecipeAndProduct_DBKey;

	// Medicine_DBKey
	@DatabaseField(columnName = "Medicine_DBKey")
	private int Medicine_DBKey;

	// PreparationName
	@DatabaseField(columnName = "PreparationName")
	private String PreparationName;

	// PreparationData
	@DatabaseField(columnName = "PreparationData")
	private String PreparationData;

	// AdviceDoDate
	@DatabaseField(columnName = "AdviceDoDate")
	private String AdviceDoDate;

	// AdviceDoTimeSegmental
	@DatabaseField(columnName = "AdviceDoTimeSegmental")
	private String AdviceDoTimeSegmental;

	// AdviceDoPercentage
	@DatabaseField(columnName = "AdviceDoPercentage")
	private Double AdviceDoPercentage;

	// NutrientAdviceType
	@DatabaseField(columnName = "NutrientAdviceType")
	private String NutrientAdviceType;

	// AdviceAmount
	@DatabaseField(columnName = "AdviceAmount")
	private Double AdviceAmount;

	// Specification
	@DatabaseField(columnName = "Specification")
	private String Specification;

	// Unit
	@DatabaseField(columnName = "Unit")
	private String Unit;

	// PreparationMode
	@DatabaseField(columnName = "PreparationMode")
	private String PreparationMode;

	// NutrientAdviceDetailRemark
	@DatabaseField(columnName = "NutrientAdviceDetailRemark")
	private String NutrientAdviceDetailRemark;

	// AdviceValidStatus
	@DatabaseField(columnName = "AdviceValidStatus")
	private String AdviceValidStatus;

	// PreparationPack
	@DatabaseField(columnName = "PreparationPack")
	private String PreparationPack;

	// PreparationPerson
	@DatabaseField(columnName = "PreparationPerson")
	private String PreparationPerson;

	// PreparationStatus
	@DatabaseField(columnName = "PreparationStatus")
	private String PreparationStatus;

	// HandoverStatus
	@DatabaseField(columnName = "HandoverStatus")
	private String HandoverStatus;

	// HandoverPeople
	@DatabaseField(columnName = "HandoverPeople")
	private String HandoverPeople;

	// HandoverTime
	@DatabaseField(columnName = "HandoverTime")
	private String HandoverTime;

	// PreparationID
	@DatabaseField(columnName = "PreparationID")
	private String PreparationID;

	// PreparationTime
	@DatabaseField(columnName = "PreparationTime")
	private String PreparationTime;

	// NutrientProductCompleteNo
	@DatabaseField(columnName = "NutrientProductCompleteNo")
	private String NutrientProductCompleteNo;

	// RefundStatus
	@DatabaseField(columnName = "RefundStatus")
	private String RefundStatus;

	// Refund
	@DatabaseField(columnName = "Refund")
	private String Refund;

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

	// CurrentPrice
	@DatabaseField(columnName = "CurrentPrice")
	private Double CurrentPrice;

	// TakeOrder
	@DatabaseField(columnName = "TakeOrder")
	private String TakeOrder;

	// LiquidAmount
	@DatabaseField(columnName = "LiquidAmount")
	private Double LiquidAmount;

	// CheckerName
	@DatabaseField(columnName = "CheckerName")
	private String CheckerName;

	// IsQualified
	@DatabaseField(columnName = "IsQualified")
	private String IsQualified;

	// SingleMetering
	@DatabaseField(columnName = "SingleMetering")
	private String SingleMetering;

	// Directions
	@DatabaseField(columnName = "Directions")
	private String Directions;

	public String getNutrientAdviceDetail_DBKEY() {
		return NutrientAdviceDetail_DBKEY;
	}

	public void setNutrientAdviceDetail_DBKEY(String NutrientAdviceDetail_DBKEY) {
		this.NutrientAdviceDetail_DBKEY = NutrientAdviceDetail_DBKEY;
	}

	public String getNutrientAdvice_DBKey() {
		return NutrientAdvice_DBKey;
	}

	public void setNutrientAdvice_DBKey(String NutrientAdvice_DBKey) {
		this.NutrientAdvice_DBKey = NutrientAdvice_DBKey;
	}

	public int getRecipeAndProduct_DBKey() {
		return RecipeAndProduct_DBKey;
	}

	public void setRecipeAndProduct_DBKey(int RecipeAndProduct_DBKey) {
		this.RecipeAndProduct_DBKey = RecipeAndProduct_DBKey;
	}

	public int getMedicine_DBKey() {
		return Medicine_DBKey;
	}

	public void setMedicine_DBKey(int Medicine_DBKey) {
		this.Medicine_DBKey = Medicine_DBKey;
	}

	public String getPreparationName() {
		return PreparationName;
	}

	public void setPreparationName(String PreparationName) {
		this.PreparationName = PreparationName;
	}

	public String getPreparationData() {
		return PreparationData;
	}

	public void setPreparationData(String PreparationData) {
		this.PreparationData = PreparationData;
	}

	public String getAdviceDoDate() {
		return AdviceDoDate;
	}

	public void setAdviceDoDate(String AdviceDoDate) {
		this.AdviceDoDate = AdviceDoDate;
	}

	public String getAdviceDoTimeSegmental() {
		return AdviceDoTimeSegmental;
	}

	public void setAdviceDoTimeSegmental(String AdviceDoTimeSegmental) {
		this.AdviceDoTimeSegmental = AdviceDoTimeSegmental;
	}

	public Double getAdviceDoPercentage() {
		return AdviceDoPercentage;
	}

	public void setAdviceDoPercentage(Double AdviceDoPercentage) {
		this.AdviceDoPercentage = AdviceDoPercentage;
	}

	public String getNutrientAdviceType() {
		return NutrientAdviceType;
	}

	public void setNutrientAdviceType(String NutrientAdviceType) {
		this.NutrientAdviceType = NutrientAdviceType;
	}

	public Double getAdviceAmount() {
		return AdviceAmount;
	}

	public void setAdviceAmount(Double AdviceAmount) {
		this.AdviceAmount = AdviceAmount;
	}

	public String getSpecification() {
		return Specification;
	}

	public void setSpecification(String Specification) {
		this.Specification = Specification;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String Unit) {
		this.Unit = Unit;
	}

	public String getPreparationMode() {
		return PreparationMode;
	}

	public void setPreparationMode(String PreparationMode) {
		this.PreparationMode = PreparationMode;
	}

	public String getNutrientAdviceDetailRemark() {
		return NutrientAdviceDetailRemark;
	}

	public void setNutrientAdviceDetailRemark(String NutrientAdviceDetailRemark) {
		this.NutrientAdviceDetailRemark = NutrientAdviceDetailRemark;
	}

	public String getAdviceValidStatus() {
		return AdviceValidStatus;
	}

	public void setAdviceValidStatus(String AdviceValidStatus) {
		this.AdviceValidStatus = AdviceValidStatus;
	}

	public String getPreparationPack() {
		return PreparationPack;
	}

	public void setPreparationPack(String PreparationPack) {
		this.PreparationPack = PreparationPack;
	}

	public String getPreparationPerson() {
		return PreparationPerson;
	}

	public void setPreparationPerson(String PreparationPerson) {
		this.PreparationPerson = PreparationPerson;
	}

	public String getPreparationStatus() {
		return PreparationStatus;
	}

	public void setPreparationStatus(String PreparationStatus) {
		this.PreparationStatus = PreparationStatus;
	}

	public String getHandoverStatus() {
		return HandoverStatus;
	}

	public void setHandoverStatus(String HandoverStatus) {
		this.HandoverStatus = HandoverStatus;
	}

	public String getHandoverPeople() {
		return HandoverPeople;
	}

	public void setHandoverPeople(String HandoverPeople) {
		this.HandoverPeople = HandoverPeople;
	}

	public String getHandoverTime() {
		return HandoverTime;
	}

	public void setHandoverTime(String HandoverTime) {
		this.HandoverTime = HandoverTime;
	}

	public String getPreparationID() {
		return PreparationID;
	}

	public void setPreparationID(String PreparationID) {
		this.PreparationID = PreparationID;
	}

	public String getPreparationTime() {
		return PreparationTime;
	}

	public void setPreparationTime(String PreparationTime) {
		this.PreparationTime = PreparationTime;
	}

	public String getNutrientProductCompleteNo() {
		return NutrientProductCompleteNo;
	}

	public void setNutrientProductCompleteNo(String NutrientProductCompleteNo) {
		this.NutrientProductCompleteNo = NutrientProductCompleteNo;
	}

	public String getRefundStatus() {
		return RefundStatus;
	}

	public void setRefundStatus(String RefundStatus) {
		this.RefundStatus = RefundStatus;
	}

	public String getRefund() {
		return Refund;
	}

	public void setRefund(String Refund) {
		this.Refund = Refund;
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

	public Double getCurrentPrice() {
		return CurrentPrice;
	}

	public void setCurrentPrice(Double CurrentPrice) {
		this.CurrentPrice = CurrentPrice;
	}

	public String getTakeOrder() {
		return TakeOrder;
	}

	public void setTakeOrder(String TakeOrder) {
		this.TakeOrder = TakeOrder;
	}

	public Double getLiquidAmount() {
		return LiquidAmount;
	}

	public void setLiquidAmount(Double LiquidAmount) {
		this.LiquidAmount = LiquidAmount;
	}

	public String getCheckerName() {
		return CheckerName;
	}

	public void setCheckerName(String CheckerName) {
		this.CheckerName = CheckerName;
	}

	public String getIsQualified() {
		return IsQualified;
	}

	public void setIsQualified(String IsQualified) {
		this.IsQualified = IsQualified;
	}

	public String getSingleMetering() {
		return SingleMetering;
	}

	public void setSingleMetering(String SingleMetering) {
		this.SingleMetering = SingleMetering;
	}

	public String getDirections() {
		return Directions;
	}

	public void setDirections(String Directions) {
		this.Directions = Directions;
	}

	@Override
	public String toString() {
		return "nutrientadvicedetail [NutrientAdviceDetail_DBKEY="
				+ NutrientAdviceDetail_DBKEY + ",NutrientAdvice_DBKey="
				+ NutrientAdvice_DBKey + ",RecipeAndProduct_DBKey="
				+ RecipeAndProduct_DBKey + ",Medicine_DBKey=" + Medicine_DBKey
				+ ",PreparationName=" + PreparationName + ",PreparationData="
				+ PreparationData + ",AdviceDoDate=" + AdviceDoDate
				+ ",AdviceDoTimeSegmental=" + AdviceDoTimeSegmental
				+ ",AdviceDoPercentage=" + AdviceDoPercentage
				+ ",NutrientAdviceType=" + NutrientAdviceType
				+ ",AdviceAmount=" + AdviceAmount + ",Specification="
				+ Specification + ",Unit=" + Unit + ",PreparationMode="
				+ PreparationMode + ",NutrientAdviceDetailRemark="
				+ NutrientAdviceDetailRemark + ",AdviceValidStatus="
				+ AdviceValidStatus + ",PreparationPack=" + PreparationPack
				+ ",PreparationPerson=" + PreparationPerson
				+ ",PreparationStatus=" + PreparationStatus
				+ ",HandoverStatus=" + HandoverStatus + ",HandoverPeople="
				+ HandoverPeople + ",HandoverTime=" + HandoverTime
				+ ",PreparationID=" + PreparationID + ",PreparationTime="
				+ PreparationTime + ",NutrientProductCompleteNo="
				+ NutrientProductCompleteNo + ",RefundStatus=" + RefundStatus
				+ ",Refund=" + Refund + ",CreateBy=" + CreateBy
				+ ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP + ",CurrentPrice="
				+ CurrentPrice + ",TakeOrder=" + TakeOrder + ",LiquidAmount="
				+ LiquidAmount + ",CheckerName=" + CheckerName
				+ ",IsQualified=" + IsQualified + ",SingleMetering="
				+ SingleMetering + ",Directions=" + Directions + ",]";
	}
}
