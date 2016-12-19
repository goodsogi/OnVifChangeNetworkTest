package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

public class Capabilities implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mAnalyticsXAddr;
	public String mDeviceXAddr;
	public String mEventsXAddr;
	public String mImagingXAddr;
	public String mMediaXAddr;
	public String mPTZXAddr;
	public String mExDeviceIOXAddr;
	public String mExDisplayXAddr;
	public String mExRecordingXAddr;
	public String mExSearchXAddr;
	public String mExReplayXAddr;
	public String mExReceiverXAddr;
	public String mExAnalyticsDeviceXAddr;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Capabilities() {
	}

	public Capabilities(Parcel reply) {
		mAnalyticsXAddr = utils.readString(reply);
		mDeviceXAddr = utils.readString(reply);
		mEventsXAddr = utils.readString(reply);
		mImagingXAddr = utils.readString(reply);
		mMediaXAddr = utils.readString(reply);
		mPTZXAddr = utils.readString(reply);
		mExDeviceIOXAddr = utils.readString(reply);
		mExDisplayXAddr = utils.readString(reply);
		mExRecordingXAddr = utils.readString(reply);
		mExSearchXAddr = utils.readString(reply);
		mExReplayXAddr = utils.readString(reply);
		mExReceiverXAddr = utils.readString(reply);
		mExAnalyticsDeviceXAddr = utils.readString(reply);	
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
		utils.writeString(dst, mAnalyticsXAddr);
		utils.writeString(dst, mDeviceXAddr);
		utils.writeString(dst, mEventsXAddr);
		utils.writeString(dst, mImagingXAddr);
		utils.writeString(dst, mMediaXAddr);
		utils.writeString(dst, mPTZXAddr);
		utils.writeString(dst, mExDeviceIOXAddr);
		utils.writeString(dst, mExDisplayXAddr);
		utils.writeString(dst, mExRecordingXAddr);
		utils.writeString(dst, mExSearchXAddr);
		utils.writeString(dst, mExReplayXAddr);
		utils.writeString(dst, mExReceiverXAddr);
		utils.writeString(dst, mExAnalyticsDeviceXAddr);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Capabilities> CREATOR = new Creator<Capabilities>() {
		public Capabilities createFromParcel(Parcel in) {
			return new Capabilities(in);
		}

		public Capabilities[] newArray(int size) {
			return new Capabilities[size];
		}
	};
}