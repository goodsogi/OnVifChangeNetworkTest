package org.onvif.ver10.device.wsdl.set;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class SetDNSResponse extends SoapObject {
	private final static String METHOD_NAME = SetDNSResponse.class.getSimpleName();

	public SetDNSResponse() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}

	public SetDNSResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			// not used
		}
	}

}
