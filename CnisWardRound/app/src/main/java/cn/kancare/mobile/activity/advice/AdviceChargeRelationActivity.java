package cn.kancare.mobile.activity.advice;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.app.ActionBar.LayoutParams;

import java.util.List;

import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.BackFragment;
import cn.kancare.mobile.bean.advice.ChargingItems;
import cn.kancare.mobile.bean.basic.SysCode;
import cn.kancare.mobile.bo.advice.ChargingAdviceDetailBo;
import cn.kancare.mobile.bo.advice.ChargingItemsBo;
import cn.kancare.mobile.bo.advice.ChargingItemsRelationBo;
import cn.kancare.mobile.common.constant.SysCodeType;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.ViewFindUtils;
import os.zxs.force.core.util.ViewUtil;
import os.zxs.force.core.view.FlowRadioGroup;
import os.zxs.force.core.view.activity.BaseActivity;

public class AdviceChargeRelationActivity extends BaseActivity {

    AdviceChargeRelationActivity context;

    ChargingItemsBo chargingItemsBo;
    ChargingItemsRelationBo chargingItemsRelationBo;
    ChargingAdviceDetailBo chargingAdviceDetailBo;

    String NutrientAdviceDetail_DBKEY;
    int RecipeAndProduct_DBKey;
    BackFragment backFragment;
    public FlowRadioGroup FlowRadioGroupChargingItemName;
    public FlowRadioGroup FlowRadioGroupChargingItemSpec;
    public EditText EditTextChargingItemPrice;
    public EditText EditTextChargingItemNum;
    public EditText EditTextChargingItemUnit;
    public EditText EditTextChargingItemMoney;
    Button btnSave2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            loadAdviceInputForm();

            EditTextChargingItemNum.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                public void afterTextChanged(Editable s) {
                    calcMoney();
                }
            });

        } catch (Exception e) {
            doException(e);
        }

    }

    private void loadAdviceInputForm() throws Exception {
        // 收费项目
        List<ChargingItems> lstChargingItems = chargingItemsRelationBo.queryRelationItems(chargingItemsBo, RecipeAndProduct_DBKey);
        for (int i = 0; i < lstChargingItems.size(); i++) {
            ChargingItems chargingItem = lstChargingItems.get(i);
            RadioButton radioButton = new RadioButton(context);
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setText(chargingItem.getChargingItemName());
            FlowRadioGroupChargingItemName.addView(radioButton);
            radioButton.setTag(chargingItem);
            radioButton.setOnClickListener(new ItemClickListener());
            if (i == 0) {
                radioButton.setChecked(true);
                radioButton.performClick();
            }
        }

    }

    class ItemClickListener implements View.OnClickListener {

        public void onClick(View v) {
            //加载规格
            ChargingItems chargingItem = (ChargingItems)v.getTag();
            String itemSpec = chargingItem.getChargingItemSpec();
            String[] specArr = itemSpec.split("#");
            FlowRadioGroupChargingItemSpec.removeAllViews();
            for (int i = 0; i < specArr.length; i++) {
                String spec = specArr[i];
                RadioButton radioButton = new RadioButton(context);
                LayoutParams layoutParams = new LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                radioButton.setLayoutParams(layoutParams);
                radioButton.setText(spec);
                FlowRadioGroupChargingItemSpec.addView(radioButton);
                //单价
                double price = (i == 0 ? chargingItem.getChargingItemPrice1() : chargingItem.getChargingItemPrice2());

                radioButton.setTag(price);
                radioButton.setOnClickListener(new SpecClickListener());
                if (i == 0) {
                    radioButton.setChecked(true);
                    radioButton.performClick();
                }
            }

            EditTextChargingItemUnit.setText(chargingItem.getChargingItemUnit());
        }
    }

    class SpecClickListener implements View.OnClickListener {
        public void onClick(View v) {
            double price = Convert.cash2Double(v.getTag().toString());
            EditTextChargingItemPrice.setText(price + "");
            calcMoney();
        }
    }

    private void calcMoney(){

        double price = Convert.cash2Double(EditTextChargingItemPrice.getText().toString());
        double num = Convert.cash2Double(EditTextChargingItemNum.getText().toString());
        EditTextChargingItemMoney.setText((price * num) + "");
    }

    protected String getLogTag() {
        return "重庆三院关联收费项";
    }

    protected int getLayoutId() {
        return R.layout.advice_charge_relation;
    }

    protected void receiveIntent(Intent intent) throws Exception {
        NutrientAdviceDetail_DBKEY = intent.getStringExtra("NutrientAdviceDetail_DBKEY");
        RecipeAndProduct_DBKey = intent.getIntExtra("RecipeAndProduct_DBKey", 0);
    }

    protected void initializeBo() throws Exception {
        chargingItemsBo = new ChargingItemsBo(this);
        chargingItemsRelationBo = new ChargingItemsRelationBo(this);
        chargingAdviceDetailBo = new ChargingAdviceDetailBo(this);
    }

    protected void setView() throws Exception {
        context = this;
        backFragment = (BackFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_back);
        backFragment.setTitle("关联收费项");
        View view = getWindow().getDecorView();
        FlowRadioGroupChargingItemName = ViewFindUtils.find(view, R.id.FlowRadioGroupChargingItemName);
        FlowRadioGroupChargingItemSpec = ViewFindUtils.find(view, R.id.FlowRadioGroupChargingItemSpec);
        EditTextChargingItemPrice = ViewFindUtils.find(view, R.id.EditTextChargingItemPrice);
        EditTextChargingItemNum = ViewFindUtils.find(view, R.id.EditTextChargingItemNum);
        EditTextChargingItemUnit = ViewFindUtils.find(view, R.id.EditTextChargingItemUnit);
        EditTextChargingItemMoney = ViewFindUtils.find(view, R.id.EditTextChargingItemMoney);
        btnSave2 = ViewFindUtils.find(view, R.id.btnSave2);

    }

}
