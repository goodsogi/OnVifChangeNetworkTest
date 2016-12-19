package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceInformation implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mManufacturer;
	public String mModel;
	public String mFirmwareVersion;
	public String mSerialNumber;
	public String mHardwareId;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public DeviceInformation() {
	}

	public DeviceInformation(Parcel reply) {
		mManufacturer = utils.readString(reply);
		mModel = utils.readString(reply);
		mFirmwareVersion = utils.readString(reply);
		mSerialNumber = utils.readString(reply);
		mHardwareId = utils.readString(reply);
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
		utils.writeString(dst, mManufacturer);
		utils.writeString(dst, mModel);
		utils.writeString(dst, mFirmwareVersion);
		utils.writeString(dst, mSerialNumber);
		utils.writeString(dst, mHardwareId);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<DeviceInformation> CREATOR = new Creator<DeviceInformation>() {
		public DeviceInformation createFromParcel(Parcel in) {
			return new DeviceInformation(in);
		}

		public DeviceInformation[] newArray(int size) {
			return new DeviceInformation[size];
		}
	};
}