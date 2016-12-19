package com.commax.onvif_device_manager.content_provider;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * 디비 관리
 *
 * @author Jeonggyu Park
 */
class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "onvifDevice.db";
    //데이터베이스 버전을 높여야 재설치했을 때 새로운 테이블과 칼럼등이 생성됨
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    //OnVif 디바이스 테이블 생성
    private static final String SQL_CREATE_ONVIF_DEVICE_TABLE = "CREATE TABLE "
            + ContentProviderConstants.OnvifDeviceEntry.TABLE_NAME + " (" +  ContentProviderConstants.OnvifDeviceEntry._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT " + COMMA_SEP
            +  ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_IP + TEXT_TYPE + COMMA_SEP
            +  ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP
            +  ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP
            +  ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_DEVICE_NAME + TEXT_TYPE + " )";



    //OnVif 디바이스 테이블 삭제
    private static final String SQL_DELETE_ONVIF_DEVICE_TABLE = "DROP TABLE IF EXISTS "
            + ContentProviderConstants.OnvifDeviceEntry.TABLE_NAME;



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ONVIF_DEVICE_TABLE);
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 기존 테이블 삭제
        db.execSQL(SQL_DELETE_ONVIF_DEVICE_TABLE);

        // 새로 DB 생성
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}