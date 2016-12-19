package org.onvif.ver10.media.wsdl.set;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public class AddMetadataConfigurationResponse {
	private final static String TAG = "AddMetadataConfigurationResponse";
	private String mErrorMSg;

	public AddMetadataConfigurationResponse(SoapObject obj) {
		if (!obj.getName().equals("AgentErrorMsg")) {
		
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo info = new PropertyInfo();
				obj.getPropertyInfo(i, info);

				if (!info.getName().equalsIgnoreCase("Configurations"))
					continue;

			}
		} else {
			mErrorMSg = obj.toString();
		}

	}

	public String getErrorMsg() {
		return mErrorMSg;
	}

}
