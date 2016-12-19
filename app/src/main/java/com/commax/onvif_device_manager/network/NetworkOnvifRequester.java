package com.commax.onvif_device_manager.network;

import com.commax.onvif_device_manager.interfaces.CallbackDialogListener;
import com.commax.onvif_device_manager.uitls.LOG;

import org.ksoap2.SoapFault;
import org.onvif.ver10.device.wsdl.DeviceService;
import org.onvif.ver10.device.wsdl.get.GetCapabilities;
import org.onvif.ver10.device.wsdl.get.GetCapabilitiesResponse;
import org.onvif.ver10.device.wsdl.set.SetNetworkInterfaces;
import org.onvif.ver10.device.wsdl.set.SetNetworkInterfacesResponse;
import org.onvif.ver10.media.wsdl.MediaService;
import org.onvif.ver10.media.wsdl.get.GetProfiles;
import org.onvif.ver10.media.wsdl.get.GetProfilesResponse;
import org.onvif.ver10.media.wsdl.get.GetStreamUri;
import org.onvif.ver10.media.wsdl.get.GetStreamUriResponse;
import org.onvif.ver10.schema.StreamSetup;
import org.onvif.ver10.schema.StreamType;
import org.onvif.ver10.schema.Transport;
import org.onvif.ver10.schema.TransportProtocol;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 * @brief Onvif Request를 모아 둔 클래스
 */
public class NetworkOnvifRequester {
    /***********************************************************************************
     * private defines
     ***********************************************************************************/
    private static final String TAG = NetworkOnvifRequester.class.getSimpleName();
    /*************************************************************************
     * public Defines
     *************************************************************************/
    public static final int SUCCESS = 0;
    public static final int ERROR_SOAP_FAULT = -1;
    public static final int ERROR_SOAP_AUTH = -2;
    public static final int ERROR_SOCKET_TIMEOUT = -3;
    public static final int ERROR_IOEXCEPTION = -4;
    public static final int ERROR_XMLPULLPARSEREXCEPTION = -5;
    public static final int ERROR_UNKNOWN_IPADDRESS = -6;
    public static final int ERROR_UNKNOWN = -7;
    public static final int ERROR_INVALID_STREAM_URI = -8;
    public static final int ERROR_INVALID_SNAPSHOT_URI = -9;
    /**************************************************************************
     * values
     **************************************************************************/
    private String mIPAddress;
    private int mPort;
    private String mID;
    private String mPassword;
    private String mDeviceServiceUri;
    private String mMediaServiceUri;
    private boolean mRun;
    private int mResult;

    /**************************************************************************
     * Device Service WSDL
     **************************************************************************/
    private DeviceService mDeviceService;
    public GetCapabilitiesResponse mGetCapabilitiesResponse;

    /**************************************************************************
     * Media Service WSDL
     **************************************************************************/
    private MediaService mMediaService;
    public GetProfilesResponse mGetProfilesResponse;
    public ArrayList<GetStreamUriResponse> mGetStreamUriResponses;
    public SetNetworkInterfacesResponse mSetNetworkResponse;

    /**************************************************************************
     * Constructor
     **************************************************************************/
    public NetworkOnvifRequester(String ip, int port, String id, String pw) {
        this.mIPAddress = ip;
        this.mPort = port;
        this.mID = id;
        this.mPassword = pw;

        mDeviceServiceUri = String.format("http://%s:%d/onvif/device_service", mIPAddress, mPort);
        mMediaServiceUri = String.format("http://%s:%d/onvif/media_service", mIPAddress, mPort);
        LOG.i(TAG, String.format("DeviceServiceUri:%s", mDeviceServiceUri));
        LOG.i(TAG, String.format("MediaServiceUri:%s", mMediaServiceUri));
    }

    /*************************************************************************
     * createDeviceManagementAuthHeader
     *************************************************************************/
    public int createDeviceManagementAuthHeader() {
        return sendOnvifRequest(OnvifRequest.createDeviceManagementAuthHeader, null);
    }

    /**********************************************************************************************
     * GetCapabilities
     **********************************************************************************************/
    public int GetCapabilities() {
        return sendOnvifRequest(OnvifRequest.GetCapabilities, null);
    }

    /*************************************************************************
     * createMediaManagementAuthHeader
     *************************************************************************/
    public int createMediaManagementAuthHeader() {
        return sendOnvifRequest(OnvifRequest.createMediaServiceAuthHeader, null);
    }

    /**********************************************************************************************
     * GetProfiles
     **********************************************************************************************/
    public int GetProfiles() {
        return sendOnvifRequest(OnvifRequest.GetProfiles, null);
    }

    /**********************************************************************************************
     * GetStreamUri
     **********************************************************************************************/
    public int GetStreamUri(String profileToken) {
        return sendOnvifRequest(OnvifRequest.GetStreamUri, profileToken);
    }

    /**********************************************************************************************
     * sendOnvifRequest
     **********************************************************************************************/
    private int sendOnvifRequest(int type, Object obj) {
        mRun = true;
        new OnvifRequest(type, obj, new CallbackDialogListener<Integer>() {
            @Override
            public void callDialogListener(Integer value) {
                mResult = value;
                mRun = false;
            }
        }).start();

        while (mRun) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return mResult;
    }

