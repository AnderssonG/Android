package com.zxc.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    ListView userList;
    DatabaseHelper dbHelper;
    ArrayList<String> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userList = findViewById(R.id.userListView);
        dbHelper = new DatabaseHelper(this);

        cargarUsuarios();
    }

    private void cargarUsuarios() {
        usuarios = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email FROM " + DatabaseHelper.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(0);
                usuarios.add(email);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                usuarios
        );
        userList.setAdapter(adapter);
    }
}


