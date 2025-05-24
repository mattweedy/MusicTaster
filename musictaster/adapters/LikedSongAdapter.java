package com.example.musictaster.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musictaster.R;
import com.example.musictaster.activities.SongSelectorActivity;
import com.example.musictaster.database.DatabaseHelper;
import com.example.musictaster.models.SongModel;
import com.example.musictaster.models.UserSongModel;

import java.util.List;

public class LikedSongAdapter extends BaseAdapter {

    private int userID = 0;
    Context context;
    List<SongModel> songs;
    TextView tv_songName;
    TextView tv_songArtist;

    public LikedSongAdapter(Context context, List<SongModel> songs, int userID) {
        this.userID = userID;
        this.context = context;
        this.songs = songs;
    }

    public LikedSongAdapter(SongSelectorActivity context, List<SongModel> songs, int userID) {
        this.userID = userID;
        this.context = context;
        this.songs = songs;
    }

    @Override
    public int getCount() {
        return this.songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_profile_song_list_view, null);
        }

        tv_songName = (TextView) convertView.findViewById(R.id.tv_songName);
        tv_songArtist = (TextView) convertView.findViewById(R.id.tv_songArtist);

        tv_songName.setText(songs.get(position).getSongName());
        tv_songArtist.setText((songs.get(position)).getSongArtist());

        return convertView;
    }
}
