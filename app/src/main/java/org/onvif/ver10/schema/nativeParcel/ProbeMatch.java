package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


import com.commax.onvif_device_manager.uitls.LOG;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.kxml2.kdom.Element;

import java.net.URLDecoder;

public class ProbeMatch implements Parcelable {
	/*****************************************************************************************
	 * @Override toString
	 *****************************************************************************************/
	@Override
	public String toString() {
		LOG.i(TAG,"Onvif Address:"+mOnvifDeviceServiceXAddr);
		return super.toString();
	}

	/*****************************************************************************************
	 * private values
	 *****************************************************************************************/
	private final static String TAG = ProbeMatch.class.getSimpleName();
	private String mSrcIPAddress = null;
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/

	public String mTypes;
	public String mScopes;
	public String mXAddrs;
	public int mMetadataVersion;

	// device infomation
	public String mOnvifVendorModel = "";
	public String mOnvifIPAddress = "";
	public int mOnvifPort;
	public String mOnvifDeviceServiceXAddr = "";

	//public boolean isSBOXDevice;
	public boolean isSBOXDevice;
	public int mResult;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public ProbeMatch() {

	}

	public ProbeMatch(Parcel src) {
		this.mSrcIPAddress = utils.readString(src);
		this.mTypes = utils.readString(src);
		this.mScopes = utils.readString(src);
		this.mXAddrs = utils.readString(src);
		this.mMetadataVersion = utils.readInt(src);
		this.mOnvifVendorModel = utils.readString(src);
		this.mOnvifIPAddress = utils.readString(src);
		this.mOnvifPort = utils.readInt(src);
		this.mOnvifDeviceServiceXAddr = utils.readString(src);
		this.mResult = utils.readInt(src);
	}

	public ProbeMatch(SoapSerializationEnvelope obj, String uuid, String srcIPAddress) {
		this.mSrcIPAddress = srcIPAddress;
		if (mSrcIPAddress.equals("239.255.255.250") || mSrcIPAddress.equals("[FF02::C]"))
			this.mSrcIPAddress = null;

		if (obj != null) {
			// ================================================
			// HEADER
			// ================================================
			Element[] header = (Element[]) obj.headerIn;
			int size = header.length;
			for (int i = 0; i < size; i++) {
				if (header[i].getName().equals("MessageID")) {
					// mHeaderMessageID = header[i].getText(0).toString();
				} else if (header[i].getName().equals("RelatesTo")) {
					String RelatesToUUID = header[i].getText(0).toString();
					if (!RelatesToUUID.contains(uuid)) {
						return;
					}
				} else if (header[i].getName().equals("To")) {
					// Log.i(TAG, "HEADER To:" + (header[i].getText(0).toString()));
				} else if (header[i].getName().equals("Action")) {
					// Log.i(TAG, "HEADER Action:" + (header[i].getText(0).toString()));
				} else if (header[i].getName().equals("AppSequence")) {
					// Log.i(TAG, "HEADER AppSequence:" + (header[i].getText(0).toString()));
				}
			}

			// ================================================
			// BODY
			// ================================================
			SoapObject body = (SoapObject) obj.bodyIn;
			size = body.getPropertyCount();
			for (int i = 0; i < size; i++) {
				PropertyInfo ProbeMatch = new PropertyInfo();
				body.getPropertyInfo(i, ProbeMatch);
				if (ProbeMatch.getName().equals("ProbeMatch")) {
					SoapObject childObj = (SoapObject) ProbeMatch.getValue();
					int psize = childObj.getPropertyCount();
					for (int p = 0; p < psize; p++) {
						PropertyInfo childProperty = new PropertyInfo();
						childObj.getPropertyInfo(p, childProperty);
						if (childProperty.getName().equals("EndpointReference")) {
							try {
								Log.d(TAG, "BODY EndpointReference:" + childProperty.getValue().toString());
								if (childProperty.getValue().toString().contains("sboxnvr")) {
									isSBOXDevice = true;
								}
							} catch (Exception e) {

							}
						} else if (childProperty.getName().equals("Types")) {
							mTypes = childProperty.getValue().toString();
							Log.d(TAG, "BODY Types:" + mTypes);
						} else if (childProperty.getName().equals("Scopes")) {
							mScopes = childProperty.getValue().toString();
							Log.d(TAG, "BODY Scopes :" + mScopes);
						} else if (childProperty.getName().equals("XAddrs")) {
							mXAddrs = childProperty.getValue().toString();
							Log.d(TAG, "BODY XAddrs:" + mXAddrs);
						} else if (childProperty.getName().equals("MetadataVersion")) {
							try {
								mMetadataVersion = Integer.parseInt(childProperty.getValue().toString());
							} catch (Exception e) {
								e.printStackTrace();
							}
							Log.d(TAG, "BODY MetadataVersion:" + mMetadataVersion);
						}
					}
				}
			}
			setParsingData();
		}
	}

