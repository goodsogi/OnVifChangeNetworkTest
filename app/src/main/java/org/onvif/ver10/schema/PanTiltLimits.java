package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

public class PanTiltLimits implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public Space2DDescription mRange;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public PanTiltLimits() {

	}

	public PanTiltLimits(Parcel src) {
		if (src.readInt() != 0) {
			this.mRange = new Space2DDescription(src);
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
	public static final Creator<PanTiltLimits> CREATOR = new Creator<PanTiltLimits>() {
		public PanTiltLimits createFromParcel(Parcel in) {
			return new PanTiltLimits(in);
		}

		public PanTiltLimits[] newArray(int size) {
			return new PanTiltLimits[size];
		}
	};

}
