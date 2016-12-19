package org.onvif.ver10.media.wsdl.get;

import android.util.Log;

import org.ksoap2.serialization.AttributeInfo;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.onvif.ver10.schema.FloatRange;
import org.onvif.ver10.schema.H264Configuration;
import org.onvif.ver10.schema.IPAddress;
import org.onvif.ver10.schema.IntRectangle;
import org.onvif.ver10.schema.Mpeg4Configuration;
import org.onvif.ver10.schema.MulticastConfiguration;
import org.onvif.ver10.schema.PanTiltLimits;
import org.onvif.ver10.schema.Space1DDescription;
import org.onvif.ver10.schema.Space2DDescription;
import org.onvif.ver10.schema.Vector1D;
import org.onvif.ver10.schema.Vector2D;
import org.onvif.ver10.schema.VideoRateControl;
import org.onvif.ver10.schema.VideoResolution;
import org.onvif.ver10.schema.ZoomLimits;
import org.onvif.ver10.schema.nativeParcel.PTZConfiguration;
import org.onvif.ver10.schema.nativeParcel.PTZSpeed;
import org.onvif.ver10.schema.nativeParcel.Profile;
import org.onvif.ver10.schema.nativeParcel.VideoEncoderConfiguration;
import org.onvif.ver10.schema.nativeParcel.VideoSourceConfiguration;
import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class GetProfilesResponse {
	private final static String METHOD_NAME = GetProfilesResponse.class.getSimpleName();

	public ArrayList<Profile> mProfiles;

	public GetProfilesResponse(SoapObject obj) {
		try {
			if (obj != null && obj.getName().equals(METHOD_NAME)) {
				mProfiles = new ArrayList<Profile>();
				for (int i = 0; i < obj.getPropertyCount(); i++) {
					PropertyInfo root = new PropertyInfo();
					obj.getPropertyInfo(i, root);
					// =================================================================================
					// Profiles
					// =================================================================================
					if (root.getName().equalsIgnoreCase("Profiles")) {
						SoapObject Profiles = (SoapObject) root.getValue();
						Profile tempProfile = null;
						String token = null;
						boolean fixed = false;

						for (int a = 0; a < Profiles.getAttributeCount(); a++) {
							AttributeInfo info = new AttributeInfo();
							Profiles.getAttributeInfo(a, info);
							// =================================================================================
							// fixed
							// =================================================================================
							if (info.getName().equalsIgnoreCase("fixed")) {
								try {
									fixed = Boolean.parseBoolean(info.getValue().toString());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							// =================================================================================
							// token
							// =================================================================================
							else if (info.getName().equalsIgnoreCase("token")) {
								token = info.getValue().toString();
							}
						}

						if (token == null) {
							Log.e(METHOD_NAME, String.format("Profiles[%d] not found Token", i));
							continue;
						} else {
							tempProfile = new Profile(token, fixed ? 1 : 0);
						}

						for (int j = 0; j < Profiles.getPropertyCount(); j++) {
							PropertyInfo subSoapObject = new PropertyInfo();
							Profiles.getPropertyInfo(j, subSoapObject);
							// =================================================================================
							// Name
							// =================================================================================
							if (subSoapObject.getName().equalsIgnoreCase("Name")) {
								tempProfile.mName = subSoapObject.getValue().toString();
							}
							// =================================================================================
							// VideoSourceConfiguration
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("VideoSourceConfiguration")) {
								SoapObject VideoSourceConfiguration = (SoapObject) subSoapObject.getValue();
								org.onvif.ver10.schema.nativeParcel.VideoSourceConfiguration VSC = new VideoSourceConfiguration();
								// =================================================================================
								// VideoSourceConfiguration - token
								// =================================================================================
								if (hasAttribute(subSoapObject, "token")) {
									VSC.mToken = VideoSourceConfiguration.getAttributeAsString("token");
									Log.i(METHOD_NAME, "VideoSourceConfiguration - token:" + VSC.mToken);
								}
								for (int count = 0; count < VideoSourceConfiguration.getPropertyCount(); count++) {
									PropertyInfo videoSourceConfiguration = new PropertyInfo();
									VideoSourceConfiguration.getPropertyInfo(count, videoSourceConfiguration);
									Log.i(METHOD_NAME, String.format("VideoSourceConfiguration Method:%s", videoSourceConfiguration.toString()));

									if (videoSourceConfiguration.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// VideoSourceConfiguration - Name
										// =================================================================================
										if (videoSourceConfiguration.getName().equalsIgnoreCase("Name")) {
											VSC.mName = videoSourceConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "videoSourceConfiguration - Name:" + VSC.mName);
										}
										// =================================================================================
										// VideoSourceConfiguration - UseCount
										// =================================================================================
										else if (videoSourceConfiguration.getName().equalsIgnoreCase("UseCount")) {
											try {
												VSC.mUseCount = Integer.parseInt(videoSourceConfiguration.getValue().toString());
												Log.i(METHOD_NAME, "VideoSourceConfiguration - UseCount:" + VSC.mUseCount);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "VideoSourceConfiguration - UseCount:" + e.toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// VideoSourceConfiguration - SourceToken
										// =================================================================================
										else if (videoSourceConfiguration.getName().equalsIgnoreCase("SourceToken")) {
											VSC.mSourceToken = videoSourceConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "VideoSourceConfiguration - SourceToken:" + VSC.mSourceToken);
										}
									} else if (videoSourceConfiguration.getValue() instanceof SoapObject) {
										// =================================================================================
										// VideoSourceConfiguration - Bounds
										// =================================================================================
										if (videoSourceConfiguration.getName().equalsIgnoreCase("Bounds")) {
											Log.i(METHOD_NAME, "VideoSourceConfiguration - Bounds");
											VSC.mBounds = new IntRectangle();
											SoapObject Bounds = (SoapObject) videoSourceConfiguration.getValue();
											try {
												VSC.mBounds.mX = Integer.parseInt((String) Bounds.getAttributeSafelyAsString("x"));
												Log.i(METHOD_NAME, "VideoSourceConfiguration - bounds - x:" + VSC.mBounds.mX);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "VideoSourceConfiguration - bounds - x:" + Bounds.getAttributeSafelyAsString("x"));
											}
											try {
												VSC.mBounds.mY = Integer.parseInt((String) Bounds.getAttributeSafelyAsString("y"));
												Log.i(METHOD_NAME, "VideoSourceConfiguration - bounds - y:" + VSC.mBounds.mY);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "VideoSourceConfiguration - bounds - y:" + Bounds.getAttributeSafelyAsString("y"));
											}
											try {
												VSC.mBounds.mWidth = Integer.parseInt((String) Bounds.getAttributeSafelyAsString("width"));
												Log.i(METHOD_NAME, "VideoSourceConfiguration - bounds - width:" + VSC.mBounds.mWidth);
											} catch (Exception e) {
												Log.e(METHOD_NAME,
														"VideoSourceConfiguration - bounds - width:" + Bounds.getAttributeSafelyAsString("width"));
											}
											try {
												VSC.mBounds.mHeight = Integer.parseInt((String) Bounds.getAttributeSafelyAsString("height"));
												Log.i(METHOD_NAME, "VideoSourceConfiguration - bounds - height:" + VSC.mBounds.mHeight);
											} catch (Exception e) {
												Log.e(METHOD_NAME,
														"VideoSourceConfiguration - bounds - height:" + Bounds.getAttributeSafelyAsString("height"));
											}
										}
									}
								}
								tempProfile.mVideoSourceConfiguration = VSC;
							}
							// =================================================================================
							// AudioSourceConfiguration
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("AudioSourceConfiguration")) {

							}
							// =================================================================================
							// VideoEncoderConfiguration
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("VideoEncoderConfiguration")) {
								SoapObject VideoEncoderConfiguration = (SoapObject) subSoapObject.getValue();
								Log.i(METHOD_NAME, "VideoEncoderConfiguration:" + VideoEncoderConfiguration.getPropertyCount());
								org.onvif.ver10.schema.nativeParcel.VideoEncoderConfiguration VEC = new VideoEncoderConfiguration();

								// =================================================================================
								// VideoEncoderConfiguration - token
								// =================================================================================
								if (hasAttribute(subSoapObject, "token")) {
									VEC.mToken = VideoEncoderConfiguration.getAttributeAsString("token");
									Log.i(METHOD_NAME, "VideoEncoderConfiguration - token:" + VEC.mToken);
								}

								for (int count = 0; count < VideoEncoderConfiguration.getPropertyCount(); count++) {
									PropertyInfo videoEncoderConfiguration = new PropertyInfo();
									VideoEncoderConfiguration.getPropertyInfo(count, videoEncoderConfiguration);
									Log.i(METHOD_NAME, String.format("VideoEncoderConfiguration Method:%s", videoEncoderConfiguration.toString()));

									if (videoEncoderConfiguration.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// VideoEncoderConfiguration - Name
										// =================================================================================
										if (videoEncoderConfiguration.getName().equalsIgnoreCase("Name")) {
											VEC.mName = videoEncoderConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "VideoEncoderConfiguration - Name:" + VEC.mName);
										}
										// =================================================================================
										// VideoEncoderConfiguration - UseCount
										// =================================================================================
										else if (videoEncoderConfiguration.getName().equalsIgnoreCase("UseCount")) {
											try {
												VEC.mUseCount = Integer.parseInt(videoEncoderConfiguration.getValue().toString());
												Log.i(METHOD_NAME, "VideoEncoderConfiguration - UseCount:" + VEC.mUseCount);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "VideoEncoderConfiguration - UseCount:" + e.toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// VideoEncoderConfiguration - Encoding
										// =================================================================================
										else if (videoEncoderConfiguration.getName().equalsIgnoreCase("Encoding")) {
											VEC.mEncoding = videoEncoderConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "VideoEncoderConfiguration - Encoding:" + VEC.mEncoding);
										}
										// =================================================================================
										// VideoEncoderConfiguration - Quality
										// =================================================================================
										else if (videoEncoderConfiguration.getName().equalsIgnoreCase("Quality")) {
											try {
												VEC.mQuality = Float.parseFloat(videoEncoderConfiguration.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
											Log.i(METHOD_NAME, "VideoEncoderConfiguration - Quality:" + VEC.mQuality);
										}
										// =================================================================================
										// VideoEncoderConfiguration - SessionTimeout
										// =================================================================================
										else if (videoEncoderConfiguration.getName().equalsIgnoreCase("SessionTimeout")) {
											VEC.mSessionTimeout = videoEncoderConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "VideoEncoderConfiguration - SessionTimeout : " + VEC.mSessionTimeout);
										}
										// =================================================================================
										// VideoEncoderConfiguration - Unknowns
										// =================================================================================
										else {
											try {
												Log.e(METHOD_NAME, "VideoEncoderConfiguration - instanceof : "
														+ videoEncoderConfiguration.getValue().getClass().getSimpleName());
											} catch (Exception e) {
												Log.e(METHOD_NAME,
														"VideoEncoderConfiguration - instanceof : " + videoEncoderConfiguration.toString());
												e.printStackTrace();
											}
										}
									} else if (videoEncoderConfiguration.getValue() instanceof SoapObject) {
										// =================================================================================
										// VideoEncoderConfiguration - Resolution
										// =================================================================================
										if (videoEncoderConfiguration.getName().equalsIgnoreCase("Resolution")) {
											Log.i(METHOD_NAME, "VideoEncoderConfiguration - Resolution");
											VEC.mResolution = new VideoResolution();
											SoapObject Resolution = (SoapObject) videoEncoderConfiguration.getValue();
											for (int c = 0; c < Resolution.getPropertyCount(); c++) {
												PropertyInfo resolution = new PropertyInfo();
												Resolution.getPropertyInfo(c, resolution);
												// =================================================================================
												// Resolution - Width
												// =================================================================================
												if (resolution.getName().equalsIgnoreCase("Width")) {
													try {
														VEC.mResolution.mWidth = Integer.parseInt(resolution.getValue().toString());
														Log.i(METHOD_NAME, "VideoEncoderConfiguration - Resolution - Width:"
																+ VEC.mResolution.mWidth);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "VideoEncoderConfiguration - Resolution - Width:" + e.toString());
														e.printStackTrace();
													}

												}
												// =================================================================================
												// Resolution - Height
												// =================================================================================
												else if (resolution.getName().equalsIgnoreCase("Height")) {
													try {
														VEC.mResolution.mHeight = Integer.parseInt(resolution.getValue().toString());
														Log.i(METHOD_NAME, "VideoEncoderConfiguration - Resolution - Height:"
																+ VEC.mResolution.mHeight);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "VideoEncoderConfiguration - Resolution - Height:" + e.toString());
														e.printStackTrace();
													}
												}
											}
										}

										// =================================================================================
										// VideoEncoderConfiguration - RateControl
										// =================================================================================
										else if (videoEncoderConfiguration.getName().equalsIgnoreCase("RateControl")) {
											Log.i(METHOD_NAME, "VideoEncoderConfiguration - RateControl");
											VEC.mRateControl = new VideoRateControl();
											SoapObject RateControl = (SoapObject) videoEncoderConfiguration.getValue();
											for (int c = 0; c < RateControl.getPropertyCount(); c++) {
												PropertyInfo rateControl = new PropertyInfo();
												RateControl.getPropertyInfo(c, rateControl);
												// =================================================================================
												// RateControl - FrameRateLimit
												// =================================================================================
												if (rateControl.getName().equalsIgnoreCase("FrameRateLimit")) {
													try {
														VEC.mRateControl.mFrameRateLimit = Integer.parseInt(rateControl.getValue().toString());
														Log.i(METHOD_NAME, "VideoEncoderConfiguration - RateControl - FrameRateLimit:"
																+ VEC.mRateControl.mFrameRateLimit);
													} catch (Exception e) {
														Log.e(METHOD_NAME,
																"VideoEncoderConfiguration - RateControl - FrameRateLimit:" + e.toString());
														e.printStackTrace();
													}

												}
												// =================================================================================
												// RateControl - EncodingInterval
												// =================================================================================
												else if (rateControl.getName().equalsIgnoreCase("EncodingInterval")) {
													try {
														VEC.mRateControl.mEncodingInterval = Integer.parseInt(rateControl.getValue().toString());
														Log.i(METHOD_NAME, "VideoEncoderConfiguration - RateControl - EncodingInterval:"
																+ VEC.mRateControl.mEncodingInterval);
													} catch (Exception e) {
														Log.e(METHOD_NAME,
																"VideoEncoderConfiguration - RateControl - EncodingInterval:" + e.toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// RateControl - BitrateLimit
												// =================================================================================
												else if (rateControl.getName().equalsIgnoreCase("BitrateLimit")) {
													try {
														VEC.mRateControl.mBitrateLimit = Integer.parseInt(rateControl.getValue().toString());
														Log.i(METHOD_NAME, "VideoEncoderConfiguration - RateControl - BitrateLimit:"
																+ VEC.mRateControl.mBitrateLimit);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "VideoEncoderConfiguration - RateControl - BitrateLimit:" + e.toString());
														e.printStackTrace();
													}
												}
											}
										}
										// =================================================================================
										// VideoEncoderConfiguration - MPEG4
										// =================================================================================
										else if (videoEncoderConfiguration.getName().equalsIgnoreCase("MPEG4")) {
											Log.i(METHOD_NAME, "VideoEncoderConfiguration - MPEG4");
											VEC.mMPEG4 = new Mpeg4Configuration();
											SoapObject MPEG4 = (SoapObject) videoEncoderConfiguration.getValue();
											for (int c = 0; c < MPEG4.getPropertyCount(); c++) {
												PropertyInfo mpeg4 = new PropertyInfo();
												MPEG4.getPropertyInfo(c, mpeg4);
												// =================================================================================
												// MPEG4 - GovLength
												// =================================================================================
												if (mpeg4.getName().equalsIgnoreCase("GovLength")) {
													try {
														VEC.mMPEG4.mGovLength = Integer.parseInt(mpeg4.getValue().toString());
														Log.i(METHOD_NAME, "VideoEncoderConfiguration - MPEG4 - GovLength:" + VEC.mMPEG4.mGovLength);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "VideoEncoderConfiguration - MPEG4 - GovLength:" + e.toString());
														e.printStackTrace();
													}

												}
												// =================================================================================
												// MPEG4 - Mpeg4Profile
												// =================================================================================
												else if (mpeg4.getName().equalsIgnoreCase("Mpeg4Profile")) {
													VEC.mMPEG4.mMpeg4Profile = mpeg4.getValue().toString();
													Log.i(METHOD_NAME, "VideoEncoderConfiguration - MPEG4 - Mpeg4Profile:"
															+ VEC.mMPEG4.mMpeg4Profile);

												}
											}
										}
										// =================================================================================
										// VideoEncoderConfiguration - H264
										// =================================================================================
										else if (videoEncoderConfiguration.getName().equalsIgnoreCase("H264")) {
											Log.i(METHOD_NAME, "VideoEncoderConfiguration - H264");
											VEC.mH264 = new H264Configuration();
											SoapObject H264 = (SoapObject) videoEncoderConfiguration.getValue();
											for (int c = 0; c < H264.getPropertyCount(); c++) {
												PropertyInfo h264 = new PropertyInfo();
												H264.getPropertyInfo(c, h264);
												// =================================================================================
												// H264 - GovLength
												// =================================================================================
												if (h264.getName().equalsIgnoreCase("GovLength")) {
													try {
														VEC.mH264.mGovLength = Integer.parseInt(h264.getValue().toString());
														Log.i(METHOD_NAME, "VideoEncoderConfiguration - H264 - GovLength:" + VEC.mH264.mGovLength);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "VideoEncoderConfiguration - H264 - GovLength:" + e.toString());
														e.printStackTrace();
													}

												}
												// =================================================================================
												// H264 - H264Profile
												// =================================================================================
												else if (h264.getName().equalsIgnoreCase("H264Profile")) {
													VEC.mH264.mH264Profile = h264.getValue().toString();
													Log.i(METHOD_NAME, "VideoEncoderConfiguration - H264 - H264Profile:" + VEC.mH264.mH264Profile);

												}
											}
										}
										// =================================================================================
										// VideoEncoderConfiguration - Multicast
										// =================================================================================
										else if (videoEncoderConfiguration.getName().equalsIgnoreCase("Multicast")) {
											Log.i(METHOD_NAME, "VideoEncoderConfiguration - Multicast");
											VEC.mMulticast = new MulticastConfiguration();
											SoapObject Multicast = (SoapObject) videoEncoderConfiguration.getValue();
											for (int c = 0; c < Multicast.getPropertyCount(); c++) {
												PropertyInfo multicast = new PropertyInfo();
												Multicast.getPropertyInfo(c, multicast);
												// =================================================================================
												// Multicast - Address
												// =================================================================================
												if (multicast.getName().equalsIgnoreCase("Address")) {
													VEC.mMulticast.mAddress = new IPAddress();
													SoapObject Address = (SoapObject) multicast.getValue();
													for (int a = 0; a < Address.getPropertyCount(); a++) {
														PropertyInfo address = new PropertyInfo();
														Address.getPropertyInfo(a, address);
														// =================================================================================
														// Address - Type
														// =================================================================================
														if (address.getName().equalsIgnoreCase("Type")) {
															VEC.mMulticast.mAddress.mType = address.getValue().toString();
															Log.i(METHOD_NAME, "VideoEncoderConfiguration - Multicast - Address - Type:"
																	+ VEC.mMulticast.mAddress.mType);
														}
														// =================================================================================
														// Address - IPv4Address
														// =================================================================================
														else if (address.getName().equalsIgnoreCase("IPv4Address")) {
															VEC.mMulticast.mAddress.mIPv4Address = address.getValue().toString();
															if (VEC.mMulticast.mAddress.mIPv4Address == null) {
																Log.e(METHOD_NAME, "VideoEncoderConfiguration - Multicast - Address - IPv4Address:"
																		+ VEC.mMulticast.mAddress.mIPv4Address);
																VEC.mMulticast.mAddress.mIPv4Address = "";
															} else if (!utils.isIPv4Address(VEC.mMulticast.mAddress.mIPv4Address)) {
																Log.e(METHOD_NAME, "VideoEncoderConfiguration - Multicast - Address - IPv4Address:"
																		+ VEC.mMulticast.mAddress.mIPv4Address);
																VEC.mMulticast.mAddress.mIPv4Address = "";
															} else {
																Log.i(METHOD_NAME, "VideoEncoderConfiguration - Multicast - Address - IPv4Address:"
																		+ VEC.mMulticast.mAddress.mIPv4Address);
															}
														}
														// =================================================================================
														// Address - IPv6Address
														// =================================================================================
														else if (address.getName().equalsIgnoreCase("IPv6Address")) {
															VEC.mMulticast.mAddress.mIPv6Address = address.getValue().toString();
															Log.i(METHOD_NAME, "VideoEncoderConfiguration - Multicast - Address - IPv6Address:"
																	+ VEC.mMulticast.mAddress.mIPv6Address);
														}
													}
												}
												// =================================================================================
												// Multicast - Port
												// =================================================================================
												else if (multicast.getName().equalsIgnoreCase("Port")) {
													try {
														VEC.mMulticast.mPort = Integer.parseInt(multicast.getValue().toString());
														Log.i(METHOD_NAME, "VideoEncoderConfiguration - Multicast - Port:" + VEC.mMulticast.mPort);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "VideoEncoderConfiguration - Multicast - Port:" + e.toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// Multicast - TTL
												// =================================================================================
												else if (multicast.getName().equalsIgnoreCase("TTL")) {
													try {
														VEC.mMulticast.mTTL = Integer.parseInt(multicast.getValue().toString());
														Log.i(METHOD_NAME, "VideoEncoderConfiguration - Multicast - TTL:" + VEC.mMulticast.mTTL);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "VideoEncoderConfiguration - Multicast - TTL:" + e.toString());
														e.printStackTrace();
													}
												}
												// =================================================================================
												// Multicast - AutoStart
												// =================================================================================
												else if (multicast.getName().equalsIgnoreCase("AutoStart")) {
													try {
														VEC.mMulticast.mAutoStart = Boolean.parseBoolean(multicast.getValue().toString()) ? 1 : 0;
														Log.i(METHOD_NAME, "VideoEncoderConfiguration - Multicast - AutoStart:"
																+ VEC.mMulticast.mAutoStart);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "VideoEncoderConfiguration - Multicast - AutoStart:" + e.toString());
														e.printStackTrace();
													}
												}

											}
										}
										// =================================================================================
										// VideoEncoderConfiguration - Unknowns
										// =================================================================================
										else {
											try {
												Log.e(METHOD_NAME, "VideoEncoderConfiguration - instanceof : "
														+ videoEncoderConfiguration.getValue().getClass().getSimpleName());
											} catch (Exception e) {
												Log.e(METHOD_NAME,
														"VideoEncoderConfiguration - instanceof : " + videoEncoderConfiguration.toString());
												e.printStackTrace();
											}
										}
									}
								}
								tempProfile.mVideoEncoderConfiguration = VEC;
							}
							// =================================================================================
							// AudioEncoderConfiguration
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("AudioEncoderConfiguration")) {

							}
							// =================================================================================
							// VideoAnalyticsConfiguration
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("VideoAnalyticsConfiguration")) {

							}
							// =================================================================================
							// PTZConfiguration
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("PTZConfiguration")) {
								SoapObject PTZConfiguration = (SoapObject) subSoapObject.getValue();
								org.onvif.ver10.schema.nativeParcel.PTZConfiguration PTZC = new PTZConfiguration();
								// =================================================================================
								// PTZConfiguration - token
								// =================================================================================
								if (hasAttribute(subSoapObject, "token")) {
									PTZC.mToken = PTZConfiguration.getAttributeAsString("token");
									Log.i(METHOD_NAME, "PTZConfiguration - token:" + PTZC.mToken);
								}
								for (int count = 0; count < PTZConfiguration.getPropertyCount(); count++) {
									PropertyInfo ptzConfiguration = new PropertyInfo();
									PTZConfiguration.getPropertyInfo(count, ptzConfiguration);
									Log.i(METHOD_NAME, String.format("PTZConfiguration Method:%s", ptzConfiguration.toString()));

									if (ptzConfiguration.getValue() instanceof SoapPrimitive) {
										// =================================================================================
										// PTZConfiguration - Name
										// =================================================================================
										if (ptzConfiguration.getName().equalsIgnoreCase("Name")) {
											PTZC.mName = ptzConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "PTZConfiguration - Name:" + PTZC.mName);
										}
										// =================================================================================
										// PTZConfiguration - UseCount
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("UseCount")) {
											try {
												PTZC.mUseCount = Integer.parseInt(ptzConfiguration.getValue().toString());
												Log.i(METHOD_NAME, "PTZConfiguration - UseCount:" + PTZC.mUseCount);
											} catch (Exception e) {
												Log.e(METHOD_NAME, "PTZConfiguration - UseCount:" + e.toString());
												e.printStackTrace();
											}
										}
										// =================================================================================
										// PTZConfiguration - NodeToken
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("NodeToken")) {
											PTZC.mNodeToken = ptzConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "PTZConfiguration - NodeToken:" + PTZC.mNodeToken);
										}
										// =================================================================================
										// PTZConfiguration - DefaultAbsolutePantTiltPositionSpace
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("DefaultAbsolutePantTiltPositionSpace")) {
											PTZC.mDefaultAbsolutePantTiltPositionSpace = ptzConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "PTZConfiguration - DefaultAbsolutePantTiltPositionSpace:"
													+ PTZC.mDefaultAbsolutePantTiltPositionSpace);
										}
										// =================================================================================
										// PTZConfiguration - DefaultAbsoluteZoomPositionSpace
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("DefaultAbsoluteZoomPositionSpace")) {
											PTZC.mDefaultAbsoluteZoomPositionSpace = ptzConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "PTZConfiguration - DefaultAbsoluteZoomPositionSpace:"
													+ PTZC.mDefaultAbsoluteZoomPositionSpace);
										}
										// =================================================================================
										// PTZConfiguration - DefaultRelativePanTiltTranslationSpace
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("DefaultRelativePanTiltTranslationSpace")) {
											PTZC.mDefaultRelativePanTiltTranslationSpace = ptzConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "PTZConfiguration - DefaultRelativePanTiltTranslationSpace:"
													+ PTZC.mDefaultRelativePanTiltTranslationSpace);
										}
										// =================================================================================
										// PTZConfiguration - DefaultRelativeZoomTranslationSpace
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("DefaultRelativeZoomTranslationSpace")) {
											PTZC.mDefaultRelativeZoomTranslationSpace = ptzConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "PTZConfiguration - DefaultRelativeZoomTranslationSpace:"
													+ PTZC.mDefaultRelativeZoomTranslationSpace);
										}
										// =================================================================================
										// PTZConfiguration - DefaultContinuousPanTiltVelocitySpace
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("DefaultContinuousPanTiltVelocitySpace")) {
											PTZC.mDefaultContinuousPanTiltVelocitySpace = ptzConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "PTZConfiguration - DefaultContinuousPanTiltVelocitySpace:"
													+ PTZC.mDefaultContinuousPanTiltVelocitySpace);
										}
										// =================================================================================
										// PTZConfiguration - DefaultContinuousZoomVelocitySpace
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("DefaultContinuousZoomVelocitySpace")) {
											PTZC.mDefaultContinuousZoomVelocitySpace = ptzConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "PTZConfiguration - DefaultContinuousZoomVelocitySpace:"
													+ PTZC.mDefaultContinuousZoomVelocitySpace);
										}
										// =================================================================================
										// PTZConfiguration - DefaultPTZTimeout
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("DefaultPTZTimeout")) {
											PTZC.mDefaultPTZTimeout = ptzConfiguration.getValue().toString();
											Log.i(METHOD_NAME, "PTZConfiguration - DefaultPTZTimeout:" + PTZC.mDefaultPTZTimeout);
										}
									} else if (ptzConfiguration.getValue() instanceof SoapObject) {
										// =================================================================================
										// PTZConfiguration - DefaultPTZSpeed
										// =================================================================================
										if (ptzConfiguration.getName().equalsIgnoreCase("DefaultPTZSpeed")) {
											Log.i(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed");
											PTZC.mDefaultPTZSpeed = new PTZSpeed();
											SoapObject DefaultPTZSpeed = (SoapObject) ptzConfiguration.getValue();
											for (int a = 0; a < DefaultPTZSpeed.getPropertyCount(); a++) {
												PropertyInfo defaultPTZSpeed = new PropertyInfo();
												DefaultPTZSpeed.getPropertyInfo(a, defaultPTZSpeed);
												// =================================================================================
												// DefaultPTZSpeed - PanTilt
												// =================================================================================
												if (defaultPTZSpeed.getName().equalsIgnoreCase("PanTilt")) {
													SoapObject PanTilt = (SoapObject) defaultPTZSpeed.getValue();
													Log.i(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed - PanTilt:" + defaultPTZSpeed.toString());
													PTZC.mDefaultPTZSpeed.mPanTilt = new Vector2D();
													try {
														PTZC.mDefaultPTZSpeed.mPanTilt.mX = Float.parseFloat((String) PanTilt
																.getAttributeSafelyAsString("x"));
														Log.i(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed - PanTilt - x:"
																+ PTZC.mDefaultPTZSpeed.mPanTilt.mX);
													} catch (Exception e) {
														Log.e(METHOD_NAME,
																"PTZConfiguration - DefaultPTZSpeed - PanTilt - x:"
																		+ PanTilt.getAttributeSafelyAsString("x"));
													}

													try {
														PTZC.mDefaultPTZSpeed.mPanTilt.mY = Float.parseFloat((String) PanTilt
																.getAttributeSafelyAsString("y"));
														Log.i(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed - PanTilt - y:"
																+ PTZC.mDefaultPTZSpeed.mPanTilt.mY);
													} catch (Exception e) {
														Log.e(METHOD_NAME,
																"PTZConfiguration - DefaultPTZSpeed - PanTilt - y:"
																		+ PanTilt.getAttributeSafelyAsString("y"));
													}
													try {
														PTZC.mDefaultPTZSpeed.mPanTilt.mSpace = (String) PanTilt.getAttributeSafelyAsString("space");
														Log.i(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed - PanTilt - space:"
																+ PTZC.mDefaultPTZSpeed.mPanTilt.mSpace);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed - PanTilt - space:"
																+ PTZC.mDefaultPTZSpeed.mPanTilt.mSpace);
													}
												}
												// =================================================================================
												// DefaultPTZSpeed - Zoom
												// =================================================================================
												else if (defaultPTZSpeed.getName().equalsIgnoreCase("Zoom")) {
													SoapObject Zoom = (SoapObject) defaultPTZSpeed.getValue();
													Log.i(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed - Zoom:" + Zoom.toString());
													PTZC.mDefaultPTZSpeed.mZoom = new Vector1D();
													try {
														PTZC.mDefaultPTZSpeed.mZoom.mX = Float.parseFloat((String) Zoom
																.getAttributeSafelyAsString("x"));
														Log.i(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed - Zoom - x:"
																+ PTZC.mDefaultPTZSpeed.mZoom.mX);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed - Zoom - x:"
																+ PTZC.mDefaultPTZSpeed.mZoom.mX + ":" + Zoom.getAttributeSafelyAsString("x"));
													}

													try {
														PTZC.mDefaultPTZSpeed.mZoom.mSpace = (String) Zoom.getAttributeSafelyAsString("space");
														Log.i(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed - Zoom - space:"
																+ PTZC.mDefaultPTZSpeed.mZoom.mSpace);
													} catch (Exception e) {
														Log.e(METHOD_NAME, "PTZConfiguration - DefaultPTZSpeed - Zoom - space:"
																+ PTZC.mDefaultPTZSpeed.mZoom.mSpace);
													}
												}
											}
										}
										// =================================================================================
										// PTZConfiguration - PanTiltLimits
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("PanTiltLimits")) {
											Log.i(METHOD_NAME, "PTZConfiguration - PanTiltLimits");
											PTZC.mPanTiltLimits = new PanTiltLimits();
											SoapObject PanTiltLimits = (SoapObject) ptzConfiguration.getValue();
											for (int a = 0; a < PanTiltLimits.getPropertyCount(); a++) {
												PropertyInfo panTiltLimits = new PropertyInfo();
												PanTiltLimits.getPropertyInfo(a, panTiltLimits);
												// =================================================================================
												// PanTiltLimits - Range
												// =================================================================================
												if (panTiltLimits.getName().equalsIgnoreCase("Range")) {
													PTZC.mPanTiltLimits.mRange = new Space2DDescription();
													SoapObject Range = (SoapObject) panTiltLimits.getValue();
													for (int r = 0; r < Range.getPropertyCount(); r++) {
														PropertyInfo range = new PropertyInfo();
														Range.getPropertyInfo(r, range);
														// =================================================================================
														// Range - URI
														// =================================================================================
														if (range.getName().equalsIgnoreCase("URI")) {
															PTZC.mPanTiltLimits.mRange.mUri = range.getValue().toString();
															Log.i(METHOD_NAME, "PTZConfiguration - PanTiltLimits - Range - URI:"
																	+ PTZC.mPanTiltLimits.mRange.mUri);
														}
														// =================================================================================
														// Range - XRange
														// =================================================================================
														else if (range.getName().equalsIgnoreCase("XRange")) {
															PTZC.mPanTiltLimits.mRange.mXRange = new FloatRange();
															SoapObject XRange = (SoapObject) range.getValue();
															for (int x = 0; x < XRange.getPropertyCount(); x++) {
																PropertyInfo xRange = new PropertyInfo();
																XRange.getPropertyInfo(x, xRange);
																// =================================================================================
																// XRange - Min
																// =================================================================================
																if (xRange.getName().equalsIgnoreCase("Min")) {
																	try {
																		PTZC.mPanTiltLimits.mRange.mXRange.mMin = Float.parseFloat(xRange.getValue()
																				.toString());
																		Log.i(METHOD_NAME,
																				"PTZConfiguration - PanTiltLimits - Range - XRange - Min:"
																						+ PTZC.mPanTiltLimits.mRange.mXRange.mMin);
																	} catch (Exception e) {
																		Log.e(METHOD_NAME,
																				"PTZConfiguration - PanTiltLimits - Range - XRange - Min:"
																						+ PTZC.mPanTiltLimits.mRange.mXRange.mMin);
																	}
																}
																// =================================================================================
																// XRange - Max
																// =================================================================================
																else if (xRange.getName().equalsIgnoreCase("Max")) {
																	try {
																		PTZC.mPanTiltLimits.mRange.mXRange.mMax = Float.parseFloat(xRange.getValue()
																				.toString());
																		Log.i(METHOD_NAME,
																				"PTZConfiguration - PanTiltLimits - Range - XRange - Max:"
																						+ PTZC.mPanTiltLimits.mRange.mXRange.mMax);
																	} catch (Exception e) {
																		Log.e(METHOD_NAME,
																				"PTZConfiguration - PanTiltLimits - Range - XRange - Max:"
																						+ PTZC.mPanTiltLimits.mRange.mXRange.mMax);
																	}
																}
															}
														}
														// =================================================================================
														// Range - YRange
														// =================================================================================
														else if (range.getName().equalsIgnoreCase("YRange")) {
															PTZC.mPanTiltLimits.mRange.mYRange = new FloatRange();
															SoapObject YRange = (SoapObject) range.getValue();
															for (int y = 0; y < YRange.getPropertyCount(); y++) {
																PropertyInfo yRange = new PropertyInfo();
																YRange.getPropertyInfo(y, yRange);
																// =================================================================================
																// YRange - Min
																// =================================================================================
																if (yRange.getName().equalsIgnoreCase("Min")) {
																	try {
																		PTZC.mPanTiltLimits.mRange.mYRange.mMin = Float.parseFloat(yRange.getValue()
																				.toString());
																		Log.i(METHOD_NAME,
																				"PTZConfiguration - PanTiltLimits - Range - YRange - Min:"
																						+ PTZC.mPanTiltLimits.mRange.mYRange.mMin);
																	} catch (Exception e) {
																		Log.e(METHOD_NAME,
																				"PTZConfiguration - PanTiltLimits - Range - YRange - Min:"
																						+ PTZC.mPanTiltLimits.mRange.mYRange.mMin);
																	}
																}
																// =================================================================================
																// YRange - Max
																// =================================================================================
																else if (yRange.getName().equalsIgnoreCase("Max")) {
																	try {
																		PTZC.mPanTiltLimits.mRange.mYRange.mMax = Float.parseFloat(yRange.getValue()
																				.toString());
																		Log.i(METHOD_NAME,
																				"PTZConfiguration - PanTiltLimits - Range - YRange - Max:"
																						+ PTZC.mPanTiltLimits.mRange.mYRange.mMax);
																	} catch (Exception e) {
																		Log.e(METHOD_NAME,
																				"PTZConfiguration - PanTiltLimits - Range - YRange - Max:"
																						+ PTZC.mPanTiltLimits.mRange.mYRange.mMax);
																	}
																}
															}
														}
													}
												}
											}
										}
										// =================================================================================
										// PTZConfiguration - ZoomLimits
										// =================================================================================
										else if (ptzConfiguration.getName().equalsIgnoreCase("ZoomLimits")) {
											Log.i(METHOD_NAME, "PTZConfiguration - ZoomLimits");
											PTZC.mZoomLimits = new ZoomLimits();
											SoapObject ZoomLimits = (SoapObject) ptzConfiguration.getValue();
											for (int a = 0; a < ZoomLimits.getPropertyCount(); a++) {
												PropertyInfo zoomLimits = new PropertyInfo();
												ZoomLimits.getPropertyInfo(a, zoomLimits);
												// =================================================================================
												// ZoomLimits - Range
												// =================================================================================
												if (zoomLimits.getName().equalsIgnoreCase("Range")) {
													PTZC.mZoomLimits.mRange = new Space1DDescription();
													SoapObject Range = (SoapObject) zoomLimits.getValue();
													for (int r = 0; r < Range.getPropertyCount(); r++) {
														PropertyInfo range = new PropertyInfo();
														Range.getPropertyInfo(r, range);
														// =================================================================================
														// Range - URI
														// =================================================================================
														if (range.getName().equalsIgnoreCase("URI")) {
															PTZC.mZoomLimits.mRange.mUri = range.getValue().toString();
															Log.i(METHOD_NAME, "PTZConfiguration - ZoomLimits - Range - URI:"
																	+ PTZC.mZoomLimits.mRange.mUri);
														}
														// =================================================================================
														// Range - XRange
														// =================================================================================
														else if (range.getName().equalsIgnoreCase("XRange")) {
															PTZC.mZoomLimits.mRange.mXRange = new FloatRange();
															SoapObject XRange = (SoapObject) range.getValue();
															for (int x = 0; x < XRange.getPropertyCount(); x++) {
																PropertyInfo xRange = new PropertyInfo();
																XRange.getPropertyInfo(x, xRange);
																// =================================================================================
																// XRange - Min
																// =================================================================================
																if (xRange.getName().equalsIgnoreCase("Min")) {
																	try {
																		PTZC.mZoomLimits.mRange.mXRange.mMin = Float.parseFloat(xRange.getValue()
																				.toString());
																		Log.i(METHOD_NAME, "PTZConfiguration - ZoomLimits - Range - XRange - Min:"
																				+ PTZC.mZoomLimits.mRange.mXRange.mMin);
																	} catch (Exception e) {
																		Log.e(METHOD_NAME, "PTZConfiguration - ZoomLimits - Range - XRange - Min:"
																				+ PTZC.mZoomLimits.mRange.mXRange.mMin);
																	}
																}
																// =================================================================================
																// XRange - Max
																// =================================================================================
																else if (xRange.getName().equalsIgnoreCase("Max")) {
																	try {
																		PTZC.mZoomLimits.mRange.mXRange.mMax = Float.parseFloat(xRange.getValue()
																				.toString());
																		Log.i(METHOD_NAME, "PTZConfiguration - ZoomLimits - Range - XRange - Max:"
																				+ PTZC.mZoomLimits.mRange.mXRange.mMax);
																	} catch (Exception e) {
																		Log.e(METHOD_NAME, "PTZConfiguration - ZoomLimits - Range - XRange - Max:"
																				+ PTZC.mZoomLimits.mRange.mXRange.mMax);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								tempProfile.mPTZConfiguration = PTZC;
							}
							// =================================================================================
							// MetadataConfiguration
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("MetadataConfiguration")) {

							}
							// =================================================================================
							// Extension
							// =================================================================================
							else if (subSoapObject.getName().equalsIgnoreCase("Extension")) {

							}
						}
						if (tempProfile != null)
							mProfiles.add(tempProfile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean hasAttribute(PropertyInfo propertyInfo, String attributeName) {
		try {
			SoapObject xaddr = (SoapObject) propertyInfo.getValue();
			return xaddr.hasAttribute(attributeName);
		} catch (Exception e) {
			return false;
		}
	}

}
