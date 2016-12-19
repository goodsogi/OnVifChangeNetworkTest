package org.onvif.ver10.ptz.wsdl.set;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.ptz.wsdl.PTZService;
import org.onvif.ver10.schema.nativeParcel.PTZSpeed;

public class GotoHomePosition extends SoapObject {
	public static final String METHOD_NAME = GotoHomePosition.class.getSimpleName();

	public GotoHomePosition() {
		super(PTZService.NAMESPACE, METHOD_NAME);
	}

	public void setProfileToken(String profileToken) {
		this.addProperty(PTZService.NAMESPACE, "ProfileToken", profileToken);
	}

	public void setPTZSpeed(PTZSpeed ptzSpeed) {
		if (ptzSpeed != null) {
			// ===================================================================
			// Speed
			// ===================================================================
			SoapObject Speed = new SoapObject(PTZService.NAMESPACE, "Speed");

			// ===================================================================
			// Speed - PanTilt
			// ===================================================================
			if (ptzSpeed.mPanTilt != null) {
				SoapObject PanTilt = new SoapObject(PTZService.SCHEMA, "PanTilt");
				PanTilt.addAttribute("x", ptzSpeed.mPanTilt.mX);
				PanTilt.addAttribute("y", ptzSpeed.mPanTilt.mY);
				if (ptzSpeed.mPanTilt.mSpace != null && !ptzSpeed.mPanTilt.mSpace.equals("")) {
					PanTilt.addAttribute("space", ptzSpeed.mPanTilt.mSpace);
				}
				Speed.addSoapObject(PanTilt);
			}

			// ===================================================================
			// Speed - Zoom
			// ===================================================================
			if (ptzSpeed.mZoom != null) {
				SoapObject Zoom = new SoapObject(PTZService.SCHEMA, "Zoom");
				Zoom.addAttribute("x", ptzSpeed.mZoom.mX);
				if (ptzSpeed.mZoom.mSpace != null && !ptzSpeed.mZoom.mSpace.equals("")) {
					Zoom.addAttribute("space", ptzSpeed.mZoom.mSpace);
				}
				Speed.addSoapObject(Zoom);
			}
			addSoapObject(Speed);
		}
	}
}
