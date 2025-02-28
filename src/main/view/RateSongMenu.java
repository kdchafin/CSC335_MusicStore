package main.view;

import main.model.Song;
import main.model.LibraryModel;

public class RateSongMenu extends Menu {
    private Song song;
    private Menu previousMenu;
    private LibraryModel lm = LibraryModel.getInstance();
    public RateSongMenu(Menu previousMenu, Song song) {
        super("""
        [1] Rate Song 1/5
        [2] Rate Song 2/5
        [3] Rate Song 3/5
        [4] Rate Song 4/5
        [5] Rate Song 5/5
        """.trim());
        colorizeBrackets();
        this.song = song;
        this.previousMenu = previousMenu;
        defaultOption = () -> { previousMenu.executeMenu(); };
        addOption(1, "rate song 1/5", () -> { rateSong(1); });
        addOption(2, "rate song 2/5", () -> { rateSong(2); });
        addOption(3, "rate song 3/5", () -> { rateSong(3); });
        addOption(4, "rate song 4/5", () -> { rateSong(4); });
        addOption(5, "rate song 5/5", () -> { rateSong(5); });
    }

    private void rateSong(int rating) {
        System.out.println("You rated the song " + song.getTitle() + " with " + rating + "/5");
        lm.addSong(song);
        lm.setRating(song, rating);
        previousMenu.executeMenu();
    }
}
