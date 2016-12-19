package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class GetSystemDateAndTime extends SoapObject {
	public static final String METHOD_NAME = GetSystemDateAndTime.class.getSimpleName();
	
	public GetSystemDateAndTime() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}
}
