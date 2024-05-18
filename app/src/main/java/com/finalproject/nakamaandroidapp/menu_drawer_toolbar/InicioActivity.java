package com.finalproject.nakamaandroidapp.menu_drawer_toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class InicioActivity extends DrawerBaseActivity {
    ActivityInicioBinding activityInicioBinding;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                "Europa",
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
        rangos = new String[]{"nivel 2",
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

            }
        });


    }
    private void buscarMatches(){
        String valorSpinnerRegiones = spinner1.getSelectedItem().toString();
        String valorSpinnerJuegos = spinner2.getSelectedItem().toString();
        String valorSpinnerRango = spinner3.getSelectedItem().toString();

        if (valorSpinnerRegiones.isEmpty() || valorSpinnerJuegos.isEmpty()) {
            Toast.makeText(InicioActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a reference to the "usuarios" collection
        CollectionReference usuariosRef = db.collection("profiles");

        // Construct a query to find matching users
        Query query = usuariosRef
                .whereEqualTo("region", valorSpinnerRegiones)
                .whereEqualTo("favorite_game", valorSpinnerJuegos)
                .whereEqualTo("category", valorSpinnerRango);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Usuario> usuarios = new ArrayList<>();
                for (com.google.firebase.firestore.DocumentSnapshot document : task.getResult().getDocuments()) {
                    Usuario usuario = document.toObject(Usuario.class);
                    usuarios.add(usuario);
                }

                // Pass the list of matching users to the next activity
                Intent intent = new Intent(InicioActivity.this, MatchesActivity.class);
                intent.putParcelableArrayListExtra("usuarios", (ArrayList<Usuario>) usuarios);
                startActivity(intent);
            } else {
                Log.d("InicioActivity", "Error fetching matches: ", task.getException());
                Toast.makeText(InicioActivity.this, "Error al buscar coincidencias", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
