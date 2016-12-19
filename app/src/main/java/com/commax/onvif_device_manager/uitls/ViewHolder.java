package com.commax.onvif_device_manager.uitls;

import android.util.SparseArray;
import android.view.View;

/**
 * @brief 모든 Adapter에서 사용되는 ViewHolder
 * @details Adapter성능 향상을 위해 사용 됨.
 */
public class ViewHolder {
	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view, int id) {
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}
}
