package org.onvif.ver10.device.wsdl.set;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class SetSystemDateAndTimeResponse extends SoapObject {
	private final static String METHOD_NAME = SetSystemDateAndTimeResponse.class.getSimpleName();

	public SetSystemDateAndTimeResponse() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}

	public SetSystemDateAndTimeResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			// not used
		}
	}

}
