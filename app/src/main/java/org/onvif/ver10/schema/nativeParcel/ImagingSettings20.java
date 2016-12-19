package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.BacklightCompensation20;
import org.onvif.ver10.schema.Exposure20;
import org.onvif.ver10.schema.FocusConfiguration20;
import org.onvif.ver10.schema.WhiteBalance20;
import org.onvif.ver10.schema.WideDynamicRange20;

public class ImagingSettings20 implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public BacklightCompensation20 mBacklightCompensation;
	public float mBrightness = -1;
	public float mColorSaturation = -1;
	public float mContrast = -1;
	public Exposure20 mExposure;
	public FocusConfiguration20 mFocus;
	public String mIrCutFilter;
	public float mSharpness = -1;
	public WideDynamicRange20 mWideDynamicRange;
	public WhiteBalance20 mWhiteBalance;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public ImagingSettings20() {
	}

	public ImagingSettings20(Parcel src) {
		if (src.readInt() != 0) {
			mBacklightCompensation = new BacklightCompensation20(src);
		}
		this.mBrightness = utils.readFloat(src);
		this.mColorSaturation = utils.readFloat(src);
		this.mContrast = utils.readFloat(src);
		if (src.readInt() != 0) {
			mExposure = new Exposure20(src);
		}
		if (src.readInt() != 0) {
			mFocus = new FocusConfiguration20(src);
		}
		this.mIrCutFilter = utils.readString(src);
		this.mSharpness = utils.readFloat(src);

		if (src.readInt() != 0) {
			mWideDynamicRange = new WideDynamicRange20(src);
		}
		if (src.readInt() != 0) {
			mWhiteBalance = new WhiteBalance20(src);
		}
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
	public void writeToParcel(Parcel dst, int flag) {

		if (mBacklightCompensation != null) {
			utils.writeInt(dst, 1);
			mBacklightCompensation.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		utils.writeFloat(dst, mBrightness);
		utils.writeFloat(dst, mColorSaturation);
		utils.writeFloat(dst, mContrast);

		if (mExposure != null) {
			utils.writeInt(dst, 1);
			mExposure.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mFocus != null) {
			utils.writeInt(dst, 1);
			mFocus.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		utils.writeString(dst, mIrCutFilter);
		utils.writeFloat(dst, mSharpness);

		if (mWideDynamicRange != null) {
			utils.writeInt(dst, 1);
			mWideDynamicRange.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mWhiteBalance != null) {
			utils.writeInt(dst, 1);
			mWhiteBalance.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<ImagingSettings20> CREATOR = new Creator<ImagingSettings20>() {
		public ImagingSettings20 createFromParcel(Parcel in) {
			return new ImagingSettings20(in);
		}

		public ImagingSettings20[] newArray(int size) {
			return new ImagingSettings20[size];
		}
	};

}
