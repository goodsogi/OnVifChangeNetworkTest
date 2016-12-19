package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class ReceiverConfiguration implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mReceiverMode;
	public String mChannel;
	public String mMediaUri;
	public StreamSetup mStreamSetup;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public ReceiverConfiguration() {
		mReceiverMode = ReceiverMode.RECEIVER_MODE_ALWAYS; // Default;
	}

	public ReceiverConfiguration(Parcel src) {
		this.mReceiverMode = utils.readInt(src);
		this.mChannel = utils.readString(src);
		this.mMediaUri = utils.readString(src);

		if (utils.readInt(src) != 0) {
			mStreamSetup = new StreamSetup(src);
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
	public void writeToParcel(Parcel dst, int flags) {
		utils.writeInt(dst, mReceiverMode);
		utils.writeString(dst, mChannel);
		utils.writeString(dst, mMediaUri);

		if (mStreamSetup != null) {
			utils.writeInt(dst, 1);
			mStreamSetup.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<ReceiverConfiguration> CREATOR = new Creator<ReceiverConfiguration>() {
		public ReceiverConfiguration createFromParcel(Parcel in) {
			return new ReceiverConfiguration(in);
		}

		public ReceiverConfiguration[] newArray(int size) {
			return new ReceiverConfiguration[size];
		}
	};
}