package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

public class Source implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mSourceId;
	public String mName;
	public String mLocation;
	public String mDescription;
	public String mAddress;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Source() {

	}

	public Source(Parcel reply) {
		if (reply != null) {
			if (reply.readInt() != 0) {
				mSourceId = reply.readString();
			}
			if (reply.readInt() != 0) {
				mName = reply.readString();
			}
			if (reply.readInt() != 0) {
				mLocation = reply.readString();
			}
			if (reply.readInt() != 0) {
				mDescription = reply.readString();
			}
			if (reply.readInt() != 0) {
				mAddress = reply.readString();
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
			if (mSourceId != null) {
				data.writeInt(1);
				data.writeString(mSourceId);
			} else {
				data.writeInt(0);
			}
			if (mName != null) {
				data.writeInt(1);
				data.writeString(mName);
			} else {
				data.writeInt(0);
			}
			if (mLocation != null) {
				data.writeInt(1);
				data.writeString(mLocation);
			} else {
				data.writeInt(0);
			}
			if (mDescription != null) {
				data.writeInt(1);
				data.writeString(mDescription);
			} else {
				data.writeInt(0);
			}
			if (mAddress != null) {
				data.writeInt(1);
				data.writeString(mAddress);
			} else {
				data.writeInt(0);
			}
		}
	}
	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Source> CREATOR = new Creator<Source>() {
		public Source createFromParcel(Parcel in) {
			return new Source(in);
		}

		public Source[] newArray(int size) {
			return new Source[size];
		}
	};
}