package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class JobSource implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public SourceToken mSourceToken;
	public int mAutoCreateReceiver;
	public ArrayList<RecordingJobTrack> mRecordingJobTrack;
	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public JobSource() {

	}

	public JobSource(Parcel src) {
		if (utils.readInt(src) != 0) {
			mSourceToken = new SourceToken(src);
		}

		mAutoCreateReceiver = utils.readInt(src);

		mRecordingJobTrack = new ArrayList<RecordingJobTrack>();
		int jobTrackSize = utils.readInt(src);
		for (int i = 0; i < jobTrackSize; i++) {
			mRecordingJobTrack.add(new RecordingJobTrack(src));
		}
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
		if (mSourceToken != null) {
			utils.writeInt(dst, 1);
			mSourceToken.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		utils.writeInt(dst, mAutoCreateReceiver);

		if (mRecordingJobTrack != null) {
			int jobTrackSize = mRecordingJobTrack.size();
			utils.writeInt(dst, jobTrackSize);
			for (int i = 0; i < jobTrackSize; i++) {
				mRecordingJobTrack.get(i).writeToParcel(dst, flag);
			}
		} else {
			utils.writeInt(dst, 0);
		}

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<JobSource> CREATOR = new Creator<JobSource>() {
		public JobSource createFromParcel(Parcel in) {
			return new JobSource(in);
		}

		public JobSource[] newArray(int size) {
			return new JobSource[size];
		}
	};
}
