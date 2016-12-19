package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class Vector1D implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public float mX;
	public String mSpace;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Vector1D() {

	}

	public Vector1D(Parcel src) {
		this.mX = utils.readFloat(src);
		this.mSpace = utils.readString(src);

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
		utils.writeFloat(dst, mX);
		utils.writeString(dst, mSpace);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Vector1D> CREATOR = new Creator<Vector1D>() {
		public Vector1D createFromParcel(Parcel in) {
			return new Vector1D(in);
		}

		public Vector1D[] newArray(int size) {
			return new Vector1D[size];
		}
	};

}
