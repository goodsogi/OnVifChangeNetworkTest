package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class SimpleItem implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mName;
	public String mValue;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public SimpleItem() {

	}

	public SimpleItem(Parcel src) {
		this.mName = utils.readString(src);
		this.mValue = utils.readString(src);
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
	public void writeToParcel(Parcel dst, int flags) {
		utils.writeString(dst, mName);
		utils.writeString(dst, mValue);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<SimpleItem> CREATOR = new Creator<SimpleItem>() {
		public SimpleItem createFromParcel(Parcel in) {
			return new SimpleItem(in);
		}

		public SimpleItem[] newArray(int size) {
			return new SimpleItem[size];
		}
	};

}
