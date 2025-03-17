package main.view;

import main.model.Album;
import main.model.LibraryModel;

public class SelectedAlbumMenu extends Menu {
    private LibraryModel library;
    private Album album;
    public SelectedAlbumMenu(Menu previousMenu, Album album, boolean inLibrary) {
        super("""
        """.trim());
        if(inLibrary) {
            addOption(1, "remove album from library", () -> { removeAlbumFromLibrary(); previousMenu.executeMenu(); });
            menu = "[1] Remove Album From Library" + menu;
        } else {
            addOption(1, "add album to library", () -> { addAlbumToLibrary(); previousMenu.executeMenu(); });
            menu = "[1] Add Album To Library\n" + menu;
        }
        colorizeBrackets();
        this.album = album;
        this.library = LibraryModel.getInstance();
        defaultOption = () -> { previousMenu.executeMenu(); };
    }

    private void addAlbumToLibrary() {
        library.addAlbum(album);
        System.out.println("Album Added To Library");
    }

    private void removeAlbumFromLibrary() {
        // library.removeAlbum(album);
        System.out.println("Album Removed From Library");
    }
}