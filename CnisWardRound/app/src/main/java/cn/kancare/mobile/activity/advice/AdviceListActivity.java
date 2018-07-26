package cn.kancare.mobile.activity.advice;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.common.base.Strings;

import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.advice.NutrientAdvice;
import cn.kancare.mobile.bean.advice.NutrientAdviceDetail;
import cn.kancare.mobile.bean.advice.NutrientAdviceSummary;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bo.advice.NutrientAdviceBo;
import cn.kancare.mobile.bo.advice.NutrientAdviceDetailBo;
import cn.kancare.mobile.bo.advice.NutrientAdviceSummaryBo;
import cn.kancare.mobile.bo.basic.ChinaFoodCompositionBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.activity.BaseListActivity;

public class AdviceListActivity extends BaseListActivity<NutrientAdviceSummary> {

	String PatientHospitalize_DBKey;
	NutrientAdviceSummaryBo nutrientAdviceSummaryBo;
	NutrientAdviceBo nutrientAdviceBo;
	NutrientAdviceDetailBo nutrientAdviceDetailBo;
	ChinaFoodCompositionBo chinaFoodCompositionBo;

	ImageButton btnAdd;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		btnAdd.setOnClickListener(new onClickHandler());

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		refreshList();
	}

	private class onClickHandler implements android.view.View.OnClickListener {
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.btnAdd:
				Intent i = new Intent(AdviceListActivity.this,
						AdviceInfoActivity.class);
				Bundle bundle = new Bundle();
				try {
					bundle.putInt("OperateType", RequestCode.NEW_ADVICE);
				} catch (Exception e) {
					doException(e);
				}
				i.putExtras(bundle);

				startActivityForResult(i, RequestCode.NEW_ADVICE);
				break;

			default:
				break;
			}
		}
	}

	@Override
	protected int getPageSize() {
		return 20;
	}

	@Override
	protected List<NutrientAdviceSummary> getMoreData(int pageSize, int offset)
			throws Exception {
		return nutrientAdviceSummaryBo.getDao().query(pageSize, offset,
				PatientHospitalize_DBKey);
	}

	@Override
	protected int getListId() {
		return R.id.lvAdvice;
	}

	@Override
	protected int getListItemLayoutId() {
		return R.layout.advice_list_item;
	}

	@Override
	protected void setListItemView(final int position, final View view,
			NutrientAdviceSummary data, final ViewGroup parent) throws Exception {

		final ViewHolder holder = (ViewHolder) view.getTag();
		holder.TextViewNutrientAdviceSummary_DBKey.setText(data
				.getNutrientAdviceSummary_DBKey());

		if (data.getOperateFlag() == OperateFlag.NEED_ADD_TO_SERVER) {
			holder.ImageButton_Delete.setVisibility(View.VISIBLE);
			holder.ImageButton_Edit.setVisibility(View.VISIBLE);
		} else {
			holder.ImageButton_Delete.setVisibility(View.GONE);
			holder.ImageButton_Edit.setVisibility(View.GONE);
		}

        setOnListItemSubClick(view, parent,
                position, holder.ImageButton_Delete);

        setOnListItemSubClick(view, parent,
                position, holder.ImageButton_Edit);

		String adviceDateString = "";
		if (data.getAdviceBeginDate().equals(data.getAdviceEndDate())) {
			adviceDateString = DateHelper.getInstance().getDataString_3(
					data.getAdviceBeginDate());
		} else {
			adviceDateString = DateHelper.getInstance().getDataString_3(
					data.getAdviceBeginDate())
					+ "~"
					+ DateHelper.getInstance().getDataString_3(
							data.getAdviceEndDate());
		}
		holder.TextViewAdviceDate.setText("	" + adviceDateString);

		String remark = "";
		NutrientAdvice nutrientAdvice;
		try {
			nutrientAdvice = nutrientAdviceBo
					.getDao()
					.queryByNutrientAdviceSummary_DBKey(
							data.getNutrientAdviceSummary_DBKey()).get(0);
			List<NutrientAdviceDetail> listnutriNutrientAdviceDetails = nutrientAdviceDetailBo
					.getDao().queryAdviceDetail(
							nutrientAdvice.getNutrientAdvice_DBKey());
			List<ChinaFoodComposition> listChinaFoodCompositions = new ArrayList<ChinaFoodComposition>();
			for (NutrientAdviceDetail nutrientAdviceDetail : listnutriNutrientAdviceDetails) {
				ChinaFoodComposition chinaFoodComposition = chinaFoodCompositionBo
						.getDao().queryByRecipeAndProduct_DBKey(
								nutrientAdviceDetail
										.getRecipeAndProduct_DBKey());

				if (chinaFoodComposition == null)
					continue;

				if (listnutriNutrientAdviceDetails
						.indexOf(nutrientAdviceDetail) > 0) {
					remark += "\r\n";
				}

				remark += chinaFoodComposition.getFoodName() + "（"
						+ nutrientAdviceDetail.getTakeOrder() + "）"
						+ nutrientAdviceDetail.getAdviceAmount() + nutrientAdviceDetail.getUnit()
						+ "（"
						+ nutrientAdviceDetail.getNetContent()
						+ nutrientAdviceDetail.getNetContentUnit() + "）	"
						+ "备注："
						+ nutrientAdviceDetail.getNutrientAdviceDetailRemark();

				String[] arrStrings = nutrientAdviceDetail.getTakeOrder()
						.split(",");
				double amount = 0;
				if(nutrientAdviceDetail.getPreparationMode().equals("3")){
					//自助冲剂
					chinaFoodComposition.setCurrentMealAmount(Convert.cash2Double(nutrientAdviceDetail
							.getNetContent()));
				}else{
					chinaFoodComposition.setCurrentMealAmount(Convert.cash2Double(nutrientAdviceDetail
							.getNetContent()) * arrStrings.length);
				}

				listChinaFoodCompositions.add(chinaFoodComposition);
			}

			// 计算总能量
			ChinaFoodComposition totalChinaFoodComposition = chinaFoodCompositionBo
					.computeNutritionTotal(listChinaFoodCompositions);
			holder.TextViewEnergy.setText("能量:" + Convert.Round(totalChinaFoodComposition.getEnergy(), 2)
					+ "Kcal");
			holder.TextViewProtein.setText("蛋白质:" + Convert.Round(totalChinaFoodComposition.getProtein(), 2)
					+ "g");
		} catch (Exception e) {
			doException(e);
		}

		holder.TextViewRemark.setText(remark);
	}

	@Override
	protected void onListItemSubClick(View item, View widget, int position,
			int which) throws Exception {

		final NutrientAdviceSummary nutrientAdviceSummary = adapter
				.getItem(position);

		switch (which) {
		case R.id.ImageButton_Edit:
			Intent i7 = new Intent(AdviceListActivity.this,
					AdviceInfoActivity.class);
			Bundle bundle3 = new Bundle();
			try {
				bundle3.putInt("OperateType", RequestCode.EDIT_ADVICE);
				bundle3.putString("NutrientAdviceSummary_DBKey",
						nutrientAdviceSummary.getNutrientAdviceSummary_DBKey());
			} catch (Exception e) {
				doException(e);
			}
			i7.putExtras(bundle3);

			startActivityForResult(i7, RequestCode.EDIT_ADVICE);

			break;
		case R.id.ImageButton_Delete:
			// 删除
			PopUtil.AlertDialog(AdviceListActivity.this, "医嘱单『"
					+ nutrientAdviceSummary.getNutrientAdviceSummary_DBKey()
					+ "』", "确定删除该记录？", "确定",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							try {
								nutrientAdviceSummaryBo
										.DeleteAdvice(
												nutrientAdviceBo,
												nutrientAdviceDetailBo,
												nutrientAdviceSummary
														.getNutrientAdviceSummary_DBKey());
								removeAndRefresh();
							} catch (Exception e) {
								doException(e);
							}
							dialog.dismiss();

						}
					});
			break;
		}
	}

	public static class ViewHolder {
		TextView TextViewNutrientAdviceSummary_DBKey;
		ImageButton ImageButton_Edit;
		ImageButton ImageButton_Delete;
		TextView TextViewRemark;
		TextView TextViewEnergy;
		TextView TextViewProtein;
		TextView TextViewAdviceDate;
	}

	@Override
	protected void setViewHolder(View view) {
		ViewHolder holder = new ViewHolder();
		holder.TextViewNutrientAdviceSummary_DBKey = (TextView) view
				.findViewById(R.id.TextViewNutrientAdviceSummary_DBKey);
		holder.ImageButton_Edit = (ImageButton) view
				.findViewById(R.id.ImageButton_Edit);
		holder.ImageButton_Delete = (ImageButton) view
				.findViewById(R.id.ImageButton_Delete);
		holder.TextViewRemark = (TextView) view
				.findViewById(R.id.TextViewRemark);
		holder.TextViewEnergy = (TextView) view
				.findViewById(R.id.TextViewEnergy);
		holder.TextViewProtein = (TextView) view
				.findViewById(R.id.TextViewProtein);
		holder.TextViewAdviceDate = (TextView) view
				.findViewById(R.id.TextViewAdviceDate);
		view.setTag(holder);
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_ADVICE;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.advice_list;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		PatientHospitalize_DBKey = Global.currentPatient
				.getPatientHospitalize_DBKey();

	}

	@Override
	protected void initializeBo() throws Exception {
		nutrientAdviceSummaryBo = new NutrientAdviceSummaryBo(this);
		nutrientAdviceBo = new NutrientAdviceBo(this);
		nutrientAdviceDetailBo = new NutrientAdviceDetailBo(this);
		chinaFoodCompositionBo = new ChinaFoodCompositionBo(this);
	}

	@Override
	protected void setView() throws Exception {
		btnAdd = (ImageButton) findViewById(R.id.btnAdd);
	}

}
