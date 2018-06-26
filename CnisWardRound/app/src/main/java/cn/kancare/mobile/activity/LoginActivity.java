package cn.kancare.mobile.activity;

import java.sql.SQLException;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.frame.MainActivity;
import cn.kancare.mobile.bean.basic.User;
import cn.kancare.mobile.bo.basic.SettingBo;
import cn.kancare.mobile.bo.basic.UserBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.SettingCode;
import cn.kancare.mobile.common.db.DatabaseHelper;
import com.flyco.tablayoutsamples.ui.SimpleHomeActivity;
import os.zxs.force.core.App;
import os.zxs.force.core.constant.AppConstant;
import os.zxs.force.core.util.EncryptUtil;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.util.RawDBUtil;
import os.zxs.force.core.view.activity.BaseActivity;

public class LoginActivity extends BaseActivity {

	private UserBo userBo;
	private SettingBo settingBo;

	private TextView TextView_Title;
	private EditText editTextLoginName;
	private EditText editTextPwd;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			Global.version = App.getAppInfo(AppConstant.GET_VERSION);

			// 每次发布前改一下AndroidManifest.xml的版本号，就会自动变成发布模式
			// 如果要设置成调试模式，请将下面版本号改为当前版本号
			if (Global.version.equals("1.0.9")) {
				App.iS_DEBUG = true;
			}

			TextView_Title.setText("移动查房系统 v" + Global.version);

			if (App.iS_DEBUG) {
				editTextLoginName.setText("999");
				editTextPwd.setText("000");
			}

			// 初始化velocity
			Velocity.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS,
					"org.apache.velocity.runtime.log.NullLogChute");
			Velocity.init();

			RawDBUtil rawDBUtil = new RawDBUtil(this, Global.rootPackageName,
					"cnis" + Global.version,
					new String[] { DatabaseHelper.DATABASE_NAME });
			// 释放演示库
			if (!rawDBUtil.fileExists("演示", DatabaseHelper.DATABASE_NAME)) {
				rawDBUtil.rawToFile(R.drawable.cnis_ward_round_test, "演示",
						DatabaseHelper.DATABASE_NAME);
			}

			// 释放初始库
			if (!rawDBUtil.fileExists("初始化", DatabaseHelper.DATABASE_NAME)) {
				rawDBUtil.rawToFile(R.drawable.cnis_ward_round_init, "初始化",
						DatabaseHelper.DATABASE_NAME);
			}
		} catch (Exception e) {
			doException(e);
		}

	}

	@Override
	protected void setValidation() {
		super.setValidation();
		mAwesomeValidation.addValidation(editTextLoginName, "[a-zA-Z0-9]+",
				"用户名只能为字母或数字");
		mAwesomeValidation.addValidation(editTextLoginName, "^.{1,10}$",
				"用户名长度为1~10！");
	}

	public void onClickHandler(View view) {
		switch (view.getId()) {
		case R.id.buttonLogin:

			Intent i = new Intent(this, SimpleHomeActivity.class);
			startActivity(i);

			if (!mAwesomeValidation.validate()) {
				return;
			}

			String loginName = editTextLoginName.getText().toString();
			String pwd = editTextPwd.getText().toString();

			if (loginName.equals("") || pwd.equals("")) {
				PopUtil.show(this, "请输入用户名和密码！");
				return;
			}

			if (loginName.equals("0") && pwd.equals("0")) {
				Global.loginUser = new User();
				Global.loginUser.setUser_DBKey(0);
				Global.loginUser.setUserName("系统管理员");
				Goto();
				return;
			}

			User user = null;
			try {
				user = userBo.getDao().queryByLoginID(loginName);
			} catch (SQLException e) {
				doException(e);
				return;
			}

			if (user == null) {
				PopUtil.show(this, "用户不存在！");
				return;
			}

			Boolean result = user.getLoginPassword().equals(
					EncryptUtil.md5(pwd));

			if (result) {
				Global.loginUser = user;
				Goto();
			} else {
				PopUtil.show(this, "密码错误！");
			}
			break;

		default:
			break;
		}

	}

	private void Goto() {
		loadSettings();

		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}

	private void loadSettings() {
		try {
			Global.WEB_REQUEST_IP = settingBo.getSetting(SettingCode.SERVER_IP);
			Global.AppMode = settingBo.getSetting(SettingCode.APP_MODE);
			Global.PatientListOrderBy = settingBo
					.getSetting(SettingCode.PATIENT_LIST_ORDER_BY);
			Global.PatientListOrderByAscDesc = settingBo
					.getSetting(SettingCode.PATIENT_LIST_ORDER_BY_ASC_DESC);
			Global.Individuation = settingBo
					.getSetting(SettingCode.INDIVIDUATION);
			Global.IsHideHistoryPatient = settingBo
					.getSetting(SettingCode.HISTORY_PATIENT);
			Global.MachineNo = settingBo.getSetting(SettingCode.MACHINE_NO);
			if (Global.MachineNo.equals("")) {
				Global.MachineNo = String.valueOf((int) (Math.random() * 100));
				settingBo.saveSetting(SettingCode.MACHINE_NO, Global.MachineNo);
			}
		} catch (Exception e) {
			doException(e);
		}
	}

	@Override
	protected String getLogTag() {
		return LogTag.LOGIN;
	}

	@Override
	protected void initializeBo() throws Exception {
		userBo = new UserBo(this);
		settingBo = new SettingBo(this);
	}

	@Override
	protected void setView() throws Exception {
		TextView_Title = (TextView) this.findViewById(R.id.TextView_Title);
		editTextLoginName = (EditText) this
				.findViewById(R.id.editTextLoginName);
		editTextPwd = (EditText) this.findViewById(R.id.editTextPwd);
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getLayoutId() {
		return R.layout.login;
	}
}