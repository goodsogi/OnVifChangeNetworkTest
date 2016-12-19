package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class FocusConfiguration20 implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mAutoFocusMode;
	public float mDefaultSpeed;
	public float mNearLimit;
	public float mFarLimit;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public FocusConfiguration20() {

	}

	public FocusConfiguration20(Parcel src) {
		this.mAutoFocusMode = utils.readString(src);
		this.mDefaultSpeed = utils.readFloat(src);
		this.mNearLimit = utils.readFloat(src);
		this.mFarLimit = utils.readFloat(src);
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
		utils.writeString(dst, mAutoFocusMode);
		utils.writeFloat(dst, mDefaultSpeed);
		utils.writeFloat(dst, mNearLimit);
		utils.writeFloat(dst, mFarLimit);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<FocusConfiguration20> CREATOR = new Creator<FocusConfiguration20>() {
		public FocusConfiguration20 createFromParcel(Parcel in) {
			return new FocusConfiguration20(in);
		}

		public FocusConfiguration20[] newArray(int size) {
			return new FocusConfiguration20[size];
		}
	};

}
