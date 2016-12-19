package com.commax.onvif_device_manager.fragment;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.nativeParcel.MediaUri;
import org.onvif.ver10.schema.nativeParcel.Profile;
import org.onvif.ver10.schema.nativeParcel.utils;

import java.util.ArrayList;

/**
 * @brief Device를 등록 하기 위해 사용되는 클래스
 * @details Onvif통신을 통해 Profiles 정보와 MediaUris 정보를 저장 한다.
 */
public class Camera implements Parcelable {

	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	/** @brief SiteDevice의 Onvif Profile 정보 */
	public ArrayList<Profile> mProfiles;
	/** @brief SiteDevice의 Onvif Stream MediaUris 정보 */
	public ArrayList<MediaUri> mMediaUris;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public Camera() {
	}

	public Camera(Parcel src) {
		this.mProfiles = new ArrayList<Profile>();
		int profileSize = utils.readInt(src);
		for (int i = 0; i < profileSize; i++) {
			mProfiles.add(new Profile(src));
		}

		this.mMediaUris = new ArrayList<MediaUri>();
		int mediaUriSize = utils.readInt(src);
		for (int i = 0; i < mediaUriSize; i++) {
			mMediaUris.add(new MediaUri(src));
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
		if (mProfiles != null) {
			int size = mProfiles.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				mProfiles.get(i).writeToParcel(dst, flags);
			}
		} else {
			utils.writeInt(dst, 0);
		}

		if (mMediaUris != null) {
			int size = mMediaUris.size();
			utils.writeInt(dst, size);
			for (int i = 0; i < size; i++) {
				mMediaUris.get(i).writeToParcel(dst, flags);
			}
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<Camera> CREATOR = new Creator<Camera>() {
		public Camera createFromParcel(Parcel in) {
			return new Camera(in);
		}

		public Camera[] newArray(int size) {
			return new Camera[size];
		}
	};
}
