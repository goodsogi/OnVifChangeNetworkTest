package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

public class GetNetworkDefaultGatewayResponse extends SoapObject {
	private final static String METHOD_NAME = GetNetworkDefaultGatewayResponse.class.getSimpleName();

	public String mIPv4Address;

	public GetNetworkDefaultGatewayResponse() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}

	public GetNetworkDefaultGatewayResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {

			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// NetworkGateway
				// =================================================================================
				if (root.getName().equalsIgnoreCase("NetworkGateway")) {
					SoapObject NetworkGateway = (SoapObject) root.getValue();
					for (int j = 0; j < NetworkGateway.getPropertyCount(); j++) {
						PropertyInfo subSoapObject = new PropertyInfo();
						NetworkGateway.getPropertyInfo(j, subSoapObject);
						// =================================================================================
						// IPv4Address
						// =================================================================================
						if (subSoapObject.getName().equalsIgnoreCase("IPv4Address")) {
							try{
							mIPv4Address = subSoapObject.getValue().toString();
							}catch(Exception e){
								mIPv4Address="0.0.0.0";
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	public void setNetworkDefaultGateway(String IPv4Address) {
		SoapObject NetworkGateway = new SoapObject(DeviceService.NAMESPACE,"NetworkGateway");
		NetworkGateway.addProperty(DeviceService.SCHEMA,"IPv4Address",IPv4Address);
		addSoapObject(NetworkGateway);
	}
}
