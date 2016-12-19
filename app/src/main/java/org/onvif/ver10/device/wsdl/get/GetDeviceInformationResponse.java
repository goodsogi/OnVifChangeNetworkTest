package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;
import org.onvif.ver10.schema.nativeParcel.DeviceInformation;
import org.onvif.ver10.schema.nativeParcel.utils;

public class GetDeviceInformationResponse extends SoapObject {
	private final static String METHOD_NAME = GetDeviceInformationResponse.class.getSimpleName();

	public DeviceInformation mDeviceInformation;

	public GetDeviceInformationResponse() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}

	public GetDeviceInformationResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			mDeviceInformation = new DeviceInformation();
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// Manufacturer
				// =================================================================================
				if (root.getName().equalsIgnoreCase("Manufacturer")) {
					mDeviceInformation.mManufacturer = utils.toWsdlString(root.getValue().toString());					
				}
				// =================================================================================
				// Model
				// =================================================================================
				else if (root.getName().equalsIgnoreCase("Model")) {
					mDeviceInformation.mModel = utils.toWsdlString(root.getValue().toString());
				}
				// =================================================================================
				// FirmwareVersion
				// =================================================================================
				else if (root.getName().equalsIgnoreCase("FirmwareVersion")) {
					mDeviceInformation.mFirmwareVersion = utils.toWsdlString(root.getValue().toString());
				}
				// =================================================================================
				// SerialNumber
				// =================================================================================
				else if (root.getName().equalsIgnoreCase("SerialNumber")) {
					mDeviceInformation.mSerialNumber = utils.toWsdlString(root.getValue().toString());
				}
				// =================================================================================
				// HardwareId
				// =================================================================================
				else if (root.getName().equalsIgnoreCase("HardwareId")) {
					mDeviceInformation.mHardwareId = utils.toWsdlString(root.getValue().toString());
				}
			}
		}
	}

	public void setDeviceInformation(String mManufacturer, String mModel, String mFirmwareVersion, String mSerialNumber, String mHardwareId) {
		addProperty(DeviceService.NAMESPACE, "Manufacturer", mManufacturer);
		addProperty(DeviceService.NAMESPACE, "Model", mModel);
		addProperty(DeviceService.NAMESPACE, "FirmwareVersion", mFirmwareVersion);
		addProperty(DeviceService.NAMESPACE, "SerialNumber", mSerialNumber);
		addProperty(DeviceService.NAMESPACE, "HardwareId", mHardwareId);
	}

}
