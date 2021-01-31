package com.example.newtestapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseConnectionHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseConnectionHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "document.db";
    private static final int DATABASE_VERSION = 1;

    private static final String ID = "id";
    private static final String TABLE_NAME = "User";
    private static final String COLUMN_FIRST_NAME_TABLE_USER = "firstName";
    private static final String COLUMN_LAST_NAME_TABLE_USER = "lastName";
    private static final String COLUMN_USER_ACTIVE_TABLE_USER = "user_active";
    private static final String COLUMN_PASSWORD_TABLE_USER = "password";

    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FIRST_NAME_TABLE_USER + " Varchar(30),"
            + COLUMN_LAST_NAME_TABLE_USER + " Varchar(40),"
            + COLUMN_PASSWORD_TABLE_USER + " Varchar(20) not null,"
            + COLUMN_USER_ACTIVE_TABLE_USER + " Boolean default 1 not null)";

    private final static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseConnectionHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.w(TAG, "Datenbank erstellt!");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Database updated from Version: " + oldVersion + " to new Version: " + newVersion + "! Alle Daten wurden gelöscht");
        db.execSQL(DROP_TABLE);
        onCreate(db);
        db.close();
    }

    public boolean insertData(String firstName, String lastName, String password, boolean active) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME_TABLE_USER, firstName);
        values.put(COLUMN_LAST_NAME_TABLE_USER, lastName);
        values.put(COLUMN_PASSWORD_TABLE_USER, password);
        values.put(COLUMN_USER_ACTIVE_TABLE_USER, active);
        SQLiteDatabase db = getWritableDatabase();
        long dbResult = db.insert(TABLE_NAME, null, values);
        if (dbResult != -1) {
            Log.w(TAG, "Werte wurden eingetragen");
            return true;
        } else {
            return false;
        }
    }

    public Cursor query() {
        SQLiteDatabase db = getWritableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, COLUMN_USER_ACTIVE_TABLE_USER + "DESC");
    }

    public void update(long id, String firstName, String lastName, String password, boolean active) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME_TABLE_USER, firstName);
        values.put(COLUMN_LAST_NAME_TABLE_USER, lastName);
        values.put(COLUMN_PASSWORD_TABLE_USER, password);
        values.put(COLUMN_USER_ACTIVE_TABLE_USER, active);
        int updatedRow = db.update(TABLE_NAME, values, id + "=?", new String[]{Long.toString(id)});
        Log.w(TAG, "Row " + updatedRow + " was updated");
        db.close();
    }

    public boolean updateData(String id, String firstName, String lastName, String password, boolean active) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(COLUMN_FIRST_NAME_TABLE_USER, firstName);
        values.put(COLUMN_LAST_NAME_TABLE_USER, lastName);
        values.put(COLUMN_PASSWORD_TABLE_USER, password);
        values.put(COLUMN_USER_ACTIVE_TABLE_USER, active);
        db.update(TABLE_NAME, values, "id =?", new String[]{id});
        db.close();
        return true;
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, id + "=?", new String[]{Long.toString(id)});
        db.close();
    }

    public Cursor getAllData() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("Select * from " + TABLE_NAME + ";", null);
    }

    public boolean userExist(String name, String password) {
        boolean result = false;
        SQLiteDatabase db = getReadableDatabase();
        Cursor r = db.rawQuery("Select * from " + TABLE_NAME + " where lastName=? and password =? and user_active = 1;", new String[]{name, password});
        if (r.getCount() > 0) {
            result = true;
        }
        return result;
    }
}
