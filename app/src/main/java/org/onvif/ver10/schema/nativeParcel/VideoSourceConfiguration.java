package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.IntRectangle;

public class VideoSourceConfiguration implements Parcelable {
	private final static String TAG = VideoSourceConfiguration.class.getSimpleName();

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mName;
	public int mUseCount;
	public String mToken;
	public String mSourceToken;
	public IntRectangle mBounds;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public VideoSourceConfiguration() {
		this.mBounds = new IntRectangle();
	}

	public VideoSourceConfiguration(Parcel src) {
		this.mName = utils.readString(src);
		this.mUseCount = utils.readInt(src);
		this.mToken = utils.readString(src);
		this.mSourceToken = utils.readString(src);

		if (utils.readInt(src)  != 0) {
			this.mBounds = new IntRectangle(src);
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
	public void writeToParcel(Parcel dst, int flags) {
		utils.writeString(dst, mName);
		utils.writeInt(dst, mUseCount);
		utils.writeString(dst, mToken);
		utils.writeString(dst, mSourceToken);
		if (mBounds != null) {
			utils.writeInt(dst, 1);
			mBounds.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<VideoSourceConfiguration> CREATOR = new Creator<VideoSourceConfiguration>() {
		public VideoSourceConfiguration createFromParcel(Parcel in) {
			return new VideoSourceConfiguration(in);
		}

		public VideoSourceConfiguration[] newArray(int size) {
			return new VideoSourceConfiguration[size];
		}
	};

}