package com.finalproject.nakamaandroidapp.menu_drawer_toolbar;

import android.content.Context;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.nakamaandroidapp.R;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchViewHolder> {
    @NonNull

    private List<Usuario> usuarios;
    private Context context;

    public MatchAdapter(List<Usuario> usuarios, Context context){
        this.usuarios = usuarios;
        this.context = context;

    }
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutView.setLayoutParams(lp);
        MatchViewHolder rcb = new MatchViewHolder((layoutView));
        return rcb;
    }


    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        //holder.mMatchName.setText(usuarios.get(position).getId());
        Usuario usuario = usuarios.get(position);
        holder.mMatchName.setText(usuario.getNombre());

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}
