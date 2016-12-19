package org.onvif.ver10.schema;

public class Service {

	private String m_strServiceNamespace;
	private String m_strXAddr;

	public Service(String service_namespace, String xaddr) {
		this.m_strServiceNamespace = service_namespace;
		this.m_strXAddr = xaddr;
	}

	public String getNamespace() {
		return m_strServiceNamespace;
	}

	public String getXAddr() {
		return m_strXAddr;
	}

}
