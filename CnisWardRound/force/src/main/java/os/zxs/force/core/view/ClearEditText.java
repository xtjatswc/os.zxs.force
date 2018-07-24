package os.zxs.force.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import os.zxs.force.R;
import os.zxs.force.core.bridge.CallBackListener;
import os.zxs.force.core.util.ResUtil;

public class ClearEditText extends EditText implements TextWatcher,
		OnFocusChangeListener {

	//清空文本框时触发
	CallBackListener callBackListener;
	public void setCallBackListener(CallBackListener callBack) {
		callBackListener = callBack;
	}

	//文本框输入改变时触发
	CallBackListener callBackChangeListener;
	public void setCallBackChangeListener(CallBackListener callBack) {
		callBackChangeListener = callBack;
	}

	/**
	 * 左右两侧图片资源
	 */
	private Drawable left, right;
	/**
	 * 是否获取焦点，默认没有焦点
	 */
	private boolean hasFocus = false;
	/**
	 * 手指抬起时的X坐标
	 */
	private int xUp = 0;

	public ClearEditText(Context context) {
		this(context, null);
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.editTextStyle);
	}

	public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initWedgits(attrs);
	}

	/**
	 * 初始化各组件
	 * 
	 * @param attrs
	 *            属性集
	 */
	private void initWedgits(AttributeSet attrs) {
		try {
			left = getCompoundDrawables()[0];
			right = getCompoundDrawables()[2];
			if (right == null) {
				// 如果没有设置清除图标，则默认设置一个
				final Resources resources = getContext().getResources();
				int resId = R.drawable.clear_normal_list;
				resId = ResUtil.getInstanse()
						.getDrawableId("clear_normal_list");
				setCompoundDrawablesWithIntrinsicBounds(left, null,
						resources.getDrawable(resId), null);
				right = getCompoundDrawables()[2];
			}
			initDatas();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化数据
	 */
	private void initDatas() {
		try {
			// 如果文本为空，则隐藏删除图标
			if (TextUtils.isEmpty(this.getText())) {
				setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
			}
			addListeners();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加事件监听
	 */
	private void addListeners() {
		try {
			setOnFocusChangeListener(this);
			addTextChangedListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int after) {
		if (hasFocus) {
			if (TextUtils.isEmpty(s)) {
				// 如果为空，则不显示删除图标
				setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
				// 触发清空事件
				if(callBackListener != null){
					callBackListener.doCallBack();
				}
				
			} else {
				// 如果非空，则要显示删除图标
				if (null == right) {
					right = getCompoundDrawables()[2];
				}
				setCompoundDrawablesWithIntrinsicBounds(left, null, right, null);
				//触发文本改变事件
				if(callBackChangeListener != null){
					callBackChangeListener.doCallBack();
				}
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		try {
			switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
				// 获取点击时手指抬起的X坐标
				xUp = (int) event.getX();
				// 当点击的坐标到当前输入框右侧的距离小于等于getCompoundPaddingRight()的距离时，则认为是点击了删除图标
				// getCompoundPaddingRight()的说明：Returns the right padding of the
				// view, plus space for the right Drawable if any.
				if ((getWidth() - xUp) <= getCompoundPaddingRight()) {
					if (!TextUtils.isEmpty(getText().toString())) {
						setText("");
					}
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.onTouchEvent(event);
	}

	public void afterTextChanged(Editable s) {
	}

	public void onFocusChange(View v, boolean hasFocus) {
		try {
			this.hasFocus = hasFocus;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
