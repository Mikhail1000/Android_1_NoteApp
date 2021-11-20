package com.example.android1noteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private ArrayList<Notes> notesList;

    public CardsAdapter(ArrayList<Notes> notesList) {
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout, parent, false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        holder.bind(notesList.get(position));

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textView = itemView.findViewById(R.id.text_view_card);

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Notes notes) {
            textView.setText(notes.getNameNote());
        }

    }


}
