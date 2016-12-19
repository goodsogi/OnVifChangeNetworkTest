package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class WideDynamicRangeOptions20 implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ArrayList<String> mMode;
	public FloatRange mLevel;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public WideDynamicRangeOptions20() {

	}

	public WideDynamicRangeOptions20(Parcel src) {
		this.mMode = new ArrayList<String>();
		int size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			this.mMode.add(utils.readString(src));
		}
		if (utils.readInt(src) != 0) {
			this.mLevel = new FloatRange(src);
		}
	}

	/*****************************************************************************************
	 * @Override describeContents
	 *****************************************************************************************/
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*****************************************************************************************
	 * @Override writeToParcel
	 *****************************************************************************************/
	@Override
	public void writeToParcel(Parcel dst, int flag) {
		if (mMode != null) {
			int size = mMode.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				utils.writeString(dst, mMode.get(i));
			}
		} else {
			utils.writeInt(dst, 0);
		}

		if (mLevel != null) {
			utils.writeInt(dst, 1);
			mLevel.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<WideDynamicRangeOptions20> CREATOR = new Creator<WideDynamicRangeOptions20>() {
		public WideDynamicRangeOptions20 createFromParcel(Parcel in) {
			return new WideDynamicRangeOptions20(in);
		}

		public WideDynamicRangeOptions20[] newArray(int size) {
			return new WideDynamicRangeOptions20[size];
		}
	};

}
