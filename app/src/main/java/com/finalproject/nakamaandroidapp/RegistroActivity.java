package com.finalproject.nakamaandroidapp;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroActivity extends AppCompatActivity {

    private EditText inputCorreo;
    private EditText inputContraseña;
    private EditText inputNombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        inputCorreo = findViewById(R.id.input_correo);
        inputContraseña = findViewById(R.id.input_contraseña);
        inputNombreUsuario = findViewById(R.id.input_nombreusuario);

        inputContraseña.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
}
