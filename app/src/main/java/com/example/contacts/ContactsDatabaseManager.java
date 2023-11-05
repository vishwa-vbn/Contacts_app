//package com.example.contacts;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ContactsDatabaseManager {
//
//    private static final String DATABASE_NAME = "ContactsDatabase";
//    private static final int DATABASE_VERSION = 5;
//
//
//
//
//
//    private static final String TABLE_CONTACTS = "contacts";
//    private static final String COLUMN_ID = "id";
//    private static final String COLUMN_FIRST_NAME = "first_name";
//    private static final String COLUMN_LAST_NAME = "last_name";
//    private static final String COLUMN_PHONE_NUMBER = "phone_number";
//    private static final String COLUMN_PHONE_TYPE = "phone_type";
//    private static final String COLUMN_EMAIL = "email";
//    private static final String COLUMN_DATE = "date";
//    private static final String COLUMN_DATE_LABEL = "date_label";
//    private static final String COLUMN_ADDRESS = "address";
//    private static final String COLUMN_NOTES = "notes";
//    private static final String COLUMN_IS_FAVORITE = "is_favorite";
//    private static final String COLUMN_GROUP_ID = "group_id";
//
//
//
//
//    private static final String TABLE_GROUPS = "groups";
//    private static final String COLUMN_GROUP_NAME = "group_name";
//
//    // Create table query
//    private static final String CREATE_TABLE_CONTACTS =
//            "CREATE TABLE " + TABLE_CONTACTS + "(" +
//                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    COLUMN_FIRST_NAME + " TEXT, " +
//                    COLUMN_LAST_NAME + " TEXT, " +
//                    COLUMN_PHONE_NUMBER + " TEXT NOT NULL, " +
//                    COLUMN_PHONE_TYPE + " TEXT, " +
//                    COLUMN_EMAIL + " TEXT, " +
//                    COLUMN_DATE + " TEXT, " +
//                    COLUMN_DATE_LABEL + " TEXT, " +
//                    COLUMN_ADDRESS + " TEXT, " +
//                    COLUMN_NOTES + " TEXT, " +
//                    COLUMN_IS_FAVORITE + " INTEGER, " +
//                    COLUMN_GROUP_ID + " INTEGER);";
//
//
//
////call_log table
//    private static final String TABLE_CALL_LOG = "call_log";
//    private static final String COLUMN_CALL_LOG_ID = "call_log_id";
//    private static final String COLUMN_CONTACT_ID = "contact_id";
//    private static final String COLUMN_CONTACT_NUMBER = "contact_number";
//    private static final String COLUMN_CONTACT_NAME = "contact_name";
//    private static final String COLUMN_CALL_DATE  = "call_date";
//    private static final String COLUMN_CALL_DURATION = "call_duration";
//
//    private static final String CREATE_TABLE_CALL_LOG =
//            "CREATE TABLE " + TABLE_CALL_LOG + "(" +
//                    COLUMN_CALL_LOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    COLUMN_CONTACT_ID + " INTEGER, " +
//                    COLUMN_CONTACT_NUMBER + " TEXT, " +
//                    COLUMN_CONTACT_NAME + " TEXT, " +
//                    COLUMN_CALL_DURATION + " INTEGER, " +
//                    COLUMN_CALL_DATE+ " TEXT, " + // Add a new column for the date
//                    "FOREIGN KEY(" + COLUMN_CONTACT_ID + ") REFERENCES " + TABLE_CONTACTS + "(" + COLUMN_ID + "));";
//
//
//
//
//
//
//
//
//
//    private static final String CREATE_TABLE_GROUPS =
//            "CREATE TABLE " + TABLE_GROUPS + "(" +
//                    COLUMN_GROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    COLUMN_GROUP_NAME + " TEXT);";
//
//
//
//
//
//
//
//
//
//
//    private static final String TABLE_CONTACT_GROUP_ASSOCIATION = "contact_group_association";
//    private static final String COLUMN_ASSOCIATION_ID = "association_id";  // Optional, but recommended for primary key
//
//    private static final String CREATE_TABLE_CONTACT_GROUP_ASSOCIATION =
//            "CREATE TABLE " + TABLE_CONTACT_GROUP_ASSOCIATION + "(" +
//                    COLUMN_ASSOCIATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    COLUMN_CONTACT_ID + " INTEGER, " +
//                    COLUMN_GROUP_ID + " INTEGER, " +
//                    "FOREIGN KEY(" + COLUMN_CONTACT_ID + ") REFERENCES " + TABLE_CONTACTS + "(" + COLUMN_ID + "), " +
//                    "FOREIGN KEY(" + COLUMN_GROUP_ID + ") REFERENCES " + TABLE_GROUPS + "(" + COLUMN_GROUP_ID + "));";
//
//
//
//    private Context context;
//    private SQLiteDatabase database;
//    private DatabaseHelper dbHelper;
//
//    public ContactsDatabaseManager(Context context) {
//        this.context = context;
//    }
//
//
//
//
//    // Open the database connection
//    public ContactsDatabaseManager open() throws SQLException {
//        dbHelper = new DatabaseHelper(context);
//        database = dbHelper.getWritableDatabase();
//        return this;
//    }
//
//    // Close the database connection
//    public void close() {
//        dbHelper.close();
//    }
//
//    // Insert a new contact into the database
//    public long insertContact(String firstName, String lastName, String phoneNumber, String phoneType,
//                              String email, String date, String dateLabel, String address,
//                              String notes,  boolean isFavorite, int groupId) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_FIRST_NAME, firstName);
//        values.put(COLUMN_LAST_NAME, lastName);
//        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
//        values.put(COLUMN_PHONE_TYPE, phoneType);
//        values.put(COLUMN_EMAIL, email);
//        values.put(COLUMN_DATE, date);
//        values.put(COLUMN_DATE_LABEL, dateLabel);
//        values.put(COLUMN_ADDRESS, address);
//        values.put(COLUMN_NOTES, notes);
//        values.put(COLUMN_IS_FAVORITE, isFavorite);
//        values.put(COLUMN_GROUP_ID, groupId);
//
//        try {
//            long result = database.insert(TABLE_CONTACTS, null, values);
//            return result;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//
//
//
//
//
//    public long insertCallLog(long contactId, String contactNumber, String contactName, long callDuration, String callDate) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_CONTACT_ID, contactId);
//        values.put(COLUMN_CONTACT_NUMBER, contactNumber);
//        values.put(COLUMN_CONTACT_NAME, contactName);
//        values.put(COLUMN_CALL_DURATION, callDuration);
//        values.put(COLUMN_CALL_DATE, callDate); // Add call_date to the ContentValues
//
//        try {
//            long result = database.insert(TABLE_CALL_LOG, null, values);
//            return result;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//
//
//
//
//
//
//    public long insertGroup(String groupName) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_GROUP_NAME, groupName);
//
//        // Insert the group and return the newly created group ID
//        return database.insert(TABLE_GROUPS, null, values);
//    }
//
//
//    // Associate a contact with a group in the database
//    public long insertContactToGroup(long contactId, long groupId) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_CONTACT_ID, contactId);
//        values.put(COLUMN_GROUP_ID, groupId);
//
//        try {
//            long result = database.insert(TABLE_CONTACT_GROUP_ASSOCIATION, null, values);
//            return result;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//
//
//
//    public List<Contact> getFavoriteContacts() {
//        List<Contact> favoriteContacts = new ArrayList<>();
//        String selection = COLUMN_IS_FAVORITE + " = 1"; // Select rows where ISFAVORITE is true
//
//        Cursor cursor = database.query(
//                TABLE_CONTACTS,
//                null,
//                selection,
//                null,
//                null,
//                null,
//                null
//        );
//
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do {
//                    long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID)); // Retrieve the ID
//                    Contact contact = new Contact(
//                            id, // Pass the ID
//                            cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_TYPE)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_DATE_LABEL)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_NOTES)),
//                            cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE)) == 1,
//                            cursor.getInt(cursor.getColumnIndex(COLUMN_GROUP_ID))
//                    );
//                    favoriteContacts.add(contact);
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//        }
//
//        return favoriteContacts;
//    }
//
//
//    public List<Contact> getAllContacts() {
//        List<Contact> contacts = new ArrayList<>();
//        Cursor cursor = database.query(TABLE_CONTACTS, null, null, null, null, null, null);
//
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do {
//                    long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID)); // Retrieve the ID
//                    Contact contact = new Contact(
//                            id, // Pass the ID
//                            cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_TYPE)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_DATE_LABEL)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)),
//                            cursor.getString(cursor.getColumnIndex(COLUMN_NOTES)),
//                            cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE)) == 1,
//                            cursor.getInt(cursor.getColumnIndex(COLUMN_GROUP_ID))
//                    );
//                    contacts.add(contact);
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//        }
//
//        return contacts;
//    }
//
//
//
//
//    public List<CallHistoryItem> getCallLogsForContact(long contactId) {
//        List<CallHistoryItem> callLogs = new ArrayList<>();
//        String selection = COLUMN_CONTACT_ID + " = ?";
//        String[] selectionArgs = { String.valueOf(contactId) };
//        String orderBy = COLUMN_CALL_DATE + " DESC"; // Order by call date (newest first)
//
//        Cursor cursor = database.query(TABLE_CALL_LOG, null, selection, selectionArgs, null, null, orderBy);
//
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do {
//                    String dateTime = cursor.getString(cursor.getColumnIndex(COLUMN_CALL_DATE));
//                    String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NUMBER));
//                    String contactName = cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NAME));
//                    long callDuration = cursor.getLong(cursor.getColumnIndex(COLUMN_CALL_DURATION));
//                    contactId = cursor.getInt(cursor.getColumnIndex(COLUMN_CONTACT_ID));
//
//                    CallHistoryItem callHistoryItem = new CallHistoryItem(dateTime, phoneNumber, contactName, callDuration,contactId);
//                    callLogs.add(callHistoryItem);
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//        }
//
//        return callLogs;
//    }
//
//
//
//    public List<Contact> getFrequentContacts() {
//        List<Contact> frequentContacts = new ArrayList<>();
//
//        // Query the call_log table to find the top 5 frequently called contacts
//        String[] projection = {COLUMN_CONTACT_ID, "COUNT(*) AS call_count"};
//        String groupBy = COLUMN_CONTACT_ID;
//        String orderBy = "call_count DESC";
//        String limit = "5";
//
//        Cursor cursor = database.query(
//                TABLE_CALL_LOG,
//                projection,
//                null,
//                null,
//                groupBy,
//                null,
//                orderBy,
//                limit
//        );
//
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do {
//                    long contactId = cursor.getLong(cursor.getColumnIndex(COLUMN_CONTACT_ID));
//
//                    // Mark the contact as a favorite
//                    markContactAsFavorite(contactId);
//
//                    // Retrieve contact details and add them to the list
//                    Contact contact = getContactDetails(contactId);
//
//                    if (contact != null) {
//                        frequentContacts.add(contact);
//                    }
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//        }
//
//        return frequentContacts;
//    }
//
//
//
//
//
//
//
//
//    // Helper method to retrieve contact details by contactId
//    public Contact getContactDetails(long contactId) {
//        Cursor cursor = database.query(
//                TABLE_CONTACTS,
//                null,
//                COLUMN_ID + " = ?",
//                new String[]{String.valueOf(contactId)},
//                null,
//                null,
//                null
//        );
//
//        if (cursor != null && cursor.moveToFirst()) {
//            Contact contact = new Contact(
//                    contactId,
//                    cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)),
//                    cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)),
//                    cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)),
//                    cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_TYPE)),
//                    cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
//                    cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
//                    cursor.getString(cursor.getColumnIndex(COLUMN_DATE_LABEL)),
//                    cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)),
//                    cursor.getString(cursor.getColumnIndex(COLUMN_NOTES)),
//                    cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE)) == 1,
//                    cursor.getInt(cursor.getColumnIndex(COLUMN_GROUP_ID))
//            );
//            cursor.close();
//            return contact;
//        }
//
//        return null; // Contact not found
//    }
//
//
//    // Helper method to mark a contact as a favorite
//    private void markContactAsFavorite(long contactId) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_IS_FAVORITE, 1); // Set is_favorite to true
//
//        String whereClause = COLUMN_ID + " = ?";
//        String[] whereArgs = {String.valueOf(contactId)};
//
//        database.update(TABLE_CONTACTS, values, whereClause, whereArgs);
//    }
//
//
//
//    // Other methods for updating, deleting, and querying contacts
//
//    // DatabaseHelper class for creating and upgrading the database
//    private static class DatabaseHelper extends SQLiteOpenHelper {
//
//        DatabaseHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//                db.execSQL(CREATE_TABLE_CONTACTS); // Create the 'contacts' table
//
//                db.execSQL(CREATE_TABLE_GROUPS); // Create the 'groups' table
//                db.execSQL(CREATE_TABLE_CONTACT_GROUP_ASSOCIATION); // Create the 'contact_group_association' table
//
//                db.execSQL(CREATE_TABLE_CALL_LOG);
//
//
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALL_LOG);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT_GROUP_ASSOCIATION); // Remove the space here
//            onCreate(db);
//        }
//
//    }
//}










package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ContactsDatabaseManager {

    private static final String DATABASE_NAME = "ContactsDatabase";
    private static final int DATABASE_VERSION = 9;

    private static final String TABLE_CONTACTS = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_PHONE_TYPE = "phone_type";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_DATE_LABEL = "date_label";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_NOTES = "notes";
    private static final String COLUMN_IS_FAVORITE = "is_favorite";
    private static final String COLUMN_GROUP_ID = "group_id";

    private static final String TABLE_GROUPS = "groups";
    private static final String COLUMN_GROUP_NAME = "group_name";

    private static final String COLUMN_IS_DELETED = "is_deleted";

    private static final String CREATE_TABLE_CONTACTS =
            "CREATE TABLE " + TABLE_CONTACTS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRST_NAME + " TEXT, " +
                    COLUMN_LAST_NAME + " TEXT, " +
                    COLUMN_PHONE_NUMBER + " TEXT NOT NULL, " +
                    COLUMN_PHONE_TYPE + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_DATE_LABEL + " TEXT, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_NOTES + " TEXT, " +
                    COLUMN_IS_FAVORITE + " INTEGER, " +
                    COLUMN_GROUP_ID + " INTEGER," +
                    COLUMN_IS_DELETED + " INTEGER DEFAULT 0);" ;

    private static final String TABLE_CALL_LOG = "call_log";
    private static final String COLUMN_CALL_LOG_ID = "call_log_id";
    private static final String COLUMN_CONTACT_ID = "contact_id";
    private static final String COLUMN_CONTACT_NUMBER = "contact_number";
    private static final String COLUMN_CONTACT_NAME = "contact_name";
    private static final String COLUMN_CALL_DATE = "call_date";
    private static final String COLUMN_CALL_DURATION = "call_duration";

    private static final String CREATE_TABLE_CALL_LOG =
            "CREATE TABLE " + TABLE_CALL_LOG + "(" +
                    COLUMN_CALL_LOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CONTACT_ID + " INTEGER, " +
                    COLUMN_CONTACT_NUMBER + " TEXT, " +
                    COLUMN_CONTACT_NAME + " TEXT, " +
                    COLUMN_CALL_DURATION + " INTEGER, " +
                    COLUMN_CALL_DATE + " TEXT, " +
                    COLUMN_DATE_LABEL + " TEXT, " +
                    "FOREIGN KEY(" + COLUMN_CONTACT_ID + ") REFERENCES " + TABLE_CONTACTS + "(" + COLUMN_ID + "));";

    private static final String CREATE_TABLE_GROUPS =
            "CREATE TABLE " + TABLE_GROUPS + "(" +
                    COLUMN_GROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_GROUP_NAME + " TEXT);";

    private static final String TABLE_CONTACT_GROUP_ASSOCIATION = "contact_group_association";
    private static final String COLUMN_ASSOCIATION_ID = "association_id";

    private static final String CREATE_TABLE_CONTACT_GROUP_ASSOCIATION =
            "CREATE TABLE " + TABLE_CONTACT_GROUP_ASSOCIATION + "(" +
                    COLUMN_ASSOCIATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CONTACT_ID + " INTEGER, " +
                    COLUMN_GROUP_ID + " INTEGER, " +
                    "FOREIGN KEY(" + COLUMN_CONTACT_ID + ") REFERENCES " + TABLE_CONTACTS + "(" + COLUMN_ID + "), " +
                    "FOREIGN KEY(" + COLUMN_GROUP_ID + ") REFERENCES " + TABLE_GROUPS + "(" + COLUMN_GROUP_ID + "));";

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ContactsDatabaseManager(Context context) {
        this.context = context;
    }

    public ContactsDatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insertContact(String firstName, String lastName, String phoneNumber, String phoneType,
                              String email, String date, String dateLabel, String address,
                              String notes, boolean isFavorite, int groupId, String profilePicturePath) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(COLUMN_PHONE_TYPE, phoneType);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_DATE_LABEL, dateLabel);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_NOTES, notes);
        values.put(COLUMN_IS_FAVORITE, isFavorite);
        values.put(COLUMN_GROUP_ID, groupId);

        try {
            long result = database.insert(TABLE_CONTACTS, null, values);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long insertCallLog(long contactId, String contactNumber, String contactName, long callDuration, String callDate) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACT_ID, contactId);
        values.put(COLUMN_CONTACT_NUMBER, contactNumber);
        values.put(COLUMN_CONTACT_NAME, contactName);
        values.put(COLUMN_CALL_DURATION, callDuration);
        values.put(COLUMN_CALL_DATE, callDate);

        // Get the current system date and format it for COLUMN_DATE_LABEL
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateLabel = dateFormat.format(new Date());
        values.put(COLUMN_DATE_LABEL, dateLabel); // Set the date label

        try {
            long result = database.insert(TABLE_CALL_LOG, null, values);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long insertGroup(String groupName) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_GROUP_NAME, groupName);
        return database.insert(TABLE_GROUPS, null, values);
    }






    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();
        Cursor cursor = database.query(
                TABLE_GROUPS,
                new String[] {COLUMN_GROUP_ID, COLUMN_GROUP_NAME},
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    long groupId = cursor.getLong(cursor.getColumnIndex(COLUMN_GROUP_ID));
                    String groupName = cursor.getString(cursor.getColumnIndex(COLUMN_GROUP_NAME));
                    Group group = new Group(groupId, groupName);
                    groups.add(group);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return groups;
    }


    public List<Contact> getExistingGroupMembers(long groupId) {
        List<Contact> existingMembers = new ArrayList<>();
        String selection = COLUMN_GROUP_ID + " = ?";
        String[] selectionArgs = {String.valueOf(groupId)};

        Cursor cursor = database.query(
                TABLE_CONTACT_GROUP_ASSOCIATION,
                new String[]{COLUMN_CONTACT_ID},
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    long contactId = cursor.getLong(cursor.getColumnIndex(COLUMN_CONTACT_ID));
                    // Retrieve contact details using the contactId
                    Contact contact = getContactDetails(contactId);
                    if (contact != null) {
                        existingMembers.add(contact);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return existingMembers;
    }


    public void deleteGroup(long groupId) {
        try {
            // Delete group from the groups table
            database.delete(TABLE_GROUPS, COLUMN_GROUP_ID + " = ?", new String[]{String.valueOf(groupId)});

            // Delete group associations from the contact_group_association table
            database.delete(TABLE_CONTACT_GROUP_ASSOCIATION, COLUMN_GROUP_ID + " = ?", new String[]{String.valueOf(groupId)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Contact> getFavoriteContacts() {
        List<Contact> favoriteContacts = new ArrayList<>();
        String selection = COLUMN_IS_FAVORITE + " = 1";

        Cursor cursor = database.query(
                TABLE_CONTACTS,
                null,
                selection,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                    Contact contact = new Contact(
                            id,
                            cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_TYPE)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_DATE_LABEL)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_NOTES)),
                            cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE)) == 1,
                            cursor.getInt(cursor.getColumnIndex(COLUMN_GROUP_ID))
                    );
                    favoriteContacts.add(contact);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return favoriteContacts;
    }


    public void setContactFavorite(long contactId, boolean isFavorite) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_FAVORITE, isFavorite ? 1 : 0);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(contactId)};

        // Directly update the 'is_favorite' field in the database
        int rowsUpdated = database.update(TABLE_CONTACTS, values, whereClause, whereArgs);

        if (rowsUpdated == 0) {
            // Handle the case where the update didn't affect any rows (optional)
        }
    }

    public List<CallLogWithContact> getCallLogsForAllContacts() {
        List<CallLogWithContact> callLogs = new ArrayList<>();

        // Query to retrieve ID, name, and call_date from CONTACTS and CALL_LOG tables
        String query = "SELECT " +
                "c." + COLUMN_ID + " AS contact_id, " +
                "c." + COLUMN_FIRST_NAME + " || ' ' || c." + COLUMN_LAST_NAME + " AS contact_name, " +
                "cl." + COLUMN_CALL_DATE + " AS call_date " +
                "FROM " + TABLE_CONTACTS + " c " +
                "LEFT JOIN " + TABLE_CALL_LOG + " cl ON c." + COLUMN_ID + " = cl." + COLUMN_CONTACT_ID;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    long contactId = cursor.getLong(cursor.getColumnIndex("contact_id"));
                    String contactName = cursor.getString(cursor.getColumnIndex("contact_name"));
                    String callDate = cursor.getString(cursor.getColumnIndex("call_date"));
                    CallLogWithContact callLogWithContact = new CallLogWithContact(contactId, contactName, callDate);
                    callLogs.add(callLogWithContact);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return callLogs;
    }


