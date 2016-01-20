package com.youmu.wheelpicker.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.youmu.wheelpicker.R;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

public abstract class BaseDialog extends DialogFragment {

	protected Context mContext;

	private static WeakReference<BaseDialog> dialogWeakReference;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}

	public static void dissmissDialog() {
		try {
			BaseDialog dialog = dialogWeakReference.get();
			if (dialog != null && dialog.isAdded()) {
				dialog.dismiss();
				dialog = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = new Dialog(getActivity(), onGetStyle());
		dialog.setCanceledOnTouchOutside(true);
		dialog.setCancelable(true);
		View view = View.inflate(getActivity(), onGetDialogViewId(), null);
		dialog.setContentView(view);
		ButterKnife.bind(this, view);
		onDialogCreated(dialog);
		return dialog;
	}

	protected abstract void onDialogCreated(Dialog dialog);

	protected abstract int onGetDialogViewId();
	
	protected int onGetStyle() {
		return R.style.custom_dialog;
	}

	public void show(FragmentManager fragmentManager) {
		dialogWeakReference = new WeakReference<BaseDialog>(this);
		show(fragmentManager, "dialog");
	}

}
