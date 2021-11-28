package com.example.android1noteapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.GregorianCalendar;

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
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editTitleNote = view.findViewById(R.id.editText_title_note);
        EditText editDescriptionNote = view.findViewById(R.id.editText_description_note);

        editTitleNote.setText(titleNote);
        editDescriptionNote.setText(descriptionNote);

        Button buttonAddNote = view.findViewById(R.id.button_add_or_modify);
        buttonAddNote.findViewById(R.id.button_add_or_modify).setOnClickListener(v -> {
            EditText editTitle = view.findViewById(R.id.editText_title_note);
            String textTitle = editTitle.getText().toString();
            EditText editDescription = view.findViewById(R.id.editText_description_note);
            String textDescription = editDescription.getText().toString();
            GregorianCalendar gregorianCalendar = new GregorianCalendar();

            if (menuPosition != -1) {
                Notes note = listNoteFragment.source.getNote(menuPosition);
                listNoteFragment.source.updateNote(menuPosition, new Notes(textTitle, textDescription, note.getDateCreate()));
                listNoteFragment.adapter.notifyItemChanged(menuPosition);
            } else {
                listNoteFragment.source.addNote(new Notes(textTitle, textDescription, new GregorianCalendar()));
                listNoteFragment.adapter.notifyItemInserted(listNoteFragment.source.size() - 1);
            }

            requireActivity().getSupportFragmentManager().popBackStack();

            Context context = requireContext();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
    }
}