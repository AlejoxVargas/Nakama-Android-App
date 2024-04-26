package com.finalproject.nakamaandroidapp;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText inputCorreo;
    private EditText inputContraseña;
    private EditText inputNombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        inputCorreo = findViewById(R.id.input_region);
        inputContraseña = findViewById(R.id.input_rango);
        inputNombreUsuario = findViewById(R.id.input_nombreusuario);

        inputContraseña.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
}
