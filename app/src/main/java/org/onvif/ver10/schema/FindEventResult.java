package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class FindEventResult implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mRecordingToken;
	public String mTrackToken;
	public String mTime;
	public String mEvent;
	public int mStartStateEvent;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public FindEventResult() {

	}

	public FindEventResult(Parcel src) {
		mRecordingToken = utils.readString(src);
		mTrackToken = utils.readString(src);
		mTime = utils.readString(src);
		mEvent = utils.readString(src);
		mStartStateEvent = utils.readInt(src);
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
		utils.writeString(dst, mRecordingToken);
		utils.writeString(dst, mTrackToken);
		utils.writeString(dst, mTime);
		utils.writeString(dst, mEvent);
		utils.writeInt(dst, mStartStateEvent);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<FindEventResult> CREATOR = new Creator<FindEventResult>() {
		public FindEventResult createFromParcel(Parcel in) {
			return new FindEventResult(in);
		}

		public FindEventResult[] newArray(int size) {
			return new FindEventResult[size];
		}
	};
}