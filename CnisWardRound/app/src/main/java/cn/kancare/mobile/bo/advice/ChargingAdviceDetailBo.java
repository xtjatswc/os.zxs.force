package cn.kancare.mobile.bo.advice;

import android.app.Activity;

import com.alibaba.fastjson.JSON;

import java.util.List;

import cn.kancare.mobile.activity.advice.AdviceChargeRelationActivity;
import cn.kancare.mobile.bean.advice.ChargingAdviceDetail;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import cn.kancare.mobile.dao.advice.ChargingAdviceDetailDao;
import os.zxs.force.common.constant.SyncConstant;
import os.zxs.force.core.util.Convert;

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

    public  void saveAdviceRelation(AdviceChargeRelationActivity context) throws  Exception{
        ChargingAdviceDetail chargingAdviceDetail = dao.queryForId(context.NutrientAdviceDetail_DBKEY);
        Boolean isCreate = false;
        if(chargingAdviceDetail == null){
            isCreate = true;
            chargingAdviceDetail = new ChargingAdviceDetail();
        }

        chargingAdviceDetail.setNutrientAdviceDetail_DBKEY(context.NutrientAdviceDetail_DBKEY);
        chargingAdviceDetail.setRecipeAndProduct_DBKey(context.RecipeAndProduct_DBKey);
        chargingAdviceDetail.setChargingItemID(context.chargingItem.getChargingItemID());
        chargingAdviceDetail.setChargingItemName(context.chargingItem.getChargingItemName());
        chargingAdviceDetail.setChargingItemSpec(context.CurrSpec);
        chargingAdviceDetail.setChargingItemUnit(context.chargingItem.getChargingItemUnit());
        chargingAdviceDetail.setChargingNum(Convert.cash2Int(context.EditTextChargingItemNum.getText()));
        chargingAdviceDetail.setChargingPrice(Convert.cash2Double(context.EditTextChargingItemPrice.getText().toString()));
        chargingAdviceDetail.setChargingMoney(Convert.cash2Double(context.EditTextChargingItemMoney.getText().toString()));

        if(isCreate){
            chargingAdviceDetail.setOperateFlag(SyncConstant.OperateFlag.NEED_ADD_TO_SERVER);
            dao.create(chargingAdviceDetail);
        }else{
            chargingAdviceDetail.setOperateFlag(SyncConstant.OperateFlag.NEED_EDIT_TO_SERVER);
            dao.update(chargingAdviceDetail);
        }

    }
}
