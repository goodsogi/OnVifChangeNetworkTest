package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class GetDNSResponse extends SoapObject {
	private final static String METHOD_NAME = GetDNSResponse.class.getSimpleName();

	public boolean mFromDHCP;
	public String mType; // enum { 'IPv4', 'IPv6' }
	public String mIPv4Address1; // DNS 1
	public String mIPv4Address2; // DNS 2

	public GetDNSResponse() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}

	public GetDNSResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// DNSInformation
				// =================================================================================
				if (root.getName().equalsIgnoreCase("DNSInformation")) {
					SoapObject DNSInformation = (SoapObject) root.getValue();
					for (int j = 0; j < DNSInformation.getPropertyCount(); j++) {
						PropertyInfo subSoapObject = new PropertyInfo();
						DNSInformation.getPropertyInfo(j, subSoapObject);

						// =================================================================================
						// FromDHCP
						// =================================================================================
						if (subSoapObject.getName().equalsIgnoreCase("FromDHCP")) {
							mFromDHCP = Boolean.parseBoolean(subSoapObject.getValue().toString());
						}
						// =================================================================================
						// DNSFromDHCP
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("DNSFromDHCP")) {
							SoapObject DNSFromDHCP = (SoapObject) subSoapObject.getValue();
							for (int count = 0; count < DNSFromDHCP.getPropertyCount(); count++) {
								PropertyInfo dnsfromdhcp = new PropertyInfo();
								DNSFromDHCP.getPropertyInfo(count, dnsfromdhcp);
								// =================================================================================
								// DNSFromDHCP - Type
								// =================================================================================
								if (dnsfromdhcp.getName().equalsIgnoreCase("Type")) {
									if (mType == null)
										mType = dnsfromdhcp.getValue().toString();
								}
								// =================================================================================
								// DNSFromDHCP - Type
								// =================================================================================
								else if (dnsfromdhcp.getName().equalsIgnoreCase("IPv4Address")) {
									if (mIPv4Address1 == null) {
										mIPv4Address1 = dnsfromdhcp.getValue().toString();
									} else {
										mIPv4Address2 = dnsfromdhcp.getValue().toString();
									}
								}
							}
						}
						// =================================================================================
						// DNSFromDHCP
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("DNSManual")) {
							SoapObject DNSManual = (SoapObject) subSoapObject.getValue();
							for (int count = 0; count < DNSManual.getPropertyCount(); count++) {
								PropertyInfo dnsmanual = new PropertyInfo();
								DNSManual.getPropertyInfo(count, dnsmanual);
								// =================================================================================
								// DNSFromDHCP - Type
								// =================================================================================
								if (dnsmanual.getName().equalsIgnoreCase("Type")) {
									if (mType == null)
										mType = dnsmanual.getValue().toString();
								}
								// =================================================================================
								// DNSFromDHCP - Type
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
		}
	}

	public void setFromDHCP(boolean FromDHCP, String dns1, String dns2) {
		SoapObject mSoapObjectRoot = new SoapObject(DeviceService.NAMESPACE, "DNSInformation");

		mSoapObjectRoot.addProperty(DeviceService.SCHEMA, "FromDHCP", FromDHCP);

		if (dns1 != null) {
			SoapObject DNSFromDHCP_Manual = new SoapObject(DeviceService.SCHEMA, FromDHCP ? "DNSFromDHCP" : "DNSManual");
			DNSFromDHCP_Manual.addProperty(DeviceService.SCHEMA, "Type", "IPv4");
			DNSFromDHCP_Manual.addProperty(DeviceService.SCHEMA, "IPv4Address", dns1);
			mSoapObjectRoot.addSoapObject(DNSFromDHCP_Manual);
		}

		if (dns2 != null) {
			SoapObject DNSFromDHCP_Manual = new SoapObject(DeviceService.SCHEMA, FromDHCP ? "DNSFromDHCP" : "DNSManual");
			DNSFromDHCP_Manual.addProperty(DeviceService.SCHEMA, "Type", "IPv4");
			DNSFromDHCP_Manual.addProperty(DeviceService.SCHEMA, "IPv4Address", dns2);
			mSoapObjectRoot.addSoapObject(DNSFromDHCP_Manual);
		}

		addSoapObject(mSoapObjectRoot);
	}

}
