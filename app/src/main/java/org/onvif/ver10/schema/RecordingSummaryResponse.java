package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class RecordingSummaryResponse implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mDateFrom;
	public String mDateUntil;
	public int mNumberRecordings;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public RecordingSummaryResponse() {

	}

	public RecordingSummaryResponse(Parcel src) {
		this.mDateFrom = utils.readString(src);
		this.mDateUntil = utils.readString(src);
		this.mNumberRecordings = utils.readInt(src);
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
		utils.writeString(dst, mDateFrom);
		utils.writeString(dst, mDateUntil);
		utils.writeInt(dst, mNumberRecordings);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<RecordingSummaryResponse> CREATOR = new Creator<RecordingSummaryResponse>() {
		public RecordingSummaryResponse createFromParcel(Parcel in) {
			return new RecordingSummaryResponse(in);
		}

		public RecordingSummaryResponse[] newArray(int size) {
			return new RecordingSummaryResponse[size];
		}
	};
}