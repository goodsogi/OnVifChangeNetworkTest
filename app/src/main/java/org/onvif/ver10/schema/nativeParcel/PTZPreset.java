package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.PTZVector;

public class PTZPreset implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mName;
	public String mToken;
	public PTZVector mPTZPosition;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public PTZPreset() {
	}

	public PTZPreset(Parcel src) {
		this.mName = utils.readString(src);
		this.mToken = utils.readString(src);

		if (utils.readInt(src) != 0) {
			mPTZPosition = new PTZVector(src);
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
		utils.writeString(dst, mName);
		utils.writeString(dst, mToken);

		if (mPTZPosition != null) {
			utils.writeInt(dst, 1);
			mPTZPosition.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<PTZPreset> CREATOR = new Creator<PTZPreset>() {
		public PTZPreset createFromParcel(Parcel in) {
			return new PTZPreset(in);
		}

		public PTZPreset[] newArray(int size) {
			return new PTZPreset[size];
		}
	};

}
