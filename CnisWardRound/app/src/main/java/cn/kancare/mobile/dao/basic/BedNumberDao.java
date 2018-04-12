package cn.kancare.mobile.dao.basic;

import android.content.Context;
import cn.kancare.mobile.bean.basic.BedNumber;
import cn.kancare.mobile.core.base.BaseDao;

public class BedNumberDao  extends BaseDao<BedNumber>{

	public BedNumberDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "BedNumber_DBKey";
	}
}