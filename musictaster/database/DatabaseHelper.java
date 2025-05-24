package com.example.musictaster.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.musictaster.models.AlbumModel;
import com.example.musictaster.models.SongModel;
import com.example.musictaster.models.UserModel;
import com.example.musictaster.models.UserSongModel;
import com.example.musictaster.models.UserAlbumModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // database tables
    public static final String DATABASE_NAME = "musicTasterDB";

    // TABLE 0 - SONGS
    static final String SONGS_TABLE = "songs";
    static final String COLUMN_SONG_ID = "ID";
    static final String COLUMN_SONG_NAME = "songName";
    static final String COLUMN_SONG_ARTIST = "songArtist";
    static final String COLUMN_SONG_GENRE = "songGenre";

    private static final int DATABASE_VERSION = 1;

    // TABLE 1 - ALBUMS
    static final String ALBUMS_TABLE = "albums";
    static final String COLUMN_ALBUM_ID = "ID";
    static final String COLUMN_ALBUM_NAME = "albumName";
    static final String COLUMN_ALBUM_ARTIST = "albumArtist";
    static final String COLUMN_ALBUM_GENRE = "albumGenre";

    // TABLE 2 - USERS
    static final String USERS_TABLE = "users";
    static final String COLUMN_USER_ID = "ID";
    static final String COLUMN_USER_NAME = "userName";
    static final String COLUMN_USER_FAV_GENRE = "userFavGenre";

    // TABLE 3 - USERSONGS
    static final String USERSONGS_TABLE = "userSongs";
    static final String COLUMN_USERSONGS_USER_ID = "ID";
    static final String COLUMN_USERSONGS_SONG_ID = "songID";

    // TABLE 4 - USERALBUMS
    static final String USERALBUMS_TABLE = "userAlbums";
    static final String COLUMN_USERALBUMS_USER_ID = "ID";
    static final String COLUMN_USERALBUMS_ALBUM_ID = "albumID";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // called first time db is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        if(!this.isPopulated(db))
            this.populate(db);
        String createTableStatement0 = "CREATE TABLE IF NOT EXISTS " + SONGS_TABLE + " (" + COLUMN_SONG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SONG_NAME + " TEXT, " + COLUMN_SONG_ARTIST + " TEXT, " + COLUMN_SONG_GENRE + " TEXT)";
        String createTableStatement1 = "CREATE TABLE IF NOT EXISTS " + ALBUMS_TABLE + " (" + COLUMN_ALBUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ALBUM_NAME + " TEXT, " + COLUMN_ALBUM_ARTIST + " TEXT, " + COLUMN_ALBUM_GENRE + " TEXT)";
        String createTableStatement2 = "CREATE TABLE IF NOT EXISTS " + USERS_TABLE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_FAV_GENRE + " TEXT)";
        String createTableStatement3 = "CREATE TABLE IF NOT EXISTS " + USERSONGS_TABLE + " (" + COLUMN_USERSONGS_USER_ID + " INTEGER, " + COLUMN_USERSONGS_SONG_ID + " INTEGER, " + " FOREIGN KEY ("+COLUMN_USERSONGS_USER_ID+") REFERENCES "+USERS_TABLE+"("+COLUMN_USER_ID+"), " + " FOREIGN KEY ("+COLUMN_USERSONGS_SONG_ID+") REFERENCES "+SONGS_TABLE+"("+COLUMN_SONG_ID+"))";
        String createTableStatement4 = "CREATE TABLE IF NOT EXISTS " + USERALBUMS_TABLE + " (" + COLUMN_USERALBUMS_USER_ID + " INTEGER, " + COLUMN_USERALBUMS_ALBUM_ID + " INTEGER, " + " FOREIGN KEY ("+COLUMN_USERALBUMS_USER_ID+") REFERENCES "+USERS_TABLE+"("+COLUMN_USER_ID+"), " + " FOREIGN KEY ("+COLUMN_USERALBUMS_ALBUM_ID+") REFERENCES "+ALBUMS_TABLE+"("+COLUMN_ALBUM_ID+"))";

        db.execSQL(createTableStatement0);
        db.execSQL(createTableStatement1);
        db.execSQL(createTableStatement2);
        db.execSQL(createTableStatement3);
        db.execSQL(createTableStatement4);

    }
    public void populate(SQLiteDatabase db){
        String[] sqlCreate =
                {
                        "CREATE TABLE songs (ID INTEGER PRIMARY KEY AUTOINCREMENT, songName TEXT, songArtist TEXT, songGenre TEXT);",
                        "INSERT INTO songs VALUES(1,'Nothing Changed','Quavo, Takeoff','Rap');",
                        "INSERT INTO songs VALUES(2,'The Grey People','Hydra','Rap');",
                        "INSERT INTO songs VALUES(3,'Where I''m From','Digable Planets','Rap');",
                        "INSERT INTO songs VALUES(4,'Drive Slow','Kanye West','Rap');",
                        "INSERT INTO songs VALUES(5,'Walkin','Denzel Curry','Rap');",
                        "INSERT INTO songs VALUES(6,'G Walkin'' on Yo Coffin','Lil Boodang','Rap');",
                        "INSERT INTO songs VALUES(7,'family ties','Baby Keem, Kendrick Lamar','Rap');",
                        "INSERT INTO songs VALUES(8,'Electric Relaxtion','A Tribe Called Quest','Rap');",
                        "INSERT INTO songs VALUES(9,'OUT WEST','JACKBOYS','Rap');",
                        "INSERT INTO songs VALUES(10,'Cudi Smoove Spot','Isaiah Mostafa','Rap');",
                        "INSERT INTO songs VALUES(11,'Cantona','DJibouti','Techno');",
                        "INSERT INTO songs VALUES(12,'Pyramid','ALYSS','Techno');",
                        "INSERT INTO songs VALUES(13,'Bluff','yune pinku','Techno');",
                        "INSERT INTO songs VALUES(14,'Only Love Can Break Your Heart','Saint Etienne','Techno');",
                        "INSERT INTO songs VALUES(15,'You''re in My System','Kerri Chandler','Techno');",
                        "INSERT INTO songs VALUES(16,'Healing','COMPUTER DATA','Techno');",
                        "INSERT INTO songs VALUES(17,'Not my Boss!','Blue Hawaii','Techno');",
                        "INSERT INTO songs VALUES(18,'cul de sac club','sunflwr','Techno');",
                        "INSERT INTO songs VALUES(19,'Innerbloom','RUFUS DU SOL','Techno');",
                        "INSERT INTO songs VALUES(20,'Plasma','DR. GABBA','Techno');",
                        "INSERT INTO songs VALUES(21,'Fine Madeline','Plums','Indie');",
                        "INSERT INTO songs VALUES(22,'Ready To Let Go','Cage The Elephant','Indie');",
                        "INSERT INTO songs VALUES(23,'New Flesh','Current Joys','Indie');",
                        "INSERT INTO songs VALUES(24,'Archie, Marry Me','Alvvays','Indie');",
                        "INSERT INTO songs VALUES(25,'Electric Feel','MGMT','Indie');",
                        "INSERT INTO songs VALUES(26,'Notion','The Rare Occasions','Indie');",
                        "INSERT INTO songs VALUES(27,'Goodpain','Yoke Lore','Indie');",
                        "INSERT INTO songs VALUES(28,'Fall Down','Crumb','Indie');",
                        "INSERT INTO songs VALUES(29,'High','Slow Pulp','Indie');",
                        "INSERT INTO songs VALUES(30,'You Are The Right One','Sports','Indie');",
                        "INSERT INTO songs VALUES(31,'Bohemian Rhapsody','Queen','Rock');",
                        "INSERT INTO songs VALUES(32,'Stairway to Heaven','Led Zeppelin','Rock');",
                        "INSERT INTO songs VALUES(33,'Hotel California','Eagles','Rock');",
                        "INSERT INTO songs VALUES(34,'Comfortably Numb','Pink Floyd','Rock');",
                        "INSERT INTO songs VALUES(35,'Smells Like Teen Spirit','Nirvana','Rock');",
                        "INSERT INTO songs VALUES(36,'Tom Sawyer','Rush','Rock');",
                        "INSERT INTO songs VALUES(37,'La Grange','ZZ Top','Rock');",
                        "INSERT INTO songs VALUES(38,'Smoke on the Water','Deep Purple','Rock');",
                        "INSERT INTO songs VALUES(39,'Purple Haze','Jimi Hendrix','Rock');",
                        "INSERT INTO songs VALUES(40,'Sympathy for the Devil','The Rolling Stones','Rock');",
                        "INSERT INTO songs VALUES(41,'B.O.T.A','Eliza Rose','Pop');",
                        "INSERT INTO songs VALUES(42,'BREAK MY SOUL','Beyonce','Pop');",
                        "INSERT INTO songs VALUES(43,'Vegas','Doja Cat','Pop');",
                        "INSERT INTO songs VALUES(44,'About You','The 1975','Pop');",
                        "INSERT INTO songs VALUES(45,'Industry Baby','Lil Nas X, Jack Harlow','Pop');",
                        "INSERT INTO songs VALUES(46,'Static','Steve Lacy','Pop');",
                        "INSERT INTO songs VALUES(47,'Major Distribution','Drake','Pop');",
                        "INSERT INTO songs VALUES(48,'Just Wanna Rock','Lil Uzi Vert','Pop');",
                        "INSERT INTO songs VALUES(49,'Hold Me Closer','Elton John, Britney Spears','Pop');",
                        "INSERT INTO songs VALUES(50,'Celestial','Ed Sheeran','Pop');",
                        "CREATE TABLE albums (ID INTEGER PRIMARY KEY AUTOINCREMENT, albumName TEXT, albumArtist TEXT, albumGenre TEXT);",
                        "INSERT INTO albums VALUES(1,'Get Rich Or Die Tryin''','50 Cent','Rap');",
                        "INSERT INTO albums VALUES(2,'Finally Rich','Chief Keef','Rap');",
                        "INSERT INTO albums VALUES(3,'DAMN.','Kendrick Lamar','Rap');",
                        "INSERT INTO albums VALUES(4,'2001','Dr. Dre','Rap');",
                        "INSERT INTO albums VALUES(5,'Astro World','Travis Scott','Rap');",
                        "INSERT INTO albums VALUES(6,'The Infamous','Mobb Deep','Rap');",
                        "INSERT INTO albums VALUES(7,'Without Warning','21 Savage, Offset, Metro Boomin','Rap');",
                        "INSERT INTO albums VALUES(8,'All Eyez On Me','2Pac','Rap');",
                        "INSERT INTO albums VALUES(9,'Culture II','Migos','Rap');",
                        "INSERT INTO albums VALUES(10,'Dear Annie','Rejjie Snow','Rap');",
                        "INSERT INTO albums VALUES(11,'Burn Dem Bridges','Skin On Skin','Techno');",
                        "INSERT INTO albums VALUES(12,'Berlin','Techno House','Techno');",
                        "INSERT INTO albums VALUES(13,'Pump Up The Jam','Technotronic','Techno');",
                        "INSERT INTO albums VALUES(14,'Der Techno-Club','Jarow','Techno');",
                        "INSERT INTO albums VALUES(15,'Is Maith Liom Techno','An Criu Craiceailte','Techno');",
                        "INSERT INTO albums VALUES(16,'L''Amour Toujours','Gigi D''Agostino','Techno');",
                        "INSERT INTO albums VALUES(17,'Losing It','FISHER','Techno');",
                        "INSERT INTO albums VALUES(18,'I Want You (Forever)','Carl Cox, Josh Butler','Techno');",
                        "INSERT INTO albums VALUES(19,'TURN ON THE LIGHTS','BASSTON','Techno');",
                        "INSERT INTO albums VALUES(20,'Techno Pop','Kraftwerk','Techno');",
                        "INSERT INTO albums VALUES(21,'Gawk','Vundabar','Indie');",
                        "INSERT INTO albums VALUES(22,'Tigersapp','Szymon','Indie');",
                        "INSERT INTO albums VALUES(23,'Fuzzybrain','Dayglow','Indie');",
                        "INSERT INTO albums VALUES(24,'Saltwater','Geowulf','Indie');",
                        "INSERT INTO albums VALUES(25,'Fade','Launder','Indie');",
                        "INSERT INTO albums VALUES(26,'Social Cues','Cage The Elephant','Indie');",
                        "INSERT INTO albums VALUES(27,'Naked All The Time','Sports','Indie');",
                        "INSERT INTO albums VALUES(28,'Hello, Anxiety','Phum Viphurit','Indie');",
                        "INSERT INTO albums VALUES(29,'Buds','Surf Curse','Indie');",
                        "INSERT INTO albums VALUES(30,'Spring EP','Wallows','Indie');",
                        "INSERT INTO albums VALUES(31,'1989','Taylor Swift','Pop');",
                        "INSERT INTO albums VALUES(32,'21','Adele','Pop');",
                        "INSERT INTO albums VALUES(33,'x','Ed Sheeran','Pop');",
                        "INSERT INTO albums VALUES(34,'In The Lonely Hour','Sam Smith','Pop');",
                        "INSERT INTO albums VALUES(35,'thank u, next','Ariana Grande','Pop');",
                        "INSERT INTO albums VALUES(36,'Marry Me','Olly Murs','Pop');",
                        "INSERT INTO albums VALUES(37,'Sonder','Dermot Kennedy','Pop');",
                        "INSERT INTO albums VALUES(38,'CRASH','Charli XCX','Pop');",
                        "INSERT INTO albums VALUES(39,'Give Me The Future','Bastille','Pop');",
                        "INSERT INTO albums VALUES(40,'Dawn FM','The Weeknd','Pop');",
                        "INSERT INTO albums VALUES(41,'The Dark Side of the Moon','Pink Floyd','Rock');",
                        "INSERT INTO albums VALUES(42,'Nevermind','Nirvana','Rock');",
                        "INSERT INTO albums VALUES(43,'Rumours','Fleetwood Mac','Rock');",
                        "INSERT INTO albums VALUES(44,'Ten','Pearl Jam','Rock');",
                        "INSERT INTO albums VALUES(45,'Highway to Hell','AC/DC','Rock');",
                        "INSERT INTO albums VALUES(46,'Funeral','Arcade Fire','Rock');",
                        "INSERT INTO albums VALUES(47,'Van Halen','Van Halen','Rock');",
                        "INSERT INTO albums VALUES(48,'Led Zeppelin II','Led Zeppelin','Rock');",
                        "INSERT INTO albums VALUES(49,'Revolver','The Beatles','Rock');",
                        "INSERT INTO albums VALUES(50,'Moving Pictures','Rush','Rock');",
                        "CREATE TABLE users (ID INTEGER PRIMARY KEY AUTOINCREMENT, userName TEXT, userFavGenre TEXT);",
                        "CREATE TABLE userSongs (ID INTEGER, songID INTEGER,  FOREIGN KEY (ID) REFERENCES users(ID),  FOREIGN KEY (songID) REFERENCES songs(ID));",
                        "CREATE TABLE userAlbums (ID INTEGER, albumID INTEGER,  FOREIGN KEY (ID) REFERENCES users(ID),  FOREIGN KEY (albumID) REFERENCES albums(ID));",
                        "DELETE FROM sqlite_sequence;",
                        "INSERT INTO sqlite_sequence VALUES('songs',50);",
                        "INSERT INTO sqlite_sequence VALUES('albums',50);",
                        "INSERT INTO sqlite_sequence VALUES('users',0);",
                        "CREATE VIEW view_favSong AS select userSongs.ID, userSongs.songid, songs.songGenre FROM userSongs JOIN songs ON userSongs.songid = songs.ID;",
                        "CREATE VIEW view_favAlbum AS select userAlbums.ID, userAlbums.albumid, albums.albumGenre FROM userAlbums JOIN albums ON userAlbums.albumid = albums.ID;",
        "CREATE VIEW view_fav AS SELECT userSongs.ID, userSongs.songID, songs.ID FROM userSongs JOIN songs ON userSongs.songID = songs.ID;"
                };
        for(String sqlStatement : sqlCreate)
            db.execSQL(sqlStatement);
    }
    public boolean isPopulated(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='songs';", null);
        if (cursor.moveToFirst()){
            return cursor.getInt(0) == 1;
        }
        else
            return false;
    }
    // called if db version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // ADDS
    public boolean addSongOne(SongModel songModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SONG_NAME, songModel.getSongName());
        cv.put(COLUMN_SONG_ARTIST, songModel.getSongArtist());
        cv.put(COLUMN_SONG_GENRE, songModel.getSongGenre());

        // Reference: https://www.youtube.com/watch?v=312RhjfetP8&list=LL | Helped with setting up database
        long insert = db.insert(SONGS_TABLE, null, cv);
        if (insert == -1) { return false; }
        // Reference Complete

        return true;
    }

    public boolean addAlbumOne(AlbumModel albumModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ALBUM_NAME, albumModel.getAlbumName());
        cv.put(COLUMN_ALBUM_ARTIST, albumModel.getAlbumArtist());
        cv.put(COLUMN_ALBUM_GENRE, albumModel.getAlbumGenre());

        long insert = db.insert(ALBUMS_TABLE, null, cv);
        if (insert == -1) { return false; }

        return true;
    }

    public boolean addUserOne(UserModel userModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, userModel.getUserName());
        cv.put(COLUMN_USER_FAV_GENRE, "");

        long insert = db.insert(USERS_TABLE, null, cv);
        if (insert == -1) { return false; }

        return true;
    }

    public boolean addUserSongOne(UserSongModel userSongModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERSONGS_USER_ID, userSongModel.getID());
        cv.put(COLUMN_USERSONGS_SONG_ID, userSongModel.getSongID());

        long insert = db.insert(USERSONGS_TABLE, null, cv);
        if (insert == -1) { return false; }

        return true;
    }

    public boolean addUserAlbumOne(UserAlbumModel userAlbumModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERALBUMS_USER_ID, userAlbumModel.getID());
        cv.put(COLUMN_USERALBUMS_ALBUM_ID, userAlbumModel.getAlbumID());

        long insert = db.insert(USERALBUMS_TABLE, null, cv);
        if (insert == -1) { return false; }

        return true;
    }

    // DELETES
    public boolean deleteOne(SongModel songModel) {
        // find song in db, if found, delete and return true
        // if not found, return false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + SONGS_TABLE + " WHERE " + COLUMN_SONG_ID + " = " + songModel.getSongID();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean deleteUserSongOne(UserSongModel userSongModel) {
        // find song in db, if found, delete and return true
        // if not found, return false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USERSONGS_TABLE + " WHERE " + COLUMN_USERSONGS_SONG_ID + " = " + userSongModel.getSongID();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean deleteUserAlbumOne(UserAlbumModel userAlbumModel) {
        // find song in db, if found, delete and return true
        // if not found, return false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USERALBUMS_TABLE + " WHERE " + COLUMN_USERALBUMS_ALBUM_ID + " = " + userAlbumModel.getAlbumID();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    // EXISTS
    public boolean userExists(UserModel userModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT " + COLUMN_USER_NAME + " FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = \"" + userModel.getUserName() + "\"";

        Cursor cursor = db.rawQuery(queryString, null);
        // if the query returns nothing return false (user does not exist and let it be added)
        if (cursor.getCount() == 0) { return false; }

        // otherwise return true (don't add user to db)
        return true;
    }

    // GET ALL
    public List<SongModel> getAllSongs() {
        List<SongModel> returnSongList = new ArrayList<>();

        // get data from db
        String queryString = "SELECT * FROM " + SONGS_TABLE;
        returnSongList = populateSongModel(queryString);

        return returnSongList;
    }

    public int getAllUsersReturnLastID() {
        List<UserModel> returnUserList = new ArrayList<>();
        int lastID;

        // get data from db
        String queryString = "SELECT * FROM " + USERS_TABLE;
        returnUserList = populateUserModel(queryString);

        if (returnUserList.size() != 0){
            lastID = returnUserList.get(returnUserList.size()-1).getUserID();
        } else {
            lastID = 0;
        }

        return lastID;
    }

    // SPECIFIC GETS
    public List<SongModel> getSongsFromGenre(ArrayList<String> genres) {
        List<SongModel> returnSongList = new ArrayList<>();

        String baseString = "SELECT * FROM " + SONGS_TABLE + " WHERE " + COLUMN_SONG_GENRE + " IN ('";
        String queryString = new String();
        String concatString = new String();

        // loop to find num items in list, and find names to append to queryString
        for (int i = 0; i < genres.size(); i++) {
            if (i == (genres.size()-1)) {
                concatString = genres.get(i) + "');";
            } else{
                concatString = genres.get(i) + "', '";
            }
            baseString = baseString.concat(concatString);
        }

        queryString = baseString;
        returnSongList = populateSongModel(queryString);

        return returnSongList;
    }

    public SongModel getSongByName(String name) {
        SongModel returnSong = new SongModel();

        String queryString = "SELECT * FROM " + SONGS_TABLE + " WHERE " + COLUMN_SONG_NAME + " = \"" + name + "\"";

        returnSong = populateOneSongModel(queryString);

        return returnSong;
    }

    public AlbumModel getAlbumByName(String name) {
        AlbumModel returnAlbum = new AlbumModel();

        String queryString = "SELECT * FROM " + ALBUMS_TABLE + " WHERE " + COLUMN_ALBUM_NAME + " = \"" + name + "\"";

        returnAlbum = populateOneAlbumModel(queryString);

        return returnAlbum;
    }

    public int getUserIDByName(String name) {
        List<UserModel> returnUser = new ArrayList<UserModel>();
        int returnID;

        String queryString = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = \"" + name + "\"";

        returnUser = populateUserModel(queryString);
        returnID = returnUser.get(0).getUserID();

        return returnID;
    }

    public List<AlbumModel> getAlbumsFromGenre(ArrayList<String> genres) {
        List<AlbumModel> returnAlbumList = new ArrayList<>();

        String baseString = "SELECT * FROM " + ALBUMS_TABLE + " WHERE " + COLUMN_ALBUM_GENRE + " IN ('";
        String queryString = new String();
        String concatString = new String();

        // loop to find num items in list, and find names to append to queryString
        for (int i = 0; i < genres.size(); i++) {
            if (i == (genres.size()-1)) {
                concatString = genres.get(i) + "');";
            } else{
                concatString = genres.get(i) + "', '";
            }
            baseString = baseString.concat(concatString);
        }

        queryString = baseString;
        returnAlbumList = populateAlbumModel(queryString);

        return returnAlbumList;
    }

    public List<String> getFavSongAlbum(int userID) {
//        String createViewFave = "CREATE VIEW IF NOT EXISTS view_fav AS SELECT * " +
//                " FROM " + USERSONGS_TABLE +
//                " JOIN " + SONGS_TABLE +
//                " ON " + USERSONGS_TABLE + "." + COLUMN_USERSONGS_SONG_ID + " = " + SONGS_TABLE + "." + COLUMN_SONG_ID + ";";
        List<String> returnFavSongAlbum = new ArrayList<String>();

        String songQueryString = " SELECT " + COLUMN_SONG_GENRE + " FROM view_favSong WHERE view_favSong." + COLUMN_USERSONGS_USER_ID + " = " + userID +
                " GROUP BY " + COLUMN_SONG_GENRE + " ORDER BY COUNT(*) DESC LIMIT 1;";

        String albumQueryString = " SELECT " + COLUMN_ALBUM_GENRE + " FROM view_favAlbum WHERE view_favAlbum." + COLUMN_USERALBUMS_USER_ID + " = " + userID +
                " GROUP BY " + COLUMN_ALBUM_GENRE + " ORDER BY COUNT(*) DESC LIMIT 1;";

        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL(createViewFave);

        Cursor c1 = db.rawQuery(songQueryString, null);
        c1.moveToFirst();
//        songResult = c1.getString(0);
        String songResult = c1.getString(0);
        c1.close();

        Cursor c2 = db.rawQuery(albumQueryString, null);
        c2.moveToFirst();
        String albumResult = c2.getString(0);
        c2.close();

        returnFavSongAlbum.add(songResult);
        returnFavSongAlbum.add(albumResult);

        return returnFavSongAlbum;
    }

    public List<SongModel> getSongsFromLiked(int UID) {
        List<SongModel> returnSongList = new ArrayList<>();

//        String baseString = "SELECT * FROM usersongs right join songs on usersongs.songid=songs.id where usersongs.id = userid;";
//        String queryString = "SELECT * FROM " + USERSONGS_TABLE + " RIGHT JOIN " + SONGS_TABLE + " ON " + USERSONGS_TABLE + "." + COLUMN_USERSONGS_SONG_ID + " = " + SONGS_TABLE + "." + COLUMN_SONG_ID + " WHERE " + USERSONGS_TABLE + "." + COLUMN_USERSONGS_USER_ID + " = " + UID;
        String queryString = "SELECT * FROM " + SONGS_TABLE + " LEFT JOIN " + USERSONGS_TABLE + " ON " + SONGS_TABLE + "." + COLUMN_SONG_ID + " = " + USERSONGS_TABLE + "." + COLUMN_USERSONGS_SONG_ID + " WHERE " + USERSONGS_TABLE + "." + COLUMN_USERSONGS_USER_ID + " = " + UID;

        returnSongList = populateSongModel(queryString);

        return returnSongList;
    }

    public List<AlbumModel> getAlbumsFromLiked(int UID) {
        List<AlbumModel> returnAlbumList = new ArrayList<>();

//        String baseString = "SELECT * FROM useralbums right join albums on useralbums.albumid=albums.id where useralbums.id = userid;";
//        String queryString = "SELECT * FROM " + USERALBUMS_TABLE + " RIGHT JOIN " + ALBUMS_TABLE + " ON " + USERALBUMS_TABLE + "." + COLUMN_USERALBUMS_ALBUM_ID + " = " + ALBUMS_TABLE + "." + COLUMN_ALBUM_ID + " WHERE " + USERALBUMS_TABLE + "." + COLUMN_USERALBUMS_USER_ID + " = " + UID;
        String queryString = "SELECT * FROM " + ALBUMS_TABLE + " LEFT JOIN " + USERALBUMS_TABLE + " ON " + ALBUMS_TABLE + "." + COLUMN_ALBUM_ID + " = " + USERALBUMS_TABLE + "." + COLUMN_USERALBUMS_ALBUM_ID + " WHERE " + USERALBUMS_TABLE + "." + COLUMN_USERALBUMS_USER_ID + " = " + UID;

        returnAlbumList = populateAlbumModel(queryString);

        return returnAlbumList;
    }

    // multiple
    public List<SongModel> populateSongModel(String query) {
        List<SongModel> populatedSongs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new song object for each row
            // and put them into the return list
            do {
                int songID = cursor.getInt(0);
                String songName = cursor.getString(1);
                String songArtist = cursor.getString(2);
                String songGenre = cursor.getString(3);

                SongModel newSong = new SongModel(songID, songName, songArtist, songGenre);
                populatedSongs.add(newSong);

            } while (cursor.moveToNext());
        } else {
            // if there are no songs, add no songs to the list
        }

        // close cursor and db
        cursor.close();
        db.close();
        return populatedSongs;
    }

    // singular
    public SongModel populateOneSongModel(String query) {
        SongModel populatedSong = new SongModel();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new song object for each row
            // and put them into the return list
            do {
                int songID = cursor.getInt(0);
                String songName = cursor.getString(1);
                String songArtist = cursor.getString(2);
                String songGenre = cursor.getString(3);

                SongModel newSong = new SongModel(songID, songName, songArtist, songGenre);
                populatedSong = newSong;

            } while (cursor.moveToNext());
        } else {
            // if there are no songs, add no songs to the list
        }

        // close cursor and db
        cursor.close();
        db.close();
        return populatedSong;
    }

    // multiple
    public List<AlbumModel> populateAlbumModel(String query) {
        List<AlbumModel> populatedSongs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new albums object for each row
            // and put them into the return list
            do {
                int albumID = cursor.getInt(0);
                String albumName = cursor.getString(1);
                String albumArtist = cursor.getString(2);
                String albumGenre = cursor.getString(3);

                AlbumModel newAlbum = new AlbumModel(albumID, albumName, albumArtist, albumGenre);
                populatedSongs.add(newAlbum);

            } while (cursor.moveToNext());
        } else {
            // if there are no albums, add no albums to the list
        }

        // close cursor and db
        cursor.close();
        db.close();
        return populatedSongs;
    }

    // singular
    public AlbumModel populateOneAlbumModel(String query) {
        AlbumModel populatedAlbum = new AlbumModel();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new song object for each row
            // and put them into the return list
            do {
                int albumID = cursor.getInt(0);
                String albumName = cursor.getString(1);
                String albumArtist = cursor.getString(2);
                String albumGenre = cursor.getString(3);

                AlbumModel newAlbum = new AlbumModel(albumID, albumName, albumArtist, albumGenre);
                populatedAlbum = newAlbum;

            } while (cursor.moveToNext());
        } else {
            // if there are no albums, add no albums to the list
        }

        // close cursor and db
        cursor.close();
        db.close();
        return populatedAlbum;
    }

    public List<UserModel> populateUserModel(String query) {
        List<UserModel> populatedUsers = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new user object for each row
            // and put them into the return list
            do {
                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userFavGenre = cursor.getString(2);

                UserModel newUser = new UserModel(userID, userName, userFavGenre);
                populatedUsers.add(newUser);

            } while (cursor.moveToNext());
        } else {
            // if there are no users, add no users to the list
        }

        // close cursor and db
        cursor.close();
        db.close();
        return populatedUsers;
    }

    // UPDATE
    public void updateUserFavGenre(int userID, String genre) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + USERS_TABLE + " SET " + COLUMN_USER_FAV_GENRE + " = " + genre;

    }
}
