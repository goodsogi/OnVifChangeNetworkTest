package org.onvif.ver10.media.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.media.wsdl.MediaService;

public class GetSnapshotUri extends SoapObject {
	public static final String METHOD_NAME = GetSnapshotUri.class.getSimpleName();

	public GetSnapshotUri() {
		super(MediaService.NAMESPACE, METHOD_NAME);
	}

	public void setProfileToken(String ProfileToken) {
		this.addProperty(MediaService.NAMESPACE, "ProfileToken", ProfileToken);
	}

}
