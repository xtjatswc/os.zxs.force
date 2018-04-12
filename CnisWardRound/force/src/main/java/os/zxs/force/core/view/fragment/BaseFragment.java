package os.zxs.force.core.view.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import os.zxs.force.core.log.CnislogBo;
import os.zxs.force.core.util.ExceptionUtil;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public abstract class BaseFragment extends Fragment {
	protected CnislogBo log;
	//表单校验
	protected AwesomeValidation mAwesomeValidation;
	
	/**
	 * 获取TAG
	 */
	protected abstract String getLogTag();

	/**
	 * 初始化Bo对象
	 * 
	 * @throws Exception
	 */
	protected abstract void initializeBo() throws Exception;

	protected abstract int getLayoutId();
	
	/**控件赋值
	 * @throws Exception
	 */
	protected abstract void setView(View layout) throws Exception;
	
	/**
	 * 设置表单校验规则
	 */
	protected void setValidation(){
		mAwesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
		mAwesomeValidation.setColor(Color.YELLOW);  // mandatory for UNDERLABEL style
	}

	/**
	 * Android生命周期回调方法-创建
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutView = inflater.inflate(getLayoutId(), container, false);
		try {
			setView(layoutView);
			setValidation();
			initializeBo();
		} catch (Exception e) {
			doException(e);
		}
		log = new CnislogBo(this.getActivity());

		return layoutView;
	}

	protected void doException(Exception e) {
		ExceptionUtil.doException(this.getActivity(), log, getLogTag(), e);
	}
}
