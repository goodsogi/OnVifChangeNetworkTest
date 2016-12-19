package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

public class MediaUri implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mUri;
	public boolean mInvalidAfterConnect;
	public boolean mInvalidAfterReboot;
	public String mTimeout;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public MediaUri() {

	}

	public MediaUri(Parcel reply) {
		mUri = utils.readString(reply);
		mInvalidAfterConnect = utils.readInt(reply) == 1;
		mInvalidAfterReboot = utils.readInt(reply) == 1;
		mTimeout = utils.readString(reply);
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
	public void writeToParcel(Parcel data, int flag) {
		utils.writeString(data, mUri);
		utils.writeInt(data, mInvalidAfterConnect ? 1 : 0);
		utils.writeInt(data, mInvalidAfterReboot ? 1 : 0);
		utils.writeString(data, mTimeout);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<MediaUri> CREATOR = new Creator<MediaUri>() {
		public MediaUri createFromParcel(Parcel in) {
			return new MediaUri(in);
		}

		public MediaUri[] newArray(int size) {
			return new MediaUri[size];
		}
	};

}
