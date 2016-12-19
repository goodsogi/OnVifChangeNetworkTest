package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class Rectangle implements Parcelable {
	private final static String TAG = Rectangle.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public float mBottom;
	public float mTop;
	public float mRight;
	public float mLeft;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Rectangle() {
	}

	public Rectangle(Parcel src) {
		this.mBottom = utils.readFloat(src);
		this.mTop = utils.readFloat(src);
		this.mRight = utils.readFloat(src);
		this.mLeft = utils.readFloat(src);
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
		utils.writeFloat(dst, mBottom);
		utils.writeFloat(dst, mTop);
		utils.writeFloat(dst, mRight);
		utils.writeFloat(dst, mLeft);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Rectangle> CREATOR = new Creator<Rectangle>() {
		public Rectangle createFromParcel(Parcel in) {
			return new Rectangle(in);
		}

		public Rectangle[] newArray(int size) {
			return new Rectangle[size];
		}
	};

}
