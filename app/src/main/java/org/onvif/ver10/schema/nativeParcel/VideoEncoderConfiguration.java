package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.H264Configuration;
import org.onvif.ver10.schema.Mpeg4Configuration;
import org.onvif.ver10.schema.MulticastConfiguration;
import org.onvif.ver10.schema.VideoRateControl;
import org.onvif.ver10.schema.VideoResolution;

public class VideoEncoderConfiguration implements Parcelable {
	private final static String TAG = VideoEncoderConfiguration.class.getSimpleName();

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mName;
	public int mUseCount;
	public String mToken;
	public String mEncoding; // enum { 'JPEG', 'MPEG4', 'H264' }
	public VideoResolution mResolution;
	public float mQuality;
	public VideoRateControl mRateControl;
	public Mpeg4Configuration mMPEG4;
	public H264Configuration mH264;
	public MulticastConfiguration mMulticast;
	public String mSessionTimeout;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public VideoEncoderConfiguration() {
		this.mResolution = new VideoResolution();
		this.mRateControl = new VideoRateControl();
		this.mMulticast = new MulticastConfiguration();
	}

	public VideoEncoderConfiguration(Parcel src) {
		this.mName = utils.readString(src);
		this.mUseCount = utils.readInt(src);
		this.mToken = utils.readString(src);
		this.mEncoding = utils.readString(src);

		if (utils.readInt(src) != 0) {
			this.mResolution = new VideoResolution(src);
		}

		this.mQuality = utils.readFloat(src);

		if (utils.readInt(src) != 0) {
			this.mRateControl = new VideoRateControl(src);
		}
		if (utils.readInt(src) != 0) {
			this.mMPEG4 = new Mpeg4Configuration(src);
		}
		if (utils.readInt(src) != 0) {
			this.mH264 = new H264Configuration(src);
		}

		if (utils.readInt(src) != 0) {
			this.mMulticast = new MulticastConfiguration(src);
		}

		this.mSessionTimeout = utils.readString(src);
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
		utils.writeString(dst, mEncoding);

		if (mResolution != null) {
			utils.writeInt(dst, 1);
			mResolution.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		utils.writeFloat(dst, mQuality);

		if (mRateControl != null) {
			utils.writeInt(dst, 1);
			mRateControl.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mMPEG4 != null) {
			utils.writeInt(dst, 1);
			mMPEG4.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mH264 != null) {
			utils.writeInt(dst, 1);
			mH264.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mMulticast != null) {
			utils.writeInt(dst, 1);
			mMulticast.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		utils.writeString(dst, mSessionTimeout);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<VideoEncoderConfiguration> CREATOR = new Creator<VideoEncoderConfiguration>() {
		public VideoEncoderConfiguration createFromParcel(Parcel in) {
			return new VideoEncoderConfiguration(in);
		}

		public VideoEncoderConfiguration[] newArray(int size) {
			return new VideoEncoderConfiguration[size];
		}
	};

}