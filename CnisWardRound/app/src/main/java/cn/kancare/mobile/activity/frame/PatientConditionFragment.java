package cn.kancare.mobile.activity.frame;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import cn.kancare.mobile.common.patient.IPatientCondition;

import java.util.List;

import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.basic.Department;
import cn.kancare.mobile.bo.basic.DepartmentBo;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.bridge.CallBackListener;
import os.zxs.force.core.util.ViewFindUtils;
import os.zxs.force.core.view.fragment.BaseGridFragment;

public class PatientConditionFragment extends BaseGridFragment<Department> implements IPatientCondition{
    FragmentActivity context;
    DepartmentBo departmentBo;
    CallBackListener callBackListener;
    String Department_DBKey = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = super.onCreateView(inflater, container,
                savedInstanceState);

        return layoutView;
    }

    public void setOnConditionChangeListener(CallBackListener callBack)
    {
        callBackListener = callBack;
    }

    public String getDepartment_DBkey()
    {
        return Department_DBKey;
    }

    protected List<Department> getInitializeData() throws Exception {
        return departmentBo.getDao().queryForAll();
    }

    protected int getGridId() {
        return R.id.GridViewDepartment;
    }

    protected int getGridItemLayoutId() {
        return R.layout.frame_patient_list_condition_item;
    }

    protected void setGridItemView(int position, View view, final Department data, ViewGroup parent) throws Exception {
        Button ButtonDepartment = ViewFindUtils.hold(view, R.id.ButtonDepartment, new ViewFindUtils.HoldCallBack() {
            public void doInit(View view) {
                Button ButtonDepartment = (Button)view;
                ButtonDepartment.setText(data.getDepartmentName());
                ButtonDepartment.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Department_DBKey = data.getDepartment_DBKey() + "";
                        callBackListener.doCallBack();
                    }
                });
            }
        });

    }

    protected void setViewHolder(View view) {

    }

    protected String getLogTag() {
        return LogTag.CNIS_LOG;
    }

    protected void initializeBo() throws Exception {
        departmentBo = new DepartmentBo(context);
    }

    protected int getLayoutId() {
        return R.layout.frame_patient_list_condition;
    }

    protected void setView(View layout) throws Exception {
        context = this.getActivity();
    }

}


