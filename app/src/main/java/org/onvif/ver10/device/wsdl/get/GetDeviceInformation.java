package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class GetDeviceInformation extends SoapObject {
	public static final String METHOD_NAME = GetDeviceInformation.class.getSimpleName();
	
	public GetDeviceInformation() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}
}
