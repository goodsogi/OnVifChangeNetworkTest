package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.MulticastConfiguration;

public class AudioEncoderConfiguration implements Parcelable {
	private final static String TAG = AudioEncoderConfiguration.class.getSimpleName();

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mName;
	public int mUseCount;
	public String mToken;
	public String mEncoding; // enum { 'G711', 'G726', 'AAC' }
	public int mBitrate;
	public int mSampleRate;
	public MulticastConfiguration mMulticast;
	public String mSessionTimeout;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public AudioEncoderConfiguration() {
		this.mMulticast = new MulticastConfiguration();
	}

	public AudioEncoderConfiguration(Parcel src) {
		this.mName = utils.readString(src);
		this.mUseCount = utils.readInt(src);
		this.mToken = utils.readString(src);
		this.mEncoding = utils.readString(src);
		this.mBitrate = utils.readInt(src);
		this.mSampleRate = utils.readInt(src);

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
		utils.writeInt(dst, mBitrate);
		utils.writeInt(dst, mSampleRate);

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
	public static final Creator<AudioEncoderConfiguration> CREATOR = new Creator<AudioEncoderConfiguration>() {
		public AudioEncoderConfiguration createFromParcel(Parcel in) {
			return new AudioEncoderConfiguration(in);
		}

		public AudioEncoderConfiguration[] newArray(int size) {
			return new AudioEncoderConfiguration[size];
		}
	};

}