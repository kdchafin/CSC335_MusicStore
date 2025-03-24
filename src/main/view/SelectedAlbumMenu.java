package main.view;

import java.util.Scanner;

import main.model.Album;
import main.model.Song;
import main.model.LibraryModel;

public class SelectedAlbumMenu extends Menu {
    private LibraryModel library;
    private Album album;
    public SelectedAlbumMenu(Menu previousMenu, Album album, boolean inLibrary) {
        super("");
        if(inLibrary) {
            addOption(1, "remove album from library", () -> { removeAlbumFromLibrary(); previousMenu.executeMenu(); });
            addOption(2, "add album to playlist", () -> { addAlbumToPlaylist(); previousMenu.executeMenu(); });
            menu = "[1] Remove Album From Library\n" + menu;
            menu += "[2] Add Album To Playlist";
        } else {
            addOption(1, "add album to library", () -> { addAlbumToLibrary(); previousMenu.executeMenu(); });
            menu = "[1] Add Album To Library\n" + menu;
        }
        colorizeBrackets();
        this.album = album;
        this.library = Menu.getUser().getLibraryModel();
        defaultOption = () -> { previousMenu.executeMenu(); };
    }

    private void addAlbumToLibrary() {
        library.addAlbum(album);
        System.out.println("Album Added To Library");
    }

    private void removeAlbumFromLibrary() {
        library.removeAlbum(album);
        System.out.println("Album Removed From Library");
    }

    private void addAlbumToPlaylist() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a playlist name: ");
        String name = in.nextLine();
        library.addAlbum(album);
        for(Song s: album.getAlbumSongs()) {
            String temp = library.addSongToPlaylist(s, name);
            if(!temp.equals(s.getTitle() + " added to " + name + " successfully!")) {
                System.out.println(temp);
                return;
            }
        }
        System.out.println(album.getTitle() + " added to " + name + " successfully!");
    }
}