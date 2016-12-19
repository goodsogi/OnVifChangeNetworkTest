package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class Mpeg4Options implements Parcelable {
	private final static String TAG = Mpeg4Options.class.getSimpleName();
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
	public ArrayList<String> mMpeg4ProfilesSupported;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Mpeg4Options() {

	}

	public Mpeg4Options(Parcel src) {
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

		this.mMpeg4ProfilesSupported = new ArrayList<String>();
		size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			this.mMpeg4ProfilesSupported.add(utils.readString(src));
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

		if (mMpeg4ProfilesSupported != null) {
			int size = mMpeg4ProfilesSupported.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				utils.writeString(dst, mMpeg4ProfilesSupported.get(i));
			}
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Mpeg4Options> CREATOR = new Creator<Mpeg4Options>() {
		public Mpeg4Options createFromParcel(Parcel in) {
			return new Mpeg4Options(in);
		}

		public Mpeg4Options[] newArray(int size) {
			return new Mpeg4Options[size];
		}
	};

}
