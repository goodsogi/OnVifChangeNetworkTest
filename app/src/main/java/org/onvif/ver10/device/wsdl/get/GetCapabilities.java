package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class GetCapabilities extends SoapObject {
	public static final String METHOD_NAME = GetCapabilities.class.getSimpleName();

	public GetCapabilities() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}
}
