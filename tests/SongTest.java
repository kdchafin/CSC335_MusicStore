package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.model.Album;
import main.model.Song;


import org.junit.jupiter.api.BeforeEach;

public class SongTest {
    Album loveAtFirstSting;
    Song rockYouLikeAHurricane;
    Song bigCityNights;
    
    @BeforeEach
    void setUp() {
        loveAtFirstSting = new Album("Love At First Sting", "The Scorpions", "Rock", 1984);
        rockYouLikeAHurricane = new Song("Rock You Like A Hurricane", loveAtFirstSting);
        bigCityNights = new Song("Big City Nights", loveAtFirstSting);
        loveAtFirstSting.addSong(rockYouLikeAHurricane);
        loveAtFirstSting.addSong(bigCityNights);
    }

    @Test
    void testGetTitle() {
        assertEquals("Rock You Like A Hurricane", rockYouLikeAHurricane.getTitle());
        assertEquals("Big City Nights", bigCityNights.getTitle());
    }

    @Test
    void testGetArtist() {
        assertEquals("The Scorpions", rockYouLikeAHurricane.getArtist());
        assertEquals("The Scorpions", bigCityNights.getArtist());
    }

    @Test
    void testGetAndSetRating() {
        assertThrows(IllegalArgumentException.class, () -> rockYouLikeAHurricane.setRating(0));
        assertEquals(0, rockYouLikeAHurricane.getRating()); // assert default value is 0
        assertDoesNotThrow(() -> rockYouLikeAHurricane.setRating(1));
        assertEquals(1, rockYouLikeAHurricane.getRating());
        assertDoesNotThrow(() -> rockYouLikeAHurricane.setRating(2));
        assertEquals(2, rockYouLikeAHurricane.getRating());
        assertDoesNotThrow(() -> rockYouLikeAHurricane.setRating(3));
        assertEquals(3, rockYouLikeAHurricane.getRating());
        assertDoesNotThrow(() -> rockYouLikeAHurricane.setRating(4));
        assertEquals(4, rockYouLikeAHurricane.getRating());
        assertDoesNotThrow(() -> rockYouLikeAHurricane.setRating(5));
        assertEquals(5, rockYouLikeAHurricane.getRating());
        assertThrows(IllegalArgumentException.class, () -> rockYouLikeAHurricane.setRating(6));
    }

    @Test
    void testIsFavorite() {
        assertFalse(rockYouLikeAHurricane.isFavorite());
        rockYouLikeAHurricane.setRating(5);
        assertTrue(rockYouLikeAHurricane.isFavorite());
    }

    @Test
    void testToString() {
        assertEquals("Rock You Like A Hurricane by The Scorpions on the album \"Love At First Sting\"", rockYouLikeAHurricane.toString());
        rockYouLikeAHurricane.setRating(5);
        assertEquals("Rock You Like A Hurricane by The Scorpions on the album \"Love At First Sting\" [🔥🔥🔥🔥🔥]", rockYouLikeAHurricane.toString());
        rockYouLikeAHurricane.setRating(4);
        assertEquals("Rock You Like A Hurricane by The Scorpions on the album \"Love At First Sting\" [🔥🔥🔥🔥]", rockYouLikeAHurricane.toString());
        rockYouLikeAHurricane.setRating(3);
        assertEquals("Rock You Like A Hurricane by The Scorpions on the album \"Love At First Sting\" [🔥🔥🔥]", rockYouLikeAHurricane.toString());
        rockYouLikeAHurricane.setRating(2);
        assertEquals("Rock You Like A Hurricane by The Scorpions on the album \"Love At First Sting\" [🔥🔥]", rockYouLikeAHurricane.toString());
        rockYouLikeAHurricane.setRating(1);
        assertEquals("Rock You Like A Hurricane by The Scorpions on the album \"Love At First Sting\" [🔥]", rockYouLikeAHurricane.toString());
    }

    @Test
    void testCopyConstructor() {
        Song copy = new Song(rockYouLikeAHurricane);
        assertEquals(rockYouLikeAHurricane.getTitle(), copy.getTitle());
        assertEquals(rockYouLikeAHurricane.getArtist(), copy.getArtist());
        assertEquals(rockYouLikeAHurricane.getAlbum(), copy.getAlbum());
        assertEquals(rockYouLikeAHurricane.getRating(), copy.getRating());
        assertEquals(rockYouLikeAHurricane.isFavorite(), copy.isFavorite());
        assertNotEquals(loveAtFirstSting, copy);
    }

    @Test
    void testEquals() {
        Song copy = new Song(rockYouLikeAHurricane);
        Object obj = new Object();
        assertFalse(rockYouLikeAHurricane.equals(obj)); // obj is not instance of Song and should return false
        assertFalse(copy == rockYouLikeAHurricane); // copy constructor should dereference original song
        assertFalse(rockYouLikeAHurricane == null); // song is not null
        assertTrue(copy.equals(rockYouLikeAHurricane)); // overridden equals method should return true for a copy of the object
        assertFalse(rockYouLikeAHurricane.equals(bigCityNights)); // should return false for 2 different songs (different titles)
    }
}
