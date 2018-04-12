package cn.kancare.mobile.core.util.spinner;

import android.app.Activity;
import android.widget.Spinner;

import java.util.ArrayList;

import cn.kancare.mobile.bo.basic.SysCodeBo;
import os.zxs.force.core.util.spinner.SpinnerOption;

public class SpinnerUtil extends os.zxs.force.core.util.spinner.SpinnerUtil{

	public static void setSpinnerValue(Activity context, SysCodeBo sysBo,
			Spinner spinner, String sysCodeType, String value) throws Exception {

		ArrayList<SpinnerOption> towns = sysBo.getSpinnerOptions(sysCodeType);
		setSpinnerValue(context, spinner, value, towns);
	}

}
