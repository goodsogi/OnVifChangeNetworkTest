package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class JobItem implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mJobToken;
	public JobConfiguration mJobConfiguration;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public JobItem() {

	}

	public JobItem(Parcel src) {
		mJobToken = utils.readString(src);
		if (utils.readInt(src) != 0) {
			mJobConfiguration = new JobConfiguration(src);
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
		utils.writeString(dst, mJobToken);
		if (mJobConfiguration != null) {
			utils.writeInt(dst, 1);
			mJobConfiguration.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<JobItem> CREATOR = new Creator<JobItem>() {
		public JobItem createFromParcel(Parcel in) {
			return new JobItem(in);
		}

		public JobItem[] newArray(int size) {
			return new JobItem[size];
		}
	};
}