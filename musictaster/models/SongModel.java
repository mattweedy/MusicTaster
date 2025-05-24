package com.example.musictaster.models;

import android.media.Image;

public class SongModel {
    private int songID;
    private String songName;
    private String songArtist;
    private String songGenre;
    public Boolean selected = false;
//    private Image songImage;

    // constructors
    public SongModel(int songID, String songName, String songArtist, String songGenre) {
        this.songID = songID;
        this.songName = songName;
        this.songArtist = songArtist;
        this.songGenre = songGenre;
    }

    public SongModel() {
    }

    // toString
    @Override
    public String toString() {
        return "SongModel{" +
                "songID=" + songID +
                ", songName='" + songName + '\'' +
                ", songArtist='" + songArtist + '\'' +
                ", songGenre='" + songGenre + '\'' +
                '}';
    }

    // getters and setters
    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }
}
