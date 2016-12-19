package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class Vector2D implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public float mX;
	public float mY;
	public String mSpace;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Vector2D() {

	}

	public Vector2D(Parcel src) {
		this.mX = utils.readFloat(src);
		this.mY = utils.readFloat(src);
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
		utils.writeFloat(dst, mY);
		utils.writeString(dst, mSpace);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Vector2D> CREATOR = new Creator<Vector2D>() {
		public Vector2D createFromParcel(Parcel in) {
			return new Vector2D(in);
		}

		public Vector2D[] newArray(int size) {
			return new Vector2D[size];
		}
	};

}
