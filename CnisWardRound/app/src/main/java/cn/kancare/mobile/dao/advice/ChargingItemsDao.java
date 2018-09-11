package cn.kancare.mobile.dao.advice;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import cn.kancare.mobile.bean.advice.ChargingItems;
import cn.kancare.mobile.bean.advice.NutrientAdviceDetail;
import cn.kancare.mobile.core.base.BaseDao;

public class ChargingItemsDao extends BaseDao<ChargingItems> {
    public ChargingItemsDao(Context context) throws Exception {
        super(context);
    }

    protected String getPrimaryKey() {
        return "ChargingItemID";
    }


}
