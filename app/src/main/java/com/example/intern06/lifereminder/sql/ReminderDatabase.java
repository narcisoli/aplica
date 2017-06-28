package com.example.intern06.lifereminder.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.intern06.lifereminder.obiecte.reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderDatabase extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "reminder";

    private static final String TABLE_CONTACTS = "remindertable";


    private static final String DATA = "data";
    private static final String TEXT = "text";
    private static final String STATUS = "status";
    private static final String CULOARETEXT = "culoaretext";
    private static final String CULOAREFUNDAL = "culoarefundal";
    private static final String FIXEAZA = "fixeaza";


    public ReminderDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + DATA + " TEXT PRIMARY KEY," + TEXT + " TEXT,"
                + STATUS + " INTEGER," + CULOARETEXT + " INTEGER," + CULOAREFUNDAL + " INTEGER," + FIXEAZA + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }


    public void onDelete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addReminder(reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATA, reminder.getData());
        values.put(TEXT, reminder.getText());
        values.put(STATUS, reminder.getStatus());
        values.put(CULOARETEXT, reminder.getCuloaretext());
        values.put(CULOAREFUNDAL, reminder.getCuloarefundal());
        values.put(FIXEAZA, reminder.getFixeaza());


        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public List<reminder> getReminders() {
        List<reminder> contactList = new ArrayList<reminder>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", TABLE_CONTACTS});
        boolean a = true;
        if (!cursor.moveToFirst()) {
            onCreate(db);
            cursor.close();
        }

        try {


            cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    reminder rem = new reminder();
                    rem.setData(cursor.getString(0));
                    rem.setText(cursor.getString(1));
                    rem.setStatus(cursor.getInt(2));
                    rem.setCuloaretext(cursor.getInt(3));
                    rem.setCuloarefundal(cursor.getInt(4));
                    rem.setFixeaza(cursor.getInt(5));

                    contactList.add(rem);
                } while (cursor.moveToNext());
            }


        } catch (SQLException ex) {
        }
        return contactList;
    }

    public void deleteReminder(reminder note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, DATA + " = ?", new String[]{note.getData()});
        db.close();
    }
    public int updateContact(reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATA, reminder.getData());
        values.put(TEXT, reminder.getText());
        values.put(STATUS, reminder.getStatus());
        values.put(CULOARETEXT, reminder.getCuloaretext());
        values.put(CULOAREFUNDAL, reminder.getCuloarefundal());
        values.put(FIXEAZA, reminder.getFixeaza());

        // updating row
        return db.update(TABLE_CONTACTS, values, DATA + " = ?",
                new String[] { String.valueOf(reminder.getData()) });
    }
}