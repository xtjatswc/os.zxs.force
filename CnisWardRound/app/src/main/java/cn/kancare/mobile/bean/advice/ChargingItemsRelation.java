package cn.kancare.mobile.bean.advice;

import java.sql.Date;
import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "chargingitemsrelation")
public class ChargingItemsRelation implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3278786409890674018L;
    // RecipeAndProduct_DBKey
    @DatabaseField(columnName = "RecipeAndProduct_DBKey")
    private int RecipeAndProduct_DBKey;

    // ChargingItemID
    @DatabaseField(columnName = "ChargingItemID")
    private int ChargingItemID;


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


    @Override
    public String toString() {
        return "chargingitemsrelation [RecipeAndProduct_DBKey=" + RecipeAndProduct_DBKey + ",ChargingItemID=" + ChargingItemID + ",]";
    }
}


