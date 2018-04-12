package cn.kancare.mobile.core.sync;

import java.io.Serializable;


public class DBKeyEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8767493805630884272L;

	private String OldDBKey;
	
	private String NewDBKey;
	
	private int OperateFlag;

	public String getOldDBKey() {
		return OldDBKey;
	}

	public void setOldDBKey(String oldDBKey) {
		OldDBKey = oldDBKey;
	}

	public String getNewDBKey() {
		return NewDBKey;
	}

	public void setNewDBKey(String newDBKey) {
		NewDBKey = newDBKey;
	}

	public int getOperateFlag() {
		return OperateFlag;
	}

	public void setOperateFlag(int operateFlag) {
		OperateFlag = operateFlag;
	}
}
