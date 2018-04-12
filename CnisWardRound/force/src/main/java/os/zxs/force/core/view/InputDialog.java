package os.zxs.force.core.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class InputDialog {

	public interface InputDialogListener {
		public void doResult(int pos, String newValue);
	}

	public class InputDataType {
		public static final int DECIMAL = 1;
		public static final int INT = 2;
		public static final int VARCHAR = 3;
	}

	public class ButtonPos {
		public static final int PositiveButton = 1;
		public static final int NeutralButton = 2;
		public static final int NegativeButton = 3;
	}

	public static void show(final Context context, String title,
			int inputDataType, String defaultValue, String hint,
			final InputDialogListener inputDialogListener) {

		final EditText input = getInput(context, inputDataType, defaultValue,
				hint);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setIcon(android.R.drawable.ic_dialog_info)
				.setView(input)
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						Close(context, input, dialog);
					}
				});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				inputDialogListener.doResult(ButtonPos.PositiveButton, input
						.getText().toString());

				Close(context, input, dialog);
			}
		});
		builder.show();
	}

	// 带2个自定义按钮
	public static void show2(final Context context, String title,
			int inputDataType, String defaultValue, String hint,
			final InputDialogListener inputDialogListener,
			String PositiveButtonText, String NegativeButtonText) {

		final EditText input = getInput(context, inputDataType, defaultValue,
				hint);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setIcon(android.R.drawable.ic_dialog_info)
				.setView(input);

		builder.setPositiveButton(PositiveButtonText,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						inputDialogListener.doResult(ButtonPos.PositiveButton,
								input.getText().toString());

						Close(context, input, dialog);
					}

				});

		builder.setNeutralButton(NegativeButtonText,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						inputDialogListener.doResult(ButtonPos.NeutralButton,
								input.getText().toString());

						Close(context, input, dialog);
					}
				});

		builder.show();
	}

	// 带三个自定义按钮
	public static void show3(final Context context, String title,
			int inputDataType, String defaultValue, String hint,
			final InputDialogListener inputDialogListener,
			String PositiveButtonText, String NeutralButtonText,
			String NegativeButtonText) {

		final EditText input = getInput(context, inputDataType, defaultValue,
				hint);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setIcon(android.R.drawable.ic_dialog_info)
				.setView(input);

		builder.setPositiveButton(PositiveButtonText,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						inputDialogListener.doResult(ButtonPos.PositiveButton,
								input.getText().toString());

						Close(context, input, dialog);
					}

				});

		builder.setNeutralButton(NeutralButtonText,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						inputDialogListener.doResult(ButtonPos.NeutralButton,
								input.getText().toString());

						Close(context, input, dialog);
					}
				});

		builder.setNegativeButton(NegativeButtonText,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						inputDialogListener.doResult(ButtonPos.NegativeButton,
								input.getText().toString());

						Close(context, input, dialog);
					}
				});

		builder.show();
	}

	private static EditText getInput(final Context context, int inputDataType,
			String defaultValue, String hint) {
		final EditText input = new EditText(context);
		if (inputDataType == InputDataType.DECIMAL) {
			int inputType = InputType.TYPE_CLASS_NUMBER
					| InputType.TYPE_NUMBER_VARIATION_NORMAL
					| InputType.TYPE_NUMBER_FLAG_DECIMAL;
			input.setInputType(inputType);
		} else if (inputDataType == InputDataType.INT) {
			int inputType = InputType.TYPE_CLASS_NUMBER
					| InputType.TYPE_NUMBER_VARIATION_NORMAL;
			input.setInputType(inputType);
		}

		input.setGravity(Gravity.CENTER_HORIZONTAL);
		input.setSelectAllOnFocus(true);
		input.setText(defaultValue);
		input.setHint(hint);

		return input;
	}

	// 关闭键盘并注销窗口
	private static void Close(final Context context, final EditText input,
			DialogInterface dialog) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(input.getWindowToken(), 0);

		dialog.dismiss();
	}
}
