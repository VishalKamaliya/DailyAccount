package com.anand.dailyaccount.commonJavaClasses;

/**
 * Created by Anand Jakhaniya on 08-10-2017.
 * @author Anand Jakhaniya
 */

public interface DatabaseSource {

    String DATABASE_NAME = "dailyAccount.db";
    int DATABASE_VERSION = 1;

    /**
     * Table name
     */
    String USER_TABLE = "user";
    String RECORD_TABLE = "record";

    String COLUMN_ID = "id";
    String COLUMN_NAME = "name";
    String USER_EMAIL = "user_email";
    String USER_PASSWORD = "user_password";
    String COLUMN_AMOUNT = "amount";
    String COLUMN_DESCRIPTION = "description";
    String COLUMN_TYPE = "type";
    String COLUMN_DATE = "date";

    String CREATE_USER = "create table "
            + USER_TABLE + "("
            + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME +" text,"
            + USER_EMAIL +" integer,"
            + USER_PASSWORD +" integer)";

    String CREATE_RECORD = "create table "
            + RECORD_TABLE + "("
            + COLUMN_ID +" INTEGER PRIMARY KEY, "
            + COLUMN_NAME +" text,"
            + COLUMN_AMOUNT +" integer,"
            + COLUMN_DESCRIPTION +" text,"
            + COLUMN_TYPE +" text,"
            + COLUMN_DATE +" integer)";
}
