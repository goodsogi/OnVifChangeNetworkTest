package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.PTZSpaces;

public class PTZNode implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mName;
	public String mToken;
	public PTZSpaces mSupportedPTZSpaces;
	public int mMaximumNumberOfPresets;
	public int mHomeSupported;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public PTZNode() {
	}

	public PTZNode(Parcel src) {
		this.mName = utils.readString(src);
		this.mToken = utils.readString(src);

		if (utils.readInt(src) != 0) {
			mSupportedPTZSpaces = new PTZSpaces(src);
		}

		this.mMaximumNumberOfPresets = utils.readInt(src);
		this.mHomeSupported = utils.readInt(src);
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

		if (mSupportedPTZSpaces != null) {
			utils.writeInt(dst, 1);
			mSupportedPTZSpaces.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		utils.writeInt(dst, mMaximumNumberOfPresets);
		utils.writeInt(dst, mHomeSupported);

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<PTZNode> CREATOR = new Creator<PTZNode>() {
		public PTZNode createFromParcel(Parcel in) {
			return new PTZNode(in);
		}

		public PTZNode[] newArray(int size) {
			return new PTZNode[size];
		}
	};

}
