package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class GetDNS extends SoapObject {
	public static final String METHOD_NAME = GetDNS.class.getSimpleName();
	
	public GetDNS() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}
}
