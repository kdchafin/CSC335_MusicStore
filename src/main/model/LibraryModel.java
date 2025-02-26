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
        return albums;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void createPlaylist(String name) {
        playlists.add(new Playlist(name));
    }

    public String addSongToPlaylist(Song song, String name) {
        for (Playlist pl : playlists) {
            if (pl.getName().equals(name)) {
                pl.addSong(song);
                return "Song Added To " + pl.getName() + " successfully!";
            }
        }
        return "No Playlist With That Name Found.";
    }

    public String removeSongToPlaylist(Song song, String name) {
        for (Playlist pl : playlists) {
            if (pl.getName().equals(name)) {
                pl.removeSong(song);
                return "Song removed from " + pl.getName() + " successfully!";
            }
        }
        return "No Playlist With That Name Found.";
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
}