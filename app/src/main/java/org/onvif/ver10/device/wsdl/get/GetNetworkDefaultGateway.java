package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class GetNetworkDefaultGateway extends SoapObject {
	public static final String METHOD_NAME = GetNetworkDefaultGateway.class.getSimpleName();
	
	public GetNetworkDefaultGateway() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}
}
