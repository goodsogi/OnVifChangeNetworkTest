package org.onvif.ver10.media.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.media.wsdl.MediaService;

public class GetVideoEncoderConfigurationOptions extends SoapObject {
	public static final String METHOD_NAME = GetVideoEncoderConfigurationOptions.class.getSimpleName();

	public GetVideoEncoderConfigurationOptions() {
		super(MediaService.NAMESPACE, METHOD_NAME);
		this.addAttribute("xmlns", MediaService.NAMESPACE);
	}

	public void setProfileToken(String token) {
		addProperty(MediaService.NAMESPACE, "ProfileToken", token);
	}

	public void setConfigurationToken(String token) {
		addProperty(MediaService.NAMESPACE, "ConfigurationToken", token);
	}
}
