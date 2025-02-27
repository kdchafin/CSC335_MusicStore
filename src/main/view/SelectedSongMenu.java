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
        """.trim());
        this.song = song;
        this.library = LibraryModel.getInstance();
        defaultOption = () -> { previousMenu.executeMenu(); };
        addOption(1, "add song to library", () -> { addSongToLibrary(); executeMenu(); });
        addOption(2, "add song to playlist", () -> { addSongToPlaylist(); executeMenu(); });
        addOption(3, "rate song", () -> { rateSong(); });
        addOption(4, "mark song as favorite", () -> { markSongAsFavorite(); executeMenu(); });
    }

    private void addSongToLibrary() {
        library.addSong(this.song);
        System.out.println("Song Added To Library");
    }

    private void addSongToPlaylist() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a playlist name: ");
        String name = in.nextLine();
        library.addSongToPlaylist(this.song, name);
        System.out.println("Song Added To " + name + " uccessfully");
    }

    private void markSongAsFavorite() {
        library.setRating(this.song, 5);
        System.out.println(this.song.getTitle() + " Marked As Favorite!");
    }

    private void rateSong() {
        RateSongMenu rateSongMenu = new RateSongMenu(this, this.song);
        rateSongMenu.executeMenu();
    }
}