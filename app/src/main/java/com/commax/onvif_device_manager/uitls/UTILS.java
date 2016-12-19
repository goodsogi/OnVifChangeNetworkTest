package com.commax.onvif_device_manager.uitls;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.os.Vibrator;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

public class UTILS {
	private final static String TAG = "UTILS";
	public final static String CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
	/**
	 * Types of device
	 */
	public final static short DISPLAY_TABLET = 1;
	public final static short DISPLAY_MOBILE = 2;
	public static short DISPLAY_MODE;
	public static int g_iMjpegIconSize;

	public static boolean DEVICE_UNIT_STATE_CHECK = false;

	/**
	 * VideoPlayView -> Fragment Return Value (onResume)
	 */
	public final static short RETURN_NONE = 0;
	public final static short SCREEN_ON = 1;
	public final static short SCREEN_OFF = 2;
	public final static short GOTO_SETTINGS = 3;

	/**
	 * Only once Excute
	 */
	public final static int INET4ADDRESS = 1;
	// public final static int INET6ADDRESS = 2;

	/**
	 * EXIT Flag
	 */
	public static boolean EXIT_FLAG = false;

	public static boolean isLandscape(Context context) {
		boolean value = true;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		int rotation = disp.getRotation();
		switch (rotation) {
		case Surface.ROTATION_0:
			value = false;
			break;
		case Surface.ROTATION_90:
			value = true;
			break;
		case Surface.ROTATION_180:
			value = false;
			break;
		case Surface.ROTATION_270:
			value = true;
			break;
		}
		LOG.i(TAG, "isLandscape:" + rotation);
		return value;
	}

	/************************************************************************************************
	 * SAVE FILE NAME REPLACE
	 ************************************************************************************************/
	public static String makeValidFileName(String fileName, String replaceStr) {
		if (fileName == null || fileName.trim().length() == 0 || replaceStr == null)
			return String.valueOf(System.currentTimeMillis());

		return fileName.replaceAll("[:\\\\/%*?:|\"<>]", replaceStr);
	}

	/************************************************************************************************
	 * Vibrator
	 ************************************************************************************************/
	public static void Vibrator(Context context, int ms_time) {
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(ms_time);
	}

