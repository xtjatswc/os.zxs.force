package cn.kancare.mobile.bean.basic;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278786409890674018L;
	// User_DBKey
	@DatabaseField(columnName = "User_DBKey",id=true)
	private int User_DBKey;

	// Organization_DBKey
	@DatabaseField(columnName = "Organization_DBKey")
	private int Organization_DBKey;

	// UserLoginID
	@DatabaseField(columnName = "UserLoginID")
	private String UserLoginID;

	// UserName
	@DatabaseField(columnName = "UserName")
	private String UserName;

	// UserNameFirstLetter
	@DatabaseField(columnName = "UserNameFirstLetter")
	private String UserNameFirstLetter;

	// LoginPassword
	@DatabaseField(columnName = "LoginPassword")
	private String LoginPassword;

	// IsLocked
	@DatabaseField(columnName = "IsLocked")
	private String IsLocked;

	// UserJob
	@DatabaseField(columnName = "UserJob")
	private String UserJob;

	// UserGender
	@DatabaseField(columnName = "UserGender")
	private String UserGender;

	// UserJobNumber
	@DatabaseField(columnName = "UserJobNumber")
	private String UserJobNumber;

	// UserTitle
	@DatabaseField(columnName = "UserTitle")
	private String UserTitle;

	// UserDateOfBirth
	@DatabaseField(columnName = "UserDateOfBirth")
	private String UserDateOfBirth;

	// UserEducation
	@DatabaseField(columnName = "UserEducation")
	private String UserEducation;

	// TelPhone
	@DatabaseField(columnName = "TelPhone")
	private String TelPhone;

	// MobilePhone
	@DatabaseField(columnName = "MobilePhone")
	private String MobilePhone;

	// Email
	@DatabaseField(columnName = "Email")
	private String Email;

	// Description
	@DatabaseField(columnName = "Description")
	private String Description;

	// UserPhoto
	@DatabaseField(columnName = "UserPhoto")
	private String UserPhoto;

	// IsSupperUser
	@DatabaseField(columnName = "IsSupperUser")
	private String IsSupperUser;

	// IsActive
	@DatabaseField(columnName = "IsActive")
	private String IsActive;

	// CreateBy
	@DatabaseField(columnName = "CreateBy")
	private String CreateBy;

	// CreateTime
	@DatabaseField(columnName = "CreateTime")
	private String CreateTime;

	// CreateProgram
	@DatabaseField(columnName = "CreateProgram")
	private String CreateProgram;

	// CreateIP
	@DatabaseField(columnName = "CreateIP")
	private String CreateIP;

	// UpdateBy
	@DatabaseField(columnName = "UpdateBy")
	private String UpdateBy;

	// UpdateTime
	@DatabaseField(columnName = "UpdateTime")
	private String UpdateTime;

	// UpdateProgram
	@DatabaseField(columnName = "UpdateProgram")
	private String UpdateProgram;

	// UpdateIP
	@DatabaseField(columnName = "UpdateIP")
	private String UpdateIP;

	public int getUser_DBKey() {
		return User_DBKey;
	}

	public void setUser_DBKey(int User_DBKey) {
		this.User_DBKey = User_DBKey;
	}

	public int getOrganization_DBKey() {
		return Organization_DBKey;
	}

	public void setOrganization_DBKey(int Organization_DBKey) {
		this.Organization_DBKey = Organization_DBKey;
	}

	public String getUserLoginID() {
		return UserLoginID;
	}

	public void setUserLoginID(String UserLoginID) {
		this.UserLoginID = UserLoginID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public String getUserNameFirstLetter() {
		return UserNameFirstLetter;
	}

	public void setUserNameFirstLetter(String UserNameFirstLetter) {
		this.UserNameFirstLetter = UserNameFirstLetter;
	}

	public String getLoginPassword() {
		return LoginPassword;
	}

	public void setLoginPassword(String LoginPassword) {
		this.LoginPassword = LoginPassword;
	}

	public String getIsLocked() {
		return IsLocked;
	}

	public void setIsLocked(String IsLocked) {
		this.IsLocked = IsLocked;
	}

	public String getUserJob() {
		return UserJob;
	}

	public void setUserJob(String UserJob) {
		this.UserJob = UserJob;
	}

	public String getUserGender() {
		return UserGender;
	}

	public void setUserGender(String UserGender) {
		this.UserGender = UserGender;
	}

	public String getUserJobNumber() {
		return UserJobNumber;
	}

	public void setUserJobNumber(String UserJobNumber) {
		this.UserJobNumber = UserJobNumber;
	}

	public String getUserTitle() {
		return UserTitle;
	}

	public void setUserTitle(String UserTitle) {
		this.UserTitle = UserTitle;
	}

	public String getUserDateOfBirth() {
		return UserDateOfBirth;
	}

	public void setUserDateOfBirth(String UserDateOfBirth) {
		this.UserDateOfBirth = UserDateOfBirth;
	}

	public String getUserEducation() {
		return UserEducation;
	}

	public void setUserEducation(String UserEducation) {
		this.UserEducation = UserEducation;
	}

	public String getTelPhone() {
		return TelPhone;
	}

	public void setTelPhone(String TelPhone) {
		this.TelPhone = TelPhone;
	}

	public String getMobilePhone() {
		return MobilePhone;
	}

	public void setMobilePhone(String MobilePhone) {
		this.MobilePhone = MobilePhone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public String getUserPhoto() {
		return UserPhoto;
	}

	public void setUserPhoto(String UserPhoto) {
		this.UserPhoto = UserPhoto;
	}

	public String getIsSupperUser() {
		return IsSupperUser;
	}

	public void setIsSupperUser(String IsSupperUser) {
		this.IsSupperUser = IsSupperUser;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String IsActive) {
		this.IsActive = IsActive;
	}

	public String getCreateBy() {
		return CreateBy;
	}

	public void setCreateBy(String CreateBy) {
		this.CreateBy = CreateBy;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String CreateTime) {
		this.CreateTime = CreateTime;
	}

	public String getCreateProgram() {
		return CreateProgram;
	}

	public void setCreateProgram(String CreateProgram) {
		this.CreateProgram = CreateProgram;
	}

	public String getCreateIP() {
		return CreateIP;
	}

	public void setCreateIP(String CreateIP) {
		this.CreateIP = CreateIP;
	}

	public String getUpdateBy() {
		return UpdateBy;
	}

	public void setUpdateBy(String UpdateBy) {
		this.UpdateBy = UpdateBy;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(String UpdateTime) {
		this.UpdateTime = UpdateTime;
	}

	public String getUpdateProgram() {
		return UpdateProgram;
	}

	public void setUpdateProgram(String UpdateProgram) {
		this.UpdateProgram = UpdateProgram;
	}

	public String getUpdateIP() {
		return UpdateIP;
	}

	public void setUpdateIP(String UpdateIP) {
		this.UpdateIP = UpdateIP;
	}

	@Override
	public String toString() {
		return "user [User_DBKey=" + User_DBKey + ",Organization_DBKey="
				+ Organization_DBKey + ",UserLoginID=" + UserLoginID
				+ ",UserName=" + UserName + ",UserNameFirstLetter="
				+ UserNameFirstLetter + ",LoginPassword=" + LoginPassword
				+ ",IsLocked=" + IsLocked + ",UserJob=" + UserJob
				+ ",UserGender=" + UserGender + ",UserJobNumber="
				+ UserJobNumber + ",UserTitle=" + UserTitle
				+ ",UserDateOfBirth=" + UserDateOfBirth + ",UserEducation="
				+ UserEducation + ",TelPhone=" + TelPhone + ",MobilePhone="
				+ MobilePhone + ",Email=" + Email + ",Description="
				+ Description + ",UserPhoto=" + UserPhoto + ",IsSupperUser="
				+ IsSupperUser + ",IsActive=" + IsActive + ",CreateBy="
				+ CreateBy + ",CreateTime=" + CreateTime + ",CreateProgram="
				+ CreateProgram + ",CreateIP=" + CreateIP + ",UpdateBy="
				+ UpdateBy + ",UpdateTime=" + UpdateTime + ",UpdateProgram="
				+ UpdateProgram + ",UpdateIP=" + UpdateIP + ",]";
	}
}
