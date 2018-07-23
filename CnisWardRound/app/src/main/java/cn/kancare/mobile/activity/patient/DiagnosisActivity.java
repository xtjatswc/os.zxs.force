package cn.kancare.mobile.activity.patient;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.basic.Diagnosis;
import cn.kancare.mobile.bo.basic.DiagnosisBo;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.util.ViewFindUtils;
import os.zxs.force.core.view.activity.BaseListActivity;

public class DiagnosisActivity  extends BaseListActivity<Diagnosis> {

    Context context;
    DiagnosisBo diagnosisBo;

    EditText editTextKeyword;

    protected List<Diagnosis> getInitializeData() throws Exception {
        String keyword = editTextKeyword.getText().toString();
        return diagnosisBo.getDao().query(20, 0, keyword);
    }

    protected List<Diagnosis> getMoreData(int listCount) throws Exception {
        String keyword = editTextKeyword.getText().toString();
        return diagnosisBo.getDao().query(20, listCount, keyword);
    }

    protected int getListId() {
        return R.id.lvDiagnosis;
    }

    protected int getListItemLayoutId() {
        return R.layout.diagnosis_list_item;
    }

    protected void setListItemView(int position, View view, Diagnosis data, ViewGroup parent) {
        TextView textViewName = ViewFindUtils.hold(view, R.id.textViewName);
        textViewName.setText(data.getDiagnosisName());
    }

    protected void setViewHolder(View view) {

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
    }
}
