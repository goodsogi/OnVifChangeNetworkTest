package org.onvif.ver10.device.wsdl;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.get.GetCapabilities;
import org.onvif.ver10.device.wsdl.get.GetCapabilitiesResponse;
import org.onvif.ver10.device.wsdl.get.GetDeviceInformation;
import org.onvif.ver10.device.wsdl.get.GetDeviceInformationResponse;
import org.onvif.ver10.device.wsdl.get.GetSystemDateAndTime;
import org.onvif.ver10.device.wsdl.get.GetSystemDateAndTimeResponse;
import org.onvif.ver10.device.wsdl.get.GetUsers;
import org.onvif.ver10.device.wsdl.get.GetUsersResponse;
import org.onvif.ver10.device.wsdl.set.SetNetworkInterfaces;
import org.onvif.ver10.device.wsdl.set.SetNetworkInterfacesResponse;
import org.onvif.ver10.schema.BaseService;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class DeviceService extends BaseService {
	public static final String NAMESPACE = "http://www.onvif.org/ver10/device/wsdl";
	public static final String SCHEMA = "http://www.onvif.org/ver10/schema";

	public DeviceService(String url) {
		super(url);
		mEnvelope.addPrefix("tds", NAMESPACE);
		mEnvelope.addPrefix("tt", SCHEMA);
	}

	public DeviceService(String url, int timeout) {
		super(url, timeout);
		mEnvelope.addPrefix("tds", NAMESPACE);
		mEnvelope.addPrefix("tt", SCHEMA);
	}

	/********************************************************************************************************/
	public GetSystemDateAndTimeResponse getSystemDateAndTime(GetSystemDateAndTime request) throws IOException,
			XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetSystemDateAndTimeResponse(obj);
	}

	public GetDeviceInformationResponse getDeviceInformation(GetDeviceInformation request) throws IOException,
			XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetDeviceInformationResponse(obj);
	}

	public GetUsersResponse getUsers(GetUsers request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetUsersResponse(obj);
	}

	public GetCapabilitiesResponse getCapabilities(GetCapabilities request) throws IOException, XmlPullParserException,
			SoapFault {
		SoapObject obj = createService(request);
		return new GetCapabilitiesResponse(obj);
	}

	public SetNetworkInterfacesResponse setNetworkInterface(SetNetworkInterfaces request) throws IOException, XmlPullParserException,
	SoapFault{
		SoapObject obj = createService(request.getSoapObject());
		return new SetNetworkInterfacesResponse(obj);
	}
}
