package com.example.musictaster.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.musictaster.R;
import com.example.musictaster.adapters.LikedAlbumAdapter;
import com.example.musictaster.adapters.LikedSongAdapter;
import com.example.musictaster.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends Activity {
    // reference to layout
    TextView tv_songGenre;
    TextView tv_albumGenre;
    List<String> favourites = new ArrayList<String>();
    DatabaseHelper dbHelper;
    ListView lv_song;
    ListView lv_album;
    Button btn_home;
    int UID;

    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_profile);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            UID = data.getInt("userID");
        }

        tv_songGenre = (TextView) findViewById(R.id.tv_songGenre);
        tv_albumGenre = (TextView) findViewById(R.id.tv_albumGenre);
//        lv_song = (ListView) findViewById(android.R.id.list);
//        lv_album = (ListView) findViewById(android.R.id.list);
        lv_song = (ListView) findViewById(R.id.lv_song);
        lv_album = (ListView) findViewById(R.id.lv_album);
        btn_home = (Button) findViewById(R.id.btn_home);
        favourites = getFavSongAlbum(UID);

        tv_songGenre.setText(favourites.get(0));
        tv_albumGenre.setText(favourites.get(1));

//        updateUserFavGenre(UID);

        dbHelper = new DatabaseHelper(ProfileActivity.this);

        ShowSongsOnListView(dbHelper);
        ShowAlbumsOnListView(dbHelper);

        btn_home.setOnClickListener(v -> { returnToHome(); }
        );
    }

    // calculate favourite genre from the count of songs/albums
    public List<String> getFavSongAlbum(int userID) {
        List<String> favSongAlbum;

        DatabaseHelper dbHelper = new DatabaseHelper(ProfileActivity.this);
        favSongAlbum = dbHelper.getFavSongAlbum(userID);
        dbHelper.close();

        return favSongAlbum;
    }

    private void ShowSongsOnListView(DatabaseHelper dbHelper) {
        LikedSongAdapter likedSongAdapter = new LikedSongAdapter(ProfileActivity.this, dbHelper.getSongsFromLiked(UID), UID);
        lv_song.setAdapter(likedSongAdapter);
    }

    private void ShowAlbumsOnListView(DatabaseHelper dbHelper) {
        LikedAlbumAdapter likedAlbumAdapter = new LikedAlbumAdapter(ProfileActivity.this, dbHelper.getAlbumsFromLiked(UID), UID);
        lv_album.setAdapter(likedAlbumAdapter);
    }

    public void returnToHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

//    public void updateUserFavGenre(int userID) {
//        DatabaseHelper dbHelper = new DatabaseHelper(ProfileActivity.this);
//        dbHelper.updateUserFavGenre(UID, favourites.get(0).toString());
//
//        dbHelper.close();
//    }
}
