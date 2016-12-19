package org.onvif.ver10.media.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.schema.VideoEncoder;

public class GetVideoEncoderConfigurationResponse {
	private final static String TAG = "GetVideoEncoderConfigurationResponse";
	private VideoEncoder mVideoEncoder;

	public GetVideoEncoderConfigurationResponse(SoapObject obj) {
		for (int i = 0; i < obj.getPropertyCount(); i++) {
			PropertyInfo info = new PropertyInfo();
			obj.getPropertyInfo(i, info);
			SoapObject Options = (SoapObject) info.getValue();
			if (!info.getName().equalsIgnoreCase("Configuration"))
				continue;

			mVideoEncoder = new VideoEncoder();

			for (int j = 0; j < Options.getPropertyCount(); j++) {
				PropertyInfo subSoapObject = new PropertyInfo();
				Options.getPropertyInfo(j, subSoapObject);
				if (subSoapObject.getValue() instanceof SoapObject) {
					// ====================================================================
					// Name
					// ====================================================================
					if (subSoapObject.getName().equals("Name")) {
				
						try {
							String Name = subSoapObject.getValue().toString();
							mVideoEncoder.setVideoEncoderName(Name);
						
						} catch (Exception e) {
						}
					}
					// ====================================================================
					// UseCount
					// ====================================================================
					else if (subSoapObject.getName().equals("UseCount")) {
						try {
							int UseCount = Integer.parseInt(subSoapObject.getValue().toString());
							mVideoEncoder.setVideoEncoderUseCount(UseCount);
						
						} catch (Exception e) {
						}
					}
					// ====================================================================
					// Encoding
					// ====================================================================
					else if (subSoapObject.getName().equals("Encoding")) {
						try {
							String Encoding = subSoapObject.getValue().toString();
							mVideoEncoder.setVideoEncoderEncoding(Encoding);
							
						} catch (Exception e) {
						}
					}
					// ====================================================================
					// Resolution
					// ====================================================================
					else if (subSoapObject.getName().equals("Resolution")) {
						SoapObject Resolution = (SoapObject) subSoapObject.getValue();
						int ResolutionsAvailableSize = Resolution.getPropertyCount();
						int Width = 0, Height = 0;
						for (int re = 0; re < ResolutionsAvailableSize; re++) {
							PropertyInfo resolution = new PropertyInfo();
							Resolution.getPropertyInfo(re, resolution);
							if (resolution.getName().contains("Width")) {
								try {
									Width = Integer.parseInt(resolution.getValue().toString());
									mVideoEncoder.setVideoEncoderWidth(Width);
									
								} catch (Exception e) {
								}
							} else if (resolution.getName().contains("Height")) {
								try {
									Height = Integer.parseInt(resolution.getValue().toString());
									mVideoEncoder.setVideoEncoderHeight(Height);
								
								} catch (Exception e) {
								}
							} else {
								
							}
						}

					}
					// ====================================================================
					// Quality
					// ====================================================================
					else if (subSoapObject.getName().contains("Quality")) {
						try {
							float Quality = Float.parseFloat(subSoapObject.getValue().toString());
							mVideoEncoder.setVideoEncoderQuality(Quality);
							
						} catch (Exception e) {
						}
					}
					// ====================================================================
					// RateControl
					// ====================================================================
					else if (subSoapObject.getName().contains("RateControl")) {
						try {
							SoapObject RateControl = (SoapObject) subSoapObject.getValue();
							int rsize = RateControl.getPropertyCount();
							for (int r = 0; r < rsize; r++) {
								PropertyInfo ratecontrol = new PropertyInfo();
								RateControl.getPropertyInfo(r, ratecontrol);
								if (ratecontrol.getName().contains("FrameRateLimit")) {
									int framerateLimit = Integer.parseInt(ratecontrol.getValue().toString());
									mVideoEncoder.setVideoEncoderFrameRateLimit(framerateLimit);
									
								} else if (ratecontrol.getName().contains("EncodingInterval")) {
									int encodingIntervalLimit = Integer.parseInt(ratecontrol.getValue().toString());
									mVideoEncoder.setVideoEncoderEncodingInterval(encodingIntervalLimit);
							
								} else if (ratecontrol.getName().contains("BitrateLimit")) {
									int bitrateLimit = Integer.parseInt(ratecontrol.getValue().toString());
									mVideoEncoder.setVideoEncoderBitrateLimit(bitrateLimit);
								
								}
							}
						} catch (Exception e) {
						}
					}
					// ====================================================================
					// MPEG4 - optional
					// ====================================================================
					else if (subSoapObject.getName().equals("MPEG4")) {

						SoapObject MPEG4 = (SoapObject) subSoapObject.getValue();
						int size = MPEG4.getPropertyCount();
						for (int en = 0; en < size; en++) {
							PropertyInfo mpeg4 = new PropertyInfo();
							MPEG4.getPropertyInfo(en, mpeg4);
							// ====================================================================
							// MPEG4 - optional (GovLengthRange)
							// ====================================================================
							if (mpeg4.getName().contains("GovLength")) {
								int govlength = Integer.parseInt(mpeg4.getValue().toString());
								mVideoEncoder.setVideoEncoderGovLength(govlength);
							
							}
							// ====================================================================
							// MPEG4 - optional (Mpeg4ProfilesSupported)
							// ====================================================================
							else if (mpeg4.getName().contains("Mpeg4Profile")) {
								String ProfileSupport = mpeg4.getValue().toString();
								mVideoEncoder.setVideoEncoderProfile(ProfileSupport);
								

							}
						}
					}
					// ====================================================================
					// H264 - optional
					// ====================================================================
					else if (subSoapObject.getName().equals("H264")) {

						SoapObject H264 = (SoapObject) subSoapObject.getValue();
						int size = H264.getPropertyCount();
						for (int en = 0; en < size; en++) {
							PropertyInfo h264 = new PropertyInfo();
							H264.getPropertyInfo(en, h264);

							// ====================================================================
							// H264 - optional (GovLengthRange)
							// ====================================================================
							if (h264.getName().contains("GovLength")) {
								int govlength = Integer.parseInt(h264.getValue().toString());
								mVideoEncoder.setVideoEncoderGovLength(govlength);
							
							}
							// ====================================================================
							// H264 - optional (H264ProfilesSupported)
							// ====================================================================
							else if (h264.getName().contains("H264Profile")) {
								String ProfileSupport = h264.getValue().toString();
								mVideoEncoder.setVideoEncoderProfile(ProfileSupport);
								
							}
						}
					}
				} else {
					// ====================================================================
					// Name
					// ====================================================================
					if (subSoapObject.getName().equals("Name")) {					
						try {
							String Name = subSoapObject.getValue().toString();
							mVideoEncoder.setVideoEncoderName(Name);
						
						} catch (Exception e) {
						}
					}
					// ====================================================================
					// UseCount
					// ====================================================================
					else if (subSoapObject.getName().equals("UseCount")) {
						try {
							int UseCount = Integer.parseInt(subSoapObject.getValue().toString());
							mVideoEncoder.setVideoEncoderUseCount(UseCount);
							
						} catch (Exception e) {
						}
					}
					// ====================================================================
					// Encoding
					// ====================================================================
					else if (subSoapObject.getName().equals("Encoding")) {
						try {
							String Encoding = subSoapObject.getValue().toString();
							mVideoEncoder.setVideoEncoderEncoding(Encoding);
							
						} catch (Exception e) {
						}
					}
					// ====================================================================
					// Quality
					// ====================================================================
					else if (subSoapObject.getName().contains("Quality")) {
						try {
							float Quality = Float.parseFloat(subSoapObject.getValue().toString());
							mVideoEncoder.setVideoEncoderQuality(Quality);
							
						} catch (Exception e) {
						}
					}

				}
			}
		}
	}

	public VideoEncoder getVideoEncoder() {
		return mVideoEncoder;
	}

}
