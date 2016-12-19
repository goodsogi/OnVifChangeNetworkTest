package org.onvif.ver10.device.wsdl.set;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SetSystemDateAndTime {
	public static final String METHOD_NAME = SetSystemDateAndTime.class.getSimpleName();

	private SoapObject mSoapObject;

	public String mDateTimeType; // {"Manual", "NTP"}
	public boolean mDaylightSavings;
	public String mTZ; // Time zone ID
	public GregorianCalendar mUTCDateTime;

	// Extension
	public boolean mUse24HourFormat;
	public String mDateFormat; // {“yyyy/MM/dd”, “MM/dd/yyyy”, “dd/MM/yyyy”}

	public SetSystemDateAndTime() {

	}

	public SetSystemDateAndTime(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			int year = 0, month = 0, day = 0, hour = 0, minute = 0, second = 0;
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// DateTimeType
				// =================================================================================
				if (root.getName().equalsIgnoreCase("DateTimeType")) {
					mDateTimeType = root.getValue().toString();
				}
				// =================================================================================
				// DaylightSavings
				// =================================================================================
				else if (root.getName().equalsIgnoreCase("DaylightSavings")) {
					mDaylightSavings = Boolean.parseBoolean(root.getValue().toString());
				}
				// =================================================================================
				// TimeZone
				// =================================================================================
				else if (root.getName().equalsIgnoreCase("TimeZone")) {
					mTZ = ((SoapObject) root.getValue()).getPropertyAsString("TZ");
				}
				// =================================================================================
				// UTCDateTime
				// =================================================================================
				else if (root.getName().equalsIgnoreCase("UTCDateTime")) {
					SoapObject UTCDateTime = (SoapObject) root.getValue();
					for (int u = 0; u < UTCDateTime.getPropertyCount(); u++) {
						PropertyInfo utcdatetime = new PropertyInfo();
						UTCDateTime.getPropertyInfo(u, utcdatetime);
						// =================================================================================
						// Time
						// =================================================================================
						if (utcdatetime.getName().equalsIgnoreCase("Time")) {
							hour = Integer.parseInt(((SoapObject) utcdatetime.getValue()).getPropertyAsString("Hour"));
							minute = Integer.parseInt(((SoapObject) utcdatetime.getValue())
									.getPropertyAsString("Minute"));
							second = Integer.parseInt(((SoapObject) utcdatetime.getValue())
									.getPropertyAsString("Second"));
						}
						// =================================================================================
						// Date
						// =================================================================================
						else if (utcdatetime.getName().equalsIgnoreCase("Date")) {
							year = Integer.parseInt(((SoapObject) utcdatetime.getValue()).getPropertyAsString("Year"));
							month = Integer
									.parseInt(((SoapObject) utcdatetime.getValue()).getPropertyAsString("Month"));
							day = Integer.parseInt(((SoapObject) utcdatetime.getValue()).getPropertyAsString("Day"));
						}
					}
					
					if(month - 1 < 0){
						month = 1;
					}
					
					mUTCDateTime = new GregorianCalendar(year, month - 1, day, hour, minute, second);
				}
				// =================================================================================
				// Extension
				// =================================================================================
				else if (root.getName().equalsIgnoreCase("Extension")) {
					if (((SoapObject) root.getValue()).hasProperty("Use24HourFormat"))
						mUse24HourFormat = Boolean.parseBoolean(((SoapObject) root.getValue())
								.getPropertyAsString("Use24HourFormat"));

					if (((SoapObject) root.getValue()).hasProperty("DateFormat"))
						mDateFormat = ((SoapObject) root.getValue()).getPropertyAsString("DateFormat");

				}
			}

		}
	}

	public SoapObject getSoapObject() {
		return mSoapObject;
	}

	public void setSystenDateAndTime(String DateTimeType, boolean DaylightSavings /* automatic date&time */, String TZ,
									 Calendar UTCDateTime, boolean Extension, boolean Use24HourFormat, String DateFormat) {
		mSoapObject = new SoapObject(DeviceService.NAMESPACE, METHOD_NAME);

		// DateTimeType
		mSoapObject.addProperty(DeviceService.NAMESPACE, "DateTimeType", DateTimeType);

		// DaylightSavings
		mSoapObject.addProperty(DeviceService.NAMESPACE, "DaylightSavings", DaylightSavings);

		// Time Zone
		if (TZ != null && !TZ.equals("")) {
			SoapObject soapTimeZone = new SoapObject(DeviceService.NAMESPACE, "TimeZone");
			soapTimeZone.addProperty(DeviceService.SCHEMA, "TZ", TZ);
			mSoapObject.addSoapObject(soapTimeZone);
		}

		// UTCDateTime
		if (UTCDateTime != null) {
			SoapObject soapUTCDateTime = new SoapObject(DeviceService.NAMESPACE, "UTCDateTime");
			SoapObject soapUTCTime = new SoapObject(DeviceService.SCHEMA, "Time");
			soapUTCTime.addProperty(DeviceService.SCHEMA, "Hour", UTCDateTime.get(Calendar.HOUR_OF_DAY));
			soapUTCTime.addProperty(DeviceService.SCHEMA, "Minute", UTCDateTime.get(Calendar.MINUTE));
			soapUTCTime.addProperty(DeviceService.SCHEMA, "Second", UTCDateTime.get(Calendar.SECOND));
			SoapObject soapUTCDate = new SoapObject(DeviceService.SCHEMA, "Date");
			soapUTCDate.addProperty(DeviceService.SCHEMA, "Year", UTCDateTime.get(Calendar.YEAR));
			soapUTCDate.addProperty(DeviceService.SCHEMA, "Month", UTCDateTime.get(Calendar.MONTH) + 1);
			soapUTCDate.addProperty(DeviceService.SCHEMA, "Day", UTCDateTime.get(Calendar.DAY_OF_MONTH));

			soapUTCDateTime.addSoapObject(soapUTCTime);
			soapUTCDateTime.addSoapObject(soapUTCDate);
			mSoapObject.addSoapObject(soapUTCDateTime);
		}
		if (Extension) {
			// Extension
			SoapObject soapExtension = new SoapObject(DeviceService.NAMESPACE, "Extension");
			soapExtension.addProperty(DeviceService.SCHEMA, "Use24HourFormat", Use24HourFormat);
			if (DateFormat != null) {
				soapExtension.addProperty(DeviceService.SCHEMA, "DateFormat", DateFormat);
			}
			mSoapObject.addSoapObject(soapExtension);
		}
	}
}
