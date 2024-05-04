package com.finalproject.nakamaandroidapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateProfileActivity extends AppCompatActivity {

    Spinner spinnerJuegos;
    Spinner spinnerRegion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        spinnerJuegos = (Spinner) findViewById(R.id.spinner_Juegos);
        spinnerRegion = (Spinner) findViewById(R.id.spinner_Region);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.juegos, android.R.layout.simple_spinner_item);
        spinnerJuegos.setAdapter(adapter);

    }
}