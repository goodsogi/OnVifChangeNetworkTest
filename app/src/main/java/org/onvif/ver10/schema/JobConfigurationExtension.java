package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class JobConfigurationExtension implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/
	// Type
	public final static int TYPE_NORMAL = 0;
	public final static int TYPE_EVENT = 1;
	// Description
	public final static String DESC_NORMAL = "Normal";
	public final static String DESC_EVENT_DIGITAL_INPUT_1 = "Event_DI1";
	public final static String DESC_EVENT_DIGITAL_INPUT_2 = "Event_DI2";
	public final static String DESC_EVENT_DIGITAL_INPUT_3 = "Event_DI3";
	public final static String DESC_EVENT_DIGITAL_INPUT_4 = "Event_DI4";
	public final static String DESC_EVENT_MOTION_DETECTION = "Event_MD";
	public final static String DESC_EVENT_AUDIO_DETECTION = "Event_AD";
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mType;
	public String mDescription;
	public int mPreRecordingTime;
	public int mPostRecordingTime;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public JobConfigurationExtension() {
	}

	public JobConfigurationExtension(Parcel src) {
		this.mType = utils.readInt(src);
		this.mDescription = utils.readString(src);
		this.mPreRecordingTime = utils.readInt(src);
		this.mPostRecordingTime = utils.readInt(src);
	}

	/*****************************************************************************************
	 * @Override describeContents
	 *****************************************************************************************/
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*****************************************************************************************
	 * @Override writeToParcel
	 *****************************************************************************************/
	@Override
	public void writeToParcel(Parcel dst, int flags) {
		utils.writeInt(dst, mType);
		utils.writeString(dst, mDescription);
		utils.writeInt(dst, mPreRecordingTime);
		utils.writeInt(dst, mPostRecordingTime);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<JobConfigurationExtension> CREATOR = new Creator<JobConfigurationExtension>() {
		public JobConfigurationExtension createFromParcel(Parcel in) {
			return new JobConfigurationExtension(in);
		}

		public JobConfigurationExtension[] newArray(int size) {
			return new JobConfigurationExtension[size];
		}
	};

}