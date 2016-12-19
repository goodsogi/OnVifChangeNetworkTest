package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

public class ElementItem implements Parcelable {
	private final static String TAG = ElementItem.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public String mXMLTree; // 해당 내용 분석 못함으로 인해 String 값으로 일단 저장 함.
	public String mName;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public ElementItem() {

	}

	public ElementItem(Parcel src) {
		this.mXMLTree = utils.readString(src);
		this.mName = utils.readString(src);
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
		utils.writeString(dst, mXMLTree);
		utils.writeString(dst, mName);
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<ElementItem> CREATOR = new Creator<ElementItem>() {
		public ElementItem createFromParcel(Parcel in) {
			return new ElementItem(in);
		}

		public ElementItem[] newArray(int size) {
			return new ElementItem[size];
		}
	};

}
