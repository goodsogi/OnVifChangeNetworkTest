package org.onvif.ver10.schema.nativeParcel;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class utils {
	private final static String TAG = utils.class.getSimpleName();
	/************************************************************************************************
	 * AP_MODULE_ENABLED STATE
	 ************************************************************************************************/
	public static final int STATE_AP_MODULE_ENABLED = 0;
	public static final int STATE_AP_MODULE_DISABLED = 1;
	public static final int STATE_AP_MODULE_UNKNOWN = 2;
	/************************************************************************************************
	 * IPv4 Pattern
	 ************************************************************************************************/
	private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

	/************************************************************************************************
	 * Check IPv4
	 ************************************************************************************************/
	public static boolean isIPv4Address(final String input) {
		if (input == null) {
			return false;
		}
		return IPV4_PATTERN.matcher(input).matches();
	}

	/************************************************************************************************
	 * Prefix to Netamsk
	 ************************************************************************************************/
	public static InetAddress getIPv4LocalNetMask(int netPrefix) {

		try {
			// Since this is for IPv4, it's 32 bits, so set the sign value of
			// the int to "negative"...
			int shiftby = (1 << 31);
			// For the number of bits of the prefix -1 (we already set the sign bit)
			for (int i = netPrefix - 1; i > 0; i--) {
				// Shift the sign right... Java makes the sign bit sticky on a shift...
				// So no need to "set it back up"...
				shiftby = (shiftby >> 1);
			}
			// Transform the resulting value in xxx.xxx.xxx.xxx format, like if
			// / it was a standard address...
			String maskString = Integer.toString((shiftby >> 24) & 255) + "." + Integer.toString((shiftby >> 16) & 255) + "."
					+ Integer.toString((shiftby >> 8) & 255) + "." + Integer.toString(shiftby & 255);
			// Return the address thus created...
			return InetAddress.getByName(maskString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Something went wrong here...
		return null;
	}

	/****************************************************************
	 * writeString
	 ****************************************************************/
	public static void writeString(Parcel dst, String val) {
		if (dst != null) {
			if (val != null) {
				dst.writeInt(1);
				dst.writeString(val);
			} else {
				dst.writeInt(0);
			}
		}
	}

	/****************************************************************
	 * writeBoolean
	 ****************************************************************/
	public static void writeBoolean(Parcel dst, Boolean val) {
		if (dst != null) {
			if (val) {
				dst.writeInt(1);
			} else {
				dst.writeInt(0);
			}
		}
	}

	/****************************************************************
	 * writeInt
	 ****************************************************************/
	public static void writeInt(Parcel dst, int val) {
		if (dst != null) {
			dst.writeInt(val);
		}
	}

	/****************************************************************
	 * writeFloat
	 ****************************************************************/
	public static void writeFloat(Parcel dst, float val) {
		if (dst != null) {
			dst.writeFloat(val);
		}
	}

	/****************************************************************
	 * writeLong
	 ****************************************************************/
	public static void writeLong(Parcel dst, long val) {
		if (dst != null) {
			dst.writeLong(val);
		}
	}

	/****************************************************************
	 * readString
	 ****************************************************************/
	public static String readString(Parcel src) {
		String val = null;
		if (src != null) {
			if (src.readInt() != 0) {
				val = src.readString();
			}
		}
		return val;
	}

	/****************************************************************
	 * readBoolean
	 ****************************************************************/
	public static boolean readBoolean(Parcel src) {
		if (src != null) {
			return src.readInt() == 1;
		}
		return false;
	}

	/****************************************************************
	 * readInt
	 ****************************************************************/
	public static int readInt(Parcel src) {
		if (src != null) {
			return src.readInt();
		}
		return -1;
	}

	/****************************************************************
	 * readFloat
	 ****************************************************************/
	public static float readFloat(Parcel src) {
		if (src != null) {
			return src.readFloat();
		}
		return -1;
	}

	/****************************************************************
	 * readLong
	 ****************************************************************/
	public static long readLong(Parcel src) {
		if (src != null) {
			return src.readLong();
		}
		return -1;
	}

	/****************************************************************
	 * byteArrayToParcel
	 ****************************************************************/
	public static Parcelable byteArrayToParcel(byte[] bundleBytes, String key) {
		final Parcel parcel = Parcel.obtain();
		parcel.unmarshall(bundleBytes, 0, bundleBytes.length);
		parcel.setDataPosition(0);
		Bundle bundle = (Bundle) parcel.readBundle();
		parcel.recycle();
		return bundle.getParcelable(key);
	}

	/****************************************************************
	 * parcelToByteArray
	 ****************************************************************/
	public static byte[] parcelToByteArray(Parcelable data, String key) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(key, data);
		final Parcel parcel = Parcel.obtain();
		bundle.writeToParcel(parcel, 0);
		byte[] bundleBytes = parcel.marshall();
		parcel.recycle();
		return bundleBytes;
	}

	/****************************************************************
	 * byteArrayToParcel
	 ****************************************************************/
	public static String toWsdlString(String value) {
		if (value == null) {
			return "";
		}
		if (value.contains("anyType")) {
			return "";
		}
		return value;
	}

	/****************************************************************
	 * formatKernelVersion
	 ****************************************************************/
	private static String readLine(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename), 256);
		try {
			return reader.readLine();
		} finally {
			reader.close();
		}
	}

	public static String formatKernelVersion() {
		// Example (see tests for more):
		// Linux version 3.0.31-g6fb96c9 (android-build@xxx.xxx.xxx.xxx.com) \
		// (gcc version 4.6.x-xxx 20120106 (prerelease) (GCC) ) #1 SMP PREEMPT \
		// Thu Jun 28 11:02:39 PDT 2012
		try {
			final String PROC_VERSION_REGEX = "Linux version (\\S+) " + /* group 1: "3.0.31-g6fb96c9" */
			"\\((\\S+?)\\) " + /* group 2: "x@y.com" (kernel builder) */
			"(?:\\(gcc.+? \\)) " + /* ignore: GCC version information */
			"(#\\d+) " + /* group 3: "#1" */
			"(?:.*?)?" + /* ignore: optional SMP, PREEMPT, and any CONFIG_FLAGS */
			"((Sun|Mon|Tue|Wed|Thu|Fri|Sat).+)"; /* group 4: "Thu Jun 28 11:02:39 PDT 2012" */
			String rawKernelVersion = readLine("/proc/version");
			Matcher m = Pattern.compile(PROC_VERSION_REGEX).matcher(rawKernelVersion);
			if (!m.matches()) {
				return "Unavailable";
			} else if (m.groupCount() < 4) {
				return "Unavailable";
			}
			return m.group(1) + "\n" + // 3.0.31-g6fb96c9
					m.group(2) + " " + m.group(3) + "\n" + // x@y.com #1
					m.group(4); // Thu Jun 28 11:02:39 PDT 2012
		} catch (Exception e) {
			e.printStackTrace();
			return "Unavailable";
		}
	}

	/****************************************************************
	 * isAPModule
	 ****************************************************************/
	public static int isAPModule() {
		String model = Build.MODEL;
		int isAPModule = STATE_AP_MODULE_UNKNOWN;

		if (model != null) {
			// SDHN Wifi 모델
			if (model.equals("NVR-400SW")) {
				isAPModule = STATE_AP_MODULE_ENABLED;
			}
			// SKYMOBIS Wifi 모델
			else if (model.equals("HN-W400")) {
				isAPModule = STATE_AP_MODULE_ENABLED;
			} else {
				isAPModule = STATE_AP_MODULE_UNKNOWN;
			}
		}

		return isAPModule;
	}
}