package cn.kancare.mobile.activity.mealrecord.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bean.mealrecord.RelationOfDietaryFood;
import cn.kancare.mobile.bo.basic.ChinaFoodCompositionBo;
import cn.kancare.mobile.common.constant.FlagCode;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.SyncConstant.FoodType;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordFoodListener;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordInfoListener;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.ResUtil;
import os.zxs.force.core.view.InputDialog;
import os.zxs.force.core.view.InputDialog.InputDataType;
import os.zxs.force.core.view.InputDialog.InputDialogListener;
import os.zxs.force.core.view.fragment.BaseGridFragment;
import os.zxs.force.core.view.fragment.OnBackListener;

public class MealRecordFoodFragment extends
		BaseGridFragment<ChinaFoodComposition> implements
		MealRecordFoodListener {

	MealRecordInfoListener mealRecordInfoListener;
	ChinaFoodCompositionBo chinaFoodCompositionBo;

	OnBackListener onBackListener;

	Button btnButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = super.onCreateView(inflater, container,
				savedInstanceState);

		try {

		} catch (Exception e) {
			doException(e);
		}

		return layout;
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_MEALRECORD;
	}

	@Override
	protected void initializeBo() throws Exception {
		chinaFoodCompositionBo = new ChinaFoodCompositionBo(getActivity());
	}

	@Override
	protected int getLayoutId() {
		return R.layout.mealrecord_fragment_food;
	}

	@Override
	protected void setView(View layout) throws Exception {
		mealRecordInfoListener = (MealRecordInfoListener) getActivity();
	}

	@Override
	protected int getGridId() {
		return R.id.gridView1;
	}

	@Override
	protected int getGridItemLayoutId() {
		return R.layout.mealrecord_fragment_food_item;
	}

	@Override
	protected List<ChinaFoodComposition> getInitializeData() throws Exception {
		return chinaFoodCompositionBo.getDao().query(FoodType.FOOD);
	}

	public static class ViewHolder {
		ChinaFoodComposition chinaFoodComposition;

		ImageView ImageViewFoodPic;
		TextView TextViewFoodName;
		ImageView ImageViewMinus;
		TextView EditTextValue;
		ImageView ImageViewPlus;
		TextView TextViewTip;
	}

	@Override
	protected void setGridItemView(final int position, final View view,
			ChinaFoodComposition data, final ViewGroup parent) throws Exception {

		final ViewHolder holder = (ViewHolder) view.getTag();
		holder.chinaFoodComposition = data;
		holder.TextViewFoodName.setText(data.getFoodName());

		// 整或拆
		String unit = "";
		if (data.getFoodTableInsideType() == FlagCode.FOOD_TABLE_INSIDE_TYPE
				&& data.getWrapperType() != FlagCode.WRAPPER_TYPE) {
			unit = data.getMeasureUnitName();
			holder.TextViewTip.setVisibility(View.VISIBLE);
		} else {
			unit = data.getMinUnitName();
			holder.TextViewTip.setVisibility(View.GONE);
		}

		if(unit != null){
			holder.TextViewFoodName.setText(data.getFoodName() + " （"  + unit +"）");
		}

		try {
			holder.ImageViewFoodPic.setBackgroundResource(ResUtil.getInstanse()
					.getDrawableId(data.getFoodCode()));
		} catch (Exception e) {
			holder.ImageViewFoodPic
					.setBackgroundResource(R.drawable.ic_launcher);
		}

		if (data.getCurrentMealAmount() == 0) {
			holder.EditTextValue.setText("");
		} else {
			holder.EditTextValue.setText(Convert.RoundString2(
					data.getCurrentMealAmount(), 2));
		}

		holder.ImageViewMinus.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				MealRecordFoodFragment.this.onGridItemSubClick(view, parent,
						position, holder.ImageViewMinus.getId());
			}
		});

		holder.ImageViewPlus.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				MealRecordFoodFragment.this.onGridItemSubClick(view, parent,
						position, holder.ImageViewPlus.getId());
			}
		});
	}

	@Override
	protected void setViewHolder(View view) {
		final ViewHolder holder = new ViewHolder();
		holder.ImageViewFoodPic = (ImageView) view
				.findViewById(R.id.ImageViewFoodPic);
		holder.TextViewFoodName = (TextView) view
				.findViewById(R.id.TextViewFoodName);
		holder.ImageViewMinus = (ImageView) view
				.findViewById(R.id.ImageViewMinus);
		holder.EditTextValue = (TextView) view.findViewById(R.id.EditTextValue);
		holder.ImageViewPlus = (ImageView) view
				.findViewById(R.id.ImageViewPlus);

		holder.TextViewTip = (TextView) view.findViewById(R.id.TextViewTip);

		holder.EditTextValue.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				InputDialog.show(MealRecordFoodFragment.this.getActivity(),
						holder.chinaFoodComposition.getFoodName(),
						InputDataType.DECIMAL, holder.EditTextValue.getText()
								.toString(), "", new InputDialogListener() {

							public void doResult(int pos, String newValue) {
								holder.EditTextValue.setText(Convert
										.RoundString2(newValue, 2));
								holder.chinaFoodComposition
										.setCurrentMealAmount(Convert.Round(
												newValue, 2));
								mealRecordInfoListener.calcNutrition();

							}
						});

			}
		});

		view.setTag(holder);
	}

	@Override
	public void onGridItemSubClick(View item, View widget, int position,
			int which) {

		try {
			final ViewHolder holder = (ViewHolder) item.getTag();

			final ChinaFoodComposition chinaFoodComposition = adapter
					.getItem(position);
			double value = Convert.cash2Double(holder.EditTextValue.getText()
					.toString());
			switch (which) {
			case R.id.ImageViewMinus:

				if (value > 1) {
					value--;
					holder.EditTextValue
							.setText(Convert.RoundString2(value, 2));

				} else {
					value = 0;
					holder.EditTextValue.setText("");

				}
				break;
			case R.id.ImageViewPlus:
				value++;
				holder.EditTextValue.setText(Convert.RoundString2(value, 2));

				break;
			default:
				break;
			}

			chinaFoodComposition.setCurrentMealAmount(Convert.Round(value, 2));
			mealRecordInfoListener.calcNutrition();

		} catch (Exception e) {
			doException(e);
		}

	}

	public List<ChinaFoodComposition> getFoodRecords() {
		return adapter.getItems();
	}

	public void setFoodRecords(
			List<RelationOfDietaryFood> lstRelationOfDietaryFoods) {

		for (ChinaFoodComposition chinaFoodComposition : adapter.getItems()) {

			// 清空
			chinaFoodComposition.setCurrentMealAmount(0d);

			if (lstRelationOfDietaryFoods != null) {
				for (RelationOfDietaryFood relationOfDietaryFood : lstRelationOfDietaryFoods) {
					if (chinaFoodComposition.getChinaFoodComposition_DBKey() == relationOfDietaryFood
							.getChinaFoodComposition_DBKey()) {
						// 赋值
						chinaFoodComposition
								.setCurrentMealAmount(relationOfDietaryFood
										.getMealAmount());
					}
				}
			}
		}

		// 刷新数据集
		adapter.notifyDataSetChanged();
	}

	public Boolean hasFoodRecords() {
		Boolean has = false;
		for (ChinaFoodComposition chinaFoodComposition : adapter.getItems()) {
			if (chinaFoodComposition.getCurrentMealAmount() != 0) {
				has = true;
				continue;
			}
		}
		return has;
	}

}