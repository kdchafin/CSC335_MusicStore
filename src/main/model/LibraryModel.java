package main.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LibraryModel {
    private static LibraryModel instance;
    private ArrayList<Song> songs;
    private HashSet<String> artists;
    private ArrayList<Album> albums;
    private ArrayList<Playlist> playlists;

    public LibraryModel() {
        songs = new ArrayList<Song>();
        artists = new HashSet<String>();
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

    public String addAlbumToPlaylist(Album album, String name) {
        for (Playlist pl : playlists) {
            if (pl.getName().equals(name)) {
                pl.addAlbum(album);
                return "Album added to " + pl.getName() + " successfully!";
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

    public ArrayList<String> getArtists() {
        Set<String> temp = new HashSet<>();
        for (String artist : artists) {
            temp.add(artist);
        }
        // purge duplicates !!!
        return new ArrayList<String>(temp);
    }

    public void addSong(Song song) {
        for (Song s : songs) {
            if (s.equals(song)) {
                return; // do not add duplicate songs
            }
        }
        songs.add(song);
        artists.add(song.getArtist());
    }

    /*
     * Add an album and its songs the the Library model
     */
    public void addAlbum(Album album) {

        albums.stream().filter(a -> a.equals(album)).findFirst().ifPresentOrElse(
            a -> { return; }, 
            () -> { 
                albums.add(album);
                artists.add(album.getArtist());
                for (Song song : album.getSongsOnAlbum()) {
                    addSong(song);
                }
            }
        );
    }
}