package com.commax.onvif_device_manager.fragment.site;


import com.commax.onvif_device_manager.fragment.Camera;

/**
 * @brief SiteDevice의 정보를 담고 있다.
 */
public class SiteDeviceItem {
	/** @brief 해당 SiteDeviceItem의 네트워크 접속 상태 현황(정상) */
	public final static int NETWORK_STATUS_CONNECTED = 1;
	/** @brief 해당 SiteDeviceItem의 네트워크 접속 상태 현황(비정상) */
	public final static int NETWORK_STATUS_DISCONNECTED = 0;
	/** @brief 해당 SiteDeviceItem의 네트워크 접속 상태 현황(접속중) */
	public final static int NETWORK_STATUS_LOADING = 2;

	public String mSiteName;
	public String mSiteIP;
	public int mSitePort;
	public String mSiteID;
	public String mSitePassword;
	public int mSiteStatus;

	public Camera mIPCamera;

	public SiteDeviceItem(String SiteName, String SiteIP, int SitePort, String SiteID, String SitePassword,
						  int SiteStatus) {
		this.mSiteName = SiteName;
		this.mSiteIP = SiteIP;
		this.mSitePort = SitePort;
		this.mSiteID = SiteID;
		this.mSitePassword = SitePassword;
		this.mSiteStatus = SiteStatus;
	}

	public void update(String SiteName, String SiteIP, int SitePort, String SiteID, String SitePassword) {
		this.mSiteName = SiteName;
		this.mSiteIP = SiteIP;
		this.mSitePort = SitePort;
		this.mSiteID = SiteID;
		this.mSitePassword = SitePassword;
	}

	public void setIPCamera(Camera camera) {
		mIPCamera = camera;
	}

	public Camera getIPCamera() {
		return mIPCamera;
	}
}
