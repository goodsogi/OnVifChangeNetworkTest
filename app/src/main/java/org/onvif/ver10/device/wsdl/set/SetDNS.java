package org.onvif.ver10.device.wsdl.set;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class SetDNS {
	public static final String METHOD_NAME = SetDNS.class.getSimpleName();

	private SoapObject mSoapObject;

	public boolean mFromDHCP;
	public String mType; // enum { 'IPv4', 'IPv6' }
	public String mIPv4Address1; // DNS 1
	public String mIPv4Address2; // DNS 2

	public SetDNS() {

	}

	public SetDNS(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// FromDHCP
				// =================================================================================
				if (root.getName().equalsIgnoreCase("mFromDHCP")) {
					mFromDHCP = Boolean.parseBoolean(root.getValue().toString());
				}
				// =================================================================================
				// DNSManual
				// =================================================================================
				else if (root.getName().equalsIgnoreCase("DNSManual")) {
					SoapObject DNSManual = (SoapObject) root.getValue();
					for (int j = 0; j < DNSManual.getPropertyCount(); j++) {
						PropertyInfo dnsmanual = new PropertyInfo();
						DNSManual.getPropertyInfo(j, dnsmanual);
						// =================================================================================
						// DNSManual - Type
						// =================================================================================
						if (dnsmanual.getName().equalsIgnoreCase("Type")) {
							if (mType == null)
								mType = dnsmanual.getValue().toString();
						}
						// =================================================================================
						// DNSManual - IPv4Address
						// =================================================================================
						else if (dnsmanual.getName().equalsIgnoreCase("IPv4Address")) {
							if (mIPv4Address1 == null) {
								mIPv4Address1 = dnsmanual.getValue().toString();
							} else {
								mIPv4Address2 = dnsmanual.getValue().toString();
							}
						}
					}
				}
			}
		}
	}

	public SoapObject getSoapObject() {
		return mSoapObject;
	}

	public void setFromDHCP(boolean FromDHCP, String dns1, String dns2) {
		mSoapObject = new SoapObject(DeviceService.NAMESPACE, METHOD_NAME);
		mSoapObject.addProperty(DeviceService.SCHEMA, "FromDHCP", FromDHCP);

		if (dns1 != null) {
			SoapObject DNSFromDHCP_Manual = new SoapObject(DeviceService.SCHEMA, FromDHCP ? "DNSFromDHCP" : "DNSManual");
			DNSFromDHCP_Manual.addProperty(DeviceService.SCHEMA, "Type", "IPv4");
			DNSFromDHCP_Manual.addProperty(DeviceService.SCHEMA, "IPv4Address", dns1);
			mSoapObject.addSoapObject(DNSFromDHCP_Manual);
		}

		if (dns2 != null) {
			SoapObject DNSFromDHCP_Manual = new SoapObject(DeviceService.SCHEMA, FromDHCP ? "DNSFromDHCP" : "DNSManual");
			DNSFromDHCP_Manual.addProperty(DeviceService.SCHEMA, "Type", "IPv4");
			DNSFromDHCP_Manual.addProperty(DeviceService.SCHEMA, "IPv4Address", dns2);
			mSoapObject.addSoapObject(DNSFromDHCP_Manual);
		}
	}

}
