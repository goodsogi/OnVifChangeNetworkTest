package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class RecordingItem implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mRecordingToken;
	public RecordingConfiguration mRecordingConfiguration;
	public ArrayList<Track> mTrack;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public RecordingItem() {

	}

	public RecordingItem(Parcel src) {
		mRecordingToken = utils.readString(src);
		if (utils.readInt(src) != 0) {
			mRecordingConfiguration = new RecordingConfiguration(src);
		}

		mTrack = new ArrayList<Track>();
		int trackSize = utils.readInt(src);
		for (int i = 0; i < trackSize; i++) {
			mTrack.add(new Track(src));
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
	public void writeToParcel(Parcel dst, int flag) {
		utils.writeString(dst, mRecordingToken);

		if (mRecordingConfiguration != null) {
			utils.writeInt(dst, 1);
			mRecordingConfiguration.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mTrack != null) {
			int trackSize = mTrack.size();
			utils.writeInt(dst, trackSize);
			for (int i = 0; i < trackSize; i++) {
				mTrack.get(i).writeToParcel(dst, flag);
			}
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<RecordingItem> CREATOR = new Creator<RecordingItem>() {
		public RecordingItem createFromParcel(Parcel in) {
			return new RecordingItem(in);
		}

		public RecordingItem[] newArray(int size) {
			return new RecordingItem[size];
		}
	};
}