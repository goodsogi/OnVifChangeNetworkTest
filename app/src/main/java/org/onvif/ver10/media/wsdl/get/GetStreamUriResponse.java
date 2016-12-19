package org.onvif.ver10.media.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.schema.nativeParcel.MediaUri;

public class GetStreamUriResponse {
	public final static String METHOD_NAME = GetStreamUriResponse.class.getSimpleName();
	public MediaUri mMediaUri;
	public GetStreamUriResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			mMediaUri = new MediaUri();
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// MediaUri
				// =================================================================================
				if (root.getName().equalsIgnoreCase("MediaUri")) {
					SoapObject MediaUri = (SoapObject) root.getValue();
					for (int j = 0; j < MediaUri.getPropertyCount(); j++) {
						PropertyInfo subSoapObject = new PropertyInfo();
						MediaUri.getPropertyInfo(j, subSoapObject);
						// =================================================================================
						// Uri
						// =================================================================================
						if (subSoapObject.getName().equalsIgnoreCase("Uri")) {
							mMediaUri.	mUri = subSoapObject.getValue().toString();
						}
						// =================================================================================
						// InvalidAfterConnect
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("InvalidAfterConnect")) {
							try {
								mMediaUri.mInvalidAfterConnect = Boolean.parseBoolean(subSoapObject.getValue().toString());
							} catch (Exception e) {
							}
						}
						// =================================================================================
						// InvalidAfterReboot
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("InvalidAfterReboot")) {
							try {
								mMediaUri.mInvalidAfterReboot = Boolean.parseBoolean(subSoapObject.getValue().toString());
							} catch (Exception e) {
							}
						}
						// =================================================================================
						// Timeout
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("Timeout")) {
							mMediaUri.mTimeout = subSoapObject.getValue().toString();
						}
					}
				}
			}
		}
	}
}
