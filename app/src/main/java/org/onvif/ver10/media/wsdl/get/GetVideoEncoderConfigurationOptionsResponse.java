package org.onvif.ver10.media.wsdl.get;

import android.util.Log;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.schema.H264Options;
import org.onvif.ver10.schema.IntRange;
import org.onvif.ver10.schema.JpegOptions;
import org.onvif.ver10.schema.Mpeg4Options;
import org.onvif.ver10.schema.VideoResolution;
import org.onvif.ver10.schema.nativeParcel.VideoEncoderConfigurationOptions;

import java.util.ArrayList;

public class GetVideoEncoderConfigurationOptionsResponse {
	private final static String METHOD_NAME = GetVideoEncoderConfigurationOptionsResponse.class.getSimpleName();
	public VideoEncoderConfigurationOptions mOptions;

	public GetVideoEncoderConfigurationOptionsResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo info = new PropertyInfo();
				obj.getPropertyInfo(i, info);
				SoapObject Options = (SoapObject) info.getValue();
				if (!info.getName().equalsIgnoreCase("Options"))
					continue;
				mOptions = new VideoEncoderConfigurationOptions();
				for (int j = 0; j < Options.getPropertyCount(); j++) {
					PropertyInfo subSoapObject = new PropertyInfo();
					Options.getPropertyInfo(j, subSoapObject);
					if (subSoapObject.getValue() instanceof SoapObject) {
						// ====================================================================
						// QualityRange
						// ====================================================================
						if (subSoapObject.getName().equalsIgnoreCase("QualityRange")) {
							mOptions.mQualityRange = new IntRange();
							SoapObject QualityRange = (SoapObject) subSoapObject.getValue();
							for (int c = 0; c < QualityRange.getPropertyCount(); c++) {
								PropertyInfo qualityRange = new PropertyInfo();
								QualityRange.getPropertyInfo(c, qualityRange);
								// ====================================================================
								// QualityRange - Min
								// ====================================================================
								if (qualityRange.getName().equalsIgnoreCase("Min")) {
									try {
										mOptions.mQualityRange.mMin = Integer.parseInt(qualityRange.getValue().toString());
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
								// ====================================================================
								// QualityRange - Max
								// ====================================================================
								else if (qualityRange.getName().equalsIgnoreCase("Max")) {
									try {
										mOptions.mQualityRange.mMax = Integer.parseInt(qualityRange.getValue().toString());
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
						// ====================================================================
						// JPEG - optional
						// ====================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("JPEG")) {
							mOptions.mJPEG = new JpegOptions();
							SoapObject JPEG = (SoapObject) subSoapObject.getValue();
							int size = JPEG.getPropertyCount();
							for (int en = 0; en < size; en++) {
								PropertyInfo jpeg = new PropertyInfo();
								JPEG.getPropertyInfo(en, jpeg);
								// ====================================================================
								// JPEG - ResolutionsAvailable
								// ====================================================================
								if (jpeg.getName().equalsIgnoreCase("ResolutionsAvailable")) {
									if (mOptions.mJPEG.mResolutionsAvailable == null) {
										mOptions.mJPEG.mResolutionsAvailable = new ArrayList<VideoResolution>();
									}
									VideoResolution videoResolution = new VideoResolution();
									SoapObject ResolutionsAvailable = (SoapObject) jpeg.getValue();
									for (int c = 0; c < ResolutionsAvailable.getPropertyCount(); c++) {
										PropertyInfo resolutionsAvailable = new PropertyInfo();
										ResolutionsAvailable.getPropertyInfo(c, resolutionsAvailable);
										// ====================================================================
										// ResolutionsAvailable - Width
										// ====================================================================
										if (resolutionsAvailable.getName().equalsIgnoreCase("Width")) {
											try {
												videoResolution.mWidth = Integer.parseInt(resolutionsAvailable.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// ResolutionsAvailable - Height
										// ====================================================================
										else if (resolutionsAvailable.getName().equalsIgnoreCase("Height")) {
											try {
												videoResolution.mHeight = Integer.parseInt(resolutionsAvailable.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
									mOptions.mJPEG.mResolutionsAvailable.add(videoResolution);
								}
								// ====================================================================
								// JPEG - FrameRateRange
								// ====================================================================
								else if (jpeg.getName().contains("FrameRateRange")) {
									mOptions.mJPEG.mFrameRateRange = new IntRange();
									SoapObject FrameRateRange = (SoapObject) subSoapObject.getValue();
									for (int c = 0; c < FrameRateRange.getPropertyCount(); c++) {
										PropertyInfo frameRateRange = new PropertyInfo();
										FrameRateRange.getPropertyInfo(c, frameRateRange);
										// ====================================================================
										// FrameRateRange - Min
										// ====================================================================
										if (frameRateRange.getName().equalsIgnoreCase("Min")) {
											try {
												mOptions.mJPEG.mFrameRateRange.mMin = Integer.parseInt(frameRateRange.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// FrameRateRange - Max
										// ====================================================================
										else if (frameRateRange.getName().equalsIgnoreCase("Max")) {
											try {
												mOptions.mJPEG.mFrameRateRange.mMax = Integer.parseInt(frameRateRange.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
								}
								// ====================================================================
								// JPEG - EncodingIntervalRange
								// ====================================================================
								else if (jpeg.getName().contains("EncodingIntervalRange")) {
									mOptions.mJPEG.mEncodingIntervalRange = new IntRange();
									SoapObject EncodingIntervalRange = (SoapObject) subSoapObject.getValue();
									for (int c = 0; c < EncodingIntervalRange.getPropertyCount(); c++) {
										PropertyInfo encodingIntervalRange = new PropertyInfo();
										EncodingIntervalRange.getPropertyInfo(c, encodingIntervalRange);
										// ====================================================================
										// FrameRateRange - Min
										// ====================================================================
										if (encodingIntervalRange.getName().equalsIgnoreCase("Min")) {
											try {
												mOptions.mJPEG.mEncodingIntervalRange.mMin = Integer.parseInt(encodingIntervalRange.getValue()
														.toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// FrameRateRange - Max
										// ====================================================================
										else if (encodingIntervalRange.getName().equalsIgnoreCase("Max")) {
											try {
												mOptions.mJPEG.mEncodingIntervalRange.mMax = Integer.parseInt(encodingIntervalRange.getValue()
														.toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
								}
							}
						}
						// ====================================================================
						// MPEG4 - optional
						// ====================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("MPEG4")) {
							mOptions.mMPEG4 = new Mpeg4Options();
							SoapObject MPEG4 = (SoapObject) subSoapObject.getValue();
							int size = MPEG4.getPropertyCount();
							for (int en = 0; en < size; en++) {
								PropertyInfo mPEG4 = new PropertyInfo();
								MPEG4.getPropertyInfo(en, mPEG4);
								// ====================================================================
								// MPEG4 - ResolutionsAvailable
								// ====================================================================
								if (mPEG4.getName().equalsIgnoreCase("ResolutionsAvailable")) {
									if (mOptions.mMPEG4.mResolutionsAvailable == null) {
										mOptions.mMPEG4.mResolutionsAvailable = new ArrayList<VideoResolution>();
									}
									VideoResolution videoResolution = new VideoResolution();
									SoapObject ResolutionsAvailable = (SoapObject) mPEG4.getValue();
									for (int c = 0; c < ResolutionsAvailable.getPropertyCount(); c++) {
										PropertyInfo resolutionsAvailable = new PropertyInfo();
										ResolutionsAvailable.getPropertyInfo(c, resolutionsAvailable);
										// ====================================================================
										// ResolutionsAvailable - Width
										// ====================================================================
										if (resolutionsAvailable.getName().equalsIgnoreCase("Width")) {
											try {
												videoResolution.mWidth = Integer.parseInt(resolutionsAvailable.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// ResolutionsAvailable - Height
										// ====================================================================
										else if (resolutionsAvailable.getName().equalsIgnoreCase("Height")) {
											try {
												videoResolution.mHeight = Integer.parseInt(resolutionsAvailable.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
									mOptions.mMPEG4.mResolutionsAvailable.add(videoResolution);
								}
								// ====================================================================
								// MPEG4 - GovLengthRange
								// ====================================================================
								else if (mPEG4.getName().contains("GovLengthRange")) {
									mOptions.mMPEG4.mGovLengthRange = new IntRange();
									SoapObject GovLengthRange = (SoapObject) subSoapObject.getValue();
									for (int c = 0; c < GovLengthRange.getPropertyCount(); c++) {
										PropertyInfo govLengthRange = new PropertyInfo();
										GovLengthRange.getPropertyInfo(c, govLengthRange);
										// ====================================================================
										// GovLengthRange - Min
										// ====================================================================
										if (govLengthRange.getName().equalsIgnoreCase("Min")) {
											try {
												mOptions.mMPEG4.mGovLengthRange.mMin = Integer.parseInt(govLengthRange.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// GovLengthRange - Max
										// ====================================================================
										else if (govLengthRange.getName().equalsIgnoreCase("Max")) {
											try {
												mOptions.mMPEG4.mGovLengthRange.mMax = Integer.parseInt(govLengthRange.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
								}
								// ====================================================================
								// MPEG4 - FrameRateRange
								// ====================================================================
								else if (mPEG4.getName().contains("FrameRateRange")) {
									mOptions.mMPEG4.mFrameRateRange = new IntRange();
									SoapObject FrameRateRange = (SoapObject) subSoapObject.getValue();
									for (int c = 0; c < FrameRateRange.getPropertyCount(); c++) {
										PropertyInfo frameRateRange = new PropertyInfo();
										FrameRateRange.getPropertyInfo(c, frameRateRange);
										// ====================================================================
										// FrameRateRange - Min
										// ====================================================================
										if (frameRateRange.getName().equalsIgnoreCase("Min")) {
											try {
												mOptions.mMPEG4.mFrameRateRange.mMin = Integer.parseInt(frameRateRange.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// FrameRateRange - Max
										// ====================================================================
										else if (frameRateRange.getName().equalsIgnoreCase("Max")) {
											try {
												mOptions.mMPEG4.mFrameRateRange.mMax = Integer.parseInt(frameRateRange.getValue().toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
								}
								// ====================================================================
								// MPEG4 - EncodingIntervalRange
								// ====================================================================
								else if (mPEG4.getName().contains("EncodingIntervalRange")) {
									mOptions.mMPEG4.mEncodingIntervalRange = new IntRange();
									SoapObject EncodingIntervalRange = (SoapObject) subSoapObject.getValue();
									for (int c = 0; c < EncodingIntervalRange.getPropertyCount(); c++) {
										PropertyInfo encodingIntervalRange = new PropertyInfo();
										EncodingIntervalRange.getPropertyInfo(c, encodingIntervalRange);
										// ====================================================================
										// FrameRateRange - Min
										// ====================================================================
										if (encodingIntervalRange.getName().equalsIgnoreCase("Min")) {
											try {
												mOptions.mMPEG4.mEncodingIntervalRange.mMin = Integer.parseInt(encodingIntervalRange.getValue()
														.toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// FrameRateRange - Max
										// ====================================================================
										else if (encodingIntervalRange.getName().equalsIgnoreCase("Max")) {
											try {
												mOptions.mMPEG4.mEncodingIntervalRange.mMax = Integer.parseInt(encodingIntervalRange.getValue()
														.toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
								}
								// ====================================================================
								// MPEG4 - Mpeg4ProfilesSupported
								// ====================================================================
								else if (mPEG4.getName().equalsIgnoreCase("Mpeg4ProfilesSupported")) {
									if (mOptions.mMPEG4.mMpeg4ProfilesSupported == null) {
										mOptions.mMPEG4.mMpeg4ProfilesSupported = new ArrayList<String>();
									}
									String ProfilesSupported = mPEG4.getValue().toString();
									if (ProfilesSupported != null) {
										mOptions.mMPEG4.mMpeg4ProfilesSupported.add(ProfilesSupported);
									}
								}
							}
						}
						// ====================================================================
						// H264 - optional
						// ====================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("H264")) {
							mOptions.mH264 = new H264Options();
							SoapObject H264 = (SoapObject) subSoapObject.getValue();
							int size = H264.getPropertyCount();
							for (int en = 0; en < size; en++) {
								PropertyInfo h264 = new PropertyInfo();
								H264.getPropertyInfo(en, h264);
								// ====================================================================
								// H264 - ResolutionsAvailable
								// ====================================================================
								if (h264.getName().equalsIgnoreCase("ResolutionsAvailable")) {
									Log.i(METHOD_NAME, "H264 - ResolutionsAvailable");
									if (mOptions.mH264.mResolutionsAvailable == null) {
										mOptions.mH264.mResolutionsAvailable = new ArrayList<VideoResolution>();
										Log.i(METHOD_NAME, "H264 - ResolutionsAvailable Create");
									}
									VideoResolution videoResolution = new VideoResolution();
									SoapObject ResolutionsAvailable = (SoapObject) h264.getValue();
									for (int c = 0; c < ResolutionsAvailable.getPropertyCount(); c++) {
										PropertyInfo resolutionsAvailable = new PropertyInfo();
										ResolutionsAvailable.getPropertyInfo(c, resolutionsAvailable);
										// ====================================================================
										// ResolutionsAvailable - Width
										// ====================================================================
										if (resolutionsAvailable.getName().equalsIgnoreCase("Width")) {
											try {
												videoResolution.mWidth = Integer.parseInt(resolutionsAvailable.getValue().toString());
												Log.i(METHOD_NAME, "H264 - ResolutionsAvailable Width:"+videoResolution.mWidth);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// ResolutionsAvailable - Height
										// ====================================================================
										else if (resolutionsAvailable.getName().equalsIgnoreCase("Height")) {
											try {
												videoResolution.mHeight = Integer.parseInt(resolutionsAvailable.getValue().toString());
												Log.i(METHOD_NAME, "H264 - ResolutionsAvailable Height:"+videoResolution.mHeight);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
									mOptions.mH264.mResolutionsAvailable.add(videoResolution);
								}
								// ====================================================================
								// H264 - GovLengthRange
								// ====================================================================
								else if (h264.getName().contains("GovLengthRange")) {
									Log.i(METHOD_NAME, "H264 - GovLengthRange");
									mOptions.mH264.mGovLengthRange = new IntRange();
									SoapObject GovLengthRange = (SoapObject) h264.getValue();
									for (int c = 0; c < GovLengthRange.getPropertyCount(); c++) {
										PropertyInfo govLengthRange = new PropertyInfo();
										GovLengthRange.getPropertyInfo(c, govLengthRange);
										// ====================================================================
										// GovLengthRange - Min
										// ====================================================================
										if (govLengthRange.getName().equalsIgnoreCase("Min")) {
											try {
												mOptions.mH264.mGovLengthRange.mMin = Integer.parseInt(govLengthRange.getValue().toString());
												Log.i(METHOD_NAME, "H264 - GovLengthRange Min:"+mOptions.mH264.mGovLengthRange.mMin);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// GovLengthRange - Max
										// ====================================================================
										else if (govLengthRange.getName().equalsIgnoreCase("Max")) {
											try {
												mOptions.mH264.mGovLengthRange.mMax = Integer.parseInt(govLengthRange.getValue().toString());
												Log.i(METHOD_NAME, "H264 - GovLengthRange Max:"+mOptions.mH264.mGovLengthRange.mMax);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
								}
								// ====================================================================
								// H264 - FrameRateRange
								// ====================================================================
								else if (h264.getName().contains("FrameRateRange")) {
									Log.i(METHOD_NAME, "H264 - FrameRateRange");
									mOptions.mH264.mFrameRateRange = new IntRange();
									SoapObject FrameRateRange = (SoapObject) h264.getValue();
									for (int c = 0; c < FrameRateRange.getPropertyCount(); c++) {
										PropertyInfo frameRateRange = new PropertyInfo();
										FrameRateRange.getPropertyInfo(c, frameRateRange);
										// ====================================================================
										// FrameRateRange - Min
										// ====================================================================
										if (frameRateRange.getName().equalsIgnoreCase("Min")) {
											try {
												mOptions.mH264.mFrameRateRange.mMin = Integer.parseInt(frameRateRange.getValue().toString());
												Log.i(METHOD_NAME, "H264 - FrameRateRange Min:" + mOptions.mH264.mFrameRateRange.mMin);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// FrameRateRange - Max
										// ====================================================================
										else if (frameRateRange.getName().equalsIgnoreCase("Max")) {
											try {
												mOptions.mH264.mFrameRateRange.mMax = Integer.parseInt(frameRateRange.getValue().toString());
												Log.i(METHOD_NAME, "H264 - FrameRateRange Max:" + mOptions.mH264.mFrameRateRange.mMin);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
								}
								// ====================================================================
								// H264 - EncodingIntervalRange
								// ====================================================================
								else if (h264.getName().contains("EncodingIntervalRange")) {
									mOptions.mH264.mEncodingIntervalRange = new IntRange();
									SoapObject EncodingIntervalRange = (SoapObject) h264.getValue();
									for (int c = 0; c < EncodingIntervalRange.getPropertyCount(); c++) {
										PropertyInfo encodingIntervalRange = new PropertyInfo();
										EncodingIntervalRange.getPropertyInfo(c, encodingIntervalRange);
										// ====================================================================
										// FrameRateRange - Min
										// ====================================================================
										if (encodingIntervalRange.getName().equalsIgnoreCase("Min")) {
											try {
												mOptions.mH264.mEncodingIntervalRange.mMin = Integer.parseInt(encodingIntervalRange.getValue()
														.toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
										// ====================================================================
										// FrameRateRange - Max
										// ====================================================================
										else if (encodingIntervalRange.getName().equalsIgnoreCase("Max")) {
											try {
												mOptions.mH264.mEncodingIntervalRange.mMax = Integer.parseInt(encodingIntervalRange.getValue()
														.toString());
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
								}
								// ====================================================================
								// H264 - H264ProfilesSupported
								// ====================================================================
								else if (h264.getName().equalsIgnoreCase("H264ProfilesSupported")) {
									if (mOptions.mH264.mH264ProfilesSupported == null) {
										mOptions.mH264.mH264ProfilesSupported = new ArrayList<String>();
									}
									String ProfilesSupported = h264.getValue().toString();
									if (ProfilesSupported != null) {
										mOptions.mH264.mH264ProfilesSupported.add(ProfilesSupported);
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
