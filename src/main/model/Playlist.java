package main.model;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Playlist {
    private final String plName;
    private ArrayList<Song> plSonglist;

    public Playlist(String name) {
        this.plName = name;
        plSonglist = new ArrayList<Song>();
    }

    public Playlist(Playlist playlist) {
        this.plName = playlist.getName();
        plSonglist = new ArrayList<Song>(playlist.plSonglist);
    }

    public String getName() {
        return this.plName;
    }

    public void addAlbum(Album album) {
        for(Song s : album.getAlbumSongs()) {
            addSong(s);
        }
    }

    public void addSong(Song song) {
        for(Song s : plSonglist) {
            if(s.equals(song)) return;
        }
        plSonglist.add(song);
    }

    public void removeAtIndex(int index) {
        plSonglist.remove(index);
    }

    public void addAtIndex(Song song, int index) {
        plSonglist.add(index, song);
    }

    public Song removeSong(Song song) {
        boolean temp = plSonglist.remove(song);
        return temp ? song : null;
    }

    public Song removeSong(int index) {
        return plSonglist.remove(index);
    }

    public int getSize() {
        return plSonglist.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.plName + ":\n");
        int index = 0;
        for(Song s: plSonglist) {
            index++;
            sb.append(String.valueOf(index) + ". " + s.toString() + "\n");
        }
        return sb.toString();
    }
}
