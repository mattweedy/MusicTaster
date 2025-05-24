package com.example.musictaster.models;

import java.util.List;

public class UserModel {
    private int userID;
    private String userName;
    private String userFavGenre;

    // constructors
    public UserModel(int userID, String userName, String userFavGenre) {
        this.userID = userID;
        this.userName = userName;
        this.userFavGenre = userFavGenre;
    }

    public UserModel() {
    }

    // toString
    @Override
    public String toString() {
        return "UserModel{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userFavGenre='" + userFavGenre + '\'' +
                '}';
    }

    // getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFavGenre() {
        return userFavGenre;
    }

    public void setUserFavGenre(String userFavGenre) {
        this.userFavGenre = userFavGenre;
    }
}
