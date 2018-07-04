package cn.kancare.mobile.activity.frame;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.basic.Department;
import cn.kancare.mobile.bo.basic.DepartmentBo;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.util.ViewFindUtils;
import os.zxs.force.core.view.fragment.BaseGridFragment;

public class PatientConditionFragment extends BaseGridFragment<Department>{
    FragmentActivity context;
    DepartmentBo departmentBo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = super.onCreateView(inflater, container,
                savedInstanceState);

//        gridView = (GridView) layoutView.findViewById(getGridId());
//        refreshList();
//
//        // 条目点击事件
//        gridView.setOnItemClickListener(new ItemClickListener());

        return layoutView;
    }

    protected List<Department> getInitializeData() throws Exception {
        return departmentBo.getDao().query(300, 0);
    }

    protected int getGridId() {
        return R.id.GridViewDepartment;
    }

    protected int getGridItemLayoutId() {
        return R.layout.frame_patient_list_condition_item;
    }

    protected void setGridItemView(int position, View view, Department data, ViewGroup parent) throws Exception {
        Button ButtonDepartment = ViewFindUtils.hold(view, R.id.ButtonDepartment);
        ButtonDepartment.setText(data.getDepartmentName());
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
