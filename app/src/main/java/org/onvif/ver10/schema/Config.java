package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class Config implements Parcelable {
	private final static String TAG = Config.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ItemList mParameters;
	public String mName;
	public String mType;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Config() {
	}

	public Config(Parcel src) {
		if (utils.readInt(src) == 1) {
			mParameters = new ItemList(src);
		}
		this.mName = utils.readString(src);
		this.mType = utils.readString(src);
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
		if (mParameters != null) {
			utils.writeInt(dst, 1);
			mParameters.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}
		utils.writeString(dst, mName);
		utils.writeString(dst, mType);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Config> CREATOR = new Creator<Config>() {
		public Config createFromParcel(Parcel in) {
			return new Config(in);
		}

		public Config[] newArray(int size) {
			return new Config[size];
		}
	};

}
