package org.onvif.ver10.device.wsdl.set;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class SetNetworkDefaultGatewayResponse extends SoapObject {
	private final static String METHOD_NAME = SetNetworkDefaultGatewayResponse.class.getSimpleName();
	public SetNetworkDefaultGatewayResponse() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}
	public SetNetworkDefaultGatewayResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {		
		}
	}
}
