package org.onvif.ver10.search.wsdl.get;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.Source;
import org.onvif.ver10.schema.Track;
import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class GetTrackInformationResponse implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mRecordingToken;
	public Source mSource;
	public String mEarliestRecording; // optional
	public String mLatestRecording; // optional
	public String mContent;
	public ArrayList<Track> mTrack; // optional
	public int mRecordingStatus; // enum { 'Initiated', 'Recording', 'Stopped', 'Removing', 'Removed', 'Unknown' }
	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public GetTrackInformationResponse() {
	}

	public GetTrackInformationResponse(Parcel src) {
		mRecordingToken = utils.readString(src);
		if (utils.readInt(src) != 0) {
			mSource = new Source(src);
		}
		mEarliestRecording = utils.readString(src);
		mLatestRecording = utils.readString(src);
		mContent = utils.readString(src);

		mTrack = new ArrayList<Track>();
		int trackSize = utils.readInt(src);
		for (int i = 0; i < trackSize; i++) {
			mTrack.add(new Track(src));
		}

		mRecordingStatus = utils.readInt(src);
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
		if (mSource != null) {
			utils.writeInt(dst, 1);
			mSource.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
		utils.writeString(dst, mEarliestRecording);
		utils.writeString(dst, mLatestRecording);
		utils.writeString(dst, mContent);

		if (mTrack != null) {
			int trackSize = mTrack.size();
			utils.writeInt(dst, trackSize);
			for (int i = 0; i < trackSize; i++) {
				mTrack.get(i).writeToParcel(dst, flag);
			}
		} else {
			utils.writeInt(dst, 0);
		}
		
		utils.writeInt(dst, mRecordingStatus);
	}
	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<GetTrackInformationResponse> CREATOR = new Creator<GetTrackInformationResponse>() {
		public GetTrackInformationResponse createFromParcel(Parcel in) {
			return new GetTrackInformationResponse(in);
		}

		public GetTrackInformationResponse[] newArray(int size) {
			return new GetTrackInformationResponse[size];
		}
	};
}