package org.onvif.ver10.ptz.wsdl.get;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.ptz.wsdl.PTZService;

public class GetNode extends SoapObject {
	public static final String METHOD_NAME = GetNode.class.getSimpleName();

	public GetNode() {
		super(PTZService.NAMESPACE, METHOD_NAME);
	}

	public void setNodeToken(String nodeToken) {
		this.addProperty(PTZService.NAMESPACE, "NodeToken", nodeToken);
	}
}
