package com.finalproject.nakamaandroidapp.menu_drawer_toolbar;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.finalproject.nakamaandroidapp.LoginActivity;
import com.finalproject.nakamaandroidapp.R;
import com.finalproject.nakamaandroidapp.homePrueba;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view){
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container=drawerLayout.findViewById(R.id.activity_container);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        manejaracciondenavegacion(item.getItemId());
        return false;
    }

    private void manejaracciondenavegacion (int itemId){
        if (itemId== R.id.nav_faq){
            //Aquí iria la actividad o pagina de FAQ
            iniciarNuevaActividad(InicioActivity.class);
        } else if (itemId== R.id.nav_config) {
            //Aquí iría la actividad de config
            iniciarNuevaActividad(ConfiguracionActivity.class);
        } else if (itemId== R.id.nav_cont) {
            //Aquí iría la actividad de contacto
            iniciarNuevaActividad(InicioActivity.class);
        }
        else if (itemId== R.id.nav_logout) {
            //Aquí iría la actividad de logout
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(DrawerBaseActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void iniciarNuevaActividad (Class<?> destinoactividad){
        startActivity(new Intent(this, destinoactividad));
        overridePendingTransition(0,0);
    }
}