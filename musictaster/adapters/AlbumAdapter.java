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

import com.example.musictaster.database.DatabaseHelper;
import com.example.musictaster.models.AlbumModel;
import com.example.musictaster.activities.AlbumSelectorActivity;
import com.example.musictaster.R;
import com.example.musictaster.models.AlbumModel;
import com.example.musictaster.models.SongModel;
import com.example.musictaster.models.UserAlbumModel;

import java.util.List;

public class AlbumAdapter extends BaseAdapter {

    private int userID;
    Context context;
    List<AlbumModel> albums;
    ImageView iv_albumImage;
    TextView tv_albumName;
    TextView tv_albumArtist;

    public AlbumAdapter(Context context, List<AlbumModel> albums, int userID) {
        this.userID = userID;
        this.context = context;
        this.albums = albums;

        createCheckedHolder();
    }

    public AlbumAdapter(AlbumSelectorActivity context, List<AlbumModel> albums, int userID) {
        this.userID = userID;
        this.context = context;
        this.albums = albums;

        createCheckedHolder();
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

    public static class ViewHolder {
        // ImageView albumImage;
        TextView tv_albumName;
        TextView tv_albumArtist;
        CheckBox cb_like;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("getview:", "position=" + position);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        final ViewHolder holder;

        // Reference: https://stackoverflow.com/questions/31339850/how-to-get-a-checkbox-value-using-custom-adapter-and-listview
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_custom_album_list_view, null);
            holder = new ViewHolder();

            holder.cb_like = (CheckBox) convertView.findViewById(R.id.cb_like);
            holder.tv_albumName = (TextView) convertView.findViewById(R.id.tv_albumName);
            holder.tv_albumArtist = (TextView) convertView.findViewById(R.id.tv_albumArtist);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (albums.size() <= 0) {
            convertView.setVisibility(View.GONE);
        } else {
            holder.cb_like.setChecked(checkedHolder[position]);

            holder.cb_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkedHolder[position] = isChecked;
                    DatabaseHelper dbHelper = new DatabaseHelper(context);
                    AlbumModel album = dbHelper.getAlbumByName(holder.tv_albumName.getText().toString());
                    // create new useralbummodel to store the user's added albums
                    UserAlbumModel userAlbumModel = new UserAlbumModel(userID, album.getAlbumID());

                    if (holder.cb_like.isChecked()) {
                        // insert into db
                        boolean success = dbHelper.addUserAlbumOne(userAlbumModel);
                        Toast.makeText(context, "Added Album To Liked", Toast.LENGTH_SHORT).show();
                    } else {
                        // delete from db
                        boolean success = dbHelper.deleteUserAlbumOne(userAlbumModel);
                        Toast.makeText(context, "Removed Album From Liked", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        // end reference

        iv_albumImage = (ImageView) convertView.findViewById(R.id.iv_albumImage);
        tv_albumName = (TextView) convertView.findViewById(R.id.tv_albumName);
        tv_albumArtist = (TextView) convertView.findViewById(R.id.tv_albumArtist);

        tv_albumName.setText(albums.get(position).getAlbumName());
        tv_albumArtist.setText((albums.get(position)).getAlbumArtist());

        return convertView;
    }
    public boolean[] checkedHolder;

    private void createCheckedHolder() {
        checkedHolder = new boolean[getCount()];
    }
}
