package org.onvif.ver10.media.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.media.wsdl.MediaService;
import org.onvif.ver10.schema.StreamSetup;

public class GetStreamUri extends SoapObject {
	public static final String METHOD_NAME = GetStreamUri.class.getSimpleName();

	public GetStreamUri() {
		super(MediaService.NAMESPACE, METHOD_NAME);
	}

	public void setStreamSetup(StreamSetup streamSetup, String ProfileToken) {
		// StreamSetup
		SoapObject streamsetup = new SoapObject(MediaService.NAMESPACE, "StreamSetup");

		// StreamSetup - Stream
		streamsetup.addProperty(MediaService.SCHEMA, "Stream", streamSetup.getStreamType());

		// StreamSetup - Transport
		SoapObject transport = new SoapObject(MediaService.SCHEMA, "Transport");

		// StreamSetup - Transport - Protocol
		transport.addProperty(MediaService.SCHEMA, "Protocol", streamSetup.mTransport.getTransportProtocol());

		streamsetup.addSoapObject(transport);
		this.addSoapObject(streamsetup);

		// ProfileToken
		this.addProperty(MediaService.NAMESPACE, "ProfileToken", ProfileToken);

	}

}
