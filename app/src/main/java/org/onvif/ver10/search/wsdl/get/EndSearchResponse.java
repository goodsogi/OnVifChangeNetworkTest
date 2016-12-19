package org.onvif.ver10.search.wsdl.get;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class EndSearchResponse implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mEndpoint;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public EndSearchResponse() {

	}

	public EndSearchResponse(Parcel src) {
		this.mEndpoint = utils.readString(src);
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
		utils.writeString(dst, mEndpoint);
	}
}