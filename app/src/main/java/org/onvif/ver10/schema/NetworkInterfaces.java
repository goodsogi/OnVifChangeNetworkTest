package org.onvif.ver10.schema;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.List;

public class NetworkInterfaces {

	private SoapObject mSoapObjectNetworkInterfaces;

	public NetworkInterfaces() {
		mSoapObjectNetworkInterfaces = new SoapObject(DeviceService.NAMESPACE, NetworkInterfaces.class.getSimpleName());
	}

	public SoapObject getSoapObject() {
		return mSoapObjectNetworkInterfaces;
	}

	public void setNetworkInterfacesToken(String token) {
		mSoapObjectNetworkInterfaces.addAttribute("token", token);
	}

	public void setNetworkInterfacesEnable(boolean Enabled) {
		mSoapObjectNetworkInterfaces.addProperty(DeviceService.SCHEMA, "Enabled", Enabled);
	}

	public void setNetworkInterfaceInfo(String Name, String HwAddress) {
		SoapObject Info = new SoapObject(DeviceService.SCHEMA, "Info");
		Info.addProperty(DeviceService.SCHEMA, "Name", Name);
		Info.addProperty(DeviceService.SCHEMA, "HwAddress", HwAddress);
		mSoapObjectNetworkInterfaces.addSoapObject(Info);
	}

	public void setNetworkInterfaceIPv4(boolean Enabled, boolean DHCP, String Address) {
		SoapObject IPv4 = new SoapObject(DeviceService.SCHEMA, "IPv4");
		IPv4.addProperty(DeviceService.SCHEMA, "Enabled", Enabled);
		SoapObject Config = new SoapObject(DeviceService.SCHEMA, "Config");

		SoapObject FromDHCP_Manual = new SoapObject(DeviceService.SCHEMA, DHCP ? "FromDHCP" : "Manual");
		FromDHCP_Manual.addProperty(DeviceService.SCHEMA, "Address", Address);

		int PrefixLength = 32;
		try {
			NetworkInterface networkInterface = NetworkInterface.getByName("eth0");
			List<InterfaceAddress> address = networkInterface.getInterfaceAddresses();
			for (InterfaceAddress interfaceAddress : address) {
				if (interfaceAddress.getAddress() instanceof Inet4Address) {
					PrefixLength = interfaceAddress.getNetworkPrefixLength();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		FromDHCP_Manual.addProperty(DeviceService.SCHEMA, "PrefixLength", PrefixLength);
		Config.addSoapObject(FromDHCP_Manual);
		Config.addProperty(DeviceService.SCHEMA, "DHCP", DHCP);
		IPv4.addSoapObject(Config);
		mSoapObjectNetworkInterfaces.addSoapObject(IPv4);
	}
}
