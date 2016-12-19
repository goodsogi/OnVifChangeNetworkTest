package org.onvif.ver10.image.wsdl.get;

import android.util.Log;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.onvif.ver10.schema.BacklightCompensationOptions20;
import org.onvif.ver10.schema.ExposureOptions20;
import org.onvif.ver10.schema.FloatRange;
import org.onvif.ver10.schema.FocusOptions20;
import org.onvif.ver10.schema.WhiteBalanceOptions20;
import org.onvif.ver10.schema.WideDynamicRangeOptions20;
import org.onvif.ver10.schema.nativeParcel.ImagingOptions20;
import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class GetOptionsResponse {
	private final static String METHOD_NAME = GetOptionsResponse.class.getSimpleName();

	public ImagingOptions20 mImagingOptions;

	public GetOptionsResponse(SoapObject obj) {
		try {
			if (obj != null && obj.getName().equals(METHOD_NAME)) {
				Log.i(METHOD_NAME, obj.toString());
				for (int i = 0; i < obj.getPropertyCount(); i++) {
					PropertyInfo root = new PropertyInfo();
					obj.getPropertyInfo(i, root);
					// =================================================================================
					// ImagingOptions
					// =================================================================================
					if (root.getName().equalsIgnoreCase("ImagingOptions")) {
						SoapObject ImagingOptions = (SoapObject) root.getValue();
						mImagingOptions = new ImagingOptions20();

						for (int j = 0; j < ImagingOptions.getPropertyCount(); j++) {
							PropertyInfo subSoapObject = new PropertyInfo();
							ImagingOptions.getPropertyInfo(j, subSoapObject);
							// =================================================================================
							// BacklightCompensation
							// =================================================================================
							if (subSoapObject.getName().equalsIgnoreCase("BacklightCompensation")) {
								BacklightCompensationOptions20 BLC = new BacklightCompensationOptions20();
								SoapObject BacklightCompensation = (SoapObject) subSoapObject.getValue();
								for (int count = 0; count < BacklightCompensation.getPropertyCount(); count++) {
									PropertyInfo backlightCompensation = new PropertyInfo();
									BacklightCompensation.getPropertyInfo(count, backlightCompensation);
									if (backlightCompensation.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// BacklightCompensation - Mode
										// =================================================================================
										if (backlightCompensation.getName().equalsIgnoreCase("Mode")) {
											if (BLC.mMode == null) {
												BLC.mMode = new ArrayList<String>();
											}
											try {
												BLC.mMode.add(backlightCompensation.getValue().toString());
												Log.i(METHOD_NAME, "BacklightCompensation - Mode:" + backlightCompensation.getValue().toString());
											} catch (Exception e) {
												Log.e(METHOD_NAME, "BacklightCompensation - Mode:" + backlightCompensation.getValue().toString());
												e.printStackTrace();
											}
										}
									} else if (backlightCompensation.getValue() instanceof SoapObject) {
										// =================================================================================
										// BacklightCompensation - Level
										// =================================================================================
										if (backlightCompensation.getName().equalsIgnoreCase("Level")) {
											BLC.mLevel = new FloatRange();
											SoapObject Level = (SoapObject) backlightCompensation.getValue();
											for (int c = 0; c < Level.getPropertyCount(); c++) {
												PropertyInfo level = new PropertyInfo();
												Level.getPropertyInfo(c, level);
												// =================================================================================
												// Level - Min
												// =================================================================================
												if (level.getName().equalsIgnoreCase("Min")) {
													try {
														BLC.mLevel.mMin = Float.parseFloat(level.getValue().toString());
														Log.i(METHOD_NAME, "BacklightCompensation - Level - Min:" + BLC.mLevel.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "BacklightCompensation - Level - Min:" + level.getValue().toString());
														e.printStackTrace();
													}

												}
												// =================================================================================
												// Level - Max
												// =================================================================================
												else if (level.getName().equalsIgnoreCase("Max")) {
													try {
														BLC.mLevel.mMax = Float.parseFloat(level.getValue().toString());
														Log.i(METHOD_NAME, "BacklightCompensation - Level - Max:" + BLC.mLevel.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "BacklightCompensation - Level - Max:" + level.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
									}
								}
								mImagingOptions.mBacklightCompensation = BLC;
							}
							// =================================================================================
							// Brightness
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Brightness")) {
								FloatRange BT = new FloatRange();
								SoapObject Brightness = (SoapObject) subSoapObject.getValue();
								for (int c = 0; c < Brightness.getPropertyCount(); c++) {
									PropertyInfo brightness = new PropertyInfo();
									Brightness.getPropertyInfo(c, brightness);
									// =================================================================================
									// Brightness - Min
									// =================================================================================
									if (brightness.getName().equalsIgnoreCase("Min")) {
										try {
											BT.mMin = Float.parseFloat(brightness.getValue().toString());
											Log.i(METHOD_NAME, "Brightness - Min:" + BT.mMin);
										} catch (Exception e) {
											Log.e(METHOD_NAME, "Brightness - Min:" + brightness.getValue().toString());
											e.printStackTrace();
										}

									}
									// =================================================================================
									// Brightness - Max
									// =================================================================================
									else if (brightness.getName().equalsIgnoreCase("Max")) {
										try {
											BT.mMax = Float.parseFloat(brightness.getValue().toString());
											Log.i(METHOD_NAME, "Brightness - Max:" + BT.mMax);
										} catch (Exception e) {
											Log.e(METHOD_NAME, "Brightness - Max:" + brightness.getValue().toString());
											e.printStackTrace();
										}
									}
								}
								mImagingOptions.mBrightness = BT;
							}
							// =================================================================================
							// ColorSaturation
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("ColorSaturation")) {
								FloatRange CS = new FloatRange();
								SoapObject ColorSaturation = (SoapObject) subSoapObject.getValue();
								for (int c = 0; c < ColorSaturation.getPropertyCount(); c++) {
									PropertyInfo colorSaturation = new PropertyInfo();
									ColorSaturation.getPropertyInfo(c, colorSaturation);
									// =================================================================================
									// ColorSaturation - Min
									// =================================================================================
									if (colorSaturation.getName().equalsIgnoreCase("Min")) {
										try {
											CS.mMin = Float.parseFloat(colorSaturation.getValue().toString());
											Log.i(METHOD_NAME, "ColorSaturation - Min:" + CS.mMin);
										} catch (Exception e) {
											Log.e(METHOD_NAME, "ColorSaturation - Min:" + colorSaturation.getValue().toString());
											e.printStackTrace();
										}

									}
									// =================================================================================
									// ColorSaturation - Max
									// =================================================================================
									else if (colorSaturation.getName().equalsIgnoreCase("Max")) {
										try {
											CS.mMax = Float.parseFloat(colorSaturation.getValue().toString());
											Log.i(METHOD_NAME, "ColorSaturation - Max:" + CS.mMax);
										} catch (Exception e) {
											Log.e(METHOD_NAME, "ColorSaturation - Max:" + colorSaturation.getValue().toString());
											e.printStackTrace();
										}
									}
								}
								mImagingOptions.mColorSaturation = CS;
							}
							// =================================================================================
							// Contrast
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Contrast")) {
								FloatRange CT = new FloatRange();
								SoapObject Contrast = (SoapObject) subSoapObject.getValue();
								for (int c = 0; c < Contrast.getPropertyCount(); c++) {
									PropertyInfo contrast = new PropertyInfo();
									Contrast.getPropertyInfo(c, contrast);
									// =================================================================================
									// Contrast - Min
									// =================================================================================
									if (contrast.getName().equalsIgnoreCase("Min")) {
										try {
											CT.mMin = Float.parseFloat(contrast.getValue().toString());
											Log.i(METHOD_NAME, "Contrast - Min:" + CT.mMin);
										} catch (Exception e) {
											Log.e(METHOD_NAME, "Contrast - Min:" + contrast.getValue().toString());
											e.printStackTrace();
										}

									}
									// =================================================================================
									// Contrast - Max
									// =================================================================================
									else if (contrast.getName().equalsIgnoreCase("Max")) {
										try {
											CT.mMax = Float.parseFloat(contrast.getValue().toString());
											Log.i(METHOD_NAME, "Contrast - Max:" + CT.mMax);
										} catch (Exception e) {
											Log.e(METHOD_NAME, "Contrast - Max:" + contrast.getValue().toString());
											e.printStackTrace();
										}
									}
								}
								mImagingOptions.mContrast = CT;
							}
							// =================================================================================
							// Exposure
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Exposure")) {
								ExposureOptions20 EO20 = new ExposureOptions20();
								SoapObject Exposure = (SoapObject) subSoapObject.getValue();
								for (int count = 0; count < Exposure.getPropertyCount(); count++) {
									PropertyInfo exposure = new PropertyInfo();
									Exposure.getPropertyInfo(count, exposure);
									if (exposure.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// Exposure - Mode
										// =================================================================================
										if (exposure.getName().equalsIgnoreCase("Mode")) {
											if (EO20.mMode == null) {
												EO20.mMode = new ArrayList<String>();
											}
											try {
												EO20.mMode.add(exposure.getValue().toString());
												Log.i(METHOD_NAME, "Exposure - Mode:" + exposure.getValue().toString());
											} catch (Exception e) {
												Log.i(METHOD_NAME, "Exposure - Mode:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// Exposure - Priority
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("Priority")) {
											if (EO20.mPriority == null) {
												EO20.mPriority = new ArrayList<String>();
											}
											try {
												EO20.mPriority.add(exposure.getValue().toString());
												Log.i(METHOD_NAME, "Exposure - Priority:" + exposure.getValue().toString());
											} catch (Exception e) {
												Log.e(METHOD_NAME, "Exposure - Priority:" + exposure.getValue().toString());
												e.printStackTrace();
											}
										}
									} else if (exposure.getValue() instanceof SoapObject) {
										// =================================================================================
										// Exposure - MinExposureTime
										// =================================================================================
										if (exposure.getName().equalsIgnoreCase("MinExposureTime")) {
											EO20.mMinExposureTime = new FloatRange();
											SoapObject MinExposureTime = (SoapObject) exposure.getValue();
											for (int c = 0; c < MinExposureTime.getPropertyCount(); c++) {
												PropertyInfo minExposureTime = new PropertyInfo();
												MinExposureTime.getPropertyInfo(c, minExposureTime);
												// =================================================================================
												// MinExposureTime - Min
												// =================================================================================
												if (minExposureTime.getName().equalsIgnoreCase("Min")) {
													try {
														EO20.mMinExposureTime.mMin = Float.parseFloat(minExposureTime.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MinExposureTime - Min:" + EO20.mMinExposureTime.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MinExposureTime - Min:"
																+ minExposureTime.getValue().toString());
														e.printStackTrace();
													}

												}
												// =================================================================================
												// MinExposureTime - Max
												// =================================================================================
												else if (minExposureTime.getName().equalsIgnoreCase("Max")) {
													try {
														EO20.mMinExposureTime.mMax = Float.parseFloat(minExposureTime.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MinExposureTime - Max:" + EO20.mMinExposureTime.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MinExposureTime - Max:"
																+ minExposureTime.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// Exposure - MaxExposureTime
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MaxExposureTime")) {
											EO20.mMaxExposureTime = new FloatRange();
											SoapObject MaxExposureTime = (SoapObject) exposure.getValue();
											for (int c = 0; c < MaxExposureTime.getPropertyCount(); c++) {
												PropertyInfo maxExposureTime = new PropertyInfo();
												MaxExposureTime.getPropertyInfo(c, maxExposureTime);
												// =================================================================================
												// MaxExposureTime - Min
												// =================================================================================
												if (maxExposureTime.getName().equalsIgnoreCase("Min")) {
													try {
														EO20.mMaxExposureTime.mMin = Float.parseFloat(maxExposureTime.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MaxExposureTime - Min:" + EO20.mMaxExposureTime.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MaxExposureTime - Min:"
																+ maxExposureTime.getValue().toString());
														e.printStackTrace();
													}

												}
												// =================================================================================
												// MaxExposureTime - Max
												// =================================================================================
												else if (maxExposureTime.getName().equalsIgnoreCase("Max")) {
													try {
														EO20.mMaxExposureTime.mMax = Float.parseFloat(maxExposureTime.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MaxExposureTime - Max:" + EO20.mMaxExposureTime.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MaxExposureTime - Max:"
																+ maxExposureTime.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// Exposure - MinGain
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MinGain")) {
											EO20.mMinGain = new FloatRange();
											SoapObject MinGain = (SoapObject) exposure.getValue();
											for (int c = 0; c < MinGain.getPropertyCount(); c++) {
												PropertyInfo minGain = new PropertyInfo();
												MinGain.getPropertyInfo(c, minGain);
												// =================================================================================
												// MinGain - Min
												// =================================================================================
												if (minGain.getName().equalsIgnoreCase("Min")) {
													try {
														EO20.mMinGain.mMin = Float.parseFloat(minGain.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MinGain - Min:" + EO20.mMinGain.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MinGain - Min:" + minGain.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// MinGain - Max
												// =================================================================================
												else if (minGain.getName().equalsIgnoreCase("Max")) {
													try {
														EO20.mMinGain.mMax = Float.parseFloat(minGain.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MinGain - Max:" + EO20.mMinGain.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MinGain - Max:" + minGain.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// Exposure - MaxGain
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MaxGain")) {
											EO20.mMaxGain = new FloatRange();
											SoapObject MaxGain = (SoapObject) exposure.getValue();
											for (int c = 0; c < MaxGain.getPropertyCount(); c++) {
												PropertyInfo maxGain = new PropertyInfo();
												MaxGain.getPropertyInfo(c, maxGain);
												// =================================================================================
												// MaxGain - Min
												// =================================================================================
												if (maxGain.getName().equalsIgnoreCase("Min")) {
													try {
														EO20.mMaxGain.mMin = Float.parseFloat(maxGain.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MaxGain - Min:" + EO20.mMaxGain.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MaxGain - Min:" + maxGain.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// MaxGain - Max
												// =================================================================================
												else if (maxGain.getName().equalsIgnoreCase("Max")) {
													try {
														EO20.mMaxGain.mMax = Float.parseFloat(maxGain.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MaxGain - Max:" + EO20.mMaxGain.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MaxGain - Max:" + maxGain.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// Exposure - MinIris
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MinIris")) {
											EO20.mMinIris = new FloatRange();
											SoapObject MinIris = (SoapObject) exposure.getValue();
											for (int c = 0; c < MinIris.getPropertyCount(); c++) {
												PropertyInfo minIris = new PropertyInfo();
												MinIris.getPropertyInfo(c, minIris);
												// =================================================================================
												// MinIris - Min
												// =================================================================================
												if (minIris.getName().equalsIgnoreCase("Min")) {
													try {
														EO20.mMinIris.mMin = Float.parseFloat(minIris.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MinIris - Min:" + EO20.mMinIris.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MinIris - Min:" + minIris.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// MinIris - Max
												// =================================================================================
												else if (minIris.getName().equalsIgnoreCase("Max")) {
													try {
														EO20.mMinIris.mMax = Float.parseFloat(minIris.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MinIris - Max:" + EO20.mMinIris.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MinIris - Max:" + minIris.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// Exposure - MaxIris
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("MaxIris")) {
											EO20.mMaxIris = new FloatRange();
											SoapObject MaxIris = (SoapObject) exposure.getValue();
											for (int c = 0; c < MaxIris.getPropertyCount(); c++) {
												PropertyInfo maxIris = new PropertyInfo();
												MaxIris.getPropertyInfo(c, maxIris);
												// =================================================================================
												// MaxIris - Min
												// =================================================================================
												if (maxIris.getName().equalsIgnoreCase("Min")) {
													try {
														EO20.mMaxIris.mMin = Float.parseFloat(maxIris.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MaxIris - Min:" + EO20.mMaxIris.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MaxIris - Min:" + maxIris.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// MaxIris - Max
												// =================================================================================
												else if (maxIris.getName().equalsIgnoreCase("Max")) {
													try {
														EO20.mMaxIris.mMax = Float.parseFloat(maxIris.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - MaxIris - Max:" + EO20.mMaxIris.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - MaxIris - Max:" + maxIris.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// Exposure - ExposureTime
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("ExposureTime")) {
											EO20.mExposureTime = new FloatRange();
											SoapObject ExposureTime = (SoapObject) exposure.getValue();
											for (int c = 0; c < ExposureTime.getPropertyCount(); c++) {
												PropertyInfo exposureTime = new PropertyInfo();
												ExposureTime.getPropertyInfo(c, exposureTime);
												// =================================================================================
												// ExposureTime - Min
												// =================================================================================
												if (exposureTime.getName().equalsIgnoreCase("Min")) {
													try {
														EO20.mExposureTime.mMin = Float.parseFloat(exposureTime.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - ExposureTime - Min:" + EO20.mExposureTime.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - ExposureTime - Min:" + exposureTime.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// ExposureTime - Max
												// =================================================================================
												else if (exposureTime.getName().equalsIgnoreCase("Max")) {
													try {
														EO20.mExposureTime.mMax = Float.parseFloat(exposureTime.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - ExposureTime - Max:" + EO20.mExposureTime.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - ExposureTime - Max:" + exposureTime.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// Exposure - Gain
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("Gain")) {
											EO20.mGain = new FloatRange();
											SoapObject Gain = (SoapObject) exposure.getValue();
											for (int c = 0; c < Gain.getPropertyCount(); c++) {
												PropertyInfo gain = new PropertyInfo();
												Gain.getPropertyInfo(c, gain);
												// =================================================================================
												// Gain - Min
												// =================================================================================
												if (gain.getName().equalsIgnoreCase("Min")) {
													try {
														EO20.mGain.mMin = Float.parseFloat(gain.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - Gain - Min:" + EO20.mGain.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - Gain - Min:" + gain.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// Gain - Max
												// =================================================================================
												else if (gain.getName().equalsIgnoreCase("Max")) {
													try {
														EO20.mGain.mMax = Float.parseFloat(gain.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - Gain - Max:" + EO20.mGain.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - Gain - Max:" + gain.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// Exposure - Iris
										// =================================================================================
										else if (exposure.getName().equalsIgnoreCase("Iris")) {
											EO20.mIris = new FloatRange();
											SoapObject Iris = (SoapObject) exposure.getValue();
											for (int c = 0; c < Iris.getPropertyCount(); c++) {
												PropertyInfo iris = new PropertyInfo();
												Iris.getPropertyInfo(c, iris);
												// =================================================================================
												// Iris - Min
												// =================================================================================
												if (iris.getName().equalsIgnoreCase("Min")) {
													try {
														EO20.mIris.mMin = Float.parseFloat(iris.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - Iris - Min:" + EO20.mIris.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - Iris - Min:" + iris.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// Iris - Max
												// =================================================================================
												else if (iris.getName().equalsIgnoreCase("Max")) {
													try {
														EO20.mIris.mMax = Float.parseFloat(iris.getValue().toString());
														Log.i(METHOD_NAME, "Exposure - Iris - Max:" + EO20.mIris.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Exposure - Iris - Max:" + iris.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
									}
								}
								mImagingOptions.mExposure = EO20;
							}
							// =================================================================================
							// Focus
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Focus")) {
								FocusOptions20 FO20 = new FocusOptions20();
								SoapObject Focus = (SoapObject) subSoapObject.getValue();
								for (int count = 0; count < Focus.getPropertyCount(); count++) {
									PropertyInfo focus = new PropertyInfo();
									Focus.getPropertyInfo(count, focus);
									if (focus.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// Focus - AutoFocusModes
										// =================================================================================
										if (focus.getName().equalsIgnoreCase("AutoFocusModes")) {
											if (FO20.mAutoFocusModes == null) {
												FO20.mAutoFocusModes = new ArrayList<String>();
											}
											try {
												FO20.mAutoFocusModes.add(focus.getValue().toString());
												Log.i(METHOD_NAME, "Focus - AutoFocusModes:" + focus.getValue().toString());
											} catch (Exception e) {
												Log.e(METHOD_NAME, "Focus - AutoFocusModes:" + focus.getValue().toString());
												e.printStackTrace();
											}
										}
									} else if (focus.getValue() instanceof SoapObject) {
										// =================================================================================
										// Focus - DefaultSpeed
										// =================================================================================
										if (focus.getName().equalsIgnoreCase("DefaultSpeed")) {
											FO20.mDefaultSpeed = new FloatRange();
											SoapObject DefaultSpeed = (SoapObject) focus.getValue();
											for (int c = 0; c < DefaultSpeed.getPropertyCount(); c++) {
												PropertyInfo defaultSpeed = new PropertyInfo();
												DefaultSpeed.getPropertyInfo(c, defaultSpeed);
												// =================================================================================
												// DefaultSpeed - Min
												// =================================================================================
												if (defaultSpeed.getName().equalsIgnoreCase("Min")) {
													try {
														FO20.mDefaultSpeed.mMin = Float.parseFloat(defaultSpeed.getValue().toString());
														Log.i(METHOD_NAME, "Focus - DefaultSpeed - Min:" + FO20.mDefaultSpeed.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Focus - DefaultSpeed - Min:" + defaultSpeed.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// DefaultSpeed - Max
												// =================================================================================
												else if (defaultSpeed.getName().equalsIgnoreCase("Max")) {
													try {
														FO20.mDefaultSpeed.mMax = Float.parseFloat(defaultSpeed.getValue().toString());
														Log.i(METHOD_NAME, "Focus - DefaultSpeed - Max:" + FO20.mDefaultSpeed.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Focus - DefaultSpeed - Max:" + defaultSpeed.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// Focus - NearLimit
										// =================================================================================
										else if (focus.getName().equalsIgnoreCase("NearLimit")) {
											FO20.mNearLimit = new FloatRange();
											SoapObject NearLimit = (SoapObject) focus.getValue();
											for (int c = 0; c < NearLimit.getPropertyCount(); c++) {
												PropertyInfo nearLimit = new PropertyInfo();
												NearLimit.getPropertyInfo(c, nearLimit);
												// =================================================================================
												// NearLimit - Min
												// =================================================================================
												if (nearLimit.getName().equalsIgnoreCase("Min")) {
													try {
														FO20.mNearLimit.mMin = Float.parseFloat(nearLimit.getValue().toString());
														Log.i(METHOD_NAME, "Focus - NearLimit - Min:" + FO20.mNearLimit.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Focus - NearLimit - Min:" + nearLimit.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// NearLimit - Max
												// =================================================================================
												else if (nearLimit.getName().equalsIgnoreCase("Max")) {
													try {
														FO20.mNearLimit.mMax = Float.parseFloat(nearLimit.getValue().toString());
														Log.i(METHOD_NAME, "Focus - NearLimit - Max:" + FO20.mNearLimit.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Focus - NearLimit - Max:" + nearLimit.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// Focus - FarLimit
										// =================================================================================
										else if (focus.getName().equalsIgnoreCase("FarLimit")) {
											FO20.mFarLimit = new FloatRange();
											SoapObject FarLimit = (SoapObject) focus.getValue();
											for (int c = 0; c < FarLimit.getPropertyCount(); c++) {
												PropertyInfo farLimit = new PropertyInfo();
												FarLimit.getPropertyInfo(c, farLimit);
												// =================================================================================
												// FarLimit - Min
												// =================================================================================
												if (farLimit.getName().equalsIgnoreCase("Min")) {
													try {
														FO20.mFarLimit.mMin = Float.parseFloat(farLimit.getValue().toString());
														Log.i(METHOD_NAME, "Focus - FarLimit - Min:" + FO20.mFarLimit.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Focus - FarLimit - Min:" + farLimit.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// FarLimit - Max
												// =================================================================================
												else if (farLimit.getName().equalsIgnoreCase("Max")) {
													try {
														FO20.mFarLimit.mMax = Float.parseFloat(farLimit.getValue().toString());
														Log.i(METHOD_NAME, "Focus - FarLimit - Max:" + FO20.mFarLimit.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "Focus - FarLimit - Max:" + farLimit.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
									}
								}
								mImagingOptions.mFocus = FO20;
							}
							// =================================================================================
							// IrCutFilterModes
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("IrCutFilterModes")) {
								try {
									mImagingOptions.mIrCutFilterModes = utils.toWsdlString(subSoapObject.getValue().toString());
									Log.i(METHOD_NAME, "IrCutFilterModes :" + mImagingOptions.mIrCutFilterModes);
								} catch (Exception e) {
									Log.e(METHOD_NAME, "IrCutFilterModes :" + subSoapObject.getValue().toString());
									e.printStackTrace();
								}
							}
							// =================================================================================
							// Sharpness
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Sharpness")) {
								FloatRange SP = new FloatRange();
								SoapObject Sharpness = (SoapObject) subSoapObject.getValue();
								for (int c = 0; c < Sharpness.getPropertyCount(); c++) {
									PropertyInfo sharpness = new PropertyInfo();
									Sharpness.getPropertyInfo(c, sharpness);
									// =================================================================================
									// Sharpness - Min
									// =================================================================================
									if (sharpness.getName().equalsIgnoreCase("Min")) {
										try {
											SP.mMin = Float.parseFloat(sharpness.getValue().toString());
											Log.i(METHOD_NAME, "Sharpness - Min:" + SP.mMin);
										} catch (Exception e) {
											Log.e(METHOD_NAME, "Sharpness - Min:" + sharpness.getValue().toString());
											e.printStackTrace();
										}

									}
									// =================================================================================
									// Sharpness - Max
									// =================================================================================
									else if (sharpness.getName().equalsIgnoreCase("Max")) {
										try {
											SP.mMax = Float.parseFloat(sharpness.getValue().toString());
											Log.i(METHOD_NAME, "Sharpness - Max:" + SP.mMax);
										} catch (Exception e) {
											Log.e(METHOD_NAME, "Sharpness - Max:" + sharpness.getValue().toString());
											e.printStackTrace();
										}
									}
								}
								mImagingOptions.mSharpness = SP;
							}

							// =================================================================================
							// WideDynamicRange
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("WideDynamicRange")) {
								WideDynamicRangeOptions20 WDRO20 = new WideDynamicRangeOptions20();
								SoapObject WideDynamicRange = (SoapObject) subSoapObject.getValue();
								for (int count = 0; count < WideDynamicRange.getPropertyCount(); count++) {
									PropertyInfo wideDynamicRange = new PropertyInfo();
									WideDynamicRange.getPropertyInfo(count, wideDynamicRange);
									if (wideDynamicRange.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// WideDynamicRange - Mode
										// =================================================================================
										if (wideDynamicRange.getName().equalsIgnoreCase("Mode")) {
											if (WDRO20.mMode == null) {
												WDRO20.mMode = new ArrayList<String>();
											}
											try {
												WDRO20.mMode.add(wideDynamicRange.getValue().toString());
												Log.i(METHOD_NAME, "WideDynamicRange - Mode:" + wideDynamicRange.getValue().toString());
											} catch (Exception e) {
												Log.e(METHOD_NAME, "WideDynamicRange - Mode:" + wideDynamicRange.getValue().toString());
												e.printStackTrace();
											}
										}
									} else if (wideDynamicRange.getValue() instanceof SoapObject) {
										// =================================================================================
										// WideDynamicRange - Level
										// =================================================================================
										if (wideDynamicRange.getName().equalsIgnoreCase("Level")) {
											WDRO20.mLevel = new FloatRange();
											SoapObject Level = (SoapObject) wideDynamicRange.getValue();
											for (int c = 0; c < Level.getPropertyCount(); c++) {
												PropertyInfo level = new PropertyInfo();
												Level.getPropertyInfo(c, level);
												// =================================================================================
												// Level - Min
												// =================================================================================
												if (level.getName().equalsIgnoreCase("Min")) {
													try {
														WDRO20.mLevel.mMin = Float.parseFloat(level.getValue().toString());
														Log.i(METHOD_NAME, "WideDynamicRange - Level - Min:" + WDRO20.mLevel.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "WideDynamicRange - Level - Min:" + level.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// Level - Max
												// =================================================================================
												else if (level.getName().equalsIgnoreCase("Max")) {
													try {
														WDRO20.mLevel.mMax = Float.parseFloat(level.getValue().toString());
														Log.i(METHOD_NAME, "WideDynamicRange - Level - Max:" + WDRO20.mLevel.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "WideDynamicRange - Level - Max:" + level.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
									}
								}
								mImagingOptions.mWideDynamicRange = WDRO20;
							}
							// =================================================================================
							// WhiteBalance
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("WhiteBalance")) {
								WhiteBalanceOptions20 WBO20 = new WhiteBalanceOptions20();
								SoapObject WhiteBalance = (SoapObject) subSoapObject.getValue();
								for (int count = 0; count < WhiteBalance.getPropertyCount(); count++) {
									PropertyInfo whiteBalance = new PropertyInfo();
									WhiteBalance.getPropertyInfo(count, whiteBalance);
									if (whiteBalance.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// WhiteBalance - Mode
										// =================================================================================
										if (whiteBalance.getName().equalsIgnoreCase("Mode")) {
											if (WBO20.mMode == null) {
												WBO20.mMode = new ArrayList<String>();
											}
											try {
												WBO20.mMode.add(whiteBalance.getValue().toString());
												Log.i(METHOD_NAME, "WhiteBalance - Mode:" + whiteBalance.getValue().toString());
											} catch (Exception e) {
												Log.e(METHOD_NAME, "WhiteBalance - Mode:" + whiteBalance.getValue().toString());
												e.printStackTrace();
											}
										}
									} else if (whiteBalance.getValue() instanceof SoapObject) {
										// =================================================================================
										// WhiteBalance - YrGain
										// =================================================================================
										if (whiteBalance.getName().equalsIgnoreCase("YrGain")) {
											WBO20.mYrGain = new FloatRange();
											SoapObject YrGain = (SoapObject) whiteBalance.getValue();
											for (int c = 0; c < YrGain.getPropertyCount(); c++) {
												PropertyInfo yrGain = new PropertyInfo();
												YrGain.getPropertyInfo(c, yrGain);
												// =================================================================================
												// DefaultSpeed - Min
												// =================================================================================
												if (yrGain.getName().equalsIgnoreCase("Min")) {
													try {
														WBO20.mYrGain.mMin = Float.parseFloat(yrGain.getValue().toString());
														Log.i(METHOD_NAME, "WhiteBalance - YrGain - Min:" + WBO20.mYrGain.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "WhiteBalance - YrGain - Min:" + yrGain.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// DefaultSpeed - Max
												// =================================================================================
												else if (yrGain.getName().equalsIgnoreCase("Max")) {
													try {
														WBO20.mYrGain.mMax = Float.parseFloat(yrGain.getValue().toString());
														Log.i(METHOD_NAME, "WhiteBalance - YrGain - Max:" + WBO20.mYrGain.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "WhiteBalance - YrGain - Max:" + yrGain.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// WhiteBalance - YbGain
										// =================================================================================
										else if (whiteBalance.getName().equalsIgnoreCase("YbGain")) {
											WBO20.mYbGain = new FloatRange();
											SoapObject YbGain = (SoapObject) whiteBalance.getValue();
											for (int c = 0; c < YbGain.getPropertyCount(); c++) {
												PropertyInfo ybGain = new PropertyInfo();
												YbGain.getPropertyInfo(c, ybGain);
												// =================================================================================
												// YbGain - Min
												// =================================================================================
												if (ybGain.getName().equalsIgnoreCase("Min")) {
													try {
														WBO20.mYbGain.mMin = Float.parseFloat(ybGain.getValue().toString());
														Log.i(METHOD_NAME, "WhiteBalance - YbGain - Min:" + WBO20.mYbGain.mMin);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "WhiteBalance - YbGain - Min:" + ybGain.getValue().toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// YbGain - Max
												// =================================================================================
												else if (ybGain.getName().equalsIgnoreCase("Max")) {
													try {
														WBO20.mYbGain.mMax = Float.parseFloat(ybGain.getValue().toString());
														Log.i(METHOD_NAME, "WhiteBalance - YbGain - Max:" + WBO20.mYbGain.mMax);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "WhiteBalance - YbGain - Max:" + ybGain.getValue().toString());
														e.printStackTrace();
													}
												}
											}
										}
									}
								}
								mImagingOptions.mWhiteBalance = WBO20;
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
