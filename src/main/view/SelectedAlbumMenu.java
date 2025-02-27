package main.view;

import main.model.Album;
import main.model.LibraryModel;

public class SelectedAlbumMenu extends Menu {
    private LibraryModel library;
    private Album album;
    public SelectedAlbumMenu(Menu previousMenu, Album album) {
        super("""
        [1] Add Album to Library
        """.trim());

        this.album = album;
        this.library = LibraryModel.getInstance();
        defaultOption = () -> { previousMenu.executeMenu(); };
        addOption(1, "add album to library", () -> { addAlbumToLibrary(); executeMenu(); });
    }

    private void addAlbumToLibrary() {
        library.addAlbum(album);
        System.out.println("Album Added To Library");
    }
}