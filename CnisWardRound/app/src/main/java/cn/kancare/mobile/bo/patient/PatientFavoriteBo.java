package cn.kancare.mobile.bo.patient;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.bean.patient.PatientFavorite;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.patient.PatientFavoriteDao;

public class PatientFavoriteBo extends BaseBo<PatientFavoriteDao> {

	public PatientFavoriteBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new PatientFavoriteDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUploadResult(String json) throws Exception {
		// TODO Auto-generated method stub

	}

	public List<String> getFavorites() throws Exception {
		List<String> lstResult = new ArrayList<String>();
		List<PatientFavorite> lst = dao.queryByUserDBkey();
		if (lst != null && lst.size() > 0) {
			for (PatientFavorite patientFavorite : lst) {
				lstResult.add(patientFavorite.getPatientHospitalize_DBKey());
			}
		}
		return lstResult;
	}
}
