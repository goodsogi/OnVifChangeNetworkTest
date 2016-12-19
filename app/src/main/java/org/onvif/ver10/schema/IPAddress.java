package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class IPAddress implements Parcelable {
	private final static String TAG = IPAddress.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mType; // enum { 'IPv4', 'IPv6' }
	public String mIPv4Address;
	public String mIPv6Address;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public IPAddress() {

	}

	public IPAddress(Parcel src) {
		this.mType = utils.readString(src);
		this.mIPv4Address = utils.readString(src);
		this.mIPv6Address = utils.readString(src);
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
		utils.writeString(dst, mType);
		utils.writeString(dst, mIPv4Address);
		utils.writeString(dst, mIPv6Address);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<IPAddress> CREATOR = new Creator<IPAddress>() {
		public IPAddress createFromParcel(Parcel in) {
			return new IPAddress(in);
		}

		public IPAddress[] newArray(int size) {
			return new IPAddress[size];
		}
	};

}
