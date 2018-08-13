package cn.kancare.mobile.bean.advice;

import java.sql.Date;
import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import cn.kancare.mobile.core.base.BaseBean;

@DatabaseTable(tableName = "chargingadvicedetail")
public class ChargingAdviceDetail extends BaseBean {
    /**
     *
     */
    private static final long serialVersionUID = -3278786409890674018L;
    // NutrientAdviceDetail_DBKEY
    @DatabaseField(columnName = "NutrientAdviceDetail_DBKEY", id = true)
    private int NutrientAdviceDetail_DBKEY;

    // RecipeAndProduct_DBKey
    @DatabaseField(columnName = "RecipeAndProduct_DBKey")
    private int RecipeAndProduct_DBKey;

    // ChargingItemID
    @DatabaseField(columnName = "ChargingItemID")
    private int ChargingItemID;

    // ChargingItemName
    @DatabaseField(columnName = "ChargingItemName")
    private String ChargingItemName;

    // ChargingItemSpec
    @DatabaseField(columnName = "ChargingItemSpec")
    private String ChargingItemSpec;

    // ChargingItemUnit
    @DatabaseField(columnName = "ChargingItemUnit")
    private String ChargingItemUnit;

    // ChargingNum
    @DatabaseField(columnName = "ChargingNum")
    private int ChargingNum;

    // ChargingPrice
    @DatabaseField(columnName = "ChargingPrice")
    private Double ChargingPrice;

    // ChargingMoney
    @DatabaseField(columnName = "ChargingMoney")
    private Double ChargingMoney;


    public int getNutrientAdviceDetail_DBKEY() {
        return NutrientAdviceDetail_DBKEY;
    }

    public void setNutrientAdviceDetail_DBKEY(int NutrientAdviceDetail_DBKEY) {
        this.NutrientAdviceDetail_DBKEY = NutrientAdviceDetail_DBKEY;
    }

    public int getRecipeAndProduct_DBKey() {
        return RecipeAndProduct_DBKey;
    }

    public void setRecipeAndProduct_DBKey(int RecipeAndProduct_DBKey) {
        this.RecipeAndProduct_DBKey = RecipeAndProduct_DBKey;
    }

    public int getChargingItemID() {
        return ChargingItemID;
    }

    public void setChargingItemID(int ChargingItemID) {
        this.ChargingItemID = ChargingItemID;
    }

    public String getChargingItemName() {
        return ChargingItemName;
    }

    public void setChargingItemName(String ChargingItemName) {
        this.ChargingItemName = ChargingItemName;
    }

    public String getChargingItemSpec() {
        return ChargingItemSpec;
    }

    public void setChargingItemSpec(String ChargingItemSpec) {
        this.ChargingItemSpec = ChargingItemSpec;
    }

    public String getChargingItemUnit() {
        return ChargingItemUnit;
    }

    public void setChargingItemUnit(String ChargingItemUnit) {
        this.ChargingItemUnit = ChargingItemUnit;
    }

    public int getChargingNum() {
        return ChargingNum;
    }

    public void setChargingNum(int ChargingNum) {
        this.ChargingNum = ChargingNum;
    }

    public Double getChargingPrice() {
        return ChargingPrice;
    }

    public void setChargingPrice(Double ChargingPrice) {
        this.ChargingPrice = ChargingPrice;
    }

    public Double getChargingMoney() {
        return ChargingMoney;
    }

    public void setChargingMoney(Double ChargingMoney) {
        this.ChargingMoney = ChargingMoney;
    }


    @Override
    public String toString() {
        return "chargingadvicedetail [NutrientAdviceDetail_DBKEY=" + NutrientAdviceDetail_DBKEY + ",RecipeAndProduct_DBKey=" + RecipeAndProduct_DBKey + ",ChargingItemID=" + ChargingItemID + ",ChargingItemName=" + ChargingItemName + ",ChargingItemSpec=" + ChargingItemSpec + ",ChargingItemUnit=" + ChargingItemUnit + ",ChargingNum=" + ChargingNum + ",ChargingPrice=" + ChargingPrice + ",ChargingMoney=" + ChargingMoney + ",]";
    }
}


