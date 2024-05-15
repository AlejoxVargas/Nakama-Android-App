package com.finalproject.nakamaandroidapp.menu_drawer_toolbar;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.nakamaandroidapp.R;

import java.util.List;

public class MatchesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mMatchesAdapter;
    private RecyclerView.LayoutManager mMatchesLManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        //recibo la lista de usuarios
        List<Usuario> usuarios = getIntent().getParcelableArrayListExtra("usuarios");

        mRecyclerView =(RecyclerView) findViewById(R.id.recView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        mMatchesLManager = new LinearLayoutManager(MatchesActivity.this);
        mRecyclerView.setLayoutManager(mMatchesLManager);
        mMatchesAdapter = new MatchAdapter(usuarios, MatchesActivity.this);
        mRecyclerView.setAdapter(mMatchesAdapter);

    }
}