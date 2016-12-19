package org.onvif.ver10.media.wsdl.set;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.media.wsdl.MediaService;

public class AddMetadataConfiguration extends SoapObject {
	public static final String METHOD_NAME = "AddMetadataConfiguration";


	public AddMetadataConfiguration() {
		super(MediaService.NAMESPACE, METHOD_NAME);
		this.addAttribute("xmlns", MediaService.NAMESPACE);	
	}
	
	public void setProfileToken(String value) {
        this.addProperty("ProfileToken", value);
    }
	
	public void setConfigurationToken(String value){
		  this.addProperty("ConfigurationToken", value);		  
	}
}

