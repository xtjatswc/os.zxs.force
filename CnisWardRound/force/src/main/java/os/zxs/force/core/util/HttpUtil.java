package os.zxs.force.core.util;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.loopj.android.http.HttpGet;

public class HttpUtil {
	public static String doHttpGet(String url) throws Exception, IOException {

		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {

			String json = EntityUtils.toString(httpResponse.getEntity());
			return json;
		}

		return "";
	}

	public static String doHttpPost(String url, List<NameValuePair> params) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("bookname", etBookName.getText()
//				.toString()));
		
		
		httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(httpResponse.getEntity());
			return result;
		}

		return "";
	}

}
