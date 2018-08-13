package cn.kancare.mobile.bo.advice;

import android.app.Activity;

import com.alibaba.fastjson.JSON;

import java.util.List;

import cn.kancare.mobile.bean.advice.ChargingAdviceDetail;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import cn.kancare.mobile.dao.advice.ChargingAdviceDetailDao;
import os.zxs.force.common.constant.SyncConstant;

public class ChargingAdviceDetailBo extends BaseBo<ChargingAdviceDetailDao> {
    public ChargingAdviceDetailBo(Activity context) throws Exception {
        super(context);
    }

    protected void setDao() throws Exception {
        dao = new ChargingAdviceDetailDao(context);
    }

    public void doDownloadJson(String json) throws Exception {
        if (json.equals(""))
            return;

        List<ChargingAdviceDetail> models = JSON.parseArray(json,
                ChargingAdviceDetail.class);

        for (ChargingAdviceDetail model : models) {
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

    public void doUploadResult(String result) throws Exception {
        if (!result.equals("")) {
            List<DBKeyEntity> lstSyncResults = JSON.parseArray(result,
                    DBKeyEntity.class);
            for (DBKeyEntity dbKeyEntity : lstSyncResults) {
                ChargingAdviceDetail chargingAdviceDetail = dao.queryForId(dbKeyEntity
                        .getOldDBKey());
                chargingAdviceDetail.setOperateFlag(SyncConstant.OperateFlag.NONE);
                dao.update(chargingAdviceDetail);
            }
        }
    }
}
