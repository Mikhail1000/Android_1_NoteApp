package com.example.android1noteapp;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Objects;


public class listNoteFragment extends Fragment {

    public static final String CURRENT_NOTE = "current_note";
    private int currentNote = 0;
    static CardSourceImp source;
    ArrayList<Setting> settings = new ArrayList<>();

    private static final String ARG_INDEX = "index";
    private static final String ARG_NOTES = "notes";

    private String mParam1;
    private String mParam2;

    static CardsAdapter adapter;

    public listNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null){
            currentNote = savedInstanceState.getInt(CURRENT_NOTE, 0);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);

        source = new CardSourceImp(requireContext());

        adapter = new CardsAdapter(requireActivity(), source);
        adapter.setClickListener((view1, position) -> {
            currentNote = position;
            showNote(position);
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(AppCompatResources.getDrawable(requireContext(), R.drawable.separator)));

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        initList(view);
        initButtons(view);


        if (isLandscape()){
            showLandNote(currentNote);
        }
    }



    private void initButtons(View view) {
        settings.add(new Setting("Ночная тема", "0"));
        settings.add(new Setting("Русский язык", "1"));
        settings.add(new Setting("От старых к новым", "0"));


        Button buttonSettings = view.findViewById(R.id.button_settings);
        buttonSettings.findViewById(R.id.button_settings).setOnClickListener(v -> {
            showSettings();
        });
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_NOTE, currentNote);
        super.onSaveInstanceState(outState);
    }

    private void initList(View view) {
        /*notes.add(new Notes("Заголовок 1", "Описание заметки 1", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 2", "Описание заметки 2", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 3", "Описание заметки 3", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 4", "Описание заметки 4", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 5", "Описание заметки 5", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 6", "Описание заметки 6", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 7", "Описание заметки 7", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 8", "Описание заметки 8", new GregorianCalendar()));
        notes.add(new Notes("Заголовок 9", "Описание заметки 9", new GregorianCalendar()));*/

        /*LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < notes.size(); i++) {
            TextView tv = new TextView(getContext());
            tv.setText(notes. get(i).getNameNote());
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int position = i;
            tv.setOnClickListener(v -> {
                currentNote = position;
                showNote(position);
            });
        }*/
    }

    private void showNote(int index) {
        if (isLandscape()){
            showLandNote(index);
        } else {
            showPortNote(index);
        }
    }

    private void showPortNote(int index) {
        NoteFragment noteFragment = NoteFragment.newInstance(index, source);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .add(R.id.fragment_container, noteFragment)
                .commit();
    }

    private void showLandNote(int index) {
        NoteFragment detail = NoteFragment.newInstance(index, source);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_note, detail)
                .commit();
    }

    private void showSettings() {
        showPortSettings();
    }

    private void showPortSettings() {
        SettingsFragment settingsFragment = SettingsFragment.newInstance(settings);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .add(R.id.fragment_container, settingsFragment)
                .commit();
    }

    /*@Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        requireActivity().getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id.fragment_container, new AboutAppFragment())
                    .commit();
            return true;
        } else if (id == R.id.action_search){
            return true;
        } else if (id == R.id.action_add_note){
            source.addNote(new Notes("Добавленная заметка", "Это заметка добавленная через меню", new GregorianCalendar()));
            adapter.notifyItemInserted(source.size() - 1);
            return true;
        } else if (id == R.id.action_clear_notes){
            source.clearNote();
            adapter.notify();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    /*@Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_context_delete_note){
            return true;
        } else if (item.getItemId() == R.id.action_context_update_note){
            adapter.getMenu_position();
            return true;
        }

        return super.onContextItemSelected(item);

    }*/
}