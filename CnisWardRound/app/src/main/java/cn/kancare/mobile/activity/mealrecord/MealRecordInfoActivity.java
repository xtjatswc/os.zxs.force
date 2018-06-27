package cn.kancare.mobile.activity.mealrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.mealrecord.fragment.MealRecordTotalFragment;
import cn.kancare.mobile.bo.mealrecord.MealRecordBo;
import cn.kancare.mobile.bo.mealrecord.RelationOfDietaryFoodBo;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordFoodListener;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordInfoListener;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordTimeListener;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordTmealListener;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordTotalListener;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.activity.BaseActivity;

public class MealRecordInfoActivity extends BaseActivity implements
		MealRecordInfoListener {

	Button btnSave;
	public MealRecordTimeListener timeFragment;
	public MealRecordTmealListener tmealFragment;
	public MealRecordFoodListener foodFragment;
	public MealRecordTotalListener totalFragment;

	int OperateType;
	private String MealDate;

	MealRecordBo mealRecordBo;
	public RelationOfDietaryFoodBo relationOfDietaryFoodBo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			timeFragment.setRecordTime(MealDate);

			mealRecordBo.loadMealRecord(this);

			btnSave.setOnClickListener(new onClickListener());
		} catch (Exception e) {
			doException(e);
		}

	}

	class onClickListener implements OnClickListener {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnSave:
				try {
					if (!mealRecordBo
							.saveMealRecord(MealRecordInfoActivity.this)) {
						PopUtil.show(MealRecordInfoActivity.this,
								"未保存，请录入数据后再进行保存操作！");
						return;
					}

					PopUtil.show(MealRecordInfoActivity.this, "保存成功！");

					Intent intent = new Intent();
					setResult(RESULT_OK, intent);
					finish();

				} catch (Exception e) {
					doException(e);
				}
				break;

			default:
				break;
			}
		}
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_MEALRECORD;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.mealrecord_info;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		MealDate = intent.getStringExtra("MealDate");
		OperateType = intent.getIntExtra("OperateType", 0);
	}

	@Override
	protected void initializeBo() throws Exception {
		mealRecordBo = new MealRecordBo(this);
		relationOfDietaryFoodBo = new RelationOfDietaryFoodBo(this);
	}

	@Override
	protected void setView() throws Exception {
		timeFragment = (MealRecordTimeListener) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_time);
		tmealFragment = (MealRecordTmealListener) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_tmeal);
		foodFragment = (MealRecordFoodListener) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_food);
		totalFragment = (MealRecordTotalFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_total);
		btnSave = (Button) findViewById(R.id.btnSave);
	}

	public void loadMealRecord() {
		try {
			if (mealRecordBo != null) {
				mealRecordBo.loadMealRecord(this);
				calcNutrition();
			}
		} catch (Exception e) {
			doException(e);
		}
	}

	public void saveMealRecord() {
		try {
			if (mealRecordBo != null) {
				mealRecordBo.saveMealRecord(MealRecordInfoActivity.this);
			}
		} catch (Exception e) {
			doException(e);
		}
	}

	public void calcNutrition() {

		totalFragment.calcNutrition(foodFragment.getFoodRecords());
	}

}
