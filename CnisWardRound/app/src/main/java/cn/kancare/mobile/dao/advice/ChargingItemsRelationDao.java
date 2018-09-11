package cn.kancare.mobile.dao.advice;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import cn.kancare.mobile.bean.advice.ChargingItemsRelation;
import cn.kancare.mobile.bean.advice.NutrientAdviceDetail;
import cn.kancare.mobile.core.base.BaseDao;

public class ChargingItemsRelationDao extends BaseDao<ChargingItemsRelation> {
    public ChargingItemsRelationDao(Context context) throws Exception {
        super(context);
    }

    protected String getPrimaryKey() {
        return null;
    }

    public List<ChargingItemsRelation> queryRelationItems(
            int RecipeAndProduct_DBKey) throws SQLException {
        QueryBuilder<ChargingItemsRelation, Integer> queryBuilder = dao
                .queryBuilder();
        queryBuilder.where().eq("RecipeAndProduct_DBKey", RecipeAndProduct_DBKey);
        return queryBuilder.query();
    }
}
