package os.zxs.force.core.view;

import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import os.zxs.force.core.bridge.CallBackListener;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;

public class DatePickerView extends EditText {

	CallBackListener callBackListener;
	public DatePickerDialog datePicker;
	public java.util.Date date;

	public java.util.Date getDate() {
		return date;
	}

	public void setCallBack(CallBackListener callBackListener) {
		this.callBackListener = callBackListener;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
		this.setText(DateHelper.getInstance().getDataString_2(date));
		datePicker = new DatePickerDialog(this.getContext(),
				new OnDateSetListener() {

					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						DatePickerView.this.date = new Date(year - 1900,
								monthOfYear, dayOfMonth);
						DatePickerView.this.setText(DateHelper.getInstance()
								.getDataString_2(DatePickerView.this.date));

						if (callBackListener != null) {
							callBackListener.doCallBack();
						}
					}
				}, DateHelper.getInstance().getYear(date), date.getMonth(),
				date.getDate());

		if (callBackListener != null) {
			callBackListener.doCallBack();
		}
	}

	public DatePickerView(Context context) {
		this(context, null);
	}

	public DatePickerView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.editTextStyle);
	}

	public DatePickerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initWedgits(context, attrs);
	}

	private void initWedgits(Context context, AttributeSet attrs) {

		this.setFocusable(false);

		Date d = new Date();
		setDate(d);

		this.setOnTouchListener(new onTouchListener());

	}

	class onTouchListener implements OnTouchListener {

		public boolean onTouch(View v, MotionEvent event) {

			try {
				EditText et_start_time = (EditText) v;
				int inType = et_start_time.getInputType();
				et_start_time.setInputType(InputType.TYPE_NULL);

				datePicker.show();

				et_start_time.setInputType(inType);
				et_start_time.setSelection(et_start_time.getText().length());

			} catch (Exception e) {
				PopUtil.AlertDialog(DatePickerView.this.getContext(), "异常信息",
						e.getMessage());

			}

			return false;
		}

	}

}
