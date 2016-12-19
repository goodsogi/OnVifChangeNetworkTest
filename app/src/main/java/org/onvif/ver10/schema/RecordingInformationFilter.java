package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class RecordingInformationFilter implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mSourceName; // optional
	public String mDataFrom; // optional
	public String mDataUntil; // optional

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public RecordingInformationFilter() {

	}

	public RecordingInformationFilter(Parcel src) {
		mSourceName = utils.readString(src);
		mDataFrom = utils.readString(src);
		mDataUntil = utils.readString(src);
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
		utils.writeString(dst, mSourceName);
		utils.writeString(dst, mDataFrom);
		utils.writeString(dst, mDataUntil);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<RecordingInformationFilter> CREATOR = new Creator<RecordingInformationFilter>() {
		public RecordingInformationFilter createFromParcel(Parcel in) {
			return new RecordingInformationFilter(in);
		}

		public RecordingInformationFilter[] newArray(int size) {
			return new RecordingInformationFilter[size];
		}
	};
}