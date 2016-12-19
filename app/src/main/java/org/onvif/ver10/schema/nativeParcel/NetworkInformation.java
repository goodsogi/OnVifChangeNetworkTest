package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

public class NetworkInformation implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	/**
	 * The ethernet interface is configured by dhcp
	 */
	public static final int ETHERNET_CONN_MODE_DHCP = 1;
	/**
	 * The ethernet interface is configured manually
	 */
	public static final int ETHERNET_CONN_MODE_MANUAL = 0;
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mInterfaceName; // eth0
	public int mConnectionMode; // DHCP , MANUAL
	public String mIPAddress;
	public String mNetMaskAddress;
	public int mNetMaskPrefix;
	public String mGateWayAddress;
	public String mDNS1Address;
	public String mDNS2Address;
	public String mMacAddress;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public NetworkInformation() {
		this.mNetMaskPrefix = -1;
	}

	public NetworkInformation(Parcel reply) {
		mInterfaceName = utils.readString(reply);
		mConnectionMode = utils.readInt(reply);
		mIPAddress = utils.readString(reply);
		mNetMaskAddress = utils.readString(reply);
		mNetMaskPrefix = utils.readInt(reply);
		mGateWayAddress = utils.readString(reply);
		mDNS1Address = utils.readString(reply);
		mDNS2Address = utils.readString(reply);
		mMacAddress = utils.readString(reply);
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
		utils.writeString(data, mInterfaceName);
		utils.writeInt(data, mConnectionMode);
		utils.writeString(data, mIPAddress);
		utils.writeString(data, mNetMaskAddress);
		utils.writeInt(data, mNetMaskPrefix);
		utils.writeString(data, mGateWayAddress);
		utils.writeString(data, mDNS1Address);
		utils.writeString(data, mDNS2Address);
		utils.writeString(data, mMacAddress);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<NetworkInformation> CREATOR = new Creator<NetworkInformation>() {
		public NetworkInformation createFromParcel(Parcel in) {
			return new NetworkInformation(in);
		}

		public NetworkInformation[] newArray(int size) {
			return new NetworkInformation[size];
		}
	};
}