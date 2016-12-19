package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class H264Configuration implements Parcelable {
	private final static String TAG = H264Configuration.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mGovLength;
	public String mH264Profile; // enum { 'Baseline', 'Main', 'Extended', 'High' }

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public H264Configuration() {

	}

	public H264Configuration(Parcel src) {
		this.mGovLength = utils.readInt(src);
		this.mH264Profile = utils.readString(src);
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
		utils.writeInt(dst, mGovLength);
		utils.writeString(dst, mH264Profile);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<H264Configuration> CREATOR = new Creator<H264Configuration>() {
		public H264Configuration createFromParcel(Parcel in) {
			return new H264Configuration(in);
		}

		public H264Configuration[] newArray(int size) {
			return new H264Configuration[size];
		}
	};

}
