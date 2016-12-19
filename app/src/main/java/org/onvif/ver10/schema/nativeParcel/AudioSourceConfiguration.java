package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

public class AudioSourceConfiguration implements Parcelable {
	private final static String TAG = AudioSourceConfiguration.class.getSimpleName();

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mName;
	public int mUseCount;
	public String mToken;
	public String mSourceToken;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public AudioSourceConfiguration() {
	}

	public AudioSourceConfiguration(Parcel src) {
		this.mName = utils.readString(src);
		this.mUseCount = utils.readInt(src);
		this.mToken = utils.readString(src);
		this.mSourceToken = utils.readString(src);
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
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<AudioSourceConfiguration> CREATOR = new Creator<AudioSourceConfiguration>() {
		public AudioSourceConfiguration createFromParcel(Parcel in) {
			return new AudioSourceConfiguration(in);
		}

		public AudioSourceConfiguration[] newArray(int size) {
			return new AudioSourceConfiguration[size];
		}
	};

}