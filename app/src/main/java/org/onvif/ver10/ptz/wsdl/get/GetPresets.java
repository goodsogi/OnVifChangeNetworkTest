package org.onvif.ver10.ptz.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.ptz.wsdl.PTZService;

public class GetPresets extends SoapObject {
	public static final String METHOD_NAME = GetPresets.class.getSimpleName();

	public GetPresets() {
		super(PTZService.NAMESPACE, METHOD_NAME);
	}

	public void setProfileToken(String profileToken) {
		this.addProperty(PTZService.NAMESPACE, "ProfileToken", profileToken);
	}
}
