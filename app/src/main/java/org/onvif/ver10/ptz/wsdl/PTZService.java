package org.onvif.ver10.ptz.wsdl;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.ptz.wsdl.get.GetNode;
import org.onvif.ver10.ptz.wsdl.get.GetNodeResponse;
import org.onvif.ver10.ptz.wsdl.get.GetPresets;
import org.onvif.ver10.ptz.wsdl.get.GetPresetsResponse;
import org.onvif.ver10.ptz.wsdl.set.ContinuousMove;
import org.onvif.ver10.ptz.wsdl.set.ContinuousMoveResponse;
import org.onvif.ver10.ptz.wsdl.set.GotoHomePosition;
import org.onvif.ver10.ptz.wsdl.set.GotoHomePositionResponse;
import org.onvif.ver10.ptz.wsdl.set.GotoPreset;
import org.onvif.ver10.ptz.wsdl.set.GotoPresetResponse;
import org.onvif.ver10.ptz.wsdl.set.RemovePreset;
import org.onvif.ver10.ptz.wsdl.set.RemovePresetResponse;
import org.onvif.ver10.ptz.wsdl.set.SetHomePosition;
import org.onvif.ver10.ptz.wsdl.set.SetHomePositionResponse;
import org.onvif.ver10.ptz.wsdl.set.SetPreset;
import org.onvif.ver10.ptz.wsdl.set.SetPresetResponse;
import org.onvif.ver10.ptz.wsdl.set.Stop;
import org.onvif.ver10.ptz.wsdl.set.StopResponse;
import org.onvif.ver10.schema.BaseService;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class PTZService extends BaseService {
	public static final String NAMESPACE = "http://www.onvif.org/ver20/ptz/wsdl";
	public static final String SCHEMA = "http://www.onvif.org/ver10/schema";

	public PTZService(String url) {
		super(url);
		mEnvelope.addPrefix("tptz", NAMESPACE);
		mEnvelope.addPrefix("tt", SCHEMA);
	}

	public PTZService(String url, int timeout) {
		super(url, timeout);
		mEnvelope.addPrefix("tptz", NAMESPACE);
		mEnvelope.addPrefix("tt", SCHEMA);
	}

	/********************************************************************************************************/
	public GetNodeResponse getNode(GetNode request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetNodeResponse(obj);
	}

	public ContinuousMoveResponse continuousMove(ContinuousMove request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new ContinuousMoveResponse(obj);
	}

	public StopResponse stop(Stop request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new StopResponse(obj);
	}

	public GotoHomePositionResponse gotoHomePosition(GotoHomePosition request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GotoHomePositionResponse(obj);
	}

	public SetHomePositionResponse setHomePosition(SetHomePosition request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new SetHomePositionResponse(obj);
	}

	public GetPresetsResponse getPresets(GetPresets request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GetPresetsResponse(obj);
	}
	public SetPresetResponse setPreset(SetPreset request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new SetPresetResponse(obj);
	}
	public RemovePresetResponse removePreset(RemovePreset request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new RemovePresetResponse(obj);
	}
	
	public GotoPresetResponse gotoPreset(GotoPreset request) throws IOException, XmlPullParserException, SoapFault {
		SoapObject obj = createService(request);
		return new GotoPresetResponse(obj);
	}
}
