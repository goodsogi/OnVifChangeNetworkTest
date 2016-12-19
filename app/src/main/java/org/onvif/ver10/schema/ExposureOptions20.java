package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class ExposureOptions20 implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ArrayList<String> mMode;
	public ArrayList<String> mPriority;
	public FloatRange mMinExposureTime;
	public FloatRange mMaxExposureTime;
	public FloatRange mMinGain;
	public FloatRange mMaxGain;
	public FloatRange mMinIris;
	public FloatRange mMaxIris;
	public FloatRange mExposureTime;
	public FloatRange mGain;
	public FloatRange mIris;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public ExposureOptions20() {

	}

	public ExposureOptions20(Parcel src) {
		this.mMode = new ArrayList<String>();
		int size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			this.mMode.add(utils.readString(src));
		}

		this.mPriority = new ArrayList<String>();
		size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			this.mPriority.add(utils.readString(src));
		}

		if (utils.readInt(src) != 0) {
			this.mMinExposureTime = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mMaxExposureTime = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mMinGain = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mMaxGain = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mMinIris = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mMaxIris = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mExposureTime = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mGain = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mIris = new FloatRange(src);
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
		if (mMode != null) {
			int size = mMode.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				utils.writeString(dst, mMode.get(i));
			}
		} else {
			utils.writeInt(dst, 0);
		}
		
		if (mPriority != null) {
			int size = mPriority.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				utils.writeString(dst, mPriority.get(i));
			}
		} else {
			utils.writeInt(dst, 0);
		}
		
		if (mMinExposureTime != null) {
			utils.writeInt(dst, 1);
			mMinExposureTime.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mMaxExposureTime != null) {
			utils.writeInt(dst, 1);
			mMaxExposureTime.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mMinGain != null) {
			utils.writeInt(dst, 1);
			mMinGain.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mMaxGain != null) {
			utils.writeInt(dst, 1);
			mMaxGain.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mMinIris != null) {
			utils.writeInt(dst, 1);
			mMinIris.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mMaxIris != null) {
			utils.writeInt(dst, 1);
			mMaxIris.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mExposureTime != null) {
			utils.writeInt(dst, 1);
			mExposureTime.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mGain != null) {
			utils.writeInt(dst, 1);
			mGain.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mIris != null) {
			utils.writeInt(dst, 1);
			mIris.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<ExposureOptions20> CREATOR = new Creator<ExposureOptions20>() {
		public ExposureOptions20 createFromParcel(Parcel in) {
			return new ExposureOptions20(in);
		}

		public ExposureOptions20[] newArray(int size) {
			return new ExposureOptions20[size];
		}
	};

}
