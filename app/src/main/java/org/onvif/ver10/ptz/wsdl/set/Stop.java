package org.onvif.ver10.ptz.wsdl.set;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.ptz.wsdl.PTZService;

public class Stop extends SoapObject {
	public static final String METHOD_NAME = Stop.class.getSimpleName();

	public Stop() {
		super(PTZService.NAMESPACE, METHOD_NAME);
	}

	public void setProfileToken(String profileToken) {
		this.addProperty(PTZService.NAMESPACE, "ProfileToken", profileToken);
	}

	public void setPanTilt(boolean stop) {
		this.addProperty(PTZService.NAMESPACE, "PanTilt", stop);
	}

	public void setZoom(boolean stop) {
		this.addProperty(PTZService.NAMESPACE, "Zoom", stop);
	}
}