	/************************************************************************************************
	 * getLocal IP
	 ************************************************************************************************/
	public static String getLocalIpAddress(Context mContext, int type) {
		String realIP = null;
		try {
			ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
			for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = (NetworkInterface) en.nextElement();
				for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						switch (type) {
						// case INET6ADDRESS:
						// if (inetAddress instanceof Inet6Address) {
						//
						// if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
						// && inetAddress.isSiteLocalAddress()) {
						// return inetAddress.getHostAddress().toString();
						// }
						// }
						// break;
						case INET4ADDRESS:
							if (inetAddress instanceof Inet4Address) {
								boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
								if (isWifi) {
									LOG.i(TAG, "getLocalIpAddress is isWifi");
									WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
									DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
									String[] dhcpInfos = dhcpInfo.toString().split(" ");
									int l = 0;
									while (l < dhcpInfos.length) {
										if (dhcpInfos[l].equals("ipaddr")) {
											realIP = dhcpInfos[l + 1];
											break;
										}
										l++;
									}
									if (realIP == null) {
										LOG.i(TAG, "getLocalIpAddress is isWifi realIP1");
										if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
											realIP = inetAddress.getHostAddress().toString();
											LOG.i(TAG, "getLocalIpAddress is isWifi realIP2  :" + realIP);
											break;
										}
									}
								} else {
									LOG.i(TAG, "getLocalIpAddress is realIP1");
									
									if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
										realIP = inetAddress.getHostAddress().toString();
										LOG.i(TAG, "getLocalIpAddress is realIP2  :" + realIP);
										break;
									}
								}
							}
							break;
						}
					}
				}
			}
		} catch (SocketException ex) {
			ex.toString();
		}

		if (realIP == null) {
			LOG.e(TAG, "getLocalIpAddress is null");
			SocketThread thread = new SocketThread();
			thread.start();
			while (thread.isRun()) {
				UTILS.SLEEP(100);
			}
			realIP = thread.getRealIP();
			thread = null;
		}
		LOG.i(TAG, "getLocalIpAddress is :" + realIP );
		return realIP;
	}

	public static boolean enableNetwork(Context context) {
		try {
			ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo lte = manager.getNetworkInfo(ConnectivityManager.TYPE_WIMAX);
			NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			// NetworkInfo ethernet = manager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);

			// try {
			// if (ethernet.isConnected()) {
			// LOG.i(TAG, "ethernet isConnected");
			// return true;
			// }
			// } catch (Exception e) {
			//
			// }

			if (wifi.isConnected()) {
				LOG.i(TAG, "wifi isConnected");
				return true;
			}

			if (mobile.isConnected()) {
				LOG.i(TAG, "mobile isConnected");
				return true;
			}

			if (lte.isConnected()) {
				LOG.i(TAG, "lte isConnected");
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/************************************************************************************************
	 * ThreadSleep
	 * 
	 * @throws InterruptedException
	 ************************************************************************************************/
	public static void SLEEP(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/************************************************************************************************
	 * Custom Dialog (Two button or One button)
	 ************************************************************************************************/
	public static void AlertDialog(Context context, String title, String msg, final DialogListener lin) {

		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		if (title != null)
			alert.setTitle(title);
		alert.setMessage(msg);
		alert.setCancelable(false);
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if (lin != null) {
					lin.callBack();
				}
				dialog.dismiss();
			}
		});
		alert.show();

	}

	public static void AlertDialog2(Context context, String title, String msg, final DialogListener lin) {

		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		if (title != null)
			alert.setTitle(title);
		alert.setMessage(msg);
		alert.setCancelable(false);
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if (lin != null) {
					lin.callBack();
				}
				dialog.dismiss();
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alert.show();

	}

	public interface DialogListener {
		public void callBack();

	}

	/************************************************************************************************
	 * Byte to Bitmap
	 ************************************************************************************************/
	public static Bitmap byteArrayToBitmap(byte[] $byteArray) {
		Bitmap bitmap = BitmapFactory.decodeByteArray($byteArray, 0, $byteArray.length);
		bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
		return bitmap;
	}

	/************************************************************************************************
	 * IPv4 Pattern
	 ************************************************************************************************/
	private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

	/************************************************************************************************
	 * Check IPv4
	 ************************************************************************************************/
	public static boolean isIPv4Address(final String input) {
		return IPV4_PATTERN.matcher(input).matches();
	}

	/************************************************************************************************
	 * Device Unit RealAddress
	 ************************************************************************************************/
	public static String setRealAddress(String $address) {
		try {
			String tempAddress[];
			tempAddress = $address.split("//");
			if (tempAddress.length <= 1) {
				if (!UTILS.isIPv4Address($address)) {
					if ($address.length() < 8)
						if (!$address.contains("."))
							$address += ".nuvicoconnect.com";
				}
			} else {
				$address = tempAddress[1];
			}
		} catch (Exception e) {
			return $address;
		}
		return $address;
	}

	/************************************************************************************************
	 * UTC ZERO Time
	 ************************************************************************************************/
	public static String getTimeStringUTC(long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT"));
		c.setTimeInMillis(time);

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		return String.format("%02d/%02d/%04d %02d:%02d:%02d", month, day, year, hour, minute, second);
	}

	/************************************************************************************************
	 * System Current Time 2
	 ************************************************************************************************/
	public static String getTimeStringIMG(long time) {
		Calendar c = Calendar.getInstance();
		// c.setTimeZone(TimeZone.getTimeZone("GMT"));
		c.setTimeInMillis(time);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		return String.format("%02d-%02d-%04d-%02dh%02dm%02ds.jpeg", month, day, year, hour, minute, second);
	}

	public static String getTimeStringREC(long time) {
		Calendar c = Calendar.getInstance();
		// c.setTimeZone(TimeZone.getTimeZone("GMT"));
		c.setTimeInMillis(time);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		return String.format("%02d-%02d-%04d-%02dh%02dm%02ds", month, day, year, hour, minute, second);
	}

	/************************************************************************************************
	 * String to Integer
	 ************************************************************************************************/
	public static int parseInt(String str) {
		try {
			if (str.equals("")) {
				return 0;
			} else {
				return Integer.parseInt(str);
			}
		} catch (Exception e) {
			return 0;
		}
	}

	/************************************************************************************************
	 * String to Integer
	 ************************************************************************************************/
	public static float parseFloat(String str) {
		try {
			if (str.equals("")) {
				return 0;
			} else {
				return Float.parseFloat(str);
			}
		} catch (Exception e) {
			return 0;
		}
	}

	/************************************************************************************************
	 * ArrayList to Integer Array
	 ************************************************************************************************/
	public static int[] parseIntArray(ArrayList<Integer> list) {
		try {
			if (list != null) {
				if (list.size() > 0) {
					int[] ret = new int[list.size()];
					for (int i = 0; i < ret.length; i++)
						ret[i] = list.get(i);
					return ret;
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

	/************************************************************************************************
	 * ArrayList to Long Array
	 ************************************************************************************************/
	public static long[] parseLongArray(ArrayList<Long> list) {
		try {
			if (list != null) {
				if (list.size() > 0) {
					long[] ret = new long[list.size()];
					for (int i = 0; i < ret.length; i++)
						ret[i] = list.get(i);
					return ret;
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

	/************************************************************************************************
	 * NavigationBar Visible
	 ************************************************************************************************/
	// public static int ShowNavigationBar(boolean b) {
	// return FragmentMain.setNavigationVisible(b);
	// }

	/************************************************************************************************
	 * Check to tablet
	 ************************************************************************************************/
	public static boolean isTablet(Context context) {
		// TODO: This hacky stuff goes away when we allow users to target devices
		int xlargeBit = Configuration.SCREENLAYOUT_SIZE_XLARGE; // upgrade to HC SDK to get this
		Configuration config = context.getResources().getConfiguration();
		return (config.screenLayout & xlargeBit) == xlargeBit;
		// return true;
	}

	public static boolean isTablet() {
		if (DISPLAY_MODE == DISPLAY_TABLET)
			return true;
		return false;
	}

	/************************************************************************************************
	 * SoftKeyboard Settings
	 ************************************************************************************************/
	public static void setSoftKeyboardLayout(Activity activity, boolean b) {
		try {
			if (b)
				activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
			else
				activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		} catch (Exception e) {

		}
	}

	/************************************************************************************************
	 * getExternalStoragePath
	 ************************************************************************************************/
	public static String getExternalStoragePath() {
		boolean exists = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		if (exists)
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		else
			return "/";
	}

	/************************************************************************************************
	 * Make Directory
	 ************************************************************************************************/
	public static void makeDirectory(String makeDirectoryName) {
		File path = new File(makeDirectoryName);
		if (!path.isDirectory()) {
			path.mkdirs();
			LOG.d(TAG, path.getAbsolutePath());
		}
	}

	/************************************************************************************************
	 * Make UUID
	 ************************************************************************************************/
	public static String createUUID() {
		return UUID.randomUUID().toString();
	}

	public static boolean FileCheck(String path) {
		try {
			File file = new File(path);
			return file.exists();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean setFileRename(String path, String rename) {
		try {
			File file = new File(path);
			File refile = new File(rename);
			if (file.renameTo(refile)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/************************************************************************************************
	 * Snapshot
	 ************************************************************************************************/
	public static Bitmap loadBackgroundBitmap(Context context, String imgFilePath) throws Exception, OutOfMemoryError {
		if (!FileCheck(imgFilePath)) {
			throw new FileNotFoundException("background-image file not found : " + imgFilePath);
		}

		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int displayWidth = display.getWidth();
		int displayHeight = display.getHeight();

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Config.RGB_565;
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(imgFilePath, options);

		float widthScale = options.outWidth / displayWidth;
		float heightScale = options.outHeight / displayHeight;
		float scale = widthScale > heightScale ? widthScale : heightScale;

		if (scale >= 8) {
			options.inSampleSize = 8;
		} else if (scale >= 6) {
			options.inSampleSize = 6;
		} else if (scale >= 4) {
			options.inSampleSize = 4;
		} else if (scale >= 2) {
			options.inSampleSize = 2;
		} else {
			options.inSampleSize = 1;
		}
		options.inJustDecodeBounds = false;
		LOG.d(TAG, "widthScale:" + widthScale + "  heightScale:" + heightScale);
		return BitmapFactory.decodeFile(imgFilePath, options);
	}

	public static Bitmap autoScaleBitmap(Context context, int bitmapResource) {
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int displayWidth = display.getWidth();
		int displayHeight = display.getHeight();

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Config.RGB_565;
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), bitmapResource, options);

		float widthScale = options.outWidth / displayWidth;
		float heightScale = options.outHeight / displayHeight;
		float scale = widthScale > heightScale ? widthScale : heightScale;

		if (scale >= 8) {
			options.inSampleSize = 8;
		} else if (scale >= 6) {
			options.inSampleSize = 6;
		} else if (scale >= 4) {
			options.inSampleSize = 4;
		} else if (scale >= 2) {
			options.inSampleSize = 2;
		} else {
			options.inSampleSize = 1;
		}
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeResource(context.getResources(), bitmapResource, options);
	}

	/************************************************************************************************
	 * check Service Running?
	 ************************************************************************************************/
	public static boolean getServiceTaskName(Context context) {
		boolean checked = false;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> info;
		info = am.getRunningServices(30);

		for (Iterator iterator = info.iterator(); iterator.hasNext();) {
			RunningServiceInfo runningTaskInfo = (RunningServiceInfo) iterator.next();
			if (runningTaskInfo.service.getClassName().equals("com.skymobis.agent.AgentService")) {
				checked = true;
				LOG.i(TAG, "Service is.... : " + checked);
			}
		}
		return checked;
	}

	/************************************************************************************************
	 * Get Ex SdCard Path
	 ************************************************************************************************/
	public static String getMicroSDCardDirectory() {
		List<String> mMounts = readMountsFile();
		List<String> mVold = readVoldFile();

		for (int i = 0; i < mMounts.size(); i++) {
			String mount = mMounts.get(i);

			if (!mVold.contains(mount)) {
				mMounts.remove(i--);
				continue;
			}

			File root = new File(mount);
			if (!root.exists() || !root.isDirectory()) {
				mMounts.remove(i--);
				continue;
			}

			if (!isAvailableFileSystem(mount)) {
				mMounts.remove(i--);
				continue;
			}

			if (!checkMicroSDCard(mount)) {
				mMounts.remove(i--);
			}
		}

		if (mMounts.size() == 1) {
			return mMounts.get(0);
		}

		return null;
	}

	private static List<String> readMountsFile() {
		/**
		 * Scan the /proc/mounts file and look for lines like this: /dev/block/vold/179:1 /mnt/sdcard vfat
		 * rw,dirsync,nosuid
		 * ,nodev,noexec,relatime,uid=1000,gid=1015,fmask=0602,dmask=0602,allow_utime=0020,codepage=cp437
		 * ,iocharset=iso8859-1,shortname=mixed,utf8,errors=remount-ro 0 0
		 * 
		 * When one is found, split it into its elements and then pull out the path to the that mount point and add it
		 * to the arraylist
		 */
		List<String> mMounts = new ArrayList<String>();

		try {
			Scanner scanner = new Scanner(new File("/proc/mounts"));

			while (scanner.hasNext()) {
				String line = scanner.nextLine();

				if (line.startsWith("/dev/block/vold/")) {
					String[] lineElements = line.split("[ \t]+");
					String element = lineElements[1];

					mMounts.add(element);
				}
			}
		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		return mMounts;
	}

	private static List<String> readVoldFile() {
		/**
		 * Scan the /system/etc/vold.fstab file and look for lines like this: dev_mount sdcard /mnt/sdcard 1
		 * /devices/platform/s3c-sdhci.0/mmc_host/mmc0
		 * 
		 * When one is found, split it into its elements and then pull out the path to the that mount point and add it
		 * to the arraylist
		 */

		List<String> mVold = new ArrayList<String>();

		try {
			Scanner scanner = new Scanner(new File("/system/etc/vold.fstab"));

			while (scanner.hasNext()) {
				String line = scanner.nextLine();

				if (line.startsWith("dev_mount")) {
					String[] lineElements = line.split("[ \t]+");
					String element = lineElements[2];

					if (element.contains(":")) {
						element = element.substring(0, element.indexOf(":"));
					}

					mVold.add(element);
				}
			}
		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		return mVold;
	}

	private static boolean checkMicroSDCard(String fileSystemName) {
		StatFs statFs = new StatFs(fileSystemName);

		long totalSize = (long) statFs.getBlockSize() * statFs.getBlockCount();

		LOG.i(TAG, "checkMicroSDCard totalSize:" + totalSize);
		// if(totalSize < (1073741820)){ // 1 Gbyte
		if (totalSize < (268435456)) { // 256 Mbyte
			return false;
		}

		return true;
	}

	private static boolean isAvailableFileSystem(String fileSystemName) {
		final String[] unAvailableFileSystemList = { "/dev", "/mnt/asec", "/mnt/obb", "/system", "/data", "/cache", "/efs", "/firmware" };

		for (String name : unAvailableFileSystemList) {
			if (fileSystemName.contains(name) == true) {
				return false;
			}
		}

		if (Environment.getExternalStorageDirectory().getAbsolutePath().equals(fileSystemName) == true) {
			return false;
		}

		return true;
	}

	/************************************************************************************************
	 * readableFileSize
	 *************************************************************************************************/
	public static String readableFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
}
