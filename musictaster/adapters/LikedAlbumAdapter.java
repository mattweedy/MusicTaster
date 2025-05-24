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
import com.example.musictaster.activities.AlbumSelectorActivity;
import com.example.musictaster.database.DatabaseHelper;
import com.example.musictaster.models.AlbumModel;
import com.example.musictaster.models.UserAlbumModel;

import java.util.List;

public class LikedAlbumAdapter extends BaseAdapter {

    private int userID;
    Context context;
    List<AlbumModel> albums;
    ImageView iv_albumImage;
    TextView tv_albumName;
    TextView tv_albumArtist;

    public LikedAlbumAdapter(Context context, List<AlbumModel> albums, int userID) {
        this.userID = userID;
        this.context = context;
        this.albums = albums;
    }

    public LikedAlbumAdapter(AlbumSelectorActivity context, List<AlbumModel> albums, int userID) {
        this.userID = userID;
        this.context = context;
        this.albums = albums;
    }

    @Override
    public int getCount() {
        return this.albums.size();
    }

    @Override
    public Object getItem(int position) {
        return albums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_profile_album_list_view, null);
        }

        iv_albumImage = (ImageView) convertView.findViewById(R.id.iv_albumImage);
        tv_albumName = (TextView) convertView.findViewById(R.id.tv_albumName);
        tv_albumArtist = (TextView) convertView.findViewById(R.id.tv_albumArtist);

        tv_albumName.setText(albums.get(position).getAlbumName());
        tv_albumArtist.setText((albums.get(position)).getAlbumArtist());

        return convertView;
    }
}
