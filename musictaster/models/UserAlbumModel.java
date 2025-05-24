package com.example.musictaster.models;

public class UserAlbumModel {
    private int ID;
    private int albumID;

    // constructors
    public UserAlbumModel(int ID, int albumID) {
        this.ID = ID;
        this.albumID = albumID;
    }

    public UserAlbumModel() {
    }

    // toString
    @Override
    public String toString() {
        return "UserAlbumModel{" +
                "ID=" + ID +
                ", albumID=" + albumID +
                '}';
    }

    // getters and setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = UserAlbumModel.this.albumID;
    }
}
