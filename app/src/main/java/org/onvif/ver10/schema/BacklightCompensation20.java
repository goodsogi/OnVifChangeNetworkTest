package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class BacklightCompensation20 implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mMode;
	public float mLevel;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public BacklightCompensation20() {

	}

	public BacklightCompensation20(Parcel src) {
		this.mMode = utils.readString(src);
		this.mLevel = utils.readFloat(src);
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
		utils.writeFloat(dst, mLevel);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<BacklightCompensation20> CREATOR = new Creator<BacklightCompensation20>() {
		public BacklightCompensation20 createFromParcel(Parcel in) {
			return new BacklightCompensation20(in);
		}

		public BacklightCompensation20[] newArray(int size) {
			return new BacklightCompensation20[size];
		}
	};

}
