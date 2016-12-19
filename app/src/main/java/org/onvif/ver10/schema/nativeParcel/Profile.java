package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
	private final static String TAG = Profile.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	// Profile
	public String mToken;
	public int mFixed;
	public String mName;

	// VideoSourceConfiguration
	public VideoSourceConfiguration mVideoSourceConfiguration;

	// AudioSourceConfiguration
	public AudioSourceConfiguration mAudioSourceConfiguration;

	// VideoEncoderConfiguration
	public VideoEncoderConfiguration mVideoEncoderConfiguration;

	// AudioEncoderConfiguration
	public AudioEncoderConfiguration mAudioEncoderConfiguration;

	// VideoAnalyticsConfiguration
	public VideoAnalyticsConfiguration mVideoAnalyticsConfiguration;

	// PTZConfiguration
	public PTZConfiguration mPTZConfiguration;
	
	// MetadataConfiguration
	public MetadataConfiguration mMetadataConfiguration;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Profile() {
	}

	public Profile(String token, int fixed) {
		this.mToken = token;
		this.mFixed = fixed;
	}

	public Profile(Parcel src) {
		this.mToken = utils.readString(src);
		this.mFixed = utils.readInt(src);
		this.mName = utils.readString(src);

		if (utils.readInt(src) != 0) {
			mVideoSourceConfiguration = new VideoSourceConfiguration(src);
		}

		if (utils.readInt(src) != 0) {
			mAudioSourceConfiguration = new AudioSourceConfiguration(src);
		}

		if (utils.readInt(src) != 0) {
			mVideoEncoderConfiguration = new VideoEncoderConfiguration(src);
		}

		if (utils.readInt(src) != 0) {
			mAudioEncoderConfiguration = new AudioEncoderConfiguration(src);
		}

		if (utils.readInt(src) != 0) {
			mVideoAnalyticsConfiguration = new VideoAnalyticsConfiguration(src);
		}

		if (utils.readInt(src) != 0) {
			mPTZConfiguration = new PTZConfiguration(src);
		}
		
		if (utils.readInt(src) != 0) {
			mMetadataConfiguration = new MetadataConfiguration(src);
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
		utils.writeString(dst, mToken);
		utils.writeInt(dst, mFixed);
		utils.writeString(dst, mName);

		if (mVideoSourceConfiguration != null) {
			utils.writeInt(dst, 1);
			mVideoSourceConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mAudioSourceConfiguration != null) {
			utils.writeInt(dst, 1);
			mAudioSourceConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mVideoEncoderConfiguration != null) {
			utils.writeInt(dst, 1);
			mVideoEncoderConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mAudioEncoderConfiguration != null) {
			utils.writeInt(dst, 1);
			mAudioEncoderConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}
		if (mVideoAnalyticsConfiguration != null) {
			utils.writeInt(dst, 1);
			mVideoAnalyticsConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mPTZConfiguration != null) {
			utils.writeInt(dst, 1);
			mPTZConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}
		
		if (mMetadataConfiguration != null) {
			utils.writeInt(dst, 1);
			mMetadataConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Profile> CREATOR = new Creator<Profile>() {
		public Profile createFromParcel(Parcel in) {
			return new Profile(in);
		}

		public Profile[] newArray(int size) {
			return new Profile[size];
		}
	};
}
