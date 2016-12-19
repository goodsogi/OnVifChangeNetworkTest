package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class IncludedSources implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mToken;
	public String mType; // optional - anyURI

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public IncludedSources() {

	}

	public IncludedSources(Parcel src) {
		this.mToken = utils.readString(src);
		this.mType = utils.readString(src);
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
		utils.writeString(dst, mToken);
		utils.writeString(dst, mType);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<IncludedSources> CREATOR = new Creator<IncludedSources>() {
		public IncludedSources createFromParcel(Parcel in) {
			return new IncludedSources(in);
		}

		public IncludedSources[] newArray(int size) {
			return new IncludedSources[size];
		}
	};
}