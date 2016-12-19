package org.onvif.ver10.device.wsdl.set;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class SetNetworkInterfacesResponse extends SoapObject {
	private final static String METHOD_NAME = SetNetworkInterfacesResponse.class.getSimpleName();

	public boolean mRebootNeeded;

	public SetNetworkInterfacesResponse() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}

	public SetNetworkInterfacesResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			try {
				mRebootNeeded = Boolean.parseBoolean(obj.getPropertyAsString("RebootNeeded"));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void setRebootNeeded(boolean RebootNeeded) {
		addProperty(DeviceService.NAMESPACE, "RebootNeeded", RebootNeeded);
	}

}
