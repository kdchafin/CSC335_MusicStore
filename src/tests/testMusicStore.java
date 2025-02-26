package tests;

import org.junit.jupiter.api.Test;

import main.model.Album;
import main.model.MusicStore;
import main.model.Song;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class testMusicStore {
    @Test
    public void testGetSongByTitle() {
        Album album = new Album("title", "artist", "rock", 2025);
        Song song = new Song("Test Song", album);

        MusicStore ms = new MusicStore();
        ms.addAlbumToList(album);
        ms.addSongToList(song);

        ArrayList<Album> albumList = ms.getAlbumList();
        assertEquals(albumList.get(0), album);

        ArrayList<Song> songList = ms.getSongList();
        assertEquals(songList.get(0), song);
    }
}
