package com.example.musictaster.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.musictaster.R;
import com.example.musictaster.adapters.AlbumAdapter;
import com.example.musictaster.database.DatabaseHelper;

import java.util.ArrayList;

public class AlbumSelectorActivity extends Activity {
    TextView tv_title;
    Button btn_next;
    Button btn_home;
    ListView lv_albumList;
    ImageView iv_albumImage;

    DatabaseHelper dbHelper;
    ArrayList<String> selectedGenres = new ArrayList<>();
    int UID;

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_album_selector);

        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_home = (Button) findViewById(R.id.btn_home);
        lv_albumList = (ListView) findViewById(R.id.lv_albumList);

        dbHelper = new DatabaseHelper(AlbumSelectorActivity.this);

        // unpackaging bundle from GenreSelectorActivity to display only selected genres
        Bundle data = getIntent().getExtras();
        if (data != null) {
            selectedGenres = data.getStringArrayList("selected genres");
            UID = data.getInt("userID");
        }

        // will automatically display all genres/albums on loading of the page
        ShowAlbumsOnListView(dbHelper);

        btn_next.setOnClickListener(v -> {
            startProfileActivity();
        });
        
        btn_home.setOnClickListener(v -> {
            returnToHome();
        });
    }

    public void returnToHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void startProfileActivity() {
        Intent pIntent = new Intent(AlbumSelectorActivity.this, ProfileActivity.class);
        pIntent.putExtra("userID", UID);
        startActivity(pIntent);
    }

    private void ShowAlbumsOnListView(DatabaseHelper dbHelper) {
        AlbumAdapter albumAdapter = new AlbumAdapter(AlbumSelectorActivity.this, dbHelper.getAlbumsFromGenre(selectedGenres), UID);
        lv_albumList.setAdapter(albumAdapter);
    }
}
