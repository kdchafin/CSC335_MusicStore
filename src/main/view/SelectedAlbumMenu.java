package main.view;

import main.model.Album;
import main.model.LibraryModel;

import java.util.Scanner;

public class SelectedAlbumMenu extends Menu {
    private LibraryModel library;
    private Album album;
    public SelectedAlbumMenu(Menu previousMenu, Album album) {
        super("""
        [1] Add Album to Library
        [2] Add Album To Playlist
        [3] List Songs
        """.trim());

        this.album = album;
        this.library = LibraryModel.getInstance();
        defaultOption = () -> { previousMenu.executeMenu(); };
        addOption(1, "add album to library", () -> { addAlbumToLibrary(); executeMenu(); });
        addOption(2, "add album to playlist", () -> { addAlbumToPlaylist(); executeMenu(); });
    }

    private void addAlbumToLibrary() {
        library.addAlbum(album);
        System.out.println("Album Added To Library");
    }

    private void addAlbumToPlaylist() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a playlist name: ");
        String name = in.nextLine();
        String result = library.addAlbumToPlaylist(this.album, name);
        System.out.println(result);
    }
}