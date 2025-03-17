package main.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;

public class LibraryModel extends MusicStore {

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

    // get the top ten most played songs and add them to a playlist
    public void generateMostPlayed() {
        // remove the Most played and regenerate it
        for (Playlist p : playlists) {
            if (p.getName().equals("Most Played")) {
                playlists.remove(p);
                break;
            }
        }
        
        Playlist temp = getOrAddInternal("Most Played");
        ArrayList<Song> sortedSongs = new ArrayList<>(songs);

        // sort songs by plays and get top ten
        sortedSongs.sort((a, b) -> Integer.compare(b.getPlays(), a.getPlays()));
        List<Song> topSongs = sortedSongs.subList(0, Math.min(10, sortedSongs.size()));

        // add top ten to playlist
        for (Song s : topSongs) {
            temp.addSong(new Song(s));
        }

        return;
    }

    public void generateTopRated() {
        Playlist pl = getOrAddInternal("Top Rated");    
        for(Song s: songs) {
            if(s.getRating() > 3) {
                pl.addSong(new Song(s));
            }
        }
    }

    public void generateFavoriteSongs() {
        Playlist pl = getOrAddInternal("Favorite Songs");
        for(Song s: songs) {
            if(s.isFavorite()) {
                pl.addSong(new Song(s));
            }
        }
    }

    public void generateByGenre() {
        HashMap<String, Integer> genres = new HashMap<>();
        for(Song s: songs) {
            String genre = s.getGenre();
            if(genres.containsKey(genre)) {
                genres.put(genre, genres.get(genre) + 1);
            } else {
                genres.put(genre, 1);
            }
        }
        for(String genre: genres.keySet()) {
            if(genres.get(genre) >= 10) {
                Playlist pl = getOrAddInternal(genre);
                for(Song s: songs) {
                    if(s.getGenre().equals(genre)) {
                        pl.addSong(new Song(s));
                    }
                }
            }
        }
    }

    // get the ten most recently played songs and add them to a playlist
    public String addToRecentlyPlayed(Song song) {
        if (!songs.contains(song)) {
            return "You do not own this song in your library.";
        }

        Song curSong = null;
        for (Song s : songs) {
            if (s.equals(song)) {
                s.play();
                curSong = s;
            }
        }

        Playlist temp = getOrAddInternal("Recently Played");
        
        // remove last song if playlist is full ( > 10 songs)
        if (temp.getSize() == 10) {
            temp.removeAtIndex(9);
        }

        // add song to front of list
        temp.addAtIndex(curSong, 0);

        return "Playing the song: " + curSong.getTitle();
    }

    // get or add a playlist of a specific name
    private Playlist getOrAddInternal(String name) {
        Playlist temp = null;

        for (Playlist p : playlists) {
            if (p.getName().equals(name)) {
                return p;
            }
        }

        // make list if it doesnt exist
        if (temp == null) {
            temp = new Playlist(name);
            playlists.add(temp);
        }
        return temp;
    }

    // add referenced copies from a dereferenced object
    public String addSongToPlaylist(Song song, String name) {
        Song s = ms.getSong(song);

        if (!songs.contains(s)) return s.getTitle() + " not found in the library";

        for (Playlist pl : playlists) {
            if (pl.getName().equals(name)) {
                pl.addSong(s);
                return s.getTitle() + " added to " + pl.getName() + " successfully!";
            }
        }
        return "There is no playlist named \"" + name + "\".";
    }

    // rate referenced copies from a dereferenced object (if it exists in the library)
    public void setRating(Song song, int rating) {
        Song s = ms.getSong(song);

        if (songs.contains(s)) {
            s.setRating(rating);
        } else {
            throw new IllegalArgumentException("Song not in the library");
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
            temp.add(s.getAlbum().getTitle() + " by " + s.getAlbum().getArtist() + " [" + s.getAlbum().getAlbumSongs().size() + " songs]");
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
        generateMostPlayed();
        generateTopRated();
        generateFavoriteSongs();
        generateByGenre();
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

        for (Playlist pl : playlists) {
            if (pl.getName().equals(name)) {
                pl.removeSong(s);
                return "Song removed from " + pl.getName() + " successfully!";
            }
        }
        return "There is no playlist named \"" + name + "\".";
    }

// ----------------------  Search methods  ----------------------

    public ArrayList<Song> getSongsByTitle(String title) {
        ArrayList<Song> dereferencedSongs = new ArrayList<>();
        for(Song song : songs) {
            if(song.getTitle().equalsIgnoreCase(title)) {
                dereferencedSongs.add(new Song(song));
            }
        }
        return dereferencedSongs;
    }

    public ArrayList<Album> getAlbumsByTitle(String title) {
        ArrayList<Album> albums = new ArrayList<>();
        for(Song s: songs) {
            if(s.getAlbum().getTitle().equalsIgnoreCase(title)) albums.add(s.getAlbum());
        }
        ArrayList<Album> dereferencedAlbums = new ArrayList<>();
        for(Album a: new HashSet<>(albums)) {
            dereferencedAlbums.add(new Album(a));
        }

        return dereferencedAlbums;
    }

    public ArrayList<Song> getSongsByArtist(String artist) { 
        ArrayList<Song> temp = new ArrayList<>();
        for(Song s: this.songs) {
            if(s.getArtist().equalsIgnoreCase(artist)) temp.add(new Song(s));
        }
        return temp;
    }

    public ArrayList<Album> getAlbumsByArtist(String artist) {
        ArrayList<Album> albums = new ArrayList<>();
        for(Song s: this.songs) {
            if(s.getArtist().equalsIgnoreCase(artist)) albums.add(s.getAlbum());
        }
        ArrayList<Album> dereferencedAlbums = new ArrayList<>();
        for(Album a: new HashSet<>(albums)) {
            dereferencedAlbums.add(new Album(a));
        }
        return dereferencedAlbums;
    }

    public ArrayList<Song> getSongsByGenre(String genre) {
        ArrayList<Song> dereferencedSongs = new ArrayList<>();
        for(Song s : songs) {
            if(s.getGenre().equalsIgnoreCase(genre)) {
                dereferencedSongs.add(new Song(s));
            }
        }
        return dereferencedSongs;
    }

    // ----------------------  Remove methods  ----------------------
    public void removeSong(Song song) {
        for(Song s: songs) {
            if(s.equals(song)) {
                songs.remove(s);
                return;
            }
        }
    }

    public void removeAlbum(Album album) {
        for(Song s: album.getAlbumSongs()) {
            removeSong(s);
        }
    }
}
