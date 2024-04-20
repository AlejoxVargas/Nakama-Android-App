package com.finalproject.nakamaandroidapp;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        botonIniciarSesion = findViewById(R.id.boton_iniciarsesion);
        inputCorreo = findViewById(R.id.input_correo);
        inputContraseña = findViewById(R.id.input_contraseña);

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