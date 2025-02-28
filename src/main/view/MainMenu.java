package main.view;

import main.model.User;

public class MainMenu extends Menu {
    public MainMenu(Menu previousMenu, User loggedInUser) {
        super("""
        Hello user. What would you like to do?
        [1] Interact With Music Store
        [2] View Library
        [3] Switch Account
        """.trim());
        menu = menu.replace("user", loggedInUser.getUsername());
        colorizeBrackets();
        defaultOption = () -> { previousMenu.executeMenu(); };
        addOption(1, "music store", () -> { MusicStoreMenu musicStoreMenu = new MusicStoreMenu(this); musicStoreMenu.executeMenu(); });
        addOption(2, "view library", () -> { LibraryMenu libraryMenu = new LibraryMenu(this); libraryMenu.executeMenu(); });
        addOption(3, "switch account", () -> { previousMenu.executeMenu(); });
    }
}
