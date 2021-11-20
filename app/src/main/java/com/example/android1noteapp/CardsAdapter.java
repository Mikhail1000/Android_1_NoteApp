package com.example.android1noteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private ArrayList<Notes> notesList;
    private OnCardClickListener clickListener;

    public void setClickListener(OnCardClickListener clickListener) {
        this.clickListener = clickListener;
    }

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

    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textView = itemView.findViewById(R.id.text_view_card_title);
        TextView textViewDescription = itemView.findViewById(R.id.text_view_card_description);

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Notes notes) {
            textView.setText(notes.getNameNote());
            textViewDescription.setText(notes.getDescriptionNote());
            itemView.setOnClickListener(v -> {
              if (clickListener != null) {
                  clickListener.onCardClick(v, getAdapterPosition());
              }
            });
        }

    }

    interface OnCardClickListener {
        void onCardClick(View view, int position);
    }


}
