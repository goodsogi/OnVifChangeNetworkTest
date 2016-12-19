package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

public class SourceToken implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mToken;
	public String mType;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public SourceToken() {

	}

	public SourceToken(Parcel reply) {
		if (reply != null) {
			if (reply.readInt() != 0) {
				mToken = reply.readString();
			}
			if (reply.readInt() != 0) {
				mType = reply.readString();
			}
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
	public void writeToParcel(Parcel data, int flag) {
		if (data != null) {
			if (mToken != null) {
				data.writeInt(1);
				data.writeString(mToken);
			} else {
				data.writeInt(0);
			}
			if (mType != null) {
				data.writeInt(1);
				data.writeString(mType);
			} else {
				data.writeInt(0);
			}
		}
	}
	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<SourceToken> CREATOR = new Creator<SourceToken>() {
		public SourceToken createFromParcel(Parcel in) {
			return new SourceToken(in);
		}

		public SourceToken[] newArray(int size) {
			return new SourceToken[size];
		}
	};
}
