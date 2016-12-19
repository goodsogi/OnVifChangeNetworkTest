package org.onvif.ver10.image.wsdl.set;

import android.util.Log;

import org.ksoap2.serialization.SoapObject;
import org.onvif.ver10.image.wsdl.ImageService;
import org.onvif.ver10.schema.nativeParcel.ImagingSettings20;

public class SetImagingSettings extends SoapObject {
	public static final String METHOD_NAME = SetImagingSettings.class.getSimpleName();

	private SoapObject mImagingSettings20;

	public SetImagingSettings() {
		super(ImageService.NAMESPACE, METHOD_NAME);
	}

	public void setVideoSourceToken(String videoSourceToken) {
		this.addProperty(ImageService.NAMESPACE, "VideoSourceToken", videoSourceToken);
		Log.i(METHOD_NAME,"setVideoSourceToken:"+videoSourceToken);
	}

	public void setImagingSettings20(ImagingSettings20 settings) {
		mImagingSettings20 = new SoapObject(ImageService.NAMESPACE, "ImagingSettings");
		if (settings != null) {
			// ===================================================================
			// BacklightCompensation
			// ===================================================================
			if (settings.mBacklightCompensation != null) {
				SoapObject BacklightCompensation = new SoapObject(ImageService.SCHEMA, "BacklightCompensation");
				BacklightCompensation.addProperty(ImageService.SCHEMA, "Mode", settings.mBacklightCompensation.mMode);
				BacklightCompensation.addProperty(ImageService.SCHEMA, "Level", settings.mBacklightCompensation.mLevel);
				mImagingSettings20.addSoapObject(BacklightCompensation);
			}
			// ===================================================================
			// Brightness
			// ===================================================================
			if (settings.mBrightness != -1) {
				mImagingSettings20.addProperty(ImageService.SCHEMA, "Brightness", settings.mBrightness);
			}
			// ===================================================================
			// ColorSaturation
			// ===================================================================
			if (settings.mColorSaturation != -1) {
				mImagingSettings20.addProperty(ImageService.SCHEMA, "ColorSaturation", settings.mColorSaturation);
			}
			// ===================================================================
			// Contrast
			// ===================================================================
			if (settings.mContrast != -1) {
				mImagingSettings20.addProperty(ImageService.SCHEMA, "Contrast", settings.mContrast);
			}
			// ===================================================================
			// Exposure
			// ===================================================================
			if (settings.mExposure != null) {
				SoapObject Exposure = new SoapObject(ImageService.SCHEMA, "Exposure");
				Exposure.addProperty(ImageService.SCHEMA, "Mode", settings.mExposure.mMode);

				if (settings.mExposure.mPriority != null)
					Exposure.addProperty(ImageService.SCHEMA, "Priority", settings.mExposure.mPriority);

				// Window
				if (settings.mExposure.mWindow != null) {
					SoapObject Window = new SoapObject(ImageService.SCHEMA, "Window");
					Window.addAttribute("bottom", settings.mExposure.mWindow.mBottom);
					Window.addAttribute("top", settings.mExposure.mWindow.mTop);
					Window.addAttribute("right", settings.mExposure.mWindow.mRight);
					Window.addAttribute("left", settings.mExposure.mWindow.mLeft);
					Exposure.addSoapObject(Window);
				}

				if (settings.mExposure.mMinExposureTime != -1)
					Exposure.addProperty(ImageService.SCHEMA, "MinExposureTime", settings.mExposure.mMinExposureTime);

				if (settings.mExposure.mMaxExposureTime != -1)
					Exposure.addProperty(ImageService.SCHEMA, "MaxExposureTime", settings.mExposure.mMaxExposureTime);

				if (settings.mExposure.mMinGain != -1)
					Exposure.addProperty(ImageService.SCHEMA, "MinGain", settings.mExposure.mMinGain);

				if (settings.mExposure.mMaxGain != -1)
					Exposure.addProperty(ImageService.SCHEMA, "MaxGain", settings.mExposure.mMaxGain);

				if (settings.mExposure.mMinIris != -1)
					Exposure.addProperty(ImageService.SCHEMA, "MinIris", settings.mExposure.mMinIris);

				if (settings.mExposure.mMaxIris != -1)
					Exposure.addProperty(ImageService.SCHEMA, "MaxIris", settings.mExposure.mMaxIris);

				if (settings.mExposure.mExposureTime != -1)
					Exposure.addProperty(ImageService.SCHEMA, "ExposureTime", settings.mExposure.mExposureTime);

				if (settings.mExposure.mGain != -1)
					Exposure.addProperty(ImageService.SCHEMA, "Gain", settings.mExposure.mGain);

				if (settings.mExposure.mIris != -1)
					Exposure.addProperty(ImageService.SCHEMA, "Iris", settings.mExposure.mIris);

				mImagingSettings20.addSoapObject(Exposure);
			}

			// ===================================================================
			// Focus
			// ===================================================================
			if (settings.mFocus != null) {
				SoapObject Focus = new SoapObject(ImageService.SCHEMA, "Focus");
				Focus.addProperty(ImageService.SCHEMA, "AutoFocusMode", settings.mFocus.mAutoFocusMode);

				if (settings.mFocus.mDefaultSpeed != -1)
					Focus.addProperty(ImageService.SCHEMA, "DefaultSpeed", settings.mFocus.mDefaultSpeed);

				if (settings.mFocus.mNearLimit != -1)
					Focus.addProperty(ImageService.SCHEMA, "NearLimit", settings.mFocus.mNearLimit);

				if (settings.mFocus.mFarLimit != -1)
					Focus.addProperty(ImageService.SCHEMA, "FarLimit", settings.mFocus.mFarLimit);

				mImagingSettings20.addSoapObject(Focus);
			}

			// ===================================================================
			// IrCutFilter
			// ===================================================================
			if (settings.mIrCutFilter != null) {
				mImagingSettings20.addProperty(ImageService.SCHEMA, "IrCutFilter", settings.mIrCutFilter);
			}

			// ===================================================================
			// Sharpness
			// ===================================================================
			if (settings.mSharpness != -1) {
				mImagingSettings20.addProperty(ImageService.SCHEMA, "Sharpness", settings.mSharpness);
			}

			// ===================================================================
			// WideDynamicRange
			// ===================================================================
			if (settings.mWideDynamicRange != null) {
				SoapObject WideDynamicRange = new SoapObject(ImageService.SCHEMA, "WideDynamicRange");
				WideDynamicRange.addProperty(ImageService.SCHEMA, "Mode", settings.mWideDynamicRange.mMode);

				if (settings.mWideDynamicRange.mLevel != -1)
					WideDynamicRange.addProperty(ImageService.SCHEMA, "Level", settings.mWideDynamicRange.mLevel);

				mImagingSettings20.addSoapObject(WideDynamicRange);
			}

			// ===================================================================
			// WhiteBalance
			// ===================================================================
			if (settings.mWhiteBalance != null) {
				SoapObject WhiteBalance = new SoapObject(ImageService.SCHEMA, "WhiteBalance");
				WhiteBalance.addProperty(ImageService.SCHEMA, "Mode", settings.mWhiteBalance.mMode);

				if (settings.mWhiteBalance.mCrGain != -1)
					WhiteBalance.addProperty(ImageService.SCHEMA, "CrGain", settings.mWhiteBalance.mCrGain);

				if (settings.mWhiteBalance.mCbGain != -1)
					WhiteBalance.addProperty(ImageService.SCHEMA, "CbGain", settings.mWhiteBalance.mCbGain);

				mImagingSettings20.addSoapObject(WhiteBalance);
			}
		}
		addSoapObject(mImagingSettings20);
	}
}
