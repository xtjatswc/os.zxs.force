package cn.kancare.mobile.core.base;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public abstract class BaseBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1664897197410616301L;

	// OperateFlag    0、从服务器下载时默认为0;1 、需要新增到服务器 ;2、需要修改到服务器;
	@DatabaseField(columnName = "OperateFlag")
	private int OperateFlag;

	public int getOperateFlag() {
		return OperateFlag;
	}

	public void setOperateFlag(int operateFlag) {
		OperateFlag = operateFlag;
	}
}
