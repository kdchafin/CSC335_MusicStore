package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.model.*;

public class LibraryModelTest {
    private LibraryModel libraryModel;
    private Album album;
    private Song song;
    private MusicStore musicStore;

    @BeforeEach
    void setUp() {
        libraryModel = new LibraryModel();
        musicStore = MusicStore.getInstance();
        musicStore.generateDataset();
        album = musicStore.getAlbumsByTitle("19").get(0);
        song = musicStore.getSongsByTitle("Daydreamer").get(0);
    }

    @Test
    void testSingletonInstance() {
        LibraryModel instance1 = LibraryModel.getInstance();
        LibraryModel instance2 = LibraryModel.getInstance();
        assertEquals(instance1, instance2);
        instance1.addSong(song);
        assertEquals(1, instance1.getSongs().size());
        assertEquals(1, instance2.getSongs().size());
    }

    @Test
    void testGetSongs() {
        assertEquals(0, libraryModel.getSongs().size());
        libraryModel.addSong(song);
        assertEquals(1, libraryModel.getSongs().size());
    }

    @Test
    void testGetArtists() {
        assertEquals(0, libraryModel.getArtists().size());
        libraryModel.addSong(song);
        assertEquals(1, libraryModel.getArtists().size());
    }

    @Test
    void testGetAlbums() {
        assertEquals(0, libraryModel.getAlbums().size());
        libraryModel.addAlbum(album);
        assertEquals(1, libraryModel.getAlbums().size());
    }

    @Test
    void testGetPlaylists() {
        assertEquals(0, libraryModel.getPlaylists().size());
        libraryModel.createPlaylist("TestPlaylist");
        assertEquals(1, libraryModel.getPlaylists().size());
    }

    @Test
    void testCreatePlaylist() {
        assertEquals(0, libraryModel.getPlaylists().size());
        assertEquals("Playlist \"Banger Tunes\" created successfully.", libraryModel.createPlaylist("Banger Tunes"));
        assertEquals(1, libraryModel.getPlaylists().size());

        // assert that creating duplicate playlists is not possible
        assertEquals("A playlist named \"Banger Tunes\" already exists.", libraryModel.createPlaylist("Banger Tunes"));
        assertEquals(1, libraryModel.getPlaylists().size());
    }

    @Test
    void testDeletePlaylist() {
        assertEquals(0, libraryModel.getPlaylists().size());
        libraryModel.createPlaylist("Banger Tunes");
        assertEquals(1, libraryModel.getPlaylists().size());
        assertEquals("Playlist \"Banger Tunes\" deleted successfully.", libraryModel.deletePlaylist("Banger Tunes"));
        assertEquals(0, libraryModel.getPlaylists().size());
        assertEquals("There is no playlist named \"Banger Tunes\".", libraryModel.deletePlaylist("Banger Tunes"));
    }

    @Test
    void testAddSongToPlaylist() {
        libraryModel.createPlaylist("Banger Tunes");
        libraryModel.addSong(song);
        assertEquals("Daydreamer added to Banger Tunes successfully!", libraryModel.addSongToPlaylist(song, "Banger Tunes"));
        assertEquals("There is no playlist named \"Midnight Vibes\".", libraryModel.addSongToPlaylist(song, "Midnight Vibes"));
    }

    @Test
    void testRemoveSongFromPlaylist() {
        libraryModel.createPlaylist("Banger Tunes");
        libraryModel.addSong(song);
        assertEquals("Daydreamer added to Banger Tunes successfully!", libraryModel.addSongToPlaylist(song, "Banger Tunes"));
        assertEquals("There is no playlist named \"Banger Tunes\".", libraryModel.removeSongFromPlaylist(song, "Banger Tunes"));
        assertEquals("There is no playlist named \"Midnight Vibes\".", libraryModel.removeSongFromPlaylist(song, "Midnight Vibes"));
    }

    @Test
    void testGetFavoriteSongs() {
        assertEquals(0, libraryModel.getFavoriteSongs().size());
        libraryModel.addSong(song);
        assertEquals(1, libraryModel.getFavoriteSongs().size());
        libraryModel.setRating(song, 5);
        assertEquals(1, libraryModel.getFavoriteSongs().size());
    }

    @Test
    void testAddSong() {
        assertEquals(0, libraryModel.getSongs().size());
        libraryModel.addSong(song);
        assertEquals(1, libraryModel.getSongs().size());
        // assert that adding duplicate songs is not possible
        libraryModel.addSong(song);
        assertEquals(1, libraryModel.getSongs().size());
    }

    @Test
    void testAddAlbum() {
        assertEquals(0, libraryModel.getAlbums().size());
        libraryModel.addAlbum(album);
        assertEquals(1, libraryModel.getAlbums().size());
        // assert that adding duplicate albums is not possible
        libraryModel.addAlbum(album);
        assertEquals(1, libraryModel.getAlbums().size());
    }

    @Test
    void testGetSongsByTitle() {
        libraryModel.addSong(song);
        assertEquals(1, libraryModel.getSongsByTitle("Daydreamer").size());
        assertEquals(0, libraryModel.getSongsByTitle("Nonexistent Song").size());
    }

    @Test
    void testGetAlbumsByTitle() {
        libraryModel.addAlbum(album);
        assertEquals(1, libraryModel.getAlbumsByTitle("19").size());
        assertEquals(0, libraryModel.getAlbumsByTitle("Nonexistent Album").size());
    }

    @Test
    void testGetSongsByArtist() {
        libraryModel.addSong(song);
        assertEquals(1, libraryModel.getSongsByArtist("Adele").size());
        assertEquals(0, libraryModel.getSongsByArtist("Nonexistent Artist").size());
    }

    @Test
    void testGetAlbumsByArtist() {
        libraryModel.addSong(song);
        assertEquals(1, libraryModel.getAlbumsByArtist("Adele").size());
        assertEquals(0, libraryModel.getAlbumsByArtist("Nonexistent Artist").size());
    }

    @Test
    void testSetRating() {
        libraryModel.addSong(song);
        libraryModel.setRating(song, 5);
        assertEquals(5, libraryModel.getSongsByTitle("Daydreamer").get(0).getRating());
    }

    @Test
    void testSetRatingForNonexistentSong() {
        libraryModel.setRating(song, 5); // Song not in library
        assertNotEquals(5, song.getRating());
    }
}