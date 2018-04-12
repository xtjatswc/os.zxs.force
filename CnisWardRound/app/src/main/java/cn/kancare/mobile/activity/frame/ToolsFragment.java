package cn.kancare.mobile.activity.frame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kancare.mobile.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ToolsFragment extends Fragment {

	private GridView gview;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	// 图片封装为一个数组
	private int[] icon = { R.drawable.address_book, R.drawable.calendar,
			R.drawable.camera, R.drawable.clock, R.drawable.games_control,
			R.drawable.messenger, R.drawable.ringtone, R.drawable.settings,
			R.drawable.speech_balloon, R.drawable.weather, R.drawable.world,
			R.drawable.youtube };
	private String[] iconName = { "通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
			"设置", "语音", "天气", "浏览器", "视频" };

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.frame_tools,
				container, false);

		gview = (GridView) messageLayout.findViewById(R.id.gview);
		// 新建List
		data_list = new ArrayList<Map<String, Object>>();
		// 获取数据
		getData();
		// 新建适配器
		String[] from = { "image", "text" };
		int[] to = { R.id.image, R.id.text };
		sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.item, from,
				to);
		// 配置适配器
		gview.setAdapter(sim_adapter);

		// 添加点击事件
		gview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int index = arg2 + 1;// id是从0开始的，所以需要+1
				Toast.makeText(getActivity(), "你按下了选项：" + index, 0)
						.show();
				// Toast用于向用户显示一些帮助/提示
			}
		});

		return messageLayout;
	}

	public List<Map<String, Object>> getData() {
		// cion和iconName的长度是相同的，这里任选其一都可以
		for (int i = 0; i < icon.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", icon[i]);
			map.put("text", iconName[i]);
			data_list.add(map);
		}

		return data_list;
	}

}
