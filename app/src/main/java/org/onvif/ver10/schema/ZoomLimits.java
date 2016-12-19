package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

public class ZoomLimits implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public Space1DDescription mRange;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public ZoomLimits() {

	}

	public ZoomLimits(Parcel src) {
		if (src.readInt() != 0) {
			this.mRange = new Space1DDescription(src);
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
		if (mRange != null) {
			dst.writeInt(1);
			mRange.writeToParcel(dst, flag);
		} else {
			dst.writeInt(0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<ZoomLimits> CREATOR = new Creator<ZoomLimits>() {
		public ZoomLimits createFromParcel(Parcel in) {
			return new ZoomLimits(in);
		}

		public ZoomLimits[] newArray(int size) {
			return new ZoomLimits[size];
		}
	};

}
