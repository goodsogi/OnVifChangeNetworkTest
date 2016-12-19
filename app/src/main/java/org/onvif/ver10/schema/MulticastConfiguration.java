package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class MulticastConfiguration implements Parcelable {
	private final static String TAG = MulticastConfiguration.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public IPAddress mAddress;
	public int mPort;
	public int mTTL;
	public int mAutoStart;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public MulticastConfiguration() {
	}

	public MulticastConfiguration(Parcel src) {
		if (utils.readInt(src) == 1) {
			this.mAddress = new IPAddress(src);
		}
		this.mPort = utils.readInt(src);
		this.mTTL = utils.readInt(src);
		this.mAutoStart = utils.readInt(src);
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
		if (mAddress != null) {
			utils.writeInt(dst, 1);
			mAddress.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		utils.writeInt(dst, mPort);
		utils.writeInt(dst, mTTL);
		utils.writeInt(dst, mAutoStart);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<MulticastConfiguration> CREATOR = new Creator<MulticastConfiguration>() {
		public MulticastConfiguration createFromParcel(Parcel in) {
			return new MulticastConfiguration(in);
		}

		public MulticastConfiguration[] newArray(int size) {
			return new MulticastConfiguration[size];
		}
	};

}
