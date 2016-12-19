package org.onvif.ver10.search.wsdl.get;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.RecordingInformation;

import java.util.ArrayList;

public class GetRecordingSearchResultsResponse implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mSearchState;
	public ArrayList<RecordingInformation> mRecordingInformation;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public GetRecordingSearchResultsResponse() {
		mRecordingInformation = new ArrayList<RecordingInformation>();
	}

	public GetRecordingSearchResultsResponse(Parcel reply) {
		if (reply != null) {
			mSearchState = reply.readInt();

			mRecordingInformation = new ArrayList<RecordingInformation>();
			int infoSize = reply.readInt();
			for (int i = 0; i < infoSize; i++) {
				mRecordingInformation.add(new RecordingInformation(reply));
			}
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
		if (data != null) {
			data.writeInt(mSearchState);

			if (mRecordingInformation != null) {
				int infoSize = mRecordingInformation.size();
				data.writeInt(infoSize);
				for (int i = 0; i < infoSize; i++) {
					mRecordingInformation.get(i).writeToParcel(data, flag);
				}
			} else {
				data.writeInt(0);
			}
		}
	}
}