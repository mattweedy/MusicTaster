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
import com.example.musictaster.activities.GenreSelectorActivity;
import com.example.musictaster.database.DatabaseHelper;
import com.example.musictaster.models.SongModel;
import com.example.musictaster.activities.SongSelectorActivity;
import com.example.musictaster.models.UserSongModel;

import java.util.List;
import java.util.UUID;

public class SongAdapter extends BaseAdapter {

    private int userID = 0;
    Context context;
    List<SongModel> songs;
     ImageView iv_songImage;
    TextView tv_songName;
    TextView tv_songArtist;

    public SongAdapter(Context context, List<SongModel> songs, int userID) {
        this.userID = userID;
        this.context = context;
        this.songs = songs;

        createCheckedHolder();
    }

    public SongAdapter(SongSelectorActivity context, List<SongModel> songs, int userID) {
        this.userID = userID;
        this.context = context;
        this.songs = songs;

        createCheckedHolder();
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

    public class ViewHolder {
        // ImageView iv_songImage;
        TextView tv_songName;
        TextView tv_songArtist;
        CheckBox cb_like;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        final ViewHolder holder;

        // Reference: https://stackoverflow.com/questions/31339850/how-to-get-a-checkbox-value-using-custom-adapter-and-listview
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_custom_song_list_view, null);
            holder = new ViewHolder();

            holder.cb_like = (CheckBox) convertView.findViewById(R.id.cb_like);
            holder.tv_songName = (TextView) convertView.findViewById(R.id.tv_songName);
            holder.tv_songArtist = (TextView) convertView.findViewById(R.id.tv_songArtist);
            holder.cb_like.setChecked(songs.get(position).selected);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (songs.size() <= 0) {
            convertView.setVisibility(View.GONE);
        } else {
            holder.cb_like.setChecked(checkedHolder[position]);

            holder.cb_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkedHolder[position] = isChecked;
                    DatabaseHelper dbHelper = new DatabaseHelper(context);
                    SongModel song = dbHelper.getSongByName(holder.tv_songName.getText().toString());
                    // create new usersongmodel to store the user's added songs
                    UserSongModel userSongModel = new UserSongModel(userID, song.getSongID());
                    song.selected = isChecked;
                    if (isChecked) {
                        song.selected = true;
                        // insert into db
                        boolean success = dbHelper.addUserSongOne(userSongModel);
                        Toast.makeText(context, "Added Song To Liked", Toast.LENGTH_SHORT).show();
                    } else {
                        // delete from db
                        boolean success = dbHelper.deleteUserSongOne(userSongModel);
                        Toast.makeText(context, "Removed Song From Liked", Toast.LENGTH_SHORT).show();
                    }
                    notifyDataSetChanged();
                }
            });
        }
        // end reference



        iv_songImage = (ImageView) convertView.findViewById(R.id.iv_songImage);
        tv_songName = (TextView) convertView.findViewById(R.id.tv_songName);
        tv_songArtist = (TextView) convertView.findViewById(R.id.tv_songArtist);

        tv_songName.setText(songs.get(position).getSongName());
        tv_songArtist.setText((songs.get(position)).getSongArtist());

        return convertView;
    }
    public boolean[] checkedHolder;

    private void createCheckedHolder() {
        checkedHolder = new boolean[getCount()];
    }


}
