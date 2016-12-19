package org.onvif.ver10.image.wsdl.get;

import android.util.Log;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.onvif.ver10.schema.BacklightCompensation20;
import org.onvif.ver10.schema.Exposure20;
import org.onvif.ver10.schema.FocusConfiguration20;
import org.onvif.ver10.schema.Rectangle;
import org.onvif.ver10.schema.WhiteBalance20;
import org.onvif.ver10.schema.WideDynamicRange20;
import org.onvif.ver10.schema.nativeParcel.ImagingSettings20;
import org.onvif.ver10.schema.nativeParcel.utils;

public class GetImagingSettingsResponse {
	private final static String METHOD_NAME = GetImagingSettingsResponse.class.getSimpleName();

	public ImagingSettings20 mImagingSettings;

	public GetImagingSettingsResponse(SoapObject obj) {
		try {
			if (obj != null && obj.getName().equals(METHOD_NAME)) {
				Log.i(METHOD_NAME, obj.toString());
				for (int i = 0; i < obj.getPropertyCount(); i++) {
					PropertyInfo root = new PropertyInfo();
					obj.getPropertyInfo(i, root);
					// =================================================================================
					// ImagingSettings
					// =================================================================================
					if (root.getName().equalsIgnoreCase("ImagingSettings")) {
						SoapObject ImagingOptions = (SoapObject) root.getValue();
						mImagingSettings = new ImagingSettings20();

						for (int j = 0; j < ImagingOptions.getPropertyCount(); j++) {
							PropertyInfo subSoapObject = new PropertyInfo();
							ImagingOptions.getPropertyInfo(j, subSoapObject);
							// =================================================================================
							// BacklightCompensation
							// =================================================================================
							if (subSoapObject.getName().equalsIgnoreCase("BacklightCompensation")) {
								BacklightCompensation20 BLC = new BacklightCompensation20();
								SoapObject BacklightCompensation = (SoapObject) subSoapObject.getValue();
								for (int count = 0; count < BacklightCompensation.getPropertyCount(); count++) {
									PropertyInfo backlightCompensation = new PropertyInfo();
									BacklightCompensation.getPropertyInfo(count, backlightCompensation);
									if (backlightCompensation.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// BacklightCompensation - Mode
										// =================================================================================
										if (backlightCompensation.getName().equalsIgnoreCase("Mode")) {
											BLC.mMode = utils.toWsdlString(backlightCompensation.getValue().toString());
											Log.i(METHOD_NAME, "BacklightCompensation - Mode:" + BLC.mMode);
										}
										// =================================================================================
										// BacklightCompensation - Level
										// =================================================================================
										else if (backlightCompensation.getName().equalsIgnoreCase("Level")) {
											try {
												BLC.mLevel = Float.parseFloat(utils.toWsdlString(backlightCompensation.getValue().toString()));
												Log.i(METHOD_NAME, "BacklightCompensation - Level:" + BLC.mLevel);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "BacklightCompensation - Level:" + backlightCompensation.getValue().toString());
												e.printStackTrace();
											}
										}
									}
								}
								mImagingSettings.mBacklightCompensation = BLC;
							}
							// =================================================================================
							// Brightness
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Brightness")) {
								try {
									mImagingSettings.mBrightness = Float.parseFloat(subSoapObject.getValue().toString());
									Log.i(METHOD_NAME, "Brightness:" + mImagingSettings.mBrightness);
								} catch (Exception e) {
									Log.e(METHOD_NAME, "Brightness:" + subSoapObject.getValue().toString());
									e.printStackTrace();
								}
							}
							// =================================================================================
							// ColorSaturation
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("ColorSaturation")) {
								try {
									mImagingSettings.mColorSaturation = Float.parseFloat(subSoapObject.getValue().toString());
									Log.i(METHOD_NAME, "ColorSaturation:" + mImagingSettings.mColorSaturation);
								} catch (Exception e) {
									Log.e(METHOD_NAME, "ColorSaturation:" + subSoapObject.getValue().toString());
									e.printStackTrace();
								}
							}
							// =================================================================================
							// Contrast
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Contrast")) {
								try {
									mImagingSettings.mContrast = Float.parseFloat(subSoapObject.getValue().toString());
									Log.i(METHOD_NAME, "Contrast:" + mImagingSettings.mContrast);
								} catch (Exception e) {
									Log.e(METHOD_NAME, "Contrast:" + subSoapObject.getValue().toString());
									e.printStackTrace();
								}
							}
							// =================================================================================
							// Exposure
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Exposure")) {
								Exposure20 E20 = new Exposure20();
								SoapObject Exposure = (SoapObject) subSoapObject.getValue();
								for (int count = 0; count < Exposure.getPropertyCount(); count++) {
									PropertyInfo exposure = new PropertyInfo();
									Exposure.getPropertyInfo(count, exposure);
									if (exposure.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// Exposure - Mode
										// =================================================================================
										if (exposure.getName().equalsIgnoreCase("Mode")) {
											E20.mMode = exposure.getValue().toString();
											Log.i(METHOD_NAME, "Exposure - Mode:" + E20.mMode);
										}
										// =================================================================================
										// Exposure - Priority
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("Priority")) {
											E20.mPriority = exposure.getValue().toString();
											Log.i(METHOD_NAME, "Exposure - Priority:" + E20.mPriority);
										}
									} else if (exposure.getValue() instanceof SoapObject) {
										// =================================================================================
										// Exposure - Window
										// =================================================================================
										if (exposure.getName().equalsIgnoreCase("Window")) {
											E20.mWindow = new Rectangle();
											SoapObject Window = (SoapObject) exposure.getValue();
											try {
												E20.mWindow.mBottom = Float.parseFloat((String) Window.getAttributeSafelyAsString("bottom"));
												Log.i(METHOD_NAME, "Exposure - Window - bottom:" + E20.mWindow.mBottom);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "Exposure - Window - bottom:" + Window.getAttributeSafelyAsString("bottom"));
											}
											try {
												E20.mWindow.mTop = Float.parseFloat((String) Window.getAttributeSafelyAsString("top"));
												Log.i(METHOD_NAME, "Exposure - Window - top:" + E20.mWindow.mTop);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "Exposure - Window - top:" + Window.getAttributeSafelyAsString("top"));
											}
											try {
												E20.mWindow.mRight = Float.parseFloat((String) Window.getAttributeSafelyAsString("right"));
												Log.i(METHOD_NAME, "Exposure - Window - right:" + E20.mWindow.mRight);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "Exposure - Window - right:" + Window.getAttributeSafelyAsString("right"));
											}
											try {
												E20.mWindow.mLeft = Float.parseFloat((String) Window.getAttributeSafelyAsString("left"));
												Log.i(METHOD_NAME, "Exposure - Window - left:" + E20.mWindow.mLeft);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "Exposure - Window - left:" + Window.getAttributeSafelyAsString("left"));
											}
										}
										// =================================================================================
										// Exposure - MinExposureTime
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MinExposureTime")) {
											try {
												mImagingSettings.mExposure.mMinExposureTime = Float.parseFloat(exposure.getValue().toString());
												Log.i(METHOD_NAME, "mExposure - MinExposureTime:" + mImagingSettings.mExposure.mMinExposureTime);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "mExposure - MinExposureTime:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Exposure - MaxExposureTime
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MaxExposureTime")) {
											try {
												mImagingSettings.mExposure.mMaxExposureTime = Float.parseFloat(exposure.getValue().toString());
												Log.i(METHOD_NAME, "mExposure - MaxExposureTime:" + mImagingSettings.mExposure.mMaxExposureTime);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "mExposure - MaxExposureTime:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Exposure - MinGain
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MinGain")) {
											try {
												mImagingSettings.mExposure.mMinGain = Float.parseFloat(exposure.getValue().toString());
												Log.i(METHOD_NAME, "mExposure - MinGain:" + mImagingSettings.mExposure.mMinGain);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "mExposure - MinGain:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Exposure - MaxGain
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MaxGain")) {
											try {
												mImagingSettings.mExposure.mMaxGain = Float.parseFloat(exposure.getValue().toString());
												Log.i(METHOD_NAME, "mExposure - MaxGain:" + mImagingSettings.mExposure.mMaxGain);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "mExposure - MaxGain:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Exposure - MinIris
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MinIris")) {
											try {
												mImagingSettings.mExposure.mMinIris = Float.parseFloat(exposure.getValue().toString());
												Log.i(METHOD_NAME, "mExposure - MinIris:" + mImagingSettings.mExposure.mMinIris);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "mExposure - MinIris:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Exposure - MaxIris
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MaxIris")) {
											try {
												mImagingSettings.mExposure.mMaxIris = Float.parseFloat(exposure.getValue().toString());
												Log.i(METHOD_NAME, "mExposure - MaxIris:" + mImagingSettings.mExposure.mMaxIris);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "mExposure - MaxIris:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Exposure - ExposureTime
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("ExposureTime")) {
											try {
												mImagingSettings.mExposure.mExposureTime = Float.parseFloat(exposure.getValue().toString());
												Log.i(METHOD_NAME, "mExposure - ExposureTime:" + mImagingSettings.mExposure.mExposureTime);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "mExposure - ExposureTime:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Exposure - Gain
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("Gain")) {
											try {
												mImagingSettings.mExposure.mGain = Float.parseFloat(exposure.getValue().toString());
												Log.i(METHOD_NAME, "mExposure - Gain:" + mImagingSettings.mExposure.mGain);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "mExposure - Gain:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Exposure - Iris
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("Iris")) {
											try {
												mImagingSettings.mExposure.mIris = Float.parseFloat(exposure.getValue().toString());
												Log.i(METHOD_NAME, "mExposure - Iris:" + mImagingSettings.mExposure.mIris);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "mExposure - Iris:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
									}
								}
								mImagingSettings.mExposure = E20;
							}
							// =================================================================================
							// Focus
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Focus")) {
								FocusConfiguration20 F20 = new FocusConfiguration20();
								SoapObject Focus = (SoapObject) subSoapObject.getValue();
								for (int count = 0; count < Focus.getPropertyCount(); count++) {
									PropertyInfo focus = new PropertyInfo();
									Focus.getPropertyInfo(count, focus);
									if (focus.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// Focus - AutoFocusModes
										// =================================================================================
										if (focus.getName().equalsIgnoreCase("AutoFocusModes")) {
											F20.mAutoFocusMode = focus.getValue().toString();
											Log.i(METHOD_NAME, "Focus - AutoFocusModes:" + F20.mAutoFocusMode);
										}
										// =================================================================================
										// Focus - DefaultSpeed
										// =================================================================================
										else if (focus.getName().equalsIgnoreCase("DefaultSpeed")) {
											try {
												F20.mDefaultSpeed = Float.parseFloat(focus.getValue().toString());
												Log.i(METHOD_NAME, "Focus - DefaultSpeed:" + F20.mDefaultSpeed);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "Focus - DefaultSpeed:" + focus.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Focus - NearLimit
										// =================================================================================
										else if (focus.getName().equalsIgnoreCase("NearLimit")) {
											try {
												F20.mNearLimit = Float.parseFloat(focus.getValue().toString());
												Log.i(METHOD_NAME, "Focus - NearLimit:" + F20.mNearLimit);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "Focus - NearLimit:" + focus.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Focus - FarLimit
										// =================================================================================
										else if (focus.getName().equalsIgnoreCase("FarLimit")) {
											try {
												F20.mFarLimit = Float.parseFloat(focus.getValue().toString());
												Log.i(METHOD_NAME, "Focus - FarLimit:" + F20.mFarLimit);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "Focus - FarLimit:" + focus.getValue().toString());
												e.printStackTrace();
											}
										}
									}
								}
								mImagingSettings.mFocus = F20;
							}
							// =================================================================================
							// IrCutFilter
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("IrCutFilter")) {
								try {
									mImagingSettings.mIrCutFilter = utils.toWsdlString(subSoapObject.getValue().toString());
									Log.i(METHOD_NAME, "IrCutFilter:" + mImagingSettings.mIrCutFilter);
								} catch (Exception e) {
									Log.e(METHOD_NAME, "IrCutFilter:" + subSoapObject.getValue().toString());
									e.printStackTrace();
								}
							}
							// =================================================================================
							// Sharpness
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Sharpness")) {
								try {
									mImagingSettings.mSharpness = Float.parseFloat(subSoapObject.getValue().toString());
									Log.i(METHOD_NAME, "Sharpness:" + mImagingSettings.mSharpness);
								} catch (Exception e) {
									Log.e(METHOD_NAME, "Sharpness:" + subSoapObject.getValue().toString());
									e.printStackTrace();
								}
							}

							// =================================================================================
							// WideDynamicRange
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("WideDynamicRange")) {
								WideDynamicRange20 WDR20 = new WideDynamicRange20();
								SoapObject WideDynamicRange = (SoapObject) subSoapObject.getValue();
								for (int count = 0; count < WideDynamicRange.getPropertyCount(); count++) {
									PropertyInfo wideDynamicRange = new PropertyInfo();
									WideDynamicRange.getPropertyInfo(count, wideDynamicRange);
									if (wideDynamicRange.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// WideDynamicRange - Mode
										// =================================================================================
										if (wideDynamicRange.getName().equalsIgnoreCase("Mode")) {
											WDR20.mMode = utils.toWsdlString(wideDynamicRange.getValue().toString());
											Log.i(METHOD_NAME, "WideDynamicRange - Mode:" + WDR20.mMode);
										}
										// =================================================================================
										// WideDynamicRange - Level
										// =================================================================================
										else if (wideDynamicRange.getName().equalsIgnoreCase("Level")) {
											try {
												WDR20.mLevel = Float.parseFloat(utils.toWsdlString(wideDynamicRange.getValue().toString()));
												Log.i(METHOD_NAME, "WideDynamicRange - Level:" + WDR20.mLevel);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "WideDynamicRange - Level:" + wideDynamicRange.getValue().toString());
												e.printStackTrace();
											}
										}
									}
								}
								mImagingSettings.mWideDynamicRange = WDR20;
							}
							// =================================================================================
							// WhiteBalance
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("WhiteBalance")) {
								WhiteBalance20 WB20 = new WhiteBalance20();
								SoapObject WhiteBalance = (SoapObject) subSoapObject.getValue();
								for (int count = 0; count < WhiteBalance.getPropertyCount(); count++) {
									PropertyInfo whiteBalance = new PropertyInfo();
									WhiteBalance.getPropertyInfo(count, whiteBalance);
									if (whiteBalance.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// WhiteBalance - Mode
										// =================================================================================
										if (whiteBalance.getName().equalsIgnoreCase("Mode")) {
											WB20.mMode = whiteBalance.getValue().toString();
											Log.i(METHOD_NAME, "WhiteBalance - Mode:" + WB20.mMode);
										}
										// =================================================================================
										// WhiteBalance - CrGain
										// =================================================================================
										else if (whiteBalance.getName().equalsIgnoreCase("CrGain")) {
											try {
												WB20.mCrGain = Float.parseFloat(whiteBalance.getValue().toString());
												Log.i(METHOD_NAME, "WhiteBalance - CrGain:" + WB20.mCrGain);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "WhiteBalance - CrGain:" + whiteBalance.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// WhiteBalance - CbGain
										// =================================================================================
										else if (whiteBalance.getName().equalsIgnoreCase("CbGain")) {
											try {
												WB20.mCbGain = Float.parseFloat(whiteBalance.getValue().toString());
												Log.i(METHOD_NAME, "WhiteBalance - CbGain:" + WB20.mCbGain);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "WhiteBalance - CbGain:" + whiteBalance.getValue().toString());
												e.printStackTrace();
											}
										}
									}
								}
								mImagingSettings.mWhiteBalance = WB20;
							}
						}
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
