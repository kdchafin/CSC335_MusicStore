package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.model.Album;
import main.model.Song;


import org.junit.jupiter.api.BeforeEach;

public class AlbumTest {
    Album loveAtFirstSting;
    
    @BeforeEach
    void setUp() {
        loveAtFirstSting = new Album("Love At First Sting", "The Scorpions", "Rock", 1984);
        Song rockYouLikeAHurricane = new Song("Rock You Like A Hurricane", loveAtFirstSting);
        Song bigCityNights = new Song("Big City Nights", loveAtFirstSting);
        loveAtFirstSting.addSong(rockYouLikeAHurricane);
        loveAtFirstSting.addSong(bigCityNights);
    }

    @Test
    void testGetTitle() {
        assertEquals("Love At First Sting", loveAtFirstSting.getTitle());
    }

    @Test
    void testGetArtist() {
        assertEquals("The Scorpions", loveAtFirstSting.getArtist());
    }

    @Test
    void testGetGenre() {
        assertEquals("Rock", loveAtFirstSting.getGenre());
    }

    @Test
    void testGetYear() {
        assertEquals(1984, loveAtFirstSting.getYear());
    }

    @Test
    void testGetSongsOnAlbum() {
        assertEquals(2, loveAtFirstSting.getSongsOnAlbum().size());
        assertEquals("Rock You Like A Hurricane", loveAtFirstSting.getSongsOnAlbum().get(0).getTitle());
        assertEquals("Big City Nights", loveAtFirstSting.getSongsOnAlbum().get(1).getTitle());
    }

    @Test
    void testToString() {
        assertEquals("""
        Love At First Sting by The Scorpions
        1. Rock You Like A Hurricane
        2. Big City Nights
        """.trim(), loveAtFirstSting.toString());
    }

    @Test
    void testCopyConstructor() {
        Album copy = new Album(loveAtFirstSting);
        assertEquals(loveAtFirstSting.getTitle(), copy.getTitle());
        assertEquals(loveAtFirstSting.getArtist(), copy.getArtist());
        assertEquals(loveAtFirstSting.getGenre(), copy.getGenre());
        assertEquals(loveAtFirstSting.getYear(), copy.getYear());
        assertEquals(loveAtFirstSting.getSongsOnAlbum(), copy.getSongsOnAlbum());
        assertFalse(loveAtFirstSting == copy);
        assertTrue(loveAtFirstSting.equals(copy));
    }

    @Test
    void testAddSong() {
        Song crossfire = new Song("Crossfire", loveAtFirstSting);
        loveAtFirstSting.addSong(crossfire);
        assertEquals(3, loveAtFirstSting.getSongsOnAlbum().size());
    }

    @Test
    void testEquals() {
        Album copy = new Album(loveAtFirstSting);
        Object obj = new Object();
        assertFalse(loveAtFirstSting.equals(obj)); // obj is not instance of Album and should return false
        assertFalse(copy == loveAtFirstSting); // copy constructor should dereference original album
        assertFalse(loveAtFirstSting == null); // album is not null
        assertTrue(copy.equals(loveAtFirstSting)); // overridden equals method should return true for a copy of the object
    }
}
