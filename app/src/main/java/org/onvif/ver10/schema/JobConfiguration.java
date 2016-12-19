package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class JobConfiguration implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mRecordingToken;
	public int mJobMode;
	public int mPriority;
	public JobSource mJobSource; // [optional], [unbounded]
	public JobConfigurationExtension mJobConfigurationExtension;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public JobConfiguration() {
		// this.mJobMode = RecordingJobMode.RECORDING_JOB_MODE_ACTIVE; // 1 Default?
	}

	public JobConfiguration(Parcel src) {
		this.mRecordingToken = utils.readString(src);
		this.mJobMode = utils.readInt(src);
		this.mPriority = utils.readInt(src);
		if (utils.readInt(src) != 0) {
			this.mJobSource = new JobSource(src);
		}
		if (utils.readInt(src) != 0) {
			this.mJobConfigurationExtension = new JobConfigurationExtension(src);
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
	public void writeToParcel(Parcel dst, int flag) {
		utils.writeString(dst, mRecordingToken);
		utils.writeInt(dst, mJobMode);
		utils.writeInt(dst, mPriority);
		if (mJobSource != null) {
			utils.writeInt(dst, 1);
			mJobSource.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		
		if (mJobConfigurationExtension != null) {
			utils.writeInt(dst, 1);
			mJobConfigurationExtension.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<JobConfiguration> CREATOR = new Creator<JobConfiguration>() {
		public JobConfiguration createFromParcel(Parcel in) {
			return new JobConfiguration(in);
		}

		public JobConfiguration[] newArray(int size) {
			return new JobConfiguration[size];
		}
	};
}