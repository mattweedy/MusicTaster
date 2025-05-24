package com.example.musictaster.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musictaster.R;
import com.example.musictaster.adapters.SongAdapter;
import com.example.musictaster.database.DatabaseHelper;

import java.util.ArrayList;

public class SongSelectorActivity extends ListActivity {
    TextView tv_title;
    Button btn_next;
    Button btn_home;
//    ListView lv_songList;
    ImageView iv_songImage;
    LinearLayout ll_song;

    DatabaseHelper dbHelper;
    ArrayList<String> selectedGenres = new ArrayList<>();
    int UID;

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_song_selector);

        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_home = (Button) findViewById(R.id.btn_home);
//        lv_songList = (ListView) findViewById(R.id.list);

        dbHelper = new DatabaseHelper(SongSelectorActivity.this);

        // unpackaging bundle from GenreSelectorActivity to display only selected genres
        Bundle data = getIntent().getExtras();
        if (data != null) {
            selectedGenres = data.getStringArrayList("selected genres");
            UID = data.getInt("userID");
        }

        // will automatically display all genres/songs on loading of the page
        ShowSongsOnListView(dbHelper);

        btn_next.setOnClickListener(v -> {
            startAlbumActivity();
        });

        btn_home.setOnClickListener(v -> { returnToHome(); }
        );
    }

    public void returnToHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void startAlbumActivity() {
        Intent aIntent = new Intent(SongSelectorActivity.this, AlbumSelectorActivity.class);
        aIntent.putExtra("selected genres", selectedGenres);
        aIntent.putExtra("userID", UID);
        startActivity(aIntent);
    }

    private void ShowSongsOnListView(DatabaseHelper dbHelper) {
        SongAdapter songAdapter = new SongAdapter(SongSelectorActivity.this, dbHelper.getSongsFromGenre(selectedGenres), UID);
        setListAdapter(songAdapter);
    }
}
