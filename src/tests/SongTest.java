package tests;

import main.model.Album;
import main.model.Song;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SongTest {

    @Test
    public void testConstructorAndGetters() {
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song = new Song("Test Song", album);

        assertEquals("Test Song", song.getTitle());
        assertEquals(album, song.getAlbum());
        assertEquals("Test Album", song.getAlbum().getTitle());
        assertEquals("Test Artist", song.getArtist());
        assertEquals("Rock", song.getGenre());
        assertEquals(2020, song.getYear());
        assertEquals(0, song.getRating());
    }

    @Test
    public void testCopyConstructor() {
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song1 = new Song("Test Song", album);
        song1.setRating(3);
        Song song2 = new Song(song1);

        assertEquals("Test Song", song2.getTitle());
        assertEquals(album, song2.getAlbum());
        assertEquals(3, song2.getRating());
    }

    @Test
    public void testSetRatingValid() {
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song = new Song("Test Song", album);

        song.setRating(1);
        assertEquals(1, song.getRating());

        song.setRating(5);
        assertEquals(5, song.getRating());
    }

    @Test
    public void testSetRatingInvalid() {
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song = new Song("Test Song", album);

        assertThrows(IllegalArgumentException.class, () -> song.setRating(0));
        assertThrows(IllegalArgumentException.class, () -> song.setRating(6));
    }

    @Test
    public void testIsFavorite() {
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song = new Song("Test Song", album);

        assertFalse(song.isFavorite());
        song.setRating(5);
        assertTrue(song.isFavorite());
    }

    @Test
    public void testToString() {
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song = new Song("Test Song", album);

        String expectedWithoutRating = "Test Song by Test Artist, \"Test Album\" (2020) [Rock]";
        assertEquals(expectedWithoutRating, song.toString());

        song.setRating(3);
        String expectedWithRating = "Test Song by Test Artist, \"Test Album\" (2020) [Rock] [🔥🔥🔥]";
        assertEquals(expectedWithRating, song.toString());
    }

    @Test
    public void testEquals() {
        Album album = new Album("Test Album", "Test Artist", "Rock", 2020);
        Song song1 = new Song("Test Song", album);
        Song song2 = new Song("Test Song", album);
        Song song3 = new Song("Different Song", album);

        assertEquals(song1, song2);
        assertNotEquals(song1, song3);
        assertNotEquals(song1, null);
        assertNotEquals(song1, new Object());
    }
}