package main.view;

import main.model.Song;
import main.model.LibraryModel;

import java.util.Scanner;

public class SelectedSongMenu extends Menu {
    private LibraryModel library;
    private Song song;
    public SelectedSongMenu(Menu previousMenu, Song song) {
        super("""
        [1] Add Song To Library
        [2] Add Song To Playlist
        [3] Rate Song
        [4] Mark Song As Favorite
        [5] Play Song
        """.trim());
        colorizeBrackets();
        this.song = song;
        this.library = LibraryModel.getInstance();
        defaultOption = () -> { previousMenu.executeMenu(); };
        addOption(1, "add song to library", () -> { addSongToLibrary(); executeMenu(); });
        addOption(2, "add song to playlist", () -> { addSongToPlaylist(); executeMenu(); });
        addOption(3, "rate song", () -> { rateSong(); });
        addOption(4, "mark song as favorite", () -> { markSongAsFavorite(); executeMenu(); });
        addOption(5, "play selected song", () -> { playSong(); executeMenu(); });
    }

    private void addSongToLibrary() {
        library.addSong(this.song);
        System.out.println("Song Added To Library");
    }

    private void addSongToPlaylist() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a playlist name: ");
        String name = in.nextLine();
        library.addSong(this.song);
        String result = library.addSongToPlaylist(this.song, name);
        System.out.println(result);
    }

    private void markSongAsFavorite() {
        library.addSong(this.song);
        library.setRating(this.song, 5);
        System.out.println(this.song.getTitle() + " Marked As Favorite!");
    }

    private void rateSong() {
        RateSongMenu rateSongMenu = new RateSongMenu(this, this.song);
        rateSongMenu.executeMenu();
    }

    private void playSong() {
        String msg = library.addToRecentlyPlayed(this.song);
        System.out.println(msg);
    }
}