package org.onvif.ver10.search.wsdl.get;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.SearchScope;
import org.onvif.ver10.schema.nativeParcel.utils;

public class FindEvents implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mStartPoint;
	public String mEndPoint;
	public SearchScope mScope;
	public String mSearchFilter; // RecordingJob - jobConfigurationExtension - Description (Normal, Event_DI1 ..)
	public int mIncludeStartState;
	public int mMaxMatches; // optional
	public int mKeepAliveTime; // second

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public FindEvents() {
	}

	public FindEvents(Parcel src) {
		this.mStartPoint = utils.readString(src);
		this.mEndPoint = utils.readString(src);

		if (utils.readInt(src) != 0) {
			this.mScope = new SearchScope(src);
		}

		this.mSearchFilter = utils.readString(src);

		this.mIncludeStartState = utils.readInt(src);
		this.mMaxMatches = utils.readInt(src);
		this.mKeepAliveTime = utils.readInt(src);

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
	public void writeToParcel(Parcel dst, int flag) {
		utils.writeString(dst, mStartPoint);
		utils.writeString(dst, mEndPoint);

		if (mScope != null) {
			utils.writeInt(dst, 1);
			mScope.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		utils.writeString(dst, mSearchFilter);
		utils.writeInt(dst, mIncludeStartState);
		utils.writeInt(dst, mMaxMatches);
		utils.writeInt(dst, mKeepAliveTime);
	}
}