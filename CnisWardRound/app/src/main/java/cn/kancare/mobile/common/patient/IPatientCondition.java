package cn.kancare.mobile.common.patient;

import os.zxs.force.core.bridge.CallBackListener;

public interface IPatientCondition {
    void setOnConditionChangeListener(CallBackListener callBackListener);
    String getDepartment_DBkey();
}
