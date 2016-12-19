package org.onvif.ver10.media.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.media.wsdl.MediaService;

public class GetProfiles extends SoapObject {
	public static final String METHOD_NAME = GetProfiles.class.getSimpleName();

	public GetProfiles() {
		super(MediaService.NAMESPACE, METHOD_NAME);
	}
}
