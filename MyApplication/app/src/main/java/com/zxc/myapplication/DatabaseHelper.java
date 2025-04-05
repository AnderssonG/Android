package com.zxc.myapplication;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "usuarios.db";
    public static final String TABLE_NAME = "usuarios";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");
        //db.execSQL("INSERT INTO " + TABLE_NAME + " (email, password) VALUES ('admin@mail.com', '123456')"); // Usuario por defecto
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public static boolean usuarioExiste(SQLiteDatabase db, String email) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email=?", new String[]{email});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

}

