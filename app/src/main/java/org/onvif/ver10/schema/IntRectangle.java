package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class IntRectangle implements Parcelable {
	private final static String TAG = IntRectangle.class.getSimpleName();

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mX;
	public int mY;
	public int mWidth;
	public int mHeight;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public IntRectangle() {
	}

	public IntRectangle(Parcel src) {
		this.mX = utils.readInt(src);
		this.mY = utils.readInt(src);
		this.mWidth = utils.readInt(src);
		this.mHeight = utils.readInt(src);
	}

	/*****************************************************************************************
	 * @Override describeContents
	 *****************************************************************************************/
	@Override
	public int describeContents() {
		return 0;
	}

	/*****************************************************************************************
	 * @Override writeToParcel
	 *****************************************************************************************/
	@Override
	public void writeToParcel(Parcel dst, int flags) {
		utils.writeInt(dst, mX);
		utils.writeInt(dst, mY);
		utils.writeInt(dst, mWidth);
		utils.writeInt(dst, mHeight);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<IntRectangle> CREATOR = new Creator<IntRectangle>() {
		public IntRectangle createFromParcel(Parcel in) {
			return new IntRectangle(in);
		}

		public IntRectangle[] newArray(int size) {
			return new IntRectangle[size];
		}
	};

}