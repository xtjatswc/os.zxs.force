package os.zxs.force.core.log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "cnislog")
public class CnisLog {
	//ID
	@DatabaseField(columnName = "ID",generatedId = true)
	private int ID;	 
	
	// Level
	@DatabaseField(columnName = "Level")
	private String Level;	
	
	// Tag
	@DatabaseField(columnName = "Tag")
	private String Tag;

	// Msg
	@DatabaseField(columnName = "Msg")
	private String Msg;
	
	// CreateTime
	@DatabaseField(columnName = "CreateTime")
	private String CreateTime;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}
}
