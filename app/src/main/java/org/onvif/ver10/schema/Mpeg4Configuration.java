package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class Mpeg4Configuration implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mGovLength;
	public String mMpeg4Profile; // enum { 'SP', 'ASP' }

	public Mpeg4Configuration() {

	}

	public Mpeg4Configuration(Parcel src) {
		this.mGovLength = utils.readInt(src);
		this.mMpeg4Profile = utils.readString(src);
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
		utils.writeInt(dst, mGovLength);
		utils.writeString(dst, mMpeg4Profile);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Mpeg4Configuration> CREATOR = new Creator<Mpeg4Configuration>() {
		public Mpeg4Configuration createFromParcel(Parcel in) {
			return new Mpeg4Configuration(in);
		}

		public Mpeg4Configuration[] newArray(int size) {
			return new Mpeg4Configuration[size];
		}
	};
}
