package cn.kancare.mobile.activity.mealrecord.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bo.basic.ChinaFoodCompositionBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordTotalListener;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.view.fragment.BaseFragment;

public class MealRecordTotalFragment extends BaseFragment implements
		MealRecordTotalListener {

	ChinaFoodCompositionBo chinaFoodCompositionBo;

	TextView TextViewEnergy;
	TextView TextViewProtein;
	TextView TextViewFat;
	TextView TextViewCarbohydrate;
	TextView TextViewProtein_p;
	TextView TextViewFat_p;
	TextView TextViewCarbohydrate_p;
	TextView TextViewCa;
	TextView TextViewP;
	TextView TextViewK;
	TextView TextViewNa;

	TextView TextViewMg;
	TextView TextViewFe;
	TextView TextViewVitaminC;
	TextView TextViewTotalDietaryFiber;

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
		return R.layout.mealrecord_fragment_total;
	}

	@Override
	protected void setView(View layout) throws Exception {
		TextViewEnergy = (TextView) layout.findViewById(R.id.TextViewEnergy);
		TextViewProtein = (TextView) layout.findViewById(R.id.TextViewProtein);
		TextViewFat = (TextView) layout.findViewById(R.id.TextViewFat);
		TextViewCarbohydrate = (TextView) layout
				.findViewById(R.id.TextViewCarbohydrate);
		TextViewProtein_p = (TextView) layout
				.findViewById(R.id.TextViewProtein_p);
		TextViewFat_p = (TextView) layout.findViewById(R.id.TextViewFat_p);
		TextViewCarbohydrate_p = (TextView) layout
				.findViewById(R.id.TextViewCarbohydrate_p);
		TextViewCa = (TextView) layout.findViewById(R.id.TextViewCa);
		TextViewP = (TextView) layout.findViewById(R.id.TextViewP);
		TextViewK = (TextView) layout.findViewById(R.id.TextViewK);
		TextViewNa = (TextView) layout.findViewById(R.id.TextViewNa);

		TextViewMg = (TextView) layout.findViewById(R.id.TextViewMg);
		TextViewFe = (TextView) layout.findViewById(R.id.TextViewFe);
		TextViewVitaminC = (TextView) layout
				.findViewById(R.id.TextViewVitaminC);
		TextViewTotalDietaryFiber = (TextView) layout
				.findViewById(R.id.TextViewTotalDietaryFiber);

	}

	public void calcNutrition(
			List<ChinaFoodComposition> lstChinaFoodCompositions) {

		ChinaFoodComposition total = chinaFoodCompositionBo
				.computeTotal(lstChinaFoodCompositions);


		TextViewProtein.setText(String.format("%.2f", total.getProtein())
				+ " g");
		TextViewFat.setText(String.format("%.2f", total.getFat()) + " g");
		TextViewCarbohydrate.setText(String.format("%.2f",
				total.getCarbohydrate())
				+ " g");

		double totalKcal = 0;
		if(Global.BUILD_FLAG.equals("HX")){
			totalKcal = total.getEnergy();
		}
		else{
			totalKcal= total.getProtein() * 4 + total.getFat() * 9
					+ total.getCarbohydrate() * 4;
		}
		
		TextViewEnergy.setText(String.format("%.2f", totalKcal)
				+ " Kcal"); //total.getEnergy()

		if (totalKcal == 0) {
			TextViewProtein_p.setText("");
			TextViewFat_p.setText("");
			TextViewCarbohydrate_p.setText("");
		} else {
			double Protein_p = total.getProtein() * 4 / totalKcal * 100;
			double Fat_p = total.getFat() * 9 / totalKcal * 100;
			double Carbohydrate_p = total.getCarbohydrate() * 4 / totalKcal
					* 100;
			
			//膳调时，发现营养摄入不足和过量时，做出相应建议量
			String flag = "";
			if(Protein_p <= 10 ){
				flag = "↓";
			}else if(Protein_p >= 15 ){
				flag = "↑";
			}			
			TextViewProtein_p.setText(Convert.RoundString2(Protein_p, 2) + "%" + flag);
			
			flag = "";
			if(Fat_p <= 20 ){
				flag = "↓";
			}else if(Fat_p >= 25 ){
				flag = "↑";
			}		
			TextViewFat_p.setText(Convert.RoundString2(Fat_p, 2) + "%" + flag);
			
			flag = "";
			if(Carbohydrate_p <= 60 ){
				flag = "↓";
			}else if(Carbohydrate_p >= 70 ){
				flag = "↑";
			}	
			TextViewCarbohydrate_p.setText(Convert.RoundString2(Carbohydrate_p,
					2) + "%" + flag);
		}

		TextViewCa.setText(String.format("%.2f", total.getCa()) + " mg");
		TextViewP.setText(String.format("%.2f", total.getP()) + " mg");
		TextViewK.setText(String.format("%.2f", total.getK()) + " mg");
		TextViewNa.setText(String.format("%.2f", total.getNa()) + " mg");

		TextViewMg.setText(String.format("%.2f", total.getMg()) + " mg");
		TextViewFe.setText(String.format("%.2f", total.getFe()) + " mg");
		TextViewVitaminC.setText(String.format("%.2f", total.getVitaminC())
				+ " mg");
		TextViewTotalDietaryFiber.setText(String.format("%.2f",
				total.getTotalDietaryFiber())
				+ " mg");

	}

}
