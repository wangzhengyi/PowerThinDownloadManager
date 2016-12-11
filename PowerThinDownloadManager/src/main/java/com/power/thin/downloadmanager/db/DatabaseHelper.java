package com.power.thin.downloadmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "powerthindownloadmanager";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + DownloadInfoTable.TABLE_NAME + "("
                + DownloadInfoTable.ID + " INTEGER PRIMARY KEY AUTO INCREMENT NOT NULL, "
                + DownloadInfoTable.REQUEST_ID + " INTEGER NOT NULL ON CONFLICT FAIL, "
                + DownloadInfoTable.DOWNLOAD_URL + " TEST NOT NULL ON CONFLICT FAIL, "
                + DownloadInfoTable.DESTINATION_FILE_PATH + " TEST NOT NULL ON CONFLICT FAIL, "
                + DownloadInfoTable.TOTAL_SIZE + " TEST, "
                + DownloadInfoTable.DOWNLOAD_SIZE + " TEST"
                + ")";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
