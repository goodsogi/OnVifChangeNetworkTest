package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

public class TrackItem implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mTrackToken;
	public TrackConfiguration mTrackConfiguration;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public TrackItem() {

	}

	public TrackItem(Parcel reply) {
		if (reply != null) {
			if (reply.readInt() != 0) {
				mTrackToken = reply.readString();
			}
			if (reply.readInt() != 0) {
				mTrackConfiguration = new TrackConfiguration(reply);
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
			if (mTrackConfiguration != null) {
				data.writeInt(1);
				mTrackConfiguration.writeToParcel(data, flag);
			} else {
				data.writeInt(0);
			}
		}
	}
}