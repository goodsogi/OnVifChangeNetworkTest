package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class WhiteBalanceOptions20 implements Parcelable {
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ArrayList<String> mMode;
	public FloatRange mYrGain;
	public FloatRange mYbGain;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public WhiteBalanceOptions20() {

	}

	public WhiteBalanceOptions20(Parcel src) {
		this.mMode = new ArrayList<String>();
		int size = utils.readInt(src);
		for (int i = 0; i < size; i++) {
			this.mMode.add(utils.readString(src));
		}

		if (utils.readInt(src) != 0) {
			this.mYrGain = new FloatRange(src);
		}
		if (utils.readInt(src) != 0) {
			this.mYbGain = new FloatRange(src);
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
		if (mMode != null) {
			int size = mMode.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				utils.writeString(dst, mMode.get(i));
			}
		} else {
			utils.writeInt(dst, 0);
		}

		if (mYrGain != null) {
			utils.writeInt(dst, 1);
			mYrGain.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mYbGain != null) {
			utils.writeInt(dst, 1);
			mYbGain.writeToParcel(dst, flag);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<WhiteBalanceOptions20> CREATOR = new Creator<WhiteBalanceOptions20>() {
		public WhiteBalanceOptions20 createFromParcel(Parcel in) {
			return new WhiteBalanceOptions20(in);
		}

		public WhiteBalanceOptions20[] newArray(int size) {
			return new WhiteBalanceOptions20[size];
		}
	};

}
