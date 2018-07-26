package cn.kancare.mobile.activity.mealrecord;

import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bean.mealrecord.MealRecord;
import cn.kancare.mobile.bo.basic.ChinaFoodCompositionBo;
import cn.kancare.mobile.bo.mealrecord.MealRecordBo;
import cn.kancare.mobile.bo.mealrecord.RelationOfDietaryFoodBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.activity.BaseGridActivity;

public class MealRecordActivity extends BaseGridActivity<MealRecord> {

	MealRecordBo mealRecordBo;
	ChinaFoodCompositionBo chinaFoodCompositionBo;
	public RelationOfDietaryFoodBo relationOfDietaryFoodBo;

	List<ChinaFoodComposition> lstChinaFoodCompositions;
	ImageButton btnAdd;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {

			lstChinaFoodCompositions = chinaFoodCompositionBo.getDao()
					.queryForAll();

			btnAdd.setOnClickListener(new onClickListener());

		} catch (Exception e) {
			doException(e);
		}

	}

	public class onClickListener implements OnClickListener {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnAdd:

				Intent i = new Intent(MealRecordActivity.this,
						MealRecordInfoActivity.class);
				Bundle bundle = new Bundle();
				try {
					bundle.putInt("OperateType", RequestCode.NEW_MEALRECORD);
				} catch (Exception e) {
					doException(e);
				}
				i.putExtras(bundle);

				startActivityForResult(i, RequestCode.NEW_MEALRECORD);

				break;

			default:
				break;
			}

		}

	}

	@Override
	protected void onListItemClick(MealRecord data) {
		super.onListItemClick(data);

		Intent i = new Intent(MealRecordActivity.this,
				MealRecordInfoActivity.class);
		Bundle bundle = new Bundle();
		try {
			String date = DateHelper.getInstance().getDataString_1(
					adapter.getCurrentItem().getMealDate());
			bundle.putInt("OperateType", RequestCode.EDIT_MEALRECORD);
			bundle.putString("MealDate", date);
		} catch (Exception e) {
			doException(e);
		}
		i.putExtras(bundle);

		startActivityForResult(i, RequestCode.EDIT_MEALRECORD);
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_MEALRECORD;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.mealrecord;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initializeBo() throws Exception {
		mealRecordBo = new MealRecordBo(this);
		relationOfDietaryFoodBo = new RelationOfDietaryFoodBo(this);
		chinaFoodCompositionBo = new ChinaFoodCompositionBo(this);
	}

	@Override
	protected void setView() throws Exception {
		btnAdd = (ImageButton) findViewById(R.id.btnAdd);

	}

	@Override
	protected int getPageSize() {
		return 6;
	}

	@Override
	protected List<MealRecord> getMoreData(int pageSize, int offset) throws Exception {
		return mealRecordBo.getDao().query(pageSize, offset,
				Global.currentPatient.getPatientHospitalize_DBKey());
	}

	@Override
	protected int getGridId() {
		return R.id.gridView1;
	}

	@Override
	protected int getGridItemLayoutId() {
		return R.layout.mealrecord_item;
	}

	@Override
	protected void setGridItemView(final int position, final View view,
			MealRecord data, final ViewGroup parent) throws Exception {
		final ViewHolder holder = (ViewHolder) view.getTag();
		holder.TextViewTitle.setText(DateHelper.getInstance().getDataString_2(
				data.getMealDate()));

		setOnGridItemSubClick(view, parent,
				position, holder.ImageButtonDelete);

		// 合计
		lstChinaFoodCompositions = mealRecordBo.getFoodAmountList(this,
				lstChinaFoodCompositions, data);

		ChinaFoodComposition total = chinaFoodCompositionBo
				.computeTotal(lstChinaFoodCompositions);

		double totalKcal = 0;
		if(Global.BUILD_FLAG.equals("HX")){
			totalKcal = total.getEnergy();
		}else{
			totalKcal = total.getProtein() * 4 + total.getFat() * 9
					+ total.getCarbohydrate() * 4;
		}
		
		
		
		holder.TextViewEnergy.setText(String.format("%.2f", totalKcal)); //total.getEnergy()
		holder.TextViewProtein
				.setText(String.format("%.2f", total.getProtein()) + " g");
		holder.TextViewFat
				.setText(String.format("%.2f", total.getFat()) + " g");
		holder.TextViewCarbohydrate.setText(String.format("%.2f",
				total.getCarbohydrate())
				+ " g");

		if (totalKcal == 0) {
			holder.TextViewProtein_p.setText("");
			holder.TextViewFat_p.setText("");
			holder.TextViewCarbohydrate_p.setText("");
		} else {
			double Protein_p = total.getProtein() * 4 / totalKcal * 100;
			double Fat_p = total.getFat() * 9 / totalKcal * 100;
			double Carbohydrate_p = total.getCarbohydrate() * 4 / totalKcal
					* 100;

			holder.TextViewProtein_p.setText(Convert.RoundString2(Protein_p, 2)
					+ "%");
			holder.TextViewFat_p.setText(Convert.RoundString2(Fat_p, 2) + "%");
			holder.TextViewCarbohydrate_p.setText(Convert.RoundString2(
					Carbohydrate_p, 2) + "%");
		}
	}

	protected void onGridItemSubClick(View item, View widget, int position,
			int which) throws Exception {

		final ViewHolder holder = (ViewHolder) item.getTag();

		final MealRecord mealRecord = adapter.getItem(position);

		switch (which) {
		case R.id.ImageButtonDelete:
			// 删除
			PopUtil.AlertDialog(
					MealRecordActivity.this,
					"提示",
					"确定删除『"
							+ DateHelper.getInstance().getDataString_2(
									mealRecord.getMealDate()) + "』的记录？",
					"确定", new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int which) {
							try {
								mealRecordBo.getDao().deleteById(
										mealRecord.getMealRecord_DBKey());

								relationOfDietaryFoodBo
										.getDao()
										.deleteByMealRecord_DBKey(
												mealRecord
														.getMealRecord_DBKey());
								removeAndRefresh();
							} catch (Exception e) {
								doException(e);
							}
							dialog.dismiss();

						}
					});
			break;

		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		// requestCode标示请求的标示 resultCode表示有数据

		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case RequestCode.NEW_MEALRECORD:
			case RequestCode.EDIT_MEALRECORD:
				refreshList();
				break;
			default:
				break;
			}
		}

	}

	public static class ViewHolder {
		TextView TextViewTitle;
		TextView TextViewEnergy;
		TextView TextViewProtein;
		TextView TextViewFat;
		TextView TextViewCarbohydrate;
		TextView TextViewProtein_p;
		TextView TextViewFat_p;
		TextView TextViewCarbohydrate_p;
		ImageView ImageButtonDelete;
	}

	@Override
	protected void setViewHolder(View view) {
		ViewHolder holder = new ViewHolder();
		holder.TextViewTitle = (TextView) view.findViewById(R.id.TextViewTitle);
		holder.TextViewEnergy = (TextView) view
				.findViewById(R.id.TextViewEnergy);
		holder.TextViewProtein = (TextView) view
				.findViewById(R.id.TextViewProtein);
		holder.TextViewFat = (TextView) view.findViewById(R.id.TextViewFat);
		holder.TextViewCarbohydrate = (TextView) view
				.findViewById(R.id.TextViewCarbohydrate);
		holder.ImageButtonDelete = (ImageView) view
				.findViewById(R.id.ImageButtonDelete);
		holder.TextViewProtein_p = (TextView) view
				.findViewById(R.id.TextViewProtein_p);
		holder.TextViewFat_p = (TextView) view.findViewById(R.id.TextViewFat_p);
		holder.TextViewCarbohydrate_p = (TextView) view
				.findViewById(R.id.TextViewCarbohydrate_p);
		view.setTag(holder);

	}

}
