package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class GetUsers extends SoapObject {
	public static final String METHOD_NAME = "GetUsers";
	
	public GetUsers() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}
}
