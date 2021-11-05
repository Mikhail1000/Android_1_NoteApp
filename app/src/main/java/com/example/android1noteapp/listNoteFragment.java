package com.example.android1noteapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class listNoteFragment extends Fragment {

    ArrayList<Notes> notes = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INDEX = "index";
    private static final String ARG_NOTES = "notes";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public listNoteFragment() {
        // Required empty public constructor
    }

    /*public static listNoteFragment newInstance(int index, ArrayList<Notes> notes) {
        listNoteFragment fragment = new listNoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        args.putParcelableArrayList(ARG_NOTES, notes);
        fragment.setArguments(args);
        return fragment;
    }*/

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_INDEX);
            mParam2 = getArguments().getString(ARG_NOTES);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        notes.add(new Notes("Заголовок 1", "Описание заметки 1", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 2", "Описание заметки 2", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 3", "Описание заметки 3", new GregorianCalendar()));

        LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < notes.size(); i++) {
            TextView tv = new TextView(getContext());
            tv.setText(notes. get(i).getNameNote());
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int position = i;
            tv.setOnClickListener(v -> {
                showNote(position);
            });
        }
    }

    private void showNote(int index) {
        NoteFragment noteFragment = NoteFragment.newInstance(index, notes);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, noteFragment)
                .commit();
    }
}