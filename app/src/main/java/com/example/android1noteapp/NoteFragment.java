package com.example.android1noteapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteFragment extends Fragment {

    private static final String ARG_INDEX = "index";
    private static final String ARG_NOTES = "notes";
    private int index = -1;
    ArrayList<Notes> notes;

    public NoteFragment() {
        // Required empty public constructor
    }

    public static NoteFragment newInstance(int index, ArrayList<Notes> notes) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        args.putParcelableArrayList(ARG_NOTES, notes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
            notes = getArguments().getParcelableArrayList(ARG_NOTES);
        }
        if (savedInstanceState != null){
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
            notes = getArguments().getParcelableArrayList(ARG_NOTES);

            if (index != -1) {
                LinearLayout layoutView = (LinearLayout) view;
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(25, 20, 25, 20);
                Notes note = notes.get(index);
                TextView title = new TextView(getContext());
                title.setText(note.getNameNote());
                title.setTextSize(30);
                title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                title.setTypeface(Typeface.DEFAULT_BOLD);
                layoutView.addView(title);
                TextView description = new TextView(getContext());
                description.setText(note.getDescriptionNote());
                description.setTextSize(20);
                description.setBackgroundResource(R.color.white);
                layoutView.addView(description, layoutParams);
            }
        }
    }
}