
package cn.kancare.mobile.bean.advice;

import java.sql.Date;
import java.io.Serializable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "chargingitems")
public class ChargingItems  implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = -3278786409890674018L;
    // auto_increment
    @DatabaseField(columnName = "ChargingItemID",id=true)
    private int ChargingItemID;

    // ChargingItemCode
    @DatabaseField(columnName = "ChargingItemCode")
    private String ChargingItemCode;

    // ChargingItemName
    @DatabaseField(columnName = "ChargingItemName")
    private String ChargingItemName;

    // ChargingItemSpec
    @DatabaseField(columnName = "ChargingItemSpec")
    private String ChargingItemSpec;

    // ChargingItemUnit
    @DatabaseField(columnName = "ChargingItemUnit")
    private String ChargingItemUnit;

    // ChargingItemPrice1
    @DatabaseField(columnName = "ChargingItemPrice1")
    private Double ChargingItemPrice1;

    // ChargingItemPrice2
    @DatabaseField(columnName = "ChargingItemPrice2")
    private Double ChargingItemPrice2;

    // SortNo
    @DatabaseField(columnName = "SortNo")
    private Double SortNo;

    // Enabled
    @DatabaseField(columnName = "Enabled")
    private int Enabled;


    public int getChargingItemID()
    {
        return ChargingItemID;
    }

    public void setChargingItemID(int ChargingItemID)
    {
        this.ChargingItemID = ChargingItemID;
    }

    public String getChargingItemCode()
    {
        return ChargingItemCode;
    }

    public void setChargingItemCode(String ChargingItemCode)
    {
        this.ChargingItemCode = ChargingItemCode;
    }

    public String getChargingItemName()
    {
        return ChargingItemName;
    }

    public void setChargingItemName(String ChargingItemName)
    {
        this.ChargingItemName = ChargingItemName;
    }

    public String getChargingItemSpec()
    {
        return ChargingItemSpec;
    }

    public void setChargingItemSpec(String ChargingItemSpec)
    {
        this.ChargingItemSpec = ChargingItemSpec;
    }

    public String getChargingItemUnit()
    {
        return ChargingItemUnit;
    }

    public void setChargingItemUnit(String ChargingItemUnit)
    {
        this.ChargingItemUnit = ChargingItemUnit;
    }

    public Double getChargingItemPrice1()
    {
        return ChargingItemPrice1;
    }

    public void setChargingItemPrice1(Double ChargingItemPrice1)
    {
        this.ChargingItemPrice1 = ChargingItemPrice1;
    }

    public Double getChargingItemPrice2()
    {
        return ChargingItemPrice2;
    }

    public void setChargingItemPrice2(Double ChargingItemPrice2)
    {
        this.ChargingItemPrice2 = ChargingItemPrice2;
    }

    public Double getSortNo()
    {
        return SortNo;
    }

    public void setSortNo(Double SortNo)
    {
        this.SortNo = SortNo;
    }

    public int getEnabled()
    {
        return Enabled;
    }

    public void setEnabled(int Enabled)
    {
        this.Enabled = Enabled;
    }


    @Override
    public String toString()
    {
        return "chargingitems [ChargingItemID=" + ChargingItemID + ",ChargingItemCode=" + ChargingItemCode + ",ChargingItemName=" + ChargingItemName + ",ChargingItemSpec=" + ChargingItemSpec + ",ChargingItemUnit=" + ChargingItemUnit + ",ChargingItemPrice1=" + ChargingItemPrice1 + ",ChargingItemPrice2=" + ChargingItemPrice2 + ",SortNo=" + SortNo + ",Enabled=" + Enabled + ",]";
    }
}


