package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	public final static int COVERT_CHANNEL_LIVE_1 = 0x0001;
	public final static int COVERT_CHANNEL_LIVE_2 = 0x0010;
	public final static int COVERT_CHANNEL_LIVE_3 = 0x0100;
	public final static int COVERT_CHANNEL_LIVE_4 = 0x1000;

	// ===========================================================================
	// example 1: 0x11111111 (모든 채널이 Covert 상태)
	// example 2: 0x00001111 (Live 채널 전부 Covert 상태)
	// ===========================================================================
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mUsername;
	public String mPassword;
	public String mUserLevel; // { 'Administrator', 'Operator', 'User', 'Anonymous', 'Extended' }
	public int mCovert;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public User() {

	}

	public User(Parcel reply) {
		mUsername = utils.readString(reply);
		mPassword = utils.readString(reply);
		mUserLevel = utils.readString(reply);
		mCovert = utils.readInt(reply);
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
		utils.writeString(dst, mUsername);
		utils.writeString(dst, mPassword);
		utils.writeString(dst, mUserLevel);
		utils.writeInt(dst, mCovert);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<User> CREATOR = new Creator<User>() {
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		public User[] newArray(int size) {
			return new User[size];
		}
	};
}