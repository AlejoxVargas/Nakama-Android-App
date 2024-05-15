package com.finalproject.nakamaandroidapp.menu_drawer_toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.finalproject.nakamaandroidapp.R;
import com.finalproject.nakamaandroidapp.databinding.ActivityInicioBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InicioActivity extends DrawerBaseActivity {
    ActivityInicioBinding activityInicioBinding;
    private DatabaseReference rootDatabaseRef;
    Button boton1;
    TextView label1;
    TextView label2;
    TextView label3;
    TextView label4;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    EditText texto1;
    String[] regiones;
    String[] juegos;
    String[] rangos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding= ActivityInicioBinding.inflate(getLayoutInflater());

        setContentView(activityInicioBinding.getRoot());

        rootDatabaseRef = FirebaseDatabase.getInstance().getReference().child("nodobbdd");

        boton1 = (Button) findViewById(R.id.inicio_boton);
        label1 = (TextView) findViewById(R.id.inicio_label1);
        label2 = (TextView) findViewById(R.id.inicio_label2);
        label3 = (TextView) findViewById(R.id.inicio_label3);
        label4 = (TextView) findViewById(R.id.inicio_label4);
        spinner1 = (Spinner) findViewById(R.id.inicio_spinner1);
        spinner2 = (Spinner) findViewById(R.id.inicio_spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner_rango);
        texto1 = (EditText) findViewById(R.id.inicio_input_edad);
        regiones = new String[]{"Norteamérica",
                "Europa del Oeste",
                "Europa Nórdica y del Este",
                "Oceanía",
                "Rusia",
                "Turquía",
                "Brasil",
                "Latinoamérica Norte",
                "Latinoamérica Sur",
                "Japón"};
        juegos = new String[]{"Fortnite",
                "Rocket League",
                "Minecraft",
                "Valorant",
                "Brawl Stars"};
        rangos = new String[]{"Bajo",
                "Medio",
                "Alto",
                "Muy alto"};


        //metemos los datos de los spinner

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, regiones);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, juegos);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rangos);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        //Preparamos el codigo del boton

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buscarMatches();
                //cambio de actividad al recycleView (matchesActivity)
            }
        });


    }
    private void buscarMatches(){
        String valorSpinnerRegiones = spinner1.getSelectedItem().toString();
        String valorSpinnerJuegos = spinner2.getSelectedItem().toString();
        String valorSpinnerRango = spinner3.getSelectedItem().toString();
        String valorEditText = texto1.getText().toString().trim();

        if (valorSpinnerRegiones.isEmpty() || valorSpinnerJuegos.isEmpty() || valorSpinnerRango.isEmpty() || valorEditText.isEmpty()) {
            Toast.makeText(InicioActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Query query = rootDatabaseRef.orderByChild("region").equalTo(valorSpinnerRegiones)
                .orderByChild("juegos").equalTo(valorSpinnerJuegos)
                .orderByChild("rango").equalTo(valorSpinnerRango)
                .orderByChild("Edad").equalTo(Integer.parseInt(valorEditText));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Lista para almacenar objetos Usuario
                List<Usuario> usuarios = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Extrae los datos del usuario de cada nodo
                    String idUsuario = snapshot.getKey(); // Obtiene el ID del usuario (clave del nodo)
                    String nombreUsuario = snapshot.child("nombre").getValue(String.class); // Obtiene el nombre del usuario
                    Usuario usuario = new Usuario(idUsuario, nombreUsuario); // Crea un objeto Usuario
                    usuarios.add(usuario); // Agrega el usuario a la lista
                }

                //Pasa la lista de usuarios a la siguiente actividad
                Intent intent = new Intent(InicioActivity.this, MatchesActivity.class);
                intent.putParcelableArrayListExtra("usuarios", (ArrayList<Usuario>) usuarios);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Maneja el fallo de la consulta
                Toast.makeText(InicioActivity.this, "Error al buscar entradas: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}