package tests;

import main.model.Album;
import main.model.Playlist;
import main.model.Song;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest {

    @Test
    public void testConstructorAndGetName() {
        Playlist playlist = new Playlist("My Playlist");
        assertEquals("My Playlist", playlist.getName());
    }

    @Test
    public void testCopyConstructor() {
        Playlist playlist1 = new Playlist("Original Playlist");
        Playlist playlist2 = new Playlist(playlist1);

        assertEquals("Original Playlist", playlist2.getName());
    }

    @Test
    public void testAddSong() {
        Playlist playlist = new Playlist("My Playlist");
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song = new Song("Test Song", album);

        playlist.addSong(song);
        assertTrue(playlist.toString().contains("Test Song"));
    }

    @Test
    public void testAddDuplicateSong() {
        Playlist playlist = new Playlist("My Playlist");
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song = new Song("Test Song", album);

        playlist.addSong(song);
        playlist.addSong(song);

        String output = playlist.toString();
        assertEquals(1, output.split("Test Song").length - 1);
    }

    @Test
    public void testAddAlbum() {
        Playlist playlist = new Playlist("My Playlist");
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        album.addSong(new Song("Song1", album));
        album.addSong(new Song("Song2", album));

        playlist.addAlbum(album);

        assertTrue(playlist.toString().contains("Song1"));
        assertTrue(playlist.toString().contains("Song2"));
    }

    @Test
    public void testRemoveSongByObject() {
        Playlist playlist = new Playlist("My Playlist");
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song = new Song("Test Song", album);

        playlist.addSong(song);
        assertEquals(song, playlist.removeSong(song));
        assertFalse(playlist.toString().contains("Test Song"));
    }

    @Test
    public void testRemoveSongByIndex() {
        Playlist playlist = new Playlist("My Playlist");
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song1 = new Song("Song1", album);
        Song song2 = new Song("Song2", album);

        playlist.addSong(song1);
        playlist.addSong(song2);

        assertEquals(song1, playlist.removeSong(0));
        assertFalse(playlist.toString().contains("Song1"));
    }

    @Test
    public void testToString() {
        Playlist playlist = new Playlist("My Playlist");
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song = new Song("Test Song", album);

        playlist.addSong(song);

        String expected = "My Playlist:\n1. Test Song by Test Artist, \"Test Album\" (2020) [Rock]";
        assertTrue(playlist.toString().startsWith(expected));
    }
}
