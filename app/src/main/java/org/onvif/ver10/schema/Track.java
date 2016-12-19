package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

public class Track implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mTrackToken;
	public int mType;
	public String mDescription;
	public String mDataFrom;
	public String mDataTo;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Track() {

	}

	public Track(Parcel reply) {
		if (reply != null) {
			if (reply.readInt() != 0) {
				mTrackToken = reply.readString();
			}
			mType = reply.readInt();
			if (reply.readInt() != 0) {
				mDescription = reply.readString();
			}
			if (reply.readInt() != 0) {
				mDataFrom = reply.readString();
			}
			if (reply.readInt() != 0) {
				mDataTo = reply.readString();
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
			if (mTrackToken != null) {
				data.writeInt(1);
				data.writeString(mTrackToken);
			} else {
				data.writeInt(0);
			}
			data.writeInt(mType);
			if (mDescription != null) {
				data.writeInt(1);
				data.writeString(mDescription);
			} else {
				data.writeInt(0);
			}
			if (mDataFrom != null) {
				data.writeInt(1);
				data.writeString(mDataFrom);
			} else {
				data.writeInt(0);
			}
			if (mDataTo != null) {
				data.writeInt(1);
				data.writeString(mDataTo);
			} else {
				data.writeInt(0);
			}
		}
	}
	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Track> CREATOR = new Creator<Track>() {
		public Track createFromParcel(Parcel in) {
			return new Track(in);
		}

		public Track[] newArray(int size) {
			return new Track[size];
		}
	};
}