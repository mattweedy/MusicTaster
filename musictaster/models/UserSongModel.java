package com.example.musictaster.models;

public class UserSongModel {
    private int ID;
    private int songID;

    // constructors
    public UserSongModel(int ID, int songID) {
        this.ID = ID;
        this.songID = songID;
    }

    public UserSongModel() {
    }

    // toString
    @Override
    public String toString() {
        return "UserSongModel{" +
                "ID=" + ID +
                ", songID=" + songID +
                '}';
    }

    // getters and setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }
}
