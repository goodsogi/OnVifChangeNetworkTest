package org.onvif.ver10.schema;

public enum Category {

	All, Analytics, Device, Events, Imaging, Media, PTZ;

	public String value() {
		return name();
	}

	public static Category fromValue(String v) {
		return valueOf(v);
	}

}