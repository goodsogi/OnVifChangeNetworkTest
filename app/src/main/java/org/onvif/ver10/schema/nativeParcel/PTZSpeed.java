package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.Vector1D;
import org.onvif.ver10.schema.Vector2D;

public class PTZSpeed implements Parcelable {
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
	public PTZSpeed() {

	}

	public PTZSpeed(Parcel src) {
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
	public static final Creator<PTZSpeed> CREATOR = new Creator<PTZSpeed>() {
		public PTZSpeed createFromParcel(Parcel in) {
			return new PTZSpeed(in);
		}

		public PTZSpeed[] newArray(int size) {
			return new PTZSpeed[size];
		}
	};
}
