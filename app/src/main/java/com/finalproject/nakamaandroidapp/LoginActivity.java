package com.finalproject.nakamaandroidapp;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button botonRegistrarse;
    private Button botonIniciarSesion;
    private EditText inputCorreo;
    private EditText inputContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        botonRegistrarse = findViewById(R.id.boton_registrarse);
        botonIniciarSesion = findViewById(R.id.boton_empezar);
        inputCorreo = findViewById(R.id.input_region);
        inputContraseña = findViewById(R.id.input_rango);

        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        inputContraseña.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
}