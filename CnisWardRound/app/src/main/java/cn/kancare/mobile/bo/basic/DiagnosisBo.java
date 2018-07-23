package cn.kancare.mobile.bo.basic;

import android.app.Activity;

import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.basic.DiagnosisDao;

public class DiagnosisBo extends BaseBo<DiagnosisDao> {
    public DiagnosisBo(Activity context) throws Exception {
        super(context);
    }

    protected void setDao() throws Exception {
        dao = new DiagnosisDao(context);
    }

    public void doDownloadJson(String json) throws Exception {

    }

    public void doUploadResult(String json) throws Exception {

    }
}
