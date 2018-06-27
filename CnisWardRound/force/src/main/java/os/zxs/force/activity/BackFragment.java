package os.zxs.force.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import os.zxs.force.R;
import os.zxs.force.core.view.fragment.BaseFragment;
import os.zxs.force.core.view.fragment.OnBackListener;

public abstract class BackFragment extends BaseFragment {
	OnBackListener onBackListener;

	protected TextView textViewBack;
	protected TextView textViewTitle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = super.onCreateView(inflater, container,
				savedInstanceState);

		try {
			textViewBack.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (onBackListener == null) {
						doBack();
					} else {
						onBackListener.BeforPressBack(BackFragment.this);
					}
				}
			});
		} catch (Exception e) {
			doException(e);
		}

		return layout;
	}

	public void doBack() {
		getActivity().onBackPressed();
	}

	protected abstract String getLogTag();

	protected abstract void initializeBo() throws Exception;;

	@Override
	protected int getLayoutId() {
		return R.layout.back;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		try {
			onBackListener = (OnBackListener) context;
		} catch (Exception e) {

		}
	}

	@Override
	protected void setView(View layout) throws Exception {
		textViewBack = (TextView) layout.findViewById(R.id.textViewBack);
		textViewTitle = (TextView) layout.findViewById(R.id.textViewTitle);
	}
}