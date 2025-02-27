package main.model;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Album {
    
    protected int yearReleased;
    protected String artist;
    protected String albumTitle;
    protected String genre;
    private ArrayList<Song> songsOnAlbum = new ArrayList<>();
    
    public Album(String albumTitle, String artist, String genre, int yearReleased) {
        this.albumTitle = albumTitle;
        this.artist = artist;
        this.genre = genre;
        this.yearReleased = yearReleased;
    }

    // copy constructor
    public Album(Album album) {
        this.albumTitle = album.getTitle();
        this.artist = album.getArtist();
        this.genre = album.getGenre();
        this.yearReleased = album.getYear();
        this.songsOnAlbum = album.getAlbumSongs();
    }

    //WARNING: All getters from this class return references to the original objects
    public int getYear() {
        return yearReleased;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return albumTitle;
    }

    public void addSong(Song song) {
        songsOnAlbum.add(song);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        sb.append(this.getTitle() + " by " + this.getArtist());
        for(Song song : this.getAlbumSongs()) {
            index++;
            sb.append("\n" + index + ". " + song.getTitle());
        }

        return sb.toString();
    }

    public ArrayList<Song> getAlbumSongs() {
        return songsOnAlbum;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Album)) {
            return false;
        }
        Album other = (Album) obj;
        return this.getTitle().equals(other.getTitle()) 
            && this.getArtist().equals(other.getArtist()) 
            && this.getGenre().equals(other.getGenre())
            && this.getYear() == other.getYear()
            && this.getAlbumSongs().equals(other.getAlbumSongs());
    }
}