package main.model;

import java.util.ArrayList;

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

    public int getYear() {
        return this.yearReleased;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getAlbumTitle() {
        return this.albumTitle;
    }

    public void addSong(Song song) {
        songsOnAlbum.add(song);
    }

    @Override
    public String toString() {
        String tString = "";
        tString = "Album: " + this.getAlbumTitle() +
                  ", Artist: " + this.getArtist() +
                  ", Genre: " + this.getGenre() +
                  ", Year: " + this.getYear();

        return tString;
    }
}