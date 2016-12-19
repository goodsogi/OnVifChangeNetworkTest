package org.onvif.ver10.ptz.wsdl.set;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.ptz.wsdl.PTZService;

public class RemovePreset extends SoapObject {
	public static final String METHOD_NAME = RemovePreset.class.getSimpleName();

	public RemovePreset() {
		super(PTZService.NAMESPACE, METHOD_NAME);
	}

	public void setProfileToken(String profileToken) {
		this.addProperty(PTZService.NAMESPACE, "ProfileToken", profileToken);
	}

	public void setPresetToken(String presetToken) {
		if (presetToken != null && !presetToken.equals(""))
			this.addProperty(PTZService.NAMESPACE, "PresetToken", presetToken);
	}
}
