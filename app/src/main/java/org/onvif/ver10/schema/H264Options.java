package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class H264Options implements Parcelable {
	private final static String TAG = H264Options.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ArrayList<VideoResolution> mResolutionsAvailable;
	public IntRange mGovLengthRange;
	public IntRange mFrameRateRange;
	public IntRange mEncodingIntervalRange;
	public ArrayList<String> mH264ProfilesSupported;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public H264Options() {

	}

	public H264Options(Parcel src) {
		this.mResolutionsAvailable = new ArrayList<VideoResolution>();

		int size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			this.mResolutionsAvailable.add(new VideoResolution(src));
		}

		if (utils.readInt(src) == 1) {
			this.mGovLengthRange = new IntRange(src);
		}

		if (utils.readInt(src) == 1) {
			this.mFrameRateRange = new IntRange(src);
		}

		if (utils.readInt(src) == 1) {
			this.mEncodingIntervalRange = new IntRange(src);
		}

		this.mH264ProfilesSupported = new ArrayList<String>();
		size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			this.mH264ProfilesSupported.add(utils.readString(src));
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
		if (mResolutionsAvailable != null) {
			int size = mResolutionsAvailable.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				mResolutionsAvailable.get(i).writeToParcel(dst, flags);
			}
		} else {
			utils.writeInt(dst, 0);
		}

		if (mGovLengthRange != null) {
			utils.writeInt(dst, 1);
			mGovLengthRange.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mFrameRateRange != null) {
			utils.writeInt(dst, 1);
			mFrameRateRange.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mEncodingIntervalRange != null) {
			utils.writeInt(dst, 1);
			mEncodingIntervalRange.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mH264ProfilesSupported != null) {
			int size = mH264ProfilesSupported.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				utils.writeString(dst, mH264ProfilesSupported.get(i));
			}
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<H264Options> CREATOR = new Creator<H264Options>() {
		public H264Options createFromParcel(Parcel in) {
			return new H264Options(in);
		}

		public H264Options[] newArray(int size) {
			return new H264Options[size];
		}
	};

}
