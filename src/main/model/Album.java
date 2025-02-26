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

    public int getYear() {
        return this.yearReleased;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getTitle() {
        return this.albumTitle;
    }

    public void addSong(Song song) {
        songsOnAlbum.add(song);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        sb.append(this.getTitle() + " by " + this.getArtist());
        for(Song song : this.getSongsOnAlbum()) {
            sb.append("\n" + index + ". " + song.getTitle());
            index++;
        }

        return sb.toString();
    }

    public ArrayList<Song> getSongsOnAlbum() {
        return songsOnAlbum;
    }
}

