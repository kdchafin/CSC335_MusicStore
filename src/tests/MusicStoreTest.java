package tests;

import static org.junit.jupiter.api.Assertions.*;

import main.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MusicStoreTest {
    private MusicStore musicStore;

    @BeforeEach
    void setUp() {
        musicStore = MusicStore.getInstance();
        musicStore.generateDataset();
    }

    @Test
    void testGetInstance() {
        assertNotNull(musicStore);
    }

    @Test
    void testGetSong() {
        ArrayList<Song> songFromStore = musicStore.getSongsByTitle("Daydreamer");
        assertEquals(songFromStore.size(), 2);

        ArrayList<Song> songsFromStore = musicStore.getSongsByArtist("adele");
        assertEquals(songsFromStore.size(), 48);
    }

    @Test
    void testGetAlbum() {
        ArrayList<Album> albumsFromStore = musicStore.getAlbumsByTitle("21");
        assertEquals(albumsFromStore.size(), 1);

        ArrayList<Album> albumsFromArtist = musicStore.getAlbumsByArtist("adele");
        assertEquals(albumsFromArtist.size(), 2);
    }

    @Test
    void testGetAll() {
        ArrayList<Song> allSongs = musicStore.getAllSongs();
        assertEquals(allSongs.size(), 489);

        ArrayList<Album> allAlbums = musicStore.getAllAlbums();
        assertEquals(allAlbums.size(), 45);

        ArrayList<String> allArtists = musicStore.getAllArtists();
        assertEquals(allArtists.size(), 14);
    }

}