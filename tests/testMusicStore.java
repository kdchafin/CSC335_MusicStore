package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.model.Album;
import main.model.MusicStore;
import main.model.Song;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class testMusicStore {
    private MusicStore ms;
    private Album album;
    private Song song;

    @BeforeEach
    public void setUp() {
        ms = MusicStore.getInstance();
        album = new Album("Test Album", "Test Artist", "Rock", 2025);
        song = new Song("Test Song", album);
        ms.addAlbumToList(album);
        ms.addSongToList(song);
    }

    @Test
    public void testSingletonInstance() {
        MusicStore instance1 = MusicStore.getInstance();
        MusicStore instance2 = MusicStore.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetSongByTitle() {
        List<Song> songs = ms.getSongByTitle("Test Song");
        assertFalse(songs.isEmpty());
        assertEquals("Test Song", songs.get(0).getTitle());
    }

    @Test
    public void testGetSongByArtist() {
        List<Song> songs = ms.getSongByArtist("Test Artist");
        assertFalse(songs.isEmpty());
        assertEquals("Test Artist", songs.get(0).getArtist());
    }

    @Test
    public void testGetAlbumByTitle() {
        List<Album> albums = ms.getAlbumByTitle("Test Album");
        assertFalse(albums.isEmpty());
        assertEquals("Test Album", albums.get(0).getTitle());
    }

    @Test
    public void testGetAlbumByArtist() {
        List<Album> albums = ms.getAlbumByArtist("Test Artist");
        assertFalse(albums.isEmpty());
        assertEquals("Test Artist", albums.get(0).getArtist());
    }

    @Test
    public void testGetAllSongs() {
        List<Song> songs = ms.getAllSongs();
        assertFalse(songs.isEmpty());
        assertEquals("Test Song", songs.get(0).getTitle());
    }

    @Test
    public void testGetAllAlbums() {
        List<Album> albums = ms.getAllAlbums();
        assertFalse(albums.isEmpty());
        assertEquals("Test Album", albums.get(0).getTitle());
    }

    @Test
    public void testGetAllArtists() {
        List<String> artists = ms.getAllArtists();
        assertFalse(artists.isEmpty());
        assertTrue(artists.contains("Test Artist"));
    }
}