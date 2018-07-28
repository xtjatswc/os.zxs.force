package cn.kancare.mobile.activity.frame;

import java.sql.SQLException;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.courserecord.CourseRecordListActivity;
import cn.kancare.mobile.activity.patient.PatientInfoActivity;
import cn.kancare.mobile.activity.patient.PatientViewActivity;
import cn.kancare.mobile.activity.questionnaire.QuestionnaireListActivity;
import cn.kancare.mobile.bean.basic.User;
import cn.kancare.mobile.bean.patient.PatientFavorite;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;
import cn.kancare.mobile.bo.CourseRecordBo;
import cn.kancare.mobile.bo.basic.DepartmentBo;
import cn.kancare.mobile.bo.basic.UserBo;
import cn.kancare.mobile.bo.laboratoryindex.LaboratoryIndexBo;
import cn.kancare.mobile.bo.laboratoryindex.SearchPageConfigBo;
import cn.kancare.mobile.bo.laboratoryindex.TestResultBo;
import cn.kancare.mobile.bo.patient.PatientFavoriteBo;
import cn.kancare.mobile.bo.patient.PatientHospitalizeBasicInfoBo;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionnaireBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import cn.kancare.mobile.common.patient.IPatientCondition;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import os.zxs.force.core.baseanimation.BadgeView;
import os.zxs.force.core.bridge.CallBackListener;
import os.zxs.force.core.util.ColorUtil;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.util.spinner.SpinnerOption;
import os.zxs.force.core.util.spinner.SpinnerUtil;
import os.zxs.force.core.view.ClearEditText;
import os.zxs.force.core.view.fragment.BaseListFragment;
import os.zxs.force.core.view.slide.SlideLayout;

