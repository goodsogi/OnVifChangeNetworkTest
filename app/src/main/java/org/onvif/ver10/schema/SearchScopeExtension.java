package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class SearchScopeExtension implements Parcelable {
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
	public SearchScopeExtension() {

	}

	public SearchScopeExtension(Parcel src) {
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
	public static final Creator<SearchScopeExtension> CREATOR = new Creator<SearchScopeExtension>() {
		public SearchScopeExtension createFromParcel(Parcel in) {
			return new SearchScopeExtension(in);
		}

		public SearchScopeExtension[] newArray(int size) {
			return new SearchScopeExtension[size];
		}
	};
}