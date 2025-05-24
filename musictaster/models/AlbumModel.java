package com.example.musictaster.models;

import android.media.Image;

public class AlbumModel {
    private int albumID;
    private String albumName;
    private String albumArtist;
    private String albumGenre;
//    private Image albumImage;

    // constructors
    public AlbumModel(int albumID, String albumName, String albumArtist, String albumGenre) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.albumGenre = albumGenre;
    }

    public AlbumModel() {
    }

    // toString
    @Override
    public String toString() {
        return "AlbumModel{" +
                "albumID=" + albumID +
                ", albumName='" + albumName + '\'' +
                ", albumArtist='" + albumArtist + '\'' +
                ", albumGenre='" + albumGenre + '\'' +
                '}';
    }

    // getters and setters
    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getAlbumGenre() {
        return albumGenre;
    }

    public void setAlbumGenre(String albumGenre) {
        this.albumGenre = albumGenre;
    }
}

