package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class Exposure20 implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mMode;
	public String mPriority;
	public Rectangle mWindow;
	public float mMinExposureTime = -1;
	public float mMaxExposureTime = -1;
	public float mMinGain = -1;
	public float mMaxGain = -1;
	public float mMinIris = -1;
	public float mMaxIris = -1;
	public float mExposureTime = -1;
	public float mGain = -1;
	public float mIris = -1;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Exposure20() {

	}

	public Exposure20(Parcel src) {
		this.mMode = utils.readString(src);
		this.mPriority = utils.readString(src);
		if (utils.readInt(src) != 0) {
			this.mWindow = new Rectangle(src);
		}
		this.mMinExposureTime = utils.readFloat(src);
		this.mMaxExposureTime = utils.readFloat(src);
		this.mMinGain = utils.readFloat(src);
		this.mMaxGain = utils.readFloat(src);
		this.mMinIris = utils.readFloat(src);
		this.mMaxIris = utils.readFloat(src);
		this.mExposureTime = utils.readFloat(src);
		this.mGain = utils.readFloat(src);
		this.mIris = utils.readFloat(src);
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
		utils.writeString(dst, mPriority);
		if (mWindow != null) {
			utils.writeInt(dst, 1);
			mWindow.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		utils.writeFloat(dst, mMinExposureTime);
		utils.writeFloat(dst, mMaxExposureTime);
		utils.writeFloat(dst, mMinGain);
		utils.writeFloat(dst, mMaxGain);
		utils.writeFloat(dst, mMinIris);
		utils.writeFloat(dst, mMaxIris);
		utils.writeFloat(dst, mExposureTime);
		utils.writeFloat(dst, mGain);
		utils.writeFloat(dst, mIris);

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Exposure20> CREATOR = new Creator<Exposure20>() {
		public Exposure20 createFromParcel(Parcel in) {
			return new Exposure20(in);
		}

		public Exposure20[] newArray(int size) {
			return new Exposure20[size];
		}
	};

}
