# MusicTaster
MusicTaster is an Android application developed using Java and Android Studio. It allows users to explore various music genres, select songs and albums, and manage personalised lists of liked tracks and albums. User preferences are stored locally, providing a tailored music selection experience.
## Features
- **User Authentication:** Prompts users to enter a username to personalise their experience.
- **Genre Exploration:** Browse and select from a variety of music genres.
- **Song and Album Selection:** Choose favourite songs and albums to add to liked lists.
- **Local Data Storage:** Utilises SQLite for storing user preferences and selections.
- **Dynamic UI:** Interactive interfaces for genre selection and liked items management.
## Architecture Overview
- **Activites:**
  - `MainActivity`: Initial screen prompting for username input
  - `GenreSelectionActivity`: Displays available music genres for selection.
  - `SongSelectorActivity`: Lists songs based on the selected genre.
  - `AlbumSelectorActivity`: Lists albums based on the selected genre.
  - `LikedSongsActivity`: Shows the user's liked songs.
  - `LikedAlbumsActivity`: Shows the user's liked albums.
- **Database:**
  - SQLite database with tables for users, liked songs, and liked albums.
  - `albuminsert.sql`: Script for initialising the albums table with sample data.
## Project Structure
```
MusicTaster/
├── album-covers/         # Contains album cover images
├── musictaster/          # Main application source code
│   ├── activities/       # Activity classes
│   ├── database/         # Database helper classes
│   └── models/           # Data models
├── albuminsert.sql       # SQL script for album data
├── README.md             # Project documentation
└── ...                   # Other configuration files
```
## Installation
1. **Clone the repository:**
```
git clone https://github.com/mattweedy/MusicTaster.git
```
2. **Open in Android Studio:**
- Launch Android Studio.
- Select "Open an existing project".
- Navigate to the cloned MusicTaster directory.
3. **Build and Run:**
- Connect an Android device or start an emulator.
- Click the "Run" button to build and launch the app.
## Screenshots
![image](https://github.com/user-attachments/assets/cf739536-3e42-481c-9517-0316cf677e46)
![image](https://github.com/user-attachments/assets/9c309e32-1863-4379-93ae-b74edab67198)
![image](https://github.com/user-attachments/assets/621b9069-702e-40f8-91d6-51fb9ef7d650)
## Future Enhancements
- **Online Integration:** Fetch real-time data from music APIs.
- **User Authentication:** Implement secure login and registration.
- **Enhanced UI/UX:** Improve the user interface for better experience.
- **Playlist Management:** Allow users to create and manage custom playlists.
## License
This project is licensed under the MIT License
