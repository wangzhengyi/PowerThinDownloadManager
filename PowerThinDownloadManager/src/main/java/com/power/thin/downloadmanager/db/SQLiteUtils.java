package com.power.thin.downloadmanager.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
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
                        new String[]{String.valueOf(downloadInfoBean.getRequestId())});
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

    public DownloadInfoBean getDownloadInfo(int requestId) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        DownloadInfoBean bean = null;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM" + DownloadInfoTable.TABLE_NAME
                            + " WHERE " + DownloadInfoTable.REQUEST_ID + " = ?",
                    new String[]{String.valueOf(requestId)});
            if (cursor != null && cursor.moveToFirst()) {
                bean = new DownloadInfoBean();
                bean.setRequestId(requestId);
                bean.setDownloadUrl(cursor.getString(
                        cursor.getColumnIndex(DownloadInfoTable.DOWNLOAD_URL)));
                bean.setDestinationPath(cursor.getString(
                        cursor.getColumnIndex(DownloadInfoTable.DESTINATION_FILE_PATH)));
                bean.setTotalSize(cursor.getString(
                        cursor.getColumnIndex(DownloadInfoTable.TOTAL_SIZE)));
                bean.setDownloadSize(cursor.getString(
                        cursor.getColumnIndex(DownloadInfoTable.DOWNLOAD_SIZE)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return bean;
    }

    public List<DownloadInfoBean> getAllDownloadInfo() {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        Cursor cursor = null;
        List<DownloadInfoBean> downloadInfoBeanList = new ArrayList<DownloadInfoBean>();
        try {
            cursor = db.rawQuery("SELECT * FROM" + DownloadInfoTable.TABLE_NAME, null);
            if (cursor != null && cursor.moveToFirst()) {
                DownloadInfoBean bean = new DownloadInfoBean();
                bean.setRequestId(cursor.getInt(
                        cursor.getColumnIndex(DownloadInfoTable.REQUEST_ID)));
                bean.setDownloadUrl(cursor.getString(
                        cursor.getColumnIndex(DownloadInfoTable.DOWNLOAD_URL)));
                bean.setDestinationPath(cursor.getString(
                        cursor.getColumnIndex(DownloadInfoTable.DESTINATION_FILE_PATH)));
                bean.setTotalSize(cursor.getString(
                        cursor.getColumnIndex(DownloadInfoTable.TOTAL_SIZE)));
                bean.setDownloadSize(cursor.getString(
                        cursor.getColumnIndex(DownloadInfoTable.DOWNLOAD_SIZE)));
                downloadInfoBeanList.add(bean);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return downloadInfoBeanList;
    }

    public int deleteDownloadInfo(int requestId) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int affectedNum = 0;
        try {
            affectedNum = db.delete(
                    DownloadInfoTable.TABLE_NAME,
                    DownloadInfoTable.REQUEST_ID + " = ?",
                    new String[]{String.valueOf(requestId)});
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return affectedNum;
    }

    public int deleteAllDownloadInfo() {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int affectedNum = 0;
        try {
            affectedNum = db.delete(DownloadInfoTable.TABLE_NAME, null, null);
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return affectedNum;
    }
}
