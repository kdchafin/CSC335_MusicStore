package main.view;

import main.model.LibraryModel;

public class LibraryMenu extends Menu {
    public LibraryMenu(Menu previousMenu) {
        super("""
        [1] Get Songs
        [2] Get Albums
        [3] Get Artists
        [4] Get Playlists
        [5] Get Favorite Songs
        """.trim());
        LibraryModel library = LibraryModel.getInstance();
        defaultOption = () -> { previousMenu.executeMenu(); };
        addOption(1, "get songs", () -> { 
            // System.out.println("[1] Get Songs Chosen"); 
            System.out.println(library.getSongs()); 
            executeMenu();
        });
        addOption(2, "get albums", () -> { System.out.println("Get Albums"); executeMenu();});
        addOption(3, "get artists", () -> { System.out.println("Get Artists"); executeMenu();});
        addOption(4, "get playlists", () -> { System.out.println("Get Playlists"); executeMenu();});
        addOption(5, "get favorite songs", () -> { 
            System.out.println(library.getFavoriteSongs()); 
            executeMenu();
        });
    }
}
