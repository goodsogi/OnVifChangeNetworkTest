package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class WhiteBalance20 implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mMode;
	public float mCrGain;
	public float mCbGain;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public WhiteBalance20() {

	}

	public WhiteBalance20(Parcel src) {
		this.mMode = utils.readString(src);
		this.mCrGain = utils.readFloat(src);
		this.mCbGain = utils.readFloat(src);
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
		utils.writeString(dst, mMode);
		utils.writeFloat(dst, mCrGain);
		utils.writeFloat(dst, mCbGain);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<WhiteBalance20> CREATOR = new Creator<WhiteBalance20>() {
		public WhiteBalance20 createFromParcel(Parcel in) {
			return new WhiteBalance20(in);
		}

		public WhiteBalance20[] newArray(int size) {
			return new WhiteBalance20[size];
		}
	};

}
