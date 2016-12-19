package org.onvif.ver10.schema;

public class ReceiverMode {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	public final static int RECEIVER_MODE_UNKNOWN = 0; // This case should never happen.
	public final static int RECEIVER_MODE_AUTO = 1; // The receiver connects on demand, as required by consumers of the media streams.
	public final static int RECEIVER_MODE_ALWAYS = 2; // The receiver attempts to maintain a persistent connection to the configured endpoint.
	public final static int RECEIVER_MODE_NEVER = 3;// The receiver does not attempt to connect.
	
	
	public final static String RECEIVER_MODE_UNKNOWN_STR = "Unknown"; 
	public final static String RECEIVER_MODE_AUTO_STR ="AutoConnect"; 
	public final static String RECEIVER_MODE_ALWAYS_STR = "AlwaysConnect"; 
	public final static String RECEIVER_MODE_NEVER_STR = "NeverConnect"; 
	

}