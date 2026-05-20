package com.example.practica1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsernameLogin, editTextPasswordLogin;
    private Button loginAppBtn;
    private Button closeAppBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextUsernameLogin = findViewById(R.id.edit_text_username);
        editTextPasswordLogin = findViewById(R.id.edit_text_password);
        loginAppBtn = findViewById(R.id.log_in);
        closeAppBtn = findViewById(R.id.close);

        //Botón "Ingresar"
        loginAppBtn.setOnClickListener(v -> {
            onClickLoginBtn();
        });

        //Botón "Salir"
        closeAppBtn.setOnClickListener(v ->{
            finishAffinity();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        int itemId = menuItem.getItemId();

        // Redireccionar a la activity "Registrar"
        if(itemId == R.id.menu_register){
            Intent intent = new Intent(this, RegistrarActivity.class);
            startActivity(intent);
            return true;
        }

        // Salir desde el menú
        if(itemId == R.id.menu_exit){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }
    private void onClickLoginBtn(){
        String username = editTextUsernameLogin.getText().toString().trim();
        String password = editTextPasswordLogin.getText().toString().trim();

        //Validar campos vacíos
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        //Leer SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        String savedUsername = prefs.getString("username", "");
        String savedPassword = prefs.getString("password", "");

        //verificar usuario y contraseña
        if(username.equals(savedUsername) && password.equals(savedPassword)){
            Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_LONG).show();

            //Ir a HomeActivity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error de usuario y contraseña inválidos", Toast.LENGTH_SHORT).show();
        }
    }
}