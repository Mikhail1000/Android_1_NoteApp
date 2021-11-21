package com.example.android1noteapp;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private final Activity activity;
    private CardSourceImp source;
    private OnCardClickListener clickListener;
    int menu_position = -1;

    public int getMenu_position() {
        return menu_position;
    }

    public void setClickListener(OnCardClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public CardsAdapter(Activity activity, CardSourceImp notesList) {
        this.activity = activity;
        this.source = notesList;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout, parent, false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(source.getNote(position));
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textView = itemView.findViewById(R.id.text_view_card_title);
        TextView textViewDescription = itemView.findViewById(R.id.text_view_card_description);

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);


            activity.registerForContextMenu(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        void bind(Notes notes) {
            textView.setText(notes.getNameNote());
            textViewDescription.setText(notes.getDescriptionNote());
            textView.setOnClickListener(v -> {
              if (clickListener != null) {
                  clickListener.onCardClick(v, getAdapterPosition());
              }
            });
            //textViewDescription.setLongClickable(true);
            textViewDescription.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    menu_position = getLayoutPosition();

                    textViewDescription.showContextMenu(10,10);
                    return false;
                }
            });
            /*textView.setOnClickListener(v -> {
              if (clickListener != null) {
                  menu_position = getLayoutPosition();

                  textViewDescription.showContextMenu(10,10);
              }
            });*/

        }

    }

    interface OnCardClickListener {
        void onCardClick(View view, int position);
    }


}
