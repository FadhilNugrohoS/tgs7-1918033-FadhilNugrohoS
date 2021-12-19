package com.example.restapi;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_vapestore";
    private static final String tb_barang = "tb_barang";
    private static final String tb_barang_id = "id";
    private static final String tb_barang_nama = "nama";
    private static final String tb_barang_harga = "harga";
    private static final String CREATE_TABLE_BARANG = "CREATE TABLE " +
            tb_barang +"("
            + tb_barang_id + " INTEGER PRIMARY KEY ,"
            + tb_barang_nama + " TEXT ,"
            + tb_barang_harga + " TEXT " + ")";
    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BARANG);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
    public void CreateBarang(Barang data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_barang_id, data.get_id());
        values.put(tb_barang_nama, data.get_nama());
        values.put(tb_barang_harga, data.get_harga());
        db.insert(tb_barang, null, values);
        db.close();
    }
    public List<Barang> ReadBarang() {
        List<Barang> listMhs = new ArrayList<Barang>();
        String selectQuery = "SELECT * FROM " + tb_barang;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Barang data = new Barang();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_harga(cursor.getString(2));
                listMhs.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMhs;}
    public int UpdateMahasiswa (Barang data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_barang_nama, data.get_nama());
        values.put(tb_barang_harga, data.get_harga());
        return db.update(tb_barang, values, tb_barang_id + " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void DeleteMahasiswa(Barang data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_barang,tb_barang_id+ " = ?", new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}
