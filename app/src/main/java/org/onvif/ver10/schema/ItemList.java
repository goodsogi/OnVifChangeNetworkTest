package org.onvif.ver10.schema;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

public class ItemList implements Parcelable {
	private final static String TAG = ItemList.class.getSimpleName();
	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public ArrayList<SimpleItem> mSimpleItem;
	public ArrayList<ElementItem> mElementItem;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public ItemList() {
		
	}

	public ItemList(Parcel src) {
		this.mSimpleItem = new ArrayList<SimpleItem>();
		int SimpleItemSize = utils.readInt(src);
		for (int i = 0; i < SimpleItemSize; i++) {
			SimpleItem simpleItem = new SimpleItem(src);
			this.mSimpleItem.add(simpleItem);
		}

		this.mElementItem = new ArrayList<ElementItem>();
		int ElementItemSize = utils.readInt(src);
		for (int i = 0; i < ElementItemSize; i++) {
			ElementItem elementItem = new ElementItem(src);
			this.mElementItem.add(elementItem);
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
		int SimpleItemSize = mSimpleItem.size();
		utils.writeInt(dst, SimpleItemSize);
		for (int i = 0; i < SimpleItemSize; i++) {
			mSimpleItem.get(i).writeToParcel(dst, flags);
		}

		int ElementItemSize = mElementItem.size();
		utils.writeInt(dst, ElementItemSize);
		for (int i = 0; i < ElementItemSize; i++) {
			mElementItem.get(i).writeToParcel(dst, flags);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<ItemList> CREATOR = new Creator<ItemList>() {
		public ItemList createFromParcel(Parcel in) {
			return new ItemList(in);
		}

		public ItemList[] newArray(int size) {
			return new ItemList[size];
		}
	};

}
