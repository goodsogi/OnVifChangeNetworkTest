package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class ReplayUri implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public StreamSetup mStreamSetup;
	public String mRecordingToken;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public ReplayUri() {

	}

	public ReplayUri(Parcel src) {
		if (utils.readInt(src) != 0) {
			mStreamSetup = new StreamSetup(src);
		}
		this.mRecordingToken = utils.readString(src);
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
		if (mStreamSetup != null) {
			utils.writeInt(dst, 1);
			mStreamSetup.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		utils.writeString(dst, mRecordingToken);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<ReplayUri> CREATOR = new Creator<ReplayUri>() {
		public ReplayUri createFromParcel(Parcel in) {
			return new ReplayUri(in);
		}

		public ReplayUri[] newArray(int size) {
			return new ReplayUri[size];
		}
	};
}
