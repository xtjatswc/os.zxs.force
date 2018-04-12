package os.zxs.force.core.log;

import java.sql.SQLException;

import android.content.Context;
import android.util.Log;
import os.zxs.force.core.constant.LogLevel;
import os.zxs.force.core.util.DateHelper;

public class CnislogBo {
	private CnisLogDao dao;

	public CnislogBo(Context context) {
		try {
			dao = new CnisLogDao(context);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CnisLogDao getDao() {
		return dao;
	}

	public void info(String tag, String msg) {
		try {
			CnisLog log = new CnisLog();
			log.setTag(tag);
			log.setMsg(msg);
			log.setLevel(LogLevel.INFO);
			log.setCreateTime(DateHelper.getInstance().getDataString_1(null));
			dao.create(log);
			Log.i(log.getTag(), log.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void warn(String tag, String msg) {
		try {
			CnisLog log = new CnisLog();
			log.setTag(tag);
			log.setMsg(msg);
			log.setLevel(LogLevel.WARN);
			log.setCreateTime(DateHelper.getInstance().getDataString_1(null));
			dao.create(log);
			Log.w(log.getTag(),log.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void debug(String tag, String msg) {
		try {
			CnisLog log = new CnisLog();
			log.setTag(tag);
			log.setMsg(msg);
			log.setLevel(LogLevel.DEBUG);
			log.setCreateTime(DateHelper.getInstance().getDataString_1(null));
			dao.create(log);
			Log.d(log.getTag(), log.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void error(String tag, String msg) {
		try {
			CnisLog log = new CnisLog();
			log.setTag(tag);
			log.setMsg(msg);
			log.setLevel(LogLevel.ERROR);
			log.setCreateTime(DateHelper.getInstance().getDataString_1(null));
			dao.create(log);
			Log.e(log.getTag(), log.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
