package com.commax.onvif_device_manager.uitls;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @brief Static TOAST 클래스
 */
public class TOAST {
	private static Toast mToast;

	public static void show(Context context, String msg) {
		if (mToast == null) {
			mToast = new Toast(context);
			mToast = Toast.makeText(context, null, Toast.LENGTH_LONG);
		}
		mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		mToast.setText(msg);
		mToast.show();
	}
}
