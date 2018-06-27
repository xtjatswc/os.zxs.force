package os.zxs.force.core.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import os.zxs.force.core.log.CnislogBo;
import os.zxs.force.core.util.ExceptionUtil;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public abstract class BaseActivity extends FragmentActivity {
	protected CnislogBo log;
	//表单校验
	protected AwesomeValidation mAwesomeValidation;

	/**
	 * 获取Activity的TAG
	 */
	protected abstract String getLogTag(); 	
	
	protected abstract int getLayoutId();

	/**
	 * 初始化Intent对象
	 */
	protected abstract void receiveIntent(Intent intent) throws Exception;

	
	/**
	 * 初始化Bo对象
	 * @throws Exception 
	 */
	protected abstract void initializeBo() throws Exception;
	
	/**控件赋值
	 * @throws Exception
	 */
	protected abstract void setView() throws Exception;

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
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		try {

			setContentView(getLayoutId());

			Intent intent = this.getIntent();
			receiveIntent(intent);
			setView();
			setValidation();
			initializeBo();
		} catch (Exception e) {
			doException(e);
		}
		log = new CnislogBo(this);
	}
	
	protected void doException(Exception e)
	{
		ExceptionUtil.doException(this, log, getLogTag(), e);		
	}
}
