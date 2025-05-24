package com.example.musictaster.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.musictaster.R;
import com.example.musictaster.database.DatabaseHelper;
import com.example.musictaster.models.UserModel;

public class MainActivity extends Activity {
    // references to layout
    Button btn_begin;
    EditText et_username;
    private int UID;

    /*
     TODO: 2. CREATE NEXT ACTIVITY ( DISPLAY USER SONGS AND ALBUMS + CALCULATE AND DISPLAY FAVOURITE GENRE )
     TODO: 3. IF USER EXISTS SEND THEM TO THEIR DISPLAY PAGE
     TODO: 4. MAKE APP PRETTY - ROUNDED BUTTONS (UwU)
     TODO: 5. ((MAYBE)) LET USERS CHANGE THEIR PREFERENCES - MIGHT BREAK WHOLE THING (?)
     TODO: 6. ALBUM COVER ART IF TIME
     TODO: 7. ADD CAMERA OR SOME API (lol)
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.close();
        btn_begin = (Button) findViewById(R.id.btn_begin);
        et_username = (EditText) findViewById(R.id.et_username);

        btn_begin.setOnClickListener(
                v -> {
                    if (et_username.getText().toString().matches("")) {
                        Toast.makeText(this, "you need to enter a username", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // open db
                    DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);

                    // get the last users ID and increment
                    int nextID = dbHelper.getAllUsersReturnLastID() + 1;
                    // create new usermodel and add to database
                    UserModel userModel = new UserModel(nextID, et_username.getText().toString(), "");

                    if (!dbHelper.userExists(userModel)) {
                        boolean success = dbHelper.addUserOne(userModel);
                    } else {
                        Toast.makeText(this, "username already exists", Toast.LENGTH_SHORT).show();
                        // TODO: have stuff happen when user logs in
                        UID = dbHelper.getUserIDByName(et_username.getText().toString());
                        startProfileActivity();
                        return;
                    }


                    Intent gIntent = new Intent(MainActivity.this, GenreSelectorActivity.class);
                    gIntent.putExtra("userID", userModel.getUserID());
                    startActivity(gIntent);
                }
        );
    }

    public void startProfileActivity() {
        Intent pIntent = new Intent(MainActivity.this, ProfileActivity.class);
        pIntent.putExtra("userID", UID);
        startActivity(pIntent);
    }
}