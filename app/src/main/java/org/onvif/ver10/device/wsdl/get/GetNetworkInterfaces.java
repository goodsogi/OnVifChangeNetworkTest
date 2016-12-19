package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class GetNetworkInterfaces extends SoapObject {
	public static final String METHOD_NAME = GetNetworkInterfaces.class.getSimpleName();
	
	public GetNetworkInterfaces() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}
}