//commit check

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();

        String[] columns = {COLUMN_ID, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_PHONE_NUMBER, COLUMN_PHONE_TYPE, COLUMN_EMAIL, COLUMN_DATE, COLUMN_DATE_LABEL, COLUMN_ADDRESS, COLUMN_NOTES, COLUMN_IS_FAVORITE, COLUMN_GROUP_ID};

        // Filter out contacts with is_deleted = 1 (deleted)
        String selection = COLUMN_IS_DELETED + " = ?";
        String[] selectionArgs = {"0"};

        Cursor cursor = database.query(
                TABLE_CONTACTS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    long contactId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                    String firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
                    String lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
                    String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));
                    String phoneType = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_TYPE));
                    String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                    String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                    String dateLabel = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_LABEL));
                    String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                    String notes = cursor.getString(cursor.getColumnIndex(COLUMN_NOTES));
                    boolean isFavorite = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE)) == 1;
                    int groupId = cursor.getInt(cursor.getColumnIndex(COLUMN_GROUP_ID));

                    Contact contact = new Contact(contactId, firstName, lastName, phoneNumber, phoneType, email, date, dateLabel, address, notes, isFavorite, groupId);
                    contacts.add(contact);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return contacts;
    }




    public List<CallHistoryItem> getCallLogsForContact(long contactId) {
        List<CallHistoryItem> callLogs = new ArrayList<>();
        String selection = COLUMN_CONTACT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(contactId)};
        String orderBy = COLUMN_CALL_DATE + " DESC";

        Cursor cursor = database.query(TABLE_CALL_LOG, null, selection, selectionArgs, null, null, orderBy);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String dateTime = cursor.getString(cursor.getColumnIndex(COLUMN_CALL_DATE));
                    String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NUMBER));
                    String contactName = cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NAME));
                    long callDuration = cursor.getLong(cursor.getColumnIndex(COLUMN_CALL_DURATION));
                    contactId = cursor.getInt(cursor.getColumnIndex(COLUMN_CONTACT_ID));

                    CallHistoryItem callHistoryItem = new CallHistoryItem(dateTime, phoneNumber, contactName, callDuration, contactId);
                    callLogs.add(callHistoryItem);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return callLogs;
    }


    public long insertContactToGroup(long contactId, long groupId) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACT_ID, contactId);
        values.put(COLUMN_GROUP_ID, groupId);

        try {
            return database.insert(TABLE_CONTACT_GROUP_ASSOCIATION, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Handle the error
        }
    }

    public int updateGroup(long groupId, String newGroupName) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_GROUP_NAME, newGroupName);

        String whereClause = COLUMN_GROUP_ID + " = ?";
        String[] whereArgs = {String.valueOf(groupId)};

        try {
            return database.update(TABLE_GROUPS, values, whereClause, whereArgs);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Handle the error
        }
    }


    public int updateContact(long contactId, String firstName, String lastName, String phoneNumber, String phoneType, String email, String date, String dateLabel, String address, String notes, boolean isFavorite, int groupId) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(COLUMN_PHONE_TYPE, phoneType);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_DATE_LABEL, dateLabel);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_NOTES, notes);
        values.put(COLUMN_IS_FAVORITE, isFavorite ? 1 : 0);
        values.put(COLUMN_GROUP_ID, groupId);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(contactId)};

        try {
            return database.update(TABLE_CONTACTS, values, whereClause, whereArgs);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Handle the error
        }
    }


    public Contact getContactById(long contactId) {
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(contactId)};

        Cursor cursor = database.query(
                TABLE_CONTACTS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));
            String phoneType = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_TYPE));
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
            String dateLabel = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_LABEL));
            String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
            String notes = cursor.getString(cursor.getColumnIndex(COLUMN_NOTES));
            boolean isFavorite = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE)) == 1;
            int groupId = cursor.getInt(cursor.getColumnIndex(COLUMN_GROUP_ID));

            cursor.close();

            return new Contact(contactId, firstName, lastName, phoneNumber, phoneType, email, date, dateLabel, address, notes, isFavorite, groupId);
        } else {
            // Handle the case where the contact with the given ID was not found
            return null;
        }
    }


    public void clearGroupMembers(long groupId) {
        String whereClause = COLUMN_GROUP_ID + " = ?";
        String[] whereArgs = {String.valueOf(groupId)};

        try {
            database.delete(TABLE_CONTACT_GROUP_ASSOCIATION, whereClause, whereArgs);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error
        }
    }



    public List<Contact> getFrequentContacts() {
        List<Contact> frequentFavoriteContacts = new ArrayList<>();
        String[] projection = {TABLE_CONTACTS + "." + COLUMN_ID, "COUNT(*) AS call_count"};
        String groupBy = TABLE_CONTACTS + "." + COLUMN_ID;
        String orderBy = "call_count DESC";
        String limit = "5";

        // Join the TABLE_CALL_LOG with TABLE_CONTACTS on the contact ID
        String tables = TABLE_CALL_LOG + " INNER JOIN " + TABLE_CONTACTS + " ON " +
                TABLE_CALL_LOG + "." + COLUMN_CONTACT_ID + " = " +
                TABLE_CONTACTS + "." + COLUMN_ID;

        String selection = TABLE_CONTACTS + "." + COLUMN_IS_FAVORITE + " = 1"; // Add the WHERE clause

        Cursor cursor = database.query(
                tables,
                projection,
                selection, // Use the WHERE clause
                null,
                groupBy,
                null,
                orderBy,
                limit
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    long contactId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                    Contact contact = getContactDetails(contactId);
                    if (contact != null) {
                        frequentFavoriteContacts.add(contact);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return frequentFavoriteContacts;
    }

    public Contact getContactDetails(long contactId) {
        Cursor cursor = database.query(
                TABLE_CONTACTS,
                null,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(contactId)},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            Contact contact = new Contact(
                    contactId,
                    cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_TYPE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DATE_LABEL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NOTES)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE)) == 1,
                    cursor.getInt(cursor.getColumnIndex(COLUMN_GROUP_ID))
            );
            cursor.close();
            return contact;
        }

        return null;
    }



    //merge button funtionality , updating contacts with same phone number
    public int updateContactPhoneNumbers(long contactId, String newPhoneNumbers) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONE_NUMBER, newPhoneNumbers);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(contactId)};

        try {
            return database.update(TABLE_CONTACTS, values, whereClause, whereArgs);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error
            return 0;
        }
    }

    //merge button functionality,deleting the duplicate contact when merge successful
    public void deleteContact(long contactId) {
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(contactId)};

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            int rowsDeleted = db.delete(TABLE_CONTACTS, whereClause, whereArgs);
            if (rowsDeleted > 0) {
                Toast customToast = new Toast(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View customToastView = inflater.inflate(R.layout.delete_custom_toast, null);
                customToast.setView(customToastView);
                customToast.setDuration(Toast.LENGTH_SHORT);
                customToast.show();
            } else {
                // Contact with the given ID was not found
                Toast.makeText(context, "Contact not found.", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error
        }
        finally {
            db.close();
        }
    }

    //NEW,Update the is_deleted column to 1 for a specific contact
    public void updateIsDeleted(long contactId, int currentIsDeleted) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            if (currentIsDeleted == 0) {
                values.put(COLUMN_IS_DELETED, 1);
                String whereClause = COLUMN_ID + " = ?";
                String[] whereArgs = {String.valueOf(contactId)};
                db.update(TABLE_CONTACTS, values, whereClause, whereArgs);
            } else if (currentIsDeleted == 1) {
                //values.put(COLUMN_IS_DELETED, 2);
                deleteContact(contactId);
            }
        }
        finally {
            db.close();

        }
    }

    //NEW, this method gives back the is_deleted value for specific contact_id
    public int getCurrentIsDeleted(long contactId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int currentIsDeleted = -1; // Initialize with an invalid value

        String[] columns = {COLUMN_IS_DELETED};
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(contactId)};

        Cursor cursor = db.query(TABLE_CONTACTS, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            currentIsDeleted = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_DELETED));
            cursor.close();
        }

        db.close();
        return currentIsDeleted;
    }

    //NEW,this method is called when trash is clicked in nav menu.
    public List<Contact> getDeletedContacts() {
        List<Contact> deletedContacts = new ArrayList<>();

        String[] columns = {COLUMN_ID, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_PHONE_NUMBER};
        String selection = COLUMN_IS_DELETED + " = ?";
        String[] selectionArgs = {"1"};

        Cursor cursor = database.query(
                TABLE_CONTACTS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        //ok

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                    String firstName  = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
                    String lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
                    String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));

                    Contact contact = new Contact(id, firstName, lastName, phoneNumber);
                    deletedContacts.add(contact);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return deletedContacts;
    }

    //NEW , Restore page code
    public int restoreContact(long contactId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_DELETED, 0); // Set is_deleted to 0 to mark the contact as not deleted

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(contactId)};

        try {
            return db.update(TABLE_CONTACTS, values, whereClause, whereArgs);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error
            return 0;
        } finally {
            db.close();
        }
    }





    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_CONTACTS);
            db.execSQL(CREATE_TABLE_GROUPS);
            db.execSQL(CREATE_TABLE_CONTACT_GROUP_ASSOCIATION);
            db.execSQL(CREATE_TABLE_CALL_LOG);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            if (oldVersion < 5) {
//                // Perform the necessary schema updates for version 5
//                db.execSQL("ALTER TABLE " + TABLE_CALL_LOG + " ADD COLUMN " + COLUMN_DATE_LABEL + " TEXT");
//            }
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALL_LOG);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT_GROUP_ASSOCIATION);
            onCreate(db);
        }
    }
}
