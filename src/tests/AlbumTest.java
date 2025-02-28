package tests;

import org.junit.jupiter.api.Test;
import main.model.Album;
import main.model.Song;
import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest {

    @Test
    public void testConstructorAndGetters() {
        Album album = new Album("Title", "Artist", "Genre", 2020);
        assertEquals("Title", album.getTitle());
        assertEquals("Artist", album.getArtist());
        assertEquals("Genre", album.getGenre());
        assertEquals(2020, album.getYear());
    }

    @Test
    public void testCopyConstructor() {
        Album original = new Album("Title", "Artist", "Genre", 2020);
        Album copy = new Album(original);
        assertEquals(original.getTitle(), copy.getTitle());
        assertEquals(original.getArtist(), copy.getArtist());
        assertEquals(original.getGenre(), copy.getGenre());
        assertEquals(original.getYear(), copy.getYear());
        assertEquals(original.getAlbumSongs(), copy.getAlbumSongs());
    }

    @Test
    public void testAddSong() {
        Album album = new Album("Title", "Artist", "Genre", 2020);
        Song song = new Song("Song Title", album);
        album.addSong(song);
        assertTrue(album.getAlbumSongs().contains(song));
    }

    @Test
    public void testToString() {
        Album album = new Album("Title", "Artist", "Genre", 2020);
        Song song1 = new Song("Song 1", album);
        Song song2 = new Song("Song 2", album);
        album.addSong(song1);
        album.addSong(song2);
        String expected = "Title by Artist\n1. Song 1\n2. Song 2";
        assertEquals(expected, album.toString());
    }

    @Test
    public void testEqualsSameObject() {
        Album album = new Album("Title", "Artist", "Genre", 2020);
        assertTrue(album.equals(album));
    }

    @Test
    public void testEqualsDifferentObjectSameValues() {
        Album album1 = new Album("Title", "Artist", "Genre", 2020);
        Album album2 = new Album("Title", "Artist", "Genre", 2020);
        assertTrue(album1.equals(album2));
    }

    @Test
    public void testEqualsDifferentObjectDifferentValues() {
        Album album1 = new Album("Title1", "Artist", "Genre", 2020);
        Album album2 = new Album("Title2", "Artist", "Genre", 2020);
        assertFalse(album1.equals(album2));
    }

    @Test
    public void testEqualsNull() {
        Album album = new Album("Title", "Artist", "Genre", 2020);
        assertFalse(album.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        Album album = new Album("Title", "Artist", "Genre", 2020);
        Object obj = new Object();
        assertFalse(album.equals(obj));
    }
}