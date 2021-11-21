package com.example.android1noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        if (savedInstanceState == null){
            listNoteFragment listNoteFragment = new listNoteFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, listNoteFragment)
                    .commit();
        }
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.action_about) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("")
                        .add(R.id.fragment_container, new AboutAppFragment())
                        .commit();
                drawerLayout.closeDrawers();
                return true;
            } else if (id == R.id.action_search){
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id.fragment_container, new AboutAppFragment())
                    .commit();
            return true;
        } else if (id == R.id.action_search){
            return true;
        } else if (id == R.id.action_add_note){
            listNoteFragment.source.addNote(new Notes("Добавленная заметка", "Это заметка добавленная через меню", new GregorianCalendar()));
            listNoteFragment.adapter.notifyItemInserted(listNoteFragment.source.size() - 1);
            return true;
        } else if (id == R.id.action_clear_notes){
            int size = listNoteFragment.source.size();
            listNoteFragment.source.clearNote();
            listNoteFragment.adapter.notifyItemRangeRemoved(0, size);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = listNoteFragment.adapter.getMenu_position();

        if (item.getItemId() == R.id.action_context_delete_note){

            listNoteFragment.source.deleteNote(position);
            listNoteFragment.adapter.notifyItemRemoved(position);
            return true;
        } else if (item.getItemId() == R.id.action_context_update_note){
            //listNoteFragment.source.updateNote(listNoteFragment.adapter.getMenu_position(), );
            return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Вы вышли из приложения", Toast.LENGTH_SHORT).show();
    }
}