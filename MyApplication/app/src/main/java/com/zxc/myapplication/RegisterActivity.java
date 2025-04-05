package com.zxc.myapplication;



import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button registerButton;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);
        emailInput = findViewById(R.id.registerEmail);
        passwordInput = findViewById(R.id.registerPassword);
        registerButton = findViewById(R.id.registerBtn);

        registerButton.setOnClickListener(view -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                if (registrarUsuario(email, password)) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    finish(); // Cierra esta pantalla y vuelve al login
                } else {
                    Toast.makeText(this, "Error al registrar. ¿El usuario ya existe?", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean registrarUsuario(String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Verificar si ya existe el usuario
        if (DatabaseHelper.usuarioExiste(db, email)) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        long result = db.insert(DatabaseHelper.TABLE_NAME, null, values);
        return result != -1;
    }


}
