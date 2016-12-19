package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class Space2DDescription implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mUri;
	public FloatRange mXRange;
	public FloatRange mYRange;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Space2DDescription() {

	}

	public Space2DDescription(Parcel src) {
		this.mUri = utils.readString(src);
		if (src.readInt() != 0) {
			this.mXRange = new FloatRange(src);
		}
		if (src.readInt() != 0) {
			this.mYRange = new FloatRange(src);
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
		if (mYRange != null) {
			dst.writeInt(1);
			mYRange.writeToParcel(dst, flag);
		} else {
			dst.writeInt(0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Space2DDescription> CREATOR = new Creator<Space2DDescription>() {
		public Space2DDescription createFromParcel(Parcel in) {
			return new Space2DDescription(in);
		}

		public Space2DDescription[] newArray(int size) {
			return new Space2DDescription[size];
		}
	};

}
