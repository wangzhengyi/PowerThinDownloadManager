package com.power.thin.downloadmanager.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SQLiteUtils {
    private DatabaseHelper mDatabaseHelper;

    public SQLiteUtils(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public SQLiteUtils(Context context, String name, CursorFactory cursorFactory, int version) {
        mDatabaseHelper = new DatabaseHelper(context, name, cursorFactory, version);
    }

    /**
     * 保存下载任务信息到数据库
     *
     * @param downloadInfoBean 下载信息描述类
     */
    public void saveDownloadInfo(DownloadInfoBean downloadInfoBean) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DownloadInfoTable.REQUEST_ID, downloadInfoBean.getRequestId());
        cv.put(DownloadInfoTable.DOWNLOAD_URL, downloadInfoBean.getDownloadUrl());
        cv.put(DownloadInfoTable.DESTINATION_FILE_PATH, downloadInfoBean.getDestinationPath());
        cv.put(DownloadInfoTable.TOTAL_SIZE, downloadInfoBean.getTotalSize());
        cv.put(DownloadInfoTable.DOWNLOAD_SIZE, downloadInfoBean.getDownloadSize());
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(
                    "SELECT * FROM " + DownloadInfoTable.TABLE_NAME
                            + " WHERE " + DownloadInfoTable.REQUEST_ID + "=?",
                    new String[]{String.valueOf(downloadInfoBean.getRequestId())});
            if (cursor != null && cursor.moveToFirst()) {
                db.update(DownloadInfoTable.TABLE_NAME, cv, DownloadInfoTable.REQUEST_ID + "= ?",
                        new String[] {String.valueOf(downloadInfoBean.getRequestId())});
            } else {
                db.insert(DownloadInfoTable.TABLE_NAME, null, cv);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }
}
