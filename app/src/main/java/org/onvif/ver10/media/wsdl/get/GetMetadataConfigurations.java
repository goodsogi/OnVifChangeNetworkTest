package org.onvif.ver10.media.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.media.wsdl.MediaService;

public class GetMetadataConfigurations extends SoapObject {
	public static final String METHOD_NAME = "GetMetadataConfigurations";


	public GetMetadataConfigurations() {
		super(MediaService.NAMESPACE, METHOD_NAME);

	}
}
