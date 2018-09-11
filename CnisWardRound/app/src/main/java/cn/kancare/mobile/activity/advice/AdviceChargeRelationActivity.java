package cn.kancare.mobile.activity.advice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.BackFragment;
import cn.kancare.mobile.bo.advice.ChargingAdviceDetailBo;
import cn.kancare.mobile.bo.advice.ChargingItemsBo;
import cn.kancare.mobile.bo.advice.ChargingItemsRelationBo;
import os.zxs.force.core.view.FlowRadioGroup;
import os.zxs.force.core.view.activity.BaseActivity;

public class AdviceChargeRelationActivity extends BaseActivity {

    ChargingItemsBo chargingItemsBo;
    ChargingItemsRelationBo chargingItemsRelationBo;
    ChargingAdviceDetailBo chargingAdviceDetailBo;

    String NutrientAdviceDetail_DBKEY;
    String RecipeAndProduct_DBKey;
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

    }

    protected String getLogTag() {
        return "重庆三院关联收费项";
    }

    protected int getLayoutId() {
        return R.layout.advice_charge_relation;
    }

    protected void receiveIntent(Intent intent) throws Exception {
        NutrientAdviceDetail_DBKEY = intent.getStringExtra("NutrientAdviceDetail_DBKEY");
        RecipeAndProduct_DBKey = intent.getStringExtra("RecipeAndProduct_DBKey");
    }

    protected void initializeBo() throws Exception {
        chargingItemsBo = new ChargingItemsBo(this);
        chargingItemsRelationBo = new ChargingItemsRelationBo(this);
        chargingAdviceDetailBo = new ChargingAdviceDetailBo(this);
    }

    protected void setView() throws Exception {
        backFragment = (BackFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_back);
        backFragment.setTitle("关联收费项");
        FlowRadioGroupChargingItemName = (FlowRadioGroup)findViewById(R.id.FlowRadioGroupChargingItemName);

    }

}
