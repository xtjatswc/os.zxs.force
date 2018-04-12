package os.zxs.force.core.event;

import android.os.SystemClock;
import os.zxs.force.core.bridge.CallBackListener;

public class Multihit {

	CallBackListener callBackListener;
 
	// 存储时间的数组
	long[] mHits;

	public Multihit(int click, CallBackListener callBackListener) {
		mHits = new long[click];
		this.callBackListener = callBackListener;
	}

	public void listen() {
		// 双击事件响应
		/**
		 * arraycopy,拷贝数组 src 要拷贝的源数组 srcPos 源数组开始拷贝的下标位置 dst 目标数组 dstPos
		 * 开始存放的下标位置 length 要拷贝的长度（元素的个数）
		 * 
		 */
		// 实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
		System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
		mHits[mHits.length - 1] = SystemClock.uptimeMillis();
		// 多击事件的时间间隔500ms
		if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
			// 多击后具体的操作
			callBackListener.doCallBack();
		}
	}

}
