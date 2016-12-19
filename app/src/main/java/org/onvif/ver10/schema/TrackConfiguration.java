package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

public class TrackConfiguration implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mTrackType;
	public String mDescription;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public TrackConfiguration() {

	}

	public TrackConfiguration(Parcel reply) {
		if (reply != null) {
			mTrackType = reply.readInt();

			if (reply.readInt() != 0) {
				mDescription = reply.readString();
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
			data.writeInt(mTrackType);

			if (mDescription != null) {
				data.writeInt(1);
				data.writeString(mDescription);
			} else {
				data.writeInt(0);
			}

		}
	}
}