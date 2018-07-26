package cn.kancare.mobile.activity.advice.fragment;

import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.advice.NutrientAdvice;
import cn.kancare.mobile.bean.advice.NutrientAdviceDetail;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bo.advice.NutrientAdviceBo;
import cn.kancare.mobile.bo.advice.NutrientAdviceDetailBo;
import cn.kancare.mobile.bo.advice.NutrientAdviceSummaryBo;
import cn.kancare.mobile.bo.basic.ChinaFoodCompositionBo;
import cn.kancare.mobile.common.advice.AdviceListener.AdviceInfoListener;
import cn.kancare.mobile.common.advice.AdviceListener.NutrientProductListener;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.view.fragment.BaseListFragment;

public class NutrientProductFragment extends
		BaseListFragment<ChinaFoodComposition> implements
		NutrientProductListener {
	List<NutrientAdviceDetail> listNutrientAdviceDetails;

	NutrientAdviceSummaryBo nutrientAdviceSummaryBo;
	NutrientAdviceBo nutrientAdviceBo;
	NutrientAdviceDetailBo nutrientAdviceDetailBo;

	ChinaFoodCompositionBo chinaFoodCompositionBo;
	AdviceInfoListener adviceInfoListener;

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
    protected int getPageSize() {
        return 2000;
    }

    @Override
	protected List<ChinaFoodComposition> getMoreData(int pageSize, int offset)
			throws Exception {
		return chinaFoodCompositionBo.getDao().query(pageSize, offset, "2");
	}

	@Override
	protected int getListId() {
		return R.id.ListView_Product;
	}

	@Override
	protected int getListItemLayoutId() {
		return R.layout.advice_product_list_item;
	}

	@Override
	protected void setListItemView(int position, View view,
			ChinaFoodComposition data, ViewGroup parent) {
		final ViewHolder holder = (ViewHolder) view.getTag();
		holder.TextView_ProductName.setText(data.getFoodName());

		holder.TextView_Flag.setVisibility(View.GONE);
		if (listNutrientAdviceDetails != null) {
			for (NutrientAdviceDetail detail : listNutrientAdviceDetails) {
				if (detail.getRecipeAndProduct_DBKey() == data
						.getRecipeAndProduct_DBKey()) {
					holder.TextView_Flag.setText(detail.getTakeOrder());
					holder.TextView_Flag.setVisibility(View.VISIBLE);
					holder.TextView_Flag.setTextColor(Color.BLUE); // Color.aliceblue
					break;
				}
			}
		}

	}

	public static class ViewHolder {
		TextView TextView_ProductName;
		TextView TextView_Flag;
	}

	@Override
	protected void setViewHolder(View view) {
		ViewHolder holder = new ViewHolder();
		holder.TextView_ProductName = (TextView) view
				.findViewById(R.id.TextView_ProductName);
		holder.TextView_Flag = (TextView) view.findViewById(R.id.TextView_Flag);
		view.setTag(holder);
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_ADVICE;
	}

	@Override
	protected void initializeBo() throws Exception {
		chinaFoodCompositionBo = new ChinaFoodCompositionBo(this.getActivity());
		nutrientAdviceSummaryBo = new NutrientAdviceSummaryBo(
				this.getActivity());
		nutrientAdviceBo = new NutrientAdviceBo(this.getActivity());
		nutrientAdviceDetailBo = new NutrientAdviceDetailBo(this.getActivity());
	}

	@Override
	protected int getLayoutId() {
		return R.layout.advice_product_list;
	}

	@Override
	protected void setView(View layout) throws Exception {
		adviceInfoListener = (AdviceInfoListener) getActivity();

	}

	@Override
	protected void onListItemClick(ChinaFoodComposition data) {
		super.onListItemClick(data);
		adviceInfoListener.ProductChange(data);
	}

	@Override
	protected Boolean isSelectedChangeColor() {
		return true;
	}

	@Override
	protected int getUnSelectedColor() {
		return 0xFF008000;
	}

	protected void onListItemSubClick(View item, View widget, int position, int which) throws Exception {

	}

	public void refreshProductStatus(String NutrientAdviceSummary_DBKey)
			throws Exception {
		NutrientAdvice nutrientAdvice = nutrientAdviceBo
				.getDao()
				.queryByNutrientAdviceSummary_DBKey(NutrientAdviceSummary_DBKey)
				.get(0);
		listNutrientAdviceDetails = nutrientAdviceDetailBo.getDao()
				.queryAdviceDetail(nutrientAdvice.getNutrientAdvice_DBKey());

		if (listNutrientAdviceDetails.size() == 0) {
			nutrientAdviceBo.getDao().deleteById(
					nutrientAdvice.getNutrientAdvice_DBKey());
			nutrientAdviceSummaryBo.getDao().deleteById(
					NutrientAdviceSummary_DBKey);
		}

		adapter.notifyDataSetChanged();
	}
}
