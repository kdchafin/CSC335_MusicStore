package main.view;

import main.model.Song;
import main.model.LibraryModel;

import java.util.Scanner;

public class SelectedSongMenu extends Menu {
    private LibraryModel library;
    private Song song;
    public SelectedSongMenu(Menu previousMenu, Song song, boolean inLibrary) {
        super("""
        [2] Add Song To Playlist
        [3] Rate Song
        [4] Mark Song As Favorite
        [5] View Album Information
        """.trim());
        if(inLibrary) {
            addOption(1, "remove song from library", () -> { removeSongFromLibrary(); executeMenu(); });
            menu = "[1] Remove Song From Library\n" + menu;
            menu += "\n[6] Play Song";
            addOption(6, "play song", () -> { playSong(); executeMenu(); });
        } else {
            addOption(1, "add song to library", () -> { addSongToLibrary(); executeMenu(); });
            menu = "[1] Add Song To Library\n" + menu;
        }
        colorizeBrackets();
        this.song = song;
        this.library = LibraryModel.getInstance();
        defaultOption = () -> { previousMenu.executeMenu(); };
        
        addOption(2, "add song to playlist", () -> { addSongToPlaylist(); executeMenu(); });
        addOption(3, "rate song", () -> { rateSong(); });
        addOption(4, "mark song as favorite", () -> { markSongAsFavorite(); executeMenu(); });
        addOption(5, "view album information", () -> { viewAlbumInformation(); executeMenu(); });
    }

    private void addSongToLibrary() {
        library.addSong(this.song);
        System.out.println("Song Added To Library");
    }

    private void removeSongFromLibrary() {
        System.out.println("Song Removed From Library!");
        library.removeSong(this.song);
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

    private void viewAlbumInformation() {
        System.out.println(this.song.getAlbum().toString());
    }

    private void playSong() {
        String msg = library.addToRecentlyPlayed(this.song);
        System.out.println(msg);
    }
}