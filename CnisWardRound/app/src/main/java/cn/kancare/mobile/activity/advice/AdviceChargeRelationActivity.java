package cn.kancare.mobile.activity.advice;

import android.content.Intent;
import android.os.Bundle;
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
import os.zxs.force.core.util.ViewFindUtils;
import os.zxs.force.core.util.ViewUtil;
import os.zxs.force.core.view.FlowRadioGroup;
import os.zxs.force.core.view.activity.BaseActivity;

public class AdviceChargeRelationActivity extends BaseActivity {

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
        } catch (Exception e) {
            doException(e);
        }

    }

    private void loadAdviceInputForm() throws Exception {
        // 收费项目
        List<ChargingItems> lstChargingItems = chargingItemsRelationBo.queryRelationItems(chargingItemsBo, RecipeAndProduct_DBKey);
        for (ChargingItems chargingItem : lstChargingItems) {
            RadioButton radioButton = new RadioButton(this);
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setText(chargingItem.getChargingItemName());
            FlowRadioGroupChargingItemName.addView(radioButton);
            radioButton.setTag(chargingItem);
            //radioButton.setOnClickListener(new CalcClickListener());
            //if (chargingItem.getSysCodeName().equals("粉剂")) {
            //    radioButton.setChecked(true);
            //}
        }

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
