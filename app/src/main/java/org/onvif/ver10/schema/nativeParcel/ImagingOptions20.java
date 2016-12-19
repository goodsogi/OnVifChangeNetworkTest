package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.BacklightCompensationOptions20;
import org.onvif.ver10.schema.ExposureOptions20;
import org.onvif.ver10.schema.FloatRange;
import org.onvif.ver10.schema.FocusOptions20;
import org.onvif.ver10.schema.WhiteBalanceOptions20;
import org.onvif.ver10.schema.WideDynamicRangeOptions20;

public class ImagingOptions20 implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public BacklightCompensationOptions20 mBacklightCompensation;
	public FloatRange mBrightness;
	public FloatRange mColorSaturation;
	public FloatRange mContrast;
	public ExposureOptions20 mExposure;
	public FocusOptions20 mFocus;
	public String mIrCutFilterModes;
	public FloatRange mSharpness;
	public WideDynamicRangeOptions20 mWideDynamicRange;
	public WhiteBalanceOptions20 mWhiteBalance;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public ImagingOptions20() {

	}

	public ImagingOptions20(Parcel src) {
		if (src.readInt() != 0) {
			mBacklightCompensation = new BacklightCompensationOptions20(src);
		}
		if (src.readInt() != 0) {
			mBrightness = new FloatRange(src);
		}
		if (src.readInt() != 0) {
			mColorSaturation = new FloatRange(src);
		}
		if (src.readInt() != 0) {
			mContrast = new FloatRange(src);
		}
		if (src.readInt() != 0) {
			mExposure = new ExposureOptions20(src);
		}
		if (src.readInt() != 0) {
			mFocus = new FocusOptions20(src);
		}

		mIrCutFilterModes = utils.readString(src);

		if (src.readInt() != 0) {
			mSharpness = new FloatRange(src);
		}

		if (src.readInt() != 0) {
			mWideDynamicRange = new WideDynamicRangeOptions20(src);
		}
		if (src.readInt() != 0) {
			mWhiteBalance = new WhiteBalanceOptions20(src);
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
		if (mBrightness != null) {
			utils.writeInt(dst, 1);
			mBrightness.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mColorSaturation != null) {
			utils.writeInt(dst, 1);
			mColorSaturation.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mContrast != null) {
			utils.writeInt(dst, 1);
			mContrast.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
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

		utils.writeString(dst, mIrCutFilterModes);

		if (mSharpness != null) {
			utils.writeInt(dst, 1);
			mSharpness.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
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
	public static final Creator<ImagingOptions20> CREATOR = new Creator<ImagingOptions20>() {
		public ImagingOptions20 createFromParcel(Parcel in) {
			return new ImagingOptions20(in);
		}

		public ImagingOptions20[] newArray(int size) {
			return new ImagingOptions20[size];
		}
	};

}
