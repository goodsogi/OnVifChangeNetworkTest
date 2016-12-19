package com.commax.onvif_device_manager.content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.commax.onvif_device_manager.DeviceManagerConstants;

/**
 * Onvif 디바이스 관련 레코드 관리하는 Content Provider
 */
public class OnvifDeviceProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DBHelper mOpenHelper;

    // Codes for the UriMatcher //////
    private static final int ONVIF_DEVICE = 100;
    ////////

    /**
     * URI Matcher 생성
     *
     * @return UriMatcher
     */
    private static UriMatcher buildUriMatcher() {
        // Build a UriMatcher by adding a specific code to return based on a match
        // It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ContentProviderConstants.CONTENT_AUTHORITY;

        // add a code for each type of URI you want
        matcher.addURI(authority, ContentProviderConstants.OnvifDeviceEntry.TABLE_NAME, ONVIF_DEVICE);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new DBHelper(getContext());

        return true;
    }


    /**
     * URI 타입 가져옴
     */
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        Log.d(DeviceManagerConstants.LOG_TAG, "uri match: " + match);

        switch (match) {
            case ONVIF_DEVICE: {
                return ContentProviderConstants.OnvifDeviceEntry.CONTENT_DIR_TYPE;
            }

            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    /**
     * 레코드 조회
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {

            case ONVIF_DEVICE: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        ContentProviderConstants.OnvifDeviceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                return retCursor;
            }


            default: {
                // By default, we assume a bad URI
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    /**
     * 레코드 삽입
     *
     * @param uri
     * @param values
     * @return
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;
        switch (sUriMatcher.match(uri)) {
            case ONVIF_DEVICE: {
                long _id = db.insert(ContentProviderConstants.OnvifDeviceEntry.TABLE_NAME, null, values);
                // insert unless it is already contained in the database
                if (_id > 0) {
                    returnUri = ContentProviderConstants.OnvifDeviceEntry.buildFlavorsUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into: " + uri);
                }
                break;
            }

            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);

            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    /**
     * 레코드 삭제
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int numDeleted;
        switch (match) {
            case ONVIF_DEVICE:
                numDeleted = db.delete(
                        ContentProviderConstants.OnvifDeviceEntry.TABLE_NAME, selection, selectionArgs);
                // reset _ID
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                        ContentProviderConstants.OnvifDeviceEntry.TABLE_NAME + "'");
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return numDeleted;
    }

    /**
     * 레코드 일괄 삽입
     *
     * @param uri
     * @param values
     * @return
     */
    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ONVIF_DEVICE:
                // allows for multiple transactions
                db.beginTransaction();

                // keep track of successful inserts
                int numInserted = 0;
                try {
                    for (ContentValues value : values) {
                        if (value == null) {
                            throw new IllegalArgumentException("Cannot have null content values");
                        }
                        long _id = -1;
                        try {
                            _id = db.insertOrThrow(ContentProviderConstants.OnvifDeviceEntry.TABLE_NAME,
                                    null, value);
                        } catch (SQLiteConstraintException e) {
                            Log.w(DeviceManagerConstants.LOG_TAG, "Attempting to insert " +
                                    value.getAsString(
                                            ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_ID
                                    + " but value is already in database."));
                        }
                        if (_id != -1) {
                            numInserted++;
                        }
                    }
                    if (numInserted > 0) {
                        // If no errors, declare a successful transaction.
                        // database will not populate if this is not called
                        db.setTransactionSuccessful();
                    }
                } finally {
                    // all transactions occur at once
                    db.endTransaction();
                }
                if (numInserted > 0) {
                    // if there was successful insertion, notify the content resolver that there
                    // was a change
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return numInserted;

            default:
                return super.bulkInsert(uri, values);
        }
    }

    /**
     * 레코드 업데이트
     *
     * @param uri
     * @param contentValues
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int numUpdated = 0;

        if (contentValues == null) {
            throw new IllegalArgumentException("Cannot have null content values");
        }

        switch (sUriMatcher.match(uri)) {
            case ONVIF_DEVICE: {
                numUpdated = db.update(ContentProviderConstants.OnvifDeviceEntry.TABLE_NAME,
                        contentValues,
                        selection,
                        selectionArgs);
                break;
            }

            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }

        if (numUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numUpdated;
    }


}
