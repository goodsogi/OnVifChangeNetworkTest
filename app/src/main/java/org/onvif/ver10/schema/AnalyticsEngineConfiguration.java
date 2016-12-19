package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class AnalyticsEngineConfiguration implements Parcelable {
	private final static String TAG = AnalyticsEngineConfiguration.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ArrayList<ItemList> mAnalyticsModule;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public AnalyticsEngineConfiguration() {
	}

	public AnalyticsEngineConfiguration(Parcel src) {
		this.mAnalyticsModule = new ArrayList<ItemList>();
		int AnalyticsModuleSize = utils.readInt(src);
		for (int i = 0; i < AnalyticsModuleSize; i++) {
			ItemList analyticsModule = new ItemList(src);
			mAnalyticsModule.add(analyticsModule);
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
		int AnalyticsModuleSize = mAnalyticsModule.size();
		utils.writeInt(dst, AnalyticsModuleSize);
		for (int i = 0; i < AnalyticsModuleSize; i++) {
			mAnalyticsModule.get(i).writeToParcel(dst, flags);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<AnalyticsEngineConfiguration> CREATOR = new Creator<AnalyticsEngineConfiguration>() {
		public AnalyticsEngineConfiguration createFromParcel(Parcel in) {
			return new AnalyticsEngineConfiguration(in);
		}

		public AnalyticsEngineConfiguration[] newArray(int size) {
			return new AnalyticsEngineConfiguration[size];
		}
	};

}
