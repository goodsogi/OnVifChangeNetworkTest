package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class Recording implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mRecordingToken;
	public RecordingConfiguration mRecordingConfiguration;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Recording() {

	}

	public Recording(String token, RecordingConfiguration config) {
		this.mRecordingToken = token;
		this.mRecordingConfiguration = config;
	}

	public Recording(Parcel src) {
		this.mRecordingToken = utils.readString(src);

		if (utils.readInt(src) != 0) {
			mRecordingConfiguration = new RecordingConfiguration(src);
		}
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
	public void writeToParcel(Parcel dst, int flags) {
		utils.writeString(dst, mRecordingToken);
		if (mRecordingConfiguration != null) {
			utils.writeInt(dst, 1);
			mRecordingConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Recording> CREATOR = new Creator<Recording>() {
		public Recording createFromParcel(Parcel in) {
			return new Recording(in);
		}

		public Recording[] newArray(int size) {
			return new Recording[size];
		}
	};
}