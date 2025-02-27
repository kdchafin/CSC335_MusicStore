package main.model;

import java.util.ArrayList;

public class LibraryModel {
    private static LibraryModel instance;
    private ArrayList<Song> songs;
    private ArrayList<String> artists;
    private ArrayList<Album> albums;
    private ArrayList<Playlist> playlists;

    public LibraryModel() {
        songs = new ArrayList<Song>();
        artists = new ArrayList<String>();
        albums = new ArrayList<Album>();
        playlists = new ArrayList<Playlist>();
    }

    public static LibraryModel getInstance() {
        if(instance == null) {
            instance = new LibraryModel();
        }
        return instance;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<String> getArtists() {
        return new ArrayList<String>(artists);
    }

    public ArrayList<Album> getAlbums() {
        ArrayList<Album> temp = new ArrayList<>();
        for(Album album : albums) {
            temp.add(new Album(album));
        }
        return temp;
    }

    public ArrayList<Playlist> getPlaylists() {
        ArrayList<Playlist> temp = new ArrayList<>();
        for(Playlist playlist : playlists) {
            temp.add(new Playlist(playlist));
        }
        return temp;
    }

    public String createPlaylist(String name) {
        for(Playlist pl : playlists) {
            if(pl.getName().equals(name)) {
                return "A playlist named \"" + name + "\" already exists."; 
            }
        }
        playlists.add(new Playlist(name));
        return "Playlist \"" + name + "\" created successfully.";
    }

    public String deletePlaylist(String name) {
        for(Playlist pl : playlists) {
            if(pl.getName().equals(name)) {
                playlists.remove(pl);
                return "Playlist \"" + name + "\" deleted successfully.";
            }
        }
        return "There is no playlist named \"" + name + "\".";
    }

    public String addSongToPlaylist(Song song, String name) {
        for (Playlist pl : playlists) {
            if (pl.getName().equals(name)) {
                pl.addSong(song);
                return "Song added to " + pl.getName() + " successfully!";
            }
        }
        return "There is no playlist named \"" + name + "\".";
    }

    public String removeSongFromPlaylist(Song song, String name) {
        for (Playlist pl : playlists) {
            if (pl.getName().equals(name)) {
                pl.removeSong(song);
                return "Song removed from " + pl.getName() + " successfully!";
            }
        }
        return "There is no playlist named \"" + name + "\".";
    }

    public ArrayList<Song> getFavoriteSongs() {
        ArrayList<Song> favoriteSongs = new ArrayList<>();
        for(Song song : songs) {
            if(song.isFavorite()) {
                favoriteSongs.add(song);
            }
        }
        return favoriteSongs;
    }

    public void addSong(Song song) {
        for (Song s : songs) {
            if (s.equals(song)) {
                return; // do not add duplicate songs
            }
        }
        songs.add(song);
    }

    public void addAlbum(Album album) {
        for (Album a : albums) {
            if (a.equals(album)) {
                return; // do not add duplicate albums
            }
        }
        albums.add(album);
    }
}