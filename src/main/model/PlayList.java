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

    public String getName() {
        return this.plName;
    }

    public void addSong(Song song) {
        plSonglist.add(song);
    }

    public Song removeSong(Song song) {
        boolean temp = plSonglist.remove(song);
        return temp ? song : null;
    }

    public Song removeSong(int index) {
        return plSonglist.remove(index);
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
