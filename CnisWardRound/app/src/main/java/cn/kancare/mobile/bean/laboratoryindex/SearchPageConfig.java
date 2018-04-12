package cn.kancare.mobile.bean.laboratoryindex;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "searchpageconfig")
public class SearchPageConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// auto_increment
	@DatabaseField(columnName = "search_DBKEY", id = true)
	private int search_DBKEY;

	// link_DBKEY
	@DatabaseField(columnName = "link_DBKEY")
	private int link_DBKEY;

	// upperLimit
	@DatabaseField(columnName = "upperLimit")
	private String upperLimit;

	// lowerLimit
	@DatabaseField(columnName = "lowerLimit")
	private String lowerLimit;

	// showName
	@DatabaseField(columnName = "showName")
	private String showName;

	// recommended
	@DatabaseField(columnName = "recommended")
	private String recommended;

	public int getSearchDBKEY() {
		return search_DBKEY;
	}

	public void setSearchDBKEY(int search_DBKEY) {
		this.search_DBKEY = search_DBKEY;
	}

	public int getLinkDBKEY() {
		return link_DBKEY;
	}

	public void setLinkDBKEY(int link_DBKEY) {
		this.link_DBKEY = link_DBKEY;
	}

	public String getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}

	public String getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

	@Override
	public String toString() {
		return "searchpageconfig [search_DBKEY=" + search_DBKEY
				+ ",link_DBKEY=" + link_DBKEY + ",upperLimit=" + upperLimit
				+ ",lowerLimit=" + lowerLimit + ",showName=" + showName
				+ ",recommended=" + recommended + ",]";
	}
}
