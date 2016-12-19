package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

public class OnvifClientInfo implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/

	public String mIPAddress;
	public int mPort;
	public String mID;
	public String mPassword;
	public String mDeviceXAddr;
	public int isChangeUserInfo;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public OnvifClientInfo() {

	}

	public OnvifClientInfo(Parcel reply) {
		mIPAddress = utils.readString(reply);
		mPort = utils.readInt(reply);
		mID = utils.readString(reply);
		mPassword = utils.readString(reply);
		mDeviceXAddr = utils.readString(reply);
		isChangeUserInfo = utils.readInt(reply);
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
	public void writeToParcel(Parcel dst, int flag) {
		utils.writeString(dst, mIPAddress);
		utils.writeInt(dst, mPort);
		utils.writeString(dst, mID);
		utils.writeString(dst, mPassword);
		utils.writeString(dst, mDeviceXAddr);
		utils.writeInt(dst, isChangeUserInfo);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<OnvifClientInfo> CREATOR = new Creator<OnvifClientInfo>() {
		public OnvifClientInfo createFromParcel(Parcel in) {
			return new OnvifClientInfo(in);
		}

		public OnvifClientInfo[] newArray(int size) {
			return new OnvifClientInfo[size];
		}
	};
}