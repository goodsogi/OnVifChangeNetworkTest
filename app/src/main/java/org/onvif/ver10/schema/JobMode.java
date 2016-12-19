package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class JobMode implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mJobToken;
	public int mJobMode;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public JobMode() {
		mJobMode = RecordingJobMode.RECORDING_JOB_MODE_ACTIVE;
	}

	public JobMode(Parcel src) {
		this.mJobToken = utils.readString(src);
		this.mJobMode = utils.readInt(src);
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
		utils.writeString(dst, mJobToken);
		utils.writeInt(dst, mJobMode);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<JobMode> CREATOR = new Creator<JobMode>() {
		public JobMode createFromParcel(Parcel in) {
			return new JobMode(in);
		}

		public JobMode[] newArray(int size) {
			return new JobMode[size];
		}
	};
}