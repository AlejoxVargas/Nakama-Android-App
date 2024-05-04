package com.finalproject.nakamaandroidapp.menu_drawer_toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.finalproject.nakamaandroidapp.R;
import com.finalproject.nakamaandroidapp.databinding.ActivityInicioBinding;
import com.google.android.material.slider.Slider;

public class InicioActivity extends DrawerBaseActivity {
    ActivityInicioBinding activityInicioBinding;
    Button boton1;
    TextView label1;
    TextView label2;
    TextView label3;
    TextView label4;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding= ActivityInicioBinding.inflate(getLayoutInflater());

        setContentView(activityInicioBinding.getRoot());

        boton1 = (Button) findViewById(R.id.inicio_boton);
        label1 = (TextView) findViewById(R.id.inicio_label1);
        label2 = (TextView) findViewById(R.id.inicio_label2);
        label3 = (TextView) findViewById(R.id.inicio_label3);
        label4 = (TextView) findViewById(R.id.inicio_label4);
        spinner1 = (Spinner) findViewById(R.id.inicio_spinner1);
        spinner2 = (Spinner) findViewById(R.id.inicio_spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner_rango);

    }
}