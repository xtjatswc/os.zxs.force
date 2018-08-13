package cn.kancare.mobile.bo.advice;

import android.app.Activity;

import com.alibaba.fastjson.JSON;

import java.util.List;

import cn.kancare.mobile.bean.advice.ChargingItemsRelation;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.advice.ChargingItemsRelationDao;

public class ChargingItemsRelationBo extends BaseBo<ChargingItemsRelationDao> {
    public ChargingItemsRelationBo(Activity context) throws Exception {
        super(context);
    }

    protected void setDao() throws Exception {
        dao = new ChargingItemsRelationDao(context);
    }

    public void doDownloadJson(String json) throws Exception {
        if (json == "")
            return;

        List<ChargingItemsRelation> models = JSON.parseArray(json,
                ChargingItemsRelation.class);

        for (ChargingItemsRelation model : models) {
            try {
                dao.create(model);
            } catch (Exception e) {
                try {
                    dao.update(model);
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }
        }
    }

    public void doUploadResult(String json) throws Exception {

    }
}
