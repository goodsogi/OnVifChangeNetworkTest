package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class ReplayConfiguration implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mSessionTimeout;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public ReplayConfiguration() {

	}

	public ReplayConfiguration(Parcel src) {
		this.mSessionTimeout = utils.readInt(src);
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
	public void writeToParcel(Parcel dst, int flag) {
		utils.writeInt(dst, mSessionTimeout);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<ReplayConfiguration> CREATOR = new Creator<ReplayConfiguration>() {
		public ReplayConfiguration createFromParcel(Parcel in) {
			return new ReplayConfiguration(in);
		}

		public ReplayConfiguration[] newArray(int size) {
			return new ReplayConfiguration[size];
		}
	};
}