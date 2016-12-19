package org.onvif.ver10.device.wsdl.set;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.util.List;

public class SetNetworkInterfaces {
	public static final String METHOD_NAME = SetNetworkInterfaces.class.getSimpleName();

	private SoapObject mSoapObject;
	private SoapObject mSoapNetworkInterface;
	private SoapObject mSoapIPv4;

	public String InterfaceToken;
	public boolean Enabled;
	public boolean IPv4Enabled;
	public boolean IPv4DHCP;
	public String IPv4Adderss;
	public int IPv4PrefixLength;

	public boolean isInterfaceToken;
	public boolean isEnabled;
	public boolean isIPv4Enabled;
	public boolean isIPv4DHCP;
	public boolean isIPv4Adderss;
	public boolean isIPv4PrefixLength;

	public SetNetworkInterfaces() {
		mSoapObject = new SoapObject(DeviceService.NAMESPACE, METHOD_NAME);
		mSoapNetworkInterface = new SoapObject(DeviceService.NAMESPACE, "NetworkInterface");
	}

	public SetNetworkInterfaces(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// InterfaceToken
				// =================================================================================
				if (root.getName().equalsIgnoreCase("InterfaceToken")) {
					InterfaceToken = root.getValue().toString();
					isInterfaceToken = true;
				}
				// =================================================================================
				// NetworkInterface
				// =================================================================================
				else if (root.getName().equalsIgnoreCase("NetworkInterface")) {
					SoapObject NetworkInterfaces = (SoapObject) root.getValue();
					for (int j = 0; j < NetworkInterfaces.getPropertyCount(); j++) {
						PropertyInfo subSoapObject = new PropertyInfo();
						NetworkInterfaces.getPropertyInfo(j, subSoapObject);
						// =================================================================================
						// NetworkInterface - Enabled
						// =================================================================================
						if (subSoapObject.getName().equalsIgnoreCase("Enabled")) {
							Enabled = Boolean.parseBoolean(subSoapObject.getValue().toString());
							isEnabled = true;
						}
						// =================================================================================
						// NetworkInterface - IPv4
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("IPv4")) {
							SoapObject IPv4 = (SoapObject) subSoapObject.getValue();
							for (int ipv4Count = 0; ipv4Count < IPv4.getPropertyCount(); ipv4Count++) {
								PropertyInfo ipv4 = new PropertyInfo();
								IPv4.getPropertyInfo(ipv4Count, ipv4);
								// =================================================================================
								// NetworkInterface - IPv4 - Enabled
								// =================================================================================
								if (ipv4.getName().equalsIgnoreCase("Enabled")) {
									IPv4Enabled = Boolean.parseBoolean(ipv4.getValue().toString());
									isIPv4Enabled = true;
								}
								// =================================================================================
								// NetworkInterface - IPv4 - DHCP
								// =================================================================================
								else if (ipv4.getName().equalsIgnoreCase("DHCP")) {
									IPv4DHCP = Boolean.parseBoolean(ipv4.getValue().toString());
									isIPv4DHCP = true;
								}
								// =================================================================================
								// NetworkInterface - IPv4 - Manual
								// =================================================================================
								else if (ipv4.getName().equalsIgnoreCase("Manual")) {
									if (((SoapObject) ipv4.getValue()).hasProperty("Address")) {
										IPv4Adderss = ((SoapObject) ipv4.getValue()).getPropertyAsString("Address");
										isIPv4Adderss = true;
									}
									if (((SoapObject) ipv4.getValue()).hasProperty("PrefixLength")) {
										IPv4PrefixLength = Integer.parseInt(((SoapObject) ipv4.getValue())
												.getPropertyAsString("PrefixLength"));
										isIPv4PrefixLength = true;
									}

								}
							}

						}
					}
				}

				try {

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	public SoapObject getSoapObject() {
		if (mSoapIPv4 != null) {
			mSoapNetworkInterface.addSoapObject(mSoapIPv4);
		}
		mSoapObject.addSoapObject(mSoapNetworkInterface);
		return mSoapObject;
	}

	public void setInterfaceToken(String token) {
		InterfaceToken = token;
		mSoapObject.addProperty(DeviceService.NAMESPACE, "InterfaceToken", token);
	}

	public void setNetworkInterfacesEnabled(boolean Enabled) {
		mSoapNetworkInterface.addProperty(DeviceService.SCHEMA, "Enabled", Enabled);
	}

	public void setIPv4DHCP(boolean DHCP) {
		if (mSoapIPv4 == null) {
			mSoapIPv4 = new SoapObject(DeviceService.SCHEMA, "IPv4");
		}
		mSoapIPv4.addProperty(DeviceService.SCHEMA, "DHCP", DHCP);
	}

	public void setIPv4ManualAddress(String Address) {
		if (mSoapIPv4 == null) {
			mSoapIPv4 = new SoapObject(DeviceService.SCHEMA, "IPv4");
		}
		if (Address != null) {
			// Manual
			SoapObject Manual = new SoapObject(DeviceService.SCHEMA, "Manual");
			Manual.addProperty(DeviceService.SCHEMA, "Address", Address);
			int prefixlength = 32;
			try {
				java.net.NetworkInterface networkInterface = java.net.NetworkInterface.getByName(InterfaceToken);
				List<InterfaceAddress> address = networkInterface.getInterfaceAddresses();
				for (InterfaceAddress interfaceAddress : address) {
					if (interfaceAddress.getAddress() instanceof Inet4Address) {
						prefixlength = interfaceAddress.getNetworkPrefixLength();
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Manual.addProperty(DeviceService.SCHEMA, "PrefixLength", prefixlength);
			mSoapIPv4.addSoapObject(Manual);
		}
	}
	public void setIPv4ManualAddress(String Address, int prefix) {
		if (mSoapIPv4 == null) {
			mSoapIPv4 = new SoapObject(DeviceService.SCHEMA, "IPv4");
		}
		if (Address != null) {
			// Manual
			SoapObject Manual = new SoapObject(DeviceService.SCHEMA, "Manual");
			Manual.addProperty(DeviceService.SCHEMA, "Address", Address);			
			Manual.addProperty(DeviceService.SCHEMA, "PrefixLength", prefix);
			mSoapIPv4.addSoapObject(Manual);
		}
	}	
}
