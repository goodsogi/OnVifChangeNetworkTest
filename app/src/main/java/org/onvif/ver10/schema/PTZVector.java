package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class PTZVector implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/

	public Vector2D mPanTilt;
	public Vector1D mZoom;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public PTZVector() {

	}

	public PTZVector(Parcel src) {
		if (utils.readInt(src) != 0) {
			mPanTilt = new Vector2D(src);
		}
		if (utils.readInt(src) != 0) {
			mZoom = new Vector1D(src);
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
		if (mPanTilt != null) {
			utils.writeInt(dst, 1);
			mPanTilt.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mZoom != null) {
			utils.writeInt(dst, 1);
			mZoom.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<PTZVector> CREATOR = new Creator<PTZVector>() {
		public PTZVector createFromParcel(Parcel in) {
			return new PTZVector(in);
		}

		public PTZVector[] newArray(int size) {
			return new PTZVector[size];
		}
	};
}
