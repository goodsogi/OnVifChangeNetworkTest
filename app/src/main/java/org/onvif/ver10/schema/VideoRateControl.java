package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class VideoRateControl implements Parcelable {
	private final static String TAG = VideoRateControl.class.getSimpleName();

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mFrameRateLimit;
	public int mEncodingInterval;
	public int mBitrateLimit;
	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public VideoRateControl() {
	}

	public VideoRateControl(Parcel src) {
		this.mFrameRateLimit = utils.readInt(src);
		this.mEncodingInterval = utils.readInt(src);
		this.mBitrateLimit = utils.readInt(src);
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
		utils.writeInt(dst, mFrameRateLimit);
		utils.writeInt(dst, mEncodingInterval);
		utils.writeInt(dst, mBitrateLimit);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<VideoRateControl> CREATOR = new Creator<VideoRateControl>() {
		public VideoRateControl createFromParcel(Parcel in) {
			return new VideoRateControl(in);
		}

		public VideoRateControl[] newArray(int size) {
			return new VideoRateControl[size];
		}
	};

}
