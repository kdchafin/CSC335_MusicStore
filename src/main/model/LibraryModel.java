package main.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LibraryModel extends MusicStore{

    private static LibraryModel instance;
    private final MusicStore ms;

    private ArrayList<Song> songs;
    private ArrayList<Playlist> playlists;

    public LibraryModel() {
        songs = new ArrayList<Song>();
        playlists = new ArrayList<Playlist>();
        ms = MusicStore.getInstance();
    }

    public static LibraryModel getInstance() {
        if(instance == null) {
            instance = new LibraryModel();
        }
        return instance;
    }

    // add referenced copies from a dereferenced object
    public void addSong(Song song) {
        Song s = ms.getSong(song);

        if (songs.contains(s)) return;
        songs.add(s);
    }

    // add referenced copies from a dereferenced object
    public void addAlbum(Album album) {
        Album a = ms.getAlbum(album);

        if (hasAlbum(a)) return;
        for (Song s : a.getAlbumSongs()) {
            addSong(s);
        }
    }

    // add referenced copies from a dereferenced object
    public void addSongToPlaylist(Song song, String name) {
        Song s = ms.getSong(song);

        if (!songs.contains(s)) return;

        for (Playlist pl : playlists) {
            if (pl.getName().equals(name)) {
                pl.addSong(s);
                return;
            }
        }
    }

    // rate referenced copies from a dereferenced object (if it exists in the library)
    public void setRating(Song song, int rating) {
        Song s = ms.getSong(song);

        if (songs.contains(s)) {
            s.setRating(rating);
        }
    }

    public boolean hasAlbum(Album album) {
        for (Song s : this.songs) {
            if (s.getAlbum().equals(album)) return true;
        }
        return false;
    }

// ----------------------  Playlist methods  ----------------------


    public ArrayList<Song> getSongs() {
        ArrayList<Song> temp = new ArrayList<>();

        for(Song song : songs) {
            temp.add(new Song(song));
        }
        return temp;
    }

    public ArrayList<String> getAlbums() {
        HashSet<String> temp = new HashSet<>();

        for(Song s : songs) {
            temp.add(s.getAlbum().getTitle());
        }
        return new ArrayList<String>(temp);
    }

    public ArrayList<String> getArtists() {
        HashSet<String> temp = new HashSet<>();
        for (Song s : songs) {
            temp.add(s.getArtist()); //string is immutable by nature
        }
        return new ArrayList<String>(temp);
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

    public String removeSongFromPlaylist(Song song, String name) {
        Song s = ms.getSong(song);

        for (Playlist pl : instance.playlists) {
            if (pl.getName().equals(name)) {
                pl.removeSong(s);
                return "Song removed from " + pl.getName() + " successfully!";
            }
        }
        return "There is no playlist named \"" + name + "\".";
    }
}