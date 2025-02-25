package main.view;

public class MainMenu extends Menu {
    public MainMenu() {
        super("""
        Hello user. What would you like to do?
        [1] Interact With Music Store
        [2] View Library
        [3] Exit"""
        );
        defaultOption = () -> { System.exit(0); };
        addOption(1, "music store", () -> { MusicStoreMenu musicStoreMenu = new MusicStoreMenu(this); musicStoreMenu.executeMenu(); });
        addOption(2, "view library", () -> { LibraryMenu libraryMenu = new LibraryMenu(this); libraryMenu.executeMenu(); });
        addOption(3, "exit", () -> { System.exit(0); });
    }
}
