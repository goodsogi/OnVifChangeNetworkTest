package org.onvif.ver10.image.wsdl;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.image.wsdl.get.GetImagingSettings;
import org.onvif.ver10.image.wsdl.get.GetImagingSettingsResponse;
import org.onvif.ver10.image.wsdl.get.GetOptions;
import org.onvif.ver10.image.wsdl.get.GetOptionsResponse;
import org.onvif.ver10.image.wsdl.set.SetImagingSettings;
import org.onvif.ver10.image.wsdl.set.SetImagingSettingsResponse;
import org.onvif.ver10.schema.BaseService;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class ImageService extends BaseService {
	public static final String NAMESPACE = "http://www.onvif.org/ver20/imaging/wsdl";
	public static final String SCHEMA = "http://www.onvif.org/ver10/schema";

	public ImageService(String url) {
		super(url);
		mEnvelope.addPrefix("timg", NAMESPACE);
		mEnvelope.addPrefix("tt", SCHEMA);
	}

	public ImageService(String url, int timeout) {
		super(url, timeout);
		mEnvelope.addPrefix("timg", NAMESPACE);
		mEnvelope.addPrefix("tt", SCHEMA);
	}

	/********************************************************************************************************/
	public GetOptionsResponse getOptions(GetOptions request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetOptionsResponse(obj);
	}

	public GetImagingSettingsResponse getImagingSettings(GetImagingSettings request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetImagingSettingsResponse(obj);
	}

	public SetImagingSettingsResponse setImagingSettings(SetImagingSettings request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new SetImagingSettingsResponse(obj);
	}
}
