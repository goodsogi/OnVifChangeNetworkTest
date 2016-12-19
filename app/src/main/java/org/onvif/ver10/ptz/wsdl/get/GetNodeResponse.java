package org.onvif.ver10.ptz.wsdl.get;

import android.util.Log;

import org.ksoap2.serialization.AttributeInfo;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.onvif.ver10.schema.FloatRange;
import org.onvif.ver10.schema.PTZSpaces;
import org.onvif.ver10.schema.Space1DDescription;
import org.onvif.ver10.schema.Space2DDescription;
import org.onvif.ver10.schema.nativeParcel.PTZNode;

public class GetNodeResponse {
	private final static String METHOD_NAME = GetNodeResponse.class.getSimpleName();

	public PTZNode mPTZNode;

	public GetNodeResponse(SoapObject obj) {
		try {
			if (obj != null && obj.getName().equals(METHOD_NAME)) {
				mPTZNode = new PTZNode();

				for (int i = 0; i < obj.getPropertyCount(); i++) {
					PropertyInfo root = new PropertyInfo();
					obj.getPropertyInfo(i, root);
					// =================================================================================
					// PTZNode
					// =================================================================================
					if (root.getName().equalsIgnoreCase("PTZNode")) {
						SoapObject PTZNode = (SoapObject) root.getValue();
						for (int a = 0; a < PTZNode.getAttributeCount(); a++) {
							AttributeInfo info = new AttributeInfo();
							PTZNode.getAttributeInfo(a, info);
							// =================================================================================
							// token
							// =================================================================================
							if (info.getName().equalsIgnoreCase("token")) {
								mPTZNode.mToken = info.getValue().toString();
								Log.i(METHOD_NAME, "PTZNode - token:" + mPTZNode.mToken);
							}
						}

						for (int j = 0; j < PTZNode.getPropertyCount(); j++) {
							PropertyInfo subSoapObject = new PropertyInfo();
							PTZNode.getPropertyInfo(j, subSoapObject);
							// =================================================================================
							// Name
							// =================================================================================
							if (subSoapObject.getName().equalsIgnoreCase("Name")) {
								mPTZNode.mName = subSoapObject.getValue().toString();
								Log.i(METHOD_NAME, "PTZNode - Name:" + mPTZNode.mName);
							}

							// =================================================================================
							// SupportedPTZSpaces
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("SupportedPTZSpaces")) {

								mPTZNode.mSupportedPTZSpaces = new PTZSpaces();

								SoapObject SupportedPTZSpaces = (SoapObject) subSoapObject.getValue();

								for (int count = 0; count < SupportedPTZSpaces.getPropertyCount(); count++) {
									PropertyInfo supportedPTZSpaces = new PropertyInfo();
									SupportedPTZSpaces.getPropertyInfo(count, supportedPTZSpaces);

									if (supportedPTZSpaces.getValue() instanceof SoapPrimitive) {
										Log.i(METHOD_NAME, "SupportedPTZSpaces - SoapPrimitive:" + supportedPTZSpaces.getValue().toString());
									}

									else if (supportedPTZSpaces.getValue() instanceof SoapObject) {
										// =================================================================================
										// SupportedPTZSpaces - AbsolutePanTiltPositionSpace
										// =================================================================================
										if (supportedPTZSpaces.getName().equalsIgnoreCase("AbsolutePanTiltPositionSpace")) {
											mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace = new Space2DDescription();
											Log.i(METHOD_NAME, "SupportedPTZSpaces - AbsolutePanTiltPositionSpace");

											SoapObject AbsolutePanTiltPositionSpace = (SoapObject) supportedPTZSpaces.getValue();
											for (int r = 0; r < AbsolutePanTiltPositionSpace.getPropertyCount(); r++) {
												PropertyInfo absolutePanTiltPositionSpace = new PropertyInfo();
												AbsolutePanTiltPositionSpace.getPropertyInfo(r, absolutePanTiltPositionSpace);

												// =================================================================================
												// AbsolutePanTiltPositionSpace - URI
												// =================================================================================
												if (absolutePanTiltPositionSpace.getName().equalsIgnoreCase("URI")) {
													mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mUri = absolutePanTiltPositionSpace
															.getValue().toString();
													Log.i(METHOD_NAME, "SupportedPTZSpaces - AbsolutePanTiltPositionSpace - URI:"
															+ mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mUri);
												}
												// =================================================================================
												// AbsolutePanTiltPositionSpace - XRange
												// =================================================================================
												else if (absolutePanTiltPositionSpace.getName().equalsIgnoreCase("XRange")) {
													mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mXRange = new FloatRange();
													SoapObject XRange = (SoapObject) absolutePanTiltPositionSpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// XRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mXRange.mMin = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - AbsolutePanTiltPositionSpace - XRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mXRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - AbsolutePanTiltPositionSpace - XRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mXRange.mMin);
															}
														}
														// =================================================================================
														// XRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mXRange.mMax = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - AbsolutePanTiltPositionSpace - XRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mXRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - AbsolutePanTiltPositionSpace - XRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mXRange.mMax);
															}
														}
													}
												}
												// =================================================================================
												// AbsolutePanTiltPositionSpace - YRange
												// =================================================================================
												else if (absolutePanTiltPositionSpace.getName().equalsIgnoreCase("YRange")) {
													mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mYRange = new FloatRange();
													SoapObject XRange = (SoapObject) absolutePanTiltPositionSpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// YRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mYRange.mMin = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - AbsolutePanTiltPositionSpace - YRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mYRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - AbsolutePanTiltPositionSpace - YRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mYRange.mMin);
															}
														}
														// =================================================================================
														// YRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mYRange.mMax = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - AbsolutePanTiltPositionSpace - YRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mYRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - AbsolutePanTiltPositionSpace - YRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mAbsolutePanTiltPositionSpace.mYRange.mMax);
															}
														}
													}
												}
											}
										}
										// =================================================================================
										// SupportedPTZSpaces - ZoomSpeedSpace
										// =================================================================================
										else if (supportedPTZSpaces.getName().equalsIgnoreCase("ZoomSpeedSpace")) {
											mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace = new Space1DDescription();
											Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace");

											SoapObject ZoomSpeedSpace = (SoapObject) supportedPTZSpaces.getValue();
											for (int r = 0; r < ZoomSpeedSpace.getPropertyCount(); r++) {
												PropertyInfo absoluteZoomPositionSpace = new PropertyInfo();
												ZoomSpeedSpace.getPropertyInfo(r, absoluteZoomPositionSpace);

												// =================================================================================
												// ZoomSpeedSpace - URI
												// =================================================================================
												if (absoluteZoomPositionSpace.getName().equalsIgnoreCase("URI")) {
													mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mUri = absoluteZoomPositionSpace.getValue()
															.toString();
													Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - URI:"
															+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mUri);
												}
												// =================================================================================
												// ZoomSpeedSpace - XRange
												// =================================================================================
												else if (absoluteZoomPositionSpace.getName().equalsIgnoreCase("XRange")) {
													mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange = new FloatRange();
													SoapObject XRange = (SoapObject) absoluteZoomPositionSpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// XRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin = Float.parseFloat(xRange
																		.getValue().toString());
																Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Min:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Min:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin);
															}
														}
														// =================================================================================
														// XRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax = Float.parseFloat(xRange
																		.getValue().toString());
																Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Max:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Max:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax);
															}
														}
													}
												}
											}
										}

										// =================================================================================
										// SupportedPTZSpaces - ContinuousPanTiltVelocitySpace
										// =================================================================================
										else if (supportedPTZSpaces.getName().equalsIgnoreCase("ContinuousPanTiltVelocitySpace")) {
											mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace = new Space2DDescription();
											Log.i(METHOD_NAME, "SupportedPTZSpaces - ContinuousPanTiltVelocitySpace");

											SoapObject ContinuousPanTiltVelocitySpace = (SoapObject) supportedPTZSpaces.getValue();
											for (int r = 0; r < ContinuousPanTiltVelocitySpace.getPropertyCount(); r++) {
												PropertyInfo relativePanTiltTranslationSpace = new PropertyInfo();
												ContinuousPanTiltVelocitySpace.getPropertyInfo(r, relativePanTiltTranslationSpace);

												// =================================================================================
												// ContinuousPanTiltVelocitySpace - URI
												// =================================================================================
												if (relativePanTiltTranslationSpace.getName().equalsIgnoreCase("URI")) {
													mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mUri = relativePanTiltTranslationSpace
															.getValue().toString();
													Log.i(METHOD_NAME, "SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - URI:"
															+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mUri);
												}
												// =================================================================================
												// ContinuousPanTiltVelocitySpace - XRange
												// =================================================================================
												else if (relativePanTiltTranslationSpace.getName().equalsIgnoreCase("XRange")) {
													mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange = new FloatRange();
													SoapObject XRange = (SoapObject) relativePanTiltTranslationSpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// XRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMin = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - XRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - XRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMin);
															}
														}
														// =================================================================================
														// XRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMax = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - XRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - XRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMax);
															}
														}
													}
												}
												// =================================================================================
												// ContinuousPanTiltVelocitySpace - YRange
												// =================================================================================
												else if (relativePanTiltTranslationSpace.getName().equalsIgnoreCase("YRange")) {
													mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange = new FloatRange();
													SoapObject XRange = (SoapObject) relativePanTiltTranslationSpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// YRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMin = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - YRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - YRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMin);
															}
														}
														// =================================================================================
														// YRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMax = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - YRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - YRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMax);
															}
														}
													}
												}
											}
										}
										// =================================================================================
										// SupportedPTZSpaces - ZoomSpeedSpace
										// =================================================================================
										else if (supportedPTZSpaces.getName().equalsIgnoreCase("ZoomSpeedSpace")) {
											mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace = new Space1DDescription();
											Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace");

											SoapObject ZoomSpeedSpace = (SoapObject) supportedPTZSpaces.getValue();
											for (int r = 0; r < ZoomSpeedSpace.getPropertyCount(); r++) {
												PropertyInfo relativeZoomTranslationSpace = new PropertyInfo();
												ZoomSpeedSpace.getPropertyInfo(r, relativeZoomTranslationSpace);

												// =================================================================================
												// ZoomSpeedSpace - URI
												// =================================================================================
												if (relativeZoomTranslationSpace.getName().equalsIgnoreCase("URI")) {
													mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mUri = relativeZoomTranslationSpace.getValue()
															.toString();
													Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - URI:"
															+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mUri);
												}
												// =================================================================================
												// ZoomSpeedSpace - XRange
												// =================================================================================
												else if (relativeZoomTranslationSpace.getName().equalsIgnoreCase("XRange")) {
													mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange = new FloatRange();
													SoapObject XRange = (SoapObject) relativeZoomTranslationSpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// XRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin = Float.parseFloat(xRange
																		.getValue().toString());
																Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Min:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Min:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin);
															}
														}
														// =================================================================================
														// XRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax = Float.parseFloat(xRange
																		.getValue().toString());
																Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Max:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Max:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax);
															}
														}
													}
												}
											}
										}

										// =================================================================================
										// SupportedPTZSpaces - ContinuousPanTiltVelocitySpace
										// =================================================================================
										else if (supportedPTZSpaces.getName().equalsIgnoreCase("ContinuousPanTiltVelocitySpace")) {
											mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace = new Space2DDescription();
											Log.i(METHOD_NAME, "SupportedPTZSpaces - ContinuousPanTiltVelocitySpace");

											SoapObject ContinuousPanTiltVelocitySpace = (SoapObject) supportedPTZSpaces.getValue();
											for (int r = 0; r < ContinuousPanTiltVelocitySpace.getPropertyCount(); r++) {
												PropertyInfo continuousPanTiltVelocitySpace = new PropertyInfo();
												ContinuousPanTiltVelocitySpace.getPropertyInfo(r, continuousPanTiltVelocitySpace);

												// =================================================================================
												// ContinuousPanTiltVelocitySpace - URI
												// =================================================================================
												if (continuousPanTiltVelocitySpace.getName().equalsIgnoreCase("URI")) {
													mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mUri = continuousPanTiltVelocitySpace
															.getValue().toString();
													Log.i(METHOD_NAME, "SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - URI:"
															+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mUri);
												}
												// =================================================================================
												// ContinuousPanTiltVelocitySpace - XRange
												// =================================================================================
												else if (continuousPanTiltVelocitySpace.getName().equalsIgnoreCase("XRange")) {
													mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange = new FloatRange();
													SoapObject XRange = (SoapObject) continuousPanTiltVelocitySpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// XRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMin = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - XRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - XRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMin);
															}
														}
														// =================================================================================
														// XRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMax = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - XRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - XRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mXRange.mMax);
															}
														}
													}
												}
												// =================================================================================
												// ContinuousPanTiltVelocitySpace - YRange
												// =================================================================================
												else if (continuousPanTiltVelocitySpace.getName().equalsIgnoreCase("YRange")) {
													mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange = new FloatRange();
													SoapObject XRange = (SoapObject) continuousPanTiltVelocitySpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// YRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMin = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - YRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - YRange - Min:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMin);
															}
														}
														// =================================================================================
														// YRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMax = Float
																		.parseFloat(xRange.getValue().toString());
																Log.i(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - YRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME,
																		"SupportedPTZSpaces - ContinuousPanTiltVelocitySpace - YRange - Max:"
																				+ mPTZNode.mSupportedPTZSpaces.mContinuousPanTiltVelocitySpace.mYRange.mMax);
															}
														}
													}
												}
											}
										}
										// =================================================================================
										// SupportedPTZSpaces - ZoomSpeedSpace
										// =================================================================================
										else if (supportedPTZSpaces.getName().equalsIgnoreCase("ZoomSpeedSpace")) {
											mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace = new Space1DDescription();
											Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace");

											SoapObject ZoomSpeedSpace = (SoapObject) supportedPTZSpaces.getValue();
											for (int r = 0; r < ZoomSpeedSpace.getPropertyCount(); r++) {
												PropertyInfo continuousZoomVelocitySpace = new PropertyInfo();
												ZoomSpeedSpace.getPropertyInfo(r, continuousZoomVelocitySpace);

												// =================================================================================
												// ZoomSpeedSpace - URI
												// =================================================================================
												if (continuousZoomVelocitySpace.getName().equalsIgnoreCase("URI")) {
													mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mUri = continuousZoomVelocitySpace.getValue()
															.toString();
													Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - URI:"
															+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mUri);
												}
												// =================================================================================
												// ZoomSpeedSpace - XRange
												// =================================================================================
												else if (continuousZoomVelocitySpace.getName().equalsIgnoreCase("XRange")) {
													mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange = new FloatRange();
													SoapObject XRange = (SoapObject) continuousZoomVelocitySpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// XRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin = Float.parseFloat(xRange
																		.getValue().toString());
																Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Min:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Min:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin);
															}
														}
														// =================================================================================
														// XRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax = Float.parseFloat(xRange
																		.getValue().toString());
																Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Max:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Max:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax);
															}
														}
													}
												}
											}
										}

										// =================================================================================
										// SupportedPTZSpaces - ZoomSpeedSpace
										// =================================================================================
										else if (supportedPTZSpaces.getName().equalsIgnoreCase("ZoomSpeedSpace")) {
											mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace = new Space1DDescription();
											Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace");

											SoapObject ZoomSpeedSpace = (SoapObject) supportedPTZSpaces.getValue();
											for (int r = 0; r < ZoomSpeedSpace.getPropertyCount(); r++) {
												PropertyInfo panTiltSpeedSpace = new PropertyInfo();
												ZoomSpeedSpace.getPropertyInfo(r, panTiltSpeedSpace);

												// =================================================================================
												// ZoomSpeedSpace - URI
												// =================================================================================
												if (panTiltSpeedSpace.getName().equalsIgnoreCase("URI")) {
													mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mUri = panTiltSpeedSpace.getValue().toString();
													Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - URI:"
															+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mUri);
												}
												// =================================================================================
												// ZoomSpeedSpace - XRange
												// =================================================================================
												else if (panTiltSpeedSpace.getName().equalsIgnoreCase("XRange")) {
													mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange = new FloatRange();
													SoapObject XRange = (SoapObject) panTiltSpeedSpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// XRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin = Float.parseFloat(xRange
																		.getValue().toString());
																Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Min:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Min:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin);
															}
														}
														// =================================================================================
														// XRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax = Float.parseFloat(xRange
																		.getValue().toString());
																Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Max:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Max:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax);
															}
														}
													}
												}
											}
										}

										// =================================================================================
										// SupportedPTZSpaces - ZoomSpeedSpace
										// =================================================================================
										else if (supportedPTZSpaces.getName().equalsIgnoreCase("ZoomSpeedSpace")) {
											mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace = new Space1DDescription();
											Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace");

											SoapObject ZoomSpeedSpace = (SoapObject) supportedPTZSpaces.getValue();
											for (int r = 0; r < ZoomSpeedSpace.getPropertyCount(); r++) {
												PropertyInfo zoomSpeedSpace = new PropertyInfo();
												ZoomSpeedSpace.getPropertyInfo(r, zoomSpeedSpace);

												// =================================================================================
												// ZoomSpeedSpace - URI
												// =================================================================================
												if (zoomSpeedSpace.getName().equalsIgnoreCase("URI")) {
													mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mUri = zoomSpeedSpace.getValue().toString();
													Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - URI:"
															+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mUri);
												}
												// =================================================================================
												// ZoomSpeedSpace - XRange
												// =================================================================================
												else if (zoomSpeedSpace.getName().equalsIgnoreCase("XRange")) {
													mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange = new FloatRange();
													SoapObject XRange = (SoapObject) zoomSpeedSpace.getValue();
													for (int x = 0; x < XRange.getPropertyCount(); x++) {
														PropertyInfo xRange = new PropertyInfo();
														XRange.getPropertyInfo(x, xRange);
														// =================================================================================
														// XRange - Min
														// =================================================================================
														if (xRange.getName().equalsIgnoreCase("Min")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin = Float.parseFloat(xRange
																		.getValue().toString());
																Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Min:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin);
															} catch (Exception e) {
																Log.e(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Min:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMin);
															}
														}
														// =================================================================================
														// XRange - Max
														// =================================================================================
														else if (xRange.getName().equalsIgnoreCase("Max")) {
															try {
																mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax = Float.parseFloat(xRange
																		.getValue().toString());
																Log.i(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Max:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax);
															} catch (Exception e) {
																Log.e(METHOD_NAME, "SupportedPTZSpaces - ZoomSpeedSpace - XRange - Max:"
																		+ mPTZNode.mSupportedPTZSpaces.mZoomSpeedSpace.mXRange.mMax);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							// =================================================================================
							// MaximumNumberOfPresets
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("MaximumNumberOfPresets")) {
								try {
									mPTZNode.mMaximumNumberOfPresets = Integer.parseInt(subSoapObject.getValue().toString());
									Log.i(METHOD_NAME, "SupportedPTZSpaces - MaximumNumberOfPresets:" + mPTZNode.mMaximumNumberOfPresets);
								} catch (Exception e) {
									Log.e(METHOD_NAME, "SupportedPTZSpaces - MaximumNumberOfPresets:" + e.toString());
									e.printStackTrace();
								}
							}
							// =================================================================================
							// HomeSupported
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("HomeSupported")) {
								try {
									mPTZNode.mHomeSupported = Boolean.parseBoolean(subSoapObject.getValue().toString()) ? 1 : 0;
									Log.i(METHOD_NAME, "SupportedPTZSpaces - HomeSupported:" + mPTZNode.mHomeSupported);
								} catch (Exception e) {
									Log.e(METHOD_NAME, "SupportedPTZSpaces - HomeSupported:" + e.toString());
									e.printStackTrace();
								}
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
