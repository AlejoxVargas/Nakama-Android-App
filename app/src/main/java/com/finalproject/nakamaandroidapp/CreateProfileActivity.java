package com.finalproject.nakamaandroidapp;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.finalproject.nakamaandroidapp.menu_drawer_toolbar.InicioActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateProfileActivity extends AppCompatActivity {

    Spinner spinnerGames;
    Spinner spinnerRegion;
    TextView tvCategory;
    Button btnCreate;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        spinnerGames = (Spinner) findViewById(R.id.spinner_Juegos);
        spinnerRegion = (Spinner) findViewById(R.id.spinner_Region);
        tvCategory = (TextView) findViewById(R.id.input_rango);
        btnCreate = (Button) findViewById(R.id.boton_empezar);

        ArrayAdapter<CharSequence> adapterRegion= ArrayAdapter.createFromResource(this, R.array.region, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterGames= ArrayAdapter.createFromResource(this, R.array.games, android.R.layout.simple_spinner_item);

        spinnerRegion.setAdapter(adapterRegion);
        spinnerGames.setAdapter(adapterGames);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String region = spinnerRegion.getSelectedItem().toString();
                String favoriteGame = spinnerGames.getSelectedItem().toString();
                String category = tvCategory.getText().toString();
                createData(region, favoriteGame, category);
            }
        });
    }

    public void createData(String region, String favoriteGame, String category) {
        Map<String, Object> subscription = new HashMap<>();
        subscription.put("region", region);
        subscription.put("favorite_game", favoriteGame);
        subscription.put("category", category);

        db.collection("profiles")
                .add(subscription).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Intent intent = new Intent(CreateProfileActivity.this, InicioActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(CreateProfileActivity.this, "error at created ", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}