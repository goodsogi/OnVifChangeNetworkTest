package org.onvif.ver10.search.wsdl.get;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class GetTrackInformation implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mRecordingToken;
	public String mTrackToken;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public GetTrackInformation() {
	}

	public GetTrackInformation(Parcel src) {
		this.mRecordingToken = utils.readString(src);
		this.mTrackToken = utils.readString(src);
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
	}
}