package org.onvif.ver10.device.wsdl.get;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.device.wsdl.DeviceService;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GetSystemDateAndTimeResponse extends SoapObject {
	private final static String METHOD_NAME = GetSystemDateAndTimeResponse.class.getSimpleName();

	public String mDateTimeType; // {"Manual", "NTP"}
	public boolean mDaylightSavings;
	public String mTZ; // Time zone ID
	public GregorianCalendar mUTCDateTime;
	public GregorianCalendar mLocalDateTime;

	// Extension
	public boolean mUse24HourFormat;
	public String mDateFormat; // {“yyyy/MM/dd”, “MM/dd/yyyy”, “dd/MM/yyyy”}


	public GetSystemDateAndTimeResponse() {
		super(DeviceService.NAMESPACE, METHOD_NAME);
	}

	public GetSystemDateAndTimeResponse(SoapObject obj) {
		if (obj != null && obj.getName().equals(METHOD_NAME)) {
			int year = 0, month = 0, day = 0, hour = 0, minute = 0, second = 0;
			for (int i = 0; i < obj.getPropertyCount(); i++) {
				PropertyInfo root = new PropertyInfo();
				obj.getPropertyInfo(i, root);
				// =================================================================================
				// Manufacturer
				// =================================================================================
				if (root.getName().equalsIgnoreCase("SystemDateAndTime")) {
					SoapObject systemDateAndTime = (SoapObject) root.getValue();
					for (int j = 0; j < systemDateAndTime.getPropertyCount(); j++) {
						PropertyInfo subSoapObject = new PropertyInfo();
						systemDateAndTime.getPropertyInfo(j, subSoapObject);
						// =================================================================================
						// DateTimeType
						// =================================================================================
						if (subSoapObject.getName().equalsIgnoreCase("DateTimeType")) {
							mDateTimeType = subSoapObject.getValue().toString();
						}
						// =================================================================================
						// DaylightSavings
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("DaylightSavings")) {
							mDaylightSavings = Boolean.parseBoolean(subSoapObject.getValue().toString());
						}
						// =================================================================================
						// TimeZone
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("TimeZone")) {
							mTZ = ((SoapObject) subSoapObject.getValue()).getPropertyAsString("TZ");
						}
						// =================================================================================
						// UTCDateTime
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("UTCDateTime")) {
							SoapObject UTCDateTime = (SoapObject) subSoapObject.getValue();
							for (int u = 0; u < UTCDateTime.getPropertyCount(); u++) {
								PropertyInfo utcdatetime = new PropertyInfo();
								UTCDateTime.getPropertyInfo(u, utcdatetime);
								// =================================================================================
								// Time
								// =================================================================================
								if (utcdatetime.getName().equalsIgnoreCase("Time")) {
									hour = Integer.parseInt(((SoapObject) utcdatetime.getValue())
											.getPropertyAsString("Hour"));
									minute = Integer.parseInt(((SoapObject) utcdatetime.getValue())
											.getPropertyAsString("Minute"));
									second = Integer.parseInt(((SoapObject) utcdatetime.getValue())
											.getPropertyAsString("Second"));
								}
								// =================================================================================
								// Date
								// =================================================================================
								else if (utcdatetime.getName().equalsIgnoreCase("Date")) {
									year = Integer.parseInt(((SoapObject) utcdatetime.getValue())
											.getPropertyAsString("Year"));
									month = Integer.parseInt(((SoapObject) utcdatetime.getValue())
											.getPropertyAsString("Month"));
									day = Integer.parseInt(((SoapObject) utcdatetime.getValue())
											.getPropertyAsString("Day"));
								}
							}
							mUTCDateTime = new GregorianCalendar(year, month - 1, day, hour, minute, second);
						}
						// =================================================================================
						// LocalDateTime
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("LocalDateTime")) {
							SoapObject LocalDateTime = (SoapObject) subSoapObject.getValue();
							for (int u = 0; u < LocalDateTime.getPropertyCount(); u++) {
								PropertyInfo localdatetime = new PropertyInfo();
								LocalDateTime.getPropertyInfo(u, localdatetime);
								// =================================================================================
								// Time
								// =================================================================================
								if (localdatetime.getName().equalsIgnoreCase("Time")) {
									hour = Integer.parseInt(((SoapObject) localdatetime.getValue())
											.getPropertyAsString("Hour"));
									minute = Integer.parseInt(((SoapObject) localdatetime.getValue())
											.getPropertyAsString("Minute"));
									second = Integer.parseInt(((SoapObject) localdatetime.getValue())
											.getPropertyAsString("Second"));
								}
								// =================================================================================
								// Date
								// =================================================================================
								else if (localdatetime.getName().equalsIgnoreCase("Date")) {
									year = Integer.parseInt(((SoapObject) localdatetime.getValue())
											.getPropertyAsString("Year"));
									month = Integer.parseInt(((SoapObject) localdatetime.getValue())
											.getPropertyAsString("Month"));
									day = Integer.parseInt(((SoapObject) localdatetime.getValue())
											.getPropertyAsString("Day"));
								}
							}
							mLocalDateTime = new GregorianCalendar(year, month - 1, day, hour, minute, second);
						}
						// =================================================================================
						// Extension
						// =================================================================================
						else if (subSoapObject.getName().equalsIgnoreCase("Extension")) {
							mUse24HourFormat = Boolean.parseBoolean(((SoapObject)subSoapObject.getValue()).getPropertyAsString("Use24HourFormat"));
							mDateFormat = ((SoapObject)subSoapObject.getValue()).getPropertyAsString("DateFormat");
						}
					}
				}
			}
		}
	}

	public void setSystemDateAndTime(String DateTimeType, boolean DaylightSavings /* automatic date&time */, String TZ,
									 Calendar uTCDateTime, Calendar localTime, boolean Use24HourFormat, String DateFormat) {
		SoapObject mSoapObject = new SoapObject(DeviceService.NAMESPACE, "SystemDateAndTime");
		try {
			// DateTimeType
			mSoapObject.addProperty(DeviceService.SCHEMA, "DateTimeType", DateTimeType);

			// DaylightSavings
			mSoapObject.addProperty(DeviceService.SCHEMA, "DaylightSavings", DaylightSavings);

			// TimeZone
			SoapObject soapTimeZone = new SoapObject(DeviceService.SCHEMA, "TimeZone");
			soapTimeZone.addProperty(DeviceService.SCHEMA, "TZ", TZ);
			mSoapObject.addSoapObject(soapTimeZone);

			// UTCDateTime
			SoapObject soapUTCDateTime = new SoapObject(DeviceService.SCHEMA, "UTCDateTime");
			// Time
			SoapObject soapUTCTime = new SoapObject(DeviceService.SCHEMA, "Time");
			soapUTCTime.addProperty(DeviceService.SCHEMA, "Hour", uTCDateTime.get(Calendar.HOUR_OF_DAY));
			soapUTCTime.addProperty(DeviceService.SCHEMA, "Minute", uTCDateTime.get(Calendar.MINUTE));
			soapUTCTime.addProperty(DeviceService.SCHEMA, "Second", uTCDateTime.get(Calendar.SECOND));
			// Date
			SoapObject soapUTCDate = new SoapObject(DeviceService.SCHEMA, "Date");
			soapUTCDate.addProperty(DeviceService.SCHEMA, "Year", uTCDateTime.get(Calendar.YEAR));
			soapUTCDate.addProperty(DeviceService.SCHEMA, "Month", uTCDateTime.get(Calendar.MONTH) + 1);
			soapUTCDate.addProperty(DeviceService.SCHEMA, "Day", uTCDateTime.get(Calendar.DAY_OF_MONTH));

			soapUTCDateTime.addSoapObject(soapUTCTime);
			soapUTCDateTime.addSoapObject(soapUTCDate);
			mSoapObject.addSoapObject(soapUTCDateTime);

			// LocalDateTime
			SoapObject soapLocalDateTime = new SoapObject(DeviceService.SCHEMA, "LocalDateTime");
			// Time
			SoapObject soapLocalTime = new SoapObject(DeviceService.SCHEMA, "Time");
			soapLocalTime.addProperty(DeviceService.SCHEMA, "Hour", localTime.get(Calendar.HOUR_OF_DAY));
			soapLocalTime.addProperty(DeviceService.SCHEMA, "Minute", localTime.get(Calendar.MINUTE));
			soapLocalTime.addProperty(DeviceService.SCHEMA, "Second", localTime.get(Calendar.SECOND));
			// Date
			SoapObject soapLocalDate = new SoapObject(DeviceService.SCHEMA, "Date");
			soapLocalDate.addProperty(DeviceService.SCHEMA, "Year", localTime.get(Calendar.YEAR));
			soapLocalDate.addProperty(DeviceService.SCHEMA, "Month", localTime.get(Calendar.MONTH) + 1);
			soapLocalDate.addProperty(DeviceService.SCHEMA, "Day", localTime.get(Calendar.DAY_OF_MONTH));

			soapLocalDateTime.addSoapObject(soapLocalTime);
			soapLocalDateTime.addSoapObject(soapLocalDate);
			mSoapObject.addSoapObject(soapLocalDateTime);

			// Extension
			SoapObject soapExtension = new SoapObject(DeviceService.SCHEMA, "Extension");
			soapExtension.addProperty(DeviceService.SCHEMA, "Use24HourFormat", Use24HourFormat);
			soapExtension.addProperty(DeviceService.SCHEMA, "DateFormat", DateFormat);
			mSoapObject.addSoapObject(soapExtension);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addSoapObject(mSoapObject);
	}
}
