package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class RuleEngineConfiguration implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ArrayList<ItemList> mRule;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public RuleEngineConfiguration() {
	}

	public RuleEngineConfiguration(Parcel src) {
		this.mRule = new ArrayList<ItemList>();
		int RuleSize = utils.readInt(src);
		for (int i = 0; i < RuleSize; i++) {
			ItemList rule = new ItemList(src);
			mRule.add(rule);
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
		if (mRule != null) {
			int RuleSize = mRule.size();
			utils.writeInt(dst, RuleSize);
			for (int i = 0; i < RuleSize; i++) {
				mRule.get(i).writeToParcel(dst, flags);
			}
		} else {
			utils.writeInt(dst, 0);
		}

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<RuleEngineConfiguration> CREATOR = new Creator<RuleEngineConfiguration>() {
		public RuleEngineConfiguration createFromParcel(Parcel in) {
			return new RuleEngineConfiguration(in);
		}

		public RuleEngineConfiguration[] newArray(int size) {
			return new RuleEngineConfiguration[size];
		}
	};

}