    public int setNetworkInterface() {
        return sendOnvifRequest(OnvifRequest.SetNetworkInterface, null);
    }

    /**********************************************************************************************
     * OnvifRequest Thread
     **********************************************************************************************/
    private class OnvifRequest extends Thread {
        public final static int createDeviceManagementAuthHeader = 0x00100;
        public final static int GetCapabilities = 0x00101;
        public final static int GetDeviceInformation = 0x00102;

        public final static int createMediaServiceAuthHeader = 0x00200;
        public final static int GetProfiles = 0x00201;
        public final static int GetVideoEncoderConfigurationOptions = 0x00202;
        public final static int SetVideoEncoderConfiguration = 0x00203;
        public final static int GetStreamUri = 0x00204;
        public final static int GetSnapshotUri = 0x00205;
        public final static int SetNetworkInterface = 0x00300;

        private int mType;
        private Object mObject;
        private CallbackDialogListener<Integer> mCallback;

        public void sendCallback(int value) {
            if (mCallback != null) {
                mCallback.callDialogListener(value);
            }
        }

        public OnvifRequest(int request_type, Object obj, CallbackDialogListener<Integer> l) {
            this.mType = request_type;
            this.mObject = obj;
            this.mCallback = l;
        }

        @Override
        public void run() {
            int result = 0;
            try {
                switch (mType) {
                    case createDeviceManagementAuthHeader:
                        LOG.d(TAG, "createDeviceManagementAuthHeader");
                        mDeviceService = null;
                        mDeviceService = new DeviceService(mDeviceServiceUri);
                        mDeviceService.createAuthHeader(mID, mPassword);
                        break;
                    case GetCapabilities:
                        LOG.d(TAG, "GetCapabilities");
                        Object request = new GetCapabilities();
                        mGetCapabilitiesResponse = mDeviceService.getCapabilities((org.onvif.ver10.device.wsdl.get.GetCapabilities) request);
                        break;
                    case GetDeviceInformation:
                        break;
                    case createMediaServiceAuthHeader:
                        LOG.d(TAG, "createMediaServiceAuthHeader");
                        mMediaService = null;
                        mMediaService = new MediaService(mMediaServiceUri);
                        mMediaService.setWsUsernameToken(mDeviceService.getWsUsernameToken());
                        break;
                    case GetProfiles:
                        LOG.d(TAG, "GetProfiles");
                        request = new GetProfiles();
                        mGetProfilesResponse = mMediaService.getProfiles((org.onvif.ver10.media.wsdl.get.GetProfiles) request);
                        mGetStreamUriResponses = null;
                        break;
                    case GetVideoEncoderConfigurationOptions:

                        break;
                    case SetVideoEncoderConfiguration:

                        break;
                    case GetStreamUri:
                        LOG.d(TAG, "GetStreamUri");
                        request = new GetStreamUri();
                        StreamSetup streamsetup = new StreamSetup();
                        streamsetup.mStreamType = StreamType.STREAM_TYPE_UNICAST;
                        Transport transport = new Transport();
                        transport.mTransportProtocol = TransportProtocol.TRANSPORT_PROTOCOL_UDP;
                        streamsetup.mTransport = transport;
                        ((org.onvif.ver10.media.wsdl.get.GetStreamUri) request).setStreamSetup(streamsetup, (String) mObject);

                        if (mGetStreamUriResponses == null) {
                            mGetStreamUriResponses = new ArrayList<GetStreamUriResponse>();
                        }
                        mGetStreamUriResponses.add(mMediaService.getStreamUri((org.onvif.ver10.media.wsdl.get.GetStreamUri) request));
                        break;
                    case GetSnapshotUri:
                        break;

                    case SetNetworkInterface:

                        Object setNetworkRequest = new SetNetworkInterfaces();
                        //아래 값 수정 필요!!
                        ((SetNetworkInterfaces) setNetworkRequest).setNetworkInterfacesEnabled(true);
                        ((SetNetworkInterfaces) setNetworkRequest).setIPv4ManualAddress("192.168.0.4");
                        ((SetNetworkInterfaces) setNetworkRequest).setIPv4DHCP(false);


                        if(mDeviceService == null) {
                            mDeviceService = new DeviceService(mDeviceServiceUri);
                            mDeviceService.createAuthHeader(mID, mPassword);
                        }

                        mSetNetworkResponse = mDeviceService.setNetworkInterface((org.onvif.ver10.device.wsdl.set.SetNetworkInterfaces) setNetworkRequest);


                        break;

                    default:
                        LOG.e(TAG, "unknown onvif requst message:" + mType);
                        result = -100;
                        break;
                }

                result = SUCCESS;
            } catch (SoapFault e) {
                e.printStackTrace();
                result = ERROR_SOAP_FAULT;
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                result = ERROR_SOCKET_TIMEOUT;
            } catch (IOException e) {
                e.printStackTrace();
                if (e.toString().contains("auth")) {
                    result = ERROR_SOAP_AUTH;
                } else if (e.toString().contains("password")) {
                    result = ERROR_SOAP_AUTH;
                } else if (e.toString().contains("400")) {
                    result = ERROR_SOAP_AUTH;
                } else {
                    result = ERROR_IOEXCEPTION;
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                result = ERROR_XMLPULLPARSEREXCEPTION;
            }
            sendCallback(result);
            super.run();
        }
    }
}
