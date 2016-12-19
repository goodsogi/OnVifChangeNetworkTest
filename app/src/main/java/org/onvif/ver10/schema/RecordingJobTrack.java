package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class RecordingJobTrack implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mSourceTag;
	public String mDestination;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public RecordingJobTrack() {

	}

	public RecordingJobTrack(Parcel src) {
		mSourceTag = utils.readString(src);
		mDestination = utils.readString(src);
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
		utils.writeString(dst, mSourceTag);
		utils.writeString(dst, mDestination);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<RecordingJobTrack> CREATOR = new Creator<RecordingJobTrack>() {
		public RecordingJobTrack createFromParcel(Parcel in) {
			return new RecordingJobTrack(in);
		}

		public RecordingJobTrack[] newArray(int size) {
			return new RecordingJobTrack[size];
		}
	};
}
