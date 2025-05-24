package com.example.musictaster.activities;

import java.util.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musictaster.R;
import com.example.musictaster.database.DatabaseHelper;

import java.util.ArrayList;

public class GenreSelectorActivity extends Activity {
    TextView tv_title;
    Button btn_next;
    Button btn_home;
    CheckBox cb_pop;
    CheckBox cb_rap;
    CheckBox cb_techno;
    CheckBox cb_indie;
    CheckBox cb_rock;

    DatabaseHelper dbHelper;
    ArrayList<String> selectedGenres;
    public int UID;

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_genre_selector);

        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_home = (Button) findViewById(R.id.btn_home);
        cb_pop = (CheckBox) findViewById(R.id.cb_pop);
        cb_rap = (CheckBox) findViewById(R.id.cb_rap);
        cb_techno = (CheckBox) findViewById(R.id.cb_techno);
        cb_indie = (CheckBox) findViewById(R.id.cb_indie);
        cb_rock = (CheckBox) findViewById(R.id.cb_rock);
        selectedGenres = new ArrayList<String>();

        dbHelper = new DatabaseHelper(GenreSelectorActivity.this);

        // unpackaging bundle to store userID
        Bundle data = getIntent().getExtras();
        if (data != null) { UID = data.getInt("userID"); }

        cb_pop.setOnClickListener(v -> {
            addGenreToList(cb_pop);
        });

        cb_rap.setOnClickListener(v -> {
            addGenreToList(cb_rap);
        });

        cb_techno.setOnClickListener(v -> {
            addGenreToList(cb_techno);
        });

        cb_indie.setOnClickListener(v -> {
            addGenreToList(cb_indie);
        });

        cb_rock.setOnClickListener(v -> {
            addGenreToList(cb_rock);
        });

        btn_next.setOnClickListener(
                (View.OnClickListener) v -> {
                    if (selectedGenres.size() == 0) {
                        Toast.makeText(this, "ERROR: Select at least one genre", Toast.LENGTH_SHORT).show();
                    } else{
                        startSongActivity();
                    }
                }
        );

        btn_home.setOnClickListener(v -> finish());
    }

    public void startSongActivity() {
        Intent sIntent = new Intent(GenreSelectorActivity.this, SongSelectorActivity.class);
        if (selectedGenres.size() != 0) {
            sIntent.putExtra("selected genres", selectedGenres);
            sIntent.putExtra("userID", UID);
        }
        startActivity(sIntent);
    }

    // method to add and remove selected genres to list
    public void addGenreToList(CheckBox cb) {
        if (cb.isChecked()) {
            selectedGenres.add(cb.getText().toString());

            String[] answer = Arrays.copyOf(selectedGenres.toArray(), selectedGenres.size(), String[].class);
            String toastString = "added genre. genres: " + Arrays.toString(answer);

            Toast.makeText(this, toastString, Toast.LENGTH_SHORT).show();
        }

        if (!cb.isChecked() && selectedGenres.contains(cb.getText().toString())) {
            selectedGenres.remove(cb.getText().toString());

            String[] answer = Arrays.copyOf(selectedGenres.toArray(), selectedGenres.size(), String[].class);
            String toastString = "removed genre. genres: " + Arrays.toString(answer);

            Toast.makeText(this, toastString, Toast.LENGTH_SHORT).show();
        }
    }
}
