package main.view;

import main.model.Song;
import main.model.LibraryModel;

public class SelectedSongMenu extends Menu {
    Song song;
    public SelectedSongMenu(Menu previousMenu, Song song) {
        super("""
        [1] Add Song To Library
        [2] Add Song To Playlist
        [3] Rate Song
        [4] Mark Song As Favorite
        """.trim());
        this.song = song;
        defaultOption = () -> { previousMenu.executeMenu(); };
        addOption(1, "add song to library", () -> { addSongToLibrary(); executeMenu(); });
        addOption(2, "add song to playlist", () -> { System.out.println("Add Song To Playlist // Not Implemented Yet"); executeMenu(); });
        addOption(3, "rate song", () -> { rateSong(); });
        addOption(4, "mark song as favorite", () -> { markSongAsFavorite(); });
    }

    private void addSongToLibrary() {
        LibraryModel library = LibraryModel.getInstance();
        library.addSong(this.song);
        System.out.println("Song Added To Library");
    }

    private void markSongAsFavorite() {
        LibraryModel library = LibraryModel.getInstance();
        this.song.setRating(5);
        library.addSong(this.song);
        System.out.println(this.song.getTitle() + " Marked As Favorite!");
    }

    private void rateSong() {
        RateSongMenu rateSongMenu = new RateSongMenu(this, this.song);
        rateSongMenu.executeMenu();
    }
    
}
