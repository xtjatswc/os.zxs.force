package cn.kancare.mobile.bean.basic;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "chinafoodcomposition")
public class ChinaFoodComposition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// ChinaFoodComposition_DBKey
	@DatabaseField(columnName = "ChinaFoodComposition_DBKey", id = true)
	private int ChinaFoodComposition_DBKey;

	// FoodCategory_DBKey
	@DatabaseField(columnName = "FoodCategory_DBKey")
	private int FoodCategory_DBKey;

	// FoodCode
	@DatabaseField(columnName = "FoodCode")
	private String FoodCode;

	// FoodName
	@DatabaseField(columnName = "FoodName")
	private String FoodName;

	// FoodFirstLetter
	@DatabaseField(columnName = "FoodFirstLetter")
	private String FoodFirstLetter;

	// FoodTableInsideType
	@DatabaseField(columnName = "FoodTableInsideType")
	private int FoodTableInsideType;

	// Water
	@DatabaseField(columnName = "Water")
	private Double Water;

	// Energy
	@DatabaseField(columnName = "Energy")
	private Double Energy;

	// EnergyKJ
	@DatabaseField(columnName = "EnergyKJ")
	private Double EnergyKJ;

	// Protein
	@DatabaseField(columnName = "Protein")
	private Double Protein;

	// Fat
	@DatabaseField(columnName = "Fat")
	private Double Fat;

	// Carbohydrate
	@DatabaseField(columnName = "Carbohydrate")
	private Double Carbohydrate;

	// TotalDietaryFiber
	@DatabaseField(columnName = "TotalDietaryFiber")
	private Double TotalDietaryFiber;

	// SolubleDietaryFiber
	@DatabaseField(columnName = "SolubleDietaryFiber")
	private Double SolubleDietaryFiber;

	// InsolubleDietaryFiber
	@DatabaseField(columnName = "InsolubleDietaryFiber")
	private Double InsolubleDietaryFiber;

	// Ash
	@DatabaseField(columnName = "Ash")
	private Double Ash;

	// Cholesterol
	@DatabaseField(columnName = "Cholesterol")
	private Double Cholesterol;

	// VitaminA
	@DatabaseField(columnName = "VitaminA")
	private Double VitaminA;

	// TotalCarotene
	@DatabaseField(columnName = "TotalCarotene")
	private Double TotalCarotene;

	// Thiamin
	@DatabaseField(columnName = "Thiamin")
	private Double Thiamin;

	// Riboflavin
	@DatabaseField(columnName = "Riboflavin")
	private Double Riboflavin;

	// VitaminB6
	@DatabaseField(columnName = "VitaminB6")
	private Double VitaminB6;

	// VitaminB12
	@DatabaseField(columnName = "VitaminB12")
	private Double VitaminB12;

	// Folate
	@DatabaseField(columnName = "Folate")
	private Double Folate;

	// Niacin
	@DatabaseField(columnName = "Niacin")
	private Double Niacin;

	// VitaminC
	@DatabaseField(columnName = "VitaminC")
	private Double VitaminC;

	// VitaminETotal
	@DatabaseField(columnName = "VitaminETotal")
	private Double VitaminETotal;

	// VitaminETE
	@DatabaseField(columnName = "VitaminETE")
	private Double VitaminETE;

	// Ca
	@DatabaseField(columnName = "Ca")
	private Double Ca;

	// P
	@DatabaseField(columnName = "P")
	private Double P;

	// K
	@DatabaseField(columnName = "K")
	private Double K;

	// Na
	@DatabaseField(columnName = "Na")
	private Double Na;

	// Mg
	@DatabaseField(columnName = "Mg")
	private Double Mg;

	// Fe
	@DatabaseField(columnName = "Fe")
	private Double Fe;

	// Zn
	@DatabaseField(columnName = "Zn")
	private Double Zn;

	// Se
	@DatabaseField(columnName = "Se")
	private Double Se;

	// Cu
	@DatabaseField(columnName = "Cu")
	private Double Cu;

	// Mn
	@DatabaseField(columnName = "Mn")
	private Double Mn;

	// I
	@DatabaseField(columnName = "I")
	private Double I;

	// Ile
	@DatabaseField(columnName = "Ile")
	private Double Ile;

	// Leu
	@DatabaseField(columnName = "Leu")
	private Double Leu;

	// Lys
	@DatabaseField(columnName = "Lys")
	private Double Lys;

	// SAATotal
	@DatabaseField(columnName = "SAATotal")
	private Double SAATotal;

	// Met
	@DatabaseField(columnName = "Met")
	private Double Met;

	// Cys
	@DatabaseField(columnName = "Cys")
	private Double Cys;

	// AAA
	@DatabaseField(columnName = "AAA")
	private Double AAA;

	// Phe
	@DatabaseField(columnName = "Phe")
	private Double Phe;

	// Tyr
	@DatabaseField(columnName = "Tyr")
	private Double Tyr;

	// Thr
	@DatabaseField(columnName = "Thr")
	private Double Thr;

	// Trp
	@DatabaseField(columnName = "Trp")
	private Double Trp;

	// Val
	@DatabaseField(columnName = "Val")
	private Double Val;

	// Arg
	@DatabaseField(columnName = "Arg")
	private Double Arg;

	// His
	@DatabaseField(columnName = "His")
	private Double His;

	// Ala
	@DatabaseField(columnName = "Ala")
	private Double Ala;

	// Asp
	@DatabaseField(columnName = "Asp")
	private Double Asp;

	// Glu
	@DatabaseField(columnName = "Glu")
	private Double Glu;

	// Gly
	@DatabaseField(columnName = "Gly")
	private Double Gly;

	// Pro
	@DatabaseField(columnName = "Pro")
	private Double Pro;

	// Ser
	@DatabaseField(columnName = "Ser")
	private Double Ser;

	// FattyAcidTotal
	@DatabaseField(columnName = "FattyAcidTotal")
	private String FattyAcidTotal;

	// SFA
	@DatabaseField(columnName = "SFA")
	private String SFA;

	// MUFA
	@DatabaseField(columnName = "MUFA")
	private String MUFA;

	// PUFA
	@DatabaseField(columnName = "PUFA")
	private String PUFA;

	// UNK
	@DatabaseField(columnName = "UNK")
	private String UNK;

	// SFADvideTotal
	@DatabaseField(columnName = "SFADvideTotal")
	private String SFADvideTotal;

	// SFADvide60
	@DatabaseField(columnName = "SFADvide60")
	private String SFADvide60;

	// SFADvide80
	@DatabaseField(columnName = "SFADvide80")
	private String SFADvide80;

	// SFADvide100
	@DatabaseField(columnName = "SFADvide100")
	private String SFADvide100;

	// SFADvide110
	@DatabaseField(columnName = "SFADvide110")
	private String SFADvide110;

	// SFADvide120
	@DatabaseField(columnName = "SFADvide120")
	private String SFADvide120;

	// SFADvide130
	@DatabaseField(columnName = "SFADvide130")
	private String SFADvide130;

	// SFADvide140
	@DatabaseField(columnName = "SFADvide140")
	private String SFADvide140;

	// SFADvide150
	@DatabaseField(columnName = "SFADvide150")
	private String SFADvide150;

	// SFADvide160
	@DatabaseField(columnName = "SFADvide160")
	private String SFADvide160;

	// SFADvide170
	@DatabaseField(columnName = "SFADvide170")
	private String SFADvide170;

	// SFADvide180
	@DatabaseField(columnName = "SFADvide180")
	private String SFADvide180;

	// SFADvide190
	@DatabaseField(columnName = "SFADvide190")
	private String SFADvide190;

	// SFADvide200
	@DatabaseField(columnName = "SFADvide200")
	private String SFADvide200;

	// SFADvide220
	@DatabaseField(columnName = "SFADvide220")
	private String SFADvide220;

	// SFADvide240
	@DatabaseField(columnName = "SFADvide240")
	private String SFADvide240;

	// MUFADvideTotal
	@DatabaseField(columnName = "MUFADvideTotal")
	private String MUFADvideTotal;

	// MUFADvide141
	@DatabaseField(columnName = "MUFADvide141")
	private String MUFADvide141;

	// MUFADvide161
	@DatabaseField(columnName = "MUFADvide161")
	private String MUFADvide161;

	// MUFADvide171
	@DatabaseField(columnName = "MUFADvide171")
	private String MUFADvide171;

	// MUFADvide181
	@DatabaseField(columnName = "MUFADvide181")
	private String MUFADvide181;

	// MUFADvide201
	@DatabaseField(columnName = "MUFADvide201")
	private String MUFADvide201;

	// MUFADvide221
	@DatabaseField(columnName = "MUFADvide221")
	private String MUFADvide221;

	// MUFADvide241
	@DatabaseField(columnName = "MUFADvide241")
	private String MUFADvide241;

	// PUFADvideTotal
	@DatabaseField(columnName = "PUFADvideTotal")
	private String PUFADvideTotal;

	// PUFADivde162
	@DatabaseField(columnName = "PUFADivde162")
	private String PUFADivde162;

	// PUFADivde182
	@DatabaseField(columnName = "PUFADivde182")
	private String PUFADivde182;

	// PUFADivde183
	@DatabaseField(columnName = "PUFADivde183")
	private String PUFADivde183;

	// PUFADivde184
	@DatabaseField(columnName = "PUFADivde184")
	private String PUFADivde184;

	// PUFADivde202
	@DatabaseField(columnName = "PUFADivde202")
	private String PUFADivde202;

	// PUFADivde203
	@DatabaseField(columnName = "PUFADivde203")
	private String PUFADivde203;

	// PUFADivde204
	@DatabaseField(columnName = "PUFADivde204")
	private String PUFADivde204;

	// PUFADivde205
	@DatabaseField(columnName = "PUFADivde205")
	private String PUFADivde205;

	// PUFADivde223
	@DatabaseField(columnName = "PUFADivde223")
	private String PUFADivde223;

	// PUFADivde224
	@DatabaseField(columnName = "PUFADivde224")
	private String PUFADivde224;

	// PUFADivde225
	@DatabaseField(columnName = "PUFADivde225")
	private String PUFADivde225;

	// PUFADivde226
	@DatabaseField(columnName = "PUFADivde226")
	private String PUFADivde226;

	// UNKDvide
	@DatabaseField(columnName = "UNKDvide")
	private String UNKDvide;

	// Cholin
	@DatabaseField(columnName = "Cholin")
	private Double Cholin;

	// Biotin
	@DatabaseField(columnName = "Biotin")
	private Double Biotin;

	// PantothenicAcid
	@DatabaseField(columnName = "PantothenicAcid")
	private Double PantothenicAcid;

	// VitK
	@DatabaseField(columnName = "VitK")
	private Double VitK;

	// VitD
	@DatabaseField(columnName = "VitD")
	private Double VitD;

	// IsActive
	@DatabaseField(columnName = "IsActive")
	private String IsActive;

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

	// FoodEdiblePart
	@DatabaseField(columnName = "FoodEdiblePart")
	private Double FoodEdiblePart;

	// CurrentMealAmount
	@DatabaseField(columnName = "CurrentMealAmount")
	private Double CurrentMealAmount;

	// NutrientProductSpecification
	@DatabaseField(columnName = "NutrientProductSpecification")
	private Double NutrientProductSpecification;

	// WrapperType 0 、拆分(不用规格) 1、原包装
	@DatabaseField(columnName = "WrapperType")
	private int WrapperType;

	// RecipeAndProduct_DBKey
	@DatabaseField(columnName = "RecipeAndProduct_DBKey")
	private int RecipeAndProduct_DBKey;

	// MeasureUnitName
	@DatabaseField(columnName = "MeasureUnitName")
	private String MeasureUnitName;

	// BaseUnitName
	@DatabaseField(columnName = "BaseUnitName")
	private String BaseUnitName;

	// MinUnitName
	@DatabaseField(columnName = "MinUnitName")
	private String MinUnitName;

	// MeasureUnit_DBKey
	@DatabaseField(columnName = "MeasureUnit_DBKey")
	private String MeasureUnit_DBKey;

	// BaseUnit_DBKey
	@DatabaseField(columnName = "BaseUnit_DBKey")
	private String BaseUnit_DBKey;

	// MinUnit_DBKey
	@DatabaseField(columnName = "MinUnit_DBKey")
	private String MinUnit_DBKey;

	// RecipeAndProductPrice
	@DatabaseField(columnName = "RecipeAndProductPrice")
	private Double RecipeAndProductPrice;

	// MinNum
	@DatabaseField(columnName = "MinNum")
	private Double MinNum;

	public int getChinaFoodComposition_DBKey() {
		return ChinaFoodComposition_DBKey;
	}

	public void setChinaFoodComposition_DBKey(int ChinaFoodComposition_DBKey) {
		this.ChinaFoodComposition_DBKey = ChinaFoodComposition_DBKey;
	}

	public int getFoodCategory_DBKey() {
		return FoodCategory_DBKey;
	}

	public void setFoodCategory_DBKey(int FoodCategory_DBKey) {
		this.FoodCategory_DBKey = FoodCategory_DBKey;
	}

	public String getFoodCode() {
		return FoodCode;
	}

	public void setFoodCode(String FoodCode) {
		this.FoodCode = FoodCode;
	}

	public String getFoodName() {
		return FoodName;
	}

	public void setFoodName(String FoodName) {
		this.FoodName = FoodName;
	}

	public String getFoodFirstLetter() {
		return FoodFirstLetter;
	}

	public void setFoodFirstLetter(String FoodFirstLetter) {
		this.FoodFirstLetter = FoodFirstLetter;
	}

	public int getFoodTableInsideType() {
		return FoodTableInsideType;
	}

	public void setFoodTableInsideType(int FoodTableInsideType) {
		this.FoodTableInsideType = FoodTableInsideType;
	}

	public Double getWater() {
		return Water;
	}

	public void setWater(Double Water) {
		this.Water = Water;
	}

	public Double getEnergy() {
		if (Energy == null) {
			return 0.0;
		}
		return Energy;
	}

	public void setEnergy(Double Energy) {
		this.Energy = Energy;
	}

	public Double getEnergyKJ() {
		return EnergyKJ;
	}

	public void setEnergyKJ(Double EnergyKJ) {
		this.EnergyKJ = EnergyKJ;
	}

	public Double getProtein() {
		if (Protein == null) {
			return 0.0;
		}
		return Protein;
	}

	public void setProtein(Double Protein) {
		this.Protein = Protein;
	}

	public Double getFat() {
		if (Fat == null) {
			return 0.0;
		}
		return Fat;
	}

	public void setFat(Double Fat) {
		this.Fat = Fat;
	}

	public Double getCarbohydrate() {
		if (Carbohydrate == null) {
			return 0.0;
		}
		return Carbohydrate;
	}

	public void setCarbohydrate(Double Carbohydrate) {
		this.Carbohydrate = Carbohydrate;
	}

	public Double getTotalDietaryFiber() {
		if (TotalDietaryFiber == null) {
			return 0.0;
		}
		return TotalDietaryFiber;
	}

	public void setTotalDietaryFiber(Double TotalDietaryFiber) {
		this.TotalDietaryFiber = TotalDietaryFiber;
	}

	public Double getSolubleDietaryFiber() {
		return SolubleDietaryFiber;
	}

	public void setSolubleDietaryFiber(Double SolubleDietaryFiber) {
		this.SolubleDietaryFiber = SolubleDietaryFiber;
	}

	public Double getInsolubleDietaryFiber() {
		return InsolubleDietaryFiber;
	}

	public void setInsolubleDietaryFiber(Double InsolubleDietaryFiber) {
		this.InsolubleDietaryFiber = InsolubleDietaryFiber;
	}

	public Double getAsh() {
		return Ash;
	}

	public void setAsh(Double Ash) {
		this.Ash = Ash;
	}

	public Double getCholesterol() {
		return Cholesterol;
	}

	public void setCholesterol(Double Cholesterol) {
		this.Cholesterol = Cholesterol;
	}

	public Double getVitaminA() {
		return VitaminA;
	}

	public void setVitaminA(Double VitaminA) {
		this.VitaminA = VitaminA;
	}

	public Double getTotalCarotene() {
		return TotalCarotene;
	}

	public void setTotalCarotene(Double TotalCarotene) {
		this.TotalCarotene = TotalCarotene;
	}

	public Double getThiamin() {
		return Thiamin;
	}

	public void setThiamin(Double Thiamin) {
		this.Thiamin = Thiamin;
	}

	public Double getRiboflavin() {
		return Riboflavin;
	}

	public void setRiboflavin(Double Riboflavin) {
		this.Riboflavin = Riboflavin;
	}

	public Double getVitaminB6() {
		return VitaminB6;
	}

	public void setVitaminB6(Double VitaminB6) {
		this.VitaminB6 = VitaminB6;
	}

	public Double getVitaminB12() {
		return VitaminB12;
	}

	public void setVitaminB12(Double VitaminB12) {
		this.VitaminB12 = VitaminB12;
	}

	public Double getFolate() {
		return Folate;
	}

	public void setFolate(Double Folate) {
		this.Folate = Folate;
	}

	public Double getNiacin() {
		return Niacin;
	}

	public void setNiacin(Double Niacin) {
		this.Niacin = Niacin;
	}

	public Double getVitaminC() {
		if (VitaminC == null) {
			return 0.0;
		}
		return VitaminC;
	}

	public void setVitaminC(Double VitaminC) {
		this.VitaminC = VitaminC;
	}

	public Double getVitaminETotal() {
		return VitaminETotal;
	}

	public void setVitaminETotal(Double VitaminETotal) {
		this.VitaminETotal = VitaminETotal;
	}

	public Double getVitaminETE() {
		return VitaminETE;
	}

	public void setVitaminETE(Double VitaminETE) {
		this.VitaminETE = VitaminETE;
	}

	public Double getCa() {
		if (Ca == null) {
			return 0.0;
		}
		return Ca;
	}

	public void setCa(Double Ca) {
		this.Ca = Ca;
	}

	public Double getP() {
		if (P == null) {
			return 0.0;
		}
		return P;
	}

	public void setP(Double P) {
		this.P = P;
	}

	public Double getK() {
		if (K == null) {
			return 0.0;
		}
		return K;
	}

	public void setK(Double K) {
		this.K = K;
	}

	public Double getNa() {
		if (Na == null) {
			return 0.0;
		}
		return Na;
	}

	public void setNa(Double Na) {
		this.Na = Na;
	}

	public Double getMg() {
		if (Mg == null) {
			return 0.0;
		}
		return Mg;
	}

	public void setMg(Double Mg) {
		this.Mg = Mg;
	}

	public Double getFe() {
		if (Fe == null) {
			return 0.0;
		}
		return Fe;
	}

	public void setFe(Double Fe) {
		this.Fe = Fe;
	}

	public Double getZn() {
		return Zn;
	}

	public void setZn(Double Zn) {
		this.Zn = Zn;
	}

	public Double getSe() {
		return Se;
	}

	public void setSe(Double Se) {
		this.Se = Se;
	}

	public Double getCu() {
		return Cu;
	}

	public void setCu(Double Cu) {
		this.Cu = Cu;
	}

	public Double getMn() {
		return Mn;
	}

	public void setMn(Double Mn) {
		this.Mn = Mn;
	}

	public Double getI() {
		return I;
	}

	public void setI(Double I) {
		this.I = I;
	}

	public Double getIle() {
		return Ile;
	}

	public void setIle(Double Ile) {
		this.Ile = Ile;
	}

	public Double getLeu() {
		return Leu;
	}

	public void setLeu(Double Leu) {
		this.Leu = Leu;
	}

	public Double getLys() {
		return Lys;
	}

	public void setLys(Double Lys) {
		this.Lys = Lys;
	}

	public Double getSAATotal() {
		return SAATotal;
	}

	public void setSAATotal(Double SAATotal) {
		this.SAATotal = SAATotal;
	}

	public Double getMet() {
		return Met;
	}

	public void setMet(Double Met) {
		this.Met = Met;
	}

	public Double getCys() {
		return Cys;
	}

	public void setCys(Double Cys) {
		this.Cys = Cys;
	}

	public Double getAAA() {
		return AAA;
	}

	public void setAAA(Double AAA) {
		this.AAA = AAA;
	}

	public Double getPhe() {
		return Phe;
	}

	public void setPhe(Double Phe) {
		this.Phe = Phe;
	}

	public Double getTyr() {
		return Tyr;
	}

	public void setTyr(Double Tyr) {
		this.Tyr = Tyr;
	}

	public Double getThr() {
		return Thr;
	}

	public void setThr(Double Thr) {
		this.Thr = Thr;
	}

	public Double getTrp() {
		return Trp;
	}

	public void setTrp(Double Trp) {
		this.Trp = Trp;
	}

	public Double getVal() {
		return Val;
	}

	public void setVal(Double Val) {
		this.Val = Val;
	}

	public Double getArg() {
		return Arg;
	}

	public void setArg(Double Arg) {
		this.Arg = Arg;
	}

	public Double getHis() {
		return His;
	}

	public void setHis(Double His) {
		this.His = His;
	}

	public Double getAla() {
		return Ala;
	}

	public void setAla(Double Ala) {
		this.Ala = Ala;
	}

	public Double getAsp() {
		return Asp;
	}

	public void setAsp(Double Asp) {
		this.Asp = Asp;
	}

	public Double getGlu() {
		return Glu;
	}

	public void setGlu(Double Glu) {
		this.Glu = Glu;
	}

	public Double getGly() {
		return Gly;
	}

	public void setGly(Double Gly) {
		this.Gly = Gly;
	}

	public Double getPro() {
		return Pro;
	}

	public void setPro(Double Pro) {
		this.Pro = Pro;
	}

	public Double getSer() {
		return Ser;
	}

	public void setSer(Double Ser) {
		this.Ser = Ser;
	}

	public String getFattyAcidTotal() {
		return FattyAcidTotal;
	}

	public void setFattyAcidTotal(String FattyAcidTotal) {
		this.FattyAcidTotal = FattyAcidTotal;
	}

	public String getSFA() {
		return SFA;
	}

	public void setSFA(String SFA) {
		this.SFA = SFA;
	}

	public String getMUFA() {
		return MUFA;
	}

	public void setMUFA(String MUFA) {
		this.MUFA = MUFA;
	}

	public String getPUFA() {
		return PUFA;
	}

	public void setPUFA(String PUFA) {
		this.PUFA = PUFA;
	}

	public String getUNK() {
		return UNK;
	}

	public void setUNK(String UNK) {
		this.UNK = UNK;
	}

	public String getSFADvideTotal() {
		return SFADvideTotal;
	}

	public void setSFADvideTotal(String SFADvideTotal) {
		this.SFADvideTotal = SFADvideTotal;
	}

	public String getSFADvide60() {
		return SFADvide60;
	}

	public void setSFADvide60(String SFADvide60) {
		this.SFADvide60 = SFADvide60;
	}

	public String getSFADvide80() {
		return SFADvide80;
	}

	public void setSFADvide80(String SFADvide80) {
		this.SFADvide80 = SFADvide80;
	}

	public String getSFADvide100() {
		return SFADvide100;
	}

	public void setSFADvide100(String SFADvide100) {
		this.SFADvide100 = SFADvide100;
	}

	public String getSFADvide110() {
		return SFADvide110;
	}

	public void setSFADvide110(String SFADvide110) {
		this.SFADvide110 = SFADvide110;
	}

	public String getSFADvide120() {
		return SFADvide120;
	}

	public void setSFADvide120(String SFADvide120) {
		this.SFADvide120 = SFADvide120;
	}

	public String getSFADvide130() {
		return SFADvide130;
	}

	public void setSFADvide130(String SFADvide130) {
		this.SFADvide130 = SFADvide130;
	}

	public String getSFADvide140() {
		return SFADvide140;
	}

	public void setSFADvide140(String SFADvide140) {
		this.SFADvide140 = SFADvide140;
	}

	public String getSFADvide150() {
		return SFADvide150;
	}

	public void setSFADvide150(String SFADvide150) {
		this.SFADvide150 = SFADvide150;
	}

	public String getSFADvide160() {
		return SFADvide160;
	}

	public void setSFADvide160(String SFADvide160) {
		this.SFADvide160 = SFADvide160;
	}

	public String getSFADvide170() {
		return SFADvide170;
	}

	public void setSFADvide170(String SFADvide170) {
		this.SFADvide170 = SFADvide170;
	}

	public String getSFADvide180() {
		return SFADvide180;
	}

	public void setSFADvide180(String SFADvide180) {
		this.SFADvide180 = SFADvide180;
	}

	public String getSFADvide190() {
		return SFADvide190;
	}

	public void setSFADvide190(String SFADvide190) {
		this.SFADvide190 = SFADvide190;
	}

	public String getSFADvide200() {
		return SFADvide200;
	}

	public void setSFADvide200(String SFADvide200) {
		this.SFADvide200 = SFADvide200;
	}

	public String getSFADvide220() {
		return SFADvide220;
	}

	public void setSFADvide220(String SFADvide220) {
		this.SFADvide220 = SFADvide220;
	}

	public String getSFADvide240() {
		return SFADvide240;
	}

	public void setSFADvide240(String SFADvide240) {
		this.SFADvide240 = SFADvide240;
	}

	public String getMUFADvideTotal() {
		return MUFADvideTotal;
	}

	public void setMUFADvideTotal(String MUFADvideTotal) {
		this.MUFADvideTotal = MUFADvideTotal;
	}

	public String getMUFADvide141() {
		return MUFADvide141;
	}

	public void setMUFADvide141(String MUFADvide141) {
		this.MUFADvide141 = MUFADvide141;
	}

	public String getMUFADvide161() {
		return MUFADvide161;
	}

	public void setMUFADvide161(String MUFADvide161) {
		this.MUFADvide161 = MUFADvide161;
	}

	public String getMUFADvide171() {
		return MUFADvide171;
	}

	public void setMUFADvide171(String MUFADvide171) {
		this.MUFADvide171 = MUFADvide171;
	}

	public String getMUFADvide181() {
		return MUFADvide181;
	}

	public void setMUFADvide181(String MUFADvide181) {
		this.MUFADvide181 = MUFADvide181;
	}

	public String getMUFADvide201() {
		return MUFADvide201;
	}

	public void setMUFADvide201(String MUFADvide201) {
		this.MUFADvide201 = MUFADvide201;
	}

	public String getMUFADvide221() {
		return MUFADvide221;
	}

	public void setMUFADvide221(String MUFADvide221) {
		this.MUFADvide221 = MUFADvide221;
	}

	public String getMUFADvide241() {
		return MUFADvide241;
	}

	public void setMUFADvide241(String MUFADvide241) {
		this.MUFADvide241 = MUFADvide241;
	}

	public String getPUFADvideTotal() {
		return PUFADvideTotal;
	}

	public void setPUFADvideTotal(String PUFADvideTotal) {
		this.PUFADvideTotal = PUFADvideTotal;
	}

	public String getPUFADivde162() {
		return PUFADivde162;
	}

	public void setPUFADivde162(String PUFADivde162) {
		this.PUFADivde162 = PUFADivde162;
	}

	public String getPUFADivde182() {
		return PUFADivde182;
	}

	public void setPUFADivde182(String PUFADivde182) {
		this.PUFADivde182 = PUFADivde182;
	}

	public String getPUFADivde183() {
		return PUFADivde183;
	}

	public void setPUFADivde183(String PUFADivde183) {
		this.PUFADivde183 = PUFADivde183;
	}

	public String getPUFADivde184() {
		return PUFADivde184;
	}

	public void setPUFADivde184(String PUFADivde184) {
		this.PUFADivde184 = PUFADivde184;
	}

	public String getPUFADivde202() {
		return PUFADivde202;
	}

	public void setPUFADivde202(String PUFADivde202) {
		this.PUFADivde202 = PUFADivde202;
	}

	public String getPUFADivde203() {
		return PUFADivde203;
	}

	public void setPUFADivde203(String PUFADivde203) {
		this.PUFADivde203 = PUFADivde203;
	}

	public String getPUFADivde204() {
		return PUFADivde204;
	}

	public void setPUFADivde204(String PUFADivde204) {
		this.PUFADivde204 = PUFADivde204;
	}

	public String getPUFADivde205() {
		return PUFADivde205;
	}

	public void setPUFADivde205(String PUFADivde205) {
		this.PUFADivde205 = PUFADivde205;
	}

	public String getPUFADivde223() {
		return PUFADivde223;
	}

	public void setPUFADivde223(String PUFADivde223) {
		this.PUFADivde223 = PUFADivde223;
	}

	public String getPUFADivde224() {
		return PUFADivde224;
	}

	public void setPUFADivde224(String PUFADivde224) {
		this.PUFADivde224 = PUFADivde224;
	}

	public String getPUFADivde225() {
		return PUFADivde225;
	}

	public void setPUFADivde225(String PUFADivde225) {
		this.PUFADivde225 = PUFADivde225;
	}

	public String getPUFADivde226() {
		return PUFADivde226;
	}

	public void setPUFADivde226(String PUFADivde226) {
		this.PUFADivde226 = PUFADivde226;
	}

	public String getUNKDvide() {
		return UNKDvide;
	}

	public void setUNKDvide(String UNKDvide) {
		this.UNKDvide = UNKDvide;
	}

	public Double getCholin() {
		return Cholin;
	}

	public void setCholin(Double Cholin) {
		this.Cholin = Cholin;
	}

	public Double getBiotin() {
		return Biotin;
	}

	public void setBiotin(Double Biotin) {
		this.Biotin = Biotin;
	}

	public Double getPantothenicAcid() {
		return PantothenicAcid;
	}

	public void setPantothenicAcid(Double PantothenicAcid) {
		this.PantothenicAcid = PantothenicAcid;
	}

	public Double getVitK() {
		return VitK;
	}

	public void setVitK(Double VitK) {
		this.VitK = VitK;
	}

	public Double getVitD() {
		return VitD;
	}

	public void setVitD(Double VitD) {
		this.VitD = VitD;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String IsActive) {
		this.IsActive = IsActive;
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

	public Double getFoodEdiblePart() {
		return FoodEdiblePart;
	}

	public void setFoodEdiblePart(Double FoodEdiblePart) {
		this.FoodEdiblePart = FoodEdiblePart;
	}

	@Override
	public String toString() {
		return "chinafoodcomposition [ChinaFoodComposition_DBKey="
				+ ChinaFoodComposition_DBKey + ",FoodCategory_DBKey="
				+ FoodCategory_DBKey + ",FoodCode=" + FoodCode + ",FoodName="
				+ FoodName + ",FoodFirstLetter=" + FoodFirstLetter
				+ ",FoodTableInsideType=" + FoodTableInsideType + ",Water="
				+ Water + ",Energy=" + Energy + ",EnergyKJ=" + EnergyKJ
				+ ",Protein=" + Protein + ",Fat=" + Fat + ",Carbohydrate="
				+ Carbohydrate + ",TotalDietaryFiber=" + TotalDietaryFiber
				+ ",SolubleDietaryFiber=" + SolubleDietaryFiber
				+ ",InsolubleDietaryFiber=" + InsolubleDietaryFiber + ",Ash="
				+ Ash + ",Cholesterol=" + Cholesterol + ",VitaminA=" + VitaminA
				+ ",TotalCarotene=" + TotalCarotene + ",Thiamin=" + Thiamin
				+ ",Riboflavin=" + Riboflavin + ",VitaminB6=" + VitaminB6
				+ ",VitaminB12=" + VitaminB12 + ",Folate=" + Folate
				+ ",Niacin=" + Niacin + ",VitaminC=" + VitaminC
				+ ",VitaminETotal=" + VitaminETotal + ",VitaminETE="
				+ VitaminETE + ",Ca=" + Ca + ",P=" + P + ",K=" + K + ",Na="
				+ Na + ",Mg=" + Mg + ",Fe=" + Fe + ",Zn=" + Zn + ",Se=" + Se
				+ ",Cu=" + Cu + ",Mn=" + Mn + ",I=" + I + ",Ile=" + Ile
				+ ",Leu=" + Leu + ",Lys=" + Lys + ",SAATotal=" + SAATotal
				+ ",Met=" + Met + ",Cys=" + Cys + ",AAA=" + AAA + ",Phe=" + Phe
				+ ",Tyr=" + Tyr + ",Thr=" + Thr + ",Trp=" + Trp + ",Val=" + Val
				+ ",Arg=" + Arg + ",His=" + His + ",Ala=" + Ala + ",Asp=" + Asp
				+ ",Glu=" + Glu + ",Gly=" + Gly + ",Pro=" + Pro + ",Ser=" + Ser
				+ ",FattyAcidTotal=" + FattyAcidTotal + ",SFA=" + SFA
				+ ",MUFA=" + MUFA + ",PUFA=" + PUFA + ",UNK=" + UNK
				+ ",SFADvideTotal=" + SFADvideTotal + ",SFADvide60="
				+ SFADvide60 + ",SFADvide80=" + SFADvide80 + ",SFADvide100="
				+ SFADvide100 + ",SFADvide110=" + SFADvide110 + ",SFADvide120="
				+ SFADvide120 + ",SFADvide130=" + SFADvide130 + ",SFADvide140="
				+ SFADvide140 + ",SFADvide150=" + SFADvide150 + ",SFADvide160="
				+ SFADvide160 + ",SFADvide170=" + SFADvide170 + ",SFADvide180="
				+ SFADvide180 + ",SFADvide190=" + SFADvide190 + ",SFADvide200="
				+ SFADvide200 + ",SFADvide220=" + SFADvide220 + ",SFADvide240="
				+ SFADvide240 + ",MUFADvideTotal=" + MUFADvideTotal
				+ ",MUFADvide141=" + MUFADvide141 + ",MUFADvide161="
				+ MUFADvide161 + ",MUFADvide171=" + MUFADvide171
				+ ",MUFADvide181=" + MUFADvide181 + ",MUFADvide201="
				+ MUFADvide201 + ",MUFADvide221=" + MUFADvide221
				+ ",MUFADvide241=" + MUFADvide241 + ",PUFADvideTotal="
				+ PUFADvideTotal + ",PUFADivde162=" + PUFADivde162
				+ ",PUFADivde182=" + PUFADivde182 + ",PUFADivde183="
				+ PUFADivde183 + ",PUFADivde184=" + PUFADivde184
				+ ",PUFADivde202=" + PUFADivde202 + ",PUFADivde203="
				+ PUFADivde203 + ",PUFADivde204=" + PUFADivde204
				+ ",PUFADivde205=" + PUFADivde205 + ",PUFADivde223="
				+ PUFADivde223 + ",PUFADivde224=" + PUFADivde224
				+ ",PUFADivde225=" + PUFADivde225 + ",PUFADivde226="
				+ PUFADivde226 + ",UNKDvide=" + UNKDvide + ",Cholin=" + Cholin
				+ ",Biotin=" + Biotin + ",PantothenicAcid=" + PantothenicAcid
				+ ",VitK=" + VitK + ",VitD=" + VitD + ",IsActive=" + IsActive
				+ ",CreateBy=" + CreateBy + ",CreateTime=" + CreateTime
				+ ",CreateProgram=" + CreateProgram + ",CreateIP=" + CreateIP
				+ ",UpdateBy=" + UpdateBy + ",UpdateTime=" + UpdateTime
				+ ",UpdateProgram=" + UpdateProgram + ",UpdateIP=" + UpdateIP
				+ ",FoodEdiblePart=" + FoodEdiblePart + ",]";
	}

	public Double getCurrentMealAmount() {
		if (CurrentMealAmount == null) {
			return 0.0;
		}
		return CurrentMealAmount;
	}

	public void setCurrentMealAmount(Double currentMealAmount) {
		CurrentMealAmount = currentMealAmount;
	}

	public Double getNutrientProductSpecification() {
		return NutrientProductSpecification;
	}

	public void setNutrientProductSpecification(
			Double nutrientProductSpecification) {
		NutrientProductSpecification = nutrientProductSpecification;
	}

	public int getWrapperType() {
		return WrapperType;
	}

	public void setWrapperType(int wrapperType) {
		WrapperType = wrapperType;
	}

	public int getRecipeAndProduct_DBKey() {
		return RecipeAndProduct_DBKey;
	}

	public void setRecipeAndProduct_DBKey(int recipeAndProduct_DBKey) {
		RecipeAndProduct_DBKey = recipeAndProduct_DBKey;
	}

	public String getMeasureUnitName() {
		return MeasureUnitName;
	}

	public void setMeasureUnitName(String measureUnitName) {
		MeasureUnitName = measureUnitName;
	}

	public Double getRecipeAndProductPrice() {
		return RecipeAndProductPrice;
	}

	public void setRecipeAndProductPrice(Double recipeAndProductPrice) {
		RecipeAndProductPrice = recipeAndProductPrice;
	}

	public String getBaseUnitName() {
		return BaseUnitName;
	}

	public void setBaseUnitName(String baseUnitName) {
		BaseUnitName = baseUnitName;
	}

	public String getMinUnitName() {
		return MinUnitName;
	}

	public void setMinUnitName(String minUnitName) {
		MinUnitName = minUnitName;
	}

	public String getMeasureUnit_DBKey() {
		return MeasureUnit_DBKey;
	}

	public void setMeasureUnit_DBKey(String measureUnit_DBKey) {
		MeasureUnit_DBKey = measureUnit_DBKey;
	}

	public String getBaseUnit_DBKey() {
		return BaseUnit_DBKey;
	}

	public void setBaseUnit_DBKey(String baseUnit_DBKey) {
		BaseUnit_DBKey = baseUnit_DBKey;
	}

	public String getMinUnit_DBKey() {
		return MinUnit_DBKey;
	}

	public void setMinUnit_DBKey(String minUnit_DBKey) {
		MinUnit_DBKey = minUnit_DBKey;
	}

	public Double getMinNum() {
		return MinNum;
	}

	public void setMinNum(Double minNum) {
		MinNum = minNum;
	}
}
