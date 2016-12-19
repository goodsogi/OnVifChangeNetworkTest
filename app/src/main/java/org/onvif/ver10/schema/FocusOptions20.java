package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class FocusOptions20 implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ArrayList<String> mAutoFocusModes;
	public FloatRange mDefaultSpeed;
	public FloatRange mNearLimit;
	public FloatRange mFarLimit;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public FocusOptions20() {

	}

	public FocusOptions20(Parcel src) {
		this.mAutoFocusModes = new ArrayList<String>();
		int size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			this.mAutoFocusModes.add(utils.readString(src));
		}

		if (utils.readInt(src) != 0) {
			this.mDefaultSpeed = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mNearLimit = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mFarLimit = new FloatRange(src);
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
		if (mAutoFocusModes != null) {
			int size = mAutoFocusModes.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				utils.writeString(dst, mAutoFocusModes.get(i));
			}
		} else {
			utils.writeInt(dst, 0);
		}

		if (mDefaultSpeed != null) {
			utils.writeInt(dst, 1);
			mDefaultSpeed.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mNearLimit != null) {
			utils.writeInt(dst, 1);
			mNearLimit.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mFarLimit != null) {
			utils.writeInt(dst, 1);
			mFarLimit.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<FocusOptions20> CREATOR = new Creator<FocusOptions20>() {
		public FocusOptions20 createFromParcel(Parcel in) {
			return new FocusOptions20(in);
		}

		public FocusOptions20[] newArray(int size) {
			return new FocusOptions20[size];
		}
	};

}
