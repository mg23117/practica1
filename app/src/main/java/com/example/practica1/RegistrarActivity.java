package com.example.practica1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrarActivity extends AppCompatActivity {

    private EditText editTextName, editTextUsername, editTextPassword, editTextConfirmPassword, editTextEmail;

    private Button buttonRegister, buttonCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);

        Toolbar toolbar = findViewById(R.id.toolbar_register);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nuevo usuario");

        editTextName = findViewById(R.id.edit_text_name);
        editTextUsername = findViewById(R.id.edit_text_register_username);
        editTextPassword = findViewById(R.id.edit_text_register_password);
        editTextConfirmPassword = findViewById(R.id.edit_text_register_confirm_password);
        editTextEmail = findViewById(R.id.edit_text_register_email);
        buttonRegister = findViewById(R.id.button_register);
        buttonCancel = findViewById(R.id.button_cancel_register);

        buttonRegister.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();

            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validación Usuario
            if (username.length() < 3) {
                Toast.makeText(this, "Usuario mínimo 3 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validación Password
            if (password.length() < 5 || !password.matches("[A-Za-z0-9]+")) {
                Toast.makeText(this, "Contraseña mínimo 5 caracteres alfanuméricos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Confirmación Password
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validación Email
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Formato de correo inválido", Toast.LENGTH_SHORT).show();
                return;
            }

                // Guardar en SharedPreferences
                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name", name);
                editor.putString("username", username);
                editor.putString("password", password);
                editor.putString("email", email);
                editor.apply();

                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();


                editTextName.setText("");
                editTextUsername.setText("");
                editTextPassword.setText("");
                editTextConfirmPassword.setText("");
                editTextEmail.setText("");
        });

        buttonCancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}