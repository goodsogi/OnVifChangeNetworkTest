package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

public class Transport implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	public int mTransportProtocol;
//	public Transport mTunnel;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Transport() {

	}

	public Transport(int type, Transport tunnel) {
		this.mTransportProtocol = type;
//		this.mTunnel = tunnel;
	}

	public Transport(Parcel reply) {
		if (reply != null) {
			mTransportProtocol = reply.readInt();

//			if (reply.readInt() != 0) {
//				mTunnel = new Transport(reply);
//			}
		}
	}
	
	public String getTransportProtocol(){
		switch(mTransportProtocol){
		case TransportProtocol.TRANSPORT_PROTOCOL_UDP:
			return TransportProtocol.TRANSPORT_PROTOCOL_UDP_STR;
		case TransportProtocol.TRANSPORT_PROTOCOL_TCP:
			return TransportProtocol.TRANSPORT_PROTOCOL_TCP_STR;
		case TransportProtocol.TRANSPORT_PROTOCOL_RTSP:
			return TransportProtocol.TRANSPORT_PROTOCOL_RTSP_STR;
		case TransportProtocol.TRANSPORT_PROTOCOL_HTTP:
			return TransportProtocol.TRANSPORT_PROTOCOL_HTTP_STR;
		}
		return TransportProtocol.TRANSPORT_PROTOCOL_TCP_STR;
	}
	
	/*****************************************************************************************
	 * @Override describeContents
	 *****************************************************************************************/
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	/*****************************************************************************************
	 * @Override writeToParcel
	 *****************************************************************************************/
	@Override
	public void writeToParcel(Parcel data, int flags) {
		if (data != null) {
			data.writeInt(mTransportProtocol);
			
//			if(mTunnel!=null){
//				data.writeInt(1);
//				mTunnel.writeToParcel(data, flags);
//			}else{
//				data.writeInt(0);
//			}
		}

	}
	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Transport> CREATOR = new Creator<Transport>() {
		public Transport createFromParcel(Parcel in) {
			return new Transport(in);
		}

		public Transport[] newArray(int size) {
			return new Transport[size];
		}
	};
}
