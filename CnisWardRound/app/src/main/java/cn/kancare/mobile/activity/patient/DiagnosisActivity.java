package cn.kancare.mobile.activity.patient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.BackFragment;
import cn.kancare.mobile.bean.basic.Diagnosis;
import cn.kancare.mobile.bo.basic.DiagnosisBo;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.bridge.CallBackListener;
import os.zxs.force.core.util.ViewFindUtils;
import os.zxs.force.core.view.ClearEditText;
import os.zxs.force.core.view.activity.BaseListActivity;

public class DiagnosisActivity  extends BaseListActivity<Diagnosis> {

    Context context;
    DiagnosisBo diagnosisBo;

    ClearEditText editTextKeyword;
    BackFragment backFragment;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editTextKeyword.setCallBackListener(new CallBackListener() {

            public void doCallBack() {
                refreshList();
            }
        });
        editTextKeyword.setCallBackChangeListener(new CallBackListener() {
            public void doCallBack() {
                refreshList();
            }
        });

    }

    @Override
    protected int getPageSize() {
        return 20;
    }

    protected List<Diagnosis> getMoreData(int pageSize, int offset) throws Exception {
        String keyword = editTextKeyword.getText().toString();
        return diagnosisBo.getDao().query(pageSize, offset, keyword);
    }

    protected int getListId() {
        return R.id.lvDiagnosis;
    }

    protected int getListItemLayoutId() {
        return R.layout.diagnosis_list_item;
    }

    protected void setListItemView(int position, View view, Diagnosis data, ViewGroup parent) {
        TextView textViewCode = ViewFindUtils.hold(view, R.id.textViewCode);
        textViewCode.setText(data.getDiagnosisCode());

        TextView textViewName = ViewFindUtils.hold(view, R.id.textViewName);
        textViewName.setText(data.getDiagnosisName());

        TextView textInputCode = ViewFindUtils.hold(view, R.id.textInputCode);
        textInputCode.setText(data.getInputCode());

    }

    protected void onListItemSubClick(View item, View widget, int position, int which) throws Exception {

    }

    protected void setViewHolder(View view) {

    }

    @Override
    protected void onListItemClick(Diagnosis data) {
        super.onListItemClick(data);
        Intent intent = new Intent();
        intent.putExtra("ID", data.getID());
        setResult(RESULT_OK, intent);
        finish();
    }

    protected String getLogTag() {
        return LogTag.CNIS_LOG;
    }

    protected int getLayoutId() {
        return R.layout.diagnosis_list;
    }

    protected void receiveIntent(Intent intent) throws Exception {

    }

    protected void initializeBo() throws Exception {
        diagnosisBo = new DiagnosisBo(this);
    }

    protected void setView() throws Exception {
        context = this;
        View view = this.getWindow().getDecorView();
        editTextKeyword = ViewFindUtils.find(view, R.id.editTextKeyword);
        backFragment = (BackFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_back);
        backFragment.setTitle("ICD-10疾病编码");
    }
}
