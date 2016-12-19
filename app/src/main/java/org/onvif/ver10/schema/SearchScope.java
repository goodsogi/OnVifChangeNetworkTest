package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class SearchScope implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ArrayList<IncludedSources> mIncludedSources;
	public ArrayList<String> mIncludedRecordings;
	public String mRecordingInformationFilter;
	public SearchScopeExtension mSearchScopeExtension;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public SearchScope() {
		mIncludedSources = new ArrayList<IncludedSources>();
		mIncludedRecordings = new ArrayList<String>();
	}

	public SearchScope(Parcel src) {

		mIncludedSources = new ArrayList<IncludedSources>();
		int size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			mIncludedSources.add(new IncludedSources(src));
		}

		mIncludedRecordings = new ArrayList<String>();
		size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			mIncludedRecordings.add(utils.readString(src));
		}

		if (utils.readInt(src) != 0) {
			mSearchScopeExtension = new SearchScopeExtension(src);
		}
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
	public void writeToParcel(Parcel dst, int flag) {

		if (mIncludedSources != null) {
			int size = mIncludedSources.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				mIncludedSources.get(i).writeToParcel(dst, flag);
			}
		} else {
			utils.writeInt(dst, 0);
		}
		if (mIncludedRecordings != null) {
			int size = mIncludedRecordings.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				utils.writeString(dst, mIncludedRecordings.get(i));
			}
		} else {
			utils.writeInt(dst, 0);
		}

		utils.writeString(dst, mRecordingInformationFilter);

		if (mSearchScopeExtension != null) {
			utils.writeInt(dst, 1);
			mSearchScopeExtension.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<SearchScope> CREATOR = new Creator<SearchScope>() {
		public SearchScope createFromParcel(Parcel in) {
			return new SearchScope(in);
		}

		public SearchScope[] newArray(int size) {
			return new SearchScope[size];
		}
	};
}