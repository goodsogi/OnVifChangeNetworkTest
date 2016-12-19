package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class JpegOptions implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ArrayList<VideoResolution> mResolutionsAvailable;
	public IntRange mFrameRateRange;
	public IntRange mEncodingIntervalRange;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public JpegOptions() {

	}

	public JpegOptions(Parcel src) {
		this.mResolutionsAvailable = new ArrayList<VideoResolution>();

		int size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			this.mResolutionsAvailable.add(new VideoResolution(src));
		}

		if (utils.readInt(src) == 1) {
			this.mFrameRateRange = new IntRange(src);
		}

		if (utils.readInt(src) == 1) {
			this.mEncodingIntervalRange = new IntRange(src);
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
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<JpegOptions> CREATOR = new Creator<JpegOptions>() {
		public JpegOptions createFromParcel(Parcel in) {
			return new JpegOptions(in);
		}

		public JpegOptions[] newArray(int size) {
			return new JpegOptions[size];
		}
	};
}
