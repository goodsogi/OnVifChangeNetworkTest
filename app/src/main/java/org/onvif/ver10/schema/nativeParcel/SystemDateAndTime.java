package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

public class SystemDateAndTime implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/

	public String mDateTimeType; // 'Manual' , 'NTP'
	public boolean mDaylightSavings = true; // Always true
	public String mTZ; // Returns the ID of this TimeZone, such as America/Los_Angeles, GMT-08:00 or UTC.
	public int mUtcYear;
	public int mUtcMonth;
	public int mUtcDay;
	public int mUtcHour;
	public int mUtcMinute;
	public int mUtcSecond;
	public int mLocalYear;
	public int mLocalMonth;
	public int mLocalDay;
	public int mLocalHour;
	public int mLocalMinute;
	public int mLocalSecond;
	public boolean mExtenstion;
	public boolean mUse24HourFormat; // custom values
	public String mDateFormat; // custom values

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public SystemDateAndTime() {
	}

	public SystemDateAndTime(Parcel reply) {

		mDateTimeType = utils.readString(reply);
		mDaylightSavings = utils.readBoolean(reply);
		mTZ = utils.readString(reply);

		mUtcYear = utils.readInt(reply);
		mUtcMonth = utils.readInt(reply);
		mUtcDay = utils.readInt(reply);
		mUtcHour = utils.readInt(reply);
		mUtcMinute = utils.readInt(reply);
		mUtcSecond = utils.readInt(reply);
		mLocalYear = utils.readInt(reply);
		mLocalMonth = utils.readInt(reply);
		mLocalDay = utils.readInt(reply);
		mLocalHour = utils.readInt(reply);
		mLocalMinute = utils.readInt(reply);
		mLocalSecond = utils.readInt(reply);

		mExtenstion = utils.readBoolean(reply);
		if (mExtenstion) {
			mUse24HourFormat = utils.readBoolean(reply);
			mDateFormat = utils.readString(reply);
		}
	}

	/*****************************************************************************************
	 * @Override describeContents
	 *****************************************************************************************/
	@Override
	public int describeContents() {
		return 0;
	}

	/*****************************************************************************************
	 * @Override writeToParcel
	 *****************************************************************************************/
	@Override
	public void writeToParcel(Parcel data, int flag) {
		utils.writeString(data, mDateTimeType);
		utils.writeBoolean(data, mDaylightSavings);
		utils.writeString(data, mTZ);

		utils.writeInt(data, mUtcYear);
		utils.writeInt(data, mUtcMonth);
		utils.writeInt(data, mUtcDay);
		utils.writeInt(data, mUtcHour);
		utils.writeInt(data, mUtcMinute);
		utils.writeInt(data, mUtcSecond);

		utils.writeInt(data, mLocalYear);
		utils.writeInt(data, mLocalMonth);
		utils.writeInt(data, mLocalDay);
		utils.writeInt(data, mLocalHour);
		utils.writeInt(data, mLocalMinute);
		utils.writeInt(data, mLocalSecond);

		utils.writeBoolean(data, mExtenstion);
		if (mExtenstion) {
			utils.writeBoolean(data, mUse24HourFormat);
			utils.writeString(data, mDateFormat);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<SystemDateAndTime> CREATOR = new Creator<SystemDateAndTime>() {
		public SystemDateAndTime createFromParcel(Parcel in) {
			return new SystemDateAndTime(in);
		}

		public SystemDateAndTime[] newArray(int size) {
			return new SystemDateAndTime[size];
		}
	};
}