package org.onvif.ver10.image.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.image.wsdl.ImageService;

public class GetImagingSettings extends SoapObject {
	public static final String METHOD_NAME = GetImagingSettings.class.getSimpleName();

	public GetImagingSettings() {
		super(ImageService.NAMESPACE, METHOD_NAME);
	}

	public void setVideoSourceToken(String videoSourceToken) {
		this.addProperty(ImageService.NAMESPACE, "VideoSourceToken", videoSourceToken);
	}
}
