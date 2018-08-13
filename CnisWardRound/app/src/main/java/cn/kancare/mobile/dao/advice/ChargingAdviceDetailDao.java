package cn.kancare.mobile.dao.advice;

import android.content.Context;
import cn.kancare.mobile.bean.advice.ChargingAdviceDetail;
import cn.kancare.mobile.core.base.BaseDao;

public class ChargingAdviceDetailDao extends BaseDao<ChargingAdviceDetail> {
    public ChargingAdviceDetailDao(Context context) throws Exception {
        super(context);
    }

    protected String getPrimaryKey() {
        return "NutrientAdviceDetail_DBKEY";
    }
}
