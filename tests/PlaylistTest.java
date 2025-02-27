package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.model.*;

public class PlaylistTest {
    private Playlist bangerTunes;
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
        bangerTunes = new Playlist("Banger Tunes");
    }

    @Test
    void testGetName() {
        assertEquals("Banger Tunes", bangerTunes.getName());
    }

    @Test
    void testAddSong() {
        assertEquals(0, bangerTunes.getSongList().size());
        bangerTunes.addSong(rockYouLikeAHurricane);
        assertEquals(1, bangerTunes.getSongList().size());
        bangerTunes.addSong(bigCityNights);
        assertEquals(2, bangerTunes.getSongList().size());
        
        // assert that adding duplicate songs doesn't work
        bangerTunes.addSong(bigCityNights); 
        assertEquals(2, bangerTunes.getSongList().size());
    }

    @Test
    void testRemoveSongBySongObject() {
        assertEquals(0, bangerTunes.getSongList().size());
        bangerTunes.addSong(rockYouLikeAHurricane);
        bangerTunes.addSong(bigCityNights);
        assertEquals(2, bangerTunes.getSongList().size());

        // assert that removing songs works
        Song removed = bangerTunes.removeSong(bigCityNights);
        assertEquals(1, bangerTunes.getSongList().size());
        assertEquals(bigCityNights, removed);

        // assert that removing non-existent songs doesn't work
        removed = bangerTunes.removeSong(bigCityNights);
        assertEquals(1, bangerTunes.getSongList().size());
        assertNull(removed);
    }

    @Test
    void testRemoveSongByIndex() {
        assertEquals(0, bangerTunes.getSongList().size());
        bangerTunes.addSong(rockYouLikeAHurricane);
        bangerTunes.addSong(bigCityNights);
        assertEquals(2, bangerTunes.getSongList().size());

        // assert that removing songs works
        Song removed = bangerTunes.removeSong(1);
        assertEquals(1, bangerTunes.getSongList().size());
        assertEquals(bigCityNights, removed);

        // assert that removing a song at an index that doesn't exist throws index out of bounds exception
        assertThrows(IndexOutOfBoundsException.class, () -> bangerTunes.removeSong(1));
        assertEquals(1, bangerTunes.getSongList().size());
    }

    @Test
    void testToString() {
        bangerTunes.addSong(rockYouLikeAHurricane);
        bangerTunes.addSong(bigCityNights);
        assertEquals("Banger Tunes:\n1. Rock You Like A Hurricane by The Scorpions on the album \"Love At First Sting\"\n2. Big City Nights by The Scorpions on the album \"Love At First Sting\"\n", bangerTunes.toString());
        rockYouLikeAHurricane.setRating(5);
        assertEquals("Banger Tunes:\n1. Rock You Like A Hurricane by The Scorpions on the album \"Love At First Sting\" [🔥🔥🔥🔥🔥]\n2. Big City Nights by The Scorpions on the album \"Love At First Sting\"\n", bangerTunes.toString());
    }

    @Test
    void testCopyConstructor() {
        Playlist copy = new Playlist(bangerTunes);
        assertEquals(bangerTunes.getName(), copy.getName());
        assertEquals(bangerTunes.getSongList(), copy.getSongList());
        assertNotEquals(bangerTunes, copy);
    }
}
