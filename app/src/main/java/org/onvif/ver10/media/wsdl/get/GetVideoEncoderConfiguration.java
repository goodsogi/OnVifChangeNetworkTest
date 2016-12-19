package org.onvif.ver10.media.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.media.wsdl.MediaService;

public class GetVideoEncoderConfiguration extends SoapObject {
	public static final String METHOD_NAME = "GetVideoEncoderConfiguration";

	public GetVideoEncoderConfiguration() {
		super(MediaService.NAMESPACE, METHOD_NAME);
		this.addAttribute("xmlns", MediaService.NAMESPACE);
	}

	public void setConfigurationToken(String token) {
		addProperty(MediaService.NAMESPACE, "ConfigurationToken", token);
	}
}
