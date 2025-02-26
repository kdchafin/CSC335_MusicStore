package main.model;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Playlist {
    private final String plName;
    private ArrayList<Song> plSonglist;

    public Playlist(String name) {
        this.plName = name;
    }

    public String getName() {
        return this.plName;
    }

    public void addSong(Song song) {
        plSonglist.add(song);
    }

    public void removeSong(Song song) {
        plSonglist.remove(song);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.plName + "\n[" + String.valueOf(plSonglist.size() + " Songs]\n"));
        for(Song s: plSonglist) {
            sb.append(s.toString());
        }
        return sb.toString();
    }
}
