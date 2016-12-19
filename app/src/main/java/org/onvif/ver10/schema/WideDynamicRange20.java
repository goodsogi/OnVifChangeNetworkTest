package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class WideDynamicRange20 implements Parcelable {
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
	public WideDynamicRange20() {

	}

	public WideDynamicRange20(Parcel src) {
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
	public static final Creator<WideDynamicRange20> CREATOR = new Creator<WideDynamicRange20>() {
		public WideDynamicRange20 createFromParcel(Parcel in) {
			return new WideDynamicRange20(in);
		}

		public WideDynamicRange20[] newArray(int size) {
			return new WideDynamicRange20[size];
		}
	};

}
