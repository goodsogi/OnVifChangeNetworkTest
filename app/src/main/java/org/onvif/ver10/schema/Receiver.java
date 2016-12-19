package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class Receiver implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mReceiverToken;
	public ReceiverConfiguration mReceiverConfiguration;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Receiver() {

	}

	public Receiver(String token, ReceiverConfiguration config) {
		this.mReceiverToken = token;
		this.mReceiverConfiguration = config;
	}

	public Receiver(Parcel src) {
		mReceiverToken = utils.readString(src);
		if (utils.readInt(src) != 0) {
			mReceiverConfiguration = new ReceiverConfiguration(src);
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
		utils.writeString(dst, mReceiverToken);

		if (mReceiverConfiguration != null) {
			utils.writeInt(dst, 1);
			mReceiverConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Receiver> CREATOR = new Creator<Receiver>() {
		public Receiver createFromParcel(Parcel in) {
			return new Receiver(in);
		}

		public Receiver[] newArray(int size) {
			return new Receiver[size];
		}
	};
}