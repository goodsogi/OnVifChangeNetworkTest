package org.onvif.ver10.schema;

import android.util.Log;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.onvif.ver10.device.wsdl.DeviceService;
import org.onvif.ver10.device.wsdl.get.GetSystemDateAndTime;
import org.onvif.ver10.device.wsdl.get.GetSystemDateAndTimeResponse;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class BaseService {
	private static final String TAG = BaseService.class.getSimpleName();
	/************************************
	 * DEFINE
	 *************************************/
	private static final String USER_AGENT = "SOAP Client";
	private static final String WSSE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private static final String WSU = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
	private static final String PASSWD_TYPE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest";

	public static int TIMEOUT = 5000; // 5sec

	protected SoapSerializationEnvelope mEnvelope;
	protected HttpTransportSE mHttpTransport;
	protected ArrayList<HeaderProperty> mHeaderProperty;

	protected WsUsernameToken mUsernameToken;

	protected String mURL;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	public WsUsernameToken getWsUsernameToken() {
		return mUsernameToken;
	}

	public void setWsUsernameToken(WsUsernameToken wsToken) {
		mUsernameToken = wsToken;
	}

	public void setDeviceUserName(String username) {
		if (mUsernameToken != null) {
			mUsernameToken.setUsername(username);
		}
	}

	public void setDeviceUserPassword(String userpassword) {
		if (mUsernameToken != null) {
			mUsernameToken.setUserpassword(userpassword);
		}
	}

	public long getLocalTimeOffset() {
		if (mUsernameToken != null) {
			return mUsernameToken.getLocalTimeOffSet();
		}
		return 0;
	}

	public long getUTCTimeOffset() {
		if (mUsernameToken != null) {
			return mUsernameToken.getUTCTimeOffSet();
		}
		return 0;
	}

	public BaseService(String url) {
		mURL = url;
		mEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
		mEnvelope.setAddAdornments(false);
		mEnvelope.dotNet = false;

		Marshal floatMarshal = new MarshalFloat();
		floatMarshal.register(mEnvelope);

		mHttpTransport = new HttpTransportSE(mURL, TIMEOUT);
		mHeaderProperty = new ArrayList<HeaderProperty>();

		addHeader("User-Agent", USER_AGENT);
	}

	public BaseService(String url, int timeout) {
		mURL = url;
		mEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
		mEnvelope.setAddAdornments(false);
		mEnvelope.dotNet = false;

		Marshal floatMarshal = new MarshalFloat();
		floatMarshal.register(mEnvelope);

		mHttpTransport = new HttpTransportSE(mURL, timeout);
		mHeaderProperty = new ArrayList<HeaderProperty>();

		addHeader("User-Agent", USER_AGENT);
	}

	private Element buildAuthHeader() {
		Element security = new Element().createElement(WSSE, "Security");
		security.setPrefix("wsse", WSSE);
		security.setPrefix("wsu", WSU);

		Element usernameToken = new Element().createElement(WSSE, "UsernameToken");
		Element username = new Element().createElement(WSSE, "Username");
		Element password = new Element().createElement(WSSE, "Password");
		Element nonce = new Element().createElement(WSSE, "Nonce");
		Element created = new Element().createElement(WSU, "Created");

		mUsernameToken.calculatePasswordDigest();

		username.addChild(Node.TEXT, mUsernameToken.getUsername());
		password.addChild(Node.TEXT, mUsernameToken.getDigest());
		nonce.addChild(Node.TEXT, mUsernameToken.getNonce());
		created.addChild(Node.TEXT, mUsernameToken.getCreated());

		password.setAttribute(null, "Type", PASSWD_TYPE);
		usernameToken.addChild(Node.ELEMENT, username);
		usernameToken.addChild(Node.ELEMENT, password);
		usernameToken.addChild(Node.ELEMENT, nonce);
		usernameToken.addChild(Node.ELEMENT, created);
		security.addChild(Node.ELEMENT, usernameToken);

		return security;
	}

	protected SoapObject createService(SoapObject obj) throws IOException, XmlPullParserException, SoapFault {
		if (mUsernameToken != null) {
			mEnvelope.headerOut = new Element[1];
			mEnvelope.headerOut[0] = buildAuthHeader();
		}
		mEnvelope.setOutputSoapObject(obj);
		mHttpTransport.call(null, mEnvelope, mHeaderProperty);

		if (mEnvelope.bodyIn instanceof SoapFault)
			throw (SoapFault) mEnvelope.bodyIn;

		Log.i(TAG, "responseDump:" + mHttpTransport.responseDump);
		return (SoapObject) mEnvelope.bodyIn;

	}

	public SoapSerializationEnvelope getEnvelope() {
		return mEnvelope;
	}

	public HttpTransportSE getTransport() {
		return mHttpTransport;
	}

	public void addHeader(String name, String value) {
		mHeaderProperty.add(new HeaderProperty(name, value));
	}

	public static long convertUTCToLocalTime(long utc) {
		long local = utc;
		TimeZone tz = TimeZone.getDefault();
		int offset = tz.getOffset(utc); // The offset includes daylight savings time
		local = utc + offset;
		return local;
	}

	public boolean createAuthHeader(String username, String password) throws IOException, XmlPullParserException, SoapFault, SocketTimeoutException {
		GetSystemDateAndTime request = new GetSystemDateAndTime();
		DeviceService service = new DeviceService(mURL);
		mUsernameToken = null;
		try {
			GetSystemDateAndTimeResponse response = service.getSystemDateAndTime(request);
			if (response.mUTCDateTime != null) {
				mUsernameToken = new WsUsernameToken(username, password, response.mUTCDateTime.getTimeInMillis());
			} else {
				mUsernameToken = new WsUsernameToken(username, password, System.currentTimeMillis());
			}

			if (response.mLocalDateTime != null) {
				mUsernameToken.setLocalTimeOffset(response.mLocalDateTime.getTimeInMillis());
			} else {
				mUsernameToken.setLocalTimeOffset(System.currentTimeMillis());
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();			
			Log.e(TAG, "createAuthHeader error 1 !!!");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "createAuthHeader error 2 !!!");
			mUsernameToken = new WsUsernameToken(username, password, System.currentTimeMillis());
			mUsernameToken.setLocalTimeOffset(System.currentTimeMillis());
		}

		service = null;
		return true;
	}
}
