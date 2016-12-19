package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

public class StreamSetup implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mStreamType;
	public Transport mTransport;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public StreamSetup() {
	}

	public StreamSetup(int type, Transport transport) {
		this.mStreamType = type;
		this.mTransport = transport;
	}

	public StreamSetup(Parcel reply) {
		if (reply != null) {
			mStreamType = reply.readInt();

			if (reply.readInt() != 0) {
				mTransport = new Transport(reply);
			}
		}
	}

	public String getStreamType() {
		switch (mStreamType) {
		case StreamType.STREAM_TYPE_UNICAST:
			return StreamType.STREAM_TYPE_UNICAST_STR;
		case StreamType.STREAM_TYPE_MULTICAST:
			return StreamType.STREAM_TYPE_MULTICAST_STR;
		}
		return StreamType.STREAM_TYPE_UNICAST_STR;
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
	public void writeToParcel(Parcel data, int flags) {
		if (data != null) {
			data.writeInt(mStreamType);
			if (mTransport != null) {
				data.writeInt(1);
				mTransport.writeToParcel(data, flags);
			} else {
				data.writeInt(0);
			}
		}
	}
	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<StreamSetup> CREATOR = new Creator<StreamSetup>() {
		public StreamSetup createFromParcel(Parcel in) {
			return new StreamSetup(in);
		}

		public StreamSetup[] newArray(int size) {
			return new StreamSetup[size];
		}
	};
}
