package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

public class MetadataConfiguration implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mName;
	public int mUseCount;
	public String mToken;

	// 더 있지만.. 필요 할 경우 구현 예정

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public MetadataConfiguration() {

	}

	public MetadataConfiguration(Parcel src) {
		mName = utils.readString(src);
		mUseCount = utils.readInt(src);
		mToken = utils.readString(src);

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
		utils.writeString(dst, mName);
		utils.writeInt(dst, mUseCount);

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<MetadataConfiguration> CREATOR = new Creator<MetadataConfiguration>() {
		public MetadataConfiguration createFromParcel(Parcel in) {
			return new MetadataConfiguration(in);
		}

		public MetadataConfiguration[] newArray(int size) {
			return new MetadataConfiguration[size];
		}
	};

}
