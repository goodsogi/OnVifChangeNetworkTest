package org.onvif.ver10.media.wsdl;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.media.wsdl.get.GetMetadataConfigurations;
import org.onvif.ver10.media.wsdl.get.GetMetadataConfigurationsResponse;
import org.onvif.ver10.media.wsdl.get.GetProfiles;
import org.onvif.ver10.media.wsdl.get.GetProfilesResponse;
import org.onvif.ver10.media.wsdl.get.GetSnapshotUri;
import org.onvif.ver10.media.wsdl.get.GetSnapshotUriResponse;
import org.onvif.ver10.media.wsdl.get.GetStreamUri;
import org.onvif.ver10.media.wsdl.get.GetStreamUriResponse;
import org.onvif.ver10.media.wsdl.get.GetVideoEncoderConfiguration;
import org.onvif.ver10.media.wsdl.get.GetVideoEncoderConfigurationOptions;
import org.onvif.ver10.media.wsdl.get.GetVideoEncoderConfigurationOptionsResponse;
import org.onvif.ver10.media.wsdl.get.GetVideoEncoderConfigurationResponse;
import org.onvif.ver10.media.wsdl.set.AddMetadataConfiguration;
import org.onvif.ver10.media.wsdl.set.AddMetadataConfigurationResponse;
import org.onvif.ver10.media.wsdl.set.SetVideoEncoderConfiguration;
import org.onvif.ver10.media.wsdl.set.SetVideoEncoderConfigurationResponse;
import org.onvif.ver10.schema.BaseService;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MediaService extends BaseService {
	public static String NAMESPACE = "http://www.onvif.org/ver10/media/wsdl";
	public static final String SCHEMA = "http://www.onvif.org/ver10/schema";

	public MediaService(String url) {
		super(url);
		mEnvelope.addPrefix("trt", NAMESPACE);
		mEnvelope.addPrefix("tt", SCHEMA);
	}

	public void setMediaNamespace(String namespace) {
		NAMESPACE = namespace;
	}

	/********************************************************************************************************/

	public GetProfilesResponse getProfiles(GetProfiles request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetProfilesResponse(obj);
	}

	public GetMetadataConfigurationsResponse getMetadataConfigurations(GetMetadataConfigurations request) throws IOException, XmlPullParserException,
			SoapFault {
		SoapObject obj = createService(request);
		return new GetMetadataConfigurationsResponse(obj);
	}

	public AddMetadataConfigurationResponse getAddMetadataConfiguration(AddMetadataConfiguration request) throws IOException, XmlPullParserException,
			SoapFault {
		SoapObject obj = createService(request);
		return new AddMetadataConfigurationResponse(obj);
	}

	public GetStreamUriResponse getStreamUri(GetStreamUri request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetStreamUriResponse(obj);
	}

	public GetSnapshotUriResponse getSnapshotUri(GetSnapshotUri request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetSnapshotUriResponse(obj);
	}

	public GetVideoEncoderConfigurationResponse getVideoEncoderConfiguration(GetVideoEncoderConfiguration request) throws IOException,
			XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetVideoEncoderConfigurationResponse(obj);
	}

	public GetVideoEncoderConfigurationOptionsResponse getVideoEncoderConfigurationOptions(GetVideoEncoderConfigurationOptions request)
			throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetVideoEncoderConfigurationOptionsResponse(obj);
	}

	public SetVideoEncoderConfigurationResponse setVideoEncoderConfiguration(SetVideoEncoderConfiguration request) throws IOException,
			XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new SetVideoEncoderConfigurationResponse(obj);
	}
}
