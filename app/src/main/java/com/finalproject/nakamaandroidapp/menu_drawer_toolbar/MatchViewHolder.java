package com.finalproject.nakamaandroidapp.menu_drawer_toolbar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.nakamaandroidapp.R;

public class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView mMatchName;
    public MatchViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        mMatchName = (TextView) itemView.findViewById(R.id.matchNombre);
    }

    @Override
    public void onClick(View v) {

    }

}
