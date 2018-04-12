package cn.kancare.mobile.activity.frame;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import cn.kancare.mobile.R;
import os.zxs.force.core.App;
import os.zxs.force.core.bridge.CallBackListener;

/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 * 
 * @author zxs
 */
public class MainActivity extends Activity implements OnClickListener {

	/**
	 * 用于展示工具箱的Fragment
	 */
	private ToolsFragment toolsFragment;

	/**
	 * 用于展示患者列表的Fragment
	 */
	private PatientListFragment patientListFragment;

	/**
	 * 用于展示数据同步的Fragment
	 */
	private SyncFragment syncFragment;

	/**
	 * 用于展示设置的Fragment
	 */
	private SettingFragment settingFragment;

	/**
	 * 工具箱界面布局
	 */
	private View toolsLayout;

	/**
	 * 患者列表界面布局
	 */
	private View patientListLayout;

	/**
	 * 数据同步界面布局
	 */
	private View syncLayout;

	/**
	 * 设置界面布局
	 */
	private View settingLayout;

	/**
	 * 在Tab布局上显示工具箱图标的控件
	 */
	private ImageView toolsImage;

	/**
	 * 在Tab布局上显示患者列表图标的控件
	 */
	private ImageView patientListImage;

	/**
	 * 在Tab布局上显示数据同步图标的控件
	 */
	private ImageView syncImage;

	/**
	 * 在Tab布局上显示设置图标的控件
	 */
	private ImageView settingImage;

	/**
	 * 在Tab布局上显示工具箱标题的控件
	 */
	private TextView toolsText;

	/**
	 * 在Tab布局上显示患者列表标题的控件
	 */
	private TextView patientListText;

	/**
	 * 在Tab布局上显示数据同步标题的控件
	 */
	private TextView syncText;

	/**
	 * 在Tab布局上显示设置标题的控件
	 */
	private TextView settingText;

	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.frame_main);
		// 初始化布局元素
		initViews();
		fragmentManager = getFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);

	}

	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {
		toolsLayout = findViewById(R.id.tools_layout);
		patientListLayout = findViewById(R.id.patient_list_layout);
		syncLayout = findViewById(R.id.sync_layout);
		settingLayout = findViewById(R.id.setting_layout);

		toolsImage = (ImageView) findViewById(R.id.tools_image);
		patientListImage = (ImageView) findViewById(R.id.patient_list_image);
		syncImage = (ImageView) findViewById(R.id.sync_image);
		settingImage = (ImageView) findViewById(R.id.setting_image);

		toolsText = (TextView) findViewById(R.id.tools_text);
		patientListText = (TextView) findViewById(R.id.patient_list_text);
		syncText = (TextView) findViewById(R.id.sync_text);
		settingText = (TextView) findViewById(R.id.setting_text);

		toolsLayout.setOnClickListener(this);
		patientListLayout.setOnClickListener(this);
		syncLayout.setOnClickListener(this);
		settingLayout.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.patient_list_layout:
			// 当点击了患者列表tab时，选中第1个tab
			setTabSelection(0);
			break;
		case R.id.sync_layout:
			// 当点击了数据同步tab时，选中第2个tab
			setTabSelection(1);
			break;
		case R.id.tools_layout:
			// 当点击了工具箱tab时，选中第3个tab
			setTabSelection(2);
			break;
		case R.id.setting_layout:
			// 当点击了设置tab时，选中第4个tab
			setTabSelection(3);
			break;
		default:
			break;
		}
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param index
	 *            每个tab页对应的下标。0表示患者列表，1表示数据同步，2表示工具箱，3表示设置。
	 */
	public void setTabSelection(int index) {

		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
		case 0:
			// 当点击了患者列表tab时，改变控件的图片和文字颜色
			patientListImage.setImageResource(R.drawable.patient_list_selected);
			patientListText.setTextColor(Color.WHITE);
			if (patientListFragment == null) {
				// 如果ContactsFragment为空，则创建一个并添加到界面上
				patientListFragment = new PatientListFragment();
				transaction.add(R.id.content, patientListFragment,
						"patientList");
			} else {
				// 如果ContactsFragment不为空，则直接将它显示出来
				transaction.show(patientListFragment);
				CallBackListener callBackListener = (CallBackListener) patientListFragment;
				callBackListener.doCallBack();
			}

			break;
		case 1:
			// 当点击了数据同步tab时，改变控件的图片和文字颜色
			syncImage.setImageResource(R.drawable.sync_selected);
			syncText.setTextColor(Color.WHITE);
			if (syncFragment == null) {
				// 如果NewsFragment为空，则创建一个并添加到界面上
				syncFragment = new SyncFragment();
				transaction.add(R.id.content, syncFragment, "sync");
			} else {
				// 如果NewsFragment不为空，则直接将它显示出来
				transaction.show(syncFragment);
			}
			break;
		case 2:
			// 当点击了工具箱tab时，改变控件的图片和文字颜色
			toolsImage.setImageResource(R.drawable.tools_selected);
			toolsText.setTextColor(Color.WHITE);
			if (toolsFragment == null) {
				// 如果MessageFragment为空，则创建一个并添加到界面上
				toolsFragment = new ToolsFragment();
				transaction.add(R.id.content, toolsFragment, "tools");
			} else {
				// 如果MessageFragment不为空，则直接将它显示出来
				transaction.show(toolsFragment);
			}
			break;
		case 3:
		default:
			// 当点击了设置tab时，改变控件的图片和文字颜色
			settingImage.setImageResource(R.drawable.setting_selected);
			settingText.setTextColor(Color.WHITE);
			if (settingFragment == null) {
				// 如果SettingFragment为空，则创建一个并添加到界面上
				settingFragment = new SettingFragment();
				transaction.add(R.id.content, settingFragment, "setting");
			} else {
				// 如果SettingFragment不为空，则直接将它显示出来
				transaction.show(settingFragment);
			}
			break;
		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		patientListImage.setImageResource(R.drawable.patient_list_unselected);
		patientListText.setTextColor(Color.parseColor("#82858b"));
		syncImage.setImageResource(R.drawable.sync_unselected);
		syncText.setTextColor(Color.parseColor("#82858b"));
		toolsImage.setImageResource(R.drawable.tools_unselected);
		toolsText.setTextColor(Color.parseColor("#82858b"));
		settingImage.setImageResource(R.drawable.setting_unselected);
		settingText.setTextColor(Color.parseColor("#82858b"));
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {

		if (toolsFragment != null) {
			transaction.hide(toolsFragment);

		}
		if (patientListFragment != null) {
			transaction.hide(patientListFragment);

		}
		if (syncFragment != null) {
			transaction.hide(syncFragment);

		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);

		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getRepeatCount() == 0) {

			App.toExit(this);

			return true;
		}

		return super.dispatchKeyEvent(event);
	}

}
