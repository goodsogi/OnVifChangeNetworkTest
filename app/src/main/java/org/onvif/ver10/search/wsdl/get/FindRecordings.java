package org.onvif.ver10.search.wsdl.get;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.SearchScope;
import org.onvif.ver10.schema.nativeParcel.utils;

public class FindRecordings implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public SearchScope mScope;
	public int mMaxMatches; // optional
	public int mKeepAliveTime; // second

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public FindRecordings() {
	}

	public FindRecordings(Parcel src) {
		if (src != null) {
			if (src.readInt() != 0) {
				this.mScope = new SearchScope(src);
			}
			this.mMaxMatches = utils.readInt(src);
			this.mKeepAliveTime = utils.readInt(src);
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
		if (dst != null) {
			if (mScope != null) {
				dst.writeInt(1);
				mScope.writeToParcel(dst, flag);
			} else {
				dst.writeInt(0);
			}
			utils.writeInt(dst, mMaxMatches);
			utils.writeInt(dst, mKeepAliveTime);
		}
	}
}