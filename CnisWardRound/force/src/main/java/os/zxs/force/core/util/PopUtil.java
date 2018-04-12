package os.zxs.force.core.util;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.Toast;

public class PopUtil {
	public static void show(Context context, String msg) {
		Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	// 弹出简单菜单
	public static void AlertSimpleDialog(Context context, int arrayId,
			DialogInterface.OnClickListener onClickListener) {
		AlertDialog.Builder builder = new Builder(context);

		builder.setItems(context.getResources().getStringArray(arrayId),
				onClickListener);
		builder.show();
	}

	// 带取消按钮的Dialog
	public static void AlertDialog(Context context, String title,
			String message, String buttonText1,
			DialogInterface.OnClickListener onClickListener1,
			String buttonText2, DialogInterface.OnClickListener onClickListener2) {
		AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
		builder2.setTitle(title);
		builder2.setMessage(message);
		builder2.setNegativeButton(buttonText2, onClickListener2);
		builder2.setPositiveButton(buttonText1, onClickListener1);
		builder2.show();
	}

	// 带取消按钮的Dialog
	public static void AlertDialog(Context context, String title,
			String message, String buttonText,
			DialogInterface.OnClickListener onOKClickListener) {
		AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
		builder2.setTitle(title);
		builder2.setMessage(message);
		builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder2.setPositiveButton(buttonText, onOKClickListener);
		builder2.show();
	}

	// 一个自定义按钮的Dialog
	public static void AlertDialogOneButton(Context context, String title,
			String message, String buttonText,
			DialogInterface.OnClickListener onOKClickListener) {
		AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
		builder2.setTitle(title);
		builder2.setMessage(message);
		builder2.setPositiveButton(buttonText, onOKClickListener);
		builder2.show();
	}

	// 带确定钮的Dialog
	public static void AlertDialog(Context context, String title, String message) {
		AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
		builder2.setTitle(title);
		builder2.setMessage(message);
		builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder2.show();
	}

	// 带取确定钮的Dialog，自定义事件
	public static void AlertDialog(Context context, String title,
			String message, DialogInterface.OnClickListener onOKClickListener) {
		AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
		builder2.setTitle(title);
		builder2.setMessage(message);
		builder2.setPositiveButton("确定", onOKClickListener);
		builder2.show();
	}
}
