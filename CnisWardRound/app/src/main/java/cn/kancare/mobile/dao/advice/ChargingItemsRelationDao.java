package cn.kancare.mobile.dao.advice;

import android.content.Context;
import cn.kancare.mobile.bean.advice.ChargingItemsRelation;
import cn.kancare.mobile.core.base.BaseDao;

public class ChargingItemsRelationDao extends BaseDao<ChargingItemsRelation> {
    public ChargingItemsRelationDao(Context context) throws Exception {
        super(context);
    }

    protected String getPrimaryKey() {
        return null;
    }
}
