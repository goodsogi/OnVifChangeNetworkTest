package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class PTZSpaces implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public Space2DDescription mAbsolutePanTiltPositionSpace;
	public Space1DDescription mAbsoluteZoomPositionSpace;
	public Space2DDescription mRelativePanTiltTranslationSpace;
	public Space1DDescription mRelativeZoomTranslationSpace;
	public Space2DDescription mContinuousPanTiltVelocitySpace;
	public Space1DDescription mContinuousZoomVelocitySpace;
	public Space1DDescription mPanTiltSpeedSpace;
	public Space1DDescription mZoomSpeedSpace;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public PTZSpaces() {

	}

	public PTZSpaces(Parcel src) {
		if (utils.readInt(src) != 0) {
			mAbsolutePanTiltPositionSpace = new Space2DDescription(src);
		}
		if (utils.readInt(src) != 0) {
			mAbsoluteZoomPositionSpace = new Space1DDescription(src);
		}
		if (utils.readInt(src) != 0) {
			mRelativePanTiltTranslationSpace = new Space2DDescription(src);
		}
		if (utils.readInt(src) != 0) {
			mRelativeZoomTranslationSpace = new Space1DDescription(src);
		}
		if (utils.readInt(src) != 0) {
			mContinuousPanTiltVelocitySpace = new Space2DDescription(src);
		}
		if (utils.readInt(src) != 0) {
			mContinuousZoomVelocitySpace = new Space1DDescription(src);
		}
		if (utils.readInt(src) != 0) {
			mPanTiltSpeedSpace = new Space1DDescription(src);
		}
		if (utils.readInt(src) != 0) {
			mZoomSpeedSpace = new Space1DDescription(src);
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
		if (mAbsolutePanTiltPositionSpace != null) {
			utils.writeInt(dst, 1);
			mAbsolutePanTiltPositionSpace.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mAbsoluteZoomPositionSpace != null) {
			utils.writeInt(dst, 1);
			mAbsoluteZoomPositionSpace.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mRelativePanTiltTranslationSpace != null) {
			utils.writeInt(dst, 1);
			mRelativePanTiltTranslationSpace.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mRelativeZoomTranslationSpace != null) {
			utils.writeInt(dst, 1);
			mRelativeZoomTranslationSpace.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mContinuousPanTiltVelocitySpace != null) {
			utils.writeInt(dst, 1);
			mContinuousPanTiltVelocitySpace.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mContinuousZoomVelocitySpace != null) {
			utils.writeInt(dst, 1);
			mContinuousZoomVelocitySpace.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mPanTiltSpeedSpace != null) {
			utils.writeInt(dst, 1);
			mPanTiltSpeedSpace.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mZoomSpeedSpace != null) {
			utils.writeInt(dst, 1);
			mZoomSpeedSpace.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<PTZSpaces> CREATOR = new Creator<PTZSpaces>() {
		public PTZSpaces createFromParcel(Parcel in) {
			return new PTZSpaces(in);
		}

		public PTZSpaces[] newArray(int size) {
			return new PTZSpaces[size];
		}
	};
}
