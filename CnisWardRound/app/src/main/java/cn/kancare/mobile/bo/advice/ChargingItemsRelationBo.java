package cn.kancare.mobile.bo.advice;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.kancare.mobile.bean.advice.ChargingItems;
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

    public List<ChargingItems> queryRelationItems(ChargingItemsBo chargingItemsBo,
            int RecipeAndProduct_DBKey) throws Exception {
        List<ChargingItems> lstChargingItems = new ArrayList<ChargingItems>();
        List<ChargingItemsRelation> lst = dao.queryRelationItems(RecipeAndProduct_DBKey);
        for (ChargingItemsRelation re : lst) {
            ChargingItems chargingItems = chargingItemsBo.getDao().queryForId(re.getChargingItemID());
            if(chargingItems != null){
                lstChargingItems.add(chargingItems);
            }
        }

        return lstChargingItems;
    }

}
