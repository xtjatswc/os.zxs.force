package cn.kancare.mobile.activity.questionnaire;

import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.questionnaire.PatientQuestionnaire;
import cn.kancare.mobile.bean.questionnaire.QuestionDetailType;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionBo;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionnaireBo;
import cn.kancare.mobile.bo.questionnaire.PatientQuestionnaireResultBo;
import cn.kancare.mobile.bo.questionnaire.QuestionDetailBo;
import cn.kancare.mobile.bo.questionnaire.QuestionDetailTypeBo;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.QuestionnaireConstant;
import cn.kancare.mobile.common.constant.QuestionnaireConstant.QuestionnaireStatus;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.core.util.ColorUtil;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.activity.BaseListActivity;

public class QuestionnaireListActivity extends
		BaseListActivity<PatientQuestionnaire> {
	QuestionDetailTypeBo questionDetailTypeBo;
	QuestionDetailBo questionDetailBo;

	PatientQuestionnaireBo patientQuestionnaireBo;
	PatientQuestionBo patientQuestionBo;
	PatientQuestionnaireResultBo patientQuestionnaireResultBo;

	RadioGroup radioGroupQType;
	ImageButton btnAdd;
	ImageButton btnChart;
	String PatientHospitalize_DBKey;
	int QuestionProperty = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			final PopupMenu popupMenu = new PopupMenu(this, btnAdd);

			btnAdd.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					if (QuestionProperty == 0) {
						popupMenu.show();
					} else {
						newQuestionnaire(QuestionProperty);
					}
				}
			});

			btnChart.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {

					Intent i = new Intent(QuestionnaireListActivity.this,
							QuestionnaireChartActivity.class);
					startActivity(i);

				}
			});

			Menu menu = popupMenu.getMenu();
			List<QuestionDetailType> lstQuestionDetailTypes = questionDetailTypeBo
					.getDao().queryForAll();
			for (QuestionDetailType questionDetailType : lstQuestionDetailTypes) {
				// 通过代码添加菜单项
				menu.add(Menu.NONE, questionDetailType.getPropertyValue(), 0,
						"+ " + questionDetailType.getPropertyName());
			}

			popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

				public boolean onMenuItemClick(MenuItem item) {

					newQuestionnaire(item.getItemId());

					return false;
				}

			});

			radioGroupQType
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							RadioButton radioButton = (RadioButton) findViewById(checkedId);
							QuestionProperty = Convert.cash2Int(radioButton
									.getTag());
							refreshList();
						}
					});

		} catch (Exception e) {
			doException(e);
		}
	}

	public void newQuestionnaire(int quesProperty) {
		Intent i = null;
		if (quesProperty == QuestionnaireConstant.NRI_TYPEVALUE) {
			i = new Intent(QuestionnaireListActivity.this,
					QuestionnaireNriActivity.class);
		} else {
			i = new Intent(QuestionnaireListActivity.this,
					QuestionnaireActivity.class);
		}

		Bundle bundle = new Bundle();

		bundle.putInt("OperateType", RequestCode.NEW_QUESTIONNAIRE);
		bundle.putInt("QuestionProperty", quesProperty);
		bundle.putString("PatientHospitalize_DBKey", PatientHospitalize_DBKey);

		i.putExtras(bundle);
		startActivityForResult(i, RequestCode.NEW_QUESTIONNAIRE);
	}

	@Override
	protected void receiveIntent(Intent intent) {
		PatientHospitalize_DBKey = intent
				.getStringExtra("PatientHospitalize_DBKey");
	}

	@Override
	protected void initializeBo() throws Exception {
		questionDetailTypeBo = new QuestionDetailTypeBo(this);
		questionDetailBo = new QuestionDetailBo(this);

		patientQuestionnaireBo = new PatientQuestionnaireBo(this);
		patientQuestionBo = new PatientQuestionBo(this);
		patientQuestionnaireResultBo = new PatientQuestionnaireResultBo(this);
	}

	public int getPageSize() {
		return 20;
	}

	/**
	 * 加载更多数据
	 * 
	 * @throws Exception
	 */
	public List<PatientQuestionnaire> getMoreData(int pageSize, int offset)
			throws Exception {

		return patientQuestionnaireBo.getDao().query(pageSize, offset,
				PatientHospitalize_DBKey, QuestionProperty);

	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_QUESTIONNAIRE;
	}

	public int getListId() {
		return R.id.lvQuestionnaire;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.questionnaire_list;
	}

	public int getListItemLayoutId() {
		return R.layout.questionnaire_list_item;
	}

	@Override
	protected void onListItemClick(final PatientQuestionnaire data) {
		super.onListItemClick(data);

		PopUtil.AlertSimpleDialog(this, R.array.QuestionnaireListMenuArray,
				new OnMenuClickListener());
	}

	private class OnMenuClickListener implements
			DialogInterface.OnClickListener {

		public void onClick(DialogInterface arg0, int arg1) {

			if (arg1 == 0) {
				try {
					// 编辑
					Intent i = null;
					if (adapter.getCurrentItem().getQuestionProperty() == QuestionnaireConstant.NRI_TYPEVALUE) {
						i = new Intent(QuestionnaireListActivity.this,
								QuestionnaireNriActivity.class);
					} else {
						i = new Intent(QuestionnaireListActivity.this,
								QuestionnaireActivity.class);
					}

					Bundle bundle = new Bundle();
					try {
						bundle.putString("PatientHospitalize_DBKey",
								PatientHospitalize_DBKey);
						bundle.putInt("OperateType",
								RequestCode.EDIT_QUESTIONNAIRE);
						bundle.putString("PatientQuestionnaire_DBKey", adapter
								.getCurrentItem()
								.getPatientQuestionnaire_DBKey());
					} catch (Exception e) {
						doException(e);
					}
					i.putExtras(bundle);

					startActivityForResult(i, RequestCode.EDIT_QUESTIONNAIRE);
				} catch (Exception e) {
					doException(e);
				}

			} else if (arg1 == 1) {
				try {
					// 查看结果
					if (adapter.getCurrentItem().getQuestionnaireStatus()
							.equals(QuestionnaireStatus.UNFINISHED)) {
						PopUtil.AlertDialog(QuestionnaireListActivity.this,
								"提示", "该问卷调查尚未完成！");
						return;
					}

					Intent i = new Intent(QuestionnaireListActivity.this,
							QuestionnaireResultActivity.class);
					Bundle bundle = new Bundle();
					try {
						bundle.putString("PatientHospitalize_DBKey",
								PatientHospitalize_DBKey);
						bundle.putInt("OperateType",
								RequestCode.VIEW_QUESTIONNAIRE_RESULT);
						bundle.putString("PatientQuestionnaire_DBKey", adapter
								.getCurrentItem()
								.getPatientQuestionnaire_DBKey());
					} catch (Exception e) {
						doException(e);
					}
					i.putExtras(bundle);

					startActivityForResult(i,
							RequestCode.VIEW_QUESTIONNAIRE_RESULT);
				} catch (Exception e) {
					doException(e);
				}
			} else if (arg1 == 2) {
				/*
				 * if (adapter.getCurrentItem().getQuestionnaireStatus()
				 * .equals(QuestionnaireStatus.SUBMITTED)) {
				 * PopUtil.AlertDialog(QuestionnaireListActivity.this, "提示",
				 * "已提交试卷无法删除！"); return; }
				 */

				// 删除
				PopUtil.AlertDialog(QuestionnaireListActivity.this, "提示",
						"确定删除该记录？", "确定",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								try {
									patientQuestionnaireBo
											.deleteQuestionnaire(
													patientQuestionBo,
													patientQuestionnaireResultBo,
													adapter.getCurrentItem()
															.getPatientQuestionnaire_DBKey());

									removeAndRefresh();
								} catch (Exception e) {
									doException(e);
								}
								dialog.dismiss();

							}
						});
			}
			arg0.dismiss();
		}
	}

	public void setListItemView(final int position, View view,
			PatientQuestionnaire data, final ViewGroup parent) {
		QuestionDetailType questionDetailType;
		try {
			questionDetailType = questionDetailTypeBo.getDao()
					.queryQuestionDetailType(data.getQuestionProperty());

			// 问卷编号
			TextView tvQuestionnaireNo = (TextView) view
					.findViewById(R.id.questionnaireNo);
			tvQuestionnaireNo.setText(data.getQuestionnaireNo());
			// 问卷类型
			TextView tvQuestionType = (TextView) view
					.findViewById(R.id.questionType);
			tvQuestionType.setText("	" + questionDetailType.getPropertyName());
			// 得分
			TextView tvScore = (TextView) view.findViewById(R.id.score);

			// 状态
			TextView tvQuestionnaireStatus = (TextView) view
					.findViewById(R.id.zt);
			if (data.getQuestionnaireStatus().equals(
					QuestionnaireStatus.SUBMITTED)) {
				tvQuestionnaireStatus.setText("已提交");

				tvQuestionnaireStatus.setTextColor(ColorUtil
						.getColor(R.color.green));
				// 得分
				if (questionDetailType.getPropertyValue() == QuestionnaireConstant.NRS2002_TYPEVALUE) {
					tvScore.setText(data.getNSR2002Score() + "分");
				} else if (questionDetailType.getPropertyValue() == QuestionnaireConstant.NRI_TYPEVALUE) {
					tvScore.setText(data.getWeight3MonthAgo() + "分");
				} else {
					tvScore.setText(data.getPGSGAScore() + "分");
				}
			} else {
				tvQuestionnaireStatus.setText("待完成");
				tvQuestionnaireStatus.setTextColor(ColorUtil
						.getColor(R.color.red));
				tvScore.setText("___");

			}

			// 调查时间
			TextView tvScreeningDate = (TextView) view
					.findViewById(R.id.recordTime);

			tvScreeningDate.setText(DateHelper.getInstance().getDataString_3(
					data.getScreeningDate()));
			// 不接受营养治疗原因
			TextView tvRemark = (TextView) view.findViewById(R.id.Remark);
			if (data.getRemark().equals("")) {
				tvRemark.setVisibility(View.GONE);
			} else {
				tvRemark.setVisibility(View.VISIBLE);
				tvRemark.setText("	备注：" + data.getRemark());
			}

			// 问卷图标
			ImageView imageViewQuestionnaire = (ImageView) view
					.findViewById(R.id.imageViewQuestionnaire);
			if (data.getQuestionnaireStatus().equals(
					QuestionnaireStatus.SUBMITTED)) {

				Boolean flag = patientQuestionnaireBo.hasThreats(data);

				if (flag) {
					imageViewQuestionnaire
							.setBackgroundResource(R.drawable.questionnaire3);
				}
			}

		} catch (Exception e) {
			doException(e);
		}
	}

	public void onListItemSubClick(View item, View widget, int position, int which) throws Exception {

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		// requestCode标示请求的标示 resultCode表示有数据

		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case RequestCode.NEW_QUESTIONNAIRE:
			case RequestCode.EDIT_QUESTIONNAIRE:
				refreshList();
				break;
			default:
				break;
			}
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		refreshList();
	}

	@Override
	protected void setView() throws Exception {
		btnAdd = (ImageButton) findViewById(R.id.btnAdd);
		btnChart = (ImageButton) findViewById(R.id.btnChart);
		radioGroupQType = (RadioGroup) findViewById(R.id.radioGroupQType);

	}

	public void setViewHolder(View view) {
		// TODO Auto-generated method stub

	}

}