	/****************************************************************************************************
	 * setParsingData : 검색 된 결과 값을 가지고 실제 사용 가능한 값으로 데이터 파싱
	 ****************************************************************************************************/
	private void setParsingData() {
		if (mScopes != null) {
			String Scopes[] = mScopes.split("\\s+");
			for (int s = 0; s < Scopes.length; s++) {
				if (Scopes[s].contains("onvif://www.onvif.org/name/")) {
					try {
						mOnvifVendorModel = URLDecoder.decode(Scopes[s].replaceAll("onvif://www.onvif.org/name/", ""), "UTF-8");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (mXAddrs != null) {
			String Xaddr[] = mXAddrs.split("\\s+");
			int ipAddressCount = Xaddr.length;
			Log.d(TAG, "ipAddressCount1 :" + ipAddressCount + ":" + mXAddrs);
			if (mXAddrs.contains("169.254") || mXAddrs.contains("[fe80")) {
				for (int x = 0; x < ipAddressCount; x++) {
					if (checkAvailableIPAddress(Xaddr[x]) == null) {
						mXAddrs = mXAddrs.replace(Xaddr[x], "");
					}
				}
				Xaddr = mXAddrs.split("\\s+");
				ipAddressCount = Xaddr.length;
			}
			Log.d(TAG, "ipAddressCount2 :" + ipAddressCount + ":" + mXAddrs);
			// IP주소가 2개 이상인 경우.(zero configure 제외)
			// IP주소 중Local Network와 동일 한 대역의 IP주소 사용 함.
			// 단 사용자가 직접 추가 한 IP주소가 있는 경우(mSrcIPAddress)
			// 해당 IP를 사용 할 수도 있음.
			if (ipAddressCount >= 2) {
				for (int x = 0; x < ipAddressCount; x++) {
					String temp[] = Xaddr[x].split("/");
					// IP주소의 유효성 체크(IPv4, zero configuration)
					String ip = checkAvailableIPAddress(Xaddr[x]);
					if (ip != null) {
						setOnvifIPInfo(ip, temp, Xaddr, x);
					}
				}
			}
			// IP주소가 1개 인 경우 해당 IP주소가 Zero Configuration이 아니면
			// 그대로 사용 함.
			else if (ipAddressCount == 1) {
				String ip = checkAvailableIPAddress(Xaddr[0]);
				if (ip != null) {
					String temp[] = Xaddr[0].split("/");
					setOnvifIPInfo(ip, temp, Xaddr, 0);
				}
			}
		}
	}

	private void setOnvifIPInfo(String ip, String[] temp, String[] Xaddr, int x) {
		mOnvifIPAddress = ip;
		try {
			mOnvifPort = Integer.parseInt(temp[2].split(":")[1]);
		} catch (Exception e) {
			mOnvifPort = 8000;
		}
		int tempSize = temp.length;

		for (int t = 3; t < tempSize; t++) {
			if (t == (tempSize - 1)) {
				mOnvifDeviceServiceXAddr += temp[t];
			} else {
				mOnvifDeviceServiceXAddr += temp[t] + "/";
			}
		}
		mXAddrs = Xaddr[x];

		if (mSrcIPAddress != null) {
			mOnvifIPAddress = mSrcIPAddress;
		}

		Log.d(TAG, "mOnvifIPAddress:" + mOnvifIPAddress + " mOnvifPort:" + mOnvifPort + " :" + mOnvifDeviceServiceXAddr + ":" + mXAddrs);

	}

	/****************************************************************************************************
	 * checkAvailableIPAddress : 해당 IP가 제대로 된 IP주소 인지 확인(zero config 제외시킴)
	 ****************************************************************************************************/
	private String checkAvailableIPAddress(String ipv4) {
		try {
			String temp[] = ipv4.split("/");
			String ip = temp[2].split(":")[0];
			//if (ipv4.contains("169.254") || !utils.isIPv4Address(ip)) {
			if (ipv4.contains("169.254") || ipv4.contains("[fe80") ) {
				return null;
			}
			return ip;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/****************************************************************************************************
	 * Parcelable
	 ****************************************************************************************************/

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
	public void writeToParcel(Parcel dst, int flags) {
		utils.writeString(dst, mSrcIPAddress);
		utils.writeString(dst, mTypes);
		utils.writeString(dst, mScopes);
		utils.writeString(dst, mXAddrs);
		utils.writeInt(dst, mMetadataVersion);
		utils.writeString(dst, mOnvifVendorModel);
		utils.writeString(dst, mOnvifIPAddress);
		utils.writeInt(dst, mOnvifPort);
		utils.writeString(dst, mOnvifDeviceServiceXAddr);
		utils.writeInt(dst, mResult);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<ProbeMatch> CREATOR = new Creator<ProbeMatch>() {
		public ProbeMatch createFromParcel(Parcel in) {
			return new ProbeMatch(in);
		}

		public ProbeMatch[] newArray(int size) {
			return new ProbeMatch[size];
		}
	};

}
