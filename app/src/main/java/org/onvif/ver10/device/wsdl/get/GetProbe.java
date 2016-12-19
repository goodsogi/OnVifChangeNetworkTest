package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public class GetProbe extends SoapObject {
	public static final String METHOD_NAME = GetProbe.class.getSimpleName();

	public String mIPv4Address;

	public GetProbe() {
		super(null, METHOD_NAME);
	}

	public GetProbe(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// IPv4Address
				// =================================================================================
				if (root.getName().equalsIgnoreCase("mIPv4Address")) {
					mIPv4Address = root.getValue().toString();
				}
			}
		}
	}

	public void setIPAddress(String IPv4Address) {
		if (IPv4Address != null)
			addProperty("IPv4Address", IPv4Address);
	}
}
