package cn.kancare.mobile.dao.basic;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.util.List;

import cn.kancare.mobile.bean.basic.Diagnosis;
import cn.kancare.mobile.core.base.BaseDao;

public class DiagnosisDao  extends BaseDao<Diagnosis> {
    public DiagnosisDao(Context context) throws Exception {
        super(context);
    }

    @Override
    protected String getPrimaryKey() {
        return "ID";
    }

    public List<Diagnosis> query(int limit, int offset,
                                 String keyword) throws Exception {

        QueryBuilder<Diagnosis, Integer> qBuilder = dao.queryBuilder();
        Where<Diagnosis, Integer> where = qBuilder.where();

        if (!keyword.equals("")) {
//            keyword = "%" + keyword + "%";
//            where.or(where.like("InputCode", keyword),
//                    where.like("DiagnosisName", keyword)
//            );

        }
        qBuilder.limit(limit).offset(offset)
                .orderBy("ID", true);
        return qBuilder.query();

    }
}
