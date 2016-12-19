package org.onvif.ver10.image.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.image.wsdl.ImageService;

public class GetOptions extends SoapObject {
	public static final String METHOD_NAME = GetOptions.class.getSimpleName();

	public GetOptions() {
		super(ImageService.NAMESPACE, METHOD_NAME);
	}

	public void setVideoSourceToken(String videoSourceToken) {
		this.addProperty(ImageService.NAMESPACE, "VideoSourceToken", videoSourceToken);
	}
}
