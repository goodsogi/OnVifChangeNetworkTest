package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.schema.nativeParcel.ProbeMatch;

import java.util.ArrayList;

public class GetProbeResponse extends SoapObject {
	private final static String METHOD_NAME = GetProbeResponse.class.getSimpleName();

	public ArrayList<ProbeMatch> mProbeMatches;

	public GetProbeResponse() {
		super(null, METHOD_NAME);
	}

	public GetProbeResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			mProbeMatches = new ArrayList<ProbeMatch>();
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// ProbeMatch
				// =================================================================================
				if (root.getName().equalsIgnoreCase("ProbeMatch")) {
					SoapObject probeMatch = (SoapObject) root.getValue();
					String Types = null;
					String Scopes = null;
					String XAddrs = null;
					int MetadataVersion = 0;
					if (probeMatch.hasProperty("Types")) {
						Types = probeMatch.getPropertyAsString("Types");
					}
					if (probeMatch.hasProperty("Scopes")) {
						Scopes = probeMatch.getPropertyAsString("Scopes");
					}
					if (probeMatch.hasProperty("XAddrs")) {
						XAddrs = probeMatch.getPropertyAsString("XAddrs");
					}
					if (probeMatch.hasProperty("MetadataVersion")) {
						MetadataVersion = Integer.parseInt(probeMatch.getPropertyAsString("MetadataVersion"));
					}
//					mProbeMatches.add(new ProbeMatch(Types, Scopes, XAddrs, MetadataVersion));
				}
			}
		}
	}

	public void setProbeMatches(ArrayList<ProbeMatch> list) {
		if (list == null) {
			return;
		}
		// SoapObject mSoapObjectRoot = new SoapObject(null, "ProbeMatches");
		int size = list.size();
		for (int i = 0; i < size; i++) {
			SoapObject mSoapProbeMatch = new SoapObject(null, "ProbeMatch");
			mSoapProbeMatch.addProperty("Types", list.get(i).mTypes);
			mSoapProbeMatch.addProperty("Scopes", list.get(i).mScopes);
			mSoapProbeMatch.addProperty("XAddrs", list.get(i).mXAddrs);
			mSoapProbeMatch.addProperty("MetadataVersion", list.get(i).mMetadataVersion);
			addSoapObject(mSoapProbeMatch);
		}
		// addSoapObject(mSoapObjectRoot);
	}
}
