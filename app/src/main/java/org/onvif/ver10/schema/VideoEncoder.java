package org.onvif.ver10.schema;

public class VideoEncoder {
	private final static String TAG = "VideoEncoder";

	private String mVideoEncoderName;
	private int mVideoEncoderUseCount;
	private String mVideoEncoderEncoding;
	private int mVideoEncoderWidth;
	private int mVideoEncoderHeight;
	private float mVideoEncoderQuality;
	private int mVideoEncoderFrameRateLimit;
	private int mVideoEncoderEncodingInterval;
	private int mVideoEncoderBitrateLimit;
	private int mVideoEncoderGovLength;
	private String mVideoEncoderProfile;

	// ===================================================================================
	// SET METHOD
	// ===================================================================================
	public void setVideoEncoderName(String name) {
		mVideoEncoderName = name;
	}	
	public void setVideoEncoderUseCount(int usecount) {
		mVideoEncoderUseCount = usecount;
	}

	public void setVideoEncoderEncoding(String encoding) {
		mVideoEncoderEncoding = encoding;
	}

	public void setVideoEncoderWidth(int width) {
		mVideoEncoderWidth = width;
	}

	public void setVideoEncoderHeight(int height) {
		mVideoEncoderHeight = height;
	}

	public void setVideoEncoderQuality(float quality) {
		mVideoEncoderQuality = quality;
	}

	public void setVideoEncoderFrameRateLimit(int framerate) {
		mVideoEncoderFrameRateLimit = framerate;
	}

	public void setVideoEncoderEncodingInterval(int interval) {
		mVideoEncoderEncodingInterval = interval;
	}

	public void setVideoEncoderBitrateLimit(int bitrate) {
		mVideoEncoderBitrateLimit = bitrate;
	}

	public void setVideoEncoderGovLength(int gov) {
		mVideoEncoderGovLength = gov;
	}

	public void setVideoEncoderProfile(String support) {
		mVideoEncoderProfile = support;
	}

	// ===================================================================================
	// GET METHOD
	// ===================================================================================
	public String getVideoEncoderName() {
		return mVideoEncoderName;
	}

	public int getVideoEncoderUseCount() {
		return mVideoEncoderUseCount;
	}

	public String getVideoEncoderEncoding() {
		return mVideoEncoderEncoding;
	}

	public int getVideoEncoderWidth() {
		return mVideoEncoderWidth;
	}

	public int getVideoEncoderHeight() {
		return mVideoEncoderHeight;
	}

	public float getVideoEncoderQuality() {
		return mVideoEncoderQuality;
	}

	public int getVideoEncoderFrameRateLimit() {
		return mVideoEncoderFrameRateLimit;
	}

	public int getVideoEncoderEncodingInterval() {
		return mVideoEncoderEncodingInterval;
	}

	public int getVideoEncoderBitrateLimit() {
		return mVideoEncoderBitrateLimit;
	}

	public int getVideoEncoderGovLength() {
		return mVideoEncoderGovLength;
	}

	public String getVideoEncoderProfile() {
		return mVideoEncoderProfile;
	}

}
