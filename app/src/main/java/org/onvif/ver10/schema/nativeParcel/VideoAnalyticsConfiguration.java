package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.AnalyticsEngineConfiguration;
import org.onvif.ver10.schema.RuleEngineConfiguration;

public class VideoAnalyticsConfiguration implements Parcelable {
	private final static String TAG = VideoAnalyticsConfiguration.class.getSimpleName();

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mName;
	public int mUseCount;
	public String mToken;
	public AnalyticsEngineConfiguration mAnalyticsEngineConfiguration;
	public RuleEngineConfiguration mRuleEngineConfiguration;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public VideoAnalyticsConfiguration() {
		this.mAnalyticsEngineConfiguration = new AnalyticsEngineConfiguration();
		this.mRuleEngineConfiguration = new RuleEngineConfiguration();
	}

	public VideoAnalyticsConfiguration(Parcel src) {
		this.mName = utils.readString(src);
		this.mUseCount = utils.readInt(src);
		this.mToken = utils.readString(src);

		if (utils.readInt(src) != 0) {
			this.mAnalyticsEngineConfiguration = new AnalyticsEngineConfiguration(src);
		}

		if (utils.readInt(src) != 0) {
			this.mRuleEngineConfiguration = new RuleEngineConfiguration(src);
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
	public void writeToParcel(Parcel dst, int flags) {
		utils.writeString(dst, mName);
		utils.writeInt(dst, mUseCount);
		utils.writeString(dst, mToken);

		if (mAnalyticsEngineConfiguration != null) {
			utils.writeInt(dst, 1);
			mAnalyticsEngineConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mRuleEngineConfiguration != null) {
			utils.writeInt(dst, 1);
			mRuleEngineConfiguration.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<VideoAnalyticsConfiguration> CREATOR = new Creator<VideoAnalyticsConfiguration>() {
		public VideoAnalyticsConfiguration createFromParcel(Parcel in) {
			return new VideoAnalyticsConfiguration(in);
		}

		public VideoAnalyticsConfiguration[] newArray(int size) {
			return new VideoAnalyticsConfiguration[size];
		}
	};

}