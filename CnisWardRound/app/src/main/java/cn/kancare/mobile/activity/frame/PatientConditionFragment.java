package cn.kancare.mobile.activity.frame;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import cn.kancare.mobile.activity.mealrecord.MealRecordActivity;
import cn.kancare.mobile.bo.basic.SettingBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.SettingCode;
import cn.kancare.mobile.common.patient.IPatientCondition;

import java.util.List;

import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.basic.Department;
import cn.kancare.mobile.bo.basic.DepartmentBo;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.bridge.CallBackListener;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.ViewFindUtils;
import os.zxs.force.core.view.fragment.BaseGridFragment;

public class PatientConditionFragment extends BaseGridFragment<Department> implements IPatientCondition{
    FragmentActivity context;
    DepartmentBo departmentBo;
    SettingBo settingBo;
    CallBackListener callBackListener;
    String Department_DBKey = "";

    TextView TextViewDepartment;
    CheckBox CheckBoxMyPatient;
    CheckBox CheckBoxMyStar;

    String DefaultDepartmentKey = SettingCode.DEFAULT_DEPARTMENT + "_" + Global.loginUser.getUserLoginID();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = super.onCreateView(inflater, container,
                savedInstanceState);

        CheckBoxMyPatient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                callBackListener.doCallBack();
            }
        });

        CheckBoxMyStar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                callBackListener.doCallBack();
            }
        });

        //读取默认科室
        try {
            Department_DBKey = settingBo.getSetting(DefaultDepartmentKey);
            if(Convert.cash2Int(Department_DBKey) == 0){
                TextViewDepartment.setText("当前科室：全部科室");
            }else{
                Department department = departmentBo.getDao().queryForId(Department_DBKey);
                TextViewDepartment.setText("当前科室：" + department.getDepartmentName());
            }
        } catch (Exception e) {
            doException(e);
        }

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

    public Boolean getMyPatientEnabled() {
        return CheckBoxMyPatient.isChecked();
    }

    public Boolean getMyStarEnabled() {
        return CheckBoxMyStar.isChecked();
    }

    public int getPageSize() {
        return 1000;
    }

    public List<Department> getMoreData(int pageSize, int offset) throws Exception {
        List<Department> lst = departmentBo.getDao().query(pageSize, offset);
        if(offset == 0) {
            Department department = new Department();
            department.setDepartment_DBKey(0);
            department.setDepartmentName("全部科室");
            lst.add(0, department);
        }
        return lst;
    }

    public void onListItemSubClick(View item, ViewGroup parent, int position, int which, Department data) throws Exception {
        Department department = data;
        switch (which) {
            case R.id.ButtonDepartment:
                Department_DBKey = department.getDepartment_DBKey() + "";
                TextViewDepartment.setText("当前科室：" + department.getDepartmentName());
                callBackListener.doCallBack();
                //存储默认科室
                try {
                    settingBo.saveSetting(DefaultDepartmentKey, department.getDepartment_DBKey() + "");
                } catch (Exception e) {
                    doException(e);
                }

                //刷新显示
                adapter.notifyDataSetChanged();

                break;
        }
    }

    public int getListItemLayoutId() {
        return R.layout.frame_patient_list_condition_item;
    }

    public void setViewHolder(View view) {
    }

    public int getListId() {
        return R.id.GridViewDepartment;
    }

    public void setListItemView(int position, View view, final Department data, ViewGroup parent) throws Exception {

        Button ButtonDepartment = ViewFindUtils.hold(view, R.id.ButtonDepartment);
        ButtonDepartment.setText(data.getDepartmentName());
        gridListAdapter.setOnListItemSubClick(view, parent, position, ButtonDepartment, data);

        if(data.getDepartment_DBKey() == Convert.cash2Int(Department_DBKey)){
            ButtonDepartment.setSelected(true);
        }else{
            ButtonDepartment.setSelected(false);
        }

    }

    public void onListItemClick(Department data) {
        super.onListItemClick(data);
    }

    protected String getLogTag() {
        return LogTag.CNIS_LOG;
    }

    protected void initializeBo() throws Exception {
        departmentBo = new DepartmentBo(context);
        settingBo = new SettingBo(context);
    }

    protected int getLayoutId() {
        return R.layout.frame_patient_list_condition;
    }

    protected void setView(View layout) throws Exception {
        context = this.getActivity();
        TextViewDepartment = ViewFindUtils.find(layout, R.id.TextViewDepartment);
        CheckBoxMyPatient = ViewFindUtils.find(layout, R.id.CheckBoxMyPatient);
        CheckBoxMyStar = ViewFindUtils.find(layout, R.id.CheckBoxMyStar);
    }

}


