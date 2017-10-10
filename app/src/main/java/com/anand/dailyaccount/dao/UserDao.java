package com.anand.dailyaccount.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.anand.dailyaccount.commonJavaClasses.DatabaseSource;
import com.anand.dailyaccount.commonJavaClasses.DatabaseSourceImpl;
import com.anand.dailyaccount.entity.User;

/**
 * Created by Anand Jakhaniya on 08-10-2017.
 * @author Anand Jakhaniya
 */

public class UserDao implements DatabaseSource {

    private SQLiteDatabase database;
    private final String selectedField = COLUMN_ID+", "+COLUMN_NAME+","+USER_EMAIL+", "+USER_PASSWORD;

    public UserDao() {
        DatabaseSourceImpl baseDataSourceImpl = DatabaseSourceImpl.getInstance();
        database = baseDataSourceImpl.getDataBase();
    }

    public void addUser(User user) {
        ContentValues values = getContentValues(user);
        database.insert(USER_TABLE, null, values);
    }

    public void update(User user) {
        ContentValues values = getContentValues(user);
        database.update(USER_TABLE, values, COLUMN_ID + " = " + user.id, null);
    }

    public User getUser(String email) {
        Cursor cursor = null;
        cursor = database.rawQuery("select "+selectedField+" from "+USER_TABLE+" where "+USER_EMAIL+" = '" + email+"'", null);

        cursor.moveToFirst();

        User user = null;
        while (!cursor.isAfterLast()) {
            user = cursorToUser(cursor);
            cursor.moveToNext();
        }
        return user;
    }

    private ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.id);
        values.put(COLUMN_NAME, user.name);
        values.put(USER_EMAIL, user.email);
        values.put(USER_PASSWORD, user.password);
        return values;
    }

    private User cursorToUser(Cursor cursor) {

        User user = new User();
        user.id = cursor.getLong(0);
        user.name = cursor.getString(1);
        user.email = cursor.getString(2);
        user.password = cursor.getString(3);
        return user;
    }
}
