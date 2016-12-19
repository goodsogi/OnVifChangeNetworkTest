package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class IntRange implements Parcelable {
	private final static String TAG = IntRange.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mMin;
	public int mMax;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public IntRange() {
	}

	public IntRange(Parcel src) {
		this.mMin = utils.readInt(src);
		this.mMax = utils.readInt(src);
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
		utils.writeInt(dst, mMin);
		utils.writeInt(dst, mMax);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<IntRange> CREATOR = new Creator<IntRange>() {
		public IntRange createFromParcel(Parcel in) {
			return new IntRange(in);
		}

		public IntRange[] newArray(int size) {
			return new IntRange[size];
		}
	};

}
