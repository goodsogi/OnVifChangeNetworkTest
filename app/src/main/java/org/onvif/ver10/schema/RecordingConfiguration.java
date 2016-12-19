package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class RecordingConfiguration implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public Source mSource;
	public String mContent;
	public int mMaximumRetentionTime;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public RecordingConfiguration() {

	}

	public RecordingConfiguration(Parcel src) {
		if (utils.readInt(src) != 0) {
			mSource = new Source(src);
		}

		this.mContent = utils.readString(src);
		this.mMaximumRetentionTime = utils.readInt(src);
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
		if (mSource != null) {
			utils.writeInt(dst, 1);
			mSource.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		utils.writeString(dst, mContent);
		utils.writeInt(dst, mMaximumRetentionTime);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<RecordingConfiguration> CREATOR = new Creator<RecordingConfiguration>() {
		public RecordingConfiguration createFromParcel(Parcel in) {
			return new RecordingConfiguration(in);
		}

		public RecordingConfiguration[] newArray(int size) {
			return new RecordingConfiguration[size];
		}
	};
}