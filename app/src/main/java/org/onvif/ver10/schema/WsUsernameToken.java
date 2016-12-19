package org.onvif.ver10.schema;

import org.kobjects.base64.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.TimeZone;

public class WsUsernameToken {
	private final static String TAG = WsUsernameToken.class.getSimpleName();
	protected String mUsername;
	protected String mPassword;
	protected String mNonce;
	protected String mCreated;
	protected String mDigest;
	private long mLocalTimeOffset = 0;
	private long mUTCTimeOffset = 0;

	public static TimeZone utc = TimeZone.getTimeZone("UTC");

	public WsUsernameToken(String username, String password, long utcTime) {
		setUTCTimeOffset(utcTime);
		mUsername = username;
		mPassword = password;

		if (mUsername == null) {
			mUsername = "";
		}
		if (mPassword == null) {
			mPassword = "";
		}
	}

	public String getUser_Passowrd() {
		return mUsername + ":" + mPassword;
	}

	// ===================================================================================================
	// GetSystemDateAndTime의 UTC Time과 해당 System의 시간 Offset을 설정 함.
	// ===================================================================================================
	public void setUTCTimeOffset(long utcTime) {
		long clientTime = System.currentTimeMillis();
		mUTCTimeOffset = utcTime - clientTime;
	}

	public long getUTCTimeOffSet() {
		return mUTCTimeOffset;
	}

	// ===================================================================================================
	// GetSystemDateAndTime의 Local Time과 해당 System의 시간 Offset을 설정 함.
	// ===================================================================================================
	public void setLocalTimeOffset(long time) {
		long clientTime = System.currentTimeMillis();
		mLocalTimeOffset = time - clientTime;
	}

	public long getLocalTimeOffSet() {
		return mLocalTimeOffset;
	}

	private String localToGmtTimestamp() {
		return BaseService.sdf.format(new Date(System.currentTimeMillis() + mUTCTimeOffset));
	}

	private String calculatePasswordDigest(byte[] nonceBytes, String created, String password) {
		String encoded = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.reset();
			md.update(nonceBytes);
			md.update(created.getBytes());
			md.update(password.getBytes());

			byte[] encodedPassword = md.digest();
			encoded = Base64.encode(encodedPassword);

		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

		return encoded;
	}

	public void calculatePasswordDigest() {
		// Generate a random nonce
		byte[] nonceBytes = new byte[16];
		for (int i = 0; i < 16; ++i)
			nonceBytes[i] = (byte) (Math.random() * 256 - 128);

		mNonce = Base64.encode(nonceBytes);
		mCreated = localToGmtTimestamp();
		mDigest = calculatePasswordDigest(nonceBytes, mCreated, mPassword);

	}

	public String getNonce() {
		return mNonce;
	}

	public String getCreated() {
		return mCreated;
	}

	public String getDigest() {
		return mDigest;
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		if (username == null) {
			username = "";
		}
		mUsername = username;

	}

	public void setUserpassword(String password) {
		if (password == null) {
			password = "";
		}
		mPassword = password;
	}
}
