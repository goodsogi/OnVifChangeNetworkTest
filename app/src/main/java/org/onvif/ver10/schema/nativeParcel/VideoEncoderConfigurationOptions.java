package org.onvif.ver10.schema.nativeParcel;

import android.os.Parcel;
import android.os.Parcelable;

import org.onvif.ver10.schema.H264Options;
import org.onvif.ver10.schema.IntRange;
import org.onvif.ver10.schema.JpegOptions;
import org.onvif.ver10.schema.Mpeg4Options;

public class VideoEncoderConfigurationOptions implements Parcelable {
	private final static String TAG = VideoEncoderConfigurationOptions.class.getSimpleName();

	/*****************************************************************************************
	 * public defines
	 *****************************************************************************************/

	/*****************************************************************************************
	 * public values
	 *****************************************************************************************/
	public IntRange mQualityRange;
	public JpegOptions mJPEG;
	public Mpeg4Options mMPEG4;
	public H264Options mH264;

	/*****************************************************************************************
	 * Constructors
	 *****************************************************************************************/
	public VideoEncoderConfigurationOptions() {
	}

	public VideoEncoderConfigurationOptions(Parcel src) {
		if (utils.readInt(src) == 1) {
			this.mQualityRange = new IntRange(src);
		}

		if (utils.readInt(src) == 1) {
			this.mJPEG = new JpegOptions(src);
		}

		if (utils.readInt(src) == 1) {
			this.mMPEG4 = new Mpeg4Options(src);
		}

		if (utils.readInt(src) == 1) {
			this.mH264 = new H264Options(src);
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
		if (mQualityRange != null) {
			utils.writeInt(dst, 1);
			mQualityRange.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mJPEG != null) {
			utils.writeInt(dst, 1);
			mJPEG.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mMPEG4 != null) {
			utils.writeInt(dst, 1);
			mMPEG4.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}

		if (mH264 != null) {
			utils.writeInt(dst, 1);
			mH264.writeToParcel(dst, flags);
		} else {
			utils.writeInt(dst, 0);
		}
	}

	/*****************************************************************************************
	 * CREATOR
	 *****************************************************************************************/
	public static final Creator<VideoEncoderConfigurationOptions> CREATOR = new Creator<VideoEncoderConfigurationOptions>() {
		public VideoEncoderConfigurationOptions createFromParcel(Parcel in) {
			return new VideoEncoderConfigurationOptions(in);
		}

		public VideoEncoderConfigurationOptions[] newArray(int size) {
			return new VideoEncoderConfigurationOptions[size];
		}
	};
}
