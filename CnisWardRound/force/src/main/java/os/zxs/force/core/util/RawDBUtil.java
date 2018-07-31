package os.zxs.force.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RawDBUtil {
	String sp = File.separator;
	private Context mContext = null;
	private String[] fileList = null; // 数据库文件列表
	private int choicePostion = -3; // 选择数据库列表中的位置
	private AlertDialog dialog = null;
	private String appName = "myApp";
	private String backFolder = "backup";
	String[] dbNames = { "数据库名称" };// 待备份的文件列表

	public RawDBUtil(Context context, String appName, String backFolder,
			String[] dbNames) {
		mContext = context;
		this.appName = appName;
		this.backFolder = backFolder;
		this.dbNames = dbNames;
	}

	/**
	 * 恢复数据的Dialog
	 */
	public void restoreDB() {
		fileList = getFileList();
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("恢复");
		builder.setSingleChoiceItems(getFileList(), -1, new DialogClick());
		builder.setPositiveButton("确定", new DialogClick());
		builder.setNegativeButton("取消", new DialogClick());
		builder.show();
	}

	/**
	 * 备份数据库
	 */
	public void backupDB() {
		showDialog("是否备份数据库", 'B');
		PopUtil.show(mContext, "备份成功！");
	}

	/**
	 * 判断文件或路径是否存在
	 */
	public Boolean fileExists(String folderName, String fileName) {
		String path = Environment.getExternalStorageDirectory()
				+ File.separator + appName + File.separator + backFolder
				+ File.separator + folderName + File.separator + fileName;
		File f = new File(path);
		return f.exists();
	}

	/**
	 * 复制数据库到指定文件夹下
	 * 
	 * @throws IOException
	 */
	public void rawToFile(int rawId, String folderName, String fileName)
			throws IOException {

		File sdFile = sdCardOk();
		if (sdFile != null) {

			// 创建目录
			File f = new File(sdFile.getAbsolutePath() + sp + folderName);
			if (!f.exists()) {
				f.mkdirs();
			}

			// 创建文件
			File backFile = new File(f.getAbsolutePath() + sp + fileName);
			backFile.createNewFile();

			FileOutputStream os = new FileOutputStream(backFile);// 得到数据库文件的写入流
			InputStream is = mContext.getResources().openRawResource(rawId);
			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				os.write(buffer, 0, count);
				os.flush();
			}
			is.close();
			os.close();
		}
	}

	/**
	 * 显示一个Dialog
	 * 
	 * @param title
	 *            标题 ，必须引用资源ID resource ID
	 * @param sign
	 *            根据标示调用方法 I - 恢复默认设置 D - 恢复默认设置 H -选择主机
	 */
	private void showDialog(String title, char sign) {
		/*
		 * final char s = sign; new
		 * AlertDialog.Builder(mContext).setTitle(title)
		 * .setPositiveButton("确定", new OnClickListener() { public void
		 * onClick(DialogInterface dialogI, int which) { switch (s) { case 'B':
		 * // 备份数据库 if (dialog == null) { dialog = awaitDialog(mContext); } else
		 * { dialog.show(); } new ExecuteTask().execute('B'); break; default:
		 * break; } } }).setNegativeButton("取消", new OnClickListener() { public
		 * void onClick(DialogInterface dialog, int which) { } }).show();
		 */
		new ExecuteTask().execute('B');
	}

	/**
	 * 备份操作
	 * 
	 * @return
	 */
	private boolean backUp() {
		boolean isOk = false;
		File sdFile = sdCardOk();
		if (sdFile != null) {
			try {
				// String[] dbNames = { "数据库名称" };
				// 创建日期文件夹
				String folder_date = datePrefix();
				File f = new File(sdFile.getAbsolutePath() + sp + folder_date);
				if (!f.exists()) {
					f.mkdirs();
				}
				for (int i = 0; i < dbNames.length; i++) {
					String dbName = dbNames[i];
					File dbFile = dbOk(dbName);
					if (dbFile != null) {
						File backFile = new File(f.getAbsolutePath() + sp
								+ dbFile.getName());
						backFile.createNewFile();
						isOk = fileCopy(backFile, dbFile.getAbsoluteFile());
						if (!isOk) {
							break;
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isOk;
	}

	/**
	 * 时间前缀
	 * 
	 * @return
	 */
	private String datePrefix() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date(System.currentTimeMillis());
		String str = format.format(date);
		return str;
	}

	/**
	 * 文件夹列表
	 * 
	 * @return
	 */
	private String[] getFileList() {
		String[] fileList = null;
		File file = sdCardOk();
		if (file != null) {
			File[] list = file.listFiles();
			if (list != null && list.length > 0) {
				fileList = new String[list.length];
				for (int i = 0; i < list.length; i++) {
					fileList[i] = list[i].getName();
				}
			}
		}
		return fileList;
	}

	/**
	 * sdCard是否存在 备份的文件夹是否存在
	 * 
	 * @return null不能使用
	 */
	private File sdCardOk() {
		File bkFile = null;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			String backUpPath = Environment.getExternalStorageDirectory() + sp
					+ appName + sp + backFolder;
			bkFile = new File(backUpPath);
			if (!bkFile.exists()) {
				bkFile.mkdirs();
			} else
				return bkFile;
		} else
			Toast.makeText(mContext, "Sdcard 不存在", Toast.LENGTH_SHORT).show();
		return bkFile;
	}

	/**
	 * 恢复数据库
	 * 
	 * @param name
	 *            选择的文件名称 选中的数据库名称
	 * @param resoreDbName
	 *            需要恢复的数据库名称
	 * @return
	 */
	public boolean restore(String name, File f) {
		boolean isOk = false;
		if (f != null) {
			File dbFile = dbOk(name);
			try {
				// System.out.println("覆盖的名称"+dbName);
				if (dbFile != null) {
					isOk = fileCopy(dbFile, f.getAbsoluteFile());
				} else
					isOk = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isOk;
	}

	/**
	 * 数据库文件是否存在，并可以使用
	 * 
	 * @return
	 */
	private File dbOk(String dbName) {
		String absPath = Environment.getDataDirectory().getAbsolutePath();
		String pakName = mContext.getPackageName();
		String dbPath = absPath + sp + "data" + sp + pakName + sp + "databases"
				+ sp + dbName;
		File file = new File(dbPath);
		if (file.exists()) {
			return file;
		} else {
			return null;
		}
	}

	/**
	 * 等候动画
	 */
	public AlertDialog awaitDialog(Context context) {
		ProgressBar bar = new ProgressBar(context);
		bar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCancelable(false);
		dialog.show();
		Window window = dialog.getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.width = 50;
		params.height = 50;
		window.setAttributes(params);
		window.setContentView(bar);
		return dialog;
	}

	/**
	 * 
	 * @param outFile
	 *            写入
	 * @param inFile
	 *            读取
	 * @throws FileNotFoundException
	 */
	private boolean fileCopy(File outFile, File inFile) throws IOException {
		if (outFile == null || inFile == null) {
			return false;
		}
		boolean isOk = true;
		FileChannel inChannel = new FileInputStream(inFile).getChannel();// 只读
		FileChannel outChannel = new FileOutputStream(outFile).getChannel();// 只写
		try {
			long size = inChannel.transferTo(0, inChannel.size(), outChannel);
			if (size <= 0) {
				isOk = false;
			}
		} catch (IOException e) {
			isOk = false;
			e.printStackTrace();
		} finally {
			if (inChannel != null) {
				inChannel.close();
			}
			if (outChannel != null) {
				outChannel.close();
			}
		}
		return isOk;
	}

	private class DialogClick implements DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			if (which == -1) {// 确定
				if (choicePostion < 0) {
					Toast.makeText(mContext, "选择数据库", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				String folderName = fileList[choicePostion];
				String backUpPath = Environment.getExternalStorageDirectory()
						+ sp + appName + sp + backFolder + sp + folderName;
				File file = new File(backUpPath);
				if (file.isDirectory()) {
					File[] files = file.listFiles();
					boolean isOk = false;
					for (int i = 0; i < files.length; i++) {
						File f = files[i];
						isOk = restore(f.getName(), f);
						if (!isOk) {
							String fail_msg = "恢复失败" + ":" + f.getName();
							Toast.makeText(mContext, fail_msg,
									Toast.LENGTH_SHORT).show();
							return;
						}
					}
					if (isOk) {
						// 如果有数据体现则需要刷新出新的数据
						PopUtil.AlertDialog(mContext, "提示", "还原成功，重新登录后生效！", "确定",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog, int whichButton) {

										// finish();//
										android.os.Process.killProcess(android.os.Process
												.myPid()); // 获取PID
										System.exit(0); // 常规java、c#的标准退出法，返回值为0代表正常退出
									}

								});

					}
				}
			} else if (which == -2) {// 取消
			} else if (which >= 0) {
				choicePostion = which;
			}
		}
	}

	/**
	 * 执行任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ExecuteTask extends AsyncTask<Character, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Character... params) {
			char c = params[0];
			if (c == 'B') {
				backUp();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (dialog != null) {
				dialog.dismiss();
			}
		}
	}
}