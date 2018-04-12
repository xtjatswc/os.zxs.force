package com.basgeekball.awesomevalidation.validators;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Build;
import android.text.Html;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.model.NumericRange;
import com.basgeekball.awesomevalidation.utility.ValidationCallback;

public abstract class Validator {

	protected ArrayList<ValidationHolder> mValidationHolderList;

	public Validator() {
		mValidationHolderList = new ArrayList<ValidationHolder>();
	}

	public void set(EditText editText, String regex, String errMsg) {
		Pattern pattern = Pattern.compile(regex);
		ValidationHolder validationHolder = new ValidationHolder(editText,
				pattern, errMsg);
		mValidationHolderList.add(validationHolder);
	}

	public void set(Activity activity, int viewId, String regex, int errMsgId) {
		EditText editText = (EditText) activity.findViewById(viewId);
		String errMsg = activity.getResources().getString(errMsgId);
		set(editText, regex, errMsg);
	}

	public void set(EditText editText, Pattern pattern, String errMsg) {
		ValidationHolder validationHolder = new ValidationHolder(editText,
				pattern, errMsg);
		mValidationHolderList.add(validationHolder);
	}

	public void set(Activity activity, int viewId, Pattern pattern, int errMsgId) {
		EditText editText = (EditText) activity.findViewById(viewId);
		String errMsg = activity.getResources().getString(errMsgId);
		set(editText, pattern, errMsg);
	}

	public void set(EditText editText, NumericRange numericRange, String errMsg) {
		ValidationHolder validationHolder = new ValidationHolder(editText,
				numericRange, errMsg);
		mValidationHolderList.add(validationHolder);
	}

	public void set(Activity activity, int viewId, NumericRange numericRange,
			int errMsgId) {
		EditText editText = (EditText) activity.findViewById(viewId);
		String errMsg = activity.getResources().getString(errMsgId);
		set(editText, numericRange, errMsg);
	}

	protected boolean checkFields(ValidationCallback callback) {
		boolean result = true;
		boolean hasFailed = false;
		for (ValidationHolder validationHolder : mValidationHolderList) {
			Matcher matcher = null;
			boolean valid = true;
			if (validationHolder.isRegexType()) {
				matcher = validationHolder.getPattern().matcher(
						validationHolder.getText());
				valid = matcher.matches();
			} else if (validationHolder.isRangeType()) {
				try {
					valid = validationHolder.getNumericRange().isValid(
							validationHolder.getText());
				} catch (NumberFormatException e) {
					valid = false;
				}
				if (!valid) {
					matcher = Pattern.compile("±*").matcher(
							validationHolder.getText());
				}
			}
			if (!valid && matcher != null) {
				callback.execute(validationHolder, matcher);
				if (!hasFailed) {
					EditText editText = validationHolder.getEditText();
					editText.requestFocus();
					editText.setSelection(editText.getText().length());
					hasFailed = true;
				}
				result = false;
			}
		}
		return result;
	}

	public abstract boolean trigger();

	public abstract void halt();

	
	/**给文字设定颜色，避免因app设置thame造成EditText errorText显示空白的问题
	 * #ffffff 白色
	 * #000000   黑色
	 * @param error
	 * @return
	 */
	protected CharSequence setErrorTextColor(String error) {
		int version = Build.VERSION.SDK_INT;
		if (version >= 14) {
			CharSequence text = Html.fromHtml("<font color=#ffffff>" + error
					+ "</font>");
			return text;
		} else {
			return error;
		}
	}
}