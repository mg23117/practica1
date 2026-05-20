package com.example.practica1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Conectamos el BottomNavigationView con el .xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Fragment que se mostrará al abrir home.
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new InicioFragment())
                .commit();

        //Eventos del menú inferior.
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;

            //Verificamos que el botón fue cliqueado.
            if(item.getItemId() == R.id.nav_inicio){
                selectedFragment = new InicioFragment();
            }else if(item.getItemId() == R.id.nav_productos){
                selectedFragment = new ProductosFragment();
            } else if (item.getItemId() == R.id.nav_perfil) {
                selectedFragment = new PerfilFragment();
            }else{
                selectedFragment = new InicioFragment();
            }

            //Reemplazar fragment actual.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();

            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.top_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_logout){

            Intent intent =
                    new Intent(HomeActivity.this,
                            LoginActivity.class);

            startActivity(intent);

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
