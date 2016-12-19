package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.schema.nativeParcel.Capabilities;

public class GetCapabilitiesResponse {
	private final static String METHOD_NAME = GetCapabilitiesResponse.class.getSimpleName();

	public Capabilities mCapabilities;

	public GetCapabilitiesResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			mCapabilities = new Capabilities();
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// Capabilities
				// =================================================================================
				if (root.getName().equalsIgnoreCase("Capabilities")) {					
					SoapObject Capabilities = (SoapObject) root.getValue();
					for (int j = 0; j < Capabilities.getPropertyCount(); j++) {
						PropertyInfo subSoapObject = new PropertyInfo();
						Capabilities.getPropertyInfo(j, subSoapObject);
						// =================================================================================
						// Analytics
						// =================================================================================
						if (subSoapObject.getName().equalsIgnoreCase("Analytics")) {
							if (hasProperty(subSoapObject, "XAddr")) {
								mCapabilities.mAnalyticsXAddr = ((SoapObject) subSoapObject.getValue()).getPropertyAsString("XAddr");
							}
						}
						// =================================================================================
						// Device
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("Device")) {
							if (hasProperty(subSoapObject, "XAddr")) {
								mCapabilities.mDeviceXAddr = ((SoapObject) subSoapObject.getValue()).getPropertyAsString("XAddr");
							}
						}
						// =================================================================================
						// Events
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("Events")) {
							if (hasProperty(subSoapObject, "XAddr")) {
								mCapabilities.mEventsXAddr = ((SoapObject) subSoapObject.getValue()).getPropertyAsString("XAddr");
							}
						}
						// =================================================================================
						// Imaging
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("Imaging")) {
							if (hasProperty(subSoapObject, "XAddr")) {
								mCapabilities.mImagingXAddr = ((SoapObject) subSoapObject.getValue()).getPropertyAsString("XAddr");
							}
						}
						// =================================================================================
						// Media
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("Media")) {
							if (hasProperty(subSoapObject, "XAddr")) {
								mCapabilities.mMediaXAddr = ((SoapObject) subSoapObject.getValue()).getPropertyAsString("XAddr");
							}
						}
						// =================================================================================
						// PTZ
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("PTZ")) {
							if (hasProperty(subSoapObject, "XAddr")) {
								mCapabilities.mPTZXAddr = ((SoapObject) subSoapObject.getValue()).getPropertyAsString("XAddr");
							}
						}
						// =================================================================================
						// Extension
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("Extension")) {
							SoapObject Extension = (SoapObject) subSoapObject.getValue();
							for (int count = 0; count < Extension.getPropertyCount(); count++) {
								PropertyInfo extension = new PropertyInfo();
								Extension.getPropertyInfo(count, extension);
								// =================================================================================
								// Extension - DeviceIO
								// =================================================================================
								if (extension.getName().equalsIgnoreCase("DeviceIO")) {
									if (hasProperty(extension, "XAddr")) {
										mCapabilities.mExDeviceIOXAddr = ((SoapObject) extension.getValue()).getPropertyAsString("XAddr");
									}
								}
								// =================================================================================
								// Extension - Display
								// =================================================================================
								else if (extension.getName().equalsIgnoreCase("Display")) {
									if (hasProperty(extension, "XAddr")) {
										mCapabilities.mExDisplayXAddr = ((SoapObject) extension.getValue()).getPropertyAsString("XAddr");
									}
								}
								// =================================================================================
								// Extension - Recording
								// =================================================================================
								else if (extension.getName().equalsIgnoreCase("Recording")) {
									if (hasProperty(extension, "XAddr")) {
										mCapabilities.mExRecordingXAddr = ((SoapObject) extension.getValue()).getPropertyAsString("XAddr");
									}
								}
								// =================================================================================
								// Extension - Search
								// =================================================================================
								else if (extension.getName().equalsIgnoreCase("Search")) {
									if (hasProperty(extension, "XAddr")) {
										mCapabilities.mExSearchXAddr = ((SoapObject) extension.getValue()).getPropertyAsString("XAddr");
									}
								}
								// =================================================================================
								// Extension - Replay
								// =================================================================================
								else if (extension.getName().equalsIgnoreCase("Replay")) {
									if (hasProperty(extension, "XAddr")) {
										mCapabilities.mExReplayXAddr = ((SoapObject) extension.getValue()).getPropertyAsString("XAddr");
									}
								}
								// =================================================================================
								// Extension - Receiver
								// =================================================================================
								else if (extension.getName().equalsIgnoreCase("Receiver")) {
									if (hasProperty(extension, "XAddr")) {
										mCapabilities.mExReceiverXAddr = ((SoapObject) extension.getValue()).getPropertyAsString("XAddr");
									}
								}
								// =================================================================================
								// Extension - AnalyticsDevice
								// =================================================================================
								else if (extension.getName().equalsIgnoreCase("AnalyticsDevice")) {
									if (hasProperty(extension, "XAddr")) {
										mCapabilities.mExAnalyticsDeviceXAddr = ((SoapObject) extension.getValue()).getPropertyAsString("XAddr");
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private boolean hasProperty(PropertyInfo propertyInfo, String propertyName) {
		try {
			SoapObject xaddr = (SoapObject) propertyInfo.getValue();
			return xaddr.hasProperty(propertyName);
		} catch (Exception e) {
			return false;
		}
	}

}
