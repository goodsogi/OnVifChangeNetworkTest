package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.PanTiltLimits;
import org.onvif.ver10.schema.ZoomLimits;

public class PTZConfiguration implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mName;
	public int mUseCount;
	public String mToken;
	public String mNodeToken;
	public String mDefaultAbsolutePantTiltPositionSpace;
	public String mDefaultAbsoluteZoomPositionSpace;
	public String mDefaultRelativePanTiltTranslationSpace;
	public String mDefaultRelativeZoomTranslationSpace;
	public String mDefaultContinuousPanTiltVelocitySpace;
	public String mDefaultContinuousZoomVelocitySpace;
	public PTZSpeed mDefaultPTZSpeed;
	public String mDefaultPTZTimeout;
	public PanTiltLimits mPanTiltLimits;
	public ZoomLimits mZoomLimits;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public PTZConfiguration() {

	}

	public PTZConfiguration(Parcel src) {
		mName = utils.readString(src);
		mUseCount = utils.readInt(src);
		mToken = utils.readString(src);
		mNodeToken = utils.readString(src);
		mDefaultAbsolutePantTiltPositionSpace = utils.readString(src);
		mDefaultAbsoluteZoomPositionSpace = utils.readString(src);
		mDefaultRelativePanTiltTranslationSpace = utils.readString(src);
		mDefaultRelativeZoomTranslationSpace = utils.readString(src);
		mDefaultContinuousPanTiltVelocitySpace = utils.readString(src);
		mDefaultContinuousZoomVelocitySpace = utils.readString(src);

		if (src.readInt() != 0) {
			mDefaultPTZSpeed = new PTZSpeed(src);
		}

		mDefaultPTZTimeout = utils.readString(src);

		if (src.readInt() != 0) {
			mPanTiltLimits = new PanTiltLimits(src);
		}
		if (src.readInt() != 0) {
			mZoomLimits = new ZoomLimits(src);
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
		utils.writeString(dst, mName);
		utils.writeInt(dst, mUseCount);
		utils.writeString(dst, mToken);
		utils.writeString(dst, mNodeToken);
		utils.writeString(dst, mDefaultAbsolutePantTiltPositionSpace);
		utils.writeString(dst, mDefaultAbsoluteZoomPositionSpace);
		utils.writeString(dst, mDefaultRelativePanTiltTranslationSpace);
		utils.writeString(dst, mDefaultRelativeZoomTranslationSpace);
		utils.writeString(dst, mDefaultContinuousPanTiltVelocitySpace);
		utils.writeString(dst, mDefaultContinuousZoomVelocitySpace);

		if (mDefaultPTZSpeed != null) {
			utils.writeInt(dst, 1);
			mDefaultPTZSpeed.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		utils.writeString(dst, mDefaultPTZTimeout);

		if (mPanTiltLimits != null) {
			utils.writeInt(dst, 1);
			mPanTiltLimits.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mZoomLimits != null) {
			utils.writeInt(dst, 1);
			mZoomLimits.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<PTZConfiguration> CREATOR = new Creator<PTZConfiguration>() {
		public PTZConfiguration createFromParcel(Parcel in) {
			return new PTZConfiguration(in);
		}

		public PTZConfiguration[] newArray(int size) {
			return new PTZConfiguration[size];
		}
	};

}
