package com.finalproject.nakamaandroidapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrearPerfilActivity extends AppCompatActivity {

    Spinner spinnerJuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        spinnerJuegos = (Spinner) findViewById(R.id.spinner_Juegos);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.juegos, android.R.layout.simple_spinner_item);
        spinnerJuegos.setAdapter(adapter);

    }
}