package com.example.android1noteapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditNoteFragment extends Fragment {

    private static final String ARG_TITLE_NOTE = "titleNote";
    private static final String ARG_DESCRIPTION_NOTE = "descriptionNote";
    private static final String ARG_MENU_POSITION = "menuPositionNote";

    // TODO: Rename and change types of parameters
    private String titleNote;
    private String descriptionNote;
    private int menuPosition;

    public EditNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param titleNote Parameter 1.
     * @param descriptionNote Parameter 2.
     * @param menuPosition Parameter 3.
     * @return A new instance of fragment EditNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditNoteFragment newInstance(String titleNote, String descriptionNote, int menuPosition) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE_NOTE, titleNote);
        args.putString(ARG_DESCRIPTION_NOTE, descriptionNote);
        args.putInt(ARG_MENU_POSITION, menuPosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            descriptionNote = getArguments().getString(ARG_DESCRIPTION_NOTE);
            titleNote = getArguments().getString(ARG_TITLE_NOTE);
            menuPosition = getArguments().getInt(ARG_MENU_POSITION);
        }
        if (savedInstanceState != null){
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editTitleNote = view.findViewById(R.id.editText_title_note);
        EditText editDescriptionNote = view.findViewById(R.id.editText_description_note);

        editTitleNote.setText(titleNote);
        editDescriptionNote.setText(descriptionNote);
    }
}