package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.onvif.ver10.device.wsdl.DeviceService;
import org.onvif.ver10.schema.NetworkInterfaces;

public class GetNetworkInterfacesResponse extends SoapObject {
	private final static String METHOD_NAME = GetNetworkInterfacesResponse.class.getSimpleName();

	public String mNetworkInterfacesToken;
	public boolean mNetworkInterfacesEnabled;
	public String mNetworkInterfacesInfoName;
	public String mNetworkInterfacesInfoHwAddress;
	public boolean mNetworkInterfacesIPv4Enabled;
	public boolean mNetworkInterfacesIPv4ConfigDHCP;
	public String mNetworkInterfacesIPv4ConfigAddress;
	public int mNetworkInterfacesIPv4ConfigPrefixLength;

	public GetNetworkInterfacesResponse() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}

	public GetNetworkInterfacesResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {

			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// NetworkInterfaces
				// =================================================================================
				if (root.getName().equalsIgnoreCase("NetworkInterfaces")) {
					SoapObject NetworkInterfaces = (SoapObject) root.getValue();
					mNetworkInterfacesToken = NetworkInterfaces.getAttributeAsString("token");

					if (!mNetworkInterfacesToken.equalsIgnoreCase("eth0")) {
						continue;
					}

					for (int j = 0; j < NetworkInterfaces.getPropertyCount(); j++) {
						PropertyInfo subSoapObject = new PropertyInfo();
						NetworkInterfaces.getPropertyInfo(j, subSoapObject);

						if (subSoapObject.getValue() instanceof SoapPrimitive) {
							// =================================================================================
							// Enabled
							// =================================================================================
							if (subSoapObject.getName().equalsIgnoreCase("Enabled")) {
								try {
									mNetworkInterfacesEnabled = Boolean.parseBoolean(subSoapObject.getValue()
											.toString());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else if (subSoapObject.getValue() instanceof SoapObject) {
							// =================================================================================
							// Info
							// =================================================================================
							if (subSoapObject.getName().equalsIgnoreCase("Info")) {
								SoapObject Info = (SoapObject) subSoapObject.getValue();
								for (int infoCount = 0; infoCount < Info.getPropertyCount(); infoCount++) {
									PropertyInfo info = new PropertyInfo();
									Info.getPropertyInfo(infoCount, info);
									// =================================================================================
									// Info - Name
									// =================================================================================
									if (info.getName().equalsIgnoreCase("Name")) {
										mNetworkInterfacesInfoName = info.getValue().toString();
									}
									// =================================================================================
									// Info - HwAddress
									// =================================================================================
									else if (info.getName().equalsIgnoreCase("HwAddress")) {
										try {
											mNetworkInterfacesInfoHwAddress = info.getValue().toString();
										} catch (Exception e) {
											mNetworkInterfacesInfoHwAddress = "00:00:00:00:00:00";
										}
									}
								}
							}
							// =================================================================================
							// IPv4
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("IPv4")) {
								SoapObject IPv4 = (SoapObject) subSoapObject.getValue();
								for (int ipv4Count = 0; ipv4Count < IPv4.getPropertyCount(); ipv4Count++) {
									PropertyInfo ipv4 = new PropertyInfo();
									IPv4.getPropertyInfo(ipv4Count, ipv4);
									// =================================================================================
									// IPv4 - Enabled
									// =================================================================================
									if (ipv4.getName().equalsIgnoreCase("Enabled")) {
										mNetworkInterfacesIPv4Enabled = Boolean
												.parseBoolean(ipv4.getValue().toString());
									}
									// =================================================================================
									// IPv4 - Config
									// =================================================================================
									else if (ipv4.getName().equalsIgnoreCase("Config")) {
										SoapObject Config = (SoapObject) ipv4.getValue();
										for (int configCount = 0; configCount < Config.getPropertyCount(); configCount++) {
											PropertyInfo config = new PropertyInfo();
											Config.getPropertyInfo(configCount, config);
											// =================================================================================
											// IPv4 - Config - DHCP
											// =================================================================================
											if (config.getName().equalsIgnoreCase("DHCP")) {
												mNetworkInterfacesIPv4ConfigDHCP = Boolean.parseBoolean(config
														.getValue().toString());
											}
											// =================================================================================
											// IPv4 - Config - FromDHCP
											// =================================================================================
											else if (config.getName().equalsIgnoreCase("FromDHCP")) {
												mNetworkInterfacesIPv4ConfigAddress = ((SoapObject) config.getValue())
														.getPropertyAsString("Address");
												mNetworkInterfacesIPv4ConfigPrefixLength = Integer
														.parseInt(((SoapObject) config.getValue())
																.getPropertyAsString("PrefixLength"));
											}
											// =================================================================================
											// IPv4 - Config - Manual
											// =================================================================================
											else if (config.getName().equalsIgnoreCase("Manual")) {
												mNetworkInterfacesIPv4ConfigAddress = ((SoapObject) config.getValue())
														.getPropertyAsString("Address");
												mNetworkInterfacesIPv4ConfigPrefixLength = Integer
														.parseInt(((SoapObject) config.getValue())
																.getPropertyAsString("PrefixLength"));
											}
										}

									}
								}
							}
						}
					}
				}
			}
		}
	}

	public void addNetworkInterfaces(NetworkInterfaces Interface) {
		addSoapObject(Interface.getSoapObject());
	}

}
