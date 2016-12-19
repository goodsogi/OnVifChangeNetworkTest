package org.onvif.ver10.schema;

public class SearchState {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	public final static int SEARCH_STATE_UNKNOWN = 0;
	public final static int SEARCH_STATE_QUEUED = 1;
	public final static int SEARCH_STATE_SEARCHING = 2;
	public final static int SEARCH_STATE_COMPLETED = 3;

	public final static String SEARCH_STATE_UNKNOWN_STR = "Queued";
	public final static String SEARCH_STATE_QUEUED_STR = "Searching";
	public final static String SEARCH_STATE_SEARCHING_STR = "Completed";
	public final static String SEARCH_STATE_COMPLETED_STR = "Unknown";
}