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

    Spinner spinnerGames;
    Spinner spinnerRegion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        spinnerGames = (Spinner) findViewById(R.id.spinner_Juegos);
        spinnerRegion = (Spinner) findViewById(R.id.spinner_Region);

        ArrayAdapter<CharSequence> adapterRegion= ArrayAdapter.createFromResource(this, R.array.region, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterGames= ArrayAdapter.createFromResource(this, R.array.games, android.R.layout.simple_spinner_item);

        spinnerRegion.setAdapter(adapterRegion);
        spinnerGames.setAdapter(adapterGames);
    }
}