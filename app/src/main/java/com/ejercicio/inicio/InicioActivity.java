package com.ejercicio.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.ejercicio.inicio.databinding.ActivityInicioBinding;
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
    Slider slider1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding= activityInicioBinding.inflate(getLayoutInflater());

        setContentView(activityInicioBinding.getRoot());

        boton1 = (Button) findViewById(R.id.inicio_boton);
        label1 = (TextView) findViewById(R.id.inicio_label1);
        label2 = (TextView) findViewById(R.id.inicio_label2);
        label3 = (TextView) findViewById(R.id.inicio_label3);
        label4 = (TextView) findViewById(R.id.inicio_label4);
        spinner1 = (Spinner) findViewById(R.id.inicio_spinner1);
        spinner2 = (Spinner) findViewById(R.id.inicio_spinner2);
        spinner3 = (Spinner) findViewById(R.id.inicio_spinner3);
        slider1 = (Slider) findViewById(R.id.inicio_slider);
    }
}