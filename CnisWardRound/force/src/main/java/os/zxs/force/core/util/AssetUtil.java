package os.zxs.force.core.util;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;

public class AssetUtil {

	/**
	 * 把资源文本文件送到String串中
	 * 
	 * @param is
	 * @return
	 */
	public static String readStream(InputStream is) {
		String res;
		try {
			byte[] buf = new byte[is.available()];
			is.read(buf);
			res = new String(buf, "utf-8");
			is.close();
		} catch (Exception e) {
			res = "";
		}
		return (res);

	}

	public static String getContent(String fileName)
			throws IOException {
		AssetManager aM = ContextUtil.getInstance().getAssets();
		InputStream is = aM.open(fileName);
		String text = readStream(is);
		is.close();
		return text;
	}
}
