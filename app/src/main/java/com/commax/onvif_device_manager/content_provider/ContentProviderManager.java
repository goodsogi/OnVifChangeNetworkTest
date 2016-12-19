package com.commax.onvif_device_manager.content_provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.commax.onvif_device_manager.device.DeviceInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Content Provider 관리
 * Created by bagjeong-gyu on 2016. 9. 26..
 */

public class ContentProviderManager {


    /**
     * OnVif 디바이스 모든 데이터 삭제
     *
     * @param context
     */
    public static void deleteAllOnvifDevice(Context context) {
        int cursor =
                context.getContentResolver().delete(ContentProviderConstants.OnvifDeviceEntry.CONTENT_URI,
                        null, null);
    }


    /**
     * OnVif 디바이스 데이터 저장
     *
     * @param context
     * @param contentValues
     */
    public static void saveOnvifDevice(Context context, ContentValues contentValues) {


        Uri cursor =
                context.getContentResolver().insert(ContentProviderConstants.OnvifDeviceEntry.CONTENT_URI,
                        contentValues);


    }



    /**
     * 모든 OnvifDevice 항목 가져옴
     * @param context
     * @return
     */
    public static List<DeviceInfo> getAllOnvifDevice(Context context) {
        Cursor cursor =
                context.getContentResolver().query(ContentProviderConstants.OnvifDeviceEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

        List<DeviceInfo> deviceInfos = new ArrayList<>();

        DeviceInfo deviceInfo = null;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            String ip = cursor.getString(cursor
                    .getColumnIndex(ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_IP));
            String id = cursor.getString(cursor
                    .getColumnIndex(ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_ID));
            String password = cursor.getString(cursor
                    .getColumnIndex(ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_PASSWORD));
            String deviceName = cursor.getString(cursor
                    .getColumnIndex(ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_DEVICE_NAME));

            deviceInfo = new DeviceInfo();
            deviceInfo.setIp(ip);
            deviceInfo.setId(id);
            deviceInfo.setPassword(password);
            deviceInfo.setDeviceName(deviceName);

            deviceInfos.add(deviceInfo);

        }

        cursor.close();

        return deviceInfos;
    }



}
