package os.zxs.force.core.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;

import os.zxs.force.R;

/**
 * @author http://blog.csdn.net/finddreams
 * @Description:自定义对话框
 */
public class Loading extends ProgressDialog {

    private AnimationDrawable mAnimation;
    private ImageView mImageView;
    private String mLoadingTip;
    private TextView mLoadingTv;
    private int count = 0;
    private String oldLoadingTip;
    private int mResid;

    private static Loading loading;

    public static void turn(Context context) {
        turnoff();
        loading = new Loading(context, "正在加载中", R.anim.frame3);
        loading.show();
    }

    public static void turnoff() {
        if (loading != null) {

            Message message = new Message();
            message.obj = loading;
            ;
            new Handler() {
                public void handleMessage(Message msg) {
                    try {
                        Loading loading = (Loading) msg.obj;
                        loading.hide();
                        loading.dismiss();
                    } catch (Exception ex) {}
                }
            }.sendMessageDelayed(message, 500 * 1);
        }
    }

    public Loading(Context context, String content, int id) {
        super(context);
        this.mLoadingTip = content;
        this.mResid = id;
        //按对话框以外的地方起不起作用
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

        mImageView.setBackgroundResource(mResid);
        // 通过ImageView对象拿到背景显示的AnimationDrawable
        mAnimation = (AnimationDrawable) mImageView.getBackground();
        // 为了防止在onCreate方法中只显示第一帧的解决方案之一
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();

            }
        });
        mLoadingTv.setText(mLoadingTip);

    }

    public void setContent(String str) {
        mLoadingTv.setText(str);
    }

    private void initView() {
        setContentView(R.layout.progress_dialog);
        mLoadingTv = (TextView) findViewById(R.id.loadingTv);
        mImageView = (ImageView) findViewById(R.id.loadingIv);
    }

	/*@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		mAnimation.start(); 
		super.onWindowFocusChanged(hasFocus);
	}*/
}
