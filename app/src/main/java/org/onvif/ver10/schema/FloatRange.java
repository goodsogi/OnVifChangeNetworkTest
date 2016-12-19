package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class FloatRange implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public float mMin;
	public float mMax;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public FloatRange() {

	}

	public FloatRange(Parcel src) {
		this.mMin = utils.readFloat(src);
		this.mMax = utils.readFloat(src);

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
		utils.writeFloat(dst, mMin);
		utils.writeFloat(dst, mMax);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<FloatRange> CREATOR = new Creator<FloatRange>() {
		public FloatRange createFromParcel(Parcel in) {
			return new FloatRange(in);
		}

		public FloatRange[] newArray(int size) {
			return new FloatRange[size];
		}
	};

}
