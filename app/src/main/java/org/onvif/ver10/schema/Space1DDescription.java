package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class Space1DDescription implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mUri;
	public FloatRange mXRange;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Space1DDescription() {

	}

	public Space1DDescription(Parcel src) {
		this.mUri = utils.readString(src);
		if (src.readInt() != 0) {
			this.mXRange = new FloatRange(src);
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
		utils.writeString(dst, mUri);
		if (mXRange != null) {
			dst.writeInt(1);
			mXRange.writeToParcel(dst, flag);
		} else {
			dst.writeInt(0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Space1DDescription> CREATOR = new Creator<Space1DDescription>() {
		public Space1DDescription createFromParcel(Parcel in) {
			return new Space1DDescription(in);
		}

		public Space1DDescription[] newArray(int size) {
			return new Space1DDescription[size];
		}
	};

}
