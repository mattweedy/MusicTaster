# MusicTaster
## Mobile Software Development

This app is for selecting from different genres of music and choosing songs and albums to add to your liked lists. It updates the user's liked songs and albums tables in the database automatically when they are added or removed.

# Classes
## Activities
### MainActivity
This activity is the beginning of the app. User is prompted to enter their username so that it can store their chosen songs and albums. Username cannot be blank and when you press the begin button it will send them to the GenreSelectorActivity.
The begin button has an onClickListener to detect when it is pressed so it can validate the EditText field and decide whether to remain on the page, send the new user to the following page, or send a returning user to their profile.

### GenreSelectorActivity
Here the user can choose from 5 genres that will filter down the songs and album choices on the following activites that they can choose from.
Each genre checkbox checked will add a string of that genre name to a list, which is passed to the DataBaseHelper, parsed and then dynamically built into an SQL statment so that the correct songs are displayed on the following page/activity.
Also contains a Home button to return the user to the MainActivity if they desire.

### SongSelectorActivity
User is able to add songs to their liked list.
Implements a ListView that is populated using a custom adapter with a custom layout file.
Also unpackages the current user's ID so that it can apply changes to the database for that specific user.

### AlbumSelectorActivity
Does the same as SongSelectorActivity but displays a list of albums from the database instead of songs.
The next button on this activity will call startProfileActivity() which will send the user to their Profile page.

### ProfileActivity
This is the class that handles the user's profile. It contains two ListViews that are each populated using separate custom adapters. These display the user's liked songs and albums. This actiivity contains a method that opens a new instance of the DataBaseHelper that will set the text attribute of the TextViews below the lists to display the respective favourite genre.
A returning user is sent to this page.

## Adapters
### SongAdapter
Custom adapter used for populating the SongSelectorActivity ListView. Uses a ViewHolder class to group the checkboxes on each ListView item so that when they are clicked the status of them are stored. When a checkbox is clicked it will either add or delete that song to the database depending on its status.

### LikedSongAdapter
Same as the SongAdapter except it does not have the functionality for the checkboxes as they are removed.

### AlbumAdapter
Same as SongAdapter but for albums.

### LikedAlbumAdapter
Same as LikedSongAdapter but for albums.

## Database
### DatabaseHelper
This class contains all CRUD operations needed by the database, as well as specific methods to handle finding the last created user's ID for example.

## Models
### SongModel
Defines the structure of a song. Contains all relevant information such as the ID, the name, artist and genre. Includes constructors, getters and setters.

### AlbumModel
Same as SongModel but for albums.

### UserModel
Contains the userID, the userName and the users favourite genre.

### UserSongModel
Contains the user's ID and the ID of the song they added.

### UserAlbumModel
Same as UserSongModel but for albums.

## Views
### CustomListView
A custom list view to be used for my own custom adapters.

#### Future Work
Implement the album covers to be displayed for each song and album on the ListViews.
Implement the recommendations feature (give songs and albums for the user to add based on their choices).
Allow users to take picture for their profile picture.
