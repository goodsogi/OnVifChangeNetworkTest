package org.onvif.ver10.media.wsdl.set;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.media.wsdl.MediaService;
import org.onvif.ver10.schema.nativeParcel.VideoEncoderConfiguration;

public class SetVideoEncoderConfiguration extends SoapObject {
	public static final String METHOD_NAME = SetVideoEncoderConfiguration.class.getSimpleName();

	// SOAP
	private SoapObject mConfiguration;

	public SetVideoEncoderConfiguration() {
		super(MediaService.NAMESPACE, METHOD_NAME);
		mConfiguration = new SoapObject(MediaService.NAMESPACE, "Configuration");
	}

	public void setVideoEncoderConfiguration(VideoEncoderConfiguration config) {
		mConfiguration.addAttribute("token", config.mToken);
		mConfiguration.addProperty(MediaService.SCHEMA, "Name", config.mName);
		mConfiguration.addProperty(MediaService.SCHEMA, "UseCount", config.mUseCount);
		mConfiguration.addProperty(MediaService.SCHEMA, "Encoding", config.mEncoding);
		// Slog.i(METHOD_NAME,
		// String.format("token:%s, Name:%s, UseCount:%d, Encoding:%s", config.mToken, config.mName, config.mUseCount,
		// config.mEncoding));

		SoapObject Resolution = new SoapObject(MediaService.SCHEMA, "Resolution");
		Resolution.addProperty(MediaService.SCHEMA, "Width", config.mResolution.mWidth);
		Resolution.addProperty(MediaService.SCHEMA, "Height", config.mResolution.mHeight);
		mConfiguration.addSoapObject(Resolution);
		// Slog.i(METHOD_NAME, String.format("Resolution %dx%d", config.mResolution.mWidth,
		// config.mResolution.mHeight));

		mConfiguration.addProperty(MediaService.SCHEMA, "Quality", config.mQuality);
		// Slog.i(METHOD_NAME, String.format("Quality %f", config.mQuality));

		SoapObject RateControl = new SoapObject(MediaService.SCHEMA, "RateControl");
		RateControl.addProperty(MediaService.SCHEMA, "FrameRateLimit", config.mRateControl.mFrameRateLimit);
		RateControl.addProperty(MediaService.SCHEMA, "EncodingInterval", config.mRateControl.mEncodingInterval);
		RateControl.addProperty(MediaService.SCHEMA, "BitrateLimit", config.mRateControl.mBitrateLimit);
		mConfiguration.addSoapObject(RateControl);
		// Slog.i(METHOD_NAME, String.format("RateControl FrameRateLimit:%d, EncodingInterval:%d, BitrateLimit:%d",
		// config.mRateControl.mFrameRateLimit,
		// config.mRateControl.mEncodingInterval, config.mRateControl.mBitrateLimit));

		if (config.mEncoding.equals("H264")) {
			SoapObject H264 = new SoapObject(MediaService.SCHEMA, "H264");
			H264.addProperty(MediaService.SCHEMA, "GovLength", /* config.mH264.mGovLength */config.mRateControl.mFrameRateLimit);
			H264.addProperty(MediaService.SCHEMA, "H264Profile", config.mH264.mH264Profile);
			mConfiguration.addSoapObject(H264);
			// Slog.i(METHOD_NAME, String.format("H264 GovLength:%d, H264Profile:%s", config.mH264.mGovLength,
			// config.mH264.mH264Profile));

		} else if (config.mEncoding.equals("MPEG4")) {
			SoapObject MPEG4 = new SoapObject(MediaService.SCHEMA, "MPEG4");
			MPEG4.addProperty(MediaService.SCHEMA, "GovLength", config.mMPEG4.mGovLength);
			MPEG4.addProperty(MediaService.SCHEMA, "Mpeg4Profile", config.mMPEG4.mMpeg4Profile);
			mConfiguration.addSoapObject(MPEG4);
		}

		SoapObject Multicast = new SoapObject(MediaService.SCHEMA, "Multicast");
		SoapObject Address = new SoapObject(MediaService.SCHEMA, "Address");
		Address.addProperty(MediaService.SCHEMA, "Type", config.mMulticast.mAddress.mType);
		if (config.mMulticast.mAddress.mIPv4Address != null) {
			Address.addProperty(MediaService.SCHEMA, "IPv4Address", config.mMulticast.mAddress.mIPv4Address);
		}
		if (config.mMulticast.mAddress.mIPv6Address != null) {
			Address.addProperty(MediaService.SCHEMA, "IPv6Address", config.mMulticast.mAddress.mIPv6Address);
		}
		Multicast.addSoapObject(Address);
		Multicast.addProperty(MediaService.SCHEMA, "Port", config.mMulticast.mPort);
		Multicast.addProperty(MediaService.SCHEMA, "TTL", config.mMulticast.mTTL);
		Multicast.addProperty(MediaService.SCHEMA, "AutoStart", config.mMulticast.mAutoStart == 1);
		mConfiguration.addSoapObject(Multicast);
		// Slog.i(METHOD_NAME, String.format("Multicast Type:%s, Port:%d, TTL:%d, AutoStart:%s",
		// config.mMulticast.mAddress.mType,
		// config.mMulticast.mPort, config.mMulticast.mTTL, config.mMulticast.mAutoStart == 1));

		mConfiguration.addProperty(MediaService.SCHEMA, "SessionTimeout", config.mSessionTimeout);
		// Slog.i(METHOD_NAME, String.format("SessionTimeout:%s", config.mSessionTimeout));

		// The ForcePersistence element is obsolete and should always be assumed to be true.
		// mConfiguration.addProperty(MediaService.SCHEMA, "ForcePersistence", true);
		// Slog.i(METHOD_NAME, String.format("ForcePersistence:%s", true));

		this.addSoapObject(mConfiguration);

	}
}
