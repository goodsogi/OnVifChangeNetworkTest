package org.onvif.ver10.device.wsdl.set;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class SetNetworkDefaultGateway {
	public static final String METHOD_NAME = SetNetworkDefaultGateway.class.getSimpleName();

	private SoapObject mSoapObject;
	public String mIPv4Address;

	public SetNetworkDefaultGateway() {
	}

	public SetNetworkDefaultGateway(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// IPv4Address
				// =================================================================================
				if (root.getName().equalsIgnoreCase("IPv4Address")) {
					mIPv4Address = root.getValue().toString();
				}
			}
		}
	}

	public SoapObject getSoapObject() {
		return mSoapObject;
	}

	public void setNetworkDefaultGateway(String IPv4Address) {
		mSoapObject = new SoapObject(DeviceService.NAMESPACE, METHOD_NAME);
		mSoapObject.addProperty(DeviceService.SCHEMA, "IPv4Address", IPv4Address);
	}
}
