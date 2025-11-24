package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "overprice_pc.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_REPAIR_REQUESTS = "repair_requests";
    private static final String TABLE_PURCHASE_REQUESTS = "purchase_requests";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_OWNER_NAME = "ownerName";
    private static final String COLUMN_PHONE_NUMBER = "phoneNumber";
    private static final String COLUMN_PC_MODEL = "pcModel";
    private static final String COLUMN_SERVICE_TYPE = "serviceType";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_COMPLETION_DATE = "completionDate";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REPAIR_TABLE = "CREATE TABLE " + TABLE_REPAIR_REQUESTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_OWNER_NAME + " TEXT,"
                + COLUMN_PHONE_NUMBER + " TEXT,"
                + COLUMN_PC_MODEL + " TEXT,"
                + COLUMN_SERVICE_TYPE + " TEXT,"
                + COLUMN_NOTE + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_TIME + " TEXT,"
                + COLUMN_STATUS + " TEXT,"
                + COLUMN_COMPLETION_DATE + " TEXT"
                + ")";
        db.execSQL(CREATE_REPAIR_TABLE);

        String CREATE_PURCHASE_TABLE = "CREATE TABLE " + TABLE_PURCHASE_REQUESTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_OWNER_NAME + " TEXT,"
                + COLUMN_PHONE_NUMBER + " TEXT,"
                + COLUMN_SERVICE_TYPE + " TEXT,"
                + COLUMN_NOTE + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_TIME + " TEXT,"
                + COLUMN_STATUS + " TEXT,"
                + COLUMN_COMPLETION_DATE + " TEXT"
                + ")";
        db.execSQL(CREATE_PURCHASE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPAIR_REQUESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASE_REQUESTS);
        onCreate(db);
    }

    // --- МЕТОДЫ ДЛЯ ЗАЯВОК НА РЕМОНТ ---

    // Добавление заявки на ремонт
    public long addRepairRequest(Request request) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_OWNER_NAME, request.getOwnerName());
        values.put(COLUMN_PHONE_NUMBER, request.getPhoneNumber());
        values.put(COLUMN_PC_MODEL, request.getPcModel());
        values.put(COLUMN_SERVICE_TYPE, request.getServiceType());
        values.put(COLUMN_NOTE, request.getNote());
        values.put(COLUMN_DATE, request.getDate());
        values.put(COLUMN_TIME, request.getTime());
        values.put(COLUMN_STATUS, request.getStatus());
        values.put(COLUMN_COMPLETION_DATE, request.getCompletionDate());

        long id = db.insert(TABLE_REPAIR_REQUESTS, null, values);
        db.close();
        return id;
    }

    // Получение всех заявок на ремонт
    public List<Request> getAllRepairRequests() {
        List<Request> requestList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_REPAIR_REQUESTS + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Request request = new Request();
                request.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                request.setOwnerName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OWNER_NAME)));
                request.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE_NUMBER)));
                request.setPcModel(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PC_MODEL)));
                request.setServiceType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SERVICE_TYPE)));
                request.setNote(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE)));
                request.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                request.setTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
                request.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS)));
                request.setCompletionDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPLETION_DATE)));

                requestList.add(request);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return requestList;
    }

    // Получение заявок на ремонт по статусу
    public List<Request> getRepairRequestsByStatus(String status) {
        List<Request> requestList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_REPAIR_REQUESTS +
                " WHERE " + COLUMN_STATUS + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{status});

        if (cursor.moveToFirst()) {
            do {
                Request request = new Request();
                request.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                request.setOwnerName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OWNER_NAME)));
                request.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE_NUMBER)));
                request.setPcModel(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PC_MODEL)));
                request.setServiceType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SERVICE_TYPE)));
                request.setNote(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE)));
                request.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                request.setTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
                request.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS)));
                request.setCompletionDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPLETION_DATE)));

                requestList.add(request);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return requestList;
    }

    // Обновление статуса заявки на ремонт
    public int updateRepairRequestStatus(int id, String status, String completionDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_STATUS, status);
        values.put(COLUMN_COMPLETION_DATE, completionDate);

        int result = db.update(TABLE_REPAIR_REQUESTS, values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return result;
    }

    // Обновление всех данных заявки на ремонт
    public int updateRepairRequest(int id, String ownerName, String phoneNumber,
                                   String pcModel, String serviceType, String note, String date, String time, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_OWNER_NAME, ownerName);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(COLUMN_PC_MODEL, pcModel);
        values.put(COLUMN_SERVICE_TYPE, serviceType);
        values.put(COLUMN_NOTE, note);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_STATUS, status);

        int result = db.update(TABLE_REPAIR_REQUESTS, values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return result;
    }

    // Удаление одной заявки на ремонт
    public void deleteRepairRequest(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REPAIR_REQUESTS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    // Очистка всей таблицы заявок на ремонт (БЫСТРЫЙ СБРОС)
    public void clearAllRepairRequests() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REPAIR_REQUESTS, null, null);
        db.close();
    }

    // Подсчёт заявок на ремонт по статусу
    public int countRepairRequestsByStatus(String status) {
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_REPAIR_REQUESTS +
                " WHERE " + COLUMN_STATUS + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, new String[]{status});

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }

    // Общее количество заявок на ремонт
    public int getTotalRepairRequests() {
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_REPAIR_REQUESTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }

    // --- МЕТОДЫ ДЛЯ ЗАЯВОК НА ПОКУПКУ ---

    public long addPurchaseRequest(Request request) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_OWNER_NAME, request.getOwnerName());
        values.put(COLUMN_PHONE_NUMBER, request.getPhoneNumber());
        values.put(COLUMN_SERVICE_TYPE, request.getServiceType());
        values.put(COLUMN_NOTE, request.getNote());
        values.put(COLUMN_DATE, request.getDate());
        values.put(COLUMN_TIME, request.getTime());
        values.put(COLUMN_STATUS, request.getStatus());
        values.put(COLUMN_COMPLETION_DATE, request.getCompletionDate());

        long id = db.insert(TABLE_PURCHASE_REQUESTS, null, values);
        db.close();
        return id;
    }

    public List<Request> getAllPurchaseRequests() {
        List<Request> requestList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PURCHASE_REQUESTS + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Request request = new Request();
                request.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                request.setOwnerName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OWNER_NAME)));
                request.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE_NUMBER)));
                request.setServiceType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SERVICE_TYPE)));
                request.setNote(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE)));
                request.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                request.setTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
                request.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS)));
                request.setCompletionDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPLETION_DATE)));

                requestList.add(request);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return requestList;
    }

    public int countPurchaseRequestsByStatus(String status) {
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_PURCHASE_REQUESTS +
                " WHERE " + COLUMN_STATUS + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, new String[]{status});

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }

    public int getTotalPurchaseRequests() {
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_PURCHASE_REQUESTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }

    public void deletePurchaseRequest(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PURCHASE_REQUESTS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void clearAllPurchaseRequests() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PURCHASE_REQUESTS, null, null);
        db.close();
    }

    public int updatePurchaseRequestStatus(int id, String status, String completionDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_STATUS, status);
        values.put(COLUMN_COMPLETION_DATE, completionDate);

        int result = db.update(TABLE_PURCHASE_REQUESTS, values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return result;
    }
}