package cn.kancare.mobile.bean.basic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "diagnosis")
public class Diagnosis {
    /**
     * ICD诊断目录
     */
    private static final long serialVersionUID = -3278786409890674018L;
    // ID
    @DatabaseField(columnName = "ID",id=true)
    private int ID;

    // DiagnosisCode
    @DatabaseField(columnName = "DiagnosisCode")
    private String DiagnosisCode;

    // DiagnosisName
    @DatabaseField(columnName = "DiagnosisName",index=true)
    private String DiagnosisName;

    // InputCode
    @DatabaseField(columnName = "InputCode",index=true)
    private String InputCode;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDiagnosisCode() {
        return DiagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        DiagnosisCode = diagnosisCode;
    }

    public String getDiagnosisName() {
        return DiagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        DiagnosisName = diagnosisName;
    }

    public String getInputCode() {
        return InputCode;
    }

    public void setInputCode(String inputCode) {
        InputCode = inputCode;
    }
}
