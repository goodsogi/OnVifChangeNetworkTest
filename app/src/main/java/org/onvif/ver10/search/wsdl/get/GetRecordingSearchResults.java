package org.onvif.ver10.search.wsdl.get;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class GetRecordingSearchResults implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mSearchToken;
	public int mMinResults; // optional
	public int mMaxResults; // optional
	public int mWaitTime; // optional, second

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public GetRecordingSearchResults() {

	}

	public GetRecordingSearchResults(Parcel src) {
		this.mSearchToken = utils.readString(src);
		this.mMinResults = utils.readInt(src);
		this.mMaxResults = utils.readInt(src);
		this.mWaitTime = utils.readInt(src);
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
		utils.writeString(dst, mSearchToken);
		utils.writeInt(dst, mMinResults);
		utils.writeInt(dst, mMaxResults);
		utils.writeInt(dst, mWaitTime);
	}
}