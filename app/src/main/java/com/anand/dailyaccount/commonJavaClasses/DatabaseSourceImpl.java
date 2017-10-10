package com.anand.dailyaccount.commonJavaClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Anand Jakhaniya on 08-10-2017.
 * @author Anand Jakhaniya
 */

public class DatabaseSourceImpl extends SQLiteOpenHelper implements DatabaseSource {

    private final String TAG = this.getClass().getSimpleName();
    private static DatabaseSourceImpl ourInstance = null;
    private SQLiteDatabase database;

    public static DatabaseSourceImpl getInstance() {
        if (ourInstance == null) {
            ourInstance = new DatabaseSourceImpl(ApplicationMainClass.getInstance());
            ourInstance.openDataBase();
        }
        if (!ourInstance.getDataBase().isOpen()) {
            ourInstance.openDataBase();
        }
        return ourInstance;
    }

    private DatabaseSourceImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void openDataBase() {
        this.database = getWritableDatabase();
    }

    public SQLiteDatabase getDataBase() {
        if (!database.isOpen()) {
            openDataBase();
        }
        return this.database;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "Start Creating Database...");
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_RECORD);
        Log.i(TAG, "New Database Created successfully..");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersionNumber, int newVersionNumber) {
        Log.i(TAG, "Upgrading database from " + oldVersionNumber + " to " + newVersionNumber);
        /*if(newVersionNumber > oldVersionNumber){
            Log.i(TAG, "Upgrading database from version " + oldVersionNumber + " to " + newVersionNumber );
            try {
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RECORD_TABLE);
            }catch (Exception e){
                e.printStackTrace();
                Log.i(TAG, "Error while droping tables  " + e.toString());
            }
            onCreate(sqLiteDatabase);
        }*/
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       onUpgrade(db,oldVersion,newVersion);
    }

    public void resetDataTable() {

        Log.i(TAG, "Resetting DataTable's data...");
        SQLiteDatabase db = this.getWritableDatabase();
        resetDataTableInternal(db);
    }


    public void resetDataTableInternal(SQLiteDatabase db) {

        Log.i(TAG, "Resetting DataTable's data...");
        db.delete(USER_TABLE, null, null);
        db.delete(RECORD_TABLE, null, null);
    }
}
