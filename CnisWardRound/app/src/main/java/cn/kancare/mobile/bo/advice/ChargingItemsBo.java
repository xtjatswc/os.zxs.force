package cn.kancare.mobile.bo.advice;

import android.app.Activity;

import com.alibaba.fastjson.JSON;

import java.util.List;

import cn.kancare.mobile.bean.advice.ChargingItems;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.advice.ChargingItemsDao;

public class ChargingItemsBo extends BaseBo<ChargingItemsDao> {

    public ChargingItemsBo(Activity context) throws Exception {
        super(context);
    }

    protected void setDao() throws Exception {
        dao = new ChargingItemsDao(context);
    }

    public void doDownloadJson(String json) throws Exception {
        if (json.equals(""))
            return;

        List<ChargingItems> models = JSON.parseArray(json,
                ChargingItems.class);

        for (ChargingItems model : models) {
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
