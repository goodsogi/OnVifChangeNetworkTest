package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class VideoResolution implements Parcelable {
	private final static String TAG = VideoResolution.class.getSimpleName();

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mWidth;
	public int mHeight;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public VideoResolution() {
	}

	public VideoResolution(Parcel src) {
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
		utils.writeInt(dst, mWidth);
		utils.writeInt(dst, mHeight);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<VideoResolution> CREATOR = new Creator<VideoResolution>() {
		public VideoResolution createFromParcel(Parcel in) {
			return new VideoResolution(in);
		}

		public VideoResolution[] newArray(int size) {
			return new VideoResolution[size];
		}
	};

}
