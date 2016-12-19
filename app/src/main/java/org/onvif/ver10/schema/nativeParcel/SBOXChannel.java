package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class SBOXChannel implements Parcelable {
	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public int mChannelIndex;
	public int mChannelNumber;
	public String mChannelIP;
	public int mChannelPort;
	public String mChannelXAddr;
	public String mChannelID;
	public String mChannelPassword;
	public String mChannelAlias;
	public int mChannelEnableLive;

	// ONVIF
	public Capabilities mCapabilities;
	public DeviceInformation mDeviceInformation;
	public ArrayList<Profile> mProfiles;
	public ArrayList<MediaUri> mStreamUris;
	public MediaUri mSnapshotUri;
	public VideoEncoderConfigurationOptions mVideoEncoderConfigurationOptions;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public SBOXChannel(int channelIndex, int channelNumber, String channelIP, int channelPort, String channelXAddr, String channelID,
					   String channelPassword, String channelAlias, int channelEnableLive) {
		this.mChannelIndex = channelIndex;
		this.mChannelNumber = channelNumber;
		this.mChannelIP = channelIP;
		this.mChannelPort = channelPort;
		this.mChannelXAddr = channelXAddr;
		this.mChannelID = channelID;
		this.mChannelPassword = channelPassword;
		this.mChannelAlias = channelAlias;
		this.mChannelEnableLive = channelEnableLive;
	}

	public SBOXChannel(Parcel src) {
		try {
			this.mChannelIndex = utils.readInt(src);
			this.mChannelNumber = utils.readInt(src);
			this.mChannelIP = utils.readString(src);
			this.mChannelPort = utils.readInt(src);
			this.mChannelXAddr = utils.readString(src);
			this.mChannelID = utils.readString(src);
			this.mChannelPassword = utils.readString(src);
			this.mChannelAlias = utils.readString(src);
			this.mChannelEnableLive = utils.readInt(src);

			if (utils.readInt(src) != 0) {
				this.mCapabilities = new Capabilities(src);
			}
			if (utils.readInt(src) != 0) {
				this.mDeviceInformation = new DeviceInformation(src);
			}

			this.mProfiles = new ArrayList<Profile>();
			int profileSize = utils.readInt(src);
			for (int i = 0; i < profileSize; i++) {
				this.mProfiles.add(new Profile(src));
			}

			this.mStreamUris = new ArrayList<MediaUri>();
			int streamSize = utils.readInt(src);
			for (int i = 0; i < streamSize; i++) {
				this.mStreamUris.add(new MediaUri(src));
			}

			if (utils.readInt(src) != 0) {
				this.mSnapshotUri = new MediaUri(src);
			}

			if (utils.readInt(src) != 0) {
				this.mVideoEncoderConfigurationOptions = new VideoEncoderConfigurationOptions(src);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public SBOXChannel(int channel_number) {
		this.mChannelNumber = channel_number;
	}

	/*****************************************************************************************
	 * clear
	 *****************************************************************************************/
	public void clear() {
		this.mChannelIndex = 0;
		this.mChannelNumber = 0;
		this.mChannelIP = null;
		this.mChannelPort = 0;
		this.mChannelXAddr = null;
		this.mChannelID = null;
		this.mChannelPassword = null;
		this.mChannelAlias = null;
		this.mChannelEnableLive = 0;

		this.mCapabilities = null;
		this.mDeviceInformation = null;
		this.mProfiles = null;
		this.mStreamUris = null;
		this.mSnapshotUri = null;

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
		try {
			utils.writeInt(dst, mChannelIndex);
			utils.writeInt(dst, mChannelNumber);
			utils.writeString(dst, mChannelIP);
			utils.writeInt(dst, mChannelPort);
			utils.writeString(dst, mChannelXAddr);
			utils.writeString(dst, mChannelID);
			utils.writeString(dst, mChannelPassword);
			utils.writeString(dst, mChannelAlias);
			utils.writeInt(dst, mChannelEnableLive);

			if (mCapabilities != null) {
				utils.writeInt(dst, 1);
				mCapabilities.writeToParcel(dst, flags);
			} else {
				utils.writeInt(dst, 0);
			}

			if (mDeviceInformation != null) {
				utils.writeInt(dst, 1);
				mDeviceInformation.writeToParcel(dst, flags);
			} else {
				utils.writeInt(dst, 0);
			}

			if (mProfiles != null) {
				int profileSize = mProfiles.size();
				utils.writeInt(dst, profileSize);
				for (int i = 0; i < profileSize; i++) {
					mProfiles.get(i).writeToParcel(dst, flags);
				}
			} else {
				utils.writeInt(dst, 0);
			}

			if (mStreamUris != null) {
				int streamSize = mStreamUris.size();
				utils.writeInt(dst, streamSize);
				for (int i = 0; i < streamSize; i++) {
					mStreamUris.get(i).writeToParcel(dst, flags);
				}
			} else {
				utils.writeInt(dst, 0);
			}

			if (mSnapshotUri != null) {
				utils.writeInt(dst, 1);
				mSnapshotUri.writeToParcel(dst, flags);
			} else {
				utils.writeInt(dst, 0);
			}

			if (mVideoEncoderConfigurationOptions != null) {
				utils.writeInt(dst, 1);
				mVideoEncoderConfigurationOptions.writeToParcel(dst, flags);
			} else {
				utils.writeInt(dst, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<SBOXChannel> CREATOR = new Creator<SBOXChannel>() {

		public SBOXChannel createFromParcel(Parcel in) {
			return new SBOXChannel(in);
		}

		public SBOXChannel[] newArray(int size) {
			return new SBOXChannel[size];
		}
	};

}