public class PatientListFragment extends
		BaseListFragment<PatientHospitalizeBasicInfo> implements CallBackListener {
	FragmentActivity context;
	PatientHospitalizeBasicInfoBo patientBo;
	DepartmentBo departmentBo;
	PatientFavoriteBo patientFavoriteBo;
	PatientQuestionnaireBo patientQuestionnaireBo;
	CourseRecordBo courseRecordBo;
	UserBo userBo;
	public LaboratoryIndexBo laboratoryIndexBo;
	public TestResultBo testResultBo;
	public SearchPageConfigBo searchPageConfigBo;

	ImageButton btnSearch;
	ImageButton ImageButton_Add;
	Button btnMore;
	ClearEditText editTextCondition;
	ImageView imageViewQuestionnaire;
	SlideLayout slideLayoutCondition;

	IPatientCondition iPatientCondition;
	Switch switchStatus;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutView = super.onCreateView(inflater, container,
				savedInstanceState);

		iPatientCondition.setOnConditionChangeListener(new CallBackListener() {
			public void doCallBack() {
				gridListAdapter.refreshList();
			}
		});

		btnSearch.setOnClickListener(new onClickHandler());
		btnMore.setOnClickListener(new onClickHandler());
		ImageButton_Add.setOnClickListener(new onClickHandler());
		editTextCondition.setCallBackListener(new CallBackListener() {

			public void doCallBack() {
				gridListAdapter.refreshList();
			}
		});
		switchStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				gridListAdapter.refreshList();
			}
		});

		return layoutView;
	}

	/**
	 * 
	 * 处理点击事件
	 * 
	 * @param view
	 */
	private class onClickHandler implements android.view.View.OnClickListener {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ImageButton_Add:
				Intent i = new Intent(PatientListFragment.this.getActivity(),
						PatientInfoActivity.class);
				Bundle bundle = new Bundle();
				try {
					// 新建患者，把科室带过去
					String department_DBKey = iPatientCondition.getDepartment_DBkey();
					bundle.putString("department_DBKey", department_DBKey);
					bundle.putInt("OperateType", RequestCode.NEW_PATIENT_INFO);
				} catch (Exception e) {
					doException(e);
				}
				i.putExtras(bundle);
				startActivityForResult(i, RequestCode.NEW_PATIENT_INFO);
				break;
			case R.id.btnSearch:
				gridListAdapter.refreshList();
				break;
			case R.id.btnMore:
				if(slideLayoutCondition.getSlideState() == SlideLayout.STATE_CLOSE){
					slideLayoutCondition.smoothOpenSlide();
				}else{
					slideLayoutCondition.smoothCloseSlide();
				}
				break;
			default:
				break;
			}

		}
	}

	public void onListItemClick(final PatientHospitalizeBasicInfo data) {
		super.onListItemClick(data);

		Global.currentPatient = data;

		// 患者信息
		Intent i = new Intent(getActivity(), PatientViewActivity.class);
		Bundle bundle = new Bundle();
		try {
			bundle.putString("PatientHospitalize_DBKey", adapter
					.getCurrentItem().getPatientHospitalize_DBKey());
		} catch (Exception e) {
			doException(e);
		}
		i.putExtras(bundle);

		startActivityForResult(i, RequestCode.VIEW_PATIENT_INFO);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adapter.notifyDataSetChanged();
		Global.currentPatient = null;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RequestCode.NEW_PATIENT_INFO) {
			if (resultCode == RequestCode.NEW_PATIENT_INFO) {
				String patientName = data.getExtras().getString("patientName");
				editTextCondition.setText(patientName);
				gridListAdapter.refreshList();
			}
		} else if (requestCode == RequestCode.VIEW_PATIENT_INFO) {
			if (resultCode == RequestCode.DELETE_PATIENT_INFO) {
				gridListAdapter.refreshList(); // 删除患者后，返回到患者列表界面刷新
			}
		}
	}

	public int getPageSize() {
		return 8;
	}

	public List<PatientHospitalizeBasicInfo> getMoreData(int pageSize, int offset)
			throws Exception {

		String keyword = editTextCondition.getText().toString();
		String department_DBKey = iPatientCondition.getDepartment_DBkey();
		List<String> lstFavorite = null;
		if (iPatientCondition.getMyStarEnabled()) {
			lstFavorite = patientFavoriteBo.getFavorites();
			if (lstFavorite == null || lstFavorite.size() == 0) {
				return null;
			}
		}
		List<PatientHospitalizeBasicInfo> list = patientBo.getDao().query(pageSize,
				offset, keyword, department_DBKey, lstFavorite,
				iPatientCondition.getMyPatientEnabled(), switchStatus.isChecked());
		return list;

	}

	public int getListId() {
		return R.id.lvPatients;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.patient_list;
	}

	public int getListItemLayoutId() {
		return R.layout.patient_list_item;
	}

	public static class ViewHolder {
		TextView tvPatientName;
		TextView tvDepartmentName;
		TextView tvBedCode;
		TextView tvClinicistName;
		TextView tvInHospitalDate;
		TextView tvAge;
		ImageView ImageViewSex;
		TextView tvDiseaseName;
		TextView tvHospitalizationNumber;
		public TextView tvPatientNo;
		Switch switchChildStatus;
		ImageView imageViewLove;
		public ImageView imageViewQuestionnaire;
		public ImageView imageViewCourseRecord;
		public BadgeView badge;
		public BadgeView badgeCourseRecord;
		public BadgeView badgeDietician;
		ImageView imageViewWarning;

	}

	public void setViewHolder(View view) {
		ViewHolder holder = new ViewHolder();
		holder.tvPatientName = (TextView) view.findViewById(R.id.patientName);
		holder.tvDepartmentName = (TextView) view.findViewById(R.id.departName);
		holder.tvBedCode = (TextView) view.findViewById(R.id.bedCode);
		holder.tvClinicistName = (TextView) view
				.findViewById(R.id.clinicistName);
		holder.tvInHospitalDate = (TextView) view
				.findViewById(R.id.inHospitalDate);
		holder.ImageViewSex = (ImageView) view.findViewById(R.id.ImageViewSex);
		holder.tvAge = (TextView) view.findViewById(R.id.age);
		holder.tvDiseaseName = (TextView) view.findViewById(R.id.diseaseName);
		holder.tvHospitalizationNumber = (TextView) view
				.findViewById(R.id.hospitalizationNumber);
		holder.tvPatientNo = (TextView) view.findViewById(R.id.patientNo);
		holder.switchChildStatus = (Switch) view
				.findViewById(R.id.switchChildStatus);
		holder.imageViewLove = (ImageView) view
				.findViewById(R.id.imageViewLove);
		holder.imageViewQuestionnaire = (ImageView) view
				.findViewById(R.id.imageViewQuestionnaire);
		holder.imageViewCourseRecord = (ImageView) view
				.findViewById(R.id.imageViewCourseRecord);
		holder.badge = new BadgeView(this.getActivity(),
				holder.imageViewQuestionnaire);
		holder.badge.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
		holder.badgeCourseRecord = new BadgeView(this.getActivity(),
				holder.imageViewCourseRecord);
		holder.badgeCourseRecord
				.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);

		// 主管营养师气泡
		holder.badgeDietician = new BadgeView(this.getActivity(),
				holder.ImageViewSex);
		holder.badgeDietician.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);
		holder.badgeDietician.setTextColor(0xFFFF4500); // Color.orangered 红橙色
		holder.badgeDietician.setBackgroundColor(0xFFFFD700);// Color.gold 金色

		holder.imageViewWarning = (ImageView) view
				.findViewById(R.id.imageViewWarning);

		view.setTag(holder);
	}

	public void setListItemView(final int position, final View view,
			PatientHospitalizeBasicInfo patientinfo, final ViewGroup parent) throws Exception {

		final ViewHolder holder = (ViewHolder) view.getTag();

		// 患者姓名
		holder.tvPatientName.setText(patientinfo.getPatientName());
		// 科室
		holder.tvDepartmentName.setText("	"
				+ patientinfo.getDepartmentName());
		// 床号
		holder.tvBedCode.setText("床号：" + patientinfo.getBedCode());

		// 临床医生
		holder.tvClinicistName.setText(patientinfo.getClinicistName());
		// 入院日期
		String d = DateHelper.getInstance().getDataString_2(
				patientinfo.getInHospitalData());
		holder.tvInHospitalDate.setText(d + " 入院");

		// 主管营养师气泡
		if (patientinfo.getNutrientDoctor_DBKey() == 0) {
			holder.badgeDietician.hide();
		} else {
			User user = userBo.getDao().queryForId(
					patientinfo.getNutrientDoctor_DBKey());
			if (user != null) {
				holder.badgeDietician.setText(user.getUserName());
			}
			holder.badgeDietician.show();
		}

		// 性别
		// String sex = patientinfo.getGender().equals("M") ? "男" : "女";
		// TextView tvGender = (TextView) view.findViewById(R.id.gender);
		// tvGender.setText("	性别：" + sex);

		// 性别图标
		if (patientinfo.getGender().equals("M")) {
			holder.ImageViewSex.setImageResource(R.drawable.male);
		} else {
			holder.ImageViewSex.setImageResource(R.drawable.female);
		}

		// 设置我的患者
		gridListAdapter.setOnListItemSubClick(view, parent,
				position, holder.ImageViewSex, patientinfo);

		// 年龄
		holder.tvAge.setText("	"
				+ Convert.RoundString2(patientinfo.getAge(), 2) + " 岁");
		// 疾病
		holder.tvDiseaseName.setText("	" + patientinfo.getDiseaseName()
				+ " " + patientinfo.getClinicalDiagnosis());
		// 住院号
		holder.tvHospitalizationNumber.setText("住院号："
				+ patientinfo.getHospitalizationNumber());
		// 患者编号
		holder.tvPatientNo.setText(patientinfo.getPatientNo());
		if (patientinfo.getNutrientDoctor_DBKey() == Global.loginUser
				.getUser_DBKey() && Global.loginUser.getUser_DBKey() != 0) {

			holder.tvPatientNo
					.setTextColor(ColorUtil.getColor(R.color.red));// red
		} else {
			holder.tvPatientNo.setTextColor(ColorUtil
					.getColor(R.color.gray));// gray
		}

		// 营养治疗状态
		if (patientinfo.getTherapyStatusName().equals("出院")) {
			holder.switchChildStatus.setChecked(false);
		} else {
			holder.switchChildStatus.setChecked(true);
		}

		gridListAdapter.setOnListItemSubClick(view, parent,
				position, holder.switchChildStatus, patientinfo);


		// 收藏
		final ImageView imageViewLove = holder.imageViewLove;
		String pkey = Global.loginUser.getUser_DBKey() + "+"
				+ patientinfo.getPatientHospitalize_DBKey();
		PatientFavorite patientFavorite = patientFavoriteBo.getDao()
				.queryForId(pkey);
		if (patientFavorite == null) {
			imageViewLove.setBackgroundResource(R.drawable.heart_love2);
		} else {
			imageViewLove.setBackgroundResource(R.drawable.heart_love);
		}

		gridListAdapter.setOnListItemSubClick(view, parent,
				position, holder.imageViewLove, patientinfo);

		gridListAdapter.setOnListItemSubClick(view, parent,
				position, imageViewLove, patientinfo);


		// 问卷图标
		patientQuestionnaireBo.setQuestionnaireInfo(patientinfo, holder);
		gridListAdapter.setOnListItemSubClick(view, parent,
				position, holder.imageViewQuestionnaire, patientinfo);


		// 查房记录图标
		courseRecordBo.setCourseInfo(patientinfo, holder);
		gridListAdapter.setOnListItemSubClick(view, parent,
				position, holder.imageViewCourseRecord, patientinfo);

		// 根据检验数据判断是否有营养不良风险
		// if (patientBo.checkIsRisk(this,
		// patientinfo.getPatientHospitalize_DBKey())) {
		// holder.imageViewWarning.setVisibility(View.VISIBLE);
		// } else {
		// holder.imageViewWarning.setVisibility(View.INVISIBLE);
		// }
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_PATIENT_INFO;
	}

	@Override
	protected void initializeBo() throws Exception {
		patientBo = new PatientHospitalizeBasicInfoBo(getActivity());
		departmentBo = new DepartmentBo(getActivity());
		patientFavoriteBo = new PatientFavoriteBo(getActivity());
		patientQuestionnaireBo = new PatientQuestionnaireBo(getActivity());
		courseRecordBo = new CourseRecordBo(getActivity());

		laboratoryIndexBo = new LaboratoryIndexBo(getActivity());
		testResultBo = new TestResultBo(getActivity());
		searchPageConfigBo = new SearchPageConfigBo(getActivity());
		userBo = new UserBo(getActivity());
	}

	@Override
	protected void setView(View layout) throws Exception {
		context = getActivity();
		btnSearch = (ImageButton) layout.findViewById(R.id.btnSearch);
		btnMore = (Button) layout.findViewById(R.id.btnMore);
		ImageButton_Add = (ImageButton) layout
				.findViewById(R.id.ImageButton_Add);
		editTextCondition = (ClearEditText) layout
				.findViewById(R.id.editTextCondition);
		iPatientCondition = (IPatientCondition) getChildFragmentManager().findFragmentById(R.id.fragment_patient_condition);
		imageViewQuestionnaire = (ImageView) layout
				.findViewById(R.id.imageViewQuestionnaire);
		slideLayoutCondition = (SlideLayout)layout.findViewById(R.id.SlideLayoutCondition);
		switchStatus = (Switch)layout.findViewById(R.id.switchStatus);
	}

	public void onListItemSubClick(View item, ViewGroup parent, int position, int which, PatientHospitalizeBasicInfo data) throws Exception {

		final ViewHolder holder = (ViewHolder) item.getTag();

		final PatientHospitalizeBasicInfo patientInfo = data;

		Global.currentPatient = patientInfo;

		switch (which) {
		case R.id.imageViewLove:
			// 收藏
			String pkey = Global.loginUser.getUser_DBKey() + "+"
					+ patientInfo.getPatientHospitalize_DBKey();

			PatientFavorite patientFavorite = patientFavoriteBo.getDao()
					.queryForId(pkey);
			if (patientFavorite == null) {
				// 添加收藏
				patientFavorite = new PatientFavorite();
				patientFavorite.setPKey(pkey);
				patientFavorite.setUser_DBKey(Global.loginUser
						.getUser_DBKey());
				patientFavorite.setPatientHospitalize_DBKey(patientInfo
						.getPatientHospitalize_DBKey());
				patientFavoriteBo.getDao().create(patientFavorite);
				holder.imageViewLove
						.setBackgroundResource(R.drawable.heart_love);
			} else {
				// 取消收藏
				patientFavoriteBo.getDao().deleteById(pkey);
				holder.imageViewLove
						.setBackgroundResource(R.drawable.heart_love2);
			}
			break;
		case R.id.imageViewQuestionnaire:
			// 筛查
			Intent i = new Intent(PatientListFragment.this.getActivity(),
					QuestionnaireListActivity.class);
			Bundle bundle = new Bundle();
			try {
				bundle.putInt("OperateType", RequestCode.LIST_QUESTIONNAIRE);
				bundle.putString("PatientHospitalize_DBKey",
						patientInfo.getPatientHospitalize_DBKey());
			} catch (Exception e) {
				doException(e);
			}
			i.putExtras(bundle);

			startActivityForResult(i, RequestCode.LIST_QUESTIONNAIRE);

			break;
		case R.id.imageViewCourseRecord:
			// 查房记录
			Intent i2 = new Intent(PatientListFragment.this.getActivity(),
					CourseRecordListActivity.class);
			Bundle bundle2 = new Bundle();
			try {
				bundle2.putInt("OperateType",
						RequestCode.LIST_COURSE_RECORD);
				bundle2.putString("PatientHospitalize_DBKey",
						patientInfo.getPatientHospitalize_DBKey());
			} catch (Exception e) {
				doException(e);
			}
			i2.putExtras(bundle2);

			startActivityForResult(i2, RequestCode.LIST_COURSE_RECORD);
			break;
		case R.id.ImageViewSex:
			// 设置我为我的患者
			// 收藏
			if (Global.loginUser.getUser_DBKey() == 0) {
				PopUtil.AlertDialog(this.getActivity(), "提示", "请用营养师的帐号登录！");
			} else {
				if (patientInfo.getNutrientDoctor_DBKey() == Global.loginUser
						.getUser_DBKey()) {
					// 取消我的患者
					patientInfo.setNutrientDoctor_DBKey(0);
					holder.tvPatientNo.setTextColor(ColorUtil
							.getColor(R.color.gray));// gray
					holder.badgeDietician.hide();
				} else {
					if (patientInfo.getNutrientDoctor_DBKey() == 0) {
						// 设置为我的患者
						patientBo.setPatientDietician(
								PatientListFragment.this.getActivity(),
								holder, patientInfo, userBo);
					} else {
						// 询问是否切换
						patientBo.setPatientDietician2(this.getActivity(),
								holder, patientInfo, userBo);
					}

				}
			}

			patientInfo.setOperateFlag(OperateFlag.NEED_EDIT_TO_SERVER);
			patientBo.getDao().update(patientInfo);
			break;
		case R.id.switchChildStatus:
			if(holder.switchChildStatus.isChecked()){
				patientInfo.setTherapyStatus("0");
				patientInfo.setTherapyStatusName("待筛查");
			}else{
				patientInfo.setTherapyStatus("9");
				patientInfo.setTherapyStatusName("出院");
			}
			patientBo.getDao().update(patientInfo);
			break;
		default:
			break;
		}

	}

	public void doCallBack() {
		try {
			patientBo.getDao().updateOrderBy();
		} catch (SQLException e) {
			doException(e);
		}
		gridListAdapter.refreshList();
	}

}
