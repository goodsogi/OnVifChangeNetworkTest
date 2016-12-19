package org.onvif.ver10.media.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public class GetMetadataConfigurationsResponse {
	private final static String TAG = "GetMetadataConfigurationsResponse";
	private String mErrorMSg;
	private String m_iMetadataTokenValue;

	public GetMetadataConfigurationsResponse(SoapObject obj) {
		if (!obj.getName().equals("AgentErrorMsg")) {
		for (int i = 0; i < obj.getPropertyCount(); i++) {
			PropertyInfo info = new PropertyInfo();
			obj.getPropertyInfo(i, info);

			if (!info.getName().equalsIgnoreCase("Configurations"))
				continue;
			try {
				m_iMetadataTokenValue = (((SoapObject) info.getValue()).getAttributeAsString("token"));
			} catch (Exception e) {
				e.printStackTrace();
				m_iMetadataTokenValue = null;
			}
			
		}} else {
			mErrorMSg = obj.toString();
		}
	}
	public String getErrorMsg() {
		return mErrorMSg;
	}
	public String getTokenValue() {
		return m_iMetadataTokenValue;
	}
}
