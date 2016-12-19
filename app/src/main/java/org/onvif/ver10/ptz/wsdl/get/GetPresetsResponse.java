package org.onvif.ver10.ptz.wsdl.get;

import android.util.Log;

import org.ksoap2.serialization.AttributeInfo;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.onvif.ver10.schema.PTZVector;
import org.onvif.ver10.schema.Vector1D;
import org.onvif.ver10.schema.Vector2D;
import org.onvif.ver10.schema.nativeParcel.PTZPreset;
import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class GetPresetsResponse {
	private final static String METHOD_NAME = GetPresetsResponse.class.getSimpleName();

	public ArrayList<PTZPreset> mPTZPresets;

	public GetPresetsResponse(SoapObject obj) {
		try {
			if (obj != null && obj.getName().equals(METHOD_NAME)) {
				mPTZPresets = new ArrayList<PTZPreset>();

				for (int i = 0; i < obj.getPropertyCount(); i++) {
					PropertyInfo root = new PropertyInfo();
					obj.getPropertyInfo(i, root);
					// =================================================================================
					// Preset
					// =================================================================================
					if (root.getName().equalsIgnoreCase("Preset")) {

						PTZPreset ptzPreset = new PTZPreset();

						SoapObject Preset = (SoapObject) root.getValue();
						for (int a = 0; a < Preset.getAttributeCount(); a++) {
							AttributeInfo info = new AttributeInfo();
							Preset.getAttributeInfo(a, info);
							// =================================================================================
							// token
							// =================================================================================
							if (info.getName().equalsIgnoreCase("token")) {
								ptzPreset.mToken = utils.toWsdlString(info.getValue().toString());
								Log.i(METHOD_NAME, "Preset - token:" + ptzPreset.mToken);
							}
						}

						for (int j = 0; j < Preset.getPropertyCount(); j++) {
							PropertyInfo subSoapObject = new PropertyInfo();
							Preset.getPropertyInfo(j, subSoapObject);
							// =================================================================================
							// Name
							// =================================================================================
							if (subSoapObject.getName().equalsIgnoreCase("Name")) {
								ptzPreset.mName = utils.toWsdlString(subSoapObject.getValue().toString());
								Log.i(METHOD_NAME, "Preset - Name:" + ptzPreset.mName);
							}

							// =================================================================================
							// PTZPosition
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("PTZPosition")) {

								ptzPreset.mPTZPosition = new PTZVector();

								SoapObject PTZPosition = (SoapObject) subSoapObject.getValue();

								for (int count = 0; count < PTZPosition.getPropertyCount(); count++) {
									PropertyInfo pTZPosition = new PropertyInfo();
									PTZPosition.getPropertyInfo(count, pTZPosition);

									if (pTZPosition.getValue() instanceof SoapPrimitive) {
										Log.i(METHOD_NAME, "PTZPosition - SoapPrimitive:" + pTZPosition.getValue().toString());
									}

									else if (pTZPosition.getValue() instanceof SoapObject) {

										// =================================================================================
										// PTZPosition - PanTilt
										// =================================================================================
										if (pTZPosition.getName().equalsIgnoreCase("PanTilt")) {
											SoapObject PanTilt = (SoapObject) pTZPosition.getValue();
											ptzPreset.mPTZPosition.mPanTilt = new Vector2D();
											try {
												ptzPreset.mPTZPosition.mPanTilt.mX = Float.parseFloat((String) PanTilt
														.getAttributeSafelyAsString("x"));
												Log.i(METHOD_NAME, "PTZPosition - PanTilt - x:" + ptzPreset.mPTZPosition.mPanTilt.mX);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "PTZPosition - PanTilt - x:" + PanTilt.getAttributeSafelyAsString("x"));
											}

											try {
												ptzPreset.mPTZPosition.mPanTilt.mY = Float.parseFloat((String) PanTilt
														.getAttributeSafelyAsString("y"));
												Log.i(METHOD_NAME, "PTZPosition - PanTilt - y:" + ptzPreset.mPTZPosition.mPanTilt.mY);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "PTZPosition - PanTilt - y:" + PanTilt.getAttributeSafelyAsString("y"));
											}
											try {
												ptzPreset.mPTZPosition.mPanTilt.mSpace = utils.toWsdlString((String) PanTilt.getAttributeSafelyAsString("space"));
												Log.i(METHOD_NAME, "PTZPosition - PanTilt - space:" + ptzPreset.mPTZPosition.mPanTilt.mSpace);
											} catch (Exception e) {
												Log.e(METHOD_NAME,
														"PTZPosition - PanTilt - space:" + (String) PanTilt.getAttributeSafelyAsString("space"));
											}
										}
										// =================================================================================
										// PTZPosition - Zoom
										// =================================================================================
										else if (pTZPosition.getName().equalsIgnoreCase("Zoom")) {
											SoapObject Zoom = (SoapObject) pTZPosition.getValue();
											Log.i(METHOD_NAME, "PTZPosition - Zoom:" + Zoom.toString());
											ptzPreset.mPTZPosition.mZoom = new Vector1D();
											try {
												ptzPreset.mPTZPosition.mZoom.mX = Float.parseFloat((String) Zoom.getAttributeSafelyAsString("x"));
												Log.i(METHOD_NAME, "PTZPosition - Zoom - x:" + ptzPreset.mPTZPosition.mZoom.mX);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "PTZPosition - Zoom - x:" + Zoom.getAttributeSafelyAsString("x"));
											}

											try {
												ptzPreset.mPTZPosition.mZoom.mSpace = (String) Zoom.getAttributeSafelyAsString("space");
												Log.i(METHOD_NAME, "PTZPosition - Zoom - space:" + ptzPreset.mPTZPosition.mZoom.mSpace);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "PTZPosition - Zoom - space:" + (String) Zoom.getAttributeSafelyAsString("space"));
											}
										}
									}
								}
							}
						}
						mPTZPresets.add(ptzPreset);
					}
				}
			} else {
				Log.i(METHOD_NAME, "Not Match");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
